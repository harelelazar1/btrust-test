package btrust.btrustOne.admin.dataModule.entitiesManagement.tests.createBusinessCategory;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.EntitiesManagerPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.BusinessCategoryPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.LinkedDocumentsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinkedDocumentsTest extends BaseAdminUserTest {

    int parentList;
    int childList;
    int summaryList;

    @BeforeClass
    @Step("Navigate to entities management screen, " +
            "Enter to a parent type business category," +
            "Enter to Linked documents tab" +
            "save the data" +
            "and enter to business category screen")
    public void getDataFromParentBusinessCategory() {
        AdministratorPage administratorPage = new AdministratorPage(driver);
        administratorPage.chooseFromSideBar("Entities Management");
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");

        entitiesManagerPage.chooseOrganizationBusinessCategoryList("Organization");
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        businessCategoryPage.tabList("Linked Documents");
        Assert.assertTrue(businessCategoryPage.contentTitle().contains("Linked Documents"));
        Assert.assertEquals(businessCategoryPage.contentType(), "Parent");
        LinkedDocumentsPage linkedDocumentsPage = new LinkedDocumentsPage(driver);
        parentList = linkedDocumentsPage.countLinkedDocuments("parent");

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
        businessCategoryPage.tabList("Linked Documents");
        Assert.assertEquals(businessCategoryPage.contentTitle(), "Linked Documents");
        Assert.assertEquals(businessCategoryPage.contentType(), "Parent");
    }

    @Test(priority = 1, description = "Organization fields")
    @Description("Enter to organization content and check that all the data from the parent appears in the child")
    public void getDataFromParent() {
        LinkedDocumentsPage linkedDocumentsPage = new LinkedDocumentsPage(driver);
        Assert.assertEquals(linkedDocumentsPage.countLinkedDocuments("parent"), parentList);
    }

    @Test(priority = 2, description = "Search field")
    @Description("Navigate between the contents," +
            "type in the search field" +
            "and check the field is exist in the table")
    public void searchField() {
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        businessCategoryPage.contentsList("Organization Documents");
        businessCategoryPage.searchField("w9 irs");
        LinkedDocumentsPage linkedDocumentsPage = new LinkedDocumentsPage(driver);
        Assert.assertEquals(linkedDocumentsPage.getValueFromDataProfileTable(
                "NO"), "99");
        Assert.assertEquals(linkedDocumentsPage.getValueFromDataProfileTable(
                "DOCUMENT NAME"), "W9 IRS");
        Assert.assertEquals(linkedDocumentsPage.getValueFromDataProfileTable(
                "DOCUMENT TYPE"), "Upload request");
        Assert.assertEquals(linkedDocumentsPage.getValueFromDataProfileTable(
                "DOCUMENT STATUS"), "active");

        businessCategoryPage.contentsList("liad Documents");
        businessCategoryPage.searchField("tess");
        Assert.assertEquals(linkedDocumentsPage.getValueFromDataProfileTable(
                "NO"), "297");
        Assert.assertEquals(linkedDocumentsPage.getValueFromDataProfileTable(
                "DOCUMENT NAME"), "Tesssttttt");
        Assert.assertEquals(linkedDocumentsPage.getValueFromDataProfileTable(
                "DOCUMENT TYPE"), "E-Document");
        Assert.assertEquals(linkedDocumentsPage.getValueFromDataProfileTable(
                "DOCUMENT STATUS"), "active");

        businessCategoryPage.contentsList("Summary");
        businessCategoryPage.searchField("upload doc name");
        Assert.assertEquals(linkedDocumentsPage.getValueFromDataProfileTable(
                "NO"), "293");
        Assert.assertEquals(linkedDocumentsPage.getValueFromDataProfileTable(
                "DOCUMENT NAME"), "upload doc name");
        Assert.assertEquals(linkedDocumentsPage.getValueFromDataProfileTable(
                "DOCUMENT TYPE"), "Upload request");
        Assert.assertEquals(linkedDocumentsPage.getValueFromDataProfileTable(
                "DOCUMENT STATUS"), "active");
        Assert.assertEquals(linkedDocumentsPage.getValueFromDataProfileTable(
                "SOURCE"), "Child");
    }

    @Test(priority = 3, description = "Sum fields")
    @Description("Sum all the fields that appear in the content parent and in content child list" +
            "and check that all the fields exist in the content summary")
    public void sumFields() {
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        businessCategoryPage.contentsList("Organization Documents");
        LinkedDocumentsPage linkedDocumentsPage = new LinkedDocumentsPage(driver);
        parentList = linkedDocumentsPage.countLinkedDocuments("parent");
        businessCategoryPage.contentsList("liad Documents");
        childList = linkedDocumentsPage.countLinkedDocuments("child");
        businessCategoryPage.contentsList("Summary");
        Assert.assertEquals(businessCategoryPage.contentType(), "Parent + Child");

        summaryList = linkedDocumentsPage.countLinkedDocuments("summary");
        Assert.assertEquals(parentList + childList, summaryList);
    }
}
