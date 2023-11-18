package btrust.btrustOne.client.triggers.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PendingTriggerPage extends BasePage {

    public PendingTriggerPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".MuiTableBody-root >.MuiTableRow-root :nth-child(2)")
    protected List<WebElement> pendingTriggersList;
    @FindBy(css = ".topbar-triggers>.wrapper")
    protected WebElement pageTitle;


    @Step("Page Title")
    public String pageTitle() {
        waitForPageFinishLoading();
        return getText(pageTitle);
    }

    @Step("Choose First trigger from pending triggers list")
    public void chooseFirstTriggerFromPendingList() {
        waitForPageFinishLoading();
        for (WebElement el : pendingTriggersList) {
            scrollToElement(el);
            waitForElementToBeClickable(el);
            click(el);
            break;
        }
    }


    @Step("Search and choose trigger in pending triggers list")
    public void chooseTriggerNameFromList(String triggerName) {
        waitForPageFinishLoading();
        for (WebElement el : pendingTriggersList) {
            if (getText(el).equalsIgnoreCase(triggerName)) {
                scrollToElement(el);
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }


}
