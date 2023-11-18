package api.ocr.mathilda.mathildaTests;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.CNIVariables;
import utilities.MongoDB.Variables.Ocr.OcrHandlers;
import utilities.MongoDB.Variables.StartSessionVariables;

import java.io.File;
import java.io.IOException;
import utilities.TestUtils;
public class gesturesInCni {

    ApiResponse apiResponse = new ApiResponse();
    Variables variables = new Variables();
    MongoHandler mongoHandler = new MongoHandler();
    int iterationCounter = 1;

    @Test(description = "mathilda test cni with gestures")
    @Description("api mathilda test of cni with gestures stage in the end of the process")
    public void t01_cniWithGesturesStage() throws IOException, InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "CNI", "flowConfigName", "gesturesWithActive.json", apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_3.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        switch (variables.getActionType()) {
            case "stage": {
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getStatus(), "success");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getOrder()) {
                    case 2: {
                        Assert.assertEquals(variables.getOrder(), 2);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 3: {
                        Assert.assertEquals(variables.getOrder(), 3);
                        Assert.assertEquals(variables.getTypeUnderStage(), "back");
                        break;
                    }
                    case 4: {
                        Assert.assertEquals(variables.getOrder(), 4);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 5: {
                        Assert.assertEquals(variables.getOrder(), 5);
                        Assert.assertEquals(variables.getTypeUnderStage(), "otp");
                        Assert.assertTrue(variables.getOtpNum()>0);
                        break;
                    }
                }
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getOrder(), 2);
                Assert.assertEquals(variables.getTypeUnderStage(), "video");
                break;
            }
            case "message": {
                Assert.assertEquals(variables.getActionType(), "message");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getMessageId()) {
                    case 100: {
                        Assert.assertEquals(variables.getMessageId(), 100);
                        Assert.assertEquals(variables.getMessageText(), "Wrong side");
                        break;
                    }
                    case 103: {
                        Assert.assertEquals(variables.getMessageId(), 103);
                        Assert.assertEquals(variables.getMessageText(), "Card detected");
                        break;
                    }
                }
                break;
            }
        }

        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_3.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        switch (variables.getActionType()) {
            case "stage": {
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getStatus(), "success");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getOrder()) {
                    case 2: {
                        Assert.assertEquals(variables.getOrder(), 2);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 3: {
                        Assert.assertEquals(variables.getOrder(), 3);
                        Assert.assertEquals(variables.getTypeUnderStage(), "back");
                        break;
                    }
                    case 4: {
                        Assert.assertEquals(variables.getOrder(), 4);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 5: {
                        Assert.assertEquals(variables.getOrder(), 5);
                        Assert.assertEquals(variables.getTypeUnderStage(), "otp");
                        Assert.assertTrue(variables.getOtpNum()>0);
                        break;
                    }
                }
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getOrder(), 2);
                Assert.assertEquals(variables.getTypeUnderStage(), "video");
                break;
            }
            case "message": {
                Assert.assertEquals(variables.getActionType(), "message");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getMessageId()) {
                    case 100: {
                        Assert.assertEquals(variables.getMessageId(), 100);
                        Assert.assertEquals(variables.getMessageText(), "Wrong side");
                        break;
                    }
                    case 103: {
                        Assert.assertEquals(variables.getMessageId(), 103);
                        Assert.assertEquals(variables.getMessageText(), "Card detected");
                        break;
                    }
                }
                break;
            }
        }

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_3.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        switch (variables.getActionType()) {
            case "stage": {
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getStatus(), "success");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getOrder()) {
                    case 2: {
                        Assert.assertEquals(variables.getOrder(), 2);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 3: {
                        Assert.assertEquals(variables.getOrder(), 3);
                        Assert.assertEquals(variables.getTypeUnderStage(), "back");
                        break;
                    }
                    case 4: {
                        Assert.assertEquals(variables.getOrder(), 4);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 5: {
                        Assert.assertEquals(variables.getOrder(), 5);
                        Assert.assertEquals(variables.getTypeUnderStage(), "otp");
                        Assert.assertTrue(variables.getOtpNum()>0);
                        break;
                    }
                }
                break;
            }
            case "message": {
                Assert.assertEquals(variables.getActionType(), "message");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getMessageId()) {
                    case 100: {
                        Assert.assertEquals(variables.getMessageId(), 100);
                        Assert.assertEquals(variables.getMessageText(), "Wrong side");
                        break;
                    }
                    case 103: {
                        Assert.assertEquals(variables.getMessageId(), 103);
                        Assert.assertEquals(variables.getMessageText(), "Card detected");
                        break;
                    }
                }
                break;
            }
        }

        while (variables.getMessageId() != 100) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_3.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        switch (variables.getActionType()) {
            case "stage": {
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getStatus(), "success");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getOrder()) {
                    case 2: {
                        Assert.assertEquals(variables.getOrder(), 2);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 3: {
                        Assert.assertEquals(variables.getOrder(), 3);
                        Assert.assertEquals(variables.getTypeUnderStage(), "back");
                        break;
                    }
                    case 4: {
                        Assert.assertEquals(variables.getOrder(), 4);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 5: {
                        Assert.assertEquals(variables.getOrder(), 5);
                        Assert.assertEquals(variables.getTypeUnderStage(), "otp");
                        Assert.assertTrue(variables.getOtpNum()>0);
                        break;
                    }
                }
                break;
            }
            case "message": {
                Assert.assertEquals(variables.getActionType(), "message");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getMessageId()) {
                    case 100: {
                        Assert.assertEquals(variables.getMessageId(), 100);
                        Assert.assertEquals(variables.getMessageText(), "Wrong side");
                        break;
                    }
                    case 103: {
                        Assert.assertEquals(variables.getMessageId(), 103);
                        Assert.assertEquals(variables.getMessageText(), "Card detected");
                        break;
                    }
                }
                break;
            }
        }

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_back_1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        switch (variables.getActionType()) {
            case "stage": {
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getStatus(), "success");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getOrder()) {
                    case 2: {
                        Assert.assertEquals(variables.getOrder(), 2);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 3: {
                        Assert.assertEquals(variables.getOrder(), 3);
                        Assert.assertEquals(variables.getTypeUnderStage(), "back");
                        break;
                    }
                    case 4: {
                        Assert.assertEquals(variables.getOrder(), 4);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 5: {
                        Assert.assertEquals(variables.getOrder(), 5);
                        Assert.assertEquals(variables.getTypeUnderStage(), "otp");
                        Assert.assertTrue(variables.getOtpNum()>0);
                        break;
                    }
                }
                break;
            }
            case "message": {
                Assert.assertEquals(variables.getActionType(), "message");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getMessageId()) {
                    case 100: {
                        Assert.assertEquals(variables.getMessageId(), 100);
                        Assert.assertEquals(variables.getMessageText(), "Wrong side");
                        break;
                    }
                    case 103: {
                        Assert.assertEquals(variables.getMessageId(), 103);
                        Assert.assertEquals(variables.getMessageText(), "Card detected");
                        break;
                    }
                }
                break;
            }
        }

        while (variables.getMessageId() != 103) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_back_1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        switch (variables.getActionType()) {
            case "stage": {
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getStatus(), "success");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getOrder()) {
                    case 2: {
                        Assert.assertEquals(variables.getOrder(), 2);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 3: {
                        Assert.assertEquals(variables.getOrder(), 3);
                        Assert.assertEquals(variables.getTypeUnderStage(), "back");
                        break;
                    }
                    case 4: {
                        Assert.assertEquals(variables.getOrder(), 4);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 5: {
                        Assert.assertEquals(variables.getOrder(), 5);
                        Assert.assertEquals(variables.getTypeUnderStage(), "otp");
                        Assert.assertTrue(variables.getOtpNum()>0);
                        break;
                    }
                }
                break;
            }
            case "message": {
                Assert.assertEquals(variables.getActionType(), "message");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getMessageId()) {
                    case 100: {
                        Assert.assertEquals(variables.getMessageId(), 100);
                        Assert.assertEquals(variables.getMessageText(), "Wrong side");
                        break;
                    }
                    case 103: {
                        Assert.assertEquals(variables.getMessageId(), 103);
                        Assert.assertEquals(variables.getMessageText(), "Card detected");
                        break;
                    }
                }
                break;
            }
        }

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_back_1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        switch (variables.getActionType()) {
            case "stage": {
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getStatus(), "success");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getOrder()) {
                    case 2: {
                        Assert.assertEquals(variables.getOrder(), 2);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 3: {
                        Assert.assertEquals(variables.getOrder(), 3);
                        Assert.assertEquals(variables.getTypeUnderStage(), "back");
                        break;
                    }
                    case 4: {
                        Assert.assertEquals(variables.getOrder(), 4);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 5: {
                        Assert.assertEquals(variables.getOrder(), 5);
                        Assert.assertEquals(variables.getTypeUnderStage(), "otp");
                        Assert.assertTrue(variables.getOtpNum()>0);
                        break;
                    }
                }
                break;
            }
            case "message": {
                Assert.assertEquals(variables.getActionType(), "message");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getMessageId()) {
                    case 100: {
                        Assert.assertEquals(variables.getMessageId(), 100);
                        Assert.assertEquals(variables.getMessageText(), "Wrong side");
                        break;
                    }
                    case 103: {
                        Assert.assertEquals(variables.getMessageId(), 103);
                        Assert.assertEquals(variables.getMessageText(), "Card detected");
                        break;
                    }
                }
                break;
            }
        }

//        while (!variables.getActionType().equalsIgnoreCase("complete")) {
        while (variables.getOrder() != 6) {
            if (iterationCounter == 1) {
                apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavaa"), apiResponse.CLIENT_REQUEST_OCR);
            }
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavab"), apiResponse.CLIENT_REQUEST_OCR);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavac"), apiResponse.CLIENT_REQUEST_OCR);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavad"), apiResponse.CLIENT_REQUEST_OCR);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavae"), apiResponse.CLIENT_REQUEST_OCR);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavaf"), apiResponse.CLIENT_REQUEST_OCR);
            apiResponse.clientResponseLivenessOtpStage(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "json_obj", "{\"stageName\":\"otp\",\"stageOrdinal\":5}", "message_type", "report_stage_ending", apiResponse.CLIENT_REQUEST_OCR);
            Thread.sleep(1500);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavad"), apiResponse.CLIENT_REQUEST_OCR);
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
            if (apiResponse.clientRequest.getBody().asString().contains("\"action_type\":\"complete\"")) {
                apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
            }
            iterationCounter++;
        }

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 6);
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
        apiResponse.clientResponseLivenessOtpStage(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "json_obj", "{\"stageName\":\"gestures\",\"stageOrdinal\":6}", "message_type", "report_stage_ending", apiResponse.CLIENT_REQUEST_OCR);
        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");

        //stage 0 (front)
        Assert.assertEquals(variables.getActionType1(), "stage");
        Assert.assertEquals(variables.getOrder1(), 1);
        Assert.assertEquals(variables.getType1(), "front");
        Assert.assertEquals(variables.getCardType(), "cni_front");
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertNotNull(variables.getCroppedImage());
        Assert.assertEquals(variables.getStageStatus(), "success");
        //stage 1 (video)
        Assert.assertEquals(variables.getActionType2(), "stage");
        Assert.assertEquals(variables.getOrder2(), 2);
        Assert.assertEquals(variables.getType2(), "video");
        Assert.assertEquals(variables.getStageStatus2(), "success");
        //stage 2 (back)
        Assert.assertEquals(variables.getActionType3(), "stage");
        Assert.assertEquals(variables.getOrder3(), 3);
        Assert.assertEquals(variables.getType3(), "back");
        Assert.assertEquals(variables.getCardType2(), "cni_back");
        Assert.assertNotNull(variables.getInputImage2());
        Assert.assertNotNull(variables.getCroppedImage2());
        Assert.assertEquals(variables.getStageStatus3(), "success");
        //stage 3 (video)
        Assert.assertEquals(variables.getActionType4(), "stage");
        Assert.assertEquals(variables.getOrder4(), 4);
        Assert.assertEquals(variables.getType4(), "video");
        Assert.assertEquals(variables.getStageStatus4(), "success");
        //stage 4 (otp)
        Assert.assertEquals(variables.getActionType5(), "stage");
        Assert.assertEquals(variables.getOrder5(), 5);
        Assert.assertEquals(variables.getType5(), "otp");
        Assert.assertTrue(variables.getOtpNum()>0);
        Assert.assertEquals(variables.getStageStatus4(), "success");

        //gestures stage  (5)
        Assert.assertEquals(variables.getGestureActionType(), "complete");
        Assert.assertEquals(variables.getGestureOrder(), 6);
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

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "gesturesWithActive.json");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertNotNull(StartSessionVariables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "CNI");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertNotNull(CommonVariables.getCaseId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "gesturesWithActive.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "CNI");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //front stage
        Assert.assertEquals(OcrHandlers.getActionType(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");
        Assert.assertEquals(CNIVariables.getFrontCardType(), "cni_front");
        Assert.assertNotNull(CNIVariables.getFaceImage());
        Assert.assertNotNull(CNIVariables.getInputImage());
        Assert.assertNotNull(CNIVariables.getCroppedImage());

        //front video stage
        Assert.assertEquals(OcrHandlers.getActionType3(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder3(), 2);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage3(), "video");
        Assert.assertEquals(OcrHandlers.getStageStatus3(), "success");

        //back stage
        Assert.assertEquals(OcrHandlers.getActionType2(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder2(), 3);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage2(), "back");
        Assert.assertEquals(CNIVariables.getBackCardType(), "cni_back");
        Assert.assertNotNull(CNIVariables.getInputImage2());
        Assert.assertNotNull(CNIVariables.getCroppedImage2());

        //back video stage
        Assert.assertEquals(OcrHandlers.getActionType4(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder4(), 4);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage4(), "video");
        Assert.assertEquals(OcrHandlers.getStageStatus4(), "success");

        //Otp stage
        Assert.assertEquals(OcrHandlers.getActionType5(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder5(), 5);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage5(), "otp");
        Assert.assertNotNull(CNIVariables.getOtpNum());
        if (CNIVariables.getOtpNum().length() == 3) {
            Assert.assertEquals("0" + CNIVariables.getOtpNum(), String.valueOf(variables.getOtpNum()));
        } else Assert.assertEquals(CNIVariables.getOtpNum(), String.valueOf(variables.getOtpNum()));
        Assert.assertEquals(OcrHandlers.getStageStatus5(), "success");

        //Gestures stage
        Assert.assertEquals(OcrHandlers.getActionType6(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder6(), 6);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage6(), "gestures");
        Assert.assertNotNull(CNIVariables.getGestures1());
        Assert.assertNotNull(CNIVariables.getGestures2());
        Assert.assertNotNull(CNIVariables.getGestures3());
        Assert.assertEquals(CNIVariables.getSequenceSecondsInterval(), 6);
        Assert.assertNotNull(CNIVariables.getResourceGesture1());
        Assert.assertNotNull(CNIVariables.getResourceGesture2());
        Assert.assertNotNull(CNIVariables.getResourceGesture3());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @AfterMethod
    public void closingWebhookConnection() {
        mongoHandler.closeMongoDBConnection();
    }

}
