package selenium.liveness.test.newServer.newVersion.passive;

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
import utilities.MongoDB.Variables.StartSessionVariables;

import java.io.IOException;

public class Functionality extends BaseTest {

    Variables variables;
    MongoHandler mongoHandler;
    MainPage mainPage;

    @BeforeMethod
    public void initSession() throws InterruptedException {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        mainPage = new MainPage(driver);
        driver.get(LIVENESS_DEMO);
        Thread.sleep(3000);
        mainPage.chooseFromServicesList("Liveness");
    }

    @Test(description = "demo passive liveness with face mask")
    @Description("Demo of passive liveness with face mask by injecting face with mask image")
    public void t01_passiveLivenessWithFaceMask() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/moranWithFaceMask.jpg", null, 1, true, "Face mask detected"));

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.resultsWait(80);;
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
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

    @Test(description = "demo passive liveness with illuminated face")
    @Description("Demo of passive liveness illuminated face by injecting illuminated face image")
    public void t02_passiveLivenessIlluminatedFace() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/too_bright.jpg", null, 1, true, "Face not properly illuminated"));

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.resultsWait(80);;
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
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

    @Test(description = "demo passive liveness with face not in focus")
    @Description("Demo of passive liveness with face not in focus by injecting face not in focus image")
    public void t03_passiveLivenessNoFocusFace() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/roleMessages/BadFaceFocus/Blur.jpg", null, 1, true, "Face not in focus"));

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.resultsWait(80);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
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
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
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
}
