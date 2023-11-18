package btrust.btrustOne.admin.workflowManagement.workflowBuilder.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class WorkflowBuilderDefinitionPage extends BasePage {
    public WorkflowBuilderDefinitionPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".WorkflowBuilderItem-module__main__1MikF .EditTitleInput-module__title__28vuN")
    protected WebElement workflowName;
    @FindBy(css = ".EditTitleInput-module__title__28vuN .EditTitleInput-module__edit__2ZSDr")
    protected WebElement editTheWorkFlowNameButton;
    @FindBy(css = ".Header-module__btns__1sP1I .Header-module__edit__i5ADQ")
    protected WebElement editFlowParametersButton;
    @FindBy(css = ".Sidebar-module__container__395B1 .SidebarMenu-module__title__2A1NN")
    protected List<WebElement> leftSideMenu;
    @FindBy(css = ".SidebarMenu-module__menu__1R0dY > div > div .SidebarMenu-module__text__3v2A5")
    protected List<WebElement> taskTypeNameList;
    @FindBy(css = ".SidebarMenu-module__menu__1R0dY > div > .SidebarMenu-module__item__2-eGa.false")
    protected List<WebElement> taskTypeNameForDragList;
    @FindBy(css = ".Drawer-module__container__1EIOc .EditTitleInput-module__edit__2ZSDr")
    protected List<WebElement> informationButton;
    @FindBy(css = ".Plus-module__container__V-YVE.false.false")
    protected List<WebElement> addTaskPlace;
    @FindBy(css = ".react-flow__nodes .Task-module__title__3rMrh")
    protected List<WebElement> taskNameInWorkflow;
    @FindBy(css = ".react-flow__nodes .Task-module__icon__3RG9G >.MuiSvgIcon-root")
    protected List<WebElement> taskPlaceInWorkflow;
    @FindBy(css = ".Screening-module__tabContainer__ZLUtk .Screening-module__label__9i6aE")
    protected List<WebElement> searchTypeTitleList;
    @FindBy(css = ".default__value-container.css-1hwfws3")
    protected List<WebElement> searchTypeFieldList;
    @FindBy(css = ".Header-module__btns__1sP1I [type=button]")
    protected List<WebElement> clickOnButton;
    @FindBy(css = ".BackToList-module__container__3r40F [type=button]")
    protected WebElement backToListButton;


    //Screening Settings tab
    @FindBy(css = ".MuiButtonBase-root.MuiTab-root .MuiTab-wrapper")
    protected List<WebElement> tabNameList;
    @FindBy(css = ".DragableFieldsSources-module__container__Rrt0W.null  .DragableFieldsSources-module__black__1Qzz7")
    protected List<WebElement> mandatoryFieldsNameList;
    @FindBy(css = ".DragableFieldsSources-module__row__2vtW7.false  :nth-child(2) .default__value-container ")
    protected List<WebElement> typeFieldsNameList;
    @FindBy(css = ".DragableFieldsSources-module__row__2vtW7.false  :nth-child(3) .default__value-container ")
    protected List<WebElement> sourceFieldsNameList;
    @FindBy(css = ".DragableFieldsSources-module__row__2vtW7.false  :nth-child(4) .default__value-container ")
    protected List<WebElement> FieldsNameList;
    @FindBy(css = ".default__menu.css-26l3qy-menu .default__option.default__option")
    protected List<WebElement> selectList;

    //Task Settings tab
    @FindBy(css = ".Task-module__container__3kP18 .Task-module__name__rt7jv")
    protected List<WebElement> taskSettingNameList;
    @FindBy(css = ".Mui-checked.Task-module__checked__Q2TfN")
    protected List<WebElement> RadioButtonMarkList;
    @FindBy(css = ".RDCScreening-module__container__1rtY5 input")
    protected List<WebElement> allRadioButtons;
    @FindBy(css = ".RDCScreening-module__container__1rtY5 input")
    protected List<WebElement> clickRadioButtons;
    @FindBy(css = ".InputNumber-module__container__30y1v.undefined input")
    protected WebElement durationInputField;





    @Step("click on Back To List Button")
    public void clickBackToListButton() {
        waitForElementToBeClickable(backToListButton);
        click(backToListButton);
    }


    @Step("Click on button: {button}")
    public void chooseButton(String button) {
        for (WebElement el : clickOnButton) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(button) && isEnabled(el)) {
                click(el);
                break;
            }
        }
    }


    @Step("Fill mandatory fields")
    public void fillMandatoryFields(String mandatoryName, String fieldType, String nameFromList) {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(mandatoryFieldsNameList.get(0));
        for (int i = 0; i < mandatoryFieldsNameList.size(); i++) {
            scrollToElement(mandatoryFieldsNameList.get(i));
            if (getText(mandatoryFieldsNameList.get(i)).equalsIgnoreCase(mandatoryName)) {
                switch (fieldType) {
                    case "Type": {
                        waitForPageFinishLoading();
                        scrollToElement(typeFieldsNameList.get(i));
                        click(typeFieldsNameList.get(i));
                        for (WebElement el : selectList) {
                            scrollToElement(el);
                            if (getText(el).equalsIgnoreCase(nameFromList)) {
                                //                    scrollToElement(el);
                                click(el);
                                break;
                            }
                        }
                        break;
                    }
                    case "Source": {
                        scrollToElement(sourceFieldsNameList.get(i));
                        click(sourceFieldsNameList.get(i));
                        for (WebElement el : selectList) {
                            scrollToElement(el);
                            if (getText(el).equalsIgnoreCase(nameFromList)) {
                                scrollToElement(el);
                                click(el);
                                break;
                            }
                        }
                        break;
                    }
                    case "Field": {
                        scrollToElement(FieldsNameList.get(i));
                        click(FieldsNameList.get(i));
                        for (WebElement el : selectList) {
                            scrollToElement(el);
                            if (getText(el).equalsIgnoreCase(nameFromList)) {
                                scrollToElement(el);
                                click(el);
                                break;
                            }
                        }
                        break;
                    }

                }

            }
        }
    }


    @Step("Mark setting in the Task setting tab")
    public void markSettingInTheTaskSettingTab(String settingName, String fieldType, String inputFieldValue) {
        waitForPageFinishLoading();
        for (int i = 0; i < taskSettingNameList.size(); i++) {
            scrollToElement(taskSettingNameList.get(i));
            if (getText(taskSettingNameList.get(i)).equalsIgnoreCase(settingName)) {
                switch (fieldType) {
                    case "Input": {
                        scrollToElement(allRadioButtons.get(i));
                        fillText(allRadioButtons.get(i), inputFieldValue);
                        break;
                    }
                    case "RadioButton": {
                        scrollToElement(allRadioButtons.get(i));
                        if (!isSelected(allRadioButtons.get(i))) {
                            click(allRadioButtons.get(i));
                            Assert.assertTrue(isSelected(allRadioButtons.get(i)));
                            break;
                        }
                        break;
                    }
                }
            }

        }
    }


    @Step("Unmarked setting in the Task setting tab")
    public void unmarkedSettingInTheTaskSettingTab(String settingName, String fieldType, String inputFieldValue) {
        waitForPageFinishLoading();
        for (int i = 0; i < taskSettingNameList.size(); i++) {
            scrollToElement(taskSettingNameList.get(i));
            if (getText(taskSettingNameList.get(i)).equalsIgnoreCase(settingName)) {
                switch (fieldType) {
                    case "Input": {
                        scrollToElement(allRadioButtons.get(i));
                        fillText(allRadioButtons.get(i), inputFieldValue);
                        break;
                    }
                    case "RadioButton": {
                        scrollToElement(allRadioButtons.get(i));
                        if (isSelected(allRadioButtons.get(i))) {
                            click(allRadioButtons.get(i));
                            Assert.assertTrue(!isSelected(allRadioButtons.get(i)));
                            break;
                        }
                        break;
                    }
                }
            }

        }
    }


    @Step("Click On Tab By Name")
    public void clickOnTabByName(String tabName) {
        for (WebElement el : tabNameList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(tabName)) {
                scrollToElement(el);
                click(el);
                break;
            }
        }
    }


    @Step("Click On Task In Workflow")
    public void clickOnTaskInWorkflow(String taskName) {
        waitForPageFinishLoading();
        sleep(3000);
        for (int i = 0; i < taskNameInWorkflow.size(); i++) {
            scrollToElement(taskNameInWorkflow.get(i));
            if (getText(taskNameInWorkflow.get(i)).equalsIgnoreCase(taskName)) {
                sleep(3000);
                scrollToElement(taskPlaceInWorkflow.get(i));
                waitForElementToBeClickable(taskPlaceInWorkflow.get(i));
                click(taskPlaceInWorkflow.get(i));
                break;
            }
        }
    }


    @Step("Fill Search Type Fields")
    public void fillSearchTypeFields(String searchTypeTitle, String nameFromList) {
        for (int i = 0; i < searchTypeTitleList.size(); i++) {
            scrollToElement(searchTypeTitleList.get(i));
            if (getText(searchTypeTitleList.get(i)).equalsIgnoreCase(searchTypeTitle)) {
                scrollToElement(searchTypeFieldList.get(i));
                click(searchTypeFieldList.get(i));
                for (WebElement el : selectList) {
                    scrollToElement(el);
                    if (getText(el).equalsIgnoreCase(nameFromList)) {
                        scrollToElement(el);
                        click(el);
                        break;
                    }
                }
            }
        }
    }


    @Step("Check Workflow Name")
    public void checkWorkflowName(String name) {
        waitForPageFinishLoading();
        waitForTextToBeInElement(workflowName, name);
    }


    @Step("Check if edit WorkFlow Name Button display")
    public boolean checkIfEditWorkFlowButtonDisplay() {
        try {
            waitForPageFinishLoading();
            if (isDisplayed(editTheWorkFlowNameButton)) {
                scrollToElement(editTheWorkFlowNameButton);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("Check If edit Flow Parameters Button Display")
    public boolean checkIfEditFlowParametersButtonDisplay() {
        try {
            waitForPageFinishLoading();
            if (isDisplayed(editFlowParametersButton)) {
                scrollToElement(editFlowParametersButton);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("Check if Left side menu appear")
    public boolean checkIfLeftSideMenuAppear() {
        try {
            waitForPageFinishLoading();
            for (WebElement el : leftSideMenu) {
                if (isDisplayed(el)) {
                    scrollToElement(el);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("check if information button clickable")
    public boolean checkIfInformationButtonClickable() {
        try {
            waitForPageFinishLoading();
            for (WebElement el : informationButton) {
                scrollToElement(el);
                if ((isEnabled(el))) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("Choose task name form table")
    public void chooseTaskNameFromTable(String taskName) {
        waitForPageFinishLoading();
        //       scrollToElement(taskTypeNameForDragList.get(0));
        for (WebElement el : taskTypeNameForDragList) {
            scrollToElement(el);
            if (getText(el).equals(taskName)) {
                waitForElementToBeClickable(el);
                click(el);
            }
        }
    }

}
