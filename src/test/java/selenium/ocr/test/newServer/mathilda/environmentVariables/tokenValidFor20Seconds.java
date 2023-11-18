package selenium.ocr.test.newServer.mathilda.environmentVariables;

import api.Variables;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import selenium.BaseTest;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;

public class tokenValidFor20Seconds extends BaseTest {

    ResultsPage resultsPage;
    Variables variables;
    MainPage mainPage;
    MongoHandler mongoHandler;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        mainPage = new MainPage(driver);
        resultsPage = new ResultsPage(driver);
        driver.get(OCR_DEMO);
    }

    @Test
    public void t01_iLiD_20secondsTimeout() throws InterruptedException {
        mainPage.chooseFromServicesList("Israel ID");
        System.out.println("Sleep for 20 seconds");
        Thread.sleep(20000);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");
        // Mongo Testing ****************************************************************
        mongoHandler.queryHandler(variables.getCaseId(), "timeout");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
    }

    @Test
    public void t02_iLdL_20secondsTimeout() throws InterruptedException {
        mainPage.chooseFromServicesList("Israel Driving License");
        System.out.println("Sleep for 20 seconds");
        Thread.sleep(20000);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");
        // Mongo Testing ****************************************************************
        mongoHandler.queryHandler(variables.getCaseId(), "timeout");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
    }

    @Test
    public void t03_cni_20secondsTimeout() throws InterruptedException {
        mainPage.chooseFromServicesList("CNI");
        System.out.println("Sleep for 20 seconds");
        Thread.sleep(20000);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");
        // Mongo Testing ****************************************************************
        mongoHandler.queryHandler(variables.getCaseId(), "timeout");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
    }

    @Test
    public void t04_mrz_20secondsTimeout() throws InterruptedException {
        mainPage.chooseFromServicesList("MRZ");
        System.out.println("Sleep for 20 seconds");
        Thread.sleep(20000);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");
        // Mongo Testing ****************************************************************
        mongoHandler.queryHandler(variables.getCaseId(), "timeout");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
    }

    @Test
    public void t05_dKdL_20secondsTimeout() throws InterruptedException {
        mainPage.chooseFromServicesList("Denmark Driving License");
        System.out.println("Sleep for 20 seconds");
        Thread.sleep(20000);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");
        // Mongo Testing ****************************************************************
        mongoHandler.queryHandler(variables.getCaseId(), "timeout");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
    }

    @Test
    public void t06_phc_20secondsTimeout() throws InterruptedException {
        mainPage.chooseFromServicesList("Philippines Cheque");
        System.out.println("Sleep for 20 seconds");
        Thread.sleep(20000);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");
        // Mongo Testing ****************************************************************
        mongoHandler.queryHandler(variables.getCaseId(), "timeout");
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
    }
}