package btrust.idm.pageObject.questionnaireForm;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmAndSubmitPage extends BasePage {
    public ConfirmAndSubmitPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".Confirmation_title__22fdh")
    protected WebElement confirmationTitle; // confirmation and submission header
    @FindBy(css = ".Confirmation_data__26Wi5 > [type=button]")
    protected WebElement confirmAndSubmitButton; // confirmation and submission header
    @FindBy(css = ".Confirmation_summitedSuccessfully__33PeZ > button[type='button']")
    protected WebElement backToDocumentListButton; // confirmation and submission header
    @FindBy(css = ".Alert_container__1o01c > :nth-child(2)")
    protected WebElement errorMessage;


    @Step("Click on confirm And Submit Button")
    public void confirmAndSubmitButton() {
        waitForElementToBeClickable(confirmAndSubmitButton);
        click(confirmAndSubmitButton);
    }

    @Step("Check that confirmationTitle appear")
    public boolean confirmationTitle(String text) {
        try {
            waitForTextToBeInElement(confirmationTitle, text);
            getText(confirmationTitle).equalsIgnoreCase(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Check that errorMessage appear")
    public String errorMessage(String text) {
        waitForTextToBeInElement(errorMessage, text);
        return getText(errorMessage);
    }

    @Step("back To Document List Button")
    public void backToDocumentListButton() {
        waitForElementToBeClickable(backToDocumentListButton);
        click(backToDocumentListButton);
    }
}
