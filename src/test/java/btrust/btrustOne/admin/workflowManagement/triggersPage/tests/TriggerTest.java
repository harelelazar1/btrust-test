package btrust.btrustOne.admin.workflowManagement.triggersPage.tests;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject.Permission;
import btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject.RolesAndPermissionPage;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class TriggerTest extends BaseAdminUserTest {

    String triggerName;

    @BeforeClass
    @Step("Administritive tab")
    public void definePermission() {
        AdministratorPage administratorPage = new AdministratorPage(driver);
        administratorPage.openAllSideBarGroups();
        administratorPage.chooseFromSideBar("Roles and permissions");
        RolesAndPermissionPage rolesAndPermissionPage = new RolesAndPermissionPage(driver);
        Assert.assertEquals(rolesAndPermissionPage.rolesAndPermissionsTitle(), "Roles & Permissions");
        rolesAndPermissionPage.fillTextInFieldTypeRoleNameToSearchAndClick("e2eRoleAndPermission");
        Permission permission = new Permission(driver);
        permission.navigatePermissionTab("Administritive");
        permission.clickSelectAllButtonStatus("Select All");
        permission.clickOnButton("Continue");

    }

    @BeforeMethod
    @Step("log off and login to btrust")
    public void logOffAndLoginToBtrusr() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.logOut();
        LoginPage login = new LoginPage(driver);
        login.loginNewForSecurity("harelelazar@gmail.com", "Amitbiton20");
    }

}
