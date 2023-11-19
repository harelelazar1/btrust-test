package selenium.liveness.pageObject;


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

public class LivenessPage extends BasePage {

    public LivenessPage(WebDriver driver) {
        super(driver);
    }

    public String caseId;

    @FindBy(css = "#start")
    protected WebElement scanButton;
    @FindBy(css = "#finalTextMessage > h1")
    protected WebElement resultTitle;
    @FindBy(css = ".header")
    protected WebElement errorMessage;
    @FindBy(css = "#again")
    protected WebElement takeAnotherTestButton;
    @FindBy(css = ".image > img")
    protected List<WebElement> imageList;
    @FindBy(css = "#scanovate-dynamic-message")
    protected WebElement turnHeadMessage;
    @FindBy(css = "#scanovate-dynamic-message")
    protected WebElement livenessDynamicErrorMessage;
    @FindBy(css = ".table .firstTrOfTable td:nth-child(2)")
    protected WebElement caseIdFromResultTable;
    @FindBy(css = ".container")
    protected WebElement livenessBubbleContainer;


    @Step("Check if resultTitleIsDisplayed")
    public boolean resultTitleIsDisplayed() {
        try {
            waitForElementToBeVisibility(resultTitle);
            isDisplayed(resultTitle);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Step("get text of the result title in the result page")
    public String resultTitle() {
        waitForTextToBeInElement(livenessDynamicErrorMessage, "PROCESSING... PLEASE WAIT");
        waitForElementToBeVisibility(resultTitle);
        return getText(resultTitle);
    }

    @Step("Check if resultTitleIsDisplayed")
    public boolean resultTitleIsDisplayed(String title) {
        try {
            waitForTextToBeInElement(resultTitle, title);
            return isDisplayed(resultTitle);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("found: " + getText(resultTitle));
            return false;
        }
    }

    @Step("get text of the message in scan liveness page")
    public String livenessDynamicErrorMessage(String textMessage) {
        waitForTextToBeInElement(livenessDynamicErrorMessage, textMessage);
        return getText(livenessDynamicErrorMessage);
    }

    @Step("Check if errorMessageIsDisplayed")
    public boolean errorMessageIsDisplayed() {
        try {
            waitForElementToBeVisibility(errorMessage);
            isDisplayed(errorMessage);
            String error = getText(errorMessage);
            System.out.println(error);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Step("get text errorMessage liveness page")
    public String errorMessageText(String textMessage) {
        waitForTextToBeInElement(errorMessage, textMessage);
        return getText(errorMessage);
    }

    public void takeAnotherTestButton() {
        waitForElementToBeClickable(takeAnotherTestButton);
        click(takeAnotherTestButton);
    }

    public boolean takeAnotherTestButtonIsDisplay() {
        try {
            waitForElementToBeVisibility(takeAnotherTestButton);
            isDisplayed(takeAnotherTestButton);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Step("check 2 images exist in base 64")
    public boolean checkImagesExistAndGetCaseId(int numberOfPicAppearsInLivenessResultPage) {
        try {
            waitForElementToBeVisibility(resultTitle);
            if (imageList.size() == numberOfPicAppearsInLivenessResultPage) {
                waitForElementToBeVisibility(caseIdFromResultTable);
                caseId = getText(caseIdFromResultTable);
                System.out.println("Case id is: " + caseId);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Step("click on start button")
    public void startButton() {
        waitForElementToBeClickable(scanButton);
        click(scanButton);
    }

    @Step("get back liveness Bubble Container Width")
    public int livenessBubbleContainerWidth() {
        waitForElementToBeVisibility(livenessBubbleContainer);
        int width = livenessBubbleContainer.getSize().getWidth();
        return width;
    }

    @Step("Scan Liveness")
    public void scanLiveness(String filePath) throws IOException {
        sleep(2000);
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        if (folder.isDirectory()) {
            for (File file : listOfFiles) {
                if (resultTitleIsDisplayed()) {
                    break;
                }
                singleFile(file);
            }
        } else {
            singleFile(folder);
        }
    }

    @Step("Scan Liveness - active")
    public void scanLivenessActive(String filePath, String filePathLeft, String filePathRight) throws IOException {
        sleep(1000);
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        if (folder.isDirectory()) {
            for (File file : listOfFiles) {
                if (resultTitleIsDisplayed()) {
                    break;
                }
                singleFile(file);
            }
        } else {
            singleFile(folder);
            waitForElementToBeVisibility(turnHeadMessage);
            if (getText(turnHeadMessage).equalsIgnoreCase("TURN HEAD LEFT")) {
                waitForElementToBeVisibility(turnHeadMessage);
                System.out.println(getText(turnHeadMessage));
                scanLiveness(filePathLeft);
                scanLiveness(filePathRight);
            } else if (getText(turnHeadMessage).equalsIgnoreCase("TURN HEAD RIGHT")) {
                waitForElementToBeVisibility(turnHeadMessage);
                System.out.println(getText(turnHeadMessage));
                scanLiveness(filePathRight);
                scanLiveness(filePathLeft);
            }
        }
    }


    public void singleFile(File file) throws IOException {
        System.out.println("Choose file: \n" + file.getName());
        BufferedImage image = ImageIO.read((file));
        byte[] arr = encodeToByteArray(image, "jpg");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                sb.append("[");
            }
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(",");
            } else {
                sb.append("]");
            }
        }
        js.executeScript("window.getOutsideInjectedImageAllowed = true;" +
                "window.getOutsideInjectedImage = function() {" +
                "if(window.getOutsideInjectedImageAllowed === false){" +
                "return null" +
                "};" +
                "window.getOutsideInjectedImageAllowed = false;" +
                "setTimeout(() => {" +
                "        window.getOutsideInjectedImageAllowed = true;" +
                "    });" +
                "console.log('liad Sending');" +
                "const myBlob = new Blob([new Uint8Array(JSON.parse('" + sb + "')).buffer], {type: 'image/jpg'});" +
                "window.liadBlob = myBlob;" +
                "window.liadSb ='" + sb + "';" +
                "console.log('liad blob' + myBlob);" +
                "return {data: myBlob, frameId: Date.now()};" +
                "}");
        System.out.println("Inject file: \n" + file.getName());
        System.out.println("============================================\n");
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