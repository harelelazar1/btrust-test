package btrust.btrustOne.admin.usersManager.users.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewUserPage extends BasePage {


    public NewUserPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".Title-module__top__1W8-6>.Title-module__text__2ZDff")
    protected WebElement usersTitle;
    @FindBy(css = " .GeneralInformation-module__label__268ac , .LoginDetails-module__label__25f08.LoginDetails-module__required__2Plrz")
    protected List<WebElement> titleFieldsInput;
    @FindBy(css = ".GeneralInformation-module__inputItem__NwWsd [value],.LoginDetails-module__inputItem__3Lcoy [value]")
    protected List<WebElement> fieldInput;
    @FindBy(css = ".RolePermissions-module__item__10XHu .RolePermissions-module__label__1DpO6.RolePermissions-module__required__3egqa")
    protected List<WebElement> titleFieldsSelect;
    @FindBy(css = ".default-custom-selector  .default__value-container input")
    protected List<WebElement> fieldSelect;
    @FindBy(css = ".custom-phone-input.not-existedCode.react-tel-input .flag-custom-container.flag-dropdown")
    protected WebElement clickPhoneNumberField;
    @FindBy(css = ".flag-custom-container.flag-dropdown.open .country-list .country")
    protected List<WebElement> selectPhoneNumberList;
    @FindBy(css = ".input-custom.undefined.form-control")
    protected WebElement phoneNumberField;
    @FindBy(css = ".custom-phone-input.not-existedCode.react-tel-input .country")
    protected List<WebElement> selectNameFromListPhoneNumber;
    @FindBy(css = ".default__menu-list > .default__option")
    protected List<WebElement> selectNameFromListSelect;
    @FindBy(css = ".BottomStickyActions-module__actions__1YB7n [type='button']")
    protected List<WebElement> CancelOrCreateButton;
    @FindBy(css = ".InputSearch-module__inputItem__2Oj7R [value]")
    protected WebElement fieldTypeUserNameToSearch;
    @FindBy(css = ".InputSearch-module__inputItem__2Oj7R .MuiSvgIcon-root")
    protected WebElement findMagnifyingGlass;
    @FindBy(css = ".GeneralInformation-module__items__I9adP > div:nth-of-type(1) > [value]")
    protected WebElement attributeFirstName;
    @FindBy(css = ".GeneralInformation-module__items__I9adP > div:nth-of-type(2) > [value]")
    protected WebElement attributeLastName;
    @FindBy(css = ".RolePermissions-module__selectors__1tXSt > .RolePermissions-module__add__3ytpm")
    protected WebElement LinkAddNewRolePermission;
    @FindBy(css = ".RolePermissions-module__selectors__1tXSt>:nth-child(2) .RolePermissions-module__label__1DpO6.RolePermissions-module__required__3egqa")
    protected List<WebElement> titleFields2Select;
    @FindBy(css = ".RolePermissions-module__selectors__1tXSt>:nth-child(2) .default-custom-selector.isWhite-undefined")
    protected List<WebElement> field2Select;


    @Step("click link add new role permission")
    public void clickAddNewRolePermission() {
        waitForElementToBeClickable(LinkAddNewRolePermission);
        click(LinkAddNewRolePermission);
    }


    @Step("Return the attribute name from  Role Name")
    public String attributeNameFromAddNewUser(String fieldName) {
        String value = null;
        switch (fieldName) {
            case "First Name": {
                waitForElementToBeVisibility(attributeFirstName);
                value = getAttribute(attributeFirstName, "value");
                break;
            }
            case "Last Name": {
                waitForElementToBeVisibility(attributeLastName);
                value = getAttribute(attributeLastName, "value");
                break;
            }
        }
        return value;
    }

    @Step("Fill text in field type user name to search")
    public void fillTextInFieldTypeUserNameToSearch(String userName) {
        waitForPageFinishLoading();
        waitForElementToBeClickable(fieldTypeUserNameToSearch);
        click(fieldTypeUserNameToSearch);
        clear(fieldTypeUserNameToSearch);
        sleep(3000);
        fieldTypeUserNameToSearch.sendKeys(userName);
        waitForElementToBeVisibility(findMagnifyingGlass);
    }


    @Step("click on Button")
    public void clickOnButton(String name) {
        for (int i = 0; i < CancelOrCreateButton.size(); i++) {
            if (getText(CancelOrCreateButton.get(i)).equalsIgnoreCase(name)) {
                waitForElementToBeClickable(CancelOrCreateButton.get(i));
                click(CancelOrCreateButton.get(i));
                break;
            }
        }
    }

    @Step("Return the text of users title")
    public String usersTitle() {
        waitForElementToBeVisibility(usersTitle);
        return getText(usersTitle);
    }

    @Step("New User page > fill text in fields ")
    public void fillTextInNewUserPage(String fieldType, String fieldNameInput, String valueName, String fieldName, String nameFromList, String countryPhone, String number) {
        switch (fieldType) {
            case "input": {
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
                for (int j = 0; j < titleFieldsSelect.size(); j++) {
                    waitForElementToBeVisibility(titleFieldsSelect.get(j));
                    if (getText(titleFieldsSelect.get(j)).equalsIgnoreCase(fieldName)) {
                        scrollToElement(fieldSelect.get(j));
                        click(fieldSelect.get(j));
                        fillText(fieldSelect.get(j), nameFromList);
                        waitForList(selectNameFromListSelect);
                        for (WebElement el : selectNameFromListSelect) {
                            System.out.println(getText(el));
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
                for (int i = 0; i < titleFieldsInput.size(); i++) {
                    waitForElementToBeVisibility(titleFieldsInput.get(i));
                    if (getText(titleFieldsInput.get(i)).equalsIgnoreCase(fieldNameInput)) {
                        scrollToElement(clickPhoneNumberField);
                        click(clickPhoneNumberField);
                        for (WebElement el : selectNameFromListPhoneNumber) {
                            if (getText(el).contains(countryPhone)) {
                                scrollToElement(el);
                                waitForElementToBeClickable(el);
                                click(el);
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

    @Step("New User page > fill text in fields ")
    public void rolePermissions(String fieldName, String nameFromList) {
        for (int j = 0; j < titleFields2Select.size(); j++) {
            waitForElementToBeVisibility(titleFields2Select.get(j));
            if (getText(titleFields2Select.get(j)).equalsIgnoreCase(fieldName)) {
                click(field2Select.get(j));
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
    }
}







