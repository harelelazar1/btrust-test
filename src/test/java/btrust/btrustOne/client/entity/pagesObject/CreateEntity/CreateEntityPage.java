package btrust.btrustOne.client.entity.pagesObject.CreateEntity;

import btrust.BasePage;
import btrust.btrustOne.client.BaseClientUserTest;
import btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject.CasesCreationPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CreateEntityPage extends BasePage {
    public CreateEntityPage(WebDriver driver) {
        super(driver);
    }

    BaseClientUserTest baseClientUserTest = new BaseClientUserTest();

    ContactInformationForEntityPage contactInformationForEntityPagePage = new ContactInformationForEntityPage(driver);
    EmailAndFormsPage emailAndFormsPage = new EmailAndFormsPage(driver);
    EntitySetupPage entitySetupPage = new EntitySetupPage(driver);
    OptionalFieldsPage optionalFieldsPage = new OptionalFieldsPage(driver);
    WorkflowPage workflowPage = new WorkflowPage(driver);
    CasesCreationPage casesCreationPage = new CasesCreationPage(driver);

    CreateEntityLayoutButtonsPage createEntityLayoutButtonsPage = new CreateEntityLayoutButtonsPage(driver);
    String randomString;
    String legalName;


    @Step("Create entity new")
    public void createEntityNew(String entityType, String businessCategory, String workflowName, String contactFirstName, String contactLastName, String contactEmail,String moveToScreen) {

        //getting to the new entity builder flow
        Assert.assertEquals(entitySetupPage.entityInformationTitle(), "Select Entity Type and Category");
        entitySetupPage.selectEntityType(entityType);
        entitySetupPage.selectBusinessCategory(businessCategory);

        //fill the mandatory fields page
        //       Assert.assertEquals(entitySetupPage.entityAndBusinessCategorySubTitle(), "Entity Type\n" + "Organization\n" + "Business category\n" + "Client");
        Assert.assertEquals(entitySetupPage.entityAndBusinessCategorySubTitle(), "Entity Type\n" + entityType +"\n"+"Business category\n" + businessCategory);



        Assert.assertEquals(entitySetupPage.organizationClientInformationTitle("Organization - Client information"), "Organization - Client information");
        randomString = baseClientUserTest.randomString();
        //   entitySetupPage.fillMandatoryTypeFieldsForEntity("LEI Code", "Lei" + randomString);
        entitySetupPage.fillMandatoryTypeFieldsForEntity("legal_name", "Entity" + randomString);
        entitySetupPage.fillMandatoryTypeFieldsForEntity("Business Address", "Ashdod");

        createEntityLayoutButtonsPage.clickOnButton("Continue");

        //fill the optional fields page
        Assert.assertEquals(optionalFieldsPage.additionalNonMandatoryInformationTitle("Additional non-mandatory information"), "Additional non-mandatory information");
        optionalFieldsPage.fillOptionalFieldsForEntity("VAT Code Tax Identification Number", "1234");
        optionalFieldsPage.fillOptionalFieldsForEntity("Legal headquarter - Address", "Tel Aviv");
        createEntityLayoutButtonsPage.clickOnButton("Continue");

        //workflow  selection page
        Assert.assertEquals(workflowPage.workflowTitle(), "Workflow");
        workflowPage.chooseWorkflowType1(workflowName);
        workflowPage.chooseCurrentDate();
//        workflowPage.chooseDueDate("August 2022", "1");
        createEntityLayoutButtonsPage.clickOnButton("Continue");

        // add contact person
        Assert.assertEquals(contactInformationForEntityPagePage.contactsTitle("Contacts"), "Contacts");
        contactInformationForEntityPagePage.addNewContactPerson();
        contactInformationForEntityPagePage.fillContactDetails("First Name", contactFirstName);
        contactInformationForEntityPagePage.fillContactDetails("Last Name", contactLastName);
        contactInformationForEntityPagePage.fillContactDetails("Position", "a");
        contactInformationForEntityPagePage.fillContactDetails("Business Phone", "11111111111");
        contactInformationForEntityPagePage.fillContactDetails("Second Phone", "11111111111");
        contactInformationForEntityPagePage.fillContactDetails("Fax Number", "11111111111");
        contactInformationForEntityPagePage.fillContactDetails("Email", contactEmail);
        contactInformationForEntityPagePage.clickOnButtonsInPopup("Save");
        contactInformationForEntityPagePage.parentCheckboxForContacts();
        createEntityLayoutButtonsPage.clickOnButton("Continue");

        //email and forms page
//        Assert.assertEquals(emailAndFormsPage.emailTemplatesTitle("E-mail"), "E-mail");
////        emailAndFormsPage.selectEmailTemplate("hello");
//        createEntityLayoutButtonsPage.clickOnButton("Continue");
//        legalName = entitySetupPage.returnLegalName("legal_name");
//        System.out.println(legalName);
        createEntityLayoutButtonsPage.clickOnButton("Send");
        casesCreationPage.clickOnButton("Confirm");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sleep(3000);
        casesCreationPage.clickOnButton(moveToScreen);
        sleep(3000);

    }


}
