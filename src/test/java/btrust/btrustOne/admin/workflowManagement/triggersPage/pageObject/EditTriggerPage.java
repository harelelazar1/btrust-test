package btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EditTriggerPage extends BasePage {

    public EditTriggerPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".MainInfo-module__content__3IEYa .MainInfo-module__label__3MMFW")
    protected List<WebElement> titleFields;
    @FindBy(css = ".MuiSwitch-root.trigger-switch")
    protected WebElement statusButton;
    @FindBy(css = ".MainInfo-module__header__3khh3 .MainInfo-module__text__nZjgv")
    protected WebElement triggerStatus;
    @FindBy(css = ".MuiListItem-gutters.MuiListItem-button")
    protected WebElement deleteTrigger;
    @FindBy(css = ".MuiButtonBase-root.MuiButton-root.MuiButton-text")
    protected WebElement hamburgerMenuButton;
    @FindBy(css = ".Reccurance-module__checkbox__3uf7I [type='checkbox']")
    protected WebElement checkboxRecurrence;
    @FindBy(css = ".Segment-module__container__ihQmr  button.Segment-module__subCondition__ncYX_")
    protected WebElement addNewSubConditionButton;
    @FindBy(css = ".AddGroup-module__container__2vOSK  [type=button]")
    protected WebElement addGroupButton;
    @FindBy(css = ".BottomStickyActions-module__wrapper__1b1UJ [type='button']")
    protected List<WebElement> buttonsList;
    @FindBy(css = ".Breadcrumbs-module__breadcrumbs__3ywP3 [role=tabpanel]")
    protected WebElement linkBack;
    @FindBy(css = ".DeleteTriggerPopup-module__actions__1-dG7>[type='button']")
    protected List<WebElement> deleteTriggerButtonsList;
    @FindBy(css = ".MainInfo-module__header__3khh3 .MainInfo-module__status__3t-NI span")
    protected WebElement triggerName;
    @FindBy(css = ".Reccurance-module__numberContainer__3dH9B input")
    protected WebElement numberFieldIsEnabled;
    @FindBy(css = ":nth-child(3) .default__input>:nth-child(1)")
    protected WebElement periodFieldIsEnabled;
    @FindBy(css = ".react-datepicker__input-container >input[type='text']")
    protected WebElement hourFieldIsEnabled;
    @FindBy(css = ".Segment-module__condition__1ugOu  .Segment-module__select__1IyMI")
    protected List<WebElement> fieldsList;
    @FindBy(css = ".default__menu.css-26l3qy-menu .default__option.default__option")
    protected List<WebElement> selectList;
    @FindBy(css = ".Segment-module__condition__1ugOu .Segment-module__input__1Hcvh,.Segment-module__subCondition__ncYX_ .Segment-module__input__1Hcvh")
    protected List<WebElement> inputField;
    @FindBy(css = ".Segment-module__operator__n6WTh button")
    protected List<WebElement> subConditionOperatorButtonList;
    @FindBy(css = ".BlueSelector-module__menu__2XhIE>.BlueSelector-module__item__3RS7-")
    protected List<WebElement> chooseOperatorList;
    @FindBy(css = ".Segment-module__container__ihQmr  .Segment-module__subCondition__ncYX_")
    protected List<WebElement> subConditionRows;
    @FindBy(css = ".Segment-module__subCondition__ncYX_  .Segment-module__select__1IyMI")
    protected List<WebElement> subConditionFields;
    @FindBy(css = ".Segment-module__subCondition__ncYX_ .Segment-module__input__1Hcvh")
    protected List<WebElement> subConditionInputField;
    @FindBy(css = ".NewTriggerSegmentation-module__selector__3nWTN [type=button]")
    protected List<WebElement> addGroupOperatorButtonList;
    @FindBy(css = ".TriggersItem-module__wrapper__3nkOt .Segment-module__container__ihQmr")
    protected List<WebElement> groupRowsList;
    @FindBy(css = ":nth-child(6)>.Segment-module__condition__1ugOu .Segment-module__select__1IyMI")
    protected List<WebElement> addGroupFieldsList;
    @FindBy(css = ".Reccurance-module__recuranceContainer__2MqMH .default__single-value.css-1uccc91-singleValue")
    protected WebElement periodNameField;


    @Step("return the period name that display")
    public String periodName() {
        waitForPageFinishLoading();
        scrollToElement(periodNameField);
        return getText(periodNameField);
    }


    @Step("Check add group Operator Name")
    public boolean addGroupCheckOperatorName(String operatorName) {
        try {
            waitForPageFinishLoading();
            for (int i = 0; i < addGroupOperatorButtonList.size(); i++) {
                scrollToElement(addGroupOperatorButtonList.get(i));
                if (!getText(addGroupOperatorButtonList.get(i)).equalsIgnoreCase(operatorName)) {
                    scrollToElement(addGroupOperatorButtonList.get(i));
                    waitForElementToBeVisibility(addGroupOperatorButtonList.get(i));
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }


    @Step("Add group and fill the fields")
    public void addGroupAndFillFieldsList(String fieldNumber, String firstFieldSelectName, String secondFieldSelectName, String thirdFieldSelectName, String fourthFieldSelectName, String fifthFieldInputName) {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(groupRowsList.get(0));
        for (int i = 0; i < groupRowsList.size(); i++) {
            scrollToElement(groupRowsList.get(i));
            if ((groupRowsList.size() > 1)) {
                i++;
                for (int g = i; g < groupRowsList.size(); g++) {
                    scrollToElement(groupRowsList.get(g));
                    switch (fieldNumber) {
                        case "firstField": {
                            click(addGroupFieldsList.get(0));
                            for (WebElement el : selectList) {
                                scrollToElement(el);
                                if (getText(el).equalsIgnoreCase(firstFieldSelectName)) {
                                    waitForElementToBeVisibility(el);
                                    click(el);
                                    return;
                                }
                            }
                            break;
                        }
                        case "secondField": {
                            click(addGroupFieldsList.get(1));
                            for (WebElement el : selectList) {
                                scrollToElement(el);
                                if (getText(el).equalsIgnoreCase(secondFieldSelectName)) {
                                    waitForElementToBeVisibility(el);
                                    click(el);
                                    return;
                                }
                            }
                            break;
                        }
                        case "thirdField": {
                            click(addGroupFieldsList.get(2));
                            for (WebElement el : selectList) {
                                scrollToElement(el);
                                if (getText(el).equalsIgnoreCase(thirdFieldSelectName)) {
                                    waitForElementToBeVisibility(el);
                                    click(el);
                                    return;
                                }
                            }
                            break;
                        }
                        case "fourthField": {
                            click(addGroupFieldsList.get(3));
                            for (WebElement el : selectList) {
                                scrollToElement(el);
                                if (getText(el).equalsIgnoreCase(fourthFieldSelectName)) {
                                    waitForElementToBeVisibility(el);
                                    click(el);
                                    return;
                                }
                            }
                            break;
                        }
                        case "fifthField": {
                            scrollToElement(inputField.get(inputField.size() - 1));
                            if (inputField.size() == 1) {
                                sleep(5000);
                                scrollToElement(inputField.get(inputField.size() - 1));
                            }
                            click(inputField.get(inputField.size() - 1));
                            fillText(inputField.get(inputField.size() - 1), fifthFieldInputName);
                            return;
                        }
                    }
                }
            }
        }
    }


    @Step("Choose add group operator from list")
    public void chooseAddGroupOperatorFromList(String operatorName) {
        waitForPageFinishLoading();
        for (int i = 0; i < addGroupOperatorButtonList.size(); i++) {
            scrollToElement(addGroupOperatorButtonList.get(i));
            click(addGroupOperatorButtonList.get(i));
            waitForList(chooseOperatorList);
            for (WebElement el : chooseOperatorList) {
                if (getText(el).equalsIgnoreCase(operatorName)) {
                    waitForElementToBeClickable(el);
                    click(el);
                    return;
                }
            }
        }
    }


    @Step("Choose sub condition operator from list")
    public void chooseSubConditionOperatorFromList(String operatorName) {
        waitForPageFinishLoading();
        for (int i = 0; i < subConditionOperatorButtonList.size(); i++) {
            scrollToElement(subConditionOperatorButtonList.get(i));
            click(subConditionOperatorButtonList.get(i));
            waitForList(chooseOperatorList);
            for (WebElement el : chooseOperatorList) {
                if (getText(el).equalsIgnoreCase(operatorName)) {
                    waitForElementToBeClickable(el);
                    click(el);
                    return;
                }
            }
        }
    }


    @Step("Create Sub condition")
    public void createSubCondition(String fieldNumber, String firstFieldSelectName, String secondFieldSelectName, String thirdFieldSelectName, String fourthFieldSelectName) {
        waitForPageFinishLoading();
        for (int i = 0; i < subConditionRows.size(); i++) {
            scrollToElement(subConditionRows.get(i));
            switch (fieldNumber) {
                case "firstField": {
                    click(subConditionFields.get(0));
                    for (WebElement el : selectList) {
                        scrollToElement(el);
                        if (getText(el).equalsIgnoreCase(firstFieldSelectName)) {
                            waitForElementToBeVisibility(el);
                            click(el);
                            return;
                        }
                    }
                    break;
                }
                case "secondField": {
                    click(subConditionFields.get(1));
                    for (WebElement el : selectList) {
                        scrollToElement(el);
                        if (getText(el).equalsIgnoreCase(secondFieldSelectName)) {
                            waitForElementToBeVisibility(el);
                            click(el);
                            return;
                        }
                    }
                    break;
                }
                case "thirdField": {
                    click(subConditionFields.get(2));
                    for (WebElement el : selectList) {
                        scrollToElement(el);
                        if (getText(el).equalsIgnoreCase(thirdFieldSelectName)) {
                            waitForElementToBeVisibility(el);
                            click(el);
                            return;
                        }
                    }
                    break;
                }
                case "fourthField": {
                    for (WebElement el : subConditionInputField) {
                        scrollToElement(el);
                        waitForElementToBeClickable(el);
                        fillText(el, fourthFieldSelectName);
                        return;
                    }
                }
            }
        }
    }


    @Step("Check sub condition Operator Name")
    public boolean subConditionCheckOperatorName(String operatorName) {
        try {
            waitForPageFinishLoading();
            for (int i = 0; i < subConditionOperatorButtonList.size(); i++) {
                scrollToElement(subConditionOperatorButtonList.get(i));
                if (!getText(subConditionOperatorButtonList.get(i)).equalsIgnoreCase(operatorName)) {
                    scrollToElement(subConditionOperatorButtonList.get(i));
                    waitForElementToBeVisibility(subConditionOperatorButtonList.get(i));
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }


    @Step("Click add Group button")
    public void addNewGroupButton() {
        waitForPageFinishLoading();
        scrollToElement(addGroupButton);
        waitForElementToBeClickable(addGroupButton);
        click(addGroupButton);
    }


    @Step("Click add sub condition button")
    public void addNewSubConditionButton() {
        waitForElementToBeClickable(addNewSubConditionButton);
        click(addNewSubConditionButton);
    }


    @Step("Click checkbox for Recurrence")
    public void clickCheckboxForRecurrence() {
        waitForPageFinishLoading();
        scrollToElement(checkboxRecurrence);
        click(checkboxRecurrence);
    }


    @Step("Return trigger status")
    public String returnTriggerStatus() {
        scrollToElement(triggerName);
        waitForTextToBeInElement(triggerName, "Delete");
        return getText(triggerName);
    }


    @Step("click on link back to roles and Permissions screen")
    public void linkBack() {
        scrollToElement(linkBack);
        waitForElementToBeClickable(linkBack);
        click(linkBack);
    }


    @Step("Change trigger status")
    public String changeTriggerStatus(String statusCondition) {
        waitForPageFinishLoading();
        waitForTextToBeInElement(triggerStatus, statusCondition);
        scrollToElement(statusButton);
        click(statusButton);
        return getText(triggerStatus);
    }


    @Step("Check if hamburger menu button display")
    public boolean checkIfHamburgerMenuButtonDisplay() {
        try {
            waitForPageFinishLoading();
            scrollToElement(hamburgerMenuButton);
            if (isDisplayed(hamburgerMenuButton)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("delete trigger")
    public void deleteTrigger(String buttonOption) {
        waitForPageFinishLoading();
        if (isDisplayed(hamburgerMenuButton)) {
            scrollToElement(hamburgerMenuButton);
            click(hamburgerMenuButton);
            click(deleteTrigger);
            for (WebElement el : deleteTriggerButtonsList) {
                scrollToElement(el);
                if (getText(el).equalsIgnoreCase(buttonOption)) {
                    waitForElementToBeClickable(el);
                    click(el);
                    break;
                }
            }
        }
    }


    @Step("Check if checkbox is enable")
    public boolean checkIfCheckboxIsEnable() {
        try {
            waitForPageFinishLoading();
            if (isEnabled(checkboxRecurrence)) {
                scrollToElement(checkboxRecurrence);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("Check if new sub condition is enable")
    public boolean checkIfNewSubConditionEnable() {
        try {
            waitForPageFinishLoading();
            if (isEnabled(addNewSubConditionButton)) {
                scrollToElement(addNewSubConditionButton);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("Check if Cancel/Apply buttons display")
    public boolean checkIfButtonsDisplay() {
        try {
            for (WebElement el : buttonsList) {
                waitForPageFinishLoading();
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


    @Step("Click cancel or apply button")
    public void clickCancelOrApplyButton(String buttonName) {
        for (WebElement el : buttonsList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(buttonName)) {
                scrollToElement(el);
                click(el);
                break;
            }
        }
    }


    @Step("Check if add group is enable")
    public boolean checkIfAddGroupEnable() {
        try {
            waitForPageFinishLoading();
            if (isEnabled(addGroupButton)) {
                scrollToElement(addGroupButton);
                return true;

            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("Check Trigger Reccurance Fields")
    public boolean checkTriggerReccuranceFields(String fieldType) {
        boolean bol = false;
        switch (fieldType) {
            case "Number": {
                scrollToElement(numberFieldIsEnabled);
                bol = isEnabled(numberFieldIsEnabled);
                break;
            }
            case "Period": {
                scrollToElement(periodFieldIsEnabled);
                bol = isEnabled(periodFieldIsEnabled);
                break;
            }
            case "Time": {
                scrollToElement(hourFieldIsEnabled);
                bol = isEnabled(hourFieldIsEnabled);
                break;
            }
        }
        return bol;
    }


    @Step("Create First Condition")
    public void createFirstCondition(String fieldNumber, String firstFieldSelectName, String secondFieldSelectName, String thirdFieldSelectName, String fourthFieldSelectName, String fifthFieldInputName) {
        waitForPageFinishLoading();
        for (int i = 0; i < fieldsList.size(); i++) {
            scrollToElement(fieldsList.get(i));
            switch (fieldNumber) {
                case "firstField": {
                    click(fieldsList.get(0));
                    for (WebElement el : selectList) {
                        scrollToElement(el);
                        if (getText(el).equalsIgnoreCase(firstFieldSelectName)) {
                            waitForElementToBeVisibility(el);
                            click(el);
                            return;
                        }
                    }
                    break;
                }
                case "secondField": {
                    click(fieldsList.get(1));
                    for (WebElement el : selectList) {
                        scrollToElement(el);
                        if (getText(el).equalsIgnoreCase(secondFieldSelectName)) {
                            waitForElementToBeVisibility(el);
                            click(el);
                            return;
                        }
                    }
                    break;
                }
                case "thirdField": {
                    click(fieldsList.get(2));
                    for (WebElement el : selectList) {
                        scrollToElement(el);
                        if (getText(el).equalsIgnoreCase(thirdFieldSelectName)) {
                            waitForElementToBeVisibility(el);
                            click(el);
                            return;
                        }
                    }
                    break;
                }
                case "fourthField": {
                    scrollToElement(fieldsList.get(3));
                    click(fieldsList.get(3));
                    for (WebElement el : selectList) {
                        scrollToElement(el);
                        if (getText(el).equalsIgnoreCase(fourthFieldSelectName)) {
                            waitForElementToBeVisibility(el);
                            click(el);
                            return;
                        }
                    }
                    break;
                }
                case "fifthField": {
                    for (WebElement el : inputField) {
                        scrollToElement(el);
                        click(el);
                        fillText(el, fifthFieldInputName);
                        return;
                    }

                }
            }
        }
    }


}



