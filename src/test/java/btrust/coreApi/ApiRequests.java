package btrust.coreApi;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiRequests {

    Response apiRequest;

    private final String btrustToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1c2VyRGF0YSI6IntcImlkXCI6ODMsXCJjb21wYW55SWRcIjo0LFwiYWN0aXZlU3ViQ29tcGFueVwiOjAsXCJyb2xlc1wiOls3XSxcInBlcm1pc3Npb25zXCI6W3tcImlkXCI6MCxcInJvbGVJZFwiOjAsXCJ0eXBlXCI6MCxcInR5cGVJZFwiOjAsXCJjYW5SZWFkXCI6ZmFsc2UsXCJjYW5FZGl0XCI6ZmFsc2UsXCJjYW5DcmVhdGVcIjpmYWxzZX0se1wiaWRcIjowLFwicm9sZUlkXCI6MCxcInR5cGVcIjo2LFwidHlwZUlkXCI6MCxcImNhblJlYWRcIjp0cnVlLFwiY2FuRWRpdFwiOnRydWUsXCJjYW5DcmVhdGVcIjp0cnVlfV19IiwiaWF0IjoxNjIyNTU0NjUxLCJleHAiOjMxOTkzNTQ2NTF9.S53gOLQJ9XqD3UsFZzx_pn9hOJ6hpCM8CDLHI2rXvPR256GLDj4Y0Eb4YKSWrERs8hJlvLA9okxLkJJ6I1JgxHzgrwgb2gdMyDPEji6rGXRijKzsbwWYUzT1xe78p0cb-LUGfrcfM80HK7qV2CePZ_ho7C9Jczg_evV56xVIoN10eZrrpzUDEJ-dfTjtXSK93AkGeh6t1n3Lm8xhDYPrZ2fy59Da4w9MmPzUwl3VaAxIawum2VRM2U6p15hH4bB_p1HZaVdA_2GmXf74KuRkguyPbbDWZI7g9LVU5POj6JGxTsRtDOaGBeFNsRXKVkvb2DizMxCl1AhXPwf_G9-Oaw";


    public Response getCaseContact(int caseId) {
        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .when()
                .get("https://qa-nginx-gateway.scanovateoncloud.com/api/v3/cases/" + caseId + "/get_case_contacts")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(apiRequest.getBody().asString());
        return apiRequest;
    }


    public Response createPortalLink(int caseId, int contactId) {
        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .queryParam("contactId", contactId)
                .when()
                .get("https://qa-nginx-gateway.scanovateoncloud.com/api/v3/cases/"+ caseId +"/portal_link")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(apiRequest.getBody().asString());
        return apiRequest;
    }


    public Response getSession(String caseId, String contactId) {

        String requestBody = "{\n" +
                "\"caseId\":\"" + caseId + "\",\n" +
                "\"contactId\":\"" + contactId + "\"\n" +
                "}";

        apiRequest = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .header("Content-Type", "application/json")
                .body(requestBody)
                //   .when()
                .post("https://qa-nginx-gateway.scanovateoncloud.com/api/get-session")
                .then()
                .statusCode(200)
                .extract()
                .response();

//        System.out.println(apiRequest.getBody().asString());
        return apiRequest;
    }


    public Response getSessionStatus(String sessionId) {

        String requestBody = "{\n" +
                "\"sessionId\":\"" + sessionId + "\n" +
                "}";

        apiRequest = given()
                .header("Authorization", btrustToken)
                .header("Content-Type", "application/json")
                .body(requestBody)
                //   .when()
                .post("https://qa-nginx-portal.scanovateoncloud.com/api/get-session-status")
                .then()
                .statusCode(200)
                .extract()
                .response();

//        System.out.println(apiRequest.getBody().asString());
        return apiRequest;
    }


}
