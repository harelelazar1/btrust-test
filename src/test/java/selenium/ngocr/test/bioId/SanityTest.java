package selenium.ngocr.test.bioId;

import api.Variables;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.ILIDVariables;
import utilities.MongoDB.Variables.Ocr.OcrHandlers;
import utilities.MongoDB.Variables.StartSessionVariables;

import java.io.IOException;

public class SanityTest extends BaseTest {


    MainPage mainPage;
    Variables variables;
    MongoHandler mongoHandler;

    @BeforeClass
    public void resetVariables() {
        mainPage = new MainPage(driver);
        variables = new Variables();
        mongoHandler = new MongoHandler();
        mainPage = new MainPage(driver);
    }

    @BeforeMethod
    public void linkToOCR() {
        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            driver.get(OCR_DEMO);
        } else {
            driver.get(OCR_DEMO_NEW);
        }
    }

    public static void chooseConfigFile() {
        MainPage mainPage = new MainPage(driver);
        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            mainPage.chooseConfigFile("two_sides.json");
        } else {
            mainPage.chooseConfigFile(System.getProperty("COMPANY_ID"));
        }
    }

    @Test(description = "bio Id demo sanity test",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Run Demo End to End test of bio Id")
    public void t01_e2eBioIdDemoFlow() throws IOException, InterruptedException {
        SanityTest.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel ID");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack1.jpg", 1, false, null);
        ResultsPage resultsPage = new ResultsPage(driver);
//        Assert.assertEquals(resultsPage.verifyListsSizes(TEN_SECONDS), 41);
        resultsPage.collectResults(variables);

        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            Assert.assertEquals(variables.getActionType(), "complete");
            Assert.assertEquals(variables.getStatus(), "success");
            Assert.assertNotNull(variables.getCaseId());
            Assert.assertEquals(variables.getConfigFileName(), "two_sides.json");
            Assert.assertTrue(variables.isSuccess());
        } else {
            Assert.assertEquals(variables.getActionType(), "complete");
            Assert.assertEquals(variables.getStatus(), "success");
//            Assert.assertNotNull(variables.getCaseId());
//            Assert.assertEquals(variables.getConfigFileName(), "two_sides.json");
//            Assert.assertTrue(variables.isSuccess());
        }

        //Front section
        Assert.assertEquals(variables.getCardType1(), "bio_front");
        Assert.assertEquals(variables.getLastNameHebrew(), "שוקר");
        Assert.assertEquals(variables.getFirstNameHebrew(), "ניצן");
        Assert.assertEquals(variables.getDateOfBirth(), "21.08.1988");
        Assert.assertEquals(variables.getDateOfIssue(), "23.5.2019");
        Assert.assertEquals(variables.getDateOfExpiry(), "20.05.2029");
        Assert.assertEquals(variables.getIdNumber(), 200761625);
        Assert.assertTrue(variables.isImageIsColorful());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isPeriodBetweenIssueAndExpiry());
        Assert.assertTrue(variables.isChipAuth());
        Assert.assertFalse(variables.isFaceImageReplacedWithPassportImage());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertTrue(variables.isIssueDateValid());
        // Images
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getInputImage2(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
        Assert.assertEquals(variables.getCroppedImage2(), "cropped_image");

        //Back section
        Assert.assertEquals(variables.getCardType2(), "bio_back");
        Assert.assertEquals(variables.getIdNumber2(), 200761625);
        Assert.assertEquals(variables.getPlaceOfBirth(), "ישראל");
        Assert.assertEquals(variables.getGender(), "זכר");
        Assert.assertEquals(variables.getCitizenship(), "אזרחות ישראלית");
        Assert.assertTrue(variables.isImageIsColorful2());
        Assert.assertTrue(variables.isIdChecksumValid2());
        Assert.assertTrue(variables.isTemplateMatched2());
        Assert.assertTrue(variables.isDocumentInFrame2());
        Assert.assertTrue(variables.isIdNumberMatchesFront());
//        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");

        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "IL-ID");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "two_sides.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-ID");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");

        Assert.assertEquals(ILIDVariables.getLastNameHebrew(), "שוקר");
        Assert.assertEquals(ILIDVariables.getFirstNameHebrew(), "ניצן");
        Assert.assertEquals(ILIDVariables.getDateOfBirth(), "21.08.1988");
        Assert.assertEquals(ILIDVariables.getDateOfIssue(), "23.5.2019");
        Assert.assertEquals(ILIDVariables.getDateOfExpiry(), "20.05.2029");
        Assert.assertEquals(ILIDVariables.getIdNumber(), "200761625");
        Assert.assertEquals(ILIDVariables.getCardType(), "bio_front");
        Assert.assertNotNull(ILIDVariables.getFaceImage());
        Assert.assertNotNull(ILIDVariables.getInputImage());
        Assert.assertNotNull(ILIDVariables.getCroppedImage());
        Assert.assertTrue(ILIDVariables.isImageIsColorful());
        Assert.assertTrue(ILIDVariables.isIdChecksumValid());
        Assert.assertTrue(ILIDVariables.isTemplateMatch());
        Assert.assertTrue(ILIDVariables.isPeriodBetweenIssueAndExpiryValid());
        Assert.assertTrue(ILIDVariables.isChipAuth());
        Assert.assertTrue(ILIDVariables.isFaceImageReplacedWithPassportImage());
        Assert.assertTrue(ILIDVariables.isFaceSizeValid());
        Assert.assertTrue(ILIDVariables.isFacePositionValid());
        Assert.assertTrue(ILIDVariables.isFaceRotationValid());
        Assert.assertTrue(ILIDVariables.isDocumentInFrame());
        Assert.assertTrue(ILIDVariables.isExpiryDateValid());
        Assert.assertTrue(ILIDVariables.isIssueDateValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");

        Assert.assertEquals(OcrHandlers.getActionType2(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder2(), 2);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage2(), "back");
        Assert.assertEquals(ILIDVariables.getIdNumber2(), "200761625");
        Assert.assertEquals(ILIDVariables.getPlaceOfBirth(), "ישראל");
        Assert.assertEquals(ILIDVariables.getGender(), "זכר");
        Assert.assertEquals(ILIDVariables.getCardType2(), "bio_back");
        Assert.assertNotNull(ILIDVariables.getInputImage2());
        Assert.assertNotNull(ILIDVariables.getCroppedImage2());
        Assert.assertTrue(ILIDVariables.isImageIsColorful2());
        Assert.assertTrue(ILIDVariables.isIdChecksumValid2());
        Assert.assertTrue(ILIDVariables.isTemplateMatch2());
        Assert.assertTrue(ILIDVariables.isIdNumberMatchesFront());
        Assert.assertTrue(ILIDVariables.isDocumentInFrame2());
        Assert.assertEquals(OcrHandlers.getStageStatus2(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");

    }


    @Test(description = "bio Id demo sanity test",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Run Demo End to End test of bio Id")
    public void t02_e2eBioIdNewDemoFlow() throws IOException, InterruptedException {
        SanityTest.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel ID");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/new bioID/Gregory/newBioIDFront.jpeg", "./ocr/new bioID/Gregory/newBioIDBack.jpeg", 1, false, null);
        ResultsPage resultsPage = new ResultsPage(driver);
//        Assert.assertEquals(resultsPage.verifyListsSizes(TEN_SECONDS), 41);
        resultsPage.collectResults(variables);

        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            Assert.assertEquals(variables.getActionType(), "complete");
            Assert.assertEquals(variables.getStatus(), "success");
            Assert.assertNotNull(variables.getCaseId());
            Assert.assertEquals(variables.getConfigFileName(), "two_sides.json");
            Assert.assertTrue(variables.isSuccess());
        } else {
            Assert.assertEquals(variables.getActionType(), "complete");
            Assert.assertEquals(variables.getStatus(), "success");
//            Assert.assertNotNull(variables.getCaseId());
//            Assert.assertEquals(variables.getConfigFileName(), "two_sides.json");
//            Assert.assertTrue(variables.isSuccess());
        }

        //Front section
        Assert.assertEquals(variables.getCardType1(), "bio_type_2_front");
        Assert.assertEquals(variables.getLastNameHebrew(), "צ׳רנומורדיק");
        Assert.assertEquals(variables.getFirstNameHebrew(), "גריגורי");
        Assert.assertEquals(variables.getDateOfBirth(), "05.03.1992");
        Assert.assertEquals(variables.getDateOfIssue(), "5.4.2022");
        Assert.assertEquals(variables.getDateOfExpiry(), "03.04.2027");
        Assert.assertEquals(variables.getIdNumber(), 345648513);
        Assert.assertTrue(variables.isImageIsColorful());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isPeriodBetweenIssueAndExpiry());
        Assert.assertTrue(variables.isChipAuth());
        Assert.assertFalse(variables.isFaceImageReplacedWithPassportImage());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertFalse(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertTrue(variables.isIssueDateValid());
        // Images
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getInputImage2(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
        Assert.assertEquals(variables.getCroppedImage2(), "cropped_image");

        //Back section
        Assert.assertEquals(variables.getCardType2(), "bio_type_2_back");
        Assert.assertEquals(variables.getIdNumber2(), 345648513);
        Assert.assertEquals(variables.getPlaceOfBirth(), "רוסיה");
        Assert.assertEquals(variables.getGender(), "זכר");
        Assert.assertEquals(variables.getCitizenship(), "אזרחות ישראלית");
        Assert.assertTrue(variables.isImageIsColorful2());
        Assert.assertTrue(variables.isIdChecksumValid2());
        Assert.assertTrue(variables.isTemplateMatched2());
        Assert.assertTrue(variables.isDocumentInFrame2());
        Assert.assertTrue(variables.isIdNumberMatchesFront());
    }
}