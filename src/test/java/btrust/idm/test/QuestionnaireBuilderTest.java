package btrust.idm.test;

import btrust.BaseIdmTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.idm.pageObject.QuestionnaireBuilderPage;
import btrust.idm.pageObject.SettingsPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class QuestionnaireBuilderTest extends BaseIdmTest {

    @BeforeMethod
    public void navigateToQuestionnaireBuilderPage() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.settingsButton();
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.chooseTab("Settings", "Developer Tools");
        settingsPage.chooseTab("Settings", "Questionnaire Builder");
        QuestionnaireBuilderPage questionnaireBuilderPage = new QuestionnaireBuilderPage(driver);
        Assert.assertTrue(questionnaireBuilderPage.questionnaireBuilderTitleIsDisplayed("Questionnaire Builder"));
    }

    @Test(description = "Create new questionnaire")
    @Description("Create new questionnaire, edit questionnaire and remove questionnaire")
    public void t01_createNewQuestionnaire() {
        // create
        QuestionnaireBuilderPage questionnaireBuilderPage = new QuestionnaireBuilderPage(driver);
        questionnaireBuilderPage.display5Questionnaires();
        questionnaireBuilderPage.addNewQuestionnaireButton();
        questionnaireBuilderPage.addFieldButton();
        questionnaireBuilderPage.chooseQuestions();
        questionnaireBuilderPage.fillNewQuestions("Unique ID", "Field Label Test");
        questionnaireBuilderPage.fillNewQuestions("Field Label", "Field Label Test");
        questionnaireBuilderPage.saveButton();
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.chooseTab("Settings", "Developer Tools");
        settingsPage.chooseTab("Settings", "Questionnaire Builder");

        Assert.assertEquals(questionnaireBuilderPage.questionnaireList(), 6);

        // edit
        questionnaireBuilderPage.editQuestionnaireButton();
        questionnaireBuilderPage.chooseQuestions();
        questionnaireBuilderPage.fillNewQuestions("Field Label", "Field Label edit");

        Assert.assertEquals(questionnaireBuilderPage.questionsLabel("Field Label edit"), "Field Label edit");
        questionnaireBuilderPage.saveButton();

        // remove
        questionnaireBuilderPage.removeQuestionnaireButton();
        settingsPage.chooseTab("Settings", "Developer Tools");
        settingsPage.chooseTab("Settings", "Questionnaire Builder");
        Assert.assertTrue(questionnaireBuilderPage.questionnaireBuilderTitleIsDisplayed("Questionnaire Builder"));
        Assert.assertEquals(questionnaireBuilderPage.questionnaireList(), 5);
    }
}