package api.ocr.cni.newVersion;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.CNIVariables;
import utilities.MongoDB.Variables.Ocr.OcrHandlers;
import utilities.MongoDB.Variables.StartSessionVariables;

import java.io.File;
import java.io.IOException;

import utilities.TestUtils;

public class Functionality {

    Variables variables;
    ApiResponse apiResponse;
    MongoHandler mongoHandler;

    @BeforeMethod
    public void startSessionAndInitToken() {
        //Init token
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "CNI", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
    }

    @Test(description = "Cni front side - first stage e2e timeout session")
    @Description("Cni front side - timeout session at stage 0")
    public void t01_frontSideFirstStageCniTimeout() throws InterruptedException, IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/moran/cardNotFound/cardNotFound.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        Thread.sleep(70000);
        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/moran/cardNotFound/cardNotFound.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }
        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(variables.getOrderTimeout(), 1);
        Assert.assertEquals(variables.getStageTypeTimeout(), "front");
        Assert.assertEquals(variables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(variables.getLastReceivedImage());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
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
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), TestUtils.getDefaultMathilda());
        Assert.assertEquals(CommonVariables.getServiceType(), "CNI");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(CNIVariables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(CNIVariables.getOrderTimeout(), 1);
        Assert.assertEquals(CNIVariables.getTypeUnderStageTimeout(), "front");
        Assert.assertEquals(CNIVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(CNIVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(description = "api back side - third stage e2e timeout session")
    @Description("Cni front side - timeout session at stage 2")
    public void t02_backSideSecondStageCniTimeout() throws InterruptedException, IOException {
        String noCardImage = "./ocr/small_jpg.jpg";
        while (variables.getActionType().equalsIgnoreCase("message")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_3.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }
        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 2);
        Assert.assertTrue(variables.isSuccess());
        Thread.sleep(60000);
        if (variables.getTypeUnderStage().equalsIgnoreCase("video")) {
            long start = System.currentTimeMillis();
            while (!(variables.getTypeUnderStage().equalsIgnoreCase("back")) && (System.currentTimeMillis() - start) / 1000 < 90) {
                System.out.println(System.currentTimeMillis() - start / 1000);
                apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File(noCardImage), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
            }
        }
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/moran/cardNotFound/cardNotFound.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());

        //first stage (front/back)
        Assert.assertEquals(variables.getActionType1(), "stage");
        Assert.assertEquals(variables.getOrder1(), 1);
        Assert.assertEquals(variables.getType1(), "front");
        Assert.assertEquals(variables.getCardType(), "france_id_cni_front");
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertNotNull(variables.getCroppedImage());
        Assert.assertEquals(variables.getStageStatus(), "success");
        Assert.assertNotNull(variables.getLastReceivedImage());

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
        Assert.assertEquals(CommonVariables.getConfigFilename(), TestUtils.getDefaultMathilda());
        Assert.assertEquals(CommonVariables.getServiceType(), "CNI");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");
        Assert.assertEquals(CNIVariables.getFrontCardType(), "cni_front");
        Assert.assertNotNull(CNIVariables.getFaceImage());
        Assert.assertNotNull(CNIVariables.getInputImage());
        Assert.assertNotNull(CNIVariables.getCroppedImage());

        Assert.assertEquals(OcrHandlers.getActionType3(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder3(), 2);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage3(), "video");
        Assert.assertEquals(OcrHandlers.getStageStatus3(), "success");

        Assert.assertEquals(CNIVariables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(CNIVariables.getOrderTimeout(), 3);
        Assert.assertEquals(CNIVariables.getTypeUnderStageTimeout(), "back");
        Assert.assertEquals(CNIVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(CNIVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }



    @Test(description = "api blurred image timeout")
    @Description("Cni front side - blurred image timeout")
    public void t04_blurredImageCniTimeout() throws IOException {
        String blurredImagePath = "./ocr/cni/France_CNI_Blurred_Front.jpg";
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(),
                "frame", new File(blurredImagePath), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(),
                    "frame", new File(blurredImagePath), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }
        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getMessageText(), "Card detected");
        Assert.assertEquals(variables.getStageTypeTimeout(), "front");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());
    }

    @Test(description = "api blurred image timeout")
    @Description("Cni front side - dark image timeout")
    public void t05_DarkImageCniTimkeout() throws IOException {
        String darkImagePath = "./ocr/cni/France_CNI_Front_Dark.jpg";
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(),
                "frame", new File(darkImagePath), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(),
                    "frame", new File(darkImagePath), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }
        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStageTypeTimeout(), "front");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());
    }

    @Test(description = "Cni cut image")
    @Description("Cni cut image")
    public void t06_frontSideCutImageCniTimeout() throws InterruptedException, IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/cni_cut_image.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
//        while (!variables.getActionType().equalsIgnoreCase("complete")) {
//            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/moran/cardNotFound/cardNotFound.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
//        }
        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(variables.getOrderTimeout(), 1);
        Assert.assertEquals(variables.getStageTypeTimeout(), "front");
        Assert.assertEquals(variables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(variables.getLastReceivedImage());

    }
}

