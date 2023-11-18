package btrust.btrustOne.admin.usersManager.subCompaniesPage.tests;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.usersManager.subCompaniesPage.pageObject.SubCompaniesPage;
import btrust.btrustOne.admin.usersManager.users.pageObject.UsersPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SubCompanyTest extends BaseAdminUserTest {

    String subCompanyName;
    AdministratorPage administratorPage;
    SubCompaniesPage subCompaniesPage;
    UsersPage usersPage;

    @BeforeMethod
    @Step("Enter to sub company screen")
    public void navigateToSubCompanyScreen() {
        administratorPage = new AdministratorPage(driver);
        administratorPage.chooseFromSideBar("Sub Companies");
        subCompaniesPage = new SubCompaniesPage(driver);
        Assert.assertEquals(subCompaniesPage.subCompaniesTitle(), "Sub Companies");
    }

    @Test(description = "Add new sub company",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Add new sub company and check that the new sub company displayed in the table")
    public void t01_addNewSubCompany() {
        subCompaniesPage.clickOnAddSubCompanyButton();
        Assert.assertEquals(subCompaniesPage.newSubCompanyTitle(), "New Sub Company");
        subCompaniesPage.fillInFiled("Sub Company Name", "Qa automation test" + randomString());
        subCompanyName = subCompaniesPage.valueOfSubCompanyNameField();
        subCompaniesPage.fillInFiled("LEI Code", randomString());
        subCompaniesPage.selectCountry("Israel");
        subCompaniesPage.clickOnButton("Save");
        subCompaniesPage.searchField(subCompanyName);
        Assert.assertEquals(subCompaniesPage.subCompanyInformation("SUB COMPANY NAME", subCompanyName), subCompanyName);
    }

    @Test(description = "Close the popup",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("close the popup of create new sub company")
    public void t02_closePopup() {
        subCompaniesPage.clickOnAddSubCompanyButton();
        Assert.assertEquals(subCompaniesPage.newSubCompanyTitle(), "New Sub Company");
        subCompaniesPage.closePopupButton();
        Assert.assertEquals(subCompaniesPage.subCompaniesTitle(), "Sub Companies");
        subCompaniesPage.clickOnAddSubCompanyButton();
        Assert.assertEquals(subCompaniesPage.newSubCompanyTitle(), "New Sub Company");
        subCompaniesPage.clickOnButton("Cancel");
        Assert.assertEquals(subCompaniesPage.subCompaniesTitle(), "Sub Companies");
    }

    @Test(description = "Mandatory fields",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Check that save button is disabled if the user not fill the mandatory fields")
    public void t03_mandatoryFields() {
        subCompaniesPage.clickOnAddSubCompanyButton();
        Assert.assertEquals(subCompaniesPage.newSubCompanyTitle(), "New Sub Company");
        Assert.assertFalse(subCompaniesPage.buttonIsEnabled("Save"));
        subCompaniesPage.closePopupButton();
    }
}