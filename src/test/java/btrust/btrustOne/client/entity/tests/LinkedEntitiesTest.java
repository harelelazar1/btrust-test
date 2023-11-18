package btrust.btrustOne.client.entity.tests;

import btrust.btrustOne.client.BaseClientUserTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.cases.pagesObject.CasePage;
import btrust.btrustOne.client.entity.pagesObject.CreateEntity.CreateEntityPage;
import btrust.btrustOne.client.entity.pagesObject.CreateEntity.CreateEntityLayoutButtonsPage;
import btrust.btrustOne.client.entity.pagesObject.CreateEntity.EntitySetupPage;
import btrust.btrustOne.client.entity.pagesObject.CreateEntity.OptionalFieldsPage;
import btrust.btrustOne.client.entity.pagesObject.EntitiesPage;
import btrust.btrustOne.client.entity.pagesObject.EntityPage;
import btrust.coreApi.ApiResponses;
import btrust.coreApi.variables.CaseContact;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LinkedEntitiesTest extends BaseClientUserTest {


    NavigationPage navigationPage;
    EntitySetupPage entitySetupPage;
    CreateEntityLayoutButtonsPage createEntityLayoutButtonsPage;
    EntitiesPage entitiesPage;
    EntityPage entityPage;
    OptionalFieldsPage optionalFieldsPage;
    BaseClientUserTest baseClientUserTest;
    CreateEntityPage createEntityPage;
    CasePage casePage;

    String randomString = randomString();
    ApiResponses apiResponses;
    CaseContact caseContact;

    int caseId;
    int contactId;

    String caseID;
    String contactID;
    String sessionId;
    String url;


    @Step("before method for entity setup info tab test in create entity process")
    @BeforeMethod
    public void getToNewEntitySetupPage() {
        navigationPage = new NavigationPage(driver);
        entitySetupPage = new EntitySetupPage(driver);
        createEntityLayoutButtonsPage = new CreateEntityLayoutButtonsPage(driver);
        optionalFieldsPage = new OptionalFieldsPage(driver);
        navigationPage.mainMenuList("Entities");
        entitiesPage = new EntitiesPage(driver);
        entityPage = new EntityPage(driver);
        baseClientUserTest = new BaseClientUserTest();
        createEntityPage = new CreateEntityPage(driver);
        casePage = new CasePage(driver);
        apiResponses = new ApiResponses();
        caseContact = new CaseContact();

    }


    @Test(description = "Create New Entity and add Linked entities")
    @Description("Create New Entity and add Linked entities- Check new tab")
    public void t01_createNewEntityAndAddLinkedEntities() {
        entitiesPage.chooseFromAddEntityMenu("Create entity and workflow");
        createEntityPage.createEntityNew("Organization", "Client", "Automation Linked Entities", "firstName", "lastName", "harel.elazar@scanovate.com", "Back to Entity");
        entityPage.chooseFromSideBar("Cases");
        Assert.assertEquals(entityPage.casesListTitle(), "CASES");
        entityPage.chooseFromCasesListByWorkflow("Automation Linked Entities");
        Assert.assertFalse(casePage.checkIfTabDisplay("Related Cases"));
        casePage.clickOnEntityName();
        entityPage.chooseBusinessCategoryFromSideBar("Linked Entities", "Client", "Search and add");
        entityPage.searchAndAddClientToConnect("Search Clients to Connect", "harel", "harel", "Linked", "Confirm & Close", "Confirm", "Continue");
        Assert.assertTrue(entityPage.checkLinkedEntitiesToMainEntity());
        entityPage.chooseFromSideBar("Cases");
        Assert.assertEquals(entityPage.casesListTitle(), "CASES");
        entityPage.chooseFromCasesListByWorkflow("Automation Linked Entities");
        Assert.assertTrue(casePage.checkIfTabDisplay("Related Cases"));

    }


    @Test(description = "Create New Entity and add contact enrollment row")
    @Description("Create New Entity and add contact enrollment row")
    public void t02_createNewEntityAndAddContactEnrollmentRow() {
        entitiesPage.chooseFromAddEntityMenu("Create entity and workflow");
        createEntityPage.createEntityNew("Organization", "Client", "Automation Linked Entities", "firstName", "lastName", "harel.elazar@scanovate.com", "Open the Case");
        caseID = casePage.caseId();

        caseId = Integer.parseInt(caseID);
        Assert.assertFalse(casePage.checkIfContactsEnrollmentRowDisplay());

        //backend
        apiResponses.setCaseContact(caseContact, caseId);
        contactId = caseContact.getContactId();
        Assert.assertEquals(caseContact.getCaseId(), caseId);

//        apiResponses.setCreatePortalLink(caseContact, caseId, contactId);
//        Assert.assertEquals(caseContact.getContactId(), contactId);
//        link = caseContact.getPortalLink();

        caseID = Integer.toString(caseId);
        contactID = Integer.toString(contactId);


        apiResponses.setSessionStatus(caseContact, caseID, contactID);
        Assert.assertTrue(caseContact.isSuccess());
        assertEquals(caseContact.getErrorCode(), 0);
        sessionId = caseContact.getSessionId();
        url = caseContact.getUrl();

        driver.navigate().refresh();
        Assert.assertTrue(casePage.checkIfContactsEnrollmentRowDisplay());
    }


}
