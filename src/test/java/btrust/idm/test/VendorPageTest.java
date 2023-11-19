package btrust.idm.test;

import btrust.BaseIdmTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import btrust.idm.pageObject.*;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VendorPageTest extends BaseIdmTest {

    @BeforeMethod
    public void beforeMethod() {
        NavigationPage navigation = new NavigationPage(driver);
        navigation.settingsButton();
        SettingsPage setting = new SettingsPage(driver);
        setting.chooseTab("Settings", "Workflow Builder");
        WorkflowBuilderPage workflowBuilderPage = new WorkflowBuilderPage(driver);
        Assert.assertEquals(workflowBuilderPage.workflowBuilderTitle("Workflow Builder"), "Workflow Builder");
        setting.chooseTab("Settings", "Services Marketplace");
        ServicesMarketplacePage servicesMarketplacePage = new ServicesMarketplacePage(driver);
        Assert.assertEquals(servicesMarketplacePage.servicesMarketplaceTitle(), "Services Marketplace");
        driver.navigate().refresh();
    }

    @Test(enabled = false,description = "edit vendor with status active")
    @Description("choose vendor with status active and click to edit")
    public void t01_editVendorStatusActive() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByContainText("Regula");
        servicesMarketplace.chooseVendorByName("Regula");
        VendorPage vendor = new VendorPage(driver);
        vendor.editVendor("https://regula.ado-tech.com/webapi", "TestUser", "LiadTest");
        servicesMarketplace.chooseVendorByName("Regula");

        vendor.editVendor("https://api.regulaforensics.com/webapi", "TestUser", "Regul@SdkTest");
    }

    @Test(description = "clear vendor with status active")
    @Description("enter to vendor with status active, click on clear and check the error messages")
    public void t02_clearVendorStatusActive() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByContainText("Regula");
        servicesMarketplace.chooseVendorByName("Regula");
        VendorPage vendor = new VendorPage(driver);
        vendor.clearVendor();
        vendor.editButton();

        String error = vendor.errorMessage("Please fill the required information");
        Assert.assertEquals(error, "Please fill the required information");
    }

    @Test(description = "check the link failed popup")
    @Description("click on edit when the url is wrong - status Active")
    public void t03_linkFailedStatusActive() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByContainText("Regula");
        servicesMarketplace.chooseVendorByName("Regula");
        VendorPage vendor = new VendorPage(driver);
        vendor.editVendor("https://regula.ado-tech.com", "TestUser2", "Regul@SdkTest");

        String linkField = vendor.linkFailedPopUp();
        Assert.assertEquals(linkField, "Link Failed");
        vendor.linkFieldClose();
    }

    @Test(description = "check popup blocker after click to replace")
    @Description("choose vendor with status disabled and click to replace")
    public void t04_popUpBlocker() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByContainText("Mitek");
        servicesMarketplace.chooseVendorByName("Mitek");
        VendorPage vendor = new VendorPage(driver);
        vendor.editVendor("https://globalidentity.eu.sandbox.mitekcloud.com/api/OcrVerify/v2/Dossierd", "scanovate.sandboxeu", ":Vc5y3`=H)");

        String excepted = vendor.doubleBookedTitle("Double Booked!");
        Assert.assertEquals(excepted, "Double Booked!");

        vendor.popUpBlockedCancel();
    }

    @Test(description = "edit vendor with status disabled")
    @Description("choose vendor with status disabled and click to edit")
    public void t05_replaceVendorStatusDisabled() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByContainText("Mitek");
        servicesMarketplace.chooseVendorByName("Mitek");
        VendorPage vendor = new VendorPage(driver);
        vendor.editButton();
        vendor.popUpBlockedApprove();
        servicesMarketplace.filterByContainText("Regula");
        servicesMarketplace.chooseVendorByName("Regula");
        vendor.editVendor("https://api.regulaforensics.com/webapi", "TestUser", "Regul@SdkTest");
        vendor.popUpBlockedApprove();
    }

    @Test(description = "check the error messages")
    @Description("click on edit when the fields is blank - status disabled")
    public void t06_blankFieldsStatusActive() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByContainText("Mitek");
        servicesMarketplace.chooseVendorByName("Mitek");
        VendorPage vendor = new VendorPage(driver);
        vendor.clearVendor();
        vendor.editButton();

        String error = vendor.errorMessage("Please fill the required information");
        Assert.assertEquals(error, "Please fill the required information");
    }

    @Test(description = "check the link failed popup")
    @Description("click on edit when the url is wrong - status disabled")
    public void t07_linkFailedsStatusActive() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByContainText("Mitek");
        servicesMarketplace.chooseVendorByName("Mitek");
        VendorPage vendor = new VendorPage(driver);
        vendor.editVendor("https://projects.invisionapp.com/d/main#/console/19536389/409936435/preview", "bla", ":Vc5y3`=H)");
        vendor.popUpBlockedApprove();

        String linkField = vendor.linkFailedPopUp();
        Assert.assertEquals(linkField, "Link Failed");
        vendor.linkFieldClose();
    }

    @Test(description = "check error message when type wrong URL")
    @Description("type wrong URL and check that appear error message")
    public void t08_errorMessageWrongURL() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByContainText("Regula");
        servicesMarketplace.chooseVendorByName("Regula");
        VendorPage vendor = new VendorPage(driver);
        vendor.editVendor("https:", "TestUser2", "Regul@SdkTest");

        String error = vendor.errorMessageURL("Please fill the required information");
        Assert.assertEquals(error, "Please fill the required information");
    }

    @Test(description = "Error message key & value fields empty")
    @Description("click that error message appear if key & value fields is empty")
    public void t09_keyValueFieldsValidation() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByContainText("Regula");
        servicesMarketplace.chooseVendorByName("Regula");
        VendorPage vendor = new VendorPage(driver);
        vendor.addHeaderButton();
        vendor.editButton();

        String expected = vendor.errorMessageKeyValue("Please fill the required information");
        Assert.assertEquals(expected, "Please fill the required information");
    }

    @Test(description = "check that error message of key & value fields appear")
    @Description("click on edit button and check that key & value fields appear")
    public void t10_errorMessageValue() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByContainText("Regula");
        servicesMarketplace.chooseVendorByName("Regula");
        VendorPage vendor = new VendorPage(driver);
        vendor.addHeaderButton();
        vendor.fillKeyField("k");
        vendor.editButton();

        String key = vendor.errorMessageKeyValue("Please fill the required information");
        Assert.assertEquals(key, "Please fill the required information");

        vendor.removeButton();
        vendor.addHeaderButton();
        vendor.fillValueField("v");
        vendor.editButton();

        String value = vendor.errorMessageKeyValue("Please fill the required information");
        Assert.assertEquals(value, "Please fill the required information");
    }

    @Test(description = "Add and remove key & value fields")
    @Description("Add and remove key & value fields")
    public void t11_fillDetailsKeyValueFields() {
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        servicesMarketplace.filterByContainText("Regula");
        servicesMarketplace.chooseVendorByName("Regula");
        VendorPage vendor = new VendorPage(driver);
        vendor.addHeaderButton();
        vendor.fillKeyField("k");
        vendor.fillValueField("v");
        vendor.editButton();
        servicesMarketplace.filterByContainText("Regula");
        servicesMarketplace.chooseVendorByName("Regula");

        String keyField = vendor.keyField();
        Assert.assertEquals(keyField, "k");
        String valueField = vendor.valueField();
        Assert.assertEquals(valueField, "v");
        vendor.removeButton();

        Assert.assertFalse(vendor.keyFieldsIsNotDisplayed());
        Assert.assertFalse(vendor.valueFieldsIsNotDisplayed());

        vendor.editButton();
        servicesMarketplace.filterByContainText("Regula");
        servicesMarketplace.chooseVendorByName("Regula");

        Assert.assertFalse(vendor.keyFieldsIsNotDisplayed());
        Assert.assertFalse(vendor.valueFieldsIsNotDisplayed());
    }

    @Test(description = "Add vendors")
    @Description("Login with new account and add vendors")
    public void t_12addVendors() {
        NavigationPage navigation = new NavigationPage(driver);
        navigation.logOut();
        LoginPage login = new LoginPage(driver);
        login.login("scanovate", "system@scanovate.com", "Scanovate2018!");
        ServicesMarketplacePage servicesMarketplace = new ServicesMarketplacePage(driver);
        int num = servicesMarketplace.totalAdmin();
        servicesMarketplace.addNewAccount("Liad" + num, "123", "Banks", "Tel-Aviv", "search", "idm", "Liad", "Tobi", "English", "liadtu" + num, "@qa.com", "Liad1234");
        String companyName = servicesMarketplace.copyCompanyName();
        servicesMarketplace.copyEmail();
        servicesMarketplace.generateAccountButton();
        String user = servicesMarketplace.copyEmail();
        NewAccountPage newAccount = new NewAccountPage(driver);
        newAccount.popupSuccessCreateAccountCloseButton();
        navigation.logOut();
        login.login(companyName, user, "Liad1234");
        navigation.settingsButton();
//        servicesMarketplace.filterByContainText("Regula");
//        servicesMarketplace.chooseVendorByName("Regula", "Regula");
        VendorPage vendor = new VendorPage(driver);
//        vendor.vendor3Field("https://regula.ado-tech.com/webapi", "TestUser2", "Regul@SdkTest");
        servicesMarketplace.filterByContainText("Scanovate Liveness");
        servicesMarketplace.chooseVendorByName("Scanovate Liveness");
        vendor.vendor2Field("http://10.0.2.23", "http://10.0.2.23");
        servicesMarketplace.filterByContainText("Scanovate OCR");
        servicesMarketplace.chooseVendorByName("Scanovate OCR");
        vendor.vendor2Field("http://10.0.2.23", "http://10.0.2.23");
        servicesMarketplace.filterByContainText("Scanovate Biometric");
        servicesMarketplace.chooseVendorByName("Scanovate Biometric");
        vendor.vendor1Field("http://10.0.2.24:3001");
        servicesMarketplace.filterByContainText("Scanovate FaceDb");
        servicesMarketplace.chooseVendorByName("Scanovate FaceDb");
        vendor.vendor1Field("https://face-dev.scanovate.com");
//        servicesMarketplace.filterByContainText("RDC");
//        servicesMarketplace.chooseVendorByName("RDC","RDC");
//        vendor.RDCVendorFields("https://service.rdc.eu.com/api/grid-service/v1/grid-inquiry/realtime", "Liron1", "Scan2018", "VD03000P");
//        servicesMarketplace.filterByContainText("Bureau van Dijk");
//        servicesMarketplace.chooseVendorByName("Bureau van Dijk","Bureau van Dijk");
//        vendor.BureauVanDijkendorFields("https://Orbis.bvdinfo.com/api/orbis", "18TL69866f5a09a7ea1190b5d89d672fa480");
//        servicesMarketplace.filterByContainText("GenId");
//        servicesMarketplace.chooseVendorByName("GenId", "GenId");
//        vendor.genldFields("https://www.checkid.online/inspectionjob/", "Scanovate_Demo", "Scanovate@2019");
//        vendor.popUpBlockedApprove();
//        servicesMarketplace.filterByContainText("Mitek");
//        servicesMarketplace.chooseVendorByName("Mitek", "Mitek");
//        vendor.vendor4Field("https://globalidentity.eu.sandbox.mitekcloud.com/api/OcrVerify/v2/Dossierd", "https://identity.eu.sandbox.mitekcloud.com/connect/token", "scanovate.sandboxeu", ":Vc5y3`=H)");
//        vendor.popUpBlockedApprove();

//        servicesMarketplace.filterByContainText("Mitek");
//        String mitekA = servicesMarketplace.statusVendor("Mitek", "Mitek");
//        Assert.assertEquals("Active", mitekA);
//        servicesMarketplace.filterByContainText("Regula");
//        String regulaD = servicesMarketplace.statusVendor("Regula", "Regula");
//        Assert.assertEquals("Disabled", regulaD);
//        servicesMarketplace.filterByContainText("RDC");
//        String RDC = servicesMarketplace.statusVendor("RDC","RDC");
//        Assert.assertEquals("Active", RDC);
//        servicesMarketplace.filterByContainText("Bureau van Dijk");
//        String BureauVanDijkendor = servicesMarketplace.statusVendor("Bureau van Dijk","Bureau van Dijk");
//        Assert.assertEquals("Active", BureauVanDijkendor);
        servicesMarketplace.filterByContainText("Scanovate Liveness");
        String sLiveness = servicesMarketplace.statusVendor("Scanovate Liveness", "Scanovate Liveness");
        Assert.assertEquals("Active", sLiveness);
        servicesMarketplace.filterByContainText("Scanovate OCR");
        String ScanovateOcr = servicesMarketplace.statusVendor("Scanovate OCR", "Scanovate OCR");
        Assert.assertEquals("Active", ScanovateOcr);
        servicesMarketplace.filterByContainText("Scanovate Biometric");
        String ScanovateBio = servicesMarketplace.statusVendor("Scanovate Biometric", "Scanovate Biometric");
        Assert.assertEquals("Active", ScanovateBio);
//        servicesMarketplace.filterByContainText("GenId");
//        String GenId = servicesMarketplace.statusVendor("GenId", "GenId");
//        Assert.assertEquals("Disabled", GenId);
        servicesMarketplace.filterByContainText("Scanovate FaceDb");
        String ScanovateFace = servicesMarketplace.statusVendor("Scanovate FaceDb", "Scanovate FaceDb");
        Assert.assertEquals("Active", ScanovateFace);
    }
}