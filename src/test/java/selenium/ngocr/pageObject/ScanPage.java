package selenium.ngocr.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BasePage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ScanPage extends BasePage {

    public ScanPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".CaptureProcessingOverlay_motionContainer__progressLabel__BpENM")
    protected WebElement stageCounter;
    @FindBy(css = ".CaptureInstruction_container__label__nXjqv")
    protected WebElement instructionsTitle;
    @FindBy(css = "#backX")
    protected WebElement closeButton;
    @FindBy(css = "div.RecordOverlay_container__button__XH\\+05")
    protected WebElement recordingButton;
    @FindBy(css = ".recordNumbersContainer > div")
    protected List<WebElement> otpNum;
    @FindBy(css = "div.RecordOverlay_container__button__XH\\+05")
    protected WebElement finishRecordingLabel;
    @FindBy(css = ".GestureOverlay_container__progressBar__3qKLW > div")
    protected List<WebElement> gesturesExamples;
    @FindBy(css = "div>.CCGestureSvg_container__1k9IF.CCGestureBarIcon_container__icon__3vfLp")
    protected List<WebElement> gesturesArrow;
    @FindBy(css = ".GestureOverlay_container__progressBar__3qKLW > div > .CCGestureBarIcon_container__counter__2dc2o")
    protected List<WebElement> gesturesTimer;
    @FindBy(css = ".CaptureOverlay_container__1aypZ > .CaptureOverlay_container__spinner__1z06w")
    protected WebElement gesturesLoader;
    @FindBy(css = "div.SingleCaptureOverlay_container__button__Atq-f")
    protected WebElement cardCaptureButton;


    @Step("Return answer if the gestures loader appeared on the screen")
    public boolean isGesturesLoaderAppeared() {
        waitForElementToBeVisibility(gesturesLoader);
        return isDisplayed(gesturesLoader);
    }

    @Step("Return the size of the gestures arrows")
    public int getGestureArrow() {
        waitForList(gesturesArrow);
        return gesturesArrow.size();
    }

    @Step("Return the stages counter")
    public String getStageCounter() {
        waitForPageFinishLoading();
        scrollToElement(stageCounter);
        waitForElementToBeVisibility(stageCounter);
        isDisplayed(stageCounter);
        return getText(stageCounter);
    }

    @Step("Return the instructions title text")
    public String getInstructionsTitle() {
        scrollToElement(instructionsTitle);
        waitForElementToBeVisibility(instructionsTitle);
        return getText(instructionsTitle);
    }

    @Step("Return answer if the title appearing in the page")
    public boolean isLabelAppear() {
        try {
            return isDisplayed(finishRecordingLabel);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }



    @Step("Click Card capture button")
    public void clickCardCaptureButton() {
        waitForElementToBeClickable(cardCaptureButton);
        click(cardCaptureButton);
    }

    @Step("Click Card capture button")
    public boolean CardCaptureButtonDisplay() {
        try {
            return isDisplayed(cardCaptureButton);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Step("Wait for error text to be instructions title text")
    public boolean waitForErrorTextToBeInInstructionsTitle(String textMessage) {
        waitForTextToBeInElement(instructionsTitle, textMessage);
        return getText(instructionsTitle).contains(textMessage);
    }

    @Step("Close popup button")
    public void CloseButton() {
        waitForElementToBeClickable(closeButton);
        click(closeButton);
    }

    @Step("Click on the recording button")
    public void clickRecordingButton() {
        try {
            waitForElementToBeVisibility(recordingButton);
            click(recordingButton);
            js.executeScript("console.log('click audio chunk injection');");
        } catch (Exception e) {
            System.out.println("Error with clicking the record button: " + e.getMessage());
        }
    }
    @Step("Return the OTP number")
    public String getOtpNum() {
        waitForList(otpNum);
        StringBuilder stringBuilder = new StringBuilder();
        for (WebElement el : otpNum) {
            stringBuilder.append(getText(el));
        }
        return stringBuilder.toString();
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
}
