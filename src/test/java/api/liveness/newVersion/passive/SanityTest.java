package api.liveness.newVersion.passive;

import api.Variables;
import api.ApiResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Liveness.LivenessHandlers;
import utilities.MongoDB.Variables.Liveness.PassiveVariables;
import utilities.MongoDB.Variables.Liveness.SttVariables;
import utilities.MongoDB.Variables.StartSessionVariables;
import utilities.TestUtils;

import java.io.File;
import java.io.IOException;

public class SanityTest {

    Variables variables;
    ApiResponse apiResponse;
    MongoHandler mongoHandler;

    int iterationCounter = 1;

    public SanityTest() {
        apiResponse = new ApiResponse();
        mongoHandler = new MongoHandler();
        variables = new Variables();
    }

    @Test(description = "passive liveness e2e test",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Passive liveness e2e sanity test")
    public void t01_e2eLivenessPassiveSanity() throws  IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "LIVENESS", "flowConfigName", "mathilda.json", apiResponse.CLIENT_INIT_LIVENESS);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getOrder(), 1);
        Assert.assertEquals(variables.getTypeUnderStage(), "passive");
//        Assert.assertEquals(variables.getTypeUnderStage(), "stt");
//        Assert.assertEquals(variables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
        Assert.assertEquals(variables.getStatus(), "success"); //due to image injection
        Assert.assertTrue(variables.isSuccess());
// stt send
//        while (!variables.getActionType().equalsIgnoreCase("complete")) {
//            if (iterationCounter == 1) {
//                apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavaa"), apiResponse.CLIENT_REQUEST_LIVENESS);
//            }
//            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavab"), apiResponse.CLIENT_REQUEST_LIVENESS);
//            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavac"), apiResponse.CLIENT_REQUEST_LIVENESS);
//            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavad"), apiResponse.CLIENT_REQUEST_LIVENESS);
//            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavae"), apiResponse.CLIENT_REQUEST_LIVENESS);
//            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavaf"), apiResponse.CLIENT_REQUEST_LIVENESS);
//            apiResponse.clientResponseLivenessOtpStage(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "json_obj", "{\"stageName\":\"stt\",\"stageOrdinal\":2}", "message_type", "report_stage_ending", apiResponse.CLIENT_REQUEST_LIVENESS);
//            Thread.sleep(1500);
//            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavad"), apiResponse.CLIENT_REQUEST_LIVENESS);
//            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//            if (apiResponse.clientRequest.getBody().asString().contains("\"action_type\":\"complete\"")) {
//                apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//            }
//            iterationCounter++;
//        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");

        //Passive stage
        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder1(), 1);
        Assert.assertEquals(variables.getType1(), "passive");
        Assert.assertEquals(variables.getHeadPositionType(), "center");
        Assert.assertTrue(variables.isHeadPositionExpected());
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getLivenessScore() - 0.9458342790603638) < 0.1);
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertEquals(variables.getStageStatus(), "success");

        //Stt stage
//        Assert.assertEquals(variables.getActionType2(), "complete");
//        Assert.assertEquals(variables.getOrder2(), 2);
//        Assert.assertEquals(variables.getType2(), "stt");
//        Assert.assertEquals(variables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
//        Assert.assertEquals(variables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
//        Assert.assertEquals(variables.getThreshold2(), 0.7);
//        Assert.assertEquals(variables.getLanguage(), "he-IL");
//        Assert.assertTrue(Math.abs(variables.getScore() - 0.9230769230769231) < 0.01);
//        Assert.assertEquals(variables.getStageStatus5(), "success");
//        Assert.assertTrue(variables.getScore() > variables.getThreshold2());

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");
        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getHeadPositionType(), "center");
        Assert.assertTrue(PassiveVariables.isHeadPositionExpected());
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertTrue(Math.abs(PassiveVariables.getScore() - 0.9458342790603638) < 0.01);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(SttVariables.getActionType6(), "complete");
        Assert.assertEquals(SttVariables.getOrder6(), 2);
        Assert.assertEquals(SttVariables.getTypeUnderStage6(), "stt");
        Assert.assertEquals(SttVariables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
        Assert.assertEquals(SttVariables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
        Assert.assertEquals(SttVariables.getThreshold(), 0.7);
        Assert.assertEquals(SttVariables.getLanguage(), "he-IL");
        Assert.assertTrue(Math.abs(SttVariables.getScore() - 0.9230769230769231) < 0.01);
        Assert.assertEquals(SttVariables.getStageStatus6(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }
}
