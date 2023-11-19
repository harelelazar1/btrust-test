package selenium.liveness.test.newServer.newVersion.active;

import api.Variables;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Liveness.*;
import utilities.MongoDB.Variables.StartSessionVariables;
import utilities.TestUtils;

import java.io.IOException;

public class SanityTest extends BaseTest {

    Variables variables = new Variables();
    MongoHandler mongoHandler = new MongoHandler();

    @Test(description = "active liveness sanity demo",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Demo of active liveness sanity test by injecting straight, left & right face images, and then injecting audio for the otp and stt parts")
    public void t01_livenessActiveSanity() throws InterruptedException, IOException {
        driver.get(LIVENESS_DEMO);
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseConfigFile("qa_config1.json");
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
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
        Assert.assertEquals(variables.getDblScore(),0.985521137714386);
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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "qa_config.json");
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

        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage7());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");

        Assert.assertEquals(OtpVariables.getActionType5(), "stage");
        Assert.assertEquals(OtpVariables.getOrder5(), 5);
        Assert.assertEquals(OtpVariables.getTypeUnderStage5(), "otp");
        Assert.assertEquals(OtpVariables.getOtpNumber(), 1111);
        Assert.assertEquals(OtpVariables.getStageStatus5(), "success");

        Assert.assertEquals(SttVariables.getActionType6(), "complete");
        Assert.assertEquals(SttVariables.getOrder6(), 6);
        Assert.assertEquals(SttVariables.getTypeUnderStage6(), "stt");
        Assert.assertEquals(SttVariables.getSttText(), "החשבון החשבון יהיה בבעלותי ולא יהיו בו נהנים");
        Assert.assertEquals(SttVariables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
        Assert.assertEquals(SttVariables.getThreshold(), 0.7);
        Assert.assertEquals(SttVariables.getLanguage(), "he-IL");
        Assert.assertEquals(SttVariables.getScore(), 0.8470588235294118);
        Assert.assertEquals(SttVariables.getStageStatus6(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }



}
