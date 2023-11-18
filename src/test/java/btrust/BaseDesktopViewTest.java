package btrust;

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

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class BaseDesktopViewTest {

    protected WebDriver driver;
    //    public RemoteWebDriver driver;
    String BrowserVersion;
    String BrowserName;

    @BeforeClass(alwaysRun = true)
    @Step("Setting and open the driver")
    public void setup(ITestContext testContext) throws MalformedURLException {
        System.setProperty("attachFailed","true");
        System.out.println("====================\n" + "Start class: " + getClass().getName());
        String machine = "local";
        switch (machine) {
            case "local": {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                if (System.getProperty("HEADLESS","false").equals("true")){
                    options.addArguments("--headless");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--window-size=1280,800");
                    options.addArguments("--allow-insecure-localhost");
                }
                driver = new ChromeDriver(options);
                driver.manage().deleteAllCookies();
                testContext.setAttribute("WebDriver", this.driver);
                driver.manage().window().maximize();
                break;
            }
            case "docker": {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("92.0");
                capabilities.setCapability("sessionTimeout", "5m");
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", true);
                capabilities.setCapability("videoName", this.getClass().getName() + "_" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss")) + ".mp4");
                capabilities.setCapability("enableLog", true);
                capabilities.setCapability("logName", this.getClass().getName() + "_" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss")) + ".log");
                capabilities.setCapability("screenResolution", "1920x1080x24");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized", "disable-extensions",
                        "test-type", "no-default-browser-check", "ignore-certificate-errors",
                        "use-fake-ui-for-media-stream", "use-fake-device-for-media-stream", "allow-file-access-from-files");
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
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
