package utilities;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.nio.charset.StandardCharsets;

public class AllureAttachment {

    @Attachment(value = "{0}", type = "text/plain")
    public static String addTextAttachment(String message) {
        return message;
    }

    @Attachment(value = "Html source", type = "text/html", fileExtension = ".html")
    public static byte[] getPageSource(WebDriver driver) {
        return driver.getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Page Screenshot", type = "image/png", fileExtension = ".png")
    static byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}