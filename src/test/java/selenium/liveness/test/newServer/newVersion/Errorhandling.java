package selenium.liveness.test.newServer.newVersion;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ScanPage;
import java.io.IOException;

public class Errorhandling extends BaseTest {

    @BeforeMethod
    public void initSession() {
        driver.get(LIVENESS_DEMO);
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseFromServicesList("Liveness");
    }

    @Test(description = "liveness demo -No face found- error message")
    @Description("Liveness demo -No face found- error message by injecting image without face")
    public void t01_noFaceFoundDemoErrorMessage() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/face_not_found.jpg", null, 1, true, "No face found"));
    }

 //   @Test(description = "liveness demo -Too many faces- error message")
    @Description("Liveness demo -Too many faces- error message by injecting image with many faces")
    public void t02_tooManyFacesDemoErrorMessage() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/too_many_faces.jpg", null, 1, true, "Too many faces"));
    }

    @Test(description = "liveness demo -Face too close- error message")
    @Description("Liveness demo -Face too close- error message by injecting image with close face")
    public void t03_faceToCloseDemoErrorMessage() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/face_too_close.jpg", null, 1, true, "face too close"));
    }

    @Test(description = "liveness demo -Look straight- error message")
    @Description("Liveness demo -Look straight- error message by injecting side image face")
    public void t04_lookStraightDemoErrorMessage() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/barLeft.jpg", null, 1, true, "Look straight"));
    }

    @Test(description = "liveness demo -Look left/right- error message")
    @Description("Liveness demo -Look left/right- error message by injecting side image face")
    public void t05_lookLeftOrRightDemoErrorMessage() throws InterruptedException, IOException {
        driver.get(LIVENESS_DEMO);
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseConfigFile("qa_config.json");
        mainPage.chooseFromServicesList("Liveness");

        Injection injection = new Injection(driver);
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        ScanPage scanPage = new ScanPage(driver);
        System.out.println(scanPage.getInstructionsTitle());
        switch (scanPage.getInstructionsTitle()) {
            case "הזיזו את הראש שמאלה": {
                Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/barCenter.jpg", null, 1, true, "הזיזו את הראש שמאלה"));
                break;
            }
            case "הזיזו את הראש ימינה": {
                Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/barCenter.jpg", null, 1, true, "הזיזו את הראש ימינה"));
                break;
            }
        }
    }

    @Test(description = "liveness demo -Come closer- error message")
    @Description("Liveness demo -Come closer- error message by injecting image with far face")
    public void t06_comeCloserDemoErrorMessage() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/face_too_small.jpg", null, 1, true, "Come closer"));
    }

    @Test(description = "liveness demo -Face not properly illuminated- error message")
    @Description("Liveness demo -Face not properly illuminated- error message by injecting image with far face")
    public void t07_faceNotProperlyIlluminatedDemoErrorMessage() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/too_bright.jpg", null, 1, true, "Face not properly illuminated"));
    }

    @Test(description = "liveness demo -Face not in focus- error message")
    @Description("Liveness demo -Face not in focus- error message by injecting image with far face")
    public void t08_faceNotInFocusDemoErrorMessage() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/roleMessages/BadFaceFocus/Blur.jpg", null, 1, true, "Face not in focus"));
    }

    @Test(description = "liveness demo -Face mask detected- error message")
    @Description("Liveness demo -Face mask detected- error message by injecting image with far face")
    public void t09_faceMaskDetectedDemoErrorMessage() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./liveness/apiPic/moranWithFaceMask.jpg", null, 1, true, "Face mask detected"));
    }
}
