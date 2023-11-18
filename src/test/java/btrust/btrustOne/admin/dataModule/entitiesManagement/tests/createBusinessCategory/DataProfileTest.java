package btrust.btrustOne.admin.dataModule.entitiesManagement.tests.createBusinessCategory;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.EntitiesManagerPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.BusinessCategoryPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.DataProfilePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DataProfileTest extends BaseAdminUserTest {

    int parentList;
    int childList;
    int summaryList;
    String dataField;

    @BeforeClass
    @Step("Navigate to entities management screen, " +
            "Enter to a parent type business category," +
            "Enter to Data profile tab" +
            "save the data" +
            "and enter to business category screen")
    public void getDataFromParentBusinessCategory() {
        AdministratorPage administratorPage = new AdministratorPage(driver);
        administratorPage.chooseFromSideBar("Entities Management");
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");

        entitiesManagerPage.chooseOrganizationBusinessCategoryList("Organization");
        DataProfilePage dataProfilePage = new DataProfilePage(driver);
        parentList = dataProfilePage.dataFieldList("child");
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);

        businessCategoryPage.clickOnEntitiesManagementBreadcrumb();
        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");
        entitiesManagerPage.chooseBusinessCategory("yes", "liad");
        Assert.assertEquals(businessCategoryPage.entityName(), "liad");
    }

    @BeforeMethod
    @Step("Navigate between the babs")
    public void navigateToEntitiesManagementScreen() {
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        businessCategoryPage.tabList("Entity Name");
        Assert.assertEquals(businessCategoryPage.contentTitle(), "Entity Name");
        businessCategoryPage.tabList("Data Profile");
        businessCategoryPage.contentsList("Organization fields");
        Assert.assertEquals(businessCategoryPage.contentTitle(), "Organization fields");
    }

    @Test(priority = 1, description = "Organization fields")
    @Description("Enter to organization content and check that all the data from the parent appears in the child")
    public void organizationFields() {
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        businessCategoryPage.contentsList("Organization fields");
        Assert.assertEquals(businessCategoryPage.contentType(), "Parent");
        DataProfilePage dataProfilePage = new DataProfilePage(driver);

        Assert.assertEquals(parentList, dataProfilePage.dataFieldList("parent"));
    }

    @Test(priority = 2, description = "Add new filed")
    @Description("Enter to child fields content," +
            "add new filed," +
            "and check that its added to the list")
    public void addNewField() {
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        businessCategoryPage.contentsList("liad fields");
        Assert.assertEquals(businessCategoryPage.contentTitle(), "liad fields");
        Assert.assertEquals(businessCategoryPage.contentType(), "Child");
        businessCategoryPage.clickOnButton("Add New Field");
        DataProfilePage dataProfilePage = new DataProfilePage(driver);
        dataProfilePage.fillNewFieldDetails(
                "Automation test",
                "Short text",
                "automation field label",
                "yes",
                "qa error message");
        Assert.assertTrue(businessCategoryPage.clickOnButton("Save"));

        businessCategoryPage.tabList("Entity Name");
        Assert.assertEquals(businessCategoryPage.contentTitle(), "Entity Name");
        businessCategoryPage.tabList("Data Profile");
        Assert.assertEquals(businessCategoryPage.contentTitle(), "Organization fields");
        businessCategoryPage.contentsList("liad fields");
        Assert.assertEquals(businessCategoryPage.contentTitle(), "liad fields");
        dataField = dataProfilePage.getValueFromDataProfileTable(
                "child", "DATA FIELD");
        businessCategoryPage.searchField(dataField);

        Assert.assertEquals(dataProfilePage.getValueFromDataProfileTable(
                "child", "DATA FIELD"), dataField);
    }

    @Test(priority = 3, description = "Search field")
    @Description("Navigate between the contents," +
            "type in the search field" +
            "and check the field is exist in the table")
    public void searchField() {
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        businessCategoryPage.contentsList("Organization fields");
        businessCategoryPage.searchField("vat_code");
        DataProfilePage dataProfilePage = new DataProfilePage(driver);
        Assert.assertEquals(dataProfilePage.getValueFromDataProfileTable(
                "parent", "DATA FIELD"), "vat_code");
        Assert.assertEquals(dataProfilePage.getValueFromDataProfileTable(
                "parent", "DATA TYPE"), "ShortText");
        Assert.assertEquals(dataProfilePage.getValueFromDataProfileTable(
                "parent", "FIELD LABEL"), "VAT Code Tax Identification Number");

        businessCategoryPage.contentsList("liad fields");
        businessCategoryPage.searchField("Automation test");
        Assert.assertEquals(dataProfilePage.getValueFromDataProfileTable(
                "child", "DATA FIELD"), "Automation test");

        businessCategoryPage.contentsList("Summary");
        businessCategoryPage.searchField("lei_code");
        Assert.assertEquals(dataProfilePage.getValueFromDataProfileTable(
                "summary", "DATA FIELD"), "lei_code");
        Assert.assertEquals(dataProfilePage.getValueFromDataProfileTable(
                "summary", "DATA TYPE"), "ShortText");
        Assert.assertEquals(dataProfilePage.getValueFromDataProfileTable(
                "summary", "FIELD LABEL"), "LEI code");
        Assert.assertEquals(dataProfilePage.getValueFromDataProfileTable(
                "summary", "SOURCE"), "parent");

        businessCategoryPage.contentsList("Summary");
        businessCategoryPage.searchField("Automation test");
        Assert.assertEquals(dataProfilePage.getValueFromDataProfileTable(
                "summary", "DATA FIELD"), "Automation test");
        Assert.assertEquals(dataProfilePage.getValueFromDataProfileTable(
                "summary", "DATA TYPE"), "ShortText");
        Assert.assertEquals(dataProfilePage.getValueFromDataProfileTable(
                "summary", "FIELD LABEL"), "automation field label");
        Assert.assertEquals(dataProfilePage.getValueFromDataProfileTable(
                "summary", "SOURCE"), "child");
    }

//    @Test(priority = 4, description = "Sum fields")
//    @Description("Sum all the fields that appear in the content parent and in content child list" +
//            "and check that all the fields exist in the content summary")
//    public void sumFields() {
//        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
//        businessCategoryPage.contentsList("Organization fields");
//        DataProfilePage dataProfilePage = new DataProfilePage(driver);
//        parentList = dataProfilePage.dataFieldList("parent");
//        businessCategoryPage.contentsList("liad fields");
//        childList = dataProfilePage.dataFieldList("child");
//        businessCategoryPage.contentsList("Summary");
//        Assert.assertEquals(businessCategoryPage.contentType(), "Parent + Child");
//
//        summaryList = dataProfilePage.dataFieldList("summary");
//        Assert.assertEquals(parentList + childList, summaryList);
//    }
    
}