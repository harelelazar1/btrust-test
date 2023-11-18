package btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EmailAndFormsPage extends BasePage {
    public EmailAndFormsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.EmailTemplateForm-module__title__Nk8En")
    protected WebElement emailAndFormsTitle;
    @FindBy(css = ".EmailTemplateForm-module__subject__bMJMl>input")
    protected WebElement subjectField;
    @FindBy(css = ".NewCaseEmailsAndForms-module__form__1pXvP >:nth-child(2) >textarea")
    protected WebElement bodyField;
    @FindBy(css = ".NewCaseEmailsAndForms-module__form__1pXvP >:nth-child(4) >textarea")
    protected WebElement defaultSignatureField;
    @FindBy(css = ".BottomStickyActions-module__wrapper__1b1UJ > button")
    protected List<WebElement> buttonsList;
    @FindBy(css = ".PopupBottomButtons-module__container__20krY [type=button]")
    protected List<WebElement> casesCreationButtonsList;


    @Step("Return the text of email & forms title")
    public String emailAndFormsTitle() {
        waitForElementToBeVisibility(emailAndFormsTitle);
        return getText(emailAndFormsTitle);
    }

    @Step("Fill the email & forms fields")
    public void fillEmailAndFormsFields(String subject, String body, String signature) {
        waitForElementToBeClickable(subjectField);
        fillText(subjectField, subject);
        waitForElementToBeClickable(bodyField);
        fillText(bodyField, body);
        scrollToElement(defaultSignatureField);
        waitForElementToBeClickable(defaultSignatureField);
        fillText(defaultSignatureField, signature);
    }

    @Step("Cases Creation - Click on button : {button}")
    public void chooseButtonCasesCreation(String button) {
        waitForPageFinishLoading();
        for (WebElement el : casesCreationButtonsList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(button)) {
                waitForElementToBeClickable(el);
                sleep(2000);
                click(el);
                break;
            }
        }
    }

    @Step("Click on button: {button}")
    public void chooseButton(String button) {
        waitForList(buttonsList);
        for (WebElement el : buttonsList) {
            if (getText(el).equalsIgnoreCase(button)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Check if button is enabled")
    public boolean buttonIsEnabled(String button) {
        boolean buttonIsEnabled = true;
        waitForList(buttonsList);
        for (WebElement el : buttonsList) {
            if (getText(el).contains(button)) {
                buttonIsEnabled = isEnabled(el);
                break;
            }
        }
        return buttonIsEnabled;
    }
}