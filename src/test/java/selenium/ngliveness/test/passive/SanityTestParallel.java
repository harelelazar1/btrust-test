package selenium.ngliveness.test.passive;

import api.Variables;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import selenium.BaseTest;
import selenium.BaseTestNew;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Liveness.LivenessHandlers;
import utilities.MongoDB.Variables.Liveness.PassiveVariables;
import utilities.MongoDB.Variables.Liveness.SttVariables;
import utilities.MongoDB.Variables.StartSessionVariables;
import utilities.RetryAnalyzer;
import utilities.TestUtils;

import java.io.IOException;

//@Test(description = "Class-level parallel execution", parallel = TestNGParallelMode.CLASSES)
public class SanityTestParallel extends BaseTestNew {

    MongoHandler mongoHandler;
    Variables variables;
    MainPage mainPage;


    @BeforeClass
    public void resetVariables() {
        mongoHandler = new MongoHandler();
        variables = new Variables();
        mainPage = new MainPage(driver.get());
    }


    @Test(description = "passive liveness sanity demo")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Demo of passive liveness sanity test by injecting straight face images, and then injecting audio for the stt part")
    public void t01_livenessPassiveSanity() throws IOException, InterruptedException {
        driver.get().navigate().to("https://qa-ngliveness.scanovateoncloud.com/");
        mainPage.chooseConfigFile(System.getProperty("COMPANY_ID"));
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver.get());
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
//        injection.sttInjection("./liveness/audio/stt/fullStt");

//        injection.imageInjection1("./liveness/apiPic/barCenter.jpg", "./liveness/fakeImages/12-11-2020_17-30-20_7251777322.jpg",1,1,false,true);


        ResultsPage resultsPage = new ResultsPage(driver.get());
        resultsPage.collectResults(variables);


        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), variables.getStatus());


        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getDblScore() - 0.8978993892669678f) < 0.1);
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");


        Assert.assertTrue(resultsPage.verifyImagesAreReal());


    }

    @Test(description = "passive liveness sanity demo")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Demo of passive liveness sanity test by injecting straight face images, and then injecting audio for the stt part")
    public void t02_livenessPassiveSanity() throws IOException, InterruptedException {
        driver.get().navigate().to("https://qa-ngliveness.scanovateoncloud.com");
        mainPage.chooseConfigFile(System.getProperty("COMPANY_ID"));
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver.get());
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
//        injection.sttInjection("./liveness/audio/stt/fullStt");

//        injection.imageInjection1("./liveness/apiPic/barCenter.jpg", "./liveness/fakeImages/12-11-2020_17-30-20_7251777322.jpg",1,1,false,true);


        ResultsPage resultsPage = new ResultsPage(driver.get());
        resultsPage.collectResults(variables);


        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), variables.getStatus());


        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertTrue(Math.abs(variables.getDblScore() - 0.8978993892669678f) < 0.1);
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");


        Assert.assertTrue(resultsPage.verifyImagesAreReal());

    }
}
