package btrust.onboardingProcess.ui.pagesObject.questionnaire;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TextAreaTypePage extends BasePage {
    public TextAreaTypePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".Questionnaire_questionNumber__3qDmA")
    protected WebElement questionNumber;
    @FindBy(css = ".QuestionItem_title__1Ej2n")
    protected WebElement questionTitle;
    @FindBy(css = ".TypeTextarea_container__1SmxQ > textarea")
    protected WebElement textAreaField;
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

    @Step("Fill in textArea field")
    public void fillTextArea(String text) {
        waitForElementToBeClickable(textAreaField);
        fillText(textAreaField, text);
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
