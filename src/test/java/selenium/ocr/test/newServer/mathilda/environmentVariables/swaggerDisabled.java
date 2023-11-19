package selenium.ocr.test.newServer.mathilda.environmentVariables;

import api.Variables;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import selenium.BaseTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class swaggerDisabled extends BaseTest {

    ResultsPage resultsPage;
    Variables variables;
    MainPage mainPage;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        mainPage = new MainPage(driver);
        resultsPage = new ResultsPage(driver);
        driver.get("https://ocr-qa.scanovate.com/demo/swagger");
    }

    @Test
    public void t01_swaggerDisabled() throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();

        Assert.assertTrue(driver.getPageSource().contains("The requested URL was not found on this server"));
        Assert.assertEquals(con.getResponseCode(), 404);
    }
}
