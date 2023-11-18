package btrust.btrustOne.client;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NavigationPage extends BasePage {
    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div .styles-module__user_name__7aIqP")
    protected WebElement openAccountMenu;
    @FindBy(css = "body :nth-child(3).MuiPaper-root.MuiMenu-paper>ul")
    protected WebElement logOutButton;
    @FindBy(css = ".Navigation-module__menu-options__JhbRD>:nth-child(2)")
    protected WebElement notificationIcon;
    @FindBy(css = ".notifications-container > .header > span")
    protected WebElement notificationTitle;
    @FindBy(css = "div nav> :nth-child(2) li")
    protected List<WebElement> mainMenuList;
    @FindBy(css = ".Navigation-module__menu-options__JhbRD>:nth-child(3)")
    protected WebElement settingsButton;
    @FindBy(css = "[alt='back']")
    protected WebElement closeNotificationButton;
    @FindBy(css = "[role='tabpanel'] > img[alt='notification']")
    protected WebElement notificationButtonWithAlert;
    @FindBy(css = ".notifications-container")
    protected WebElement notificationContainerWindow;
    @FindBy(css = ".tasks .task-item")
    protected List<WebElement> documentsUpdateList;
    @FindBy(css = ".ui .Navigation-module__btrustUser__3NsB1")
    protected WebElement backToBtrustUserButton;
    @FindBy(css = ".SettingsMenu-module__container__16uEj.SettingsMenu-module__container__16uEj")
    protected WebElement settingButton;
    @FindBy(css = ".SettingsMenu-module__list__3f_cr>a .SettingsMenu-module__mainText__14RCT")
    protected List<WebElement> adminModule;


    @Step("Click on back to btrust user button")
    public void returnToAdminPage(String moduleName) {
        waitForPageFinishLoading();
        scrollToElement(settingButton);
        click(settingButton);
        for (WebElement el : adminModule) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(moduleName)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }


    @Step("click on logOutButton")
    public void logOut() {
        waitForElementToBeVisibility(openAccountMenu);
        waitForElementToBeClickable(openAccountMenu);
        scrollToElement(openAccountMenu);
        waitForElementToBeClickable(openAccountMenu);
        sleep(2000);
        click(openAccountMenu);
        scrollToElement(logOutButton);
        sleep(2000);
        click(logOutButton);
    }

    @Step("click on notification icon")
    public void notificationIcon() {
        waitForPageFinishLoading();
        scrollToElement(notificationIcon);
        click(notificationIcon);
    }

    @Step("check notification title appear")
    public String notificationTitle(String text) {
        waitForTextToBeInElement(notificationTitle, text);
        return getText(notificationTitle);
    }

    @Step("choose from itemList: {chooseFromItemList}")
    public void mainMenuList(String chooseFromItemList) {
        waitForPageFinishLoading();
        for (WebElement el : mainMenuList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(chooseFromItemList)) {
                click(el);
                break;
            }
        }
    }

    @Step("choose from itemList: {chooseFromItemList}")
    public boolean mainMenuListNotEqual(String chooseFromItemList) {
        waitForPageFinishLoading();
//        waitForElementToBeVisibility(settingsButton);
        waitForList(mainMenuList);
        for (WebElement el : mainMenuList) {
            if (getText(el).equalsIgnoreCase(chooseFromItemList)) {
                scrollToElement(el);
                waitForElementToBeClickable(el);
                return true;
            }
        }
        return false;
    }


    @Step("Click on settingsButton")
    public void settingsButton() {
        waitForElementToBeClickable(settingsButton);
        sleep(1000);
        click(settingsButton);
    }

    @Step("Click on closeNotificationButton")
    public void closeNotificationButton() {
        waitForElementToBeClickable(closeNotificationButton);
        click(closeNotificationButton);
    }

    @Step("get attribute of the red dot in the notification button")
    public String notificationButtonWithAlert() {
        waitForElementToBeVisibility(notificationButtonWithAlert);
        return getAttribute(notificationButtonWithAlert, "src");
    }

    @Step("choose doc form notification container")
    public void chooseDocumentUpdate(String docUpdateText) {
        waitForPageFinishLoading();
        scrollToElement(notificationIcon);
        waitForElementToBeClickable(notificationIcon);
        click(notificationIcon);
        waitForPageFinishLoading();
        waitForElementToBeVisibility(notificationContainerWindow);
        sleep(6000); //the sleep here is to improve test stability
        for (WebElement el : documentsUpdateList) {
            if (getText(el).contains(docUpdateText)) {
                sleep(6000);//the sleep here is to improve test stability
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Click on back to btrust user button")
    public void clickOnBackToBtrustUserButton() {
        waitForElementToBeClickable(backToBtrustUserButton);
        click(backToBtrustUserButton);
    }


}