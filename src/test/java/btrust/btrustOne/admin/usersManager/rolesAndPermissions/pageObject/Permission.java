package btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Permission extends BasePage {
    public Permission(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[role='tab'] > .MuiTab-wrapper")
    protected List<WebElement> navigatePermissionTab;
    @FindBy(css = ".SelectAll-module__container__3L52w .MuiButtonBase-root")
    protected WebElement allButton;
    @FindBy(css = ".RoleItem-module__tabContainer__3L5o3 .SelectAll-module__container__3L52w .SelectAll-module__text__2DfIS")
    protected WebElement allButtonStatus;
    @FindBy(css = ".BottomStickyActions-module__wrapper__1b1UJ> button[type='button']")
    protected List<WebElement> buttonList;
    @FindBy(css = ".TopBar-module__title__BaRNs .EditTitleInput-module__edit__2ZSDr")
    protected WebElement editButton;


    @Step("Check if Edit Button Display")
    public boolean checkIfEditButtonDisplay() {
        try {
            waitForPageFinishLoading();
            if (isDisplayed(editButton)) {
                scrollToElement(editButton);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("click on Button")
    public void clickOnButton(String name) {
        waitForPageFinishLoading();
        waitForList(buttonList);
        for (int i = 0; i < buttonList.size(); i++) {
            scrollToElement(buttonList.get(i));
            if (getText(buttonList.get(i)).equalsIgnoreCase(name)) {
                waitForElementToBeClickable(buttonList.get(i));
                clickByJS(buttonList.get(i));
                break;
            }
        }
    }

    @Step("click select all checkbox")
    public void clickSelectAllButton() {
        waitForPageFinishLoading();
        scrollToElement(allButton);
        waitForElementToBeClickable(allButton);
        clickByJS(allButton);
    }

    @Step("click select all checkbox")
    public void clickSelectAllButtonStatus(String allButtonsStatus) {
        waitForPageFinishLoading();
        if (getText(allButtonStatus).contains(allButtonsStatus)) {
            scrollToElement(allButtonStatus);
            waitForElementToBeClickable(allButton);
            click(allButton);
        } else {
            scrollToElement(allButton);
            click(allButton);
            scrollToElement(allButton);
            click(allButton);
        }
    }


    @Step("click Deselect all checkbox")
    public void clickDeselectAllButtonStatus(String allButtonsStatus) {
        waitForPageFinishLoading();
        if (getText(allButtonStatus).contains(allButtonsStatus)) {
            waitForElementToBeClickable(allButton);
            scrollToElement(allButton);
            click(allButton);
            scrollToElement(allButton);
            click(allButton);

        } else {
            scrollToElement(allButton);
            waitForElementToBeClickable(allButton);
            click(allButton);
        }
    }


    @Step("navigate permission tab")
    public void navigatePermissionTab(String permissionTab) {
        waitForPageFinishLoading();
        for (int i = 0; i < navigatePermissionTab.size(); i++) {
            if (getText(navigatePermissionTab.get(i)).equalsIgnoreCase(permissionTab)) {
                scrollToElement(navigatePermissionTab.get(i));
                waitForElementToBeClickable(navigatePermissionTab.get(i));
                clickByJS(navigatePermissionTab.get(i));
                break;
            }
        }
    }


}
