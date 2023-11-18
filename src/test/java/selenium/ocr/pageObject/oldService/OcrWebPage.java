package selenium.ocr.pageObject.oldService;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import selenium.BasePage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class OcrWebPage extends BasePage {

    public OcrWebPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".container .content.first")
    protected WebElement titleOcr;
    @FindBy(css = ".container .action [value]")
    protected List<WebElement> ocrButtonsList;
    @FindBy(how = How.ID, using = "doubleSidedCheckbox")
    protected WebElement doubleSideCheck;


    @Step("click on OCR button")
    public void clickOcrButton(String button) {
        waitForElementToBeVisibility(titleOcr);
        for (WebElement el : ocrButtonsList) {
            if (getAttribute(el, "value").equalsIgnoreCase(button)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        sleep(1500);
        OcrScanPage ocrScanPage = new OcrScanPage(driver);
        if (!ocrScanPage.scanError.isEmpty() && ocrScanPage.scanErrorMessage().contains("Error occurred")) {
            System.out.println(ocrScanPage.scanErrorMessage());
            Assert.fail(ocrScanPage.scanErrorMessage());
            Assert.fail();
        }
    }

    @Step("verify the ocr buttons option appear in the ocr web page")
    public boolean checkOcrOptionsExist(String button) {
        try {
            waitForElementToBeVisibility(titleOcr);
            for (WebElement el : ocrButtonsList) {
                if (getAttribute(el, "value").equalsIgnoreCase(button)) {
                    waitForElementToBeClickable(el);
                    System.out.println("OCR options: " + getAttribute(el, "value"));
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Step("verify the ocr buttons option appear in the ocr web page")
    public boolean doubleSideCheck() {
        try {
            waitForElementToBeVisibility(doubleSideCheck);
            isDisplayed(doubleSideCheck);
            System.out.println("doubleSideCheck button appears");
            return true;
        } catch (Exception e) {
            System.out.println((e.getMessage()));
        }
        return false;
    }

    @Step("Scan Ocr and wait for camera alert messages")
    public boolean scanOcrWithCameraAlertMessages(String filePath, int index, String errorText) throws IOException {
        System.out.println("Wait for error message: " + errorText);
        OcrScanPage ocrScanPage = new OcrScanPage(driver);
        ocrScanPage.cameraAlertAppear();
        scrollToElement(ocrScanPage.cameraAlert);
        BufferedImage image = ImageIO.read(new File(filePath));
        byte[] arr = encodeToByteArray(image, "jpg");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                sb.append("[");
            }
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");

        js.executeScript("window.getOutsideInjectedImage = function() {" +
                "console.log('liad Sending index #" + index + "');" +
                "const myBlob = new Blob([new Uint8Array(JSON.parse('" + sb + "')).buffer], {type: 'image/jpg'});" +
                "window.liadBlob = myBlob;" +
                "window.liadSb ='" + sb + "';" +
                "console.log('liad blob' + myBlob);" +
                "return myBlob;}");
        System.out.println("Inject file: \n" + filePath);
        System.out.println("============================================\n");
        return ocrScanPage.cameraAlertText(errorText).equalsIgnoreCase(errorText);
    }

    @Step("Scan OCR")
    public void scanOCR(String filePath, int index) throws IOException {
        sleep(5000);
        BufferedImage image = ImageIO.read(new File(filePath));
        byte[] arr = encodeToByteArray(image, "jpg");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                sb.append("[");
            }
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");

        js.executeScript("window.getOutsideInjectedImage = function() {" +
                "console.log('liad Sending index #" + index + "');" +
                "const myBlob = new Blob([new Uint8Array(JSON.parse('" + sb + "')).buffer], {type: 'image/jpg'});" +
                "window.liadBlob = myBlob;" +
                "window.liadSb ='" + sb + "';" +
                "console.log('liad blob' + myBlob);" +
                "return myBlob;}");

        System.out.println("Inject file: \n" + filePath);
        System.out.println("============================================\n");
        sleep(10000);
        js.executeScript("window.getOutsideInjectedImage = null");
    }

    @Step("Encoded image to byte")
    public static byte[] encodeToByteArray(BufferedImage image, String type) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            bos.close();
            return imageBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void openNewTab() {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
    }
}
