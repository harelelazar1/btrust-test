package btrust.btrustOne.admin.generalAdmin.emailTemplate.tests.tests;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.dataModule.dataMapper.pageObject.DataMapperPage;
import btrust.btrustOne.admin.dataModule.dataMapper.pageObject.FieldPage;
import btrust.btrustOne.admin.dataModule.dataMapper.pageObject.PopupPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.EntitiesManagerPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.BusinessCategoryPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.DataProfilePage;
import btrust.btrustOne.admin.generalAdmin.emailTemplate.tests.pageObject.AddNewTemplatePage;
import btrust.btrustOne.admin.generalAdmin.emailTemplate.tests.pageObject.EmailTemplatePage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EmailTemplateTest extends BaseAdminUserTest {


    AdministratorPage administratorPage;
    EntitiesManagerPage entitiesManagerPage;
    DataMapperPage dataMapperPage;

    BusinessCategoryPage businessCategoryPage;
    EmailTemplatePage emailTemplatePage;
    AddNewTemplatePage addNewTemplatePage;


    @BeforeClass
    public void navigateToEmailTemplatePage() {
        administratorPage = new AdministratorPage(driver);
        entitiesManagerPage = new EntitiesManagerPage(driver);
        administratorPage.chooseFromSideBar("Email Template");
        businessCategoryPage = new BusinessCategoryPage(driver);
        emailTemplatePage = new EmailTemplatePage(driver);
        addNewTemplatePage = new AddNewTemplatePage(driver);
        Assert.assertEquals(emailTemplatePage.emailTemplatePageTitle(), "Email Templates");

    }


    @Test(description = "Create new email template")
    @Description("Create new email template")
    public void createNewEmailTemplate() throws InterruptedException {
        emailTemplatePage.clickOnAddButton();
        Assert.assertTrue(addNewTemplatePage.addContentToTheEmailBody("Content","IMAGE"));


    }
}