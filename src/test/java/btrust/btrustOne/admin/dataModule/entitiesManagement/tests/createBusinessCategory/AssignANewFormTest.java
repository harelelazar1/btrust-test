package btrust.btrustOne.admin.dataModule.entitiesManagement.tests.createBusinessCategory;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.EntitiesManagerPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.AssignANewFormPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.BusinessCategoryPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AssignANewFormTest extends BaseAdminUserTest {


    AdministratorPage administratorPage;
    EntitiesManagerPage entitiesManagerPage;
    BusinessCategoryPage businessCategoryPage;
    AssignANewFormPage assignANewFormPage;

    @BeforeClass
    @Step("Enter to business category screen," +
            "Navigate to linked documents tab")
    public void navigateToLinkedDocumentsTab() {
        administratorPage = new AdministratorPage(driver);
        administratorPage.chooseFromSideBar("Entities Management");
        entitiesManagerPage = new EntitiesManagerPage(driver);
        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");
        entitiesManagerPage.chooseBusinessCategory("yes", "liad");
        businessCategoryPage = new BusinessCategoryPage(driver);
        Assert.assertEquals(businessCategoryPage.entityName(), "liad");
        businessCategoryPage.tabList("Linked Documents");
        Assert.assertEquals(businessCategoryPage.contentTitle(), "Linked Documents");
        Assert.assertEquals(businessCategoryPage.contentType(), "Parent");
        businessCategoryPage.contentsList("liad Documents");
        Assert.assertEquals(businessCategoryPage.contentTitle(), "Linked Documents");
        Assert.assertEquals(businessCategoryPage.contentType(), "Child");
        assignANewFormPage = new AssignANewFormPage(driver);
    }

    @BeforeMethod
    @Step("Enter to assign a new form popup")
    public void enterToAssignANewFormPopup() {
        businessCategoryPage.clickOnButton("Assign a new form");
        Assert.assertEquals(assignANewFormPage.title(), "Assign a New Form");
        Assert.assertEquals(assignANewFormPage.subTitle(), "Select the required Document to link to this Entity Type/Business category");
    }

    @Test(priority = 1, description = "Close the assign a new form popup")
    @Description("Click on close button and on cancel button and check that the assign a new form popup is not appear")
    public void closeAssignANewFormPopup() {
        assignANewFormPage.clickOnButton("Cancel");
        Assert.assertFalse(assignANewFormPage.clickOnButton("Cancel"));
        Assert.assertEquals(businessCategoryPage.entityName(), "liad");
        businessCategoryPage.clickOnButton("Assign a new form");
        assignANewFormPage.clickOnCloseButton();
        Assert.assertFalse(assignANewFormPage.clickOnButton("Cancel"));
        Assert.assertEquals(businessCategoryPage.entityName(), "liad");
    }

    @Test(priority = 2, description = "Filter by document name")
    @Description("Filter the documents types by document name and check it's done")
    public void filerByDocumentName() {
        assignANewFormPage.typeInSearchField("GIP");
        Assert.assertTrue(assignANewFormPage.getValueFromTheTable("DOCUMENT NAME").contains("GIP"));
    }

    @Test(priority = 3, description = "Filter the form list by type")
    @Description("Filter the form list by type," +
            "and check that it's done")
    public void filterFormByType() {
        assignANewFormPage.chooseFormType("Informative document");
        Assert.assertEquals(assignANewFormPage.getValueFromTheTable("DOCUMENT TYPE"), "Informative document");
        assignANewFormPage.clickOnButton("Cancel");
        businessCategoryPage.clickOnButton("Assign a new form");
        assignANewFormPage.chooseFormType("Informative document");
        Assert.assertEquals(assignANewFormPage.getValueFromTheTable("DOCUMENT TYPE"), "Informative document");
        assignANewFormPage.clickOnCloseButton();
        businessCategoryPage.clickOnButton("Assign a new form");
        assignANewFormPage.chooseFormType("Informative document");
        Assert.assertEquals(assignANewFormPage.getValueFromTheTable("DOCUMENT TYPE"), "Informative document");
        assignANewFormPage.clickOnCloseButton();
    }
}