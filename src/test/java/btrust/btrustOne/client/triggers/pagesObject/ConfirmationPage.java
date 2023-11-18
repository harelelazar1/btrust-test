package btrust.btrustOne.client.triggers.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ConfirmationPage extends BasePage {


    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    //Workflow area
    @FindBy(css = ".paper.paddingNone.rejectionWidth .text")
    protected WebElement triggerName;
    @FindBy(css = "w .label")
    protected List<WebElement> titleFieldsSelect;
    @FindBy(css = ".css-2b097c-container > .css-1pahdxg-control , .content .blue-false")
    protected List<WebElement> fieldSelect;
    @FindBy(css = ".default__menu-list.css-1m0ufzk .default__option.default__option")
    protected List<WebElement> selectNameFromListSelect;
    @FindBy(css = ".calendar-header > .month")
    protected WebElement month;
    @FindBy(css = ".calendar-header > .btn.next")
    protected WebElement nextMonthButton;
    @FindBy(css = ".react-datepicker__month .react-datepicker__day")
    protected List<WebElement> dayList;

    //Email area
    @FindBy(css = ".EmailTemplateForm-module__select__2hS7- .default__value-container")
    protected WebElement emailField;
    @FindBy(css = ".paddingNone.paper.rejectionWidth  .actions > button[type='button']")
    protected WebElement confirmAndCreateCasesButton;
    @FindBy(css = ".EmailTemplateForm-module__subject__bMJMl [type='button']")
    protected WebElement editMailSubjectButton;
    @FindBy(css = ".EmailTemplateForm-module__editFormWrapper__12NDg [type='button']")
    protected WebElement editMailBodyButton;
    @FindBy(css = ".EmailTemplateForm-module__subject__bMJMl [type='text']")
    protected WebElement subjectField;
    @FindBy(css = "table:nth-of-type(5) td  p")
    protected WebElement bodyField;
    @FindBy(css = ".creating-popup .text")

    //Popup
    protected WebElement creatingPopupTitle;
    @FindBy(css = ".creating-popup .success")
    protected WebElement creatingCasesStatusText;
    @FindBy(css = ".creating-popup .buttons [type=button]")
    protected List<WebElement> creatingCasesButtons;


    @Step("Trigger name")
    public String triggerName() {
        scrollToElement(triggerName);
        return getText(triggerName);
    }


    @Step("Edit Email Subject")
    public void editEmailSubject(String subjectName) {
        scrollToElement(editMailSubjectButton);
        click(editMailSubjectButton);
        fillText(subjectField, subjectName);
    }


    @Step("Edit Email Body")
    public void editEmailBody(String bodyName) {
        scrollToElement(editMailBodyButton);
        click(editMailBodyButton);
        fillText(bodyField, bodyName);
    }

    @Step("Click Confirm And Create Cases Button")
    public void clickConfirmAndCreateCasesButton(String buttonName) {
        scrollToElement(confirmAndCreateCasesButton);
        waitForTextToBeInElement(confirmAndCreateCasesButton, buttonName);
        click(confirmAndCreateCasesButton);
    }


    @Step("Select From Email Template List")
    public void selectFromEmailTemplateList(String nameFromList) {
        scrollToElement(emailField);
        click(emailField);
        sleep(2000);
        waitForElementToBeVisibility(selectNameFromListSelect.get(0));
        for (WebElement el : selectNameFromListSelect) {
            if (getText(el).equalsIgnoreCase(nameFromList)) {
                scrollToElement(el);
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }


    @Step("Popup - click Buttons In Creating Cases")
    public void clickButtonsInCreatingCasesPopup(String buttonName) {
        for (WebElement el : creatingCasesButtons) {
            if (getText(el).equalsIgnoreCase(buttonName)) {
                scrollToElement(el);
                click(el);
                break;
            }
        }
    }


    @Step("Popup - Creating Cases message Text")
    public String creatingCasesMessageText() {
        waitForPageFinishLoading();
        System.out.println(getText(creatingCasesStatusText));
        return getText(creatingCasesStatusText);
    }


    @Step("Creating Popup Title")
    public String creatingPopupTitle() {
        waitForPageFinishLoading();
        return getText(creatingPopupTitle);
    }


    @Step("Fill Fields In Workflow Area")
    public void fillFieldsInWorkflowArea(String titleName, String nameFromList, String typeField, String chooseMonth, String chooseDay) {
        for (int j = 0; j < titleFieldsSelect.size(); j++) {
            waitForElementToBeVisibility(titleFieldsSelect.get(j));
            if (getText(titleFieldsSelect.get(j)).equalsIgnoreCase(titleName)) {

                switch (typeField) {
                    case "Select": {
                        scrollToElement(fieldSelect.get(j));
                        click(fieldSelect.get(j));
                        sleep(2000);
                        waitForElementToBeVisibility(selectNameFromListSelect.get(0));
                        for (WebElement el : selectNameFromListSelect) {
                            if (getText(el).equalsIgnoreCase(nameFromList)) {
                                scrollToElement(el);
                                waitForElementToBeClickable(el);
                                click(el);
                                break;
                            }
                        }
                        break;
                    }
                    case "Date": {
                        scrollToElement(fieldSelect.get(j));
                        click(fieldSelect.get(j));
                        sleep(2000);
                        while (!getText(month).equalsIgnoreCase(chooseMonth)) {
                            click(nextMonthButton);
                        }
                        //Only if the month displayed equaled to the chosen month
                        for (WebElement e : dayList) {
                            if (e.getText().equalsIgnoreCase(chooseDay)) {
                                waitForElementToBeClickable(e);
                                e.click();
                                break;
                            }
                        }
                    }

                }
            }
        }


    }


}





