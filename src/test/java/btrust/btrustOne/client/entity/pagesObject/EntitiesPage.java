package btrust.btrustOne.client.entity.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EntitiesPage extends BasePage {
    public EntitiesPage(WebDriver driver) {
        super(driver);
    }

    protected int getEntityNumber = 0;
    protected int totalEntities;
    protected int sumEntitiesBox;
    boolean bol;

    @FindBy(css = "div .AddButton-module__btn__2_wO7")
    protected WebElement addEntityButton;
    @FindBy(css = "div> li")
    protected List<WebElement> addEntityMenu;
    @FindBy(css = ".entities-list-top-bar > .Header-module__title__6xYmj")
    protected WebElement entitiesTitle;
    @FindBy(css = ".Table-module__container__z_waN > .Table-module__row__3YajP")
    protected List<WebElement> entitiesList;
    @FindBy(css = ".Table-module__mainData__lp93y :nth-child(8) [type='button']")
    protected List<WebElement> newCaseButton;
    @FindBy(css = "div .ExportButton-module__export____qPg")
    protected WebElement exportButton;

    /*
    entity info
   */
    @FindBy(css = ".Entities-module__main__1TpJk > .Table-module__container__z_waN")
    protected WebElement entitiesTable;
    @FindBy(css = ".Table-module__mainData__lp93y > :nth-child(3) > :nth-child(1)")
    protected List<WebElement> entityNameList;
    @FindBy(css = ".Table-module__container__z_waN .Table-module__mainData__lp93y > :nth-child(5)")
    protected List<WebElement> entityStatusList;
    @FindBy(css = ".Table-module__mainIcon__2iAfD > svg > path")
    protected List<WebElement> entityType;
    @FindBy(css = "tr > td:nth-child(2) > .entity-name")
    protected WebElement entityName;
    @FindBy(css = ".Table-module__container__z_waN .Table-module__businessCategory__3Ls1b")
    protected List<WebElement> entityBusinessCategory;
    /*
    filters Elements
     */
    @FindBy(css = ".filter-item > .select-button")
    protected List<WebElement> filtersList;
    @FindBy(css = ".items-container > .item")
    protected List<WebElement> optionsFromFilters;
    @FindBy(css = ".select-button > .clear.change-position")
    protected List<WebElement> clearButtonsList;
    @FindBy(css = ".InputSearch-module__inputItem__2Oj7R > input")
    protected WebElement searchField;
    @FindBy(css = "button.Sidebar-module__menuItem__2TwQN.false")
    protected List<WebElement> entityStatusFilterBoxes;
    /*
    cases box elements
     */
    @FindBy(css = ".Sidebar-module__menuContainer__36GTI > button:nth-child(1) > .Sidebar-module__count__2WQSN")
    protected WebElement totalEntitiesValueBox;
    @FindBy(css = ".Sidebar-module__menuContainer__36GTI > button:nth-child(2) > .Sidebar-module__count__2WQSN")
    protected WebElement underEvaluationValueBox;
    @FindBy(css = ".Sidebar-module__menuContainer__36GTI > button:nth-child(3) > .Sidebar-module__count__2WQSN")
    protected WebElement approvedValueBox;
    @FindBy(css = ".Sidebar-module__menuContainer__36GTI > button:nth-child(4) > .Sidebar-module__count__2WQSN")
    protected WebElement suspendedValueBox;
    @FindBy(css = ".Sidebar-module__menuContainer__36GTI > button:nth-child(5) > .Sidebar-module__count__2WQSN")
    protected WebElement rejectedValueBox;
    /*
    no result elements
    */
    @FindBy(css = ".Entities-module__main__1TpJk > .NoFound-module__container__2CMk6 > img")
    protected WebElement noResultIcon;
    @FindBy(css = ".Entities-module__main__1TpJk > .NoFound-module__container__2CMk6 > .NoFound-module__title__215Y5")
    protected WebElement noResultMessage;


    @Step("Choose from add new entity menu")
    public void chooseFromAddEntityMenu(String option) {
        waitForElementToBeVisibility(entitiesTable);
        scrollToElement(addEntityButton);
        waitForElementToBeClickable(addEntityButton);
        click(addEntityButton);
        waitForList(addEntityMenu);
        for (WebElement el : addEntityMenu) {
            if (getText(el).equalsIgnoreCase(option)) {
                scrollToElement(el);
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("check Export button display")
    public boolean exportButtonDisplay(String buttonName) {
        try {
            waitForPageFinishLoading();
            scrollToElement(exportButton);
            if (exportButton.isDisplayed()) {
                sleep(2000);
                getText(exportButton).equalsIgnoreCase(buttonName);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("check name appear in add new entity menu")
    public boolean checkNameInAddEntityMenu(String option) {
        try {
            waitForPageFinishLoading();
            scrollToElement(addEntityButton);
            if (isDisplayed(addEntityButton)) {
                sleep(2000);
                click(addEntityButton);
                waitForList(addEntityMenu);
                for (WebElement el : addEntityMenu) {
                    scrollToElement(el);
                    if (getText(el).equals(option)) {
                        waitForTextToBeInElement(el, option);
                        System.out.println(getText(el));
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }

    @Step("Check that EntitiesTitle appear")
    public boolean entitiesTitle() {
        waitForTextToBeInElement(entitiesTitle, "Entities");
        return getText(entitiesTitle).equals("Entities");
    }

    @Step("Open filter: {filter} and choose option: {option}")
    public void filterEntitiesTable(String type, String filter, String option) {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(entitiesTable);
        switch (type) {
            case "filter":
                for (WebElement el : filtersList) {
                    scrollToElement(el);
                    if (getText(el).contains(filter)) {
                        clickByJS(el);
                        break;
                    }
                }
                waitForList(optionsFromFilters);
                for (int i = 0; i < optionsFromFilters.size(); i++) {
                    scrollToElement(optionsFromFilters.get(i));
                    if (getText(optionsFromFilters.get(i)).equalsIgnoreCase(option)) {
                        click(optionsFromFilters.get(i));
                        click(searchField);
                        break;
                    }
                }
                break;
            case "box":
                waitForList(entityStatusFilterBoxes);
                for (WebElement element : entityStatusFilterBoxes) {
                    scrollToElement(element);
                    if (!getText(element).contains(option)) {
                        click(element);
                    }
                }
                break;
        }
        sleep(10000);
        waitForPageFinishLoading();
    }

    @Step("Return the count of the boxes that are turn on")
    public int countTurnOnBoxes() {
        int num = 0;
        for (WebElement el : entityStatusFilterBoxes) {
            scrollToElement(el);
            num = entityStatusFilterBoxes.size();
        }
        return num;
    }

    @Step("Click on all the clear buttons that appear")
    public void clearButton() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(entitiesTable);
        sleep(10000);
        System.out.println(clearButtonsList.size());
        if (clearButtonsList.size() > 0) {
            for (WebElement el : clearButtonsList) {
                scrollToElement(el);
                clickByJS(el);
            }
        }
        waitForElementToBeVisibility(entitiesTable);
    }

    @Step("Check if clear button is displayed")
    public boolean clearButtonIsDisplayed() {
        try {
            WebElement el = null;
            for (int i = 0; i <= clearButtonsList.size(); i++) {
                el = clearButtonsList.get(i);
            }
            return isDisplayed(el);
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Type in search field")
    public void searchFiled(String text) {
        waitForPageFinishLoading();
        scrollToElement(searchField);
        fillText(searchField, text);
        waitForPageFinishLoading();
        sleep(5000);
    }

    @Step("Type in search field")
    public void clickOnSearchFiled() {
        waitForPageFinishLoading();
        scrollToElement(searchField);
        waitForElementToBeClickable(searchField);
        click(searchField);
    }

    @Step("Return the count of entities list")
    public int counterEntitiesList() {
        waitForPageFinishLoading();
        sleep(2000);
        getEntityNumber += entitiesList.size();
        return getEntityNumber;
    }

    @Step("Return the value of total entities box")
    public int totalEntitiesValueBox() {
        sleep(2000);
        scrollToElement(totalEntitiesValueBox);
        String str = getText(totalEntitiesValueBox);
        totalEntities = Integer.parseInt(str);
        return totalEntities;
    }

    @Step("Check the entity's icon")
    public String entityIconIsDisplayed() {
        String path = null;
        for (WebElement el : entityType) {
            scrollToElement(el);
            path = getAttribute(el, "d");
        }
        return path;
    }

    @Step("Return the value of under evaluation box")
    public int underEvaluationValueBox() {
        try {
            scrollToElement(underEvaluationValueBox);
            String str = getText(underEvaluationValueBox);
            totalEntities = Integer.parseInt(str);
            return totalEntities;
        } catch (Exception e) {
            return 0;
        }
    }

    @Step("Return the value of approved box")
    public int approvedValueBox() {
        try {
            scrollToElement(approvedValueBox);
            String str = getText((approvedValueBox));
            totalEntities = Integer.parseInt(str);
            return totalEntities;
        } catch (Exception e) {
            return 0;
        }
    }

    @Step("Return the value of suspended box")
    public int suspendedValueBox() {
        try {
            String str = getText(suspendedValueBox);
            totalEntities = Integer.parseInt(str);
            return totalEntities;
        } catch (Exception e) {
            return 0;
        }
    }

    @Step("Return the value of rejected box")
    public int rejectedValueBox() {
        try {
            scrollToElement(rejectedValueBox);
            String str = getText(rejectedValueBox);
            totalEntities = Integer.parseInt(str);
            return totalEntities;
        } catch (Exception e) {
            return 0;
        }
    }

    @Step("Sum all entitiesBox")
    public int sumEntitiesBox() {
        sumEntitiesBox = (underEvaluationValueBox() + approvedValueBox() + suspendedValueBox() + rejectedValueBox());
        return sumEntitiesBox;
    }

    @Step("Check that no result icon is displayed")
    public boolean noResultIcon() {
        scrollToElement(noResultIcon);
        return imageIsDisplayed(noResultIcon);
    }

    @Step("Check that no result message is displayed")
    public String noResultMessage() {
        scrollToElement(noResultMessage);
        return getText(noResultMessage);
    }

    @Step("Choose Entity: {entityName}")
    public void chooseEntity() {
        waitForPageFinishLoading();
        waitForList(entityNameList);
        click(entityNameList.get(0));
    }

    @Step("Return the text of business category of entity")
    public String businessCategory11() {
        String value = null;
        for (WebElement el : entityBusinessCategory) {
            scrollToElement(el);
            value = getText(el);
        }
        return value;
    }


    @Step("Return the text of business category of entity")
    public String businessfCategory(String businessCategoryName) {

        String value = null;
        for (WebElement el : entityBusinessCategory) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(businessCategoryName)) {
                value = getText(el);
            } else {
                return getText(el);
            }
        }
        return value;
    }


    @Step("Return the status of entity")
    public String entityStatus() {
        waitForPageFinishLoading();
        String value = null;
        if (totalEntitiesValueBox() > 0) {
            waitForList(entityStatusList);
            for (WebElement el : entityStatusList) {
                scrollToElement(el);
                value = getText(el);
            }
        }
        return value;
    }

    @Step("Return the name of entity")
    public String entityName() {
        waitForPageFinishLoading();
        String value = null;
        waitForElementToBeVisibility(entitiesTable);
        for (WebElement el : entityNameList) {
            scrollToElement(el);
            value = getText(el);
        }
        return value;
    }

    @Step("choose name of entity from list")
    public void chooseEntityName(String entitiesName) {
        waitForPageFinishLoading();
        waitForList(entityNameList);
        for (WebElement el : entityNameList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(entitiesName)) {
                click(el);
                break;
            }
        }
    }

    @Step("check if 'New Case' button display")
    public boolean checkNewCaseDisplay(String entitiesName) {
        try {
            for (WebElement el : newCaseButton) {
                if (getText(el).contains(entitiesName)) {
                    scrollToElement(el);
                    return isDisplayed(el);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
}