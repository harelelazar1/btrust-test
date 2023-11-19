package selenium.cardCapture.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import selenium.cardCapture.pageObject.CardCapturePage;

import java.io.IOException;

public class BaseTest {

    protected WebDriver driver;

    @Test
    public void test(ITestContext testContext) throws IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("use-fake-ui-for-media-stream");
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        testContext.setAttribute("WebDriver", this.driver);
        driver.manage().window().maximize();
        driver.get("https://doron-etay.s3.eu-central-1.amazonaws.com/card_capture/index.html");

        CardCapturePage cardCapturePage = new CardCapturePage(driver);
        cardCapturePage.injectCardCapture("./ocr/mrz/liad/liadFront.jpg", 1);
        cardCapturePage.clickOnButton("inject 1");

        driver.quit();
    }
}
