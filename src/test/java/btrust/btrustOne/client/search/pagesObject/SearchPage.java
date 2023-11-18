package btrust.btrustOne.client.search.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".SearchPage-module__wrapper__2w8Js> .SearchPage-module__title__3opvr")
    protected WebElement searchTitle;
    @FindBy(css = ".SearchPanel-module__filter__1yxRl > .EntitySelect-module__container__tTBmQ>[role='tabpanel']")
    protected WebElement entityTypeSelect;
    @FindBy(css = ".EntitySelect-module__options__3hoO8 > .EntitySelect-module__item__1VFX9")
    protected List<WebElement> entityTypeList;
    @FindBy(css = ".SearchPanel-module__wrapper__1Z_Qp > input")
    protected WebElement searchField;
    @FindBy(css = ".SearchPanel-module__container__e5zd8 > button")
    protected WebElement searchButton;
    @FindBy(css = "[type=button].NoData-module__new-mandate__2Lgs2")
    protected WebElement createNewEntityButton;
    @FindBy(css = ".NoData-module__title__dgZPT > span:nth-of-type(1)")
    protected WebElement NoSuitableEntitiesWereMatchedYourSearch;

    @FindBy(css = ".AddButton-module__container__1EEUu  button")
    protected WebElement addEntityButton;
    @FindBy(css = "div> li")
    protected List<WebElement> addEntityMenu;


    boolean searchButtonIsEnabled;


    @Step("Perform search entity")
    public void searchEntity(String entityType, String entityName) {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(entityTypeSelect);
        click(entityTypeSelect);
        waitForList(entityTypeList);
        for (WebElement el : entityTypeList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(entityType)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        waitForElementToBeClickable(searchField);
        fillText(searchField, entityName);
        if (searchButtonIsEnabled()) {
            waitForElementToBeClickable(searchButton);
            click(searchButton);
        } else {
            searchButtonIsEnabled();
        }
    }


    @Step("click search entity")
    public void ClickSearchEntity() {
        waitForElementToBeClickable(entityTypeSelect);
        click(entityTypeSelect);
    }


    @Step("Search display entity name")
    public boolean searchDisplayEntityName(String entityType, int listSize) {
        boolean bol = false;
        waitForElementToBeClickable(entityTypeSelect);
        click(entityTypeSelect);
        waitForList(entityTypeList);

        if (listSize == entityTypeList.size()) {
            for (WebElement el : entityTypeList) {
                if (getText(el).equalsIgnoreCase(entityType)) {
                    waitForElementToBeClickable(el);
                    return true;
                }
            }
        }

        return false;
    }


    @Step("Return the text of search title")
    public String searchTitle() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(searchTitle);
        return getText(searchTitle);
    }

    @Step("Check if search button is enable")
    public boolean searchButtonIsEnabled() {
        searchButtonIsEnabled = true;
        searchButtonIsEnabled = isEnabled(searchButton);
        return searchButtonIsEnabled;
    }

    @Step("click on create new entity button")
    public void createNewEntityButton() {
        scrollToElement(createNewEntityButton);
        click(createNewEntityButton);
    }


    @Step("click on create new entity menu")
    public void chooseNameInAddEntityMenu(String option) {
            waitForElementToBeVisibility(addEntityButton);
            scrollToElement(addEntityButton);
            if (isDisplayed(addEntityButton)) {
                waitForElementToBeClickable(addEntityButton);
                click(addEntityButton);
                waitForList(addEntityMenu);
                for (WebElement el : addEntityMenu) {
                    scrollToElement(el);
                    if (getText(el).equals(option)) {
                        waitForTextToBeInElement(el, option);
                        click(el);
                        break;
                    }
                }
            }
    }


    @Step("Return the text of 'No Suitable Entities Were Matched Your Search'")
    public String NoSuitableEntitiesWereMatchedYourSearch() {
        waitForElementToBeVisibility(NoSuitableEntitiesWereMatchedYourSearch);
        return getText(NoSuitableEntitiesWereMatchedYourSearch);
    }


}