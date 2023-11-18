package btrust.btrustOne.admin.dataModule.serviceMarketPlace.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ServicesMarketplacePage extends BasePage {
    public ServicesMarketplacePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".Item-module__serviceItem__3v8hc .Item-module__action__2BHeD")
    protected List<WebElement> optionButtons;
    @FindBy(css = ".SettingsHeader-module__wrapper__2OOUN.undefined .SettingsHeader-module__title__acJ6f")
    protected WebElement servicesMarketplaceTitle;
    @FindBy(css = ".Service-module__item__3hoz3>:nth-child(3) .Service-module__addHeaderBtn__2a6Jh")
    protected List<WebElement> AddHeaderButton;


    @Step("Services Marketplace Title")
    public String serviceMarketplaceTitle(String text) {
        waitForTextToBeInElement(servicesMarketplaceTitle, text);
        return getText(servicesMarketplaceTitle);
    }


    @Step("check if buttons display")
    public boolean buttonDisplay() {
        try {
            sleep(4000);
            waitForPageFinishLoading();
            for (WebElement el : optionButtons) {
                if (isDisplayed(el)) {
                    waitForElementToBeClickable(el);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("check if add header button display")
    public boolean checkIfAddHeaderButtonDisplay() {
        try {
            waitForPageFinishLoading();
            for (WebElement el : AddHeaderButton) {
                if (isDisplayed(el)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("click button edit or add")
    public void clickButtonEditOrAdd(String name) {
        sleep(4000);
        waitForPageFinishLoading();
        for (WebElement el : optionButtons) {
            if (getText(el).contains(name)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

}
