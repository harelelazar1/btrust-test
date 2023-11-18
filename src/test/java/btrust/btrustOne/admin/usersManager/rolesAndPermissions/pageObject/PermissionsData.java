package btrust.btrustOne.admin.usersManager.rolesAndPermissions.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PermissionsData extends BasePage {
    public PermissionsData(WebDriver driver) {
        super(driver);
    }

    protected boolean bol;

    @FindBy(css = ".CheckboxTableData-module__subcategoryContainer__GhtMx .CheckboxTableData-module__items__1BB6- > :nth-child(1)")
    protected List<WebElement> dataObjects;
    @FindBy(css = ".CheckboxTableData-module__subcategoryContainer__GhtMx .CheckboxTableData-module__items__1BB6- > :nth-child(1) , .CheckboxTableData-module__name__1qNoM")
    protected List<WebElement> mandateObjects;
    @FindBy(css = ".CheckboxTableData-module__subcategoryContainer__GhtMx>div>:nth-child(2) .MuiButtonBase-root")
    protected List<WebElement> readData;
    @FindBy(css = ".CheckboxTableData-module__subcategoryContainer__GhtMx>div>:nth-child(3) .MuiButtonBase-root")
    protected List<WebElement> editData;
    @FindBy(css = ".CheckboxTableData-module__subcategoryContainer__GhtMx>div>:nth-child(4) .MuiButtonBase-root")
    protected List<WebElement> createData;
    @FindBy(css = ".CheckboxTableData-module__categoryItemsContainer__1LIBI>:nth-child(1) .MuiButtonBase-root")
    protected List<WebElement> mandateReadFields;
    @FindBy(css = ".CheckboxTableData-module__categoryItemsContainer__1LIBI>:nth-child(2) .MuiButtonBase-root")
    protected List<WebElement> mandateEditFields;
    @FindBy(css = ".CheckboxTableData-module__categoryItemsContainer__1LIBI>:nth-child(3) .MuiButtonBase-root")
    protected List<WebElement> mandateCreateFields;
    @FindBy(css = ".CheckboxTableData-module__items__1BB6- :nth-child(2) span.Mui-checked")
    protected List<WebElement> readDataCheckboxMark;
    @FindBy(css = ".CheckboxTableData-module__items__1BB6- :nth-child(3) span.Mui-checked")
    protected List<WebElement> editDataCheckboxMark;
    @FindBy(css = ".CheckboxTableData-module__items__1BB6- :nth-child(4) span.Mui-checked")
    protected List<WebElement> createDataCheckboxMark;
    @FindBy(css = ".CheckboxTableData-module__items__1BB6- :nth-child(2) span.Mui-checked")
    protected List<WebElement> readDataCheckboxEnable;
    @FindBy(css = ".CheckboxTableData-module__items__1BB6- :nth-child(3) span.Mui-checked")
    protected List<WebElement> editDataCheckboxEnable;
    @FindBy(css = ".CheckboxTableData-module__items__1BB6- :nth-child(4) span.Mui-checked")
    protected List<WebElement> createDataCheckboxEnable;


    @Step("permissions > data  tab choose checkbox from mandate")
    public void chooseCheckboxFromDataMandate(String businessCategory, String permission) {
        waitForPageFinishLoading();
        for (int i = 0; i < mandateObjects.size(); i++) {
            scrollToElement(mandateObjects.get(i));
            if (getText(mandateObjects.get(i)).equals(businessCategory)) {
                switch (permission) {
                    case "READ":
                        scrollToElement(mandateReadFields.get(0));
                        click(mandateReadFields.get(0));
                        break;
                    case "EDIT":
                        scrollToElement(mandateEditFields.get(1));
                        click(mandateEditFields.get(1));
                        break;
                    case "CREATE":
                        scrollToElement(mandateCreateFields.get(2));
                        click(mandateCreateFields.get(2));
                        break;
                }
            }
        }
    }


    @Step("permissions > data tab choose checkbox")
    public void chooseCheckboxFromData(String businessCategory, String permission) {
        waitForPageFinishLoading();
        for (int i = 0; i < dataObjects.size(); i++) {
            if (getText(dataObjects.get(i)).equalsIgnoreCase(businessCategory)) {
                switch (permission) {
                    case "READ":
                        scrollToElement(readData.get(i));
                        clickByJS(readData.get(i));
                        return;
                    case "EDIT":
                        scrollToElement(editData.get(i));
                        clickByJS(editData.get(i));
                        return;
                    case "CREATE":
                        scrollToElement(createData.get(i));
                        clickByJS(createData.get(i));
                        return;
                }
            }
        }
    }


    @Step("Permission data - check if checkbox is marked")
    public boolean dataCheckboxIsMark(String businessCategory, String permission) {
        waitForPageFinishLoading();
        try {
            for (int i = 0; i < dataObjects.size(); i++) {
                if (getText(dataObjects.get(i)).equalsIgnoreCase(businessCategory)) {
                    switch (permission) {
                        case "READ":
                            bol = isDisplayed(readDataCheckboxMark.get(0));
                            break;
                        case "EDIT":
                            bol = isDisplayed(editDataCheckboxMark.get(0));
                            break;
                        case "CREATE":
                            bol = isDisplayed(createDataCheckboxMark.get(0));
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

    @Step("Permission data - check if checkbox is enable")
    public boolean dataCheckboxIsEnable(String businessCategory, String permission) {
        waitForPageFinishLoading();
        try {
            for (int i = 0; i < dataObjects.size(); i++) {
                //            System.out.println("the object= " +getText(dataObjects.get(i)));
                if (getText(dataObjects.get(i)).equalsIgnoreCase(businessCategory)) {
                    switch (permission) {
                        case "READ":
                            bol = isEnabled(readDataCheckboxEnable.get(i));
                            System.out.println(bol);
                            break;
                        case "EDIT":
                            bol = isEnabled(editDataCheckboxEnable.get(i));
                            break;
                        case "CREATE":
                            bol = isEnabled(createDataCheckboxEnable.get(i));
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
