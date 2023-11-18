package btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class DataProfilePage extends BasePage {
    public DataProfilePage(WebDriver driver) {
        super(driver);
    }

    protected ArrayList<Integer> parentList = new ArrayList<>();
    protected ArrayList<Integer> childList = new ArrayList<>();
    protected ArrayList<Integer> summaryList = new ArrayList<>();
    protected int listSize;
    protected String value;
    protected String dataField;
    protected String dataType;
    protected String fieldLabel;

    @FindBy(css = ".FieldsTableHeader-module__wrapper__3xVZa > .false")
    protected List<WebElement> dataProfileTitle;
    @FindBy(css = ".ClientFields-module__tableHeader__2R-LP > .ClientFields-module__headItem__2Dnw4")
    protected List<WebElement> childDataProfileTitle;
    @FindBy(css = ".FieldsTable-module__row__3ZjpI > :nth-child(1)")
    protected List<WebElement> dataFieldList;
    @FindBy(css = ".FieldsTable-module__row__3ZjpI > :nth-child(2)")
    protected List<WebElement> dataTypeList;
    @FindBy(css = ".default__menu-list >.default__option")
    protected List<WebElement> dataTypeOptions;
    @FindBy(css = ".FieldsTable-module__row__3ZjpI > :nth-child(3)")
    protected List<WebElement> fieldLabelList;
    @FindBy(css = ".FieldsTable-module__row__3ZjpI > :nth-child(4)")
    protected List<WebElement> sourceList;
    @FindBy(css = "input[name='name']")
    protected List<WebElement> childDataFieldList;
    @FindBy(css = ".default-custom-selector > .default-select")
    protected List<WebElement> childDataTypeSelectsList;
    @FindBy(css = ".default__value-container.default__value-container--has-value.css-1hwfws3")
    protected List<WebElement> childDataTypeValuesList;
    @FindBy(css = "input[name='fieldLabel']")
    protected List<WebElement> childFieldLabelList;
    @FindBy(css = ".ClientFields-module__item__3hlxH .MuiButtonBase-root input")
    protected List<WebElement> mandatoryCheckboxList;
    @FindBy(css = "input[name='errorMessage']")
    protected List<WebElement> errorMessageInputList;
    @FindBy(css = ".ClientFields-module__row__3yB8f button.ClientFields-module__settings__P-cAA")
    protected List<WebElement> settingsButtonList;
    @FindBy(css = ".ClientFields-module__item__3hlxH > button.RemoveButton-module__remove__2fr92")
    protected List<WebElement> removeButtonList;



    @Step("Check if field display in the DATA FIELD column")
    public Boolean fieldDisplayInDataFieldColumn(String columnName, String filedName, String dateType, String fieldLabel) {
        try {
            waitForPageFinishLoading();
            switch (columnName) {
                case "DATA FIELD":
                    for (WebElement el : childDataFieldList) {
                        waitForElementToBeVisibility(el);
                        scrollToElement(el);
                        if (el.getAttribute("value").equalsIgnoreCase(filedName)) {
                            return true;
                        }
                    }
                    break;
                case "DATA TYPE":
                    for (WebElement el : childDataTypeValuesList) {
                        waitForElementToBeVisibility(el);
                        scrollToElement(el);
                        if (getText(el).equalsIgnoreCase(dateType)) {
                            return true;
                        }
                    }
                    break;
                case "FIELD LABEL":
                    for (WebElement el : childFieldLabelList) {
                        waitForElementToBeVisibility(el);
                        scrollToElement(el);
                        if (el.getAttribute("value").equalsIgnoreCase(fieldLabel)) {
                            return true;
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println("Something Went Wrong");
        }
        return false;
    }


    @Step("Count all the fields that appear in the table")
    public int dataFieldList(String content) {
        waitForPageFinishLoading();
        switch (content) {
            case "parent": {
                for (int i = 0; i < dataFieldList.size(); i++) {
                    scrollToElement(dataFieldList.get(i));
                    parentList.add(i);
                }
                listSize = parentList.size();
                break;
            }
            case "summary": {
                for (int i = 0; i < dataFieldList.size(); i++) {
                    scrollToElement(dataFieldList.get(i));
                    summaryList.add(i);
                }
                listSize = summaryList.size();
                break;
            }
            case "child": {
                for (int i = 0; i < childDataFieldList.size(); i++) {
                    scrollToElement(childDataFieldList.get(i));
                    childList.add(i);
                }
                listSize = childList.size();
                break;
            }
        }
        return listSize;
    }

    @Step("Fill the new filed details")
    public void fillNewFieldDetails(String dataField, String dataType, String fieldLabel, String isMandatory, String errorMessage) {
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        Assert.assertFalse(businessCategoryPage.clickOnButton("Save"));
        scrollToElement(childDataFieldList.get(childDataFieldList.size() - 1));
        waitForElementToBeClickable(childDataFieldList.get(childDataFieldList.size() - 1));
        fillText(childDataFieldList.get(childDataFieldList.size() - 1), dataField);
        Assert.assertFalse(businessCategoryPage.clickOnButton("Save"));
        scrollToElement(childDataTypeSelectsList.get(childDataTypeSelectsList.size() - 1));
        click(childDataTypeSelectsList.get(childDataTypeSelectsList.size() - 1));
        for (WebElement el : dataTypeOptions) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(dataType)) {
                click(el);
                break;
            }
        }
        Assert.assertFalse(businessCategoryPage.clickOnButton("Save"));
        fillText(childFieldLabelList.get(childFieldLabelList.size() - 1), fieldLabel);
        switch (isMandatory) {
            case "yes":
                click(mandatoryCheckboxList.get(mandatoryCheckboxList.size() - 1));
                fillText(errorMessageInputList.get(errorMessageInputList.size() - 1), errorMessage);
                break;
            case "no":
                break;
        }
    }

    @Step("Click on setting button")
    public void clickOnSettingButton(String dataField) {
        waitForPageFinishLoading();
        if (isDisplayed(dataFieldList.get(0))) {
            waitForList(dataFieldList);
            for (int i = 0; i < dataFieldList.size(); i++) {
                if (getText(dataFieldList.get(i)).equalsIgnoreCase(dataField)) {
                    waitForElementToBeClickable(settingsButtonList.get(i));
                    click(settingsButtonList.get(i));
                    break;
                }
            }
        } else if (isDisplayed(childDataFieldList.get(0))) {
            waitForList(childDataFieldList);
            for (int i = 0; i < childDataFieldList.size(); i++) {
                if (getText(childDataFieldList.get(i)).equalsIgnoreCase(dataField)) {
                    waitForElementToBeClickable(settingsButtonList.get(i));
                    click(settingsButtonList.get(i));
                    break;
                }
            }
        }
    }

    @Step("Click on remove button")
    public void clickOnRemoveButton(String dataField) {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(childDataFieldList.get(0));
        for (int i = 0; i < childDataFieldList.size(); i++) {
            scrollToElement(childDataFieldList.get(i));
            if (getAttribute(childDataFieldList.get(i), "value").equalsIgnoreCase(dataField)) {
                scrollToElement(removeButtonList.get(i));
                click(removeButtonList.get(i));
                break;
            }
        }
    }

    @Step("Check the value that appear in the data profile table")
    public String getValueFromDataProfileTable(String contentType, String title) {
        waitForPageFinishLoading();
        switch (contentType) {
            case "parent":
            case "summary":
                for (WebElement element : dataProfileTitle) {
                    scrollToElement(element);
                    if (getText(element).equalsIgnoreCase(title)) {
                        switch (title) {
                            case "DATA FIELD": {
                                scrollToElement(dataFieldList.get(dataFieldList.size() - 1));
                                value = getText(dataFieldList.get(dataFieldList.size() - 1));
                                break;
                            }
                            case "DATA TYPE": {
                                scrollToElement(dataTypeList.get(dataTypeList.size() - 1));
                                value = getText(dataTypeList.get(dataTypeList.size() - 1));
                                break;
                            }
                            case "FIELD LABEL": {
                                scrollToElement(fieldLabelList.get(fieldLabelList.size() - 1));
                                value = getText(fieldLabelList.get(fieldLabelList.size() - 1));
                                break;
                            }
                            case "SOURCE": {
                                scrollToElement(sourceList.get(sourceList.size() - 1));
                                value = getText(sourceList.get(sourceList.size() - 1));
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            case "child": {
                for (WebElement webElement : childDataProfileTitle) {
                    scrollToElement(webElement);
                    if (getText(webElement).equals(title)) {
                        switch (title) {
                            case "DATA FIELD": {
                                scrollToElement(childDataFieldList.get(childDataFieldList.size() - 1));
                                dataField = getAttribute(childDataFieldList.get(childDataFieldList.size() - 1), "value");
                                value = dataField;
                                break;
                            }
                            case "DATA TYPE": {
                                scrollToElement(childDataTypeValuesList.get(childDataTypeValuesList.size() - 1));
                                dataType = getText(childDataTypeValuesList.get(childDataTypeValuesList.size() - 1));
                                value = dataType;
                                break;
                            }
                            case "FIELD LABEL": {
                                scrollToElement(childFieldLabelList.get(childFieldLabelList.size() - 1));
                                fieldLabel = getAttribute(childFieldLabelList.get(childFieldLabelList.size() - 1), "value");
                                value = fieldLabel;
                                break;
                            }
                        }
                        break;
                    }
                    break;
                }
            }
        }
        return value;
    }

    @Step("Check if data field list is displayed")
    public boolean dataFiledListIsDisplayed() {
        try {
            scrollToElement(dataFieldList.get(0));
            return isDisplayed(dataFieldList.get(0));
        } catch (Exception e) {
            return false;
        }
    }
}