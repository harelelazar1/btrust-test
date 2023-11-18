package btrust.btrustOne.admin.workflowManagement.mobileInteraction.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MobileInteractionPage extends BasePage {
    public MobileInteractionPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = "div.SettingsHeaderOneLine-module__title__2CuM5")
    protected WebElement mobileInteractionTitle;
    @FindBy(css = ".SettingsHeaderOneLine-module__top__Kl1Gb button")
    protected WebElement addNewMobileFlowButton;
    @FindBy(css = "div.InputSearch-module__inputItem__2Oj7R input")
    protected WebElement searchField;
    @FindBy(css = "div .Table-module__row__5x_gY :nth-child(2)")
    protected List<WebElement> columnProcessName;
    @FindBy(css = "div .select-button.blue-false.undefined")
    protected WebElement filterByStatus;
    @FindBy(css = ".items-container .text")
    protected List<WebElement> statusName;
    @FindBy(css = ".items-container [role='button']")
    protected List<WebElement> statusButton;
    @FindBy(css = ".selected-elements.false li")
    protected WebElement selectedStatus;


    @Step("Check the title of the page")
    public String pageTitle() {
        waitForElementToBeVisibility(mobileInteractionTitle);
        scrollToElement(mobileInteractionTitle);
        return getText(mobileInteractionTitle);
    }


    @Step("search name in table and choose")
    public void searchNameAndClick(String name) {
        //      waitForPageFinishLoading();
        scrollToElement(searchField);
        waitForElementToBeClickable(searchField);
        fillText(searchField, name);
        waitForList(columnProcessName);
        for (WebElement el : columnProcessName) {
            waitForElementToBeVisibility(el);
            scrollToElement(el);
            if (getText(el).contains(name)) {
                scrollToElement(el);
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }


    @Step("search name in table and return answer")
    public Boolean searchNameAndReturnAnswer(String name) {
        try {
            //      waitForPageFinishLoading();
            scrollToElement(searchField);
            fillText(searchField, name);
            sleep(2000);
            for (WebElement el : columnProcessName) {
                waitForElementToBeVisibility(el);
                scrollToElement(el);
                if (getText(el).contains(name)) {
                    scrollToElement(el);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return false;
    }


    @Step("filter Mobile Flows By Status")
    public void showingMobileFlowsByStatus(String statusFlowName) {
        try {
            scrollToElement(filterByStatus);
            click(filterByStatus);
            sleep(2000);
            for (int i = 0; i < statusName.size(); i++) {
                waitForElementToBeVisibility(statusName.get(i));
                scrollToElement(statusName.get(i));
                if (getText(statusName.get(i)).equalsIgnoreCase(statusFlowName)) {
                    scrollToElement(statusButton.get(i));
                    waitForElementToBeClickable(statusButton.get(i));
                    if (!statusButton.get(i).isSelected()) {
                        click(statusButton.get(i));
                    }
                    click(filterByStatus);
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
    }


    @Step("Return selected status")
    public String returnSelectedStatus() {
        scrollToElement(selectedStatus);
        waitForElementToBeVisibility(selectedStatus);
        return getText(selectedStatus);
    }

}
