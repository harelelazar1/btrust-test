package btrust.btrustOne.admin.dataModule.entitiesManagement.tests.createBusinessCategory;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.EntitiesManagerPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.BusinessCategoryPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.BusinessRelationshipsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BusinessRelationshipsTest extends BaseAdminUserTest {

    int parentList;
    int childList;
    int summaryList;
    String businessRelationship;

    @BeforeClass
    @Step("Navigate to entities management screen, " +
            "Enter to a parent type business category," +
            "Enter to Business relationships tab" +
            "save the data" +
            "and enter to business category screen")
    public void getDataFromParentBusinessCategory() {
        AdministratorPage administratorPage = new AdministratorPage(driver);
        administratorPage.chooseFromSideBar("Entities Management");
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");

        entitiesManagerPage.chooseOrganizationBusinessCategoryList("Organization");
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        businessCategoryPage.tabList("Business Relationships");
        BusinessRelationshipsPage businessRelationshipsPage = new BusinessRelationshipsPage(driver);
        parentList = businessRelationshipsPage.businessRelationshipsList("child");
        businessRelationship = businessRelationshipsPage.getValueFromBusinessRelationshipsTable("child", "ENTITY TYPE");
        businessCategoryPage.clickOnEntitiesManagementBreadcrumb();
        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");
        entitiesManagerPage.chooseBusinessCategory("yes", "liad");
        Assert.assertEquals(businessCategoryPage.entityName(), "liad");
    }

    @BeforeMethod
    @Step("Navigate between the babs")
    public void navigateBetweenTabs() {
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        businessCategoryPage.tabList("Entity Name");
        Assert.assertEquals(businessCategoryPage.contentTitle(), "Entity Name");
        businessCategoryPage.tabList("Business Relationships");
        businessCategoryPage.contentsList("Organization Business Relationships");
        Assert.assertEquals(businessCategoryPage.contentTitle(), "Business Relationships");
        Assert.assertEquals(businessCategoryPage.contentType(), "Parent");
    }

    @Test(priority = 1, description = "Get data from the parent")
    @Description("Check that child business relationships get data from the parent")
    public void getDataFromParent() {
        BusinessRelationshipsPage businessRelationshipsPage = new BusinessRelationshipsPage(driver);
        Assert.assertEquals(businessRelationshipsPage.businessRelationshipsList("parent"), parentList);
        Assert.assertEquals(businessRelationshipsPage.getValueFromBusinessRelationshipsTable("parent", "ENTITY TYPE"), businessRelationship);
    }

    @Test(priority = 2, description = "Add new business relationship")
    @Description("Enter to child business category" +
            "Navigate to child content type" +
            "Click on Add new business relationship" +
            "Fill data" +
            "and check that added business relationship to the list")
    public void addNewBusinessRelationships() {
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        businessCategoryPage.contentsList("liad Business Relationships");
        businessCategoryPage.clickOnButton("Add new business relationship");
        BusinessRelationshipsPage businessRelationshipsPage = new BusinessRelationshipsPage(driver);
        businessRelationshipsPage.fillBusinessRelationshipData("ENTITY TYPE", "Organization");
        businessRelationshipsPage.fillBusinessRelationshipData("BUSINESS CATEGORY", "Client");
        businessRelationshipsPage.fillBusinessRelationshipData("RELATIONSHIP TYPE", "One to one");
        Assert.assertTrue(businessCategoryPage.clickOnButton("Save"));

        int list = businessRelationshipsPage.businessRelationshipsList("child");
        Assert.assertEquals(list, 1);

        Assert.assertEquals(businessRelationshipsPage.getValueFromBusinessRelationshipsTable("child", "ENTITY TYPE"), "Organization");
        Assert.assertEquals(businessRelationshipsPage.getValueFromBusinessRelationshipsTable("child", "BUSINESS CATEGORY"), "Client");
        Assert.assertEquals(businessRelationshipsPage.getValueFromBusinessRelationshipsTable("child", "RELATIONSHIP TYPE"), "One to one");
    }



    @Test(priority = 4, description = "Remove business relationship")
    @Description("Enter to child content type" +
            "Click on remove button" +
            "and check that the business relationship is not appear in the list")
    public void removeBusinessRelationship() {
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        businessCategoryPage.contentsList("liad Business Relationships");
        BusinessRelationshipsPage businessRelationshipsPage = new BusinessRelationshipsPage(driver);
        businessRelationshipsPage.clickOnRemoveButton("Organization");
        Assert.assertTrue(businessCategoryPage.clickOnButton("Save"));
        businessCategoryPage.contentsList("Organization Business Relationships");
        businessCategoryPage.contentsList("liad Business Relationships");
        int list = businessRelationshipsPage.businessRelationshipsList("child");
        Assert.assertEquals(list, 0);
    }
}