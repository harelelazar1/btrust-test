package btrust.btrustOne.client.login.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChangePasswordPage extends BasePage {
    public ChangePasswordPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div>.Login-module__title__xixpm span")
    protected WebElement changePasswordTitle;
    @FindBy(css = "[name='temporaryPassword']")
    protected WebElement temporaryPasswordField;
    @FindBy(css = "[name='newPassword']")
    protected WebElement newPasswordField;
    @FindBy(css = "[name='confirmPassword']")
    protected WebElement confirmPasswordField;
    @FindBy(css = ".Login-module__body__3d3Ev  button")
    protected WebElement signInButton;
    @FindBy(css = "div>.Login-module__red__3HgVX")
    protected WebElement errorMessage;

    @Step("Check if changePasswordTitle isDisplayed")
    public boolean changePasswordTitleIsDisplayed(String title) {
        try {
            waitForTextToBeInElement(changePasswordTitle, title);
            isDisplayed(changePasswordTitle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Click on signInButton")
    public void signInButton() {
        waitForElementToBeClickable(signInButton);
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].click();", signInButton);
    }

    @Step("Fill change password form")
    public void fillChangePasswordForm(String temporaryPassword, String newPassword, String confirmPassword) {
        waitForElementToBeClickable(temporaryPasswordField);
        fillText(temporaryPasswordField, temporaryPassword);
        waitForElementToBeClickable(newPasswordField);
        fillText(newPasswordField, newPassword);
        waitForElementToBeClickable(confirmPasswordField);
        fillText(confirmPasswordField, confirmPassword);
    }

    @Step("Check if errorMessageIsDisplayed")
    public boolean errorMessageIsDisplayed(String error) {
        try {
            waitForTextToBeInElement(errorMessage, error);
            isDisplayed(errorMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
