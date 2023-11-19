package btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.checkServicesUp;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.MobileInteractionUIBase;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.SuiteListener;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
@Listeners({SuiteListener.class})
public class OcrServiceUp extends MobileInteractionUIBase {


    OCRPage ocrPage;
    ImageInjectionFunctionPage imageInjectionFunctionPage;
    ScanResultPage scanResultPage;
    ScanLivenessPage scanLivenessPage;
    FaceCompareResultsPage faceCompareResults;

    @BeforeMethod(alwaysRun = true)
    public void initSession() {
        mobileFlowLink = null;
        ocrPage = new OCRPage(driver);
        imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        scanResultPage = new ScanResultPage(driver);
        scanLivenessPage = new ScanLivenessPage(driver);
    }

    @Test(description = "Check if the OCR Service is up with ILID card")
    @Description("Check if the OCR Service is up with ILID card")
    public void t01_checkIfOcrServiceUp_ILID() throws IOException {
        driver.get(createLinkToFlow(26));
        assertTrue(ocrPage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        assertEquals(ocrPage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        ocrPage.clickOnButton("Scan Israel ID");
        assertTrue(imageInjectionFunctionPage.stageCounterDisplayed("Ocr"));
        imageInjectionFunctionPage.clickOnExitButton();
    }

    @Test(description = "Check if the OCR Service is up with ILDL card")
    @Description("Check if the OCR Service is up with ILDL card")
    public void t02_checkIfOcrServiceUp_ILDL() throws IOException {
        driver.get(createLinkToFlow(26));
        assertTrue(ocrPage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        assertEquals(ocrPage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        ocrPage.clickOnButton("Scan Driving License");
        assertTrue(imageInjectionFunctionPage.stageCounterDisplayed("Ocr"));
        imageInjectionFunctionPage.clickOnExitButton();
    }

    @Test(description = "Check if the OCR Service is up with Passport card")
    @Description("Check if the OCR Service is up with Passport card")
    public void t03_checkIfOcrServiceUp_Passport() throws IOException {
        driver.get(createLinkToFlow(26));
        assertTrue(ocrPage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        assertEquals(ocrPage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        ocrPage.clickOnButton("Scan Passport");
        assertTrue(imageInjectionFunctionPage.stageCounterDisplayed("Ocr"));
        imageInjectionFunctionPage.clickOnExitButton();
    }

 //   @Test(description = "Check if the OCR Service is up with Israel Lawyer card")
    @Description("Check if the OCR Service is up with Israel Lawyer card")
    public void t04_checkIfOcrServiceUp_IsraelLawyer() throws IOException {
        driver.get(createLinkToFlow(26));
        assertTrue(ocrPage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        assertEquals(ocrPage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        ocrPage.clickOnButton("Scan Israel Lawyer Card");
        assertTrue(imageInjectionFunctionPage.stageCounterDisplayed("Ocr"));
        imageInjectionFunctionPage.clickOnExitButton();
    }

    @Test(description = "Check if the OCR Service is up with Credit card")
    @Description("Check if the OCR Service is up with Credit card")
    public void t05_checkIfOcrServiceUp_CreditCard() throws IOException {
        driver.get(createLinkToFlow(26));
        assertTrue(ocrPage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        assertEquals(ocrPage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        ocrPage.clickOnButton("Scan Credit card");
        assertTrue(imageInjectionFunctionPage.stageCounterDisplayed("Ocr"));
        imageInjectionFunctionPage.clickOnExitButton();
    }

    @Test(description = "Check if the OCR Service is up with Stay Permit")
    @Description("Check if the OCR Service is up with Stay Permit")
    public void t06_checkIfOcrServiceUp_StayPermit() throws IOException {
        driver.get(createLinkToFlow(26));
        assertTrue(ocrPage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        assertEquals(ocrPage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        ocrPage.clickOnButton("Scan Stay Permit");
        assertTrue(imageInjectionFunctionPage.stageCounterDisplayed("Ocr"));
        imageInjectionFunctionPage.clickOnExitButton();
    }

}
