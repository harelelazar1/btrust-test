package btrust.btrustOne.admin.dataModule.entitiesManagement.tests.createBusinessCategory;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.EntitiesManagerPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.BusinessCategoryPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.EntityNamePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EntityNameTest extends BaseAdminUserTest {

    int list;
    String entityName;

    @BeforeClass
    @Step("Navigate to entities management screen, " +
            "Enter to a parent type business category," +
            "Enter to Entity Name tab" +
            "save the data" +
            "and enter to business category screen")
    public void getDataFromParentBusinessCategory() {
        AdministratorPage administratorPage = new AdministratorPage(driver);
        administratorPage.chooseFromSideBar("Entities Management");
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");

        entitiesManagerPage.chooseOrganizationBusinessCategoryList("Organization");
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        businessCategoryPage.tabList("Entity Name");
        Assert.assertTrue(businessCategoryPage.contentTitle().contains("Entity Name"));
        EntityNamePage entityNamePage = new EntityNamePage(driver);
        Assert.assertEquals(entityNamePage.contentSubTitle(), "Up to 3 fields from the Parent Entity");

        list = entityNamePage.dataFieldList("parent");
        entityName = entityNamePage.fieldsLst("parent", "FIELDS");

        businessCategoryPage.clickOnEntitiesManagementBreadcrumb();
        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");
        entitiesManagerPage.chooseBusinessCategory("yes", "liad");
        Assert.assertEquals(businessCategoryPage.entityName(), "liad");
    }

    @BeforeMethod
    @Step("Navigate between the babs")
    public void navigateToEntitiesManagementScreen() {
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        businessCategoryPage.tabList("Data Profile");
        businessCategoryPage.contentsList("Organization fields");
        Assert.assertEquals(businessCategoryPage.contentTitle(), "Organization fields");
        businessCategoryPage.tabList("Entity Name");
        Assert.assertEquals(businessCategoryPage.contentTitle(), "Entity Name");
        Assert.assertEquals(businessCategoryPage.contentType(), "Parent");
    }

    @Test(priority = 1, description = "Get data from the parent")
    @Description("Check that child business category get data from the parent")
    public void getDataFromParent() {
        EntityNamePage entityNamePage = new EntityNamePage(driver);
        Assert.assertEquals(entityNamePage.dataFieldList("child"), list);
        Assert.assertEquals(entityNamePage.fieldsLst("child", "FIELDS"), entityName);
    }
}
