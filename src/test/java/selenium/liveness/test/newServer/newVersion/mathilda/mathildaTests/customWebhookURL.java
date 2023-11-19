package selenium.liveness.test.newServer.newVersion.mathilda.mathildaTests;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;

public class customWebhookURL extends BaseTest {

    Variables variables = new Variables();

    @Test(description = "liveness mathilda test of custom webhook url from the config file")
    @Description("Mathilda test of custom webhook url from the config file when choosing liveness")
    public void t01_LivenessCustomWebhookUrl() {
        MainPage mainPage = new MainPage(driver);
        driver.get(LIVENESS_DEMO);
        mainPage.chooseConfigFile("configWebhook.json");
        mainPage.chooseFromServicesList("Liveness");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.valueToCheck(variables, "name"), "name\n" + "ServerError");
        Assert.assertEquals(resultsPage.valueToCheck(variables, "message"), "message\n" + "Failed to reach webhook");
        Assert.assertEquals(resultsPage.valueToCheck(variables, "code"), "code\n" + "2007");
    }
}
