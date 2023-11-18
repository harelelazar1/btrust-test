package btrust.onboardingProcess.ui.test.btrust1.mobileInteraction;

import btrust.BaseMobileViewTest;
import btrust.onboardingProcess.ui.pagesObject.ImageInjectionFunctionPage;
import btrust.onboardingProcess.ui.pagesObject.OCRPage;
import btrust.onboardingProcess.ui.pagesObject.ScanResultPage;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MobileInteractionUIBase extends BaseMobileViewTest {
    private final String AUTH_TOKEN = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1c2VyRGF0YSI6IntcImlkXCI6ODMsXCJjb21wYW55SWRcIjo0LFwiYWN0aXZlU3ViQ29tcGFueVwiOjAsXCJyb2xlc1wiOls3XSxcInBlcm1pc3Npb25zXCI6W3tcImlkXCI6MCxcInJvbGVJZFwiOjAsXCJ0eXBlXCI6MCxcInR5cGVJZFwiOjAsXCJjYW5SZWFkXCI6ZmFsc2UsXCJjYW5FZGl0XCI6ZmFsc2UsXCJjYW5DcmVhdGVcIjpmYWxzZX0se1wiaWRcIjowLFwicm9sZUlkXCI6MCxcInR5cGVcIjo2LFwidHlwZUlkXCI6MCxcImNhblJlYWRcIjp0cnVlLFwiY2FuRWRpdFwiOnRydWUsXCJjYW5DcmVhdGVcIjp0cnVlfV19IiwiaWF0IjoxNjIyNTU0NjUxLCJleHAiOjMxOTkzNTQ2NTF9.S53gOLQJ9XqD3UsFZzx_pn9hOJ6hpCM8CDLHI2rXvPR256GLDj4Y0Eb4YKSWrERs8hJlvLA9okxLkJJ6I1JgxHzgrwgb2gdMyDPEji6rGXRijKzsbwWYUzT1xe78p0cb-LUGfrcfM80HK7qV2CePZ_ho7C9Jczg_evV56xVIoN10eZrrpzUDEJ-dfTjtXSK93AkGeh6t1n3Lm8xhDYPrZ2fy59Da4w9MmPzUwl3VaAxIawum2VRM2U6p15hH4bB_p1HZaVdA_2GmXf74KuRkguyPbbDWZI7g9LVU5POj6JGxTsRtDOaGBeFNsRXKVkvb2DizMxCl1AhXPwf_G9-Oaw";
    protected String sessionId;
    protected String mobileFlowLink;
    protected Response response;
    protected JsonPath jsonPath;

    public MobileInteractionUIBase() {
        mobileFlowLink = null;
        response = null;
        jsonPath = null;
        sessionId = null;
    }

    public String createLinkToFlow(int flowNumber) {
        response = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .header("X-Company-ID", "63f225246ef5df9ba8306568")
//                .log()
//                .all()
                .get(" https://qa-nginx-gateway.scanovateoncloud.com/flow/" + flowNumber + "/link")
                .then()
                .statusCode(200)
                .extract()
                .response();

//        System.out.println(response.getBody().asString());
        jsonPath = response.jsonPath();
        sessionId = jsonPath.getString("data").split("process_id=")[1];
        System.out.println("Session Id: " + sessionId);
        return jsonPath.getString("data");
    }

    public String createLinkWithQueryParams(int flowNumber,String urlQuery) {
        response = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .header("X-Company-ID", "63f225246ef5df9ba8306568")
                .header("Content-Type", "application/json")
                .body(urlQuery)
                .post("https://qa-nginx-gateway.scanovateoncloud.com:443/api/v3/mobile_interaction/" + flowNumber + "/link")
                .then()
                .statusCode(200)
                .extract()
                .response();
        jsonPath = response.jsonPath();
        sessionId = jsonPath.getString("link").split("process_id=")[1];
        System.out.println("Session Id: " + sessionId);
        return jsonPath.getString("link");
    }

    public String createSessionId(int flowNumber) {
        response = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .header("X-Company-ID", "63f225246ef5df9ba8306568")
//                .log()
//                .all()
                .get("https://qa-nginx-gateway.scanovateoncloud.com/server/flow/" + flowNumber + "/link")
                .then()
                .statusCode(200)
                .extract()
                .response();

//        System.out.println(response.getBody().asString());
        jsonPath = response.jsonPath();
        sessionId = jsonPath.getString("data").split("process_id=")[1];
        System.out.println("Session Id: " + sessionId);
        return sessionId;
    }

    public void runOcr(int flowId, String frontImage, String backImage, OCRPage ocrPage,
                   ImageInjectionFunctionPage imageInjectionFunctionPage, String overrideQuery) throws IOException {
        driver.get(createLinkWithQueryParams(flowId,overrideQuery));
        assertTrue(ocrPage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        assertEquals(ocrPage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        ocrPage.clickOnButton("Scan Israel ID");
        imageInjectionFunctionPage.scanOCR2Sides(frontImage, backImage);
    }

}
