package btrust.btrustOne.admin.dataModule.dataMapper.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FieldPage extends BasePage {

    public FieldPage(WebDriver driver) {
        super(driver);
    }

    String value;

    @FindBy(css = ".PopupTitle-module__text__3pjTU > .PopupTitle-module__icon__hcL5f")
    protected WebElement closeButton;
    @FindBy(css = ".PopupTitle-module__title__Khvfk > .PopupTitle-module__text__3pjTU")
    protected WebElement filedTitle;
    @FindBy(css = ".DataField-module__fieldsContainer__rLiAU  .DataFieldFormEditable-module__label__rknhL")
    protected List<WebElement> inputTitleList;
    @FindBy(css = ".DataFieldFormEditable-module__inputItem__2o0jA > input[name]")
    protected List<WebElement> inputList;
    @FindBy(css = ".DataFieldFormEditable-module__inputItem__2o0jA .default-select")
    protected List<WebElement> selectList;
    @FindBy(css = ".default__menu-list > .default__option")
    protected List<WebElement> optionsList;
    @FindBy(css = ".DataFieldFormEditable-module__inputItem__2o0jA > textarea[name]")
    protected List<WebElement> textAreaList;
    @FindBy(css = ".DataField-module__container__2puUL button[type='button']")
    protected List<WebElement> buttonsList;

    @FindBy(css = ".EditField-module__conteiner__HtcVh  > button.EditField-module__edit__3I8Ek")
    protected WebElement editFieldNameButton;
    @FindBy(css = ".DataFieldForm-module__flexList__2Cfg8 .DataFieldForm-module__label__1NAYs")
    protected List<WebElement> exitFiledTitleList;
    @FindBy(css = ".DataFieldForm-module__flexList__2Cfg8  .DataFieldForm-module__value__TK4IL")
    protected List<WebElement> filedDataList;
    @FindBy(css = ".SourcesTable-module__sources__28Qjg button")
    protected WebElement addInputSource;
    @FindBy(css = ".SourcesTable-module__header__2POg6>.SourcesTable-module__headerItem__1yxI8")
    protected List<WebElement> sourceTitleName;
    @FindBy(css = ".SourcesTable-module__rows__2EEac .SourcesTable-module__rowItem__1sQEN .isWhite-true")
    protected List<WebElement> sourceSelectField;


    @Step("Fill source data for the new field in data mapper")
    public void chooseSourceData(String fieldTitleName, String valueFromList) {
        try {
            waitForPageFinishLoading();
            for (int i = 0; i < sourceTitleName.size(); i++) {
                scrollToElement(sourceTitleName.get(i));
                if (getText(sourceTitleName.get(i)).equalsIgnoreCase(fieldTitleName)) {
                    waitForElementToBeClickable(sourceTitleName.get(i));
                    scrollToElement(sourceTitleName.get(i));
                    click(sourceSelectField.get(i));
                    chooseValueFromList(valueFromList);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }


    @Step("Select text from data")
    public void chooseValueFromList(String value) {
        waitForPageFinishLoading();
        WebElement el = driver.findElement(By.xpath(String.format("//*[text()='%s']", value)));
        click(el);
    }

    @Step("Define input output source")
    public void inputOutputSource() {
        scrollToElement(addInputSource);
        click(addInputSource);
    }

    @Step("Add more input source button")
    public void addMoreInputSourceButton() {
        scrollToElement(addInputSource);
        click(addInputSource);
    }

    @Step("Return the text of the filed title")
    public String filedTitle() {
        waitForElementToBeVisibility(filedTitle);
        return getText(filedTitle);
    }

    @Step("Click on close button")
    public void closeButton() {
        scrollToElement(closeButton);
        click(closeButton);
    }

    @Step("Configure the field")
    public void fillDataFiled(String title, String value, String option) {
        for (WebElement el : inputTitleList) {
            scrollToElement(el);
            switch (title) {
                case "DB Field Name": {
                    fillText(inputList.get(0), value);
                    break;
                }
                case "DB Field Type": {
                    waitForElementToBeClickable(selectList.get(0));
                    click(selectList.get(0));
                    waitForList(optionsList);
                    for (WebElement element : optionsList) {
                        scrollToElement(element);
                        if (getText(element).equals(option)) {
                            waitForElementToBeClickable(element);
                            click(element);
                            break;
                        }
                    }
                    break;
                }
                case "Field Label": {
                    fillText(inputList.get(1), value);
                    break;
                }
                case "Entity Type": {
                    waitForElementToBeClickable(selectList.get(1));
                    click(selectList.get(1));
                    for (WebElement element : optionsList) {
                        scrollToElement(element);
                        if (getText(element).equals(option)) {
                            click(element);
                            break;
                        }
                    }
                    break;
                }
                case "Business Category": {
                    waitForElementToBeClickable(selectList.get(2));
                    click(selectList.get(2));
                    for (WebElement element : optionsList) {
                        scrollToElement(element);
                        if (getText(element).equals(option)) {
                            click(element);
                            break;
                        }
                    }
                    break;
                }
                case "Description": {
                    fillText(textAreaList.get(0), value);
                    break;
                }
            }
            break;
        }
    }

    @Step("Click on button")
    public void clickOnButton(String button) {
        try {
            for (WebElement el : buttonsList) {
                scrollToElement(el);
                if (getText(el).equals(button)) {
                    click(el);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("The button is not found");
        }
    }

    @Step("Click on edit field name button")
    public void editFieldNameButton() {
        click(editFieldNameButton);
    }

    @Step("Return the data filed")
    public String getDataField(String title) {
        for (int i = 0; i < exitFiledTitleList.size(); i++) {
            scrollToElement(exitFiledTitleList.get(i));
            if (getText(exitFiledTitleList.get(i)).equals(title)) {
                scrollToElement(filedDataList.get(i));
                value = getText(filedDataList.get(i));
                break;
            }
        }
        return value;
    }
}
