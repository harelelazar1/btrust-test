package selenium.ngocr.test.mrz.newMrz;

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


import java.io.IOException;

public class Errorhandling extends BaseTest {

    MainPage mainPage;
    Variables variables;
    MongoHandler mongoHandler;

    @BeforeMethod
    public void resetVariables() {
        mainPage = new MainPage(driver);
        variables = new Variables();
        mongoHandler = new MongoHandler();
        mainPage = new MainPage(driver);
    }

    @BeforeMethod
    public void linkToOCR() {
        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            driver.get(OCR_DEMO);
        } else {
            driver.get(OCR_DEMO_NEW);
        }
    }

    public static void chooseConfigFile() {
        MainPage mainPage = new MainPage(driver);
        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            mainPage.chooseConfigFile("two_sides.json");
        } else {
            mainPage.chooseConfigFile(System.getProperty("COMPANY_ID"));
        }
    }



    @Test(description = "old mrz text hidden")
    @Description("Old mrz text hidden")
    public void t01_e2eNewMrzTextHidden() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("MRZ");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/mrz/errorhandling/nadavMrzTextRemoved.jpg", null, 1, false, null);


        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getErrorCode(), 1112);
        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertNotNull(variables.getErrorMessage());

    }








}
