package btrust.onboardingProcess.ui.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FaceCompareResultsPage extends BasePage {
    public FaceCompareResultsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".Stepper_line__3tRm-")
    protected WebElement progressBar;
    @FindBy(css = ".Completed_title__XTmSP")
    protected WebElement faceCompareTitle;
    @FindBy(css = ".Completed_pictures__al4fl > :nth-child(1) > img")
    protected WebElement livenessPicture;
    @FindBy(css = ".Completed_pictures__al4fl > :nth-child(1) > .Completed_picText__VHFA4")
    protected WebElement livenessDescription;
    @FindBy(css = ".Completed_pictures__al4fl > :nth-child(2) > img")
    protected WebElement ocrPicture;
    @FindBy(css = ".Completed_pictures__al4fl > :nth-child(2) > .Completed_picText__VHFA4")
    protected WebElement ocrDescription;
    @FindBy(css = ".Completed_icon__eHbHT > img")
    protected WebElement faceMatchIcon;
    @FindBy(css = ".Completed_resultText__dtozF")
    protected WebElement faceMatchTitle;
    @FindBy(css = ".Completed_container__hyin7 > div")
    protected WebElement processEndedText;


    @Step("Check if progressBar is displayed")
    public boolean progressBar() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(progressBar);
        return isDisplayed(progressBar);
    }

    @Step("Return the text of face compare title")
    public String faceCompareTitle() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(faceCompareTitle);
        return getText(faceCompareTitle);
    }

    @Step("Check if livenessPicture is displayed")
    public boolean livenessPictureIsDisplayed() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(livenessPicture);
        return imageIsDisplayed(livenessPicture);
    }

    @Step("Return the text of liveness description")
    public String livenessDescription() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(livenessDescription);
        return getText(livenessDescription);
    }

    @Step("Check that ocrPicture is displayed")
    public boolean ocrPicture() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(ocrPicture);
        return imageIsDisplayed(ocrPicture);
    }

    @Step("Return the text of ocr description")
    public String ocrDescription() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(ocrDescription);
        return getText(ocrDescription);
    }

    @Step("Check that faceMatchSuccessIcon is displayed")
    public boolean faceMatchIcon() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(faceMatchIcon);
        return isDisplayed(faceMatchIcon);
    }

    @Step("Return the text of face match title")
    public String faceMatchTitle() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(faceMatchTitle);
        return getText(faceMatchTitle);
    }

    @Step("Return the text of process ended title")
    public String processEndedText() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(processEndedText);
        return getText(processEndedText);
    }
}