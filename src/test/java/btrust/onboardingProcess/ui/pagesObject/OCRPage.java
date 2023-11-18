    package btrust.onboardingProcess.ui.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OCRPage extends BasePage {
    public OCRPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".Stepper_line__3tRm-")
    protected WebElement progressBar;
    @FindBy(css = "div img")
    protected WebElement ocrPicture;
    @FindBy(css = "div h2")
    protected WebElement ocrTitle;
    @FindBy(css = "div h3")
    protected WebElement subTitleOCRPage;
    @FindBy(css = "div>:nth-child(4)")
    protected WebElement ocrDescription;
    @FindBy(css = "div button")
    protected WebElement startScanButton;
    @FindBy(css = ".Version_number__2UVsK")
    protected WebElement version;
    @FindBy(css = "div button")
    protected List<WebElement> buttonList;

    @FindBy(css = ".ChangeLanguage_container__langButton__wuR\\+j")
    protected WebElement changeLanguageButton;
    @FindBy(css = ".LanguageList_langItem__container__ox24P")
    protected List<WebElement> chooseLanguageButton;

    @Step("Choose langauge")
    public void chooseLanguage(String languageName) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        waitForElementToBeClickable(changeLanguageButton);
        click(changeLanguageButton);
        waitForList(chooseLanguageButton);
        for (WebElement el : chooseLanguageButton) {
            if (getText(el).equalsIgnoreCase(languageName)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Check if progressBar is dispayed")
    public boolean progressBar() {
        waitForElementToBeVisibility(progressBar);
        return isDisplayed(progressBar);
    }

    @Step("Check if version is displayed")
    public boolean version() {
        waitForElementToBeVisibility(version);
        return isDisplayed(version);
    }

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

    @Step("Check if start scan button is displayed")
    public boolean startScanButtonEnable() {
        waitForElementToBeVisibility(startScanButton);
        return isDisplayed(startScanButton);
    }

    @Step("Click on button: {button}")
    public void clickOnButton(String button) {
        waitForElementToBeClickable(buttonList.get(0));
        for (WebElement el : buttonList) {
            if (getText(el).equalsIgnoreCase(button)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Return text of button: {button}")
    public String getTextOfButton(String button) {
        String buttonText = null;
        waitForElementToBeClickable(startScanButton);
        for (WebElement el : buttonList) {
            if (getText(el).contains(button)) {
                waitForElementToBeClickable(el);
                buttonText = getText(el);
                break;
            }
        }
        return buttonText;
    }
}