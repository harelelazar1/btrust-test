package btrust.btrustOne.client.cases.tests.createNewCase.tests;

import btrust.btrustOne.client.BaseClientUserTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject.ContactsInformationPage;
import btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject.WorkflowPage;
import btrust.btrustOne.client.entity.pagesObject.EntitiesPage;
import btrust.btrustOne.client.entity.pagesObject.EntityPage;
import btrust.btrustOne.client.mandate.pagesObject.MandatesPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactsInformationTest extends BaseClientUserTest {

    String mandateNumber;

    @BeforeClass
    @Step("Create new mandate and navigate to create new case flow")
    public void createNewMandateAndNavigateToCreateNewCaseFlow() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Mandates");
        MandatesPage mandatesPage = new MandatesPage(driver);
        Assert.assertEquals(mandatesPage.mandatesTitle("Mandates"), "Mandates");
        mandatesPage.addNewMandateButton();
        createMandate();
        mandatesPage.filterMandateTable("Status", "Under Investigation");
        mandateNumber = mandatesPage.firstMandateInformation("Mandate No.");
        navigationPage.mainMenuList("Entities");
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        Assert.assertTrue(entitiesPage.entitiesTitle());
    }

    @BeforeMethod
    @Step("Navigate to create new case flow")
    public void navigateToCreateNewCaseFlow() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Entities");
        EntitiesPage entitiesPage = new EntitiesPage(driver);
        Assert.assertTrue(entitiesPage.entitiesTitle());
        entitiesPage.chooseEntity();
        EntityPage entityPage = new EntityPage(driver);
        entityPage.chooseFromSideBar("Cases");
        Assert.assertEquals(entityPage.casesListTitle(), "CASES");
        entityPage.createNewCaseButton();
        WorkflowPage workflowPage = new WorkflowPage(driver);
        Assert.assertEquals(workflowPage.workflowTitle(), "Workflow");
        Assert.assertEquals(workflowPage.workflowDescription(), "Select the case properties");
        workflowPage.fillWorkflowPage("Workflow", "Test", null, null);
        workflowPage.fillWorkflowPage("Case Due Date", null, "October 2022", "20");
        workflowPage.chooseButton("Continue");
        ContactsInformationPage contactsInformationPage = new ContactsInformationPage(driver);
        Assert.assertEquals(contactsInformationPage.contactsTitle(), "Contacts");
        Assert.assertEquals(contactsInformationPage.contactsDescription(), "Include contacts who will receive the email conditions");
    }

    //TODO add test to add new contact and test to edit contact
}