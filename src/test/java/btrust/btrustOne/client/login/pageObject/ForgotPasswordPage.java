package btrust.btrustOne.client.login.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage extends BasePage {
    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".forgot-right > .go-back")
    protected WebElement previousToResetPasswordPage;
    @FindBy(css = "[name='email']")
    protected WebElement emailField;
    @FindBy(css = ".forgot-submit-container > :nth-child(1)")
    protected WebElement recoverPasswordButton;
    @FindBy(css = ".forgot-submit-container > :nth-child(2)")
    protected WebElement cancelButton;
    @FindBy(css = ".forgot-title > span")
    protected WebElement recoverYourPasswordTitle;
    @FindBy(css = ".forgot-input-container > .error-message")
    protected WebElement errorMessageEmailField;
    @FindBy(css = ".check-inbox-container > .inbox-title")
    protected WebElement checkYourInboxTitle;
    @FindBy(css = ".check-inbox-container > .inbox-text > span")
    protected WebElement checkYourInboxDescription;


    @Step("Check that recoverYourPasswordTitle appear")
    public String recoverYourPasswordTitle(String text) {
        waitForTextToBeInElement(recoverYourPasswordTitle, text);
        return getText(recoverYourPasswordTitle);
    }

    @Step("Type email :{email} in email field")
    public void emailField(String email) {
        waitForElementToBeVisibility(emailField);
        fillText(emailField, email);
        waitForElementToBeClickable(recoverPasswordButton);
        click(recoverPasswordButton);
    }

    @Step("Click on recoverPasswordButton")
    public void recoverPasswordButton() {
        waitForElementToBeClickable(recoverPasswordButton);
        click(recoverPasswordButton);
    }

    @Step("Check that errorMessageEmailField appear")
    public String errorMessageEmailField(String text) {
        waitForTextToBeInElement(errorMessageEmailField, text);
        return getText(errorMessageEmailField);
    }

    @Step("Check that checkYourInboxTitle appear")
    public String checkYourInboxTitle(String text) {
        waitForTextToBeInElement(checkYourInboxTitle, text);
        return getText(checkYourInboxTitle);
    }

    @Step("Check that errorMessageEmailField appear")
    public String checkYourInboxDescription(String text) {
        waitForTextToBeInElement(checkYourInboxDescription, text);
        return getText(checkYourInboxDescription);
    }

    @Step("Click on previousToResetPasswordPage")
    public void previousToResetPasswordPage() {
        waitForElementToBeClickable(previousToResetPasswordPage);
        click(previousToResetPasswordPage);
    }

    @Step("Click on cancelButton")
    public void cancelButton() {
        waitForElementToBeClickable(cancelButton);
        click(cancelButton);
    }
}