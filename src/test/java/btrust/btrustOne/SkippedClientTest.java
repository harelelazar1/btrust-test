package btrust.btrustOne;

import btrust.btrustOne.client.BaseClientUserTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.cases.pagesObject.CasePage;
import btrust.btrustOne.client.cases.pagesObject.CasesPage;
import btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject.CasesCreationPage;
import btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject.ContactsInformationPage;
import btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject.EmailAndFormsPage;
import btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject.WorkflowPage;
//import btrust.btrustOne.client.docPortal.pagesObject.DocumentsPortalPage;
//import btrust.btrustOne.client.docPortal.pagesObject.OtpPage;
import btrust.btrustOne.client.entity.pagesObject.CreateEntity.CreateEntityLayoutButtonsPage;
import btrust.btrustOne.client.entity.pagesObject.CreateEntity.EntitySetupPage;
import btrust.btrustOne.client.entity.pagesObject.EntitiesPage;
import btrust.btrustOne.client.entity.pagesObject.EntityPage;
import btrust.btrustOne.client.mandate.pagesObject.MandatesPage;
import btrust.btrustOne.client.mandate.pagesObject.createMandate.pagesObject.*;
import btrust.btrustOne.client.search.pagesObject.EntitySearchPage;
import btrust.btrustOne.client.search.pagesObject.SearchPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SkippedClientTest extends BaseClientUserTest {
    SearchPage searchPage;
    EntityPage entityPage;
    EntitySetupPage entitySetupPage;
    CreateEntityLayoutButtonsPage createEntityLayoutButtonsPage;

    MandatesPage mandatesPage;


    @Test(enabled = false, description = "Mandatory fields")
    public void t01_mandatoryFields() {
        ContactsInformationPage contactsInformationPage = new ContactsInformationPage(driver);
        Assert.assertFalse(contactsInformationPage.buttonIsEnabled("Continue"));
    }

    @Test(enabled = false, description = "Return to previous page")
    @Description("Click on 'Back' button and check that the user return to previous page")
    public void t02_returnToPreviousPage() {
        ContactsInformationPage contactsInformationPage = new ContactsInformationPage(driver);
        contactsInformationPage.chooseButton("Back");
        WorkflowPage workflowPage = new WorkflowPage(driver);
        Assert.assertEquals(workflowPage.workflowTitle(), "Workflow");
    }

    @Test(enabled = false, description = "Go out from the create new case flow")
    @Description("Click on 'Cancel' button and check that the user go out from the create new case flow")
    public void t03_goOutFromCreateNewCaseFlow() {
        ContactsInformationPage contactsInformationPage = new ContactsInformationPage(driver);
        contactsInformationPage.chooseButton("Cancel");
        EntityPage entityPage = new EntityPage(driver);
        Assert.assertEquals(entityPage.casesListTitle(), "CASES");
    }

    @Test(enabled = false, description = "Return to previous page")
    @Description("Click on 'Back' button and check that the user return to previous page")
    public void t01_returnToPreviousPage() {
        EmailAndFormsPage emailAndFormsPage = new EmailAndFormsPage(driver);
        emailAndFormsPage.chooseButton("Back");
        ContactsInformationPage contactsInformationPage = new ContactsInformationPage(driver);
        Assert.assertEquals(contactsInformationPage.contactsTitle(), "Contacts");
    }

    @Test(enabled = false, description = "Go out from the create new case flow")
    @Description("Click on 'Cancel' button and check that the user go out from the create new case flow")
    public void t02_goOutFromCreateNewCaseFlow() {
        EmailAndFormsPage emailAndFormsPage = new EmailAndFormsPage(driver);
        emailAndFormsPage.chooseButton("Cancel");
        EntityPage entityPage = new EntityPage(driver);
        Assert.assertEquals(entityPage.casesListTitle(), "CASES");
    }

    @Test(enabled = false, description = "Cancel create new case")
    @Description("Close the popup of accept create new case")
    public void t03_cancelCreateNewCase() {
        EmailAndFormsPage emailAndFormsPage = new EmailAndFormsPage(driver);
        emailAndFormsPage.chooseButton("Continue");
        CasesCreationPage casesCreationPage = new CasesCreationPage(driver);
        Assert.assertEquals(casesCreationPage.casesCreationTitle(), "Cases Creation");
        Assert.assertEquals(casesCreationPage.popupTitle(), "Are you sure?");
        casesCreationPage.closePopupButton();
        Assert.assertEquals(emailAndFormsPage.emailAndFormsTitle(), "E-mail");
        emailAndFormsPage.chooseButton("Continue");
        casesCreationPage.clickOnButton("Cancel");
        Assert.assertEquals(emailAndFormsPage.emailAndFormsTitle(), "E-mail");
    }

    @Test(enabled = false, description = "Back to entity")
    @Description("Click on Back to entity button in cases creation ")
    public void t04_backToEntity() {
        EmailAndFormsPage emailAndFormsPage = new EmailAndFormsPage(driver);
        emailAndFormsPage.chooseButton("Continue");
        CasesCreationPage casesCreationPage = new CasesCreationPage(driver);
        Assert.assertEquals(casesCreationPage.casesCreationTitle(), "Cases Creation");
        casesCreationPage.clickOnButton("Confirm");
        Assert.assertEquals(casesCreationPage.popupDescription("Case created successfully"), "Case created successfully");
        casesCreationPage.clickOnButton("Back to Entity");
        EntityPage entityPage = new EntityPage(driver);
        Assert.assertEquals(entityPage.entityName(), "Liad");
    }

    @Test(enabled = false, description = "Mandatory fields")
    @Description("Check that the user can not continue to next phase without fill the mandatory fields")
    public void t01_wf_mandatoryFields() {
        WorkflowPage workflowPage = new WorkflowPage(driver);
        Assert.assertFalse(workflowPage.buttonIsEnabled("Continue"));
    }

    @Test(enabled = false, description = "Return to previous screen")
    @Description("Click on 'back' & 'cancel' button and check that the user go out from the create new cases flow and go to previous screen")
    public void t02_goOutFromTheFlow() {
        WorkflowPage workflowPage = new WorkflowPage(driver);
        workflowPage.chooseButton("Back");
        EntityPage entityPage = new EntityPage(driver);
        Assert.assertEquals(entityPage.casesListTitle(), "CASES");
        entityPage.createNewCaseButton();
        workflowPage.chooseButton("Cancel");
        Assert.assertEquals(entityPage.casesListTitle(), "CASES");
    }

//    @Test(enabled = false, description = "Verify code by email")
//    @Description("Verify code by email and check that the user enter to doc portal")
//    public void t01_verifyCodeByEmail() {
//        OtpPage otpPage = new OtpPage(driver);
//        otpPage.clickOnButton("Via Email");
//        Assert.assertEquals(otpPage.pleaseCheckYourInboxTitle(), "Please check you inbox");
//        Assert.assertEquals(otpPage.enter6Digits(), "Enter 6 digits code here");
//        otpPage.codeTextBox("1", "2", "3", "4", "5", "6");
//        Assert.assertTrue(otpPage.verifyCodeButtonIsEnabled());
//        otpPage.clickVerifyCodeButton();
//        DocumentsPortalPage documentsPortalPage = new DocumentsPortalPage(driver);
//        Assert.assertTrue(documentsPortalPage.docPortalTitle().contains("Hello"));
//    }
//
//    @Test(enabled = false, description = "Verify code by SMS")
//    @Description("Verify code by SMS and check that the user enter to doc portal")
//    public void t02_verifyCodeBySMS() {
//        OtpPage otpPage = new OtpPage(driver);
//        otpPage.clickOnButton("Via SMS");
//        Assert.assertEquals(otpPage.pleaseCheckYourInboxTitle(), "The code was sent by SMS to");
//        Assert.assertEquals(otpPage.enter6Digits(), "Enter 6 digits code here");
//        otpPage.codeTextBox("1", "2", "3", "4", "5", "6");
//        otpPage.clickVerifyCodeButton();
//        DocumentsPortalPage documentsPortalPage = new DocumentsPortalPage(driver);
//        Assert.assertTrue(documentsPortalPage.docPortalTitle().contains("Hello"));
//    }
//
//    @Test(enabled = false, description = "2 attempts for entering the right code")
//    @Description("2 attempts for entering the right code")
//    public void t03_reset2Attempts() {
//        OtpPage otpPage = new OtpPage(driver);
//        otpPage.clickOnButton("Via Email");
//        otpPage.codeTextBox("1", "2", "2", "1", "2", "1");
//        otpPage.clickVerifyCodeButton();
//        Assert.assertEquals(otpPage.wrongCodeMessage("Wrong Code!"), "Wrong Code!");
//        otpPage.deleteOtpCode();
//        otpPage.codeTextBox("1", "2", "2", "1", "2", "1");
//        otpPage.clickVerifyCodeButton();
//        Assert.assertEquals(otpPage.wrongCodeMessage("Wrong Code!"), "Wrong Code!");
//        otpPage.deleteOtpCode();
//        otpPage.codeTextBox("1", "2", "3", "4", "5", "6");
//        otpPage.clickVerifyCodeButton();
//        DocumentsPortalPage documentsPortalPage = new DocumentsPortalPage(driver);
//        Assert.assertTrue(documentsPortalPage.docPortalTitle().contains("Hello"));
//    }
//
//    @Test(enabled = false, description = "3 attempts for entering wrong code and wait 1 min")
//    @Description("3 attempts for entering wrong code and wait 1 min")
//    public void t04_lock1MinAfter3WrongCodes() {
//        OtpPage otpPage = new OtpPage(driver);
//        otpPage.clickOnButton("Via SMS");
//        otpPage.codeTextBox("1", "2", "2", "1", "2", "1");
//        otpPage.clickVerifyCodeButton();
//        Assert.assertEquals(otpPage.wrongCodeMessage("Wrong Code!"), "Wrong Code!");
//        otpPage.deleteOtpCode();
//        otpPage.codeTextBox("1", "2", "2", "1", "2", "1");
//        otpPage.clickVerifyCodeButton();
//        Assert.assertEquals(otpPage.wrongCodeMessage("Wrong Code!"), "Wrong Code!");
//        otpPage.deleteOtpCode();
//        otpPage.codeTextBox("1", "2", "2", "1", "2", "1");
//        otpPage.clickVerifyCodeButton();
//        Assert.assertEquals(otpPage.moreThan3AttemptsPhrase("More than 3 attempts,Try again later"), "More than 3 attempts,Try again later");
//        otpPage.sleep(62000); // wait 1 min before try again
//        otpPage.codeTextBox("1", "2", "3", "4", "5", "6");
//        otpPage.clickVerifyCodeButton();
//        DocumentsPortalPage documentsPortalPage = new DocumentsPortalPage(driver);
//        Assert.assertTrue(documentsPortalPage.pleaseProvideTheFollowingInformationTitle("Please provide the following information"));
//    }

    @Test(enabled = false, description = "Filter by under evaluation status")
    @Description("Perform filter to entities list by entity status select - under evaluation")
    public void t04_filterByEntityUnderEvaluationStatus() {
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        entitiesPage.filterEntitiesTable("box", null, "Under Evaluation");
        entitiesPage.searchFiled("Entity");
        if (entitiesPage.underEvaluationValueBox() > 0 && entitiesPage.countTurnOnBoxes() == 2) {
            Assert.assertEquals(entitiesPage.entityStatus(), "Under Evaluation");
            Assert.assertEquals(entitiesPage.totalEntitiesValueBox(), entitiesPage.sumEntitiesBox());
            Assert.assertEquals(entitiesPage.totalEntitiesValueBox(), entitiesPage.underEvaluationValueBox());
//            Assert.assertEquals(entitiesPage.counterEntitiesList(), entitiesPage.underEvaluationValueBox());
            Assert.assertFalse(entitiesPage.clearButtonIsDisplayed());
        } else if (entitiesPage.underEvaluationValueBox() == 0) {
            Assert.assertTrue(entitiesPage.noResultIcon());
        }
    }

    @Test(enabled = false, description = "Filter by approved status")
    @Description("Perform filter to entities list by entity status select - Approved")
    public void t05_filterByEntityApprovedStatus() {
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        entitiesPage.filterEntitiesTable("box", null, "Approved");
        if (entitiesPage.approvedValueBox() > 0 && entitiesPage.countTurnOnBoxes() == 2) {
            Assert.assertEquals(entitiesPage.entityStatus(), "Approved");
            Assert.assertEquals(entitiesPage.totalEntitiesValueBox(), entitiesPage.sumEntitiesBox());
            Assert.assertEquals(entitiesPage.totalEntitiesValueBox(), entitiesPage.approvedValueBox());
            Assert.assertEquals(entitiesPage.counterEntitiesList(), entitiesPage.approvedValueBox());
            Assert.assertFalse(entitiesPage.clearButtonIsDisplayed());
        } else if (entitiesPage.approvedValueBox() == 0) {
            Assert.assertTrue(entitiesPage.noResultIcon());
        }
    }

    @Test(enabled = false, description = "Filter by suspended status")
    @Description("Perform filter to entities list by entity status select - suspended")
    public void t06_filterByEntitySuspendedStatus() throws InterruptedException {
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        entitiesPage.filterEntitiesTable("box", null, "Suspended");
        if (entitiesPage.suspendedValueBox() > 0 && entitiesPage.countTurnOnBoxes() == 2) {
            Assert.assertEquals(entitiesPage.entityStatus(), "Suspended");
            Assert.assertEquals(entitiesPage.totalEntitiesValueBox(), entitiesPage.sumEntitiesBox());
            Assert.assertEquals(entitiesPage.totalEntitiesValueBox(), entitiesPage.suspendedValueBox());
            Assert.assertEquals(entitiesPage.counterEntitiesList(), entitiesPage.suspendedValueBox());
            Assert.assertFalse(entitiesPage.clearButtonIsDisplayed());
        } else if (entitiesPage.suspendedValueBox() == 0 && entitiesPage.countTurnOnBoxes() == 2) {
            Thread.sleep(5000);
            Assert.assertTrue(entitiesPage.noResultIcon());
        }
    }

    @Test(enabled = false, description = "Filter by rejected status")
    @Description("Perform filter to entities list by entity status select - rejected")
    public void t07_filterByEntityRejectedStatus() {
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        entitiesPage.filterEntitiesTable("box", null, "Rejected");
        if (entitiesPage.rejectedValueBox() > 0 && entitiesPage.countTurnOnBoxes() == 2) {
            Assert.assertEquals(entitiesPage.entityStatus(), "Rejected");
            Assert.assertEquals(entitiesPage.totalEntitiesValueBox(), entitiesPage.sumEntitiesBox());
            Assert.assertEquals(entitiesPage.totalEntitiesValueBox(), entitiesPage.rejectedValueBox());
            Assert.assertEquals(entitiesPage.counterEntitiesList(), entitiesPage.rejectedValueBox());
            Assert.assertFalse(entitiesPage.clearButtonIsDisplayed());
        } else if (entitiesPage.rejectedValueBox() == 0) {
            Assert.assertTrue(entitiesPage.noResultIcon());
        }
    }

    @Test(enabled = false, description = "Filter by open cases")
    @Description("Perform filter to entities list by open cases select")
    public void t08_filterByOpenCases() {
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        entitiesPage.filterEntitiesTable("filter", "Case Type", "Fatca Re-Certification");

        Assert.assertEquals(entitiesPage.totalEntitiesValueBox(), entitiesPage.sumEntitiesBox());

        entitiesPage.clearButton();
        Assert.assertFalse(entitiesPage.clearButtonIsDisplayed());
    }

    @Test(enabled = false, description = "Filter by search field")
    @Description("Perform filter to entities list by search field")
    public void t09_filterBySearchField() {
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        entitiesPage.searchFiled("italy office");

        Assert.assertEquals(entitiesPage.entityName(), "italy office");
        Assert.assertEquals(entitiesPage.totalEntitiesValueBox(), entitiesPage.sumEntitiesBox());
        Assert.assertEquals(entitiesPage.totalEntitiesValueBox(), entitiesPage.counterEntitiesList());
    }

    @Test(enabled = false, description = "Filter by all filters")
    @Description("Perform filter to entities list by all filters")
    public void t10_filterByAllFilters() {
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        entitiesPage.filterEntitiesTable("filter", "Entity Type", "Organization");
        entitiesPage.filterEntitiesTable("filter", "Business Category", "Broker");
        entitiesPage.filterEntitiesTable("filter", "Case Type", "Brokers re-certification");
        entitiesPage.searchFiled("ZXC");

        Assert.assertEquals(entitiesPage.entityName(), "ZXC");
    }

    @Test(enabled = false, description = "No result message")
    @Description("Search entity that not exist in the list and check that no result elements appear")
    public void t11_noResult() {
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        entitiesPage.searchFiled("BlaTest");

        Assert.assertTrue(entitiesPage.noResultIcon());
        Assert.assertEquals(entitiesPage.noResultMessage(), "We didn’t find what you were looking for");
    }

    @Test(enabled = false, description = "Cancel mandate creation")
    @Description("Click on cancel button and check that return to mandates page")
    public void t01_cancelMandateCreation() {
        MandateAndWorkflowPage mandateAndWorkflowPage = new MandateAndWorkflowPage(driver);
        mandateAndWorkflowPage.chooseButton("Cancel");

        CancelMandateCreationPage cancelMandateCreationPage = new CancelMandateCreationPage(driver);
        Assert.assertEquals(cancelMandateCreationPage.cancelMandateCreationTitle(), "Cancel Mandate Creation");
        Assert.assertEquals(cancelMandateCreationPage.popupTitle(), "Are you sure you want to cancel the mandate creation?");
        Assert.assertEquals(cancelMandateCreationPage.popupDescription(), "By canceling the process the platform will not save any changes that were made");
        cancelMandateCreationPage.popupButtons("Confirm");

        MandatesPage mandatesPage = new MandatesPage(driver);
        Assert.assertEquals(mandatesPage.mandatesTitle("Mandates"), "Mandates");
    }

    @Test(enabled = false, description = "Not Cancel mandate creation")
    @Description("Click on cancel button in the popup to cancel the create mandate and check that return to flow to create new mandate")
    public void t02_notCancelMandateCreation() {
        MandateAndWorkflowPage mandateAndWorkflowPage = new MandateAndWorkflowPage(driver);
        mandateAndWorkflowPage.chooseButton("Cancel");
        CancelMandateCreationPage cancelMandateCreationPage = new CancelMandateCreationPage(driver);
        cancelMandateCreationPage.popupButtons("Cancel");

        Assert.assertEquals(mandateAndWorkflowPage.mandateInformationTitle(), "Mandate Information");
    }

    @Test(enabled = false,description = "Create new case")
    @Description("Create new case from exist entity")
    public void t01_createNewCase() {
        String caseId;
        WorkflowPage workflowPage = new WorkflowPage(driver);
        workflowPage.fillWorkflowPage("Workflow", "Test", null, null);
        workflowPage.fillWorkflowPage("Case Due Date", null, "October 2025", "20");
        workflowPage.chooseButton("Continue");
        ContactsInformationPage contactsInformationPage = new ContactsInformationPage(driver);
        Assert.assertEquals(contactsInformationPage.contactsTitle(), "Contacts");
        Assert.assertEquals(contactsInformationPage.contactsDescription(), "Include contacts who will receive the email conditions");
        contactsInformationPage.chooseContact("Liad");
        contactsInformationPage.chooseButton("Continue");
        EmailAndFormsPage emailAndFormsPage = new EmailAndFormsPage(driver);
        Assert.assertEquals(emailAndFormsPage.emailAndFormsTitle(), "E-mail");
        emailAndFormsPage.chooseButton("Continue");
        CasesCreationPage casesCreationPage = new CasesCreationPage(driver);
        Assert.assertEquals(casesCreationPage.casesCreationTitle(), "Cases Creation");
        casesCreationPage.clickOnButton("Confirm");
        Assert.assertEquals(casesCreationPage.popupDescription("Case created successfully"), "Case created successfully");
        casesCreationPage.clickOnButton("Open the Case");
        CasePage casePage = new CasePage(driver);
        Assert.assertEquals(casePage.caseName(), "Liad");
        caseId = casePage.caseId();
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Cases");
        CasesPage casesPage = new CasesPage(driver);
        Assert.assertEquals(casesPage.firstCaseId(), caseId);
        navigationPage.mainMenuList("Entities");
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.filterEntitiesTable("box", null, "Under Evaluation");
        entitiesPage.chooseEntity();
        EntityPage entityPage = new EntityPage(driver);
        Assert.assertEquals(entityPage.entityName(), "Liad");
        entityPage.chooseFromSideBar("Cases");
        Assert.assertEquals(entityPage.casesListTitle(), "CASES");
        Assert.assertTrue(entityPage.firstCaseId("Case no.").contains(caseId));
    }
    @Step("Check that all the data that appear in the entity that created when the user created new mandate is true")
    public void verifyMandateData() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Entities");
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.chooseEntity();
        EntityPage entityPage = new EntityPage(driver);
        Assert.assertEquals(entityPage.entityName(), "Liad");

        Assert.assertEquals(entityPage.dataTitles("Identification Data"), "Identification Data");
        Assert.assertEquals(entityPage.identificationData("LEGAL NAME", "Liad"), "Liad");
        Assert.assertEquals(entityPage.identificationData("LEI CODE", leiCode), leiCode);
        Assert.assertEquals(entityPage.identificationData("VAT CODE TAX IDENTIFICATION NUMBER", "123"), "123");

        Assert.assertEquals(entityPage.dataTitles("Addresses"), "Addresses");
        Assert.assertEquals(entityPage.addressData("ADDRESS", "789"), "789");
        Assert.assertEquals(entityPage.addressData("CITY", "Ago"), "Ago");
    }


    @Test(enabled = false,description = "Check that created new entity and new case")
    @Description("Create new mandate, check that create a new entity and check that all the data is true")
    public void t01_checkThatCreatedNewEntityAndNewCase() {
        String caseId;
        MandatesPage mandatesPage = new MandatesPage(driver);
        mandatesPage.filterMandateTable("Status", "Under Investigation");
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Cases");
        CasesPage casesPage = new CasesPage(driver);
        Assert.assertTrue(casesPage.casesTitle());
        caseId = casesPage.firstCaseId();
        verifyMandateData();

        EntityPage entityPage = new EntityPage(driver);
        entityPage.chooseFromSideBar("Cases");
        Assert.assertTrue(entityPage.firstCaseId("CASE NO.").contains(caseId));
    }

    @Test(enabled = false,description = "Create new mandate from entity")
    @Description("Enter to entity and create new mandate from mandates tab")
    public void t02_createNewMandateFromEntity() {
        MandatesPage mandatesPage = new MandatesPage(driver);
        mandatesPage.filterMandateTable("Status", "Under Investigation");
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Entities");
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.chooseEntity();
        EntityPage entityPage = new EntityPage(driver);
        Assert.assertEquals(entityPage.entityName(), "Liad");
    }

    @Test(enabled = false,description = "Create new mandate from no result message")
    @Description("Click on create new mandate button that appear in no result message and create new mandate")
    public void t03_createNewMandateFromNoMessage() {
        MandatesPage mandatesPage = new MandatesPage(driver);
        mandatesPage.searchField("Create new mandate from no result message");
        mandatesPage.createNewMandateButton();
        createMandate();
        Assert.assertEquals(mandatesPage.mandatesTitle("Mandates"), "Mandates");
        verifyMandateData();
    }

    // bug "https://scanovate.atlassian.net/browse/BD-217"
    @Test(enabled = false, description = "create new entity with matching identifiers from another entity, choose to go to the existing entity")
    @Description("create new entity with matching identifiers from another entity, warning popup should appear - back to edit and then go to the existing entity")
    public void t02_createNewEntityWithMatchingIdentifiersAndGoToTheExistingEntity() {
        //search non existing entity in the search page
        searchPage.searchEntity("Organization", "newEntity" + randomString());
        Assert.assertEquals(searchPage.NoSuitableEntitiesWereMatchedYourSearch(), "No suitable entities were matched your search");
        searchPage.createNewEntityButton();

        //getting to the new entity builder flow
        Assert.assertEquals(entitySetupPage.entityInformationTitle(), "Select Entity Type and Category");
        entitySetupPage.selectEntityType("Organization");
        entitySetupPage.selectBusinessCategory("Client");

        //fill the mandatory fields page
        Assert.assertEquals(entitySetupPage.organizationClientInformationTitle("Organization-Client information"), "Organization-Client information");
        entitySetupPage.fillMandatoryTypeFieldsForEntity("LEI Code", "34223424");
        entitySetupPage.fillMandatoryTypeFieldsForEntity("legal_name", "Liad");
        entitySetupPage.fillMandatorySelectFieldsForEntity("test", "Italy");
        entitySetupPage.fillMandatoryTypeFieldsForEntity("babble bubble insert 4 digits only", "1111");
        createEntityLayoutButtonsPage.clickOnButton("Continue");

        Assert.assertEquals(entitySetupPage.entityIdentifierExistsTitle(), "Entity identifier exists");
        Assert.assertEquals(entitySetupPage.entityIdentifierExistErrorMessage(), "The system has found entities with matching identifiers to the entity you are trying to add");
        entitySetupPage.backToEditButton();
        Assert.assertEquals(entitySetupPage.organizationClientInformationTitle("Organization-Client information"), "Organization-Client information");
        createEntityLayoutButtonsPage.clickOnButton("Continue");
        entitySetupPage.clickOnExistingEntityName("Liad");
        Assert.assertEquals(entityPage.entityName(), "Liad");
    }

    @Test(enabled = false, description = "Empty fields")
    @Description("Click on continue button when the mandatory fields is empty and check that error message is displayed")
    public void t01_info_page_emptyFields() {
        ClientInformationPage clientInformationPage = new ClientInformationPage(driver);
        clientInformationPage.chooseButton("Continue");

        Assert.assertEquals(clientInformationPage.errorMessageUnderSelect("Relationship Manager"), "This field is required");
        Assert.assertEquals(clientInformationPage.errorMessageUnderInputs("Legal Name"), "Please type an organization name");
        Assert.assertEquals(clientInformationPage.errorMessageLegalNatureOfClient(), "This field is required");
    }

    @Test(enabled = false, description = "Incorrect characters")
    @Description("Click on continue button when type incorrect characters in the fields and check that error messages is displayed")
    public void t02_info_page_incorrectCharacters() {
        ClientInformationPage clientInformationPage = new ClientInformationPage(driver);
        clientInformationPage.chooseOptionFromSelect("Relationship Manager", "TestSub");
        clientInformationPage.typeInField("Legal Name", "א");
        clientInformationPage.typeInField("VAT Code/ Tax Identification Number", "a");
        clientInformationPage.typeInField("LEI Code", "א");
        clientInformationPage.typeInField("Address", "א");
        clientInformationPage.fillBusinessPhone("Israel", "1");
        clientInformationPage.typeInField("Civic Number", "a");
        clientInformationPage.chooseLegalNatureOfTheClient("Limited company");
        clientInformationPage.chooseButton("Continue");

        Assert.assertEquals(clientInformationPage.errorMessageUnderInputs("Legal Name"), "Please type a valid organization name that includes letters and numbers only");
        Assert.assertEquals(clientInformationPage.errorMessageUnderInputs("VAT Code/ Tax Identification Number"), "Please type a valid VAT Code/TIN that includes numbers only");
        Assert.assertEquals(clientInformationPage.errorMessageUnderInputs("LEI Code"), "Please type a valid LEI code that includes letters and numbers only");
        Assert.assertEquals(clientInformationPage.errorMessageUnderInputs("Address"), "Please type a valid address that includes letters and numbers only");
        Assert.assertEquals(clientInformationPage.errorMessageBusinessPhone(), "Please type a valid phone number that consist of 7 digits or more");
        Assert.assertEquals(clientInformationPage.errorMessageUnderInputs("Civic Number"), "Please type a valid postal code that includes numbers only");
    }

    @Test(enabled = false, description = "Type less than the required number of characters")
    @Description("Click on continue button when type less than the required number of characters and check that error message is displayed")
    public void t03_info_page_typeLessThanTheRequiredNumberOfCharacters() {
        ClientInformationPage clientInformationPage = new ClientInformationPage(driver);
        clientInformationPage.chooseOptionFromSelect("Relationship Manager", "TestSub");
        clientInformationPage.typeInField("Legal Name", "a");
        clientInformationPage.typeInField("VAT Code/ Tax Identification Number", "123");
        clientInformationPage.typeInField("LEI Code", "a1");
        clientInformationPage.typeInField("Address", "a");
        clientInformationPage.fillBusinessPhone("Israel", "0547446070");
        clientInformationPage.typeInField("Civic Number", "1");
        clientInformationPage.chooseLegalNatureOfTheClient("Limited company");
        clientInformationPage.chooseButton("Continue");

        Assert.assertEquals(clientInformationPage.errorMessageUnderInputs("Legal Name"), "Please type a valid organization name that includes 2 characters and more");
    }

    @Test(enabled = false, description = "Return to back mandate & workflow tab")
    @Description("Click on back button and check that the user back to mandate & workflow tab")
    public void t04_returnToMandateAndWorkflowTab() {
        ClientInformationPage clientInformationPage = new ClientInformationPage(driver);
        clientInformationPage.chooseButton("Back");

        MandateAndWorkflowPage mandateAndWorkflowPage = new MandateAndWorkflowPage(driver);
        Assert.assertEquals(mandateAndWorkflowPage.mandateInformationTitle(), "Mandate Information");
    }
    @Test(enabled = false, description = "Check data")
    @Description("Fill the mandate information and check that all the data that the user filled appear in the confirmation tab")
    public void t01_checkData() {
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        Assert.assertEquals(confirmationPage.getValue("RELATIONSHIP NUMBER", relationshipNumber), relationshipNumber);
        Assert.assertEquals(confirmationPage.getValue("BUSINESS NATURE", "Portfolio Management"), "Portfolio Management");
        Assert.assertEquals(confirmationPage.getValue("WORKFLOW", "AML Recertification"), "AML Recertification");
        Assert.assertEquals(confirmationPage.getValue("ONBOARDING OFFICE", "Italy"), "Italy");

        Assert.assertEquals(confirmationPage.getValue("RELATIONSHIP MANAGER", "TestSub"), "TestSub");
        Assert.assertEquals(confirmationPage.clientNameValue(), "Liad");
        Assert.assertEquals(confirmationPage.getValue("TIN", "123"), "123");
        Assert.assertEquals(confirmationPage.getValue("LEI CODE", "456"), "456");
        Assert.assertEquals(confirmationPage.getValue("ADDRESS", "789"), "789");
        Assert.assertTrue(confirmationPage.getValue("PHONE", "0501234567").contains("0501234567"));
        Assert.assertEquals(confirmationPage.getValue("COUNTRY", "Japan"), "Japan");
        Assert.assertEquals(confirmationPage.getValue("CITY", "Ago"), "Ago");
        Assert.assertEquals(confirmationPage.getValue("CIVIC NUMBER", "135"), "135");

        Assert.assertTrue(confirmationPage.getContactValue("FULL NAME", "Liad Tobi").contains("Liad Tobi"));
        Assert.assertEquals(confirmationPage.getContactValue("POSITION", "QA"), "QA");
        Assert.assertEquals(confirmationPage.getContactValue("EMAIL", "automation@automatio"), "automation@automatio...");
        Assert.assertTrue(confirmationPage.getContactValue("BUSINESS PHONE", "0501234567").contains("0501234567"));
        Assert.assertTrue(confirmationPage.getContactValue("SECOND PHONE", "0501234567").contains("0501234567"));
        Assert.assertTrue(confirmationPage.getContactValue("FAX", "0501234567").contains("0501234567"));
    }

    @Test(enabled = false, description = "Edit mandate")
    @Description("Edit mandate details and check that the information appear in confirmation tab")
    public void t02_editMandate() {
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        Assert.assertEquals(confirmationPage.getValue("RELATIONSHIP NUMBER", relationshipNumber), relationshipNumber);
        Assert.assertEquals(confirmationPage.getValue("BUSINESS NATURE", "Portfolio Management"), "Portfolio Management");
        Assert.assertEquals(confirmationPage.getValue("WORKFLOW", "AML Recertification"), "AML Recertification");
        Assert.assertEquals(confirmationPage.getValue("ONBOARDING OFFICE", "Italy"), "Italy");
        Assert.assertEquals(confirmationPage.getValue("RELATIONSHIP MANAGER", "TestSub"), "TestSub");
        Assert.assertEquals(confirmationPage.clientNameValue(), "Liad");
        Assert.assertEquals(confirmationPage.getValue("TIN", "123"), "123");
        Assert.assertEquals(confirmationPage.getValue("LEI CODE", "456"), "456");
        Assert.assertEquals(confirmationPage.getValue("ADDRESS", "789"), "789");
        Assert.assertTrue(confirmationPage.getValue("PHONE", "0501234567").contains("0501234567"));
        Assert.assertEquals(confirmationPage.getValue("COUNTRY", "Japan"), "Japan");
        Assert.assertEquals(confirmationPage.getValue("CITY", "Ago"), "Ago");
        Assert.assertEquals(confirmationPage.getValue("CIVIC NUMBER", "135"), "135");
        Assert.assertTrue(confirmationPage.getContactValue("FULL NAME", "Liad Tobi").contains("Liad Tobi"));
        Assert.assertEquals(confirmationPage.getContactValue("POSITION", "QA"), "QA");
        Assert.assertEquals(confirmationPage.getContactValue("EMAIL", "automation@automatio"), "automation@automatio...");
        Assert.assertTrue(confirmationPage.getContactValue("BUSINESS PHONE", "0501234567").contains("0501234567"));
        Assert.assertTrue(confirmationPage.getContactValue("SECOND PHONE", "0501234567").contains("0501234567"));
        Assert.assertTrue(confirmationPage.getContactValue("FAX", "0501234567").contains("0501234567"));

        confirmationPage.chooseEditButton("Mandate Details", "Edit Mandate");
        MandateAndWorkflowPage mandateAndWorkflowPage = new MandateAndWorkflowPage(driver);
        Assert.assertEquals(mandateAndWorkflowPage.mandateInformationTitle(), "Mandate Information");
        mandateAndWorkflowPage.fillValueInRelationshipNumberField(mandateAndWorkflowPage.randomString());
        relationshipNumber = mandateAndWorkflowPage.getRelationshipValue();
        mandateAndWorkflowPage.chooseOptionFromSelect("Business Nature Relationship", "Portfolio Management");
        mandateAndWorkflowPage.chooseOptionFromSelect("Select Workflow", "GIAM - Mandate's Onboarding");
        mandateAndWorkflowPage.chooseDueDate("April 2022", "13");
        mandateAndWorkflowPage.chooseOptionFromSelect("Onboarding Office", "Germany");
        mandateAndWorkflowPage.chooseButton("Continue");
        ClientInformationPage clientInformationPage = new ClientInformationPage(driver);
        Assert.assertEquals(clientInformationPage.clientInformationTitle("Client Information"), "Client Information");
        clientInformationPage.chooseButton("Continue");
        ContactInformationPage contactInformationPage = new ContactInformationPage(driver);
        Assert.assertEquals(contactInformationPage.contactsTitle("Contacts"), "Contacts");
        contactInformationPage.chooseButton("Continue");
        EmailAndFormPage emailAndFormPage = new EmailAndFormPage(driver);
        Assert.assertEquals(emailAndFormPage.emailAndFormRequiredTitle("Email and Forms Required"), "Email and Forms Required");
        emailAndFormPage.chooseButton("Continue");

        Assert.assertEquals(confirmationPage.confirmationTile("We're Almost There"), "We're Almost There");
        Assert.assertEquals(confirmationPage.getValue("RELATIONSHIP NUMBER", relationshipNumber), relationshipNumber);
        Assert.assertEquals(confirmationPage.getValue("BUSINESS NATURE", "Portfolio Management"), "Portfolio Management");
        Assert.assertEquals(confirmationPage.getValue("WORKFLOW", "GIAM - Mandate's Onboarding"), "GIAM - Mandate's Onboarding");
        Assert.assertEquals(confirmationPage.getValue("ONBOARDING OFFICE", "Germany"), "Germany");
        Assert.assertEquals(confirmationPage.getValue("RELATIONSHIP MANAGER", "TestSub"), "TestSub");
        Assert.assertEquals(confirmationPage.clientNameValue(), "Liad");
        Assert.assertEquals(confirmationPage.getValue("TIN", "123"), "123");
        Assert.assertEquals(confirmationPage.getValue("LEI CODE", "456"), "456");
        Assert.assertEquals(confirmationPage.getValue("ADDRESS", "789"), "789");
        Assert.assertTrue(confirmationPage.getValue("PHONE", "0501234567").contains("0501234567"));
        Assert.assertEquals(confirmationPage.getValue("COUNTRY", "Japan"), "Japan");
        Assert.assertEquals(confirmationPage.getValue("CITY", "Ago"), "Ago");
        Assert.assertEquals(confirmationPage.getValue("CIVIC NUMBER", "135"), "135");
        Assert.assertTrue(confirmationPage.getContactValue("FULL NAME", "Liad Tobi").contains("Liad Tobi"));
        Assert.assertEquals(confirmationPage.getContactValue("POSITION", "QA"), "QA");
        Assert.assertEquals(confirmationPage.getContactValue("EMAIL", "automation@automatio"), "automation@automatio...");
        Assert.assertTrue(confirmationPage.getContactValue("BUSINESS PHONE", "0501234567").contains("0501234567"));
        Assert.assertTrue(confirmationPage.getContactValue("SECOND PHONE", "0501234567").contains("0501234567"));
        Assert.assertTrue(confirmationPage.getContactValue("FAX", "0501234567").contains("0501234567"));
    }

    @Test(enabled = false, description = "Edit client")
    @Description("Edit client and check that the information appear in confirmation tab")
    public void t03_editClient() {
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        Assert.assertEquals(confirmationPage.getValue("RELATIONSHIP NUMBER", relationshipNumber), relationshipNumber);
        Assert.assertEquals(confirmationPage.getValue("BUSINESS NATURE", "Portfolio Management"), "Portfolio Management");
        Assert.assertEquals(confirmationPage.getValue("WORKFLOW", "AML Recertification"), "AML Recertification");
        Assert.assertEquals(confirmationPage.getValue("ONBOARDING OFFICE", "Italy"), "Italy");
        Assert.assertEquals(confirmationPage.getValue("RELATIONSHIP MANAGER", "TestSub"), "TestSub");
        Assert.assertEquals(confirmationPage.clientNameValue(), "Liad");
        Assert.assertEquals(confirmationPage.getValue("TIN", "123"), "123");
        Assert.assertEquals(confirmationPage.getValue("LEI CODE", "456"), "456");
        Assert.assertEquals(confirmationPage.getValue("ADDRESS", "789"), "789");
        Assert.assertTrue(confirmationPage.getValue("PHONE", "0501234567").contains("0501234567"));
        Assert.assertEquals(confirmationPage.getValue("COUNTRY", "Japan"), "Japan");
        Assert.assertEquals(confirmationPage.getValue("CITY", "Ago"), "Ago");
        Assert.assertEquals(confirmationPage.getValue("CIVIC NUMBER", "135"), "135");
        Assert.assertTrue(confirmationPage.getContactValue("FULL NAME", "Liad Tobi").contains("Liad Tobi"));
        Assert.assertEquals(confirmationPage.getContactValue("POSITION", "QA"), "QA");
        Assert.assertEquals(confirmationPage.getContactValue("EMAIL", "automation@automatio"), "automation@automatio...");
        Assert.assertTrue(confirmationPage.getContactValue("BUSINESS PHONE", "0501234567").contains("0501234567"));
        Assert.assertTrue(confirmationPage.getContactValue("SECOND PHONE", "0501234567").contains("0501234567"));
        Assert.assertTrue(confirmationPage.getContactValue("FAX", "0501234567").contains("0501234567"));

        confirmationPage.chooseEditButton("Mandate's Client", "Edit Client");
        ClientInformationPage clientInformationPage = new ClientInformationPage(driver);
        Assert.assertEquals(clientInformationPage.clientInformationTitle("Client Information"), "Client Information");
        clientInformationPage.chooseOptionFromSelect("Relationship Manager", "TestSub");
        clientInformationPage.typeInField("Legal Name", "LiadEdit");
        clientInformationPage.typeInField("VAT Code/ Tax Identification Number", "111");
        clientInformationPage.typeInField("LEI Code", "222");
        clientInformationPage.typeInField("Address", "333");
        clientInformationPage.chooseOptionFromSelect("Country", "Albania");
        clientInformationPage.chooseOptionFromSelect("City", "Berat");
        clientInformationPage.typeInField("Civic Number", "999");
        clientInformationPage.chooseLegalNatureOfTheClient("Public company");
        clientInformationPage.chooseButton("Continue");
        ContactInformationPage contactInformationPage = new ContactInformationPage(driver);
        Assert.assertEquals(contactInformationPage.contactsTitle("Contacts"), "Contacts");
        contactInformationPage.chooseButton("Continue");
        EmailAndFormPage emailAndFormPage = new EmailAndFormPage(driver);
        Assert.assertEquals(emailAndFormPage.emailAndFormRequiredTitle("Email and Forms Required"), "Email and Forms Required");
        emailAndFormPage.chooseButton("Continue");

        Assert.assertEquals(confirmationPage.confirmationTile("We're Almost There"), "We're Almost There");
        Assert.assertEquals(confirmationPage.getValue("RELATIONSHIP NUMBER", relationshipNumber), relationshipNumber);
        Assert.assertEquals(confirmationPage.getValue("BUSINESS NATURE", "Portfolio Management"), "Portfolio Management");
        Assert.assertEquals(confirmationPage.getValue("WORKFLOW", "AML Recertification"), "AML Recertification");
        Assert.assertEquals(confirmationPage.getValue("ONBOARDING OFFICE", "Italy"), "Italy");
        Assert.assertEquals(confirmationPage.getValue("RELATIONSHIP MANAGER", "TestSub"), "TestSub");
        Assert.assertEquals(confirmationPage.clientNameValue(), "LiadEdit");
        Assert.assertEquals(confirmationPage.getValue("TIN", "111"), "111");
        Assert.assertEquals(confirmationPage.getValue("LEI CODE", "222"), "222");
        Assert.assertEquals(confirmationPage.getValue("ADDRESS", "333"), "333");
        Assert.assertTrue(confirmationPage.getValue("PHONE", "0501234567").contains("0501234567"));
        Assert.assertEquals(confirmationPage.getValue("COUNTRY", "Albania"), "Albania");
        Assert.assertEquals(confirmationPage.getValue("CITY", "Berat"), "Berat");
        Assert.assertEquals(confirmationPage.getValue("CIVIC NUMBER", "999"), "999");
        Assert.assertTrue(confirmationPage.getContactValue("FULL NAME", "Liad Tobi").contains("Liad Tobi"));
        Assert.assertEquals(confirmationPage.getContactValue("POSITION", "QA"), "QA");
        Assert.assertEquals(confirmationPage.getContactValue("EMAIL", "automation@automatio"), "automation@automatio...");
        Assert.assertTrue(confirmationPage.getContactValue("BUSINESS PHONE", "0501234567").contains("0501234567"));
        Assert.assertTrue(confirmationPage.getContactValue("SECOND PHONE", "0501234567").contains("0501234567"));
        Assert.assertTrue(confirmationPage.getContactValue("FAX", "0501234567").contains("0501234567"));
    }

    @Test(enabled = false, description = "Edit contact")
    @Description("Edit contact and check that the information appear in confirmation tab")
    public void t04_editContact() {
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        Assert.assertEquals(confirmationPage.getValue("RELATIONSHIP NUMBER", relationshipNumber), relationshipNumber);
        Assert.assertEquals(confirmationPage.getValue("BUSINESS NATURE", "Portfolio Management"), "Portfolio Management");
        Assert.assertEquals(confirmationPage.getValue("WORKFLOW", "AML Recertification"), "AML Recertification");
        Assert.assertEquals(confirmationPage.getValue("ONBOARDING OFFICE", "Italy"), "Italy");
        Assert.assertEquals(confirmationPage.getValue("RELATIONSHIP MANAGER", "TestSub"), "TestSub");
        Assert.assertEquals(confirmationPage.clientNameValue(), "Liad");
        Assert.assertEquals(confirmationPage.getValue("TIN", "123"), "123");
        Assert.assertEquals(confirmationPage.getValue("LEI CODE", "456"), "456");
        Assert.assertEquals(confirmationPage.getValue("ADDRESS", "789"), "789");
        Assert.assertTrue(confirmationPage.getValue("PHONE", "0501234567").contains("0501234567"));
        Assert.assertEquals(confirmationPage.getValue("COUNTRY", "Japan"), "Japan");
        Assert.assertEquals(confirmationPage.getValue("CITY", "Ago"), "Ago");
        Assert.assertEquals(confirmationPage.getValue("CIVIC NUMBER", "135"), "135");
        Assert.assertTrue(confirmationPage.getContactValue("FULL NAME", "Liad Tobi").contains("Liad Tobi"));
        Assert.assertEquals(confirmationPage.getContactValue("POSITION", "QA"), "QA");
        Assert.assertEquals(confirmationPage.getContactValue("EMAIL", "automation@automatio"), "automation@automatio...");
        Assert.assertTrue(confirmationPage.getContactValue("BUSINESS PHONE", "0501234567").contains("0501234567"));
        Assert.assertTrue(confirmationPage.getContactValue("SECOND PHONE", "0501234567").contains("0501234567"));
        Assert.assertTrue(confirmationPage.getContactValue("FAX", "0501234567").contains("0501234567"));

        confirmationPage.chooseEditButton("Contact Under The Mandate", "Edit Contact");
        ContactInformationPage contactInformationPage = new ContactInformationPage(driver);
        Assert.assertEquals(contactInformationPage.contactsTitle("Contacts"), "Contacts");
        Assert.assertEquals(contactInformationPage.cardTitle("Entity and Mandate’s Contact person"), "Entity and Mandate’s Contact person");
        Assert.assertEquals(contactInformationPage.cardDescription("This contact will be considered as the call back person to all future business with this organization and mandate"), "This contact will be considered as the call back person to all future business with this organization and mandate");
        contactInformationPage.defaultCardTypeInField("First Name", "LiadEdit");
        contactInformationPage.defaultCardTypeInField("Last Name", "TobiEdit");
        contactInformationPage.defaultCardTypeInField("Position", "positionEdit");
        contactInformationPage.defaultCardTypeInField("Email Address", "edit@automation.com");
        contactInformationPage.chooseButton("Continue");
        EmailAndFormPage emailAndFormPage = new EmailAndFormPage(driver);
        Assert.assertEquals(emailAndFormPage.emailAndFormRequiredTitle("Email and Forms Required"), "Email and Forms Required");
        emailAndFormPage.chooseButton("Continue");

        Assert.assertEquals(confirmationPage.confirmationTile("We're Almost There"), "We're Almost There");
        Assert.assertEquals(confirmationPage.getValue("RELATIONSHIP NUMBER", relationshipNumber), relationshipNumber);
        Assert.assertEquals(confirmationPage.getValue("BUSINESS NATURE", "Portfolio Management"), "Portfolio Management");
        Assert.assertEquals(confirmationPage.getValue("WORKFLOW", "AML Recertification"), "AML Recertification");
        Assert.assertEquals(confirmationPage.getValue("ONBOARDING OFFICE", "Italy"), "Italy");
        Assert.assertEquals(confirmationPage.getValue("RELATIONSHIP MANAGER", "TestSub"), "TestSub");
        Assert.assertEquals(confirmationPage.clientNameValue(), "Liad");
        Assert.assertEquals(confirmationPage.getValue("TIN", "123"), "123");
        Assert.assertEquals(confirmationPage.getValue("LEI CODE", "456"), "456");
        Assert.assertEquals(confirmationPage.getValue("ADDRESS", "789"), "789");
        Assert.assertTrue(confirmationPage.getValue("PHONE", "0501234567").contains("0501234567"));
        Assert.assertEquals(confirmationPage.getValue("COUNTRY", "Japan"), "Japan");
        Assert.assertEquals(confirmationPage.getValue("CITY", "Ago"), "Ago");
        Assert.assertEquals(confirmationPage.getValue("CIVIC NUMBER", "135"), "135");
        Assert.assertTrue(confirmationPage.getContactValue("FULL NAME", "LiadEdit").contains("LiadEdit Tobi"));
        Assert.assertEquals(confirmationPage.getContactValue("POSITION", "positionEdit"), "positionEdit");
        Assert.assertEquals(confirmationPage.getContactValue("EMAIL", "edit@automation.com"), "edit@automation.com");
        Assert.assertTrue(confirmationPage.getContactValue("BUSINESS PHONE", "0501234567").contains("0501234567"));
        Assert.assertTrue(confirmationPage.getContactValue("SECOND PHONE", "0501234567").contains("0501234567"));
        Assert.assertTrue(confirmationPage.getContactValue("FAX", "0501234567").contains("0501234567"));
    }

    @Test(enabled = false, description = "Return to previous tab")
    @Description("Click on Back button and check that the user return to previous tab")
    public void t05_returnToPreviousTab() {
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        confirmationPage.chooseButton("Back");
        EmailAndFormPage emailAndFormPage = new EmailAndFormPage(driver);
        Assert.assertEquals(emailAndFormPage.emailAndFormRequiredTitle("Email and Forms Required"), "Email and Forms Required");
    }
    @Test(enabled = false,description = "Empty fields")
    @Description("Click on continue button when the mandatory fields is empty and check that error message is displayed")
    public void t01_emptyFields() {
        ContactInformationPage contactInformationPage = new ContactInformationPage(driver);
        contactInformationPage.chooseButton("Continue");

        Assert.assertEquals(contactInformationPage.errorMessageUnderInputs("First Name"), "Please type a name of minimum 2 letters and more");
        Assert.assertEquals(contactInformationPage.errorMessageUnderInputs("Last Name"), "Please type a name of minimum 2 letters and more");
        Assert.assertEquals(contactInformationPage.errorMessageUnderInputs("Email Address"), "Please type an email address");
    }

    @Test(enabled = false,description = "Incorrect characters")
    @Description("Click on continue button when type incorrect characters in the fields and check that error messages is displayed")
    public void t02_incorrectCharacters() {
        ContactInformationPage contactInformationPage = new ContactInformationPage(driver);
        contactInformationPage.defaultCardTypeInField("First Name", "##");
        contactInformationPage.defaultCardTypeInField("Last Name", "##");
        contactInformationPage.defaultCardTypeInField("Position", "@");
        contactInformationPage.defaultCardFillCallNumber("Business Phone", "Israel", "1");
        contactInformationPage.defaultCardFillCallNumber("Second Phone", "Israel", "1");
        contactInformationPage.defaultCardFillCallNumber("Fax", "Israel", "1");
        contactInformationPage.defaultCardTypeInField("Email Address", "@");
        contactInformationPage.chooseButton("Continue");

        Assert.assertEquals(contactInformationPage.errorMessageUnderInputs("First Name"), "Please type a valid name that includes letters only");
        Assert.assertEquals(contactInformationPage.errorMessageUnderInputs("Last Name"), "Please type a valid name that includes letters only");
        Assert.assertEquals(contactInformationPage.errorMessageUnderInputs("Position"), "Please type a valid position role that includes letters and numbers only");
        //TODO bug "https://trello.com/c/MHtgt9c6"
//        Assert.assertEquals(contactInformationPage.errorMessageUnderSelect("Business Phone"), "Please type a valid phone number");
//        Assert.assertEquals(contactInformationPage.errorMessageUnderSelect("Second Phone"), "Please type a valid phone number");
//        Assert.assertEquals(contactInformationPage.errorMessageUnderSelect("Fax"), "Please type a valid phone number");
        Assert.assertEquals(contactInformationPage.errorMessageUnderInputs("Email Address"), "Please type a valid email address");
    }

    @Test(enabled = false,description = "Type less than the required number of characters")
    @Description("Click on continue button when type less than the required number of characters and check that error message is displayed")
    public void t03_typeLessThanTheRequiredNumberOfCharacters() {
        ContactInformationPage contactInformationPage = new ContactInformationPage(driver);
        contactInformationPage.defaultCardTypeInField("First Name", "a");
        contactInformationPage.defaultCardTypeInField("Last Name", "a");
        contactInformationPage.defaultCardFillCallNumber("Business Phone", "Israel", "111111");
        contactInformationPage.defaultCardFillCallNumber("Second Phone", "Israel", "111111");
        contactInformationPage.defaultCardFillCallNumber("Fax", "Israel", "111111");
        contactInformationPage.chooseButton("Continue");

        Assert.assertEquals(contactInformationPage.errorMessageUnderInputs("First Name"), "Please type a name of minimum 2 letters and more");
        Assert.assertEquals(contactInformationPage.errorMessageUnderInputs("Last Name"), "Please type a name of minimum 2 letters and more");
        Assert.assertEquals(contactInformationPage.errorMessageUnderSelect("Business Phone"), "Please type a valid phone number that consist of 7 digits or more");
        Assert.assertEquals(contactInformationPage.errorMessageUnderSelect("Second Phone"), "Please type a valid phone number that consist of 7 digits or more");
        Assert.assertEquals(contactInformationPage.errorMessageUnderSelect("Fax"), "Please type a valid phone number that consist of 7 digits or more");
    }

    @Test(enabled = false,description = "Return to client information tab")
    @Description("Click on back button and check that the user back to client information tab")
    public void t04_returnToClientInformationTab() {
        ContactInformationPage contactInformationPage = new ContactInformationPage(driver);
        contactInformationPage.chooseButton("Back");

        ClientInformationPage clientInformationPage = new ClientInformationPage(driver);
        Assert.assertEquals(clientInformationPage.clientInformationTitle("Client Information"), "Client Information");
    }

    @Test(enabled = false,description = "Add another contact")
    @Description("Click on Add another contact button and check that new card displayed")
    public void t05_addAnotherContact() {
        ContactInformationPage contactInformationPage = new ContactInformationPage(driver);
        contactInformationPage.chooseButton("Add Another Contact");
        Assert.assertEquals(contactInformationPage.cardTitle("Mandate's Contact"), "Mandate's Contact");
        Assert.assertEquals(contactInformationPage.cardDescription("This contact will be linked to this mandate only"), "This contact will be linked to this mandate only");
        contactInformationPage.addContactCardTypeInField("First Name", "Nitzan");
        contactInformationPage.addContactCardTypeInField("Last Name", "a");
        contactInformationPage.addContactCardTypeInField("Position", "a");
        contactInformationPage.addContactCardFillCallNumber("Business Phone", "Israel", "111111");
        contactInformationPage.addContactCardFillCallNumber("Second Phone", "Israel", "111111");
        contactInformationPage.addContactCardFillCallNumber("Fax", "Israel", "111111");
        contactInformationPage.addContactCardTypeInField("Email Address", "Nitzan@gmail.com");
        contactInformationPage.chooseButton("Remove Contact");

        Assert.assertFalse(contactInformationPage.contactCardIsDisplayed("Mandate's Contact"));
    }

    @Test(enabled = false,description = "Empty fields")
    @Description("Click on continue button when the mandatory fields is empty and check that error message is displayed")
    public void t01_mandate_emptyFields() {
        MandateAndWorkflowPage mandateAndWorkflowPage = new MandateAndWorkflowPage(driver);
        mandateAndWorkflowPage.chooseButton("Continue");

        Assert.assertEquals(mandateAndWorkflowPage.errorMessageUnderSelect("Business Nature Relationship"), "Please select the mandate business nature");
        Assert.assertEquals(mandateAndWorkflowPage.errorMessageUnderSelect("Select Workflow"), "Please select a workflow");
        Assert.assertEquals(mandateAndWorkflowPage.errorMessageUnderDatePicker(), "Please select case due date");
        Assert.assertEquals(mandateAndWorkflowPage.errorMessageUnderSelect("Onboarding Office"), "Please select the onboarding office");
    }

    @Test(enabled = false,description = "Incorrect characters")
    @Description("Click on continue button when type incorrect characters in relationship number field and check that error message is displayed")
    public void t02_mandate_incorrectCharacters() {
        MandateAndWorkflowPage mandateAndWorkflowPage = new MandateAndWorkflowPage(driver);
        mandateAndWorkflowPage.fillValueInRelationshipNumberField("אאאא");
        mandateAndWorkflowPage.chooseButton("Continue");

        Assert.assertEquals(mandateAndWorkflowPage.errorMessageRelationshipNumberField(), "Please type a valid Relationship Number in numbers and English characters");
    }

    @Test(enabled = false,description = "Type less than 4 characters")
    @Description("Click on continue button when type less than 4 characters in relationship number field and check that error message is displayed")
    public void t03_typeLessThan4Characters() {
        MandateAndWorkflowPage mandateAndWorkflowPage = new MandateAndWorkflowPage(driver);
        mandateAndWorkflowPage.fillValueInRelationshipNumberField("abc");
        mandateAndWorkflowPage.chooseButton("Continue");

        Assert.assertEquals(mandateAndWorkflowPage.errorMessageRelationshipNumberField(), "Please type a valid mandate name that includes 4 characters and more");
    }

    @Test(enabled = false,description = "Exist relationship number")
    @Description("Click on continue button when the relationship number is exist in the system and check that error message is displayed")
    public void t04_exitRelationshipNumberMessage() {
        MandateAndWorkflowPage mandateAndWorkflowPage = new MandateAndWorkflowPage(driver);
        mandateAndWorkflowPage.fillValueInRelationshipNumberField("1234");
        mandateAndWorkflowPage.chooseOptionFromSelect("Business Nature Relationship", "Portfolio Management");
        mandateAndWorkflowPage.chooseOptionFromSelect("Select Workflow", "AML Recertification");
        mandateAndWorkflowPage.chooseDueDate("April 2022", "15");
        mandateAndWorkflowPage.chooseOptionFromSelect("Onboarding Office", "Italy");
        mandateAndWorkflowPage.chooseButton("Continue");

        Assert.assertEquals(mandateAndWorkflowPage.errorMessageRelationshipNumberField(), "This relationship number already exists");
    }

    @Test(enabled = false, description = "Filter by status")
    @Description("Filter by status - select Under Investigation")
    public void t02_filterByStatus() {
        mandatesPage.filterMandateTable("Status", "Under Investigation");
        Assert.assertEquals(mandatesPage.mandatesStatus(), "Under Investigation");
    }

    @Test(enabled = false, description = "Filter by Open cases")
    @Description("Filter by Open cases - select NO")
    public void t03_timeFrameFilterOptions() {
        mandatesPage.filterMandateTable("Open Case", "Yes");
        Assert.assertFalse(mandatesPage.mandateOpenCasesList().isEmpty());
        Assert.assertTrue(mandatesPage.mandateTableIsDisplayed());
    }
    @Test(enabled = false,description = "Return to contact information tab")
    @Description("Click on back button and check that the user back to contact information tab")
    public void t01_returnToContactInformationTab() {
        EmailAndFormPage emailAndFormPage = new EmailAndFormPage(driver);
        emailAndFormPage.chooseButton("Back");

        ContactInformationPage contactInformationPage = new ContactInformationPage(driver);
        Assert.assertEquals(contactInformationPage.contactsTitle("Contacts"), "Contacts");
    }

    @Test(enabled = false, description = "Search again")
    @Description("Search again in entity search screen")
    public void t03_searchAgain() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchEntity("Organization", "Liad");
        EntitySearchPage entitySearchPage = new EntitySearchPage(driver);
        Assert.assertEquals(entitySearchPage.entitySearchTitle(), "Entity Search");
        searchPage.searchEntity("Individual Person", "Nitzan");
        Assert.assertTrue(entitySearchPage.entitiesName("Nitzan").contains("Nitzan"));
    }
    @Test(description = "Filter case by session id", enabled = false)
    @Description("Filter case by session id")
    public void t01_filterBySession() {
    }
    @Test(description = "Filter case by name", enabled = false)
    @Description("Filter case by name")
    public void t01_filterByName() {
    }
    @Test(description = "Main list test", enabled = false)
    @Description("Verify pending case appears in list")
    public void t01_mainListTest() {
    }


}