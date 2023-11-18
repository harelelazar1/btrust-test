package btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PermissionsDataFiltering extends BasePage {
    public PermissionsDataFiltering(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".default__control.css-yk16xz-control .default__placeholder.css-1wa3eu0-placeholder")
    protected List<WebElement> selectField;
    @FindBy(css = "[class] [tabindex='-1']")
    protected List<WebElement> selectListName;


    @Step("select from list")
    public void selectFromList(String nameFromList) {
        waitForPageFinishLoading();
        for (int i = 0; i < selectField.size(); i++) {
            waitForElementToBeClickable(selectField.get(i));
            click(selectField.get(i));


            for (WebElement el : selectListName) {
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
