package api.ocr.dl.oldDl;

import api.Variables;
import api.ApiResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.ILDLVariables;
import utilities.MongoDB.Variables.Ocr.OcrHandlers;
import utilities.MongoDB.Variables.StartSessionVariables;

import java.io.File;
import java.io.IOException;
import utilities.TestUtils;
public class SanityTest {

    Variables variables;
    MongoHandler mongoHandler;
    ApiResponse apiResponse;


    @BeforeClass
    public void resetVariables() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
    }

    @Test(description = "old dl api sanity test")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Api israel old driving license e2e sanity test")
    public void t01_e2eOldDlFlow() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLFront.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (!variables.getActionType().equalsIgnoreCase("message") && variables.getMessageId() == 103) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLFront.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLFront.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 2);
        Assert.assertEquals(variables.getTypeUnderStage(), "back");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());

        while (variables.getMessageId() != 100) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLFront.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 100);
        Assert.assertEquals(variables.getMessageText(), "Wrong side");
        Assert.assertTrue(variables.isSuccess());

        while (variables.getMessageId() != 103) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLBack.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLBack.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");

        //Front side data
        Assert.assertEquals(variables.getActionType2(), "stage");
        Assert.assertEquals(variables.getOrder2(), 1);
        Assert.assertEquals(variables.getType2(), "front");
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

        //Back side data
        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder1(), 2);
        Assert.assertEquals(variables.getType1(), "back");
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
        Assert.assertEquals(StartSessionVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), TestUtils.getDefaultMathilda());
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");

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

        Assert.assertEquals(OcrHandlers.getActionType2(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder2(), 2);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage2(), "back");

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