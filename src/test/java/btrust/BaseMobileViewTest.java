package btrust;

import com.google.common.collect.ImmutableMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

public class BaseMobileViewTest {

    protected WebDriver driver;
    //    public RemoteWebDriver driver;
    String BrowserVersion;
    String BrowserName;
    protected WebDriverWait redirectWait;

    @BeforeClass(alwaysRun = true)
    @Step("Setting and open the driver")
    public void setup(ITestContext testContext) throws MalformedURLException {
        System.setProperty("attachFailed", "true");
        System.out.println("====================\n" + "Start class: " + getClass().getName());
        String machine = "local";
        switch (machine) {
            case "local": {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                if (System.getProperty("HEADLESS", "false").equals("true")) {
                    options.addArguments("--headless");
                }
                Map<String, String> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", "iPhone X");
                options.addArguments("use-fake-ui-for-media-stream");
                options.addArguments("use-fake-device-for-media-stream");
                options.addArguments("allow-file-access-from-files");
                options.addArguments("--auto-open-devtools-for-tabs");
                options.setExperimentalOption("mobileEmulation", mobileEmulation);
                driver = new ChromeDriver(options);
                testContext.setAttribute("WebDriver", this.driver);
                driver.manage().window().maximize();
                break;
            }
            case "docker": {
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("92.0");
                capabilities.setCapability("sessionTimeout", "5m");
                capabilities.setCapability("enableVNC", false);
                capabilities.setCapability("enableVideo", false);
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
                driver = new RemoteWebDriver(URI.create("http://192.168.1.194:4444/wd/hub").toURL(), capabilities);
                BrowserVersion = capabilities.getVersion();
                BrowserName = capabilities.getBrowserName();
                testContext.setAttribute("WebDriver", this.driver);
                allureEnvironmentWriter(ImmutableMap.<String, String>builder()
                        .put("Browser", BrowserName)
                        .put("Browser.Version", BrowserVersion)
                        .build());
                break;
            }
        }
        redirectWait = new WebDriverWait(driver, 20);
    }

    public void failTest(String message) {
        Assert.fail(message);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
