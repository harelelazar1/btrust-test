package btrust.idm.test.onboardingProcess.mimunyashirCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.FaceCompareResultsPage;
import btrust.onboardingProcess.ui.pagesObject.ImageInjectionFunctionPage;
import btrust.onboardingProcess.ui.pagesObject.OCRPage;
import btrust.onboardingProcess.ui.pagesObject.PopupPage;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationFlowTest extends BaseIdmClientTest {

    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", mimunYashirApiKey, "202");
        driver.navigate().to(link);
    }

    @Test(description = "Registration with ID")
    @Description("Scan ID and check that created new case in console")
    public void t01_registrationID() throws IOException {
        OCRPage ocr = new OCRPage(driver);
        Assert.assertTrue(ocr.progressBar());
        ocr.clickOnButton("סריקת תעודת זהות");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/bioID/nitzan/front/NitzanBioFront.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack.jpg");

        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        String processEndedText = faceCompareResults.processEndedText();
        Assert.assertEquals(processEndedText, "process-ended");
    }

    @Test(description = "Scan back side first")
    @Description("Scan back side instead of front side and check that popup displayed")
    public void t02_scanBackSideFirst() throws IOException, InterruptedException {
        OCRPage ocrPage = new OCRPage(driver);
        Assert.assertTrue(ocrPage.progressBar());
        ocrPage.clickOnButton("סריקת תעודת זהות");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR("./ocr/bioID/nitzan/back/NitzanBioBack.jpg", "back");
        Thread.sleep(45000);

        PopupPage popupPage = new PopupPage(driver);
        Assert.assertEquals(popupPage.popupTitle(), "נגמר הזמן");
    }

    @Test(description = "Registration with DL")
    @Description("Scan DL and check that created new case in console")
    public void t03_registrationDL() throws IOException {
        OCRPage ocr = new OCRPage(driver);
        Assert.assertTrue(ocr.progressBar());
        ocr.clickOnButton("סריקת רישיון נהיגה");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/dl/LiadDLFront.jpg", "./ocr/dl/LiadDLBack.jpg");

        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        String processEndedText = faceCompareResults.processEndedText();
        Assert.assertEquals(processEndedText, "process-ended");
    }
}