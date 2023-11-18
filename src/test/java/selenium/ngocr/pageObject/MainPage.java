package selenium.ngocr.pageObject;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BasePage;
import selenium.Injection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static selenium.Injection.encodeToByteArray;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".SettingsDialog_container__uPCuN > svg:nth-child(1)")
    protected WebElement settingButton;
    @FindBy(css = ".LogoImg")
    protected WebElement logoImage;
    @FindBy(css = ".CardImg")
    protected WebElement cardImage;
    @FindBy(css = ".WelcomeLabel")
    protected WebElement welcomeTextElement;
    @FindBy(css = ".ComponentsSelection_container__1Ysrm")
    protected WebElement dropDown;
    @FindBy(css = ".ComponentsSelection_container__arxSk > option")
    protected List<WebElement> dropdownOptionsList;
    /*
    popup elements
     */
    @FindBy(css = ".DisplayPopupBackground")
    protected WebElement popup;
    @FindBy(css = ".PopupTitle")
    protected WebElement popupTitle;
    @FindBy(css = ".PopupMessage")
    protected WebElement popupText;
    @FindBy(css = ".PopupButton")
    protected WebElement popupButton;
    @FindBy(css = "#name")
    protected WebElement flowConfigNameInput;
    @FindBy(css = "button:nth-child(1)")
    protected WebElement cancelButton;
    @FindBy(css = "button:nth-child(2)")
    protected WebElement submitButton;

    @Step("Choose option from services list")
    public void chooseFromServicesList(String optionSelected) throws RuntimeException {
        try {
            chooseFromServicesList(optionSelected, false, null);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Step("Choose option from services list, with pixel inject option")
    public void chooseFromServicesList(String optionSelected, boolean flagOfPixels, String cPallet) throws IOException {
        waitForList(dropdownOptionsList);
        for (WebElement el : dropdownOptionsList) {
            waitForElementToBeClickable(el);
            if (getText(el).equalsIgnoreCase(optionSelected)) {
                waitForElementToBeClickable(el);
                preClick(flagOfPixels, cPallet);
                click(el);
                break;
            }
        }

        //Irad library version
        Variables variables = new Variables();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.getOcrLibraries(variables);
        switch (optionSelected) {
            case "Israel ID":
                System.out.println("Irad library for IL-ID: " + variables.getIsraelId());
                break;
            case "Israel Driving License":
                System.out.println("Irad library for IL-DL: " + variables.getIsraelDl());
                break;
            case "Denmark Driving License":
                System.out.println("Irad library for DK-DL: " + variables.getDenmarkDl());
                break;
            case "CNI":
                System.out.println("Irad library for CNI: " + variables.getFranceId());
                break;
            case "MRZ":
                System.out.println("Irad library for MRZ: " + variables.getPassport());
                break;
            case "Philippines Cheque":
                System.out.println("Irad library for PHC: " + variables.getPhilippineCheque());
                break;
            case "Liveness":
//                System.out.println("No liveness version available.");
                break;
        }
    }

    @Step("Verify Scanovate logo and welcome text are appearing in the page")
    public void verifyLogoAndWelcomeTextAppear() {
        waitForElementToBeVisibility(logoImage);
        waitForElementToBeVisibility(welcomeTextElement);
    }

    @Step("Choose the flow config file you desire")
    public void chooseConfigFile(String flowConfigName) {
        waitForPageFinishLoading();
        waitForElementToBeClickable(settingButton);
        click(settingButton);
        waitForElementToBeVisibility(flowConfigNameInput);
        waitForElementToBeVisibility(submitButton);
        waitForElementToBeVisibility(cancelButton);
        fillText(flowConfigNameInput, flowConfigName);
        click(submitButton);
    }

    public void preClick(boolean flagOfPixel, String cPallet) throws RuntimeException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);

        if (flagOfPixel) {
            int partitionSize = 6;
            List<String> parts = new ArrayList<>();
            List<Color> colorArrayFromPallet = new ArrayList<>();

            int len = cPallet.length();
            for (int i = 0; i < len; i += partitionSize) {
                parts.add(cPallet.substring(i, Math.min(len, i + partitionSize)));
            }

            for (String part : parts) {
                System.out.println(part);
                colorArrayFromPallet.add(Color.decode("#" + part));
            }

            for (int i = image.getMinX(); i < 8; i++) {
                for (int j = image.getMinY(); j < 8; j++) {
                    image.setRGB(i, j, colorArrayFromPallet.get(0).getRGB()); //top left
                }
            }


            //Color the top right corner
            for (int i = image.getWidth() - 1; i > image.getWidth() - 9; i--) {
                for (int j = image.getMinY(); j < 8; j++) {
                    image.setRGB(i, j, colorArrayFromPallet.get(1).getRGB()); //top right

                }
            }

            //Color the bottom right corner
            for (int i = image.getWidth() - 1; i > image.getWidth() - 9; i--) {
                for (int j = image.getHeight() - 1; j > image.getHeight() - 9; j--) {
                    image.setRGB(i, j, colorArrayFromPallet.get(2).getRGB()); //bottom right

                }
            }

            //Color the bottom left corner
            for (int i = image.getMinX(); i < 8; i++) {
                for (int j = image.getHeight() - 1; j > image.getHeight() - 9; j--) {
                    image.setRGB(i, j, colorArrayFromPallet.get(3).getRGB()); //bottom right
                }
            }
        }
        try {
            ImageIO.write(image, "png", new File("./ocr/pixelatedImages/PreClickImage.jpg"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

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
                "console.log('doron Sending index #doron');" +
                "const myBlob = new Blob([new Uint8Array(JSON.parse('" + sb + "')).buffer], {type: 'image/jpg'});" +
                "window.videoTestBlob = myBlob;" +
                "console.log(`${Date.now()} - video frame injection (fn: , fs: ${myBlob.size}) blob: ${myBlob}`);" +
                "return myBlob;}");

        js.executeScript(Injection.DEFAULT_AUDIO_OVERRIDE);
    }
}
