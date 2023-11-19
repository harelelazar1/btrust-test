package api.liveness.newVersion.mathilda.environmentVariables;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.Liveness.SttVariables;

import java.io.File;
import java.io.IOException;

public class sttThresholdViaEnvVariableTest {

    Variables variables;
    ApiResponse apiResponse;
    MongoHandler mongoHandler;
    int iterationCounter = 1;

    public sttThresholdViaEnvVariableTest() {
        apiResponse = new ApiResponse();
        mongoHandler = new MongoHandler();
        variables = new Variables();
    }

    @Test(description = "change stt threshold value via docker")
    @Description("Change stt threshold value via docker environment variable")
    public void t01_sttThresholdValueChangeViaDockerEnv() throws IOException, InterruptedException {
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

        while (!variables.getActionType().equalsIgnoreCase("message")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 155);
        Assert.assertEquals(variables.getMessageText(), "Processing");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getThreshold2(), 0.44);
        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(SttVariables.getThreshold(), 0.44);
    }
}