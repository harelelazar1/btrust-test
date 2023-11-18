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

public class EditAndCreateTest extends BaseAdminUserTest {


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
        permission.clickSelectAllButtonStatus("Select All");
        permission.clickOnButton("Continue");
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.logOut();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("qatest", "harel.elazar+rolePermission@scanovate.com", "Q1w2e3r4!");

    }

    @BeforeMethod
    @Step("log off and login to btrust")
    public void logOffAndLoginToBtrust() {
        administratorPage = new AdministratorPage(driver);
        administratorPage.openAllSideBarGroups();
    }

    @Test(description = "Entities Management- check if buttons display for edit & create permission")
    @Description("Entities Management- check display of buttons for user with edit & create permission")
    public void Test_01_entitiesManagementCheckIfButtonsDisplayForEditAndCreatePermission() {
        EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);
        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");
        Assert.assertTrue(entitiesManagerPage.checkIfNewBusinessCategoryButtonDisplay());
        entitiesManagerPage.chooseOrganizationBusinessCategoryList("Client");
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        Assert.assertTrue(businessCategoryPage.checkEditBusinessCategoryButtonDisplay());
        Assert.assertTrue(businessCategoryPage.checkIfBusinessCategoryMenuDisplay());
        businessCategoryPage.contentsList("Client fields");
        Assert.assertTrue(businessCategoryPage.checkButtonsDisplay("all tabs", "Add New Field"));
        Assert.assertFalse(businessCategoryPage.checkButtonsDisplay("all tabs", "Save"));
        businessCategoryPage.tabList("Entity Name");
        Assert.assertFalse(businessCategoryPage.checkButtonsDisplay("all tabs", "Save"));
        businessCategoryPage.tabList("Business Relationships");
        businessCategoryPage.contentsList("Client Business Relationships");
        Assert.assertTrue(businessCategoryPage.checkButtonsDisplay("all tabs", "Add new business relationship"));
        Assert.assertFalse(businessCategoryPage.checkButtonsDisplay("all tabs", "Save"));
        businessCategoryPage.tabList("Linked Documents");
        businessCategoryPage.contentsList("Client Documents");
        Assert.assertTrue(businessCategoryPage.checkButtonsDisplay("Link Documents tab", "Assign a new form"));
        businessCategoryPage.clickLinkBack();
    }


    @Test(description = "Data Mapper-check if buttons display for edit & create permission")
    @Description("Date Mapper- check display of buttons for user with edit & create permission")
    public void Test_02_DataMapperCheckIfButtonsDisplayForUserWithEditAndCreatePermission() {
        administratorPage.chooseFromSideBar("Data Mapper");
        DataMapperPage dataMapperPage = new DataMapperPage(driver);
        Assert.assertEquals(dataMapperPage.dataMapperTitle(), "Data Mapper");
        Assert.assertTrue(dataMapperPage.checkIfAddNewFieldOfDataMapperButtonDisplay());
        dataMapperPage.dataMapperSearchBar("automationTestt4boj");
        dataMapperPage.chooseDbFieldName("DB Field Name", "automationTestt4boj");
        Assert.assertTrue(dataMapperPage.checkIfEditFieldLabelButtonDisplay());
        Assert.assertTrue(dataMapperPage.checkIfSaveButtonDisplay());
        dataMapperPage.clickOnExitButton();
    }


    @Test(description = "Document Management- check if buttons display for Edit And Create permission")
    @Description("Document Management - check display of buttons for user with Edit And Create permission")
    public void Test_03_documentManagementDisplayOfButtonsForUserWithEditAndCreatePermission() {
        administratorPage.chooseFromSideBar("Document Management");
        DocumentManagementPage documentManagementPage = new DocumentManagementPage(driver);
        Assert.assertTrue(documentManagementPage.checkIfNewDocumentButtonDisplay());
        documentManagementPage.addNewDocumentTypeList("HAREL TEST");
        Assert.assertTrue(documentManagementPage.checkAddNewVersionButtonDisplay());
    }


    @Test(description = "Services Marketplace - check if buttons display for Edit And Create permission")
    @Description("Services Marketplace - check display of buttons for user with Edit And Create permission")
    public void Test_04_servicesMarketplaceDisplayOfButtonsForUserWithEditAndCreatePermission() {
        administratorPage.chooseFromSideBar("Services Marketplace");
        ServicesMarketplacePage servicesMarketplacePage = new ServicesMarketplacePage(driver);
        Assert.assertEquals(servicesMarketplacePage.serviceMarketplaceTitle("Services Marketplace"), "Services Marketplace");
        Assert.assertTrue(servicesMarketplacePage.buttonDisplay());
        servicesMarketplacePage.clickButtonEditOrAdd("Add service");
        servicesMarketplacePage.checkIfAddHeaderButtonDisplay();
    }


    @Test(description = "Workflow builder - check if buttons display for Edit And Create permission")
    @Description("Workflow builder - check display of buttons for user with Edit And Create permission")
    public void Test_05_workflowBuilderDisplayOfButtonsForUserWithEditAndCreatePermission() {
        administratorPage.openAllSideBarGroups();
        administratorPage.chooseFromSideBar("Workflow Builder");
        WorkflowBuilderPage workflowBuilderPage = new WorkflowBuilderPage(driver);
        Assert.assertEquals(workflowBuilderPage.workflowBuilderTitle("Workflow Builder"), "Workflow Builder");
        Assert.assertTrue(workflowBuilderPage.checkIfNewDocumentButtonDisplay());
        Assert.assertTrue(workflowBuilderPage.checkIfHamburgerMenuButtonDisplay());
        workflowBuilderPage.chooseWorkflowNameFromList("W9 IRS");
        WorkflowBuilderDefinitionPage workflowBuilderDefinitionPage = new WorkflowBuilderDefinitionPage(driver);
        Assert.assertTrue(workflowBuilderDefinitionPage.checkIfEditWorkFlowButtonDisplay());
        Assert.assertTrue(workflowBuilderDefinitionPage.checkIfEditFlowParametersButtonDisplay());
        Assert.assertTrue(workflowBuilderDefinitionPage.checkIfLeftSideMenuAppear());
        workflowBuilderDefinitionPage.chooseTaskNameFromTable("W9 IRS");
        workflowBuilderDefinitionPage.clickOnTaskInWorkflow("W9 IRS");
        Assert.assertTrue(workflowBuilderDefinitionPage.checkIfInformationButtonClickable());
        workflowBuilderDefinitionPage.clickBackToListButton();
    }


    @Test(description = "Trigger builder - check if buttons display for User With Edit And Create permission")
    @Description("Trigger builder - check display of buttons for user with Edit And Create permission")
    public void Test_06_triggerBuilderDisplayOfButtonsForUserWithEditAndCreatePermission() {
        administratorPage.openAllSideBarGroups();
        administratorPage.chooseFromSideBar("Triggers");
        TriggerBuilderPage triggerBuilderPage = new TriggerBuilderPage(driver);
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
        Assert.assertTrue(triggerBuilderPage.checkIfNewTriggerBuilderButtonDisplay());
        triggerBuilderPage.chooseFirstTriggerNameFromList();
        EditTriggerPage editTriggerPage = new EditTriggerPage(driver);
        Assert.assertTrue(editTriggerPage.checkIfHamburgerMenuButtonDisplay());
        Assert.assertTrue(editTriggerPage.checkIfCheckboxIsEnable());
        Assert.assertTrue(editTriggerPage.checkIfNewSubConditionEnable());
        Assert.assertTrue(editTriggerPage.checkIfAddGroupEnable());
    }


    @Test(description = "Users - check if buttons display for User With Edit And Create permission")
    @Description("Users - check display of buttons for user with Edit And Create permission")
    public void Test_07_usersDisplayOfButtonsForUserWithEditAndCreatePermission() {
        administratorPage.chooseFromSideBar("Users");
        UsersPage usersPage = new UsersPage(driver);
        Assert.assertEquals(usersPage.usersTitle(), "Users");
        Assert.assertTrue(usersPage.checkIfAddUserButtonDisplay());
        usersPage.chooseUserFromUserList("Harel92hya");
        EditUserPage editUserPage = new EditUserPage(driver);
        Assert.assertTrue(editUserPage.checkIfChangeStatusButtonDisplay());
        Assert.assertTrue(editUserPage.checkIfInputFieldsButtonEnable());
        Assert.assertTrue(editUserPage.checkIfSelectFieldsButtonEnable("EDIT & CREATE"));
        Assert.assertTrue(editUserPage.checkIfResetPasswordButtonDisplay());
        Assert.assertTrue(editUserPage.checkAddNewRolePermissionDisplay());
    }


    @Test(description = "Departments - check if buttons display for User With Edit And Create permission")
    @Description("Departments - check display of buttons for user with read permission")
    public void Test_08_departmentsDisplayOfButtonsForUserWithEditAndCreatePermission() {
        administratorPage.openAllSideBarGroups();
        administratorPage.chooseFromSideBar("Departments");
        DepartmentsPage departmentsPage = new DepartmentsPage(driver);
        Assert.assertEquals(departmentsPage.departmentTitle(), "Departments");
        Assert.assertTrue(departmentsPage.checkIfAddNewDepartmentButtonDisplay());
        departmentsPage.searchField("qa");
        departmentsPage.chooseDepartFromUserList("qa");
        Assert.assertTrue(departmentsPage.checkboxFieldEditable());
        Assert.assertTrue(departmentsPage.checkIfDepartmentEditNameButtonDisplay());
    }


    @Test(description = "Roles And Permission - check if buttons display for User With Edit And Create permission")
    @Description("Roles And Permission - check display of buttons for user with Edit And Create permission")
    public void Test_09_rolesAndPermissionDisplayOfButtonsForUserWithEditAndCreatePermission() {
        administratorPage.openAllSideBarGroups();
        administratorPage.chooseFromSideBar("Roles and permissions");
        RolesAndPermissionPage rolesAndPermissionPage = new RolesAndPermissionPage(driver);
        Assert.assertEquals(rolesAndPermissionPage.rolesAndPermissionsTitle(), "Roles & Permissions");
        Assert.assertTrue(rolesAndPermissionPage.checkIfAddRolesAndPermissionButtonDispaly());
        rolesAndPermissionPage.clickFirstRoleInTheScreen();
        Permission permission = new Permission(driver);
        Assert.assertTrue(permission.checkIfEditButtonDisplay());
        PermissionsData permissionsData = new PermissionsData(driver);
        Assert.assertTrue(permissionsData.dataCheckboxIsEnable("Client", "READ"));
        permission.navigatePermissionTab("Administritive");
        PermissionsAdministritive permissionsAdministritive = new PermissionsAdministritive(driver);
    }


    @Test(description = "Sub Companies - check if buttons display for User With Edit And Create permission")
    @Description("Sub Companies - check display of buttons for user with Edit And Create permission")
    public void Test_10_subCompaniesDisplayOfButtonsForUserWithEditAndCreatePermission() {
        administratorPage.openAllSideBarGroups();
        administratorPage.chooseFromSideBar("Sub Companies");
        SubCompaniesPage subCompaniesPage = new SubCompaniesPage(driver);
        Assert.assertEquals(subCompaniesPage.subCompaniesTitle(), "Sub Companies");
        Assert.assertTrue(subCompaniesPage.checkIfAddSubCompanyButtonDisplay());
    }

}
