package btrust.btrustOne.e2e.userManagment.rolesAndPermission.data;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject.Permission;
import btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject.PermissionsData;
import btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject.RolesAndPermissionPage;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.cases.pagesObject.CasePage;
import btrust.btrustOne.client.entity.pagesObject.EntitiesPage;
import btrust.btrustOne.client.entity.pagesObject.EntityPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import btrust.btrustOne.client.mandate.pagesObject.MandatePage;
import btrust.btrustOne.client.mandate.pagesObject.MandatesPage;
import btrust.btrustOne.client.search.pagesObject.EntitySearchPage;
import btrust.btrustOne.client.search.pagesObject.SearchPage;
import btrust.btrustOne.client.triggers.pagesObject.PendingTriggerPage;
import btrust.btrustOne.client.triggers.pagesObject.TriggerPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ReadOnlyTest extends BaseAdminUserTest {

    String roleName;
    NavigationPage navigationPage;
    EntitiesPage entitiesPage;
    EntitySearchPage entitySearchPage;
    SearchPage searchPage;
    EntityPage entityPage;
    CasePage casePage;
    TriggerPage triggersPage;
    MandatesPage mandatesPage;
    LoginPage loginPage;
    MandatePage mandatePage;
    PendingTriggerPage pendingTriggerPage;

    @Override
    @BeforeClass
    @Step("Edit role")
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
        permission.clickSelectAllButton();
        permission.clickSelectAllButton();
        PermissionsData permissionsData = new PermissionsData(driver);
        permissionsData.chooseCheckboxFromData("Client", "READ");
        permissionsData.chooseCheckboxFromData("Executor", "READ");
        permissionsData.chooseCheckboxFromDataMandate("mandate", "READ");
        permission.clickOnButton("Continue");

    }

    @BeforeMethod
    @Step("log off and login to btrust")
    public void logOffAndLoginToBtrusr() {
        navigationPage = new NavigationPage(driver);
        entitiesPage = new EntitiesPage(driver);
        pendingTriggerPage = new PendingTriggerPage(driver);
        entitySearchPage = new EntitySearchPage(driver);
        searchPage = new SearchPage(driver);
        entityPage = new EntityPage(driver);
        casePage = new CasePage(driver);
        triggersPage = new TriggerPage(driver);
        loginPage = new LoginPage(driver);
        mandatesPage = new MandatesPage(driver);
        mandatePage = new MandatePage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);
      //  driver.navigate().refresh();
        navigationPage.logOut();
        LoginPage loginPage = new LoginPage(driver);
        driver.navigate().refresh();
        loginPage.login("qatest", "harel.elazar+rolePermission@scanovate.com", "Q1w2e3r4!");
//        loginPage.login("qatest", "harel.elazar@scanovate.com", "Q1w2e3r4!");
        navigationPage.clickOnBackToBtrustUserButton();
    }


    @Test(description = "Entity search page- no result return - 'Create New Entity' button not display")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Search entity that no exist in the system and confirm that 'Create New Entity' button not display")
    public void Test_01_entitySearchPageNoResultCreateNewEntityButtonNoDisplay() {
        navigationPage.mainMenuList("Search");
        Assert.assertEquals(searchPage.searchTitle(), "Search");
        searchPage.searchEntity("Organization", "ReadOnly");
        Assert.assertEquals(entitySearchPage.noResultMessage(), "No suitable entities were matched your searchReadOnly");
        Assert.assertFalse(entitySearchPage.noResultCreateNewEntityButton());
        Assert.assertEquals(entitySearchPage.noResultDescription(), "Or try modifying your search criteria");
    }


    @Test(description = "Entity search page  - return result - 'Create New Entity' button not display")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Search entity that exist in the system and confirm that 'Create New Entity' + 'Run Workflow' buttons not display")
    public void Test_02_entitySearchPageReturnResultCreateNewEntityButtonNoDisplay() {
        navigationPage.mainMenuList("Search");
        Assert.assertEquals(searchPage.searchTitle(), "Search");
        searchPage.searchEntity("Organization", "Liad");
        Assert.assertFalse(entitySearchPage.ResultCreateNewEntityButton());
        Assert.assertFalse(entitySearchPage.ResultRunWorkflowButton());
    }

    @Test(description = "Entities page > 'Create New Entity' option not display")
    @Description("Entities page 'Create New Entity' option not display")
    public void Test_03_entitiesPageCreateNewEntitiesOptionNotDisplay() {
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        Assert.assertTrue(entitiesPage.exportButtonDisplay("Export"));
        Assert.assertFalse(entitiesPage.checkNameInAddEntityMenu("Create new entity"));
        driver.navigate().refresh();
    }

    @Test(description = "Entities page- 'New Case' option not display")
    @Description("Entities page 'New Case' option not display")
    public void Test_04_entitiesPageNewCaseOptionNotDisplay() {
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.searchFiled("Liad");
        Assert.assertFalse(entitiesPage.checkNewCaseDisplay("New Case"));
    }

    @Test(description = "Entities Page 'New Contact' Button Not Display")
    @Description("Entities > data >'New Contact' button not display")
    public void Test_05_entitiesPageNewContactButtonNotDisplay() {
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.searchFiled("Liad");
        entitiesPage.chooseEntityName("Liad");
        Assert.assertFalse(entityPage.checkNewContactButtonDisplay());
        Assert.assertFalse(entityPage.checkHamburgerMenuButtonDisplay("Edit"));
    }


    @Test(description = "Cases Page 'Create New Case' Button Not Display")
    @Description("Entities > data >'New Contact' button not display")
    public void Test_06_casesPageCreateNewCaseButtonNotDisplay() {
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.searchFiled("Liad");
        entitiesPage.chooseEntityName("Liad");
        entityPage.chooseFromSideBar("Cases");
        Assert.assertFalse(entityPage.checkCreateNewCaseButtonDisplay("Create New Case"));
    }


    @Test(description = "Tasks Page Buttons Not Display")
    @Description("Entities > cases > tasks 'Cancel Tasks' and 'Resend Email' not display ")
    public void Test_07_tasksPageButtonsNotDisplay() {
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.searchFiled("Bank yahav");
        entitiesPage.chooseEntityName("Bank yahav");
        entityPage.chooseFromSideBar("Cases");
        entityPage.chooseFromCasesListByWorkflow("Nitzan W9 IRS");
        Assert.assertFalse(casePage.checkButtonsDisplay("Cancel Tasks"));
        Assert.assertFalse(casePage.checkButtonsDisplay("Resend Email"));
    }


    @Test(description = "Entities page filter by business category")
    @Description("Entities > Perform filter to entities list by entity status select")
    public void Test_08_entitiesPageFilterByBusinessCategory() {
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.clearButton();
        entitiesPage.filterEntitiesTable("filter", "Entity Type", "Organization");
        entitiesPage.filterEntitiesTable("filter", "Business Category", "Client");
        Assert.assertEquals(entitiesPage.businessfCategory("Client"), "Client");
    }


    @Test(description = "Triggers page check if checkbox display")
    @Description("Triggers > check if checkbox display")
    public void Test_09_triggersPageCheckifCheckboxdisplay() {
        navigationPage.mainMenuList("Triggers");
        pendingTriggerPage.chooseFirstTriggerFromPendingList();
        Assert.assertFalse(triggersPage.checkIfCheckBoxDisplay());
    }


}