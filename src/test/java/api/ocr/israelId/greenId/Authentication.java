package api.ocr.israelId.greenId;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.Ocr.ILIDVariables;

import java.io.File;
import java.io.IOException;
import utilities.TestUtils;
public class Authentication {

    ApiResponse apiResponse;
    Variables variables;
    MongoHandler mongoHandler;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
    }

    @Test(description = "green Id api - scan card without face",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("green Id api - scan card without face")
    public void t01_greenId_pictureWithoutFace() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/greenID_hiddenFace.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/greenID_hiddenFace.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertFalse(variables.isFaceSizeValid());
        Assert.assertFalse(variables.isFaceRotationValid());
        Assert.assertFalse(variables.isFacePositionValid());

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertFalse(ILIDVariables.isFaceSizeValid());
        Assert.assertFalse(ILIDVariables.isFaceRotationValid());
        Assert.assertFalse(ILIDVariables.isFacePositionValid());
    }

    @Test(description = "green id stamp detected",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Green id stamp detected auth test")
    public void t02_greenIdStampDetected() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/auth/greenIdStampCovered.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/auth/greenIdStampCovered.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertFalse(variables.isStampDetectedAuth());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertFalse(ILIDVariables.isStampDetected());
    }
}
