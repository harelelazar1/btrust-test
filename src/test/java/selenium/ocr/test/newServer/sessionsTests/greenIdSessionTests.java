package selenium.ocr.test.newServer.sessionsTests;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import selenium.BaseTest;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.ILIDVariables;
import utilities.MongoDB.Variables.Ocr.OcrHandlers;
import utilities.MongoDB.Variables.StartSessionVariables;

import java.io.IOException;

public class greenIdSessionTests extends BaseTest {

    Variables variables;
    MongoHandler mongoHandler;
    MainPage mainPage;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        mainPage = new MainPage(driver);
        driver.get(OCR_DEMO);
        mainPage.chooseFromServicesList("Israel ID");
    }

    @Test(description = "green id positive session test #1")
    @Description("Green id positive injection folder of full session images #1")
    public void t01_e2ePositiveGreenIdFullSessionTest() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.injectFolder("./ocr/sessions/greenId/1", null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 26);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        //Front section
        Assert.assertEquals(variables.getCardType1(),"green");
        Assert.assertEquals(variables.getIdNumber(), 310348404);
        Assert.assertEquals(variables.getLastNameHebrew(), "סולומונוב");
        Assert.assertEquals(variables.getDateOfBirth(), "12.12.1986");
        Assert.assertEquals(variables.getDateOfIssue(), "21.06.2012");
        Assert.assertEquals(variables.getFirstNameHebrew(), "ליבנת");
        Assert.assertEquals(variables.getGender(), "נקבה");
        Assert.assertEquals(variables.getPlaceOfBirth(), "ברית המועצות");

        Assert.assertTrue(variables.isImageIsColorful());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertFalse(variables.isStampDetected());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
                Assert.assertTrue(variables.isIssueDateValid());

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "IL-ID");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-ID");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");

        Assert.assertEquals(ILIDVariables.getDateOfBirth(), "12.12.1986");
        Assert.assertEquals(ILIDVariables.getDateOfIssue(), "21.06.2012");
        Assert.assertEquals(ILIDVariables.getFirstNameHebrew(), "ליבנת");
        Assert.assertEquals(ILIDVariables.getLastNameHebrew(), "סולומונוב");
        Assert.assertEquals(ILIDVariables.getGender(), "נקבה");
        Assert.assertEquals(ILIDVariables.getIdNumber(), "310348404");
        Assert.assertEquals(ILIDVariables.getPlaceOfBirth(), "ברית המועצות");
        Assert.assertEquals(ILIDVariables.getCardType(), "green");
        Assert.assertNotNull(ILIDVariables.getFaceImage());
        Assert.assertNotNull(ILIDVariables.getInputImage());
        Assert.assertNotNull(ILIDVariables.getCroppedImage());
        Assert.assertTrue(ILIDVariables.isImageIsColorful());
        Assert.assertTrue(ILIDVariables.isIdChecksumValid());
        Assert.assertTrue(ILIDVariables.isTemplateMatch());
        Assert.assertTrue(ILIDVariables.isStampDetected());
        Assert.assertTrue(ILIDVariables.isFaceSizeValid());
        Assert.assertTrue(ILIDVariables.isFacePositionValid());
        Assert.assertTrue(ILIDVariables.isFaceRotationValid());
        Assert.assertTrue(ILIDVariables.isDocumentInFrame());
        Assert.assertTrue(ILIDVariables.isIssueDateValid());

        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(description = "green id positive session test #2")
    @Description("Green id positive injection folder of full session images #2")
    public void t02_e2ePositiveGreenIdFullSessionTest() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.injectFolder("./ocr/sessions/greenId/2", null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 26);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        //Front section
        Assert.assertEquals(variables.getCardType1(),"green");
        Assert.assertEquals(variables.getDateOfBirth(), "10.01.1994");
        Assert.assertEquals(variables.getDateOfIssue(), "01.02.2010");
        Assert.assertEquals(variables.getFirstNameHebrew(), "אדריאנו");
        Assert.assertEquals(variables.getGender(), "זכר");
        Assert.assertEquals(variables.getIdNumber(), 312200116);
        Assert.assertEquals(variables.getLastNameHebrew(), "אלעוקה");
        Assert.assertEquals(variables.getPlaceOfBirth(), "ישראל");

        Assert.assertTrue(variables.isImageIsColorful());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertFalse(variables.isStampDetected());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
                Assert.assertTrue(variables.isIssueDateValid());

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "IL-ID");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-ID");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");

        Assert.assertEquals(ILIDVariables.getDateOfBirth(), "10.01.1994");
        Assert.assertEquals(ILIDVariables.getDateOfIssue(), "01.02.2010");
        Assert.assertEquals(ILIDVariables.getFirstNameHebrew(), "אדריאנו");
        Assert.assertEquals(ILIDVariables.getLastNameHebrew(), "אלעוקה");
        Assert.assertEquals(ILIDVariables.getGender(), "זכר");
        Assert.assertEquals(ILIDVariables.getIdNumber(), "312200116");
        Assert.assertEquals(ILIDVariables.getPlaceOfBirth(), "ישראל");
        Assert.assertEquals(ILIDVariables.getCardType(), "green");
        Assert.assertNotNull(ILIDVariables.getFaceImage());
        Assert.assertNotNull(ILIDVariables.getInputImage());
        Assert.assertNotNull(ILIDVariables.getCroppedImage());
        Assert.assertTrue(ILIDVariables.isImageIsColorful());
        Assert.assertTrue(ILIDVariables.isIdChecksumValid());
        Assert.assertTrue(ILIDVariables.isTemplateMatch());
        Assert.assertTrue(ILIDVariables.isStampDetected());
        Assert.assertTrue(ILIDVariables.isFaceSizeValid());
        Assert.assertTrue(ILIDVariables.isFacePositionValid());
        Assert.assertTrue(ILIDVariables.isFaceRotationValid());
        Assert.assertTrue(ILIDVariables.isDocumentInFrame());
        Assert.assertTrue(ILIDVariables.isIssueDateValid());

        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(description = "green id positive session test #3")
    @Description("Green id positive injection folder of full session images #3")
    public void t03_e2ePositiveGreenIdFullSessionTest() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.injectFolder("./ocr/sessions/greenId/3", null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 26);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        //Front section
        Assert.assertEquals(variables.getCardType1(),"green");
        Assert.assertEquals(variables.getDateOfBirth(), "31.01.1968");
        Assert.assertEquals(variables.getDateOfIssue(), "21.07.2010");
        Assert.assertEquals(variables.getFirstNameHebrew(), "מורום");
        Assert.assertEquals(variables.getGender(), "נקבה");
        Assert.assertEquals(variables.getIdNumber(), 303482186);
        Assert.assertEquals(variables.getLastNameHebrew(), "מטטוב");
        Assert.assertEquals(variables.getPlaceOfBirth(), "ברית המועצות");

        Assert.assertTrue(variables.isImageIsColorful());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertFalse(variables.isStampDetected());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
                Assert.assertTrue(variables.isIssueDateValid());

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "IL-ID");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-ID");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");

        Assert.assertEquals(ILIDVariables.getDateOfBirth(), "31.01.1968");
        Assert.assertEquals(ILIDVariables.getDateOfIssue(), "21.07.2010");
        Assert.assertEquals(ILIDVariables.getFirstNameHebrew(), "מורום");
        Assert.assertEquals(ILIDVariables.getLastNameHebrew(), "מטטוב");
        Assert.assertEquals(ILIDVariables.getGender(), "נקבה");
        Assert.assertEquals(ILIDVariables.getIdNumber(), "303482186");
        Assert.assertEquals(ILIDVariables.getPlaceOfBirth(), "ברית המועצות");
        Assert.assertEquals(ILIDVariables.getCardType(), "green");
        Assert.assertNotNull(ILIDVariables.getFaceImage());
        Assert.assertNotNull(ILIDVariables.getInputImage());
        Assert.assertNotNull(ILIDVariables.getCroppedImage());
        Assert.assertTrue(ILIDVariables.isImageIsColorful());
        Assert.assertTrue(ILIDVariables.isIdChecksumValid());
        Assert.assertTrue(ILIDVariables.isTemplateMatch());
        Assert.assertTrue(ILIDVariables.isStampDetected());
        Assert.assertTrue(ILIDVariables.isFaceSizeValid());
        Assert.assertTrue(ILIDVariables.isFacePositionValid());
        Assert.assertTrue(ILIDVariables.isFaceRotationValid());
        Assert.assertTrue(ILIDVariables.isDocumentInFrame());
        Assert.assertTrue(ILIDVariables.isIssueDateValid());

        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }


    @Test(description = "green id positive session test #5")
    @Description("Green id positive injection folder of full session images #5")
    public void t05_e2ePositiveGreenIdFullSessionTest() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.injectFolder("./ocr/sessions/greenId/5", null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 26);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(),"complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        //Front section
        Assert.assertEquals(variables.getCardType1(), "green");
        Assert.assertEquals(variables.getDateOfBirth(), "27.03.1984");
        Assert.assertEquals(variables.getDateOfIssue(), "10.01.2001");
        Assert.assertEquals(variables.getFirstNameHebrew(), "פנינה");
        Assert.assertEquals(variables.getGender(), "נקבה\\");
        Assert.assertEquals(variables.getIdNumber(), 66410465);
        Assert.assertEquals(variables.getLastNameHebrew(), "בר נתן");
        Assert.assertEquals(variables.getPlaceOfBirth(), "ישראל");
        Assert.assertTrue(variables.isImageIsColorful());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isStampDetected());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
        Assert.assertTrue(variables.isIssueDateValid());

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "IL-ID");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-ID");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");

        Assert.assertEquals(ILIDVariables.getDateOfBirth(), "27.03.1984");
        Assert.assertEquals(ILIDVariables.getDateOfIssue(), "10.01.2001");
        Assert.assertEquals(ILIDVariables.getFirstNameHebrew(), "פנינה");
        Assert.assertEquals(ILIDVariables.getLastNameHebrew(), "בר נתן");
        Assert.assertEquals(ILIDVariables.getGender(), "נקבה\\");
        Assert.assertEquals(ILIDVariables.getIdNumber(), "066410465");
        Assert.assertEquals(ILIDVariables.getPlaceOfBirth(), "ישראל");
        Assert.assertEquals(ILIDVariables.getCardType(), "green");
        Assert.assertNotNull(ILIDVariables.getFaceImage());
        Assert.assertNotNull(ILIDVariables.getInputImage());
        Assert.assertNotNull(ILIDVariables.getCroppedImage());
        Assert.assertTrue(ILIDVariables.isImageIsColorful());
        Assert.assertTrue(ILIDVariables.isIdChecksumValid());
        Assert.assertTrue(ILIDVariables.isTemplateMatch());
        Assert.assertTrue(ILIDVariables.isStampDetected());
        Assert.assertTrue(ILIDVariables.isFaceSizeValid());
        Assert.assertTrue(ILIDVariables.isFacePositionValid());
        Assert.assertTrue(ILIDVariables.isFaceRotationValid());
        Assert.assertTrue(ILIDVariables.isDocumentInFrame());
        Assert.assertTrue(ILIDVariables.isIssueDateValid());

        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }
}
