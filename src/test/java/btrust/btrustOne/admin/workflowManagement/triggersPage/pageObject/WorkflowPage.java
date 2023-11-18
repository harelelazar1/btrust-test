package btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class WorkflowPage extends BasePage {

    public WorkflowPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = ".NewTriggerWorkflow-module__formContainer__229cG .DocumentFormTitle-module__title__OrQX-")
    protected WebElement pageTitle;
    @FindBy(css = ".NewTriggerWorkflow-module__inputItem__2ARhC >:nth-child(1)")
    protected List<WebElement> titleFieldNameList;
    @FindBy(css = ".NewTriggerWorkflow-module__inputItem__2ARhC >:nth-child(2)")
    protected List<WebElement> FieldName;
    @FindBy(css = ".default__menu-list.css-1m0ufzk>.default__option")
    protected List<WebElement> chooseNameFromList;
    @FindBy(css = "[class] [tabindex='-1']")
    protected List<WebElement> workflowList;

    @FindBy(css = ".BottomStickyActions-module__wrapper__1b1UJ> button[type='button']")
    protected List<WebElement> buttonList;

    @Step("Page Title")
    public String pageTitle() {
        waitForPageFinishLoading();
        return getText(pageTitle);
    }


    @Step("click on Button")
    public void clickOnButton(String buttonName) {
        waitForPageFinishLoading();
        waitForList(buttonList);
        for (int i = 0; i < buttonList.size(); i++) {
            scrollToElement(buttonList.get(i));
            if (getText(buttonList.get(i)).equalsIgnoreCase(buttonName)) {
                scrollToElement(buttonList.get(i));
                clickByJS(buttonList.get(i));
                break;
            }
        }
    }

    @Step("Choose from select list the fields")
    public void chooseFromSelectList(String titleName, String NameFromList) {
        int titleIndex=-1;
        waitForPageFinishLoading();
        for (int i = 0; i < titleFieldNameList.size(); i++) {
            if (getText(titleFieldNameList.get(i)).equalsIgnoreCase(titleName)) {
                titleIndex=i;
            }
        }
        if (titleIndex==-1){
            Assert.fail("title "+titleName+" not found on page");
            return;
        }
        scrollToElement(titleFieldNameList.get(titleIndex));
            scrollToElement(FieldName.get(titleIndex));
            waitForElementToBeClickable(FieldName.get(titleIndex));
            click(FieldName.get(titleIndex));
        for (WebElement el : chooseNameFromList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(NameFromList)) {
                scrollToElement(el);
                click(el);
                sleep(2000);
                return;
            }
        }
        Assert.fail("name "+NameFromList+" not found on list "+titleName);
    }


    @Step("Choose from select list - workflow field")
    public void chooseFromWorkflow(String titleName, String NameFromList) {
        waitForPageFinishLoading();
        int i = 3;
        if (getText(titleFieldNameList.get(i)).equalsIgnoreCase(titleName)) {
            scrollToElement(FieldName.get(i));
            waitForElementToBeClickable(FieldName.get(i));
            click(FieldName.get(i));
            System.out.println(workflowList.size());
            for (WebElement el : workflowList) {
                scrollToElement(el);
                if (getText(el).equalsIgnoreCase(NameFromList)) {
                    waitForElementToBeVisibility(el);
                    click(el);
                    return;
                }
            }
        }
    }


    @Step("Check Navigation Buttons")
    public boolean checkNavigationButtons(String buttonName) {
        boolean bol = false;
        waitForPageFinishLoading();
        waitForList(buttonList);
        for (int i = 0; i < buttonList.size(); i++) {
            scrollToElement(buttonList.get(i));
            if (getText(buttonList.get(i)).equalsIgnoreCase(buttonName)) {
                bol = isEnabled(buttonList.get(i));
                return bol;
            }
        }
        return bol;
    }
}





