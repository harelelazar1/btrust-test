package api.liveness.newVersion.active;

import api.Variables;
import api.ApiResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Liveness.*;
import utilities.MongoDB.Variables.StartSessionVariables;
import utilities.TestUtils;

import java.io.File;
import java.io.IOException;

public class FunctionalityTest {

    ApiResponse apiResponse;
    Variables variables;
    MongoHandler mongoHandler;
    int iterationCounter;

    String configName;

    @BeforeMethod
    public void initSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
        iterationCounter = 1;
        configName=apiResponse.ACTIVE_MATHILDA;
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "LIVENESS", "flowConfigName", configName, apiResponse.CLIENT_INIT_LIVENESS);
        apiResponse.verifyInitVariables(variables);
    }

    @Test(description = "api liveness active passive stage timeout")
    @Description("Api liveness active timeout session at passive stage (straight face)")
    public void t01_livenessActivePassiveStageTimeout() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/face_not_found.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        long start = System.currentTimeMillis();
        while (!variables.getActionType().equalsIgnoreCase("complete")&& System.currentTimeMillis()-start<apiResponse.MODULAR_TIMEOUT_MILLISECONDS+2000) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/face_not_found.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }
        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), configName);
        Assert.assertTrue(variables.isSuccess());
        //stage 1
        Assert.assertEquals(variables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(variables.getOrderTimeout(), 1);
        Assert.assertEquals(variables.getStageTypeTimeout(), "passive");
        Assert.assertEquals(variables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(variables.getLastReceivedImage());
    }

    @Test(description = "api liveness active first dynamic side timeout",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Api liveness active timeout session at the first dynamic side stage (straight face and then dynamic side)")
    public void t02_livenessActiveFirstDynamicSideStageTimeout() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/bar/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        long start = System.currentTimeMillis();
        while (!variables.getActionType().equalsIgnoreCase("complete")&& System.currentTimeMillis()-start<apiResponse.MODULAR_TIMEOUT_MILLISECONDS+2000) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/face_not_found.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
            if (variables.getActionType().equalsIgnoreCase("stage")) {
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getOrder(), 2);
                Assert.assertEquals(variables.getTypeUnderStage(), variables.getStage2RightOrLeft());
                Assert.assertEquals(variables.getStatus(), "success");
                Assert.assertTrue(variables.isSuccess());
            }
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), configName);
        Assert.assertTrue(variables.isSuccess());

        //stage 0
        Assert.assertEquals(variables.getActionType1(), "stage");
        Assert.assertEquals(variables.getOrder1(), 1);
        Assert.assertEquals(variables.getType1(), "passive");
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getLivenessScore() - 0.9395970702171326) < 0.1);
        Assert.assertTrue(variables.getLivenessScore() > variables.getThreshold());
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertEquals(variables.getStageStatus(), "success");

        //stage 1
        Assert.assertEquals(variables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(variables.getOrderTimeout(), 2);
        Assert.assertTrue(variables.getStageTypeTimeout().equalsIgnoreCase("left")
                ||variables.getStageTypeTimeout().equalsIgnoreCase("right"));
        Assert.assertEquals(variables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(variables.getLastReceivedImage());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), configName);
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //Stage 1
        Assert.assertEquals(PassiveVariables.getActionType2(), "stage");
        Assert.assertEquals(PassiveVariables.getOrder2(), 1);
        Assert.assertEquals(PassiveVariables.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertTrue(Math.abs(PassiveVariables.getScore() - 0.9395970702171326) < 0.01);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(LivenessHandlers.getActionTypeTimeout(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrderTimeout(), 2);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStageTimeout(), variables.getStage2RightOrLeft());
        Assert.assertEquals(LivenessHandlers.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(PassiveVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "api liveness active center stage timeout")
    @Description("Api liveness active timeout session at center stage (straight face, dynamic side and then straight face again)")
    public void t03_livenessActiveCenterStageTimeout() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/bar/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

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
        long start = System.currentTimeMillis();
        while (!variables.getActionType().equalsIgnoreCase("complete")&& System.currentTimeMillis()-start<apiResponse.MODULAR_TIMEOUT_MILLISECONDS+2000) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/face_not_found.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
            if (variables.getActionType().equalsIgnoreCase("stage")) {
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getOrder(), 3);
                Assert.assertEquals(variables.getTypeUnderStage(), "center");
                Assert.assertEquals(variables.getStatus(), "success");
                Assert.assertTrue(variables.isSuccess());
            }
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), configName);
        Assert.assertTrue(variables.isSuccess());

        //stage 0
        Assert.assertEquals(variables.getActionType1(), "stage");
        Assert.assertEquals(variables.getOrder1(), 1);
        Assert.assertEquals(variables.getType1(), "passive");
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getLivenessScore() - 0.9395970702171326) < 0.1);
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertEquals(variables.getStageStatus(), "success");

        //stage 1
        Assert.assertEquals(variables.getActionType3(), "stage");
        Assert.assertEquals(variables.getOrder3(), 2);
        if (variables.getType3().equalsIgnoreCase("right")) {
            Assert.assertEquals(variables.getType3(), "right");
        } else Assert.assertEquals(variables.getType3(), "left");
        Assert.assertEquals(variables.getStageStatus3(), "success");

        //stage 2
        Assert.assertEquals(variables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(variables.getOrderTimeout(), 3);
        Assert.assertEquals(variables.getStageTypeTimeout(), "center");
        Assert.assertEquals(variables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(variables.getLastReceivedImage());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), configName);
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //stage 3
        Assert.assertEquals(PassiveVariables.getActionType2(), "stage");
        Assert.assertEquals(PassiveVariables.getOrder2(), 1);
        Assert.assertEquals(PassiveVariables.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertTrue(Math.abs(PassiveVariables.getScore() - 0.9395970702171326) < 0.01);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");
        switch (variables.getStage2RightOrLeft()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "right");
                break;
            }
        }

        Assert.assertEquals(LivenessHandlers.getActionTypeTimeout(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrderTimeout(), 3);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStageTimeout(), "center");
        Assert.assertEquals(LivenessHandlers.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(PassiveVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "api liveness active second dynamic stage timeout")
    @Description("Api liveness active timeout session at second dynamic side (straight face, dynamic side ,straight face and then another dynamic side)")
    public void t04_livenessActiveSecondDynamicSideTimeout() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/bar/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

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

        long start = System.currentTimeMillis();
        while (!variables.getActionType().equalsIgnoreCase("complete")&& System.currentTimeMillis()-start<apiResponse.MODULAR_TIMEOUT_MILLISECONDS+2000) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/face_not_found.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");

        //Passive stage
        Assert.assertEquals(variables.getActionType1(), "stage");
        Assert.assertEquals(variables.getOrder1(), 1);
        Assert.assertEquals(variables.getType1(), "passive");
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getLivenessScore() - 0.9395970702171326) < 0.1);
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertEquals(variables.getActionType3(), "stage");
        Assert.assertEquals(variables.getOrder3(), 2);
        Assert.assertEquals(variables.getType3(), variables.getStage2RightOrLeft());
        Assert.assertEquals(variables.getStageStatus3(), "success");

        Assert.assertEquals(variables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(variables.getOrderTimeout(), 3);
        Assert.assertEquals(variables.getStageTypeTimeout(), "center");
        Assert.assertEquals(variables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(variables.getLastReceivedImage());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), configName);
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //stage 0
        Assert.assertEquals(PassiveVariables.getActionType2(), "stage");
        Assert.assertEquals(PassiveVariables.getOrder2(), 1);
        Assert.assertEquals(PassiveVariables.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertTrue(Math.abs(PassiveVariables.getScore() - 0.9395970702171326) < 0.01);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        //stage 1
        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");
        switch (variables.getStage2RightOrLeft()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "right");
                break;
            }
        }

        //stage 2
        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        //stage 3
        Assert.assertEquals(LivenessHandlers.getActionTypeTimeout(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrderTimeout(), 4);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStageTimeout(), variables.getStage4RightOrLeft());
        Assert.assertEquals(LivenessHandlers.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(PassiveVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "api liveness active stage 4 timeout")
    @Description("Api liveness active timeout session at stage 4 (straight face, dynamic side ,straight face ,dynamic side and then otp stage)")
    public void t05_livenessActiveStage4Timeout() throws IOException {
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

        long start = System.currentTimeMillis();
        while (!variables.getActionType().equalsIgnoreCase("complete")&& System.currentTimeMillis()-start<apiResponse.MODULAR_TIMEOUT_MILLISECONDS+2000) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/face_not_found.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        //Passive stage
        Assert.assertEquals(variables.getActionType1(), "stage");
        Assert.assertEquals(variables.getOrder1(), 1);
        Assert.assertEquals(variables.getType1(), "passive");
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getLivenessScore() - 0.9458342790603638) < 0.1);
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertEquals(variables.getActionType3(), "stage");
        Assert.assertEquals(variables.getOrder3(), 2);
        Assert.assertEquals(variables.getType3(), variables.getStage2RightOrLeft());
        Assert.assertEquals(variables.getStageStatus3(), "success");

        Assert.assertEquals(variables.getActionType3(), "stage");
        Assert.assertEquals(variables.getOrder4(), 3);
        Assert.assertEquals(variables.getType4(), "center");
        Assert.assertEquals(variables.getStageStatus4(), "success");
        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), configName);
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //stage 0
        Assert.assertEquals(PassiveVariables.getActionType2(), "stage");
        Assert.assertEquals(PassiveVariables.getOrder2(), 1);
        Assert.assertEquals(PassiveVariables.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertTrue(Math.abs(PassiveVariables.getScore() - 0.9458342790603638) < 0.01);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        //stage 1
        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");
        switch (variables.getStage2RightOrLeft()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "right");
                break;
            }
        }

        //stage 2
        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        //stage 3
        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");
        switch (variables.getStage4RightOrLeft()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "right");
                break;
            }
        }

        //stage 4
        Assert.assertEquals(LivenessHandlers.getActionTypeTimeout(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrderTimeout(), 5);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStageTimeout(), "otp");
        Assert.assertEquals(LivenessHandlers.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(PassiveVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }


    @Test(description = "api liveness active with face mask")
    @Description("Api liveness active wearing face mask")
    public void t07_activeLivenessStage0WithFaceMask() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/moranWithFaceMask.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 167);
        Assert.assertEquals(variables.getMessageText(), "Face mask detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/moranWithFaceMask.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), configName);
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(variables.getOrderTimeout(), 1);
        Assert.assertEquals(variables.getStageTypeTimeout(), "passive");
        Assert.assertEquals(variables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(variables.getLastReceivedImage());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), configName);
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionTypeTimeout(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrderTimeout(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStageTimeout(), "passive");
        Assert.assertEquals(LivenessHandlers.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(PassiveVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "api liveness active with face mask, and change to normal active image")
    @Description("Api liveness active wearing face mask, and after the face mask detected message, changing to normal liveness active image to continue with the active liveness process")
    public void t08_activeLivenessStage0FaceMaskChange() throws InterruptedException, IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/moranWithFaceMask.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 167);
        Assert.assertEquals(variables.getMessageText(), "Face mask detected");
        Assert.assertTrue(variables.isSuccess());

        //Until here image was face with face mask, from now and forward normal face image sent
        //##################################################################################
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
                while (variables.getMessageId() != 152) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barRight.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                Assert.assertEquals(variables.getMessageId(), 152);
                Assert.assertEquals(variables.getMessageText(), "Look " + variables.getTypeUnderStage());
                break;
            }
            case "left": {
                while (variables.getMessageId() != 151) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barLeft.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
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
        Assert.assertEquals(variables.getStatus(), "success");

        //Passive stage
        Assert.assertEquals(variables.getType1(), "passive");
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getLivenessScore() - 0.9458342790603638) < 0.1);
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertEquals(variables.getType3(), variables.getStage2RightOrLeft());
        Assert.assertEquals(variables.getStageStatus3(), "success");

        Assert.assertEquals(variables.getType4(), "center");
        Assert.assertEquals(variables.getStageStatus4(), "success");

        Assert.assertEquals(variables.getType6(), variables.getStage4RightOrLeft());
        Assert.assertEquals(variables.getStageStatus6(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), configName);
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), configName);
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //Stage 1
        Assert.assertEquals(PassiveVariables.getActionType2(), "stage");
        Assert.assertEquals(PassiveVariables.getOrder2(), 1);
        Assert.assertEquals(PassiveVariables.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertTrue(Math.abs(PassiveVariables.getScore() - 0.9458342790603638) < 0.01);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        //Stage 2
        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");
        switch (variables.getStage2RightOrLeft()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "right");
                break;
            }
        }

        //Stage 3
        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        //Stage 4
        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");
        switch (variables.getStage4RightOrLeft()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "right");
                break;
            }
        }

        //Otp
        Assert.assertEquals(OtpVariables.getActionType5(), "stage");
        Assert.assertEquals(OtpVariables.getOrder5(), 5);
        Assert.assertEquals(OtpVariables.getTypeUnderStage5(), "otp");
        Assert.assertNotNull(OtpVariables.getOtpNumber());
        Assert.assertEquals(OtpVariables.getStageStatus5(), "success");

        //Stt
        Assert.assertEquals(SttVariables.getActionType6(), "complete");
        Assert.assertEquals(SttVariables.getOrder6(), 6);
        Assert.assertEquals(SttVariables.getTypeUnderStage6(), "stt");
        Assert.assertEquals(SttVariables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
        Assert.assertEquals(SttVariables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
        Assert.assertEquals(SttVariables.getThreshold(), 0.7);
        Assert.assertEquals(SttVariables.getLanguage(), "he-IL");
        Assert.assertEquals(SttVariables.getScore(), 0.9230769230769231);
        Assert.assertEquals(SttVariables.getStageStatus6(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "api liveness active with face mask at stage 2, and change to normal active image")
    @Description("Api liveness active wearing face mask at stage 2, and after the face mask detected message, changing to normal liveness active image to continue with the active liveness process")
    public void t09_activeLivenessStage2FaceMaskChange() throws InterruptedException, IOException {
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

        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/moranWithFaceMask.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 153);
        Assert.assertEquals(variables.getMessageText(), "Look straight");
        Assert.assertTrue(variables.isSuccess());

        while (variables.getMessageId() != 167) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/moranWithFaceMask.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 167);
        Assert.assertEquals(variables.getMessageText(), "Face mask detected");
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
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), configName);
        Assert.assertTrue(variables.isSuccess());
        //Passive stage
        Assert.assertEquals(variables.getType1(), "passive");
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getLivenessScore() - 0.9458342790603638) < 0.1);
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertEquals(variables.getType3(), variables.getStage2RightOrLeft());
        Assert.assertEquals(variables.getStageStatus3(), "success");

        Assert.assertEquals(variables.getType4(), "center");
        Assert.assertEquals(variables.getStageStatus4(), "success");

        Assert.assertEquals(variables.getType6(), variables.getStage4RightOrLeft());
        Assert.assertEquals(variables.getStageStatus6(), "success");


        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), configName);
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //Stage 1
        Assert.assertEquals(PassiveVariables.getActionType2(), "stage");
        Assert.assertEquals(PassiveVariables.getOrder2(), 1);
        Assert.assertEquals(PassiveVariables.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertTrue(Math.abs(PassiveVariables.getScore() - 0.9458342790603638) < 0.01);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        //Stage 2
        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");
        switch (variables.getStage2RightOrLeft()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "right");
                break;
            }
        }

        //Stage 3
        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        //Stage 4
        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");
        switch (variables.getStage4RightOrLeft()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "right");
                break;
            }
        }

        //Otp
        Assert.assertEquals(OtpVariables.getActionType5(), "stage");
        Assert.assertEquals(OtpVariables.getOrder5(), 5);
        Assert.assertEquals(OtpVariables.getTypeUnderStage5(), "otp");
        Assert.assertNotNull(OtpVariables.getOtpNumber());
        Assert.assertEquals(OtpVariables.getStageStatus5(), "success");

        //Stt
        Assert.assertEquals(SttVariables.getActionType6(), "complete");
        Assert.assertEquals(SttVariables.getOrder6(), 6);
        Assert.assertEquals(SttVariables.getTypeUnderStage6(), "stt");
        Assert.assertEquals(SttVariables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
        Assert.assertEquals(SttVariables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
        Assert.assertEquals(SttVariables.getThreshold(), 0.7);
        Assert.assertEquals(SttVariables.getLanguage(), "he-IL");
        Assert.assertEquals(SttVariables.getScore(), 0.9230769230769231);
        Assert.assertEquals(SttVariables.getStageStatus6(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "api liveness active with illuminated face")
    @Description("Api liveness active with illuminated face")
    public void t10_activeLivenessStage0IlluminatedFace() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/too_bright.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 165);
        Assert.assertEquals(variables.getMessageText(), "Face not properly illuminated");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/too_bright.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), configName);
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(variables.getOrderTimeout(), 1);
        Assert.assertEquals(variables.getStageTypeTimeout(), "passive");
        Assert.assertEquals(variables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(variables.getLastReceivedImage());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), configName);
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionTypeTimeout(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrderTimeout(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStageTimeout(), "passive");
        Assert.assertEquals(LivenessHandlers.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(PassiveVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "api liveness active with illuminated face, and change to normal active image")
    @Description("Api liveness active with illuminated face, and after the illuminated face message, changing to normal liveness active image to continue with the process")
    public void t11_activeLivenessStage0IlluminatedFaceChange() throws InterruptedException, IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/too_bright.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 165);
        Assert.assertEquals(variables.getMessageText(), "Face not properly illuminated");
        Assert.assertTrue(variables.isSuccess());

        //Until here image was face with face mask, from now and forward normal face image sent
        //##################################################################################
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
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), configName);
        Assert.assertTrue(variables.isSuccess());

        //Passive stage
        Assert.assertEquals(variables.getType1(), "passive");
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getLivenessScore() - 0.9458342790603638) < 0.1);
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertEquals(variables.getType3(), variables.getStage2RightOrLeft());
        Assert.assertEquals(variables.getStageStatus3(), "success");

        Assert.assertEquals(variables.getType4(), "center");
        Assert.assertEquals(variables.getStageStatus4(), "success");

        Assert.assertEquals(variables.getType6(), variables.getStage4RightOrLeft());
        Assert.assertEquals(variables.getStageStatus6(), "success");

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), configName);
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //Stage 1
        Assert.assertEquals(PassiveVariables.getActionType2(), "stage");
        Assert.assertEquals(PassiveVariables.getOrder2(), 1);
        Assert.assertEquals(PassiveVariables.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertTrue(Math.abs(PassiveVariables.getScore() - 0.9458342790603638) < 0.01);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        //Stage 2
        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");
        switch (variables.getStage2RightOrLeft()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "right");
                break;
            }
        }

        //Stage 3
        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        //Stage 4
        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");
        switch (variables.getStage4RightOrLeft()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "right");
                break;
            }
        }

        //Otp
        Assert.assertEquals(OtpVariables.getActionType5(), "stage");
        Assert.assertEquals(OtpVariables.getOrder5(), 5);
        Assert.assertEquals(OtpVariables.getTypeUnderStage5(), "otp");
        Assert.assertNotNull(OtpVariables.getOtpNumber());
        Assert.assertEquals(OtpVariables.getStageStatus5(), "success");

        //Stt
        Assert.assertEquals(SttVariables.getActionType6(), "complete");
        Assert.assertEquals(SttVariables.getOrder6(), 6);
        Assert.assertEquals(SttVariables.getTypeUnderStage6(), "stt");
        Assert.assertEquals(SttVariables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
        Assert.assertEquals(SttVariables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
        Assert.assertEquals(SttVariables.getThreshold(), 0.7);
        Assert.assertEquals(SttVariables.getLanguage(), "he-IL");
        Assert.assertEquals(SttVariables.getScore(), 0.9230769230769231);
        Assert.assertEquals(SttVariables.getStageStatus6(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "api liveness active with illuminated face at stage 2, and change to normal active image")
    @Description("Api liveness active with illuminated face at stage 2, and after the illuminated face message, changing to normal liveness active image to continue with the process")
    public void t12_activeLivenessStage2IlluminatedFaceChange() throws InterruptedException, IOException {
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
                apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                while (!variables.getActionType().equalsIgnoreCase("stage")) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barRight.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                break;
            }
            case "left": {
                apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
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

        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/too_bright.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 153);
        Assert.assertEquals(variables.getMessageText(), "Look straight");
        Assert.assertTrue(variables.isSuccess());

        while (variables.getMessageId() != 165) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/too_bright.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 165);
        Assert.assertEquals(variables.getMessageText(), "Face not properly illuminated");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 4);

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
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), configName);
        Assert.assertTrue(variables.isSuccess());

        //Passive stage
        Assert.assertEquals(variables.getType1(), "passive");
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getLivenessScore() - 0.9458342790603638) < 0.1);
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertEquals(variables.getType3(), variables.getStage2RightOrLeft());
        Assert.assertEquals(variables.getStageStatus3(), "success");

        Assert.assertEquals(variables.getType4(), "center");
        Assert.assertEquals(variables.getStageStatus4(), "success");

        Assert.assertEquals(variables.getType6(), variables.getStage4RightOrLeft());
        Assert.assertEquals(variables.getStageStatus6(), "success");
        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), configName);
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //Stage 1
        Assert.assertEquals(PassiveVariables.getActionType2(), "stage");
        Assert.assertEquals(PassiveVariables.getOrder2(), 1);
        Assert.assertEquals(PassiveVariables.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertTrue(Math.abs(PassiveVariables.getScore() - 0.9458342790603638) < 0.01);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        //Stage 2
        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");
        switch (variables.getStage2RightOrLeft()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "right");
                break;
            }
        }

        //Stage 3
        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        //Stage 4
        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");
        switch (variables.getStage4RightOrLeft()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "right");
                break;
            }
        }
    }

    @Test(description = "api liveness active with face not in focus")
    @Description("Api liveness active with face not in focus")
    public void t13_activeLivenessStage0FaceNotInFocus() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/roleMessages/BadFaceFocus/Blur.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 166);
        Assert.assertEquals(variables.getMessageText(), "Face not in focus");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/roleMessages/BadFaceFocus/Blur.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), configName);
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(variables.getOrderTimeout(), 1);
        Assert.assertEquals(variables.getStageTypeTimeout(), "passive");
        Assert.assertEquals(variables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(variables.getLastReceivedImage());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), configName);
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionTypeTimeout(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrderTimeout(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStageTimeout(), "passive");
        Assert.assertEquals(LivenessHandlers.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(PassiveVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "api liveness active with face not in focus at stage 0, and then changing to normal image")
    @Description("Api liveness active with face not in focus at stage 0, and after the face not in focus message, changing to normal active image to continue with the process")
    public void t14_activeLivenessStage0FaceNotInFocusChange() throws InterruptedException, IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/roleMessages/BadFaceFocus/Blur.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 166);
        Assert.assertEquals(variables.getMessageText(), "Face not in focus");
        Assert.assertTrue(variables.isSuccess());

        //Until here image was face with face mask, from now and forward normal face image sent
        //##################################################################################
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
                while (!variables.getActionType().equalsIgnoreCase("message")) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barRight.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                Assert.assertEquals(variables.getMessageId(), 152);
                Assert.assertEquals(variables.getMessageText(), "Look " + variables.getTypeUnderStage());
                break;
            }
            case "left": {
                while (!variables.getActionType().equalsIgnoreCase("message")) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barLeft.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
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
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), configName);
        Assert.assertTrue(variables.isSuccess());

        //Passive stage
        Assert.assertEquals(variables.getType1(), "passive");
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getLivenessScore() - 0.9458342790603638) < 0.1);
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertEquals(variables.getType3(), variables.getStage2RightOrLeft());
        Assert.assertEquals(variables.getStageStatus3(), "success");

        Assert.assertEquals(variables.getType4(), "center");
        Assert.assertEquals(variables.getStageStatus4(), "success");

        Assert.assertEquals(variables.getType6(), variables.getStage4RightOrLeft());
        Assert.assertEquals(variables.getStageStatus6(), "success");

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), configName);
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //Stage 1
        Assert.assertEquals(PassiveVariables.getActionType2(), "stage");
        Assert.assertEquals(PassiveVariables.getOrder2(), 1);
        Assert.assertEquals(PassiveVariables.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertTrue(Math.abs(PassiveVariables.getScore() - 0.9458342790603638) < 0.01);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        //Stage 2
        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");
        switch (variables.getStage2RightOrLeft()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "right");
                break;
            }
        }

        //Stage 3
        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        //Stage 4
        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");
        switch (variables.getStage4RightOrLeft()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "right");
                break;
            }
        }

        //Otp
        Assert.assertEquals(OtpVariables.getActionType5(), "stage");
        Assert.assertEquals(OtpVariables.getOrder5(), 5);
        Assert.assertEquals(OtpVariables.getTypeUnderStage5(), "otp");
        Assert.assertNotNull(OtpVariables.getOtpNumber());
        Assert.assertEquals(OtpVariables.getStageStatus5(), "success");

        //Stt
        Assert.assertEquals(SttVariables.getActionType6(), "complete");
        Assert.assertEquals(SttVariables.getOrder6(), 6);
        Assert.assertEquals(SttVariables.getTypeUnderStage6(), "stt");
        Assert.assertEquals(SttVariables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
        Assert.assertEquals(SttVariables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
        Assert.assertEquals(SttVariables.getThreshold(), 0.7);
        Assert.assertEquals(SttVariables.getLanguage(), "he-IL");
        Assert.assertEquals(SttVariables.getScore(), 0.9230769230769231);
        Assert.assertEquals(SttVariables.getStageStatus6(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "api liveness passive with face not in focus at stage 2, and then changing to normal image")
    @Description("Api liveness passive with face not in focus at stage 2, and after the face not in focus message, changing to normal passive image to continue with the process")
    public void t15_activeLivenessStage2FaceNotInFocusChange() throws InterruptedException, IOException {
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

        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/roleMessages/BadFaceFocus/Blur.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 153);
        Assert.assertEquals(variables.getMessageText(), "Look straight");
        Assert.assertTrue(variables.isSuccess());

        while (variables.getMessageId() != 166) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/roleMessages/BadFaceFocus/Blur.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 166);
        Assert.assertEquals(variables.getMessageText(), "Face not in focus");
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
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), configName);
        Assert.assertTrue(variables.isSuccess());

        //Passive stage
        Assert.assertEquals(variables.getType1(), "passive");
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getLivenessScore() - 0.9458342790603638) < 0.1);
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertEquals(variables.getType3(), variables.getStage2RightOrLeft());
        Assert.assertEquals(variables.getStageStatus3(), "success");

        Assert.assertEquals(variables.getType4(), "center");
        Assert.assertEquals(variables.getStageStatus4(), "success");

        Assert.assertEquals(variables.getType6(), variables.getStage4RightOrLeft());
        Assert.assertEquals(variables.getStageStatus6(), "success");
        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), configName);
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //Stage 1
        Assert.assertEquals(PassiveVariables.getActionType2(), "stage");
        Assert.assertEquals(PassiveVariables.getOrder2(), 1);
        Assert.assertEquals(PassiveVariables.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertTrue(Math.abs(PassiveVariables.getScore() - 0.9458342790603638) < 0.01);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        //Stage 2
        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");
        switch (variables.getStage2RightOrLeft()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "right");
                break;
            }
        }

        //Stage 3
        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        //Stage 4
        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");
        switch (variables.getStage4RightOrLeft()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "right");
                break;
            }
        }

        //Otp
        Assert.assertEquals(OtpVariables.getActionType5(), "stage");
        Assert.assertEquals(OtpVariables.getOrder5(), 5);
        Assert.assertEquals(OtpVariables.getTypeUnderStage5(), "otp");
        Assert.assertNotNull(OtpVariables.getOtpNumber());
        Assert.assertEquals(OtpVariables.getStageStatus5(), "success");

        //Stt
        Assert.assertEquals(SttVariables.getActionType6(), "complete");
        Assert.assertEquals(SttVariables.getOrder6(), 6);
        Assert.assertEquals(SttVariables.getTypeUnderStage6(), "stt");
        Assert.assertEquals(SttVariables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
        Assert.assertEquals(SttVariables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
        Assert.assertEquals(SttVariables.getThreshold(), 0.7);
        Assert.assertEquals(SttVariables.getLanguage(), "he-IL");
        Assert.assertEquals(SttVariables.getScore(), 0.9230769230769231);
        Assert.assertEquals(SttVariables.getStageStatus6(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }
}
