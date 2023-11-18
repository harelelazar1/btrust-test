package btrust.btrustOne.client.end2End;

import btrust.btrustOne.client.BaseClientUserTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.cases.pagesObject.CasePage;
import btrust.btrustOne.client.cases.pagesObject.CasesPage;
import btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject.CasesCreationPage;
import btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject.ContactsInformationPage;
import btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject.EmailAndFormsPage;
import btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject.WorkflowPage;
import btrust.btrustOne.client.entity.pagesObject.EntitiesPage;
import btrust.btrustOne.client.entity.pagesObject.EntityPage;
import btrust.btrustOne.client.mandate.pagesObject.MandatesPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateCaseTest extends BaseClientUserTest {

    String mandateNumber;
    String caseId;

    @BeforeMethod
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
        entitiesPage.chooseEntity();
        EntityPage entityPage = new EntityPage(driver);
        Assert.assertEquals(entityPage.entityName(), "Liad");
        entityPage.chooseFromSideBar("Cases");
        Assert.assertEquals(entityPage.casesListTitle(), "CASES");
        entityPage.createNewCaseButton();
        WorkflowPage workflowPage = new WorkflowPage(driver);
        Assert.assertEquals(workflowPage.workflowTitle(), "Workflow");
        Assert.assertEquals(workflowPage.workflowDescription(), "Select the case properties");
    }

}