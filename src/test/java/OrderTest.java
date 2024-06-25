import courier.OrderClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class OrderTest {
    Order order;
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    private int track;


    public OrderTest(Order order) {
        this.order = order;
    }
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }
    @Parameterized.Parameters
    public static Object[][] getText() {
        return new Object[][]{
                {new Order ("Валера", "Иванов", "Вавилова 12", "4", "89991112233", 5, "2024-06-30", "Стучать в дверь 3 раза", new String[]{"BLACK"})},
                {new Order("Иван", "Гудков", "Панфилова 12", "10", "89998887766", 3, "2024-07-30", "Стучать в дверь 3 раза",new String[]{ "GRAY"})},
                {new Order("Сергей", "Панфилов", "Горького 10", "15", "89158887766", 2, "2024-07-1", "Стучать в дверь 3 раза",new String[]{ "BlACK", "GRAY"})},
                {new Order("Марат", "Кузьмин", "Дмитрова 12", "19", "89168887766", 1, "2024-07-12", "Стучать в дверь 3 раза",new String[]{})}
        };
    }



    @Test
    @DisplayName("Check create order with test data")
    public void createOrderTest () {
        OrderClient orderClient = new OrderClient();
        Response response =orderClient.createOrder(order);
        track = response.path("track");
                assertEquals("Неверный код ответа",SC_CREATED, response.statusCode());
                assertNotNull("Не найден номер заказа", track);

    }

}