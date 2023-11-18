package btrust.btrustOne.client;

import btrust.BaseDesktopViewTest;
import btrust.btrustOne.client.cases.pagesObject.CasesPage;
import btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject.CasesCreationPage;
import btrust.btrustOne.client.entity.pagesObject.CreateEntity.*;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import btrust.btrustOne.client.mandate.pagesObject.MandatesPage;
import btrust.btrustOne.client.mandate.pagesObject.createMandate.pagesObject.ContactInformationPage;
import btrust.btrustOne.client.mandate.pagesObject.createMandate.pagesObject.*;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class BaseClientUserTest extends BaseDesktopViewTest {



    protected int entityId;
    protected int contactId;
    protected String token;
    protected String link;
    protected String relationshipNumber;
    protected String leiCode;
    protected String legalName;
    protected String id;
    public String randomString;


    @BeforeClass
    @Step("Login to client user")
    public void loginToClientUser() {
        driver.get("https://qa-console.scanovateoncloud.com/login");
        LoginPage login = new LoginPage(driver);
        login.loginNewForSecurity("harelelazar@gmail.com", "Amitbiton20");
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.clickOnBackToBtrustUserButton();
//        navigationPage.mainMenuList("Search");

    }

    @Step("Create link to doc portal")
    public String createLinkToDocPortal() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Cases");
        CasesPage casesPage = new CasesPage(driver);
        Assert.assertTrue(casesPage.casesTitle());
        entityId = Integer.parseInt(casesPage.firstEntityId());

        RestAssured.baseURI = "https://qa-nginx-console.scanovateoncloud.com/";
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.defaultParser = Parser.JSON;
        Response extractContactId = given()
                .header("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1c2VyRGF0YSI6IntcImlkXCI6ODMsXCJjb21wYW55SWRcIjo0LFwiYWN0aXZlU3ViQ29tcGFueVwiOjAsXCJyb2xlc1wiOls3XSxcInBlcm1pc3Npb25zXCI6W3tcImlkXCI6MCxcInJvbGVJZFwiOjAsXCJ0eXBlXCI6MCxcInR5cGVJZFwiOjAsXCJjYW5SZWFkXCI6ZmFsc2UsXCJjYW5FZGl0XCI6ZmFsc2UsXCJjYW5DcmVhdGVcIjpmYWxzZX0se1wiaWRcIjowLFwicm9sZUlkXCI6MCxcInR5cGVcIjo2LFwidHlwZUlkXCI6MCxcImNhblJlYWRcIjp0cnVlLFwiY2FuRWRpdFwiOnRydWUsXCJjYW5DcmVhdGVcIjp0cnVlfV19IiwiaWF0IjoxNjIyNTU0NjUxLCJleHAiOjMxOTkzNTQ2NTF9.S53gOLQJ9XqD3UsFZzx_pn9hOJ6hpCM8CDLHI2rXvPR256GLDj4Y0Eb4YKSWrERs8hJlvLA9okxLkJJ6I1JgxHzgrwgb2gdMyDPEji6rGXRijKzsbwWYUzT1xe78p0cb-LUGfrcfM80HK7qV2CePZ_ho7C9Jczg_evV56xVIoN10eZrrpzUDEJ-dfTjtXSK93AkGeh6t1n3Lm8xhDYPrZ2fy59Da4w9MmPzUwl3VaAxIawum2VRM2U6p15hH4bB_p1HZaVdA_2GmXf74KuRkguyPbbDWZI7g9LVU5POj6JGxTsRtDOaGBeFNsRXKVkvb2DizMxCl1AhXPwf_G9-Oaw")
                .log()
                .all()
                .when()
                .get("api/entities/" + entityId + "/contacts")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(extractContactId.getBody().asString());
        JsonPath jsonPath = extractContactId.jsonPath();
        List<Object> list = jsonPath.getList("data.id");
        StringBuilder out = new StringBuilder();
        for (Object o : list) {
            out.append(o.toString());
        }
        contactId = Integer.parseInt(String.valueOf(out));

        Response createLink = given()
                .header("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1c2VyRGF0YSI6IntcImlkXCI6ODMsXCJjb21wYW55SWRcIjo0LFwiYWN0aXZlU3ViQ29tcGFueVwiOjAsXCJyb2xlc1wiOls3XSxcInBlcm1pc3Npb25zXCI6W3tcImlkXCI6MCxcInJvbGVJZFwiOjAsXCJ0eXBlXCI6MCxcInR5cGVJZFwiOjAsXCJjYW5SZWFkXCI6ZmFsc2UsXCJjYW5FZGl0XCI6ZmFsc2UsXCJjYW5DcmVhdGVcIjpmYWxzZX0se1wiaWRcIjowLFwicm9sZUlkXCI6MCxcInR5cGVcIjo2LFwidHlwZUlkXCI6MCxcImNhblJlYWRcIjp0cnVlLFwiY2FuRWRpdFwiOnRydWUsXCJjYW5DcmVhdGVcIjp0cnVlfV19IiwiaWF0IjoxNjIyNTU0NjUxLCJleHAiOjMxOTkzNTQ2NTF9.S53gOLQJ9XqD3UsFZzx_pn9hOJ6hpCM8CDLHI2rXvPR256GLDj4Y0Eb4YKSWrERs8hJlvLA9okxLkJJ6I1JgxHzgrwgb2gdMyDPEji6rGXRijKzsbwWYUzT1xe78p0cb-LUGfrcfM80HK7qV2CePZ_ho7C9Jczg_evV56xVIoN10eZrrpzUDEJ-dfTjtXSK93AkGeh6t1n3Lm8xhDYPrZ2fy59Da4w9MmPzUwl3VaAxIawum2VRM2U6p15hH4bB_p1HZaVdA_2GmXf74KuRkguyPbbDWZI7g9LVU5POj6JGxTsRtDOaGBeFNsRXKVkvb2DizMxCl1AhXPwf_G9-Oaw")
//                .log()
//                .all()
                .when()
                .get("api/token?contactId=" + contactId + "&caseId=" + entityId)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath jsonPath1 = createLink.jsonPath();
        System.out.println(createLink.getBody().asString());
        token = jsonPath1.get("data");
        link = "https://portal-qa.scanovate.com/login?token=" + token;

        return link;
    }

    @Step("Create mandate")
    public void createMandate() {
        MandateAndWorkflowPage mandateAndWorkflowPage = new MandateAndWorkflowPage(driver);
        Assert.assertEquals(mandateAndWorkflowPage.mandateInformationTitle(), "Mandate Information");
        mandateAndWorkflowPage.fillValueInRelationshipNumberField(randomString());
        relationshipNumber = mandateAndWorkflowPage.getRelationshipValue();
        mandateAndWorkflowPage.chooseOptionFromSelect("Business Nature Relationship", "Portfolio Management");
        mandateAndWorkflowPage.chooseOptionFromSelect("Select Workflow", "AML Recertification");
        mandateAndWorkflowPage.chooseDueDate("April 2023", "15");
        mandateAndWorkflowPage.chooseOptionFromSelect("Onboarding Office", "Italy");
        mandateAndWorkflowPage.chooseButton("Continue");
        ClientInformationPage clientInformationPage = new ClientInformationPage(driver);
        Assert.assertEquals(clientInformationPage.clientInformationTitle("Client Information"), "Client Information");
        try {
            clientInformationPage.chooseOptionFromSelect("Relationship Manager", "TestSub");
            clientInformationPage.typeInField("Legal Name", "Liad");
            legalName = clientInformationPage.legalNameValue();
            clientInformationPage.typeInField("VAT Code/ Tax Identification Number", "123");
            leiCode = randomString();
            clientInformationPage.typeInField("LEI Code", leiCode);
            clientInformationPage.typeInField("Address", "789");
            clientInformationPage.fillBusinessPhone("Australia", "0501234567");
            clientInformationPage.chooseOptionFromSelect("Country", "Australia");
            clientInformationPage.chooseOptionFromSelect("City", "Ararat");
            clientInformationPage.typeInField("Civic Number", "135");
            clientInformationPage.chooseLegalNatureOfTheClient("Limited company");
            clientInformationPage.chooseButton("Continue");
        } catch (Exception e) {
            clientInformationPage.chooseButton("Continue");
        }
        ContactInformationPage contactInformationPage = new ContactInformationPage(driver);
        Assert.assertEquals(contactInformationPage.contactsTitle("Contacts"), "Contacts");
        try {
            Assert.assertEquals(contactInformationPage.cardTitle("Entity and Mandate’s Contact person"), "Entity and Mandate’s Contact person");
            Assert.assertEquals(contactInformationPage.cardDescription("This contact will be considered as the call back person to all future business with this organization and mandate"), "This contact will be considered as the call back person to all future business with this organization and mandate");
            contactInformationPage.defaultCardTypeInField("First Name", "LiadAutomation");
            contactInformationPage.defaultCardTypeInField("Last Name", "TobiAutomation");
            contactInformationPage.defaultCardTypeInField("Position", "positionAutomation");
            contactInformationPage.defaultCardFillCallNumber("Business Phone", "Australia", "0501234567");
            contactInformationPage.defaultCardFillCallNumber("Second Phone", "Australia", "0501234567");
            contactInformationPage.defaultCardFillCallNumber("Fax", "Australia", "0501234567");
            contactInformationPage.defaultCardTypeInField("Email Address", "scanovate.qa@gmail.com");
        } catch (Exception e) {
            Assert.assertEquals(contactInformationPage.exitContactDescription(), "Include Contacts who will receive the email conditions:");
            Assert.assertFalse(contactInformationPage.buttonIsEnabled("Continue"));
            contactInformationPage.selectFirstContact();
        }
        contactInformationPage.chooseButton("Continue");
        EmailAndFormPage emailAndFormPage = new EmailAndFormPage(driver);
        Assert.assertEquals(emailAndFormPage.emailAndFormRequiredTitle("Email and Forms Required"), "Email and Forms Required");
        emailAndFormPage.chooseButton("Continue");
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        Assert.assertEquals(confirmationPage.confirmationTile("We're Almost There"), "We're Almost There");
        confirmationPage.chooseButton("Create & Send");
        MandatesPage mandatesPage = new MandatesPage(driver);
        Assert.assertEquals(mandatesPage.mandatesTitle("Mandates"), "Mandates");
    }

    @Step("Create entity")
    public void createEntity() {
        ContactInformationForEntityPage contactInformationForEntityPagePage = new ContactInformationForEntityPage(driver);
        EmailAndFormsPage emailAndFormsPage = new EmailAndFormsPage(driver);
        EntitySetupPage entitySetupPage = new EntitySetupPage(driver);
        OptionalFieldsPage optionalFieldsPage = new OptionalFieldsPage(driver);
        WorkflowPage workflowPage = new WorkflowPage(driver);
        CasesCreationPage casesCreationPage = new CasesCreationPage(driver);

        CreateEntityLayoutButtonsPage createEntityLayoutButtonsPage = new CreateEntityLayoutButtonsPage(driver);
        //getting to the new entity builder flow
        Assert.assertEquals(entitySetupPage.entityInformationTitle(), "Select Entity Type and Category");
        entitySetupPage.selectEntityType("Organization");
        entitySetupPage.selectBusinessCategory("Client");

        //fill the mandatory fields page
        Assert.assertEquals(entitySetupPage.entityAndBusinessCategorySubTitle(), "Entity Type\n" + "Organization\n" + "Business category\n" + "Client");
        Assert.assertEquals(entitySetupPage.organizationClientInformationTitle("Organization - Client information"), "Organization - Client information");
        randomString = randomString();
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
        workflowPage.chooseWorkflowType("Nitzan W9 IRS");
        workflowPage.chooseCurrentDate();
//        workflowPage.chooseDueDate("August 2022", "1");
        createEntityLayoutButtonsPage.clickOnButton("Continue");

        // add contact person
        Assert.assertEquals(contactInformationForEntityPagePage.contactsTitle("Contacts"), "Contacts");
        contactInformationForEntityPagePage.addNewContactPerson();
        contactInformationForEntityPagePage.fillContactDetails("First Name", "Nitzan");
        contactInformationForEntityPagePage.fillContactDetails("Last Name", "a");
        contactInformationForEntityPagePage.fillContactDetails("Position", "a");
        contactInformationForEntityPagePage.fillContactDetails("Business Phone", "11111111111");
        contactInformationForEntityPagePage.fillContactDetails("Second Phone", "11111111111");
        contactInformationForEntityPagePage.fillContactDetails("Fax Number", "11111111111");
        contactInformationForEntityPagePage.fillContactDetails("Email", "Nitzan@gmail.com");
        contactInformationForEntityPagePage.clickOnButtonsInPopup("Save");
        contactInformationForEntityPagePage.parentCheckboxForContacts();
        createEntityLayoutButtonsPage.clickOnButton("Continue");

        //email and forms page
        Assert.assertEquals(emailAndFormsPage.emailTemplatesTitle("E-mail"), "E-mail");
//        emailAndFormsPage.selectEmailTemplate("hello");
        createEntityLayoutButtonsPage.clickOnButton("Continue");
        legalName = entitySetupPage.returnLegalName("legal_name");
        System.out.println(legalName);
        createEntityLayoutButtonsPage.clickOnButton("Send");
        casesCreationPage.clickOnButton("Confirm");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        casesCreationPage.clickOnButton("Back to Entity");

    }



    @Step ("return legal name")
    public String legalNameValue(){
        System.out.println(legalName);
        return legalName;
    }




    @Step("create random string")
    public String randomString() {
        // create a string of all characters
        String alphabet = "abcdefghijkelmonpqrstuvwxyz1234567890";
        // create random string builder
        StringBuilder sb = new StringBuilder();
        // create an object of Random class
        Random random = new Random();
        // specify length of random string
        int length = 8;
        for (int i = 0; i < length; i++) {
            // generate random index number
            int index = random.nextInt(alphabet.length());
            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);
            // append the character to string builder
            sb.append(randomChar);
        }
        randomString = sb.toString();
        return randomString;
    }

    @AfterClass
    public void logout() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.logOut();
    }

}