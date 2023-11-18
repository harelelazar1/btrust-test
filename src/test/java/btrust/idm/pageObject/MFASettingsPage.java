package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MFASettingsPage extends BasePage {
    public MFASettingsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".authentication > .title > span")
    protected WebElement MFASettingsTitle;
    @FindBy(css = ".form .MuiButtonBase-root")
    protected WebElement enableMFACheckbox;
    @FindBy(css = ".form > .select")
    protected WebElement select;
    @FindBy(css = ".caseSelect__menu-list > .caseSelect__option")
    protected List<WebElement> mfaTypeList;
    @FindBy(css = ".items > button")
    protected WebElement saveButton;


    @Step("Check if MFASettingsTitleIsDisplayed")
    public boolean MFASettingsTitleIsDisplayed(String title) {
        try {
            waitForTextToBeInElement(MFASettingsTitle, title);
            isDisplayed(MFASettingsTitle);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Step("Clock on enableMFACheckbox")
    public void enableMFACheckbox() {
        waitForElementToBeClickable(enableMFACheckbox);
        sleep(2000);
        click(enableMFACheckbox);
    }

    @Step("Choose MFA Authentication")
    public void chooseMFAAuthentication(String chooseMfaType) {
        waitForElementToBeClickable(select);
        click(select);
        for (WebElement el : mfaTypeList) {
            if (getText(el).equalsIgnoreCase(chooseMfaType)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Click on saveButton")
    public void saveButton() {
        waitForElementToBeClickable(saveButton);
        sleep(2000);
        click(saveButton);
    }
}