package selenium.liveness.test.newServer.newVersion.active;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Liveness.*;
import utilities.MongoDB.Variables.StartSessionVariables;
import utilities.RetryAnalyzer;
import utilities.TestUtils;

import java.io.IOException;

public class Functionality extends BaseTest {

    Variables variables;
    MongoHandler mongoHandler;
    MainPage mainPage;

    @BeforeMethod
    public void initSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        mainPage = new MainPage(driver);
        driver.get(LIVENESS_DEMO);
    }

    @Test(description = "demo active liveness with face mask at stage 0")
    @Description("Demo of active liveness with face mask by injecting face mask image at stage 0")
    public void t01_activeLivenessStage0FaceMask() throws IOException, InterruptedException {
        mainPage.chooseConfigFile("qa_config.json");
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/moranWithFaceMask.jpg", null, 1, true, "Face mask detected"));

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(OCR_TIMEOUT), 6);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);
        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");

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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "qa_config.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionTypeTimeout(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrderTimeout(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStageTimeout(), "passive");
        Assert.assertEquals(LivenessHandlers.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(PassiveVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "demo active liveness with illuminated face at stage 0")
    @Description("Demo of active liveness with illuminated face by injecting illuminated face image at stage 0")
    public void t02_activeLivenessStage0IlluminatedFace() throws IOException, InterruptedException {
        mainPage.chooseConfigFile("qa_config.json");
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/too_bright.jpg", null, 1, true, "Face not properly illuminated"));

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(OCR_TIMEOUT), 6);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(LIVENESS_TIMEOUT), 6);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");
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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "qa_config.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionTypeTimeout(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrderTimeout(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStageTimeout(), "passive");
        Assert.assertEquals(LivenessHandlers.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(PassiveVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "demo active liveness with face not in focus at stage 0")
    @Description("Demo of active liveness with face not in focus by injecting face not in focus image at stage 0")
    public void t03_activeLivenessStage0FaceNotInFocus() throws IOException, InterruptedException {
        mainPage.chooseConfigFile("qa_config.json");
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/roleMessages/BadFaceFocus/Blur.jpg", null, 1, true, "Face not in focus"));

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(OCR_TIMEOUT), 6);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(),"timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");
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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "qa_config.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionTypeTimeout(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrderTimeout(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStageTimeout(), "passive");
        Assert.assertEquals(LivenessHandlers.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(PassiveVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "demo active liveness with face not in focus at stage 2",retryAnalyzer = RetryAnalyzer.class)
    @Description("Demo of active liveness with face not in focus by injecting face mask image at stage 2")
    public void t04_activeLivenessStage2FaceNotInFocus() throws IOException, InterruptedException {
        mainPage.chooseConfigFile("qa_config.json");
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        Assert.assertTrue(injection.livenessInjection("./liveness/apiPic/barLeft.jpg", "./liveness/roleMessages/BadFaceFocus/Blur.jpg", "./liveness/apiPic/barRight.jpg", 1, false, "Face not in focus"));

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(OCR_TIMEOUT), 10);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getThreshold(),TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getDblScore()-0.89)<0.1);
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");
        Assert.assertTrue(resultsPage.verifyImagesAreReal());

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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "qa_config.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage3());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrderTimeout(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStageTimeout(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(LeftRightCenterVariables.getLastReceivedImage());
    }

    @Test(description = "demo active liveness with face mask at stage 2",retryAnalyzer = RetryAnalyzer.class)
    @Description("Demo of active liveness with face mask by injecting face mask image at stage 2")
    public void t05_activeLivenessStage2FaceMask() throws IOException, InterruptedException {
        mainPage.chooseConfigFile("qa_config.json");
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        Assert.assertTrue(injection.livenessInjection("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/moranWithFaceMask.jpg", "./liveness/apiPic/barRight.jpg", 1, false, "Face mask detected"));

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(OCR_TIMEOUT), 10);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getThreshold(),TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getDblScore()-0.89)<0.1);
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");
        Assert.assertTrue(resultsPage.verifyImagesAreReal());

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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "qa_config.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage3());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrderTimeout(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStageTimeout(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(LeftRightCenterVariables.getLastReceivedImage());
    }

    @Test(description = "demo active liveness with illuminated face at stage 2",retryAnalyzer = RetryAnalyzer.class)
    @Description("Demo of active liveness with illuminated face by injecting illuminated face image at stage 2")
    public void t06_activeLivenessStage2IlluminatedFace() throws IOException, InterruptedException {
        mainPage.chooseConfigFile("qa_config.json");
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        Assert.assertTrue(injection.livenessInjection("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/too_bright.jpg", "./liveness/apiPic/barRight.jpg", 1, false, "Face not properly illuminated"));

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(OCR_TIMEOUT), 10);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getThreshold(),TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getDblScore()-0.89)<0.1);
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");
        Assert.assertTrue(resultsPage.verifyImagesAreReal());

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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "qa_config.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage3());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrderTimeout(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStageTimeout(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(LeftRightCenterVariables.getLastReceivedImage());
    }

    @Test(description = "demo active liveness with face mask at stage 0, and then change to normal image",retryAnalyzer = RetryAnalyzer.class)
    @Description("Demo of active liveness with face mask by injecting face mask image at stage 0, and after the face mask message, injecting normal liveness image to continue with the process")
    public void t07_activeLivenessStage0FaceMaskAndChangeToNormal() throws IOException, InterruptedException {
        mainPage.chooseConfigFile("qa_config1.json");
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.injectionErrorMessage("./liveness/apiPic/moranWithFaceMask.jpg", "Face mask detected"));
//        injection.imageInjection("./liveness/apiPic/moranWithFaceMask.jpg", 1, false);
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        injection.livenessInjection2("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/barCenter.jpg", "./liveness/apiPic/barRight.jpg");

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(OCR_TIMEOUT), 9);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config1.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getThreshold(),TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getDblScore()-0.8978993892669678)<0.1);
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "last_received_image"), "last_received_image");
        Assert.assertTrue(resultsPage.verifyImagesAreReal());

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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "livenessWithErrorMessages.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage3());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "complete");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage7());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "demo active liveness with illuminated face at stage 0, and then change to normal image",retryAnalyzer = RetryAnalyzer.class)
    @Description("Demo of active liveness with illuminated face by injecting face mask image at stage 0, and after the illuminated face message, injecting normal liveness image to continue with the process")
    public void t08_activeLivenessStage0IlluminatedFaceAndChangeToNormal() throws IOException, InterruptedException {
        mainPage.chooseConfigFile("qa_config1.json");
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.injectionErrorMessage("./liveness/apiPic/too_bright.jpg", "Face not properly illuminated"));
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        injection.livenessInjection2("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/barCenter.jpg", "./liveness/apiPic/barRight.jpg");



        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 9);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config1.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertEquals(resultsPage.valueToCheck(variables, "score\n" + "0.985521137714386"), "score\n" + "0.985521137714386");
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "livenessWithErrorMessages.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage3());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "complete");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage7());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "demo active liveness with face not in focus at stage 0, and then change to normal image",retryAnalyzer = RetryAnalyzer.class)
    @Description("Demo of active liveness with face not in focus by injecting face mask image at stage 0, and after the face not in focus message, injecting normal liveness image to continue with the process")
    public void t09_activeLivenessStage0FaceNotInFocusAndChangeToNormal() throws IOException, InterruptedException {
        mainPage.chooseConfigFile("qa_config1.json");
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.injectionErrorMessage("./liveness/roleMessages/BadFaceFocus/Blur.jpg", "Face not in focus"));
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        injection.livenessInjection2("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/barCenter.jpg", "./liveness/apiPic/barRight.jpg");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 9);


        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config1.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertEquals(resultsPage.valueToCheck(variables, "score\n" + "0.985521137714386"), "score\n" + "0.985521137714386");
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "livenessWithErrorMessages.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage3());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "complete");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage7());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "demo active liveness with illuminated face at stage 2, and then change to normal image",retryAnalyzer = RetryAnalyzer.class)
    @Description("Demo of active liveness with illuminated face by injecting illuminated face image at stage 2, and after the illuminated face message, injecting normal liveness image to continue with the process")
    public void t10_activeLivenessStage2IlluminatedFaceAndChangeToNormal() throws IOException, InterruptedException {
        mainPage.chooseConfigFile("qa_config1.json");
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        Assert.assertTrue(injection.livenessInjection("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/too_bright.jpg", "./liveness/apiPic/barRight.jpg", 1, false, "Face not properly illuminated"));
        injection.livenessInjection2("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/barCenter.jpg", "./liveness/apiPic/barRight.jpg");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 9);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config1.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertEquals(resultsPage.valueToCheck(variables, "score\n" + "0.985521137714386"), "score\n" + "0.985521137714386");
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "livenessWithErrorMessages.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage3());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "complete");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage7());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "demo active liveness with face mask at stage 2, and then change to normal image",retryAnalyzer = RetryAnalyzer.class)
    @Description("Demo of active liveness with face mask by injecting face mask image at stage 2, and after the face mask message, injecting normal liveness image to continue with the process")
    public void t11_activeLivenessStage2FaceMaskAndChangeToNormal() throws IOException, InterruptedException {
        mainPage.chooseConfigFile("qa_config1.json");
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        Assert.assertTrue(injection.livenessInjection("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/moranWithFaceMask.jpg", "./liveness/apiPic/barRight.jpg", 1, false, "Face mask detected"));
        injection.livenessInjection2("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/barCenter.jpg", "./liveness/apiPic/barRight.jpg");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 9);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config1.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertEquals(resultsPage.valueToCheck(variables, "score\n" + "0.985521137714386"), "score\n" + "0.985521137714386");
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "livenessWithErrorMessages.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage3());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "complete");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage7());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "demo active liveness with face not in focus at stage 2, and then change to normal image",retryAnalyzer = RetryAnalyzer.class)
    @Description("Demo of active liveness with face not in focus by injecting face mask image at stage 2, and after the face not in focus message, injecting normal liveness image to continue with the process")
    public void t12_activeLivenessStage2FaceNotInFocusAndChangeToNormal() throws IOException, InterruptedException {
        mainPage.chooseConfigFile("qa_config1.json");
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        Assert.assertTrue(injection.livenessInjection("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/too_bright.jpg", "./liveness/apiPic/barRight.jpg", 1, false, "Face not properly illuminated"));
        injection.livenessInjection2("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/barCenter.jpg", "./liveness/apiPic/barRight.jpg");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 9);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config1.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertEquals(resultsPage.valueToCheck(variables, "score\n" + "0.985521137714386"), "score\n" + "0.985521137714386");
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "livenessWithErrorMessages.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage3());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "complete");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage7());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "active liveness timeout session at passive stage",retryAnalyzer = RetryAnalyzer.class)
    @Description("Demo active liveness timeout session at passive stage")
    public void t13_activeLivenessTimeoutAtPassiveStage() throws InterruptedException {
        mainPage.chooseConfigFile("qa_config1.json");
        mainPage.chooseFromServicesList("Liveness");
        Thread.sleep(95000);
        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);


        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config1.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(resultsPage.valueToCheck(variables, "last_received_image"), "last_received_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "qa_config.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionTypeTimeout(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrderTimeout(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStageTimeout(), "passive");
        Assert.assertEquals(LivenessHandlers.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(PassiveVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "active liveness timeout session at first dynamic side stage")
    @Description("Demo active liveness timeout session at first dynamic side stage")
    public void t14_activeLivenessTimeoutAtFirstDynamicSideStage() throws IOException, InterruptedException {
        mainPage.chooseConfigFile("qa_config.json");
        mainPage.chooseFromServicesList("Liveness");
//        Injection injection = new Injection(driver);
//        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(OCR_TIMEOUT), 6);
        resultsPage.collectResults(variables);


        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config.json");
        Assert.assertTrue(variables.isSuccess());

//        Assert.assertEquals(variables.getThreshold(),TestUtils.LIVENESS_THRESHOLD);
//        Assert.assertEquals(variables.getDblScore(),0.8978993892669678);
//        Assert.assertEquals(variables.getFaceImage(), "face_image");
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(resultsPage.valueToCheck(variables, "last_received_image"), "last_received_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "qa_config.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.8978993892669678);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrderTimeout(), 2);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStageTimeout());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(LeftRightCenterVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "active liveness timeout session at center stage",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Demo active liveness timeout session at center stage")
    public void t15_activeLivenessTimeoutAtCenterStage() throws IOException, InterruptedException {
        mainPage.chooseConfigFile("qa_config1.json");
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        Assert.assertTrue(injection.livenessInjection("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/face_not_found.jpg", "./liveness/apiPic/barRight.jpg", 1, false, "No face found"));
        Thread.sleep(90000);
        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 10);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config1.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertEquals(variables.getDblScore(),0.985521137714386);
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(resultsPage.valueToCheck(variables, "last_received_image"), "last_received_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "qa_config.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage3());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrderTimeout(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStageTimeout(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(LeftRightCenterVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "active liveness timeout session at second dynamic side stage",retryAnalyzer = RetryAnalyzer.class)
    @Description("Demo active liveness timeout session at second dynamic side stage")
    public void t16_activeLivenessTimeoutAtSecondDynamicSideStage() throws IOException, InterruptedException {
        mainPage.chooseConfigFile("qa_config1.json");
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        Assert.assertTrue(injection.livenessInjection("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/face_not_found.jpg", "./liveness/apiPic/barRight.jpg", 1, false, "No face found"));
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        Thread.sleep(90000);
        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 10);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config1.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertEquals(variables.getDblScore(),0.985521137714386);
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(resultsPage.valueToCheck(variables, "last_received_image"), "last_received_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "qa_config.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage3());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrderTimeout(), 4);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStageTimeout());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(LeftRightCenterVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }


    @Test(description = "active liveness timeout session at stt stage",retryAnalyzer = RetryAnalyzer.class)
    @Description("Demo active liveness timeout session at stt stage")
    public void t18_activeLivenessTimeoutAtSttStage() throws IOException, InterruptedException {
        mainPage.chooseConfigFile("qa_config.json");
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        injection.livenessInjection2("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/barCenter.jpg", "./liveness/apiPic/barRight.jpg");

//        injection.otpInjection("./liveness/audio/otp/otpAudioChunks");

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(OCR_TIMEOUT), 10);
        resultsPage.collectResults(variables);


        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getThreshold(),TestUtils.LIVENESS_THRESHOLD);
        Assert.assertEquals(variables.getDblScore(), 0.985521137714386);
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "score"), "score\n" + "0.8978993892669678");
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "otp_number"), "otp_number\n" + variables.getOtpNum());
        Assert.assertEquals(resultsPage.valueToCheck(variables, "last_received_image"), "last_received_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "qa_config.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage3());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage7());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");

        Assert.assertEquals(OtpVariables.getActionType5(), "stage");
        Assert.assertEquals(OtpVariables.getOrder5(), 5);
        Assert.assertEquals(OtpVariables.getTypeUnderStage5(), "otp");
        Assert.assertEquals(OtpVariables.getOtpNumber(), 1111);
        Assert.assertEquals(OtpVariables.getStageStatus5(), "success");

        Assert.assertEquals(SttVariables.getActionTypeTimeout(), "complete");
        Assert.assertEquals(SttVariables.getOrderTimeout(), 6);
        Assert.assertEquals(SttVariables.getTypeUnderStageTimeout(), "stt");
        Assert.assertEquals(SttVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(SttVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }
}
