import courier.OrderClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderListTest {

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Get order list")
    public void getOrderListTest() {
        OrderClient orderClient = new OrderClient();
        Response response = orderClient.getOrderList();
        assertEquals("Неверный код ответа",SC_OK, response.statusCode());
        assertNotNull("Ошибка, у заказа нет id",response.body());

    }
}
