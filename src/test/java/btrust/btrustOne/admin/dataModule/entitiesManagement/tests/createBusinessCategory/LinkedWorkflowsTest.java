package btrust.btrustOne.admin.dataModule.entitiesManagement.tests.createBusinessCategory;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.EntitiesManagerPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.BusinessCategoryPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.LinkedWorkflowsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinkedWorkflowsTest extends BaseAdminUserTest {

    int parentList;

    @BeforeClass
    @Step("Navigate to entities management screen, " +
            "Enter to a parent type business category," +
            "Enter to Linked Workflows tab" +
            "save the data" +
            "and enter to business category screen")
    public void getDataFromParentBusinessCategory() {
        AdministratorPage administratorPage = new AdministratorPage(driver);
        administratorPage.chooseFromSideBar("Entities Management");
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");

        entitiesManagerPage.chooseOrganizationBusinessCategoryList("Organization");
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        businessCategoryPage.tabList("Linked Workflows");
        Assert.assertTrue(businessCategoryPage.contentTitle().contains("Linked Workflows"));
        LinkedWorkflowsPage linkedWorkflowsPage = new LinkedWorkflowsPage(driver);
        parentList = linkedWorkflowsPage.countLinkedWorkflows("parent");

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
        businessCategoryPage.tabList("Linked Workflows");
        Assert.assertEquals(businessCategoryPage.contentTitle(), "Linked Workflows");
        Assert.assertEquals(businessCategoryPage.contentType(), "Parent");
    }

    @Test(priority = 1, description = "Organization fields")
    @Description("Enter to organization content and check that all the data from the parent appears in the child")
    public void getDataFromParent() {
        LinkedWorkflowsPage linkedWorkflowsPage = new LinkedWorkflowsPage(driver);
        Assert.assertEquals(linkedWorkflowsPage.countLinkedWorkflows("parent"), parentList);
    }
}
