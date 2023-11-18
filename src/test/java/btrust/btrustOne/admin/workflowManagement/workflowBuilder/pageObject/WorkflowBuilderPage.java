package btrust.btrustOne.admin.workflowManagement.workflowBuilder.pageObject;

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

    @FindBy(css = ".SettingsHeader-module__wrapper__2OOUN.undefined>.SettingsHeader-module__title__acJ6f")
    protected WebElement workflowBuilderTitle;
    @FindBy(css = ".SettingsHeader-module__title__acJ6f .AddButton-module__btn__2_wO7")
    protected WebElement addWorkflowBuilder;
    @FindBy(css = ".MuiButtonBase-root.MuiButton-root.MuiButton-text")
    protected List<WebElement> hamburgerMenuButton;
    @FindBy(css = ".Table-module__row__2xpKc :nth-child(2).Table-module__item__HOh1R")
    protected List<WebElement> nameFromWorkflowList;
    @FindBy(css = ".paper.paddingNone  .WorkflowPopup-module__content__1Hw6K .WorkflowPopup-module__label__7FLKZ")
    protected List<WebElement> titleFieldNameList;
    @FindBy(css = ".WorkflowPopup-module__content__1Hw6K > div:nth-of-type(1) > input")
    protected WebElement inputField;
    @FindBy(css = ".WorkflowPopup-module__content__1Hw6K  .default__value-container.css-1hwfws3,.WorkflowPopup-module__content__1Hw6K > div:nth-of-type(1) > input")
    protected List<WebElement> selectFieldList;
    @FindBy(css = " .WorkflowPopup-module__content__1Hw6K  .default__menu.css-26l3qy-menu .default__option")
    protected List<WebElement> selectList;
    @FindBy(css = ".WorkflowPopup-module__content__1Hw6K  .MuiIconButton-label")
    protected WebElement radioButton;
    @FindBy(css = ".WorkflowPopup-module__container__3DHuv :nth-child(3) button")
    protected List<WebElement> createOrCancelButtons;


    @Step("Click Create Or Cancel Buttons")
    public void clickCreateOrCancelButtons(String buttonName) {
        for (WebElement el : createOrCancelButtons) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(buttonName)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }


    @Step("Create New Workflow")
    public void createNewWorkflow(String fieldTitle, String fieldType, String inputFieldValue, String nameFromList) {
        waitForPageFinishLoading();

        switch (fieldType) {
            case "Input": {
                scrollToElement(inputField);
                fillText(inputField, inputFieldValue);
                break;
            }
            case "Select": {
                for (int i = 0; i < titleFieldNameList.size(); i++) {
                    scrollToElement(titleFieldNameList.get(i));
                    if (getText(titleFieldNameList.get(i)).equalsIgnoreCase(fieldTitle)) {
                        waitForPageFinishLoading();
                        scrollToElement(selectFieldList.get(i));
                        click(selectFieldList.get(i));
                        for (WebElement el : selectList) {
                            scrollToElement(el);
                            if (getText(el).equalsIgnoreCase(nameFromList)) {
                                waitForElementToBeClickable(el);
                                click(el);
                                break;
                            }
                        }
                    }
                }
                break;
            }
            case "RadioButton": {
                scrollToElement(radioButton);
                click(radioButton);
                break;
            }
        }

    }


    @Step("Workflow Builder Title")
    public String workflowBuilderTitle(String text) {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(workflowBuilderTitle);
        return getText(workflowBuilderTitle);
    }

    @Step("Click add New Workflow")
    public void addNewWorkflowButton() {
        waitForPageFinishLoading();
        waitForElementToBeClickable(addWorkflowBuilder);
        click(addWorkflowBuilder);
    }

    @Step("Check if add New Document Button display")
    public boolean checkIfNewDocumentButtonDisplay() {
        try {
            waitForPageFinishLoading();
            if (isDisplayed(addWorkflowBuilder)) {
                scrollToElement(addWorkflowBuilder);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("Check if hamburger menu button display")
    public boolean checkIfHamburgerMenuButtonDisplay() {
        try {
            waitForPageFinishLoading();
            for (WebElement el : hamburgerMenuButton) {
                if (isDisplayed(el)) {
                    scrollToElement(addWorkflowBuilder);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("Choose Workflow Name From List")
    public void chooseWorkflowNameFromList(String workflowName) {
        waitForPageFinishLoading();
        for (WebElement el : nameFromWorkflowList) {
            scrollToElement(el);
            if (getText(el).equals(workflowName)) {
                click(el);
                break;
            }
        }
    }


    @Step("Check Workflow name in workflow builder list")
    public boolean checkWorkflowName(String workflowName) {
        try {
            waitForPageFinishLoading();
            for (WebElement el : nameFromWorkflowList) {
                scrollToElement(el);
                if (getText(el).equalsIgnoreCase(workflowName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return false;
    }

}



