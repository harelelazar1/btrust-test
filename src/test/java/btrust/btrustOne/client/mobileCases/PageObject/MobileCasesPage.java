package btrust.btrustOne.client.mobileCases.PageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MobileCasesPage extends BasePage {

    public MobileCasesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "tr.MuiTableRow-root.MuiTableRow-head>th>[role='button']")
    protected List<WebElement> columnTitleName;
    @FindBy(css = "tbody.MuiTableBody-root>tr.MuiTableRow-root.table-row >:nth-child(3)")
    protected List<WebElement> columnNameList;
    @FindBy(css = "tbody.MuiTableBody-root>tr.MuiTableRow-root.table-row >:nth-child(3)")
    protected WebElement pageTitle;
    @FindBy(css = ".TopBar-module__input-item__1iR8i>input")
    protected WebElement searchField;


    @Step("Return the text of search title")
    public String searchTitle() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(pageTitle);
        return getText(pageTitle);
    }

    @Step("Type in search field")
    public void searchFiled(String text) {
        waitForPageFinishLoading();
        scrollToElement(searchField);
        fillText(searchField, text);
        waitForPageFinishLoading();
        sleep(5000);
    }












}






