package api.ocr.singleFrame;

import api.Variables;
import api.ApiResponse;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.ILDLVariables;
import utilities.MongoDB.Variables.Ocr.OcrHandlers;
import utilities.MongoDB.Variables.StartSessionVariables;

import java.io.File;
import utilities.TestUtils;
public class ILDLSanitySingleFrame {

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
    public void t01_frontSideNewILDL() {
        apiResponse.singleFrameRequest("library_name", "IL-DL", "case_id", apiResponse.randomString(), "frame", new File("./ocr/dl/newDLFront.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");

        //Front side data
        Assert.assertEquals(variables.getActionType2(), "complete");
        Assert.assertEquals(variables.getOrder2(), 1);
        Assert.assertEquals(variables.getType2(), "any");
        Assert.assertEquals(variables.getLastNameHebrew(), "עבוש");
        Assert.assertEquals(variables.getLastNameEnglish(), "ABOSH");
        Assert.assertEquals(variables.getFirstNameHebrew(), "מנחם מאור");
        Assert.assertEquals(variables.getFirstNameEnglish(), "MENACHEM MAOR");
        Assert.assertEquals(variables.getDateOfBirth(), "26.03.1992");
        Assert.assertEquals(variables.getDateOfIssue(), "31.03.2016");
        Assert.assertEquals(variables.getDateOfExpiry(), "26.03.2026");
        Assert.assertEquals(variables.getIdNumber(), 203114798);
        Assert.assertEquals(variables.getLicenseNumber(), 9182620);
        Assert.assertEquals(variables.getAddress(), "ביאליק 40א בית שמש");
        Assert.assertEquals(variables.getLicenseCategories(), "A1 B");
        Assert.assertEquals(variables.getCardType2(), "new_front");
        Assert.assertNotNull(variables.getFaceImage2());
        Assert.assertNotNull(variables.getInputImage2());
        Assert.assertNotNull(variables.getCroppedImage2());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertFalse(variables.isFaceRotationValid());
        Assert.assertEquals(variables.getStageStatus3(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        MongoHandler mongoHandler = new MongoHandler();
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "single");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "");
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(CommonVariables.getStatus(), "success");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Process ended successfully");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "single");
        Assert.assertEquals(OcrHandlers.getActionType(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "any");
        Assert.assertEquals(ILDLVariables.getLastNameHebrew(), "עבוש");
        Assert.assertEquals(ILDLVariables.getLastNameEnglish(), "ABOSH");
        Assert.assertEquals(ILDLVariables.getFirstNameHebrew(), "מנחם מאור");
        Assert.assertEquals(ILDLVariables.getFirstNameEnglish(), "MENACHEM MAOR");
        Assert.assertEquals(ILDLVariables.getDateOfBirth(), "26.03.1992");
        Assert.assertEquals(ILDLVariables.getDateOfIssue(), "31.03.2016");
        Assert.assertEquals(ILDLVariables.getDateOfExpiry(), "26.03.2026");
        Assert.assertEquals(ILDLVariables.getLicenseNumber(), "9182620");
        Assert.assertEquals(ILDLVariables.getIdNumber(), "203114798");
        Assert.assertEquals(ILDLVariables.getAddress(), "ביאליק 40א בית שמש");
        Assert.assertEquals(ILDLVariables.getLicenseCategories(), "A1 B");
        Assert.assertEquals(ILDLVariables.getCardType(), "new_front");
        Assert.assertNotNull(ILDLVariables.getFaceImage());
        Assert.assertNotNull(ILDLVariables.getInputImage());
        Assert.assertNotNull(ILDLVariables.getCroppedImage());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid());
        Assert.assertTrue(ILDLVariables.isTemplateMatched());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame());
        Assert.assertTrue(ILDLVariables.isExpiryDateValid());
        Assert.assertTrue(ILDLVariables.isFaceSizeValid());
        Assert.assertTrue(ILDLVariables.isFacePositionValid());
        Assert.assertFalse(ILDLVariables.isFaceRotationValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void t02_frontSideOldILDL() {
        apiResponse.singleFrameRequest("library_name", "IL-DL", "case_id", apiResponse.randomString(), "frame", new File("./ocr/dl/oldDLFront.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");

        Assert.assertEquals(variables.getActionType2(), "complete");
        Assert.assertEquals(variables.getOrder2(), 1);
        Assert.assertEquals(variables.getType2(), "any");
        Assert.assertEquals(variables.getLastNameHebrew(), "גרוס פישמן");
        Assert.assertEquals(variables.getLastNameEnglish(), "FISHMAMGROSS");
        Assert.assertEquals(variables.getFirstNameHebrew(), "מאיה");
        Assert.assertEquals(variables.getFirstNameEnglish(), "MAYA");
        Assert.assertEquals(variables.getDateOfBirth(), "06.01.1978");
        Assert.assertEquals(variables.getDateOfIssue(), "14.12.2006");
        Assert.assertEquals(variables.getDateOfExpiry(), "06.01.2017");
        Assert.assertEquals(variables.getIdNumber(), 34471045);
        Assert.assertEquals(variables.getLicenseNumber(), 6905739);
        Assert.assertEquals(variables.getAddress(), "אלוף דוד 158 רמת גן");
        Assert.assertEquals(variables.getLicenseCategories(), "B");
        Assert.assertEquals(variables.getCardType2(), "old_front");
        Assert.assertNotNull(variables.getFaceImage2());
        Assert.assertNotNull(variables.getInputImage2());
        Assert.assertNotNull(variables.getCroppedImage2());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertFalse(variables.isExpiryDateValid());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
        Assert.assertEquals(variables.getStageStatus3(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        MongoHandler mongoHandler = new MongoHandler();
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "single");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "");
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(CommonVariables.getStatus(), "success");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Process ended successfully");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "single");
        Assert.assertEquals(OcrHandlers.getActionType(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "any");
        Assert.assertEquals(ILDLVariables.getLastNameHebrew(), "גרוס פישמן");
        Assert.assertEquals(ILDLVariables.getLastNameEnglish(), "FISHMAMGROSS");
        Assert.assertEquals(ILDLVariables.getFirstNameHebrew(), "מאיה");
        Assert.assertEquals(ILDLVariables.getFirstNameEnglish(), "MAYA");
        Assert.assertEquals(ILDLVariables.getDateOfBirth(), "06.01.1978");
        Assert.assertEquals(ILDLVariables.getDateOfIssue(), "14.12.2006");
        Assert.assertEquals(ILDLVariables.getDateOfExpiry(), "06.01.2017");
        Assert.assertEquals(ILDLVariables.getLicenseNumber(), "6905739");
        Assert.assertEquals(ILDLVariables.getIdNumber(), "034471045");
        Assert.assertEquals(ILDLVariables.getAddress(), "אלוף דוד 158 רמת גן");
        Assert.assertEquals(ILDLVariables.getLicenseCategories(), "B");
        Assert.assertEquals(ILDLVariables.getCardType(), "old_front");
        Assert.assertNotNull(ILDLVariables.getFaceImage());
        Assert.assertNotNull(ILDLVariables.getInputImage());
        Assert.assertNotNull(ILDLVariables.getCroppedImage());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid());
        Assert.assertTrue(ILDLVariables.isTemplateMatched());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame());
        Assert.assertFalse(ILDLVariables.isExpiryDateValid());
        Assert.assertTrue(ILDLVariables.isFaceSizeValid());
        Assert.assertTrue(ILDLVariables.isFacePositionValid());
        Assert.assertTrue(ILDLVariables.isFaceRotationValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void t03_backSideNewILDL() {
        apiResponse.singleFrameRequest("library_name", "IL-DL", "case_id", apiResponse.randomString(), "frame", new File("./ocr/dl/newDLBack.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");

        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder1(), 1);
        Assert.assertEquals(variables.getType1(), "any");
        Assert.assertEquals(variables.getIdNumber(), 203114798);
        Assert.assertEquals(variables.getbYear(), 2009);
        Assert.assertEquals(variables.getCardType1(), "new_back");
        Assert.assertNotNull(variables.getInputImage1());
        Assert.assertNotNull(variables.getCroppedImage1());
        Assert.assertTrue(variables.isIdChecksumValid2());
        Assert.assertTrue(variables.isTemplateMatched2());
        Assert.assertTrue(variables.isDocumentInFrame2());
        Assert.assertEquals(variables.getStageStatus2(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        MongoHandler mongoHandler = new MongoHandler();
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "single");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "");
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(CommonVariables.getStatus(), "success");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Process ended successfully");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "single");
        Assert.assertEquals(OcrHandlers.getActionType2(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder2(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage2(), "any");
        Assert.assertEquals(ILDLVariables.getIdNumber2(), "203114798");
        Assert.assertEquals(ILDLVariables.getbYear(), "2009");
        Assert.assertEquals(ILDLVariables.getCardType2(), "new_back");
        Assert.assertNotNull(ILDLVariables.getInputImage2());
        Assert.assertNotNull(ILDLVariables.getCroppedImage2());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid2());
        Assert.assertTrue(ILDLVariables.isTemplateMatched2());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame2());
        Assert.assertEquals(OcrHandlers.getStageStatus2(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void t04_backSideOldILDL() {
        apiResponse.singleFrameRequest("library_name", "IL-DL", "case_id", apiResponse.randomString(), "frame", new File("./ocr/dl/oldDLBack.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");

        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder1(), 1);
        Assert.assertEquals(variables.getType1(), "any");
        Assert.assertEquals(variables.getIdNumber(), 34471045);
        Assert.assertEquals(variables.getbYear(), 1995);
        Assert.assertEquals(variables.getCardType1(), "old_back");
        Assert.assertNotNull(variables.getInputImage1());
        Assert.assertNotNull(variables.getCroppedImage1());
        Assert.assertTrue(variables.isIdChecksumValid2());
        Assert.assertTrue(variables.isTemplateMatched2());
        Assert.assertTrue(variables.isDocumentInFrame2());
        Assert.assertEquals(variables.getStageStatus2(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        MongoHandler mongoHandler = new MongoHandler();
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "single");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "");
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(CommonVariables.getStatus(), "success");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Process ended successfully");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "single");
        Assert.assertEquals(OcrHandlers.getActionType2(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder2(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage2(), "any");
        Assert.assertEquals(ILDLVariables.getIdNumber2(), "034471045");
        Assert.assertEquals(ILDLVariables.getbYear(), "1995");
        Assert.assertEquals(ILDLVariables.getCardType2(), "old_back");
        Assert.assertNotNull(ILDLVariables.getInputImage2());
        Assert.assertNotNull(ILDLVariables.getCroppedImage2());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid2());
        Assert.assertTrue(ILDLVariables.isTemplateMatched2());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame2());
        Assert.assertEquals(OcrHandlers.getStageStatus2(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }
}
