package btrust.btrustOne.admin.dataModule.dataMapper.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PopupPage extends BasePage {
    public PopupPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".paper.paddingNone.mandateWidth button")
    protected List<WebElement> popupButtons;


    @Step("Click on button")
    public void clickOnButton(String button) {
        try {
            waitForList(popupButtons);
            for (WebElement el : popupButtons) {
                if (getText(el).equals(button)) {
                    click(el);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("The button is not found");
        }
    }



}
