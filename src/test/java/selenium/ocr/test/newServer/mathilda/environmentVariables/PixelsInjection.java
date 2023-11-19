package selenium.ocr.test.newServer.mathilda.environmentVariables;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import selenium.BaseTest;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;

import javax.sound.sampled.UnsupportedAudioFileException;
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
        driver.get(OCR_DEMO);
    }

    @AfterMethod
    public void verifyStatusIsSuccess() {
        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
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

    @Test(description = "blue id with cPallet pixel injection")
    @Description("Blue id with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t01_blueIdPixelInjection() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Israel ID", true, cPallet);
        File blueIdPixelated = new File("./ocr/pixelatedImages/blueIdPixelated.jpg");
        injection.injectPixels(cPallet, new File("./ocr/blueID/liad/blueID_color1.jpg"), 1f, blueIdPixelated);
        injection.imageInjection(String.valueOf(blueIdPixelated), 1, false);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 30);
    }

    @Test(description = "green id with cPallet pixel injection")
    @Description("Green id with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t02_greenIdPixelInjection() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Israel ID", true, cPallet);
        File greenIdPixelated = new File("./ocr/pixelatedImages/greenIdPixelated.jpg");
        injection.injectPixels(cPallet, new File("./ocr/greenID/bar/BarGreenID1.jpg"), 1f, greenIdPixelated);
        injection.imageInjection(String.valueOf(greenIdPixelated), 1, true);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 26);
    }

    @Test(description = "bio id with cPallet pixel injection")
    @Description("Bio id with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t03_bioIdPixelInjection() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Israel ID", true, cPallet);
        File frontBioIdPixelated = new File("./ocr/pixelatedImages/frontBioIdPixelated.jpg");
        File backBioIdPixelated = new File("./ocr/pixelatedImages/backBioIdPixelated.jpg");
        injection.injectPixels(cPallet, new File("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg"), 1f, frontBioIdPixelated);
        injection.injectPixels(cPallet, new File("./ocr/bioID/nitzan/back/NitzanBioBack1.jpg"), 1f, backBioIdPixelated);
        injection.imageInjectionConfigure(String.valueOf(frontBioIdPixelated), String.valueOf(backBioIdPixelated), 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 41);
    }

    @Test(description = "new il-dl with cPallet pixel injection")
    @Description("New IL-DL with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t04_newDlPixelInjection() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Israel Driving License", true, cPallet);
        File frontNewDlPixelated = new File("./ocr/pixelatedImages/frontNewDlPixelated.jpg");
        File backNewDlPixelated = new File("./ocr/pixelatedImages/backNewDlPixelated.jpg");
        injection.injectPixels(cPallet, new File("./ocr/dl/newDLFront.jpg"), 1f, frontNewDlPixelated);
        injection.injectPixels(cPallet, new File("./ocr/dl/newDLBack.jpg"), 1f, backNewDlPixelated);
        injection.imageInjectionConfigure(String.valueOf(frontNewDlPixelated), String.valueOf(backNewDlPixelated), 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 35);
    }

    @Test(description = "old il-dl with cPallet pixel injection")
    @Description("OLd IL-DL with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t05_oldDlPixelInjection() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Israel Driving License", true, cPallet);
        File frontOldDlPixelated = new File("./ocr/pixelatedImages/frontOldDlPixelated.jpg");
        File backOldDlPixelated = new File("./ocr/pixelatedImages/backOldDlPixelated.jpg");
        injection.injectPixels(cPallet, new File("./ocr/dl/oldDLFront.jpg"), 1f, frontOldDlPixelated);
        injection.injectPixels(cPallet, new File("./ocr/dl/oldDLBack.jpg"), 1f, backOldDlPixelated);
        injection.imageInjectionConfigure(String.valueOf(frontOldDlPixelated), String.valueOf(backOldDlPixelated), 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 35);
    }

  //  @Test(description = "old dk-dl with cPallet pixel injection")
    @Description("OLd DL-DL with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t06_oldDkDlPixelInjection() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Denmark Driving License", true, cPallet);
        File oldDkDlPixelated = new File("./ocr/pixelatedImages/oldDkDlPixelated.jpg");
        injection.injectPixels(cPallet, new File("./ocr/denmarkDrivingLicense/oldDKDL/210015722.jpg"), 1f, oldDkDlPixelated);
        injection.imageInjection(String.valueOf(oldDkDlPixelated), 1, false);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 16);
    }

//    @Test(description = "new dk-dl with cPallet pixel injection")
    @Description("New DL-DL with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t07_newDkDlPixelInjection() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Denmark Driving License", true, cPallet);
        File newDkDlPixelated = new File("./ocr/pixelatedImages/newDkDlPixelated.jpg");
        injection.injectPixels(cPallet, new File("./ocr/denmarkDrivingLicense/newDKDL/314764922.jpg"), 1f, newDkDlPixelated);
        injection.imageInjection(String.valueOf(newDkDlPixelated), 1, false);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 16);
    }

 //   @Test(description = "phc with cPallet pixel injection")
    @Description("Phc with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t08_phcPixelInjection() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Philippines Cheque", true, cPallet);
        File phcPixelated = new File("./ocr/pixelatedImages/phcPixelated.jpg");
        injection.injectPixels(cPallet, new File("./ocr/cheque/PhilippinesCheque/BDOCheque.jpg"), 1f, phcPixelated);
        injection.imageInjection(String.valueOf(phcPixelated), 1 , false);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 7);
    }

    @Test(description = "cni with cPallet pixel injection")
    @Description("Cni with cPallet pixel inject into the image and then injecting the new image to the server, inorder to pass the test with 'success' status")
    public void t09_cniPixelInjection() throws IOException, InterruptedException, UnsupportedAudioFileException {
        mainPage.chooseFromServicesList("CNI", true, cPallet);
        File frontCniPixelated = new File("./ocr/pixelatedImages/frontCniPixelated.jpg");
        File backCniPixelated = new File("./ocr/pixelatedImages/backCniPixelated.jpg");
        injection.injectPixels(cPallet, new File("./ocr/cni/good_fr_3.jpg"), 1f, frontCniPixelated);
        injection.injectPixels(cPallet, new File("./ocr/cni/good_fr_back_1.jpg"), 1f, backCniPixelated);
        injection.imageInjectionWithTilt(String.valueOf(frontCniPixelated), String.valueOf(backCniPixelated), 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 13);
    }
}
