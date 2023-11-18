package btrust.btrustOne.client.cases.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class RdcTaskPage extends BasePage {
    public RdcTaskPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".flexible-task-info .task-data-status")
    protected WebElement taskStatus;
    @FindBy(css = ".RDC-module__container__2JaiK .NoResults-module__description__2nMFl")
    protected WebElement NoResultFoundMessage;
    @FindBy(css = ".RDC-module__container__2JaiK .SearchingResults-module__description__13avz")
    protected WebElement hitsFoundMessage;
    @FindBy(css = ".BottomActions-module__right__35JME [type='button']")
    protected List<WebElement> buttonList;
    @FindBy(css = ".SearchingResults-module__modify__lbddO [type=button]")
    protected WebElement modifySearchLinkButton;
    @FindBy(css = ".SearchingResults-module__btnContainer__3wYgO button:nth-child(1)")
    protected List<WebElement> markPositive;
    @FindBy(css = ".SearchingResults-module__btnContainer__3wYgO :nth-child(2)")
    protected List<WebElement> markNegative;
    @FindBy(css = ".SearchingResults-module__table__7EOV9 > div > :nth-child(1)")
    protected List<WebElement> returnNamesList;
    @FindBy(css = ".SearchingResults-module__table__7EOV9 > div > :nth-child(2)")
    protected List<WebElement> countryCodeFieldList;
    @FindBy(css = ".MuiButtonBase-root.MuiIconButton-root")
    protected WebElement checkboxField;
    @FindBy(css = ".SearchingResults-module__table__7EOV9 > div:nth-of-type(1)")
    protected WebElement returnNamesRow;


    //Screening result popup
    @FindBy(css = ".Sidebar-module__btns__3F96w button:nth-child(1)")
    protected List<WebElement> popupMarkPositive;
    @FindBy(css = " .Sidebar-module__btns__3F96w button:nth-child(2)")
    protected List<WebElement> popupMarkNegative;
    @FindBy(css = ".Sidebar-module__hits__2U4nJ .Sidebar-module__name__2VbN8")
    protected List<WebElement> popupReturnNamesList;
    @FindBy(css = ".ScreeningResultsPopup-module__bottom__3DOlR [type='button']")
    protected WebElement popupConfirmButton;


    //Postponed popup
    @FindBy(css = ".PostponePopup-module__container__2wy6G .PopupTitle-module__text__3pjTU")
    protected WebElement postponedPopupTitle;
    @FindBy(css = ".paper.cancelTasksWidth.paddingNone  textarea")
    protected WebElement areaTextArea;
    @FindBy(css = ".PostponePopup-module__actions__Fz5an  [type='button']")
    protected List<WebElement> popUpButtons;
    @FindBy(css = ".MuiSvgIcon-root.MuiSvgIcon-fontSizeLarge")
    protected WebElement exitPopup;
    @FindBy(css = ".RDC-module__container__2JaiK .RDC-module__caseId__2ljHJ")
    protected WebElement linkToNewTask;


    //Edit fields screen
    @FindBy(css = ".RDC-module__container__2JaiK .PersonDateTable-module__addFields__wXeua")
    protected WebElement addFieldsLink;
    @FindBy(css = ".PersonDateTable-module__table__2oKZV .PersonDateTable-module__remove__3j2dp")
    protected List<WebElement> deleteButtonList;
    @FindBy(css = ".PersonDateTable-module__table__2oKZV > div>:nth-child(1)")
    protected List<WebElement> fieldNameList;
    @FindBy(css = ".RDC-module__container__2JaiK .PersonDateTable-module__row__eJ0q- >:nth-child(2) input")
    protected List<WebElement> fieldValueList;
    @FindBy(css = ".default__menu.css-26l3qy-menu .default__option")
    protected List<WebElement> selectFieldNameFromList;
    @FindBy(css = ".multiIsBlue-undefined.isCondition-undefined")
    protected List<WebElement> newFieldName;
    @FindBy(css = ".PersonDateTable-module__inputValue__ifv2S.undefined")
    protected List<WebElement> newValueName;


    @Step("Add new Search Fields for rdc")
    public void addNewSearchFieldsForRdc(String nameFromList, String valueName) {
        waitForPageFinishLoading();
        click(addFieldsLink);
        waitForElementToBeVisibility(newFieldName.get(0));
        for (int i = 0; i < newFieldName.size(); i++) {
            scrollToElement(newFieldName.get(i));
            click(newFieldName.get(i));
            for (WebElement el : selectFieldNameFromList) {
                if (getText(el).equalsIgnoreCase(nameFromList)) {
                    scrollToElement(el);
                    click(el);
                    scrollToElement(newValueName.get(i));
                    click(newValueName.get(i));
                    fillText(newValueName.get(i), valueName);
                    break;
                }
            }
        }
    }


    @Step("Edit Search Fields for rdc")
    public void editSearchFieldsForRdc(String fieldName, String newValue) {
        waitForPageFinishLoading();
        for (int i = 0; i < fieldNameList.size(); i++) {
            scrollToElement(fieldNameList.get(i));
            if (getText(fieldNameList.get(i)).equalsIgnoreCase(fieldName)) {
                scrollToElement(fieldValueList.get(i));
                click(fieldValueList.get(i));
                fillText(fieldValueList.get(i), newValue);
                break;
            }
        }
    }


    @Step("clickOnReturnRow")
    public void addNewFieldToSearch() {
        waitForElementToBeClickable(returnNamesRow);
        click(returnNamesRow);
    }


    @Step("clickOnReturnRow")
    public void clickOnReturnRow() {
        waitForElementToBeClickable(returnNamesRow);
        click(returnNamesRow);
    }


    @Step("popup Mark Button By Row")
    public void popupMarkButtonByRow(String returnName, String condition) {
        waitForPageFinishLoading();
        for (int i = 0; i < popupReturnNamesList.size(); i++) {
            scrollToElement(popupReturnNamesList.get(i));
            if (getText(popupReturnNamesList.get(i)).equalsIgnoreCase(returnName)) {
                switch (condition) {
                    case "true": {
                        waitForElementToBeClickable(popupMarkPositive.get(i));
                        click(popupMarkPositive.get(i));
                        break;
                    }
                    case "false": {
                        waitForElementToBeClickable(popupMarkNegative.get(i));
                        click(popupMarkNegative.get(i));
                        break;
                    }
                }
                if (isEnabled(popupConfirmButton)) {
                    click(popupConfirmButton);
                }
            }
        }
    }


    @Step("Click on checkbox")
    public void clickOnCheckbox() {
        waitForElementToBeClickable(checkboxField);
        click(checkboxField);
    }


    @Step("Click on modify search")
    public void clickOnModifySearch() {
        waitForElementToBeClickable(modifySearchLinkButton);
        click(modifySearchLinkButton);
    }


    @Step("Click on Link To New Task")
    public void clickOnLinkToNewTask() {
        waitForElementToBeClickable(linkToNewTask);
        click(linkToNewTask);
    }


    @Step("Fill the text in Postponed Pop Up and confirm")
    public void fillAreaTextInPopUpAndConfirm(String text, String buttonName) {
        waitForElementToBeVisibility(postponedPopupTitle);
        fillText(areaTextArea, text);
        for (WebElement el : popUpButtons) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(buttonName)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }


    @Step("Return the text of Postponed Pop Up Title")
    public String postponedPopupTitle() {
        waitForElementToBeVisibility(postponedPopupTitle);
        return getText(postponedPopupTitle);
    }


    @Step("Mark True Positive Button By column")
    public void markTruePositiveButtonByColumn(String status) {
        List<WebElement> list = new ArrayList();
        switch (status) {
            case "positive": {
                list = markPositive;
                System.out.println(list);
                break;
            }
            case "negative": {
                list = markNegative;
                break;
            }
        }
        waitForPageFinishLoading();
        for (WebElement el : list) {
            scrollToElement(el);
            click(el);
        }
    }


    @Step("Mark True Positive Button By Row")
    public void markTruePositiveButtonByRow(String nameField, String countryCodeField) {
        waitForPageFinishLoading();
        for (int i = 0; i < returnNamesList.size(); i++) {
            scrollToElement(returnNamesList.get(i));
            if (getText(returnNamesList.get(i)).equalsIgnoreCase(nameField) && getText(countryCodeFieldList.get(i)).equalsIgnoreCase(countryCodeField)) {
                System.out.println("match index: " + i);
                System.out.println("index is: " + returnNamesList.indexOf(getText(returnNamesList.get(i)).equalsIgnoreCase(nameField) && getText(countryCodeFieldList.get(i)).equalsIgnoreCase(countryCodeField)));
                scrollToElement(markPositive.get(i));
                if (!isSelected(markPositive.get(i))) {
                    click(markPositive.get(i));
                    break;
                }
            }
        }
    }


    @Step("Mark False Button By Row")
    public void markFalseButtonByRow(String nameField, String countryCodeField) {
        waitForPageFinishLoading();
        for (int i = 0; i < returnNamesList.size(); i++) {
            if (getText(returnNamesList.get(i)).equalsIgnoreCase(nameField) && getText(countryCodeFieldList.get(i)).equalsIgnoreCase(countryCodeField)) {
                scrollToElement(markNegative.get(i));
                if (!isSelected(markNegative.get(i))) {
                    click(markNegative.get(i));
                    break;
                }
            }
        }
    }

    @Step("Mark False Button By Row")
    public int markerHandler(String nameField, String countryCodeField, boolean flag) {
        waitForList(returnNamesList);
        int counter = 0;
        if (flag) {
            for (int i = 0; i < returnNamesList.size(); i++) {
                if (getText(returnNamesList.get(i)).equalsIgnoreCase(nameField)) {
                    if (getText(countryCodeFieldList.get(i)).equalsIgnoreCase(countryCodeField)) {
                        scrollToElement(markPositive.get(i));
                        click(markPositive.get(i));
                        counter++;
                    } else {
                        scrollToElement(markNegative.get(i));
                        click(markNegative.get(i));
                    }
                } else {
                    scrollToElement(markNegative.get(i));
                    click(markNegative.get(i));
                }
            }
        } else if (!(flag)) {
            for (int i = 0; i < returnNamesList.size(); i++) {
                if (getText(returnNamesList.get(i)).equalsIgnoreCase(nameField)) {
                    if (getText(countryCodeFieldList.get(i)).equalsIgnoreCase(countryCodeField)) {
                        click(markNegative.get(i));
                    } else click(markPositive.get(i));
                } else click(markPositive.get(i));
            }
        }
        return counter;
    }


    @Step("Check if modify Search Link display")
    public boolean CheckIfModifySearchLinkDisplay() {
        try {
            if (isDisplayed(modifySearchLinkButton)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return false;
    }


    @Step("Check task status")
    public String taskStatus() {
        waitForPageFinishLoading();
        scrollToElement(taskStatus);
        return getText(taskStatus);
    }

    @Step("Search result message - hits not found")
    public String hitsNotFoundMessage() {
        waitForPageFinishLoading();
        scrollToElement(NoResultFoundMessage);
        return getText(NoResultFoundMessage);
    }

    @Step("Search result message - hits found")
    public String hitsFoundMessage() {
        waitForPageFinishLoading();
        scrollToElement(hitsFoundMessage);
        return getText(hitsFoundMessage);
    }

    @Step("Click on button: {button}")
    public void chooseButton(String button) {
        waitForList(buttonList);
        for (WebElement el : buttonList) {
            if (getText(el).equalsIgnoreCase(button)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }


    @Step("Check if button is enable")
    public boolean buttonIsEnable(String button) {
        try {
            waitForList(buttonList);
            for (WebElement el : buttonList) {
                scrollToElement(el);
                if (getText(el).equalsIgnoreCase(button)) {
                    return isEnabled(el);
                }
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return false;
    }
}