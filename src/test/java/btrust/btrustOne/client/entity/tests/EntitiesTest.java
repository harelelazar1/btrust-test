package btrust.btrustOne.client.entity.tests;

import btrust.btrustOne.client.BaseClientUserTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.entity.pagesObject.EntitiesPage;
import btrust.btrustOne.client.search.pagesObject.SearchPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EntitiesTest extends BaseClientUserTest {

    @BeforeMethod
    public void navigateToEntitiesPage() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Entities");
        driver.navigate().refresh();
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.clearButton();
        Assert.assertFalse(entitiesPage.clearButtonIsDisplayed());
    }

    @Test(description = "Filter by organization type")
    @Description("Perform filter to entities list by type, select - organization")
    public void t01_filterByOrganizationType() {
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        entitiesPage.filterEntitiesTable("filter", "Entity Type", "Organization");
        entitiesPage.searchFiled("Entity");
        Assert.assertTrue(entitiesPage.entityIconIsDisplayed().equalsIgnoreCase("M12 7V3H2v18h20V7H12zM6 19H4v-2h2v2zm0-4H4v-2h2v2zm0-4H4V9h2v2zm0-4H4V5h2v2zm4 12H8v-2h2v2zm0-4H8v-2h2v2zm0-4H8V9h2v2zm0-4H8V5h2v2zm10 12h-8v-2h2v-2h-2v-2h2v-2h-2V9h8v10zm-2-8h-2v2h2v-2zm0 4h-2v2h2v-2z"));
        Assert.assertEquals(entitiesPage.totalEntitiesValueBox(), entitiesPage.sumEntitiesBox());
//        Assert.assertEquals(entitiesPage.totalEntitiesValueBox(), entitiesPage.counterEntitiesList());
        entitiesPage.clearButton();
        Assert.assertFalse(entitiesPage.clearButtonIsDisplayed());
    }

    @Test(description = "Filter by individual person type")
    @Description("Perform filter to entities list by type, select - individual person")
    public void t02_filterByIndividualPersonType() {
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        entitiesPage.filterEntitiesTable("filter", "Entity Type", "Individual Person");
        Assert.assertTrue(entitiesPage.entityIconIsDisplayed().equalsIgnoreCase("M12 5.9c1.16 0 2.1.94 2.1 2.1s-.94 2.1-2.1 2.1S9.9 9.16 9.9 8s.94-2.1 2.1-2.1m0 9c2.97 0 6.1 1.46 6.1 2.1v1.1H5.9V17c0-.64 3.13-2.1 6.1-2.1M12 4C9.79 4 8 5.79 8 8s1.79 4 4 4 4-1.79 4-4-1.79-4-4-4zm0 9c-2.67 0-8 1.34-8 4v2c0 .55.45 1 1 1h14c.55 0 1-.45 1-1v-2c0-2.66-5.33-4-8-4z"));
        Assert.assertEquals(entitiesPage.totalEntitiesValueBox(), entitiesPage.sumEntitiesBox());
//        Assert.assertEquals(entitiesPage.totalEntitiesValueBox(), entitiesPage.counterEntitiesList());
        entitiesPage.clearButton();
        Assert.assertFalse(entitiesPage.clearButtonIsDisplayed());
    }

    @Test(description = "Filter by business category")
    @Description("Perform filter to entities list by business category select")
    public void t03_filterByBusinessCategory() {
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        entitiesPage.filterEntitiesTable("filter", "Entity Type", "Organization");
        entitiesPage.filterEntitiesTable("filter", "Business Category", "Broker");
        Assert.assertEquals(entitiesPage.businessfCategory("Broker"), "Broker");
        Assert.assertEquals(entitiesPage.totalEntitiesValueBox(), entitiesPage.sumEntitiesBox());
        Assert.assertEquals(entitiesPage.totalEntitiesValueBox(), entitiesPage.counterEntitiesList());
    }

}