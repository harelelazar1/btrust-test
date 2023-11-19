package btrust.idm.test;

import btrust.BaseIdmTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.idm.pageObject.SearchPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTest extends BaseIdmTest {

    @BeforeMethod
    public void navigateToCasesPage() {
        NavigationPage navigation = new NavigationPage(driver);
        navigation.mainMenuList("Dashboard");
        navigation.mainMenuList("Cases (Individuals)");
        SearchPage searchPage = new SearchPage(driver);
        Assert.assertTrue(searchPage.statusFilterIsDisplayedOrNot());
    }

    @Test(description = "enter to search page")
    @Description("enter to search page, move between the cases and check that all cases that appear in api  request appear in client")
    public void t01_enterToSearchPage() {
        SearchPage search = new SearchPage(driver);
        List<Integer> apiList = search.getValueFromResponse();
        List<Integer> clientList = search.caseIdList();
        System.out.println(apiList);
        System.out.println(clientList);

        Assert.assertEquals(apiList, clientList);
    }

    @Test(description = "filter by date range")
    @Description("open time frame filter and choose date range from the list and choose date")
    public void t02_filterByTimeFrameDateRange() {
        SearchPage search = new SearchPage(driver);

 //       search.filterByFlow("Onboarding");
        search.chooseDate("Date range", "15", "15", "January 2022");
        System.out.println(System.currentTimeMillis());
        String infoCase1th = search.infoCase1th("Onboarding - new server 15/01/2022 01:22");
        System.out.println(System.currentTimeMillis());
        Assert.assertTrue(infoCase1th.contains("Onboarding - new server 15/01/2022 01:22"));
    }

    @Test(description = "check 'no results' message")
    @Description("Searching and checking message appears No results found and click on clear button to check if cases list appear")
    public void t03_noResults() {
        SearchPage search = new SearchPage(driver);
        search.chooseDate("Date range", "1", "2", "July 2020");

        String expected = search.noResultsMessage("No results found");
        Assert.assertEquals(expected, "No results found");
        search.filterClearTimeFrame();
    }

    @Test(description = "filter by flow filter")
    @Description("filter by flow filter, select 1 option")
    public void t04_filterByFlow() {
        SearchPage search = new SearchPage(driver);
        search.filterByFlow("Onboarding");

        String expected = search.flowStatus("Onboarding");
        Assert.assertEquals(expected, "Onboarding");
        search.filterClearFlow();
    }

    @Test(description = "filter by flow, select 2 options")
    @Description("open flow filter and select 2 options and click on clear button")
    public void t05_filterByFlow2Options() {
        SearchPage search = new SearchPage(driver);
        search.filterByFlow2Options("login", "Onboarding");
        search.filterClearFlow();

        String expected = search.flowStatus("Flow:All");
        Assert.assertEquals("Flow:All", expected);

    }

    @Test(description = "filter by process type")
    @Description("filter by process type, select 1 option")
    public void t06_filterByProcessType() {
        SearchPage search = new SearchPage(driver);
        search.filterByProcessType("בדיקת חיות");

        String expected = search.processTypeStatus("בדיקת חיות");
        Assert.assertEquals(expected, "בדיקת חיות");
        search.filterClearProcessType();
    }

    @Test(description = "filter by process type, select 2 options")
    @Description("open process type filter and select 2 options and click on clear button")
    public void t07_filterByProcessType2Options() {
        SearchPage search = new SearchPage(driver);
        search.filterByProcessType2Options("בדיקת חיות", "השוואת פנים");
        search.filterClearProcessType();

        String expected = search.processTypeStatus("Process Type:All");
        Assert.assertEquals("Process Type:All", expected);
    }

    @Test(description = "filter by status")
    @Description("filter by status, select 1 option")
    public void t08_filterByStatus() {
        SearchPage search = new SearchPage(driver);
        search.filterByStatus("חסר צד אחורי");

        String expected = search.statusStatus("חסר צד אחורי");
        Assert.assertEquals(expected, "חסר צד אחורי");
        search.filterClearStatus();
    }

    @Test(description = "filter by status, select 2 options")
    @Description("open status filter and select 2 options and click on clear button")
    public void t09_filterByStatus2Options() {
        SearchPage search = new SearchPage(driver);
        search.filterByStatus2Options("הפנים אינן תואמות", "הפנים תואמות");
        search.filterClearStatus();

        String expected = search.statusStatus("Status:All");
        Assert.assertEquals("Status:All", expected);
    }

    @Test(description = "filter by name, check that get result after type 3 characters")
    @Description("type 3 characters of case in contain text field")
    public void t10_filterByName3Char() {
        NavigationPage n = new NavigationPage(driver);
        n.mainMenuList("Search");
        SearchPage search = new SearchPage(driver);
        search.filterByContainText("ליע");

        String expected = search.caseNameFirstResultListCases("ליעד טובי");
        Assert.assertEquals("ליעד טובי", expected);
        search.clearContainText();
    }

    @Test(description = "filter by name, no result message")
    @Description("Type a case name that is not in the cases list")
    public void t11_filterByNameNoFoundMessage() {
        SearchPage search = new SearchPage(driver);
        search.filterByContainText("abcd");

        String expected = search.noResultsMessage("No results found");
        Assert.assertEquals("No results found", expected);
        search.clearContainText();
    }

    @Test(description = "filter by name")
    @Description("type full name of case in contain text field")
    public void t12_filterByName() {
        SearchPage search = new SearchPage(driver);
        search.filterByContainText("ליעד טובי");

        String expected = search.caseNameFirstResultListCases("ליעד טובי");
        Assert.assertEquals("ליעד טובי", expected);
        search.clearContainText();
    }

    @Test(description = "Check that the share button copies the URL after filtering")
    @Description("Do filter, click on share button, copy the url in new tab and check that open a new page with the filter that is done")
    public void t13_shareButton() {
        SearchPage search = new SearchPage(driver);
        search.setTimeFrameFilter("Last 2 Months");
        search.filterByFlow("Onboarding");
        search.filterByProcessType("בדיקת חיות");
        search.shareListButton();

        String expected = search.shareListMessage("The url address was successfully copied");
        Assert.assertEquals("The url address was successfully copied", expected);
    }

    @Test(description = "filter by last month")
    @Description("open time frame filter and choose filter by last month")
    public void t14_filterByTimeFrameMonth() {
        SearchPage search = new SearchPage(driver);
        search.setTimeFrameFilter("Last month");

        String expected = search.timeFrameFilterAfterCreateFilter();
        Assert.assertEquals(search.timeFrameFilterAfterCreateFilter(), expected);
    }

    @Test(description = "Open cases")
    @Description("Open cases and check that the correct case open")
    public void t15_openCases() {
        SearchPage search = new SearchPage(driver);
        search.case1th();

        String caseNumberTicket = search.caseNumberTicket();
        String caseNumber1 = search.caseNumber1th();
        Assert.assertEquals(caseNumberTicket, caseNumber1);

        search.case2th();
        caseNumberTicket = search.caseNumberTicket();
        String caseNumber2 = search.caseNumber2th();
        Assert.assertEquals(caseNumberTicket, caseNumber2);

        search.case3th();
        caseNumberTicket = search.caseNumberTicket();
        String caseNumber3 = search.caseNumber3th();
        Assert.assertEquals(caseNumberTicket, caseNumber3);

        search.case4th();
        caseNumberTicket = search.caseNumberTicket();
        String caseNumber4 = search.caseNumber4th();
        Assert.assertEquals(caseNumberTicket, caseNumber4);

        search.case5th();
        caseNumberTicket = search.caseNumberTicket();
        String caseNumber5 = search.caseNumber5th();
        Assert.assertEquals(caseNumberTicket, caseNumber5);
    }
}