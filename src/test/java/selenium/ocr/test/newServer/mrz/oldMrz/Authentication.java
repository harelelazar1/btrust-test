package selenium.ocr.test.newServer.mrz.oldMrz;

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
import utilities.MongoDB.Variables.Ocr.MRZVariables;
import utilities.MongoDB.Variables.Ocr.OcrHandlers;
import utilities.MongoDB.Variables.StartSessionVariables;

import java.io.IOException;

public class Authentication extends BaseTest {

    Variables variables;
    MongoHandler mongoHandler;
    MainPage mainPage;
    Injection injection;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        mainPage = new MainPage(driver);
        injection = new Injection(driver);
        driver.get(OCR_DEMO);
        mainPage.chooseFromServicesList("MRZ");
    }

    @Test(description = "old mrz without face image")
    @Description("Old mrz without face image")
    public void t01_oldMrzWithoutFace() throws IOException, InterruptedException {
        injection.imageInjectionConfigure("./ocr/mrz/guy/guyMrzWithoutFace.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 28);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getMrzText(), "P<ISRSTIEBEL<<GUY<<<<<<<<<<<<<<<<<<<<<<<<<<<23369458<8ISR8609199M26120410<2649440<1<<<80");
        Assert.assertEquals(variables.getMrzType(), "TD3");
        Assert.assertEquals(variables.getDocumentType(), "passport");
        
        Assert.assertEquals(variables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(variables.getLastNameEnglish(), "STIEBEL");
        Assert.assertEquals(variables.getFirstNameEnglish(), "GUY");
        Assert.assertEquals(variables.getPassportNumber(), 23369458);
        Assert.assertEquals(variables.getNationalityCode(), "ISR");
        Assert.assertEquals(variables.getDateOfBirth(), "19/09/86");
        Assert.assertEquals(variables.getGender(), "Male");
        Assert.assertEquals(variables.getDateOfExpiry(), "04/12/26");
        Assert.assertEquals(variables.getPersonalNumber(), "0-2649440-1");

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
        

        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertFalse(variables.isFaceSizeValid());
        Assert.assertFalse(variables.isFacePositionValid());
        Assert.assertFalse(variables.isFaceRotationValid());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "MRZ");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "MRZ");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");
        Assert.assertEquals(MRZVariables.getMrzText(), "P<ISRSTIEBEL<<GUY<<<<<<<<<<<<<<<<<<<<<<<<<<<23369458<8ISR8609199M26120410<2649440<1<<<80");
        Assert.assertEquals(MRZVariables.getMrzType(), "TD3");
        Assert.assertEquals(MRZVariables.getDocumentType(), "passport");
        Assert.assertEquals(MRZVariables.getDocumentSubType(), "");
        Assert.assertEquals(MRZVariables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(MRZVariables.getLastName(), "STIEBEL");
        Assert.assertEquals(MRZVariables.getFirstName(), "GUY");
        Assert.assertEquals(MRZVariables.getPassportNumber(), "23369458");
        Assert.assertEquals(MRZVariables.getNationalityCode(), "ISR");
        Assert.assertEquals(MRZVariables.getDateOfBirth(), "19/09/86");
        Assert.assertEquals(MRZVariables.getGender(), "Male");
        Assert.assertEquals(MRZVariables.getDateOfExpiry(), "04/12/26");
        Assert.assertEquals(MRZVariables.getPersonalNumber(), "0-2649440-1");
        Assert.assertFalse(MRZVariables.isMightBeTruncated());
        Assert.assertNotNull(MRZVariables.getFaceImage());
        Assert.assertNotNull(MRZVariables.getInputImage());
        Assert.assertNotNull(MRZVariables.getCroppedImage());
        Assert.assertNotNull(MRZVariables.getCroppedFieldImage());
        Assert.assertTrue(MRZVariables.isDocumentInFrame());
        Assert.assertTrue(MRZVariables.isExpiryDateValid());
        Assert.assertFalse(MRZVariables.isFaceSizeValid());
        Assert.assertFalse(MRZVariables.isFacePositionValid());
        Assert.assertFalse(MRZVariables.isFaceRotationValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(description = "old mrz without face image")
    @Description("Old mrz without face image")
    public void t02_oldMrzWithFaceSizeInvalid() throws IOException, InterruptedException {
        injection.imageInjectionConfigure("./ocr/mrz/guy/guyMrzWithFaceSizeInvalid.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 28);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getMrzText(), "P<ISRSTIEBEL<<GUY<<<<<<<<<<<<<<<<<<<<<<<<<<<23369458<8ISR8609199M26120410<2649440<1<<<80");
        Assert.assertEquals(variables.getMrzType(), "TD3");
        Assert.assertEquals(variables.getDocumentType(), "passport");
        
        Assert.assertEquals(variables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(variables.getLastNameEnglish(), "STIEBEL");
        Assert.assertEquals(variables.getFirstNameEnglish(), "GUY");
        Assert.assertEquals(variables.getPassportNumber(), 23369458);
        Assert.assertEquals(variables.getNationalityCode(), "ISR");
        Assert.assertEquals(variables.getDateOfBirth(), "19/09/86");
        Assert.assertEquals(variables.getGender(), "Male");
        Assert.assertEquals(variables.getDateOfExpiry(), "04/12/26");
        Assert.assertEquals(variables.getPersonalNumber(), "0-2649440-1");

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
        

        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertFalse(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "MRZ");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "MRZ");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");
        Assert.assertEquals(MRZVariables.getMrzText(), "P<ISRSTIEBEL<<GUY<<<<<<<<<<<<<<<<<<<<<<<<<<<23369458<8ISR8609199M26120410<2649440<1<<<80");
        Assert.assertEquals(MRZVariables.getMrzType(), "TD3");
        Assert.assertEquals(MRZVariables.getDocumentType(), "passport");
        Assert.assertEquals(MRZVariables.getDocumentSubType(), "");
        Assert.assertEquals(MRZVariables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(MRZVariables.getLastName(), "STIEBEL");
        Assert.assertEquals(MRZVariables.getFirstName(), "GUY");
        Assert.assertEquals(MRZVariables.getPassportNumber(), "23369458");
        Assert.assertEquals(MRZVariables.getNationalityCode(), "ISR");
        Assert.assertEquals(MRZVariables.getDateOfBirth(), "19/09/86");
        Assert.assertEquals(MRZVariables.getGender(), "Male");
        Assert.assertEquals(MRZVariables.getDateOfExpiry(), "04/12/26");
        Assert.assertEquals(MRZVariables.getPersonalNumber(), "0-2649440-1");
        Assert.assertFalse(MRZVariables.isMightBeTruncated());
        Assert.assertNotNull(MRZVariables.getFaceImage());
        Assert.assertNotNull(MRZVariables.getInputImage());
        Assert.assertNotNull(MRZVariables.getCroppedImage());
        Assert.assertNotNull(MRZVariables.getCroppedFieldImage());
        Assert.assertTrue(MRZVariables.isDocumentInFrame());
        Assert.assertTrue(MRZVariables.isExpiryDateValid());
        Assert.assertFalse(MRZVariables.isFaceSizeValid());
        Assert.assertTrue(MRZVariables.isFacePositionValid());
        Assert.assertTrue(MRZVariables.isFaceRotationValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(description = "old mrz with face image roatation")
    @Description("Old mrz with face image roatation")
    public void t03_oldMrzWithFaceRotation() throws IOException, InterruptedException {
        injection.imageInjectionConfigure("./ocr/mrz/guy/guyMrzWithFaceRoatation.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 28);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getMrzText(), "P<ISRSTIEBEL<<GUY<<<<<<<<<<<<<<<<<<<<<<<<<<<23369458<8ISR8609199M26120410<2649440<1<<<80");
        Assert.assertEquals(variables.getMrzType(), "TD3");
        Assert.assertEquals(variables.getDocumentType(), "passport");
        
        Assert.assertEquals(variables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(variables.getLastNameEnglish(), "STIEBEL");
        Assert.assertEquals(variables.getFirstNameEnglish(), "GUY");
        Assert.assertEquals(variables.getPassportNumber(), 23369458);
        Assert.assertEquals(variables.getNationalityCode(), "ISR");
        Assert.assertEquals(variables.getDateOfBirth(), "19/09/86");
        Assert.assertEquals(variables.getGender(), "Male");
        Assert.assertEquals(variables.getDateOfExpiry(), "04/12/26");
        Assert.assertEquals(variables.getPersonalNumber(), "0-2649440-1");

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
        

        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertFalse(variables.isFaceRotationValid());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "MRZ");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "MRZ");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");
        Assert.assertEquals(MRZVariables.getMrzText(), "P<ISRSTIEBEL<<GUY<<<<<<<<<<<<<<<<<<<<<<<<<<<23369458<8ISR8609199M26120410<2649440<1<<<80");
        Assert.assertEquals(MRZVariables.getMrzType(), "TD3");
        Assert.assertEquals(MRZVariables.getDocumentType(), "passport");
        Assert.assertEquals(MRZVariables.getDocumentSubType(), "");
        Assert.assertEquals(MRZVariables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(MRZVariables.getLastName(), "STIEBEL");
        Assert.assertEquals(MRZVariables.getFirstName(), "GUY");
        Assert.assertEquals(MRZVariables.getPassportNumber(), "23369458");
        Assert.assertEquals(MRZVariables.getNationalityCode(), "ISR");
        Assert.assertEquals(MRZVariables.getDateOfBirth(), "19/09/86");
        Assert.assertEquals(MRZVariables.getGender(), "Male");
        Assert.assertEquals(MRZVariables.getDateOfExpiry(), "04/12/26");
        Assert.assertEquals(MRZVariables.getPersonalNumber(), "0-2649440-1");
        Assert.assertFalse(MRZVariables.isMightBeTruncated());
        Assert.assertNotNull(MRZVariables.getFaceImage());
        Assert.assertNotNull(MRZVariables.getInputImage());
        Assert.assertNotNull(MRZVariables.getCroppedImage());
        Assert.assertNotNull(MRZVariables.getCroppedFieldImage());
        Assert.assertTrue(MRZVariables.isDocumentInFrame());
        Assert.assertTrue(MRZVariables.isExpiryDateValid());
        Assert.assertTrue(MRZVariables.isFaceSizeValid());
        Assert.assertTrue(MRZVariables.isFacePositionValid());
        Assert.assertFalse(MRZVariables.isFaceRotationValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }
}
