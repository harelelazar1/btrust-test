package btrust.btrustOne.client.mandate.tests.createMandate.tests;

import btrust.btrustOne.client.BaseClientUserTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.mandate.pagesObject.MandatesPage;
import btrust.btrustOne.client.mandate.pagesObject.createMandate.pagesObject.ClientInformationPage;
import btrust.btrustOne.client.mandate.pagesObject.createMandate.pagesObject.MandateAndWorkflowPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ClientInformationTest extends BaseClientUserTest {

    @BeforeMethod
    public void enterToClientInformationScreen() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Mandates");
        MandatesPage mandatesPage = new MandatesPage(driver);
        Assert.assertEquals(mandatesPage.mandatesTitle("Mandates"), "Mandates");
        mandatesPage.addNewMandateButton();
        MandateAndWorkflowPage mandateAndWorkflowPage = new MandateAndWorkflowPage(driver);
        Assert.assertEquals(mandateAndWorkflowPage.mandateInformationTitle(), "Mandate Information");
        mandateAndWorkflowPage.fillValueInRelationshipNumberField(mandateAndWorkflowPage.randomString());
        mandateAndWorkflowPage.chooseOptionFromSelect("Business Nature Relationship", "Portfolio Management");
        mandateAndWorkflowPage.chooseOptionFromSelect("Select Workflow", "AML Recertification");
        mandateAndWorkflowPage.chooseDueDate("April 2022", "15");
        mandateAndWorkflowPage.chooseOptionFromSelect("Onboarding Office", "Italy");
        mandateAndWorkflowPage.chooseButton("Continue");
        ClientInformationPage clientInformationPage = new ClientInformationPage(driver);
        Assert.assertEquals(clientInformationPage.clientInformationTitle("Client Information"), "Client Information");
    }

}