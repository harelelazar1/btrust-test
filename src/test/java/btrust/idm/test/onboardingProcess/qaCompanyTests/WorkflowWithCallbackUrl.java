package btrust.idm.test.onboardingProcess.qaCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.ImageInjectionFunctionPage;
import btrust.onboardingProcess.ui.pagesObject.OCRPage;
import btrust.onboardingProcess.ui.pagesObject.ScanLivenessPage;
import btrust.onboardingProcess.ui.pagesObject.ScanResultPage;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class WorkflowWithCallbackUrl extends BaseIdmClientTest {

    @BeforeMethod
    public void createToken() {
        String token = tokenWorkflowWithUrl("446", "?callback_url=https://www.ynet.co.il/");
        driver.navigate().to(token);
    }

    @Test(description = "call back url should return at the end of the flow")
    @Description("url should appear at the end of the flow instead of the regular result screen after full onboarding flow")
    public void t01_urlCallbackTest() throws IOException, InterruptedException {
        OCRPage ocrPage = new OCRPage(driver);
        ocrPage.clickOnButton("Scan Driving License");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/ilDL/harel-ilDL.jpg", "./ocr/dl/harel-ilDLBack.jpg");
        ScanResultPage scanResult = new ScanResultPage(driver);
        scanResult.clickOnButton("Continue");
        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
        scanLiveness.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/harel/harelLivenessCenter.jpg");
        Thread.sleep(6000);
        Assert.assertTrue(driver.getCurrentUrl().contains("https://m.ynet.co.il/"));
    }


    @Step("Create link for workflow: {number}")
    public String tokenWorkflowWithUrl(String number, String Parameters) {
        RestAssured.baseURI = "https://btrustqa.scanovate.com/";
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.defaultParser = Parser.JSON;
        Response response = given()
                .header("Authorization", "Bearer 4122ba73-3304-4b9d-a958-aa07f34c4dc8")
                .header("Content-Type", "application/json")
                .log()
                .all()
                .when()
                .get("api/flow/" + number + "/link" + Parameters)
                .then()
                .statusCode(200)
                .extract()
                .response();

        String data = response.path("data");
        System.out.println(data);
        System.out.println(sessionId);
        return data;
    }
}