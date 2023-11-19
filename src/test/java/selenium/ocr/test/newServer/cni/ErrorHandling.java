package selenium.ocr.test.newServer.cni;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import selenium.BaseTest;
import utilities.MongoDBConnection;
import utilities.MongoDBVariables;

import java.io.IOException;

public class ErrorHandling extends BaseTest {

    Variables variables = new Variables();
    MongoDBConnection mongoDBConnection = new MongoDBConnection();
    MongoDBVariables mongoDBVariables = new MongoDBVariables();

    @BeforeMethod
    public void startSession() {
        driver.get(OCR_DEMO);
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseFromServicesList("Europe Card");
    }

    @Test(description = "cni wrong side error message")
    @Description("Cni demo test of wrong side error message")
    public void t01_wrongSideErrorMessage() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./ocr/cni/France_CNI_Back.jpg", null, 1, true, "Wrong side"));
        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(OCR_TIMEOUT), 6);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        Assert.assertNotNull(mongoDBVariables.getObjectId());
        Assert.assertEquals(mongoDBVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(mongoDBVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(mongoDBVariables.getServiceType(), "CNI");
        Assert.assertEquals(mongoDBVariables.getStatus(), "session_start");
        Assert.assertEquals(mongoDBVariables.getWorkingMode(), "multi");

        mongoDBConnection.mongoDBHandler(mongoDBVariables, "events", variables.getCaseId(), "timeout");

        Assert.assertNotNull(mongoDBVariables.getObjectId());
        Assert.assertEquals(mongoDBVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(mongoDBVariables.getServiceType(), "CNI");
        Assert.assertEquals(mongoDBVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(mongoDBVariables.getStatus(), "timeout");
        Assert.assertEquals(mongoDBVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(mongoDBVariables.isSuccess());
        Assert.assertNotNull(mongoDBVariables.getVideo());
        Assert.assertEquals(mongoDBVariables.getWorkingMode(), "multi");

        Assert.assertEquals(mongoDBVariables.getActionType(), "stage");
        Assert.assertEquals(mongoDBVariables.getOrder(), 1);
        Assert.assertEquals(mongoDBVariables.getTypeUnderStage(), "front");
        Assert.assertNotNull(mongoDBVariables.getLastReceivedImage());
        Assert.assertEquals(mongoDBVariables.getStageStatus(), "timeout");

        Assert.assertEquals(mongoDBVariables.getServerType(), "modular-server-ocr");
    }

}
