package btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;

public class WorkflowPage extends BasePage {
    public WorkflowPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.Title-module__text__3zZ5G")
    protected WebElement workflowTitle;
    @FindBy(css = ".DocumentFormTitle-module__container__H1euF > .DocumentFormTitle-module__description__1Hxp5")
    protected WebElement workflowDescription;
    @FindBy(css = ".WhiteBoxWrapper-module__container__1tY-A>div>:nth-child(1)")
    protected List<WebElement> titleList;
    @FindBy(css = ".WhiteBoxWrapper-module__container__1tY-A>div>:nth-child(2)")
    protected WebElement workflowSelect;
    @FindBy(css = ".default__menu-list.css-1m0ufzk>div")
    protected List<WebElement> workflowsList;
    @FindBy(css = ".DateSelector_container > button")
    protected WebElement caseDueDateSelect;
    @FindBy(css = ".calendar-header > .month")
    protected WebElement month;
    @FindBy(css = ".calendar-header > .btn.next")
    protected WebElement nextMonthButton;
    @FindBy(css = ".react-datepicker__month .react-datepicker__day")
    protected List<WebElement> dayList;
    @FindBy(css = ".BottomStickyActions-module__wrapper__1b1UJ > button")
    protected List<WebElement> buttonsList;


    @Step("Return the text of workflow title")
    public String workflowTitle() {
        waitForElementToBeVisibility(workflowTitle);
        return getText(workflowTitle);
    }

    @Step("Return the text of workflow description")
    public String workflowDescription() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(workflowDescription);
        return getText(workflowDescription);
    }

    @Step("Fill the workflow page")
    public void fillWorkflowPage(String title, String workflow, String chooseMonth, String chooseDay) {
        waitForPageFinishLoading();
        String value = null;
        waitForList(titleList);
        for (WebElement el : titleList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(title)) {
                waitForTextToBeInElement(el, title);
                value = getText(el);
                break;
            }
        }
        switch (Objects.requireNonNull(value)) {
            case "Workflow":
                scrollToElement(workflowSelect);
                click(workflowSelect);
                waitForList(workflowsList);
                for (WebElement element : workflowsList) {
                    scrollToElement(element);
                    if (getText(element).equalsIgnoreCase(workflow)) {
                        click(element);
                        break;
                    }
                }
                break;
            case "Case Due Date":
                scrollToElement(caseDueDateSelect);
                click(caseDueDateSelect);
                while (month() != null) {
                    if (getText(month).equalsIgnoreCase(chooseMonth)) {
                        for (WebElement e : dayList) {
                            scrollToElement(e);
                            if (getText(e).equalsIgnoreCase(chooseDay)) {
                                click(e);
                                break;
                            }
                        }
                        break;
                    } else {
                        scrollToElement(nextMonthButton);
                        click(nextMonthButton);
                    }
                }
        }
        sleep(2000);
    }

    @Step("Return the value of month")
    public String month() {
        waitForElementToBeVisibility(month);
        return getText(month);
    }

    @Step("Click on button: {button}")
    public void chooseButton(String button) {
        waitForPageFinishLoading();
        waitForList(buttonsList);
        for (WebElement el : buttonsList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(button)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Check if button is enabled")
    public boolean buttonIsEnabled(String button) {
        boolean buttonIsEnabled = false;
        for (WebElement el : buttonsList) {
            scrollToElement(el);
            if (getText(el).contains(button)) {
                buttonIsEnabled = isEnabled(el);
                break;
            }
        }
        return buttonIsEnabled;
    }
}