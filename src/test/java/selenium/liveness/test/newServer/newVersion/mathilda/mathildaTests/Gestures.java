package selenium.liveness.test.newServer.newVersion.mathilda.mathildaTests;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import selenium.ocr.pageObject.newService.ScanPage;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Liveness.*;
import utilities.MongoDB.Variables.StartSessionVariables;
import utilities.TestUtils;

import java.io.IOException;

public class Gestures extends BaseTest {

    Variables variables;
    MongoHandler mongoHandler;
    MainPage mainPage;
    Injection injection;
    ScanPage scanPage;
    ResultsPage resultsPage;

    @BeforeMethod
    public void initSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        mainPage = new MainPage(driver);
        injection = new Injection(driver);
        scanPage = new ScanPage(driver);
        resultsPage = new ResultsPage(driver);
    }

    @Test(description = "liveness passive with gestures")
    @Description("Liveness passive with gestures stage as final stage")
    public void t01_livenessPassiveAndGesture() throws IOException, InterruptedException {
        driver.get(LIVENESS_DEMO);
        mainPage.chooseConfigFile("gesturesWithPassive.json");
        mainPage.chooseFromServicesList("Liveness");
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        injection.injectGesturesImages(scanPage, "./liveness/apiPic/barCenter.jpg");

        Assert.assertEquals(resultsPage.verifyListsSizes(), 12);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), variables.getStatus());
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "gesturesWithPassive.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertEquals(resultsPage.valueToCheck(variables, "score\n" + "0.9221184849739075"), "score\n" + "0.9221184849739075");
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");

        Assert.assertEquals(resultsPage.valueToCheck(variables, "sequenceSecondsInterval"), "sequenceSecondsInterval\n" + "8");
        Assert.assertNotNull(resultsPage.valueToCheck(variables, "0"));
        Assert.assertNotNull(resultsPage.valueToCheck(variables, "1"));

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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "gesturesWithPassive.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //Passive stage
        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        //Gestures stage
        Assert.assertEquals(LivenessHandlers.getActionType8(), "complete");
        Assert.assertEquals(LivenessHandlers.getOrder8(), 2);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage8(), "gestures");
        Assert.assertNotNull(GesturesVariables.getGestures1());
        Assert.assertNotNull(GesturesVariables.getGestures2());
        Assert.assertEquals(GesturesVariables.getSequenceSecondsInterval(), 8);
        Assert.assertNotNull(GesturesVariables.getResourceGesture1());
        Assert.assertNotNull(GesturesVariables.getResourceGesture2());
    }

    @Test(description = "liveness active with gestures")
    @Description("Liveness active with gestures stage as middle stage")
    public void t02_livenessActiveAndGesture() throws IOException, InterruptedException {
        driver.get(LIVENESS_DEMO);
        mainPage.chooseConfigFile("gesturesWithActive.json");
        mainPage.chooseFromServicesList("Liveness");
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        injection.injectGesturesImages(scanPage, "./liveness/apiPic/barCenter.jpg");
        injection.livenessInjection2("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/barCenter.jpg", "./liveness/apiPic/barRight.jpg");

        Assert.assertEquals(resultsPage.verifyListsSizes(), 13);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), variables.getStatus());
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "gesturesWithActive.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertEquals(resultsPage.valueToCheck(variables, "score\n" + "0.9221184849739075"), "score\n" + "0.9221184849739075");
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");

        Assert.assertEquals(resultsPage.valueToCheck(variables, "sequenceSecondsInterval"), "sequenceSecondsInterval\n" + "6");
        Assert.assertNotNull(resultsPage.valueToCheck(variables, "0"));
        Assert.assertNotNull(resultsPage.valueToCheck(variables, "1"));
        Assert.assertNotNull(resultsPage.valueToCheck(variables, "2"));

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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "gesturesWithActive.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //Passive stage
        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        //Gestures stage
        Assert.assertEquals(LivenessHandlers.getActionType8(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder8(), 2);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage8(), "gestures");
        Assert.assertNotNull(GesturesVariables.getGestures1());
        Assert.assertNotNull(GesturesVariables.getGestures2());
        Assert.assertNotNull(GesturesVariables.getGestures3());
        Assert.assertEquals(GesturesVariables.getSequenceSecondsInterval(), 6);
        Assert.assertNotNull(GesturesVariables.getResourceGesture1());
        Assert.assertNotNull(GesturesVariables.getResourceGesture2());
        Assert.assertNotNull(GesturesVariables.getResourceGesture3());

        //Stage 3
        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");
        switch (LeftRightCenterVariables.getTypeUnderStage3()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage3(), "right");
                break;
            }
        }
        Assert.assertNotNull(LeftRightCenterVariables.getHeadPositionType());
        Assert.assertEquals(LeftRightCenterVariables.getHeadPositionType(), LeftRightCenterVariables.getTypeUnderStage3());
        Assert.assertTrue(LeftRightCenterVariables.isHeadPositionExpected());

        //Stage 4
        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 4);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertNotNull(LeftRightCenterVariables.getHeadPositionType1());
        Assert.assertEquals(LeftRightCenterVariables.getHeadPositionType1(), "center");
        Assert.assertTrue(LeftRightCenterVariables.isHeadPositionExpected1());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        //Stage 5
        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "complete");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 5);
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");
        switch (LeftRightCenterVariables.getTypeUnderStage7()) {
            case "left": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "left");
                break;
            }
            case "right": {
                Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage7(), "right");
                break;
            }
        }
        Assert.assertNotNull(LeftRightCenterVariables.getHeadPositionType2());
        Assert.assertEquals(LeftRightCenterVariables.getHeadPositionType2(), LeftRightCenterVariables.getTypeUnderStage7());
        Assert.assertTrue(LeftRightCenterVariables.isHeadPositionExpected2());
    }
}
