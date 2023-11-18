package selenium.liveness.test.newServer.newVersion.mathilda.environmentVariables;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;

public class LivenessLicenseDisable extends BaseTest {

    Variables variables = new Variables();

    @Test(description = "liveness license disabled")
    @Description("liveness license disabled")
    public void t01_livenessLicenseDisabled() {
        driver.get(LIVENESS_DEMO);
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseFromServicesList("Liveness");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.valueToCheck(variables, "name"), "name\n" + "ServerError");
        Assert.assertEquals(resultsPage.valueToCheck(variables, "message"), "message\n" + "No initiated library found for service 'LIVENESS', or license is not given for this service");
        Assert.assertEquals(resultsPage.valueToCheck(variables, "code"), "code\n" + "2018");
    }
}
