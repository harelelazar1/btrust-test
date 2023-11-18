package btrust.btrustOne.client.entity.pagesObject.CreateEntity;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EntitySetupPage extends BasePage {
    public EntitySetupPage(WebDriver driver) {
        super(driver);
    }

    String legalName;

    @FindBy(css = "div.Title-module__title__2YNd9")
    protected WebElement entityInformationTitle;
    @FindBy(css = "div:nth-of-type(2) > .isWhite-undefined.multiIsBlue-undefined,div:nth-of-type(3) > .isWhite-undefined.multiIsBlue-undefined")
    protected WebElement entitySelectBox;
    @FindBy(css = "[class] [tabindex='-1']")
    protected List<WebElement> entitySelectList;
    @FindBy(css = "div:nth-of-type(3) > .isWhite-undefined.multiIsBlue-undefined.removeBorder-undefined > .css-2b097c-container.default-select.null > .css-yk16xz-control.default__control")
    protected WebElement businessCategoryBox;
    @FindBy(css = "[class] [tabindex='-1']")
    protected List<WebElement> businessCategoryList;


    //mandatory fields
    @FindBy(css = "div.Title-module__text__1LN-A")
    protected WebElement organizationClientInformationTitle;
    @FindBy(css = "div.MainSelectors-module__box__1zQ6B")
    protected WebElement entityAndBusinessCategorySubTitle;
    @FindBy(css = ".InputItem-module__label__g4G7d.undefined")
    protected List<WebElement> mandatoryFieldsTitleList;
    @FindBy(css = ".InputItem-module__inputItem__28J7w > input")
    protected List<WebElement> mandatoryTypeBoxList;
    @FindBy(css = ".SelectItem-module__inputItem__BD6kM > .SelectItem-module__required__2QRBL")
    protected List<WebElement> mandatorySelectFieldsTitleList;
    @FindBy(css = ".css-yk16xz-control")
    protected List<WebElement> mandatoryBoxArrowButtonList;
    @FindBy(css = " [class] [tabindex='-1']")
    protected List<WebElement> selectBoxList;
    @FindBy(css ="div .ResultBox-module__label__2ew02")
    protected List<WebElement> entitySetupTitlesList;
    @FindBy(css = "div .ResultBox-module__value__xVPlV")
    protected List<WebElement> entitySetupBoxList;

    //existing entity warning popup appear
    @FindBy(css = ".paper .PopupTitle-module__text__3pjTU")
    protected WebElement entityIdentifierExistsTitle;
    @FindBy(css = ".IdentifierPopup-module__alert__ubGmA")
    protected WebElement entityIdentifierExistErrorMessage;
    @FindBy(css = ".IdentifierPopup-module__main__2eJ44 .IdentifierPopup-module__rows__1MN-C")
    protected List<WebElement> matchingEntitiesNameList;
    @FindBy(css = ".IdentifierPopup-module__mainBtn__10ywp")
    protected WebElement backToEditButton;
    @FindBy(css = ".ConfirmPopup-module__container__2Yb0Q .PopupTitle-module__text__3pjTU")
    protected WebElement cancelEntityPopupButton;
    @FindBy(css = "div>button.PopupBottomButtons-module__main__1zDL0")
    protected WebElement confirmButtonInCancelPopup;


    @Step("Return the text of 'Entity Information'")
    public String entityInformationTitle() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(entityInformationTitle);
        scrollToElement(entityInformationTitle);
        return getText(entityInformationTitle);
    }

    @Step("Return the text of 'cancel entity popup title'")
    public String cancelEntityPopupTitle() {
        waitForElementToBeVisibility(cancelEntityPopupButton);
        scrollToElement(cancelEntityPopupButton);
        return getText(cancelEntityPopupButton);
    }

    @Step("Return the text of 'sub title' - entity and business category")
    public String entityAndBusinessCategorySubTitle() {
        waitForElementToBeVisibility(entityAndBusinessCategorySubTitle);
        scrollToElement(entityAndBusinessCategorySubTitle);
        return getText(entityAndBusinessCategorySubTitle);
    }

    @Step("Open entityType select list: {entityType} ")
    public void selectEntityType(String entityType) {
        waitForPageFinishLoading();
        scrollToElement(entitySelectBox);
        click(entitySelectBox);
        for (WebElement el : entitySelectList) {
            scrollToElement(el);
            if (getText(el).contains(entityType)) {
                click(el);
                break;
            }
        }
    }

    @Step("Open business Category select list: {businessCategory} ")
    public void selectBusinessCategory(String businessCategory) {
        waitForPageFinishLoading();
        scrollToElement(businessCategoryBox);
        click(businessCategoryBox);
        for (WebElement el : businessCategoryList) {
            waitForElementToBeClickable(el);
            scrollToElement(el);
            if (getText(el).contains(businessCategory)) {
                click(el);
                break;
            }
        }
    }


    @Step("click on back To Edit Button")
    public void backToEditButton() {
        scrollToElement(backToEditButton);
        click(backToEditButton);
    }

    @Step("click on back To confirm Button In Cancel Popup")
    public void confirmButtonInCancelPopup() {
        scrollToElement(confirmButtonInCancelPopup);
        click(confirmButtonInCancelPopup);
    }

    //second tab for page for entity setup tab - mandatory fields

    @Step("Return the text of 'organization Client Information Title'")
    public String organizationClientInformationTitle(String titleText) {
        waitForPageFinishLoading();
        waitForTextToBeInElement(organizationClientInformationTitle, titleText);
        return getText(organizationClientInformationTitle);
    }

    @Step("fill mandatory fields - select dropdown list")
    public void fillMandatorySelectFieldsForEntity(String mandatoryFieldTitle, String selectedOption) {
        for (int i = 0; i < mandatorySelectFieldsTitleList.size(); i++) {
            scrollToElement(mandatorySelectFieldsTitleList.get(i));
            if (getText(mandatorySelectFieldsTitleList.get(i)).equalsIgnoreCase(mandatoryFieldTitle)) {
                click(mandatoryBoxArrowButtonList.get(i));
                for (WebElement el : selectBoxList) {
                    scrollToElement(el);
                    if (getText(el).contains(selectedOption)) {
                        click(el);
                        break;
                    }
                }
            }
        }
    }

    @Step("fill mandatory fields - type in text box")
    public void fillMandatoryTypeFieldsForEntity(String mandatoryFieldTitle, String typeOption) {
        waitForPageFinishLoading();
        for (int i = 0; i < mandatoryFieldsTitleList.size(); i++) {
            scrollToElement(mandatoryFieldsTitleList.get(i));
            waitForElementToBeVisibility((mandatoryFieldsTitleList.get(i)));
            if (getText(mandatoryFieldsTitleList.get(i)).equalsIgnoreCase(mandatoryFieldTitle)) {
                scrollToElement(mandatoryTypeBoxList.get(i));
                waitForElementToBeClickable(mandatoryTypeBoxList.get(i));
                click(mandatoryTypeBoxList.get(i));
                fillText(mandatoryTypeBoxList.get(i), typeOption);
                break;
            }
        }
    }



    @Step("Return the first case information")
    public String returnLegalName(String title) {
        waitForPageFinishLoading();
        for (int i = 0; i < entitySetupTitlesList.size(); i++) {
            scrollToElement(entitySetupTitlesList.get(i));
            waitForElementToBeVisibility(entitySetupTitlesList.get(i));
            if (getText(entitySetupTitlesList.get(i)).equalsIgnoreCase(title)){
                scrollToElement(entitySetupBoxList.get(i));
                legalName = getText(entitySetupBoxList.get(i));
                System.out.println(legalName);
            }
        }
        return legalName;
    }




    @Step("Return the text of 'Entity identifier exists Title'")
    public String entityIdentifierExistsTitle() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(entityIdentifierExistsTitle);
        scrollToElement(entityIdentifierExistsTitle);
        return getText(entityIdentifierExistsTitle);
    }

    @Step("Return the text of 'Entity identifier exists error Message")
    public String entityIdentifierExistErrorMessage() {
        scrollToElement(entityIdentifierExistErrorMessage);
        return getText(entityIdentifierExistErrorMessage);
    }

    @Step("Click on entity name")
    public void clickOnExistingEntityName(String entityName) {
        waitForList(matchingEntitiesNameList);
        for (WebElement el : matchingEntitiesNameList) {
            scrollToElement(el);
            if (getText(el).contains(entityName)) {
                click(el);
            }
        }
    }
}