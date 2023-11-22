package btrust.btrustOne.admin.usersManager.users.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EditUserPage extends BasePage {

    String status;

    public EditUserPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".MuiButtonBase-root.MuiButton-root.MuiButton-text")
    protected WebElement clickChangeStatusButton;
    @FindBy(css = ".MuiButtonBase-root.MuiListItem-root.MuiMenuItem-root.MuiMenuItem-gutters.MuiListItem-gutters.MuiListItem-button")
    protected WebElement selectStatus;
    @FindBy(css = ".Header-module__info__3uNIl .Header-module__status__wX760.Header-module__active__LEQ0i")
    protected WebElement statusActive;
    @FindBy(css = ".Header-module__info__3uNIl .Header-module__status__wX760.undefined")
    protected WebElement statusNonActive;
    @FindBy(css = ".RolePermissions-module__selectors__1tXSt>:nth-child(2) .RolePermissions-module__label__1DpO6.RolePermissions-module__required__3egqa")
    protected List<WebElement> titleFields2Select;
    @FindBy(css = ".RolePermissions-module__selectors__1tXSt>:nth-child(2) .default-custom-selector.isWhite-undefined")
    protected List<WebElement> field2Select;
    @FindBy(css = ".default__menu-list > .default__option")
    protected List<WebElement> selectNameFromListSelect;
    @FindBy(css = ".Actions-module__container__1A1Yw > button[type='button']")
    protected List<WebElement> saveButton;
    @FindBy(css = ".RolePermissions-module__selectors__1tXSt > .RolePermissions-module__add__3ytpm")
    protected WebElement LinkAddNewRolePermission;
    @FindBy(css = " .GeneralInformation-module__label__268ac , .LoginDetails-module__label__25f08.LoginDetails-module__required__2Plrz")
    protected List<WebElement> titleFieldsInput;
    @FindBy(css = ".GeneralInformation-module__inputItem__NwWsd [value],.LoginDetails-module__inputItem__3Lcoy [value]")
    protected List<WebElement> fieldInput;
    @FindBy(css = ".RolePermissions-module__item__10XHu .RolePermissions-module__label__1DpO6.RolePermissions-module__required__3egqa")
    protected List<WebElement> titleFieldsSelect;
    @FindBy(css = ".RolePermissions-module__inputItem__nA_be .default-custom-selector")
    protected List<WebElement> fieldSelect;
    @FindBy(css = ".custom-phone-input.m.react-tel-input .flag-custom-container.flag-dropdown")
    protected WebElement clickPhoneNumberField;
    @FindBy(css = ".input-custom.undefined.form-control")
    protected WebElement phoneNumberField;
    @FindBy(css = "ul[role='listbox'] > .country")
    protected List<WebElement> selectNameFromListPhoneNumber;
    @FindBy(css = ".LoginDetails-module__inputItem__3SvqK .LoginDetails-module__reset__25GgP")
    protected WebElement ResetPassword;
    @FindBy(css = ".default__single-value--is-disabled")
    protected List<WebElement> fieldSelectDisable;
    @FindBy(css = ".css-1uccc91-singleValue")
    protected List<WebElement> fieldSelectEnable;


    @Step("check If input fields enable")
    public boolean checkIfInputFieldsButtonEnable() {
        try {
            waitForPageFinishLoading();
            for (WebElement el : fieldInput)
                if (isEnabled(el)) {
                    return true;
                }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    protected boolean waitToClickable(WebElement el) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(el));
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Step("check If select fields enable")
    public boolean checkIfSelectFieldsButtonEnable(String permission) {
        try {
            switch (permission) {
                case "READ": {
                    waitForPageFinishLoading();
                    for (WebElement el : fieldSelectDisable) {
                        click(el);
                        if (isDisplayed(selectNameFromListSelect.get(0))) {
                            return true;
                        }
                    }
                    break;
                }
                case "EDIT & CREATE": {
                    waitForPageFinishLoading();
                    for (WebElement el : fieldSelectEnable) {
                        click(el);
                        if (isDisplayed(selectNameFromListSelect.get(0))) {
                            return true;
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("click change status button")
    public void clickChangeStatusButton() {
        waitForPageFinishLoading();
        click(clickChangeStatusButton);
    }


    @Step("check If Change Status Button Display")
    public boolean checkIfChangeStatusButtonDisplay() {
        try {
            waitForPageFinishLoading();
            scrollToElement(clickChangeStatusButton);
            if (isDisplayed(clickChangeStatusButton)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("check If Reset password Button Display")
    public boolean checkIfResetPasswordButtonDisplay() {
        try {
            waitForPageFinishLoading();
            if (isDisplayed(ResetPassword)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("select Status From List")
    public void selectStatusFromList() {
        waitForPageFinishLoading();
        click(selectStatus);
    }

    @Step("user name status")
    public boolean userNameStatus(String userStatus) {
        waitForPageFinishLoading();
        switch (userStatus) {
            case "Active": {
                if (getText(statusActive).equalsIgnoreCase(userStatus)) {
                    return true;
                }
                break;
            }
            case "Non-active": {
                if (getText(statusNonActive).equalsIgnoreCase(userStatus)) {
                    return true;
                }

                break;
            }
        }
        return false;
    }

    @Step("Edit User page > fill text in fields")
    public void rolePermissions(String fieldName, String nameFromList) {
        for (int j = 0; j < titleFields2Select.size(); j++) {
            waitForElementToBeVisibility(titleFields2Select.get(j));
            if (getText(titleFields2Select.get(j)).equalsIgnoreCase(fieldName)) {
                click(field2Select.get(j));
                waitForList(selectNameFromListSelect);
                for (WebElement el : selectNameFromListSelect) {
                    if (getText(el).equalsIgnoreCase(nameFromList)) {
                        scrollToElement(el);
                        waitForElementToBeClickable(el);
                        click(el);
                        break;
                    }
                }
            }
        }
    }


    @Step("click on Button")
    public void clickOnButton(String name) {
        for (int i = 0; i < saveButton.size(); i++) {
            if (getText(saveButton.get(i)).equalsIgnoreCase(name)) {
                waitForElementToBeClickable(saveButton.get(i));
                click(saveButton.get(i));
                break;
            }
        }
    }

    @Step("click link add new role permission")
    public void clickAddNewRolePermission() {
        waitForElementToBeClickable(LinkAddNewRolePermission);
        click(LinkAddNewRolePermission);
    }


    @Step("check if link add new role permission display")
    public boolean checkAddNewRolePermissionDisplay() {
        try {
            waitForPageFinishLoading();
            if (isDisplayed(LinkAddNewRolePermission)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("fill text in fields")
    public void fillTextInEditUserPage(String fieldType, String fieldNameInput, String valueName, String fieldName, String nameFromList, String countryPhone, String number) {
        switch (fieldType) {
            case "input": {
                waitForPageFinishLoading();
                for (int i = 0; i < titleFieldsInput.size(); i++) {
                    waitForElementToBeVisibility(titleFieldsInput.get(i));
                    if (getText(titleFieldsInput.get(i)).equalsIgnoreCase(fieldNameInput)) {
                        fillText(fieldInput.get(i), valueName);
                        break;
                    }
                }
                break;
            }
            case "select": {
                waitForPageFinishLoading();
                for (int j = 0; j < titleFieldsSelect.size(); j++) {
                    waitForElementToBeVisibility(titleFieldsSelect.get(j));
                    if (getText(titleFieldsSelect.get(j)).equalsIgnoreCase(fieldName)) {
                        click(fieldSelect.get(j));
                        waitForElementToBeVisibility(selectNameFromListSelect.get(0));
                        for (WebElement el : selectNameFromListSelect) {
                            if (getText(el).equalsIgnoreCase(nameFromList)) {
                                scrollToElement(el);
                                waitForElementToBeClickable(el);
                                click(el);
                                break;
                            }
                        }
                    }

                }
                break;
            }
            case "inputPhoneNumber": {
                waitForPageFinishLoading();
                for (int i = 0; i < titleFieldsInput.size(); i++) {
                    waitForElementToBeVisibility(titleFieldsInput.get(i));
                    if (getText(titleFieldsInput.get(i)).equalsIgnoreCase(fieldNameInput)) {
                        click(clickPhoneNumberField);
                        for (WebElement el : selectNameFromListPhoneNumber) {
                            if (getText(el).contains(countryPhone)) {
                                scrollToElement(el);
                                waitForElementToBeClickable(el);
                                click(el);
//                                fillText(phoneNumberField,number);
                                phoneNumberField.sendKeys(number);
                                break;
                            }
                        }
                    }
                }
                break;
            }
        }
    }


}



