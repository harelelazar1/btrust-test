package btrust.btrustOne.client.entity.pagesObject.CreateEntity;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WorkflowPage extends BasePage {
    public WorkflowPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".WhiteBoxWrapper-module__container__1PzPb .Title-module__text__1LN-A")
    protected WebElement workflowTitle;
    @FindBy(css = ".css-1wy0on6.default__indicators")
    protected WebElement workflowSelectArrowButton;
    @FindBy(css = "[class] [tabindex='-1']")
    protected List<WebElement> workflowSelectList;
    @FindBy(css = ".DateItem-module__inputItem__2H4md .select-button")
    protected WebElement caseDueDateArrowButton;
    @FindBy(css = ".default-select.undefined.css-2b097c-container .default__input input")
    protected WebElement clickOnWorkflowField;



    /*
        case due date elements
    */
    @FindBy(css = ".DateSelector_container > button")
    protected WebElement caseDueDateSelect;
    @FindBy(css = ".calendar-header > .month")
    protected WebElement month;
    @FindBy(css = ".react-datepicker__month .react-datepicker__day")
    protected List<WebElement> dayList;
    @FindBy(css = ".calendar-header > .btn.next")
    protected WebElement nextMonthButton;


    @Step("Return the text of 'workflow title'")
    public String workflowTitle() {
        waitForElementToBeVisibility(workflowTitle);
        scrollToElement(workflowTitle);
        return getText(workflowTitle);
    }

    @Step("choose workflow type")
    public void chooseWorkflowType(String workflowName) {
        waitForElementToBeVisibility(workflowSelectArrowButton);
        scrollToElement(workflowSelectArrowButton);
        click(workflowSelectArrowButton);
        waitForList(workflowSelectList);
        for (WebElement el : workflowSelectList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(workflowName)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("choose workflow type")
    public void chooseWorkflowType1(String workflowName) {
        waitForElementToBeVisibility(workflowSelectArrowButton);
        scrollToElement(workflowSelectArrowButton);
        click(workflowSelectArrowButton);
        waitForList(workflowSelectList);
        clickOnWorkflowField.sendKeys(workflowName);for (WebElement el : workflowSelectList) {
            scrollToElement(el);
            sleep(1000);
            if (getText(el).equalsIgnoreCase(workflowName)) {
                scrollToElement(el);
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("get text of the month")
    protected String month() {
        waitForElementToBeVisibility(month);
        return getText(month);
    }

    @Step("choose workflow due date")
    public void chooseDueDate(String chooseMonth, String chooseDay) {
        waitForElementToBeClickable(caseDueDateSelect);
        click(caseDueDateSelect);
        while (month() != null) {
            if (getText(month).equalsIgnoreCase(chooseMonth)) {
                for (WebElement el : dayList) {
                    System.out.println(getText(el));
                    if (getText(el).equalsIgnoreCase(chooseDay)) {
                        waitForElementToBeClickable(el);
                        el.click();
                        break;
                    }
                }
                break;
            } else {
                click(nextMonthButton);
            }
        }
    }

    @Step("choose workflow due date")
    public void chooseCurrentDate() {
        waitForElementToBeClickable(caseDueDateSelect);

        click(caseDueDateSelect);
        WebElement  currDay=driver.findElement(By.className("react-datepicker__day--today"));
        currDay.click();
    }

}