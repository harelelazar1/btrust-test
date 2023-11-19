package api.ngocr.israelId.greenId;

import api.ApiResponse;
import api.Variables;
import btrust.onboardingProcess.api.variables.PreProcess;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.ILIDVariables;
import utilities.MongoDB.Variables.Ocr.OcrHandlers;
import utilities.MongoDB.Variables.StartSessionVariables;
import utilities.TestUtils;

import java.io.File;
import java.io.IOException;

import static btrust.btrustOne.admin.BaseAdminUserTest.randomString;

public class SanityTest {

    Variables variables;
    MongoHandler mongoHandler;
    ApiResponse apiResponse;
    String sessionId;
    String X_Request_Id;
    String X_Session_Id;
    String clientTranslationFileValue;
    boolean devMode ;
    PreProcess preProcess;


    @BeforeMethod
    public void resetVariables() {
        preProcess = new PreProcess();
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
        sessionId = "new" + randomString();
        X_Session_Id = "new" + randomString();
        X_Request_Id = "new" + randomString();
        clientTranslationFileValue = "scanovate_default_en.json";
        devMode = false;
    }

    @Test(description = "green id api sanity test",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Api green id e2e sanity test")
    public void t01_e2eGreenIdFlow() throws IOException {
        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/auth/face_stamp_true.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);


        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertTrue(variables.getMessageId()==101 ||variables.getMessageId()==103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/auth/face_stamp_true.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);

        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");

        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder(), 1);
        Assert.assertEquals(variables.getTypeUnderStage(), "front");
        Assert.assertEquals(variables.getDateOfBirth(), "25.11.1967");
        Assert.assertEquals(variables.getDateOfIssue(), "21.05.2009");
        Assert.assertEquals(variables.getFirstNameHebrew(), "אדם ברדלי");
        Assert.assertEquals(variables.getGender(), "זכר");
        Assert.assertEquals(variables.getIdNumber(), 332358530);
        Assert.assertEquals(variables.getLastNameHebrew(), "לביק");
        Assert.assertEquals(variables.getPlaceOfBirth(), "ארצות הברית");
        Assert.assertEquals(variables.getStageStatus(), "success");
        Assert.assertEquals(variables.getCardType(), "green");
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertNotNull(variables.getCroppedImage());
        Assert.assertTrue(variables.isImageIsColorful());
        Assert.assertTrue(variables.isStampDetected());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isIssueDateValid());

        Assert.assertNotNull(variables.getCaseId());
    }


    @Test(description = "green old id api sanity test",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Api green old id e2e sanity test")
    public void t02_e2eGreenOldIdFlow() throws IOException {
        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/greenId_old/israel_id_green_old.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);


        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertTrue(variables.getMessageId()==101 ||variables.getMessageId()==103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/greenId_old/israel_id_green_old.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);

        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");

        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder(), 1);
        Assert.assertEquals(variables.getTypeUnderStage(), "front");
        Assert.assertEquals(variables.getDateOfBirth(), "12.02.1978");
        Assert.assertEquals(variables.getDateOfIssue(), "04.08.97");
        Assert.assertEquals(variables.getFirstNameHebrew(), "מנאר");
        Assert.assertEquals(variables.getGender(), "נקבה");
        Assert.assertEquals(variables.getIdNumber(), 32846545);
        Assert.assertEquals(variables.getLastNameHebrew(), "אבו זקיקה");
        Assert.assertEquals(variables.getPlaceOfBirth(), "ישראל");
        Assert.assertEquals(variables.getStageStatus(), "success");
        Assert.assertEquals(variables.getCardType(), "green_old");
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertNotNull(variables.getCroppedImage());
        Assert.assertTrue(variables.isImageIsColorful());
        Assert.assertTrue(variables.isStampDetected());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isIssueDateValid());

        Assert.assertNotNull(variables.getCaseId());
    }




}
