package btrust.btrustOne.admin.generalAdmin.languagesAndTranslations.tests;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.dataModule.dataMapper.pageObject.DataMapperPage;
import btrust.btrustOne.admin.dataModule.documentManagement.pageObject.DocumentManagementPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.EntitiesManagerPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.BusinessCategoryPage;
import btrust.btrustOne.admin.generalAdmin.languagesAndTranslations.pageObject.LanguagesPage;
import btrust.btrustOne.admin.generalAdmin.languagesAndTranslations.pageObject.TranslatePage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LanguagesAndTranslationsTest extends BaseAdminUserTest {

    AdministratorPage administratorPage;
    EntitiesManagerPage entitiesManagerPage;
    LanguagesPage languagesPage;
    TranslatePage translatePage;


    @BeforeMethod
    @Step("Enter to Languages And Translations screen")
    public void setLanguagesAndTranslationScreen() {
        administratorPage = new AdministratorPage(driver);
        entitiesManagerPage = new EntitiesManagerPage(driver);
        languagesPage = new LanguagesPage(driver);
        translatePage = new TranslatePage(driver);
        administratorPage = new AdministratorPage(driver);
        administratorPage.chooseFromSideBar("Languages & Translations");
        Assert.assertEquals(languagesPage.pageTitleName(), "Languages");
    }


    @Test(description = "Check the Languages options in the Languages page")
    @Description("Check the Languages options in the Languages page")
    public void checkLanguagesOptionsInTheScreen() {
        languagesPage.checkIfLanguageDisplay("English (US)");
        languagesPage.checkIfLanguageDisplay("Hebrew");
        languagesPage.checkIfLanguageDisplay("Italian");
    }


    @Test(description = "Check the option to add and remove new field")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Check the option to add and remove new field")
    public void checkAddAndRemoveFieldOption() {
        languagesPage.chooseLanguageFromList("English (US)");
        Assert.assertEquals(translatePage.pageTitleName(), "English (US)");
        translatePage.chooseModuleForTranslate("Mobile Interaction OCR - Philippines Cheque");
        translatePage.chooseServiceFromList("Instructions messages");
        translatePage.addNewTranslateForField("No cheque detected", "No cheque detected1");
        translatePage.clickOnSaveButton();
        translatePage.deleteNewTranslateField("No cheque detected");
        translatePage.clickOnPopeUpButtons("Remove field");
        translatePage.clickOnSaveButton();
        translatePage.clickOnBackButton();

    }


    @Test(description = "Try to add a new field without saving and move to another module")
    @Description("Try to add a new field without saving and move to another module")
    public void addNewFieldWithoutSave() {
        languagesPage.chooseLanguageFromList("English (US)");
        Assert.assertEquals(translatePage.pageTitleName(), "English (US)");
        translatePage.chooseModuleForTranslate("Mobile Interaction OCR - Philippines Cheque");
        translatePage.chooseServiceFromList("Instructions messages");
        translatePage.addNewTranslateForField("No cheque detected", "No cheque detected1");
        translatePage.chooseModuleForTranslate("Mobile Interaction - Liveness");
        translatePage.clickOnPopeUpButtons("Discard Changes");
        translatePage.clickOnBackButton();
    }


}