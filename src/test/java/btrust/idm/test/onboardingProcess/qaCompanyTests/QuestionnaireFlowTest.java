package btrust.idm.test.onboardingProcess.qaCompanyTests;

import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class QuestionnaireFlowTest extends BaseIdmClientTest {

    @BeforeMethod
    @Step("Open mobile view")
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", qaApiKey, "322");
        driver.navigate().to(link);
    }

    @AfterMethod
    @Step("Sleep 2 sec")
    public void sleep() throws InterruptedException {
        Thread.sleep(2000);
    }

//    @AfterMethod
//    @Step("Verify the questionnaire results")
//    public void verifyQuestionnaireResults() throws InterruptedException {
//        searchCase("https://btrustqa.scanovate.com/", "Scanovate QA", "qa@scanovate.com", "Scan2018", "Cases (Individuals)", qaApiKey);
//        SearchPage searchPage = new SearchPage(driver);
//        Assert.assertTrue(searchPage.questionnaireResults("1. מה השם שלך?", "ליעד"));
//        Assert.assertTrue(searchPage.questionnaireResults("2. מה אתה אוהב לאכול?", "טופו"));
//        Assert.assertTrue(searchPage.questionnaireResults("3. העלה תמונה", ".jpg"));
//        Assert.assertTrue(searchPage.questionnaireResults("4. מתי אתה קם בבוקר?", "6"));
//        Assert.assertTrue(searchPage.questionnaireResults("5. תבחר אופציה מהרשימה", "אופציה שניה"));
//        Assert.assertTrue(searchPage.questionnaireResults("6. ספר עליך בקצרה", "Test"));
//        Assert.assertTrue(searchPage.questionnaireResults("7. מה האימייל שלך?", "liad@qa.com"));
//        Assert.assertTrue(searchPage.questionnaireResults("8. מה המספר שלך?", "123"));
//        Assert.assertTrue(searchPage.questionnaireResults("8. אנא שתף לינק לתנאים", ""));
//        driver.quit();
//    }

//    @Test(description = "Positive flow")
//    @Description("Perform positive flow")
//    public void t01_positiveFlow() {
//        TextTypePage textTypePage = new TextTypePage(driver);
//        Assert.assertTrue(textTypePage.questionTitleIsDisplayed("מה השם שלך?"));
//        textTypePage.fillAnswer("ליעד");
//        textTypePage.clickOnButton("Next");
//
//        CheckboxTypePage checkboxTypePage = new CheckboxTypePage(driver);
//        Assert.assertTrue(checkboxTypePage.questionTitleIsDisplayed("מה אתה אוהב לאכול?"));
//        checkboxTypePage.chooseFromCheckbox("המבורגר");
//        checkboxTypePage.chooseFromCheckbox("טופו");
//        Assert.assertTrue(checkboxTypePage.nextButton());
//
//        FileTypePage fileTypePage = new FileTypePage(driver);
//        Assert.assertTrue(fileTypePage.questionTitleIsDisplayed("העלה תמונה"));
//        fileTypePage.uploadFile("./ocr/mrz/liad/1.jpg");
//        Assert.assertTrue(fileTypePage.nextButton());
//
//        RadioButtonTypePage radioButtonTypePage = new RadioButtonTypePage(driver);
//        Assert.assertTrue(radioButtonTypePage.questionTitleIsDisplayed("מתי אתה קם בבוקר?"));
//        radioButtonTypePage.chooseFromCheckbox("6");
//        Assert.assertTrue(radioButtonTypePage.nextButton());
//
//        SelectTypePage selectTypePage = new SelectTypePage(driver);
//        Assert.assertTrue(selectTypePage.questionTitleIsDisplayed("תבחר אופציה מהרשימה"));
//        selectTypePage.chooseOptionsFromList("אופציה ראשונה");
//        Assert.assertTrue(selectTypePage.nextButton());
//
//        TextAreaTypePage textAreaTypePage = new TextAreaTypePage(driver);
//        Assert.assertTrue(textAreaTypePage.questionTitleIsDisplayed("ספר עליך בקצרה"));
//        textAreaTypePage.fillTextArea("Test");
//        Assert.assertTrue(textAreaTypePage.nextButton());
//
//        EmailTypePage emailTypePage = new EmailTypePage(driver);
//        Assert.assertTrue(emailTypePage.questionTitleIsDisplayed("מה האימייל שלך?"));
//        emailTypePage.emailField("liad@qa.com");
//        Assert.assertTrue(emailTypePage.nextButton());
//
//        NumberTypePage numberTypePage = new NumberTypePage(driver);
//        Assert.assertTrue(numberTypePage.questionTitleIsDisplayed("מה המספר שלך?"));
//        numberTypePage.numberField("123");
//        Assert.assertTrue(numberTypePage.nextButton());
//
//        PrivacyLinkTypePage privacyLinkTypePage = new PrivacyLinkTypePage(driver);
//        Assert.assertTrue(privacyLinkTypePage.questionTitleIsDisplayed("אנא שתף לינק לתנאים"));
//        Assert.assertTrue(privacyLinkTypePage.linkIsDisplayed("Link to terms"));
//        Assert.assertTrue(privacyLinkTypePage.confirmMessageIsDisplayed("אני מסכים לתנאים שהוצגו בלינק"));
//        Assert.assertTrue(privacyLinkTypePage.confirmButtonIsDisplayed());
//        Assert.assertTrue(privacyLinkTypePage.clickOnConfirmButton());
//        Assert.assertTrue(privacyLinkTypePage.nextButton());
//        FaceCompareResultsPage faceCompareResultsPage = new FaceCompareResultsPage(driver);
//        Assert.assertEquals(faceCompareResultsPage.processEndedText(), "process-ended");
//    }
//
//    @Test(description = "Negative legitimacy test")
//    @Description("Perform negative legitimacy test")
//    public void t02_negativeFlowLegitimacy() {
//        TextTypePage textTypePage = new TextTypePage(driver);
//        Assert.assertTrue(textTypePage.questionTitleIsDisplayed("מה השם שלך?"));
//        textTypePage.fillAnswer("שם_עם_מעל_לעשר_תווים");
//        textTypePage.clickOnButton("Next");
//
//        Assert.assertEquals(textTypePage.errorMessageIsDisplayed(), "More than 10 characters is not allowed");
//        textTypePage.fillAnswer("א");
//        textTypePage.clickOnButton("Next");
//
//        Assert.assertEquals(textTypePage.errorMessageIsDisplayed(), "Less than 2 characters is not allowed");
//        textTypePage.fillAnswer("ליעד");
//        textTypePage.clickOnButton("Next");
//
//        CheckboxTypePage checkboxTypePage = new CheckboxTypePage(driver);
//        Assert.assertTrue(checkboxTypePage.questionTitleIsDisplayed("מה אתה אוהב לאכול?"));
//        checkboxTypePage.chooseFromCheckbox("המבורגר");
//        checkboxTypePage.chooseFromCheckbox("טופו");
//        checkboxTypePage.chooseFromCheckbox("פיצה");
//        Assert.assertTrue(checkboxTypePage.nextButton());
//
//        FileTypePage fileTypePage = new FileTypePage(driver);
//        Assert.assertTrue(fileTypePage.questionTitleIsDisplayed("העלה תמונה"));
//        fileTypePage.uploadFile("./filesToBrokersTest/2019 Annual Client Letter.pdf");
//        Assert.assertTrue(fileTypePage.nextButton());
//        Assert.assertTrue(fileTypePage.errorMessageIsDisplayed("Please provide allowed types: jpg, png"));
//        fileTypePage.uploadFile("./ocr/mrz/liad/1.jpg");
//        Assert.assertTrue(fileTypePage.nextButton());
//
//        RadioButtonTypePage radioButtonTypePage = new RadioButtonTypePage(driver);
//        Assert.assertTrue(radioButtonTypePage.questionTitleIsDisplayed("מתי אתה קם בבוקר?"));
//        radioButtonTypePage.chooseFromCheckbox("6");
//        Assert.assertTrue(radioButtonTypePage.nextButton());
//
//        SelectTypePage selectTypePage = new SelectTypePage(driver);
//        Assert.assertTrue(selectTypePage.questionTitleIsDisplayed("תבחר אופציה מהרשימה"));
//        selectTypePage.chooseOptionsFromList("אופציה ראשונה");
//        Assert.assertTrue(selectTypePage.nextButton());
//
//        TextAreaTypePage textAreaTypePage = new TextAreaTypePage(driver);
//        Assert.assertTrue(textAreaTypePage.questionTitleIsDisplayed("ספר עליך בקצרה"));
//        textAreaTypePage.fillTextArea("Test");
//        Assert.assertTrue(textAreaTypePage.nextButton());
//
//        EmailTypePage emailTypePage = new EmailTypePage(driver);
//        Assert.assertTrue(emailTypePage.questionTitleIsDisplayed("מה האימייל שלך?"));
//        emailTypePage.emailField("li");
//        Assert.assertTrue(emailTypePage.nextButton());
//        Assert.assertTrue(emailTypePage.errorMessageIsDisplayed("Please enter a valid E-mail address"));
//        emailTypePage.emailField("liad@qa.com");
//        Assert.assertTrue(emailTypePage.nextButton());
//
//        NumberTypePage numberTypePage = new NumberTypePage(driver);
//        Assert.assertTrue(numberTypePage.questionTitleIsDisplayed("מה המספר שלך?"));
//        numberTypePage.numberField("123");
//        Assert.assertTrue(numberTypePage.nextButton());
//
//        PrivacyLinkTypePage privacyLinkTypePage = new PrivacyLinkTypePage(driver);
//        Assert.assertTrue(privacyLinkTypePage.questionTitleIsDisplayed("אנא שתף לינק לתנאים"));
//        Assert.assertTrue(privacyLinkTypePage.linkIsDisplayed("Link to terms"));
//        Assert.assertTrue(privacyLinkTypePage.confirmMessageIsDisplayed("אני מסכים לתנאים שהוצגו בלינק"));
//        Assert.assertTrue(privacyLinkTypePage.confirmButtonIsDisplayed());
//        Assert.assertTrue(privacyLinkTypePage.nextButton());
//        Assert.assertTrue(privacyLinkTypePage.errorMessageIsDisplayed("Please approved The terms and the services"));
//        Assert.assertTrue(privacyLinkTypePage.clickOnConfirmButton());
//        Assert.assertTrue(privacyLinkTypePage.nextButton());
//    }
//
//    @Test(description = "Negative Required fields test")
//    @Description("Perform negative required fields test")
//    public void t03_negativeFlowRequiredFields() {
//        TextTypePage textTypePage = new TextTypePage(driver);
//        Assert.assertTrue(textTypePage.questionTitleIsDisplayed("מה השם שלך?"));
//        textTypePage.fillAnswer("");
//        textTypePage.clickOnButton("Next");
//        Assert.assertTrue(textTypePage.errorMessageIsDisplayed().equalsIgnoreCase("Answer cannot be empty") || textTypePage.errorMessageIsDisplayed().equalsIgnoreCase("More than 10 characters is not allowed"));
//        textTypePage.fillAnswer("ליעד");
//        textTypePage.clickOnButton("Next");
//
//        CheckboxTypePage checkboxTypePage = new CheckboxTypePage(driver);
//        Assert.assertTrue(checkboxTypePage.questionTitleIsDisplayed("מה אתה אוהב לאכול?"));
//        Assert.assertTrue(checkboxTypePage.nextButton());
//        Assert.assertTrue(checkboxTypePage.errorMessageIsDisplayed("Answer cannot be empty"));
//        checkboxTypePage.chooseFromCheckbox("טופו");
//        Assert.assertTrue(checkboxTypePage.nextButton());
//
//        FileTypePage fileTypePage = new FileTypePage(driver);
//        Assert.assertTrue(fileTypePage.questionTitleIsDisplayed("העלה תמונה"));
//        Assert.assertTrue(fileTypePage.nextButton());
//        Assert.assertTrue(fileTypePage.errorMessageIsDisplayed("Answer cannot be empty"));
//        fileTypePage.uploadFile("./ocr/mrz/liad/1.jpg");
//        Assert.assertTrue(fileTypePage.nextButton());
//
//        RadioButtonTypePage radioButtonTypePage = new RadioButtonTypePage(driver);
//        Assert.assertTrue(radioButtonTypePage.questionTitleIsDisplayed("מתי אתה קם בבוקר?"));
//        Assert.assertTrue(radioButtonTypePage.nextButton());
//        radioButtonTypePage.chooseFromCheckbox("6");
//        Assert.assertTrue(radioButtonTypePage.errorMessageIsDisplayed("Answer cannot be empty"));
//        Assert.assertTrue(radioButtonTypePage.nextButton());
//
//        SelectTypePage selectTypePage = new SelectTypePage(driver);
//        Assert.assertTrue(selectTypePage.questionTitleIsDisplayed("תבחר אופציה מהרשימה"));
//        Assert.assertTrue(selectTypePage.nextButton());
//        Assert.assertTrue(selectTypePage.errorMessageIsDisplayed("Answer cannot be empty"));
//        selectTypePage.chooseOptionsFromList("אופציה ראשונה");
//        Assert.assertTrue(selectTypePage.nextButton());
//
//        TextAreaTypePage textAreaTypePage = new TextAreaTypePage(driver);
//        Assert.assertTrue(textAreaTypePage.questionTitleIsDisplayed("ספר עליך בקצרה"));
//        Assert.assertTrue(textAreaTypePage.nextButton());
//        Assert.assertTrue(textAreaTypePage.errorMessageIsDisplayed("Answer cannot be empty"));
//        textAreaTypePage.fillTextArea("Test");
//        Assert.assertTrue(textAreaTypePage.nextButton());
//
//        EmailTypePage emailTypePage = new EmailTypePage(driver);
//        Assert.assertTrue(emailTypePage.questionTitleIsDisplayed("מה האימייל שלך?"));
//        Assert.assertTrue(emailTypePage.nextButton());
//        Assert.assertTrue(emailTypePage.errorMessageIsDisplayed("Answer cannot be empty"));
//        emailTypePage.emailField("liad@qa.com");
//        Assert.assertTrue(emailTypePage.nextButton());
//
//        NumberTypePage numberTypePage = new NumberTypePage(driver);
//        Assert.assertTrue(numberTypePage.questionTitleIsDisplayed("מה המספר שלך?"));
//        Assert.assertTrue(numberTypePage.nextButton());
//        Assert.assertTrue(numberTypePage.errorMessageIsDisplayed("Answer cannot be empty"));
//        numberTypePage.numberField("123");
//        Assert.assertTrue(numberTypePage.nextButton());
//
//        PrivacyLinkTypePage privacyLinkTypePage = new PrivacyLinkTypePage(driver);
//        Assert.assertTrue(privacyLinkTypePage.questionTitleIsDisplayed("אנא שתף לינק לתנאים"));
//        Assert.assertTrue(privacyLinkTypePage.linkIsDisplayed("Link to terms"));
//        Assert.assertTrue(privacyLinkTypePage.confirmMessageIsDisplayed("אני מסכים לתנאים שהוצגו בלינק"));
//        Assert.assertTrue(privacyLinkTypePage.confirmButtonIsDisplayed());
//        Assert.assertTrue(privacyLinkTypePage.nextButton());
//        Assert.assertTrue(privacyLinkTypePage.errorMessageIsDisplayed("Please approved The terms and the services"));
//        Assert.assertTrue(privacyLinkTypePage.clickOnConfirmButton());
//        Assert.assertTrue(privacyLinkTypePage.nextButton());
//    }
//
//    @Test(description = "Negative previous button test")
//    @Description("Perform negative test by pressing on the previous button and verify successful")
//    public void t04_negativeFlowPreviousCheck() {
//        TextTypePage textTypePage = new TextTypePage(driver);
//        Assert.assertTrue(textTypePage.questionTitleIsDisplayed("מה השם שלך?"));
//        textTypePage.fillAnswer("ליעד");
//        textTypePage.clickOnButton("Next");
//
//        CheckboxTypePage checkboxTypePage = new CheckboxTypePage(driver);
//        Assert.assertTrue(checkboxTypePage.previousButton());
//        Assert.assertTrue(textTypePage.questionTitleIsDisplayed("מה השם שלך?"));
//        textTypePage.clickOnButton("Next");
//        Assert.assertTrue(checkboxTypePage.questionTitleIsDisplayed("מה אתה אוהב לאכול?"));
//        checkboxTypePage.chooseFromCheckbox("המבורגר");
//        checkboxTypePage.chooseFromCheckbox("טופו");
//        Assert.assertTrue(checkboxTypePage.nextButton());
//
//        FileTypePage fileTypePage = new FileTypePage(driver);
//        Assert.assertTrue(fileTypePage.previousButton());
//        Assert.assertTrue(checkboxTypePage.questionTitleIsDisplayed("מה אתה אוהב לאכול?"));
//        Assert.assertTrue(checkboxTypePage.nextButton());
//        Assert.assertTrue(fileTypePage.questionTitleIsDisplayed("העלה תמונה"));
//        fileTypePage.uploadFile("./ocr/mrz/liad/1.jpg");
//        Assert.assertTrue(fileTypePage.nextButton());
//
//        RadioButtonTypePage radioButtonTypePage = new RadioButtonTypePage(driver);
//        Assert.assertTrue(radioButtonTypePage.previousButton());
//        Assert.assertTrue(fileTypePage.questionTitleIsDisplayed("העלה תמונה"));
//        Assert.assertTrue(fileTypePage.nextButton());
//        Assert.assertTrue(radioButtonTypePage.questionTitleIsDisplayed("מתי אתה קם בבוקר?"));
//        radioButtonTypePage.chooseFromCheckbox("6");
//        Assert.assertTrue(radioButtonTypePage.nextButton());
//
//        SelectTypePage selectTypePage = new SelectTypePage(driver);
//        Assert.assertTrue(radioButtonTypePage.previousButton());
//        Assert.assertTrue(radioButtonTypePage.questionTitleIsDisplayed("מתי אתה קם בבוקר?"));
//        Assert.assertTrue(radioButtonTypePage.nextButton());
//        Assert.assertTrue(selectTypePage.questionTitleIsDisplayed("תבחר אופציה מהרשימה"));
//        selectTypePage.chooseOptionsFromList("אופציה ראשונה");
//        Assert.assertTrue(selectTypePage.nextButton());
//
//        TextAreaTypePage textAreaTypePage = new TextAreaTypePage(driver);
//        Assert.assertTrue(textAreaTypePage.previousButton());
//        Assert.assertTrue(selectTypePage.questionTitleIsDisplayed("תבחר אופציה מהרשימה"));
//        Assert.assertTrue(selectTypePage.nextButton());
//        Assert.assertTrue(textAreaTypePage.questionTitleIsDisplayed("ספר עליך בקצרה"));
//        textAreaTypePage.fillTextArea("Test");
//        Assert.assertTrue(textAreaTypePage.nextButton());
//
//        EmailTypePage emailTypePage = new EmailTypePage(driver);
//        Assert.assertTrue(emailTypePage.previousButton());
//        Assert.assertTrue(textAreaTypePage.questionTitleIsDisplayed("ספר עליך בקצרה"));
//        Assert.assertTrue(textAreaTypePage.nextButton());
//        Assert.assertTrue(emailTypePage.questionTitleIsDisplayed("מה האימייל שלך?"));
//        emailTypePage.emailField("liad@qa.com");
//        Assert.assertTrue(emailTypePage.nextButton());
//
//        NumberTypePage numberTypePage = new NumberTypePage(driver);
//        Assert.assertTrue(numberTypePage.previousButton());
//        Assert.assertTrue(emailTypePage.questionTitleIsDisplayed("מה האימייל שלך?"));
//        Assert.assertTrue(emailTypePage.nextButton());
//        Assert.assertTrue(numberTypePage.questionTitleIsDisplayed("מה המספר שלך?"));
//        numberTypePage.numberField("123");
//        Assert.assertTrue(numberTypePage.nextButton());
//
//        PrivacyLinkTypePage privacyLinkTypePage = new PrivacyLinkTypePage(driver);
//        Assert.assertTrue(privacyLinkTypePage.previousButton());
//        Assert.assertTrue(numberTypePage.questionTitleIsDisplayed("מה המספר שלך?"));
//        Assert.assertTrue(numberTypePage.nextButton());
//        Assert.assertTrue(privacyLinkTypePage.questionTitleIsDisplayed("אנא שתף לינק לתנאים"));
//        Assert.assertTrue(privacyLinkTypePage.linkIsDisplayed("Link to terms"));
//        Assert.assertTrue(privacyLinkTypePage.confirmMessageIsDisplayed("אני מסכים לתנאים שהוצגו בלינק"));
//        Assert.assertTrue(privacyLinkTypePage.confirmButtonIsDisplayed());
//        Assert.assertTrue(privacyLinkTypePage.clickOnConfirmButton());
//        Assert.assertTrue(privacyLinkTypePage.nextButton());
//    }
}