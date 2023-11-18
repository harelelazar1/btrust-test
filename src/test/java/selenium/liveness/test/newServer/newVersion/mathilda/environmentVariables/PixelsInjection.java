package selenium.liveness.test.newServer.newVersion.mathilda.environmentVariables;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;

import java.io.File;
import java.io.IOException;

public class PixelsInjection extends BaseTest {

    String cPallet = "FFFFFFFFA500FFFF000000FF";
    Variables variables;
    MongoHandler mongoHandler;
    MainPage mainPage;
    Injection injection;

    @BeforeMethod
    public void initSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        mainPage = new MainPage(driver);
        injection = new Injection(driver);
        driver.get(LIVENESS_DEMO);
    }

    @AfterMethod
    public void verifyStatusIsSuccess() {
        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertTrue(variables.isSuccess());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());

        Assert.assertEquals(CommonVariables.getStatus(), "success");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Process ended successfully");
    }

    @Test(description = "liveness passive with cPallet pixel injection")
    @Description("Liveness passive with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t01_livenessPassivePixelInjection() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Liveness", true, cPallet);
        File livenessPassivePixelated = new File("./liveness/pixelatedImages/livenessPassivePixelated.jpg");
        injection.injectPixels(cPallet, new File("./liveness/apiPic/barCenter.jpg"), 1f, livenessPassivePixelated);
        injection.imageInjection(String.valueOf(livenessPassivePixelated), 1, false);
        injection.sttInjection("./liveness/audio/stt/fullStt");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 14);
    }

    @Test(description = "liveness active with cPallet pixel injection")
    @Description("Liveness active with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t02_livenessActivePixelInjection() throws IOException, InterruptedException {
        mainPage.chooseConfigFile("qa_config.json");
        mainPage.chooseFromServicesList("Liveness", true, cPallet);
        File livenessPassivePixelated = new File("./liveness/pixelatedImages/livenessPassivePixelated.jpg");
        File livenessLeftPixelated = new File("./liveness/pixelatedImages/livenessLeftPixelated.jpg");
        File livenessRightPixelated = new File("./liveness/pixelatedImages/livenessRightPixelated.jpg");
        injection.injectPixels(cPallet, new File("./liveness/apiPic/barCenter.jpg"), 1f, livenessPassivePixelated);
        injection.injectPixels(cPallet, new File("./liveness/apiPic/barLeft.jpg"), 1f, livenessLeftPixelated);
        injection.injectPixels(cPallet, new File("./liveness/apiPic/barRight.jpg"), 1f, livenessRightPixelated);

        injection.livenessInjection2(String.valueOf(livenessLeftPixelated), String.valueOf(livenessPassivePixelated), String.valueOf(livenessRightPixelated));
        injection.otpInjection("./liveness/audio/otp/otpAudioChunks");
        injection.sttInjection("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 15);
    }
}
