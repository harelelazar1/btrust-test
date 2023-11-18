package btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class TriggerBuilderPage extends BasePage {

    public TriggerBuilderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".SettingsModules-module__main__2w4Og .SettingsHeader-module__title__acJ6f")
    protected WebElement pageTitle;
    @FindBy(css = ".SettingsHeader-module__rightFilterPosition__2TlUn .AddButton-module__btn__2_wO7")
    protected WebElement addNewTriggerBuilder;
    @FindBy(css = ".Table-module__row__WbLrg :nth-child(2)")
    protected List<WebElement> nameFromTriggerBuilderList;
    @FindBy(css = ".Table-module__row__WbLrg .Table-module__status__wKWHh")
    protected List<WebElement> statusFromTriggerBuilderList;
    @FindBy(css = ".BottomStickyActions-module__wrapper__1b1UJ [type='button']")
    protected List<WebElement> buttons;


    @Step("Page title")
    public String pageTitle() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(pageTitle);
        return getText(pageTitle);
    }

    @Step("Check if add New trigger builder Button display")
    public boolean checkIfNewTriggerBuilderButtonDisplay() {
        try {
            waitForPageFinishLoading();
            if (isDisplayed(addNewTriggerBuilder)) {
                scrollToElement(addNewTriggerBuilder);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("Click add New trigger builder Button")
    public void ClickAddNewTriggerBuilderButton() {
        waitForElementToBeClickable(addNewTriggerBuilder);
        click(addNewTriggerBuilder);
    }


    @Step("Choose trigger Name From List by name + status")
    public void chooseTriggerNameFromList(String triggerName, String status) {
        waitForPageFinishLoading();
        for (int i = 0; i < nameFromTriggerBuilderList.size(); i++) {
            scrollToElement(nameFromTriggerBuilderList.get(i));
            if (getText(nameFromTriggerBuilderList.get(i)).equalsIgnoreCase(triggerName)) {
                if (getText(statusFromTriggerBuilderList.get(i)).equalsIgnoreCase(status)) {
                    waitForElementToBeClickable(statusFromTriggerBuilderList.get(i));
                    click(statusFromTriggerBuilderList.get(i));
                    break;
                }
            }
        }
    }


    @Step("Choose trigger Name From List by name + status")
    public void deleteTriggerNameFromList() {
        waitForPageFinishLoading();
        for (int i = 0; i < nameFromTriggerBuilderList.size(); i++) {
            System.out.println("i: " + i);
            scrollToElement(nameFromTriggerBuilderList.get(i));
            if (getText(nameFromTriggerBuilderList.get(i)).startsWith("QA TEST")) {
                click(statusFromTriggerBuilderList.get(i));
                EditTriggerPage editTriggerPage = new EditTriggerPage(driver);
                editTriggerPage.deleteTrigger("Confirm");
                Assert.assertEquals(editTriggerPage.returnTriggerStatus(), "Deleted");
                editTriggerPage.linkBack();
            } else System.out.println("kaki");
        }
    }


    @Step("Choose the first trigger Name From List")
    public void chooseFirstTriggerNameFromList() {
        waitForPageFinishLoading();
        for (WebElement el : nameFromTriggerBuilderList) {
            scrollToElement(el);
            click(el);
            break;
        }
    }


    @Step("Return Trigger Status from Trigger Builder page ")
    public String returnTriggerStatus(String triggerName) {
        String triggerStatus = null;
        waitForPageFinishLoading();
        for (int i = 0; i < nameFromTriggerBuilderList.size(); i++) {
            scrollToElement(nameFromTriggerBuilderList.get(i));
            if (getText(nameFromTriggerBuilderList.get(i)).equalsIgnoreCase(triggerName)) {
                triggerStatus = getText(statusFromTriggerBuilderList.get(i));
                break;
            }
        }
        return triggerStatus;
    }


    @Step("Check trigger Name in trigger list by name")
    public boolean checkIfTriggerNameDisplayInList(String triggerName) {
        try {
            waitForPageFinishLoading();
            for (WebElement el : nameFromTriggerBuilderList) {
                scrollToElement(el);
                if (getText(el).equalsIgnoreCase(triggerName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return false;
    }

}

