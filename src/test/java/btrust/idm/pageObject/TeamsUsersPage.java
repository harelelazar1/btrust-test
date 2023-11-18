package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TeamsUsersPage extends BasePage {
    public TeamsUsersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div .SettingsHeaderFiltersAndSearch-module__title__WW0OL")
    protected WebElement teamsUsersTitle;
    @FindBy(css = "div>.AddButton-module__btn__2_wO7")
    protected WebElement newUserButton;
    @FindBy(css = "#long-menu-id ul > li")
    protected List<WebElement> chooseFromMenu;
    @FindBy(css = "td > button")
    protected WebElement userMenu;
    @FindBy(css = ".heading > :nth-child(3)")
    protected WebElement editUserPopupTitle;
    @FindBy(css = ".user-form > :nth-child(1) > input")
    protected WebElement userNameField;

    @FindBy(css = ".user-form > :nth-child(2) > input")
    protected WebElement emailField;
    @FindBy(css = ".buttons > :nth-child(1)")
    protected WebElement cancelButtonEditPopup;
    @FindBy(css = ".buttons > :nth-child(2)")
    protected WebElement updateButtonEditPopup;
    @FindBy(css = ".user-form > :nth-child(1) > .error-message > span")
    protected WebElement errorMessageNameField;
    @FindBy(css = ".user-form > :nth-child(2) > .error-message > span")
    protected WebElement errorMessageEmailField;
    @FindBy(css = ".user-form > :nth-child(3) > :nth-child(1) > .error-message > span")
    protected WebElement errorMessageRoleSelect;
    @FindBy(css = ".user-form > :nth-child(3) > :nth-child(2) > .error-message > span")
    protected WebElement errorMessageGroupSelect;
    @FindBy(css = ".buttons > :nth-child(1)")
    protected WebElement cancelButtonSuspendPopup;
    @FindBy(css = ".buttons > :nth-child(2)")
    protected WebElement OKButtonSuspendPopup;
    @FindBy(css = ".confirm-action-popup > .conf-title")
    protected WebElement suspendPopupTitle;
    @FindBy(css = ".MuiTableRow-root.table-row > :nth-child(4) > span")
    protected List<WebElement> statusUserList;
    @FindBy(css = ".MuiTableRow-root.table-row > :nth-child(4) > span")
    protected WebElement statusUser;
    @FindBy(css = "[name='name']")
    protected WebElement addUserNameField;
    @FindBy(css = "[name='email']")
    protected WebElement addUserEmailField;
    @FindBy(css = ".user-form > :nth-child(3) > :nth-child(1) > .default-select")
    protected WebElement addUserRoleSelect;
    @FindBy(css = ".user-form > :nth-child(3) > :nth-child(2) > .default-select")
    protected WebElement addUserGroupSelect;
    @FindBy(css = ".user-form > :nth-child(3) > :nth-child(1) .default__menu-list > .default__option")
    protected List<WebElement> addUserRoleList;
    @FindBy(css = ".user-form > :nth-child(3) > :nth-child(2) .default__menu-list > .default__option")
    protected List<WebElement> addUserGroupList;
    @FindBy(css = ".buttons > :nth-child(2)")
    protected WebElement addUserCreateButton;
    @FindBy(css = ".user-cred-popup .title")
    protected WebElement popupCreateUserSuccessTitle;
    @FindBy(css = ".user-cred-popup > .body > :nth-child(2) > .data")
    protected WebElement popupCreateUserSuccessPassword;
    @FindBy(css = ".user-cred-popup .action > button")
    protected WebElement popupCreateUserSuccessCloseButton;
    @FindBy(css = ".user-confirmation-popup  > .buttons > :nth-child(2)")
    protected WebElement popupNewUserConfirmationConfirmButton;
    @FindBy(css = ".confirm-action-popup > .conf-title > span")
    protected WebElement deleteUserPopupTitle;
    @FindBy(css = ".confirm-action-popup > .buttons > :nth-child(1)")
    protected WebElement deleteUserPopupCancelButton;
    @FindBy(css = ".confirm-action-popup > .buttons > :nth-child(2)")
    protected WebElement deleteUserPopupOKButton;
    @FindBy(css = " .user-confirmation-popup > .user-conf-title")
    protected WebElement resetPassPopupTile;
    @FindBy(css = ".user-confirmation-popup > .buttons > :nth-child(1)")
    protected WebElement resetPassPopupCancelButton;
    @FindBy(css = ".user-confirmation-popup > .buttons > :nth-child(2)")
    protected WebElement resetPassPopupConfirmButton;
    @FindBy(css = ".user-actions > button")
    protected List<WebElement> openUserMenuList;
    @FindBy(css = "div.Table-module__wrapper__vQJw7 .Table-module__row__33Rxn :nth-child(2)")
    protected List<WebElement> userNameList;
    @FindBy(css = ".MuiTableRow-root.table-row > :nth-child(2)")
    protected WebElement userName;
    @FindBy(css = ".MuiTableRow-root.table-row > :nth-child(3)")
    protected List<WebElement> emailList;
    @FindBy(css = ".MuiTableRow-root.table-row > :nth-child(3)")
    protected WebElement email;
    @FindBy(css = "#new-pass")
    protected WebElement password;
    @FindBy(css = ".confirm-action-popup button")
    protected WebElement closeButtonConfirmationResetPass;
    @FindBy(css = ".SettingsHeaderFiltersAndSearch-module__filters__2gIpr :nth-child(5) input")
    protected WebElement SearchField;


    @FindBy(css = ".GeneralInformation-module__label__268ac,.LoginDetails-module__label__rNe2L,.LoginDetails-module__reset__25GgP,.RolePermissions-module__label__1DpO6")
    protected List<WebElement> titleFiled;

    protected String pass;

    @Step("check that all the elements appear")
    public String teamsUsersTitle(String text) {
        waitForTextToBeInElement(teamsUsersTitle, text);
        return getText(teamsUsersTitle);
    }


    @Step("choose user: {userName}, open menu and choose from menu: {chooseFromUserMenu}")
    public void chooseFromUserMenu(String userName, String chooseFromUserMenu) {

        for (int i = 0; i < userNameList.size(); i++) {
            scrollToElement(userNameList.get(i));
            if (getText(userNameList.get(i)).equalsIgnoreCase(userName)) {
                scrollToElement(userNameList.get(i));
                click(userNameList.get(i));
                waitForPageFinishLoading();
                waitForElementToBeVisibility(titleFiled.get(0));
                for (WebElement el : titleFiled) {
                    if (getText(el).equalsIgnoreCase(chooseFromUserMenu)) {
                        scrollToElement(el);
                        click(el);
                        break;
                    }
                }
                break;
            }
        }
    }


    @Step("check that all the elements appear in edit user popup")
    public String enterToEditUserPopup(String text) {
        waitForTextToBeInElement(editUserPopupTitle, text);
        return getText(editUserPopupTitle);
    }

    @Step("edit name and email and click update")
    public void editUser(String name, String email) {
        sleep(500);
        waitForElementToBeClickable(userNameField);
        fillText(userNameField, name);
        waitForElementToBeClickable(emailField);
        fillText(emailField, email);
        waitForElementToBeClickable(updateButtonEditPopup);
        click(updateButtonEditPopup);
    }

    @Step("click on cancel button")
    public void cancelButtonEditPopup() {
        waitForElementToBeClickable(cancelButtonEditPopup);
        click(cancelButtonEditPopup);
    }

    @Step("check that the name's user appears in the table")
    public String userName(String uName) {
        String foundName = null;
        waitForElementToBeClickable(userName);
        List<WebElement> chooseUser = userNameList;
        for (int i = 0; i < chooseUser.size(); i++) {
            WebElement name = chooseUser.get(i);
            String nameU = getText(name);
            if (nameU.equalsIgnoreCase(uName)) {
                Actions action = new Actions(driver);
                action.moveToElement(name).build().perform();
                waitForElementToBeVisibility(driver.findElement(By.cssSelector(".MuiTableRow-root.table-row > :nth-child(2)")));
                WebElement user = userNameList.get(i);
                foundName = getText(user);
                break;
            }
        }
        return foundName;
    }

    @Step("check that the email's user appears in the table")
    public String email(String uEmail) {
        String foundEmail = null;
        waitForElementToBeClickable(email);
        List<WebElement> chooseEmail = emailList;
        for (int i = 0; i < chooseEmail.size(); i++) {
            WebElement name = chooseEmail.get(i);
            String emailU = getText(name);
            if (emailU.equalsIgnoreCase(uEmail)) {
                Actions action = new Actions(driver);
                action.moveToElement(name).build().perform();
                waitForElementToBeVisibility(driver.findElement(By.cssSelector(".MuiTableRow-root.table-row > :nth-child(3)")));
                WebElement email = emailList.get(i);
                foundEmail = getText(email);
                break;
            }
        }
        return foundEmail;
    }

    @Step("check that error message of name fields appear")
    public String errorMessageNameField(String text) {
        waitForTextToBeInElement(errorMessageNameField, text);
        return getText(errorMessageNameField);
    }

    @Step("check that error message of name fields appear")
    public String errorMessageEmailField(String text) {
        waitForTextToBeInElement(errorMessageEmailField, text);
        return getText(errorMessageEmailField);
    }

    @Step("check that error message of role select appear")
    public String errorMessageRoleSelect(String text) {
        waitForTextToBeInElement(errorMessageRoleSelect, text);
        return getText(errorMessageRoleSelect);
    }

    @Step("check that error message of group select appear")
    public String errorMessageGroupSelect(String text) {
        waitForTextToBeInElement(errorMessageGroupSelect, text);
        return getText(errorMessageGroupSelect);
    }

    @Step("check that all the elements appear in suspend user popup")
    public String suspendPopupTitle(String text) {
        waitForTextToBeInElement(suspendPopupTitle, text);
        return getText(suspendPopupTitle);
    }

    @Step("click on cancel button")
    public void cancelButtonSuspendPopup() {
        waitForElementToBeClickable(cancelButtonSuspendPopup);
        click(cancelButtonSuspendPopup);
    }

    @Step("click on cancel button")
    public void OKButtonSuspendPopup() {
        waitForElementToBeClickable(OKButtonSuspendPopup);
        click(OKButtonSuspendPopup);
    }

    @Step("check that the status of user: {uName} ")
    public String statusUser(String uName) {
        String foundStatus = null;
        waitForElementToBeClickable(statusUser);
        List<WebElement> chooseUser = userNameList;
        for (int i = 0; i < chooseUser.size(); i++) {
            WebElement name = chooseUser.get(i);
            String nameU = getText(name);
            if (nameU.equalsIgnoreCase(uName)) {
                Actions action = new Actions(driver);
                action.moveToElement(name).build().perform();
                waitForElementToBeVisibility(statusUser);
                WebElement status = statusUserList.get(i);
                foundStatus = getText(status);
                break;
            }
        }
        return foundStatus;
    }

    @Step("click on newUserButton")
    public void newUserButton() {
        sleep(2000);
        waitForElementToBeClickable(newUserButton);
        click(newUserButton);
    }

    @Step("click on addUserCreateButton")
    public void addUserCreateButton() {
        waitForElementToBeClickable(addUserCreateButton);
        click(addUserCreateButton);
    }

    @Step("add new user")
    public void addUser(String addUserName, String addUserEmail, String chooseFromRoleList, String chooseFromGroupList) {
        waitForElementToBeClickable(newUserButton);
        click(newUserButton);
        waitForElementToBeClickable(addUserNameField);
        fillText(addUserNameField, addUserName);
        waitForElementToBeClickable(addUserEmailField);
        fillText(addUserEmailField, addUserEmail);
        waitForElementToBeClickable(addUserRoleSelect);
        click(addUserRoleSelect);
        for (WebElement el : addUserRoleList) {
            if (el.getText().equalsIgnoreCase(chooseFromRoleList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(addUserGroupSelect);
        click(addUserGroupSelect);
        for (WebElement el : addUserGroupList) {
            if (el.getText().equalsIgnoreCase(chooseFromGroupList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(addUserCreateButton);
        click(addUserCreateButton);
    }

    @Step("click on popupNewUserConfirmationConfirmButton")
    public void popupNewUserConfirmationConfirmButton() {
        waitForElementToBeClickable(popupNewUserConfirmationConfirmButton);
        click(popupNewUserConfirmationConfirmButton);
    }

    @Step("click on CloseButton")
    public void popupCreateUserSuccessCloseButton() {
        waitForElementToBeClickable(popupCreateUserSuccessCloseButton);
        click(popupCreateUserSuccessCloseButton);
    }

    @Step("Check the title of popupCreateUserSuccessTitle")
    public String popupTitle(String text) {
        waitForTextToBeInElement(popupCreateUserSuccessTitle, text);
        return getText(popupCreateUserSuccessTitle);
    }

    @Step("Copy - Paste of password")
    public String copyPass() {
        waitForElementToBeVisibility(popupCreateUserSuccessPassword);
        pass = getText(popupCreateUserSuccessPassword);
        System.out.println("copy: " + pass);
        return pass;
    }

    @Step("Check that deleteUserPopupTitle appear")
    public String deleteUserPopup(String text) {
        waitForTextToBeInElement(deleteUserPopupTitle, text);
        return getText(deleteUserPopupTitle);
    }

    @Step("click on deleteUserPopupCancelButton")
    public void deleteUserPopupCancelButton() {
        waitForElementToBeClickable(deleteUserPopupCancelButton);
        click(deleteUserPopupCancelButton);
    }

    @Step("click on deleteUserPopupOKButton")
    public void deleteUserPopupOKButton() {
        waitForElementToBeClickable(deleteUserPopupOKButton);
        click(deleteUserPopupOKButton);
    }

    @Step("Check resetPassPopupTile appear")
    public String resetPassPopupTile(String text) {
        waitForPageFinishLoading();
        scrollToElement(resetPassPopupTile);
        waitForTextToBeInElement(resetPassPopupTile, text);
        return getText(resetPassPopupTile);
    }

    @Step("click on resetPassPopupConfirmButton")
    public void resetPassPopupConfirmButton() {
        waitForPageFinishLoading();
        scrollToElement(resetPassPopupConfirmButton);
        waitForElementToBeClickable(resetPassPopupConfirmButton);
        click(resetPassPopupConfirmButton);
    }

    @Step("click on resetPassPopupCancelButton")
    public void resetPassPopupCancelButton() {
        waitForElementToBeClickable(resetPassPopupCancelButton);
        click(resetPassPopupCancelButton);
    }

    @Step("Copy password from reset password popup")
    public String copyPassResetPassword() {
        waitForElementToBeVisibility(password);
        pass = getText(password);
        System.out.println("copy: " + pass);
        return pass;
    }

    @Step("click on closeButtonConfirmationResetPass")
    public void closeButtonConfirmationResetPass() {
        waitForElementToBeClickable(closeButtonConfirmationResetPass);
        click(closeButtonConfirmationResetPass);
    }
}