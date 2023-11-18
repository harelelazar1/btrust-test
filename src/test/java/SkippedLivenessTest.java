import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.BusinessCategoryPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.BusinessRelationshipsPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.DataProfilePage;
import btrust.btrustOne.admin.usersManager.users.pageObject.EditUserPage;
import btrust.btrustOne.admin.usersManager.users.pageObject.NewUserPage;
import btrust.btrustOne.admin.usersManager.users.pageObject.UsersPage;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.SegmentationPage;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.TriggerBuilderPage;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.TriggerInformationPage;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.WorkflowPage;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Liveness.*;
import utilities.MongoDB.Variables.StartSessionVariables;

import java.io.File;
import java.io.IOException;

public class SkippedLivenessTest extends BaseAdminUserTest {

    @Test(description = "active liveness e2e test with STT", enabled = false)
    @Description("Active liveness e2e sanity test with STT")
    public void t02_e2eLivenessActiveSttSanity() throws InterruptedException, IOException {
//        String configName="qa_config1.json";
//        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "LIVENESS", "flowConfigName", configName, apiResponse.CLIENT_INIT_LIVENESS);
//        apiResponse.verifyInitVariables(variables);
//        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//
//        while (!variables.getActionType().equalsIgnoreCase("stage")) {
//            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//        }
//
//        Assert.assertEquals(variables.getActionType(), "stage");
//        Assert.assertEquals(variables.getOrder(), 2);
//        Assert.assertEquals(variables.getTypeUnderStage(), variables.getStage2RightOrLeft());
//        Assert.assertEquals(variables.getStatus(), "success");
//        Assert.assertTrue(variables.isSuccess());
//
//
//        switch (variables.getTypeUnderStage()) {
//            case "right": {
//                apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barRight.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//                Assert.assertEquals(variables.getMessageId(), 152);
//                Assert.assertEquals(variables.getMessageText(), "Look " + variables.getTypeUnderStage());
//                break;
//            }
//            case "left": {
//                apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barLeft.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//                Assert.assertEquals(variables.getMessageId(), 151);
//                Assert.assertEquals(variables.getMessageText(), "Look " + variables.getTypeUnderStage());
//                break;
//            }
//        }
//
//        Assert.assertEquals(variables.getActionType(), "message");
//        Assert.assertTrue(variables.isSuccess());
//
//        switch (variables.getMessageId()) {
//            case 152: {
//                while (!variables.getActionType().equalsIgnoreCase("stage")) {
//                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barRight.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//                }
//                break;
//            }
//            case 151: {
//                while (!variables.getActionType().equalsIgnoreCase("stage")) {
//                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barLeft.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//                }
//                break;
//            }
//        }
//
//        Assert.assertEquals(variables.getActionType(), "stage");
//        Assert.assertEquals(variables.getOrder(), 3);
//        Assert.assertEquals(variables.getTypeUnderStage(), "center");
//        Assert.assertEquals(variables.getStatus(), "success");
//        Assert.assertTrue(variables.isSuccess());
//
//        if (variables.getTypeUnderStage().equalsIgnoreCase("center")) {
//            while (!variables.getActionType().equalsIgnoreCase("message")) {
//                apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//            }
//        }
//
//        Assert.assertEquals(variables.getActionType(), "message");
//        Assert.assertEquals(variables.getMessageId(), 153);
//        Assert.assertEquals(variables.getMessageText(), "Look straight");
//        Assert.assertTrue(variables.isSuccess());
//
//        while (!variables.getActionType().equalsIgnoreCase("stage")) {
//            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//        }
//
//        Assert.assertEquals(variables.getActionType(), "stage");
//        Assert.assertEquals(variables.getOrder(), 4);
//        switch (variables.getTypeUnderStage()) {
//            case "left": {
//                Assert.assertEquals(variables.getTypeUnderStage(), "left");
//                Assert.assertEquals(variables.getTypeUnderStage(), variables.getStage4RightOrLeft());
//                break;
//            }
//            case "right": {
//                Assert.assertEquals(variables.getTypeUnderStage(), "right");
//                Assert.assertEquals(variables.getTypeUnderStage(), variables.getStage4RightOrLeft());
//                break;
//            }
//        }
//        Assert.assertEquals(variables.getStatus(), "success");
//        Assert.assertTrue(variables.isSuccess());
//
//        while (!variables.getActionType().equalsIgnoreCase("message")) {
//            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//        }
//
//        Assert.assertEquals(variables.getActionType(), "message");
//        Assert.assertTrue(variables.isSuccess());
//        switch (variables.getMessageId()) {
//            case 152: {
//                Assert.assertEquals(variables.getMessageId(), 152);
//                Assert.assertEquals(variables.getMessageText(), "Look right");
//                while (!variables.getActionType().equalsIgnoreCase("stage")) {
//                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barRight.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//                }
//                break;
//            }
//            case 151: {
//                Assert.assertEquals(variables.getMessageId(), 151);
//                Assert.assertEquals(variables.getMessageText(), "Look left");
//                while (!variables.getActionType().equalsIgnoreCase("stage")) {
//                    apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barLeft.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//                }
//                break;
//            }
//        }
//
//        Assert.assertEquals(variables.getActionType(), "stage");
//        Assert.assertEquals(variables.getOrder(), 5);
//        Assert.assertEquals(variables.getTypeUnderStage(), "otp");
//        Assert.assertTrue(variables.getOtpNum()>0);
//        Assert.assertEquals(variables.getStatus(), "success");
//        Assert.assertTrue(variables.isSuccess());
//
//        while (variables.getOrder() != 6) {
//            if (iterationCounter == 1) {
//                apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/otp/otpAudioChunks/beneficiary_chunk.wavaa"), apiResponse.CLIENT_REQUEST_LIVENESS);
//            }
//            apiResponse.clientResponseLivenessOtpStage(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "json_obj", "{\"stageName\":\"otp\",\"stageOrdinal\":5}", "message_type", "report_stage_ending", apiResponse.CLIENT_REQUEST_LIVENESS);
//            Thread.sleep(1500);
//            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/otp/otpAudioChunks/beneficiary_chunk.wavab"), apiResponse.CLIENT_REQUEST_LIVENESS);
//            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//
//            if (variables.getActionType().equalsIgnoreCase("stage")) {
//                break;
//            }
//            iterationCounter++;
//        }
//
//        Assert.assertEquals(variables.getActionType(), "stage");
//        Assert.assertEquals(variables.getOrder(), 6);
//        Assert.assertEquals(variables.getTypeUnderStage(), "stt");
//        Assert.assertEquals(variables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
//        Assert.assertEquals(variables.getStatus(), "success");
//        Assert.assertTrue(variables.isSuccess());
//        iterationCounter = 1;
//
//        while (!variables.getActionType().equalsIgnoreCase("complete")) {
//            if (iterationCounter == 1) {
//                apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/otp/otpAudioChunks/beneficiary_chunk.wavaa"), apiResponse.CLIENT_REQUEST_LIVENESS);
//            }
//            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavab"), apiResponse.CLIENT_REQUEST_LIVENESS);
//            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavac"), apiResponse.CLIENT_REQUEST_LIVENESS);
//            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavad"), apiResponse.CLIENT_REQUEST_LIVENESS);
//            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavae"), apiResponse.CLIENT_REQUEST_LIVENESS);
//            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavaf"), apiResponse.CLIENT_REQUEST_LIVENESS);
//            apiResponse.clientResponseLivenessOtpStage(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "json_obj", "{\"stageName\":\"stt\",\"stageOrdinal\":6}", "message_type", "report_stage_ending", apiResponse.CLIENT_REQUEST_LIVENESS);
//            Thread.sleep(1500);
//            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk/beneficiary_chunk.wavad"), apiResponse.CLIENT_REQUEST_LIVENESS);
//            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//
//            if (apiResponse.clientRequest.getBody().asString().contains("\"action_type\":\"complete\"")) {
//                apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//            }
//            iterationCounter++;
//        }
//
//        Assert.assertEquals(variables.getActionType(), "complete");
//        Assert.assertEquals(variables.getStatus(), "failure");
//
//        //Passive stage
//        Assert.assertEquals(variables.getActionType1(), "stage");
//        Assert.assertEquals(variables.getOrder1(), 1);
//        Assert.assertEquals(variables.getType1(), "passive");
//        Assert.assertEquals(variables.getHeadPositionType(), "center");
//        Assert.assertTrue(variables.isHeadPositionExpected());
//        Assert.assertEquals(variables.getThreshold(), 0.1);
//        Assert.assertTrue(Math.abs(variables.getLivenessScore() - 0.9458342790603638) < 0.01);
////        Assert.assertEquals(variables.getLivenessScore(), (float) 0.9458342790603638);
//        Assert.assertNotNull(variables.getFaceImage());
//        Assert.assertNotNull(variables.getInputImage());
//        Assert.assertEquals(variables.getStageStatus(), "success");
//
//        Assert.assertEquals(variables.getActionType3(), "stage");
//        Assert.assertEquals(variables.getOrder3(), 2);
//        Assert.assertEquals(variables.getType3(), variables.getStage2RightOrLeft());
//        Assert.assertNotNull(variables.getHeadPositionType1());
//        Assert.assertEquals(variables.getHeadPositionType1(), variables.getType3());
//        Assert.assertTrue(variables.isHeadPositionExpected1());
//        Assert.assertEquals(variables.getStageStatus3(), "success");
//
//        Assert.assertEquals(variables.getActionType3(), "stage");
//        Assert.assertEquals(variables.getOrder4(), 3);
//        Assert.assertEquals(variables.getType4(), "center");
//        Assert.assertNotNull(variables.getHeadPositionType2());
//        Assert.assertEquals(variables.getHeadPositionType2(), variables.getType4());
//        Assert.assertTrue(variables.isHeadPositionExpected2());
//        Assert.assertEquals(variables.getStageStatus4(), "success");
//
//        Assert.assertEquals(variables.getActionType3(), "stage");
//        Assert.assertEquals(variables.getOrder6(), 4);
//        Assert.assertEquals(variables.getType6(), variables.getStage4RightOrLeft());
//        Assert.assertNotNull(variables.getHeadPositionType3());
//        Assert.assertEquals(variables.getHeadPositionType3(), variables.getType6());
//        Assert.assertTrue(variables.isHeadPositionExpected3());
//        Assert.assertEquals(variables.getStageStatus6(), "success");
//
//        Assert.assertEquals(variables.getActionType5(), "stage");
//        Assert.assertEquals(variables.getOrder5(), 5);
//        Assert.assertEquals(variables.getType5(), "otp");
//        Assert.assertTrue(variables.getOtpNum()>0);
//        Assert.assertEquals(variables.getStageStatus5(), "success");
//
//        Assert.assertEquals(variables.getActionType2(), "complete");
//        Assert.assertEquals(variables.getOrder2(), 6);
//        Assert.assertEquals(variables.getType2(), "stt");
//        Assert.assertEquals(variables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
//        Assert.assertEquals(variables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
//        Assert.assertEquals(variables.getThreshold2(), 0.7);
//        Assert.assertEquals(variables.getLanguage(), "he-IL");
//        Assert.assertEquals(variables.getScore(), (float) 0.9230769);
//        Assert.assertEquals(variables.getStageStatus5(), "success");
//        Assert.assertTrue(variables.getScore() > variables.getThreshold2());
//
//        Assert.assertNotNull(variables.getCaseId());
//        Assert.assertEquals(variables.getConfigFileName(), "qa_config.json");
//        Assert.assertTrue(variables.isSuccess());
//
//        // Mongo Testing ****************************************************************
//        if (System.getProperty("mongoCheck", "false").equals("false")) {
//            return;
//        }
//        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
//        Assert.assertNotNull(StartSessionVariables.getObjectId());
//        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
//        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
//        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
//        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
//        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");
//
//        if (System.getProperty("mongoCheck", "false").equals("false")) {
//            return;
//        }
//        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
//        Assert.assertNotNull(CommonVariables.getObjectId());
//        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
//        Assert.assertEquals(CommonVariables.getConfigFilename(), "qa_config.json");
//        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
//        Assert.assertEquals(CommonVariables.getStatus(), "failure");
////        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
//        Assert.assertTrue(CommonVariables.isSuccess());
////        Assert.assertNotNull(CommonVariables.getVideo());
//        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");
//
//        //Stage 1
//        Assert.assertEquals(PassiveVariables.getActionType2(), "stage");
//        Assert.assertEquals(PassiveVariables.getOrder2(), 1);
//        Assert.assertEquals(PassiveVariables.getTypeUnderStage2(), "passive");
//        Assert.assertEquals(PassiveVariables.getHeadPositionType(), "center");
//        Assert.assertTrue(PassiveVariables.isHeadPositionExpected());
//        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
//        Assert.assertTrue(Math.abs(PassiveVariables.getScore() - 0.9458342790603638) < 0.01);
//        Assert.assertNotNull(PassiveVariables.getFaceImage());
//        Assert.assertNotNull(PassiveVariables.getInputImage());
//        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");
//
//        //Stage 2
//        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
//        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
//        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");
//        switch (variables.getStage2RightOrLeft()) {
//            case "left": {
//                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "left");
//                break;
//            }
//            case "right": {
//                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "right");
//                break;
//            }
//        }
//        Assert.assertNotNull(LeftRightCenterVariables.getHeadPositionType());
//        Assert.assertEquals(LeftRightCenterVariables.getHeadPositionType(), LeftRightCenterVariables.getTypeUnderStage3());
//        Assert.assertTrue(LeftRightCenterVariables.isHeadPositionExpected());
//
//        //Stage 3
//        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
//        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
//        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
//        Assert.assertNotNull(LeftRightCenterVariables.getHeadPositionType1());
//        Assert.assertEquals(LeftRightCenterVariables.getHeadPositionType1(), "center");
//        Assert.assertTrue(LeftRightCenterVariables.isHeadPositionExpected1());
//        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");
//
//        //Stage 4
//        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "stage");
//        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
//        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");
//        switch (variables.getStage4RightOrLeft()) {
//            case "left": {
//                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "left");
//                break;
//            }
//            case "right": {
//                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "right");
//                break;
//            }
//        }
//        Assert.assertNotNull(LeftRightCenterVariables.getHeadPositionType2());
//        Assert.assertEquals(LeftRightCenterVariables.getHeadPositionType2(), LeftRightCenterVariables.getTypeUnderStage7());
//        Assert.assertTrue(LeftRightCenterVariables.isHeadPositionExpected2());
//
//        //Otp
//        Assert.assertEquals(OtpVariables.getActionType5(), "stage");
//        Assert.assertEquals(OtpVariables.getOrder5(), 5);
//        Assert.assertEquals(OtpVariables.getTypeUnderStage5(), "otp");
//        Assert.assertTrue(OtpVariables.getOtpNumber()>0);
//        Assert.assertEquals(OtpVariables.getStageStatus5(), "success");
//
//        //Stt
//        Assert.assertEquals(SttVariables.getActionType6(), "complete");
//        Assert.assertEquals(SttVariables.getOrder6(), 6);
//        Assert.assertEquals(SttVariables.getTypeUnderStage6(), "stt");
//        Assert.assertEquals(SttVariables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
//        Assert.assertEquals(SttVariables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
//        Assert.assertEquals(SttVariables.getThreshold(), 0.7);
//        Assert.assertEquals(SttVariables.getLanguage(), "he-IL");
//        Assert.assertEquals(SttVariables.getScore(), 0.9230769230769231);
//        Assert.assertEquals(SttVariables.getStageStatus6(), "success");
//
//        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(enabled = false, description = "stt threshold value set via the mathilda file")
    @Description("stt liveness threshold value set via the config file (Mathilda.json) and not via the docker environment variable")
    public void t01_sttThresholdValueSetViaMathildaFile() throws InterruptedException, IOException {
//        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "LIVENESS", "flowConfigName", "changeSttThreshold.json", apiResponse.CLIENT_INIT_LIVENESS);
//        apiResponse.verifyInitVariables(variables);
//        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//
//        while (!variables.getActionType().equalsIgnoreCase("stage")) {
//            apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//        }
//
//        Assert.assertEquals(variables.getActionType(), "stage");
//        Assert.assertEquals(variables.getOrder(), 2);
//        Assert.assertEquals(variables.getTypeUnderStage(), "stt");
//        Assert.assertEquals(variables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
//        Assert.assertEquals(variables.getStatus(), "success");
//        Assert.assertTrue(variables.isSuccess());
//
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
//
//        Assert.assertEquals(variables.getActionType(), "complete");
//        Assert.assertEquals(variables.getStatus(), "failure");
//
//        Assert.assertEquals(variables.getThreshold2(), sttThresholdFromConfigFile);
//        // Mongo Testing ****************************************************************
//        if (System.getProperty("mongoCheck", "false").equals("false")) {
//            return;
//        }
//        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
//        Assert.assertEquals(SttVariables.getThreshold(), sttThresholdFromConfigFile);
    }

    @Test(description = "active liveness timeout session at otp stage", enabled = false)
    @Description("Demo active liveness timeout session at otp stage")
    public void t17_activeLivenessTimeoutAtOtpStage() throws IOException, InterruptedException {
//        mainPage.chooseConfigFile("qa_config1.json");
//        mainPage.chooseFromServicesList("Liveness");
//        Injection injection = new Injection(driver);
//        injection.livenessInjection2("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/barCenter.jpg", "./liveness/apiPic/barRight.jpg");
//
//        ResultsPage resultsPage = new ResultsPage(driver);
//        resultsPage.collectResults(variables);
//
//        Assert.assertEquals(resultsPage.verifyListsSizes(), 10);
//
//        Assert.assertEquals(variables.getActionType(), "complete");
//        Assert.assertEquals(variables.getStatus(), "timeout");
//        Assert.assertNotNull(variables.getCaseId());
//        Assert.assertEquals(variables.getConfigFileName(), "qa_config1.json");
//        Assert.assertTrue(variables.isSuccess());
//
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "threshold"), "threshold\n" + "0.1");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "score"), "score\n" + "0.9221184849739075");
//        Assert.assertEquals(variables.getFaceImage(), "face_image");
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "last_received_image"), "last_received_image");
//
//        Assert.assertTrue(resultsPage.verifyImagesAreReal());
//
//        // Mongo Testing ****************************************************************
//        if (System.getProperty("mongoCheck", "false").equals("false")) {
//            return;
//        }
//        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
//        Assert.assertNotNull(StartSessionVariables.getObjectId());
//        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
//        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
//        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
//        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
//        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");
//
//        if (System.getProperty("mongoCheck", "false").equals("false")) {
//            return;
//        }
//        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
//
//        Assert.assertNotNull(CommonVariables.getObjectId());
//        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
//        Assert.assertEquals(CommonVariables.getConfigFilename(), "qa_config.json");
//        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
//        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
//        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
//        Assert.assertTrue(CommonVariables.isSuccess());
//        Assert.assertNotNull(CommonVariables.getVideo());
//        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");
//
//        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
//        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
//        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
//        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
//        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
//        Assert.assertNotNull(PassiveVariables.getFaceImage());
//        Assert.assertNotNull(PassiveVariables.getInputImage());
//        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");
//
//        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
//        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
//        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage3());
//        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");
//
//        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
//        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
//        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
//        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");
//
//        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "stage");
//        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
//        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage7());
//        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");
//
//        Assert.assertEquals(OtpVariables.getActionTypeTimeout(), "stage");
//        Assert.assertEquals(OtpVariables.getOrderTimeout(), 5);
//        Assert.assertEquals(OtpVariables.getTypeUnderStageTimeout(), "otp");
//        Assert.assertEquals(OtpVariables.getStageStatusTimeout(), "timeout");
//        Assert.assertNotNull(OtpVariables.getLastReceivedImage());
//
//        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "active liveness sanity demo with STT", enabled = false)
    @Description("Demo of active liveness sanity test by injecting straight, left & right face images, and then injecting audio for the otp and stt parts")
    public void t02_livenessActiveSanity() throws InterruptedException, IOException {
//        driver.get(LIVENESS_DEMO);
//        MainPage mainPage = new MainPage(driver);
//        mainPage.chooseConfigFile("qa_config.json");
//        mainPage.chooseFromServicesList("Liveness");
//        Injection injection = new Injection(driver);
//        injection.livenessInjection2("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/barCenter.jpg", "./liveness/apiPic/barRight.jpg");
//        injection.otpInjection("./liveness/audio/otp/otpAudioChunks");
//        injection.sttInjection("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk");
//
//        ResultsPage resultsPage = new ResultsPage(driver);
//        resultsPage.collectResults(variables);
//
//        Assert.assertEquals(resultsPage.verifyListsSizes(), 15);
//
//        Assert.assertEquals(variables.getActionType(), "complete");
//        Assert.assertEquals(variables.getStatus(), "failure");
//        Assert.assertNotNull(variables.getCaseId());
//        Assert.assertEquals(variables.getConfigFileName(), "qa_config.json");
//        Assert.assertTrue(variables.isSuccess());
//
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "threshold\n" + "0.1"), "threshold\n" + "0.1");
//        Assert.assertEquals(variables.getDblScore(),0.9221184849739075);
////        Assert.assertEquals(resultsPage.valueToCheck(variables, "score\n" + "0.9221184849739075"), "score\n" + "0.9221184849739075");
//        Assert.assertEquals(variables.getFaceImage(), "face_image");
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "otp_number"), "otp_number\n" + variables.getOtpNum());
//
//        Assert.assertNotNull(resultsPage.valueToCheck(variables, "stt_text"));
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "original_text"), "original_text\n" + "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "threshold\n" + "0.7"), "threshold\n" + "0.7");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "language\n"), "language\n" + "he-IL");
//        Assert.assertNotNull(resultsPage.valueToCheck(variables, "score"));
//        Assert.assertTrue(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]) > Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));
//        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]));
//        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));
//
//        Assert.assertTrue(resultsPage.verifyImagesAreReal());
//
//        // Mongo Testing ****************************************************************
//        if (System.getProperty("mongoCheck", "false").equals("false")) {
//            return;
//        }
//        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
//        Assert.assertNotNull(StartSessionVariables.getObjectId());
//        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
//        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
//        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
//        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
//        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");
//
//        if (System.getProperty("mongoCheck", "false").equals("false")) {
//            return;
//        }
//        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
//
//        Assert.assertNotNull(CommonVariables.getObjectId());
//        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
//        Assert.assertEquals(CommonVariables.getConfigFilename(), "qa_config.json");
//        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
//        Assert.assertEquals(CommonVariables.getStatus(), "failure");
//        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
//        Assert.assertTrue(CommonVariables.isSuccess());
//        Assert.assertNotNull(CommonVariables.getVideo());
//        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");
//
//        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
//        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
//        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
//        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
//        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
//        Assert.assertNotNull(PassiveVariables.getFaceImage());
//        Assert.assertNotNull(PassiveVariables.getInputImage());
//        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");
//
//        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
//        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
//        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage3());
//        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");
//
//        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
//        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
//        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
//        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");
//
//        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "stage");
//        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
//        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage7());
//        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");
//
//        Assert.assertEquals(OtpVariables.getActionType5(), "stage");
//        Assert.assertEquals(OtpVariables.getOrder5(), 5);
//        Assert.assertEquals(OtpVariables.getTypeUnderStage5(), "otp");
//        Assert.assertEquals(OtpVariables.getOtpNumber(), 1111);
//        Assert.assertEquals(OtpVariables.getStageStatus5(), "success");
//
//        Assert.assertEquals(SttVariables.getActionType6(), "complete");
//        Assert.assertEquals(SttVariables.getOrder6(), 6);
//        Assert.assertEquals(SttVariables.getTypeUnderStage6(), "stt");
//        Assert.assertEquals(SttVariables.getSttText(), "החשבון החשבון יהיה בבעלותי ולא יהיו בו נהנים");
//        Assert.assertEquals(SttVariables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
//        Assert.assertEquals(SttVariables.getThreshold(), 0.7);
//        Assert.assertEquals(SttVariables.getLanguage(), "he-IL");
//        Assert.assertEquals(SttVariables.getScore(), 0.8470588235294118);
//        Assert.assertEquals(SttVariables.getStageStatus6(), "success");
//
//        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "passive liveness with stt positive session test", enabled = false)
    @Description("Passive liveness positive injection folder of full session images")
    public void t02_e2ePositivePassiveLivenessWithSttSessionTest() throws IOException, InterruptedException {
//        injection.injectFolder("./ocr/sessions/liveness/passive/1", null);
//        injection.sttInjection("./liveness/audio/stt/fullStt");
//
//        ResultsPage resultsPage = new ResultsPage(driver);
//        resultsPage.collectResults(variables);
//
//        Assert.assertEquals(resultsPage.verifyListsSizes(), 14);
//
//        Assert.assertEquals(variables.getActionType(), "complete");
//        Assert.assertEquals(variables.getStatus(), "failure");
//        Assert.assertNotNull(variables.getCaseId());
//        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
//        Assert.assertTrue(variables.isSuccess());
//
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "threshold\n" + "0.1"), "threshold\n" + "0.1");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "score\n" + "0.959386944770813"), "score\n" + "0.959386944770813");
//        Assert.assertEquals(variables.getFaceImage(), "face_image");
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
//
//        Assert.assertNotNull(resultsPage.valueToCheck(variables, "stt_text"));
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "original_text"), "original_text\n" + "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "threshold\n" + "0.7"), "threshold\n" + "0.7");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "language\n"), "language\n" + "he-IL");
//        Assert.assertNotNull(resultsPage.valueToCheck(variables, "score"));
//        Assert.assertTrue(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]) > Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));
//        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]));
//        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));
//
//        Assert.assertTrue(resultsPage.verifyImagesAreReal());
//
//        // Mongo Testing ****************************************************************
//        if (System.getProperty("mongoCheck", "false").equals("false")) {
//            return;
//        }
//        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
//        Assert.assertNotNull(StartSessionVariables.getObjectId());
//        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
//        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
//        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
//        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
//        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");
//
//        if (System.getProperty("mongoCheck", "false").equals("false")) {
//            return;
//        }
//        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
//
//        Assert.assertNotNull(CommonVariables.getObjectId());
//        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
//        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
//        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
//        Assert.assertEquals(CommonVariables.getStatus(), "failure");
//        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
//        Assert.assertTrue(CommonVariables.isSuccess());
//        Assert.assertNotNull(CommonVariables.getVideo());
//        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");
//
//        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
//        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
//        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
//        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
//        Assert.assertEquals(PassiveVariables.getScore(), 0.959386944770813);
//        Assert.assertNotNull(PassiveVariables.getFaceImage());
//        Assert.assertNotNull(PassiveVariables.getInputImage());
//        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");
//
//        Assert.assertEquals(SttVariables.getActionType6(), "complete");
//        Assert.assertEquals(SttVariables.getOrder6(), 2);
//        Assert.assertEquals(SttVariables.getTypeUnderStage6(), "stt");
//        Assert.assertEquals(SttVariables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
//        Assert.assertEquals(SttVariables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
//        Assert.assertEquals(SttVariables.getThreshold(), 0.7);
//        Assert.assertEquals(SttVariables.getLanguage(), "he-IL");
//        Assert.assertEquals(SttVariables.getScore(), 0.9230769230769231);
//        Assert.assertEquals(SttVariables.getStageStatus6(), "success");
//
//        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
//    }
    }

    @Test(description = "passive liveness with stt positive session test #2", enabled = false)
    @Description("Passive liveness positive injection folder of full session images #2")
    public void t04_e2ePositivePassiveLivenessWithSttSessionTest() throws IOException, InterruptedException {
//        injection.injectFolder("./ocr/sessions/liveness/passive/2", null);
//        injection.sttInjection("./liveness/audio/stt/fullStt");
//        ResultsPage resultsPage = new ResultsPage(driver);
//        resultsPage.collectResults(variables);
//
//        Assert.assertEquals(resultsPage.verifyListsSizes(), 14);
//
//        Assert.assertEquals(variables.getActionType(), "complete");
//        Assert.assertEquals(variables.getStatus(), "failure");
//        Assert.assertNotNull(variables.getCaseId());
//        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
//        Assert.assertTrue(variables.isSuccess());
//
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "threshold\n" + "0.1"), "threshold\n" + "0.1");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "score\n" + "0.9684814810752869"), "score\n" + "0.9684814810752869");
//        Assert.assertEquals(variables.getFaceImage(), "face_image");
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
//
//        Assert.assertNotNull(resultsPage.valueToCheck(variables, "stt_text"));
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "original_text"), "original_text\n" + "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "threshold\n" + "0.7"), "threshold\n" + "0.7");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "language\n"), "language\n" + "he-IL");
//        Assert.assertNotNull(resultsPage.valueToCheck(variables, "score"));
//        Assert.assertTrue(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]) > Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));
//        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]));
//        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));
//
//        Assert.assertTrue(resultsPage.verifyImagesAreReal());
//
//        // Mongo Testing ****************************************************************
//        if (System.getProperty("mongoCheck", "false").equals("false")) {
//            return;
//        }
//        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
//        Assert.assertNotNull(StartSessionVariables.getObjectId());
//        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
//        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
//        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
//        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
//        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");
//
//        if (System.getProperty("mongoCheck", "false").equals("false")) {
//            return;
//        }
//        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
//
//        Assert.assertNotNull(CommonVariables.getObjectId());
//        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
//        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
//        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
//        Assert.assertEquals(CommonVariables.getStatus(), "failure");
//        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
//        Assert.assertTrue(CommonVariables.isSuccess());
//        Assert.assertNotNull(CommonVariables.getVideo());
//        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");
//
//        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
//        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
//        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
//        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
//        Assert.assertEquals(PassiveVariables.getScore(), 0.9684814810752869);
//        Assert.assertNotNull(PassiveVariables.getFaceImage());
//        Assert.assertNotNull(PassiveVariables.getInputImage());
//        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");
//
//        Assert.assertEquals(SttVariables.getActionType6(), "complete");
//        Assert.assertEquals(SttVariables.getOrder6(), 2);
//        Assert.assertEquals(SttVariables.getTypeUnderStage6(), "stt");
//        Assert.assertEquals(SttVariables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
//        Assert.assertEquals(SttVariables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
//        Assert.assertEquals(SttVariables.getThreshold(), 0.7);
//        Assert.assertEquals(SttVariables.getLanguage(), "he-IL");
//        Assert.assertEquals(SttVariables.getScore(), 0.9230769230769231);
//        Assert.assertEquals(SttVariables.getStageStatus6(), "success");
//
//        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "passive liveness positive session test #3", enabled = false)
    @Description("Passive liveness with stt positive injection folder of full session images #3")
    public void t06_e2ePositivePassiveLivenessWithSttSessionTest() throws IOException, InterruptedException {
//        injection.injectFolder("./ocr/sessions/liveness/passive/3", null);
//        injection.sttInjection("./liveness/audio/stt/fullStt");
//        ResultsPage resultsPage = new ResultsPage(driver);
//        resultsPage.collectResults(variables);
//
//        Assert.assertEquals(resultsPage.verifyListsSizes(), 14);
//
//        Assert.assertEquals(variables.getActionType(), "complete");
//        Assert.assertEquals(variables.getStatus(), variables.getStatus());
//        Assert.assertNotNull(variables.getCaseId());
//        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
//        Assert.assertTrue(variables.isSuccess());
//
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "threshold\n" + "0.1"), "threshold\n" + "0.1");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "score\n" + "0.45624563097953796"), "score\n" + "0.45624563097953796");
//        Assert.assertEquals(variables.getFaceImage(), "face_image");
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
//
//        Assert.assertNotNull(resultsPage.valueToCheck(variables, "stt_text"));
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "original_text"), "original_text\n" + "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "threshold\n" + "0.7"), "threshold\n" + "0.7");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "language\n"), "language\n" + "he-IL");
//        Assert.assertNotNull(resultsPage.valueToCheck(variables, "score"));
//        Assert.assertTrue(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]) > Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));
//        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]));
//        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));
//
//        Assert.assertTrue(resultsPage.verifyImagesAreReal());
//
//        // Mongo Testing ****************************************************************
//        if (System.getProperty("mongoCheck", "false").equals("false")) {
//            return;
//        }
//        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
//        Assert.assertNotNull(StartSessionVariables.getObjectId());
//        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
//        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
//        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
//        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
//        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");
//
//        if (System.getProperty("mongoCheck", "false").equals("false")) {
//            return;
//        }
//        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
//
//        Assert.assertNotNull(CommonVariables.getObjectId());
//        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
//        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
//        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
//        Assert.assertEquals(CommonVariables.getStatus(), "failure");
//        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
//        Assert.assertTrue(CommonVariables.isSuccess());
//        Assert.assertNotNull(CommonVariables.getVideo());
//        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");
//
//        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
//        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
//        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
//        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
//        Assert.assertEquals(PassiveVariables.getScore(), 0.45624563097953796);
//        Assert.assertNotNull(PassiveVariables.getFaceImage());
//        Assert.assertNotNull(PassiveVariables.getInputImage());
//        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");
//
//        Assert.assertEquals(SttVariables.getActionType6(), "complete");
//        Assert.assertEquals(SttVariables.getOrder6(), 2);
//        Assert.assertEquals(SttVariables.getTypeUnderStage6(), "stt");
//        Assert.assertEquals(SttVariables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
//        Assert.assertEquals(SttVariables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
//        Assert.assertEquals(SttVariables.getThreshold(), 0.7);
//        Assert.assertEquals(SttVariables.getLanguage(), "he-IL");
//        Assert.assertEquals(SttVariables.getScore(), 0.9230769230769231);
//        Assert.assertEquals(SttVariables.getStageStatus6(), "success");
//
//        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
//
    }
    @Test(description = "passive liveness with stt  positive session test #4", enabled = false)
    @Description("Passive liveness positive injection folder of full session images #4")
    public void t08_e2ePositivePassiveLivenessWithSttSessionTest() throws IOException, InterruptedException {
//        injection.injectFolder("./ocr/sessions/liveness/passive/4", null);
//        injection.sttInjection("./liveness/audio/stt/fullStt");
//        ResultsPage resultsPage = new ResultsPage(driver);
//        resultsPage.collectResults(variables);
//
//        Assert.assertEquals(resultsPage.verifyListsSizes(), 14);
//
//        Assert.assertEquals(variables.getActionType(), "complete");
//        Assert.assertEquals(variables.getStatus(), variables.getStatus());
//        Assert.assertNotNull(variables.getCaseId());
//        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
//        Assert.assertTrue(variables.isSuccess());
//
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "threshold\n" + "0.1"), "threshold\n" + "0.1");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "score"), "score\n" + "0.9230769230769231");
//        Assert.assertEquals(variables.getFaceImage(), "face_image");
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
//
//        Assert.assertNotNull(resultsPage.valueToCheck(variables, "stt_text"));
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "original_text"), "original_text\n" + "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "threshold\n" + "0.7"), "threshold\n" + "0.7");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "language\n"), "language\n" + "he-IL");
//        Assert.assertNotNull(resultsPage.valueToCheck(variables, "score"));
//        Assert.assertTrue(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]) > Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));
//        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]));
//        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));
//
//        Assert.assertTrue(resultsPage.verifyImagesAreReal());
//
//        // Mongo Testing ****************************************************************
//        if (System.getProperty("mongoCheck", "false").equals("false")) {
//            return;
//        }
//        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
//        Assert.assertNotNull(StartSessionVariables.getObjectId());
//        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
//        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
//        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
//        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
//        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");
//
//        if (System.getProperty("mongoCheck", "false").equals("false")) {
//            return;
//        }
//        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
//
//        Assert.assertNotNull(CommonVariables.getObjectId());
//        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
//        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
//        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
//        Assert.assertEquals(CommonVariables.getStatus(), "failure");
//        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
//        Assert.assertTrue(CommonVariables.isSuccess());
//        Assert.assertNotNull(CommonVariables.getVideo());
//        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");
//
//        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
//        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
//        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
//        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
//        Assert.assertEquals(PassiveVariables.getScore(), 0.8701340556144714);
//        Assert.assertNotNull(PassiveVariables.getFaceImage());
//        Assert.assertNotNull(PassiveVariables.getInputImage());
//        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");
//
//        Assert.assertEquals(SttVariables.getActionType6(), "complete");
//        Assert.assertEquals(SttVariables.getOrder6(), 2);
//        Assert.assertEquals(SttVariables.getTypeUnderStage6(), "stt");
//        Assert.assertEquals(SttVariables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
//        Assert.assertEquals(SttVariables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
//        Assert.assertEquals(SttVariables.getThreshold(), 0.7);
//        Assert.assertEquals(SttVariables.getLanguage(), "he-IL");
//        Assert.assertEquals(SttVariables.getScore(), 0.9230769230769231);
//        Assert.assertEquals(SttVariables.getStageStatus6(), "success");
//
//        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }
    @Test(description = "passive liveness with stt positive session test #5", enabled = false)
    @Description("Passive liveness positive injection folder of full session images #5")
    public void t10_e2ePositivePassiveLivenessWithSttSessionTest() throws IOException, InterruptedException {
//        injection.injectFolder("./ocr/sessions/liveness/passive/5", null);
//        injection.sttInjection("./liveness/audio/stt/fullStt");
//        ResultsPage resultsPage = new ResultsPage(driver);
//        resultsPage.collectResults(variables);
//
//        Assert.assertEquals(resultsPage.verifyListsSizes(), 14);
//
//        Assert.assertEquals(variables.getActionType(), "complete");
//        Assert.assertEquals(variables.getStatus(), variables.getStatus());
//        Assert.assertNotNull(variables.getCaseId());
//        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
//        Assert.assertTrue(variables.isSuccess());
//
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "threshold\n" + "0.1"), "threshold\n" + "0.1");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "score"), "score\n" + "0.9230769230769231");
//        Assert.assertEquals(variables.getFaceImage(), "face_image");
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
//
//        Assert.assertNotNull(resultsPage.valueToCheck(variables, "stt_text"));
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "original_text"), "original_text\n" + "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "threshold\n" + "0.7"), "threshold\n" + "0.7");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "language\n"), "language\n" + "he-IL");
//        Assert.assertNotNull(resultsPage.valueToCheck(variables, "score"));
//        Assert.assertTrue(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]) > Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));
//        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]));
//        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));
//
//        Assert.assertTrue(resultsPage.verifyImagesAreReal());
//
//        // Mongo Testing ****************************************************************
//        if (System.getProperty("mongoCheck", "false").equals("false")) {
//            return;
//        }
//        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
//        Assert.assertNotNull(StartSessionVariables.getObjectId());
//        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
//        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
//        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
//        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
//        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");
//
//        if (System.getProperty("mongoCheck", "false").equals("false")) {
//            return;
//        }
//        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
//
//        Assert.assertNotNull(CommonVariables.getObjectId());
//        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
//        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
//        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
//        Assert.assertEquals(CommonVariables.getStatus(), "failure");
//        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
//        Assert.assertTrue(CommonVariables.isSuccess());
//        Assert.assertNotNull(CommonVariables.getVideo());
//        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");
//
//        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
//        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
//        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
//        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
//        Assert.assertEquals(PassiveVariables.getScore(), 0.9822120070457458);
//        Assert.assertNotNull(PassiveVariables.getFaceImage());
//        Assert.assertNotNull(PassiveVariables.getInputImage());
//        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");
//
//        Assert.assertEquals(SttVariables.getActionType6(), "complete");
//        Assert.assertEquals(SttVariables.getOrder6(), 2);
//        Assert.assertEquals(SttVariables.getTypeUnderStage6(), "stt");
//        Assert.assertEquals(SttVariables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
//        Assert.assertEquals(SttVariables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
//        Assert.assertEquals(SttVariables.getThreshold(), 0.7);
//        Assert.assertEquals(SttVariables.getLanguage(), "he-IL");
//        Assert.assertEquals(SttVariables.getScore(), 0.9230769230769231);
//        Assert.assertEquals(SttVariables.getStageStatus6(), "success");
//
//        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

}