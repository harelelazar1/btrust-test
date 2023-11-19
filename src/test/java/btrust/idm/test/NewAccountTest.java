package btrust.idm.test;

import btrust.BaseIdmTest;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import btrust.idm.pageObject.LicensePage;
import btrust.idm.pageObject.NewAccountPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewAccountTest extends BaseIdmTest {

    @BeforeClass
    @Override
    public void loginToIdmSystem() {
        driver.get("https://btrustqa.scanovate.com");
        LoginPage login = new LoginPage(driver);
        login.login("scanovate", "system@scanovate.com", "Scanovate2018!");
    }


    @Epic("Scanovate's Admin")
    @Test(description = "Enter to new account page")
    @Description("Enter to new account page check the title of page and check that back to license page after click on breadcrumbs button")
    public void t_01enterToNewAccountPage() {
        LicensePage licenses = new LicensePage(driver);
        licenses.addNewAccountButton();
        NewAccountPage newAccount = new NewAccountPage(driver);
        String newAccountTitle = newAccount.newAccountTitle("New Account");
        Assert.assertEquals("New Account", newAccountTitle);
        String companyInformationTitle = newAccount.companyInformationTitle("Company information");
        Assert.assertEquals("Company information", companyInformationTitle);
        String contactPersonTitle = newAccount.contactPersonTitle("Contact Person");
        Assert.assertEquals("Contact Person", contactPersonTitle);
        String demoLicenceTitle = newAccount.demoLicenceTitle("Demo/Licence");
        Assert.assertEquals("Demo/Licence", demoLicenceTitle);
        String userAdminTitle = newAccount.userAdminTitle("User Admin");
        Assert.assertEquals("User Admin", userAdminTitle);

        newAccount.breadcrumbsPreviousPage();
        String expected = licenses.licensePageTitle("B-Trust Licenses");
        Assert.assertEquals("B-Trust Licenses", expected);
    }

    @Epic("Scanovate's Admin")
    @Test(description = "Check error message appear")
    @Description("Enter to new account page and click on generate account button without fill the fields")
    public void t_02errorMessage() {
        LicensePage licenses = new LicensePage(driver);
        licenses.addNewAccountButton();
        NewAccountPage newAccount = new NewAccountPage(driver);
        newAccount.generateAccountButton();

        String organizationName = newAccount.organizationNameErrorMessage("Please add a valid organization name");
        Assert.assertEquals("Please add a valid organization name", organizationName);
        String screensSelect = newAccount.screensErrorMessage("Please fill the required information");
        Assert.assertEquals("Please fill the required information", screensSelect);
        String dashboardSelect = newAccount.dashboardErrorMessage("Please fill the required information");
        Assert.assertEquals("Please fill the required information", dashboardSelect);
        String firstName = newAccount.firstNameErrorMessage("Please insert a first name");
        Assert.assertEquals("Please insert a first name", firstName);
        String lastName = newAccount.lastNameErrorMessage("Please insert a last name");
        Assert.assertEquals("Please insert a last name", lastName);
        String languageSelect = newAccount.languageErrorMessage("Please fill the required information");
        Assert.assertEquals("Please fill the required information", languageSelect);
        String adminEmail = newAccount.adminEmailErrorMessage("Please enter a valid email");
        Assert.assertEquals("Please enter a valid email", adminEmail);
//        String password = newAccount.passwordErrorMessage();
//        Assert.assertEquals("Please fill the required information", password);

        newAccount.breadcrumbsPreviousPage();
        String expected = licenses.licensePageTitle("B-Trust Licenses");
        Assert.assertEquals("B-Trust Licenses", expected);
    }

    @Epic("Scanovate's Admin")
    @Test(description = "Check error message appear if type 2 chars in organization name field")
    @Description("Type 2 chars in organization name field and check that error message appear")
    public void t_03errorMessageCharacters() {
        LicensePage licenses = new LicensePage(driver);
        licenses.addNewAccountButton();
        NewAccountPage newAccount = new NewAccountPage(driver);
        newAccount.addExistAccount("", "123", "Banks", "Tel-Aviv", "search", "idm", "", "", "English", "g", "1");

        String organizationName = newAccount.organizationNameErrorMessage("Please add a valid organization name");
        Assert.assertEquals("Please add a valid organization name", organizationName);
        String firstName = newAccount.firstNameErrorMessage("Please insert a first name");
        Assert.assertEquals("Please insert a first name", firstName);
        String lastName = newAccount.lastNameErrorMessage("Please insert a last name");
        Assert.assertEquals("Please insert a last name", lastName);
        String adminEmail = newAccount.adminEmailErrorMessage("Please enter a valid email");
        Assert.assertEquals("Please enter a valid email", adminEmail);
        String password = newAccount.passwordErrorMessage("Your password does not stand in the company's password policy");
        Assert.assertEquals(password, "Your password does not stand in the company's password policy");

        newAccount.breadcrumbsPreviousPage();
        String expected = licenses.licensePageTitle("B-Trust Licenses");
        Assert.assertEquals("B-Trust Licenses", expected);
    }
}