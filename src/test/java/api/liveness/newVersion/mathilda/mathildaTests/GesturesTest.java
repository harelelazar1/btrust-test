package api.liveness.newVersion.mathilda.mathildaTests;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Liveness.GesturesVariables;
import utilities.MongoDB.Variables.Liveness.LeftRightCenterVariables;
import utilities.MongoDB.Variables.Liveness.LivenessHandlers;
import utilities.MongoDB.Variables.Liveness.PassiveVariables;
import utilities.MongoDB.Variables.StartSessionVariables;
import utilities.TestUtils;

import java.io.File;
import java.io.IOException;

public class GesturesTest {

    Variables variables;
    ApiResponse apiResponse;
    MongoHandler mongoHandler;

    @BeforeMethod
    public void initSession() {
        variables = new Variables();
        apiResponse = new ApiResponse();
        mongoHandler = new MongoHandler();
    }

    @Test(description = "liveness passive and gesture")
    @Description("Passive liveness with gestures stage in the end of the process")
    public void t01_livenessPassiveWithGesture() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "LIVENESS", "flowConfigName", "gesturesWithPassive.json", apiResponse.CLIENT_INIT_LIVENESS);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        System.out.println(variables.getThreshold());
        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 2);
        Assert.assertEquals(variables.getTypeUnderStage(), "gestures");
        Assert.assertNotNull(variables.getGesturesArray());
        Assert.assertEquals(variables.getGesturesArray().size(), 2);
        Assert.assertEquals(variables.getSequenceSecondsInterval(), 8);
        for (int i = 0; i < variables.getGesturesArray().size(); i++) { //Verifying that each gesture type is different (actionType = stage)
            for (int j = 1; j < variables.getGesturesArray().size(); j++) {
                if (i == j) {
                    break;
                }
                Assert.assertNotEquals(variables.getGesturesArray().get(i), variables.getGesturesArray().get(j));
            }
        }
        apiResponse.clientResponseLivenessOtpStage(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "json_obj", "{\"stageName\":\"gestures\",\"stageOrdinal\":2}", "message_type", "report_stage_ending", apiResponse.CLIENT_REQUEST_LIVENESS);
        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");

        //Passive stage
        Assert.assertEquals(variables.getActionType1(), "stage");
        Assert.assertEquals(variables.getOrder1(), 1);
        Assert.assertEquals(variables.getType1(), "passive");
        Assert.assertEquals(variables.getHeadPositionType(), "center");
        Assert.assertTrue(variables.isHeadPositionExpected());
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertEquals(variables.getLivenessScore(), (float) 0.9458342790603638);
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertEquals(variables.getStageStatus(), "success");

        //Gesture stage
        Assert.assertEquals(variables.getGestureActionType(), "complete");
        Assert.assertEquals(variables.getGestureOrder(), 2);
        Assert.assertEquals(variables.getGestureType(), "gestures");
        Assert.assertNotNull(variables.getGesturesArray());
        for (int i = 0; i < variables.getGesturesArray().size(); i++) { //Verifying that each gesture type is different (actionType = complete)
            for (int j = 1; j < variables.getGesturesArray().size(); j++) {
                if (i == j) {
                    break;
                }
                Assert.assertNotEquals(variables.getGesturesArray().get(i), variables.getGesturesArray().get(j));
            }
        }
        Assert.assertEquals(variables.getSequenceSecondsInterval(), 8);
        Assert.assertEquals(variables.getGestureStageStatus(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "gesturesWithPassive.json");
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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "gesturesWithPassive.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //Passive stage
        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getHeadPositionType(), "center");
        Assert.assertTrue(PassiveVariables.isHeadPositionExpected());
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9458342790603638);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        //Gestures stage
        Assert.assertEquals(LivenessHandlers.getActionType8(), "complete");
        Assert.assertEquals(LivenessHandlers.getOrder8(), 2);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage8(), "gestures");
        Assert.assertNotNull(GesturesVariables.getGestures1());
        Assert.assertNotNull(GesturesVariables.getGestures2());
        Assert.assertEquals(GesturesVariables.getSequenceSecondsInterval(), 8);
        Assert.assertNotNull(GesturesVariables.getResourceGesture1());
        Assert.assertNotNull(GesturesVariables.getResourceGestureMask1());
        Assert.assertNotNull(GesturesVariables.getResourceGestureMaskLine1());
        Assert.assertNotNull(GesturesVariables.getResourceGesture2());
        Assert.assertNotNull(GesturesVariables.getResourceGestureMask2());
        Assert.assertNotNull(GesturesVariables.getResourceGestureMaskLine2());
        //  Assert.assertNotNull(GesturesVariables.getResourceGesture3());
        //  Assert.assertNotNull(GesturesVariables.getResourceGestureMask3());
        //  Assert.assertNotNull(GesturesVariables.getResourceGestureMaskLine3());


        //     Assert.assertNotNull(GesturesVariables.getResourceGesture2());
    }

    @Test(description = "liveness active and gesture")
    @Description("Active liveness with gestures stage in the middle of the process")
    public void t02_livenessActiveWithGesture() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "LIVENESS", "flowConfigName", "gesturesWithActive.json", apiResponse.CLIENT_INIT_LIVENESS);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 2);
        Assert.assertEquals(variables.getTypeUnderStage(), "gestures");
        Assert.assertNotNull(variables.getGesturesArray());
        Assert.assertEquals(variables.getGesturesArray().size(), 3);
        Assert.assertEquals(variables.getSequenceSecondsInterval(), 6);
        for (int i = 0; i < variables.getGesturesArray().size(); i++) { //Verifying that each gesture type is different (actionType = stage)
            for (int j = 1; j < variables.getGesturesArray().size(); j++) {
                if (i == j) {
                    break;
                }
                Assert.assertNotEquals(variables.getGesturesArray().get(i), variables.getGesturesArray().get(j));
            }
        }
        apiResponse.clientResponseLivenessOtpStage(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "json_obj", "{\"stageName\":\"gestures\",\"stageOrdinal\":2}", "message_type", "report_stage_ending", apiResponse.CLIENT_REQUEST_LIVENESS);
        while (variables.getOrder() != 3) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }
        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 3);
        Assert.assertNotNull(variables.getTypeUnderStage());
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());
        switch (variables.getTypeUnderStage()) {
            case "left": {
                while (variables.getOrder() != 4) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barLeft.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
            }
            case "right": {
                while (variables.getOrder() != 4) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barRight.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
            }
        }
        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 4);
        Assert.assertEquals(variables.getTypeUnderStage(), "center");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("message")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }
        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 153);
        Assert.assertEquals(variables.getMessageText(), "Look straight");
        Assert.assertTrue(variables.isSuccess());
        while (variables.getOrder() != 5) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 5);
        Assert.assertNotNull(variables.getTypeUnderStage());
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());
        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            switch (variables.getTypeUnderStage()) {
                case "left": {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barLeft.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                case "right": {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barRight.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
            }
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");

        //Passive stage
        Assert.assertEquals(variables.getActionType1(), "stage");
        Assert.assertEquals(variables.getOrder1(), 1);
        Assert.assertEquals(variables.getType1(), "passive");
        Assert.assertEquals(variables.getHeadPositionType(), "center");
        Assert.assertTrue(variables.isHeadPositionExpected());
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertEquals(variables.getLivenessScore(), (float) 0.9458342790603638);
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertEquals(variables.getGestureActionType(), "stage");
        Assert.assertEquals(variables.getGestureOrder(), 2);
        Assert.assertEquals(variables.getGestureType(), "gestures");
        Assert.assertNotNull(variables.getGesturesArray());
        for (int i = 0; i < variables.getGesturesArray().size(); i++) { //Verifying that each gesture type is different (actionType = complete)
            for (int j = 1; j < variables.getGesturesArray().size(); j++) {
                if (i == j) {
                    break;
                }
                Assert.assertNotEquals(variables.getGesturesArray().get(i), variables.getGesturesArray().get(j));
            }
        }
        Assert.assertEquals(variables.getSequenceSecondsInterval(), 6);
        Assert.assertEquals(variables.getGestureStageStatus(), "success");

        Assert.assertEquals(variables.getOrder3(), 3);
        Assert.assertNotNull(variables.getType3());
        Assert.assertNotNull(variables.getHeadPositionType1());
        if (variables.getType3().equalsIgnoreCase("left")) {
            Assert.assertEquals(variables.getHeadPositionType1(), "left");
        } else Assert.assertEquals(variables.getHeadPositionType1(), "right");
        Assert.assertTrue(variables.isHeadPositionExpected1());
        Assert.assertEquals(variables.getStageStatus3(), "success");

        Assert.assertEquals(variables.getOrder4(), 4);
        Assert.assertEquals(variables.getType4(), "center");
        Assert.assertNotNull(variables.getHeadPositionType2());
        Assert.assertEquals(variables.getHeadPositionType2(), variables.getType4());
        Assert.assertTrue(variables.isHeadPositionExpected2());
        Assert.assertEquals(variables.getStageStatus4(), "success");

        Assert.assertEquals(variables.getActionType3(), "complete");
        Assert.assertEquals(variables.getOrder6(), 5);
        Assert.assertNotNull(variables.getType6());
        Assert.assertNotNull(variables.getHeadPositionType3());
        if (variables.getType3().equalsIgnoreCase("left")) {
            Assert.assertEquals(variables.getHeadPositionType1(), "left");
        } else Assert.assertEquals(variables.getHeadPositionType1(), "right");
        Assert.assertTrue(variables.isHeadPositionExpected3());
        Assert.assertEquals(variables.getStageStatus6(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "gesturesWithActive.json");
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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "gesturesWithActive.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //Passive stage
        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getHeadPositionType(), "center");
        Assert.assertTrue(PassiveVariables.isHeadPositionExpected());
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9458342790603638);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        //Gestures stage
        Assert.assertEquals(LivenessHandlers.getActionType8(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder8(), 2);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage8(), "gestures");
        Assert.assertNotNull(GesturesVariables.getGestures1());
        Assert.assertNotNull(GesturesVariables.getGestures2());
        Assert.assertNotNull(GesturesVariables.getGestures3());
        Assert.assertEquals(GesturesVariables.getSequenceSecondsInterval(), 6);
        Assert.assertNotNull(GesturesVariables.getResourceGesture1());
        Assert.assertNotNull(GesturesVariables.getResourceGesture2());
        Assert.assertNotNull(GesturesVariables.getResourceGesture3());

        //Stage 3
        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");
        switch (LeftRightCenterVariables.getTypeUnderStage3()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "right");
                break;
            }
        }
        Assert.assertNotNull(LeftRightCenterVariables.getHeadPositionType());
        Assert.assertEquals(LeftRightCenterVariables.getHeadPositionType(), LeftRightCenterVariables.getTypeUnderStage3());
        Assert.assertTrue(LeftRightCenterVariables.isHeadPositionExpected());

        //Stage 4
        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 4);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertNotNull(LeftRightCenterVariables.getHeadPositionType1());
        Assert.assertEquals(LeftRightCenterVariables.getHeadPositionType1(), "center");
        Assert.assertTrue(LeftRightCenterVariables.isHeadPositionExpected1());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        //Stage 5
        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "complete");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 5);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");
        switch (LeftRightCenterVariables.getTypeUnderStage7()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "right");
                break;
            }
        }
        Assert.assertNotNull(LeftRightCenterVariables.getHeadPositionType2());
        Assert.assertEquals(LeftRightCenterVariables.getHeadPositionType2(), LeftRightCenterVariables.getTypeUnderStage7());
        Assert.assertTrue(LeftRightCenterVariables.isHeadPositionExpected2());
    }
}
