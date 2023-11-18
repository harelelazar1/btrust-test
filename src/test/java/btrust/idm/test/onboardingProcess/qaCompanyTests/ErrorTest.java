package btrust.idm.test.onboardingProcess.qaCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorTest extends BaseIdmClientTest {

    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", qaApiKey, "446");
        driver.navigate().to(link);
    }

    @Test(description = "Face doesn't Match")
    @Description("Scan ocr and liveness use 2 peoples and check that appear message: Face doesn't Match")
    public void t01_faceDoesNotMatch() throws IOException {
        OCRPage ocrPage = new OCRPage(driver);
        ocrPage.clickOnButton("Scan Israel ID");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR("./ocr/blueID/liad/blueID_color.jpg", "front");
        ScanResultPage scanResult = new ScanResultPage(driver);
        scanResult.clickOnButton("Continue");
        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
        scanLiveness.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/bar/barCenter.jpg");

        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        Assert.assertEquals(faceCompareResults.faceCompareTitle(), "Face Compare Results");
        Assert.assertTrue(faceCompareResults.livenessPictureIsDisplayed());
        Assert.assertEquals(faceCompareResults.livenessDescription(), "Liveness Check");
        Assert.assertTrue(faceCompareResults.ocrPicture());
        Assert.assertEquals(faceCompareResults.ocrDescription(), "Document Image");
        Assert.assertTrue(faceCompareResults.faceMatchIcon());
        Assert.assertEquals(faceCompareResults.faceMatchTitle(), "Face doesn't Match");
    }

    @Test(description = "Scan DL instead of ID")
    @Description("Scan DL instead of ID, click try again, scan ID and check that the scan is success")
    public void t02_tryAgain() throws Exception {
        OCRPage ocrPage = new OCRPage(driver);
        ocrPage.clickOnButton("Scan Israel ID");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/blueID/liad/blueID.jpg/", null);
        ScanResultPage scanResult = new ScanResultPage(driver);
        scanResult.clickOnButton("Scan again");
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/blueID/liad/blueID.jpg", null);
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

    @Test(description = "Block user")
    @Description("Failed 3 times and Check that the user should be blocked")
    public void t03_blockUser() throws Exception {
        OCRPage ocrPage = new OCRPage(driver);
        ocrPage.clickOnButton("Scan Israel ID");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR("./ocr/dl/LiadDLFront.jpg", "front");
        Thread.sleep(45000);
        PopupPage popupPage = new PopupPage(driver);
        Assert.assertEquals(popupPage.popupTitle(), "Time's up");
        popupPage.popupButton();
        ocrPage.clickOnButton("Scan Israel ID");
        imageInjectionFunctionPage.scanOCR("./ocr/dl/LiadDLFront.jpg", "front");
        Thread.sleep(45000);
        Assert.assertEquals(popupPage.popupTitle(), "Time's up");
        popupPage.popupButton();
        ocrPage.clickOnButton("Scan Israel ID");
        imageInjectionFunctionPage.scanOCR("./ocr/dl/LiadDLFront.jpg", "front");
        Thread.sleep(45000);
        Assert.assertEquals(popupPage.popupTitle(), "General error");
        Assert.assertEquals(popupPage.popupDescription(), "מקסימום נסיונות");
        popupPage.popupButton();
        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        String processEndedText = faceCompareResults.processEndedText();
        Assert.assertEquals(processEndedText, "process-ended");
    }

    @Test(description = "Timeout liveness")
    @Description("Check that the user return to main screen of liveness when the user get timeout in liveness")
    public void t04_timeoutLiveness() throws IOException, InterruptedException {
        OCRPage ocrPage = new OCRPage(driver);
        ocrPage.clickOnButton("Scan Israel ID");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR("./ocr/greenID/bar/BarGreenID.jpg", "front");
        ScanResultPage scanResult = new ScanResultPage(driver);
        scanResult.clickOnButton("Continue");
        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
        scanLiveness.startButton();
        Thread.sleep(65000);
        PopupPage popupPage = new PopupPage(driver);
        Assert.assertEquals(popupPage.popupTitle(), "Time's up");
        popupPage.popupButton();
        Assert.assertTrue(scanLiveness.scanLivenessImage());
        Assert.assertEquals(scanLiveness.scanLivenessTitle(), "Liveness Check");
        Assert.assertEquals(scanLiveness.scanLivenessSubTitle(), "Before we start make sure that:");
        Assert.assertEquals(scanLiveness.scanLivenessDescription(), "The phone is at face level | There are good light conditions");
    }

    @Test(description = "Timeout OCR")
    @Description("Check that the user return to main screen of OCR when the user get timeout in OCR")
    public void t05_timeoutOCR() throws InterruptedException {
        OCRPage ocrPage = new OCRPage(driver);
        ocrPage.clickOnButton("Scan Israel ID");
        Thread.sleep(65000);
        PopupPage popupPage = new PopupPage(driver);
        Assert.assertEquals(popupPage.popupTitle(), "Time's up");
        popupPage.popupButton();
        Assert.assertTrue(ocrPage.ocrImage());
        Assert.assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        Assert.assertEquals(ocrPage.subTitleOCRPage(), "Before you start make sure that:");
        Assert.assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
    }
}