package selenium.liveness.test.newServer.newVersion.mathilda.environmentVariables;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;

public class NoSttCredentials extends BaseTest {

    Variables variables = new Variables();

    @Test(description = "liveness without stt credentials")
    @Description("liveness without stt credentials")
    public void t01_livenessWithoutSttCredentials() {
        driver.get(LIVENESS_DEMO);
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseFromServicesList("Liveness");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.valueToCheck(variables, "name"), "name\n" + "RequestStatusError");
        Assert.assertEquals(resultsPage.valueToCheck(variables, "message"), "message\n" + "Request status error");
        Assert.assertEquals(resultsPage.valueToCheck(variables, "code"), "code\n" + "1112");
    }
}
