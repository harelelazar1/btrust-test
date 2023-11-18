package btrust.onboardingProcess.ui.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ScanLivenessPage extends BasePage {
    public ScanLivenessPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".Stepper_line__3tRm-")
    protected WebElement progressBar;
    @FindBy(css = "div img")
    protected WebElement scanLivenessImage;
    @FindBy(css = "div h2")
    protected WebElement scanLivenessTitle;
    @FindBy(css = "div h3")
    protected WebElement scanLivenessSubTitle;
    @FindBy(css = ".ProcessIntro_description__MRoFD")
    protected WebElement scanLivenessDescription;
    @FindBy(css = "div button")
    protected WebElement startButton;
    @FindBy(css = ".header")
    protected WebElement errorMessage;

    @Step("Check if progressBar is displayed")
    public boolean progressBar() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(progressBar);
        return isDisplayed(progressBar);
    }

    @Step("Check if scanLivenessImage is displayed")
    public boolean scanLivenessImage() {
        waitForElementToBeVisibility(scanLivenessImage);
        return imageIsDisplayed(scanLivenessImage);
    }

    @Step("Return the text of scan liveness page title")
    public String scanLivenessTitle() {
        waitForElementToBeVisibility(scanLivenessTitle);
        return getText(scanLivenessTitle);
    }


    @Step("Return the text of scan liveness page sub title")
    public String scanLivenessSubTitle() {
        waitForElementToBeVisibility(scanLivenessSubTitle);
        return getText(scanLivenessSubTitle);
    }

    @Step("Return the text of scan liveness page description")
    public String scanLivenessDescription() {
        waitForElementToBeVisibility(scanLivenessDescription);
        return getText(scanLivenessDescription);
    }

    @Step("Return the text of error message")
    public String errorMessage() {
        waitForElementToBeVisibility(errorMessage);
        return getText(errorMessage);
    }

    @Step("Click on start button")
    public void startButton() {
        waitForPageFinishLoading();
        waitForElementToBeClickable(startButton);
        click(startButton);
    }
}