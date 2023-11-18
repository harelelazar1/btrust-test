package btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory;

import btrust.BasePage;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BusinessCategoryPage extends BasePage {
    public BusinessCategoryPage(WebDriver driver) {
        super(driver);
    }

    protected String content;
    protected boolean bol;

    @FindBy(css = ".TopBar-module__wrapper__1kXP9 > .TopBar-module__title__2UitW")
    protected WebElement entityName;
    @FindBy(css = ".TopBar-module__title__2UitW > button")
    protected WebElement editBusinessCategoryButton;
    @FindBy(css = ".TopBar-module__actions__j0g00 > [type='button']")
    protected WebElement openBusinessCategoryMenu;
    @FindBy(css = "ul[role='menu'] > li")
    protected List<WebElement> businessCategoryMenu;
    @FindBy(css = ".BottomActions-module__wrapper__2WHB2 > button:nth-child(1)")
    protected WebElement addNewButton;
    @FindBy(css = ".BottomActions-module__wrapper__2WHB2 > button:nth-child(2)")
    protected WebElement saveChangeButton;
    @FindBy(css = ".NewFormButton-module__container__2pRiP > button:nth-child(1)")
    protected WebElement assignANewFormButton;
    @FindBy(css = ".MuiTabs-flexContainer > button")
    protected List<WebElement> tabList;
    @FindBy(css = ".LeftTabs-module__container__22eCK > li")
    protected List<WebElement> contentsList;
    @FindBy(css = ".Title-module__container__2ovyt > .Title-module__title__3WfvS")
    protected WebElement tabTitle;
    @FindBy(css = ".Title-module__container__2ovyt > :nth-child(2)")
    protected WebElement contentType;
    @FindBy(css = ".InputSearch-module__inputItem__2Oj7R > input")
    protected WebElement searchField;
    @FindBy(css = ".Breadcrumbs-module__breadcrumbs__3ywP3 .Breadcrumbs-module__bread-page-pointer__lLgMH")
    protected WebElement entitiesManagementBreadcrumb;
    @FindBy(css = ".TopBar-module__inputItem__i9PjV > input")
    protected WebElement editBusinessCategoryInput;
    @FindBy(css = ".TopBar-module__withSave__glVSW > .TopBar-module__closeIcon__mKl10")
    protected WebElement closeEditBusinessCategoryNameButton;
    @FindBy(css = "button.TopBar-module__saveBtn__15hy5")
    protected WebElement saveButton;
    @FindBy(css = ".BottomActions-module__wrapper__2WHB2 > button")
    protected List<WebElement> buttonList;
    @FindBy(css = ".NewFormButton-module__container__2pRiP > button")
    protected WebElement buttonTab;
    @FindBy(css = ".SettingsModules-module__container__DO_dx .Breadcrumbs-module__bread-page-pointer__lLgMH")
    protected WebElement linkBack;




    @Step("click on Link Back")
    public void clickLinkBack() {
        waitForPageFinishLoading();
        scrollToElement(linkBack);
        click(linkBack);
    }


    @Step("Click on the button")
    public boolean clickButtons(String button) {
        waitForPageFinishLoading();
        scrollToElement(buttonList.get(0));
        try {
            for (WebElement el : buttonList) {
                scrollToElement(el);
                if (getText(el).equalsIgnoreCase(button)) {
                    if (isEnabled(el)) {
                        waitForElementToBeClickable(el);
                        click(el);
                        return true;
                    } else break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("check if buttons display")
    public boolean checkButtonsDisplay(String tabName, String button) {
        waitForPageFinishLoading();
        try {
            switch (tabName) {
                case "all tabs": {
                    for (WebElement el : buttonList) {
                        scrollToElement(el);
                        if (getText(el).equalsIgnoreCase(button)) {
                            if (isEnabled(el)) {
                                waitForElementToBeClickable(el);
                                return true;
                            } else
                                break;
                        }
                    }
                }

                case "Link Documents tab": {
                    if (getText(buttonTab).equalsIgnoreCase(button)) {
                        if (isEnabled(buttonTab)) {
                            waitForElementToBeClickable(buttonTab);
                            return true;
                        } else break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("Return the text of entity name")
    public String entityName() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(entityName);
        scrollToElement(entityName);
        waitForElementToBeVisibility(entityName);
        return getText(entityName);
    }

    @Step("Choose from the business category menu")
    public void businessCategoryMenu(String option) {
        waitForPageFinishLoading();
        scrollToElement(openBusinessCategoryMenu);
        waitForElementToBeClickable(openBusinessCategoryMenu);
        click(openBusinessCategoryMenu);
        waitForList(businessCategoryMenu);
        for (WebElement el : businessCategoryMenu) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(option)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("check If Business Category Menu Display")
    public boolean checkIfBusinessCategoryMenuDisplay() {
        try {
            scrollToElement(openBusinessCategoryMenu);
            waitForElementToBeVisibility(openBusinessCategoryMenu);
            if (isDisplayed(openBusinessCategoryMenu)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("Click on the button")
    public boolean clickOnButton(String button) {
        bol = false;
        waitForPageFinishLoading();
        try {
            switch (button) {
                case "Save": {
                    if (isEnabled(saveChangeButton)) {
                        sleep(2000);
                        scrollToElement(saveChangeButton);
                        clickByJS(saveChangeButton);
                        bol = true;
                    }
                    break;
                }
                case "Add new business relationship":
                case "Add New Field": {
                    if (isEnabled(addNewButton)) {
                        scrollToElement(addNewButton);
                        click(addNewButton);
                        bol = true;
                    }
                    break;
                }
                case "Assign a new form": {
                    if (isEnabled(assignANewFormButton)) {
                        scrollToElement(assignANewFormButton);
                        click(assignANewFormButton);
                        bol = true;
                    }
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            bol = false;
        }
        return bol;
    }

    @Step("Choose from tab list")
    public void tabList(String tab) {
        waitForPageFinishLoading();
        waitForElementToBeClickable(tabList.get(0));
        for (WebElement el : tabList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(tab)) {
                click(el);
                break;
            }
        }
        sleep(1000);
    }

    @Step("Choose from contents list")
    public void contentsList(String option) {
        waitForPageFinishLoading();
        for (WebElement el : contentsList) {
            scrollToElement(el);
            if (getText(el).contains(option)) {
                clickByJS(el);
                break;
            }
        }
    }

    @Step("Return the text of content title")
    public String contentTitle() {
        waitForElementToBeVisibility(tabTitle);
        scrollToElement(tabTitle);
        return getText(tabTitle);
    }

    @Step("Return the text of con tent sub title")
    public String contentType() {
        scrollToElement(contentType);
        return getText(contentType);
    }

    @Step("Type in search field")
    public void searchField(String text) {
        scrollToElement(searchField);
        fillText(searchField, text);
    }

    @Step("Click on entities management breaccrumbs")
    public void clickOnEntitiesManagementBreadcrumb() {
        waitForElementToBeVisibility(entitiesManagementBreadcrumb);
        waitForElementToBeClickable(entitiesManagementBreadcrumb);
        click(entitiesManagementBreadcrumb);
    }

    @Step("Click on edit business category button")
    public void clickOnEditBusinessCategoryButton() {
        waitForElementToBeClickable(editBusinessCategoryButton);
        click(editBusinessCategoryButton);
    }

    @Step("Check if edit business category button disply")
    public boolean checkEditBusinessCategoryButtonDisplay() {
        try {
            waitForPageFinishLoading();
            if (isDisplayed(editBusinessCategoryButton))
                return true;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }

    @Step("Rename business category name")
    public void renameBusinessCategoryName(String type, String name) {
        waitForElementToBeClickable(editBusinessCategoryInput);
        fillText(editBusinessCategoryInput, name);
        switch (type) {
            case "enter":
                editBusinessCategoryInput.sendKeys(Keys.ENTER);
                break;
            case "save button":
                waitForElementToBeClickable(saveButton);
                click(saveButton);
                break;
        }
        waitForTextToBeInElement(entityName, name);
    }

    @Step("Click on close edit the business category name")
    public void closeEditBusinessCategoryName() {
        waitForElementToBeClickable(closeEditBusinessCategoryNameButton);
        click(closeEditBusinessCategoryNameButton);
    }
}