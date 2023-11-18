package selenium.liveness.test.newServer.newVersion.mathilda.environmentVariables;

import api.ApiResponse;
import api.Variables;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import utilities.MongoDB.MongoHandler;

import java.io.IOException;

public class DisablingFullResponse extends BaseTest {

    Variables variables;
    MongoHandler mongoHandler;
    MainPage mainPage;
    ApiResponse apiResponse;
    Injection injection;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
        mainPage = new MainPage(driver);
        injection = new Injection(driver);
        driver.get(LIVENESS_DEMO);
    }

    @Test
    public void t01_disablingFullResponseOfLivenessPassive() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Liveness");
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        injection.sttInjection("./liveness/audio/stt/fullStt");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 3);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());
    }

    @Test
    public void t02_disablingFullResponseOfLivenessActive() throws IOException, InterruptedException {
        mainPage.chooseConfigFile("qa_config.json");
        mainPage.chooseFromServicesList("Liveness");
        injection.livenessInjection2("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/barCenter.jpg", "./liveness/apiPic/barRight.jpg");
        injection.otpInjection("./liveness/audio/otp/otpAudioChunks");
        injection.sttInjection("./liveness/audio/stt/fullStt");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 3);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());
    }
}
