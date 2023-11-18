package btrust.onboardingProcess.ui.pagesObject.questionnaire;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SelectTypePage extends BasePage {
    public SelectTypePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".Questionnaire_questionNumber__3qDmA")
    protected WebElement questionNumber;
    @FindBy(css = ".QuestionItem_title__1Ej2n")
    protected WebElement questionTitle;
    @FindBy(css = "div.questionnaire__value-container.css-1hwfws3")
    protected WebElement openSelect;
    @FindBy(css = "div.questionnaire__menu.css-26l3qy-menu")
    protected List<WebElement> optionsList;
    @FindBy(css = ".Questionnaire_buttons__1asHW > button:nth-child(2)")
    protected WebElement nextButton;
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

    @Step("Choose options from the list")
    public void chooseOptionsFromList(String chooseOption) {
        waitForElementToBeClickable(openSelect);
        click(openSelect);
        for (WebElement el : optionsList) {
            if (getText(el).contains(chooseOption)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }
}
