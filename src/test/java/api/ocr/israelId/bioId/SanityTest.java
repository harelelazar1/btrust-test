package api.ocr.israelId.bioId;

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
import utilities.MongoDB.Variables.Ocr.ILIDVariables;
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

    @Test(description = "bio id api sanity test",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Api bio id e2e sanity test")
    public void t01_e2eBioIdFlow() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 2);
        Assert.assertEquals(variables.getTypeUnderStage(), "back");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());

        while (variables.getMessageId() != 100) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 100);
        Assert.assertEquals(variables.getMessageText(), "Wrong side");
        Assert.assertTrue(variables.isSuccess());

        while (variables.getMessageId() != 103) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/back/NitzanBioBack1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/back/NitzanBioBack1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");

        //Front side data
        Assert.assertEquals(variables.getActionType2(), "stage");
        Assert.assertEquals(variables.getOrder2(), 1);
        Assert.assertEquals(variables.getType2(), "front");
        Assert.assertEquals(variables.getLastNameHebrew(), "שוקר");
        Assert.assertEquals(variables.getFirstNameHebrew(), "ניצן");
        Assert.assertEquals(variables.getDateOfBirth(), "21.08.1988");
        Assert.assertEquals(variables.getDateOfIssue(), "23.5.2019");
        Assert.assertEquals(variables.getDateOfExpiry(), "20.05.2029");
        Assert.assertEquals(variables.getIdNumber(), 200761625);
        Assert.assertEquals(variables.getCardType2(), "bio_front");
        Assert.assertNotNull(variables.getFaceImage2());
        Assert.assertNotNull(variables.getInputImage2());
        Assert.assertNotNull(variables.getCroppedImage2());
        Assert.assertTrue(variables.isImageIsColorful());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isPeriodBetweenIssueAndExpiry());
        Assert.assertTrue(variables.isChipAuth());
        Assert.assertFalse(variables.isFaceImageReplacedWithPassportImage());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertTrue(variables.isIssueDateValid());
        Assert.assertEquals(variables.getStageStatus3(), "success");

        //Back side data
        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder1(), 2);
        Assert.assertEquals(variables.getType1(), "back");
        Assert.assertEquals(variables.getIdNumber(), 200761625);
        Assert.assertEquals(variables.getPlaceOfBirth(), "ישראל");
        Assert.assertEquals(variables.getGender(), "זכר");
        Assert.assertEquals(variables.getCardType1(), "bio_back");
        Assert.assertNotNull(variables.getInputImage1());
        Assert.assertNotNull(variables.getCroppedImage1());
        Assert.assertTrue(variables.isImageIsColorful());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isIdNumberMatchesFront());
        Assert.assertTrue(variables.isDocumentInFrame());
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
        Assert.assertEquals(StartSessionVariables.getServiceType(), "IL-ID");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), TestUtils.getDefaultMathilda());
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
}
