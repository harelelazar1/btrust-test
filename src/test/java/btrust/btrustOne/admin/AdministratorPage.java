package btrust.btrustOne.admin;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AdministratorPage extends BasePage {
    public AdministratorPage(WebDriver driver) {
        super(driver);
    }

    protected String titleName;

    @FindBy(css = ".ui  .Navigation-module__admin-text__2Hhe0")
    protected WebElement administratorTitle;
    @FindBy(css = ".Sidebar-module__menu__14bAM > a")
    protected List<WebElement> sideBar;
    @FindBy(css = ".ui.container .Sidebar-module__title__zr5Oz")
    protected List<WebElement> titleOfSideBar;


    @Step("Return the text of administrator title")
    public String administratorTitle() {
        waitForElementToBeVisibility(administratorTitle);
        return getText(administratorTitle);
    }

    @Step("Choose from side bar : {option}")
    public void chooseFromSideBar(String option) {
        waitForPageFinishLoading();
        scrollToElement(sideBar.get(0));
        waitForElementToBeVisibility(sideBar.get(0));
        for (WebElement el : sideBar) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(option)) {
                click(el);
                break;
            }
        }
    }

    @Step("Choose from side bar : {option}")
    public void chooseFromSideBar1(String module, String option) {
        waitForPageFinishLoading();
        for (WebElement el : titleOfSideBar) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(module)) {
                waitForElementToBeClickable(el);
                click(el);
                for (WebElement el1 : sideBar) {
                    waitForElementToBeVisibility(el1);
                    scrollToElement(el1);
                    if (getText(el1).equalsIgnoreCase(option)) {
                        click(el1);
                        break;
                    }
                }
                break;
            }
        }
    }


    @Step("Check if name display in side bar")
    public boolean checkIfNewDocumentButtonDisplay(String nameFromSideBar) {
        try {
            waitForPageFinishLoading();
            for (WebElement el : sideBar) {
                if (getText(el).equalsIgnoreCase(nameFromSideBar)) {
                    scrollToElement(el);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("Open all the side bar groups")
    public void openAllSideBarGroups() {
        waitForPageFinishLoading();
        int index = titleOfSideBar.size();
        for (WebElement el : titleOfSideBar) {
            index--;
            titleName = getText(el);
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase("data module")) {
                scrollToElement(el);
                waitForElementToBeClickable(el);
                click(el);
            }
            click(el);
            if (index <= 0) {
                break;
            }
        }
    }


    @Step("Open all the side bar groups")
    public void openAllSideBarGroups1() {
        waitForPageFinishLoading();
        int index = titleOfSideBar.size();
        for (int i = 0; i < titleOfSideBar.size(); i++) {
            index--;
            titleName = getText(titleOfSideBar.get(i));
            scrollToElement(titleOfSideBar.get(i));
            if (!getText(titleOfSideBar.get(i)).equalsIgnoreCase("DATA MODULE")) {
                scrollToElement(titleOfSideBar.get(i));
                click(titleOfSideBar.get(i));
            } else {
                System.out.println("Data module already open");
            }
            if (index <= 0) {
                break;
            }
        }
    }
}