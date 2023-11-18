package btrust.btrustOne.e2e.userManagment.users;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject.Permission;
import btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject.PermissionsAdministritive;
import btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject.RolesAndPermissionPage;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class UsersTest extends BaseAdminUserTest {

    String userFirstName;
    String userLastName;

    @BeforeClass
    @Step("Edit role")
    public void definePermission() {
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
        permission.navigatePermissionTab("Administritive");
        PermissionsAdministritive permissionsAdministritive = new PermissionsAdministritive(driver);
        permission.clickSelectAllButtonStatus("Select All");
        permission.clickOnButton("Continue");
    }


    @BeforeMethod
    @Step("log off and login to btrust")
    public void logOffAndLoginToBtrusr() {
        driver.navigate().refresh();
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.logOut();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("qatest", "harel.elazar+rolePermission@scanovate.com", "Q1w2e3r4!");
    }


}



