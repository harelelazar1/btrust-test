package btrust.btrustOne.client.mandate.tests.createMandate.tests;

import btrust.btrustOne.client.BaseClientUserTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.mandate.pagesObject.MandatesPage;
import btrust.btrustOne.client.mandate.pagesObject.createMandate.pagesObject.ClientInformationPage;
import btrust.btrustOne.client.mandate.pagesObject.createMandate.pagesObject.ContactInformationPage;
import btrust.btrustOne.client.mandate.pagesObject.createMandate.pagesObject.MandateAndWorkflowPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactInformationTest extends BaseClientUserTest {

    @BeforeMethod
    public void enterToContactInformationScreen() {
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
        mandateAndWorkflowPage.chooseDueDate("August 2022", "1");
        mandateAndWorkflowPage.chooseOptionFromSelect("Onboarding Office", "Italy");
        mandateAndWorkflowPage.chooseButton("Continue");
        ClientInformationPage clientInformationPage = new ClientInformationPage(driver);
        Assert.assertEquals(clientInformationPage.clientInformationTitle("Client Information"), "Client Information");
        clientInformationPage.chooseOptionFromSelect("Relationship Manager", "TestSub");
        clientInformationPage.typeInField("Legal Name", "Liad");
        clientInformationPage.chooseLegalNatureOfTheClient("Limited company");
        clientInformationPage.chooseButton("Continue");
        ContactInformationPage contactInformationPage = new ContactInformationPage(driver);
        Assert.assertEquals(contactInformationPage.contactsTitle("Contacts"), "Contacts");
        Assert.assertEquals(contactInformationPage.cardTitle("Entity and Mandate’s Contact person"), "Entity and Mandate’s Contact person");
        Assert.assertEquals(contactInformationPage.cardDescription("This contact will be considered as the call back person to all future business with this organization and mandate"), "This contact will be considered as the call back person to all future business with this organization and mandate");
    }

}