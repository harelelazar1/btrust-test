package btrust.idm.test;

import btrust.BaseIdmTest;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import btrust.idm.pageObject.LicensePage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LicenseTest extends BaseIdmTest {

    @BeforeClass
    @Override
    public void loginToIdmSystem() {
        driver.get("https://btrustqa.scanovate.com");
        LoginPage login = new LoginPage(driver);
        login.login("scanovate", "system@scanovate.com", "Scanovate2018!");
    }

    @Test(description = "filter by Company’s name - full name")
    @Description("type Company’s name in search field - full name")
    public void t01_filterByCompanyNameFullName() {
        LicensePage licenses = new LicensePage(driver);
        licenses.filterBySearchField("Scanovate QA");

        String expected = licenses.companyNameAfterFilter("Scanovate QA");
        Assert.assertEquals("Scanovate QA", expected);
    }

    @Test(description = "filter by Company’s name - 3 characters")
    @Description("type Company’s name in search field - 3 characters")
    public void t02_filterByCompanyName3Char() {
        LicensePage licenses = new LicensePage(driver);
        licenses.filterBySearchField("ShlomoSixt47609");

        String expected = licenses.companyNameAfterFilter("ShlomoSixt47609");
        Assert.assertEquals("ShlomoSixt47609", expected);
    }

    @Test(description = "filter by Contact person")
    @Description("type Contact person in search field")
    public void t03_filterByContactPersonFullName() {
        LicensePage licenses = new LicensePage(driver);
        licenses.filterBySearchField("Patrick Boscher");

        Assert.assertEquals("Patrick Boscher", licenses.contactPersonAfterFilter("Patrick Boscher"));
    }

    @Test(description = "check that appear no result message - english characters")
    @Description("check that appear no result message - english characters")
    public void t04_noResultsMessageEngChar() {
        LicensePage licenses = new LicensePage(driver);
        licenses.filterBySearchField("zzz");

        String expected = licenses.noResultsFoundMessage("No results found");
        Assert.assertEquals("No results found", expected);
    }
}