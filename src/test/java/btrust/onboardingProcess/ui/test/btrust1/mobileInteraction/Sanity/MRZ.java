package btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.Sanity;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.MobileInteractionUIBase;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MRZ extends MobileInteractionUIBase {

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

    @Test(description = "Onboarding UI Test Scanning MRZ card and Liveness process",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding UI Test Scanning MRZ card and Liveness process")
    public void t01_passportAndLivenessOnboard() throws IOException {
        driver.get(createLinkToFlow(26));
        assertTrue(ocrPage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        assertEquals(ocrPage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        ocrPage.clickOnButton("Scan Passport");

        imageInjectionFunctionPage.scanOCR2Sides("./ocr/mrz/avner/avner2.jpg", null);

        assertEquals(scanResultPage.resultsTitle(), "Scan results");
        assertEquals(scanResultPage.resultFaceImageDescription(), "Document image");
        assertTrue(scanResultPage.faceImageIsDisplayed());
        assertEquals(scanResultPage.results("MRZ", "Date of birth"), "02.08.80");
  //    assertEquals(scanResultPage.results("MRZ", "Date of Expiry"), "10/06/24");
        assertEquals(scanResultPage.results("MRZ", "ID Number"), "0-4037370-6");
        assertEquals(scanResultPage.results("MRZ", "Nationality code"), "ISR");
        assertEquals(scanResultPage.results("MRZ", "Gender"), "Male");
        assertEquals(scanResultPage.results("MRZ", "Passport Number"), "30266026");
        assertEquals(scanResultPage.results("MRZ", "Issuing country code"), "ISR");
        assertEquals(scanResultPage.results("MRZ", "First Name"), "AVNER");
        assertEquals(scanResultPage.results("MRZ", "Last Name"), "BLUER");
        scanResultPage.clickOnButton("Continue");
        assertTrue(scanLivenessPage.scanLivenessImage());
        assertEquals(scanLivenessPage.scanLivenessTitle(), "Liveness Check");
        assertEquals(scanLivenessPage.scanLivenessSubTitle(), "Before we start make sure that:");
        assertEquals(scanLivenessPage.scanLivenessDescription(), "The phone is at face level | There are good light conditions");
        scanLivenessPage.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/avner/avnerCenter.jpg");
        assertEquals(faceCompareResults.faceCompareTitle(), "Face Compare Results");
        assertTrue(faceCompareResults.ocrPicture());
        assertEquals(faceCompareResults.ocrDescription(), "Document Image");
        assertTrue(faceCompareResults.livenessPictureIsDisplayed());
        assertEquals(faceCompareResults.livenessDescription(), "Liveness Check");
        assertTrue(faceCompareResults.faceMatchIcon());
        assertEquals(faceCompareResults.faceMatchTitle(), "Face Match");
    }
}
