package btrust.idm.test.onboardingProcess.qaCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class ShlomoFlowTest extends BaseIdmClientTest {

    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", qaApiKey, "317");
        driver.navigate().to(link);
    }

    @Test(description = "Negative flow: Scan DL with less then 1 year from issue")
    @Description("Perform negative flow - ShlomoSixt scan DL with less then 1 year from issue year")
    public void t01_wrongIssueYear() throws IOException {
        OCRPage ocrPage = new OCRPage(driver);
        ocrPage.clickOnButton("Scan Driving License");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanImage("./ocr/dl/LiadDLFront.jpg", "front");
        ScanResultPage scanResult = new ScanResultPage(driver);
        scanResult.clickOnButton("Continue");
        PopupPage popupPage = new PopupPage(driver);
        popupPage.popupButton();
        imageInjectionFunctionPage.scanImage("./ocr/dl/fakeDL/issueYearWrong.jpg", "back");
        scanResult.clickOnButton("Continue");
        Assert.assertEquals(popupPage.popupTitle(), "General error");
        Assert.assertEquals(popupPage.popupDescription(), "נדרש רשיון עם ותק של שנה לפחות");
        popupPage.popupButton();
        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        String processEndedText = faceCompareResults.processEndedText();
        Assert.assertEquals(processEndedText, "process-ended");
    }
}