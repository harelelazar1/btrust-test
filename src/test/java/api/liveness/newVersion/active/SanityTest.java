package api.liveness.newVersion.active;

import api.Variables;
import api.ApiResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;

import utilities.TestUtils;

import java.io.File;
import java.io.IOException;
import java.lang.Math;

public class SanityTest {

    Variables variables;
    MongoHandler mongoHandler;
    ApiResponse apiResponse;

    int iterationCounter = 1;


    public void resetVariables() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
    }



    @Test(description = "active liveness e2e test",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Active liveness e2e sanity test")
    public void t01_e2eLivenessActiveSanity() throws InterruptedException, IOException {
        String configName="qa_config1.json";
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "LIVENESS", "flowConfigName", configName, apiResponse.CLIENT_INIT_LIVENESS);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 2);
        Assert.assertEquals(variables.getTypeUnderStage(), variables.getStage2RightOrLeft());
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());


        switch (variables.getTypeUnderStage()) {
            case "right": {
                apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barRight.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                Assert.assertEquals(variables.getMessageId(), 152);
                Assert.assertEquals(variables.getMessageText(), "Look " + variables.getTypeUnderStage());
                break;
            }
            case "left": {
                apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barLeft.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                Assert.assertEquals(variables.getMessageId(), 151);
                Assert.assertEquals(variables.getMessageText(), "Look " + variables.getTypeUnderStage());
                break;
            }
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertTrue(variables.isSuccess());

        switch (variables.getMessageId()) {
            case 152: {
                while (!variables.getActionType().equalsIgnoreCase("stage")) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barRight.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                break;
            }
            case 151: {
                while (!variables.getActionType().equalsIgnoreCase("stage")) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barLeft.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                break;
            }
        }

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 3);
        Assert.assertEquals(variables.getTypeUnderStage(), "center");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());

        if (variables.getTypeUnderStage().equalsIgnoreCase("center")) {
            while (!variables.getActionType().equalsIgnoreCase("message")) {
                apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
            }
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 153);
        Assert.assertEquals(variables.getMessageText(), "Look straight");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 4);
        switch (variables.getTypeUnderStage()) {
            case "left": {
                Assert.assertEquals(variables.getTypeUnderStage(), "left");
                Assert.assertEquals(variables.getTypeUnderStage(), variables.getStage4RightOrLeft());
                break;
            }
            case "right": {
                Assert.assertEquals(variables.getTypeUnderStage(), "right");
                Assert.assertEquals(variables.getTypeUnderStage(), variables.getStage4RightOrLeft());
                break;
            }
        }
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("message")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertTrue(variables.isSuccess());
        switch (variables.getMessageId()) {
            case 152: {
                Assert.assertEquals(variables.getMessageId(), 152);
                Assert.assertEquals(variables.getMessageText(), "Look right");
                while (!variables.getActionType().equalsIgnoreCase("complete")) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barRight.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                break;
            }
            case 151: {
                Assert.assertEquals(variables.getMessageId(), 151);
                Assert.assertEquals(variables.getMessageText(), "Look left");
                while (!variables.getActionType().equalsIgnoreCase("complete")) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barLeft.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                break;
            }
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getOrder(), 4);
        Assert.assertEquals(variables.getTypeUnderStage(), variables.getStage4RightOrLeft());
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), configName);
        //Passive stage
        Assert.assertEquals(variables.getType1(), "passive");
        Assert.assertEquals(variables.getHeadPositionType(), "center");
        Assert.assertTrue(variables.isHeadPositionExpected());
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getLivenessScore() - 0.9458342790603638) < 0.1);
        Assert.assertNotNull(variables.getFaceImage());

        //stage 2
        Assert.assertEquals(variables.getType3(), variables.getStage2RightOrLeft());
        Assert.assertNotNull(variables.getHeadPositionType1());
        Assert.assertEquals(variables.getHeadPositionType1(), variables.getType3());
        Assert.assertTrue(variables.isHeadPositionExpected1());
        Assert.assertEquals(variables.getStageStatus3(), "success");

        //stage3
        Assert.assertEquals(variables.getType4(), "center");
        Assert.assertNotNull(variables.getHeadPositionType2());
        Assert.assertEquals(variables.getHeadPositionType2(), variables.getType4());
        Assert.assertTrue(variables.isHeadPositionExpected2());
        Assert.assertEquals(variables.getStageStatus4(), "success");

        //stage4
        Assert.assertEquals(variables.getOrder6(), 4);
        Assert.assertEquals(variables.getType6(), variables.getStage4RightOrLeft());
        Assert.assertNotNull(variables.getHeadPositionType3());
        Assert.assertEquals(variables.getHeadPositionType3(), variables.getType6());
        Assert.assertTrue(variables.isHeadPositionExpected3());

    }

}

