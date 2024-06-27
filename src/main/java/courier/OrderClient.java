package courier;

import io.restassured.response.Response;
import model.Order;

import static io.restassured.RestAssured.given;

public class OrderClient {
    private Order order;
    private static final String CREATE_ORDER_ENDPOINT = "/api/v1/orders";
    private static final String GET_ORDER_LIST_ENDPOINT = "/api/v1/orders";

    public Response createOrder(Order order) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(CREATE_ORDER_ENDPOINT);
    }
    public Response getOrderList() {
        return given()
                .header("Content-type", "application/json")
                .and()
                .get(GET_ORDER_LIST_ENDPOINT);
    }
}
