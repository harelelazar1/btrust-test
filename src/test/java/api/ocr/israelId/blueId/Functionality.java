package api.ocr.israelId.blueId;

import api.Variables;
import api.ApiResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.ILIDVariables;
import utilities.MongoDB.Variables.Ocr.OcrHandlers;
import utilities.MongoDB.Variables.StartSessionVariables;

import java.io.File;
import java.io.IOException;
import utilities.TestUtils;
public class Functionality {

    Variables variables;
    ApiResponse apiResponse;
    MongoHandler mongoHandler;

    @BeforeMethod
    public void startSessionAndInitToken() {
        //Init token
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
    }

    @Test(description = "blue id api e2e front side timeout",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Blue id end to end Api - front side timeout session")
    public void t01_e2eBlueId_frontSideTimeoutSession() throws InterruptedException, IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/moran/cardNotFound/cardNotFound.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        Thread.sleep(85000);
        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/blueID/liad/blueID_color1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }
//        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/bar/BarGreenID1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(variables.getOrderTimeout(), 1);
        Assert.assertEquals(variables.getStageTypeTimeout(), "front");
        Assert.assertEquals(variables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(variables.getLastReceivedImage());

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
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(ILIDVariables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(ILIDVariables.getOrderTimeout(), 1);
        Assert.assertEquals(ILIDVariables.getTypeUnderStageTimeout(), "front");
        Assert.assertEquals(ILIDVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(ILIDVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

}
