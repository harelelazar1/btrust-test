package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ClientConfigurationPage extends BasePage {

    public ClientConfigurationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".MuiPaper-rounded .item [type='text']")
    protected List<WebElement> clientConfigurationFields;
    @FindBy(css = ".settings-main-container .title > span")
    protected WebElement clientConfigurationTitle;
    @FindBy(css = ".config-items [role='button']")
    protected List<WebElement> itemsList;
    @FindBy(css = ".config-items > button")
    protected WebElement saveButton;
    /*
    Colors and Styles
     */
    @FindBy(css = ".config-items > :nth-child(1) .MuiExpansionPanelDetails-root .item > input")
    protected List<WebElement> colorsAndStyleFieldsList;
    @FindBy(css = ".config-items > :nth-child(1) .MuiExpansionPanelDetails-root .item > .key")
    protected List<WebElement> colorsAndStyleKeysList;
    /*
    Translations
     */
    @FindBy(css = ".config-items > :nth-child(2) .MuiExpansionPanelDetails-root .item > input")
    protected List<WebElement> translationsFieldsList;
    @FindBy(css = ".config-items > :nth-child(2) .MuiExpansionPanelDetails-root .item > .key")
    protected List<WebElement> translationsKeysList;
    /*
    Others
     */
    @FindBy(css = ".MuiCollapse-entered .item [type]")
    protected List<WebElement> othersFieldsList;
    @FindBy(css = ".config-items > :nth-child(3) .MuiExpansionPanelDetails-root .item > .key")
    protected List<WebElement> othersKeysList;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

    @Step("Check if clientConfigurationTitleIsDisplayed")
    public boolean clientConfigurationTitleIsDisplayed(String title) {
        try {
            waitForTextToBeInElement(clientConfigurationTitle, title);
            isDisplayed(clientConfigurationTitle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Open item from the list")
    public void openItem(String chooseFromItemList) {
        waitForList(itemsList);
        for (WebElement el : itemsList) {
            scrollToElement(el);
            waitForElementToBeClickable(el);
            if (getText(el).equalsIgnoreCase(chooseFromItemList)) {
                click(el);
                break;
            }
        }
    }

    @Step("Edit colors and style settings")
    public void editColorsAndStyle(String colorsAndStyleKey, String colorsAndStyleValue) {
        waitForList(colorsAndStyleKeysList);
        waitForList(colorsAndStyleFieldsList);
        for (int i = 0; i < colorsAndStyleKeysList.size(); i++) {
            WebElement colorsAndStyle = colorsAndStyleKeysList.get(i);
            String key = getText(colorsAndStyle);
            if (key.contains(colorsAndStyleKey)) {
                WebElement field = colorsAndStyleFieldsList.get(i);
                waitForElementToBeClickable(field);
                fillText(field, colorsAndStyleValue);
                break;
            }
        }
    }

    @Step("Edit translations settings")
    public void editTranslationsSettings(String translationsKey, String translationsValue) {
        waitForList(translationsKeysList);
        waitForList(translationsFieldsList);
        for (int i = 0; i < translationsKeysList.size(); i++) {
            WebElement translations = translationsKeysList.get(i);
            String key = getText(translations);
            if (key.contains(translationsKey)) {
                WebElement field = translationsFieldsList.get(i);
                scrollToElement(field);
                waitForElementToBeClickable(field);
                fillText(field, translationsValue);
                break;
            }
        }
    }

    @Step("Edit others settings")
    public void editOthersSettings(String othersKey, String othersValue) {
        waitForList(othersKeysList);
        for (int i = 2; i < othersKeysList.size(); i++) {
            WebElement others = othersKeysList.get(i);
            String key = getText(others);
            if (key.contains(othersKey)) {
                break;
            }
        }
        for (int i = 0; i < othersKeysList.size(); i++) {
            WebElement others = othersKeysList.get(i);
            String key = getText(others);
            if (key.contains(othersKey)) {
                WebElement field = othersFieldsList.get(i);
                waitForElementToBeClickable(field);
                fillText(field, othersValue);
                break;
            }
        }
    }

    @Step("Click on saveButton")
    public void saveButton() {
        waitForElementToBeClickable(saveButton);
        click(saveButton);
    }

    @Step("Check if translationsFieldsList is appear on the screen")
    public List<WebElement> getTranslationsFieldsList() {
        for (WebElement el : translationsFieldsList) {
            waitForElementToBeVisibility(el);
            scrollToElement(el);
        }
        wait.until(ExpectedConditions.visibilityOfAllElements(translationsKeysList));
        return translationsFieldsList;
    }

    @Step("Reset each field in the client configuration fields list")
    public void resetAllFields() {
        for (WebElement el : clientConfigurationFields) {
            waitForElementToBeVisibility(el);
            clear(el);
        }
    }

    @Step("Edit a specific section of values in the client configurations translations")
    public void editSpecificTranslationsValues(String sectionToEdit, String textToAdd) {
        waitForList(translationsKeysList);
        waitForList(translationsFieldsList);
        for (WebElement el : translationsKeysList) {
            waitForElementToBeVisibility(el);
            if (getText(el).startsWith(sectionToEdit)) {
                editTranslationsSettings(getText(el), getText(el) + textToAdd);
                saveButton();
            }
        }
    }
}
