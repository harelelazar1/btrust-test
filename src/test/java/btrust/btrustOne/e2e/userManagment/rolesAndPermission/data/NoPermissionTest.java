package btrust.btrustOne.e2e.userManagment.rolesAndPermission.data;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject.Permission;
import btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject.PermissionsData;
import btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject.RolesAndPermissionPage;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NoPermissionTest extends BaseAdminUserTest {

    String roleName;

    @Override
    @BeforeClass
    @Step("Administritive tab")
    public void loginToAdminUser() {
        driver.get("https://qa-nginx-console.scanovateoncloud.com/");
        LoginPage login = new LoginPage(driver);
        login.login("qatest", "harel.elazar@scanovate.com", "Scan123!");
        AdministratorPage administratorPage = new AdministratorPage(driver);
        Assert.assertEquals(administratorPage.administratorTitle(), "Administrator");
        administratorPage.openAllSideBarGroups();
        administratorPage.chooseFromSideBar("Roles and permissions");
        RolesAndPermissionPage rolesAndPermissionPage = new RolesAndPermissionPage(driver);
        Assert.assertEquals(rolesAndPermissionPage.rolesAndPermissionsTitle(), "Roles & Permissions");
        rolesAndPermissionPage.fillTextInFieldTypeRoleNameToSearchAndClick("e2eRoleAndPermission");
        Permission permission = new Permission(driver);
        permission.clickDeselectAllButtonStatus("Select All");
        PermissionsData permissionsData = new PermissionsData(driver);
        permission.clickOnButton("Continue");
    }

    @BeforeMethod
    @Step("log off and login to btrust")
    public void logOffAndLoginToBtrusr() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.logOut();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("qatest", "harel.elazar+rolePermission@scanovate.com", "Q1w2e3r4!");
        navigationPage.clickOnBackToBtrustUserButton();
    }


    @Test(enabled = true, description = "check role with no permission")
    @Description("Create new role and check if the new role appear in the role list")
    public void Test_01_checkTabsForUserWithNoPermission() {
        NavigationPage navigationPage = new NavigationPage(driver);
        Assert.assertFalse(navigationPage.mainMenuListNotEqual("Search"));
        Assert.assertFalse(navigationPage.mainMenuListNotEqual("Cases"));
        Assert.assertFalse(navigationPage.mainMenuListNotEqual("Entities"));
        Assert.assertFalse(navigationPage.mainMenuListNotEqual("Triggers"));
    }


}






