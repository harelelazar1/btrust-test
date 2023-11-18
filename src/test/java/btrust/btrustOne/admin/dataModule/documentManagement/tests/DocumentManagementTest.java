package btrust.btrustOne.admin.dataModule.documentManagement.tests;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.dataModule.documentManagement.pageObject.DocumentManagementPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.DBUtils;

import java.io.File;
import java.io.IOException;

public class DocumentManagementTest extends BaseAdminUserTest {

    AdministratorPage administratorPage;
    DocumentManagementPage documentManagementPage;
    DBUtils dbUtils = new DBUtils();
    String template_Id = dbUtils.getTemplateNumber();


    @BeforeClass
    @Step("Enter to Document Management screen")
    public void navigateToDocumentManagementtScreen() throws IOException {
        administratorPage = new AdministratorPage(driver);
        administratorPage.chooseFromSideBar("Documents Manager");
        documentManagementPage = new DocumentManagementPage(driver);
        documentManagementPage.createNewFile("C:/tmp/sample.pdf");
        Assert.assertEquals(documentManagementPage.documentManagementTitle("Documents Manager"), "Documents Manager");
    }

    @BeforeMethod
    @Step("Enter to Document Management screen")
    public void setDocumentManagement() {
        administratorPage = new AdministratorPage(driver);
        administratorPage.chooseFromSideBar("Document Management");
        documentManagementPage = new DocumentManagementPage(driver);
        Assert.assertEquals(documentManagementPage.documentManagementTitle("Documents Manager"), "Documents Manager");
    }

    @AfterClass
    @Step("Enter to Document Management screen")
    public void deleteFileFromFolder() throws IOException {
        documentManagementPage.deleteFileFromFolder("C:/tmp/sample.pdf");
    }

    @Test(description = "Add New E-Document")
    @Severity(SeverityLevel.CRITICAL)
    public void t01_addNewEformDoc() {
        String documentName = "Eform " + randomString();
        String documentIdentification = "Identification " + randomString();
        documentManagementPage.addNewDocumentButton();
        documentManagementPage.chooseNewDocumentTypeFromList("New E-Document");
        Assert.assertEquals(documentManagementPage.newDocumentRequestTitle1("Document Information"), "Document Information");
        documentManagementPage.fillEformDocumentData("Document Name", documentName, null);
        documentManagementPage.fillEformDocumentData("Document Identification", documentIdentification, null);
        documentManagementPage.fillEformDocumentData("Template ID", null, template_Id);
        documentManagementPage.newDocumentClickOnButton("Continue");
        Assert.assertEquals(documentManagementPage.newDocumentRequestTitle2("Document Information"), "Document Information");
        Assert.assertEquals(documentManagementPage.checkEformDocumentDetails("Document Name"), documentName);
        Assert.assertEquals(documentManagementPage.checkEformDocumentDetails("Document Identification"), documentIdentification);
        Assert.assertEquals(documentManagementPage.checkEformDocumentDetails("Document Type"), "E-Form");
        documentManagementPage.newDocumentClickOnButton("Continue");
        Assert.assertEquals(documentManagementPage.newVersionTitle(), documentName);
        Assert.assertEquals(documentManagementPage.getStatusFromList(0), "Draft");
        documentManagementPage.selectTodayAsActivationDate();
        Assert.assertEquals(documentManagementPage.getStatusFromList(0), "Active");
        documentManagementPage.returnBackButton();
        documentManagementPage.documentManagementSearchBar(documentName);
        documentManagementPage.searchFormNameInTableAndClick(documentName);
        documentManagementPage.addNewVersionButton();
        Assert.assertEquals(documentManagementPage.newDocumentRequestTitle1("New Version"), "New Version");
        documentManagementPage.fillEformDocumentData("Document Identification", documentIdentification, null);
        documentManagementPage.fillEformDocumentData("Template ID", null, template_Id);
        documentManagementPage.newDocumentClickOnButton("Continue");
        Assert.assertEquals(documentManagementPage.newDocumentRequestTitle2("Document Information"), "Document Information");
        Assert.assertEquals(documentManagementPage.checkEformDocumentDetails("Document Name"), documentName);
        Assert.assertEquals(documentManagementPage.checkEformDocumentDetails("Document Identification"), documentIdentification);
        Assert.assertEquals(documentManagementPage.checkEformDocumentDetails("Document Type"), "E-Form");
        documentManagementPage.newDocumentClickOnButton("Continue");
        Assert.assertEquals(documentManagementPage.newVersionTitle(), documentName);
        Assert.assertEquals(documentManagementPage.versionNumberList(),"2");
        Assert.assertEquals(documentManagementPage.getStatusFromList(0), "Draft");
        documentManagementPage.selectTodayAsActivationDate();
        Assert.assertEquals(documentManagementPage.getStatusFromList(0), "Active");
        documentManagementPage.returnBackButton();
        documentManagementPage.searchFormNameInTable(documentName);
        Assert.assertTrue(documentManagementPage.searchFormDocumentTypeInTable("E-form"));
    }


    @Test(description = "Add New Informative Document")
    @Severity(SeverityLevel.CRITICAL)
    public void t02_addNewInformativeDoc() {
        String documentName = "Informative" + randomString();
        String documentIdentification = "Identification " + randomString();
        documentManagementPage.addNewDocumentButton();
        documentManagementPage.chooseNewDocumentTypeFromList("New Informative Document (Send Only)");
        Assert.assertEquals(documentManagementPage.newDocumentRequestTitle1("New Informative Document"), "New Informative Document");
        documentManagementPage.fillInformativeDocumentData("Document Name", documentName);
        documentManagementPage.fillInformativeDocumentData("Document Identification", documentIdentification);
        documentManagementPage.uploadFile("C:/tmp/sample.pdf");
        documentManagementPage.newDocumentClickOnButton("Continue");
        documentManagementPage.documentManagementSearchBar(documentName);
        documentManagementPage.searchFormNameInTableAndClick(documentName);
        Assert.assertEquals(documentManagementPage.newVersionTitle(), documentName);
        Assert.assertEquals(documentManagementPage.getStatusFromList(0), "Draft");
        documentManagementPage.selectTodayAsActivationDate();
        Assert.assertEquals(documentManagementPage.getStatusFromList(0), "Active");
        documentManagementPage.returnBackButton();
        documentManagementPage.documentManagementSearchBar(documentName);
        documentManagementPage.searchFormNameInTableAndClick(documentName);
        documentManagementPage.addNewVersionButton();
        Assert.assertEquals(documentManagementPage.newDocumentRequestTitle1("New Version"), "New Version");
        documentManagementPage.fillInformativeDocumentData("Document Identification", documentIdentification);
        documentManagementPage.uploadFile("C:/tmp/sample.pdf");
        documentManagementPage.newDocumentClickOnButton("Continue");
        Assert.assertEquals(documentManagementPage.newVersionTitle(), documentName);
        Assert.assertEquals(documentManagementPage.versionNumberList(),"2");
        Assert.assertEquals(documentManagementPage.getStatusFromList(0), "Draft");
        documentManagementPage.selectTodayAsActivationDate();
        Assert.assertEquals(documentManagementPage.getStatusFromList(0), "Active");
        documentManagementPage.returnBackButton();
        documentManagementPage.searchFormNameInTable(documentName);
        Assert.assertTrue(documentManagementPage.searchFormDocumentTypeInTable("Informative document"));
    }

    @Test(description = "Upload New Document Request")
    @Severity(SeverityLevel.CRITICAL)
    public void t03_uploadNewDocumentRequest() {
        String documentName = "Upload" + randomString();
        String documentIdentification = "Identification " + randomString();
        documentManagementPage.addNewDocumentButton();
        documentManagementPage.chooseNewDocumentTypeFromList("New Document Request (Upload Only)");
        Assert.assertEquals(documentManagementPage.newDocumentRequestTitle1("New Document Request"), "New Document Request");
        documentManagementPage.fillUploadDocumentData("Document Name", documentName, null);
        documentManagementPage.fillUploadDocumentData("Document Identification", documentIdentification, null);
        documentManagementPage.fillUploadDocumentData("Allowed Formats", documentIdentification, "PDF");
        documentManagementPage.fillUploadDocumentData("Maximum Size Allowed", "5", null);
        documentManagementPage.uploadFile("C:/tmp/sample.pdf");
        documentManagementPage.newDocumentClickOnButton("Continue");
        documentManagementPage.documentManagementSearchBar(documentName);
        documentManagementPage.searchFormNameInTableAndClick(documentName);
        Assert.assertEquals(documentManagementPage.newVersionTitle(), documentName);
        Assert.assertEquals(documentManagementPage.getStatusFromList(0), "Draft");
        documentManagementPage.selectTodayAsActivationDate();
        Assert.assertEquals(documentManagementPage.getStatusFromList(0), "Active");
        documentManagementPage.returnBackButton();
        documentManagementPage.documentManagementSearchBar(documentName);
//        Assert.assertTrue(documentManagementPage.searchFormNameInTable(documentName));
        documentManagementPage.searchFormNameInTableAndClick(documentName);
        documentManagementPage.addNewVersionButton();
        Assert.assertEquals(documentManagementPage.newDocumentRequestTitle1("New Version"), "New Version");
        documentManagementPage.fillUploadDocumentData("Document Identification", documentIdentification, null);
        documentManagementPage.fillUploadDocumentData("Allowed Formats", documentIdentification, "PDF");
        documentManagementPage.fillUploadDocumentData("Maximum Size Allowed", "5", null);
        documentManagementPage.uploadFile("C:/tmp/sample.pdf");
        documentManagementPage.newDocumentClickOnButton("Continue");
        Assert.assertEquals(documentManagementPage.newVersionTitle(), documentName);
        Assert.assertEquals(documentManagementPage.versionNumberList(),"2");
        Assert.assertEquals(documentManagementPage.getStatusFromList(0), "Draft");
        documentManagementPage.selectTodayAsActivationDate();
        Assert.assertEquals(documentManagementPage.getStatusFromList(0), "Active");
        documentManagementPage.returnBackButton();
        documentManagementPage.searchFormNameInTable(documentName);
        Assert.assertTrue(documentManagementPage.searchFormDocumentTypeInTable("Document request"));
    }




}
