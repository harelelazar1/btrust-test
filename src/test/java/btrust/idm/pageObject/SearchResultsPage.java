package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class SearchResultsPage extends BasePage {
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".table-header .title > span")
    protected WebElement title;
    @FindBy(css = ".rezulst-table > .table-item")
    protected WebElement firstResult;
    @FindBy(css = ".location-minimized > .select-button")
    protected WebElement locationFilter;
    @FindBy(css = ".filter-data > :nth-child(4) > .select-button")
    protected WebElement yearlyRevenueFilter;
    @FindBy(css = ".filter-data > :nth-child(5) > .select-button")
    protected WebElement employeeFilter;
    @FindBy(css = ".input-minimized > input")
    protected WebElement searchField;
    @FindBy(css = ".filter-data > .location-minimized .clear")
    protected WebElement clearLocationFilter;
    @FindBy(css = ".filter-data > :nth-child(4) .clear")
    protected WebElement clearYearlyRevenueFilter;
    @FindBy(css = ".top-inputs > :nth-child(1)")
    protected WebElement countrySelect;
    @FindBy(css = ".top-inputs > :nth-child(1) .LocationSelect__option")
    protected List<WebElement> countryList;
    @FindBy(css = ".top-inputs > :nth-child(2) .LocationSelect__option")
    protected List<WebElement> cityList;
    @FindBy(css = "[name='minShortEmployees']")
    protected WebElement minShortEmployeesField;
    @FindBy(css = "[name='maxShortEmployees']")
    protected WebElement maxShortEmployeesField;
    @FindBy(css = ".action-data > button")
    protected WebElement searchButton;
    @FindBy(css = ".action-data .button")
    protected WebElement moreFiltersButton;
    @FindBy(css = ".options > .item")
    protected List<WebElement> filtersList;
    @FindBy(css = ".rezulst-table > :nth-child(1) .button")
    protected WebElement runWorkflowButton;
    @FindBy(css = ".rezulst-table > :nth-child(1) .options > .item")
    protected WebElement onboardingButton;
    @FindBy(css = ".heading > span")
    protected WebElement runWorkflowPopupTitle;
    @FindBy(css = ".RunWorkflowPopup_container > .btns > :nth-child(2) ")
    protected WebElement runWorkflowPopupApprove;
    @FindBy(css = ".ToogleSelect_container > .selector")
    protected WebElement changeSearchType;
    @FindBy(css = ".ResultsTable_container> :nth-child(1) > .pagination > .numbers")
    protected WebElement headerPaginationNumbers;
    @FindBy(css = ".ResultsTable_container> :nth-child(1) > .pagination > .pagination-btns > :nth-child(2)")
    protected WebElement headerPaginationNextButton;
    @FindBy(css = ".ResultsTable_container> :nth-child(1) > .pagination > .pagination-btns > :nth-child(1)")
    protected WebElement headerPaginationPreviousButton;
    @FindBy(css = ".table-header.right > .pagination > .numbers")
    protected WebElement footerPaginationNumbers;
    @FindBy(css = ".table-header.right > .pagination > .pagination-btns > :nth-child(2)")
    protected WebElement footerPaginationNextButton;
    @FindBy(css = ".table-header.right > .pagination > .pagination-btns > :nth-child(1)")
    protected WebElement footerPaginationPreviousButton;


    @Step("Check that title of search results screen appear")
    public String title(String text) {
        waitForTextToBeInElement(title, text);
        return getText(title);
    }

    @Step("Check that firstResult appear")
    public String firstResult() {
        waitForElementToBeVisibility(firstResult);
        return getText(firstResult);
    }

    @Step("Check that locationFilter is display")
    public boolean locationFilterIsDiplayed() {
        waitForElementToBeVisibility(locationFilter);
        js.executeScript("arguments[0].scrollIntoView();", locationFilter);
        waitForElementToBeVisibility(locationFilter);
        return isDisplayed(locationFilter);
    }

    @Step("Check that yearlyRevenueFilter is display")
    public boolean yearlyRevenueFilterIsDiplayed() {
        waitForElementToBeVisibility(yearlyRevenueFilter);
        return isDisplayed(yearlyRevenueFilter);
    }

    @Step("Check that employeeFilter is display")
    public boolean employeeFilterIsDiplayed() {
        waitForElementToBeVisibility(employeeFilter);
        return isDisplayed(employeeFilter);
    }

    @Step("Repeat search")
    public void repeatSearch(String search, String chooseFromCountryList) {
        waitForElementToBeClickable(searchField);
        fillText(searchField, search);
        waitForElementToBeClickable(locationFilter);
        click(locationFilter);
        waitForElementToBeClickable(countrySelect);
        click(countrySelect);
        for (WebElement el : countryList) {
            if (el.getText().equalsIgnoreCase(chooseFromCountryList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(searchButton);
        click(searchButton);
    }

    @Step("Delete Filters")
    public void deleteFilters() {
        waitForElementToBeClickable(clearLocationFilter);
        click(clearLocationFilter);
    }

    @Step("Check if filters displayed or not")
    public boolean assertIfFilersDisplayedOrNot() {
        try {
            waitForElementToBeClickable(clearLocationFilter);
            isDisplayed(clearLocationFilter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Click on more filters and add filters")
    public void moreFilters(String chooseFromFiltersList) {
        waitForElementToBeClickable(moreFiltersButton);
        click(moreFiltersButton);
        for (WebElement el : filtersList) {
            if (el.getText().equalsIgnoreCase(chooseFromFiltersList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }

        }
    }

    @Step("Run workflow")
    public void runWorkflow() {
        waitForElementToBeVisibility(runWorkflowButton);
        waitForElementToBeClickable(runWorkflowButton);
        click(runWorkflowButton);
        waitForElementToBeClickable(onboardingButton);
        click(onboardingButton);
        waitForElementToBeVisibility(runWorkflowPopupTitle);
        Assert.assertEquals(getText(runWorkflowPopupTitle), "Run workflow");
        waitForElementToBeClickable(runWorkflowPopupApprove);
        click(runWorkflowPopupApprove);
    }

    @Step("Check that changeSearchType is display")
    public boolean changeSearchType() {
        waitForElementToBeVisibility(changeSearchType);
        return isDisplayed(changeSearchType);
    }

    @Step("Check that searchField is display")
    public boolean searchField() {
        waitForElementToBeVisibility(searchField);
        return isDisplayed(searchField);
    }

    @Step("Check that moreFiltersButton is display")
    public boolean moreFiltersButton() {
        waitForElementToBeVisibility(moreFiltersButton);
        return isDisplayed(moreFiltersButton);
    }

    @Step("Check if searchButton is Enabled /Disabled")
    public boolean searchButtonIsEnableOrDisabled() {
        try {
            waitForElementToBeVisibility(searchButton);
            isEnabled(searchButton);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Check that headerPaginationNumbers is display")
    public boolean headerPaginationNumbers() {
        waitForElementToBeVisibility(headerPaginationNumbers);
        return isDisplayed(headerPaginationNumbers);
    }

    @Step("Check that headerPaginationNextButton is display")
    public boolean headerPaginationNextButton() {
        waitForElementToBeVisibility(headerPaginationNextButton);
        return isDisplayed(headerPaginationNextButton);
    }

    @Step("Check that headerPaginationPreviousButton is Disabled")
    public boolean headerPaginationPreviousButton() {
        waitForElementToBeVisibility(headerPaginationPreviousButton);
        return isDisplayed(headerPaginationPreviousButton);
    }

    @Step("Check that footerPaginationNumbers is display")
    public boolean footerPaginationNumbers() {
        waitForElementToBeVisibility(footerPaginationNumbers);
        return isDisplayed(footerPaginationNumbers);
    }

    @Step("Check that footerPaginationNextButton is display")
    public boolean footerPaginationNextButton() {
        waitForElementToBeVisibility(footerPaginationNextButton);
        return isDisplayed(footerPaginationNextButton);
    }

    @Step("Check that footerPaginationPreviousButton is Disabled")
    public boolean footerPaginationPreviousButton() {
        waitForElementToBeVisibility(footerPaginationPreviousButton);
        return isDisplayed(footerPaginationPreviousButton);
    }

    @Step("Check that runWorkflowButton is display")
    public boolean runWorkflowButton() {
        waitForElementToBeVisibility(runWorkflowButton);
        return isDisplayed(runWorkflowButton);
    }
}