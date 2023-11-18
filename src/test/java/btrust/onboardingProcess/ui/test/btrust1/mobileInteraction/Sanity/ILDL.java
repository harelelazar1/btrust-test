package btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.Sanity;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.MobileInteractionUIBase;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ILDL extends MobileInteractionUIBase {

    OCRPage ocrPage;
    ImageInjectionFunctionPage imageInjectionFunctionPage;
    ScanResultPage scanResultPage;
    ScanLivenessPage scanLivenessPage;
    FaceCompareResultsPage faceCompareResults;

    @BeforeMethod(alwaysRun = true)
    public void initSession() {
        mobileFlowLink = null;
        response = null;
        jsonPath = null;
        sessionId = null;
        ocrPage = new OCRPage(driver);
        imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        scanResultPage = new ScanResultPage(driver);
        scanLivenessPage = new ScanLivenessPage(driver);
        faceCompareResults = new FaceCompareResultsPage(driver);
    }

    @Test(description = "Onboarding UI Test Scanning old DL card and Liveness process",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding UI Test Scanning of old DL card and Liveness process")
    public void t01_oldIlDlAndLivenessOnboard() throws IOException {
        driver.get(createLinkToFlow(1258));
        assertTrue(ocrPage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        assertEquals(ocrPage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        ocrPage.clickOnButton("Scan Driving License");

        imageInjectionFunctionPage.scanOCR2Sides("./ocr/dl/LiadDLFront.jpg", "./ocr/dl/LiadDLBack.jpg");

        assertEquals(scanResultPage.resultsTitle(), "Scan results");
        assertEquals(scanResultPage.resultFaceImageDescription(), "Document image");
        assertTrue(scanResultPage.faceImageIsDisplayed());
        assertEquals(scanResultPage.results("dl", "Date of birth"), "17.01.1993");
        assertEquals(scanResultPage.results("dl", "Date of Issue"), "15.12.2016");
  //    assertEquals(scanResultPage.results("dl", "Date of Expiry"), "17.01.2027");
        assertEquals(scanResultPage.results("dl", "License number"), "9254737");
        assertEquals(scanResultPage.results("dl", "Address"), "קדם 33ב שוהם");
        assertEquals(scanResultPage.results("dl", "First Name"), "ליעד");
        assertEquals(scanResultPage.results("dl", "First Name English"), "LIAD");
        assertEquals(scanResultPage.results("dl", "Last Name"), "טובי");
        assertEquals(scanResultPage.results("dl", "Last Name English"), "TOBI");
        assertEquals(scanResultPage.results("dl", "B License Year"), "2010");
        assertEquals(scanResultPage.results("dl", "ID Number"), "307922328");
        assertEquals(scanResultPage.results("dl", "License category"), "B");
        scanResultPage.clickOnButton("Continue");
    }
}
