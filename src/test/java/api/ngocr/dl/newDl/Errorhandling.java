package api.ngocr.dl.newDl;

import api.ApiResponse;
import api.Variables;
import btrust.onboardingProcess.api.variables.PreProcess;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.ILDLVariables;
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
        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);
        //       apiResponse.verifyInitVariables(variables);
    }


    @Test(description = "new dl no card detected message api test")
    @Description("new dl no card detected message")
    public void t01_newDl_noCardDetectedMessage() throws IOException {
        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/moran/cardNotFound/cardNotFound.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);


        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 101);
        Assert.assertEquals(variables.getMessageText(), "Card not detected");
    }


    @Test(description = "new dl wrong side message api test")
    @Description("new dl wrong side message")
    public void t02_newDl_wrongSideMessage() throws IOException {
        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/newDLBack.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);

        while (variables.getMessageId() != 100) {

            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/newDLBack.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }
        Assert.assertEquals(variables.getMessageId(), 100);
        Assert.assertEquals(variables.getMessageText(), "Wrong side");
    }


    @Test(description = "new dl api - scan card with date of expiry hidden")
    @Description("new dl api - scan card with date of expiry hidden")
    public void t03_newDl_dateOfExpiryHidden() throws IOException {
        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/errorhandling/newDl/newDLFront - dateOfExpiryHidden.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertTrue(variables.getMessageId() == 101 || variables.getMessageId() == 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");


        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/errorhandling/newDl/newDLFront - dateOfExpiryHidden.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/newDLBack.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }


//        Assert.assertEquals(variables.getMandatoryFieldsMessage(), "Could not find mandatory fields: ['date_of_birth']");
        Assert.assertNull(variables.getDateOfBirth());
        Assert.assertNull(ILIDVariables.getDateOfBirth());
    }

    @Test(description = "new dl api - scan card with date of issue hidden")
    @Description("new dl api - scan card with date of issue hidden")
    public void t04_newDl_dateOfIssueHidden() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/errorhandling/newDl/newDLFront - dateOfIssueHidden.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/errorhandling/newDl/newDLFront - dateOfIssueHidden.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNull(variables.getDateOfIssue());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertNull(ILDLVariables.getDateOfIssue());
    }

    @Test(description = "new dl api - scan card with id number hidden")
    @Description("new dl api - scan card with id number hidden")
    public void t05_newDl_idNumberHidden() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/errorhandling/newDl/newDLFront - idNumberHidden.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/errorhandling/newDl/newDLFront - idNumberHidden.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotEquals(variables.getIdNumber(), 203114798);
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertNull(ILDLVariables.getIdNumber());
    }

    @Test(description = "new dl api - scan card with license number hidden")
    @Description("new dl api - scan card with license number hidden")
    public void t06_newDl_licenseNumberHidden() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/errorhandling/newDl/newDLFront - licenseNumberHidden.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/errorhandling/newDl/newDLFront - licenseNumberHidden.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotEquals(variables.getLicenseNumber(), 9182620);
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertNull(ILDLVariables.getLicenseNumber());
    }


}
