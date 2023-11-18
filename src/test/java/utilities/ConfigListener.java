package utilities;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.ByteArrayInputStream;

public class ConfigListener extends TestListenerAdapter implements IConfigurationListener {
    public void beforeConfiguration(ITestResult result){
        if (System.getProperty("envFail", "false").equals("true")) {
            throw new SkipException("Env down");
        }
    }
    public void onConfigurationFailure(ITestResult result) {

        if (System.getProperty("attachFailed", "false").equals("true")) {
            Object webDriverAttribute = result.getTestContext().getAttribute("WebDriver");
            if (webDriverAttribute instanceof WebDriver) {
                System.out.println("attaching setup capture");
                try {
                    Allure.addAttachment("setup_capture","jpg", new ByteArrayInputStream(((TakesScreenshot) webDriverAttribute).getScreenshotAs(OutputType.BYTES)),"jpg");
                } catch (Exception e) {
                    System.out.println("attach screenshot failed:" + e.getMessage());
                }
            }
        }
    }

}