package api.liveness.newVersion.mathilda.environmentVariables;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;

import java.io.File;
import java.io.IOException;

public class DisablingFullResponseTest {

    ApiResponse apiResponse;
    Variables variables;
    MongoHandler mongoHandler;
    String temporaryCaseId;
    int iterationCounter;

    @BeforeMethod
    public void startSession() {
        apiResponse = new ApiResponse();
        variables = new Variables();
        mongoHandler = new MongoHandler();
        temporaryCaseId = apiResponse.randomString();
        iterationCounter = 1;
    }

    @Test(description = "liveness passive without full response")
    @Description("Liveness passive e2e with full response disabled")
    public void t01_disablingFullResponseOfLivenessPassive() throws IOException, InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", temporaryCaseId, "libraryName", "LIVENESS", "flowConfigName", "mathilda.json", apiResponse.CLIENT_INIT_LIVENESS);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_LIVENESS);

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            if (iterationCounter == 1) {
                apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavaa"),apiResponse.CLIENT_REQUEST_LIVENESS);
            }
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavab"),apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavac"),apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavad"),apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavae"),apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavaf"),apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.clientResponseLivenessOtpStage(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "json_obj", "{\"stageName\":\"stt\",\"stageOrdinal\":2}", "message_type", "report_stage_ending",apiResponse.CLIENT_REQUEST_LIVENESS);
            Thread.sleep(1500);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavad"),apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_LIVENESS);
            if (apiResponse.clientRequest.getBody().asString().contains("\"action_type\":\"complete\"")) {
                apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_LIVENESS);
            }
            iterationCounter++;
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        mongoHandler.queryHandler(temporaryCaseId, variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertTrue(CommonVariables.isSuccess());
    }

    @Test(description = "liveness active without full response")
    @Description("Liveness active e2e with full response disabled")
    public void t02_disablingFullResponseOfLivenessActive() throws IOException, InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "LIVENESS", "flowConfigName", "qa_config.json", apiResponse.CLIENT_INIT_LIVENESS);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_LIVENESS);

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        switch (variables.getTypeUnderStage()) {
            case "right": {
                while (!variables.getTypeUnderStage().equalsIgnoreCase("center")) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barRight.jpg"), "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                break;
            }
            case "left": {
                while (!variables.getTypeUnderStage().equalsIgnoreCase("center")) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barLeft.jpg"), "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                break;
            }
        }

        while (!variables.getTypeUnderStage().equalsIgnoreCase(variables.getStage4RightOrLeft())) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        switch (variables.getTypeUnderStage()) {
            case "right": {
                while (!variables.getTypeUnderStage().equalsIgnoreCase("otp")) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barRight.jpg"), "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                break;
            }
            case "left": {
                while (!variables.getTypeUnderStage().equalsIgnoreCase("otp")) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barLeft.jpg"), "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                break;
            }
        }

        while (variables.getOrder() != 6) {
            if (iterationCounter == 1) {
                apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/otp/otpAudioChunks/beneficiary_chunk.wavaa"),apiResponse.CLIENT_REQUEST_LIVENESS);
            }
            apiResponse.clientResponseLivenessOtpStage(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "json_obj", "{\"stageName\":\"otp\",\"stageOrdinal\":5}", "message_type", "report_stage_ending",apiResponse.CLIENT_REQUEST_LIVENESS);
            Thread.sleep(1500);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/otp/otpAudioChunks/beneficiary_chunk.wavab"),apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_LIVENESS);

            if (variables.getActionType().equalsIgnoreCase("stage")) {
                break;
            }
            iterationCounter++;
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            if (iterationCounter == 1) {
                apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/otp/otpAudioChunks/beneficiary_chunk.wavaa"),apiResponse.CLIENT_REQUEST_LIVENESS);
            }
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavab"),apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavac"),apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavad"),apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavae"),apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavaf"),apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.clientResponseLivenessOtpStage(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "json_obj", "{\"stageName\":\"stt\",\"stageOrdinal\":6}", "message_type", "report_stage_ending",apiResponse.CLIENT_REQUEST_LIVENESS);
            Thread.sleep(1500);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavad"),apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_LIVENESS);

            if (apiResponse.clientRequest.getBody().asString().contains("\"action_type\":\"complete\"")) {
                apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_LIVENESS);
            }
            iterationCounter++;
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        mongoHandler.queryHandler(temporaryCaseId, variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertTrue(CommonVariables.isSuccess());
    }
}
