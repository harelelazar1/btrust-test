package btrust.btrustOne.client.entity.pagesObject.CreateEntity;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreateEntityLayoutButtonsPage extends BasePage {
    public CreateEntityLayoutButtonsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".BottomStickyActions-module__wrapper__1b1UJ > button")
    protected List<WebElement> buttonsList;


    @Step("Click on button")
    public void clickOnButton(String button) {
        waitForList(buttonsList);
        for (WebElement el : buttonsList) {
            if (getText(el).equalsIgnoreCase(button)) {
                scrollToElement(el);
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }
}