package selenium.ocr.test.newServer.mathilda.environmentVariables;

import api.Variables;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import selenium.BaseTest;

public class licenseDisabled extends BaseTest {

    Variables variables;
    MainPage mainPage;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        driver.get(OCR_DEMO);
        mainPage = new MainPage(driver);
    }

    @Test
    public void t01_ilidLicenseDisabled() {
        mainPage.chooseFromServicesList("Israel ID");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertEquals(variables.getErrorMessage(),  "No initiated library found for service 'IL-ID', or license is not given for this service");
        Assert.assertEquals(variables.getErrorCode(), 2018);
    }

    @Test
    public void t02_ildlLicenseDisabled() {
        mainPage.chooseFromServicesList("Israel Driving License");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertEquals(variables.getErrorMessage(),  "No initiated library found for service 'IL-DL', or license is not given for this service");
        Assert.assertEquals(variables.getErrorCode(), 2018);
    }

    @Test
    public void t03_cniLicenseDisabled() {
        mainPage.chooseFromServicesList("CNI");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertEquals(variables.getErrorMessage(),  "No initiated library found for service 'CNI', or license is not given for this service");
        Assert.assertEquals(variables.getErrorCode(), 2018);
    }

 //   @Test
    public void t04_phcLicenseDisabled() {
        mainPage.chooseFromServicesList("Philippines Cheque");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertEquals(variables.getErrorMessage(),  "No initiated library found for service 'PHL-CHEQUES', or license is not given for this service");
        Assert.assertEquals(variables.getErrorCode(), 2018);
    }

//    @Test
    public void t05_dkdlLicenseDisabled() {
        mainPage.chooseFromServicesList("Denmark Driving License");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertEquals(variables.getErrorMessage(),  "No initiated library found for service 'DK-DL', or license is not given for this service");
        Assert.assertEquals(variables.getErrorCode(), 2018);
    }

}
