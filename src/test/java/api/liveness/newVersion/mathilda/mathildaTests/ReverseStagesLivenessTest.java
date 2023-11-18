package api.liveness.newVersion.mathilda.mathildaTests;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Liveness.LeftRightCenterVariables;
import utilities.MongoDB.Variables.Liveness.OtpVariables;
import utilities.MongoDB.Variables.Liveness.PassiveVariables;
import utilities.MongoDB.Variables.Liveness.SttVariables;
import utilities.MongoDB.Variables.StartSessionVariables;
import utilities.TestUtils;

import java.io.File;
import java.io.IOException;

public class ReverseStagesLivenessTest {

    Variables variables;
    ApiResponse apiResponse;
    MongoHandler mongoHandler;
    int iterationCounter;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
        iterationCounter = 1;
    }

    @Test(description = "liveness active reverse stages")
    @Description("Liveness active reverse stages test - starting with stt stage and then otp and all the way to passive at the end")
    public void t01_livenessActiveReverseStages() throws IOException, InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "LIVENESS", "flowConfigName", "reverseStages.json", apiResponse.CLIENT_INIT_LIVENESS);
        apiResponse.verifyInitVariables(variables);
        if (iterationCounter == 1) {
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/otp/otpAudioChunks/beneficiary_chunk.wavaa"), apiResponse.CLIENT_REQUEST_LIVENESS);
        }
        apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavab"), apiResponse.CLIENT_REQUEST_LIVENESS);
        apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavac"), apiResponse.CLIENT_REQUEST_LIVENESS);
        apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavad"), apiResponse.CLIENT_REQUEST_LIVENESS);
        apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavae"), apiResponse.CLIENT_REQUEST_LIVENESS);
        apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavaf"), apiResponse.CLIENT_REQUEST_LIVENESS);
        apiResponse.clientResponseLivenessOtpStage(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "json_obj", "{\"stageName\":\"stt\",\"stageOrdinal\":2}", "message_type", "report_stage_ending", apiResponse.CLIENT_REQUEST_LIVENESS);
        Thread.sleep(1500);
        apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavad"), apiResponse.CLIENT_REQUEST_LIVENESS);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }
        iterationCounter++;

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 2);
        Assert.assertEquals(variables.getTypeUnderStage(), "otp");
        Assert.assertTrue(variables.getOtpNum()>0);
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());


        while (variables.getOrder() != 3) {
            if (iterationCounter == 1) {
                apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/otp/otpAudioChunks/beneficiary_chunk.wavaa"), apiResponse.CLIENT_REQUEST_LIVENESS);
            }
            apiResponse.clientResponseLivenessOtpStage(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "json_obj", "{\"stageName\":\"otp\",\"stageOrdinal\":5}", "message_type", "report_stage_ending", apiResponse.CLIENT_REQUEST_LIVENESS);
            Thread.sleep(1500);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/otp/otpAudioChunks/beneficiary_chunk.wavab"), apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

            if (variables.getActionType().equalsIgnoreCase("stage")) {
                break;
            }
            iterationCounter++;
        }

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 3);
        Assert.assertEquals(variables.getTypeUnderStage(), variables.getStage2RightOrLeft());
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());

        switch (variables.getTypeUnderStage()) {
            case "right": {
                while (variables.getOrder() != 4) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barRight.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                break;
            }
            case "left": {
                while (variables.getOrder() != 4) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barLeft.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                break;
            }
        }

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 4);
        Assert.assertEquals(variables.getTypeUnderStage(), "center");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("message")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barLeft.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 153);
        Assert.assertEquals(variables.getMessageText(), "Look straight");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 5);
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
            switch (variables.getStage4RightOrLeft()) {
                case "left": {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barLeft.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                    break;
                }
                case "right": {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barRight.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                    break;
                }
            }
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertTrue(variables.isSuccess());
        switch (variables.getMessageId()) {
            case 152: {
                Assert.assertEquals(variables.getMessageId(), 152);
                Assert.assertEquals(variables.getMessageText(), "Look right");
                while (!variables.getActionType().equalsIgnoreCase("stage")) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barRight.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                break;
            }
            case 151: {
                Assert.assertEquals(variables.getMessageId(), 151);
                Assert.assertEquals(variables.getMessageText(), "Look left");
                while (!variables.getActionType().equalsIgnoreCase("stage")) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barLeft.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                break;
            }
        }

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 6);
        Assert.assertEquals(variables.getTypeUnderStage(), "passive");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());

        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 153);
        Assert.assertEquals(variables.getMessageText(), "Look straight");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");

        //stt
        Assert.assertEquals(variables.getActionType2(), "stage");
        Assert.assertEquals(variables.getOrder2(), 1);
        Assert.assertEquals(variables.getType2(), "stt");
        Assert.assertEquals(variables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
        Assert.assertEquals(variables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
        Assert.assertEquals(variables.getThreshold2(), 0.7);
        Assert.assertEquals(variables.getLanguage(), "he-IL");
        Assert.assertEquals(variables.getScore(), (float) 0.9230769230769231);
        Assert.assertEquals(variables.getStageStatus5(), "success");
        Assert.assertTrue(variables.getScore() > variables.getThreshold2());

        //otp
        Assert.assertEquals(variables.getActionType5(), "stage");
        Assert.assertEquals(variables.getOrder5(), 2);
        Assert.assertEquals(variables.getType5(), "otp");
        Assert.assertTrue(variables.getOtpNum()>0);
        Assert.assertEquals(variables.getStageStatus5(), "success");

        //dynamic side #1
        Assert.assertEquals(variables.getActionType3(), "stage");
        Assert.assertEquals(variables.getOrder3(), 3);
        Assert.assertEquals(variables.getType3(), variables.getStage2RightOrLeft());
        Assert.assertEquals(variables.getStageStatus3(), "success");

        //center
        Assert.assertEquals(variables.getActionType3(), "stage");
        Assert.assertEquals(variables.getOrder4(), 4);
        Assert.assertEquals(variables.getType4(), "center");
        Assert.assertEquals(variables.getStageStatus4(), "success");

        //dynamic side #2
        Assert.assertEquals(variables.getActionType3(), "stage");
        Assert.assertEquals(variables.getOrder6(), 5);
        Assert.assertEquals(variables.getType6(), variables.getStage4RightOrLeft());
        Assert.assertEquals(variables.getStageStatus6(), "success");

        //passive
        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder1(), 6);
        Assert.assertEquals(variables.getType1(), "passive");
        Assert.assertEquals(variables.getThreshold(), 0.5);
        Assert.assertEquals(variables.getLivenessScore(), (float) 0.9458342790603638);
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "reverseStages.json");
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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "reverseStages.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //Stt
        Assert.assertEquals(SttVariables.getActionType6(), "stage");
        Assert.assertEquals(SttVariables.getOrder6(), 1);
        Assert.assertEquals(SttVariables.getTypeUnderStage6(), "stt");
        Assert.assertEquals(SttVariables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
        Assert.assertEquals(SttVariables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
        Assert.assertEquals(SttVariables.getThreshold(), 0.7);
        Assert.assertEquals(SttVariables.getLanguage(), "he-IL");
        Assert.assertEquals(SttVariables.getScore(), 0.9230769230769231);
        Assert.assertEquals(SttVariables.getStageStatus6(), "success");

        //Otp
        Assert.assertEquals(OtpVariables.getActionType5(), "stage");
        Assert.assertEquals(OtpVariables.getOrder5(), 2);
        Assert.assertEquals(OtpVariables.getTypeUnderStage5(), "otp");
        Assert.assertNotNull(OtpVariables.getOtpNumber());
        Assert.assertEquals(OtpVariables.getStageStatus5(), "success");

        //Stage 2
        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 3);
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
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 4);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        //Stage 4
        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 5);
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

        //Passive
        Assert.assertEquals(PassiveVariables.getActionType2(), "complete");
        Assert.assertEquals(PassiveVariables.getOrder2(), 6);
        Assert.assertEquals(PassiveVariables.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.5);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9458342790603638);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "liveness passive reverse stages")
    @Description("Liveness passive reverse stages test - starting  with stt stage and then passive")
    public void t02_livenessPassiveReverseStages() throws IOException, InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "LIVENESS", "flowConfigName", "qa_configReverse.json", apiResponse.CLIENT_INIT_LIVENESS);
        apiResponse.verifyInitVariables(variables);

        if (iterationCounter == 1) {
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/otp/otpAudioChunks/beneficiary_chunk.wavaa"), apiResponse.CLIENT_REQUEST_LIVENESS);
        }
        apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavab"), apiResponse.CLIENT_REQUEST_LIVENESS);
        apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavac"), apiResponse.CLIENT_REQUEST_LIVENESS);
        apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavad"), apiResponse.CLIENT_REQUEST_LIVENESS);
        apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavae"), apiResponse.CLIENT_REQUEST_LIVENESS);
        apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavaf"), apiResponse.CLIENT_REQUEST_LIVENESS);
        apiResponse.clientResponseLivenessOtpStage(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "json_obj", "{\"stageName\":\"stt\",\"stageOrdinal\":1}", "message_type", "report_stage_ending", apiResponse.CLIENT_REQUEST_LIVENESS);
        Thread.sleep(1500);
        apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavad"), apiResponse.CLIENT_REQUEST_LIVENESS);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }
        iterationCounter++;

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 2);
        Assert.assertEquals(variables.getTypeUnderStage(), "passive");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");

        //stt
        Assert.assertEquals(variables.getActionType2(), "stage");
        Assert.assertEquals(variables.getOrder2(), 1);
        Assert.assertEquals(variables.getType2(), "stt");
        Assert.assertEquals(variables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
        Assert.assertEquals(variables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
        Assert.assertEquals(variables.getThreshold2(), 0.7);
        Assert.assertEquals(variables.getLanguage(), "he-IL");
        Assert.assertEquals(variables.getScore(), (float) 0.9230769230769231);
        Assert.assertEquals(variables.getStageStatus5(), "success");
        Assert.assertTrue(variables.getScore() > variables.getThreshold2());

        //passive
        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder1(), 2);
        Assert.assertEquals(variables.getType1(), "passive");
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertEquals(variables.getLivenessScore(), (float) 0.9458342790603638);
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_configReverse.json");
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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "qa_configReverse.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //Stt
        Assert.assertEquals(SttVariables.getActionType6(), "stage");
        Assert.assertEquals(SttVariables.getOrder6(), 1);
        Assert.assertEquals(SttVariables.getTypeUnderStage6(), "stt");
        Assert.assertEquals(SttVariables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
        Assert.assertEquals(SttVariables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
        Assert.assertEquals(SttVariables.getThreshold(), 0.7);
        Assert.assertEquals(SttVariables.getLanguage(), "he-IL");
        Assert.assertEquals(SttVariables.getScore(), 0.9230769230769231);
        Assert.assertEquals(SttVariables.getStageStatus6(), "success");

        //Passive
        Assert.assertEquals(PassiveVariables.getActionType2(), "complete");
        Assert.assertEquals(PassiveVariables.getOrder2(), 2);
        Assert.assertEquals(PassiveVariables.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9458342790603638);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }
}
