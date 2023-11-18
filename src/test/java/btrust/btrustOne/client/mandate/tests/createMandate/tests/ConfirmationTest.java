package btrust.btrustOne.client.mandate.tests.createMandate.tests;

import btrust.btrustOne.client.BaseClientUserTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.mandate.pagesObject.MandatesPage;
import btrust.btrustOne.client.mandate.pagesObject.createMandate.pagesObject.*;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ConfirmationTest extends BaseClientUserTest {

    String relationshipNumber;

    @BeforeMethod
    public void enterToConfirmationScreen() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Mandates");
        MandatesPage mandatesPage = new MandatesPage(driver);
        Assert.assertEquals(mandatesPage.mandatesTitle("Mandates"), "Mandates");
        mandatesPage.addNewMandateButton();

        MandateAndWorkflowPage mandateAndWorkflowPage = new MandateAndWorkflowPage(driver);
        Assert.assertEquals(mandateAndWorkflowPage.mandateInformationTitle(), "Mandate Information");
        mandateAndWorkflowPage.fillValueInRelationshipNumberField(mandateAndWorkflowPage.randomString());
        relationshipNumber = mandateAndWorkflowPage.getRelationshipValue();
        mandateAndWorkflowPage.chooseOptionFromSelect("Business Nature Relationship", "Portfolio Management");
        mandateAndWorkflowPage.chooseOptionFromSelect("Select Workflow", "AML Recertification");
        mandateAndWorkflowPage.chooseDueDate("April 2022", "15");
        mandateAndWorkflowPage.chooseOptionFromSelect("Onboarding Office", "Italy");
        mandateAndWorkflowPage.chooseButton("Continue");

        ClientInformationPage clientInformationPage = new ClientInformationPage(driver);
        Assert.assertEquals(clientInformationPage.clientInformationTitle("Client Information"), "Client Information");
        clientInformationPage.chooseOptionFromSelect("Relationship Manager", "TestSub");
        clientInformationPage.typeInField("Legal Name", "Liad");
        clientInformationPage.typeInField("VAT Code/ Tax Identification Number", "123");
        clientInformationPage.typeInField("LEI Code", "456");
        clientInformationPage.typeInField("Address", "789");
        clientInformationPage.fillBusinessPhone("Israel", "0501234567");
        clientInformationPage.chooseOptionFromSelect("Country", "Japan");
        clientInformationPage.chooseOptionFromSelect("City", "Ago");
        clientInformationPage.typeInField("Civic Number", "135");
        clientInformationPage.chooseLegalNatureOfTheClient("Limited company");
        clientInformationPage.chooseButton("Continue");

        ContactInformationPage contactInformationPage = new ContactInformationPage(driver);
        Assert.assertEquals(contactInformationPage.contactsTitle("Contacts"), "Contacts");
        Assert.assertEquals(contactInformationPage.cardTitle("Entity and Mandate’s Contact person"), "Entity and Mandate’s Contact person");
        Assert.assertEquals(contactInformationPage.cardDescription("This contact will be considered as the call back person to all future business with this organization and mandate"), "This contact will be considered as the call back person to all future business with this organization and mandate");
        contactInformationPage.defaultCardTypeInField("First Name", "Liad");
        contactInformationPage.defaultCardTypeInField("Last Name", "Tobi");
        contactInformationPage.defaultCardTypeInField("Position", "QA");
        contactInformationPage.defaultCardFillCallNumber("Business Phone", "Israel", "0501234567");
        contactInformationPage.defaultCardFillCallNumber("Second Phone", "Israel", "0501234567");
        contactInformationPage.defaultCardFillCallNumber("Fax", "Israel", "0501234567");
        contactInformationPage.defaultCardTypeInField("Email Address", "automation@automation.com");
        contactInformationPage.chooseButton("Continue");

        EmailAndFormPage emailAndFormPage = new EmailAndFormPage(driver);
        Assert.assertEquals(emailAndFormPage.emailAndFormRequiredTitle("Email and Forms Required"), "Email and Forms Required");
        emailAndFormPage.chooseButton("Continue");
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        Assert.assertEquals(confirmationPage.confirmationTile("We're Almost There"), "We're Almost There");
    }

}