package btrust.onboardingProcess.ui.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ChequePage extends BasePage {
    public ChequePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".ProcessIntro_mainImage__U2qSj")
    protected WebElement ocrPicture;
    @FindBy(css = "div h2")
    protected WebElement ocrTitle;
    @FindBy(css = "div h3")
    protected WebElement subTitleOCRPage;
    @FindBy(css = ".ProcessIntro_description__MRoFD")
    protected WebElement ocrDescription;
    @FindBy(css = ".ProcessIntro_btn__RNEms")
    protected List<WebElement> buttonList;

    /*
    Ocr cheque scan page
     */
    @FindBy(css = ".scanovate-active-ocr-cheque-validate-container > .scanovate-active-ocr-cheque-validateData >.scanovate-active-ocr-cheque-input-field > input")
    protected List<WebElement> chequeInputList;
    @FindBy(css = ".scanovate-active-ocr-cheque-action > input")
    protected WebElement finishButton;


    @Step("Check if ocrPicture is appear on the screen")
    public boolean ocrImage() {
        waitForElementToBeVisibility(ocrPicture);
        return imageIsDisplayed(ocrPicture);

    }

    @Step("Return the text of ocr title")
    public String OCRTitleIsDisplayed() {
        waitForElementToBeVisibility(ocrTitle);
        return getText(ocrTitle);
    }

    @Step("Return text of subTitleOCRPage")
    public String subTitleOCRPage() {
        waitForElementToBeVisibility(subTitleOCRPage);
        return getText(subTitleOCRPage);
    }

    @Step("Return the text of ocr description")
    public String ocrDescription() {
        waitForElementToBeVisibility(ocrDescription);
        return getText(ocrDescription);
    }

    @Step("Click on button: {button}")
    public void clickOnButton(String button) {
        waitForPageFinishLoading();
        sleep(4000);
        waitForElementToBeClickable(buttonList.get(0));
        for (WebElement el : buttonList) {
            if (getText(el).equalsIgnoreCase(button)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Fill cheque details manually with given information")
    public void fillChequeDetails(String chequeNumber, String bankNumber, String branchNumber, String accountNumber) {
        waitForList(chequeInputList);
        fillText(chequeInputList.get(0), chequeNumber);
        fillText(chequeInputList.get(1), bankNumber);
        fillText(chequeInputList.get(2), branchNumber);
        fillText(chequeInputList.get(3), accountNumber);
        waitForElementToBeClickable(finishButton);
        scrollToElement(finishButton);
        click(finishButton);
    }
}