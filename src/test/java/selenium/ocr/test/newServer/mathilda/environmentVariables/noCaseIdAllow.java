package selenium.ocr.test.newServer.mathilda.environmentVariables;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import selenium.BaseTest;

public class noCaseIdAllow extends BaseTest {

    Variables variables;
    MainPage mainPage;
    ResultsPage resultsPage;

    @BeforeMethod
    public void initSession() {
        variables = new Variables();
        mainPage = new MainPage(driver);
        resultsPage = new ResultsPage(driver);
        driver.get(OCR_DEMO);
    }

    @Test(description = "ocr without caseId allow")
    @Description("ocr without caseId allow IL-ID library")
    public void t01_ocrWithoutCaseIdILID() {
        mainPage.chooseFromServicesList("Israel ID");
        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertEquals(variables.getErrorMessage(),  "Token was not provided");
        Assert.assertEquals(variables.getErrorCode(), 1005);
    }

    @Test(description = "ocr without caseId allow")
    @Description("ocr without caseId allow IL-DL library")
    public void t02_ocrWithoutCaseIdILDL() {
        mainPage.chooseFromServicesList("Israel Driving License");
        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertEquals(variables.getErrorMessage(),  "Token was not provided");
        Assert.assertEquals(variables.getErrorCode(), 1005);
    }

    @Test(description = "ocr without caseId allow")
    @Description("ocr without caseId allow CNI library")
    public void t03_ocrWithoutCaseIdCNI() {
        mainPage.chooseFromServicesList("CNI");
        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertEquals(variables.getErrorMessage(),  "Token was not provided");
        Assert.assertEquals(variables.getErrorCode(), 1005);
    }

    @Test(description = "ocr without caseId allow")
    @Description("ocr without caseId allow MRZ library")
    public void t04_ocrWithoutCaseIdMRZ() {
        mainPage.chooseFromServicesList("MRZ");
        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertEquals(variables.getErrorMessage(),  "Token was not provided");
        Assert.assertEquals(variables.getErrorCode(), 1005);
    }

 //   @Test(description = "ocr without caseId allow")
    @Description("ocr without caseId allow DK-DL library")
    public void t05_ocrWithoutCaseIdDKDL() {
        mainPage.chooseFromServicesList("Denmark Driving License");
        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertEquals(variables.getErrorMessage(),  "Token was not provided");
        Assert.assertEquals(variables.getErrorCode(), 1005);
    }

 //   @Test(description = "ocr without caseId allow")
    @Description("ocr without caseId allow PHC library")
    public void t06_ocrWithoutCaseIdDKDL() {
        mainPage.chooseFromServicesList("Philippines Cheque");
        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertEquals(variables.getErrorMessage(),  "Token was not provided");
        Assert.assertEquals(variables.getErrorCode(), 1005);
    }
}
