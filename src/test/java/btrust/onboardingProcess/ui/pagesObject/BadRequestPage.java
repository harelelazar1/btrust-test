package btrust.onboardingProcess.ui.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BadRequestPage extends BasePage {
    public BadRequestPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "img")
    protected WebElement badRequestImage;
    @FindBy(css = ".BadRequest_container__SLTZR > :nth-child(2) > span")
    protected WebElement badRequestTitle;
    @FindBy(css = ".BadRequest_container__SLTZR > :nth-child(3) > span")
    protected WebElement badRequestDescription;


    @Step("Return the text of bad request title")
    public String badRequestTitle() {
        waitForElementToBeVisibility(badRequestTitle);
        return getText(badRequestTitle);
    }

    @Step("Return the text of bad request description")
    public String badRequestDescription() {
        waitForElementToBeVisibility(badRequestDescription);
        return getText(badRequestDescription);
    }

    @Step("Check if bad request image is displayed")
    public boolean badRequestImageIsDisplayed() {
        waitForElementToBeVisibility(badRequestImage);
        return imageIsDisplayed(badRequestImage);
    }
}