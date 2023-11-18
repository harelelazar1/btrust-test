package btrust.onboardingProcess.ui.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MobileFormPage extends BasePage {
    public MobileFormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div .MobileFormRenderer_buttons__q4X0n button")
    protected WebElement nextButton;
    @FindBy(css = ".FormHeader_titleNavContainer__WB3j8 [role='button']")
    protected WebElement backNavigation;


    @Step("Click on next button")
    public void clickOnNextButton() {
        waitForElementToBeVisibility(nextButton);
        scrollToElement(nextButton);
        click(nextButton);
    }


    @Step("Click on back button")
    public void clickOnBackButton() {
        waitForElementToBeVisibility(backNavigation);
        scrollToElement(backNavigation);
        click(backNavigation);
    }


    @Step("Click on back button")
    public boolean checkIfBackNavigationDisplay() {
        try {
            waitForElementToBeVisibility(nextButton);
            if (isDisplayed(backNavigation)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


}