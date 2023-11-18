package btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SegmentationPage extends BasePage {

    public SegmentationPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = ".NewTriggerSegmentation-module__wrapper__1wVdP .DocumentFormTitle-module__title__OrQX-")
    protected WebElement pageTitle;
    @FindBy(css = ".BottomStickyActions-module__wrapper__1b1UJ> button[type='button']")
    protected List<WebElement> buttonList;
    @FindBy(css = ".Segment-module__condition__1ugOu  .Segment-module__select__1IyMI")
    protected List<WebElement> fieldsList;
    @FindBy(css = ":nth-child(4) .Segment-module__condition__1ugOu .Segment-module__select__1IyMI")
    protected List<WebElement> addGroupFieldsList;
    @FindBy(css = ".NewTriggerSegmentation-module__wrapperContainer__1yvcU .Segment-module__subCondition__ncYX_")
    protected List<WebElement> subConditionRows;
    @FindBy(css = ".Segment-module__subCondition__ncYX_  .Segment-module__select__1IyMI")
    protected List<WebElement> subConditionFields;
    @FindBy(css = ".Segment-module__condition__1ugOu .Segment-module__input__1Hcvh")
    protected List<WebElement> inputField;
    @FindBy(css = ".Segment-module__subCondition__ncYX_ .Segment-module__input__1Hcvh")
    protected List<WebElement> subConditionInputField;
    @FindBy(css = ".default__menu.css-26l3qy-menu .default__option.default__option")
    protected List<WebElement> selectList;
    @FindBy(css = ".Segment-module__container__ihQmr  button.Segment-module__subCondition__ncYX_")
    protected WebElement addNewSubConditionButton;
    @FindBy(css = " .AddGroup-module__container__2vOSK.undefined [type=button]")
    protected WebElement addNewGroupButton;
    @FindBy(css = ".Segment-module__subCondition__ncYX_ .Segment-module__remove__FdO-D")
    protected List<WebElement> removeSubConditionButtonList;
    @FindBy(css = ".Segment-module__operator__n6WTh button")
    protected List<WebElement> subConditionOperatorButtonList;
    @FindBy(css = ".NewTriggerSegmentation-module__selector__3nWTN [type=button]")
    protected List<WebElement> addGroupOperatorButtonList;
    @FindBy(css = ".BlueSelector-module__menu__2XhIE>.BlueSelector-module__item__3RS7-")
    protected List<WebElement> chooseOperatorList;
    @FindBy(css = ".NewTriggerSegmentation-module__wrapperContainer__1yvcU  .Segment-module__remove__FdO-D")
    protected List<WebElement> deleteCondition;
    @FindBy(css = ".Segment-module__container__ihQmr .Segment-module__condition__1ugOu .Segment-module__remove__FdO-D")
    protected List<WebElement> deleteGroupButtonList;
    @FindBy(css = ".NewTriggerSegmentation-module__wrapperContainer__1yvcU .Segment-module__container__ihQmr")
    protected List<WebElement> groupRowsList;


    @Step("Add group and fill the fields")
    public void addGroupAndFillFieldsList(String fieldNumber, String firstFieldSelectName, String secondFieldSelectName, String thirdFieldSelectName, String fourthFieldSelectName, String fifthFieldInputName) {
        waitForPageFinishLoading();
        for (int i = 0; i < deleteGroupButtonList.size(); i++) {
            scrollToElement(deleteGroupButtonList.get(i));
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
                            click(inputField.get(1));
                            scrollToElement(inputField.get(1));
                            fillText(inputField.get(1), fifthFieldInputName);
                            return;
                        }
                    }

                }
            }
        }

    }


    @Step("Remove sub condition")
    public void removeSubCondition() {
        for (WebElement el : removeSubConditionButtonList) {
            waitForElementToBeClickable(el);
            click(el);
        }
    }


    @Step("Click add sub condition")
    public void addNewSubConditionButton() {
        waitForElementToBeClickable(addNewSubConditionButton);
        click(addNewSubConditionButton);
    }


    @Step("Click add group button")
    public void addGroupButton() {
        waitForElementToBeClickable(addNewGroupButton);
        click(addNewGroupButton);
    }


    @Step("Page Title")
    public String pageTitle() {
        waitForPageFinishLoading();
        return getText(pageTitle);
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
                        click(el);
                        scrollToElement(el);
                        fillText(el, fifthFieldInputName);
                        return;
                    }

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


    @Step("click on Button")
    public void clickOnButton(String buttonName) {
        waitForPageFinishLoading();
        waitForList(buttonList);
        for (WebElement el : buttonList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(buttonName)) {
                clickByJS(el);
                break;
            }
        }
    }


    @Step("Check Navigation Buttons")
    public boolean checkNavigationButtons(String buttonName) {
        boolean bol = false;
        waitForPageFinishLoading();
        waitForList(buttonList);
        for (int i = 0; i < buttonList.size(); i++) {
            scrollToElement(buttonList.get(i));
            if (getText(buttonList.get(i)).equalsIgnoreCase(buttonName)) {
                bol = isEnabled(buttonList.get(i));
                return bol;
            }
        }
        return bol;
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


    @Step("Delete condition")
    public void deleteConditionRow() {
        waitForPageFinishLoading();
        for (WebElement el : deleteCondition) {
            scrollToElement(el);
            if (isEnabled(el)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }


}



