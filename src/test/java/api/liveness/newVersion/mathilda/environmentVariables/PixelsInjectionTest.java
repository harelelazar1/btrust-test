package api.liveness.newVersion.mathilda.environmentVariables;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;

import java.io.File;
import java.io.IOException;

public class PixelsInjectionTest {

    Variables variables;
    MongoHandler mongoHandler;
    ApiResponse apiResponse;
    File passiveFaceImagePixelated;
    File leftFaceImagePixelated;
    File rightFaceImagePixelated;
    int iterationCounter;

    @BeforeMethod
    public void initSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
        iterationCounter = 1;
    }

    @AfterMethod
    public void verifyStatusIsSuccess() {
        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "success");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Process ended successfully");
    }

    @Test(description = "liveness passive with cPallet pixel injection")
    @Description("Liveness passive with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t01_livenessPassivePixelInjection() throws IOException, InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "LIVENESS", "flowConfigName", "mathilda.json", apiResponse.CLIENT_INIT_LIVENESS);
        apiResponse.verifyInitVariables(variables);
        passiveFaceImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./liveness/apiPic/barCenter.jpg"), 1f);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", passiveFaceImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", passiveFaceImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        while (!variables.getTypeUnderStage().equalsIgnoreCase("stt")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", passiveFaceImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            if (iterationCounter == 1) {
                apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavaa"), apiResponse.CLIENT_REQUEST_LIVENESS);
            }
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavab"), apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavac"), apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavad"), apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavae"), apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavaf"), apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.clientResponseLivenessOtpStage(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "json_obj", "{\"stageName\":\"stt\",\"stageOrdinal\":2}", "message_type", "report_stage_ending", apiResponse.CLIENT_REQUEST_LIVENESS);
            Thread.sleep(1500);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavad"), apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
            if (apiResponse.clientRequest.getBody().asString().contains("\"action_type\":\"complete\"")) {
                apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
            }
            iterationCounter++;
        }
    }

    @Test(description = "liveness active with cPallet pixel injection")
    @Description("Liveness active with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t02_livenessActivePixelInjection() throws IOException, InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "LIVENESS", "flowConfigName", "qa_config.json", apiResponse.CLIENT_INIT_LIVENESS);
        apiResponse.verifyInitVariables(variables);
        passiveFaceImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./liveness/apiPic/barCenter.jpg"), 1f);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", passiveFaceImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", passiveFaceImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 2);
        Assert.assertEquals(variables.getTypeUnderStage(), variables.getStage2RightOrLeft());
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());


        switch (variables.getTypeUnderStage()) {
            case "right": {
                rightFaceImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./liveness/apiPic/barRight.jpg"), 1f);
                apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", rightFaceImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                Assert.assertEquals(variables.getMessageId(), 152);
                Assert.assertEquals(variables.getMessageText(), "Look " + variables.getTypeUnderStage());
                break;
            }
            case "left": {
                leftFaceImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./liveness/apiPic/barLeft.jpg"), 1f);
                apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", leftFaceImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                Assert.assertEquals(variables.getMessageId(), 151);
                Assert.assertEquals(variables.getMessageText(), "Look " + variables.getTypeUnderStage());
                break;
            }
        }

        switch (variables.getMessageId()) {
            case 152: {
                while (!variables.getActionType().equalsIgnoreCase("stage")) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", rightFaceImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                break;
            }
            case 151: {
                while (!variables.getActionType().equalsIgnoreCase("stage")) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", leftFaceImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                break;
            }
        }

        passiveFaceImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./liveness/apiPic/barCenter.jpg"), 1f);
        while (variables.getOrder() != 4) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", passiveFaceImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        switch (variables.getTypeUnderStage()) {
            case "right": {
                rightFaceImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./liveness/apiPic/barRight.jpg"), 1f);
                while (!variables.getTypeUnderStage().equalsIgnoreCase("otp")) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", rightFaceImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                break;
            }
            case "left": {
                leftFaceImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./liveness/apiPic/barLeft.jpg"), 1f);
                while (!variables.getTypeUnderStage().equalsIgnoreCase("otp")) {
                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", leftFaceImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
                }
                break;
            }
        }

        while (variables.getOrder() != 6) {
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

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            if (iterationCounter == 1) {
                apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/otp/otpAudioChunks/beneficiary_chunk.wavaa"), apiResponse.CLIENT_REQUEST_LIVENESS);
            }
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavab"), apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavac"), apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavad"), apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavae"), apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavaf"), apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.clientResponseLivenessOtpStage(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "json_obj", "{\"stageName\":\"stt\",\"stageOrdinal\":6}", "message_type", "report_stage_ending", apiResponse.CLIENT_REQUEST_LIVENESS);
            Thread.sleep(1500);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavad"), apiResponse.CLIENT_REQUEST_LIVENESS);
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

            if (apiResponse.clientRequest.getBody().asString().contains("\"action_type\":\"complete\"")) {
                apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
            }
            iterationCounter++;
        }


    }
}
