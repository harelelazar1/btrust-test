package btrust.idm.test.onboardingProcess.qaCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class OnboardingDLFlowTest extends BaseIdmClientTest {

    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", qaApiKey, "446");
        driver.navigate().to(link);
    }

    @Test(description = "DL button")
    @Description("Edit ocrType's value in onboarding workflow and check that DL button appear in btrust client")
    public void t01_e2eDL() throws IOException {
        OCRPage ocrPage = new OCRPage(driver);
        ocrPage.clickOnButton("Scan Driving License");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/dl/LiadDLFront.jpg", "./ocr/dl/LiadDLBack.jpg");

        ScanResultPage scanResult = new ScanResultPage(driver);
        Assert.assertTrue(scanResult.progressBarIsDisplayed());
        Assert.assertEquals(scanResult.resultsTitle(), "Scan Results");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "Document image");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        Assert.assertEquals(scanResult.results("dl", "Last name"), "טובי");
        Assert.assertEquals(scanResult.results("dl", "Last name in english"), "TOBI");
        Assert.assertEquals(scanResult.results("dl", "First name"), "ליעד");
        Assert.assertEquals(scanResult.results("dl", "First name in english"), "LIAD");
        Assert.assertEquals(scanResult.results("dl", "Date of birth"), "17.01.1993");
        Assert.assertEquals(scanResult.results("dl", "Issuing Date"), "15.12.2016");
        Assert.assertEquals(scanResult.results("dl", "Expiration Date"), "17.01.2027");
        Assert.assertEquals(scanResult.results("dl", "License number"), "9254737");
        Assert.assertEquals(scanResult.results("dl", "ID Number"), "307922328");
        Assert.assertEquals(scanResult.results("dl", "Address"), "קדם 33ב שוהם");
        Assert.assertEquals(scanResult.results("dl", "License category"), "B");
        Assert.assertEquals(scanResult.results("dl", "B License Year"), "2010");
        scanResult.clickOnButton("Continue");

        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
        Assert.assertTrue(scanLiveness.progressBar());
        Assert.assertTrue(scanLiveness.scanLivenessImage());
        Assert.assertEquals(scanLiveness.scanLivenessTitle(), "Liveness Check");
        Assert.assertEquals(scanLiveness.scanLivenessSubTitle(), "Before we start make sure that:");
        Assert.assertEquals(scanLiveness.scanLivenessDescription(), "The phone is at face level | There are good light conditions");
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
}