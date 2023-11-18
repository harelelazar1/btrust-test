package btrust.btrustOne.client.mandate.tests;

import btrust.btrustOne.client.BaseClientUserTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.mandate.pagesObject.MandatesPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MandatesTest extends BaseClientUserTest {

    MandatesPage mandatesPage;


    @BeforeMethod
    public void navigateToMandatesPage() {
        NavigationPage navigationPage = new NavigationPage(driver);
        driver.navigate().refresh();
        navigationPage.mainMenuList("Mandates");
        mandatesPage = new MandatesPage(driver);
        Assert.assertEquals(mandatesPage.mandatesTitle("Mandates"), "Mandates");
    }

    @Test(description = "Filter by creation frame")
    @Description("Filter by creation frame - select date rage")
    public void t01_filterByCreationFrame() {
        mandatesPage.filterByDateRage("Creation Frame", "Date range", "24", "25", "April 2021");
        Assert.assertEquals(mandatesPage.mandateDate("25/04/21"), "24/04/21 - 25/04/21");
    }

    @Test(description = "Filter by search field")
    @Description("perform search by search field")
    public void t04_filterBySearchField() {
        mandatesPage.searchField("ZRHZY");
        Assert.assertTrue(mandatesPage.mandatesRelationshipNumber("ZRHZY"));
    }

    @Test(description = "Filter by all filters")
    @Description("perform search by all filters")
    public void t05_filterByAllFilters() {
        mandatesPage.filterMandateTable("Creation Frame", "Last year");
        mandatesPage.filterMandateTable("Status", "draft");
        mandatesPage.filterMandateTable("Open Cases", "No");
        mandatesPage.searchField("52zxv");
        Assert.assertTrue(mandatesPage.mandateDate("22").contains("22"));
        Assert.assertEquals(mandatesPage.mandatesStatus(), "Draft");
        Assert.assertTrue(mandatesPage.mandateOpenCasesList().isEmpty());
        Assert.assertTrue(mandatesPage.mandatesRelationshipNumber("52zxv"));

        mandatesPage.clearButton();
        Assert.assertFalse(mandatesPage.clearButtonIsDisplayed());
    }

    @Test(description = "No result")
    @Description("Perform search and check that no result message is displayed")
    public void t06_noResult() {
        mandatesPage.searchField("QATest");
        Assert.assertTrue(mandatesPage.noResultIconIsDisplayed());
        Assert.assertEquals(mandatesPage.noResultMessageIsDisplayed(), "No suitable mandate were matched your search");
        Assert.assertTrue(mandatesPage.createNewMandateButtonIsDisplayed());
    }
}