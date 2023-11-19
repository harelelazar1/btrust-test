package selenium.liveness.test.newServer.sessionsTests;

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
import utilities.MongoDB.Variables.Liveness.LivenessHandlers;
import utilities.MongoDB.Variables.Liveness.PassiveVariables;
import utilities.MongoDB.Variables.Liveness.SttVariables;
import utilities.MongoDB.Variables.StartSessionVariables;
import utilities.RetryAnalyzer;
import utilities.TestUtils;

import java.io.IOException;

public class passiveLivenessSessionTests extends BaseTest {

    Variables variables;
    MongoHandler mongoHandler;
    MainPage mainPage;
    Injection injection;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        mainPage = new MainPage(driver);
        injection = new Injection(driver);
        driver.get(LIVENESS_DEMO);
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseConfigFile("qa_config2.json");
        mainPage.chooseFromServicesList("Liveness");
    }

    @Test(description = "Passive liveness positive injection folder of full session images")
    @Description("Passive liveness positive injection folder of full session images")
    public void t01_e2ePositivePassiveLivenessSessionTest() throws IOException, InterruptedException {
        injection.injectFolder("./ocr/sessions/liveness/passive/1", null);
        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 9);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config2.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");

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
        Assert.assertEquals(PassiveVariables.getScore(), 0.959386944770813);
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



    @Test(description = "Passive liveness positive injection folder of full session images #2")
    @Description("Passive liveness positive injection folder of full session images #2")
    public void t03_e2ePositivePassiveLivenessSessionTest() throws IOException, InterruptedException {
        injection.injectFolder("./ocr/sessions/liveness/passive/2", null);
        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 9);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config2.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertTrue(resultsPage.verifyImagesAreReal());
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
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
        Assert.assertEquals(PassiveVariables.getScore(), 0.9684814810752869);
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


    @Test(description = "Passive liveness positive injection folder of full session images #3")
    @Description("Passive liveness positive injection folder of full session images #3")
    public void t05_e2ePositivePassiveLivenessSessionTest() throws IOException, InterruptedException {
        injection.injectFolder("./ocr/sessions/liveness/passive/3", null);
        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 9);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config2.json");
        Assert.assertTrue(resultsPage.verifyImagesAreReal());
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
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
        Assert.assertEquals(PassiveVariables.getScore(), 0.45624563097953796);
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




    @Test(description = "Passive liveness positive injection folder of full session images #4")
    @Description("Passive liveness positive injection folder of full session images #4")
    public void t07_e2ePositivePassiveLivenessSessionTest() throws IOException, InterruptedException {
        injection.injectFolder("./ocr/sessions/liveness/passive/4", null);
        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 9);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config2.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertTrue(resultsPage.verifyImagesAreReal());
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
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
        Assert.assertEquals(PassiveVariables.getScore(), 0.8701340556144714);
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



    @Test(description = "Passive liveness positive injection folder of full session images #5",retryAnalyzer = RetryAnalyzer.class)
    @Description("Passive liveness positive injection folder of full session images #5")
    public void t09_e2ePositivePassiveLivenessSessionTest() throws IOException, InterruptedException {
        injection.injectFolder("./ocr/sessions/liveness/passive/5", null);
        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 9);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config2.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertTrue(resultsPage.verifyImagesAreReal());
        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
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
        Assert.assertEquals(PassiveVariables.getScore(), 0.9822120070457458);
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

}
