package btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EditRolePage extends BasePage {

    public EditRolePage(WebDriver driver) {
        super(driver);
    }

    protected String roleName;
    protected String data;

    @FindBy(css = ".EditTitleInput-module__inputItem__1-koz>input")
    protected WebElement roleNameField;
    @FindBy(css = "[role='tabpanel'] > [type='button']")
    protected WebElement editRoleNameField;
    @FindBy(css = "[role='tabpanel'] > button")
    protected WebElement saveButton;
    @FindBy(css = ".Breadcrumbs-module__breadcrumbs__3ywP3 [role=tabpanel]")
    protected WebElement linkBack;
    @FindBy(css = ".SelectAll-module__container__3L52w .MuiIconButton-label")
    protected WebElement allButton;
    @FindBy(css = ".default__control.css-yk16xz-control .default__single-value.css-1uccc91-singleValue")
    protected List<WebElement> fieldData;
    @FindBy(css = ".DataFiltering-module__values__3LlX9>:nth-child(4)>[value]")
    protected WebElement fillText;


    @Step("insert name to field")
    public void insertNameToField(String field) {
        fillText(fillText, field);
    }

    @Step("Edit role name field")
    public String editRoleNameField(String newName) {
        waitForElementToBeClickable(editRoleNameField);
        click(editRoleNameField);
        waitForElementToBeClickable(roleNameField);
        fillText(roleNameField, newName);
        roleName = getAttribute(roleNameField, "value");
        click(saveButton);
        return roleName;
    }

    @Step("click on link back to roles and Permissions screen")
    public void linkBack() {
        clickByJS(linkBack);
    }

    @Step("click select all checkbox")
    public void clickSelectAllButton() {
        waitForElementToBeClickable(allButton);
        sleep(2000);
        click(allButton);
    }


    @Step("check field data")
    public String checkFieldData(String name) {
        waitForPageFinishLoading();
        for (int i = 0; i < fieldData.size(); i++) {
            if (getText(fieldData.get(i)).equalsIgnoreCase(name)) {
                waitForElementToBeVisibility(fieldData.get(i));
                String data = getText(fieldData.get(i));
                break;
            }
        }
        return data;
    }
}