import courier.CourierClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Courier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static courier.CourierGenerator.randomCourier;
import static model.CourierCreds.*;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginCourierTest {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    private CourierClient courierClient;
    private Integer id;

    @Before
    public void setUp () {
        RestAssured.baseURI = BASE_URL;

    }
    @Test
    @DisplayName("Check login courier and check id courier")
    public void loginCourierAndCheckId() {
        Courier courier = randomCourier();
        courierClient = new CourierClient();
        courierClient.create(courier);
        Response loginResponse = courierClient.login(credsFromCourier(courier));
        id = loginResponse.path("id");
        assertEquals("Неверный статус код", SC_OK, loginResponse.statusCode());
        assertNotNull("id не создан", id);
    }
    @Test
    @DisplayName("Check login courier without 'login'")
    public void loginCourierWithoutLogin() {
        Courier courier = randomCourier();
        courierClient = new CourierClient();
        courierClient.create(courier);
        id = courierClient.login(credsFromCourier(courier)).path("id");
        Response loginResponse = courierClient.login(credsFromCourierWithoutLogin(courier));
        assertEquals("Неверный статус код", SC_BAD_REQUEST, loginResponse.statusCode());
    }
    @Test
    @DisplayName("Check login courier without 'password'")
    public void loginCourierWithoutPassword() {
        Courier courier = randomCourier();
        courierClient = new CourierClient();
        courierClient.create(courier);
        id = courierClient.login(credsFromCourier(courier)).path("id");
        Response loginResponse = courierClient.login(credsFromCourierWithoutPassword(courier));
        assertEquals("Неверный статус код", SC_BAD_REQUEST, loginResponse.statusCode());
    }
    @Test
    @DisplayName("Check login courier without create courier")
    public void loginCourierWithoutCreateCourier() {
        courierClient = new CourierClient();
        Response loginResponse = courierClient.login(credsFromCourierWithoutCreateCourier());
        assertEquals("Неверный статус код", SC_NOT_FOUND, loginResponse.statusCode());
    }
    @After
    public void  tearDown () {
        courierClient.delete(id);
    }
}
