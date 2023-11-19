package api.ngocr.israelId.greenId;

import api.ApiResponse;
import api.Variables;
import btrust.onboardingProcess.api.variables.PreProcess;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.ILIDVariables;
import utilities.TestUtils;

import java.io.File;
import java.io.IOException;

public class Errorhandling {

    Variables variables;
    ApiResponse apiResponse;
    MongoHandler mongoHandler;

    String sessionId;
    String X_Request_Id;
    String X_Session_Id;
    String clientTranslationFileValue;
    boolean devMode;
    PreProcess preProcess;

    @BeforeMethod
    public void startSessionAndInitToken() {
        //Init token
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
        preProcess = new PreProcess();
        sessionId = apiResponse.createRandomUuid();
        X_Session_Id = apiResponse.createRandomUuid();
        X_Request_Id = apiResponse.createRandomUuid();
        clientTranslationFileValue = "scanovate_default_en.json";
        devMode = false;
        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);
        //       apiResponse.verifyInitVariables(variables);
    }


    @Test(description = "green id not-if-frame error message api test", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("green id not-if-frame error message api test")
    public void t01_greenId_notInFrame() throws IOException {
        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/moran/cardNotFound/cardNotFound.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);


        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 101);
        Assert.assertEquals(variables.getMessageText(), "Card not detected");
    }

    @Test(description = "green Id api - scan card with date of birth hidden", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("green Id api - scan card with date of birth hidden")
    public void t02_greenId_dateOfBirthHidden() throws IOException {
        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/errorhandling/greenIdWithDateOfBirthHidden.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);


        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");


        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/blueID/errorhandling/blueIDWithDateOfBirthHidden.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }


        Assert.assertNull(variables.getDateOfBirth());
    }


    @Test(description = "green Id api - scan card with date of issue hidden", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("green Id api - scan card with date of issue hidden")
    public void t03_greenId_dateOfIssueHidden() throws IOException {
        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/errorhandling/greenIdWithDateOfIssueHidden.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);


        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/errorhandling/greenIdWithDateOfIssueHidden.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }


        Assert.assertNull(variables.getDateOfIssue());
    }

    @Test(description = "green Id api - scan card with id number hidden")
    @Description("green Id api - scan card with id number hidden")
    public void t04_greenId_idNumberHidden() throws IOException {
        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/errorhandling/greenIdWithIdNumberHidden.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);


        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");


        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/errorhandling/greenIdWithIdNumberHidden.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }


        Assert.assertEquals(variables.getStatus(), "timeout");
    }


}
