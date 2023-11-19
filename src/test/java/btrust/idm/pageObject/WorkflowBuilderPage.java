package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WorkflowBuilderPage extends BasePage {
    public WorkflowBuilderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".page-container.workflow-builder > .title ")
    protected WebElement workflowBuilderTitle;
    @FindBy(css = ".search > input")
    protected WebElement containTextField;
    @FindBy(css = ".workflow-topbar > .add-workflow")
    protected WebElement addNewWorkflowButton;
    @FindBy(css = "tr > td:nth-child(2)")
    protected List<WebElement> workflowNameList;
    @FindBy(css = ".no-vendors-container span")
    protected WebElement noWorkflowWereFoundMessage;
    @FindBy(css = ".MuiTableBody-root > tr > td:nth-child(5) ")
    protected List<WebElement> editButtonsList;
    @FindBy(css = ".MuiTable-root > .MuiTableBody-root > .MuiTableRow-root > :nth-child(6) > button")
    protected List<WebElement> removeButtonsList;


    @Step("check workflowBuilderTitle appear")
    public String workflowBuilderTitle(String text) {
        waitForTextToBeInElement(workflowBuilderTitle, text);
        return getText(workflowBuilderTitle);
    }

    @Step("type in contain text field")
    public void containTextField(String value) {
        waitForPageFinishLoading();
        waitForElementToBeClickable(containTextField);
        fillText(containTextField, value);
    }

    @Step("click on add new workflow button")
    public void addNewWorkflow() {
        waitForElementToBeClickable(addNewWorkflowButton);
        click(addNewWorkflowButton);
    }

    @Step("check that no workflow were found message appear")
    public String noWorkflowWereFoundMessage(String text) {
        waitForTextToBeInElement(noWorkflowWereFoundMessage, text);
        return getText(noWorkflowWereFoundMessage);
    }

    @Step("check that the name's user appears in the table")
    public String workflowName(String name) {
        String foundName = null;
        waitForPageFinishLoading();
        waitForList(workflowNameList);
        for (WebElement el : workflowNameList) {
            if (getText(el).equalsIgnoreCase(name)) {
                foundName = getText(el);
                break;
            }
        }
        return foundName;
    }

    @Step("Click on edit button of the workflow: {workflow}")
    public void editWorkflow(String workflow) {
        waitForPageFinishLoading();
        waitForList(editButtonsList);
        for (int i = 0; i < workflowNameList.size(); i++) {
            if (getText(workflowNameList.get(i)).equalsIgnoreCase(workflow)) {
                WebElement btn = editButtonsList.get(i);
                click(btn);
                break;
            }
        }
    }

    @Step("Click on remove button of the workflow: {workflow}")
    public void removeWorkflow(String workflow) {
        waitForPageFinishLoading();
        waitForList(workflowNameList);
        for (int i = 0; i < workflowNameList.size(); i++) {
            if (getText(workflowNameList.get(i)).equalsIgnoreCase(workflow)) {
                waitForElementToBeClickable(removeButtonsList.get(i));
                click(removeButtonsList.get(i));
                break;
            }
        }
    }

    @Step("Check that workflow is not appear in list")
    public boolean checkRemoveWorkflow(String text) {
        waitForPageFinishLoading();
        waitForList(workflowNameList);
        try {
            for (WebElement el : workflowNameList) {
                waitForTextToBeInElement(el, text);
                isDisplayed(el);
                break;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}