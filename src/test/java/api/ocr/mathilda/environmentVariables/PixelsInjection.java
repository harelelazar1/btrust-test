package api.ocr.mathilda.environmentVariables;

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
import utilities.TestUtils;
public class PixelsInjection {

    Variables variables;
    MongoHandler mongoHandler;
    ApiResponse apiResponse;
    File frontImagePixelated;
    File backImagePixelated;

    @BeforeMethod
    public void initSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
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

    @Test(description = "blue id with cPallet pixel injection")
    @Description("Blue id with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t01_blueIdPixelInjection() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        frontImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./ocr/blueID/liad/blueID_color1.jpg"), 1f);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", frontImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", frontImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }
    }

    @Test(description = "green id with cPallet pixel injection")
    @Description("Green id with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t02_greenIdPixelInjection() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        frontImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./ocr/greenID/bar/BarGreenID1.jpg"), 1f);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", frontImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", frontImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }
    }

    @Test(description = "bio id with cPallet pixel injection")
    @Description("Bio id with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t03_bioIdPixelInjection() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        frontImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg"), 1f);

        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", frontImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (variables.getMessageId() != 100) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", frontImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        backImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./ocr/bioID/nitzan/back/NitzanBioBack1.jpg"), 1f);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", backImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }
    }

    @Test(description = "new il-dl with cPallet pixel injection")
    @Description("New IL-DL with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t04_newDlPixelInjection() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        frontImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./ocr/dl/newDLFront.jpg"), 1f);

        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", frontImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (variables.getMessageId() != 100) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", frontImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        backImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./ocr/dl/newDLBack.jpg"), 1f);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", backImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }
    }

    @Test(description = "old il-dl with cPallet pixel injection")
    @Description("OLd IL-DL with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t05_oldDlPixelInjection() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        frontImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./ocr/dl/oldDLFront.jpg"), 1f);

        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", frontImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (variables.getMessageId() != 100) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", frontImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        backImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./ocr/dl/oldDLBack.jpg"), 1f);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", backImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }
    }

    //   @Test(description = "new dk-dl with cPallet pixel injection")
    @Description("New DL-DL with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t07_newDkDlPixelInjection() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "DK-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        frontImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./ocr/denmarkDrivingLicense/newDKDL/314764922.jpg"), 1f);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", frontImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", frontImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }
    }

    //   @Test(description = "old dk-dl with cPallet pixel injection")
    @Description("OLd DL-DL with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t08_oldDkDlPixelInjection() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "DK-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        frontImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./ocr/denmarkDrivingLicense/oldDKDL/210015722.jpg"), 1f);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", frontImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", frontImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }
    }

    //   @Test(description = "phc with cPallet pixel injection")
    @Description("Phc with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t08_phcPixelInjection() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "PHL-CHEQUES", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        frontImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./ocr/pixelatedImages/phcPixelated.jpg"), 1f);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", frontImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", frontImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }
    }

    @Test(description = "cni with cPallet pixel injection")
    @Description("Cni with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t09_cniPixelInjection() throws IOException, InterruptedException {
        int iterationCounter = 1;

        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "CNI", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        frontImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./ocr/cni/good_fr_3.jpg"), 1f);

        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", frontImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (variables.getMessageId() != 100) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", frontImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        backImagePixelated = apiResponse.setNewPixelsPallet(variables, new File("./ocr/cni/good_fr_back_1.jpg"), 1f);

        while (!variables.getTypeUnderStage().equalsIgnoreCase("otp")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", backImagePixelated, "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
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
    }
}
