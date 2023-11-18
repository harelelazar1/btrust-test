package btrust.onboardingProcess.api.v2;

import btrust.onboardingProcess.api.ApiResponses;
import btrust.onboardingProcess.api.Utilities;
import btrust.onboardingProcess.api.variables.*;
import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.onboardingProcess.ui.pagesObject.questionnaire.FileTypePage;
import btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.MobileInteractionUIBase;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class Metadata extends MobileInteractionUIBase {

    OCRPage ocrPage;
    ImageInjectionFunctionPage imageInjectionFunctionPage;
    ScanResultPage scanResultPage;
    ScanLivenessPage scanLivenessPage;
    FaceCompareResultsPage faceCompareResults;
    ApiResponses apiResponses;
    PreProcess preProcess;
    EndpointResults endpointResults;
    Utilities utilities;
    ChequePage chequePage;
    FileTypePage fileTypePage;
    PopupPage popupPage;
    String metadataValue = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1";
    ScanResultPage scanResult;

    @BeforeMethod
    public void initSession() {
        mobileFlowLink = null;
        response = null;
        jsonPath = null;
        sessionId = null;
        ocrPage = new OCRPage(driver);
        chequePage = new ChequePage(driver);
        imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        scanResultPage = new ScanResultPage(driver);
        scanLivenessPage = new ScanLivenessPage(driver);
        faceCompareResults = new FaceCompareResultsPage(driver);
        fileTypePage = new FileTypePage(driver);
        popupPage = new PopupPage(driver);
        apiResponses = new ApiResponses();
        preProcess = new PreProcess();
        utilities = new Utilities();
        endpointResults = new EndpointResults();
        scanResult = new ScanResultPage(driver);
    }

    @Test(description = "Onboarding UI/API Test-Scanning BlueId card and Liveness process")
    @Description("Onboarding UI/API Test-Scanning BlueId card and Liveness process")
    public void t01_blueIdAndLivenessMobileInteractionMetadata() throws IOException {
        driver.get(createLinkToFlow(26));
        assertTrue(ocrPage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        assertEquals(ocrPage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        ocrPage.clickOnButton("Scan Israel ID");
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/blueID/liad/blueID_color.jpg", null);

        assertEquals(scanResultPage.resultsTitle(), "Scan results");
        assertEquals(scanResultPage.resultFaceImageDescription(), "Document image");
        assertTrue(scanResultPage.faceImageIsDisplayed());
        assertEquals(scanResultPage.results("blue id", "Date of birth"), "17.01.1993");
//        assertEquals(scanResultPage.results("blue id", "Date of Expiry"), "28.01.2025");
        assertEquals(scanResultPage.results("blue id", "Date of Issue"), "28.01.2015");
        assertEquals(scanResultPage.results("blue id", "First Name"), "ליעד");
        assertEquals(scanResultPage.results("blue id", "Last Name"), "טובי");
        assertEquals(scanResultPage.results("blue id", "Gender"), "זכר");
        assertEquals(scanResultPage.results("blue id", "ID Number"), "307922328");
        assertEquals(scanResultPage.results("blue id", "Place of birth"), "ישראל");
        scanResultPage.clickOnButton("Continue");

        assertTrue(scanLivenessPage.scanLivenessImage());
        assertEquals(scanLivenessPage.scanLivenessTitle(), "Liveness Check");
        assertEquals(scanLivenessPage.scanLivenessSubTitle(), "Before we start make sure that:");
        assertEquals(scanLivenessPage.scanLivenessDescription(), "The phone is at face level | There are good light conditions");
        scanLivenessPage.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/liad/liad_face.jpg");

        assertEquals(faceCompareResults.faceCompareTitle(), "Face Compare Results");
        assertTrue(faceCompareResults.ocrPicture());
        assertEquals(faceCompareResults.ocrDescription(), "Document Image");
        assertTrue(faceCompareResults.livenessPictureIsDisplayed());
        assertEquals(faceCompareResults.livenessDescription(), "Liveness Check");
        assertTrue(faceCompareResults.faceMatchIcon());
        assertEquals(faceCompareResults.faceMatchTitle(), "Face Match");

        //backend
        preProcess.setSessionId(sessionId);
        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertTrue(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "");
        assertEquals(endpointResults.getDataErrorCode(), 0);
//        assertEquals(endpointResults.getMetadata(), metadataValue);

        assertEquals(ILID.getProcess(), "ocr");
        assertNotNull(ILID.getEnd());
        assertEquals(ILID.getDob(), "17.01.1993");
        assertEquals(ILID.getIssuingDate(), "28.01.2015");
        assertEquals(ILID.getExpiryDate(), "28.01.2025");
        assertEquals(ILID.getFirstName(), "ליעד");
        assertEquals(ILID.getLastName(), "טובי");
        assertEquals(ILID.getDateOfBirth(), "1993-01-17T00:00:00.000+0000");
        assertEquals(ILID.getGender(), "זכר");
        assertEquals(ILID.getIdNumber(), 307922328);
        assertEquals(ILID.getExpirationDate(), "2025-01-28T00:00:00.000+0000");
        assertNotNull(ILID.getFaceImage());
        assertNotNull(ILID.getFrontImage());
        assertNotNull(ILID.getCardImage());
        assertEquals(ILID.getDocType(), "blue");
        assertEquals(ILID.getIssueDate(), "2015-01-28T00:00:00.000+0000");
        assertEquals(ILID.getPlaceOfBirth(), "ישראל");
 //       assertNotNull(ILID.getScanVideo());
        assertEquals(ILID.getOcrType(), "IL-ID");
 //       assertTrue(ILID.isPeriodBetweenExpiryDateAndIssueDate());
        assertTrue(ILID.isFacePosition());
        assertTrue(ILID.isColorCheck());
        assertTrue(ILID.isFaceStamp());
        assertTrue(ILID.isValidityExpiryDate());
        assertTrue(ILID.isChecksum());
        assertTrue(ILID.isFaceSize());
        assertTrue(ILID.isTemplateMatching());
        assertTrue(ILID.isFaceRotation());
        assertTrue(ILID.isProcessSuccess());
        assertEquals(ILID.getCount0(), 1);

        assertEquals(Liveness.getProcess(), "liveness");
        assertNotNull(Liveness.getFaceImage());
        assertNotNull(Liveness.getVideo());
        assertTrue(Liveness.isProcessSuccess());

        assertEquals(BiometricMatch.getProcess(), "biometric_match");
        assertTrue(BiometricMatch.isProcessSuccess());
        assertTrue(Math.abs(BiometricMatch.getScore()-0.8)<0.05);
//        assertEquals(BiometricMatch.getScore(), 0.7396558523178101);
    }

    @Test(description = "Onboarding UI/API Test-Scanning GreenId card and Liveness process")
    @Description("Onboarding UI/API Test-Scanning GreenId card and Liveness process")
    public void t02_greenIdAndLivenessMobileInteractionMetadata() throws IOException {
        driver.get(createLinkToFlow(26));
        assertTrue(ocrPage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        assertEquals(ocrPage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        ocrPage.clickOnButton("Scan Israel ID");

        imageInjectionFunctionPage.scanOCR2Sides("./ocr/greenID/bar/BarGreenID.jpg", null);

        assertEquals(scanResultPage.resultsTitle(), "Scan results");
        assertEquals(scanResultPage.resultFaceImageDescription(), "Document image");
        assertTrue(scanResultPage.faceImageIsDisplayed());
        assertEquals(scanResultPage.results("green id", "Date of birth"), "19.02.1994");
        assertEquals(scanResultPage.results("green id", "Date of Issue"), "03.05.2010");
        assertEquals(scanResultPage.results("green id", "First Name"), "בר");
        assertEquals(scanResultPage.results("green id", "Last Name"), "זמסקי");
        assertEquals(scanResultPage.results("green id", "Gender"), "נקבה");
        assertEquals(scanResultPage.results("green id", "ID Number"), "312534753");
        assertEquals(scanResultPage.results("green id", "Place of birth"), "ישראל");
        scanResultPage.clickOnButton("Continue");
        assertTrue(scanLivenessPage.scanLivenessImage());
        assertEquals(scanLivenessPage.scanLivenessTitle(), "Liveness Check");
        assertEquals(scanLivenessPage.scanLivenessSubTitle(), "Before we start make sure that:");
        assertEquals(scanLivenessPage.scanLivenessDescription(), "The phone is at face level | There are good light conditions");
        scanLivenessPage.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/bar/barCenter.jpg");
        assertEquals(faceCompareResults.faceCompareTitle(), "Face Compare Results");
        assertTrue(faceCompareResults.ocrPicture());
        assertEquals(faceCompareResults.ocrDescription(), "Document Image");
        assertTrue(faceCompareResults.livenessPictureIsDisplayed());
        assertEquals(faceCompareResults.livenessDescription(), "Liveness Check");
        assertTrue(faceCompareResults.faceMatchIcon());
        assertEquals(faceCompareResults.faceMatchTitle(), "Face Match");

        //backend
        preProcess.setSessionId(sessionId);
        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertTrue(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "");
        assertEquals(endpointResults.getDataErrorCode(), 0);
//        assertEquals(endpointResults.getMetadata(), metadataValue);

        assertEquals(ILID.getProcess(), "ocr");
        assertNotNull(ILID.getEnd());
        assertEquals(ILID.getDob(), "19.02.1994");
        assertEquals(ILID.getIssuingDate(), "03.05.2010");
        assertEquals(ILID.getFirstName(), "בר");
        assertEquals(ILID.getLastName(), "זמסקי");
        assertEquals(ILID.getDateOfBirth(), "1994-02-19T00:00:00.000+0000");
        assertEquals(ILID.getGender(), "נקבה");
        assertEquals(ILID.getIdNumber(), 312534753);
        assertNotNull(ILID.getFaceImage());
        assertNotNull(ILID.getFrontImage());
        assertNotNull(ILID.getCardImage());
        assertEquals(ILID.getDocType(), "green");
        assertEquals(ILID.getIssueDate(), "2010-05-03T00:00:00.000+0000");
        assertEquals(ILID.getPlaceOfBirth(), "ישראל");
 //       assertNotNull(ILID.getScanVideo());
        assertEquals(ILID.getOcrType(), "IL-ID");
        assertTrue(ILID.isFacePosition());
        assertTrue(ILID.isColorCheck());
        assertFalse(ILID.isFaceStamp());
        assertTrue(ILID.isValidityExpiryDate());
        assertTrue(ILID.isChecksum());
        assertTrue(ILID.isFaceSize());
        assertTrue(ILID.isTemplateMatching());
        assertTrue(ILID.isFaceRotation());
        assertTrue(ILID.isProcessSuccess());
        assertEquals(ILID.getCount0(), 1);

        assertEquals(Liveness.getProcess(), "liveness");
        assertNotNull(Liveness.getFaceImage());
        assertNotNull(Liveness.getVideo());
        assertTrue(Liveness.isProcessSuccess());

        assertEquals(BiometricMatch.getProcess(), "biometric_match");
        assertTrue(BiometricMatch.isProcessSuccess());
        assertTrue(Math.abs(BiometricMatch.getScore()-0.7)<0.05);
//        assertEquals(BiometricMatch.getScore(), 0.6817070841789246);
    }

    @Test(description = "Onboarding UI/API Test-Scanning BioId card and Liveness process",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding UI/API Test-Scanning BioId card and Liveness process")
    public void t03_bioIdAndLivenessOnboarding() throws IOException {
        driver.get(createLinkToFlow(705));
        assertTrue(ocrPage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        assertEquals(ocrPage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        ocrPage.clickOnButton("Scan Israel ID");

        imageInjectionFunctionPage.scanOCR2Sides("./ocr/bioID/nitzan/front/NitzanBioFront.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack.jpg");

        assertEquals(scanResultPage.resultsTitle(), "Scan results");
        assertEquals(scanResultPage.resultFaceImageDescription(), "Document image");
        assertTrue(scanResultPage.faceImageIsDisplayed());
        assertEquals(scanResultPage.results("bio id", "Date of birth"), "21.08.1988");
        assertEquals(scanResultPage.results("bio id", "Date of Issue"), "23.5.2019");
//        assertEquals(scanResultPage.results("bio id", "Date of Expiry"), "20.05.2029");
        assertEquals(scanResultPage.results("bio id", "First Name"), "ניצן");
        assertEquals(scanResultPage.results("bio id", "Last Name"), "שוקר");
        assertEquals(scanResultPage.results("bio id", "Gender"), "זכר");
        assertEquals(scanResultPage.results("bio id", "ID Number"), "200761625");
        assertEquals(scanResultPage.results("bio id", "Place of birth"), "ישראל");
        scanResultPage.clickOnButton("Continue");
        assertTrue(scanLivenessPage.scanLivenessImage());
        assertEquals(scanLivenessPage.scanLivenessTitle(), "Liveness Check");
        assertEquals(scanLivenessPage.scanLivenessSubTitle(), "Before we start make sure that:");
        assertEquals(scanLivenessPage.scanLivenessDescription(), "The phone is at face level | There are good light conditions");
        scanLivenessPage.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/liad/liad_face.jpg");
        popupPage.popupButton();
        assertEquals(faceCompareResults.faceCompareTitle(), "Face Compare Results");
        assertTrue(faceCompareResults.ocrPicture());
        assertEquals(faceCompareResults.ocrDescription(), "Document Image");
        assertTrue(faceCompareResults.livenessPictureIsDisplayed());
        assertEquals(faceCompareResults.livenessDescription(), "Liveness Check");
        assertTrue(faceCompareResults.faceMatchIcon());
        assertEquals(faceCompareResults.faceMatchTitle(), "Face doesn't Match");

        //backend
        preProcess.setSessionId(sessionId);
        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertFalse(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "face not match");
        assertEquals(endpointResults.getDataErrorCode(), 1013);
//        assertEquals(endpointResults.getMetadata(), metadataValue);

        assertEquals(ILID.getProcess(), "ocr");
        assertNotNull(ILID.getEnd());
        assertEquals(ILID.getDob(), "21.08.1988");
        assertEquals(ILID.getIssuingDate(), "23.05.2019");
        assertEquals(ILID.getExpiryDate(), "20.05.2029");
        assertEquals(ILID.getFirstName(), "ניצן");
        assertEquals(ILID.getLastName(), "שוקר");
        assertEquals(ILID.getDateOfBirth(), "1988-08-21T00:00:00.000+0000");
        assertEquals(ILID.getGender(), "זכר");
        assertEquals(ILID.getIdNumber(), 200761625);
        assertEquals(ILID.getExpirationDate(), "2029-05-20T00:00:00.000+0000");
        assertNotNull(ILID.getFaceImage());
        assertNotNull(ILID.getFrontImage());
        assertNotNull(ILID.getCardImage());
        assertNotNull(ILID.getBackImage());
        assertEquals(ILID.getDocType(), "il_id_bio");
        assertEquals(ILID.getIssueDate(), "2019-05-23T00:00:00.000+0000");
        assertEquals(ILID.getPlaceOfBirth(), "ישראל");
 //       assertNotNull(ILID.getScanVideo());
        assertEquals(ILID.getOcrType(), "IL-ID");
        assertEquals(ILID.getBackSideIdNumber(), 200761625);
        assertTrue(ILID.isPeriodBetweenExpiryDateAndIssueDate());
        assertTrue(ILID.isFacePosition());
        assertTrue(ILID.isValidityExpiryDate());
        assertTrue(ILID.isFaceSize());
        assertTrue(ILID.isTemplateMatching());
        assertTrue(ILID.isFaceRotation());
        assertTrue(ILID.isDocumentChipValid());
        assertTrue(ILID.isColorCheck());
        assertTrue(ILID.isFaceImageReplacedWithPassportImage());
        assertTrue(ILID.isColourfulImageBackSide());
        assertTrue(ILID.isChecksum());
        assertTrue(ILID.isValidateIfFrontAndBacksideIdMatch());
        assertTrue(ILID.isChecksumBackside());
        assertTrue(ILID.isTemplateMatchingBackside());
        assertTrue(ILID.isProcessSuccess());
        assertEquals(ILID.getCount0(), 1);

        assertEquals(Liveness.getProcess(), "liveness");
        assertNotNull(Liveness.getFaceImage());
        assertNotNull(Liveness.getVideo());
        assertTrue(Liveness.isProcessSuccess());

        assertEquals(BiometricMatch.getProcess(), "biometric_match");
        assertFalse(BiometricMatch.isProcessSuccess());
        assertTrue(Math.abs(BiometricMatch.getScore()-0.5)<0.05);
//        assertEquals(BiometricMatch.getScore(), 0.513794481754303);
    }

    @Test(description = "Onboarding UI/API Test-Scanning Old DL card and Liveness process",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding UI/API Test-Scanning Old DL card and Liveness process")
    public void t04_oldIlDlAndLivenessOnboarding() throws IOException {
        driver.get(createLinkToFlow(26));
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
 //       assertEquals(scanResultPage.results("dl", "Date of Expiry"), "17.01.2027");
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
        assertTrue(scanLivenessPage.scanLivenessImage());
        assertEquals(scanLivenessPage.scanLivenessTitle(), "Liveness Check");
        assertEquals(scanLivenessPage.scanLivenessSubTitle(), "Before we start make sure that:");
        assertEquals(scanLivenessPage.scanLivenessDescription(), "The phone is at face level | There are good light conditions");
        scanLivenessPage.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/liad/liad_face.jpg");
        assertEquals(faceCompareResults.faceCompareTitle(), "Face Compare Results");
        assertTrue(faceCompareResults.ocrPicture());
        assertEquals(faceCompareResults.ocrDescription(), "Document Image");
        assertTrue(faceCompareResults.livenessPictureIsDisplayed());
        assertEquals(faceCompareResults.livenessDescription(), "Liveness Check");
        assertTrue(faceCompareResults.faceMatchIcon());
        assertEquals(faceCompareResults.faceMatchTitle(), "Face Match");

        //backend
        preProcess.setSessionId(sessionId);
        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertTrue(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "");
        assertEquals(endpointResults.getDataErrorCode(), 0);
//        assertEquals(endpointResults.getMetadata(), metadataValue);

        assertEquals(ILDL.getProcess(), "ocr");
        assertNotNull(ILDL.getEnd());
        assertEquals(ILDL.getDob(), "17.01.1993");
        assertEquals(ILDL.getIssuingDate(), "15.12.2016");
        assertEquals(ILDL.getExpiryDate(), "17.01.2027");
        assertEquals(ILDL.getFirstName(), "ליעד");
        assertEquals(ILDL.getLastName(), "טובי");
        assertEquals(ILDL.getDateOfBirth(), "1993-01-17T00:00:00.000+0000");
        assertEquals(ILDL.getAddress(), "קדם 33ב שוהם");
        assertEquals(ILDL.getIdNumber(), 307922328);
        assertEquals(ILDL.getDocumentNumber(), 9254737);
        assertEquals(ILDL.getExpirationDate(), "2027-01-17T00:00:00.000+0000");
        assertNotNull(ILDL.getFaceImage());
        assertNotNull(ILDL.getFrontImage());
        assertNotNull(ILDL.getCardImage());
        assertNotNull(ILDL.getBackImage());
        assertEquals(ILDL.getDocType(), "old_back");
        assertEquals(ILDL.getIssueDate(), "2016-12-15T00:00:00.000+0000");
//        assertNotNull(ILDL.getScanVideo());
        assertEquals(ILDL.getLicenseCategory(), "B");
        assertEquals(ILDL.getLicenseIssuingYear(), "2010-01-01T00:00:00.000+0000");
        assertEquals(ILDL.getLicenseIssueYear(), 2010);
        assertEquals(ILDL.getOcrType(), "IL-DL");
        assertEquals(ILDL.getBackSideIdNumber(), 307922328);
        assertTrue(ILDL.isFacePosition());
        assertTrue(ILDL.isValidityExpiryDate());
        assertTrue(ILDL.isChecksum());
        assertTrue(ILDL.isFaceSize());
        assertTrue(ILDL.isTemplateMatching());
        assertTrue(ILDL.isFaceRotation());
        assertTrue(ILDL.isProcessSuccess());
        assertEquals(ILDL.getCount(), 1);

        assertEquals(Liveness.getProcess(), "liveness");
        assertNotNull(Liveness.getFaceImage());
        assertNotNull(Liveness.getVideo());
        assertTrue(Liveness.isProcessSuccess());

        assertEquals(BiometricMatch.getProcess(), "biometric_match");
        assertTrue(BiometricMatch.isProcessSuccess());
        assertTrue(Math.abs(BiometricMatch.getScore()-0.8)<0.05);
//        assertEquals(BiometricMatch.getScore(), 0.7888237833976746);
    }

    @Test(description = "Onboarding UI/API Test-Scanning Passport card and Liveness process")
    @Description("Onboarding UI/API Test-Scanning Passport card and Liveness process")
    public void t05_passportAndLivenessOnboarding() throws IOException {
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
//        assertEquals(scanResultPage.results("MRZ", "Date of Expiry"), "10/06/24");
        assertEquals(scanResultPage.results("MRZ", "ID Number"), "0-4037370-6");
        assertEquals(scanResultPage.results("MRZ", "Nationality code"), "ISR");
        assertEquals(scanResultPage.results("MRZ", "Gender"), "Male");
        assertEquals(scanResultPage.results("MRZ", "Passport Number"), "30266026");
        assertEquals(scanResultPage.results("MRZ", "Nationality code"), "ISR");
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

        //backend
        preProcess.setSessionId(sessionId);
        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertTrue(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "");
        assertEquals(endpointResults.getDataErrorCode(), 0);
//        assertEquals(endpointResults.getMetadata(), metadataValue);

        assertEquals(MRZ.getProcess(), "ocr");
        assertNotNull(MRZ.getEnd());
        assertEquals(MRZ.getDob(), "02.08.1980");
        assertEquals(MRZ.getExpiryDate(), "10.06.2024");
        assertEquals(MRZ.getFirstName(), "AVNER");
        assertEquals(MRZ.getLastName(), "BLUER");
        assertEquals(MRZ.getDateOfBirth(), "1980-08-02T00:00:00.000+0000");
        assertEquals(MRZ.getNationalityName(), "Israel");
        assertEquals(MRZ.getNationalityAlpha2(), "IL");
        assertEquals(MRZ.getNationalityAlpha3(), "ISR");
        assertEquals(MRZ.getGender(), "Male");
        assertEquals(MRZ.getIdNumber(), "0-4037370-6");
        assertEquals(MRZ.getDocumentNumber(), 30266026);
        assertEquals(MRZ.getExpirationDate(), "2024-06-10T00:00:00.000+0000");
        assertEquals(MRZ.getIssuingCountryName(), "Israel");
        assertEquals(MRZ.getIssuingCountryAlpha2(), "IL");
        assertEquals(MRZ.getIssuingCountryAlpha3(), "ISR");
        assertEquals(MRZ.getCountryCode(), "IL");
        assertNotNull(MRZ.getFaceImage());
        assertNotNull(MRZ.getFrontImage());
        assertNotNull(MRZ.getCardImage());
        assertEquals(MRZ.getDocType(), "MRZ");
 //       assertNotNull(MRZ.getScanVideo());
        assertEquals(MRZ.getOcrType(), "MRZ");
        assertTrue(MRZ.isValidityExpiryDate());
        assertTrue(MRZ.isChecksum());
        assertTrue(MRZ.isTemplateMatching());
        assertTrue(MRZ.isFacePosition());
        assertTrue(MRZ.isFaceRotation());
        assertTrue(MRZ.isFaceSize());
        assertTrue(MRZ.isProcessSuccess());
        assertEquals(MRZ.getCount(), 1);

        assertEquals(Liveness.getProcess(), "liveness");
        assertNotNull(Liveness.getFaceImage());
        assertNotNull(Liveness.getVideo());
        assertTrue(Liveness.isProcessSuccess());

        assertEquals(BiometricMatch.getProcess(), "biometric_match");
        assertTrue(BiometricMatch.isProcessSuccess());
        assertTrue(Math.abs(BiometricMatch.getScore()-0.8)<0.05);
//        assertEquals(BiometricMatch.getScore(), 0.7884163856506348);
    }

  //  @Test(description = "Onboarding UI/API Test-Scanning Israeli Cheque",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding UI/API Test-Scanning Israeli Cheque")
    public void t06_israeliCheque() throws IOException {
        driver.get(createLinkToFlow(327));
        assertTrue(chequePage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertEquals(chequePage.OCRTitleIsDisplayed(), "Cheque scanning");
        assertEquals(chequePage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(chequePage.ocrDescription(), "Make sure the cheque is not ripped | The cheque is on bright element");
        chequePage.clickOnButton("Scan Cheque");

        imageInjectionFunctionPage.scanImage("./ocr/cheque/normalCheque.jpg", "dd");
        assertEquals(scanResultPage.resultsTitle(), "Scan results");
        assertEquals(scanResultPage.resultFaceImageDescription(), "Cheque image");
        assertTrue(scanResultPage.faceImageIsDisplayed());
        assertEquals(scanResultPage.results("cheque", "Bank Number"), "04");
        assertEquals(scanResultPage.results("cheque", "Cheque Number"), "0050104");
        assertEquals(scanResultPage.results("cheque", "Bank Branch Number"), "12300");
        assertEquals(scanResultPage.results("cheque", "Account Number"), "109592");
        scanResultPage.clickOnButton("Continue");

        //backend
        preProcess.setSessionId(sessionId);
        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertTrue(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "");
        assertEquals(endpointResults.getDataErrorCode(), 0);
//        assertEquals(endpointResults.getMetadata(), metadataValue);

        assertEquals(ILCheque.getProcess(), "ocr");
        assertNotNull(ILCheque.getEnd());
        assertNotNull(ILCheque.getDocumentImage());
        assertNotNull(ILCheque.getChequeNumberLineImage());
        assertEquals(ILCheque.getChequeNumber(), 50104);
        assertEquals(ILCheque.getBankBranchNumber(), 12300);
        assertEquals(ILCheque.getBankNumber(), 04);
        assertEquals(ILCheque.getBankAccountNumber(), 109592);
        assertEquals(ILCheque.getOcrType(), "cheque_document");
        assertTrue(ILCheque.isProcessSuccess());
        assertEquals(ILCheque.getCount(), 1);
    }

    @Test(description = "Onboarding UI/API Test-Liveness process",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding UI/API Test-Liveness process")
    public void t07_liveness() throws IOException {
        driver.get(createLinkToFlow(231));
        ocrPage.chooseLanguage("English");
        assertEquals(scanLivenessPage.scanLivenessTitle(), "Liveness Check");
        assertEquals(scanLivenessPage.scanLivenessSubTitle(), "Before we start make sure that:");
        assertEquals(scanLivenessPage.scanLivenessDescription(), "The phone is at face level | There are good light conditions");
        scanLivenessPage.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/amnon/amnon_live.jpg");

        //backend
        preProcess.setSessionId(sessionId);
        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertTrue(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "");
        assertEquals(endpointResults.getDataErrorCode(), 0);
 //       assertEquals(endpointResults.getMetadata(), metadataValue);

        assertEquals(Liveness.getProcess(), "liveness");
        assertNotNull(Liveness.getFaceImage());
        assertNotNull(Liveness.getVideo());
        assertTrue(Liveness.isProcessSuccess());
    }

    @Test(description = "Onboarding UI/API Test - Scanning OCR with Date Diff task")
    @Description("Onboarding UI/API Test - Scanning OCR with Date Diff task")
    public void t09_onboardingWithDateIf() throws IOException {
        driver.get(createLinkToFlow(144));
        assertTrue(ocrPage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        assertEquals(ocrPage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        ocrPage.clickOnButton("Scan Driving License");

        imageInjectionFunctionPage.scanOCR2Sides("./ocr/dl/harel_dl_front.jpg", "./ocr/dl/harel_dl_back.jpg");

        scanResult.clickOnButton("Continue");
        assertEquals(popupPage.popupTitle(), "General Error");
        assertEquals(popupPage.popupDescription(), "under age");
        popupPage.popupButton();
        assertEquals(faceCompareResults.processEndedText(), "Process ended");

        //backend
        preProcess.setSessionId(sessionId);
        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
         assertTrue(endpointResults.isSuccess());
         assertEquals(endpointResults.getErrorCode(), 0);
         assertFalse(endpointResults.isDataSuccess());
         assertEquals(endpointResults.getDataErrorMessage(), "under age");
         assertEquals(endpointResults.getDataErrorCode(), 23);
//         assertEquals(endpointResults.getMetadata(), "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");

         assertEquals(ILDL.getProcess(), "ocr");
         assertNotNull(ILDL.getEnd());
         assertEquals(ILDL.getDob(), "30.06.1987");
         assertEquals(ILDL.getIssuingDate(), "10.08.2018");
         assertEquals(ILDL.getExpiryDate(), "30.06.2028");
         assertEquals(ILDL.getFirstName(), "הראל");
         assertEquals(ILDL.getLastName(), "אלעזר");
         assertEquals(ILDL.getDateOfBirth(), "1987-06-30T00:00:00.000+0000");
         assertEquals(ILDL.getAddress(), "שפירא משה ח ם 3 ו אשדוד");
         assertEquals(ILDL.getIdNumber(), 300864550);
         assertEquals(ILDL.getDocumentNumber(), 9503218);
         assertEquals(ILDL.getExpirationDate(), "2028-06-30T00:00:00.000+0000");
         assertNotNull(ILDL.getFaceImage());
         assertNotNull(ILDL.getFrontImage());
         assertNotNull(ILDL.getCardImage());
         assertNotNull(ILDL.getBackImage());
         assertEquals(ILDL.getDocType(), "new_back");
         assertEquals(ILDL.getIssueDate(), "2018-08-10T00:00:00.000+0000");
         assertEquals(ILDL.getLicenseCategory(), "B");
         assertEquals(ILDL.getLicenseIssuingYear(), "2012-01-01T00:00:00.000+0000");
         assertEquals(ILDL.getLicenseIssueYear(), 2012);
         assertEquals(ILDL.getOcrType(), "IL-DL");
         assertEquals(ILDL.getBackSideIdNumber(), 300864550);
         assertTrue(ILDL.isFacePosition());
         assertTrue(ILDL.isValidityExpiryDate());
         assertTrue(ILDL.isChecksum());
         assertTrue(ILDL.isFaceSize());
         assertTrue(ILDL.isTemplateMatching());
         assertTrue(ILDL.isFaceRotation());
         assertTrue(ILDL.isProcessSuccess());
         assertEquals(ILDL.getCount(), 1);
    }

    //open bug  https://scanovate.atlassian.net/browse/BD-975
    @Test(description = "Onboarding UI/API Test - Scanning OCR with Comparator task",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding UI/API Test - Scanning OCR with Comparator task")
    public void t10_onboardingWithComparator() throws IOException {
        driver.get(createLinkToFlow(581));
        assertTrue(ocrPage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        assertEquals(ocrPage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        ocrPage.clickOnButton("Scan Israel ID");

        imageInjectionFunctionPage.scanOCR2Sides("./ocr/bioID/nitzan/front/NitzanBioFront.jpg", "./ocr/bioID/fakeBackSide/fakeBackSide.jpg");
        scanResult.clickOnButton("Continue");
        assertEquals(popupPage.popupTitle(), "General Error");
        assertEquals(popupPage.popupDescription(), "back side number doesn't equals to front side id number");
        popupPage.popupButton();
        assertEquals(faceCompareResults.processEndedText(), "Process ended");

        //backend
        preProcess.setSessionId(sessionId);
        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertFalse(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "back side number doesn't equals to front side id number ");
        assertEquals(endpointResults.getDataErrorCode(), 5858);
//        assertEquals(endpointResults.getMetadata(), metadataValue);

        assertEquals(ILID.getProcess(), "ocr");
        assertNotNull(ILID.getEnd());
        assertEquals(ILID.getDob(), "21.08.1988");
        assertEquals(ILID.getIssuingDate(), "23.05.2019");
        assertEquals(ILID.getExpiryDate(), "20.05.2029");
        assertEquals(ILID.getFirstName(), "ניצן");
        assertEquals(ILID.getLastName(), "שוקר");
        assertEquals(ILID.getDateOfBirth(), "1988-08-21T00:00:00.000+0000");
        assertEquals(ILID.getGender(), "נקבה");
        assertEquals(ILID.getIdNumber(), 200761625);
        assertEquals(ILID.getExpirationDate(), "2029-05-20T00:00:00.000+0000");
        assertNotNull(ILID.getFaceImage());
        assertNotNull(ILID.getFrontImage());
        assertNotNull(ILID.getCardImage());
        assertNotNull(ILID.getBackImage());
        assertEquals(ILID.getDocType(), "il_id_bio");
        assertEquals(ILID.getIssueDate(), "2019-05-23T00:00:00.000+0000");
        assertEquals(ILID.getPlaceOfBirth(), "ישראל");
   //     assertNotNull(ILID.getScanVideo());
        assertEquals(ILID.getOcrType(), "IL-ID");
        assertEquals(ILID.getBackSideIdNumber(), 34622464);
        assertTrue(ILID.isPeriodBetweenExpiryDateAndIssueDate());
        assertTrue(ILID.isFacePosition());
        assertTrue(ILID.isValidityExpiryDate());
        assertTrue(ILID.isFaceSize());
        assertTrue(ILID.isTemplateMatching());
        assertTrue(ILID.isFaceRotation());
        assertTrue(ILID.isDocumentChipValid());
        assertTrue(ILID.isColorCheck());
        assertTrue(ILID.isFaceImageReplacedWithPassportImage());
        assertTrue(ILID.isColourfulImageBackSide());
        assertTrue(ILID.isChecksum());
        assertFalse(ILID.isValidateIfFrontAndBacksideIdMatch());
        assertTrue(ILID.isChecksumBackside());
        assertTrue(ILID.isTemplateMatchingBackside());
        assertTrue(ILID.isProcessSuccess());
        assertEquals(ILID.getCount0(), 1);
    }
}
