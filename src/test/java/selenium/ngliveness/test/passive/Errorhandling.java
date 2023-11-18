package selenium.ngliveness.test.passive;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ScanPage;
import utilities.MongoDB.MongoHandler;
import utilities.RetryAnalyzer;

import java.io.IOException;

public class Errorhandling extends BaseTest {

    MongoHandler mongoHandler;
    Variables variables;
    MainPage mainPage;

    @BeforeClass
    public void resetVariables() {
        mongoHandler = new MongoHandler();
        variables = new Variables();
        mainPage = new MainPage(driver);
    }

    @BeforeMethod
    public void linkToLiveness() throws InterruptedException {
 //       Thread.sleep(10000);
        if (System.getProperty("LIVENESS_NEW").equalsIgnoreCase("false")) {
            driver.get(LIVENESS_DEMO);
        } else {
            driver.get(LIVENESS_DEMO_NEW);
        }


        Errorhandling.chooseConfigFile();
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseFromServicesList("Liveness");
    }

    public static void chooseConfigFile() {
        MainPage mainPage = new MainPage(driver);
        if (System.getProperty("LIVENESS_NEW").equalsIgnoreCase("false")) {
            mainPage.chooseConfigFile("mathilda.json");
        } else {
            mainPage.chooseConfigFile(System.getProperty("COMPANY_ID"));
        }
    }


    @Test(description = "liveness demo -No face found- error message",retryAnalyzer = RetryAnalyzer.class)
    @Description("Liveness demo -No face found- error message by injecting image without face")
    public void t01_noFaceFoundDemoErrorMessage() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/face_not_found.jpg", null, 1, true, "פנים לא זוהו"));
//        driver.navigate().refresh();

    }

    @Test(description = "liveness demo -Too many faces- error message",retryAnalyzer = RetryAnalyzer.class)
    @Description("Liveness demo -Too many faces- error message by injecting image with many faces")
    public void t02_tooManyFacesDemoErrorMessage() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/too_many_faces.jpg", null, 1, true, "זוהה יותר מפרצוף אחד"));
//        driver.navigate().refresh();

    }

    @Test(description = "liveness demo -Face too close- error message",retryAnalyzer = RetryAnalyzer.class)
    @Description("Liveness demo -Face too close- error message by injecting image with close face")
    public void t03_faceToCloseDemoErrorMessage() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/face_too_close.jpg", null, 1, true, "פרצופך קרוב מדי למצלמה"));
//        driver.navigate().refresh();
    }

    @Test(description = "liveness demo -Look straight- error message",retryAnalyzer = RetryAnalyzer.class)
    @Description("Liveness demo -Look straight- error message by injecting side image face")
    public void t04_lookStraightDemoErrorMessage() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/barLeft.jpg", null, 1, true, "הבט ישר בבקשה"));
 //       driver.navigate().refresh();
    }


    @Test(description = "liveness demo -Come closer- error message",retryAnalyzer = RetryAnalyzer.class)
    @Description("Liveness demo -Come closer- error message by injecting image with far face")
    public void t05_comeCloserDemoErrorMessage() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/face_too_small.jpg", null, 1, true, "קרב את פרצופך למסך בבקשה"));
//        driver.navigate().refresh();
    }

    @Test(description = "liveness demo -Face not properly illuminated- error message",retryAnalyzer = RetryAnalyzer.class)
    @Description("Liveness demo -Face not properly illuminated- error message by injecting image with far face")
    public void t06_faceNotProperlyIlluminatedDemoErrorMessage() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/too_bright.jpg", null, 1, true, "תאורה אינה מספקת עבור עיבוד, נסה למקם את עצמך במקום מואר יותר"));
//        driver.navigate().refresh();
    }

    @Test(description = "liveness demo -Face not in focus- error message",retryAnalyzer = RetryAnalyzer.class)
    @Description("Liveness demo -Face not in focus- error message by injecting image with far face")
    public void t07_faceNotInFocusDemoErrorMessage() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/roleMessages/BadFaceFocus/Blur.jpg", null, 1, true, "התקבלה תמונה מטושטשת"));
//        driver.navigate().refresh();
    }

    @Test(description = "liveness demo -Face mask detected- error message",retryAnalyzer = RetryAnalyzer.class)
    @Description("Liveness demo -Face mask detected- error message by injecting image with far face")
    public void t08_faceMaskDetectedDemoErrorMessage() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Thread.sleep(20000);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/moranWithFaceMask.jpg", null, 1, true, "מסכה זוהתה"));
//        driver.navigate().refresh();
    }
}
