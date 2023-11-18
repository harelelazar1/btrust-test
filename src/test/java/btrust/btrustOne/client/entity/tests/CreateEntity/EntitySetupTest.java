package btrust.btrustOne.client.entity.tests.CreateEntity;

import btrust.btrustOne.client.BaseClientUserTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.entity.pagesObject.CreateEntity.CreateEntityLayoutButtonsPage;
import btrust.btrustOne.client.entity.pagesObject.CreateEntity.EntitySetupPage;
import btrust.btrustOne.client.entity.pagesObject.CreateEntity.OptionalFieldsPage;
import btrust.btrustOne.client.entity.pagesObject.EntitiesPage;
import btrust.btrustOne.client.search.pagesObject.SearchPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EntitySetupTest extends BaseClientUserTest {


    NavigationPage navigationPage;
    SearchPage searchPage;
    EntitySetupPage entitySetupPage;
    CreateEntityLayoutButtonsPage createEntityLayoutButtonsPage;
    EntitiesPage entitiesPage;
    OptionalFieldsPage optionalFieldsPage ;

    @Step("before method for entity setup info tab test in create entity process")
    @BeforeMethod
    public void getToNewEntitySetupPage() {
        navigationPage = new NavigationPage(driver);
        searchPage = new SearchPage(driver);
        entitySetupPage = new EntitySetupPage(driver);
        createEntityLayoutButtonsPage = new CreateEntityLayoutButtonsPage(driver);
        optionalFieldsPage = new OptionalFieldsPage(driver);
        navigationPage.mainMenuList("Search");
        entitiesPage = new EntitiesPage(driver);
        searchPage.searchEntity("Organization", "newEntity" + randomString());
        Assert.assertEquals(searchPage.NoSuitableEntitiesWereMatchedYourSearch(), "No suitable entities were matched your search");
        searchPage.chooseNameInAddEntityMenu("Create entity and workflow");
    }

    @Test(description = "cancel entity creation")
    @Description("cancel entity creation")
    public void t01_cancelCrateEntity() {
        Assert.assertEquals(entitySetupPage.entityInformationTitle(), "Select Entity Type and Category");
        createEntityLayoutButtonsPage.clickOnButton("Cancel");
        Assert.assertEquals(entitySetupPage.cancelEntityPopupTitle(), "Cancelation");
        entitySetupPage.confirmButtonInCancelPopup();

        Assert.assertTrue(entitiesPage.entitiesTitle(), "Entities");
    }

    @Test(description = "back button from non mandatory page")
    @Description("back button from non mandatory page")
    public void t02_backButtonInNonMandatoryPage() {
        Assert.assertEquals(entitySetupPage.entityInformationTitle(), "Select Entity Type and Category");
        entitySetupPage.selectEntityType("Organization");
        entitySetupPage.selectBusinessCategory("Client");
        Assert.assertEquals(entitySetupPage.organizationClientInformationTitle("Organization - Client information"), "Organization - Client information");
        randomString = randomString();
        entitySetupPage.fillMandatoryTypeFieldsForEntity("legal_name", "Entity" + randomString);
        entitySetupPage.fillMandatoryTypeFieldsForEntity("Business Address", "Business Address" + randomString);
        createEntityLayoutButtonsPage.clickOnButton("Continue");

        Assert.assertEquals(optionalFieldsPage.additionalNonMandatoryInformationTitle("Additional non-mandatory information"), "Additional non-mandatory information");
        createEntityLayoutButtonsPage.clickOnButton("Back");
        Assert.assertEquals(entitySetupPage.organizationClientInformationTitle("Organization - Client information"), "Organization - Client information");
    }
}