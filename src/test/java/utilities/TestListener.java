package utilities;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener extends TestListenerAdapter implements ITestListener {

    public void onTestFailure(ITestResult result) {
        TestUtils.printResultLines(result);
        if (System.getProperty("attachFailed", "false").equals("true")) {
            Object webDriverAttribute = result.getTestContext().getAttribute("WebDriver");
            if (webDriverAttribute instanceof WebDriver) {
                System.out.println("attaching");
                try {
                    Allure.addAttachment("test_capture","jpg", new ByteArrayInputStream(((TakesScreenshot) webDriverAttribute).getScreenshotAs(OutputType.BYTES)),"jpg");
                }catch (Exception e){
                    System.out.println("attach screenshot failed:"+e.getMessage());
                }
            }
        }
    }

    public void onTestStart(ITestResult test) {
        if (System.getProperty("envFail", "false").equals("true")) {
            Assert.fail("Env down");
//            test.setThrowable(new Throwable("Env down"));
//            test.setStatus(ITestResult.FAILURE);
//            return;
        }

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("--------------- Starting test:" + test.getName() + " started at: " + formattedDate + " -------------------");
    }

    public void onTestSuccess(ITestResult test) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("Test ended at: " + formattedDate);
        System.out.println("------------ Test: " + test.getName() + " Passed ------------" + test.getName());
    }
}