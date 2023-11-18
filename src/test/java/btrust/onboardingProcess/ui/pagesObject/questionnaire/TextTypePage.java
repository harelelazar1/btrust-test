package btrust.onboardingProcess.ui.pagesObject.questionnaire;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TextTypePage extends BasePage {
    public TextTypePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".Questionnaire_questionNumber__3qDmA")
    protected WebElement questionNumber;
    @FindBy(css = ".QuestionItem_title__1Ej2n")
    protected WebElement questionTitle;
    @FindBy(css = ".TypeTextField_conteiner__13e3b > input")
    protected WebElement answerField;
    @FindBy(css = ".Questionnaire_buttons__1asHW > button")
    protected List<WebElement> buttonsList;
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

    @Step("Fill answer: {answer}")
    public void fillAnswer(String answer) {
        waitForElementToBeClickable(answerField);
        fillText(answerField, answer);
    }

    @Step("Click on button if it is enabled")
    public void clickOnButton(String button) {
        waitForList(buttonsList);
        for (WebElement el : buttonsList) {
            if (getText(el).equalsIgnoreCase(button)) {
                try {
                    waitForElementToBeClickable(el);
                    click(el);
                    break;
                } catch (Exception e) {
                    System.out.println(getText(el));
                }
            }
        }
    }

    @Step("Check if errorMessageIsDisplayed")
    public String errorMessageIsDisplayed() {
        waitForElementToBeVisibility(errorMessage);
        return getText(errorMessage);
    }
}