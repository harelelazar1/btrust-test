package btrust.btrustOne.e2e.workflowManagement.workflowBuilder.test;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.workflowManagement.workflowBuilder.pageObject.WorkflowBuilderDefinitionPage;
import btrust.btrustOne.admin.workflowManagement.workflowBuilder.pageObject.WorkflowBuilderPage;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.cases.pagesObject.CasePage;
import btrust.btrustOne.client.cases.pagesObject.RdcTaskPage;
import btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject.ContactsInformationPage;
import btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject.EmailAndFormsPage;
import btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject.WorkflowPage;
import btrust.btrustOne.client.entity.pagesObject.EntitiesPage;
import btrust.btrustOne.client.entity.pagesObject.EntityPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RdcOrganizationTest extends BaseAdminUserTest {

    AdministratorPage administratorPage;
    WorkflowBuilderPage workflowBuilderPage;
    WorkflowBuilderDefinitionPage workflowBuilderDefinitionPage;
    NavigationPage navigationPage;
    EntitiesPage entitiesPage;
    EntityPage entityPage;
    CasePage casePage;
    WorkflowPage workflowPage;
    ContactsInformationPage contactsInformationPage;
    EmailAndFormsPage emailAndFormsPage;
    RdcTaskPage rdcTaskPage;
    NavigationPage navigation;


    @Override
    @BeforeClass
    @Step("Login to admin user")
    public void loginToAdminUser() {
        driver.get("https://qa-console.scanovateoncloud.com/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginNewForSecurity( "harelelazar@gmail.com", "Amitbiton20");
        AdministratorPage administratorPage = new AdministratorPage(driver);
        Assert.assertEquals(administratorPage.administratorTitle(), "Administrator");
        administratorPage.openAllSideBarGroups();
    }


    @BeforeMethod
    @Step("createObject")
    public void createObject() {
        administratorPage = new AdministratorPage(driver);
        workflowBuilderPage = new WorkflowBuilderPage(driver);
        workflowBuilderDefinitionPage = new WorkflowBuilderDefinitionPage(driver);
        navigationPage = new NavigationPage(driver);
        entitiesPage = new EntitiesPage(driver);
        entityPage = new EntityPage(driver);
        casePage = new CasePage(driver);
        workflowPage = new WorkflowPage(driver);
        contactsInformationPage = new ContactsInformationPage(driver);
        emailAndFormsPage = new EmailAndFormsPage(driver);
        rdcTaskPage = new RdcTaskPage(driver);
        navigation = new NavigationPage(driver);
        administratorPage.chooseFromSideBar("Workflow Builder");
        Assert.assertEquals(workflowBuilderPage.workflowBuilderTitle("Workflow Builder"), "Workflow Builder");
        workflowBuilderPage.chooseWorkflowNameFromList("rdc organization");
        workflowBuilderDefinitionPage.clickOnTaskInWorkflow("Individual Risk Hits");
        workflowBuilderDefinitionPage.clickOnTabByName("Task Settings");
        workflowBuilderDefinitionPage.markSettingInTheTaskSettingTab("Mandatory", "RadioButton", null);
        workflowBuilderDefinitionPage.markSettingInTheTaskSettingTab("No hits - Close task automatically", "RadioButton", null);
        workflowBuilderDefinitionPage.markSettingInTheTaskSettingTab("Search automatically when mandatory fields arrive", "RadioButton", null);
        workflowBuilderDefinitionPage.markSettingInTheTaskSettingTab("Allow user modify search", "RadioButton", null);
        workflowBuilderDefinitionPage.unmarkedSettingInTheTaskSettingTab("Enable case close with remaining hits", "RadioButton", null);
    }


    @AfterMethod
    public void navigateToAdministratorPage() {
        navigation.returnToAdminPage("Workflows Management");
    }


    @Test(description = "Create Case with task for rdc Organization  with 'No hits - Close task automatically' - Yes")
    @Description("Workflow Builder - Create Case with rdc task for - Organization")
    public void Test_01_createRdcCaseForOrganizationNoHitsCloseTaskAutomatically_Yes() {
        workflowBuilderDefinitionPage.chooseButton("Save");
        navigationPage.clickOnBackToBtrustUserButton();
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.clearButton();
        entitiesPage.searchFiled("scanovate");
        entitiesPage.chooseEntityName("scanovate");
        entityPage.chooseFromSideBar("Cases");
        entityPage.createNewCaseButton();
        Assert.assertEquals(workflowPage.workflowTitle(), "Workflow");
        workflowPage.fillWorkflowPage("Workflow", "rdc organization", null, null);
        workflowPage.fillWorkflowPage("Case Due Date", null, "December 2022", "1");
        workflowPage.chooseButton("Continue");
        Assert.assertEquals(contactsInformationPage.contactsTitle(), "Contacts");
        contactsInformationPage.chooseContact("scanovate company");
        contactsInformationPage.chooseButton("Continue");
        Assert.assertEquals(emailAndFormsPage.emailAndFormsTitle(), "E-mail");
        emailAndFormsPage.chooseButton("Continue");
        emailAndFormsPage.chooseButtonCasesCreation("Confirm");
        emailAndFormsPage.chooseButtonCasesCreation("Open the Case");
        casePage.chooseTaskNameFromList("Task Name", "Individual Risk Hits");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Done");
        Assert.assertEquals(rdcTaskPage.hitsNotFoundMessage(), "No risk profile was matched to this company");
    }


    @Test(description = "Create Case with Task for rdc Organization with 'No hits - Close task automatically' - No")
    @Description("Workflow Builder - Create Case with rdc task for - Organization")
    public void Test_02_createRdcCaseForOrganizationNoHitsCloseTaskAutomatically_No() {
        workflowBuilderDefinitionPage.unmarkedSettingInTheTaskSettingTab("No hits - Close task automatically", "RadioButton", null);
        workflowBuilderDefinitionPage.chooseButton("Save");
        navigationPage.clickOnBackToBtrustUserButton();
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.clearButton();
        entitiesPage.searchFiled("scanovate");
        entitiesPage.chooseEntityName("scanovate");
        entityPage.chooseFromSideBar("Cases");
        entityPage.createNewCaseButton();
        Assert.assertEquals(workflowPage.workflowTitle(), "Workflow");
        workflowPage.fillWorkflowPage("Workflow", "rdc organization", null, null);
        workflowPage.fillWorkflowPage("Case Due Date", null, "December 2022", "1");
        workflowPage.chooseButton("Continue");
        Assert.assertEquals(contactsInformationPage.contactsTitle(), "Contacts");
        contactsInformationPage.chooseContact("scanovate company");
        contactsInformationPage.chooseButton("Continue");
        Assert.assertEquals(emailAndFormsPage.emailAndFormsTitle(), "E-mail");
        emailAndFormsPage.chooseButton("Continue");
        emailAndFormsPage.chooseButtonCasesCreation("Confirm");
        emailAndFormsPage.chooseButtonCasesCreation("Open the Case");
        casePage.chooseTaskNameFromList("Task Name", "Individual Risk Hits");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Open");
        Assert.assertEquals(rdcTaskPage.hitsNotFoundMessage(), "No risk profile was matched to this company");
        rdcTaskPage.chooseButton("Confirm");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Done");
    }


    @Test(description = "Create Case with Task for rdc Organization with 'Allow user modify search' - No")
    @Description("Workflow Builder - Create Case with rdc task for- Organization")
    public void Test_03_createRdcCaseForOrganizationAllowUserModifySearch_No() {
        workflowBuilderDefinitionPage.unmarkedSettingInTheTaskSettingTab("Allow user modify search", "RadioButton", null);
        workflowBuilderDefinitionPage.chooseButton("Save");
        navigationPage.clickOnBackToBtrustUserButton();
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.clearButton();
        entitiesPage.searchFiled("TEVA");
        entitiesPage.chooseEntityName("TEVA");
        entityPage.chooseFromSideBar("Cases");
        entityPage.createNewCaseButton();
        Assert.assertEquals(workflowPage.workflowTitle(), "Workflow");
        workflowPage.fillWorkflowPage("Workflow", "rdc organization", null, null);
        workflowPage.fillWorkflowPage("Case Due Date", null, "December 2022", "1");
        workflowPage.chooseButton("Continue");
        Assert.assertEquals(contactsInformationPage.contactsTitle(), "Contacts");
        contactsInformationPage.chooseContact("TEVA ISRAEL");
        contactsInformationPage.chooseButton("Continue");
        Assert.assertEquals(emailAndFormsPage.emailAndFormsTitle(), "E-mail");
        emailAndFormsPage.chooseButton("Continue");
        emailAndFormsPage.chooseButtonCasesCreation("Confirm");
        emailAndFormsPage.chooseButtonCasesCreation("Open the Case");
        casePage.chooseTaskNameFromList("Task Name", "Individual Risk Hits");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Open");
        Assert.assertFalse(rdcTaskPage.CheckIfModifySearchLinkDisplay());
    }


    @Test(description = "Create Case with Task for rdc Organization with 'Allow user modify search' - Yes")
    @Description("Workflow Builder - Create Case with rdc task for- Organization")
    public void Test_04_createRdcCaseForOrganizationAllowUserModifySearch_Yes() {
        workflowBuilderDefinitionPage.markSettingInTheTaskSettingTab("Allow user modify search", "RadioButton", null);
        workflowBuilderDefinitionPage.chooseButton("Save");
        navigationPage.clickOnBackToBtrustUserButton();
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.clearButton();
        entitiesPage.searchFiled("TEVA");
        entitiesPage.chooseEntityName("TEVA");
        entityPage.chooseFromSideBar("Cases");
        entityPage.createNewCaseButton();
        Assert.assertEquals(workflowPage.workflowTitle(), "Workflow");
        workflowPage.fillWorkflowPage("Workflow", "rdc organization", null, null);
        workflowPage.fillWorkflowPage("Case Due Date", null, "December 2022", "1");
        workflowPage.chooseButton("Continue");
        Assert.assertEquals(contactsInformationPage.contactsTitle(), "Contacts");
        contactsInformationPage.chooseContact("TEVA ISRAEL");
        contactsInformationPage.chooseButton("Continue");
        Assert.assertEquals(emailAndFormsPage.emailAndFormsTitle(), "E-mail");
        emailAndFormsPage.chooseButton("Continue");
        emailAndFormsPage.chooseButtonCasesCreation("Confirm");
        emailAndFormsPage.chooseButtonCasesCreation("Open the Case");
        casePage.chooseTaskNameFromList("Task Name", "Individual Risk Hits");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Open");
        Assert.assertTrue(rdcTaskPage.CheckIfModifySearchLinkDisplay());
    }


    @Test(description = "Create Case with Task for rdc Organization with 'Enable case close with remaining hits' - No")
    @Description("Workflow Builder - Create Case with rdc task for- Organization")
    public void Test_05_createRdcCaseForOrganizationEnableCaseCloseWithRemainingHits_No() {
        workflowBuilderDefinitionPage.unmarkedSettingInTheTaskSettingTab("Enable case close with remaining hits", "RadioButton", null);
        workflowBuilderDefinitionPage.chooseButton("Save");
        navigationPage.clickOnBackToBtrustUserButton();
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.clearButton();
        entitiesPage.searchFiled("TEVA");
        entitiesPage.chooseEntityName("TEVA");
        entityPage.chooseFromSideBar("Cases");
        entityPage.createNewCaseButton();
        Assert.assertEquals(workflowPage.workflowTitle(), "Workflow");
        workflowPage.fillWorkflowPage("Workflow", "rdc organization", null, null);
        workflowPage.fillWorkflowPage("Case Due Date", null, "December 2022", "1");
        workflowPage.chooseButton("Continue");
        Assert.assertEquals(contactsInformationPage.contactsTitle(), "Contacts");
        contactsInformationPage.chooseContact("TEVA ISRAEL");
        contactsInformationPage.chooseButton("Continue");
        Assert.assertEquals(emailAndFormsPage.emailAndFormsTitle(), "E-mail");
        emailAndFormsPage.chooseButton("Continue");
        emailAndFormsPage.chooseButtonCasesCreation("Confirm");
        emailAndFormsPage.chooseButtonCasesCreation("Open the Case");
        casePage.chooseTaskNameFromList("Task Name", "Individual Risk Hits");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Open");
        rdcTaskPage.markTruePositiveButtonByRow("Teva Pharmaceuticals Industries Ltd", "US");
        Assert.assertFalse(rdcTaskPage.buttonIsEnable("Confirm"));
    }


    @Test(description = "Create Case with Task for rdc Organization with 'Enable case close with remaining hits' - Yes")
    @Description("Workflow Builder - Create Case with rdc task for- Organization")
    public void Test_06_createRdcCaseForOrganizationEnableCaseCloseWithRemainingHits_Yes() {
        workflowBuilderDefinitionPage.markSettingInTheTaskSettingTab("Enable case close with remaining hits", "RadioButton", null);
        workflowBuilderDefinitionPage.chooseButton("Save");
        navigationPage.clickOnBackToBtrustUserButton();
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.clearButton();
        entitiesPage.searchFiled("TEVA");
        entitiesPage.chooseEntityName("TEVA");
        entityPage.chooseFromSideBar("Cases");
        entityPage.createNewCaseButton();
        Assert.assertEquals(workflowPage.workflowTitle(), "Workflow");
        workflowPage.fillWorkflowPage("Workflow", "rdc organization", null, null);
        workflowPage.fillWorkflowPage("Case Due Date", null, "December 2022", "1");
        workflowPage.chooseButton("Continue");
        Assert.assertEquals(contactsInformationPage.contactsTitle(), "Contacts");
        contactsInformationPage.chooseContact("TEVA ISRAEL");
        contactsInformationPage.chooseButton("Continue");
        Assert.assertEquals(emailAndFormsPage.emailAndFormsTitle(), "E-mail");
        emailAndFormsPage.chooseButton("Continue");
        emailAndFormsPage.chooseButtonCasesCreation("Confirm");
        emailAndFormsPage.chooseButtonCasesCreation("Open the Case");
        casePage.chooseTaskNameFromList("Task Name", "Individual Risk Hits");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Open");
        rdcTaskPage.markTruePositiveButtonByRow("Teva Pharmaceuticals Industries Ltd", "US");
        rdcTaskPage.chooseButton("Confirm");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Done");
    }


    @Test(description = "Create Case with Task for rdc Organization with 'Search automatically when mandatory fields arrive' - No")
    @Description("Workflow Builder - Create Case with rdc task for- Organization")
    public void Test_07_createRdcCaseForOrganizationSearchAutomaticallyWhenMandatoryFieldsArrive_No() {
        workflowBuilderDefinitionPage.unmarkedSettingInTheTaskSettingTab("Search automatically when mandatory fields arrive", "RadioButton", null);
        workflowBuilderDefinitionPage.chooseButton("Save");
        navigationPage.clickOnBackToBtrustUserButton();
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.clearButton();
        entitiesPage.searchFiled("NSO");
        entitiesPage.chooseEntityName("NSO");
        entityPage.chooseFromSideBar("Risk Profile");
        int before = entityPage.numberOfRows();
        entityPage.chooseFromSideBar("Cases");
        entityPage.createNewCaseButton();
        Assert.assertEquals(workflowPage.workflowTitle(), "Workflow");
        workflowPage.fillWorkflowPage("Workflow", "rdc organization", null, null);
        workflowPage.fillWorkflowPage("Case Due Date", null, "December 2022", "1");
        workflowPage.chooseButton("Continue");
        Assert.assertEquals(contactsInformationPage.contactsTitle(), "Contacts");
        contactsInformationPage.chooseContact("NSO COMAPNY");
        contactsInformationPage.chooseButton("Continue");
        Assert.assertEquals(emailAndFormsPage.emailAndFormsTitle(), "E-mail");
        emailAndFormsPage.chooseButton("Continue");
        emailAndFormsPage.chooseButtonCasesCreation("Confirm");
        emailAndFormsPage.chooseButtonCasesCreation("Open the Case");
        casePage.chooseTaskNameFromList("Task Name", "Individual Risk Hits");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Open");
        rdcTaskPage.chooseButton("Save and Send query");
        Assert.assertTrue(rdcTaskPage.hitsFoundMessage().contains("hits found.Please mark each hit as True or False. Click on each hit for further information"));
        int changes = rdcTaskPage.markerHandler("NSO Group", "IL", true);
        rdcTaskPage.clickOnCheckbox();
        rdcTaskPage.chooseButton("Confirm");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Done");
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.clearButton();
        entitiesPage.searchFiled("NSO");
        entitiesPage.chooseEntityName("NSO");
        entityPage.chooseFromSideBar("Risk Profile");
        Assert.assertEquals(entityPage.numberOfRows() - changes, before);
    }


    @Test(description = "Create Case with Task for rdc Organization with 'Search automatically when mandatory fields arrive' - Yes")
    @Description("Workflow Builder - Create Case with rdc task for- Organization")
    public void Test_08_createRdcCaseForOrganizationSearchAutomaticallyWhenMandatoryFieldsArrive_Yes() {
        workflowBuilderDefinitionPage.markSettingInTheTaskSettingTab("Search automatically when mandatory fields arrive", "RadioButton", null);
        workflowBuilderDefinitionPage.chooseButton("Save");
        navigationPage.clickOnBackToBtrustUserButton();
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.clearButton();
        entitiesPage.searchFiled("NSO");
        entitiesPage.chooseEntityName("NSO");
        entityPage.chooseFromSideBar("Risk Profile");
        entityPage.chooseFromSideBar("Cases");
        entityPage.createNewCaseButton();
        Assert.assertEquals(workflowPage.workflowTitle(), "Workflow");
        workflowPage.fillWorkflowPage("Workflow", "rdc organization", null, null);
        workflowPage.fillWorkflowPage("Case Due Date", null, "December 2022", "1");
        workflowPage.chooseButton("Continue");
        Assert.assertEquals(contactsInformationPage.contactsTitle(), "Contacts");
        contactsInformationPage.chooseContact("NSO COMAPNY");
        contactsInformationPage.chooseButton("Continue");
        Assert.assertEquals(emailAndFormsPage.emailAndFormsTitle(), "E-mail");
        emailAndFormsPage.chooseButton("Continue");
        emailAndFormsPage.chooseButtonCasesCreation("Confirm");
        emailAndFormsPage.chooseButtonCasesCreation("Open the Case");
        casePage.chooseTaskNameFromList("Task Name", "Individual Risk Hits");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Open");
        Assert.assertTrue(rdcTaskPage.hitsFoundMessage().contains("hits found.Please mark each hit as True or False. Click on each hit for further information"));
    }


    @Test(description = "Create Case with Task for rdc Organization after 'Postponed' the first task")
    @Description("Workflow Builder - Create Case with rdc task for Organization after Postponed the first task")
    public void Test_09_createRdcCaseForOrganizationAfterPostponedTheFirstTask() {
        workflowBuilderDefinitionPage.markSettingInTheTaskSettingTab("Search automatically when mandatory fields arrive", "RadioButton", null);
        workflowBuilderDefinitionPage.chooseButton("Save");
        navigationPage.clickOnBackToBtrustUserButton();
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.clearButton();
        entitiesPage.searchFiled("NSO");
        entitiesPage.chooseEntityName("NSO");
 //       entityPage.chooseFromSideBar("Risk Profile");
 //       int before = entityPage.numberOfRows();
        entityPage.chooseFromSideBar("Cases");
        entityPage.createNewCaseButton();
        Assert.assertEquals(workflowPage.workflowTitle(), "Workflow");
        workflowPage.fillWorkflowPage("Workflow", "rdc organization", null, null);
        workflowPage.fillWorkflowPage("Case Due Date", null, "December 2022", "1");
        workflowPage.chooseButton("Continue");
        Assert.assertEquals(contactsInformationPage.contactsTitle(), "Contacts");
        contactsInformationPage.chooseContact("NSO COMAPNY");
        contactsInformationPage.chooseButton("Continue");
        Assert.assertEquals(emailAndFormsPage.emailAndFormsTitle(), "E-mail");
        emailAndFormsPage.chooseButton("Continue");
        emailAndFormsPage.chooseButtonCasesCreation("Confirm");
        emailAndFormsPage.chooseButtonCasesCreation("Open the Case");
        casePage.chooseTaskNameFromList("Task Name", "Individual Risk Hits");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Open");
        Assert.assertTrue(rdcTaskPage.hitsFoundMessage().contains("hits found.Please mark each hit as True or False. Click on each hit for further information"));
        rdcTaskPage.chooseButton("Postpone Resolution");
        Assert.assertEquals(rdcTaskPage.postponedPopupTitle(), "Postpone Resolution");
        rdcTaskPage.fillAreaTextInPopUpAndConfirm("no reasom", "Confirm & Postpone");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Postponed");
        rdcTaskPage.clickOnLinkToNewTask();
        casePage.chooseTaskNameFromList("Task Name", "Individual Risk Hits");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Open");
        int changes = rdcTaskPage.markerHandler("NSO Group", "IL", true);
        rdcTaskPage.clickOnCheckbox();
        rdcTaskPage.chooseButton("Confirm");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Done");
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.clearButton();
        entitiesPage.searchFiled("NSO");
        entitiesPage.chooseEntityName("NSO");
        entityPage.chooseFromSideBar("Risk Profile");
 //       Assert.assertEquals(entityPage.numberOfRows() - changes, before);
    }


    @Test(description = "Create Case with Task for rdc Organization by mark row in popup")
    @Description("Workflow Builder - Create Case with rdc task for- Organization")
    public void Test_10_createRdcCaseForOrganizationByMarkRowInPopup() {
        navigationPage.clickOnBackToBtrustUserButton();
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.clearButton();
        entitiesPage.searchFiled("NSO");
        entitiesPage.chooseEntityName("NSO");
 //       entityPage.chooseFromSideBar("Risk Profile");
//        int before = entityPage.numberOfRows();
        entityPage.chooseFromSideBar("Cases");
        entityPage.createNewCaseButton();
        Assert.assertEquals(workflowPage.workflowTitle(), "Workflow");
        workflowPage.fillWorkflowPage("Workflow", "rdc organization", null, null);
        workflowPage.fillWorkflowPage("Case Due Date", null, "December 2022", "1");
        workflowPage.chooseButton("Continue");
        Assert.assertEquals(contactsInformationPage.contactsTitle(), "Contacts");
        contactsInformationPage.chooseContact("NSO COMAPNY");
        contactsInformationPage.chooseButton("Continue");
        Assert.assertEquals(emailAndFormsPage.emailAndFormsTitle(), "E-mail");
        emailAndFormsPage.chooseButton("Continue");
        emailAndFormsPage.chooseButtonCasesCreation("Confirm");
        emailAndFormsPage.chooseButtonCasesCreation("Open the Case");
        casePage.chooseTaskNameFromList("Task Name", "Individual Risk Hits");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Open");
        rdcTaskPage.chooseButton("Save and Send query");
        Assert.assertTrue(rdcTaskPage.hitsFoundMessage().contains("hits found.Please mark each hit as True or False. Click on each hit for further information"));
        rdcTaskPage.clickOnReturnRow();
        rdcTaskPage.popupMarkButtonByRow("Atomwaffen Division", "true");
        rdcTaskPage.popupMarkButtonByRow("NSO Group Technologies", "true");
        rdcTaskPage.popupMarkButtonByRow("NSO", "false");
        rdcTaskPage.popupMarkButtonByRow("NSO", "false");
        rdcTaskPage.popupMarkButtonByRow("NSO", "false");
        rdcTaskPage.popupMarkButtonByRow("NSO Group", "false");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Done");
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.clearButton();
        entitiesPage.searchFiled("NSO");
        entitiesPage.chooseEntityName("NSO");
        entityPage.chooseFromSideBar("Risk Profile");
     //   Assert.assertEquals(entityPage.numberOfRows() - 2, before);
    }


    @Test(description = "Create Case with Task for rdc Organization after Edit search fields")
    @Description("Workflow Builder - Create Case with rdc task for Organization after Edit search fields")
    public void Test_11_createRdcCaseForOrganizationAfterEditSearchFields() {
        navigationPage.clickOnBackToBtrustUserButton();
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.clearButton();
        entitiesPage.searchFiled("NSO");
        entitiesPage.chooseEntityName("NSO");
 //       entityPage.chooseFromSideBar("Risk Profile");
 //       int before = entityPage.numberOfRows();
        entityPage.chooseFromSideBar("Cases");
        entityPage.createNewCaseButton();
        Assert.assertEquals(workflowPage.workflowTitle(), "Workflow");
        workflowPage.fillWorkflowPage("Workflow", "rdc organization", null, null);
        workflowPage.fillWorkflowPage("Case Due Date", null, "December 2022", "1");
        workflowPage.chooseButton("Continue");
        Assert.assertEquals(contactsInformationPage.contactsTitle(), "Contacts");
        contactsInformationPage.chooseContact("NSO COMAPNY");
        contactsInformationPage.chooseButton("Continue");
        Assert.assertEquals(emailAndFormsPage.emailAndFormsTitle(), "E-mail");
        emailAndFormsPage.chooseButton("Continue");
        emailAndFormsPage.chooseButtonCasesCreation("Confirm");
        emailAndFormsPage.chooseButtonCasesCreation("Open the Case");
        casePage.chooseTaskNameFromList("Task Name", "Individual Risk Hits");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Open");
        Assert.assertTrue(rdcTaskPage.hitsFoundMessage().contains("hits found.Please mark each hit as True or False. Click on each hit for further information"));
        rdcTaskPage.clickOnModifySearch();
        rdcTaskPage.editSearchFieldsForRdc("Company Name", "Osem");
        rdcTaskPage.chooseButton("Save and Send query");
        Assert.assertTrue(rdcTaskPage.hitsFoundMessage().contains("hits found.Please mark each hit as True or False. Click on each hit for further information"));
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Open");
        int changes = rdcTaskPage.markerHandler("Osem Investments Ltd.", "IL", true);
        changes = rdcTaskPage.markerHandler("Osem Group", "BG", true);
        rdcTaskPage.markFalseButtonByRow("Osem", "IL");
        rdcTaskPage.markTruePositiveButtonByRow("Osem Investments Ltd.", "IL");
        rdcTaskPage.markTruePositiveButtonByRow("Osem Group", "BG");
        rdcTaskPage.clickOnCheckbox();
        rdcTaskPage.chooseButton("Confirm");
        Assert.assertEquals(rdcTaskPage.taskStatus(), "Done");
        navigationPage.mainMenuList("Entities");
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.clearButton();
        entitiesPage.searchFiled("NSO");
        entitiesPage.chooseEntityName("NSO");
        entityPage.chooseFromSideBar("Risk Profile");
 //       Assert.assertEquals(entityPage.numberOfRows() - 2, before);
    }


}
