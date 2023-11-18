package btrust.btrustOne.admin.usersManager.rolesAndPermissions.tests;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject.*;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RolesAndPermissionTest extends BaseAdminUserTest {

    String roleName;
    String newRoleName;
    String oldRoleName;
    AdministratorPage administratorPage;
    RolesAndPermissionPage rolesAndPermissionPage;
    NewRolePage newRolePage;
    PermissionsData permissionsData;
    Permission permission;
    PermissionsAdministritive permissionsAdministritive;
    PermissionsDataFiltering permissionsDataFiltering;
    EditRolePage editRolePage;

    @BeforeMethod
    @Step("Enter to roles and permissions screen")
    public void navigateToRolesAndPermissionsScreen() {
        administratorPage = new AdministratorPage(driver);
        administratorPage.chooseFromSideBar("Roles and permissions");
        rolesAndPermissionPage = new RolesAndPermissionPage(driver);
        Assert.assertEquals(rolesAndPermissionPage.rolesAndPermissionsTitle(), "Roles & Permissions");
        newRolePage = new NewRolePage(driver);
        permissionsData = new PermissionsData(driver);
        permission = new Permission(driver);
        permissionsAdministritive = new PermissionsAdministritive(driver);
        permissionsDataFiltering = new PermissionsDataFiltering(driver);
        editRolePage = new EditRolePage(driver);
    }

    @Test(description = "Create new role",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create new role and check if the new role appear in the role list")
    public void Test_01_createNewRole() {
        rolesAndPermissionPage.clickOnAddRolesAndPermissionButton();
        Assert.assertEquals(newRolePage.newRoleTitle(), "New Role");
        newRolePage.confirmRoleTextAndFillText("Role Name", "Qa automations " + randomString());
        roleName = newRolePage.attributeNameFromRoleName();
        permissionsData.chooseCheckboxFromData("Client", "READ");
        permission.clickOnButton("Continue");
        rolesAndPermissionPage.fillTextInFieldTypeRoleNameToSearch(roleName);
        Assert.assertTrue(rolesAndPermissionPage.searchRoleNameInRoleList(roleName));
        Assert.assertEquals(rolesAndPermissionPage.roleList(roleName), roleName);
    }

    @Test(description = "Cancel process of create new role",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Start the process of Create new role and click cancel button confirm the role name not appear in the role list")
    public void Test_02_cancelProcessOfCreateNewRole() {
        rolesAndPermissionPage.clickOnAddRolesAndPermissionButton();
        Assert.assertEquals(newRolePage.newRoleTitle(), "New Role");
        newRolePage.confirmRoleTextAndFillText("Role Name", "Qa automations " + randomString());
        roleName = newRolePage.attributeNameFromRoleName();
        permissionsData.chooseCheckboxFromData("Client", "READ");
        permission.clickOnButton("Cancel");
        Assert.assertTrue(rolesAndPermissionPage.searchRoleNameInRoleList(roleName));
    }

    @Test(description = "Permissiom data mark checkbox fields",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("New role screen > data tab > mark checkbox field and check if the relevant fields checked")
    public void Test_03_permissiomDataMarkCheckboxFields() {
        rolesAndPermissionPage.clickOnAddRolesAndPermissionButton();
        Assert.assertEquals(newRolePage.newRoleTitle(), "New Role");
        newRolePage.confirmRoleTextAndFillText("Role Name", "Qa automations " + randomString());
        permissionsData.chooseCheckboxFromData("Client", "READ");
        Assert.assertTrue(permissionsData.dataCheckboxIsMark("Client", "READ"));
        Assert.assertFalse(permissionsData.dataCheckboxIsMark("Client", "EDIT"));
        Assert.assertFalse(permissionsData.dataCheckboxIsMark("Client", "CREATE"));
        permission.clickSelectAllButton();
        permission.clickSelectAllButton();
        permissionsData.chooseCheckboxFromData("Client", "EDIT");
        Assert.assertTrue(permissionsData.dataCheckboxIsMark("Client", "READ"));
        Assert.assertTrue(permissionsData.dataCheckboxIsMark("Client", "EDIT"));
        Assert.assertFalse(permissionsData.dataCheckboxIsMark("Client", "CREATE"));
        permission.clickSelectAllButton();
        permission.clickSelectAllButton();
        permissionsData.chooseCheckboxFromData("Client", "CREATE");
        Assert.assertTrue(permissionsData.dataCheckboxIsMark("Client", "READ"));
        Assert.assertTrue(permissionsData.dataCheckboxIsMark("Client", "EDIT"));
        Assert.assertTrue(permissionsData.dataCheckboxIsMark("Client", "CREATE"));
        permissionsData.chooseCheckboxFromData("Client", "READ");
        Assert.assertFalse(permissionsData.dataCheckboxIsMark("Client", "READ"));
        Assert.assertFalse(permissionsData.dataCheckboxIsMark("Client", "EDIT"));
        Assert.assertFalse(permissionsData.dataCheckboxIsMark("Client", "CREATE"));
        permission.clickOnButton("Cancel");

    }

    @Test(description = "Permissiom administritive mark checkbox fields",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("New role screen > administritive tab > mark checkbox field and check if the relevant fields checked")
    public void Test_04_permissiomAdministritiveMarkCheckboxFields() {
        rolesAndPermissionPage.clickOnAddRolesAndPermissionButton();
        Assert.assertEquals(newRolePage.newRoleTitle(), "New Role");
        newRolePage.confirmRoleTextAndFillText("Role Name", "Qa automations " + randomString());
        permission.navigatePermissionTab("Administritive");
        permissionsAdministritive.chooseCheckboxFromAdministritive("Entities Manager", "READ");
        Assert.assertTrue(permissionsAdministritive.administritiveCheckboxIsMark("Entities Manager", "READ"));
        Assert.assertFalse(permissionsAdministritive.administritiveCheckboxIsMark("Entities Manager", "EDIT & CREATE"));
        permission.clickSelectAllButton();
        permission.clickSelectAllButton();
        permissionsAdministritive.chooseCheckboxFromAdministritive("Entities Manager", "EDIT & CREATE");
        Assert.assertTrue(permissionsAdministritive.administritiveCheckboxIsMark("Entities Manager", "READ"));
        Assert.assertTrue(permissionsAdministritive.administritiveCheckboxIsMark("Entities Manager", "EDIT & CREATE"));
        permissionsAdministritive.chooseCheckboxFromAdministritive("Entities Manager", "READ");
        Assert.assertFalse(permissionsAdministritive.administritiveCheckboxIsMark("Entities Manager", "READ"));
        Assert.assertFalse(permissionsAdministritive.administritiveCheckboxIsMark("Entities Manager", "EDIT & CREATE"));
        permission.clickOnButton("Cancel");
    }

    @Test(description = "Permissiom data filtering tab select from list",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("New role screen > data filtering > select from list")
    public void Test_05_permissiomDataFilteringTabSelectFromList() {
        rolesAndPermissionPage.clickOnAddRolesAndPermissionButton();
        Assert.assertEquals(newRolePage.newRoleTitle(), "New Role");
        newRolePage.confirmRoleTextAndFillText("Role Name", "Qa automations " + randomString());
        roleName = newRolePage.attributeNameFromRoleName();
        permissionsData.chooseCheckboxFromData("Client", "READ");
        Assert.assertTrue(permissionsData.dataCheckboxIsMark("Client", "READ"));
        permission.navigatePermissionTab("Data Filtering");
        permissionsDataFiltering.selectFromList("Client");
        permissionsDataFiltering.selectFromList("Relationship Manager");
        permissionsDataFiltering.selectFromList("Is");
        newRolePage.insertNameToField("GIP");
        permission.clickOnButton("Continue");
        Assert.assertEquals(rolesAndPermissionPage.rolesAndPermissionsTitle(), "Roles & Permissions");
        rolesAndPermissionPage.clickRoleNameFromRoleList(roleName);
        permissionsData.dataCheckboxIsMark("Client", "READ");
        permission.navigatePermissionTab("Data Filtering");
        permissionsDataFiltering.selectFromList("Bank");
        permissionsDataFiltering.selectFromList("Legal Name");
        permissionsDataFiltering.selectFromList("Is");
        newRolePage.insertNameToField("Test");
        permission.clickOnButton("Continue");
    }

    @Test(description = "Edit role name",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Edit exist role name and check the new role name")
    public void Test_06_editRoleName() {
        oldRoleName = rolesAndPermissionPage.clickFirstRoleInTheScreen();
        newRoleName = editRolePage.editRoleNameField("Qa automations " + randomString());
        editRolePage.linkBack();
        Assert.assertTrue(rolesAndPermissionPage.searchRoleNameInRoleList(newRoleName));
    }


    @Test(description = "Permission data mark checkbox fields",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Edit role screen > data tab > mark checkbox field and check if the relevant fields checked")
    public void Test_07_permissionDataMarkCheckboxFields() {
        rolesAndPermissionPage.clickFirstRoleInTheScreen();
        permission.clickSelectAllButton();
        permission.clickSelectAllButton();
        permissionsData.chooseCheckboxFromData("Broker", "READ");
        Assert.assertTrue(permissionsData.dataCheckboxIsMark("Broker", "READ"));
        Assert.assertFalse(permissionsData.dataCheckboxIsMark("Broker", "EDIT"));
        Assert.assertFalse(permissionsData.dataCheckboxIsMark("Broker", "CREATE"));
        permission.clickSelectAllButton();
        permission.clickSelectAllButton();
        permissionsData.chooseCheckboxFromData("Broker", "EDIT");
        Assert.assertTrue(permissionsData.dataCheckboxIsMark("Broker", "READ"));
        Assert.assertTrue(permissionsData.dataCheckboxIsMark("Broker", "EDIT"));
        Assert.assertFalse(permissionsData.dataCheckboxIsMark("Broker", "CREATE"));
        permission.clickSelectAllButton();
        permission.clickSelectAllButton();
        permissionsData.chooseCheckboxFromData("Broker", "CREATE");
        Assert.assertTrue(permissionsData.dataCheckboxIsMark("Broker", "READ"));
        Assert.assertTrue(permissionsData.dataCheckboxIsMark("Broker", "EDIT"));
        Assert.assertTrue(permissionsData.dataCheckboxIsMark("Broker", "CREATE"));
        permissionsData.chooseCheckboxFromData("Broker", "READ");
        Assert.assertFalse(permissionsData.dataCheckboxIsMark("Broker", "READ"));
        Assert.assertFalse(permissionsData.dataCheckboxIsMark("Broker", "EDIT"));
        Assert.assertFalse(permissionsData.dataCheckboxIsMark("Broker", "CREATE"));
    }


    @Test(description = "Permission administrtive mark checkbox fields",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Edit role screen > administrtive tab > mark checkbox field and check if the relevant fields checked")
    public void Test_08_permissionAdministrtiveMarkCheckboxFields() {
        rolesAndPermissionPage.clickFirstRoleInTheScreen();
        permission.navigatePermissionTab("Administritive");
        permission.clickSelectAllButton();
        permission.clickSelectAllButton();
        permissionsAdministritive.chooseCheckboxFromAdministritive("Data Mapper", "READ");
        Assert.assertTrue(permissionsAdministritive.administritiveCheckboxIsMark("Data Mapper", "READ"));
        Assert.assertFalse(permissionsAdministritive.administritiveCheckboxIsMark("Data Mapper", "EDIT & CREATE"));
        permission.clickSelectAllButton();
        permission.clickSelectAllButton();
        permissionsAdministritive.chooseCheckboxFromAdministritive("Data Mapper", "EDIT & CREATE");
        Assert.assertTrue(permissionsAdministritive.administritiveCheckboxIsMark("Data Mapper", "READ"));
        Assert.assertTrue(permissionsAdministritive.administritiveCheckboxIsMark("Data Mapper", "EDIT & CREATE"));
        permissionsAdministritive.chooseCheckboxFromAdministritive("Data Mapper", "READ");
        Assert.assertFalse(permissionsAdministritive.administritiveCheckboxIsMark("Data Mapper", "READ"));
        Assert.assertFalse(permissionsAdministritive.administritiveCheckboxIsMark("Data Mapper", "EDIT & CREATE"));
    }

    @Test(description = "Edit role page > check cancel button",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Edit role page check mark checkbox fields and cancel - confirm  check box empty")
    public void Test_09_editRolePageCheckCancelButton() {
        rolesAndPermissionPage.clickOnAddRolesAndPermissionButton();
        Assert.assertEquals(newRolePage.newRoleTitle(), "New Role");
        newRolePage.confirmRoleTextAndFillText("Role Name", "Qa automations " + randomString());
        roleName = newRolePage.attributeNameFromRoleName();
        permissionsData.chooseCheckboxFromData("Client", "READ");
        permission.navigatePermissionTab("Administritive");
        permissionsAdministritive.chooseCheckboxFromAdministritive("Data Mapper", "READ");
        permission.clickOnButton("Continue");
        rolesAndPermissionPage.fillTextInFieldTypeRoleNameToSearch(roleName);
        Assert.assertEquals(rolesAndPermissionPage.roleList(roleName), roleName);
        rolesAndPermissionPage.clickRoleNameFromRoleList(roleName);
        permissionsData.chooseCheckboxFromData("Client", "EDIT");
        permission.navigatePermissionTab("Administritive");
        permissionsAdministritive.chooseCheckboxFromAdministritive("Data Mapper", "EDIT & CREATE");
        Assert.assertTrue(permissionsAdministritive.administritiveCheckboxIsMark("Data Mapper", "READ"));
        Assert.assertTrue(permissionsAdministritive.administritiveCheckboxIsMark("Data Mapper", "EDIT & CREATE"));
        permission.clickOnButton("Cancel");
        Assert.assertTrue(permissionsAdministritive.administritiveCheckboxIsMark("Data Mapper", "READ"));
        Assert.assertFalse(permissionsAdministritive.administritiveCheckboxIsMark("Data Mapper", "EDIT & CREATE"));
        permission.navigatePermissionTab("Data");
        Assert.assertFalse(permissionsData.dataCheckboxIsMark("Client", "EDIT"));
        Assert.assertTrue(permissionsData.dataCheckboxIsMark("Client", "READ"));
    }

}