package courier;

import io.restassured.response.Response;
import model.Courier;
import model.CourierCreds;

import static io.restassured.RestAssured.given;

public class CourierClient {
    private static final String CREATE_ENDPOINT = "/api/v1/courier";
    private static final String LOGIN_ENDPOINT = "/api/v1/courier/login";
    private static final String DELETE_ENDPOINT = "/api/v1/courier/";

    public Response create (Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(CREATE_ENDPOINT);

    }
    public Response login (CourierCreds creds) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(creds)
                .when()
                .post(LOGIN_ENDPOINT);
    }
    public void delete (Integer id) {
        given()
                .header("Content-type", "application/json")
                .and()
                .queryParam("id", id)
                .delete(DELETE_ENDPOINT);

    }
}
