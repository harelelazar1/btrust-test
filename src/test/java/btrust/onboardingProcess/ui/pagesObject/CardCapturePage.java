package btrust.onboardingProcess.ui.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CardCapturePage extends BasePage {
    public CardCapturePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h2 > span")
    protected WebElement cardCaptureTitle;
    @FindBy(css = ".Intro_btn__2QbrO > button")
    protected WebElement okButton;
    @FindBy(css = ".scanovate-card-capture-index-video-container")
    protected WebElement videoFrame;


    @Step("Return the text of card capture title")
    public String cardCaptureTitle() {
        waitForElementToBeVisibility(cardCaptureTitle);
        return getText(cardCaptureTitle);
    }

    @Step("Click on ok button")
    public void okButton() {
        scrollToElement(okButton);
        waitForElementToBeClickable(okButton);
        click(okButton);
    }

    @Step("Click on video frame")
    public void videoFrame() {
        waitForPageFinishLoading();
        waitForElementToBeClickable(videoFrame);
        click(videoFrame);
    }
}