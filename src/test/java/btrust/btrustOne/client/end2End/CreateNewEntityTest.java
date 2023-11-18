package btrust.btrustOne.client.end2End;

import btrust.btrustOne.client.BaseClientUserTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.entity.pagesObject.CreateEntity.CreateEntityLayoutButtonsPage;
import btrust.btrustOne.client.entity.pagesObject.CreateEntity.EntitySetupPage;
import btrust.btrustOne.client.entity.pagesObject.EntitiesPage;
import btrust.btrustOne.client.entity.pagesObject.EntityPage;
import btrust.btrustOne.client.search.pagesObject.EntitySearchPage;
import btrust.btrustOne.client.search.pagesObject.SearchPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateNewEntityTest extends BaseClientUserTest {

    NavigationPage navigationPage;
    SearchPage searchPage;
    EntityPage entityPage;
    EntitySetupPage entitySetupPage;
    CreateEntityLayoutButtonsPage createEntityLayoutButtonsPage;
    EntitySearchPage entitySearchPage;
    EntitiesPage entitiesPage;


    @BeforeMethod
    @Step("navigate to the search page")
    public void navigateToSearchPage() {
        navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Cases");
        navigationPage.mainMenuList("Search");
        searchPage = new SearchPage(driver);
        entityPage = new EntityPage(driver);
        entitySetupPage = new EntitySetupPage(driver);
        createEntityLayoutButtonsPage = new CreateEntityLayoutButtonsPage(driver);
        entitySearchPage = new EntitySearchPage(driver);
        navigationPage = new NavigationPage(driver);
        entitiesPage = new EntitiesPage(driver);
        Assert.assertEquals(searchPage.searchTitle(), "Search");
    }

    @Test(description = "create new entity from non existing entity from search bar",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("create new entity from non existing entity from search bar")
    public void t01_createNewEntity() {
        //search not existing entity in the search page
        searchPage.searchEntity("Organization", "newEntity" + randomString());
        Assert.assertEquals(searchPage.NoSuitableEntitiesWereMatchedYourSearch(), "No suitable entities were matched your search");
        searchPage.chooseNameInAddEntityMenu("Create entity and workflow");

        //create entity process
        createEntity();

        Assert.assertEquals(entityPage.identificationData("LEGAL_NAME", "Entity" + randomString), legalNameValue());

    }


    @Test(description = "get to Entity creation page from entity page",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("get to Entity creation page from entity page")
    public void t02_getToEntityCreationPageFromEntitiesPage() {
        //search existing entity in the search page
        searchPage.searchEntity("Organization", "Liad");
        Assert.assertEquals(entitySearchPage.entitySearchTitle(), "Entity Search");
        searchPage.chooseNameInAddEntityMenu("Create entity and workflow");
        Assert.assertEquals(entitySetupPage.entityInformationTitle(), "Select Entity Type and Category");

        //create entity process
        createEntity();

        //get inside the entity and check the inserted data
        Assert.assertEquals(entityPage.identificationData("LEGAL_NAME", "Entity" + randomString), legalNameValue());
    }

    @Test(description = "get to Entity creation page from + button",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("get to Entity creation page from + button")
    public void t03_getToEntityCreationPageFromPlusButton() {
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        searchPage.chooseNameInAddEntityMenu("Create entity and workflow");
        Assert.assertEquals(entitySetupPage.entityInformationTitle(), "Select Entity Type and Category");

        //create entity process
        createEntity();

        Assert.assertEquals(entityPage.identificationData("LEGAL_NAME", "Entity" + randomString), legalNameValue());
    }
}