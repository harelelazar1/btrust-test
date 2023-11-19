package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LicensePage extends BasePage {
    public LicensePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".search > input")
    protected WebElement searchField;
    @FindBy(css = ".add-new-btn > button")
    protected WebElement addNewAccountButton;
    @FindBy(css = "td.MuiTableCell-root.MuiTableCell-body.name")
    protected WebElement companyNameAfterFilter;
    @FindBy(css = "td:nth-child(8)")
    protected WebElement contactPersonAfterFilter;
    @FindBy(css = ".admin-page-wrapper > .no-found")
    protected WebElement noResultsFoundMessage;
    @FindBy(css = "#root")
    protected WebElement blockMessage;
    @FindBy(css = "tbody > :nth-child(4) > .MuiTableCell-root.MuiTableCell-body.company-id")
    protected WebElement licenseSerial5;
    @FindBy(css = ".title > span")
    protected WebElement licensePageTitle;

    @Step("type in search field: {search}")
    public void filterBySearchField(String search) {
        waitForElementToBeClickable(searchField);
        fillText(searchField, search);
    }

    @Step("check that company name appear after filter")
    public String companyNameAfterFilter(String text) {
        waitForPageFinishLoading();
        scrollToElement(companyNameAfterFilter);
        waitForTextToBeInElement(companyNameAfterFilter, text);
        return getText(companyNameAfterFilter);
    }

    @Step("check that company name appear after filter")
    public String licensePageTitle(String text) {
        waitForTextToBeInElement(licensePageTitle, text);
        return getText(licensePageTitle);
    }

    @Step("check that contact person appear after filter")
    public String contactPersonAfterFilter(String text) {
        waitForPageFinishLoading();
        scrollToElement(contactPersonAfterFilter);
        waitForTextToBeInElement(contactPersonAfterFilter, text);
        return getText(contactPersonAfterFilter);
    }

    @Step("check that No results found message appear")
    public String noResultsFoundMessage(String text) {
        waitForTextToBeInElement(noResultsFoundMessage, text);
        return getText(noResultsFoundMessage);
    }

    @Step("click on add new account button")
    public void addNewAccountButton() {
        waitForElementToBeClickable(addNewAccountButton);
        click(addNewAccountButton);
    }

    @Step("check if blockMessage is present")
    public boolean blockMessage() {
        waitForPageFinishLoading();
        WebElement progressBar = blockMessage;
        boolean block = progressBar.isDisplayed();
        waitForElementToBeVisibility(blockMessage);
        return block;
    }

    @Step("Click on licenseSerial5")
    public void licenseSerial5() {
        waitForElementToBeClickable(licenseSerial5);
        click(licenseSerial5);
    }
}