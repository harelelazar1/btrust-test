package btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewRolePage extends BasePage {
    public NewRolePage(WebDriver driver) {
        super(driver);
    }

    protected boolean bol;

    @FindBy(css = ".NewRole-module__box__16GAL>.NewRole-module__inputContainer__ud5Om")
    protected List<WebElement> roleNameArea;
    @FindBy(css = "[placeholder]")
    protected List<WebElement> fillTypeName;
    @FindBy(css = "div>.NewRole-module__text__3LUhD")
    protected WebElement newRoleTitle;
    @FindBy(css = "[placeholder]")
    protected WebElement attributeName;
    @FindBy(css = ".DataFiltering-module__values__3LlX9>:nth-child(4)>[value]")
    protected WebElement fillText;


    @Step("insert name to field")
    public void insertNameToField(String field) {
        waitForPageFinishLoading();
        scrollToElement(fillText);
        fillText(fillText, field);
    }

    @Step("Return the attribute name from  Role Name")
    public String attributeNameFromRoleName() {
        waitForElementToBeVisibility(attributeName);
        return getAttribute(attributeName, "value");
    }

    @Step("Return the text of new role title")
    public String newRoleTitle() {
        waitForElementToBeVisibility(newRoleTitle);
        return getText(newRoleTitle);
    }

    @Step("Confirm text name and fill text")
    public void confirmRoleTextAndFillText(String title, String name) {
        waitForPageFinishLoading();
        for (int i = 0; i < roleNameArea.size(); i++) {
            if (getText(roleNameArea.get(i)).equalsIgnoreCase(title)) {
                fillText(fillTypeName.get(i), name);
                break;
            }
        }
    }

}







