package api.liveness.newVersion.mathilda.environmentVariables;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.StartSessionVariables;

import java.io.File;
import java.io.IOException;

public class noWebhookURLTest {

    ApiResponse apiResponse;
    Variables variables;
    MongoHandler mongoHandler;
    int iterationCounter = 1;

    public noWebhookURLTest() {
        apiResponse = new ApiResponse();
        mongoHandler = new MongoHandler();
        variables = new Variables();
    }

    @Test(description = "liveness without webhook URL")
    @Description("Liveness without webhook URL in the docker environment variable")
    public void t01_livenessWithoutWebhookURL() throws InterruptedException, IOException {
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
        Assert.assertNull(StartSessionVariables.getCaseId());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(CommonVariables.getCaseId());
    }
}
