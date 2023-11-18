package btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.parallel;

import btrust.onboardingProcess.ui.pagesObject.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ParallelTest {

    final int MAX_THREADS = 2;
    private final List<WebDriver> drivers = new ArrayList<>();


//    @BeforeClass(alwaysRun = true)
    private void createDrivers(ITestContext testContext) {
        String[] browsers;
        if (MAX_THREADS == 1) {
            browsers = new String[]{"chrome"};
        } else if (MAX_THREADS == 2) {
            browsers = new String[]{"chrome", "firefox"};
        } else {
            Assert.fail("max 2 threads allowed");
        }
        //    String[] browsers =new String[]{"firefox"};
        WebDriver driver;
        for (String b : browsers) {
            switch (b) {
                case ("firefox"):
                    String user_agent = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16";
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions ffOptions = new FirefoxOptions();
                    ffOptions.addArguments("-private");
                    ffOptions.addPreference("media.navigator.streams.fake", true);
                    ffOptions.addPreference("media.navigator.permission.disabled", true);
                    ffOptions.addPreference("general.useragent.override", user_agent);
                    driver = new FirefoxDriver(ffOptions);
                    testContext.setAttribute("WebDriver", driver);
                    drivers.add(driver);
                    break;
                case ("chrome"):
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--incognito");
//                options.addArguments("--headless");
                    Map<String, String> mobileEmulation = new HashMap<>();
                    mobileEmulation.put("deviceName", "iPhone X");
                    options.addArguments("use-fake-ui-for-media-stream");
                    options.addArguments("use-fake-device-for-media-stream");
                    options.addArguments("allow-file-access-from-files");
                    options.addArguments("--auto-open-devtools-for-tabs");
                    options.setExperimentalOption("mobileEmulation", mobileEmulation);
                    driver = new ChromeDriver(options);
                    testContext.setAttribute("WebDriver", driver);
                    drivers.add(driver);
            }
            if (drivers.size() > MAX_THREADS) {
                Assert.fail("max 2 threads supported");
            }
            if (drivers.size() == 0) {
                Assert.fail("no browser supported");
            }
        }
    }

    private String createLinkToFlow(int flowNumber) {
        String btrust1Auth = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1c2VyRGF0YSI6IntcImlkXCI6ODMsXCJjb21wYW55SWRcIjo0LFwiYWN0aXZlU3ViQ29tcGFueVwiOjAsXCJyb2xlc1wiOls3XSxcInBlcm1pc3Npb25zXCI6W3tcImlkXCI6MCxcInJvbGVJZFwiOjAsXCJ0eXBlXCI6MCxcInR5cGVJZFwiOjAsXCJjYW5SZWFkXCI6ZmFsc2UsXCJjYW5FZGl0XCI6ZmFsc2UsXCJjYW5DcmVhdGVcIjpmYWxzZX0se1wiaWRcIjowLFwicm9sZUlkXCI6MCxcInR5cGVcIjo2LFwidHlwZUlkXCI6MCxcImNhblJlYWRcIjp0cnVlLFwiY2FuRWRpdFwiOnRydWUsXCJjYW5DcmVhdGVcIjp0cnVlfV19IiwiaWF0IjoxNjIyNTU0NjUxLCJleHAiOjMxOTkzNTQ2NTF9.S53gOLQJ9XqD3UsFZzx_pn9hOJ6hpCM8CDLHI2rXvPR256GLDj4Y0Eb4YKSWrERs8hJlvLA9okxLkJJ6I1JgxHzgrwgb2gdMyDPEji6rGXRijKzsbwWYUzT1xe78p0cb-LUGfrcfM80HK7qV2CePZ_ho7C9Jczg_evV56xVIoN10eZrrpzUDEJ-dfTjtXSK93AkGeh6t1n3Lm8xhDYPrZ2fy59Da4w9MmPzUwl3VaAxIawum2VRM2U6p15hH4bB_p1HZaVdA_2GmXf74KuRkguyPbbDWZI7g9LVU5POj6JGxTsRtDOaGBeFNsRXKVkvb2DizMxCl1AhXPwf_G9-Oaw";
        Response response1 = given()
                .header("Authorization", btrust1Auth)
//                .get("https://qa-nginx-console.scanovateoncloud.com/server/flow/" + flowNumber + "/link")
                .get("https://qa-nginx-console.scanovateoncloud.com/server/flow/" + flowNumber + "/link")
                .then()
                .statusCode(200)
                .extract()
                .response();
        JsonPath jsonPath1 = response1.jsonPath();
        String sessionId = jsonPath1.getString("data").split("process_id=")[1];
        System.out.println("Session Id: " + sessionId);
        return jsonPath1.getString("data");
    }

    @Test(description = "Onboarding Test Liveness process Chrome browser")
    @Description("Onboarding Test Liveness process Chrome browser")
    public void t01_livenessOnlyChrome() throws IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
//                options.addArguments("--headless");
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone X");
        options.addArguments("use-fake-ui-for-media-stream");
        options.addArguments("use-fake-device-for-media-stream");
        options.addArguments("allow-file-access-from-files");
        options.addArguments("--auto-open-devtools-for-tabs");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        WebDriver driver1 = new ChromeDriver(options);
        drivers.add(driver1);
        OCRPage ocrPage = new OCRPage(driver1);
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver1);
        ScanLivenessPage scanLivenessPage = new ScanLivenessPage(driver1);
        CompletedPage completedPage = new CompletedPage(driver1);
        String flowLink = createLinkToFlow(356);
        driver1.get(flowLink);
        System.out.println(flowLink);
        ocrPage.chooseLanguage("English");
        assertEquals(scanLivenessPage.scanLivenessTitle(), "Liveness Check");
        assertEquals(scanLivenessPage.scanLivenessSubTitle(), "Before we start make sure that:");
        assertEquals(scanLivenessPage.scanLivenessDescription(), "The phone is at face level | There are good light conditions");
        scanLivenessPage.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/avner/avnerCenter.jpg");
        completedPage.chooseLanguage("English");
        Assert.assertEquals(completedPage.getCompleteText(), "Process ended");
    }

    @Test(description = "Onboarding Test Liveness process Firefox browser")
    @Description("Onboarding Test Liveness process Firefox browser")
    public void t01_livenessOnlyFirefox() throws IOException {
        String user_agent = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16";
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions ffOptions = new FirefoxOptions();
        ffOptions.addArguments("-private");
        ffOptions.addPreference("media.navigator.streams.fake", true);
        ffOptions.addPreference("media.navigator.permission.disabled", true);
        ffOptions.addPreference("general.useragent.override", user_agent);
        WebDriver driver1 = new FirefoxDriver(ffOptions);
        drivers.add(driver1);
        OCRPage ocrPage = new OCRPage(driver1);
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver1);
        ScanLivenessPage scanLivenessPage = new ScanLivenessPage(driver1);
        CompletedPage completedPage = new CompletedPage(driver1);
        String flowLink = createLinkToFlow(356);
        driver1.get(flowLink);
        System.out.println(flowLink);
        ocrPage.chooseLanguage("English");
        assertEquals(scanLivenessPage.scanLivenessTitle(), "Liveness Check");
        assertEquals(scanLivenessPage.scanLivenessSubTitle(), "Before we start make sure that:");
        assertEquals(scanLivenessPage.scanLivenessDescription(), "The phone is at face level | There are good light conditions");
        scanLivenessPage.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/avner/avnerCenter.jpg");
        completedPage.chooseLanguage("English");
        Assert.assertEquals(completedPage.getCompleteText(), "Process ended");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        for (WebDriver driver : drivers) {
            driver.quit();
        }
    }
}

