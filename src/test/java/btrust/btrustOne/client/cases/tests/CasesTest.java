package btrust.btrustOne.client.cases.tests;

import btrust.btrustOne.client.BaseClientUserTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.cases.pagesObject.CasesPage;
import btrust.btrustOne.client.entity.pagesObject.EntitiesPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import btrust.btrustOne.client.search.pagesObject.SearchPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CasesTest extends BaseClientUserTest {


    CasesPage casesPage ;


    @BeforeMethod
    public void navigateToCasesPage() throws InterruptedException {
        NavigationPage navigationPage = new NavigationPage(driver);

        casesPage = new CasesPage(driver);
        navigationPage.mainMenuList("Cases");
        driver.navigate().refresh();
        Thread.sleep(10000);
    }

    @Test(description = "Filter by due time frame filter")
    @Description("Perform filter to entities list by due time frame filter")
    public void t01_filterByDueTimeFrameFilter() {
        casesPage.chooseDate("Due Time Frame", "Date range", "1", "25", "September 2023");

        Assert.assertEquals(casesPage.sumCasesBox(), casesPage.returnValueBoxTotalCases());
        Assert.assertEquals(casesPage.returnValueBoxTotalCases(), casesPage.counterCases());
    }

    @Test(description = "Filter by creation time frame filter")
    @Description("Perform filter to entities list by creation time frame filter")
    public void t02_filterByCreationTimeFrameFilter() {
        casesPage.chooseDate("Creation Time Frame", "Date range", "1", "25", "September 2023");

        Assert.assertEquals(casesPage.sumCasesBox(), casesPage.returnValueBoxTotalCases());
        Assert.assertEquals(casesPage.returnValueBoxTotalCases(), casesPage.counterCases());
    }

    @Test(description = "Filter by open cases status filter",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Perform filter to entities list by closed cases status filter")
    public void t03_filterByOpenCaseStatusFilter() {
        casesPage.filterTable("Case Status", "Open");
        Assert.assertEquals(casesPage.caseStatus2("Open"), "Open");

    }

    @Test(description = "Filter by overdue cases status filter",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Perform filter to entities list by overdue cases status filter")
    public void t04_filterByOverdueCaseStatusFilter() {
        driver.navigate().refresh();
        casesPage.filterTable("Case Status", "Overdue");

        Assert.assertEquals(casesPage.caseStatus2("Overdue"), "Overdue");
    }

    @Test(description = "Filter by closed cases status filter",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Perform filter to entities list by closed cases status filter")
    public void t05_filterByClosedCaseStatusFilter() {
        casesPage.filterTable("Case Status", "Closed");

        Assert.assertEquals(casesPage.caseStatus2("Closed"), "Closed");
    }

    @Test(description = "Filter by workflow filter",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Perform filter to entities list by workflow filter")
    public void t06_filterByWorkflowFilter() {
        casesPage.filterTable("Workflow", "Fatca Re-Certification");

        Assert.assertEquals(casesPage.caseWorkflow("Fatca Re-Certification"), "Fatca Re-Certification");
        Assert.assertEquals(casesPage.sumCasesBox(), casesPage.returnValueBoxTotalCases());
    }

    @Test(description = "Filter by search field",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Perform filter to entities list by search field")
    public void t07_filterBySearchField() {
        casesPage.filterBySearchField("VLADIMIR");

        Assert.assertEquals(casesPage.caseName("VLADIMIR"), "VLADIMIR");
        casesPage.backToCasesListButton();
        Assert.assertEquals(casesPage.sumCasesBox(), casesPage.returnValueBoxTotalCases());
    }

    @Test(description = "Filter by all filters")
    @Description("Perform filter to entities list by all filters")
    public void t08_filterByAllFilters() {
        casesPage.filterTable("Due Time Frame", "Last year");
        casesPage.filterTable("Creation Time Frame", "Last year");
        casesPage.filterTable("Case Status", "Overdue");
        casesPage.filterTable("Workflow", "Copy of Nitzan W9 IRS");
        casesPage.filterBySearchField("Entity763dvkru");

        Assert.assertEquals(casesPage.caseName("Entity763dvkru"), "Entity763dvkru");
        casesPage.clearDueTimeFrameFilter();
        casesPage.clearCreationTimeFrameFilter();
        casesPage.clearCaseStatusFilter();
        casesPage.clearWorkflowFilter();
        casesPage.backToCasesListButton();
    }

    @Test(description = "No result message")
    @Description("Search entity that not exist in the list and check that no result elements appear")
    public void t09_noResult() {
        int totalCases = casesPage.returnValueBoxTotalCases();
        casesPage.filterBySearchField("BlaTest");

        Assert.assertTrue(casesPage.noResult());
        casesPage.removeValueSearchField();
        Assert.assertEquals(totalCases, casesPage.returnValueBoxTotalCases());
    }
}