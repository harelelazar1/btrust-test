package btrust.btrustOne.client.mandate.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MandatePage extends BasePage {

    public MandatePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = ".RoundedWhiteBox-module__action__34eaF > button")
    protected WebElement newContactButton;
    @FindBy(css = ".Contacts-module__row__1F2El  .MuiButtonBase-root.MuiButton-root.MuiButton-text")
    protected WebElement contactHamburgerMenuButton;
    @FindBy(css = ".MuiList-root.MuiMenu-list.MuiList-padding .MuiListItem-button")
    protected List<WebElement> chooseFromHamburgerMenu;
    @FindBy(css = ".ContactPopup-module__title__3x6jv [type='button']")
    protected WebElement closePopup;
    @FindBy(css = ".Actions-module__container__2CaxO [type='button']")
    protected List<WebElement> actionInPage;


    @Step("check if 'New Contact' button display in mandate")
    public boolean checkNewContactButtonDisplay() {
        try {
            waitForPageFinishLoading();
            if (isDisplayed(newContactButton)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("check hamburger Menu Button of 'contact information'")
    public boolean checkHamburgerMenuDisplay() {
        try {
            waitForPageFinishLoading();
            if (contactHamburgerMenuButton.isDisplayed()) {
                scrollToElement(contactHamburgerMenuButton);
                return true;

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("check hamburger Menu Button of 'contact information'")
    public boolean checkHamburgerMenuButtonDisplay(String nameFromMenu) {
        try {
            waitForPageFinishLoading();
            if (contactHamburgerMenuButton.isDisplayed()) {
                scrollToElement(contactHamburgerMenuButton);
                click(contactHamburgerMenuButton);
                for (WebElement el : chooseFromHamburgerMenu) {
                    if (getText(el).contains(nameFromMenu)) {
                        scrollToElement(el);
                        waitForElementToBeClickable(el);
                        click(chooseFromHamburgerMenu.get(0));
                        waitForElementToBeClickable(closePopup);
                        click(closePopup);
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("check if Edit Draft and Delete Draft display")
    public boolean checkButtonsDisplay(String buttonName) {
        try {
            waitForPageFinishLoading();
            for (WebElement el : actionInPage) {
                if (getText(el).contains(buttonName)) {
                    scrollToElement(el);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
}
