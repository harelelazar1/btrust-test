package selenium.liveness.test.newServer.newVersion.passive;

import api.Variables;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Liveness.LivenessHandlers;
import utilities.MongoDB.Variables.Liveness.PassiveVariables;
import utilities.MongoDB.Variables.Liveness.SttVariables;
import utilities.MongoDB.Variables.StartSessionVariables;
import utilities.RetryAnalyzer;
import utilities.TestUtils;

import java.io.IOException;

public class SanityTest extends BaseTest {


    MongoHandler mongoHandler = new MongoHandler();

    @Test(description = "passive liveness sanity demo",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Demo of passive liveness sanity test by injecting straight face images, and then injecting audio for the stt part")
    public void t01_livenessPassiveSanity() throws IOException, InterruptedException {
        Variables variables = new Variables();
        driver.get(LIVENESS_DEMO);
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
//      injection.sttInjection("./liveness/audio/stt/fullStt");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 9);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), variables.getStatus());
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getDblScore()- 0.985521137714386)<0.01);
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");

//        Assert.assertNotNull(resultsPage.valueToCheck(variables, "stt_text"));
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "original_text"), "original_text\n" + "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "threshold\n" + "0.7"), "threshold\n" + "0.7");
//        Assert.assertEquals(resultsPage.valueToCheck(variables, "language\n"), "language\n" + "he-IL");
//        Assert.assertNotNull(resultsPage.valueToCheck(variables, "score"));
//        Assert.assertTrue(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]) > Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));
//        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]));
//        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));

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
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
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
        Assert.assertEquals(SttVariables.getScore(), 0.9230769230769231);
        Assert.assertEquals(SttVariables.getStageStatus6(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "active liveness sanity demo with STT",retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Demo of active liveness sanity test by injecting straight, left & right face images, and then injecting audio for the otp and stt parts")
    public void t02_passiveSttSanity() throws InterruptedException, IOException {
        Variables variables = new Variables();
        driver.get(LIVENESS_DEMO);
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseConfigFile("passive_stt.json");
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
//        injection.livenessInjection2("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/barCenter.jpg", "./liveness/apiPic/barRight.jpg");
//        injection.otpInjection("./liveness/audio/otp/otpAudioChunks");
        injection.sttInjection("./liveness/audio/stt/fullStt");

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(LIVENESS_TIMEOUT), 14);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "passive_stt.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(variables.getDblScore()>0.8);
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");

        Assert.assertNotEquals(variables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים","stt text mismatch");
        Assert.assertEquals(variables.getOriginalText(),  "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
        Assert.assertTrue(variables.getDblScore2()>0.8);
        Assert.assertTrue(resultsPage.verifyImagesAreReal());
    }
    }
