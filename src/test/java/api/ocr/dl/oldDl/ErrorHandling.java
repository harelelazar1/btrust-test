package api.ocr.dl.oldDl;

import api.Variables;
import api.ApiResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.ILDLVariables;

import java.io.File;
import java.io.IOException;
import utilities.TestUtils;
public class ErrorHandling {

    Variables variables;
    ApiResponse apiResponse;
    MongoHandler mongoHandler;

    @BeforeMethod
    public void startSessionAndInitToken() {
        //Init token
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
    }

    @Test(description = "old dl no card detected message api test")
    @Description("old dl no card detected message")
    public void t01_oldDl_noCardDetectedMessage() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/moran/cardNotFound/cardNotFound.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 101);
        Assert.assertEquals(variables.getMessageText(), "No card detected");
        Assert.assertTrue(variables.isSuccess());
    }

    @Test(description = "old dl wrong side message api test")
    @Description("old dl wrong side message")
    public void t02_oldDl_wrongSideMessage() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLBack.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        while (variables.getMessageId() != 100) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLBack.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }
        Assert.assertEquals(variables.getMessageId(), 100);
        Assert.assertEquals(variables.getMessageText(), "Wrong side");
    }

    @Test(description = "old dl api - scan card with date of expiry hidden")
    @Description("old dl api - scan card with date of expiry hidden")
    public void t03_oldDl_dateOfExpiryHidden() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/errorhandling/oldDl/oldDLFront - dateOfExpiryHidden.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/errorhandling/oldDl/oldDLFront - dateOfExpiryHidden.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNull(variables.getDateOfExpiry());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertNull(ILDLVariables.getDateOfExpiry());
    }

    @Test(description = "old dl api - scan card with date of issue hidden")
    @Description("old dl api - scan card with date of issue hidden")
    public void t04_oldDl_dateOfIssueHidden() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/errorhandling/oldDl/oldDLFront - dateOfIssueHidden.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/errorhandling/oldDl/oldDLFront - dateOfIssueHidden.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
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

    @Test(description = "old dl api - scan card with id number hidden")
    @Description("old dl api - scan card with id number hidden")
    public void t05_oldDl_idNumberHidden() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/errorhandling/oldDl/oldDLFront - idNumberHidden.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/errorhandling/oldDl/oldDLFront - idNumberHidden.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotEquals(variables.getIdNumber(), 034471045);
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertNull(ILDLVariables.getIdNumber());
    }

    @Test(description = "old dl api - scan card with license number hidden")
    @Description("old dl api - scan card with license number hidden")
    public void t06_oldDl_licenseNumberHidden() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/errorhandling/oldDl/oldDLFront - licenseNumberHidden.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/errorhandling/oldDl/oldDLFront - licenseNumberHidden.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotEquals(variables.getLicenseNumber(), 6905739);
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertNull(ILDLVariables.getLicenseNumber());
    }
}
