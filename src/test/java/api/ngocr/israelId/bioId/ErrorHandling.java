package api.ngocr.israelId.bioId;

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

import static btrust.btrustOne.admin.BaseAdminUserTest.randomString;

public class ErrorHandling {


    Variables variables;
    ApiResponse apiResponse;
    MongoHandler mongoHandler;

    String sessionId;
    String X_Request_Id;
    String X_Session_Id;
    String clientTranslationFileValue;
    boolean devMode ;
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



    @Test(description = "bio id not-if-frame error message api test",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("bio id not-if-frame error message api test")
    public void t01_bioId_noCardDetectedMessage() throws IOException {
        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/moran/cardNotFound/cardNotFound.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);


        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 101);
        Assert.assertEquals(variables.getMessageText(), "Card not detected");
    }



    @Test(description = "wrong side error message in bio Id api",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("bio Id api - wrong side error message")
    public void t02_bioId_wrongSideMessage() throws IOException {
        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/back/NitzanBioBack1.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);

        while (variables.getMessageId() != 100) {

            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/back/NitzanBioBack1.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }
        Assert.assertEquals(variables.getMessageId(), 100);
        Assert.assertEquals(variables.getMessageText(), "Wrong side");
    }


    @Test(description = "bio Id api - scan card with date of birth hidden")
    @Description("bio Id api - scan card with date of birth hidden")
    public void t03_bioId_dateOfBirthHidden() throws IOException {
        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront - dateOfBirthHidden.jpg"),
                "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertTrue(variables.getMessageId()==101 ||variables.getMessageId()==103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");


        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront - dateOfBirthHidden.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/back/NitzanBioBack1.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }


//        Assert.assertEquals(variables.getMandatoryFieldsMessage(), "Could not find mandatory fields: ['date_of_birth']");
        Assert.assertNull(variables.getDateOfBirth());
        Assert.assertNull(ILIDVariables.getDateOfBirth());
    }

    @Test(description = "bio Id api - scan card with date of issue hidden",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("bio Id api - scan card with date of issue hidden")
    public void t04_bioId_dateOfIssueHidden() throws IOException {
        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront - dateOfIssueHidden.jpg"),
                "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertTrue(variables.getMessageId()==101 ||variables.getMessageId()==103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront - dateOfIssueHidden.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/back/NitzanBioBack1.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        Assert.assertNull(variables.getDateOfIssue());
    }

    @Test(description = "bio Id api - scan card with date of expiry hidden",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("bio Id api - scan card with date of expiry hidden")
    public void t05_bioId_dateOfExpiryHidden() throws IOException {
        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront - dateOfExpiredHidden.jpg"),
                "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);


        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertTrue(variables.getMessageId()==101 ||variables.getMessageId()==103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront - dateOfExpiredHidden.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/back/NitzanBioBack1.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        Assert.assertNull(variables.getDateOfExpiry());
    }

  //  @Test(description = "bio Id api - scan card with id number hidden")
    @Description("bio Id api - scan card with id number hidden")
    public void t06_bioId_idNumberHidden() throws IOException {
        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront - idNumberHidden.jpg"),
                "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);



        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertTrue(variables.getMessageId()==101 ||variables.getMessageId()==103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");


        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront - idNumberHidden.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/back/NitzanBioBack1.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        Assert.assertEquals(variables.getMandatoryFieldsMessage(), "timeout");



//        Assert.assertEquals(variables.getErrorCode(), 1112);
//        Assert.assertEquals(variables.getErrorName(), "ServerError");
//        Assert.assertNotNull(variables.getErrorMessage());
    }
}
