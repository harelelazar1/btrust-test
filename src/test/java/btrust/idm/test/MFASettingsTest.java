package btrust.idm.test;

import btrust.BaseIdmTest;

public class MFASettingsTest extends BaseIdmTest {

//    @Step("Turn on Authentication")
//    @BeforeClass
//    @Override
//    public void setupLogin() {
//        LoginPage login = new LoginPage(driver);
//        login.login("Liad15", "liadtu15@qa.com", "Liad1234");
//
//        NavigationPage navigationPage = new NavigationPage(driver);
//        navigationPage.settingsButton();
//        SettingsPage settingsPage = new SettingsPage(driver);
//        settingsPage.chooseTab("Settings", "MFA Settings");
//        MFASettingsPage mfaSettingsPage = new MFASettingsPage(driver);
//        Assert.assertTrue(mfaSettingsPage.MFASettingsTitleIsDisplayed("MFA Settings"));
//
//        mfaSettingsPage.enableMFACheckbox();
//        mfaSettingsPage.chooseMFAAuthentication("2FA");
//        mfaSettingsPage.saveButton();
//    }
//
//    @Step("Turn off Authentication and check that Authentication is not appear in login flow")
//    @AfterClass
//    public void disabledMFACheckbox() {
//        NavigationPage navigationPage = new NavigationPage(driver);
//        navigationPage.settingsButton();
//        SettingsPage settingsPage = new SettingsPage(driver);
//        settingsPage.chooseTab("Settings", "MFA Settings");
//        MFASettingsPage mfaSettingsPage = new MFASettingsPage(driver);
//        Assert.assertTrue(mfaSettingsPage.MFASettingsTitleIsDisplayed("MFA Settings"));
//        mfaSettingsPage.enableMFACheckbox();
//        mfaSettingsPage.saveButton();
//        navigationPage.logOut();
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login("Liad15", "liadtu15@qa.com", "Liad1234");
//        SearchPage searchPage = new SearchPage(driver);
//        Assert.assertEquals(searchPage.noResultsMessage("No results found"), "No results found");
//    }
//
//    @Step("Logout from the system")
//    @BeforeMethod
//    public void enterToMFASettingsPage() {
//        NavigationPage navigationPage = new NavigationPage(driver);
//        navigationPage.logOut();
//    }
//
//    @Test(description = "Perform login with authentication")
//    @Description("Add Authentication and perform login")
//    public void t01_performLoginWithAuthentication() {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login("Liad15", "liadtu15@qa.com", "Liad1234");
//        Assert.assertFalse(loginPage.clickOnVerifyCodeButtonIfIsEnabled());
//        loginPage.typeOTP("1", "2", "3", "4", "5", "6");
//        Assert.assertTrue(loginPage.clickOnVerifyCodeButtonIfIsEnabled());
//
//        SearchPage searchPage = new SearchPage(driver);
//        Assert.assertEquals(searchPage.noResultsMessage("No results found"), "No results found");
//    }
//
//    @Test(description = "Error message of otp")
//    @Description("Type wrong opt and check that appear error message")
//    public void t02_errorMessageOTP() {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login("Liad15", "liadtu15@qa.com", "Liad1234");
//        Assert.assertFalse(loginPage.clickOnVerifyCodeButtonIfIsEnabled());
//        loginPage.typeOTP("1", "2", "3", "4", "5", "2");
//        Assert.assertTrue(loginPage.clickOnVerifyCodeButtonIfIsEnabled());
//        Assert.assertTrue(loginPage.wrongCodeMessageIsDisplayed("Wrong code"));
//
//        loginPage.typeOTP("1", "2", "3", "4", "5", "6");
//        Assert.assertTrue(loginPage.clickOnVerifyCodeButtonIfIsEnabled());
//        SearchPage searchPage = new SearchPage(driver);
//        Assert.assertEquals(searchPage.noResultsMessage("No results found"), "No results found");
//    }
}