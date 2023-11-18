package btrust.onboardingProcess.ui.pagesObject.questionnaire;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivacyLinkTypePage extends BasePage {
    public PrivacyLinkTypePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".Questionnaire_questionNumber__3qDmA")
    protected WebElement questionNumber;
    @FindBy(css = ".QuestionItem_title__1Ej2n")
    protected WebElement questionTitle;
    @FindBy(css = "a[target='_blank']")
    protected WebElement link;
    @FindBy(css = ".TypePrivacyPolicy_agree__2fXPk")
    protected WebElement confirmMessage;
    @FindBy(css = ".TypePrivacyPolicy_agree__2fXPk > .TypePrivacyPolicy_radio__jCJVh")
    protected WebElement confirmButton;
    @FindBy(css = ".Questionnaire_buttons__1asHW > button:nth-child(2)")
    protected WebElement nextButton;
    @FindBy(css = ".Questionnaire_buttons__1asHW > button:nth-child(1)")
    protected WebElement previousButton;
    @FindBy(css = ".QuestionItem_error__3hn-t")
    protected WebElement errorMessage;

    @Step("Check if questionNumberIsDisplayed")
    public boolean questionNumberIsDisplayed(String text) {
        try {
            waitForTextToBeInElement(questionNumber, text);
            isDisplayed(questionNumber);
            return true;
        } catch (Exception e) {
            System.out.println(getText(questionNumber));
            return false;
        }
    }

    @Step("Check if questionTitleIsDisplayed")
    public boolean questionTitleIsDisplayed(String text) {
        try {
            waitForTextToBeInElement(questionTitle, text);
            isDisplayed(questionTitle);
            return true;
        } catch (Exception e) {
            System.out.println(getText(questionTitle));
            return false;
        }
    }

    @Step("Check if linkIsDisplayed")
    public boolean linkIsDisplayed(String text) {
        try {
            waitForTextToBeInElement(link, text);
            isDisplayed(link);
            return true;
        } catch (Exception e) {
            System.out.println(getText(link));
            return false;
        }
    }

    @Step("Check if confirmMessageIsDisplayed")
    public boolean confirmMessageIsDisplayed(String text) {
        try {
            waitForTextToBeInElement(confirmMessage, text);
            isDisplayed(confirmMessage);
            return true;
        } catch (Exception e) {
            System.out.println(getText(confirmMessage));
            return false;
        }
    }

    @Step("Check if confirmButtonIsDisplayed")
    public boolean confirmButtonIsDisplayed() {
        try {
            waitForElementToBeClickable(confirmButton);
            isDisplayed(confirmButton);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    @Step("Check if the confirm button is clickable")
    public boolean clickOnConfirmButton() {
        try {
            waitForElementToBeClickable(confirmButton);
            isDisplayed(confirmButton);
            click(confirmButton);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    @Step("Click on nextButton if it is enabled")
    public boolean nextButton() {
        try {
            waitForElementToBeClickable(nextButton);
            isEnabled(nextButton);
            click(nextButton);
            return true;
        } catch (Exception e) {
            System.out.println(getText(nextButton));
            return false;
        }
    }

    @Step("Click on previousButton if it is enabled")
    public boolean previousButton() {
        try {
            waitForElementToBeClickable(previousButton);
            isEnabled(previousButton);
            click(previousButton);
            return true;
        } catch (Exception e) {
            System.out.println(getText(previousButton));
            return false;
        }
    }

    @Step("Check if errorMessageIsDisplayed")
    public boolean errorMessageIsDisplayed(String text) {
        try {
            waitForTextToBeInElement(errorMessage, text);
            isDisplayed(errorMessage);
            return true;
        } catch (Exception e) {
            System.out.println(getText(errorMessage));
            return false;
        }
    }
}
