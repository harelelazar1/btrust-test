package btrust.btrustOne.client.cases.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CasePage extends BasePage {
    public CasePage(WebDriver driver) {
        super(driver);
    }

    protected String name = null;
    protected int listSize;
    protected int tasksLastElement;
    protected String workLog;
    protected String termOfBusinessSelectedDate;
    String id;


    //tasks tab
    @FindBy(css = ".tasks-container .tabs-content>:nth-child(2) [type='button'],.tasks-container .tabs-content>:nth-child(3)")
    protected List<WebElement> tabContentName;
    @FindBy(css = ".TableHeader-module__container__Mfwed .TableHeader-module__headCell__2-udR.false")
    protected List<WebElement> fieldTitleName;
    @FindBy(css = ".Enrollments-module__row__39kTl >.Enrollments-module__item__4QupN")
    protected List<WebElement> fieldName;
    @FindBy(css = "div.Enrollments-module__emptyData__3KcRp")
    protected WebElement noOneLoggedMessage;
    @FindBy(css = "a.Enrollments-module__row__39kTl>:nth-child(4)")
    protected WebElement enrollmentStatus;



    @FindBy(css = ".Title-module__case-title__VA0Hq>a")
    protected WebElement entityName;
    @FindBy(css = ".Title-module__case-description__1FIFU :nth-child(3) span")
    protected WebElement caseId;
    @FindBy(css = ".case-text > .case-title")
    protected WebElement caseName;
    @FindBy(css = "[role='tablist'] > button")
    protected List<WebElement> tabList;
    @FindBy(css = ".top-tasks-info>:nth-child(2)  [type='button']")
    protected List<WebElement> actionInPage;
    @FindBy(css = ".wrapper-container > :nth-child(1) > :nth-child(1)")
    protected WebElement identificationDataTitle;
    @FindBy(css = "li:nth-of-type(1) > .MuiTimelineContent-root.MuiTimelineItem-content > .content  .header")
    protected WebElement mandatoryTasksContainer; // the headers for the mandatory tasks container
    @FindBy(css = ".MuiTimelineItem-missingOppositeContent:nth-of-type(1)  .row-name")
    protected List<WebElement> mandatoryTaskNamesList; //  mandatory tasks name
    @FindBy(css = ".MuiTimelineItem-missingOppositeContent:nth-of-type(2)  .row-name")
    protected List<WebElement> nonMandatoryTaskNamesList; //non mandatory tasks name
    @FindBy(css = ".MuiTimelineItem-missingOppositeContent:nth-of-type(3)  .row-name")
    protected List<WebElement> closingTheCaseTask; // the last task - closing the case
    @FindBy(css = ".FatchaTasks-module__container__n9XFD .row-item.row-status")
    protected List<WebElement> taskStatusList; // status of task: awaits to client response, waiting for review, done etc
    @FindBy(css = ".tasks-container .progress .text")
    protected WebElement openTasksProgressBar; // progress bar looks like that - "0/9 Open tasks"
    @FindBy(css = ".MuiTab-wrapper > span .number")
    protected WebElement redBubbleRemainingTasks; // the red bubble above the task name"
    @FindBy(css = "ul > li:nth-child(3) .rows > button")
    protected WebElement closingStatus; // the last status button - closing the case
    @FindBy(css = ".data .status > .main")
    protected WebElement inTaskStatus; // the status in the task in side of individual task
    @FindBy(css = ".tabs-content .TasksTable-module__header-item__2yHSV > span")
    protected List<WebElement> tasksTitleNameList;
    @FindBy(css = " .case-task-row :nth-child(3)")
    protected List<WebElement> tasksFieldNameList;
    @FindBy(css = ".task-version .version-info")
    protected WebElement requestSentBubble; //inside the task there is a bubble with the text request sent - the default one
    @FindBy(css = ".worklog > div > .text")
    protected List<WebElement> workLogList; // work log list of the individual task
    @FindBy(css = ".tabs-content .TasksTable-module__header-item__2yHSV")
    protected List<WebElement> tasksList;
    @FindBy(css = ".rows > .case-task-row > .row-item.row-name.disabled-false")
    protected List<WebElement> tasksNameList;
    @FindBy(css = "[role='tabpanel']  [type= button]")
    protected WebElement reviewDocumentButton;
    @FindBy(css = "div[role='tabpanel'] > .send")
    protected WebElement docNameStatusSent;
    @FindBy(css = "div[role='tabpanel'] > .failed")
    protected WebElement docNameStatusRejected;
    @FindBy(css = "div[role='tabpanel'] > .approved")
    protected WebElement docNameStatusApproved;
    @FindBy(css = ".task-item .MuiTab-wrapper > span")
    protected WebElement workLogTitle;
    @FindBy(css = "[type = button].reject")
    protected WebElement returnToClientButton; //return to client button after reviewing doc
    @FindBy(css = "[type = button].approve")
    protected WebElement approveDocumentButton; //approve  doc button
    @FindBy(css = ".document-review .actions>[type='button']")
    protected List<WebElement> ReviewDocumentButtons;
    @FindBy(css = ".actions .close-popup")
    protected WebElement closePopup;
    @FindBy(css = ".expanded-true")
    protected WebElement tasksContainer; // mandatory/non mandatory/closing the case  tasks container
    @FindBy(css = "textarea[placeholder='Instruct the client how to refill the form']")
    protected WebElement messageToClientTextBox;
    @FindBy(css = ".show-hide-old > [type =button]")
    protected WebElement showOldVersionButton;
    @FindBy(css = "li .title")
    protected List<WebElement> titleList;
    @FindBy(css = "=.approve-btns .main[typebutton]")
    protected WebElement approveAndCloseTaskButton;
    @FindBy(css = ".breadcrumbs .wrapper span:nth-of-type(2)")
    protected WebElement taskNumber;
    @FindBy(css = ".wrapper span[role='tabpanel']")
    protected WebElement backToTaskListButton;

    //score assessment task
    @FindBy(css = ".actions .main[type=button]")
    protected WebElement completeButton;
    @FindBy(css = ".selectors-container .item:nth-of-type(1) [focusable]")
    protected WebElement settlementScoreArrowButton;
    @FindBy(css = ".default__menu-list .default__option")
    protected List<WebElement> settlementScoreListBox;
    @FindBy(css = ".selectors-container .item:nth-of-type(2) [focusable]")
    protected WebElement tradingDeskScoreArrowButton;
    @FindBy(css = ".default__menu-list .default__option")
    protected List<WebElement> tradingDeskScoreListBox;
    @FindBy(css = ".small [focusable]")
    protected WebElement adverseRiskScoreArrowButton;
    @FindBy(css = "[class] [tabindex='-1']")
    protected List<WebElement> adverseRiskScoreListBox;
    @FindBy(css = ".checkbox-container")
    protected WebElement confirmCheckbox; // checkbox option
    @FindBy(css = "div:nth-of-type(1) > .selector > .error")
    protected WebElement settlementScoreErrorMessage;
    @FindBy(css = "div:nth-of-type(2) > .selector > .error")
    protected WebElement tradingDeskScoreErrorMessage;
    @FindBy(css = "div:nth-of-type(4) > .selector > .error")
    protected WebElement termOfBusinessErrorMessage;
    @FindBy(css = ".note[type=text]")
    protected WebElement adverseEventNote;
    @FindBy(css = ".error-false [focusable='false']")
    protected WebElement termOfBusinessCalendar;
    @FindBy(css = ".select-button > .selected-date")
    protected WebElement selectedDate;
    @FindBy(css = "[role='listbox'] > div > div")
    protected List<WebElement> dayInCalender;
    @FindBy(css = ".ComplateTaskConfirmation .actions .main[type=button]")
    protected WebElement confirmButton;
    @FindBy(css = ".toogler [type=button]")
    protected List<WebElement> isdaInPlaceButtons;
    @FindBy(css = "[type='checkbox']")
    protected WebElement confirmWCAdverseEventsButton;
    @FindBy(css = ".scores > div")
    protected List<WebElement> scoreAssessmentResults;

    //closing the case
    @FindBy(css = ".selector  [class='default__value-container css-1hwfws3']")
    protected WebElement entityRecommendation; // approve, suspend and reject
    @FindBy(css = ".default__menu-list [tabindex='-1']")
    protected List<WebElement> entityRecommendationList;
    @FindBy(css = ".actions [type=button]")
    protected WebElement completeButtonClosingTheCase; // complete button
    @FindBy(css = ".close-case .data > .error:nth-of-type(3)")
    protected WebElement wcErrorMessage; // the error message under the checkbox of I confirm that I have performed adverse events checks at WC*
    @FindBy(css = ".error.margin")
    protected WebElement recommendationErrorMessage; //the error message under the  entity recommendation
    @FindBy(css = "[type = button].main")
    protected WebElement confirmCloseTheCaseButton;
    @FindBy(css = "textarea[placeholder]")
    protected WebElement reasonTextBox;
    @FindBy(css = ".ComplateTaskConfirmation .content")
    protected WebElement suspendConfirmationPhrase;
    @FindBy(css = ".disabled-content .comment")
    protected WebElement reasonOutcome;
    @FindBy(css = "[class='default__value-container css-1hwfws3']")
    protected WebElement headOfInvestmentDecision;
    @FindBy(css = ".default__menu-list [tabindex='-1']")
    protected List<WebElement> headOfInvestmentRecommendationList;
    @FindBy(css = ".head-decision .status-value")
    protected WebElement headOfInvestmentFinalDecision;

    //questionnaire task
    @FindBy(css = ".PageActions_container> [type=button].green")
    protected WebElement approveAndContinue;
    @FindBy(css = ".PageActions_container [type='button'].red")
    protected WebElement rejectAndCommentButton;
    @FindBy(css = ".questionnaire-pagination [type='button']:nth-child(3)")
    protected WebElement rightArrowPagination;
    @FindBy(css = ".approve-document[type=button]")
    protected WebElement approveQuestionnaireDoc;
    @FindBy(css = ".PageActions_comment-container [placeholder]")
    protected WebElement rejectAndCommentTextBox;
    @FindBy(css = ".PageActions_comment-container .btns .main[type=button]")
    protected WebElement saveAndContinueButton;
    @FindBy(css = ".reject-document[type = button]")
    protected WebElement returnToClientButtonQuestionnaire;
    @FindBy(css = ".QuestionnaireRejection_container .content")
    protected WebElement returnToClientQuestionnaireSummary;
    @FindBy(css = ".approve-btns .main[type=button]")
    protected WebElement sendToClientButtonQuestionnaire;

    //data tab
    @FindBy(css = ".flex-items:nth-child(1) .item:nth-child(1) .value")
    protected WebElement legalName;
    @FindBy(css = ".flex-items > div:nth-child(2) > .value")
    protected WebElement leiCode;
    @FindBy(css = ".flex-items.margin-top > .item > .value")
    protected WebElement termOfBusinessDateDataTab;
    @FindBy(css = ".assessments > div:nth-child(1) > .value")
    protected WebElement settlementScore;
    @FindBy(css = "div:nth-child(6) > svg[role='presentation'] > path")
    protected WebElement mifidScope;
    @FindBy(css = "div:nth-child(7) > svg[role='presentation'] > path")
    protected WebElement isdaInPlace;
    @FindBy(css = ".assessments > div:nth-child(2) > .value")
    protected WebElement tradingDeskScore;
    @FindBy(css = ".assessments div:nth-child(3) > .value")
    protected WebElement fitchRating;
    @FindBy(css = ".assessments div:nth-child(4) > .value")
    protected WebElement moodysRating;
    @FindBy(css = ".assessments div:nth-child(5) > .value")
    protected WebElement spRating;
    @FindBy(css = ".assessments .events")
    protected WebElement wcAdverseEvents;
    @FindBy(css = ".contact-information .main > .main-block")
    protected WebElement mainContact;
    @FindBy(css = ".contacts-list > div:nth-child(1)")
    protected WebElement callbackReferent;
    @FindBy(css = ".contacts-list > div:nth-child(2)")
    protected WebElement callbackReferent2;
    @FindBy(css = ".adresses .adress-block")
    protected WebElement headquarterAddress;

    //contact info in data tab
    @FindBy(css = ".contact-popup .text")
    protected WebElement newContactTitle;
    @FindBy(css = ".flex-items > div:nth-child(1) > input")
    protected WebElement firstNameNewContact;
    @FindBy(css = ".flex-items > div:nth-child(2) > input")
    protected WebElement lastNameNewContact;
    @FindBy(css = ".flex-items > div:nth-child(3) > input")
    protected WebElement positionNewContact;
    @FindBy(css = ".content .item:nth-child(4) [type]")
    protected WebElement businessPhoneNewContact;
    @FindBy(css = ".content .item:nth-child(5) [type]")
    protected WebElement secondPhoneNewContact;
    @FindBy(css = ".content .item:nth-child(6) [type]")
    protected WebElement faxPhoneNewContact;
    @FindBy(css = ".content > .item:nth-child(2) input")
    protected WebElement emailNewContact;
    @FindBy(css = "[type='checkbox']")
    protected WebElement callbackReferentNewContact;
    @FindBy(css = ".btns .main[type=button]")
    protected WebElement createContactButton;
    @FindBy(css = ".new-contact-btn > button[type='button']")
    protected WebElement newContactButton;
    @FindBy(css = ".contacts-list .main-block")
    protected List<WebElement> callbackReferentList;
    @FindBy(css = ".ExpansionPanelDetails_content .main .main-block")
    protected List<WebElement> mainContactsList;
    @FindBy(css = ".contacts-list [tabindex]")
    protected WebElement editDeleteMenuCallbackReferent;
    @FindBy(css = ".main [tabindex]")
    protected WebElement editAndDeleteMenuMainContactButton;
    @FindBy(css = "div:nth-child(3)  ul[role='menu'] >li")
    protected List<WebElement> editDeleteMenuList;
    @FindBy(css = ".contact-information.isInitial-undefined")
    protected WebElement contactInfoContainer;


    // documents tab
    @FindBy(css = ".MuiTableBody-root > tr")
    protected List<WebElement> documentsList;
    @FindBy(css = ".MuiTableBody-root")
    protected WebElement documentsListTable;
    @FindBy(css = "[role='presentation'] .text")
    protected WebElement reviewDocumentTitle;
    @FindBy(css = ".title [focusable]")
    protected WebElement closeReviewDocPopup;
    @FindBy(css = ".react-pdf__Page > .react-pdf__Page__canvas")
    protected WebElement documentData;
    @FindBy(css = ".form")
    protected WebElement questionnaireForm;
    //audit logs
    @FindBy(css = ".rows > button> .disabled-false.row-id.row-item")
    protected List<WebElement> taskIdList;
    @FindBy(css = ".worklog > .log-item")
    protected List<WebElement> auditLogsList;


    @Step("Check If Contacts Enrollment Row Display")
    public Boolean checkIfContactsEnrollmentRowDisplay() {
        try {
            waitForPageFinishLoading();
            if (isDisplayed(enrollmentStatus)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("Return the text of case name")
    public String caseName() {
        waitForElementToBeVisibility(caseName);
        return getText(caseName);
    }

    @Step("Return the case id")
    public String caseId() {
        sleep(3000);
        waitForElementToBeVisibility(caseId);
        id = getText(caseId).replaceFirst("#", "");
        return id;
    }

    @Step("Check task id logs between tasks tab and audit logs tab")
    public boolean taskIdLogsValidation() {
        try {

            chooseTab("Tasks");
            waitForElementToBeVisibility(tasksContainer);

            //all task id numbers
            ArrayList<String> taskIdArray = new ArrayList();
            for (WebElement taskId : taskIdList) {
                taskIdArray.add(getText(taskId));
            }

            chooseTab("Audit Logs");

            for (String taskId : taskIdArray) {
                for (WebElement auditLogs : auditLogsList) {
                    if (getText(auditLogs).contains((taskId) + " wascreatedbySystem")) {
                    }
                }
            }

            for (String taskId : taskIdArray) {
                for (WebElement auditLogs : auditLogsList) {
                    if (getText(auditLogs).contains(taskId + " was marked asDonebySystem")) {
                    }
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Step("Choose tab")
    public void chooseTab(String chooseFromTabList) {
        waitForList(tabList);
        for (WebElement el : tabList) {
            if (getText(el).contains(chooseFromTabList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        waitForElementToBeVisibility(caseName);
    }


    @Step("Check If Tab Display")
    public boolean checkIfTabDisplay(String tabName) {
        try {
            waitForList(tabList);
            for (WebElement el : tabList) {
                if (getText(el).contains(tabName)) {
                    scrollToElement(el);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Step("Check if identification Data Title Is Displayed")
    public boolean identificationDataTitleIsDisplayed(String text) {
        try {
            waitForTextToBeInElement(identificationDataTitle, text);
            isDisplayed(identificationDataTitle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Step("Click Entity name")
    public void clickOnEntityName() {
        scrollToElement(entityName);
        waitForElementToBeClickable(entityName);
        click(entityName);
    }

    @Step("Choose day in calender")
    public String chooseDayInCalender(String day) {
        waitForElementToBeClickable(termOfBusinessCalendar);
        click(termOfBusinessCalendar);
        for (WebElement el : dayInCalender) {
            if (getText(el).equalsIgnoreCase(day)) {
                waitForElementToBeClickable(el);
                click(el);
                click(termOfBusinessCalendar);
                break;
            }
        }
        termOfBusinessSelectedDate = getText(selectedDate);
        return termOfBusinessSelectedDate;
    }

    @Step("click on return to client button")
    public void returnToClientButton() {
        waitForElementToBeClickable(returnToClientButton);
        click(returnToClientButton);
    }

    @Step("Click close popup")
    public void clickClosePopup() {
        waitForElementToBeClickable(closePopup);
        click(closePopup);
    }

    @Step("check if review document buttons display")
    public boolean checkReviewDocumentButtonDisplay(String buttonName) {
        try {
            waitForElementToBeClickable(ReviewDocumentButtons.get(0));
            for (WebElement el : ReviewDocumentButtons) {
                if (getText(el).equalsIgnoreCase(buttonName)) {
                    waitForElementToBeClickable(el);
                    scrollToElement(el);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("click reject and comment button")
    public void rejectAndCommentButton() {
        waitForElementToBeClickable(rejectAndCommentButton);
        click(rejectAndCommentButton);
    }

    @Step("click on send to client button")
    public void sendToClientButton() {
        waitForElementToBeClickable(sendToClientButtonQuestionnaire);
        click(sendToClientButtonQuestionnaire);
    }

    @Step("click on approve doc button")
    public void approveDocumentButton() {
        waitForElementToBeClickable(approveDocumentButton);
        click(approveDocumentButton);
    }

    @Step("click on confirm WC Adverse Events Button")
    public void confirmWCAdverseEventsButton() {
        //can put here wait for the button - checkbox
        click(confirmWCAdverseEventsButton);
    }

    @Step("click on approve and close task button")
    public void approveAndCloseTaskButton() {
        waitForElementToBeVisibility(approveAndCloseTaskButton);
        click(approveAndCloseTaskButton);
    }

    @Step("click on the Task")
    public void clickOnTask(String text) {
        waitForElementToBeClickable(mandatoryTasksContainer);
        chooseElementFromList(tasksList, text);
    }

    @Step("click on back to task list")
    public void backToTaskListButton() {
        waitForElementToBeClickable(backToTaskListButton);
        click(backToTaskListButton);
    }

    @Step("complete Button in score assessment task")
    public void completeButton() {
        waitForElementToBeClickable(completeButton);
        click(completeButton);
    }

    @Step("confirm Button in score assessment task")
    public void confirmButton() {
        waitForElementToBeClickable(confirmButton);
        click(confirmButton);
    }

    @Step("complete button in Close The Case")
    public void completeButtonClosingTheCase() {
        waitForElementToBeClickable(completeButtonClosingTheCase);
        click(completeButtonClosingTheCase);
    }

    @Step("confirm Close The Case Button in score assessment task")
    public void confirmCloseTheCaseButton() {
        waitForElementToBeClickable(confirmCloseTheCaseButton);
        click(confirmCloseTheCaseButton);
    }

    @Step("click review document button")
    public void reviewDocumentButton() {
        waitForElementToBeClickable(reviewDocumentButton);
        click(reviewDocumentButton);
    }

    @Step("confirm checkbox is selected or not")
    public boolean confirmCheckboxIsSelected() {
        try {
            waitForElementToBeVisibility(confirmCheckbox);
            isSelected(confirmCheckbox);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("verify the case id num")
    public boolean caseIdNum(String caseNum) {
        try {
            waitForElementToBeVisibility(caseId);
            if (getText(caseId).contains(caseNum)) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Step("verify if the tasks containers is display")
    public boolean tasksContainerDisplay() {
        waitForPageFinishLoading();
        try {
            if (isDisplayed(tasksContainer)) {
                waitForElementToBeClickable(tasksContainer);
                isDisplayed(tasksContainer);
                return true;
            } else {
                waitForElementToBeClickable(tasksContainer);
                click(tasksContainer);
                isDisplayed(tasksContainer);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Step("status of the work logs")
    public String workLogList(String text) {
        waitForTextToBeInElement(workLogTitle, "Work Log");
        workLog = taskListCheck(workLogList, text);
        return workLog;
    }

    @Step("check the tasks list names")
    public String taskListCheck(List<WebElement> list, String inputFromList) {
        for (WebElement el : list) {
            waitForElementToBeVisibility(el);
            if (getText(el).contains(inputFromList)) {
                waitForElementToBeVisibility(el);
                name = getText(el);
                break;
            }
        }
        return name;
    }

    @Step("Mandatory tasks name page elements exists")
    public boolean assertMandatoryTasksNameCheck(String TaskName) {
        try {
            waitForElementToBeVisibility(openTasksProgressBar);
            taskListCheck(mandatoryTaskNamesList, TaskName);

            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Mandate and workflow page elements exists")
    public boolean assertNonMandatoryTasksNameCheck(String TaskName) {
        try {
            waitForElementToBeVisibility(openTasksProgressBar);
            taskListCheck(nonMandatoryTaskNamesList, TaskName);

            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("check 'closing the task' exist")
    public boolean closingTheCaseTaskNameCheck(String TaskName) {
        try {
            waitForElementToBeVisibility(mandatoryTasksContainer);
            taskListCheck(closingTheCaseTask, TaskName);

            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }

    }

    @Step("each form have task number")
    public String taskNumber() {
        waitForElementToBeVisibility(taskNumber);
        return getText(taskNumber);
    }

    @Step("count the tasks num - should be 9 - the progress bar 0/9")
    public String progressTasksBarNum(String text) {
        waitForTextToBeInElement(openTasksProgressBar, text);
        return getText(openTasksProgressBar);
    }

    @Step("count the remaining  tasks num in the red bubble - start at 9 ")
    public String redBubbleRemainingTasks(String text) {
        waitForTextToBeInElement(redBubbleRemainingTasks, text);
        return getText(redBubbleRemainingTasks);
    }

    @Step("doc name status bubble  - sent")
    public String docNameStatusSent(String text) {
        waitForTextToBeInElement(docNameStatusSent, text);
        return getText(docNameStatusSent);
    }

    @Step("doc name status bubble  - rejected")
    public String docNameStatusRejected(String text) {
        waitForTextToBeInElement(docNameStatusRejected, text);
        return getText(docNameStatusRejected);
    }

    @Step("doc name status bubble  - approved")
    public String docNameStatusApproved(String text) {
        waitForTextToBeInElement(docNameStatusApproved, text);
        return getText(docNameStatusApproved);
    }

    @Step("status Awaits client response appear in the task")
    public String awaitToClientResponseStatus(String text, int placeInList) {
        waitForElementToBeVisibility(openTasksProgressBar);
        taskListCheck(taskStatusList, text);
        return getText(taskStatusList.get(placeInList));
    }


    @Step("check the default status of the tsk in individual task")
    public String inTaskStatus(String text) {
        waitForTextToBeInElement(inTaskStatus, text);
        return getText(inTaskStatus);
    }

    @Step("check the default request bubble")
    public String requestSentBubble() {
        waitForElementToBeVisibility(requestSentBubble);
        return getText(requestSentBubble);
    }

    @Step("check the phrase that appear if you suspend the case")
    public String suspendConfirmationPhrase() {
        waitForElementToBeVisibility(suspendConfirmationPhrase);
        return getText(suspendConfirmationPhrase);
    }

    @Step("check the reason appears after user wrote it in the reason text box ")
    public String reasonOutcome() {
        waitForElementToBeVisibility(reasonOutcome);
        return getText(reasonOutcome);
    }

    @Step("head Of Investment Final Decision output")
    public String headOfInvestmentFinalDecision() {
        waitForElementToBeVisibility(headOfInvestmentFinalDecision);
        return getText(headOfInvestmentFinalDecision);
    }

    @Step("score assessment task - make sure it is in To Do status")
    public String toDoStatusScoreAssessment(String text, int placeInList) {
        waitForElementToBeVisibility(openTasksProgressBar);
        taskListCheck(taskStatusList, text);
        return getText(taskStatusList.get(placeInList));
    }

    @Step("Click on task from tasks list")
    public void chooseTaskByStatus(String workflow) {
        waitForPageFinishLoading();
        for (WebElement el : taskStatusList) {
            if (getText(el).equalsIgnoreCase(workflow)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("the tasks Containers should be Display the beginning of the page")
    public String closingTheTaskStatusName() {
        listSize = taskStatusList.size();
        tasksLastElement = listSize - 1;
        waitForElementToBeVisibility(taskStatusList.get(tasksLastElement));
        return getText(taskStatusList.get(tasksLastElement));
    }

    @Step("closing status should be disable at the beginning")
    public boolean closingStatusIsEnabled() {
        try {
            waitForElementToBeClickable(closingStatus);
            isEnabled(closingStatus);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Test the elements that appears in the tasks list")
    public void chooseElementFromList(List<WebElement> list, String text) {
        for (WebElement el : list) {
            if (getText(el).equalsIgnoreCase(text)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Verify settlement score list")
    public String settlementScoreListCheck(String score) {
        click(settlementScoreArrowButton);
        for (WebElement el : settlementScoreListBox) {
            waitForElementToBeVisibility(el);
            if (getText(el).contains(score)) {
                waitForElementToBeVisibility(el);
                name = getText(el);
                click(el);
                break;
            }
        }
        return name;
    }

    @Step("Verify trading list score")
    public String tradingDeskScoreListCheck(String score) {
        click(tradingDeskScoreArrowButton);
        for (WebElement el : tradingDeskScoreListBox) {
            waitForElementToBeVisibility(el);
            if (getText(el).contains(score)) {
                waitForElementToBeVisibility(el);
                name = getText(el);
                click(el);
                break;
            }
        }
        return name;
    }

    @Step("Verify adverse risk list score from low to high")
    public String adverseRiskScore(String score) {
        click(adverseRiskScoreArrowButton);
        for (WebElement el : adverseRiskScoreListBox) {
            waitForElementToBeVisibility(el);
            if (getText(el).contains(score)) {
                waitForElementToBeVisibility(el);
                name = getText(el);
                click(el);
                break;
            }
        }
        return name;
    }

    @Step("ISDA in place? yes/no option")
    public void isdaInPlaceButtons(String YesNo) {
        for (WebElement el : isdaInPlaceButtons) {
            if (getText(el).contains(YesNo)) {
                waitForElementToBeClickable(el);
                clickByJS(el); //checking the click with js, in the server i get ElementClickInterceptedException
                break;
            }
        }
    }

    @Step("mandotry feild error message")
    public boolean errorMessageAppearIsDisplayed() {
        try {
            waitForElementToBeClickable(completeButton);
            click(completeButton);
            waitForElementToBeVisibility(settlementScoreErrorMessage);
            isDisplayed(settlementScoreErrorMessage);
            isDisplayed(termOfBusinessErrorMessage);
            isDisplayed(tradingDeskScoreErrorMessage);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("choose task from list")
    public void chooseFromTaskNameList(String taskName) {
        waitForElementToBeVisibility(caseName);
        for (WebElement el : tasksNameList) {
            if (getText(el).contains(taskName)) {
                waitForElementToBeClickable(el);
                clickByJS(el); //checking the click with js, in the server i get ElementClickInterceptedException
                break;
            }
        }
    }

    @Step("choose task from list")
    public void chooseTaskNameFromList(String taskColumn, String taskName) {
        waitForPageFinishLoading();
        for (WebElement el : tasksTitleNameList) {
            if (getText(el).equalsIgnoreCase(taskColumn)) {
                for (WebElement el1 : tasksFieldNameList) {
                    scrollToElement(el1);
                    if (getText(el1).equalsIgnoreCase(taskName)) {
                        waitForElementToBeClickable(el1);
                        click(el1);
                        return;
                    }
                }
            }
        }
    }

    @Step("fill text reject and comment questionnaire text box")
    public String rejectAndCommentTextBox(String text) {
        waitForElementToBeClickable(rejectAndCommentButton);
        click(rejectAndCommentButton);
        waitForElementToBeClickable(rejectAndCommentTextBox);
        fillText(rejectAndCommentTextBox, text);
        sleep(1500); // the sleep here, because the doesn't saves in time and the flow click very fast
        waitForTextToBeInElement(saveAndContinueButton, "Save & continue");
        waitForElementToBeClickable(saveAndContinueButton);
        click(saveAndContinueButton);
        sleep(1500); // the sleep here, because the doesn't saves in time and the flow click very fast

        while (isEnabled(rightArrowPagination)) {
            waitForElementToBeClickable(approveAndContinue);
            click(approveAndContinue);
        }
        waitForElementToBeClickable(returnToClientButtonQuestionnaire);
        clickByJS(returnToClientButtonQuestionnaire);

        String documentRejectedComments = getText(returnToClientQuestionnaireSummary);

        waitForElementToBeClickable(sendToClientButtonQuestionnaire);
        click(sendToClientButtonQuestionnaire);

        return documentRejectedComments;
    }

    @Step("fill text in message to client text box")
    public void messageToClientTextBox(String text) {
        waitForElementToBeVisibility(messageToClientTextBox);
        fillText(messageToClientTextBox, text);
    }

    @Step("fill text in adverse Event Note text box")
    public void adverseEventNote(String text) {
        waitForElementToBeVisibility(adverseEventNote);
        fillText(adverseEventNote, text);
    }

    @Step("wait for show older version button")
    public void showOldVersionButton() {
        waitForElementToBeClickable(showOldVersionButton);
        click(showOldVersionButton);
    }

    @Step("fill text in suspend/reject text box")
    public void reasonTextBox(String text) {
        waitForElementToBeVisibility(reasonTextBox);
        fillText(reasonTextBox, text);
    }

    @Step("choose containers - mandatory/non mandatory/closing the task")
    public void chooseTaskContainers(String containerNames) {
        for (WebElement el : titleList) {
            if (getText(el).equalsIgnoreCase(containerNames)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("check the tasks list names")
    public String scoreAssessmentResults(String scoreResult) {
        for (WebElement el : scoreAssessmentResults) {
            waitForElementToBeVisibility(el);
            if (getText(el).contains(scoreResult)) {
                waitForElementToBeVisibility(el);
                name = getText(el);
                break;
            }
        }
        return name;
    }

    @Step("approve Questionnaire Doc - until approve doc button appear ")
    public void approveQuestionnaireDoc() {
        waitForElementToBeClickable(approveAndContinue);
        while (isEnabled(rightArrowPagination)) {
            waitForElementToBeClickable(approveAndContinue);
            click(approveAndContinue);
        }
        waitForElementToBeClickable(approveQuestionnaireDoc);
        clickByJS(approveQuestionnaireDoc);
        approveAndCloseTaskButton();
    }

    @Step("check the wc error message in 'close the case' task")
    public String wcErrorMessage() {
        waitForElementToBeVisibility(wcErrorMessage);
        return getText(wcErrorMessage);
    }

    @Step("check the recommendation error message in 'close the case' task")
    public String recommendationErrorMessage() {
        waitForElementToBeVisibility(recommendationErrorMessage);
        return getText(recommendationErrorMessage);
    }

    @Step("choose entity Recommendation - approve/suspend/reject the task")
    public void entityRecommendationList(String entityRecommendationInput) {
        waitForElementToBeClickable(entityRecommendation);
        click(entityRecommendation);
        for (WebElement el : entityRecommendationList) {
            if (getText(el).equalsIgnoreCase(entityRecommendationInput)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("choose entity Recommendation - approve/suspend/reject the task")
    public void headOfInvestmentDecision(String entityRecommendationInput) {
        waitForElementToBeClickable(headOfInvestmentDecision);
        click(headOfInvestmentDecision);
        for (WebElement el : headOfInvestmentRecommendationList) {
            if (getText(el).equalsIgnoreCase(entityRecommendationInput)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("legal name in data case tab")
    public String legalName(String text) {
        waitForTextToBeInElement(legalName, text);
        return getText(legalName);
    }

    @Step("lei Code in data case tab")
    public String leiCode(String text) {
        waitForTextToBeInElement(leiCode, text);
        return getText(leiCode);
    }

    @Step("term of business date in data case tab")
    public boolean termOfBusinessDate() {
        try {
            if (getText(termOfBusinessDateDataTab).equals(termOfBusinessSelectedDate))
                return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Step("get attribute of mifid Scope in data case tab")
    public String mifidScope() {
        waitForElementToBeVisibility(mifidScope);
        return getAttribute(mifidScope, "d");
    }

    @Step("get attribute of isda In Place in data case tab")
    public String isdaInPlace() {
        waitForElementToBeVisibility(isdaInPlace);
        return getAttribute(isdaInPlace, "d");
    }


    @Step("get text of settlement Score in data case tab")
    public String settlementScore(String text) {
        waitForTextToBeInElement(settlementScore, text);
        return getText(settlementScore);
    }

    @Step("get text of trading Desk Score in data case tab")
    public String tradingDeskScore(String text) {
        waitForTextToBeInElement(tradingDeskScore, text);
        return getText(tradingDeskScore);
    }

    @Step("get text of fitch Rating in data case tab")
    public String fitchRating(String text) {
        waitForTextToBeInElement(fitchRating, text);
        return getText(fitchRating);
    }

    @Step("get text of moody's Rating in data case tab")
    public String moodysRating(String text) {
        waitForTextToBeInElement(moodysRating, text);
        return getText(moodysRating);
    }

    @Step("get text of s&p Rating in data case tab")
    public String spRating(String text) {
        waitForTextToBeInElement(spRating, text);
        return getText(spRating);
    }

    @Step("get text of wc Adverse Events in data case tab")
    public String wcAdverseEvents(String text) {
        waitForTextToBeInElement(wcAdverseEvents, text);
        return getText(wcAdverseEvents);
    }

    @Step("get text of main Contact in data case tab")
    public String mainContact() {
        waitForElementToBeVisibility(mainContact);
        return getText(mainContact);
    }

    @Step("get text of callback Referent in data case tab")
    public String callbackReferent() {
        waitForElementToBeVisibility(callbackReferent);
        return getText(callbackReferent);
    }

    @Step("get text of callback Referent 2 in data case tab")
    public String callbackReferent2() {
        waitForElementToBeVisibility(callbackReferent2);
        return getText(callbackReferent2);
    }

    @Step("get text of headquarter Address in data case tab")
    public String headquarterAddress() {
        waitForElementToBeVisibility(headquarterAddress);
        return getText(headquarterAddress);
    }

    @Step("Choose document from documents tab")
    public boolean chooseDocumentsToReview(String chooseDoc) {
        try {
            waitForElementToBeVisibility(documentsListTable);
            for (WebElement el : documentsList) {
                if (getText(el).contains(chooseDoc)) {
                    waitForElementToBeClickable(el);
                    click(el);
                    break;
                }
            }
            waitForTextToBeInElement(reviewDocumentTitle, "Review Document");
            if (chooseDoc.equals("Due Diligence questionnaire")) // the questionnaire form looks a bit different
            {
                waitForElementToBeVisibility(questionnaireForm);
                waitForElementToBeClickable(closeReviewDocPopup);
                click(closeReviewDocPopup);
                waitForElementToBeVisibility(documentsListTable);
                return true;
            } else {
                waitForElementToBeVisibility(documentData);
                waitForElementToBeClickable(closeReviewDocPopup);
                click(closeReviewDocPopup);
                waitForElementToBeVisibility(documentsListTable);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Step("fill the data for new contact info in data tab")
    public void fillNewContactPersonInfo(String contactName, String lastName, String position, String phoneNum, String email, String callBackReferent) {
        waitForPageFinishLoading();
        scrollToElement(newContactButton);
        waitForElementToBeClickable(newContactButton);
        clickByJS(newContactButton); //checking the click with js, in the server i get ElementClickInterceptedException
        waitForTextToBeInElement(newContactTitle, "New Contact");
        waitForElementToBeVisibility(firstNameNewContact);
        fillText(firstNameNewContact, contactName);
        waitForElementToBeVisibility(lastNameNewContact);
        fillText(lastNameNewContact, lastName);
        waitForElementToBeVisibility(positionNewContact);
        fillText(positionNewContact, position);
        waitForElementToBeVisibility(businessPhoneNewContact);
        fillText(businessPhoneNewContact, phoneNum);
        waitForElementToBeVisibility(secondPhoneNewContact);
        fillText(secondPhoneNewContact, phoneNum);
        waitForElementToBeVisibility(faxPhoneNewContact);
        fillText(faxPhoneNewContact, phoneNum);
        waitForElementToBeVisibility(emailNewContact);
        fillText(emailNewContact, email);

        if (callBackReferent.equalsIgnoreCase("Yes")) {
            click(callbackReferentNewContact);
            waitForElementToBeClickable(createContactButton);
            click(createContactButton);
        } else {
            waitForElementToBeClickable(createContactButton);
            click(createContactButton);
        }
    }

    @Step("get text main contacts in case data tab")
    public String mainContacts(String contactName) {
        waitForPageFinishLoading();
        String mainContactData = null;
        waitForElementToBeVisibility(contactInfoContainer);

        for (WebElement el : mainContactsList) {
            waitForElementToBeVisibility(contactInfoContainer);
            if (getText(el).contains(contactName)) {
                waitForElementToBeVisibility(el);
                mainContactData = getText(el);
                break;
            }
        }
        return mainContactData;
    }

    @Step("get text callback referents contacts in case data tab")
    public String callbackReferentsContacts(String contactName) {
        waitForPageFinishLoading();
        String referentContactData = null;
        waitForElementToBeVisibility(contactInfoContainer);
        for (WebElement el : callbackReferentList) {
            waitForElementToBeVisibility(contactInfoContainer);
            if (getText(el).contains(contactName)) {
                waitForElementToBeVisibility(el);
                referentContactData = getText(el);
                break;
            }
        }
        return referentContactData;
    }

    @Step("delete contact from callback referent")
    public void deleteContactFromCallBackReferent(String contactName, String editDelete) {
        waitForPageFinishLoading();
        for (WebElement el : callbackReferentList) {
            if (getText(el).contains(contactName)) {
                waitForElementToBeClickable(editDeleteMenuCallbackReferent);
                click(editDeleteMenuCallbackReferent);
                for (WebElement editOrDelete : editDeleteMenuList) {
                    if (getText(editOrDelete).equalsIgnoreCase(editDelete)) {
                        waitForElementToBeClickable(editOrDelete);
                        click(editOrDelete);
                    }
                }
            }
        }
    }

    @Step("delete contact from main contact")
    public void deleteContactFromMainContact(String contactName, String editDelete) {
        waitForPageFinishLoading();
        for (WebElement el : mainContactsList) {
            if (getText(el).contains(contactName)) {
                scrollToElement(editAndDeleteMenuMainContactButton);
                waitForElementToBeClickable(editAndDeleteMenuMainContactButton);
                clickByJS(editAndDeleteMenuMainContactButton);
                for (WebElement editOrDelete : editDeleteMenuList) {
                    if (getText(editOrDelete).equalsIgnoreCase(editDelete)) {
                        waitForElementToBeClickable(editOrDelete);
                        click(editOrDelete);
                    } else {
                        System.out.println("No more contacts to delete");
                    }
                }
            }
        }
    }

    @Step("check in Cancel Tasks and Resend Email ")
    public boolean checkButtonsDisplay(String buttonName) {
        try {
            waitForPageFinishLoading();
            for (WebElement el : actionInPage) {
                if (getText(el).contains(buttonName)) {
                    scrollToElement(el);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
}