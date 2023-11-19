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
import utilities.MongoDB.Variables.Liveness.SttVariables;

import java.io.IOException;

public class EnglishStt extends BaseTest {

    Variables variables = new Variables();
    MongoHandler mongoHandler = new MongoHandler();

    @Test(description = "liveness with english stt")
    @Description("Liveness with english stt")
    public void t01_livenessWithEnglishSTT() throws IOException, InterruptedException {
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

        Assert.assertEquals(resultsPage.valueToCheck(variables, "original_text"), "original_text\n" + "Hello World, my name is automation");
        Assert.assertEquals(resultsPage.valueToCheck(variables, "language\n"), "language\n" + "en-US");
        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());

        Assert.assertEquals(SttVariables.getLanguage(), "en-US");
        Assert.assertEquals(SttVariables.getOriginalText(), "Hello World, my name is automation");
    }
}
