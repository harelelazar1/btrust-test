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

public class CreateTest extends BaseAdminUserTest {

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
        permissionsData.chooseCheckboxFromData("Client", "CREATE");
        permissionsData.chooseCheckboxFromData("Executor", "READ");
        permissionsData.chooseCheckboxFromDataMandate("mandate", "READ");
        permission.clickOnButton("Continue");
    }

    @BeforeMethod
    @Step("log off and login to btrust")
    public void logOffAndLoginToBtrust() {
  //      driver.navigate().refresh();
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.logOut();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("qatest", "harel.elazar+rolePermission@scanovate.com", "Q1w2e3r4!");
//        loginPage.login("qatest", "harel.elazar@scanovate.com", "Q1w2e3r4!");
        navigationPage.clickOnBackToBtrustUserButton();
    }


    @Test(description = "Entity search page- no result return - 'Create New Entity' button display")
    @Description("Search entity that no exist in the system and confirm that 'Create New Entity' button display")
    public void Test_01_entitySearchPageNoResultCreateNewEntityButtonDisplay() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Search");
        SearchPage searchPage = new SearchPage(driver);
        Assert.assertEquals(searchPage.searchTitle(), "Search");
        searchPage.searchEntity("Organization", "Create");
        EntitySearchPage entitySearchPage = new EntitySearchPage(driver);
        Assert.assertEquals(entitySearchPage.noResultMessage(), "No suitable entities were matched your searchCreate");
        Assert.assertTrue(entitySearchPage.noResultCreateNewEntityButton());
        Assert.assertEquals(entitySearchPage.noResultDescription(), "Or try modifying your search criteria");
    }


    @Test(description = "Entity search page  - return result - 'Create New Entity' button display")
    @Description("Search entity that exist in the system and confirm that 'Create New Entity' + 'Run Workflow' buttons display")
    public void Test_02_entitySearchPageReturnResultCreateNewEntityButtonDisplay() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Search");
        SearchPage searchPage = new SearchPage(driver);
        Assert.assertEquals(searchPage.searchTitle(), "Search");
//        Assert.assertTrue(searchPage.searchDisplayEntityName("Organization", 5));
//        searchPage.ClickSearchEntity();
        searchPage.searchEntity("Organization", "Liad");
        EntitySearchPage entitySearchPage = new EntitySearchPage(driver);
        Assert.assertTrue(entitySearchPage.ResultCreateNewEntityButton());
        Assert.assertTrue(entitySearchPage.ResultRunWorkflowButton());
    }

    @Test(description = "Entities page > 'Create New Entity' option display")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Entities page 'Create New Entity' option display")
    public void Test_03_entitiesPageCreateNewEntitiesOptiontDisplay() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Entities");
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        Assert.assertTrue(entitiesPage.entitiesTitle());
        Assert.assertTrue(entitiesPage.checkNameInAddEntityMenu("Create new entity"));
        entitiesPage.clickOnSearchFiled();
        Assert.assertTrue(entitiesPage.checkNameInAddEntityMenu("Create entity and workflow"));
        driver.navigate().refresh();
    }

    @Test(description = "Entities page- 'New Case' option display")
    @Description("Entities page 'New Case' option display")
    public void Test_04_entitiesPageNewCaseOptionDisplay() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Entities");
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.searchFiled("Liad");
        Assert.assertTrue(entitiesPage.checkNewCaseDisplay("New Case"));
    }

    @Test(description = "Cases Page 'Create New Case' Button Display")
    @Description("Entities > data >'New Contact' button display")
    public void Test_05_casesPageCreateNewCaseButtonDisplay() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Entities");
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.searchFiled("Liad");
        entitiesPage.chooseEntityName("Liad");
        EntityPage entityPage = new EntityPage(driver);
        entityPage.chooseFromSideBar("Cases");
        Assert.assertTrue(entityPage.checkCreateNewCaseButtonDisplay("Create New Case"));
    }


    @Test(description = "Tasks Page Buttons Display")
    @Description("Entities > cases > tasks 'Cancel Tasks' and 'Resend Email' display")
    public void Test_06_tasksPageButtonsDisplay() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Entities");
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.searchFiled("Bank yahav");
        entitiesPage.chooseEntityName("Bank yahav");
        EntityPage entityPage = new EntityPage(driver);
        entityPage.chooseFromSideBar("Cases");
        entityPage.chooseFromCasesListByWorkflow("Nitzan W9 IRS");
        CasePage casePage = new CasePage(driver);
        Assert.assertTrue(casePage.checkButtonsDisplay("Cancel Tasks"));
        Assert.assertTrue(casePage.checkButtonsDisplay("Resend Email"));
    }

    @Test(description = "Triggers page check checkbox and buttons")
    @Description("Triggers > check checkbox and buttons")
    public void Test_07_entitiesPageFilterByBusinessCategory() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Triggers");
        PendingTriggerPage pendingTriggerPage = new PendingTriggerPage(driver);
        pendingTriggerPage.chooseFirstTriggerFromPendingList();
        TriggerPage triggerPage = new TriggerPage(driver);
        triggerPage.fillDueDate("August 2022", "30");
        triggerPage.markFirstCheckboxInEntitiesList();
        Assert.assertTrue(triggerPage.checkButtonPostponeRecertification("Postpone Recertification"));
    }

}
