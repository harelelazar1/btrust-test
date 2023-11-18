package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    protected boolean title;

    @FindBy(css = ".total-cases-box > .total-head .big-text")
    protected WebElement totalCasesTitle;
    @FindBy(css = ".data > .header > span")
    protected WebElement welcomeBackTitle;
    @FindBy(css = ".data > .name")
    protected WebElement userNameTitle;
    @FindBy(css = ".recent-cases > .title > span")
    protected WebElement recentCasesTitle;
    @FindBy(css = ".recent-cases > .MuiTable-root")
    protected WebElement recentCasesTable;
    @FindBy(css = ".filter-item > .select-button")
    protected WebElement selectTimePeriod;
    @FindBy(css = ".radio-btns > .item")
    protected List<WebElement> selectTimePeriodList;
    @FindBy(css = ":nth-child(2) > div > div.recent-cases > table > tbody > tr:nth-child(1) > td.MuiTableCell-root.MuiTableCell-body.case-nubmer")
    protected WebElement caseNumber1th;
    @FindBy(css = "div.DashboardUserSide-module__dashboardName__FzkzG")
    protected WebElement dashboardTitle;



    @Step("Return the text of dashboard page")
    public String dashboardTitle() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(dashboardTitle);
        return getText(dashboardTitle);
    }

    @Step("check that total cases title appear on the screen")
    public boolean totalCasesTitle() {
        waitForPageFinishLoading();
        try {
            waitForPageFinishLoading();
            waitForElementToBeVisibility(totalCasesTitle);
            WebElement totalCases = totalCasesTitle;
            title = totalCases.isDisplayed();
        } catch (Exception e) {
            scrollToElement(totalCasesTitle);
            WebElement totalCases = totalCasesTitle;
            title = totalCases.isDisplayed();
        }
        return title;
    }

    @Step("click on case 1 th in Recent Cases")
    public void caseNumber1th() {
        waitForElementToBeVisibility(caseNumber1th);
        click(caseNumber1th);
    }

    @Step("check that all the elements appear")
    public void enterToDashboardPage() {
        waitForElementToBeVisibility(welcomeBackTitle);
        isEnabled(welcomeBackTitle);
        waitForElementToBeVisibility(userNameTitle);
        isEnabled(userNameTitle);
        waitForElementToBeVisibility(recentCasesTitle);
        isEnabled(recentCasesTitle);
        waitForElementToBeVisibility(recentCasesTable);
        isEnabled(recentCasesTable);
    }

    @Step("open time period menu and choose from the list: {chooseFromTimePeriodList}")
    public void selectTimePeriod(String chooseFromTimePeriodList) {
        waitForElementToBeVisibility(selectTimePeriod);
        click(selectTimePeriod);
        for (WebElement el : selectTimePeriodList) {
            if (el.getText().equalsIgnoreCase(chooseFromTimePeriodList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
    }

    @Step("Check the value of case number 1 th")
    public String caseNumber1() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(caseNumber1th);
        return getText(caseNumber1th);
    }
}