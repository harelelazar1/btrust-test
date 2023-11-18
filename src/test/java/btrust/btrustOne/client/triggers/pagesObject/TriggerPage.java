package btrust.btrustOne.client.triggers.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TriggerPage extends BasePage {
    public TriggerPage(WebDriver driver) {
        super(driver);
    }

    String EntityName;


    @FindBy(css = ".DateSelector_container > button")
    protected WebElement caseDueDateSelect;
    @FindBy(css = ".calendar-header > .month")
    protected WebElement month;
    @FindBy(css = ".calendar-header > .btn.next")
    protected WebElement nextMonthButton;
    @FindBy(css = ".react-datepicker__month .react-datepicker__day")
    protected List<WebElement> dayList;
    //    @FindBy(css = ".MuiTableRow-root.table-row .MuiIconButton-label [type='checkbox']")
//    protected List<WebElement> markCheckBox;
    @FindBy(css = ".MuiTableCell-root.MuiTableCell-body .MuiButtonBase-root.MuiIconButton-root")
    protected List<WebElement> markCheckBox;
    @FindBy(css = ".MuiTableRow-root.table-row  :nth-child(3)")
    protected List<WebElement> entityName;
    @FindBy(css = ".MuiTableCell-root.MuiTableCell-body:nth-child(3)")
    protected List<WebElement> entityNameField;
    @FindBy(css = ".actions > :nth-child(1)")
    protected WebElement ButtonPostponeRecertification;
    @FindBy(css = ".actions > :nth-child(2)")
    protected WebElement ButtonOpenCases;


    @Step("fill Due Date")
    public void fillDueDate(String chooseMonth, String chooseDay) {
        waitForElementToBeClickable(caseDueDateSelect);
        click(caseDueDateSelect);
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

    @Step("check if checkbox display")
    public boolean checkIfCheckBoxDisplay() {
        try {
            waitForPageFinishLoading();
            for (WebElement el : markCheckBox) {
                if (isDisplayed(el)) {
                    scrollToElement(el);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Step("Mark checkbox in trigger page")
    public boolean markCheckboxInTrigger(String name) {
        waitForPageFinishLoading();
        for (int i = 0; i < entityName.size(); i++) {
            if (getText(entityName.get(i)).equalsIgnoreCase(name)) {
                scrollToElement(markCheckBox.get(i));
                click(markCheckBox.get(i));
                return true;
            }
        }
        return false;
    }


    @Step("Mark checkbox in trigger page")
    public String returnEntityName() {
        waitForPageFinishLoading();
        for (int i = 0; i < markCheckBox.size(); i++) {
            click(markCheckBox.get(i));
            scrollToElement(entityName.get(i));
            EntityName = getText(entityName.get(i));
        }
        return EntityName;
    }

    @Step("Mark first checkbox in trigger page")
    public void markFirstCheckboxInEntitiesList() {
        waitForPageFinishLoading();
        for (WebElement el : markCheckBox) {
            scrollToElement(el);
            waitForElementToBeClickable(el);
            click(el);
            break;
        }
    }


    @Step("check if button display")
    public boolean checkButtonPostponeRecertification(String buttonName) {
        try {
            waitForPageFinishLoading();
            scrollToElement(ButtonPostponeRecertification);
            waitForElementToBeVisibility(ButtonPostponeRecertification);

            if (getText(ButtonPostponeRecertification).equalsIgnoreCase(buttonName)) {
                waitForTextToBeInElement(ButtonPostponeRecertification, buttonName);
                return true;
            }

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("click Button Postpone Or OpenCase")
    public void clickOnButtonPostponeOrOpenCase(String buttonName) {
        scrollToElement(ButtonOpenCases);
        waitForTextToBeInElement(ButtonOpenCases, buttonName);
        click(ButtonOpenCases);


    }
}