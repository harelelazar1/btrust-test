package btrust.onboardingProcess.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GeneraliApi {

    Response apiRequest;


    public Response gatewayEntityTypes() {

        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .header("Content-Type", "application/json")

                //.when()
                //.log()
                //.all()
                .get("https://gateway.scanovateai.com/api/v3/entity_types")
                .then()
                .statusCode(200)
                .extract()
                .response();

//      System.out.println(response.getBody().asString());
        return apiRequest;
    }


}
