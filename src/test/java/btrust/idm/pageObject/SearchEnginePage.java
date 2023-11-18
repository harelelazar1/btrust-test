package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchEnginePage extends BasePage {
    public SearchEnginePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".main-text > span")
    protected WebElement title;
    @FindBy(css = ".entity-types > button:nth-child(1)")
    protected WebElement individualButton;
    @FindBy(css = ".entity-types > button:nth-child(2)")
    protected WebElement organizationButton;
    @FindBy(css = ".more-options > button")
    protected WebElement searchButton;
    @FindBy(css = ".input-item > input")
    protected WebElement whichField;
    @FindBy(css = ".input-error > span")
    protected WebElement errorMessage;
    @FindBy(css = ".MoreFiltersSelect_container > .button")
    protected WebElement moreFiltersButton;
    @FindBy(css = ".options > .item")
    protected List<WebElement> moreFiltersList;
    @FindBy(css = ".btns > :nth-child(1)")
    protected WebElement productApprovalButton;
    @FindBy(css = ".btns > :nth-child(2)")
    protected WebElement exportControlSanctionsButton;
    @FindBy(css = ".btns > :nth-child(3)")
    protected WebElement giftsEntertainmentButton;
    @FindBy(css = ".btns > :nth-child(4)")
    protected WebElement whistleblowingButton;

    /*
        Location filter elements
     */
    @FindBy(css = ".LocationSelect > .LocationSelect__control")
    protected WebElement countrySelect;
    @FindBy(css = ".LocationSelect__menu-list > .LocationSelect__option")
    protected List<WebElement> countryList;
    @FindBy(css = ".location-filter > :nth-child(2)")
    protected WebElement addCityButton;
    @FindBy(css = ".LocationSelect__menu-list > .LocationSelect__option")
    protected List<WebElement> cityList;
    @FindBy(css = ".title > span")
    protected WebElement locationFilterTitle;

    /*
        Yearly revenue filter elements
      */
    @FindBy(css = "[name='minYearlyRevenue']")
    protected WebElement minYearlyRevenueField;
    @FindBy(css = "[name='maxYearlyRevenue']")
    protected WebElement maxYearlyRevenueField;
    @FindBy(css = ".individual-container > :nth-child(4) .text")
    protected WebElement yearlyRevenueFilterTitle;

    /*
        Employees filter elements
      */
    @FindBy(css = "[name='minEmploees']")
    protected WebElement minEmploees;
    @FindBy(css = "[name='maxEmploees']")
    protected WebElement maxEmploees;
    @FindBy(css = ".individual-container > :nth-child(5) .text")
    protected WebElement employeesFilterTitle;


    @Step("Check that title of search engine screen appear")
    public String title(String text) {
        waitForTextToBeInElement(title, text);
        return getText(title);
    }

    @Step("Check that individualButton is disable")
    public boolean individualButtonIsDisable() {
        try {
            waitForElementToBeVisibility(individualButton);
            isEnabled(individualButton);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Click on organizationButton")
    public void organizationButton() {
        waitForElementToBeClickable(organizationButton);
        click(organizationButton);
    }

    @Step("Check that searchButton is disable")
    public boolean searchButtonIsDisable() {
        waitForElementToBeVisibility(searchButton);
        return !searchButton.isEnabled();
    }

    @Step("Check that productApprovalButton is disable")
    public boolean productApprovalButtonIsDisable() {
        waitForElementToBeVisibility(productApprovalButton);
        return !productApprovalButton.isEnabled();
    }

    @Step("Check that exportControlSanctionsButton is disable")
    public boolean exportControlSanctionsButtonIsDisable() {
        waitForElementToBeVisibility(exportControlSanctionsButton);
        return !exportControlSanctionsButton.isEnabled();
    }

    @Step("Check that giftsEntertainmentButton is disable")
    public boolean giftsEntertainmentButtonIsDisable() {
        waitForElementToBeVisibility(giftsEntertainmentButton);
        return !giftsEntertainmentButton.isEnabled();
    }

    @Step("Check that whistleblowingButton is disable")
    public boolean whistleblowingButtonIsDisable() {
        waitForElementToBeVisibility(whistleblowingButton);
        return !whistleblowingButton.isEnabled();
    }

    @Step("Search organization without filters")
    public void searchOrganization(String organizationName) {
        waitForElementToBeClickable(whichField);
        fillText(whichField, organizationName);
    }

    @Step("Click on searchButton")
    public void searchButton() {
        if (searchButtonIsDisable()) {
            waitForElementToBeVisibility(searchButton);
            System.out.println("The button is disable");
        } else {
            System.out.println("The button is enable");
            waitForElementToBeClickable(searchButton);
            click(searchButton);
        }
    }

    @Step("Click enter in keyboard")
    public void clickEnter() {
        sleep(2000);
        waitForElementToBeVisibility(whichField);
        whichField.sendKeys(Keys.ENTER);
    }

    @Step("Check that error message appear")
    public String errorMessage(String text) {
        waitForTextToBeInElement(errorMessage, text);
        return getText(errorMessage);
    }

    @Step("Choose from moreFiltersList: {chooseFromMoreFiltersList}")
    public void moreFilters(String chooseFromMoreFiltersList) {
        sleep(1000);
        waitForElementToBeClickable(moreFiltersButton);
        click(moreFiltersButton);
        sleep(1000);
        for (WebElement el : moreFiltersList) {
            if (el.getText().equalsIgnoreCase(chooseFromMoreFiltersList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
    }

    @Step("Choose country from countryList: {chooseFromCountryList}" +
            "Choose city from cityList: {chooseFromCityList}")
    public void addLocationFilter(String chooseFromCountryList, String chooseFromCityList) {
        waitForElementToBeClickable(countrySelect);
        click(countrySelect);
        for (WebElement el : countryList) {
            if (el.getText().equalsIgnoreCase(chooseFromCountryList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(addCityButton);
        click(addCityButton);
        click(addCityButton);
        for (WebElement el : cityList) {
            if (el.getText().equalsIgnoreCase(chooseFromCityList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
    }

    @Step("Type minium yearly revenue: {minimum} and type maximum yearly revenue: {maximum}")
    public void addYearlyRevenueFilter(String minimum, String maximum) {
        waitForElementToBeClickable(minYearlyRevenueField);
        fillText(minYearlyRevenueField, minimum);
        waitForElementToBeClickable(maxYearlyRevenueField);
        fillText(maxYearlyRevenueField, maximum);
    }

    @Step("Type minium employee: {minimum} and type maximum employee: {maximum}")
    public void addEmployeeFilter(String minimum, String maximum) {
        waitForElementToBeClickable(minEmploees);
        fillText(minEmploees, minimum);
        waitForElementToBeClickable(maxEmploees);
        fillText(maxEmploees, maximum);
    }

    @Step("Check that locationFilterTitle is display")
    public boolean locationFilterTitleDisplayed() {
        waitForElementToBeVisibility(locationFilterTitle);
        isDisplayed(locationFilterTitle);
        return true;
    }

    @Step("Check that yearlyRevenueFilterTitle is display")
    public boolean yearlyRevenueFilterTitleDisplayed() {
        waitForElementToBeVisibility(yearlyRevenueFilterTitle);
        isDisplayed(yearlyRevenueFilterTitle);
        return true;
    }

    @Step("Check that employeesFilterTitle is display")
    public boolean employeesFilterTitleDisplayed() {
        waitForElementToBeVisibility(employeesFilterTitle);
        isDisplayed(employeesFilterTitle);
        return true;
    }
}