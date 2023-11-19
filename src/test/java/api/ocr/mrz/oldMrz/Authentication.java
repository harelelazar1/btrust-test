package api.ocr.mrz.oldMrz;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.MRZVariables;
import utilities.MongoDB.Variables.Ocr.OcrHandlers;
import utilities.MongoDB.Variables.StartSessionVariables;

import java.io.File;
import java.io.IOException;
import utilities.TestUtils;
public class Authentication {

    Variables variables;
    MongoHandler mongoHandler;
    ApiResponse apiResponse;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "MRZ", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
    }

    @Test(description = "old mrz without face image",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Old mrz without face image")
    public void t01_oldMrzWithoutFace() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/guy/guyMrzWithoutFace.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");

        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder(), 1);
        Assert.assertEquals(variables.getTypeUnderStage(), "front");
        Assert.assertEquals(variables.getMrzText(), "P<ISRSTIEBEL<<GUY<<<<<<<<<<<<<<<<<<<<<<<<<<<23369458<8ISR8609199M26120410<2649440<1<<<80");
        Assert.assertEquals(variables.getMrzType(), "TD3");
        Assert.assertEquals(variables.getDocumentType(), "passport");
        Assert.assertEquals(variables.getDocumentSubType(), "");
        Assert.assertEquals(variables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(variables.getLastNameEnglish(), "STIEBEL");
        Assert.assertEquals(variables.getFirstNameEnglish(), "GUY");
        Assert.assertEquals(variables.getPassportNumber(), 23369458);
        Assert.assertEquals(variables.getNationalityCode(), "ISR");
        Assert.assertEquals(variables.getDateOfBirth(), "19/09/86");
        Assert.assertEquals(variables.getGender(), "Male");
        Assert.assertEquals(variables.getDateOfExpiry(), "04/12/26");
        Assert.assertEquals(variables.getPersonalNumber(), "0-2649440-1");
        Assert.assertFalse(variables.isMightBeTruncated());
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertNotNull(variables.getCroppedImage());
        Assert.assertNotNull(variables.getCroppedFieldImage());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertFalse(variables.isFaceSizeValid());
        Assert.assertFalse(variables.isFacePositionValid());
        Assert.assertFalse(variables.isFaceRotationValid());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "MRZ");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), TestUtils.getDefaultMathilda());
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

    @Test(description = "old mrz without face image",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Old mrz without face image")
    public void t02_oldMrzWithFaceSizeInvalid() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/guy/guyMrzWithFaceSizeInvalid.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");

        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder(), 1);
        Assert.assertEquals(variables.getTypeUnderStage(), "front");
        Assert.assertEquals(variables.getMrzText(), "P<ISRSTIEBEL<<GUY<<<<<<<<<<<<<<<<<<<<<<<<<<<23369458<8ISR8609199M26120410<2649440<1<<<80");
        Assert.assertEquals(variables.getMrzType(), "TD3");
        Assert.assertEquals(variables.getDocumentType(), "passport");
        Assert.assertEquals(variables.getDocumentSubType(), "");
        Assert.assertEquals(variables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(variables.getLastNameEnglish(), "STIEBEL");
        Assert.assertEquals(variables.getFirstNameEnglish(), "GUY");
        Assert.assertEquals(variables.getPassportNumber(), 23369458);
        Assert.assertEquals(variables.getNationalityCode(), "ISR");
        Assert.assertEquals(variables.getDateOfBirth(), "19/09/86");
        Assert.assertEquals(variables.getGender(), "Male");
        Assert.assertEquals(variables.getDateOfExpiry(), "04/12/26");
        Assert.assertEquals(variables.getPersonalNumber(), "0-2649440-1");
        Assert.assertFalse(variables.isMightBeTruncated());
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertNotNull(variables.getCroppedImage());
        Assert.assertNotNull(variables.getCroppedFieldImage());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertFalse(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "MRZ");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), TestUtils.getDefaultMathilda());
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

    @Test(description = "old mrz with face image roatation",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Old mrz with face image roatation")
    public void t03_oldMrzWithFaceRotation() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/guy/guyMrzWithFaceRoatation.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");

        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder(), 1);
        Assert.assertEquals(variables.getTypeUnderStage(), "front");
        Assert.assertEquals(variables.getMrzText(), "P<ISRSTIEBEL<<GUY<<<<<<<<<<<<<<<<<<<<<<<<<<<23369458<8ISR8609199M26120410<2649440<1<<<80");
        Assert.assertEquals(variables.getMrzType(), "TD3");
        Assert.assertEquals(variables.getDocumentType(), "passport");
        Assert.assertEquals(variables.getDocumentSubType(), "");
        Assert.assertEquals(variables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(variables.getLastNameEnglish(), "STIEBEL");
        Assert.assertEquals(variables.getFirstNameEnglish(), "GUY");
        Assert.assertEquals(variables.getPassportNumber(), 23369458);
        Assert.assertEquals(variables.getNationalityCode(), "ISR");
        Assert.assertEquals(variables.getDateOfBirth(), "19/09/86");
        Assert.assertEquals(variables.getGender(), "Male");
        Assert.assertEquals(variables.getDateOfExpiry(), "04/12/26");
        Assert.assertEquals(variables.getPersonalNumber(), "0-2649440-1");
        Assert.assertFalse(variables.isMightBeTruncated());
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertNotNull(variables.getCroppedImage());
        Assert.assertNotNull(variables.getCroppedFieldImage());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertFalse(variables.isFaceRotationValid());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "MRZ");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), TestUtils.getDefaultMathilda());
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

}
