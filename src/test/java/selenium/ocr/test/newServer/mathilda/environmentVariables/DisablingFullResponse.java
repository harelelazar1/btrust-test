package selenium.ocr.test.newServer.mathilda.environmentVariables;

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

import javax.sound.sampled.UnsupportedAudioFileException;
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
        driver.get(OCR_DEMO);
    }

    @Test
    public void t01_disablingFullResponseOfBioId() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Israel ID");
        injection.imageInjectionConfigure("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack1.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 3);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());
    }

    @Test
    public void t02_disablingFullResponseOfBlueId() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Israel ID");
        injection.imageInjectionConfigure("./ocr/blueID/liad/blueID_color1.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 3);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());
    }

    @Test
    public void t03_disablingFullResponseOfGreenId() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Israel ID");
        injection.imageInjectionConfigure("./ocr/greenID/bar/BarGreenID1.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 3);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());
    }

    @Test
    public void t04_disablingFullResponseOfNewILDL() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Israel Driving License");
        injection.imageInjectionConfigure("./ocr/dl/newDLFront.jpg", "./ocr/dl/newDLBack.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 3);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());
    }

    @Test
    public void t05_disablingFullResponseOfOldILDL() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Israel Driving License");
        injection.imageInjectionConfigure("./ocr/dl/oldDLFront.jpg", "./ocr/dl/oldDLBack.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 3);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());
    }

    @Test
    public void t06_disablingFullResponseOfNewMrz() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("MRZ");
        injection.imageInjectionConfigure("./ocr/mrz/nadav/nadavMrz.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 3);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());
    }

    @Test
    public void t07_disablingFullResponseOfOldMrz() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("MRZ");
        injection.imageInjectionConfigure("./ocr/mrz/guy/guyMrz.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 3);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());
    }

//    @Test
    public void t08_disablingFullResponseOfOldDKDL() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Denmark Driving License");
        injection.imageInjectionConfigure("./ocr/denmarkDrivingLicense/oldDKDL/210015722.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 3);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());
    }

//    @Test
    public void t09_disablingFullResponseOfNewDKDL() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Denmark Driving License");
        injection.imageInjectionConfigure("./ocr/denmarkDrivingLicense/newDKDL/314764922.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 3);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());
    }

 //   @Test
    public void t10_disablingFullResponseOfPHC() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Philippines Cheque");
        injection.imageInjection("./ocr/cheque/PhilippinesCheque/RCBCCheque2.jpg", 1, false);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 3);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());
    }

    @Test
    public void t11_disablingFullResponseOfCNI() throws IOException, InterruptedException, UnsupportedAudioFileException {
        mainPage.chooseFromServicesList("CNI");
        injection.imageInjectionWithTilt("./ocr/cni/good_fr_3.jpg", "./ocr/cni/good_fr_back_1.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 3);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertTrue(variables.isSuccess());
    }
}
