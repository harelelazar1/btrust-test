package btrust.idm.test.onboardingProcess.shlomoSixtCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.FaceCompareResultsPage;
import btrust.onboardingProcess.ui.pagesObject.ImageInjectionFunctionPage;
import btrust.onboardingProcess.ui.pagesObject.OCRPage;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationFlowTest extends BaseIdmClientTest {

    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", shlomoApiKey, "204");
        driver.navigate().to(link);
    }

    @Test(description = "Registration flow")
    @Description("Scan DL and check that created new case with the data of the dl")
    public void t01_e2eLogin() throws IOException {
        OCRPage ocr = new OCRPage(driver);
        ocr.clickOnButton("סריקת רישיון נהיגה");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/dl/LiadDLFront.jpg", "./ocr/dl/LiadDLBack.jpg");

        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        String processEndedText = faceCompareResults.processEndedText();
        Assert.assertEquals(processEndedText, "process-ended");
    }
}