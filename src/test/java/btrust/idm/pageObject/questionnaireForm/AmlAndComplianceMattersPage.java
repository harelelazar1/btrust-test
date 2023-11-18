package btrust.idm.pageObject.questionnaireForm;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AmlAndComplianceMattersPage extends BasePage {
    public AmlAndComplianceMattersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".AML_title__1s6RV")
    protected WebElement amlAndComplianceTitle; // aml and compliance header
    @FindBy(css = ".AML_container__3nM1b > :nth-child(2) > ul > li")
    protected List<WebElement> policyList;
    @FindBy(css = ".AML_container__3nM1b > :nth-child(2) > textarea")
    protected WebElement firstFreeFieldText;
    @FindBy(css = ".AML_container__3nM1b > :nth-child(4) > ul > li")
    protected List<WebElement> yesNoList;
    @FindBy(css = "  .AML_container__3nM1b > :nth-child(4) > textarea")
    protected WebElement secondFreeFieldText;


    @Step("Check that amlAndComplianceTitle appear")
    public boolean amlAndComplianceTitle(String text) {
        try {
            waitForTextToBeInElement(amlAndComplianceTitle, text);
            getText(amlAndComplianceTitle).equalsIgnoreCase(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Fill details in AML and Compliance Matters fields")
    public void fillDetailsAMLAndComplianceMatters(String chooseFromPolicyList, String firstField, String chooseYesOrNo, String secondField) {
        waitForTextToBeInElement(amlAndComplianceTitle, "AML and Compliance Matters");
        for (WebElement el : policyList) {
            if (getText(el).equalsIgnoreCase(chooseFromPolicyList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        waitForElementToBeVisibility(firstFreeFieldText);
        fillText(firstFreeFieldText, firstField);
        for (WebElement el : yesNoList) {
            if (getText(el).equalsIgnoreCase(chooseYesOrNo)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        waitForElementToBeVisibility(secondFreeFieldText);
        fillText(secondFreeFieldText, secondField);
    }
}