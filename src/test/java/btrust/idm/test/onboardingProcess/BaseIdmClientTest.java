package btrust.idm.test.onboardingProcess;

import btrust.BaseMobileViewTest;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseIdmClientTest extends BaseMobileViewTest {

    protected String flowLink;
    protected String link;
    protected static String sessionId;
    protected int caseNumber;
    protected String caseId;
    protected String qaApiKey = "4122ba73-3304-4b9d-a958-aa07f34c4dc8";
    protected String shlomoApiKey = "be2e6bfd-e986-4b60-8124-662b01a4bce2";
    protected String harelApiKey = "2e986511-fd5d-4e4f-ba27-f2818eb2ddb9";
    protected String nyKreditApiKey = "3603db80-804d-457f-aab1-50823892db64";
    protected String mimunYashirApiKey = "6c718d02-a4d8-4125-9a10-56b9260999f4";

    @Step("Create link to workflow: {number}")
    public String createLinkToFlow(String baseUrl, String apiKey, String number) {
        RestAssured.baseURI = baseUrl;
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.defaultParser = Parser.JSON;
        Response response = given()
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .log()
                .all()
                .when()
                .get("api/flow/" + number + "/link")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
        JsonPath jsonPath = response.jsonPath();
        flowLink = jsonPath.getString("data");
        System.out.println("link: " + flowLink);
        sessionId = (flowLink.split("process_id="))[1];
        System.out.println("sessionId: " + sessionId);
        return flowLink;
    }

    @Step("Get Case ID")
    public String getCaseId(String baseUrl, String apiKey) {
        RestAssured.baseURI = baseUrl;
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.defaultParser = Parser.JSON;
        Response response = given()
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .log()
                .all()
                .when()
                .get("api/inquiries/" + sessionId + "/inquiryId")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
        JsonPath jsonPath = response.jsonPath();
        caseNumber = jsonPath.getInt("data");
        caseId = String.valueOf(caseNumber);
        System.out.println("caseID: " + caseId);
        return caseId;
    }

}