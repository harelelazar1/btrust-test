package btrust.idm.test.onboardingProcess.qaCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class IdentityOnboardingWorkflowTest extends BaseIdmClientTest {

    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", qaApiKey, "121");
        driver.navigate().to(link);
    }

    @Test(description = "E2E workflow 121")
    @Description("E2E workflow 121")
    public void t01_identityFlow() throws IOException {
        deleteIdentity();
        OCRPage ocrPage = new OCRPage(driver);
        Assert.assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        ocrPage.clickOnButton("Scan Israel ID");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR("./ocr/blueID/liad/blueID_color.jpg", "front");
        ScanResultPage scanResult = new ScanResultPage(driver);
        Assert.assertTrue(scanResult.progressBarIsDisplayed());
        Assert.assertEquals(scanResult.resultsTitle(), "Scan Results");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "Document image");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        Assert.assertEquals(scanResult.results("blue id", "Citizenship"), "אזרחות ישראלית");
        Assert.assertEquals(scanResult.results("blue id", "Last name"), "טובי");
        Assert.assertEquals(scanResult.results("blue id", "First name"), "ליעד");
        Assert.assertEquals(scanResult.results("blue id", "ID Number"), "307922328");
        Assert.assertEquals(scanResult.results("blue id", "Date of birth"), "17.01.1993");
        Assert.assertEquals(scanResult.results("blue id", "Issuing Date"), "28.01.2015");
        Assert.assertEquals(scanResult.results("blue id", "Expiration Date"), "28.01.2025");
        Assert.assertEquals(scanResult.results("blue id", "Place of birth"), "ישראל");
        Assert.assertEquals(scanResult.results("blue id", "Gender"), "זכר");
        scanResult.clickOnButton("Continue");
        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
        scanLiveness.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/liad/liad_face.jpg");

        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        Assert.assertEquals(faceCompareResults.faceCompareTitle(), "Face Compare Results");
        Assert.assertTrue(faceCompareResults.livenessPictureIsDisplayed());
        Assert.assertEquals(faceCompareResults.livenessDescription(), "Liveness Check");
        Assert.assertTrue(faceCompareResults.ocrPicture());
        Assert.assertEquals(faceCompareResults.ocrDescription(), "Document Image");
        Assert.assertTrue(faceCompareResults.faceMatchIcon());
        Assert.assertEquals(faceCompareResults.faceMatchTitle(), "Face Match");
    }

    @Step("Delete identity that exist in DB")
    public void deleteIdentity() {
        Response response = given()
                .header("Authorization", "Bearer 4122ba73-3304-4b9d-a958-aa07f34c4dc8")
                .when()
                .log()
                .all()
                .delete("https://btrustqa.scanovate.com/api/identity/307922328")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }
}