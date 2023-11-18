package btrust.btrustOne.e2e.userManagment.rolesAndPermission.administritive;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject.Permission;
import btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject.PermissionsAdministritive;
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

    AdministratorPage administratorPage;

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
        permission.navigatePermissionTab("Administritive");
        PermissionsAdministritive permissionsAdministritive = new PermissionsAdministritive(driver);
        permission.clickSelectAllButtonStatus("Select All");
        permissionsAdministritive.chooseCheckboxFromAdministritive("Entities Manager", "READ");
        permissionsAdministritive.chooseCheckboxFromAdministritive("Documents Manager", "READ");
        permissionsAdministritive.chooseCheckboxFromAdministritive("Trigger Builder", "READ");
        permissionsAdministritive.chooseCheckboxFromAdministritive("Departments", "READ");
        permission.clickOnButton("Continue");

    }

    @BeforeMethod
    @Step("log off and login to btrust")
    public void logOffAndLoginToBtrusr() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.logOut();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("qatest", "harel.elazar+rolePermission@scanovate.com", "Q1w2e3r4!");
        administratorPage = new AdministratorPage(driver);
        administratorPage.openAllSideBarGroups();
    }

    @Test(description = "No permission - check the names that display in the sidebar as administrator")
    @Description("Check sidebar with user with no permission to one or more of the modules in the company administrator")
    public void Test_01_checkDisplayModulesInTheSidebar() {
        Assert.assertFalse(administratorPage.checkIfNewDocumentButtonDisplay("Entities Management"));
        Assert.assertFalse(administratorPage.checkIfNewDocumentButtonDisplay("Document Management"));
        Assert.assertFalse(administratorPage.checkIfNewDocumentButtonDisplay("Triggers"));
        Assert.assertFalse(administratorPage.checkIfNewDocumentButtonDisplay("Departments"));
    }

}
