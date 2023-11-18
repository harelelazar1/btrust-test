package btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.Sanity;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.onboardingProcess.ui.pagesObject.questionnaire.FileTypePage;
import btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.MobileInteractionUIBase;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ILID extends MobileInteractionUIBase {

    OCRPage ocrPage;
    ChequePage chequePage;
    ImageInjectionFunctionPage imageInjectionFunctionPage;
    ScanResultPage scanResultPage;
    ScanLivenessPage scanLivenessPage;
    CompletedPage completedPage;
    FaceCompareResultsPage faceCompareResults;
    FileTypePage fileTypePage;
    PopupPage popupPage;

    @BeforeMethod(alwaysRun = true)
    public void initSession() throws IOException {
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
        completedPage = new CompletedPage(driver);
        fileTypePage = new FileTypePage(driver);
        popupPage = new PopupPage(driver);
    }



    @Test(description = "Onboarding UI Test Scanning BioId card and Liveness process - Face Match")
    @Description("Onboarding UI Test Scanning BioId card and Liveness process-Face Match")
    public void t01_bioIdAndLivenessFaceNotMatch() throws IOException {
        driver.get(createLinkToFlow(26));
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
        assertEquals(scanResultPage.results("blue id", "Citizenship"), "אזרהות ישראלית");
        scanResultPage.clickOnButton("Continue");
        assertTrue(scanLivenessPage.scanLivenessImage());
        assertEquals(scanLivenessPage.scanLivenessTitle(), "Liveness Check");
        assertEquals(scanLivenessPage.scanLivenessSubTitle(), "Before we start make sure that:");
        assertEquals(scanLivenessPage.scanLivenessDescription(), "The phone is at face level | There are good light conditions");
        scanLivenessPage.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/nitzan/straight.jpg");
        assertEquals(faceCompareResults.faceCompareTitle(), "Face Compare Results");
        assertTrue(faceCompareResults.ocrPicture());
        assertEquals(faceCompareResults.ocrDescription(), "Document Image");
        assertTrue(faceCompareResults.livenessPictureIsDisplayed());
        assertEquals(faceCompareResults.livenessDescription(), "Liveness Check");
        assertTrue(faceCompareResults.faceMatchIcon());
        assertEquals(faceCompareResults.faceMatchTitle(), "Face Match");
    }

    @Test(description = "Onboarding UI Test Scanning BioId card and Liveness process - Face doesn't Match")
    @Description("Onboarding UI Test Scanning BioId card and Liveness process-Face doesn't Match")
    public void t02_bioIdAndLivenessFaceNotMatch() throws IOException {
        driver.get(createLinkToFlow(26));
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
        assertEquals(scanResultPage.results("blue id", "Citizenship"), "אזרהות ישראלית");
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
    }


    @Test(description = "Onboarding UI Test Scanning BlueId card and Liveness process")
    @Description("Onboarding UI Test Scanning BlueId card and Liveness process")
    public void t03_blueIdAndLivenessOnboard() throws IOException {
        driver.get(createLinkToFlow(26));
        ocrPage.chooseLanguage("English");
        assertTrue(ocrPage.ocrImage());
        assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        assertEquals(ocrPage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");


        ocrPage.clickOnButton("Scan Israel ID");
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/blueID/liad/blueID_color.jpg", null);
        assertEquals(scanResultPage.resultsTitle(), "Scan results");
        assertEquals(scanResultPage.resultFaceImageDescription(), "Document image");
        assertTrue(scanResultPage.faceImageIsDisplayed());
        assertEquals(scanResultPage.results("blue id", "Date of birth"), "17.01.1993");
//        assertEquals(scanResultPage.results("blue id", "Expiration date"), "28.01.2025");
        assertEquals(scanResultPage.results("blue id", "Date of Issue"), "28.01.2015");
        assertEquals(scanResultPage.results("blue id", "First Name"), "ליעד");
        assertEquals(scanResultPage.results("blue id", "Last Name"), "טובי");
        assertEquals(scanResultPage.results("blue id", "Gender"), "זכר");
        assertEquals(scanResultPage.results("blue id", "ID Number"), "307922328");
        assertEquals(scanResultPage.results("blue id", "Place of birth"), "ישראל");
        assertEquals(scanResultPage.results("blue id", "Citizenship"), "אזרחות ישראלית");
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
    }


    @Test(description = "Onboarding UI Test Scanning GreenId card and Liveness process", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding UI Test Scanning GreenId card and Liveness process")
    public void t04_greenIdAndLivenessOnboard() throws IOException, InterruptedException {
        driver.get(createLinkToFlow(26));
        assertTrue(ocrPage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        assertEquals(ocrPage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        ocrPage.clickOnButton("Scan Israel ID");

        imageInjectionFunctionPage.scanOCR2Sides("./ocr/greenID/harel/harel_Green_Id.jpg", null);

        assertEquals(scanResultPage.resultsTitle(), "Scan results");
        assertEquals(scanResultPage.resultFaceImageDescription(), "Document image");
        assertTrue(scanResultPage.faceImageIsDisplayed());
        assertEquals(scanResultPage.results("green id", "Date of birth"), "30.06.1987");
        assertEquals(scanResultPage.results("green id", "Date of Issue"), "11.01.2010");
        assertEquals(scanResultPage.results("green id", "First Name"), "הראל");
        assertEquals(scanResultPage.results("green id", "Last Name"), "אלעזר");
        assertEquals(scanResultPage.results("green id", "Gender"), "זכר");
        assertEquals(scanResultPage.results("green id", "ID Number"), "300864550");
        assertEquals(scanResultPage.results("green id", "Place of birth"), "ישראל");
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
    }

    @Test(description = "Onboarding UI Test Scanning GreenId old card and Liveness process", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding UI Test Scanning GreenId old card and Liveness process")
    public void t05_greenIdOldAndLivenessOnboard() throws IOException, InterruptedException {
        driver.get(createLinkToFlow(26));
        assertTrue(ocrPage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        assertEquals(ocrPage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        ocrPage.clickOnButton("Scan Israel ID");

        imageInjectionFunctionPage.scanOCR2Sides("./ocr/greenId_old/greenIDold.jpg", null);

        assertEquals(scanResultPage.resultsTitle(), "Scan results");
        assertEquals(scanResultPage.resultFaceImageDescription(), "Document image");
        assertTrue(scanResultPage.faceImageIsDisplayed());
        assertEquals(scanResultPage.results("green id", "Date of birth"), "13.10.1964");
        assertEquals(scanResultPage.results("green id", "Date of Issue"), "27.12.95");
        assertEquals(scanResultPage.results("green id", "First Name"), "ג'ניס");
        assertEquals(scanResultPage.results("green id", "Last Name"), "וייצמן");
        assertEquals(scanResultPage.results("green id", "Gender"), "נקבה");
        assertEquals(scanResultPage.results("green id", "ID Number"), "015977713");
        assertEquals(scanResultPage.results("green id", "Place of birth"), "קנדה");
        scanResultPage.clickOnButton("Continue");
        assertTrue(scanLivenessPage.scanLivenessImage());
        assertEquals(scanLivenessPage.scanLivenessTitle(), "Liveness Check");
        assertEquals(scanLivenessPage.scanLivenessSubTitle(), "Before we start make sure that:");
        assertEquals(scanLivenessPage.scanLivenessDescription(), "The phone is at face level | There are good light conditions");
        scanLivenessPage.startButton();
//        if (delayed){
//            Thread.sleep(82000);
//        }
        imageInjectionFunctionPage.scanLiveness("./liveness/bar/bar_new.jpg");
        assertEquals(faceCompareResults.faceCompareTitle(), "Face Compare Results");
        assertTrue(faceCompareResults.ocrPicture());
        assertEquals(faceCompareResults.ocrDescription(), "Document Image");
        assertTrue(faceCompareResults.livenessPictureIsDisplayed());
        assertEquals(faceCompareResults.livenessDescription(), "Liveness Check");
        assertTrue(faceCompareResults.faceMatchIcon());
        assertEquals(faceCompareResults.faceMatchTitle(), "Face not Match");
    }





  //  @Test(description = "Onboarding UI Test Questionnaire process upload ID image", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding UI Test Questionnaire process upload ID image")
    public void t05_questionnaireWithSingleFrameOfId() {
        driver.get(createLinkToFlow(89));
        ocrPage.chooseLanguage("English");
        assertTrue(fileTypePage.questionTitleIsDisplayed("upload Image"));
        fileTypePage.uploadFile("./ocr/blueID/liad/blueID.jpg");
        assertTrue(fileTypePage.nextButton());
        assertEquals(faceCompareResults.processEndedText(), "Process ended");
    }

//    @Test(description = "Onboarding UI Test Questionnaire process upload DL image", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding UI Test Questionnaire process upload DL image")
    public void t06_questionnaireWithSingleFrameOfDl() {
        driver.get(createLinkToFlow(89));
        ocrPage.chooseLanguage("English");
        assertTrue(fileTypePage.questionTitleIsDisplayed("upload Image"));
        fileTypePage.uploadFile("./ocr/dl/LiadDLFront.jpg");
        assertTrue(fileTypePage.nextButton());
        assertEquals(faceCompareResults.processEndedText(), "Process ended");
    }

 //   @Test(description = "Onboarding UI Test Questionnaire process upload MRZ image", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding UI Test Questionnaire process upload MRZ image")
    public void t07_questionnaireWithSingleFrameOfMrz() {
        driver.get(createLinkToFlow(89));
        ocrPage.chooseLanguage("English");
        assertTrue(fileTypePage.questionTitleIsDisplayed("upload Image"));
        fileTypePage.uploadFile("./ocr/mrz/avner/avner2.jpg");
        assertTrue(fileTypePage.nextButton());
        assertEquals(faceCompareResults.processEndedText(), "Process ended");
    }

 //   @Test(description = "Onboarding UI Test Scanning Lawyer card", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding UI Test Scanning Lawyer card")
    public void t08_LawId() throws IOException {
        driver.get(createLinkToFlow(367));
        assertTrue(ocrPage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        assertEquals(ocrPage.subTitleOCRPage(), "Before we start make sure that:");
        assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        ocrPage.clickOnButton("Scan Israel Lawyer Card");
        imageInjectionFunctionPage.scanOCR("./ocr/lawyerID/yoram_2020_1.jpeg", null);
        assertEquals(scanResultPage.resultsTitle(), "Scan results");
        assertEquals(scanResultPage.results("lawyer", "ID Number"), "057798639");
        assertEquals(scanResultPage.results("lawyer", "Full Name Hebrew"), "יורם יהודה");
        assertEquals(scanResultPage.results("lawyer", "Full Name English"), "YORAM YEHUDA");
        assertEquals(scanResultPage.results("lawyer", "Member Number"), "14215");
        assertEquals(scanResultPage.results("lawyer", "Code"), "142158839");
        assertEquals(scanResultPage.results("lawyer", "Address"), "לוי אליהו סאלם 41/30ב רמלה 7250300");
    }
}
