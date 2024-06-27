import courier.CourierClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Courier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static courier.CourierGenerator.*;
import static model.CourierCreds.credsFromCourier;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class CreateCourierTest {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    private CourierClient courierClient;
    private Integer id;
    @Before
    public void setUp () {
        RestAssured.baseURI = BASE_URL;
    }
    @Test
    @DisplayName("Check create random courier")
    public void createCourier() {
        Courier courier = randomCourier();
        courierClient = new CourierClient();
        Response response = courierClient.create(courier);
        assertEquals("Неверный статус код", SC_CREATED, response.statusCode());
        Response loginResponse = courierClient.login(credsFromCourier(courier));
        id = loginResponse.path("id");
        assertEquals("Неверный статус код", SC_OK, loginResponse.statusCode());
    }

    @Test
    @DisplayName("Check create same courier")
    public void createSameCourier () {
        Courier courier = sameCourier();
        courierClient = new CourierClient();
        Response response = courierClient.create(courier);
        id = response.path("id");
        Response sameResponse = courierClient.create(courier);
        assertEquals("Неверный статус код", SC_CONFLICT, sameResponse.statusCode());

    }
    @Test
    @DisplayName("Check create without 'login' courier")
    public  void createCourierWithoutLogin () {
        Courier courier = withoutLoginCourier();
        courierClient = new CourierClient();
        Response response = courierClient.create(courier);
        id = response.path("id");
        assertEquals("Неверный статус код", SC_BAD_REQUEST, response.statusCode());
    }
    @Test
    @DisplayName("Check create without 'password' courier")
    public  void createCourierWithoutPassword () {
        Courier courier = withoutPasswordCourier();
        courierClient = new CourierClient();
        Response response = courierClient.create(courier);
        id = response.path("id");
        assertEquals("Неверный статус код", SC_BAD_REQUEST, response.statusCode());
    }
    @Test
    @DisplayName("Check status text 'OK' on create courier")
    public void createCourierTextResponseOK() {
        Courier courier = randomCourier();
        courierClient = new CourierClient();
        Response response = courierClient.create(courier);
        response.then().assertThat().body("ok", equalTo(true));
        id = response.path("id");

    }
    @After
    public void  tearDown () {
        courierClient.delete(id);
    }
}
