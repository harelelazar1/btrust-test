package btrust.btrustOne.client.search.tests;

import btrust.btrustOne.client.BaseClientUserTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.search.pagesObject.EntitySearchPage;
import btrust.btrustOne.client.search.pagesObject.SearchPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest extends BaseClientUserTest {

    @BeforeMethod
    public void navigate() {
        NavigationPage navigationPage = new NavigationPage(driver);
//        navigationPage.mainMenuList("Cases");
        navigationPage.mainMenuList("Search");
        SearchPage searchPage = new SearchPage(driver);
        Assert.assertEquals(searchPage.searchTitle(), "Search");
    }

    @Test(description = "Search entity")
    @Description("Perform search entity")
    public void t01_searchEntity() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchEntity("Organization", "Liad");
        EntitySearchPage entitySearchPage = new EntitySearchPage(driver);
        Assert.assertEquals(entitySearchPage.entitySearchTitle(), "Entity Search");
        Assert.assertEquals(entitySearchPage.resultTableTitle(), "Existing Cases/Entities");
        Assert.assertEquals(entitySearchPage.resultTableDescription(), "Might this be the entity that you looked for?");
        Assert.assertTrue(entitySearchPage.entitiesName("Liad").contains("Liad"));
    }

    @Test(description = "No result")
    @Description("Search entity that no exist in the system and check that error message is appear")
    public void t02_noResult() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchEntity("Organization", "blablalba");
        EntitySearchPage entitySearchPage = new EntitySearchPage(driver);
        Assert.assertTrue(entitySearchPage.noResultIcon());
        Assert.assertEquals(entitySearchPage.noResultMessage(), "No suitable entities were matched your searchblablalba");
        Assert.assertTrue(entitySearchPage.noResultCreateNewEntityButton());
        Assert.assertEquals(entitySearchPage.noResultDescription(), "Or try modifying your search criteria");
    }

    @Test(description = "Search button is disabled")
    @Description("Type 1 characters in search field and check that search button is disabled")
    public void t04_searchButtonIsDisabled() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchEntity("Organization", "a");
        Assert.assertFalse(searchPage.searchButtonIsEnabled());
        searchPage.searchEntity("Organization", "Liad");
        EntitySearchPage entitySearchPage = new EntitySearchPage(driver);
        Assert.assertEquals(entitySearchPage.entitySearchTitle(), "Entity Search");
        searchPage.searchEntity("Organization", "a");
        Assert.assertFalse(searchPage.searchButtonIsEnabled());
    }
}