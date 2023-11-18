package btrust.btrustOne.client.mandate.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class MandatesPage extends BasePage {
    public MandatesPage(WebDriver driver) {
        super(driver);
    }

    String mandateValue;

    @FindBy(css = ".SettingsHeaderFiltersAndSearch-module__wrapper__Z1s-1 > .SettingsHeaderFiltersAndSearch-module__title__WW0OL")
    protected WebElement mandatesTitle;
    @FindBy(css = ".SettingsHeaderFiltersAndSearch-module__filters__2gIpr >div")
    protected List<WebElement> filtersList;
    @FindBy(css = ".select-content.select-content .item")
    protected List<WebElement> optionsFromFilters;
    @FindBy(css = ".Table-module__container__1pp-A > .Table-module__wrapper__IgdBP")
    protected WebElement mandateTable;
    @FindBy(css = ".select-button > .clear.change-position")
    protected List<WebElement> clearButtonsList;
    @FindBy(css = ".DataIsEmpty-module__container__cPF0M > img")
    protected WebElement noResultIcon;
    @FindBy(css = ".DataIsEmpty-module__container__cPF0M > .DataIsEmpty-module__title__1WZOf")
    protected WebElement noResultMessage;
    @FindBy(css = ".DataIsEmpty-module__container__cPF0M > button")
    protected WebElement createNewMandateButton;
    @FindBy(css = ".InputSearch-module__inputItem__2Oj7R > input")
    protected WebElement searchField; // the search bar in mandates page
    @FindBy(css = ".num-dash")
    protected WebElement numOfPageResults;
    @FindBy(css = ".numbers > span:nth-of-type(3)")
    protected WebElement numOfTotalResults;
    @FindBy(css = ".Table-module__row__9AM1x .Table-module__status__2TqwZ")
    protected List<WebElement> mandatesStatusList;
    @FindBy(css = ".Table-module__wrapper__IgdBP .Table-module__item__3Uj1u.Table-module__blue__1ilWz")
    protected List<WebElement> mandateRelationshipNumberList;
    @FindBy(css = ".Table-module__wrapper__IgdBP > .Table-module__row__9AM1x > :nth-child(7)")
    protected List<WebElement> mandateOpenCasesList;
    @FindBy(css = ".pagination-btns > button:nth-of-type(1)")
    protected WebElement leftArrowPagination; // left arrow of the Pagination, should be disable at first
    @FindBy(css = ".pagination-btns > button:nth-of-type(2)")
    protected WebElement rightArrowPagination; // right arrow of the Pagination, should be disable at first
    @FindBy(css = ".month")
    protected WebElement month; // the month in the time picker
    @FindBy(css = "[role='listbox'] [tabindex='-1']")
    protected List<WebElement> dayList; //days in the calender list
    @FindBy(css = ".btn.prev > svg")
    protected WebElement previousMonthButton; // the month in the time picker
    @FindBy(css = "div[role='tabpanel'] > .selected-date")
    protected List<WebElement> mandatesDatesList;
    @FindBy(css = ".SettingsHeaderFiltersAndSearch-module__title__WW0OL > .AddButton-module__btn__2_wO7")
    protected WebElement addNewMandateButton;
    @FindBy(css = ".Table-module__wrapper__IgdBP > :nth-child(1) > .Table-module__item__3Uj1u")
    protected List<WebElement> firstMandateInformation;
    @FindBy(css = ".TableHeader-module__conteiner__1X432 > .TableHeader-module__headCell__3PPV2")
    protected List<WebElement> mandatesTableTitles;
    @FindBy(css = ".InputSearch-module__inputItem__2Oj7R > .InputSearch-module__close__1_kV0")
    protected WebElement clearSearchField;


    @Step("Check that mandates title is displayed")
    public String mandatesTitle(String title) {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(mandateTable);
        scrollToElement(mandatesTitle);
        waitForTextToBeInElement(mandatesTitle, title);
        return getText(mandatesTitle);
    }

    @Step("Check that mandate table is displayed")
    public boolean mandateTableIsDisplayed() {
        waitForElementToBeVisibility(mandateTable);
        return isDisplayed(mandateTable);
    }

    @Step("Type in search field")
    public void searchField(String text) {
        waitForElementToBeClickable(searchField);
        fillText(searchField, text);
        waitForElementToBeClickable(clearSearchField);
    }

    @Step("Open filter: {filter} and choose option: {option}")
    public void filterMandateTable(String filter, String option) {
        waitForPageFinishLoading();
        for (WebElement el : filtersList) {
            scrollToElement(el);
            if (getText(el).contains(filter)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        for (WebElement el : optionsFromFilters) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(option)) {
                waitForElementToBeClickable(el);
                click(el);
                //    click(searchField);
                break;
            }
        }
        sleep(10000);
    }

    @Step("Check the mandates status is displayed")
    public String mandatesStatus() {
        String status = null;
        try {
            for (WebElement el : mandatesStatusList) {
                scrollToElement(el);
                status = getText(el);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        return status;
    }

    @Step("click the mandate by status name")
    public void clickMandatesByStatusName(String mandateName) {
        waitForPageFinishLoading();
        waitForList(mandatesStatusList);
        for (WebElement el : mandatesStatusList) {
            if (getText(el).equalsIgnoreCase("mandateName"))
                scrollToElement(el);
            click(el);
            break;
        }
    }


    @Step("Check the mandates relationship no. is displayed")
    public Boolean mandatesRelationshipNumber(String relationshipNo) {
        try {
            waitForPageFinishLoading();
            sleep(10000);
            waitForElementToBeVisibility(mandateRelationshipNumberList.get(0));
            for (WebElement el : mandateRelationshipNumberList) {
                scrollToElement(el);
                if (getText(el).equalsIgnoreCase(relationshipNo)) {
                    scrollToElement(el);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
        return false;
    }

    @Step("Check the mandate open cases is displayed")
    public String mandateOpenCasesList() {
        String openCases = null;
        for (WebElement el : mandateOpenCasesList) {
            scrollToElement(el);
            openCases = getText(el);
        }
        return openCases;
    }

    @Step("pagination arrows test - left and right ")
    public boolean paginationArrowsTest() {
        try {
            Assert.assertTrue(leftArrowPaginationIsEnabled());
            isEnabled(rightArrowPagination);
            waitForElementToBeClickable(rightArrowPagination);
            click(rightArrowPagination);
            isEnabled(leftArrowPagination);

            while (rightArrowPagination.isEnabled()) {
                click(rightArrowPagination);
                if ((getText(numOfPageResults).contains(getText(numOfTotalResults)))) {
                    waitForTextToBeInElement(numOfTotalResults, numOfTotalResults.getText());
                    break;
                }
            }
            if (getText(numOfPageResults).contains(getText(numOfTotalResults)))
                return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Step("pagination arrows test - left")
    public boolean leftArrowPaginationIsEnabled() {
        try {
            waitForElementToBeVisibility(leftArrowPagination);
            isEnabled(leftArrowPagination);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Step("get text of the month")
    public String month() {
        waitForElementToBeVisibility(month);
        return getText(month);
    }


    @Step("Filter by date rage")
    public void filterByDateRage(String filter, String option, String chooseOldDay, String chooseFutureDay, String chooseMonth) {
        filterMandateTable(filter, option);
        while (month() != null) {
            if (getText(month).equalsIgnoreCase(chooseMonth)) {
                for (WebElement el : dayList) {
                    if (el.getText().equalsIgnoreCase(chooseOldDay)) {
                        waitForElementToBeClickable(el);
                        el.click();
                        sleep(2000);
                        break;
                    }
                }
                for (WebElement el : dayList) {
                    if (el.getText().equalsIgnoreCase(chooseFutureDay)) {
                        waitForElementToBeClickable(el);
                        el.click();
                        sleep(2000);
                        break;
                    }
                }
                break;
            } else {
                click(previousMonthButton);
            }
        }
    }


    @Step("Check the value the date of mandate")
    public String mandateDate(String date) {
        waitForPageFinishLoading();
        waitForList(mandatesDatesList);
        scrollToElement(mandatesDatesList.get(0));
        waitForTextToBeInElement(mandatesDatesList.get(0), date);
        return getText(mandatesDatesList.get(0));
    }

    @Step("Click on all the clear buttons that appear")
    public void clearButton() {
        waitForList(clearButtonsList);
        for (WebElement el : clearButtonsList) {
            waitForElementToBeClickable(el);
            click(el);
        }
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

    @Step("Check that no result icon is displayed")
    public boolean noResultIconIsDisplayed() {
        waitForElementToBeVisibility(noResultIcon);
        return imageIsDisplayed(noResultIcon);
    }

    @Step("Check that no result message is displayed")
    public String noResultMessageIsDisplayed() {
        waitForElementToBeVisibility(noResultMessage);
        return getText(noResultMessage);
    }

    @Step("Check that create new mandate button is displayed")
    public boolean createNewMandateButtonIsDisplayed() {
        try {
            waitForPageFinishLoading();
            return isDisplayed(createNewMandateButton);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }
        return false;
    }

    @Step("Click on create new mandate button")
    public void createNewMandateButton() {
        waitForElementToBeClickable(createNewMandateButton);
        click(createNewMandateButton);
    }

    @Step("Click on add new mandate button")
    public void addNewMandateButton() {
        scrollToElement(addNewMandateButton);
        click(addNewMandateButton);
    }

    @Step("Return the first mandate information")
    public String firstMandateInformation(String title) {
        waitForPageFinishLoading();
        waitForList(mandatesTableTitles);
        for (int i = 0; i < mandatesTableTitles.size(); i++) {
            if (getText(mandatesTableTitles.get(i)).equalsIgnoreCase(title)) {
                waitForList(firstMandateInformation);
                waitForElementToBeVisibility(firstMandateInformation.get(i));
                mandateValue = getText(firstMandateInformation.get(i));
                break;
            }
        }
        return mandateValue;
    }
}