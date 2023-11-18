package btrust.btrustOne.admin.dataModule.dataMapper.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DataMapperPage extends BasePage {
    public DataMapperPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".SettingsHeader-module__wrapper__2OOUN > .SettingsHeader-module__title__acJ6f")
    protected WebElement dataMapperTitle;
    @FindBy(css = ".SettingsHeader-module__wrapper__2OOUN > .SettingsHeader-module__description__YsOmA")
    protected WebElement dataMapperDescription;
    @FindBy(css = ".SettingsHeader-module__title__acJ6f > button")
    protected WebElement addDataMapperButton;
    @FindBy(css = "input[placeholder='Type field name or field label']")
    protected WebElement searchFiled;
    @FindBy(css = ".TableHeader-module__conteiner__2lTtw > [role='tabpanel']>span")
    protected List<WebElement> dataMapperTableTitleList;
    @FindBy(css = ".Table-module__row__2lNiO > .Table-module__name__15_5Q")
    protected List<WebElement> dbFieldNameList;
    @FindBy(css = ".Table-module__row__2lNiO > .Table-module__item__14psA")
    protected List<WebElement> dataMapperValueList;
    @FindBy(css = ".EditField-module__edit__3I8Ek[type=button]")
    protected WebElement labelFieldTitleEditButton;
    @FindBy(css = ".DataField-module__actions__9PBt9 > button[type='button']")
    protected WebElement saveButton;
    @FindBy(css = ".SourcesTable-module__row__2GzUD [tabindex]")
    protected List<WebElement> editAndDeleteDataSourceButton;
    @FindBy(css = ".SettingsHeader-module__title__acJ6f [type=button]")
    protected WebElement addNewFieldOfDataMapperButton;
    @FindBy(css = ".MuiSvgIcon-root.MuiSvgIcon-fontSizeLarge")
    protected WebElement exitButton;



    @Step("Click on exist button")
    public void clickOnExitButton() {
        waitForElementToBeClickable(exitButton);
        click(exitButton);
    }


    @Step("Return the data mapper title")
    public String dataMapperTitle() {
        waitForPageFinishLoading();
        scrollToElement(dataMapperTitle);
        waitForElementToBeVisibility(dataMapperTitle);
        return getText(dataMapperTitle);
    }

    @Step("Return the data mapper description")
    public String dataMapperDescription() {
        waitForElementToBeVisibility(dataMapperDescription);
        return getText(dataMapperDescription);
    }

    @Step("Click on add new data mapper button")
    public void clickOnAddButton() {
        waitForElementToBeClickable(addDataMapperButton);
        click(addDataMapperButton);
    }

    @Step("Type in search field")
    public void dataMapperSearchBar(String text) {
        sleep(3000);
        scrollToElement(searchFiled);
        waitForElementToBeClickable(searchFiled);
        fillText(searchFiled, text);
    }

    @Step("check if save button display")
    public boolean checkIfSaveButtonDisplay() {
        try {
            waitForPageFinishLoading();
            if (isEnabled(saveButton)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }
        return false;
    }

    @Step("check if add New Field Of Data Mapper Button - 'plus' icon display")
    public boolean checkIfAddNewFieldOfDataMapperButtonDisplay() {
        try {
            waitForPageFinishLoading();
            if (isDisplayed(addNewFieldOfDataMapperButton)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Step("Choose db field name from the list")
    public void chooseDbFieldName(String title, String fieldName) {
        waitForPageFinishLoading();
        for (int i = 0; i < dataMapperTableTitleList.size(); i++) {
            //      scrollToElement(dataMapperTableTitleList.get(i));
            if (getText(dataMapperTableTitleList.get(i)).equalsIgnoreCase(title)) {
                for (WebElement el : dbFieldNameList) {
                    scrollToElement(el);
                    if (getText(el).equalsIgnoreCase(fieldName)) {
                        click(el);
                        break;
                    }
                }
            }
        }
    }


    @Step("Check Db Field Name Display in the List ")
    public boolean checkDbFieldNameDisplay(String title, String fieldName) {
        try {
            waitForPageFinishLoading();
            for (int i = 0; i < dataMapperTableTitleList.size(); i++) {
                scrollToElement(dataMapperTableTitleList.get(i));
                if (getText(dataMapperTableTitleList.get(i)).equalsIgnoreCase(title)) {
                    for (WebElement el : dbFieldNameList) {
                        scrollToElement(el);
                        if (getText(el).equalsIgnoreCase(fieldName)) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        return false;
    }


    @Step("check if edit label button display")
    public boolean checkIfEditFieldLabelButtonDisplay() {
        try {
            waitForElementToBeClickable(labelFieldTitleEditButton);
            scrollToElement(labelFieldTitleEditButton);
            if (isEnabled(labelFieldTitleEditButton)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Step("check if edit or delete data source display")
    public boolean checkIfEditAndDeleteDataSourceButtonDisplay() {
        try {
            waitForPageFinishLoading();
            mouseHoverOnElement(editAndDeleteDataSourceButton.get(editAndDeleteDataSourceButton.size() - 1));
            if (isDisplayed(editAndDeleteDataSourceButton.get(editAndDeleteDataSourceButton.size() - 1))) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }
}