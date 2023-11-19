package api.ocr.mathilda.environmentVariables;

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
import utilities.TestUtils;
public class DisablingFullResponse {

    ApiResponse apiResponse;
    Variables variables;
    MongoHandler mongoHandler;
    String temporaryCaseId;

    @BeforeMethod
    public void startSession() {
        apiResponse = new ApiResponse();
        variables = new Variables();
        mongoHandler = new MongoHandler();
        temporaryCaseId = apiResponse.randomString();
    }

    @Test(description = "bio id e2e without full response")
    @Description("Bio Id e2e with full response disabled")
    public void t01_disablingFullResponseOfBioId() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", temporaryCaseId, "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (variables.getMessageId() != 100) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/back/NitzanBioBack1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(temporaryCaseId, variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertTrue(CommonVariables.isSuccess());
    }

    @Test(description = "blue id e2e without full response")
    @Description("Blue Id e2e with full response disabled")
    public void t02_disablingFullResponseOfBlueId() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", temporaryCaseId, "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/blueID/liad/blueID_color1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/blueID/liad/blueID_color1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(temporaryCaseId, variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertTrue(CommonVariables.isSuccess());
    }

    @Test(description = "green id e2e without full response")
    @Description("Green Id e2e with full response disabled")
    public void t03_disablingFullResponseOfGreenId() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", temporaryCaseId, "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/bar/BarGreenID1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/bar/BarGreenID1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(temporaryCaseId, variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertTrue(CommonVariables.isSuccess());
    }

    @Test(description = "new il-dl id e2e without full response")
    @Description("New IL-DL e2e with full response disabled")
    public void t04_disablingFullResponseOfNewILDL() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", temporaryCaseId, "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/newDLFront.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (variables.getMessageId() != 100) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/newDLFront.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/newDLBack.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(temporaryCaseId, variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertTrue(CommonVariables.isSuccess());
    }

    @Test(description = "old il-dl id e2e without full response")
    @Description("Old IL-DL e2e with full response disabled")
    public void t05_disablingFullResponseOfOldILDL() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", temporaryCaseId, "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLFront.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (variables.getMessageId() != 100) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLFront.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLBack.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(temporaryCaseId, variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertTrue(CommonVariables.isSuccess());
    }

    @Test(description = "new mrz id e2e without full response")
    @Description("New MRZ e2e with full response disabled")
    public void t06_disablingFullResponseOfNewMrz() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", temporaryCaseId, "libraryName", "MRZ", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/nadav/nadavMrz.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/nadav/nadavMrz.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(temporaryCaseId, variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertTrue(CommonVariables.isSuccess());
    }

    @Test(description = "old mrz id e2e without full response")
    @Description("Old MRZ e2e with full response disabled")
    public void t07_disablingFullResponseOfOldMrz() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", temporaryCaseId, "libraryName", "MRZ", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/guy/guyMrz.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/guy/guyMrz.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(temporaryCaseId, variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertTrue(CommonVariables.isSuccess());
    }

 //   @Test(description = "old dk-dl id e2e without full response")
    @Description("Old DK-DL e2e with full response disabled")
    public void t08_disablingFullResponseOfOldDKDL() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", temporaryCaseId, "libraryName", "DK-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/denmarkDrivingLicense/oldDKDL/210015722.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/denmarkDrivingLicense/oldDKDL/210015722.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(temporaryCaseId, variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertTrue(CommonVariables.isSuccess());
    }

//    @Test(description = "new dk-dl id e2e without full response")
    @Description("New DK-DL e2e with full response disabled")
    public void t09_disablingFullResponseOfNewDKDL() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", temporaryCaseId, "libraryName", "DK-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/denmarkDrivingLicense/newDKDL/314764922.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/denmarkDrivingLicense/newDKDL/314764922.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(temporaryCaseId, variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertTrue(CommonVariables.isSuccess());
    }

 //   @Test(description = "phc e2e without full response")
    @Description("PHC e2e with full response disabled")
    public void t10_disablingFullResponseOfPHC() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", temporaryCaseId, "libraryName", "PHL-CHEQUES", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cheque/PhilippinesCheque/BDOCheque.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cheque/PhilippinesCheque/BDOCheque.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(temporaryCaseId, variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertTrue(CommonVariables.isSuccess());
    }

    @Test(description = "cni id e2e without full response")
    @Description("CNI e2e with full response disabled")
    public void t11_disablingFullResponseOfCNI() throws IOException, InterruptedException {
        int iterationCounter = 1;
        apiResponse.clientInitResponse(variables, "application/json", "caseId", temporaryCaseId, "libraryName", "CNI", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_3.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (variables.getMessageId() != 100) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_3.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        while (!variables.getTypeUnderStage().equalsIgnoreCase("otp")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_back_1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            if (iterationCounter == 1) {
                apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavaa"),apiResponse.CLIENT_REQUEST_OCR);
            }
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavab"),apiResponse.CLIENT_REQUEST_OCR);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavac"),apiResponse.CLIENT_REQUEST_OCR);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavad"),apiResponse.CLIENT_REQUEST_OCR);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavae"),apiResponse.CLIENT_REQUEST_OCR);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavaf"),apiResponse.CLIENT_REQUEST_OCR);
            apiResponse.clientResponseLivenessOtpStage(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "json_obj", "{\"stageName\":\"otp\",\"stageOrdinal\":5}", "message_type", "report_stage_ending",apiResponse.CLIENT_REQUEST_OCR);
            Thread.sleep(1500);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavad"),apiResponse.CLIENT_REQUEST_OCR);
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_OCR);
            if (apiResponse.clientRequest.getBody().asString().contains("\"action_type\":\"complete\"")) {
                apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_OCR);
            }
            iterationCounter++;
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(temporaryCaseId, variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertTrue(CommonVariables.isSuccess());
    }
}
