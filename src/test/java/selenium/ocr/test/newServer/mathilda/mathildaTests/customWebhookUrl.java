package selenium.ocr.test.newServer.mathilda.mathildaTests;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import selenium.BaseTest;

public class customWebhookUrl extends BaseTest {

    Variables variables;
    MainPage mainPage;

    @BeforeMethod
    public void initSession() {
        variables = new Variables();
        mainPage = new MainPage(driver);
        driver.get(OCR_DEMO);
        mainPage.chooseConfigFile("configWebhook.json");
    }

    @AfterMethod
    public void verifyWebhookUrlChanged() {
        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertEquals(variables.getErrorMessage(),  "Failed to reach webhook");
        Assert.assertEquals(variables.getErrorCode(), 2007);
    }

    @Test(description = "il id mathilda test of custom webhook url from the config file")
    @Description("Mathilda test of custom webhook url from the config file when choosing IL-ID ocr type")
    public void t01_ILIDCustomWebhookUrl() {
        mainPage.chooseFromServicesList("Israel ID");
    }

    @Test(description = "il dl mathilda test of custom webhook url from the config file")
    @Description("Mathilda test of custom webhook url from the config file when choosing IL-DL ocr type")
    public void t02_ILDLCustomWebhookUrl() {
        mainPage.chooseFromServicesList("Israel Driving License");
    }

    @Test(description = "cni mathilda test of custom webhook url from the config file")
    @Description("Mathilda test of custom webhook url from the config file when choosing CNI ocr type")
    public void t03_CNICustomWebhookUrl() {
        mainPage.chooseFromServicesList("CNI");
    }

    @Test(description = "mrz mathilda test of custom webhook url from the config file")
    @Description("Mathilda test of custom webhook url from the config file when choosing MRZ ocr type")
    public void t04_MRZCustomWebhookUrl() {
        mainPage.chooseFromServicesList("MRZ");
    }

}
