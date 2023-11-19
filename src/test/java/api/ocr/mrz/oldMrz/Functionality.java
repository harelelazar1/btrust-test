package api.ocr.mrz.oldMrz;

import api.Variables;
import api.ApiResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.MRZVariables;
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
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "MRZ", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
    }


    @Test(description = "api old mrz front side timeout session")
    @Description("Old MRZ end to end Api - front side timeout session")
    public void t02_frontSideOldMrzTimeout() throws InterruptedException, IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/moran/cardNotFound/cardNotFound.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        System.out.println("Sleeping for timeout");
        Thread.sleep(87000);
        long start = System.currentTimeMillis();
        while (!variables.getActionType().equalsIgnoreCase("complete") && (System.currentTimeMillis()-start)/1000<90){
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/guy/guyMrz.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getActionTypeTimeout(), "complete");
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
        Assert.assertEquals(StartSessionVariables.getServiceType(), "MRZ");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), TestUtils.getDefaultMathilda());
        Assert.assertEquals(CommonVariables.getServiceType(), "MRZ");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(MRZVariables.getActionTypeTimeout(), "complete");
        Assert.assertEquals(MRZVariables.getOrderTimeout(), 1);
        Assert.assertEquals(MRZVariables.getTypeUnderStageTimeout(), "front");
        Assert.assertEquals(MRZVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(MRZVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }
}
