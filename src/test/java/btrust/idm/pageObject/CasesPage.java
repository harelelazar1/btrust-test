package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CasesPage extends BasePage {
    public CasesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".MuiTableBody-root > :nth-child(1) > :nth-child(3)")
    protected WebElement workflowCaseID;
    @FindBy(css = ".MuiTableBody-root > :nth-child(1) > :nth-child(5)")
    protected WebElement caseStatus;
    @FindBy(css = ".MuiTableBody-root > :nth-child(1)")
    protected WebElement firstCase;
    @FindBy(css = ".title > span")
    protected WebElement casesTitle;
    @FindBy(css = ".numbers > span:nth-child(3)")
    protected WebElement numbersOfCases;


    @Step("Check that workflowCaseID appear")
    public boolean workflowCaseID() {
        waitForElementToBeVisibility(workflowCaseID);
        return isDisplayed(workflowCaseID);
    }

    @Step("Check that caseStatus appear")
    public boolean caseStatus() {
        waitForElementToBeVisibility(caseStatus);
        return isDisplayed(caseStatus);
    }

    @Step("Click on the first case that appear in table case")
    public void firstCase() {
        waitForElementToBeClickable(firstCase);
        sleep(2000);
        click(firstCase);
    }

    @Step("Check that caseStatus appear")
    public String caseStatusDesc(String text) {
        waitForTextToBeInElement(caseStatus, text);
        return getText(caseStatus);
    }

    @Step("Check that is firstCaseIsDisplayed")
    public boolean firstCaseIsDisplayed() {
        waitForElementToBeClickable(firstCase);
        return isDisplayed(firstCase);
    }

    @Step("Check that casesTitle appear")
    public String casesTitle(String text) {
        waitForTextToBeInElement(casesTitle, text);
        return getText(casesTitle);
    }

    @Step("Check that numbersOfCases appear")
    public String numbersOfCases(String text) {
        waitForTextToBeInElement(numbersOfCases, text);
        return getText(numbersOfCases);
    }
}