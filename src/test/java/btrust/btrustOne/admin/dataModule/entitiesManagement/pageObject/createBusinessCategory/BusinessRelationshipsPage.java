package btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class BusinessRelationshipsPage extends BasePage {
    public BusinessRelationshipsPage(WebDriver driver) {
        super(driver);
    }

    protected String value;
    protected ArrayList<Integer> parentList = new ArrayList<>();
    protected ArrayList<Integer> childList = new ArrayList<>();
    protected ArrayList<Integer> summaryList = new ArrayList<>();
    protected int listSize;

    /*
    parent
     */
    @FindBy(css = ".OrganizationBusRel-module__tableHeader__2drvh > .OrganizationBusRel-module__headItem__3hCAI")
    protected List<WebElement> businessRelationshipsTitles;
    @FindBy(css = ".default__value-container > .default__single-value")
    protected List<WebElement> businessRelationshipsValuesList;
    @FindBy(css = ".BusinessRelationships-module__wrapper__3djOE > .ClientBusRel-module__table__1eTXE > .ClientBusRel-module__row__1VWze")
    protected List<WebElement> businessRelationshipsList;
    @FindBy(css = ".ClientBusRel-module__row__1VWze > :nth-child(1) .default__single-value")
    protected List<WebElement> entityTypeList;

    /*
    child
     */
    @FindBy(css = ".ClientBusRel-module__tableHeader__22r2w > .ClientBusRel-module__headItem__hKEZM")
    protected List<WebElement> editBusinessRelationshipsTitles;
    @FindBy(css = ".FieldsTable-module__row__jY5Qg > .FieldsTable-module__item__Ixn-f")
    protected List<WebElement> editBusinessRelationshipsValuesList;
    @FindBy(css = ".BusinessRelationships-module__wrapper__3djOE > .FieldsTable-module__container__2V42U > .FieldsTable-module__row__jY5Qg")
    protected List<WebElement> editBusinessRelationshipsList;

    @FindBy(css = ":nth-child(1) > .default-custom-selector")
    protected List<WebElement> entityTypeSelects;
    @FindBy(css = ":nth-child(2) > .default-custom-selector > .default-select")
    protected List<WebElement> businessCategorySelects;
    @FindBy(css = ":nth-child(3) > .default-custom-selector > .default-select")
    protected List<WebElement> relationshipTypeSelects;
    @FindBy(css = ".default__menu-list > .default__option")
    protected List<WebElement> optionsList;
    @FindBy(css = ".ClientBusRel-module__item__3wT7u > button.ClientBusRel-module__remove__2F5pL")
    protected List<WebElement> removeButtonList;

    @Step("Count all the fields that appear in the table")
    public int businessRelationshipsList(String content) {
        sleep(5000);
        switch (content) {
            case "parent": {
                if (editBusinessRelationshipsList.size() > 0) {
                    scrollToElement(editBusinessRelationshipsList.get(0));
                    for (int i = 0; i < editBusinessRelationshipsList.size(); i++) {
                        scrollToElement(editBusinessRelationshipsList.get(i));
                        parentList.add(i);
                    }
                    listSize = parentList.size();
                }
                break;
            }
            case "summary": {
                if (editBusinessRelationshipsList.size() > 0) {
                    scrollToElement(editBusinessRelationshipsList.get(0));
                    for (int i = 0; i < editBusinessRelationshipsList.size(); i++) {
                        scrollToElement(editBusinessRelationshipsList.get(i));
                        summaryList.add(i);
                    }
                    listSize = summaryList.size();
                }
                break;
            }
            case "child": {
                if (businessRelationshipsList.size() > 0) {
                    scrollToElement(businessRelationshipsList.get(0));
                    for (int i = 0; i < businessRelationshipsList.size(); i++) {
                        scrollToElement(businessRelationshipsList.get(i));
                        childList.add(i);
                    }
                    listSize = childList.size();
                }
                break;
            }
            default: {
                listSize = 0;
            }
        }
        return listSize;
    }

    @Step("Return the text that appear in the business relationships list")
    public String getValueFromBusinessRelationshipsTable(String contentType, String title) {
        waitForPageFinishLoading();
        switch (contentType) {
            case "parent":
            case "summary": {
                for (int i = 0; i < businessRelationshipsTitles.size(); i++) {
                    scrollToElement(businessRelationshipsTitles.get(i));
                    if (getText(businessRelationshipsTitles.get(i)).equalsIgnoreCase(title)) {
                        value = getText(editBusinessRelationshipsValuesList.get(i));
                        break;
                    }
                }
                break;
            }
            case "child": {
                for (int i = 0; i < editBusinessRelationshipsTitles.size(); i++) {
                    scrollToElement(editBusinessRelationshipsTitles.get(i));
                    if (getText(editBusinessRelationshipsTitles.get(i)).equalsIgnoreCase(title)) {
                        value = getText(businessRelationshipsValuesList.get(i));
                        break;
                    }
                }
                break;
            }
        }
        return value;
    }

    @Step("Click on remove business relationship button")
    public void clickOnRemoveButton(String entityType) {
        waitForList(entityTypeList);
        for (int i = 0; i < entityTypeList.size(); i++) {
            scrollToElement(entityTypeList.get(i));
            if (getText(entityTypeList.get(i)).equalsIgnoreCase(entityType)) {
                waitForElementToBeClickable(removeButtonList.get(i));
                click(removeButtonList.get(i));
                break;
            }
        }
    }

    @Step("Fill business relationship data")
    public void fillBusinessRelationshipData(String title, String option) {
        for (WebElement el : editBusinessRelationshipsTitles) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(title)) {
                switch (title) {
                    case "ENTITY TYPE": {
                        scrollToElement(entityTypeSelects.get(entityTypeSelects.size() - 1));
                        click(entityTypeSelects.get(entityTypeSelects.size() - 1));
                        break;
                    }
                    case "BUSINESS CATEGORY": {
                        scrollToElement(businessCategorySelects.get(businessCategorySelects.size() - 1));
                        click(businessCategorySelects.get(businessCategorySelects.size() - 1));
                        break;
                    }
                    case "RELATIONSHIP TYPE": {
                        scrollToElement(relationshipTypeSelects.get(relationshipTypeSelects.size() - 1));
                        click(relationshipTypeSelects.get(relationshipTypeSelects.size() - 1));
                        break;
                    }
                }
                break;
            }
        }
        waitForList(optionsList);
        for (WebElement el : optionsList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(option)) {
                scrollToElement(el);
                click(el);
                break;
            }
        }
    }
}