package btrust.onboardingProcess.api;

import btrust.onboardingProcess.api.variables.PreProcess;
import groovy.json.JsonSlurper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiRequests {

    Response apiRequest;
    private final String btrustToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1c2VyRGF0YSI6IntcImlkXCI6ODMsXCJjb21wYW55SWRcIjo0LFwiYWN0aXZlU3ViQ29tcGFueVwiOjAsXCJyb2xlc1wiOls3XSxcInBlcm1pc3Npb25zXCI6W3tcImlkXCI6MCxcInJvbGVJZFwiOjAsXCJ0eXBlXCI6MCxcInR5cGVJZFwiOjAsXCJjYW5SZWFkXCI6ZmFsc2UsXCJjYW5FZGl0XCI6ZmFsc2UsXCJjYW5DcmVhdGVcIjpmYWxzZX0se1wiaWRcIjowLFwicm9sZUlkXCI6MCxcInR5cGVcIjo2LFwidHlwZUlkXCI6MCxcImNhblJlYWRcIjp0cnVlLFwiY2FuRWRpdFwiOnRydWUsXCJjYW5DcmVhdGVcIjp0cnVlfV19IiwiaWF0IjoxNjIyNTU0NjUxLCJleHAiOjMxOTkzNTQ2NTF9.S53gOLQJ9XqD3UsFZzx_pn9hOJ6hpCM8CDLHI2rXvPR256GLDj4Y0Eb4YKSWrERs8hJlvLA9okxLkJJ6I1JgxHzgrwgb2gdMyDPEji6rGXRijKzsbwWYUzT1xe78p0cb-LUGfrcfM80HK7qV2CePZ_ho7C9Jczg_evV56xVIoN10eZrrpzUDEJ-dfTjtXSK93AkGeh6t1n3Lm8xhDYPrZ2fy59Da4w9MmPzUwl3VaAxIawum2VRM2U6p15hH4bB_p1HZaVdA_2GmXf74KuRkguyPbbDWZI7g9LVU5POj6JGxTsRtDOaGBeFNsRXKVkvb2DizMxCl1AhXPwf_G9-Oaw";


    public String loginToNewSecurityBtrustPage(String username, String password, String requestId) {
        String requestBody = "{\n" +
                "\"username\":\"" + username + "\",\n" +
                "\"password\":\"" + password + "\",\n" +
                "\"request_id\":\"" + requestId + "\"\n" +
                "}";


        apiRequest = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                //.when()
                //.log()
                //.all()
                .post("https://qa-authorization.scanovateoncloud.com/consent")
                .then()
                .statusCode(200)
                .extract()
                .response();



        if (apiRequest.getStatusCode() == 200) {
            JsonPath jsonPath = apiRequest.jsonPath();
            System.out.println("generated new token");
            return jsonPath.getString("access_token");
        } else {
            throw new RuntimeException("Login failed. Status code: " + apiRequest.getStatusCode());
        }
    }

    public String loginToBtrustPage(int companyId, String password, String username) {
        String requestBody = "{\n" +
                "\"companyId\":\"" + companyId + "\",\n" +
                "\"password\":\"" + password + "\",\n" +
                "\"userName\":\"" + username + "\"\n" +
                "}";


        apiRequest = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                //.when()
                //.log()
                //.all()
                .post("https://qa-nginx-api.scanovateoncloud.com:443/api/v3/token")
                .then()
                .statusCode(200)
                .extract()
                .response();//


        if (apiRequest.getStatusCode() == 200) {
            JsonPath jsonPath = apiRequest.jsonPath();
            return jsonPath.getString("token");
        } else {
            throw new RuntimeException("Login failed. Status code: " + apiRequest.getStatusCode());
        }

    }


    public Response createNewEmailTemplate(String emailTemplateName, String subject, boolean withPortal, String viewScript, String htmlScript, boolean enableEditing) {
        String requestBody = "{\n" +
                "\"name\":\"" + emailTemplateName + "\",\n" +
                "\"subject\":\"" + subject + "\",\n" +
                "\"withPortal\":\"" + withPortal + "\",\n" +
                "\"viewScript\":\"" + viewScript + "\",\n" +
                "\"htmlScript\":\"" + htmlScript + "\",\n" +
                "\"enableEditing\":" + enableEditing + "\n" +
                "}";


        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .header("X-Company-Id", System.getProperty("COMPANY_ID"))
                .header("Content-Type", "application/json")
                .body(requestBody)
                //.when()
                //.log()
                //.all()
                .put("https://qa-nginx-gateway.scanovateoncloud.com/email-builder")
                .then()
                .statusCode(200)
                .extract()
                .response();


        return apiRequest;
    }


    public Response updateEmailTemplate(String emailTemplateName, String subject, boolean withPortal, String viewScript, String htmlScript, boolean enableEditing, int templateId) {
        String requestBody = "{\n" +
                "\"name\":\"" + emailTemplateName + "\",\n" +
                "\"id\":\"" + templateId + "\",\n" +
                "\"subject\":\"" + subject + "\",\n" +
                "\"withPortal\":\"" + withPortal + "\",\n" +
                "\"viewScript\":\"" + viewScript + "\",\n" +
                "\"htmlScript\":\"" + htmlScript + "\",\n" +
                "\"enableEditing\":" + enableEditing + "\n" +
                "}";


        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .header("X-Company-Id", System.getProperty("COMPANY_ID"))
                .header("Content-Type", "application/json")
                .body(requestBody)
                //.when()
                //.log()
                //.all()
                .post("https://qa-nginx-gateway.scanovateoncloud.com/email-builder/" + templateId + "/update-template")
                .then()
                .statusCode(200)
                .extract()
                .response();


        return apiRequest;
    }


    public Response deleteEmailTemplate(int templateId) {

        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .header("X-Company-Id", System.getProperty("COMPANY_ID"))
                .header("Content-Type", "application/json")
                //.when()
                //.log()
                //.all()
                .delete("https://qa-nginx-gateway.scanovateoncloud.com/email-builder/" + templateId + "/delete")
                .then()
                .statusCode(200)
                .extract()
                .response();


        return apiRequest;
    }


    public Response createMobileInteractionNewFlow(String workflowName, String flow, String viewScript, int statusNumber) {
        String requestBody = "{\n" +
                "\"name\":\"" + workflowName + "\",\n" +
                "\"flow\":\"" + flow + "\",\n" +
                "\"viewScript\":\"" + viewScript + "\",\n" +
                "\"status\":" + statusNumber + "\n" +
                "}";


        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .header("X-Company-Id", System.getProperty("COMPANY_ID"))
                .header("Content-Type", "application/json")
                .body(requestBody)
                //.when()
                //.log()
                //.all()
                .put("https://qa-nginx-gateway.scanovateoncloud.com/flow")
                .then()
                .statusCode(200)
                .extract()
                .response();

//      System.out.println(response.getBody().asString());

        return apiRequest;
    }


    public Response UpdateMobileInteractionNewFlow(int idNumber, String name, String flow, String viewScript, int status) {
        String requestBody = "{\n" +
                "\"id\":\"" + idNumber + "\",\n" +
                "\"name\":\"" + name + "\",\n" +
                "\"flow\":\"" + flow + "\",\n" +
                "\"viewScript\":\"" + viewScript + "\",\n" +
                "\"status\":\"" + status + "\"\n" +
                "}";


        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .header("X-Company-Id", System.getProperty("COMPANY_ID"))
                .header("Content-Type", "application/json")
                .body(requestBody)
                //.when()
                //.log()
                //.all()
                .post("https://qa-nginx-gateway.scanovateoncloud.com/flow/" + idNumber + "/update")
                .then()
                .statusCode(200)
                .extract()
                .response();

//      System.out.println(apiRequest.getBody().asString());

        return apiRequest;
    }

    public Response initPutForSessionId(int flowId, String sessionId) {
        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .multiPart("flowId", flowId)
                .multiPart("sessionId", sessionId)
//                .log()
//                .all()
                .put("https://qa-nginx-console.scanovateoncloud.com/server/inquiries/")
                .then()
                .statusCode(200)
                .extract()
                .response();

//        System.out.println(apiRequest.getBody().asString());
        return apiRequest;
    }


    public Response initPutForSessionIdNew(int flowId, String sessionId, String companyUuid) {
        apiRequest = given()

                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .header("X-Company-Id", System.getProperty("COMPANY_ID"))

                .multiPart("flowId", flowId)
                .multiPart("sessionId", sessionId)
                .multiPart("companyUuid", companyUuid)

//                .log()
//                .all()
                .put("https://qa-nginx-gateway.scanovateoncloud.com/inquiries")
                .then()
                .statusCode(200)
                .extract()
                .response();

//        System.out.println(apiRequest.getBody().asString());
        return apiRequest;
    }


    public Response initPutForSessionId(int flowId, String missingFieldsKey, String caseId, String sessionIdKey, String sessionIdValue) {
        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .multiPart("flowId", flowId)
                .multiPart(missingFieldsKey, caseId)
                .multiPart(sessionIdKey, sessionIdValue)
//                .log()
//                .all()
                .put("https://qa-nginx-console.scanovateoncloud.com/server/inquiries/")
                .then()
                .statusCode(200)
                .extract()
                .response();

//        System.out.println(apiRequest.getBody().asString());
        return apiRequest;
    }


    public Response initPutForSessionIdNew(int flowId, String missingFieldsKey, String caseId, String sessionIdKey, String sessionIdValue,String processId) {
        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .header("X-Company-Id", System.getProperty("COMPANY_ID"))
                .header("X-Session-Id", sessionIdValue)

                .multiPart("companyUuid", System.getProperty("COMPANY_ID"))
                .multiPart("flowId", flowId)
                .multiPart("processId", processId)
                .multiPart(missingFieldsKey, caseId)
                .multiPart(sessionIdKey, sessionIdValue)
//                .log()
//                .all()
                .put("https://qa-nginx-gateway.scanovateoncloud.com/inquiries")
                .then()
//                .statusCode(200)
                .extract()
                .response();

        return apiRequest;
    }

    public Response putQuestionnaireRequest(int flowId, File questionnaireFile, String sessionId) {
        JsonSlurper jsonSlurper = new JsonSlurper();
        Map<String, Object> map = new HashMap<>();
        map.put("flowId", flowId);
        map.put("completeForm1", jsonSlurper.parse(questionnaireFile));
        map.put("sessionId", sessionId);

        RequestSpecBuilder builder = new RequestSpecBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            builder.addParam(entry.getKey(), entry.getValue());
        }
        RequestSpecification requestSpec = builder.build();
        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .header("Content-Type", "multipart/json")
                .spec(requestSpec)
//                .log()
//                .all()
                .put("https://qa-nginx-console.scanovateoncloud.com/server/inquiries/")
                .then()
                .statusCode(200)
                .extract()
                .response();

//        System.out.println(apiRequest.getBody().asString());
        return apiRequest;
    }

    public Response postJsonFile(Object filePath) {
        apiRequest = given()
                .contentType(ContentType.JSON)
                .when()
                .body(filePath)
                .post("https://webhook-qa.scanovate.com/")
                .then()
                .statusCode(200)
                .extract()
                .response();

//        System.out.println(apiRequest.getBody().asString());
        return apiRequest;
    }

    public Response putRequestForImages(PreProcess preProcess, File image) {
        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .multiPart("flowId", preProcess.getFlowId())
                .multiPart(preProcess.getMissingFields(), image)
                .multiPart("sessionId", preProcess.getSessionId())
//                .log()
//                .all()
                .put("https://qa-nginx-console.scanovateoncloud.com/server/inquiries/")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(apiRequest.getBody().asString());
        return apiRequest;
    }

    public Response callbackToGetToken(String sessionId) {
        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .header("X-Company-Id", System.getProperty("COMPANY_ID"))
                .when()
                .get("https://qa-nginx-gateway.scanovateoncloud.com/inquiries/" + sessionId + "/callback")

                //.get("https://qa-nginx-gateway.scanovateoncloud.com/api/v3/mobile_interaction/" + sessionId + "/token")
                .then()
                .statusCode(200)
                .extract()
                .response();

//        System.out.println(apiRequest.getBody().asString());
        return apiRequest;
    }

    public void clientTranslationButtonSet(boolean displayed) throws ParseException {
        System.out.println("setting translation display to `" + displayed);
        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .when()
                .get("https://qa-nginx-console.scanovateoncloud.com/server/settings/client-config")
                .then()
                .statusCode(200)
                .extract()
                .response();
        JsonPath jp = apiRequest.jsonPath();
        JSONObject configData = (JSONObject) new JSONParser().parse((String) jp.get("data"));
        configData.put("showTranslationsButton", displayed);
        JSONObject newConfig = new JSONObject();
        newConfig.put("name", configData.toJSONString());
        apiRequest = given().header("Authorization", System.getProperty("ACCESS_TOKEN")).when().body(newConfig.toJSONString())
                .post("https://qa-nginx-console.scanovateoncloud.com/server/settings/client-config")
                .then().statusCode(200).extract().response();
        System.out.println("mobile config update response:" + apiRequest.getBody().asString());
//        System.out.println(newConfig.toJSONString());
    }

    public Response getV2ResultRequest(String token) {
        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .header("X-Company-Id", System.getProperty("COMPANY_ID"))
                .when()
                .get("https://qa-nginx-gateway.scanovateoncloud.com/api/v3/mobile_interaction/v2/" + token +"/results")
                .then()
                .statusCode(200)
                .extract()
                .response();

//        System.out.println(apiRequest.getBody().asString());
        return apiRequest;
    }

    public Response getV1ResultRequest(String token) {
        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .when()
                .get("https://qa-nginx-console.scanovateoncloud.com/server/results/" + token)
                .then()
                .statusCode(200)
                .extract()
                .response();

//        System.out.println(apiRequest.getBody().asString());
        return apiRequest;
    }

    public Response getHarelResultRequest(String token) {
        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .when()
                .get("https://qa-nginx-console.scanovateoncloud.com/server/harel/" + token)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(apiRequest.getBody().asString());
        return apiRequest;
    }
}
