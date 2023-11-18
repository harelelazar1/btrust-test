package btrust.onboardingProcess.ui.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CompatibilityPage extends BasePage {
    public CompatibilityPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".BrowserCheck_title__1vABo")
    protected WebElement popupTitle;
    @FindBy(css = ".BrowserCheck_row__ROgGQ > span")
    protected WebElement popupDescription;
    @FindBy(css = ".BrowserCheck_row__ROgGQ > :nth-child(2) > img")
    protected List<WebElement> browserIconsList;
    @FindBy(css = ".BrowserCheck_copyLinkBtn__1E7l4")
    protected WebElement copyLinkButton;


    @Step("Return the text of popup title")
    public String popupTitle() {
        waitForElementToBeVisibility(popupTitle);
        return getText(popupTitle);
    }

    @Step("Return the text of popup description")
    public String popupDescriptionIsDisplayed() {
        waitForElementToBeVisibility(popupDescription);
        return getText(popupDescription);
    }

    @Step("Check if each browser icon is appear on the screen")
    public boolean compatibilityBrowserIcons() {
        waitForList(browserIconsList);
        imageIsDisplayed(browserIconsList.get(0));
        imageIsDisplayed(browserIconsList.get(1));
        imageIsDisplayed(browserIconsList.get(2));
        return true;
    }

    @Step("Check if copyLinkButtonIsDisplayed")
    public boolean copyLinkButtonIsDisplayed() {
        waitForElementToBeVisibility(copyLinkButton);
        return isDisplayed(copyLinkButton);
    }
}