package selenium;

import com.google.common.collect.ImmutableMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;


public class BaseTest {
    protected static WebDriver driver;
    //    public RemoteWebDriver driver;
    String BrowserVersion;
    String BrowserName;

    public final int OCR_TIMEOUT = 90;
    public final int LIVENESS_TIMEOUT = 90;
    public final int TEN_SECONDS = 10;


    public final String OCR_DEMO_NEW = "https://qa-ngocr.scanovateoncloud.com/";
//    public final String OCR_DEMO_NEW = "https://uat-ngocr.scanovate.com/";
    public final String LIVENESS_DEMO_NEW = "https://qa-ngliveness.scanovateoncloud.com/";
    public final String OCR_DEMO = "https://qaocr.scanovateoncloud.com/demo";
    public final String LIVENESS_DEMO = "https://qaliveness.scanovateoncloud.com/demo";


    @BeforeClass(alwaysRun = true)
    @Step("Setting and open the driver")
    public void setup(ITestContext testContext) throws MalformedURLException {
        System.out.println("====================\n" + "Start class: " + getClass().getName());
        String machine = "local";
        String view = "desktop";
        switch (machine) {
            case "local": {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                options.addArguments("use-fake-ui-for-media-stream");
                options.addArguments("use-fake-device-for-media-stream");
                options.addArguments("allow-file-access-from-files");
                options.addArguments("disable-infobars");
                options.addArguments("--auto-open-devtools-for-tabs");
                if (System.getProperty("HEADLESS","false").equals("true")){
                    options.addArguments("--headless");
                }
                switch (view) {
                    case "desktop": {
                        break;
                    }
                    case "mobile": {
                        Map<String, String> mobileEmulation = new HashMap<>();
                        mobileEmulation.put("deviceName", "iPhone X");
                        options.setExperimentalOption("mobileEmulation", mobileEmulation);
                        break;
                    }
                }
                driver = new ChromeDriver(options);
                testContext.setAttribute("WebDriver", this.driver);
                driver.manage().window().maximize();
                break;
            }
            case "docker": {
                switch (view) {
                    case "desktop": {
                        DesiredCapabilities capabilities = new DesiredCapabilities();
                        capabilities.setBrowserName("chrome");
                        capabilities.setVersion("92.0");
                        capabilities.setCapability("sessionTimeout", "5m");
                        capabilities.setCapability("enableVNC", false);
                        capabilities.setCapability("enableVideo", false);
                        capabilities.setCapability("videoName", this.getClass().getName() + "_" +
                                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss")) + ".mp4");
                        capabilities.setCapability("enableLog", true);
                        capabilities.setCapability("logName", this.getClass().getName() + "_" +
                                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss")) + ".log");
                        capabilities.setCapability("screenResolution", "1920x1080x24");
                        ChromeOptions chromeOptions = new ChromeOptions();
//                        chromeOptions.addArguments("start-maximized", "disable-extensions",
//                                "test-type", "no-default-browser-check", "ignore-certificate-errors",
//                                "use-fake-ui-for-media-stream", "use-fake-device-for-media-stream", "allow-file-access-from-files");
                        chromeOptions.addArguments("--incognito");
                        chromeOptions.addArguments("start-maximized");
                        chromeOptions.addArguments("use-fake-ui-for-media-stream");
                        chromeOptions.addArguments("use-fake-device-for-media-stream");
                        chromeOptions.addArguments("allow-file-access-from-files");
                        chromeOptions.addArguments("disable-infobars");
                        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                        driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
                        BrowserVersion = capabilities.getVersion();
                        BrowserName = capabilities.getBrowserName();
                        break;
                    }
                    case "mobile": {
                        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                        capabilities.setBrowserName("chrome");
                        capabilities.setVersion("92.0");
                        capabilities.setCapability("sessionTimeout", "5m");
                        capabilities.setCapability("enableVNC", true);
                        capabilities.setCapability("enableVideo", true);
                        capabilities.setCapability("videoName", this.getClass().getName() + "_" +
                                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss")));
                        capabilities.setCapability("screenResolution", "1920x1080x24");
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("start-maximized", "disable-extensions",
                                "test-type", "no-default-browser-check", "ignore-certificate-errors",
                                "use-fake-ui-for-media-stream", "use-fake-device-for-media-stream", "allow-file-access-from-files", "--auto-open-devtools-for-tabs");
                        Map<String, String> mobileEmulation = new HashMap<>();
                        mobileEmulation.put("deviceName", "iPhone X");
                        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                        driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
                        BrowserVersion = capabilities.getVersion();
                        BrowserName = capabilities.getBrowserName();
                        break;
                    }

                }
                testContext.setAttribute("WebDriver", this.driver);
                allureEnvironmentWriter(ImmutableMap.<String, String>builder()
                        .put("Browser", BrowserName)
                        .put("Browser.Version", BrowserVersion)
                        .build());
                break;
            }
        }
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        driver.quit();
    }
}