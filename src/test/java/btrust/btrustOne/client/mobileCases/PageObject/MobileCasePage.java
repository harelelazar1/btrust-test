package btrust.btrustOne.client.mobileCases.PageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MobileCasePage extends BasePage {

    public MobileCasePage(WebDriver driver) {
        super(driver);
    }

    //ocr
    @FindBy(css = ".CollapseList-module__collapseList__15Zzb>li .CollapseList-module__title__2Dvw2")
    protected List<WebElement> collapseName;
    @FindBy(css = ".Title-module__case-header-container__3lkH1 .Title-module__case-title__VA0Hq>div")
    protected WebElement fullNameFromCard;
    @FindBy(css = ".Ocr-module__rows__1nr4u >.Ocr-module__row__3wdsD.Ocr-module__key__5aOXn")
    protected List<WebElement> fieldsName;
    @FindBy(css = ".Ocr-module__rows__1nr4u .UserDataField-module__value__3F53u")
    protected List<WebElement> fieldsData;
    @FindBy(css = ".Ocr-module__imageContainer__2loL8 .ServerImage-module__container__1wlfo")
    protected List<WebElement> ocrImages;

    //Document verification
    @FindBy(css = "div .Processes-module__table__25GjX :nth-child(1).Processes-module__item__2xiWq")
    protected List<WebElement> checks;
    @FindBy(css = "div .Processes-module__item__2xiWq.Processes-module__data__2gPAo>[alt]")
    protected List<WebElement> results;

    //Liveness
    @FindBy(css = ".Processes-module__body__FPGUS video,.Processes-module__body__FPGUS .ServerImage-module__imageAndLabelContainer__32BTe")
    protected List<WebElement> livenessImages;
    @FindBy(css = ".Processes-module__table__25GjX>:nth-child(2)>:nth-child(1) span ,.Processes-module__table__25GjX>:nth-child(3)>:nth-child(1) span")
    protected List<WebElement> livenessFields;
    @FindBy(css = ".Processes-module__table__25GjX>:nth-child(2)>:nth-child(2) span ,.Processes-module__table__25GjX>:nth-child(3)>:nth-child(2) span")
    protected List<WebElement> livenessData;


    //FaceMatch
    @FindBy(css = ".Processes-module__table__25GjX>:nth-child(2)>:nth-child(1) span ,.Processes-module__table__25GjX>:nth-child(3)>:nth-child(1) span,.Processes-module__table__25GjX>:nth-child(4)>:nth-child(1) span")
    protected List<WebElement> faceMatchFields;
    @FindBy(css = ".Processes-module__table__25GjX>:nth-child(2)>:nth-child(2) span ,.Processes-module__table__25GjX>:nth-child(3)>:nth-child(2) span,.Processes-module__table__25GjX>:nth-child(4)>:nth-child(2)")
    protected List<WebElement> faceMatchData;








    @Step("Check amount of ocr images")
    public boolean ocrImageAmount(int expectedSize) {
        try {
            waitForPageFinishLoading();
            return ocrImages.size() == expectedSize;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Step("Check amount of liveness images")
    public boolean livenessImageAmount(int expectedSize) {
        try {
            waitForPageFinishLoading();
            return livenessImages.size() == expectedSize;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean faceMatchImageAmount(int expectedSize) {
        try {
            waitForPageFinishLoading();
            return livenessImages.size() == expectedSize;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }


    @Step("Compare data from card to the data in the screen")
    public String compareOcrDataFromCard(String name) {
        String fieldData = null;
        try {
            waitForPageFinishLoading();
            waitForElementToBeVisibility(fieldsName.get(0));
            for (int i = 0; i < fieldsName.size(); i++) {
                if (getText(fieldsName.get(i)).equalsIgnoreCase(name)) {
                    scrollToElement(fieldsData.get(i));
                    fieldData = getText(fieldsData.get(i));
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return fieldData;
    }


    @Step("Check title full name")
    public Boolean checkTitleFullName(String nameFromCard) {
        try {
            waitForPageFinishLoading();
            waitForElementToBeVisibility(fullNameFromCard);
            scrollToElement(fullNameFromCard);
            if (getText(fullNameFromCard).contains(nameFromCard)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return false;
    }


    @Step("After recognize collapse name open the card")
    public void collapseCardsName(String name) {
        try {
            waitForPageFinishLoading();
            waitForElementToBeVisibility(collapseName.get(0));
            for (WebElement collapse : collapseName) {
                if (getText(collapse).equalsIgnoreCase(name)) {
                    scrollToElement(collapse);
                    waitForElementToBeClickable(collapse);
                    click(collapse);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }


    @Step("Recognize collapse name")
    public Boolean checkIfCollapseCardsNameDisplay(String name) {
        try {
            waitForPageFinishLoading();
            waitForElementToBeVisibility(collapseName.get(0));
            for (WebElement collapse : collapseName) {
                if (getText(collapse).equalsIgnoreCase(name)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return false;
    }


    @Step("Check the result of the authentication check")
    public String checkAuthenticationResult(String checksName) {
        String result = null;
        try {
            waitForPageFinishLoading();
            waitForElementToBeVisibility(checks.get(0));
            for (int i = 0; i < checks.size(); i++) {
                scrollToElement(checks.get(i));
                if (getText(checks.get(i)).equalsIgnoreCase(checksName)) {
                    scrollToElement(results.get(i));
                    result = (results.get(i).getAttribute("alt"));
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return result;
    }


    @Step("Check liveness result")
    public String checkLivenessResult(String fieldName) {
        String dataResult = null;
        try {
            waitForPageFinishLoading();
            waitForElementToBeVisibility(livenessFields.get(0));
            for (int i = 0; i < livenessFields.size(); i++) {
                scrollToElement(livenessFields.get(i));
                if (getText(livenessFields.get(i)).equalsIgnoreCase(fieldName)) {
                    scrollToElement(livenessData.get(i));
                    dataResult = (getText(livenessData.get(i)));
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return dataResult;
    }


    @Step("Check faceMatch result")
    public String checkFaceMatchResult(String fieldName) {
        String dataResult = null;
        try {
            waitForPageFinishLoading();
            waitForElementToBeVisibility(faceMatchFields.get(0));
            for (int i = 0; i < faceMatchFields.size(); i++) {
                scrollToElement(faceMatchFields.get(i));
                if (getText(faceMatchFields.get(i)).equalsIgnoreCase(fieldName)) {
                    scrollToElement(faceMatchData.get(i));
                    dataResult = (getText(faceMatchData.get(i)));
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return dataResult;
    }


}



























