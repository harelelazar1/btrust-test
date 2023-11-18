package btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PermissionsAdministritive extends BasePage {
    public PermissionsAdministritive(WebDriver driver) {
        super(driver);
    }

    protected boolean bol;

    @FindBy(css = ".CheckboxTable-module__row__2D_rk>div:nth-child(2) .MuiButtonBase-root")
    protected List<WebElement> readAdministritive;
    @FindBy(css = ".CheckboxTable-module__row__2D_rk>div:nth-child(3) .MuiButtonBase-root")
    protected List<WebElement> editAndCreateAdministritive;
    @FindBy(css = ".CheckboxTable-module__row__2D_rk>:nth-child(2) .Mui-checked")
    protected List<WebElement> readAdministritiveCheckboxMark;
    @FindBy(css = ".CheckboxTable-module__row__2D_rk>:nth-child(3) .Mui-checked")
    protected List<WebElement> editAndCreateAdministritiveCheckboxMark;
    @FindBy(css = ".CheckboxTable-module__row__2D_rk>:nth-child(1).CheckboxTable-module__item__3BBTh")
    protected List<WebElement> administritiveCompanyAdministrator;
    @FindBy(css = ".CheckboxTable-module__row__2D_rk>div:nth-child(1)")
    protected List<WebElement> companyAdministrator;


    @Step("permissions > administritive tab choose checkbox")
    public void chooseCheckboxFromAdministritive(String companyAdministratorType, String permission) {
        waitForPageFinishLoading();
        for (int i = 0; i < companyAdministrator.size(); i++) {
            if (getText(companyAdministrator.get(i)).equalsIgnoreCase(companyAdministratorType)) {
                switch (permission) {
                    case "READ":
                        scrollToElement(readAdministritive.get(i));
                        clickByJS(readAdministritive.get(i));
                        break;
                    case "EDIT & CREATE":
                        scrollToElement(editAndCreateAdministritive.get(i));
                        clickByJS(editAndCreateAdministritive.get(i));
                        break;
                }
            }
        }
    }


    @Step("Permission administritive - check if checkbox is mark")
    public boolean administritiveCheckboxIsMark(String companyAdministrator, String permission) {
        waitForPageFinishLoading();
        try {
            for (int i = 0; i < administritiveCompanyAdministrator.size(); i++) {
                if (getText(administritiveCompanyAdministrator.get(i)).equalsIgnoreCase(companyAdministrator)) {
                    waitForList(readAdministritiveCheckboxMark);
                    switch (permission) {
                        case "READ":
                            bol = isDisplayed(readAdministritiveCheckboxMark.get(0));
                            break;
                        case "EDIT & CREATE":
                            bol = isDisplayed(editAndCreateAdministritiveCheckboxMark.get(0));
                            break;
                    }
                    break;
                }
            }
            return bol;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Permission administritive - check if checkbox is enable")
    public boolean administritiveCheckboxIsEnable(String companyAdministrator, String permission) {
        waitForPageFinishLoading();
        try {
            for (int i = 0; i < administritiveCompanyAdministrator.size(); i++) {
                if (getText(administritiveCompanyAdministrator.get(i)).equalsIgnoreCase(companyAdministrator)) {
                    switch (permission) {
                        case "READ":
                            bol = isEnabled(readAdministritive.get(i));
                            System.out.println(bol);
                            break;
                        case "EDIT&CREATE":
                            bol = isEnabled(editAndCreateAdministritive.get(i));
                            break;
                    }
                }
            }
            return bol;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


}
