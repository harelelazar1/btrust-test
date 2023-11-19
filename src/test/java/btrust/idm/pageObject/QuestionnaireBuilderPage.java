package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class QuestionnaireBuilderPage extends BasePage {
    public QuestionnaireBuilderPage(WebDriver driver) {
        super(driver);
    }

    protected int id;

    @FindBy(css = ".questionnaire > .title > span")
    protected WebElement questionnaireBuilderTitle;
    @FindBy(css = ".top-bar-questionnaire > button")
    protected WebElement addNewQuestionnaireButton;
    @FindBy(css = ".questionnaire-actions > button:nth-child(1)")
    protected WebElement saveButton;
    @FindBy(css = ".questionnaire-actions > button:nth-child(2)")
    protected WebElement addFieldButton;
    @FindBy(css = ".table-row > .item:nth-child(1)")
    protected List<WebElement> questionsNumber;
    @FindBy(css = ".table-body > [role='tabpanel'] > :nth-child(2)")
    protected WebElement questionsLabel;
    @FindBy(css = ".table-body > [role='tabpanel'] > :nth-child(3)")
    protected WebElement questionsName;
    @FindBy(css = ".fields-container > [data-id='input'] > .label")
    protected List<WebElement> keyInputList;
    @FindBy(css = ".fields-container > .row > .value > input")
    protected List<WebElement> valueInputList;
    @FindBy(css = ".fields-container > .row > .value > .default-select")
    protected WebElement questionTypeSelect;
    @FindBy(css = ".default__menu-list > .default__option")
    protected List<WebElement> questionTypeList;
    @FindBy(css = ".value > .MuiSwitch-root")
    protected WebElement requiredCheckbox;
    @FindBy(css = ".value > button")
    protected WebElement removeFieldButton;
    @FindBy(css = ".question-options > button")
    protected WebElement addOptionButton;
    @FindBy(css = ".question-options > li > input")
    protected List<WebElement> questionsOptionsFiledList;
    @FindBy(css = ".question-options > li > button")
    protected List<WebElement> removeOptionsButtonList;
    @FindBy(css = ".table-row > :nth-child(3) > button")
    protected List<WebElement> editQuestionnaireButtonList;
    @FindBy(css = "tr > td:nth-child(4) > .btn")
    protected List<WebElement> removeQuestionnaireButtonList;
    @FindBy(css = "tr > td:nth-child(1)")
    protected List<WebElement> questionnaireIDList;
    @FindBy(css = ".MuiTableBody-root > tr")
    protected List<WebElement> questionnaireList;
    @FindBy(css = ".snack-container > .text > span")
    protected WebElement tooltip;
    @FindBy(css = "[data-id='input']:nth-child(8) > .value > input")
    protected WebElement confirmMessageInput;

    @Step("Check if questionnaireBuilderTitleIsDisplayed")
    public boolean questionnaireBuilderTitleIsDisplayed(String title) {
        try {
            waitForTextToBeInElement(questionnaireBuilderTitle, title);
            isDisplayed(questionnaireBuilderTitle);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Step("Click on addNewQuestionnaireButton")
    public void addNewQuestionnaireButton() {
        waitForPageFinishLoading();
        waitForElementToBeClickable(addNewQuestionnaireButton);
        click(addNewQuestionnaireButton);
    }

    @Step("Click on saveButton")
    public void saveButton() {
        waitForElementToBeClickable(saveButton);
        sleep(2000);
        click(saveButton);
        Assert.assertTrue(questionnaireBuilderTitleIsDisplayed("Questionnaire Builder"));
    }

    @Step("Click on addFieldButton")
    public void addFieldButton() {
        waitForPageFinishLoading();
        scrollToElement(addFieldButton);
        waitForElementToBeClickable(addFieldButton);
        click(addFieldButton);
        System.out.println("done");
    }

    @Step("Choose questions from the list")
    public void chooseQuestions() {
        waitForPageFinishLoading();
        waitForList(questionsNumber);
        click(questionsNumber.get(0));
    }

    @Step("Create question")
    public String fillNewQuestions(String question, String value) {
        String val = null;
        waitForPageFinishLoading();
        scrollToElement(keyInputList.get(0));
        for (int i = 0; i < keyInputList.size(); i++) {
            if (getText(keyInputList.get(i)).equalsIgnoreCase(question)) {
                waitForElementToBeClickable(valueInputList.get(i));
                fillText(valueInputList.get(i), value);
                val = getAttribute(valueInputList.get(i), "value");
                System.out.println(val);
                break;
            }
        }
        return val;
    }

    @Step("Fill confirm message with confirmation text")
    public void fillConfirmMessage(String value) {
        waitForElementToBeVisibility(confirmMessageInput);
        scrollToElement(confirmMessageInput);
        fillText(confirmMessageInput, value);
    }

    @Step("Choose question type")
    public void chooseQuestionType(String chooseFromQuestionTypeList) {
        waitForPageFinishLoading();
        waitForElementToBeClickable(questionTypeSelect);
        click(questionTypeSelect);
        for (WebElement el : questionTypeList) {
            if (getText(el).equalsIgnoreCase(chooseFromQuestionTypeList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Select requiredCheckbox")
    public void requiredCheckbox() {
        waitForElementToBeClickable(requiredCheckbox);
        click(requiredCheckbox);
    }

    @Step("Click on removeFieldButton")
    public void removeFieldButton() {
        waitForElementToBeClickable(removeFieldButton);
        click(removeFieldButton);
    }

    @Step("Add option")
    public void addOptions(String text) {
        waitForElementToBeClickable(addOptionButton);
        click(addOptionButton);
        waitForList(questionsOptionsFiledList);
        sleep(2000);
        List<WebElement> input = questionsOptionsFiledList;
        WebElement el = input.get(questionsOptionsFiledList.size() - 1);
        fillText(el, text);
    }

    @Step("Adds label to link")
    public void addLabelToLink(String label, String text) {
        waitForElementToBeClickable(addOptionButton);
        click(addOptionButton);
        waitForList(questionsOptionsFiledList);
        sleep(2000);
        List<WebElement> input = questionsOptionsFiledList;
        WebElement labelElement = input.get(questionsOptionsFiledList.size() - 2);
        waitForElementToBeVisibility(labelElement);
        fillText(labelElement, label);
        WebElement linkElement = input.get(questionsOptionsFiledList.size() - 1);
        waitForElementToBeVisibility(linkElement);
        fillText(linkElement, text);
    }

    @Step("Remove option")
    public void removeOptions() {
        waitForList(removeOptionsButtonList);
        for (WebElement el : removeOptionsButtonList) {
            Select select = new Select(el);
            select.selectByIndex(select.getOptions().size() - 1);
            click(el);
            break;
        }
    }

    @Step("Click on the last removeQuestionnaireButton")
    public void removeQuestionnaireButton() {
        waitForList(removeQuestionnaireButtonList);
        click(removeQuestionnaireButtonList.get(removeQuestionnaireButtonList.size() - 1));
    }

    @Step("Click on editQuestionnaireButton")
    public void editQuestionnaireButton() {
        waitForPageFinishLoading();
        waitForList(editQuestionnaireButtonList);
        scrollToElement(editQuestionnaireButtonList.get(editQuestionnaireButtonList.size() - 1));
        click(editQuestionnaireButtonList.get(editQuestionnaireButtonList.size() - 1));
    }

    @Step("Return the last questionnaireID")
    public String getTheLastquestionnaireID() {
        waitForList(questionnaireIDList);
        waitForElementToBeClickable(questionnaireIDList.get(questionnaireIDList.size() - 1));
        return getText(questionnaireIDList.get(questionnaireIDList.size() - 1));
    }

    @Step("Return total questionnaireList")
    public int questionnaireList() {
        waitForList(questionnaireList);
        id = questionnaireList.size();
        return id;
    }

    @Step("Return questionsLabel")
    public String questionsLabel(String text) {
        waitForPageFinishLoading();
        scrollToElement(questionsLabel);
        waitForTextToBeInElement(questionsLabel, text);
        return getText(questionsLabel);
    }

    @Step("Return questionsName")
    public boolean questionsName(String waitForText) {
        System.out.println(getText(questionsName));
        try {
            waitForTextToBeInElement(questionsName, waitForText);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Step("Check if questionnaireIDIsDisplayed")
    public boolean questionnaireIDIsDisplayed() {
        try {
            waitForList(questionnaireList);
            isDisplayed(questionnaireIDList.get(questionnaireIDList.size() - 1));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Step("Check if tooltipIsDisplayed")
    public boolean tooltipIsDisplayed(String text) {
        try {
            waitForTextToBeInElement(tooltip, text);
            isDisplayed(tooltip);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Step("Check that have 5 questionnaires and remove if have more")
    public void display5Questionnaires() {
        waitForPageFinishLoading();
        waitForList(questionnaireIDList);
        for (int i = 0; i <= questionnaireIDList.size(); i++) {
            while (questionnaireIDList.size() > 5) {
                WebElement el = questionnaireIDList.get(i);
                String id = getText(el);
                if (!id.equalsIgnoreCase("278") && !id.equalsIgnoreCase("579") && !id.equalsIgnoreCase("626") && !id.equalsIgnoreCase("660") && !id.equalsIgnoreCase("662")) {
                    WebElement removeButton = removeQuestionnaireButtonList.get(i);
                    scrollToElement(removeButton);
                    waitForElementToBeClickable(removeButton);
                    click(removeButton);
                    sleep(2000);
                }
                break;
            }
        }
    }
}