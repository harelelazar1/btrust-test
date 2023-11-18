package btrust.btrustOne.admin.dataModule.entitiesManagement.tests;


import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.EntitiesManagerPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.BusinessCategoryPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.PopupPage;
import btrust.btrustOne.admin.usersManager.users.pageObject.UsersPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EntitiesManagementTest extends BaseAdminUserTest {

    AdministratorPage administratorPage;
    EntitiesManagerPage entitiesManagerPage;
    UsersPage usersPage;
    BusinessCategoryPage businessCategoryPage;
    PopupPage popupPage;


    @BeforeClass
    @Step("Navigate to entities management screen, " +
            "Enter to a parent type business category," +
            "Navigate between all the tabs and save the data in variables")
    public void getDataFromParentBusinessCategory() {
        AdministratorPage administratorPage = new AdministratorPage(driver);
        administratorPage.chooseFromSideBar("Entities Management");
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");

        entitiesManagerPage.chooseOrganizationBusinessCategoryList("Organization");
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        businessCategoryPage.clickOnEntitiesManagementBreadcrumb();
        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");
    }

    @BeforeMethod
    @Step("Navigate to entities management screen")
    public void navigateToEntitiesManagementPage() {
        administratorPage = new AdministratorPage(driver);
        administratorPage.openAllSideBarGroups();
        entitiesManagerPage = new EntitiesManagerPage(driver);
        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");
        usersPage = new UsersPage(driver);
        businessCategoryPage = new BusinessCategoryPage(driver);
        popupPage = new PopupPage(driver);

    }

    @Test(priority = 1, description = "Create new business category")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create new business category," +
            "add data and check that the business category is appear is the list")
    public void test01_createNewBusinessCategory() {
        entitiesManagerPage.clickOnAddNewBusinessCategoryButton("Organization");
        entitiesManagerPage.newEntityInput("QA automation " + randomString());
//        administratorPage.chooseFromSideBar("Users");
//        Assert.assertEquals(usersPage.usersTitle(), "Users");
        administratorPage.chooseFromSideBar("Entities Management");
        entitiesManagerPage.chooseBusinessCategory("yes", "QA automation");
        businessCategoryPage.clickOnEntitiesManagementBreadcrumb();
        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");
    }

    @Test(priority = 2, description = "Edit entity")
    @Description("Enter to entity that the user created in the previous test and edit the entity")
    public void test02_editEntity() {
        entitiesManagerPage.chooseBusinessCategory("yes", "QA automation");
        String before = businessCategoryPage.entityName();
        businessCategoryPage.clickOnEditBusinessCategoryButton();
        businessCategoryPage.closeEditBusinessCategoryName();
        Assert.assertEquals(before, businessCategoryPage.entityName());

        businessCategoryPage.clickOnEditBusinessCategoryButton();
        businessCategoryPage.renameBusinessCategoryName("save button", "QA automation edit name save button");
        Assert.assertEquals(businessCategoryPage.entityName(), "QA automation edit name save button");

        businessCategoryPage.clickOnEntitiesManagementBreadcrumb();
        entitiesManagerPage.chooseBusinessCategory("yes", "QA automation edit name save button");
        businessCategoryPage.clickOnEditBusinessCategoryButton();
        businessCategoryPage.renameBusinessCategoryName("enter", "QA automation edit name enter button");
        Assert.assertEquals(businessCategoryPage.entityName(), "QA automation edit name enter button");

        businessCategoryPage.clickOnEntitiesManagementBreadcrumb();

        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");
    }

    @Test(priority = 3, description = "Delete business category")
    @Description("Enter to business category from the list," +
            "click on hamburger menu," +
            "click on delete button," +
            "and check that the business category is not appear in the list")
    public void test03_deleteBusinessCategory() {
        entitiesManagerPage.chooseBusinessCategory("yes", "QA automation edit name enter button");

        Assert.assertEquals(businessCategoryPage.entityName(), "QA automation edit name enter button");
        businessCategoryPage.businessCategoryMenu("Delete");

        Assert.assertEquals(popupPage.title(), "Delete Warning!");
        Assert.assertTrue(popupPage.popupIconIsDisplayed());
        Assert.assertEquals(popupPage.popupTitle(), "All fields must be deleted before deleting this entity type.");
        Assert.assertEquals(popupPage.popupDescription(), "Continue to remove all fields and delete this entity type.");
        Assert.assertEquals(popupPage.checkboxText(), "I'm sure i want to delete the entity and all fields");
        Assert.assertFalse(popupPage.checkBoxIsSelected());
        Assert.assertFalse(popupPage.clickOnButton("yes", "Remove entity type and all it’s fields"));

        popupPage.clickOnButton("no", "Cancel");
        Assert.assertEquals(businessCategoryPage.entityName(), "QA automation edit name enter button");
        businessCategoryPage.businessCategoryMenu("Delete");
        popupPage.closePopup();

        Assert.assertEquals(businessCategoryPage.entityName(), "QA automation edit name enter button");
        businessCategoryPage.businessCategoryMenu("Delete");

        popupPage.checkThePopupCheckbox();
        popupPage.clickOnButton("yes", "Remove entity type and all it’s fields");
        businessCategoryPage.clickLinkBack();

        Assert.assertFalse(entitiesManagerPage.chooseBusinessCategory("no", "QA automation edit name enter button"));
    }

    @Test(priority = 4, description = "Delete entity that is in use on the system")
    @Description("Enter to entity that is in use on the system" +
            "Click to delete the entity" +
            "and check that appear popup the the user can not delete the entity")
    public void test04_deleteEntityInUseOnTheSystem() {
        entitiesManagerPage.chooseBusinessCategory("yes", "Nitzan Entity");

        Assert.assertEquals(businessCategoryPage.entityName(), "Nitzan Entity");
        businessCategoryPage.businessCategoryMenu("Delete");

        Assert.assertEquals(popupPage.title(), "Delete Warning!");
        Assert.assertTrue(popupPage.popupIconIsDisplayed());
        Assert.assertEquals(popupPage.popupTitle(), "All fields must be deleted before deleting this entity type.");
        Assert.assertEquals(popupPage.popupDescription(), "Continue to remove all fields and delete this entity type.");
        Assert.assertEquals(popupPage.checkboxText(), "I'm sure i want to delete the entity and all fields");
        Assert.assertFalse(popupPage.checkBoxIsSelected());
        Assert.assertFalse(popupPage.clickOnButton("yes", "Remove entity type and all it’s fields"));
        popupPage.checkThePopupCheckbox();
        popupPage.clickOnButton("no", "Remove entity type and all it’s fields");

        Assert.assertEquals(popupPage.title(), "Delete Restricted");
        Assert.assertTrue(popupPage.leavingPopupIconIsDisplayed());
        Assert.assertEquals(popupPage.leavingPopupTitle(), "This entity type is used in the system and therefor cannot be deleted.");
        Assert.assertEquals(popupPage.leavingPopupDescription(), "Please remove all connections to the fields under this entity type before trying to remove it.");

        popupPage.goBackButton();

        Assert.assertFalse(popupPage.popupIconIsDisplayed());
        Assert.assertFalse(popupPage.leavingPopupIconIsDisplayed());
        Assert.assertEquals(businessCategoryPage.entityName(), "Nitzan Entity");
    }
}