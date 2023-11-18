package btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EntitiesManagerPage extends BasePage {
    public EntitiesManagerPage(WebDriver driver) {
        super(driver);
    }

    boolean bol;
    protected String entityName;
    @FindBy(css = ".SettingsTitle-module__container__km6xM > .SettingsTitle-module__text__1VtAb")
    protected WebElement entitiesManagerTitle;
    @FindBy(css = ".EntitiesManagement-module__categoriesContainer__2AyxO .Category-module__mainItem__mWdxs")
    protected List<WebElement> businessCategoryList;
    @FindBy(css = ".EntitiesManagement-module__categoriesContainer__2AyxO .Category-module__addBlock__2idK-")
    protected List<WebElement> addNewBusinessCategoryButtonList;
    @FindBy(css = ".Category-module__inputItem__3vJCz > input")
    protected WebElement newEntityInput;
    @FindBy(css = ".Category-module__withSave__3bz1U > .Category-module__closeIcon__3u2bs")
    protected WebElement closeInputButton;
    @FindBy(css = ".EntitiesManagement-module__categoriesContainer__2AyxO > :nth-child(1) .Category-module__list__MACV3 > [role='tabpanel']")
    protected List<WebElement> organizationEntitiesList;
    @FindBy(css = ".EntitiesManagement-module__categoriesContainer__2AyxO > :nth-child(2) .Category-module__list__MACV3 > [role='tabpanel']")
    protected List<WebElement> individualPersonEntitiesList;
    @FindBy(css = ".EntitiesManagement-module__categoriesContainer__2AyxO > :nth-child(3) .Category-module__list__MACV3 > [role='tabpanel']")
    protected List<WebElement> mandateEntitiesList;
    @FindBy(css = ".Category-module__item__37sNt > li.Category-module__name__2sTkG")
    protected List<WebElement> entitiesNameList;
    @FindBy(css = ".Category-module__list__MACV3 > .Category-module__item__37sNt")
    protected List<WebElement> entitiesList;


    @Step("Check If New Business Category Button Display")
    public boolean checkIfNewBusinessCategoryButtonDisplay() {
        try {
            waitForPageFinishLoading();
            for (WebElement el : addNewBusinessCategoryButtonList) {
                if (isDisplayed(el)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("Return the text of entities management page")
    public String entitiesManagerTitle() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(entitiesManagerTitle);
        return getText(entitiesManagerTitle);
    }

    @Step("Click on add new business category button")
    public void clickOnAddNewBusinessCategoryButton(String businessCategoryName) {
        waitForList(businessCategoryList);
        for (int i = 0; i < businessCategoryList.size(); i++) {
            scrollToElement(businessCategoryList.get(i));
            if (getText(businessCategoryList.get(i)).contains(businessCategoryName)) {
                scrollToElement(addNewBusinessCategoryButtonList.get(i));
                waitForElementToBeClickable(addNewBusinessCategoryButtonList.get(i));
                click(addNewBusinessCategoryButtonList.get(i));
                break;
            }
        }
    }

    @Step("Type in new entity input")
    public void newEntityInput(String text) {
        scrollToElement(newEntityInput);
        waitForElementToBeVisibility(newEntityInput);
        fillText(newEntityInput, text);
        newEntityInput.sendKeys(Keys.ENTER);
    }

    @Step("Click on close input button")
    public void closeInputButton() {
        waitForElementToBeClickable(closeInputButton);
        click(closeInputButton);
    }

    @Step("Choose businessCategory from the organization entities list")
    public void chooseOrganizationBusinessCategoryList(String businessCategory) {
        waitForPageFinishLoading();
        waitForElementToBeClickable(organizationEntitiesList.get(0));
        for (WebElement el : organizationEntitiesList) {
            if (getText(el).contains(businessCategory)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Choose businessCategory from the individual person entities list")
    public void individualPersonBusinessCategoryList(String businessCategory) {
        waitForPageFinishLoading();
        waitForList(individualPersonEntitiesList);
        for (WebElement el : individualPersonEntitiesList) {
            if (getText(el).equalsIgnoreCase(businessCategory)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Choose businessCategory from the mandate entities list")
    public void mandateBusinessCategoryList(String businessCategory) {
        waitForPageFinishLoading();
        waitForList(mandateEntitiesList);
        for (WebElement el : mandateEntitiesList) {
            if (getText(el).equalsIgnoreCase(businessCategory)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Run on all the business category list and choose one of them if it is displayed")
    public boolean chooseBusinessCategory(String isDisplayed, String entity) {
        bol = false;
        waitForPageFinishLoading();
        try {
            for (int i = 0; i < entitiesNameList.size(); i++) {
                scrollToElement(entitiesNameList.get(i));
                if (getText(entitiesNameList.get(i)).contains(entity)) {
                    if (isDisplayed(entitiesNameList.get(i))) {
                        scrollToElement(entitiesList.get(i));
                        click(entitiesList.get(i));
                        bol = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            bol = false;
        }
        return bol;
    }
}