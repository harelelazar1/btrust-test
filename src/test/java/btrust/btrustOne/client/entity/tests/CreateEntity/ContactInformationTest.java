package btrust.btrustOne.client.entity.tests.CreateEntity;

import btrust.btrustOne.client.BaseClientUserTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.cases.pagesObject.CasesPage;
import btrust.btrustOne.client.entity.pagesObject.CreateEntity.*;
import btrust.btrustOne.client.search.pagesObject.SearchPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ContactInformationTest extends BaseClientUserTest {

    @Step("before method for contact info tab test in create entity process")
    @BeforeClass
    public void navigateToEntitiesPage() {
        NavigationPage navigationPage = new NavigationPage(driver);
        SearchPage searchPage = new SearchPage(driver);
        ContactInformationPage contactInformationPage = new ContactInformationPage(driver);
        EntitySetupPage entitySetupPage = new EntitySetupPage(driver);
        OptionalFieldsPage optionalFieldsPage = new OptionalFieldsPage(driver);
        WorkflowPage workflowPage = new WorkflowPage(driver);
        CreateEntityLayoutButtonsPage createEntityLayoutButtonsPage = new CreateEntityLayoutButtonsPage(driver);

        navigationPage.mainMenuList("Cases");
        CasesPage casesPage = new CasesPage(driver);
        Assert.assertTrue(casesPage.casesTitle(), "Cases");
        navigationPage.mainMenuList("Search");
        Assert.assertEquals(searchPage.searchTitle(), "Search");

        //search non existing entity in the search page
        searchPage.searchEntity("Organization", "newEntity" + randomString());
        Assert.assertEquals(searchPage.NoSuitableEntitiesWereMatchedYourSearch(), "No suitable entities were matched your search");
        searchPage.chooseNameInAddEntityMenu("Create entity and workflow");

        //getting to the new entity builder flow
        Assert.assertEquals(entitySetupPage.entityInformationTitle(), "Select Entity Type and Category");
        entitySetupPage.selectEntityType("Organization");
        entitySetupPage.selectBusinessCategory("Client");

        //fill the mandatory fields page
        Assert.assertEquals(entitySetupPage.organizationClientInformationTitle("Organization - Client information"), "Organization - Client information");
        randomString();
        entitySetupPage.fillMandatoryTypeFieldsForEntity("LEI Code", "Lei" + randomString);
        entitySetupPage.fillMandatoryTypeFieldsForEntity("legal_name", "Entity" + randomString);
        entitySetupPage.fillMandatoryTypeFieldsForEntity("Business Address", "Ashdod");
 //       entitySetupPage.fillMandatorySelectFieldsForEntity("test", "Italy");
 //       entitySetupPage.fillMandatoryTypeFieldsForEntity("babble bubble insert 4 digits only", "1111");

        createEntityLayoutButtonsPage.clickOnButton("Continue");

        //fill the optional fields page
        Assert.assertEquals(optionalFieldsPage.additionalNonMandatoryInformationTitle("Additional non-mandatory information"), "Additional non-mandatory information");
        optionalFieldsPage.fillOptionalFieldsForEntity("VAT Code Tax Identification Number", "1234");
        optionalFieldsPage.fillOptionalFieldsForEntity("Legal headquarter - Address", "Tel Aviv");
        createEntityLayoutButtonsPage.clickOnButton("Continue");

        //workflow  selection page
        Assert.assertEquals(workflowPage.workflowTitle(), "Workflow");
        workflowPage.chooseWorkflowType("Nitzan W9 IRS");
        workflowPage.chooseCurrentDate();
        createEntityLayoutButtonsPage.clickOnButton("Continue");

        // add contact person
        Assert.assertEquals(contactInformationPage.contactsTitle("Contacts"), "Contacts");
    }

    @Test(description = "mandatory fields error messages in contact info popup")
    @Description("mandatory fields error messages in contact info popup")
    public void t01_errorMessagesInContactInfoPopup() {
        ContactInformationPage contactInformationPage = new ContactInformationPage(driver);

        contactInformationPage.addNewContactPerson();
        contactInformationPage.clickOnButtonsInPopup("Save");
        Assert.assertEquals(contactInformationPage.verifyTitlesAndDataInContactInfoPersonPopup("First Name\n"), "First Name\n" + "Mandatory field");
        Assert.assertEquals(contactInformationPage.verifyTitlesAndDataInContactInfoPersonPopup("Last Name\n"), "Last Name\n" + "Mandatory field");
        Assert.assertEquals(contactInformationPage.verifyTitlesAndDataInContactInfoPersonPopup("Business Phone\n"), "Business Phone\n" + "Mandatory field");
        Assert.assertEquals(contactInformationPage.verifyTitlesAndDataInContactInfoPersonPopup("Email\n"), "Email\n" + "This field is required");

        contactInformationPage.fillContactDetails("First Name", "345");
        contactInformationPage.fillContactDetails("Last Name", "342");
        contactInformationPage.fillContactDetails("Email", "Nitzan");
        contactInformationPage.clickOnButtonsInPopup("Save");

        Assert.assertEquals(contactInformationPage.verifyTitlesAndDataInContactInfoPersonPopup("First Name\n"), "First Name\n" + "Please use only English or Hebrew characters and spaces");
        Assert.assertEquals(contactInformationPage.verifyTitlesAndDataInContactInfoPersonPopup("Last Name\n"), "Last Name\n" + "Please use only English or Hebrew characters and spaces");
        Assert.assertEquals(contactInformationPage.verifyTitlesAndDataInContactInfoPersonPopup("Email\n"), "Email\n" + "Please insert a valid email address");
        contactInformationPage.clickOnButtonsInPopup("Cancel");
    }

    @Test(description = "edit contact person button")
    @Description("edit contact person button")
    public void t02_editContactPersonInfo() {
        ContactInformationPage contactInformationPage = new ContactInformationPage(driver);

        contactInformationPage.addNewContactPerson();
        contactInformationPage.fillContactDetails("First Name", "Nitzan");
        contactInformationPage.fillContactDetails("Last Name", "a");
        contactInformationPage.fillContactDetails("Business Phone", "11111111111");
        contactInformationPage.fillContactDetails("Email", "Nitzan@gmail.com");
        contactInformationPage.clickOnButtonsInPopup("Save");

        Assert.assertEquals(contactInformationPage.verifyContactInfoPersonList("Full Name"), "Nitzan a");
        Assert.assertEquals(contactInformationPage.verifyContactInfoPersonList("Business Phone"), "11111111111");
        Assert.assertEquals(contactInformationPage.verifyContactInfoPersonList("Email"), "Nitzan@gmail.com");

        contactInformationPage.clickOnEditContactPerson("Nitzan a");
        contactInformationPage.fillContactDetails("First Name", "new");
        contactInformationPage.fillContactDetails("Last Name", "new");
//        contactInformationPage.fillContactDetails("Business Phone", "2222222222");
        contactInformationPage.fillContactDetails("Email", "new@gmail.com");
        contactInformationPage.clickOnButtonsInPopup("Save");

        Assert.assertEquals(contactInformationPage.verifyContactInfoPersonList("Full Name"), "new new");
//        Assert.assertEquals(contactInformationPage.verifyContactInfoPersonList("Business Phone"),"2222222222");
        Assert.assertEquals(contactInformationPage.verifyContactInfoPersonList("Email"), "new@gmail.com");
    }
}