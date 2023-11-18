package selenium.liveness.test.id_rnd;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.liveness.pageObject.LivenessPage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Id_rndTests extends BaseTest {

    protected String fakeImages = "./selenium.liveness/fakeImages";
    protected String trueImages = "./selenium.liveness/trueImages";

    List<String> success = new ArrayList<>();
    List<String> fail = new ArrayList<>();
    List<String> timeout = new ArrayList<>();


    @Test(description = "Scan fake images")
    @Description("Scan fake images")
    public void t01_scanFakeImages() throws IOException {
        driver.get("https://ibeta-liveness.scanovate.com/demo/");
        for (int i = 0; i < fakeImages.length(); i++) {
            File folder = new File(fakeImages);
            File[] listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                LivenessPage livenessPage = new LivenessPage(driver);
                livenessPage.startButton();
                livenessPage.scanLiveness(file.getAbsolutePath());
                if (livenessPage.resultTitle().contains("Failure!")) {
                    fail.add(file.getName());
                    driver.navigate().refresh();
                } else if (livenessPage.resultTitle().contains("Great!")) {
                    success.add(file.getName());
                    driver.navigate().refresh();
                } else if (livenessPage.resultTitle().contains("Timeout!")) {
                    timeout.add(file.getName());
                    driver.navigate().refresh();
                }
            }
            break;
        }
        System.out.println("Failure: " + fail.size() + "\n" + fail);
        System.out.println("Great: " + success.size() + "\n" + success);
        System.out.println("Timeout: " + timeout.size() + "+\n" + timeout);
    }

    @Test(description = "Scan true images")
    @Description("Scan true images")
    public void t02_scanTrueImages() throws IOException {
        driver.get("https://ibeta-liveness.scanovate.com/demo/");
        for (int i = 0; i < trueImages.length(); i++) {
            File folder = new File(trueImages);
            File[] listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                LivenessPage livenessPage = new LivenessPage(driver);
                livenessPage.startButton();
                livenessPage.scanLiveness(file.getAbsolutePath());
                if (livenessPage.resultTitle().contains("Failure!")) {
                    fail.add(file.getName());
                    driver.navigate().refresh();
                } else if (livenessPage.resultTitle().contains("Great!")) {
                    success.add(file.getName());
                    driver.navigate().refresh();
                } else if (livenessPage.resultTitle().contains("Timeout!")) {
                    timeout.add(file.getName());
                    driver.navigate().refresh();
                }
            }
            break;
        }
        System.out.println("Failure: " + fail.size() + "\n" + fail);
        System.out.println("Great: " + success.size() + "\n" + success);
        System.out.println("Timeout: " + timeout.size() + "+\n" + timeout);
    }

    @Test(description = "Scan single Liveness")
    @Description("Scan single Liveness")
    public void t03_scanSingleLiveness() throws IOException {
        driver.get("https://liveness-dev.scanovate.com/demo/");
        for (int i = 0; i <= 100; i++) {
            LivenessPage livenessPage = new LivenessPage(driver);
            livenessPage.startButton();
            livenessPage.scanLiveness("./liveness/liad/liadFace.jpg");
            if (livenessPage.errorMessageIsDisplayed() == true) {
                System.out.println("error occurred");
            } else if (livenessPage.resultTitle().contains("Failure!")) {
                System.out.println(livenessPage.resultTitle());
                System.out.println("number of test:" + i);
            } else if (livenessPage.resultTitle().contains("Great!")) {
                System.out.println(livenessPage.resultTitle());
            } else if (livenessPage.resultTitle().contains("Timeout!")) {
                System.out.println(livenessPage.resultTitle());
            }
            driver.navigate().refresh();
        }
    }


}