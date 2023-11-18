package btrust.btrustOne.e2e.userManagment.rolesAndPermission.administritive;


import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.dataModule.dataMapper.pageObject.DataMapperPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.EntitiesManagerPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.BusinessCategoryPage;
import btrust.btrustOne.admin.dataModule.documentManagement.pageObject.DocumentManagementPage;
import btrust.btrustOne.admin.dataModule.serviceMarketPlace.pageObject.ServicesMarketplacePage;
import btrust.btrustOne.admin.usersManager.departmentsPage.pageObject.DepartmentsPage;
import btrust.btrustOne.admin.usersManager.subCompaniesPage.pageObject.SubCompaniesPage;
import btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject.Permission;
import btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject.PermissionsAdministritive;
import btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject.PermissionsData;
import btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject.RolesAndPermissionPage;
import btrust.btrustOne.admin.usersManager.users.pageObject.EditUserPage;
import btrust.btrustOne.admin.usersManager.users.pageObject.UsersPage;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.EditTriggerPage;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.TriggerBuilderPage;
import btrust.btrustOne.admin.workflowManagement.workflowBuilder.pageObject.WorkflowBuilderDefinitionPage;
import btrust.btrustOne.admin.workflowManagement.workflowBuilder.pageObject.WorkflowBuilderPage;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReadTest extends BaseAdminUserTest {


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
        permissionsAdministritive.chooseCheckboxFromAdministritive("Entities Manager", "EDIT & CREATE");
        permissionsAdministritive.chooseCheckboxFromAdministritive("Data Mapper", "EDIT & CREATE");
        permissionsAdministritive.chooseCheckboxFromAdministritive("Documents Manager", "EDIT & CREATE");
        permissionsAdministritive.chooseCheckboxFromAdministritive("Services Marketplace", "EDIT & CREATE");
        permissionsAdministritive.chooseCheckboxFromAdministritive("Workflow Builder", "EDIT & CREATE");
        permissionsAdministritive.chooseCheckboxFromAdministritive("Trigger Builder", "EDIT & CREATE");
        permissionsAdministritive.chooseCheckboxFromAdministritive("Users", "EDIT & CREATE");
        permissionsAdministritive.chooseCheckboxFromAdministritive("Departments", "EDIT & CREATE");
        permissionsAdministritive.chooseCheckboxFromAdministritive("Roles and permissions", "EDIT & CREATE");
        permissionsAdministritive.chooseCheckboxFromAdministritive("Sub Companies", "EDIT & CREATE");
        permission.clickOnButton("Continue");
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.logOut();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("qatest", "harel.elazar+rolePermission@scanovate.com", "Q1w2e3r4!");

    }

    @BeforeMethod
    @Step("log off and login to btrust")
    public void loginToAdminUsr() {
        administratorPage = new AdministratorPage(driver);
        administratorPage.openAllSideBarGroups();
    }


    @Test(description = "Entities Management- check if buttons display for read permission")
    @Description("Entities Management- check display of buttons for user with read permission")
    public void Test_01_entitiesManagementCheckIfButtonsDisplayUserWithForReadPermission() {
        administratorPage.chooseFromSideBar("Entities Management");
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");
        Assert.assertFalse(entitiesManagerPage.checkIfNewBusinessCategoryButtonDisplay());
        entitiesManagerPage.chooseOrganizationBusinessCategoryList("Client");
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        Assert.assertFalse(businessCategoryPage.checkEditBusinessCategoryButtonDisplay());
        Assert.assertFalse(businessCategoryPage.checkIfBusinessCategoryMenuDisplay());
        businessCategoryPage.contentsList("Client fields");
        Assert.assertFalse(businessCategoryPage.checkButtonsDisplay("all tabs", "Add New Field"));
        Assert.assertFalse(businessCategoryPage.checkButtonsDisplay("all tabs", "Save"));
        businessCategoryPage.tabList("Entity Name");
        Assert.assertFalse(businessCategoryPage.checkButtonsDisplay("all tabs", "Save"));
        businessCategoryPage.tabList("Business Relationships");
        businessCategoryPage.contentsList("Client Business Relationships");
        Assert.assertFalse(businessCategoryPage.checkButtonsDisplay("all tabs", "Add new business relationship"));
        Assert.assertFalse(businessCategoryPage.checkButtonsDisplay("all tabs", "Save"));
        businessCategoryPage.tabList("Linked Documents");
        businessCategoryPage.contentsList("Client Documents");
        Assert.assertFalse(businessCategoryPage.checkButtonsDisplay("Link Documents tab", "Assign a new form"));
        businessCategoryPage.clickLinkBack();
    }


    @Test(description = "Date Mapper- check if buttons display for User With read permission")
    @Description("Date Mapper-check display of buttons for user with read permission")
    public void Test_02_DateMapperCheckIfButtonsDisplayForUserWithReadPermission() {
        administratorPage.chooseFromSideBar("Data Mapper");
        DataMapperPage dataMapperPage = new DataMapperPage(driver);
        Assert.assertEquals(dataMapperPage.dataMapperTitle(), "Data Mapper");
        Assert.assertFalse(dataMapperPage.checkIfAddNewFieldOfDataMapperButtonDisplay());
        dataMapperPage.dataMapperSearchBar("automationTestt4boj");
        dataMapperPage.chooseDbFieldName("DB Field Name", "automationTestt4boj");
        Assert.assertFalse(dataMapperPage.checkIfEditFieldLabelButtonDisplay());
        Assert.assertFalse(dataMapperPage.checkIfEditAndDeleteDataSourceButtonDisplay());
        Assert.assertFalse(dataMapperPage.checkIfSaveButtonDisplay());
        dataMapperPage.clickOnExitButton();
    }


    @Test(description = "Document Management- check if buttons display for User With read permission")
    @Description("Document Management - check display of buttons for user with read permission")
    public void Test_03_documentManagementDisplayOfButtonsForUserWithReadPermission() {
        administratorPage.chooseFromSideBar("Document Management");
        DocumentManagementPage documentManagementPage = new DocumentManagementPage(driver);
        Assert.assertFalse(documentManagementPage.checkIfNewDocumentButtonDisplay());
        documentManagementPage.addNewDocumentTypeList("fgdfgdfgdf");
        Assert.assertFalse(documentManagementPage.checkAddNewVersionButtonDisplay());
        Assert.assertFalse(documentManagementPage.editDocButtonIsEnabled());
    }

    @Test(description = "Services Marketplace - check if buttons display for User With read permission")
    @Description("Services Marketplace - check display of buttons for user with read permission")
    public void Test_04_servicesMarketplaceDisplayOfButtonsForUserWithReadPermission() {
        administratorPage.chooseFromSideBar("Services Marketplace");
        ServicesMarketplacePage servicesMarketplacePage = new ServicesMarketplacePage(driver);
        Assert.assertEquals(servicesMarketplacePage.serviceMarketplaceTitle("Services Marketplace"), "Services Marketplace");
        Assert.assertFalse(servicesMarketplacePage.buttonDisplay());
    }


    @Test(description = "Workflow builder - check if buttons display for User With read permission")
    @Description("Workflow builder - check display of buttons for user with read permission")
    public void Test_05_workflowBuilderDisplayOfButtonsForUserWithReadPermission() {
        administratorPage.openAllSideBarGroups();
        administratorPage.chooseFromSideBar("Workflow Builder");
        WorkflowBuilderPage workflowBuilderPage = new WorkflowBuilderPage(driver);
        Assert.assertEquals(workflowBuilderPage.workflowBuilderTitle("Workflow Builder"), "Workflow Builder");
        Assert.assertFalse(workflowBuilderPage.checkIfNewDocumentButtonDisplay());
        Assert.assertFalse(workflowBuilderPage.checkIfHamburgerMenuButtonDisplay());
        workflowBuilderPage.chooseWorkflowNameFromList("W9 IRS");
        WorkflowBuilderDefinitionPage workflowBuilderDefinitionPage = new WorkflowBuilderDefinitionPage(driver);
        Assert.assertFalse(workflowBuilderDefinitionPage.checkIfEditWorkFlowButtonDisplay());
        Assert.assertFalse(workflowBuilderDefinitionPage.checkIfEditFlowParametersButtonDisplay());
        Assert.assertFalse(workflowBuilderDefinitionPage.checkIfLeftSideMenuAppear());
        workflowBuilderDefinitionPage.chooseTaskNameFromTable("W9 IRS");
        workflowBuilderDefinitionPage.clickOnTaskInWorkflow("W9 IRS");
        Assert.assertFalse(workflowBuilderDefinitionPage.checkIfInformationButtonClickable());
        workflowBuilderDefinitionPage.clickBackToListButton();
    }


    @Test(description = "Trigger builder - check if buttons display for User With read permission")
    @Description("Trigger builder - check display of buttons for user with read permission")
    public void Test_06_triggerBuilderDisplayOfButtonsForUserWithReadPermission() {
        administratorPage.openAllSideBarGroups();
        administratorPage.chooseFromSideBar("Triggers");
        TriggerBuilderPage triggerBuilderPage = new TriggerBuilderPage(driver);
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
        Assert.assertFalse(triggerBuilderPage.checkIfNewTriggerBuilderButtonDisplay());
        triggerBuilderPage.chooseTriggerNameFromList("test bar", "Active");
        EditTriggerPage editTriggerPage = new EditTriggerPage(driver);
        Assert.assertFalse(editTriggerPage.checkIfHamburgerMenuButtonDisplay());
        Assert.assertFalse(editTriggerPage.checkIfCheckboxIsEnable());
        Assert.assertFalse(editTriggerPage.checkIfNewSubConditionEnable());
        Assert.assertFalse(editTriggerPage.checkIfAddGroupEnable());
    }


    @Test(description = "Users - check if buttons display for User With read permission")
    @Description("Users - check display of buttons for user with read permission")
    public void Test_07_usersDisplayOfButtonsForUserWithReadPermission() {
        administratorPage.chooseFromSideBar("Users");
        UsersPage usersPage = new UsersPage(driver);
        Assert.assertEquals(usersPage.usersTitle(), "Users");
        Assert.assertFalse(usersPage.checkIfAddUserButtonDisplay());
        usersPage.chooseUserFromUserList("Harel92hya");
        EditUserPage editUserPage = new EditUserPage(driver);
        Assert.assertFalse(editUserPage.checkIfChangeStatusButtonDisplay());
        Assert.assertFalse(editUserPage.checkIfInputFieldsButtonEnable());
        Assert.assertFalse(editUserPage.checkIfSelectFieldsButtonEnable("READ"));
        Assert.assertFalse(editUserPage.checkIfResetPasswordButtonDisplay());
        Assert.assertFalse(editUserPage.checkAddNewRolePermissionDisplay());
    }


    @Test(description = "Departments - check if buttons display for User With read permission")
    @Description("Departments - check display of buttons for user with read permission")
    public void Test_08_departmentsDisplayOfButtonsForUserWithReadPermission() {
        administratorPage.openAllSideBarGroups();
        administratorPage.chooseFromSideBar("Departments");
        DepartmentsPage departmentsPage = new DepartmentsPage(driver);
        Assert.assertEquals(departmentsPage.departmentTitle(), "Departments");
        Assert.assertFalse(departmentsPage.checkIfAddNewDepartmentButtonDisplay());
        departmentsPage.searchField("qa");
        departmentsPage.chooseDepartFromUserList("qa");
        Assert.assertFalse(departmentsPage.checkboxFieldEditable());
        Assert.assertFalse(departmentsPage.checkIfDepartmentEditNameButtonDisplay());
    }


    @Test(description = "Roles And Permission - check if buttons display for User With read permission")
    @Description("Roles And Permission - check display of buttons for user with read permission")
    public void Test_09_rolesAndPermissionDisplayOfButtonsForUserWithReadPermission() {
        administratorPage.openAllSideBarGroups();
        administratorPage.chooseFromSideBar("Roles and permissions");
        RolesAndPermissionPage rolesAndPermissionPage = new RolesAndPermissionPage(driver);
        Assert.assertEquals(rolesAndPermissionPage.rolesAndPermissionsTitle(), "Roles & Permissions");
        Assert.assertFalse(rolesAndPermissionPage.checkIfAddRolesAndPermissionButtonDispaly());
        rolesAndPermissionPage.clickFirstRoleInTheScreen();
        Permission permission = new Permission(driver);
        Assert.assertFalse(permission.checkIfEditButtonDisplay());
        PermissionsData permissionsData = new PermissionsData(driver);
        Assert.assertFalse(permissionsData.dataCheckboxIsEnable("Client", "READ"));
        permission.navigatePermissionTab("Administritive");
        PermissionsAdministritive permissionsAdministritive = new PermissionsAdministritive(driver);
    }


    @Test(description = "Sub Companies - check if buttons display for User With read permission")
    @Description("Sub Companies - check display of buttons for user with read permission")
    public void Test_10_subCompaniesDisplayOfButtonsForUserWithReadPermission() {
        administratorPage.openAllSideBarGroups();
        administratorPage.chooseFromSideBar("Sub Companies");
        SubCompaniesPage subCompaniesPage = new SubCompaniesPage(driver);
        Assert.assertEquals(subCompaniesPage.subCompaniesTitle(), "Sub Companies");
        Assert.assertFalse(subCompaniesPage.checkIfAddSubCompanyButtonDisplay());

    }

}
