package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SettingsPage extends BasePage {

    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.SettingsMenu-module__title__2h6OG")
    protected WebElement settingsTitle;
    @FindBy(css = ".SettingsMenu-module__list__3f_cr .SettingsMenu-module__mainText__14RCT")
    protected List<WebElement> chooseTab;
    @FindBy(css = "#root")
    protected WebElement blockMessage;


    @Step("click on services marketplace button: {tab}")
    public void chooseTab(String settingTitle, String tab) {
        waitForTextToBeInElement(settingsTitle, settingTitle);
        for (WebElement el : chooseTab) {
            if (el.getText().equalsIgnoreCase(tab)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
    }

    @Step("check if blockMessage is present")
    public boolean blockMessage() {
        waitForPageFinishLoading();
        WebElement progressBar = blockMessage;
        boolean block = progressBar.isDisplayed();
        waitForElementToBeVisibility(blockMessage);
        return block;
    }
}