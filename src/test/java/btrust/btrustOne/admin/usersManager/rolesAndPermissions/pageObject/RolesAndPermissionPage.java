package btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RolesAndPermissionPage extends BasePage {

    public RolesAndPermissionPage(WebDriver driver) {
        super(driver);
    }

    protected boolean bol;
    protected String oldRoleName;


    @FindBy(css = "div>button.AddButton-module__btn__2_wO7")
    protected WebElement addRolesAndPermissionButton;
    @FindBy(css = "[placeholder='Type role name to search']")
    protected WebElement fieldTypeRoleNameToSearch;
    @FindBy(css = "[role='tabpanel'].Table-module__row__33Rxn :nth-child(2)")
    protected List<WebElement> roleNameInRoleList;
    @FindBy(css = ".InputSearch-module__inputItem__2Oj7R>.MuiSvgIcon-root")
    protected WebElement findMagnifyingGlass;
    @FindBy(css = "div>.SettingsHeader-module__title__acJ6f")
    protected WebElement rolesAndPermissionsTitle;
    @FindBy(css = "[role='tabpanel'] .Table-module__item__3SnI6.Table-module__name__HvxqP")
    protected List<WebElement> clickRoleNameFromRoleList;
    @FindBy(css = "div:nth-child(1) > .Table-module__item__3SnI6.Table-module__name__HvxqP")
    protected WebElement clickFirstRoleInTheScreen;


    @Step("Return the text of roles and permissions title")
    public String rolesAndPermissionsTitle() {
        waitForElementToBeVisibility(rolesAndPermissionsTitle);
        return getText(rolesAndPermissionsTitle);
    }

    @Step("Click on add new role button")
    public void clickOnAddRolesAndPermissionButton() {
        waitForPageFinishLoading();
        scrollToElement(addRolesAndPermissionButton);
        click(addRolesAndPermissionButton);
    }


    @Step("check if add new role button display")
    public boolean checkIfAddRolesAndPermissionButtonDispaly() {
        try {
            waitForPageFinishLoading();
            if (isDisplayed(addRolesAndPermissionButton)) {
                scrollToElement(addRolesAndPermissionButton);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }
        return false;
    }


    @Step("Fill text in field type role name to search")
    public void fillTextInFieldTypeRoleNameToSearch(String roleName) {
        waitForPageFinishLoading();
        waitForElementToBeClickable(fieldTypeRoleNameToSearch);
        fillText(fieldTypeRoleNameToSearch, roleName);
        waitForElementToBeVisibility(findMagnifyingGlass);
    }

    @Step("Fill text in field type role name to search and click")
    public void fillTextInFieldTypeRoleNameToSearchAndClick(String roleName) {
        waitForPageFinishLoading();
//        scrollToElement(el);
        waitForElementToBeClickable(fieldTypeRoleNameToSearch);
        fillText(fieldTypeRoleNameToSearch, roleName);
        waitForElementToBeVisibility(findMagnifyingGlass);
        for (WebElement el : roleNameInRoleList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(roleName)) {
                click(el);
                break;
            }
        }

    }


    @Step("Search role name in the role list")
    public boolean searchRoleNameInRoleList(String newRoleName) {
        waitForPageFinishLoading();
        waitForElementToBeClickable(fieldTypeRoleNameToSearch);
        fillText(fieldTypeRoleNameToSearch, newRoleName);
        try {
                if (roleNameInRoleList.size() == 0) {
                    return true;
                }
        } catch (Exception e) {
            System.out.println("The el is not found");
        }
        return false;
    }

    @Step("search role name in role list")
    public String roleList(String roleName) {
        for (WebElement el : roleNameInRoleList) {
            if (getText(el).equalsIgnoreCase(roleName)) {
                waitForElementToBeVisibility(el);
                roleName = getText(el);
                break;
            }
        }
        return roleName;
    }

    @Step("click on role name from role list")
    public void clickRoleNameFromRoleList(String roleName) {
        for (WebElement el : clickRoleNameFromRoleList) {
            if (getText(el).equalsIgnoreCase(roleName)) {
                waitForElementToBeVisibility(el);
                click(el);
                break;
            }
        }

    }

    @Step("click on role name from role list")
    public String clickFirstRoleInTheScreen() {
        waitForElementToBeVisibility(clickFirstRoleInTheScreen);
        oldRoleName = getText(clickFirstRoleInTheScreen);
        waitForElementToBeClickable(clickFirstRoleInTheScreen);
        clickByJS(clickFirstRoleInTheScreen);
        return oldRoleName;
    }
}