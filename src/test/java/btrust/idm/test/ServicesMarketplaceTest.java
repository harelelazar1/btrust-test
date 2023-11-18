package btrust.idm.test;

import btrust.BaseIdmTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.idm.pageObject.ServicesMarketplacePage;
import btrust.idm.pageObject.SettingsPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ServicesMarketplaceTest extends BaseIdmTest {

    @BeforeMethod
    public void navigateToServicesMarketplacePage() {
        NavigationPage navigation = new NavigationPage(driver);
        navigation.settingsButton();
        driver.navigate().refresh();
        SettingsPage setting = new SettingsPage(driver);
        setting.chooseTab("Settings", "Services Marketplace");
    }

    @Test(description = "enter to services marketplace")
    @Description("enter to services marketplace and check that all the elements appear on the screen")
    public void t_01enterToServicesMarketplace() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.statusFilterIsEnable();

        String servicesMarketplaceTitle = servicesMarketplace.servicesMarketplaceTitle();
        Assert.assertEquals("Services Marketplace", servicesMarketplaceTitle);
    }

    @Test(description = "filter by service")
    @Description("filter by service and choose 1 option")
    public void t_02filterByService() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByService("Scanovate OCR");
        Assert.assertEquals("Scanovate OCR", servicesMarketplace.vendorName("Scanovate OCR", "Scanovate OCR"));
    }

    @Test(description = "filter by status")
    @Description("filter by status and choose 1 option")
    public void t_03filterByStatus() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByStatus("Active");
        String expected = servicesMarketplace.statusVendor("Active");
        Assert.assertEquals("Active", expected);
        servicesMarketplace.statusClearButton();
    }

    @Test(description = "filter by status, select 2 options")
    @Description("open status filter and select 2 options and click on clear button")
    public void t_04filterByStatus2Options() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByStatus("Active");
        servicesMarketplace.filterByStatus("Uninstall");

        String expected = servicesMarketplace.statusVendor("Active");
        Assert.assertEquals(servicesMarketplace.statusVendor("Active"), expected);
        servicesMarketplace.statusClearButton();
    }

    @Test(description = "Check that results appear after typing 3 characters")
    @Description("type 3 characters in contain text field and check that results appear")
    public void t_05filterByContainText3Characters() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByContainText("Reg");

        String expected = servicesMarketplace.vendorName("Regula", "Regula");
        Assert.assertEquals(expected, "Regula");
        servicesMarketplace.clearContainText();
    }

    @Test(description = "Check that No vendors were found appear")
    @Description("Type 3 characters of a vendor name that does not exist in the repository and check that No vendors were found appear")
    public void t_06filterByContainTextNoResult() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByContainText("bla");

        String expected = servicesMarketplace.noVendorFoundMessage("No vendors were found");
        Assert.assertEquals(expected, "No vendors were found");
        servicesMarketplace.clearContainText();
    }

    @Test(description = "filter by contain text")
    @Description("filter by contain text")
    public void t_07filterByContainText() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByContainText("regula");

        String expected = servicesMarketplace.vendorName("Regula", "Regula");
        Assert.assertEquals(expected, "Regula");
        servicesMarketplace.clearContainText();
    }

    @Test(description = "filter by service and status")
    @Description("filter by service and status")
    public void t_08filterByServiceAndStatus() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByService("OCR");
        servicesMarketplace.filterByStatus("Active");

        String expected = servicesMarketplace.statusVendor("Active");
        Assert.assertEquals("Active", expected);
        servicesMarketplace.serviceClearButton();
        servicesMarketplace.statusClearButton();
    }

    @Test(description = "filter by service and contain text")
    @Description("filter by service and contain text")
    public void t_09filterByServiceAndContainText() {
        SettingsPage setting = new SettingsPage(driver);
        setting.chooseTab("Settings", "Services Marketplace");

        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByContainText("Mit");
        servicesMarketplace.filterByService("OCR");
        String expected = servicesMarketplace.vendorName("Mitek", "Mitek");
        Assert.assertEquals("Mitek", expected);
        servicesMarketplace.clearContainText();
        servicesMarketplace.serviceClearButton();
    }

    @Test(description = "filter by status and contain text")
    @Description("filter by status and contain text")
    public void t_10filterByStatusAndContainText() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByStatus("Disabled");
        servicesMarketplace.filterByContainText("Mit");

        String expected = servicesMarketplace.vendorName("Mitek", "Mitek");
        Assert.assertEquals("Mitek", expected);
        servicesMarketplace.clearContainText();
        servicesMarketplace.statusClearButton();
    }

    @Test(description = "filter by service, status and contain text")
    @Description("filter by status and contain text")
    public void t_11filterByServiceAndStatusAndContainText() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByService("Scanovate Liveness");
        servicesMarketplace.filterByStatus("Active");
        servicesMarketplace.filterByContainText("Scanovate Liveness");

        String expected = servicesMarketplace.vendorName("Scanovate Liveness", "Scanovate Liveness");
        Assert.assertEquals("Scanovate Liveness", expected);
        servicesMarketplace.statusClearButton();
        servicesMarketplace.serviceClearButton();
        servicesMarketplace.clearContainText();
    }

    @Test(description = "Check that No vendors were found appear")
    @Description("filter by service and status and check that No vendors were found appear")
    public void t_12filterByServiceAndStatusNoResult() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByService("OCR");
        servicesMarketplace.filterByStatus("Uninstalled");

        String expected = servicesMarketplace.noVendorFoundMessage("No vendors were found");
        Assert.assertEquals(expected, "No vendors were found");
        servicesMarketplace.statusClearButton();
        servicesMarketplace.serviceClearButton();
    }
}