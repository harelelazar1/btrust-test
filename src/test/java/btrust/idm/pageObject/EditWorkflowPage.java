package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditWorkflowPage extends BasePage {
    public EditWorkflowPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".title > .icon")
    protected WebElement editWorkflowName;
    @FindBy(css = ".btns > :nth-child(2)")
    protected WebElement editButton;
    @FindBy(css = ".btns > :nth-child(1)")
    protected WebElement cancelButton;
    @FindBy(css = ".workflow-name > input")
    protected WebElement workflowNameField;
    @FindBy(css = ".warning-popup > .btns > :nth-child(1)")
    protected WebElement cancelButtonPopup;
    @FindBy(css = ".warning-popup > .btns > :nth-child(2)")
    protected WebElement approveButtonPopup;
    @FindBy(css = ".page-container.workflow-builder > .title")
    protected WebElement workflowName;
    @FindBy(css = ".h-title > span")
    protected WebElement editPopupTitle;
    @FindBy(css = "#a_unique_id-body > #a_unique_id")
    protected WebElement jsonField;

    @Step("Edit workflow name and click edit")
    public void editWorkflowName(String name) {
        waitForPageFinishLoading();
        scrollToElement(editWorkflowName);
        waitForElementToBeClickable(editWorkflowName);
        click(editWorkflowName);
        waitForElementToBeClickable(workflowNameField);
        fillText(workflowNameField, name);
        scrollToElement(editButton);
        waitForElementToBeClickable(editButton);
        click(editButton);
    }

    @Step("click edit button")
    public void editButton() {
        scrollToElement(editButton);
        waitForElementToBeClickable(editButton);
        click(editButton);
    }

    @Step("click cancel in approve change popup")
    public void cancelButtonPopup() {
        waitForTextToBeInElement(editPopupTitle, "Approve changes");
        waitForElementToBeClickable(cancelButtonPopup);
        click(cancelButtonPopup);
    }

    @Step("click approve in approve change popup")
    public void approveButtonPopup() {
        scrollToElement(editPopupTitle);
        waitForTextToBeInElement(editPopupTitle, "Approve changes");
        scrollToElement(approveButtonPopup);
        waitForElementToBeClickable(approveButtonPopup);
        click(approveButtonPopup);
    }

    @Step("check the workflow name appear")
    public String workflowName(String text) {
        waitForPageFinishLoading();
        scrollToElement(workflowName);
        waitForTextToBeInElement(workflowName, text);
        return getText(workflowName);
    }

    @Step("Fill json field")
    public void editJsonField(String json) {
        waitForElementToBeClickable(jsonField);
        clear(jsonField);
        fillText(jsonField, json);
    }
}