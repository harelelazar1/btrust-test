package btrust.idm.pageObject;

import btrust.BasePage;
import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SearchPage extends BasePage {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    String json;

    @FindBy(css = ".search-items > :nth-child(1) > .select-button")
    protected WebElement timeFrameFilter;
    @FindBy(css = ".search-items > :nth-child(2) > .select-button")
    protected WebElement flowFilter;
    @FindBy(css = ".search-items > :nth-child(2) > .select-button.blue-false")
    protected WebElement flowFilterStatus;
    @FindBy(css = ".search-items > :nth-child(3) > .select-button")
    protected WebElement processTypeFilter;
    @FindBy(css = ".search-items > :nth-child(3) > .select-button.blue-false")
    protected WebElement processTypeFilterStatus;
    @FindBy(css = ".search-items > :nth-child(4) > .select-button")
    protected WebElement statusFilter;
    @FindBy(css = ".search-items > :nth-child(4) > .select-button.blue-false")
    protected WebElement statusFilterStatus;
    @FindBy(css = "[name='search']")
    protected WebElement containTextField;
    @FindBy(css = ".action-btns > .share-btn")
    protected WebElement shareListButton;
    @FindBy(css = ".sidebar > .cases-list > .case-item")
    protected List<WebElement> casesList;
    @FindBy(css = ".select-content > .radio-btns .text")
    protected List<WebElement> timeFrameList;
    @FindBy(css = ".search-items > :nth-child(1) > .clear")
    protected WebElement timeFrameFilterClearButton;
    @FindBy(css = ".search-items > :nth-child(2) > .clear")
    protected WebElement flowFilterClearButton;
    @FindBy(css = ".search-items > :nth-child(3) > .clear")
    protected WebElement processTypeFilterClearButton;
    @FindBy(css = ".search-items > :nth-child(4) > .clear")
    protected WebElement statusClearButton;
    @FindBy(css = ".select-button > .selected-date")
    protected WebElement timeFrameFilterAfterFilter;
    @FindBy(css = ".calendar-header > .btn.prev")
    protected WebElement previousMonthButton;
    @FindBy(css = ".not-found-text > span")
    protected WebElement noResultsFoundMessage;
    @FindBy(css = ".items-container .text")
    protected List<WebElement> flowFilterList;
    @FindBy(css = ".items-container > .item")
    protected List<WebElement> processTypeFilterList;
    @FindBy(css = ".items-container > .item")
    protected List<WebElement> statusFilterList;
    @FindBy(css = "[name='search']")
    protected WebElement searchField;
    @FindBy(css = ".cases-list > :nth-child(1) .title")
    protected WebElement firstNameCaseInCaseList;
    @FindBy(css = ".MuiSnackbar-root .text")
    protected WebElement shareListMessage;
    @FindBy(css = ".cases-list > :nth-child(1)")
    protected WebElement case1th;
    @FindBy(css = ".cases-list > :nth-child(2)")
    protected WebElement case2th;
    @FindBy(css = ".cases-list > :nth-child(3)")
    protected WebElement case3th;
    @FindBy(css = ".cases-list > :nth-child(4)")
    protected WebElement case4th;
    @FindBy(css = ".cases-list > :nth-child(5)")
    protected WebElement case5th;
    @FindBy(css = ".results > :nth-child(1) .row.blue > .half > :nth-child(1)")
    protected List<WebElement> scanCardTitles;
    @FindBy(css = ".results > :nth-child(1) .row.blue > .half > :nth-child(2)")
    protected List<WebElement> scanCardValues;
    @FindBy(css = ".table-body > div > .item.question")
    protected List<WebElement> questionnaireTitles;
    @FindBy(css = ".table-body > div > .answer.item")
    protected List<WebElement> questionnaireValues;
    @FindBy(css = ".cases-list > :nth-child(1) > .case-header > .descr > span:nth-child(2)")
    protected WebElement caseNumber1th;
    @FindBy(css = ".cases-list > :nth-child(2) > .case-header > .descr > span:nth-child(2)")
    protected WebElement caseNumber2th;
    @FindBy(css = ".cases-list > :nth-child(3) > .case-header > .descr > span:nth-child(2)")
    protected WebElement caseNumber3th;
    @FindBy(css = ".cases-list > :nth-child(4) > .case-header > .descr > span:nth-child(2)")
    protected WebElement caseNumber4th;
    @FindBy(css = ".cases-list > :nth-child(5) > .case-header > .descr > span:nth-child(2)")
    protected WebElement caseNumber5th;
    @FindBy(css = ".main-info .left > .data > .subtitle > span:nth-child(2)")
    protected WebElement caseNumberTicket;
    @FindBy(css = ".calendar-header > .month")
    protected WebElement month;
    @FindBy(css = ".react-datepicker__month .react-datepicker__day")
    protected List<WebElement> dayList;
    @FindBy(css = ".cases-list > :nth-child(1) > .info")
    protected WebElement infoCase1th;
    @FindBy(css = ".sidebar .descr > span:nth-child(2)")
    protected List<WebElement> caseIdList;
    @FindBy(css = ".results > :nth-child(1) > :nth-child(1) > :nth-child(1) >.process_header > .title")
    protected WebElement firstTitleInCase;

    @FindBy(css = ".MuiChip-label > .chip_label")
    protected WebElement processLabel;
    @FindBy(css = ".MuiChip-label > .chip_label >.chip_icon")
    protected WebElement processIcon;
    @FindBy(css = ".process_header > a")
    protected List<WebElement> linkToViewResultList;
    @FindBy(css = ".process_header > .title")
    protected List<WebElement> processTitleList;
    @FindBy(css = ".raw-results > .json")
    protected WebElement resultJson;
    @FindBy(css = ".MuiChip-label > .chip_label")
    protected List<WebElement> errorTagScan;


    @Step("check that flow filter appear")
    public String flowStatus(String text) {
        waitForTextToBeInElement(flowFilterStatus, text);
        return getText(flowFilterStatus);
    }

    @Step("check that time frame filter appear")
    public String processTypeStatus(String text) {
        waitForTextToBeInElement(processTypeFilterStatus, text);
        return getText(processTypeFilterStatus);
    }

    @Step("check that status filter appear")
    public String status(String text) {
        waitForTextToBeInElement(statusFilter, text);
        return getText(statusFilter);
    }

    @Step("check that status filter appear")
    public String statusStatus(String text) {
        waitForTextToBeInElement(statusFilterStatus, text);
        return getText(statusFilterStatus);
    }

    @Step("click on share button")
    public void shareListButton() {
        waitForElementToBeClickable(shareListButton);
        click(shareListButton);
    }

    @Step("open time frame filter and choose from the list: {chooseFromTimeFrameList}")
    public void setTimeFrameFilter(String chooseFromTimeFrameList) {
        waitForElementToBeClickable(timeFrameFilter);
        click(timeFrameFilter);
        for (WebElement el : timeFrameList) {
            if (el.getText().equalsIgnoreCase(chooseFromTimeFrameList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(timeFrameFilter);
        click(timeFrameFilter);
        sleep(1000);
    }

    @Step("click on clear filter button")
    public void filterClearTimeFrame() {
        waitForElementToBeClickable(timeFrameFilterClearButton);
        click(timeFrameFilterClearButton);
    }

    @Step("check that date appear in time frame filter")
    public String timeFrameFilterAfterCreateFilter() {
        waitForElementToBeVisibility(timeFrameFilterAfterFilter);
        return getText(timeFrameFilterAfterFilter);
    }

    @Step("check that 'no results found' message appear")
    public String noResultsMessage(String text) {
        waitForTextToBeInElement(noResultsFoundMessage, text);
        return getText(noResultsFoundMessage);
    }

    @Step("open flow filter and select 1 option: {chooseFromFlowList}")
    public void filterByFlow(String chooseFromFlowList) {
        waitForElementToBeClickable(flowFilter);
        click(flowFilter);
        for (WebElement el : flowFilterList) {
            if (el.getText().equalsIgnoreCase(chooseFromFlowList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(flowFilter);
        click(flowFilter);
        sleep(1000);
    }

    @Step("open flow filter and select 2 option: {choose1FromFlowList} and {choose2FromFlowList}")
    public void filterByFlow2Options(String choose1FromFlowList, String choose2FromFlowList) {
        waitForElementToBeClickable(flowFilter);
        click(flowFilter);
        for (WebElement el : flowFilterList) {
            if (el.getText().equalsIgnoreCase(choose1FromFlowList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
            if (el.getText().equalsIgnoreCase(choose2FromFlowList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(flowFilter);
        click(flowFilter);
    }

    @Step("click on clear button of flow filter")
    public void filterClearFlow() {
        waitForElementToBeClickable(flowFilterClearButton);
        click(flowFilterClearButton);
    }

    @Step("open process type filter and select 1 option: {chooseFromProcessTypeList}")
    public void filterByProcessType(String chooseFromProcessTypeList) {
        waitForElementToBeClickable(processTypeFilter);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", processTypeFilter);
        click(processTypeFilter);
        for (WebElement el : processTypeFilterList) {
            if (el.getText().equalsIgnoreCase(chooseFromProcessTypeList)) {
                waitForElementToBeClickable(el);
                executor.executeScript("arguments[0].scrollIntoView(true);", el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(processTypeFilter);
        click(processTypeFilter);
        sleep(1000);
    }

    @Step("open process type filter and select 2 option: {choose1FromProcessTypeList} and {choose2FromProcessTypeList}")
    public void filterByProcessType2Options(String choose1FromProcessTypeList, String choose2FromProcessTypeList) {
        waitForElementToBeClickable(processTypeFilter);
        click(processTypeFilter);
        for (WebElement el : processTypeFilterList) {
            if (el.getText().equalsIgnoreCase(choose1FromProcessTypeList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
            if (el.getText().equalsIgnoreCase(choose2FromProcessTypeList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(processTypeFilter);
        click(processTypeFilter);
    }

    @Step("click on clear button of process type filter")
    public void filterClearProcessType() {
        waitForElementToBeClickable(processTypeFilterClearButton);
        click(processTypeFilterClearButton);
    }

    @Step("click on clear button of status filter")
    public void filterClearStatus() {
        waitForElementToBeClickable(statusClearButton);
        click(statusClearButton);
    }

    @Step("open status filter and select 1 option: {chooseFromStatusList}")
    public void filterByStatus(String chooseFromStatusList) {
        waitForElementToBeClickable(statusFilter);
        click(statusFilter);
        for (WebElement el : statusFilterList) {
            if (el.getText().equalsIgnoreCase(chooseFromStatusList)) {
                waitForElementToBeClickable(el);
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].scrollIntoView(true);", el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(statusFilter);
        click(statusFilter);
        sleep(1000);
    }

    @Step("open status filter and select 2 option: {choose1FromStatusList} and {choose2FromStatusList}")
    public void filterByStatus2Options(String choose1FromStatusList, String choose2FromStatusList) {
        waitForElementToBeClickable(statusFilter);
        click(statusFilter);
        for (WebElement el : statusFilterList) {
            if (el.getText().equalsIgnoreCase(choose1FromStatusList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
            if (el.getText().equalsIgnoreCase(choose2FromStatusList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(statusFilter);
        click(statusFilter);
    }

    @Step("type filter by contain text: {containText}")
    public void filterByContainText(String containText) {
        waitForPageFinishLoading();
        waitForList(casesList);
        scrollToElement(searchField);
        waitForElementToBeClickable(searchField);
        fillText(searchField, containText);
    }

    @Step("check the name of case appear")
    public String caseNameFirstResultListCases(String text) {
        waitForTextToBeInElement(firstNameCaseInCaseList, text);
        return getText(firstNameCaseInCaseList);
    }

    @Step("clear contain text field")
    public void clearContainText() {
        waitForElementToBeClickable(containTextField);
        clear(containTextField);
    }

    @Step("check that appear message after click on share button")
    public String shareListMessage(String text) {
        waitForTextToBeInElement(shareListMessage, text);
        return getText(shareListMessage);
    }

    @Step("Return the value that displayed under the title: {title}")
    public String results(String idType, String title) {
        String result = null;
        waitForPageFinishLoading();
        for (int i = 0; i < scanCardTitles.size(); i++) {
            switch (idType) {
                case "cheque":
                    Assert.assertEquals(scanCardTitles.size(), 4);
                    break;
                case "bio id":
                case "blue id":
                    Assert.assertEquals(scanCardTitles.size(), 8);
                    break;
                case "green id":
                    Assert.assertEquals(scanCardTitles.size(), 7);
                    break;
                case "dl":
                    Assert.assertEquals(scanCardTitles.size(), 10);
                    break;
                case "card capture":
                case "bio id (old service)":
                    Assert.assertEquals(scanCardTitles.size(), 6);
                    break;
                case "card detector":
                    assert scanCardTitles.size() == 10;
                    break;
                case "questionnaire":
                    assert scanCardTitles.size() == 11;
                    break;
                case "MRZ":
                    Assert.assertEquals(scanCardTitles.size(), 9);
                    break;
            }
            if (getText(scanCardTitles.get(i)).equalsIgnoreCase(title)) {
                waitForTextToBeInElement(scanCardTitles.get(i), title);
                waitForList(scanCardValues);
                scrollToElement(scanCardValues.get(i));
                result = getText(scanCardValues.get(i));
                break;
            }
        }
        return result;
    }

    @Step("Check questionnaire results")
    public boolean questionnaireResults(String title, String value) {
        waitForPageFinishLoading();
        waitForList(questionnaireTitles);
        waitForList(questionnaireValues);
        for (int i = 0; i < questionnaireTitles.size(); i++) {
            WebElement el = questionnaireTitles.get(i);
            String key = getText(el);
            if (key.contains(title)) {
                WebElement field = questionnaireValues.get(i);
                getText(field).equalsIgnoreCase(value);
                Assert.assertTrue(getText(field).contains(value));
                break;
            }
        }
        return true;
    }

    @Step("Enter to first case")
    public void enterToFirstCase(String text) {
        waitForPageFinishLoading();
        waitForTextToBeInElement(case1th, text);
        scrollToElement(case1th);
        click(case1th);
        waitForElementToBeVisibility(firstTitleInCase);
    }

    @Step("Check that value of case number")
    public String caseNumber1th() {
        waitForElementToBeVisibility(caseNumber1th);
        return getText(caseNumber1th);
    }

    @Step("Check that value of case number")
    public String caseNumber2th() {
        waitForElementToBeVisibility(caseNumber2th);
        return getText(caseNumber2th);
    }

    @Step("Check that value of case number")
    public String caseNumber3th() {
        waitForElementToBeVisibility(caseNumber3th);
        return getText(caseNumber3th);
    }

    @Step("Check that value of case number")
    public String caseNumber4th() {
        waitForElementToBeVisibility(caseNumber4th);
        return getText(caseNumber4th);
    }

    @Step("Check that value of case number")
    public String caseNumber5th() {
        waitForElementToBeVisibility(caseNumber5th);
        return getText(caseNumber5th);
    }


    @Step("Check that value of case number appear in ticket case")
    public String caseNumberTicket() {
        waitForElementToBeVisibility(caseNumberTicket);
        return getText(caseNumberTicket);
    }

    @Step("Click on case1th")
    public void case1th() {
        waitForElementToBeClickable(case1th);
        click(case1th);
    }

    @Step("Click on case2th")
    public void case2th() {
        waitForElementToBeClickable(case2th);
        click(case2th);
    }

    @Step("Click on case3th")
    public void case3th() {
        waitForElementToBeClickable(case3th);
        click(case3th);
    }

    @Step("Click on case4th")
    public void case4th() {
        waitForElementToBeClickable(case4th);
        click(case4th);
    }

    @Step("Click on case5th")
    public void case5th() {
        waitForElementToBeClickable(case5th);
        click(case5th);
    }

    @Step("Check the value of month")
    public String month() {
        waitForElementToBeVisibility(month);
        return getText(month);
    }

    @Step("Choose date")
    public void chooseDate(String chooseFromTimeFrameList, String chooseOldDay, String chooseFutureDay, String
            chooseMonth) {
        waitForElementToBeClickable(timeFrameFilter);
        click(timeFrameFilter);
        for (WebElement el : timeFrameList) {
            if (el.getText().equalsIgnoreCase(chooseFromTimeFrameList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        while (month() != null) {
            if (getText(month).equalsIgnoreCase(chooseMonth)) {
                for (WebElement el : dayList) {
                    if (el.getText().equalsIgnoreCase(chooseOldDay)) {
                        waitForElementToBeClickable(el);
                        el.click();
                        break;
                    }
                }
                for (WebElement el : dayList) {
                    if (el.getText().equalsIgnoreCase(chooseFutureDay)) {
                        waitForElementToBeClickable(el);
                        el.click();
                        break;
                    }
                }
                break;
            } else {
                click(previousMonthButton);
            }
        }
        click(timeFrameFilter);
    }

    @Step("Check the value of infoCase1th")
    public String infoCase1th(String text) {
        waitForTextToBeInElement(infoCase1th, text);
        return getText(infoCase1th);
    }

    @Step("Run API request that return the cases that appear in the first page")
    public List<Integer> getValueFromResponse() {
        RestAssured.baseURI = "https://btrustqa.scanovate.com/";
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer 4122ba73-3304-4b9d-a958-aa07f34c4dc8").build();
        RestAssured.requestSpecification = requestSpecification;
        RestAssured.defaultParser = Parser.JSON;
        Response response = given()
                .body("{\"flows\":[],\"processes\":[],\"status\":[],\"text\":\"\",\"page\":1}")
                .log()
                .all()
                .when()
                .post("https://btrustqa.scanovate.com/api/cases/filter")
                .then()
                .extract()
                .response();
        return response.path("data.cases.caseId");
    }

    @Step("Return the cases that appear in the first page")
    public List<Integer> caseIdList() {
        ArrayList<Integer> list = new ArrayList<>();
        waitForElementToBeVisibility(case1th);
        for (WebElement el : caseIdList) {
            list.add(Integer.parseInt(el.getText()));
        }
        return list;
    }

    @Step("Check is statusFilterIsDisplayedOrNot")
    public boolean statusFilterIsDisplayedOrNot() {
        try {
            waitForElementToBeVisibility(statusFilter);
            isDisplayed(statusFilter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Enter to view result of the process and return the json")
    public boolean jsonResult(String title) {
        waitForPageFinishLoading();
        waitForList(processTitleList);
        for (int i = 0; i <= processTitleList.size(); i++) {
            if (getText(processTitleList.get(i)).equalsIgnoreCase(title)) {
                waitForList(linkToViewResultList);
                waitForElementToBeClickable(linkToViewResultList.get(i));
                click(linkToViewResultList.get(i));
                break;
            }
        }
        moveToNewWindow();
        waitForPageFinishLoading();
        waitForElementToBeVisibility(resultJson);
        json = getText(resultJson);
        System.out.println(json);
        System.out.println("success: " + (boolean) JsonPath.parse(json).read("$.success"));
        Boolean bla = JsonPath.parse(json).read("$.success");
        return bla;
    }

    @Step("return the process label")
    public String getProcessLabel(String labelText) {
        waitForTextToBeInElement(processLabel, labelText);
        return getText(processLabel);
    }

    public boolean isProcessIconExist() {
        try {
            waitForElementToBeVisibility(processIcon);
            if (processIcon.isDisplayed())
                return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Step("Return the text of error tag scan")
    public String errorTagError(String tagScan) {
        String error = null;
        waitForList(errorTagScan);
        for (WebElement el : errorTagScan) {
            if (getText(el).equalsIgnoreCase(tagScan)) {
                error = getText(el);
                break;
            }
        }
        return error;
    }
}