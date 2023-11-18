package selenium.liveness.test.newServer.newVersion.mathilda.environmentVariables;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.StartSessionVariables;
import utilities.TestUtils;

import java.io.IOException;

public class NoWebhookURL extends BaseTest {

    Variables variables = new Variables();
    MongoHandler mongoHandler = new MongoHandler();

    @Test(description = "liveness without webhook URL")
    @Description("liveness without webhook URL")
    public void t01_livenessWithoutWebhookURL() throws IOException, InterruptedException {
        driver.get(LIVENESS_DEMO);
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        injection.sttInjection("./liveness/audio/stt/fullStt");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 14);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), variables.getStatus());
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertEquals(resultsPage.valueToCheck(variables, "score\n" + "0.9221184849739075"), "score\n" + "0.9221184849739075");
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");

        Assert.assertNotNull(resultsPage.valueToCheck(variables, "stt_text"));
        Assert.assertEquals(resultsPage.valueToCheck(variables, "original_text"), "original_text\n" + "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
        Assert.assertEquals(resultsPage.valueToCheck(variables, "threshold\n" + "0.7"), "threshold\n" + "0.7");
        Assert.assertEquals(resultsPage.valueToCheck(variables, "language\n"), "language\n" + "he-IL");
        Assert.assertNotNull(resultsPage.valueToCheck(variables, "score"));
        Assert.assertTrue(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]) > Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));
        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]));
        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNull(StartSessionVariables.getCaseId());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());

        Assert.assertNull(CommonVariables.getCaseId());
    }
}
