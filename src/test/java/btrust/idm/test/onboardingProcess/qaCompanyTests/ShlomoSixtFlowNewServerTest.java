package btrust.idm.test.onboardingProcess.qaCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class ShlomoSixtFlowNewServerTest extends BaseIdmClientTest {

    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", qaApiKey, "449");
        driver.navigate().to(link);
    }

    @Test(description = "Negative flow: Scan expired DL")
    @Description("Perform negative flow - ShlomoSixt expired DL Test")
    public void t01_expiredDL() throws IOException {
        OCRPage ocrPage = new OCRPage(driver);
        ocrPage.clickOnButton("Scan Driving License");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/dl/invalidExpiredDateDL.jpg", "./ocr/dl/LiadDLBack.jpg");
        ScanResultPage scanResult = new ScanResultPage(driver);
        scanResult.clickOnButton("Continue");
        PopupPage popupPage = new PopupPage(driver);
        Assert.assertEquals(popupPage.popupTitle(), "General error");
        Assert.assertEquals(popupPage.popupDescription(), "רישיון פג תוקף");
        popupPage.popupButton();
        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        Assert.assertEquals(faceCompareResults.processEndedText(), "process-ended");
    }

    @Test(enabled = true, description = "Negative flow: Scan Less then 18 DL")
    @Description("Perform negative flow - ShlomoSixt scan DL with a age of less then 18")
    public void t02_lessThen18DL() throws IOException {
        OCRPage ocrPage = new OCRPage(driver);
        ocrPage.clickOnButton("Scan Driving License");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/dl/fakeDL/under18FrontLiadDL.jpg", "./ocr/dl/LiadDLBack.jpg");
        ScanResultPage scanResult = new ScanResultPage(driver);
        scanResult.clickOnButton("Continue");
        PopupPage popupPage = new PopupPage(driver);
        Assert.assertEquals(popupPage.popupTitle(), "General error");
        Assert.assertEquals(popupPage.popupDescription(), "הגיל המינימלי לשירות הינו 18");
        popupPage.popupButton();
        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        String processEndedText = faceCompareResults.processEndedText();
        Assert.assertEquals(processEndedText, "process-ended");
    }

    @Test(description = "Negative flow: Scan front and back DL with different ID numbers")
    @Description("Perform negative flow - ShlomoSixt scan DL with different ID numbers")
    public void t03_differentIdNumbers() throws IOException {
        OCRPage ocrPage = new OCRPage(driver);
        ocrPage.clickOnButton("Scan Driving License");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/dl/LiadDLFront.jpg", "./ocr/dl/fakeDL/under18Back.jpg");
        ScanResultPage scanResult = new ScanResultPage(driver);
        scanResult.clickOnButton("Continue");
        PopupPage popupPage = new PopupPage(driver);
        Assert.assertEquals(popupPage.popupTitle(), "General error");
        Assert.assertEquals(popupPage.popupDescription(), "המסמכים שנסרקו אינם של אותה התעודה");
        popupPage.popupButton();
        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        String processEndedText = faceCompareResults.processEndedText();
        Assert.assertEquals(processEndedText, "process-ended");
    }

    @Test(description = "Positive ShlomoSixt Flow")
    @Description("Perform positive ShlomoSixt Flow")
    public void t04_positiveShlomoSixtFlow() throws IOException {
        OCRPage ocrPage = new OCRPage(driver);
        ocrPage.clickOnButton("Scan Driving License");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/dl/LiadDLFront.jpg", "./ocr/dl/LiadDLBack.jpg");
        ScanResultPage scanResultPage = new ScanResultPage(driver);
        Assert.assertEquals(scanResultPage.results("dl", "Last name"), "טובי");
        Assert.assertEquals(scanResultPage.results("dl", "Last name in english"), "TOBI");
        Assert.assertEquals(scanResultPage.results("dl", "First name"), "ליעד");
        Assert.assertEquals(scanResultPage.results("dl", "First name in english"), "LIAD");
        Assert.assertEquals(scanResultPage.results("dl", "Date of birth"), "17.01.1993");
        Assert.assertEquals(scanResultPage.results("dl", "Issuing Date"), "15.12.2016");
        Assert.assertEquals(scanResultPage.results("dl", "Expiration Date"), "17.01.2027");
        Assert.assertEquals(scanResultPage.results("dl", "License number"), "9254737");
        Assert.assertEquals(scanResultPage.results("dl", "ID Number"), "307922328");
        Assert.assertEquals(scanResultPage.results("dl", "Address"), "קדם 33ב שוהם");
        Assert.assertEquals(scanResultPage.results("dl", "License category"), "B");
        Assert.assertEquals(scanResultPage.results("dl", "B License Year"), "2010");
        scanResultPage.clickOnButton("Continue");

        ScanLivenessPage scanLivenessPage = new ScanLivenessPage(driver);
        scanLivenessPage.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/liad/liad_face.jpg");
        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        Assert.assertEquals(faceCompareResults.faceCompareTitle(), "Face Compare Results");
        Assert.assertTrue(faceCompareResults.faceMatchIcon());
        Assert.assertEquals(faceCompareResults.faceMatchTitle(), "Face Match");
    }
}
