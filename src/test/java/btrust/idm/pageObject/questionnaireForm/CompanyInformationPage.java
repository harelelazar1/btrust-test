package btrust.idm.pageObject.questionnaireForm;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CompanyInformationPage extends BasePage {
    public CompanyInformationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".CompanyInformation_title__1wML9")
    protected WebElement companyInfoTitle; // company info header
    @FindBy(css = "textarea[name='tab_2_companyInfo']")
    protected WebElement companyInfo; // company info text box

    @Step("Check that companyInfoTitle appear")
    public boolean companyInfoTitle(String text) {
        try {
            waitForTextToBeInElement(companyInfoTitle, text);
            getText(companyInfoTitle).equalsIgnoreCase(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("fill company info text box")
    public String companyInfo(String text) {
        waitForElementToBeVisibility(companyInfo);
        fillText(companyInfo, text);
        return getAttribute(companyInfo, "value");
    }
}