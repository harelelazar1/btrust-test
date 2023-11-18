package btrust.btrustOne.admin.dataModule.dataMapper.tests;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.dataModule.dataMapper.pageObject.DataMapperPage;
import btrust.btrustOne.admin.dataModule.dataMapper.pageObject.FieldPage;
import btrust.btrustOne.admin.dataModule.dataMapper.pageObject.PopupPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.EntitiesManagerPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.BusinessCategoryPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.DataProfilePage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DataMapperTest extends BaseAdminUserTest {

    AdministratorPage administratorPage;
    EntitiesManagerPage entitiesManagerPage;
    DataMapperPage dataMapperPage;
    FieldPage fieldPage;
    PopupPage popupPage;
    DataProfilePage dataProfilePage;

    BusinessCategoryPage businessCategoryPage;
    String DBFieldName = "automationTest" + randomString();
    String dbFieldType = "Short text";
    String fieldLabel = "automation test";


    @BeforeClass
    public void navigateToDataMapperPage() {
        administratorPage = new AdministratorPage(driver);
        entitiesManagerPage = new EntitiesManagerPage(driver);
        administratorPage.chooseFromSideBar("Data Mapper");
        dataMapperPage = new DataMapperPage(driver);
        businessCategoryPage = new BusinessCategoryPage(driver);
        Assert.assertEquals(dataMapperPage.dataMapperTitle(), "Data Mapper");
        Assert.assertEquals(dataMapperPage.dataMapperDescription(), "The Data Mapper module allows you to add, view and integrate data fields associated with the entities and their processes in the platform.");
        dataMapperPage = new DataMapperPage(driver);
        fieldPage = new FieldPage(driver);
        popupPage = new PopupPage(driver);
        dataProfilePage = new DataProfilePage(driver);
    }


    @Test(description = "Add new field from the Data Mapper page")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Add new field from the Data Mapper page")
    public void addNewFieldFromDataMapper() {
        dataMapperPage.clickOnAddButton();
        Assert.assertEquals(fieldPage.filedTitle(), "New Field");

        fieldPage.fillDataFiled("DB Field Name", DBFieldName, null);
        fieldPage.fillDataFiled("DB Field Type", null, dbFieldType);
        fieldPage.fillDataFiled("Field Label", fieldLabel, null);
        fieldPage.fillDataFiled("Entity Type", null, "Organization");
        fieldPage.fillDataFiled("Business Category", null, "liad");
        fieldPage.fillDataFiled("Description", "automation test description", null);
        fieldPage.addMoreInputSourceButton();
        fieldPage.chooseSourceData("Service Category", "Mobile Interaction Process");
        fieldPage.chooseSourceData("Vendor's Name", "Onboarding");
        fieldPage.chooseSourceData("Task", "OCR");
        fieldPage.chooseSourceData("Field", "First name");
        fieldPage.clickOnButton("Save");
        popupPage.clickOnButton("Yes - Save Changes");
        dataMapperPage.dataMapperSearchBar(DBFieldName);
        Assert.assertTrue(dataMapperPage.checkDbFieldNameDisplay("DB Field Name", DBFieldName));
        administratorPage.chooseFromSideBar("Entities Manager");

        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");

        entitiesManagerPage.chooseOrganizationBusinessCategoryList("liad");
        businessCategoryPage.contentsList("liad fields");
        businessCategoryPage.searchField(DBFieldName);
        Assert.assertTrue(dataProfilePage.fieldDisplayInDataFieldColumn("DATA FIELD", DBFieldName, null, null));
        Assert.assertTrue(dataProfilePage.fieldDisplayInDataFieldColumn("FIELD LABEL", null, null, fieldLabel));
        dataProfilePage.clickOnRemoveButton(DBFieldName);
        popupPage.clickOnButton("Confirm");
        businessCategoryPage.clickLinkBack();
        administratorPage.chooseFromSideBar("Data Mapper");
        dataMapperPage.dataMapperSearchBar(DBFieldName);
        Assert.assertFalse(dataMapperPage.checkDbFieldNameDisplay("DB Field Name", DBFieldName));

    }
}