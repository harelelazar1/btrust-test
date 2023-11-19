package api.ocr.singleFrame;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.*;
import utilities.MongoDB.Variables.Ocr.MRZVariables;
import utilities.MongoDB.Variables.Ocr.OcrHandlers;

import java.io.File;
import utilities.TestUtils;
public class MRZSanitySingleFrame {

    ApiResponse apiResponse;
    Variables variables;
    MongoHandler mongoHandler;

    @BeforeMethod
    public void resetVariables() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void t01_oldMRZ() {
        apiResponse.singleFrameRequest("library_name", "MRZ", "case_id", apiResponse.randomString(), "frame", new File("./ocr/mrz/guy/guyMrz.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");

        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder(), 1);
        Assert.assertEquals(variables.getTypeUnderStage(), "any");
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
        Assert.assertTrue(variables.isFaceRotationValid());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "");
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
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "single");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "");
        Assert.assertEquals(CommonVariables.getServiceType(), "MRZ");
        Assert.assertEquals(CommonVariables.getStatus(), "success");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Process ended successfully");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "single");
        Assert.assertEquals(OcrHandlers.getActionType(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "any");
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
        Assert.assertTrue(MRZVariables.isFaceRotationValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void t02_newMRZ() {
        apiResponse.singleFrameRequest("library_name", "MRZ", "case_id", apiResponse.randomString(), "frame", new File("./ocr/mrz/nadav/nadavMrz.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");

        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder(), 1);
        Assert.assertEquals(variables.getTypeUnderStage(), "any");
        Assert.assertEquals(variables.getMrzText(), "P<ISRSHLOMO<<NADAV<<<<<<<<<<<<<<<<<<<<<<<<<<31027245<4ISR9609082M26051883<1539659<8<<<52");
        Assert.assertEquals(variables.getMrzType(), "TD3");
        Assert.assertEquals(variables.getDocumentType(), "passport");
        Assert.assertEquals(variables.getDocumentSubType(), "");
        Assert.assertEquals(variables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(variables.getLastNameEnglish(), "SHLOMO");
        Assert.assertEquals(variables.getFirstNameEnglish(), "NADAV");
        Assert.assertEquals(variables.getPassportNumber(), 31027245);
        Assert.assertEquals(variables.getNationalityCode(), "ISR");
        Assert.assertEquals(variables.getDateOfBirth(), "08/09/96");
        Assert.assertEquals(variables.getGender(), "Male");
        Assert.assertEquals(variables.getDateOfExpiry(), "18/05/26");
        Assert.assertEquals(variables.getPersonalNumber(), "3-1539659-8");
        Assert.assertFalse(variables.isMightBeTruncated());
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertNotNull(variables.getCroppedImage());
        Assert.assertNotNull(variables.getCroppedFieldImage());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "");
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
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "single");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "");
        Assert.assertEquals(CommonVariables.getServiceType(), "MRZ");
        Assert.assertEquals(CommonVariables.getStatus(), "success");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Process ended successfully");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "single");
        Assert.assertEquals(OcrHandlers.getActionType(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "any");
        Assert.assertEquals(MRZVariables.getMrzText(), "P<ISRSHLOMO<<NADAV<<<<<<<<<<<<<<<<<<<<<<<<<<31027245<4ISR9609082M26051883<1539659<8<<<52");
        Assert.assertEquals(MRZVariables.getMrzType(), "TD3");
        Assert.assertEquals(MRZVariables.getDocumentType(), "passport");
        Assert.assertEquals(MRZVariables.getDocumentSubType(), "");
        Assert.assertEquals(MRZVariables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(MRZVariables.getLastName(), "SHLOMO");
        Assert.assertEquals(MRZVariables.getFirstName(), "NADAV");
        Assert.assertEquals(MRZVariables.getPassportNumber(), "31027245");
        Assert.assertEquals(MRZVariables.getNationalityCode(), "ISR");
        Assert.assertEquals(MRZVariables.getDateOfBirth(), "08/09/96");
        Assert.assertEquals(MRZVariables.getGender(), "Male");
        Assert.assertEquals(MRZVariables.getDateOfExpiry(), "18/05/26");
        Assert.assertEquals(MRZVariables.getPersonalNumber(), "3-1539659-8");
        Assert.assertFalse(MRZVariables.isMightBeTruncated());
        Assert.assertNotNull(MRZVariables.getFaceImage());
        Assert.assertNotNull(MRZVariables.getInputImage());
        Assert.assertNotNull(MRZVariables.getCroppedImage());
        Assert.assertNotNull(MRZVariables.getCroppedFieldImage());
        Assert.assertTrue(MRZVariables.isDocumentInFrame());
        Assert.assertTrue(MRZVariables.isExpiryDateValid());
        Assert.assertTrue(MRZVariables.isFaceSizeValid());
        Assert.assertTrue(MRZVariables.isFacePositionValid());
        Assert.assertTrue(MRZVariables.isFaceRotationValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }
}
