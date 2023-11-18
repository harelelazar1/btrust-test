package selenium.ocr.pageObject.oldService;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import selenium.BasePage;

import java.util.ArrayList;
import java.util.List;

public class OcrResultsPage extends BasePage {

    public OcrResultsPage(WebDriver driver) {
        super(driver);
    }

    protected String resultField;
    protected String mrzResults;
    protected ArrayList<String> ocrResultsInString = new ArrayList<>();
    protected ArrayList<String> chequeResultsInString = new ArrayList<>();
    public String caseId;

    @FindBy(css = ".content > .bottomDefaultBorder.topDefaultMargin")
    protected WebElement finalTitleMessage;
    @FindBy(css = "#again[type= button]")
    protected WebElement takeAnotherTestButton;
    @FindBy(css = ".image > img")
    protected List<WebElement> imageList;
    @FindBy(css = "table td")
    protected List<WebElement> ocrResultsFieldsList;
    @FindBy(css = "table tr")
    protected List<WebElement> mrzOcrResultsList;

    @Step("click take Another Test Button")
    public void takeAnotherTestButton() {
        waitForElementToBeClickable(takeAnotherTestButton);
        click(takeAnotherTestButton);
    }

    @Step("verify take Another Test Button is display")
    public boolean takeAnotherTestButtonIsDisplay() {
        try {
            waitForElementToBeClickable(takeAnotherTestButton);
            isDisplayed(takeAnotherTestButton);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Step("get text of the final text title")
    public String finalTitleMessage() {
        OcrScanPage ocrScanPage = new OcrScanPage(driver);
        if (!ocrScanPage.scanError.isEmpty() && ocrScanPage.scanErrorMessage().contains("Error occurred")) {
            System.out.println(ocrScanPage.scanErrorMessage());
            Assert.fail(ocrScanPage.scanErrorMessage());
            Assert.fail();
        }
        waitForPageFinishLoading();
        waitForElementToBeVisibility(finalTitleMessage);
        return getText(finalTitleMessage);
    }

    @Step("check images exist in base 64")
    public boolean checkImagesExist(int numberOfPicAppearsInOCRResultPage) {
        try {
            waitForElementToBeVisibility(finalTitleMessage);
            if (imageList.size() == numberOfPicAppearsInOCRResultPage) {
                for (WebElement el : imageList) {
                    imageIsDisplayed(el);
                }
                return true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Step("ocr fields results in String array + list size check")
    public ArrayList<String> ocrResultsInStringArray(String idType, int listSize) {
        ocrResultsInString.clear();
        waitForElementToBeVisibility(finalTitleMessage);
        for (WebElement el : ocrResultsFieldsList) {
            ocrResultsInString.add(getText(el));
        }
        if (ocrResultsInString.contains(idType)) {
            System.out.println("list size is: " + ocrResultsInString.size());
            if (ocrResultsInString.size() == listSize) {
                System.out.println("list size is correct");

                for (int i = 0; i < ocrResultsFieldsList.size(); i++) {
                    if (getText(ocrResultsFieldsList.get(i)).equalsIgnoreCase("caseId")) {
                        i = ocrResultsInString.indexOf(getText(ocrResultsFieldsList.get(i)));
                        System.out.println("number of filed " + getText(ocrResultsFieldsList.get(i)) + " is  index: " + i);
                        caseId = getText(ocrResultsFieldsList.get(i + 1));
                        System.out.println("caseId is: " + caseId);
                    }
                }

                return ocrResultsInString;
            } else {
                System.out.println("the list size is not as always");
            }
            return ocrResultsInString = null;
        }


        return null;
    }

    @Step("ocr fields results validation from ocr result tables")
    public boolean ocrResultsFields(String ocrFieldName, String ocrFieldNameResult, String fontAuth, String Pass,
                                    String alignmentXAuth, String alignmentYAuth, String characterSizeAuth) {
        try {
            waitForElementToBeVisibility(finalTitleMessage);

            for (int i = 0; i < ocrResultsFieldsList.size(); i++) {
                if (getText(ocrResultsFieldsList.get(i)).equalsIgnoreCase(ocrFieldName)) {

                    i = ocrResultsInString.indexOf(getText(ocrResultsFieldsList.get(i)));
                    System.out.println();
                    System.out.println("number of filed " + getText(ocrResultsFieldsList.get(i)) + " is  index: " + i);

                    if (getText(ocrResultsFieldsList.get(i + 1)).equalsIgnoreCase(ocrFieldNameResult)) {
                        resultField = getText(ocrResultsFieldsList.get(i + 1));
                        Assert.assertEquals(resultField, ocrFieldNameResult);
                        System.out.println(getText(ocrResultsFieldsList.get(i)));
                        System.out.println(getText(ocrResultsFieldsList.get(i + 1)));
                        i++;
                    } else {
                        return false;
                    }

                    if (i + 1 < ocrResultsInString.size()) {

                        if (getText(ocrResultsFieldsList.get(i + 1)).equalsIgnoreCase(fontAuth)) {
                            resultField = getText(ocrResultsFieldsList.get(i + 2));
                            Assert.assertEquals(resultField, Pass);
                            System.out.println(getText(ocrResultsFieldsList.get(i + 1)));
                            System.out.println(getText(ocrResultsFieldsList.get(i + 2)));
                            i++;

                            if (getText(ocrResultsFieldsList.get(i + 2)).equalsIgnoreCase(alignmentXAuth)) {
                                resultField = getText(ocrResultsFieldsList.get(i + 3));
                                Assert.assertEquals(resultField, Pass);
                                System.out.println(getText(ocrResultsFieldsList.get(i + 2)));
                                System.out.println(getText(ocrResultsFieldsList.get(i + 3)));
                                i++;
                            }

                            if (getText(ocrResultsFieldsList.get(i + 3)).equalsIgnoreCase(alignmentYAuth)) {
                                resultField = getText(ocrResultsFieldsList.get(i + 4));
                                Assert.assertEquals(resultField, Pass);
                                System.out.println(getText(ocrResultsFieldsList.get(i + 3)));
                                System.out.println(getText(ocrResultsFieldsList.get(i + 4)));
                                i++;
                            }

                            if (getText(ocrResultsFieldsList.get(i + 4)).equalsIgnoreCase(characterSizeAuth)) {
                                resultField = getText(ocrResultsFieldsList.get(i + 5));
                                Assert.assertEquals(resultField, Pass);
                                System.out.println(getText(ocrResultsFieldsList.get(i + 4)));
                                System.out.println(getText(ocrResultsFieldsList.get(i + 5)));
                                i++;
                            }
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Step("ocr fields results validation from ocr result tables")
    public boolean ocrResultsFields2(String ocrFieldName, String ocrFieldNameResult, String fontAuth, String Pass,
                                     String alignmentXAuth, String alignmentYAuth, String characterSizeAuth, String failure) {
        try {
            waitForElementToBeVisibility(finalTitleMessage);

            for (int i = 0; i < ocrResultsFieldsList.size(); i++) {
                if (getText(ocrResultsFieldsList.get(i)).equalsIgnoreCase(ocrFieldName)) {

                    i = ocrResultsInString.indexOf(getText(ocrResultsFieldsList.get(i)));
                    System.out.println();
                    System.out.println("number of filed " + getText(ocrResultsFieldsList.get(i)) + " is  index: " + i);

                    if (getText(ocrResultsFieldsList.get(i + 1)).equalsIgnoreCase(ocrFieldNameResult)) {
                        resultField = getText(ocrResultsFieldsList.get(i + 1));
                        Assert.assertEquals(resultField, ocrFieldNameResult);
                        System.out.println(getText(ocrResultsFieldsList.get(i)));
                        System.out.println(getText(ocrResultsFieldsList.get(i + 1)));
                        i++;
                    } else {
                        return false;
                    }

                    if (i + 1 < ocrResultsInString.size()) {

                        if (getText(ocrResultsFieldsList.get(i + 1)).equalsIgnoreCase(fontAuth)) {
                            resultField = getText(ocrResultsFieldsList.get(i + 2));
                            if (failure != null) {
                                Pass = failure;
                                Assert.assertEquals(resultField, Pass);
                                Pass = "PASS";
                            }

                            System.out.println(getText(ocrResultsFieldsList.get(i + 1)));
                            System.out.println(getText(ocrResultsFieldsList.get(i + 2)));
                            i++;

                            if (getText(ocrResultsFieldsList.get(i + 2)).equalsIgnoreCase(alignmentXAuth)) {
                                resultField = getText(ocrResultsFieldsList.get(i + 3));
                                Assert.assertEquals(resultField, Pass);
                                System.out.println(getText(ocrResultsFieldsList.get(i + 2)));
                                System.out.println(getText(ocrResultsFieldsList.get(i + 3)));
                                i++;
                            }

                            if (getText(ocrResultsFieldsList.get(i + 3)).equalsIgnoreCase(alignmentYAuth)) {
                                resultField = getText(ocrResultsFieldsList.get(i + 4));
                                Assert.assertEquals(resultField, Pass);
                                System.out.println(getText(ocrResultsFieldsList.get(i + 3)));
                                System.out.println(getText(ocrResultsFieldsList.get(i + 4)));
                                i++;
                            }

                            if (getText(ocrResultsFieldsList.get(i + 4)).equalsIgnoreCase(characterSizeAuth)) {
                                resultField = getText(ocrResultsFieldsList.get(i + 5));
                                Assert.assertEquals(resultField, Pass);
                                System.out.println(getText(ocrResultsFieldsList.get(i + 4)));
                                System.out.println(getText(ocrResultsFieldsList.get(i + 5)));
                                i++;
                            }
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Step("ocr fields results in mrz  tables scans")
    public String ocrResultsForMRZ(String ocrFieldName) {
        waitForElementToBeVisibility(finalTitleMessage);
        for (WebElement el : mrzOcrResultsList) {
            if (getText(el).contains(ocrFieldName)) {
                System.out.println(getText(el));
                mrzResults = getText(el);
            }
        }
        return mrzResults;
    }

    @Step("cheque fields results in String array + list size check")
    public ArrayList<String> chequeResultsInStringArray(String ocrType, int listSize) {
        chequeResultsInString.clear();
        waitForElementToBeVisibility(finalTitleMessage);
        for (WebElement el : ocrResultsFieldsList) {
            chequeResultsInString.add(getText(el));
        }
        System.out.println("list size is: " + chequeResultsInString.size());
        if (chequeResultsInString.size() == listSize) {
            System.out.println("list size is correct");
            for (int i = 0; i < ocrResultsFieldsList.size(); i++) {
                i = chequeResultsInString.indexOf(getText(ocrResultsFieldsList.get(i)));
                System.out.println("number of filed " + getText(ocrResultsFieldsList.get(i)) + " is  index: " + i);
            }
            return chequeResultsInString;
        } else {
            System.out.println("the list size is not as always");
        }
        return chequeResultsInString = null;
    }

    @Step("ocr fields results validation from ocr result tables")
    public boolean ocrResultsFieldsForCheque(String chequeNumber, String chequeNumberResult, String fields, String fieldsResult,
                                             String blocks, String blocksResults, String characters, String charactersResults, String minCorrelation, String minCorrelationResults, String averageCorrelation, String averageCorrelationResults, String requiredMinimumCorrelation, String requiredMinimumCorrelationResults) {
        try {
            waitForElementToBeVisibility(finalTitleMessage);

            for (int i = 0; i < ocrResultsFieldsList.size(); i++) {
                if (getText(ocrResultsFieldsList.get(i)).equalsIgnoreCase(chequeNumber)) {
                    i = chequeResultsInString.indexOf(getText(ocrResultsFieldsList.get(i)));

                    if (getText(ocrResultsFieldsList.get(i + 1)).equalsIgnoreCase(chequeNumberResult)) {
                        resultField = getText(ocrResultsFieldsList.get(i + 1));
                        Assert.assertEquals(resultField, chequeNumberResult);
                        System.out.println(getText(ocrResultsFieldsList.get(i)));
                        System.out.println(getText(ocrResultsFieldsList.get(i + 1)));
                        i++;
                    } else {
                        return false;
                    }

                    if (i + 1 < chequeResultsInString.size()) {

                        //fields
                        if (getText(ocrResultsFieldsList.get(i + 2)).equalsIgnoreCase(fieldsResult)) {
                            resultField = getText(ocrResultsFieldsList.get(i + 2));
                            Assert.assertEquals(getText(ocrResultsFieldsList.get(i + 1)), fields);
                            Assert.assertEquals(resultField, fieldsResult);
                            System.out.println(getText(ocrResultsFieldsList.get(i + 1)));
                            System.out.println(getText(ocrResultsFieldsList.get(i + 2)));
                            i++;

                            //blocks
                            if (getText(ocrResultsFieldsList.get(i + 3)).equalsIgnoreCase(blocksResults)) {
                                resultField = getText(ocrResultsFieldsList.get(i + 3));
                                Assert.assertEquals(getText(ocrResultsFieldsList.get(i + 2)), blocks);
                                Assert.assertEquals(resultField, blocksResults);
                                System.out.println(getText(ocrResultsFieldsList.get(i + 2)));
                                System.out.println(getText(ocrResultsFieldsList.get(i + 3)));
                                i++;
                            }

                            //characters
                            if (getText(ocrResultsFieldsList.get(i + 4)).contains(charactersResults)) {
                                resultField = getText(ocrResultsFieldsList.get(i + 4));
                                Assert.assertEquals(getText(ocrResultsFieldsList.get(i + 3)), characters);
//                                Assert.assertEquals(resultField, charactersResults);
                                System.out.println(getText(ocrResultsFieldsList.get(i + 3)));
                                System.out.println(getText(ocrResultsFieldsList.get(i + 4)));
                                i++;
                            }

                            //minimumCorrelation
                            if (getText(ocrResultsFieldsList.get(i + 5)).equalsIgnoreCase(minCorrelationResults)) {
                                resultField = getText(ocrResultsFieldsList.get(i + 5));
                                Assert.assertEquals(getText(ocrResultsFieldsList.get(i + 4)), minCorrelation);
                                Assert.assertEquals(resultField, minCorrelationResults);
                                System.out.println(getText(ocrResultsFieldsList.get(i + 4)));
                                System.out.println(getText(ocrResultsFieldsList.get(i + 5)));
                                i++;
                            }

                            //averageCorrelation
                            if (getText(ocrResultsFieldsList.get(i + 6)).equalsIgnoreCase(averageCorrelationResults)) {
                                resultField = getText(ocrResultsFieldsList.get(i + 6));
                                Assert.assertEquals(getText(ocrResultsFieldsList.get(i + 5)), averageCorrelation);
                                Assert.assertEquals(resultField, averageCorrelationResults);
                                System.out.println(getText(ocrResultsFieldsList.get(i + 5)));
                                System.out.println(getText(ocrResultsFieldsList.get(i + 6)));
                                i++;
                            }

                            //requiredMinimumCorrelation
                            if (getText(ocrResultsFieldsList.get(i + 7)).equalsIgnoreCase(requiredMinimumCorrelationResults)) {
                                resultField = getText(ocrResultsFieldsList.get(i + 7));
                                Assert.assertEquals(getText(ocrResultsFieldsList.get(i + 6)), requiredMinimumCorrelation);
                                Assert.assertEquals(resultField, requiredMinimumCorrelationResults);
                                System.out.println(getText(ocrResultsFieldsList.get(i + 6)));
                                System.out.println(getText(ocrResultsFieldsList.get(i + 7)));
                                i++;
                            }
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}

