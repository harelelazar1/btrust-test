package btrust.btrustOne.admin.workflowManagement.mobileForms.tests;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.dataModule.documentManagement.pageObject.DocumentManagementPage;
import btrust.btrustOne.admin.workflowManagement.mobileForms.pageObject.FormPage;
import btrust.btrustOne.admin.workflowManagement.mobileForms.pageObject.MobileFormPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.SuiteListener;

import java.io.IOException;

//@Listeners(SuiteListener.class)

public class mobileFormTest extends BaseAdminUserTest {


    LoginPage loginPage;
    AdministratorPage administratorPage;
    MobileFormPage mobileFormPage;
    FormPage formPage;

    String formName = "form Test " + randomString();
    String popupName = "popup Test " + randomString();


    @BeforeClass
    @Step("Enter to  Mobile form screen")
    public void navigateToMobileFormScreen() throws IOException {
        loginPage = new LoginPage(driver);
        mobileFormPage = new MobileFormPage(driver);
        formPage = new FormPage(driver);
        administratorPage = new AdministratorPage(driver);
        mobileFormPage.createNewFile("C:/tmp/sample.pdf");
        mobileFormPage.createNewFile("C:/tmp/sample.jpg");
        administratorPage.chooseFromSideBar("Mobile Forms");
        Assert.assertEquals(mobileFormPage.pageTitle(), "Mobile Forms");
//        administratorPage.openAllSideBarGroups();
    }

    @AfterClass
    @Step("Enter to Document Management screen")
    public void deleteFileFromFolder() throws IOException {
        mobileFormPage.deleteFileFromFolder("C:/tmp/sample.pdf");
        mobileFormPage.deleteFileFromFolder("C:/tmp/sample.jpg");
    }


    @Test(description = "Add new mobile form with all the cards and Delete the form")
    @Description("Add new mobile form with all the cards and Delete the form")
    public void test_01_addNewMobileFormWithAllCardsAndDelete() {
        Assert.assertEquals(mobileFormPage.pageTitle(), "Mobile Forms");
        mobileFormPage.createNewModule("Form", formName, "Create new form");
        mobileFormPage.searchNameAndClick(formName);
//        Assert.assertEquals(formPage.cardTitleName(),"Navigation Settings");
        formPage.chooseCardType("Short text");
        Assert.assertTrue(formPage.cardTitleName1("New Short Text"));
        formPage.chooseCardType("Number");
        Assert.assertTrue(formPage.cardTitleName1("New Number"));
        formPage.chooseCardType("Long Text");
        Assert.assertTrue(formPage.cardTitleName1("New Long Text"));
        formPage.chooseCardType("Phone number");
        Assert.assertTrue(formPage.cardTitleName1("New Phone Number"));
        formPage.chooseCardType("Email");
        Assert.assertTrue(formPage.cardTitleName1("New Email"));
        formPage.chooseCardType("Date");
        Assert.assertTrue(formPage.cardTitleName1("New Date"));
        formPage.chooseCardType("File");
        Assert.assertTrue(formPage.cardTitleName1("File Upload"));
        formPage.chooseCardType("Yes/No");
        Assert.assertTrue(formPage.cardTitleName1("Yes or No"));
        formPage.chooseCardType("Checkbox");
        Assert.assertTrue(formPage.cardTitleName1("Checkbox"));
        formPage.deleteCardFromForm("Checkbox", "Yes");
        formPage.chooseCardType("Image");
        Assert.assertTrue(formPage.cardTitleName1("Image"));
        formPage.deleteCardFromForm("Image", "Yes");
        formPage.chooseCardType("Title");
        Assert.assertTrue(formPage.cardTitleName1("new Title"));
        formPage.chooseCardType("Text");
        Assert.assertTrue(formPage.cardTitleName1("Text"));
        formPage.chooseCardType("Subtitle");
        Assert.assertTrue(formPage.cardTitleName1("new Subtitle"));
        formPage.chooseCardType("Link");
        Assert.assertTrue(formPage.cardTitleName1("Link"));
        formPage.deleteCardFromForm("Link", "Yes");
        formPage.chooseCardType("Divider");
        Assert.assertTrue(formPage.cardTitleName1("Divider"));
        formPage.chooseCardType("Info icon");
        Assert.assertTrue(formPage.cardTitleName1("Info icon"));
        formPage.clickOnSaveButton();
        formPage.deleteCardFromForm("Divider", "Yes");
        formPage.deleteCardFromForm("Info icon", "Yes");
        formPage.clickOnSaveButton();
        formPage.clickOnBackButton();
        Assert.assertEquals(mobileFormPage.pageTitle(), "Mobile Forms");
        mobileFormPage.selectActionFromKebabList(formName, "Delete");
        mobileFormPage.deletePopUp("Delete Warning!", "Delete");
    }

    @Test(description = "Add new Popup with all the cards and Delete the popup")
    @Description("Add new Popup with all the cards and Delete the popup")
    public void test_02_addNewPopupWithAllTheCardsAndDelete() {
        Assert.assertEquals(mobileFormPage.pageTitle(), "Mobile Forms");
        mobileFormPage.chooseTabForDisplayList("Popups");
        mobileFormPage.createNewModule("Popup", popupName, "Create new popup");
        mobileFormPage.searchNameAndClick(popupName);
        formPage.chooseCardType("Short text");
        Assert.assertTrue(formPage.cardTitleName1("New Short Text"));
        formPage.chooseCardType("Number");
        Assert.assertTrue(formPage.cardTitleName1("New Number"));
        formPage.chooseCardType("Long Text");
        Assert.assertTrue(formPage.cardTitleName1("New Long Text"));
        formPage.chooseCardType("Phone number");
        Assert.assertTrue(formPage.cardTitleName1("New Phone Number"));
        formPage.chooseCardType("Email");
        Assert.assertTrue(formPage.cardTitleName1("New Email"));
        formPage.chooseCardType("Date");
        Assert.assertTrue(formPage.cardTitleName1("New Date"));
        formPage.chooseCardType("File");
        Assert.assertTrue(formPage.cardTitleName1("File Upload"));
        formPage.chooseCardType("Yes/No");
        Assert.assertTrue(formPage.cardTitleName1("Yes or No"));
        formPage.chooseCardType("Checkbox");
        Assert.assertTrue(formPage.cardTitleName1("Checkbox"));
        formPage.deleteCardFromPopup("Checkbox", "Yes");
        formPage.chooseCardType("Image");
        Assert.assertTrue(formPage.cardTitleName1("Image"));
        formPage.deleteCardFromPopup("Image", "Yes");
        formPage.chooseCardType("Title");
        Assert.assertTrue(formPage.cardTitleName1("new Title"));
        formPage.chooseCardType("Text");
        Assert.assertTrue(formPage.cardTitleName1("Text"));
        formPage.chooseCardType("Subtitle");
        Assert.assertTrue(formPage.cardTitleName1("new Subtitle"));
        formPage.chooseCardType("Link");
        Assert.assertTrue(formPage.cardTitleName1("Link"));
        formPage.deleteCardFromPopup("Link", "Yes");
        formPage.chooseCardType("Divider");
        Assert.assertTrue(formPage.cardTitleName1("Divider"));
        formPage.deleteCardFromPopup("Divider", "Yes");
        formPage.clickOnSaveButton();
        formPage.clickOnBackButton();
        mobileFormPage.chooseTabForDisplayList("Popups");
        mobileFormPage.selectActionFromKebabList(popupName, "Delete");
        mobileFormPage.deletePopUp("Delete Warning!", "Delete");
    }


    @Test(description = "Add New Mobile Form With Popup")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Add New Mobile Form With Popup")
    public void test_03_addNewMobileFormWithPopup() {
        Assert.assertEquals(mobileFormPage.pageTitle(), "Mobile Forms");
        mobileFormPage.chooseTabForDisplayList("Popups");
        mobileFormPage.createNewModule("Popup", popupName, "Create new popup");
        mobileFormPage.searchNameAndClick(popupName);
        formPage.chooseCardType("Short text");
        Assert.assertTrue(formPage.cardTitleName1("New Short Text"));
        formPage.chooseCardType("Button");
        Assert.assertTrue(formPage.cardTitleName1("Close Button"));
        formPage.clickOnSaveButton();
        formPage.clickOnBackButton();
        mobileFormPage.chooseTabForDisplayList("Popups");
        Assert.assertTrue(mobileFormPage.searchNameInTable(popupName));
        mobileFormPage.chooseTabForDisplayList("Forms");
        mobileFormPage.createNewModule("Form", formName, "Create new form");
        mobileFormPage.searchNameAndClick(formName);
        formPage.chooseCardType("info icon");
        Assert.assertTrue(formPage.cardTitleName1("info icon"));
        formPage.fillFieldInsideCard("selection", "Link to popup", null, popupName);
        formPage.fillFieldInsideCard("input", "Text color", "#f01414", null);
        formPage.clickOnSaveButton();
        formPage.clickOnBackButton();
        mobileFormPage.selectActionFromKebabList(formName, "Delete");
        mobileFormPage.deletePopUp("Delete Warning!", "Delete");
        mobileFormPage.chooseTabForDisplayList("Popups");
        mobileFormPage.selectActionFromKebabList(popupName, "Delete");
        mobileFormPage.deletePopUp("Delete Warning!", "Delete");
    }

    @Test(description = "Add new mobile form with Divider card")
    @Description("Add new mobile form with Divider card")
    public void test_04_addNewMobileFormWithDividerCard() {
        Assert.assertEquals(mobileFormPage.pageTitle(), "Mobile Forms");
        mobileFormPage.chooseTabForDisplayList("Forms");
        mobileFormPage.createNewModule("Form", formName, "Create new form");
        mobileFormPage.searchNameAndClick(formName);
        formPage.chooseCardType("Divider");
        Assert.assertTrue(formPage.cardTitleName1("Divider"));

        formPage.fillFieldInsideCard("input", "Color", "#bf3131", null);
        formPage.fillFieldInsideCard("selection", "Height", null, "5px");
        formPage.clickOnSaveButton();
        formPage.clickOnBackButton();
        Assert.assertEquals(mobileFormPage.pageTitle(), "Mobile Forms");
        mobileFormPage.chooseTabForDisplayList("Forms");
        mobileFormPage.selectActionFromKebabList(formName, "Delete");
        mobileFormPage.deletePopUp("Delete Warning!", "Delete");
    }

    @Test(description = "Add new mobile form with Checkbox card")
    @Description("Add new mobile form with Checkbox card")
    public void test_05_addNewMobileFormWithCheckboxCard() {
        Assert.assertEquals(mobileFormPage.pageTitle(), "Mobile Forms");
        mobileFormPage.chooseTabForDisplayList("Forms");
        mobileFormPage.createNewModule("Form", formName, "Create new form");
        mobileFormPage.searchNameAndClick(formName);

        formPage.chooseCardType("Checkbox");
        Assert.assertTrue(formPage.cardTitleName1("Checkbox"));
        formPage.clickOnSaveButton();
        formPage.clickOnBackButton();
        Assert.assertEquals(mobileFormPage.pageTitle(), "Mobile Forms");
        mobileFormPage.selectActionFromKebabList(formName, "Delete");
        mobileFormPage.deletePopUp("Delete Warning!", "Delete");
    }


    @Test(description = "Add new mobile form with Link card")
    @Description("Add new mobile form with Link card")
    public void test_06_addNewMobileFormWithLinkCard() {
        Assert.assertEquals(mobileFormPage.pageTitle(), "Mobile Forms");
        mobileFormPage.chooseTabForDisplayList("Forms");
        mobileFormPage.createNewModule("Form", formName, "Create new form");
        mobileFormPage.searchNameAndClick(formName);
        formPage.chooseCardType("Link");
        Assert.assertTrue(formPage.cardTitleName1("Link"));

        formPage.fillFieldInsideCard("input", "Enter URL", "https://www.ynet.co.il/", null);
        formPage.clickOnSaveButton();
        formPage.clickOnBackButton();
        Assert.assertEquals(mobileFormPage.pageTitle(), "Mobile Forms");

        mobileFormPage.selectActionFromKebabList(formName, "Delete");
        mobileFormPage.deletePopUp("Delete Warning!", "Delete");
    }


    @Test(description = "Add new mobile form with File card")
    @Description("Add new mobile form with File card")
    public void test_07_addNewMobileFormWithFileCard() {
        Assert.assertEquals(mobileFormPage.pageTitle(), "Mobile Forms");
        mobileFormPage.chooseTabForDisplayList("Forms");
        mobileFormPage.createNewModule("Form", formName, "Create new form");
        mobileFormPage.searchNameAndClick(formName);

        formPage.chooseCardType("File (read/approve)");
        Assert.assertTrue(formPage.cardTitleName1("File (read/approve)"));
        mobileFormPage.uploadFile("C:/tmp/sample.pdf");
        formPage.clickOnSaveButton();
        formPage.clickOnBackButton();
        Assert.assertEquals(mobileFormPage.pageTitle(), "Mobile Forms");
        mobileFormPage.selectActionFromKebabList(formName, "Delete");
        mobileFormPage.deletePopUp("Delete Warning!", "Delete");
    }


    @Test(description = "Add new mobile form with Image card")
    @Description("Add new mobile form with Image card")
    public void test_08_addNewMobileFormWithImageCard() {
        Assert.assertEquals(mobileFormPage.pageTitle(), "Mobile Forms");
        mobileFormPage.chooseTabForDisplayList("Forms");
        mobileFormPage.createNewModule("Form", formName, "Create new form");
        mobileFormPage.searchNameAndClick(formName);
        formPage.chooseCardType("Image");
        Assert.assertTrue(formPage.cardTitleName1("Image"));
        mobileFormPage.uploadFile("C:/tmp/sample.jpg");

        formPage.fillFieldInsideCard("selection", "Position", null, "Centered");
        formPage.fillFieldInsideCard("input", "Image Description", "image for automation", null);

        formPage.clickOnSaveButton();
        formPage.clickOnBackButton();
        Assert.assertEquals(mobileFormPage.pageTitle(), "Mobile Forms");

        mobileFormPage.selectActionFromKebabList(formName, "Delete");
        mobileFormPage.deletePopUp("Delete Warning!", "Delete");
    }


}