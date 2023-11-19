package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IdentityPage extends BasePage {

    public IdentityPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".sidebar__wrapper button")
    protected WebElement updateBiometricDBButton;
    @FindBy(css = "div > div > div > div > div.text")
    protected WebElement popupResetDB;
    @FindBy(css = "table > .table-body")
    protected WebElement identityTable;


    @Step("Click on updateBiometricDBButton")
    public void updateBiometricDBButton() {
        waitForElementToBeClickable(updateBiometricDBButton);
        click(updateBiometricDBButton);
        waitForElementToBeVisibility(popupResetDB);
        isDisplayed(popupResetDB);
        sleep(20000);
    }

    @Step("Check if updateBiometricDBButton is displayed")
    public boolean updateBiometricDBButtonIsDisplay() {
        try {
            isDisplayed(updateBiometricDBButton);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Check if identityTable is displayed")
    public boolean identityTableIsDisplay() {
        waitForPageFinishLoading();
        sleep(5000);
        try {
            waitForElementToBeVisibility(identityTable);
            isDisplayed(identityTable);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}