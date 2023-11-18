package btrust.btrustOne.client.cases.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class CasesPage extends BasePage {
    public CasesPage(WebDriver driver) {
        super(driver);
    }

    String entityId;
    String caseId;
    String status;
    String workflow;
    String caseName;
    int sumCasesBox;
    List<Integer> integers = new ArrayList<>();

    /*
    filters elements
     */
    @FindBy(css = "div input[type='text']")
    protected WebElement entityNameToSearch;
    @FindBy(css = ".select-button.blue-false.TopBar-module__btnStyles__1llt7")
    protected List<WebElement> filtersList;
    @FindBy(css = ".radio-btns > .item")
    protected List<WebElement> radioButtonsOptionsFromFiltersList;
    @FindBy(css = ".items-container > .item")
    protected List<WebElement> checkboxOptionsFromFiltersList;
    @FindBy(css = ".ui.container .TopBar-module__title__10LST")
    protected WebElement casesTitle;
    @FindBy(css = ".TopBar-module__filters__2dTbh>:nth-child(1) .clear")
    protected WebElement clearDueTimeFrameFilter;
    @FindBy(css = ".TopBar-module__filters__2dTbh>:nth-child(2) .clear")
    protected WebElement clearCreationTimeFrameFilter;
    @FindBy(css = ".TopBar-module__filters__2dTbh>:nth-child(3) .clear")
    protected WebElement clearCaseStatusFilter;
    @FindBy(css = ".TopBar-module__filters__2dTbh>:nth-child(4) .clear")
    protected WebElement clearWorkflowFilter;
    @FindBy(css = ".TopBar-module__input-item__1iR8i input[type='text']")
    protected WebElement searchField;
    /*
    datePicker
     */
    @FindBy(css = ".calendar-header > .month")
    protected WebElement month;
    @FindBy(css = ".calendar-header > .btn.prev")
    protected WebElement previousMonthButton;
    @FindBy(css = ".react-datepicker__month>div>div")
    protected List<WebElement> dayList;

    @FindBy(css = ".MuiTableBody-root .MuiTableCell-body:nth-child(5)")
    protected List<WebElement> caseStatusList;
    @FindBy(css = ".select-button.blue-false.TopBar-module__btnStyles__1llt7")
    protected WebElement casesTable;
    @FindBy(css = ".Blocks-module__wrapper__bjzJb > :nth-child(1) .Blocks-module__value__3cd3K")
    protected WebElement valueBoxTotalCases;
    @FindBy(css = ".Blocks-module__wrapper__bjzJb > :nth-child(2) .Blocks-module__value__3cd3K")
    protected WebElement valueBoxOpenCases;
    @FindBy(css = ".Blocks-module__wrapper__bjzJb > :nth-child(3) .Blocks-module__value__3cd3K")
    protected WebElement valueBoxOverdueCases;
    @FindBy(css = ".Blocks-module__wrapper__bjzJb > :nth-child(4) .Blocks-module__value__3cd3K")
    protected WebElement valueBoxCloseCases;
    /*
    no result elements
     */
    @FindBy(css = "div :nth-child(2) img")
    protected WebElement noResultIcon;
    @FindBy(css = "div .NoFound-module__title__2FTUc")
    protected WebElement noResultMessage;
    @FindBy(css = ".BackToCases-module__wrapper__2rCyj>button")
    protected WebElement backToCasesListButton;

    @FindBy(css = ".Pagination-module__pagination__3UDnD>:nth-child(1)")
    protected WebElement previousPageButtonCasesTable;
    @FindBy(css = ".Pagination-module__pagination__3UDnD>:nth-child(3)")
    protected WebElement nextPageButtonCasesTable;
    @FindBy(css = ".TopBar-module__input-item__1iR8i > .MuiSvgIcon-root")
    protected WebElement removeValueSearchField;
    @FindBy(css = ".TopBar-module__input-item__1iR8i .MuiSvgIcon-root")
    protected WebElement searchIcon;

    /*
    case info
     */
    @FindBy(css = ".MuiTableRow-root :nth-child(2) .CasesTable-module__entity-name__8bfbx")
    protected List<WebElement> caseNameList;
    @FindBy(css = "#entity-id")
    protected List<WebElement> entityIdsList;
    @FindBy(css = "#case-id")
    protected List<WebElement> caseIdsList;
    @FindBy(css = "tr > td:nth-child(3) div:nth-child(1)")
    protected List<WebElement> workflowsList;


    @Step("Check that casesTitle appear")
    public boolean casesTitle() {
        waitForPageFinishLoading();
        scrollToElement(casesTitle);
        waitForTextToBeInElement(casesTitle, "Cases");
        return getText(casesTitle).equalsIgnoreCase("Cases");
    }

    @Step("Check the value of month")
    public String month() {
        String str = null;
        if (isDisplayed(month)) {
            scrollToElement(month);
            str = getText(month);
        }
        return str;
    }

    @Step("Perform filter to cases table")
    public void filterTable(String filter, String option) {
        waitForPageFinishLoading();
        waitForList(filtersList);
        for (WebElement el : filtersList) {
            scrollToElement(el);
            if (getText(el).contains(filter)) {
                scrollToElement(el);
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
            sleep(5000);
        }
        if (radioButtonsOptionsFromFiltersList.size() > 0) {
            for (WebElement el : radioButtonsOptionsFromFiltersList) {
                scrollToElement(el);
                if (getText(el).equalsIgnoreCase(option)) {
                    click(el);
                    break;
                }
            }
        } else if (checkboxOptionsFromFiltersList.size() > 0) {
            for (WebElement el : checkboxOptionsFromFiltersList) {
                scrollToElement(el);
                if (getText(el).equalsIgnoreCase(option)) {
                    click(el);
                    click(entityNameToSearch);
                    break;
                }
            }
        } else {
            System.out.println("not found");
        }
        sleep(10000);
    }

    @Step("Choose date from the date picker")
    public void chooseDate(String filter, String option, String chooseOldDay, String chooseFutureDay, String chooseMonth) {
        filterTable(filter, option);
        while (month() != null) {
            if (getText(month).equalsIgnoreCase(chooseMonth)) {
                for (WebElement el : dayList) {
                    scrollToElement(el);
                    if (getText(el).equalsIgnoreCase(chooseOldDay)) {
                        click(el);
                        break;
                    }
                }
                for (WebElement el : dayList) {
                    scrollToElement(el);
                    if (getText(el).equalsIgnoreCase(chooseFutureDay)) {
                        click(el);
                        click(entityNameToSearch);
                        break;
                    }
                }
                break;
            } else {
                scrollToElement(previousMonthButton);
                click(previousMonthButton);
            }
        }
        sleep(2000);
    }

    @Step("Click on clearDueTimeFrameFilter")
    public void clearDueTimeFrameFilter() {
        scrollToElement(clearDueTimeFrameFilter);
        click(clearDueTimeFrameFilter);
    }

    @Step("Click on clearCreationTimeFrameFilter")
    public void clearCreationTimeFrameFilter() {
        scrollToElement(clearCreationTimeFrameFilter);
        click(clearCreationTimeFrameFilter);
    }

    @Step("Click on clearCaseStatusFilter")
    public void clearCaseStatusFilter() {
        scrollToElement(clearCaseStatusFilter);
        click(clearCaseStatusFilter);
    }

    @Step("Click on clearWorkflowFilter")
    public void clearWorkflowFilter() {
        scrollToElement(clearWorkflowFilter);
        click(clearWorkflowFilter);
    }

    @Step("Check if removeValueSearchField is enabled or not")
    public boolean removeValueButtonSearchFieldDisplayedOrNot() {
        try {
            waitForElementToBeVisibility(removeValueSearchField);
            isDisplayed(removeValueSearchField);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Click on removeValueSearchField")
    public void removeValueSearchField() {
        scrollToElement(removeValueSearchField);
        click(removeValueSearchField);
        sleep(5000);
    }

    @Step("Return the count of the cases")
    public int counterCases() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(casesTable);
        scrollToElement(previousPageButtonCasesTable);
        while (previousPageButtonCasesTable.isEnabled()) {
            scrollToElement(previousPageButtonCasesTable);
            click(previousPageButtonCasesTable);
        }
        waitForPageFinishLoading();
        waitForElementToBeVisibility(casesTable);
        scrollToElement(caseIdsList.get(0));
        for (int i = 0; i < caseIdsList.size(); i++) {
            while (integers.size() < returnValueBoxTotalCases()) {
                integers.add(caseIdsList.size());
            }
        }
        if (isEnabled(nextPageButtonCasesTable)) {
            scrollToElement(nextPageButtonCasesTable);
            click(nextPageButtonCasesTable);
        }
        return integers.size();
    }

    @Step("Run on all the cases that appear in the cases table and return the status of the case")
    public String caseStatus(String caseStatus) {
        waitForPageFinishLoading();
        waitForTextToBeInElement(caseStatusList.get(1), caseStatus);
        while (isEnabled(previousPageButtonCasesTable)) {
            scrollToElement(previousPageButtonCasesTable);
            click(previousPageButtonCasesTable);
        }
        while (integers.size() < returnValueBoxTotalCases()) {
            waitForPageFinishLoading();
            waitForElementToBeVisibility(casesTable);
            for (WebElement el : caseStatusList) {
                scrollToElement(el);
                status = getText(el);
                integers.add(caseStatusList.size());
            }
            if (isEnabled(nextPageButtonCasesTable)) {
                scrollToElement(nextPageButtonCasesTable);
                waitForElementToBeClickable(nextPageButtonCasesTable);
                click(nextPageButtonCasesTable);
            }
        }
        return status;
    }


    @Step("Run on all the cases that appear in the cases table and return the status of the case")
    public String caseStatus2(String caseStatus) {
        waitForPageFinishLoading();
        waitForTextToBeInElement(caseStatusList.get(0), caseStatus);
        for (WebElement el : caseStatusList) {
            scrollToElement(el);
            status = getText(el);
            integers.add(caseStatusList.size());
        }
        return status;
    }

    @Step("Run on all the cases that appear in the cases table and return the workflow of the case")
    public String caseWorkflow(String caseWorkflow) {
        waitForPageFinishLoading();
        waitForTextToBeInElement(workflowsList.get(0), caseWorkflow);
        while (isEnabled(previousPageButtonCasesTable)) {
            scrollToElement(previousPageButtonCasesTable);
            click(previousPageButtonCasesTable);
        }
        while (integers.size() < returnValueBoxTotalCases()) {
            waitForPageFinishLoading();
            for (WebElement el : workflowsList) {
                scrollToElement(el);
                workflow = getText(el);
                integers.add(workflowsList.size());
            }
//            if (isEnabled(nextPageButtonCasesTable)) {
//                scrollToElement(nextPageButtonCasesTable);
//                click(nextPageButtonCasesTable);
//            }
        }
        System.out.println(workflow);
        return workflow;
    }

    @Step("Return the value of box total cases")
    public int returnValueBoxTotalCases() {
//        waitForElementToBeVisibility(valueBoxTotalCases);
        scrollToElement(valueBoxTotalCases);
        return Integer.parseInt(getText(valueBoxTotalCases));
    }

    @Step("Return the value of box open cases")
    public int returnValueBoxOpenCases() {
        scrollToElement(valueBoxOpenCases);
        return Integer.parseInt(getText(valueBoxOpenCases));
    }

    @Step("Return the value of box overdue cases")
    public int returnValueBoxOverdueCases() {
        scrollToElement(valueBoxOverdueCases);
        return Integer.parseInt(getText(valueBoxOverdueCases));
    }

    @Step("Return the value of box close cases")
    public int returnValueBoxCloseCases() {
        scrollToElement(valueBoxCloseCases);
        return Integer.parseInt(getText(valueBoxCloseCases));
    }

    @Step("Sum all cases box")
    public int sumCasesBox() {
        sumCasesBox = (returnValueBoxOpenCases() + returnValueBoxOverdueCases() + returnValueBoxCloseCases());
        return sumCasesBox;
    }

    @Step("Type entity name in search field")
    public void filterBySearchField(String entityName) {
        scrollToElement(searchField);
        fillText(searchField, entityName);
        Assert.assertTrue(removeValueButtonSearchFieldDisplayedOrNot());
    }

    @Step("Return the name of case")
    public String caseName(String name) {
        waitForPageFinishLoading();
        //     waitForTextToBeInElement(caseNameList.get(0), name);
        while (isEnabled(previousPageButtonCasesTable)) {
            waitForElementToBeClickable(previousPageButtonCasesTable);
            click(previousPageButtonCasesTable);
        }
        waitForPageFinishLoading();
        waitForList(caseNameList);
        for (WebElement el : caseNameList) {
            if (getText(el).equalsIgnoreCase(name)) {
                caseName = getText(el);
                integers.add(caseNameList.size());
                break;
            }
        }
        return caseName;
    }

    @Step("Check that no result message, no result icon appear")
    public boolean noResult() {
        try {
            waitForTextToBeInElement(noResultMessage, "We didn’t find what you were looking for");
            getText(noResultMessage);
            scrollToElement(noResultIcon);
            isDisplayed(noResultIcon);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Click on backToCasesListButton")
    public void backToCasesListButton() {
        scrollToElement(backToCasesListButton);
        clickByJS(backToCasesListButton);
        waitForElementToBeVisibility(searchIcon);
        isDisplayed(searchIcon);
        waitForPageFinishLoading();
    }

    @Step("Return the first entity id")
    public String firstEntityId() {
        waitForPageFinishLoading();
        waitForList(entityIdsList);
        entityId = getText(entityIdsList.get(0));
        return entityId;
    }

    @Step("Return the first case id")
    public String firstCaseId() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(caseIdsList.get(0));
        caseId = getText(caseIdsList.get(0));
        return caseId;
    }
}