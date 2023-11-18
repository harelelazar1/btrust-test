package selenium.liveness.test.newServer.newVersion.mathilda.mathildaTests;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.Liveness.PassiveVariables;

import java.io.IOException;

public class passiveThresholdViaMathilda extends BaseTest {

    Variables variables = new Variables();
    MongoHandler mongoHandler = new MongoHandler();
    double passiveThresholdFromConfigFile = 0.5;

    @Test(description = "liveness passive that the passive threshold changed via the mathilda file (changeThreshold.json)")
    @Description("Liveness passive that the passive threshold changed via the mathilda file (changeThreshold.json)")
    public void t01_livenessWithPassiveThresholdChangedViaMathilda() throws IOException, InterruptedException {
        driver.get(LIVENESS_DEMO);
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseConfigFile("changeSttThreshold.json");
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
        Assert.assertEquals(variables.getConfigFileName(), "changeSttThreshold.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(resultsPage.valueToCheck(variables, "threshold\n" + passiveThresholdFromConfigFile), "threshold\n" + passiveThresholdFromConfigFile);
        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());

        Assert.assertEquals(PassiveVariables.getThreshold(), passiveThresholdFromConfigFile);
    }
}
