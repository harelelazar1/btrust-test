package btrust.idm.test;

import btrust.idm.pageObject.CasePage;
import btrust.idm.pageObject.CasesPage;
import btrust.idm.pageObject.SearchEnginePage;
import btrust.idm.pageObject.SearchResultsPage;
import btrust.BaseIdmTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import btrust.idm.pageObject.DevelopmentToolsPage;
import btrust.idm.pageObject.SettingsPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SaveDataTest extends BaseIdmTest {

    @BeforeClass
    @Override
    public void loginToIdmSystem() {
        LoginPage login = new LoginPage(driver);
        login.login("Liad15", "liadtu15@qa.com", "Liad1234");
    }

    @Test(enabled = false, description = "Clear data")
    @Description("Check that data clear if save data checkbox is not selected")
    public void t01_clearData() throws InterruptedException {
        NavigationPage navigation = new NavigationPage(driver);
        navigation.settingsButton();
        SettingsPage settings = new SettingsPage(driver);
        settings.chooseTab("Settings", "Developer Tools");
        DevelopmentToolsPage developmentSettings = new DevelopmentToolsPage(driver);
        String title = developmentSettings.developmentToolsTitle("Developer Tools");
        Assert.assertEquals(title, "Developer Tools");
        Assert.assertFalse(developmentSettings.saveDataCheckBox());
        Assert.assertEquals(developmentSettings.amountField(), "1");
        developmentSettings.sendButton();
        navigation.mainMenuList("Search");
        SearchEnginePage searchEngine = new SearchEnginePage(driver);
        searchEngine.organizationButton();
        searchEngine.searchOrganization("scanovate");
        searchEngine.searchButton();
        SearchResultsPage searchResults = new SearchResultsPage(driver);
        searchResults.runWorkflow();
        CasePage ca = new CasePage(driver);
        Assert.assertTrue(ca.caseID());
        navigation.mainMenuList("Cases (Corporates)");
        CasesPage cases = new CasesPage(driver);
        Assert.assertTrue(cases.firstCaseIsDisplayed());
        Thread.sleep(120000);
        driver.navigate().refresh();
        String numberOfCases = cases.numbersOfCases("0");
        Assert.assertEquals(numberOfCases, "0");
    }
}