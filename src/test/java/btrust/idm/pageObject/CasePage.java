package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CasePage extends BasePage {
    public CasePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".case-info > .case-icon")
    protected WebElement caseIcon;
    @FindBy(css = "#case_id")
    protected WebElement caseID;
    @FindBy(css = "#case_name")
    protected WebElement caseName;
    @FindBy(css = ".case-description")
    protected WebElement caseDescription;
    @FindBy(css = "[alt='investigation']")
    protected WebElement escalateButton;
    @FindBy(css = ".case-actions > .green-btn")
    protected WebElement closeCaseButton;
    @FindBy(css = ".WatchersSelect_container > button")
    protected WebElement watchersButton;
    @FindBy(css = ".data-tab-container > .general-info")
    protected WebElement generalInfo;
    @FindBy(css = ".CompanyOverview_container > :nth-child(1) #panel1a-header")
    protected WebElement companyOverview;
    @FindBy(css = ".data-tab-container > :nth-child(3) .ExpansionPanelSummary_title")
    protected WebElement riskProfile;
    @FindBy(css = ".data-tab-container > :nth-child(3) #panel1a-header")
    protected WebElement riskProfileTab;
    @FindBy(css = ".UboRdcData_container > table > tbody")
    protected WebElement riskProfileData;
    @FindBy(css = ".MuiPaper-root >:nth-child(1) .ExpansionPanelSummary_title > span")
    protected WebElement ownershipStructure;
    @FindBy(css = ".data-tab-container > :nth-child(4) > #panel1a-header")
    protected WebElement ownershipStructureTab;
    @FindBy(css = ".data-tab-container > :nth-child(5) > #panel1a-header")
    protected WebElement directorsAndManagement;
    @FindBy(css = ".chat-and-tasks .MuiTabs-scroller.MuiTabs-fixed > .MuiTabs-flexContainer > :nth-child(1)")
    protected WebElement chatButton;
    @FindBy(css = ".chat-and-tasks .MuiTabs-scroller.MuiTabs-fixed > .MuiTabs-flexContainer > :nth-child(2)")
    protected WebElement tasksButton;
    @FindBy(css = ".overview-content > :nth-child(1) > .overview-title > span")
    protected WebElement legalOverviewTitle;
    @FindBy(css = ".overview-content > :nth-child(2) > :nth-child(1)")
    protected WebElement legalName;
    @FindBy(css = ".overview-content > :nth-child(2) > :nth-child(2)")
    protected WebElement legalStatus;
    @FindBy(css = ".overview-content > :nth-child(2) > :nth-child(3)")
    protected WebElement previusName;
    @FindBy(css = ".overview-content > :nth-child(2) > :nth-child(4)")
    protected WebElement dateOfIntercoporation;
    @FindBy(css = ".overview-content > :nth-child(2) > :nth-child(5)")
    protected WebElement alternativeName;
    @FindBy(css = ".overview-content > :nth-child(2) > :nth-child(6)")
    protected WebElement legaTypeEntity;
    @FindBy(css = ".overview-content > :nth-child(2) > :nth-child(7)")
    protected WebElement tradeRegisterNumber;
    @FindBy(css = ".overview-content > :nth-child(2) > :nth-child(8)")
    protected WebElement specialialisation;
    @FindBy(css = "#panel1a-content > div > div > div > div:nth-child(3) > div > span")
    protected WebElement contactInfoTitle;
    @FindBy(css = ".legal-overview-content.contact-info > :nth-child(1)")
    protected WebElement contactInfoBox1;
    @FindBy(css = ".legal-overview-content.contact-info > :nth-child(2)")
    protected WebElement contactInfoBox2;
    @FindBy(css = ".legal-overview-content.contact-info > :nth-child(3)")
    protected WebElement contactInfoBox3;
    @FindBy(css = ".data-tab-container > :nth-child(4) .expandtable-title")
    protected WebElement uboTitle;
    @FindBy(css = ".data-tab-container > :nth-child(5) .ExpansionPanelDetails_content > :nth-child(1) > span")
    protected WebElement boardsComitteesTitle;
    @FindBy(css = ".data-tab-container > :nth-child(5) .ExpansionPanelDetails_content > :nth-child(3) > span")
    protected WebElement seniorManagementTitle;
    @FindBy(css = ".WatchersSelect_container > .options")
    protected WebElement watcherUser;
    @FindBy(css = ".comments-textarea__control > .comments-textarea__input")
    protected WebElement chatMessageField;
    @FindBy(css = ".comment > button")
    protected WebElement sendButton;
    @FindBy(css = ".comments-textarea__suggestions__list > li > span")
    protected List<WebElement> usersList;
    @FindBy(css = ".flex-items > .avatar")
    protected WebElement userIcon;
    @FindBy(css = ".chat-info > .name")
    protected WebElement userName;
    @FindBy(css = ".chat-content > :nth-child(1) .chat-info > .title")
    protected WebElement userMessage;
    @FindBy(css = ".MuiTabs-flexContainer > button")
    protected WebElement tab;
    @FindBy(css = ".MuiTabs-flexContainer > button")
    protected List<WebElement> tabList;
    @FindBy(css = ".tabcase-container > div")
    protected WebElement documentsTitle;
    @FindBy(css = ".MuiExpansionPanelSummary-content > .ExpansionPanelSummary_title")
    protected WebElement caseConclusionReportTitle;
    @FindBy(css = "#panel1a-header")
    protected WebElement caseConclusionReport;
    @FindBy(css = ".status-box > .label")
    protected WebElement statusBox;
    @FindBy(css = ".status-box > .status")
    protected WebElement statusDesc;
    @FindBy(css = ".status-box > .data")
    protected WebElement statusData;
    @FindBy(css = ".tabcase-container > .audit-logs")
    protected WebElement logs;
    @FindBy(css = ".audit-logs > :nth-child(1)  > .log-content > :nth-child(3)")
    protected WebElement comment;
    @FindBy(css = ".audit-logs > :nth-child(2)  > .log-content > :nth-child(2)")
    protected WebElement tag;
    @FindBy(css = ".audit-logs > :nth-child(2)  > .log-content > :nth-child(3)")
    protected WebElement mentioned;
    @FindBy(css = ".chat-item > :nth-child(1) .replies")
    protected WebElement replies;
    @FindBy(css = ".chat-item > :nth-child(1) .btns > :nth-child(1)")
    protected WebElement openTaskButton;
    @FindBy(css = ".chat-item > :nth-child(1) .btns > :nth-child(2)")
    protected WebElement replayButton;
    @FindBy(css = ".current-replies textarea")
    protected WebElement replayInput;
    @FindBy(css = ".chat-content > :nth-child(1) > :nth-child(1)")
    protected WebElement firstMessage;
    @FindBy(css = ".attached-title > span")
    protected WebElement attachTitle;
    @FindBy(css = ".comment-input > button")
    protected WebElement sendReplayButton;
    @FindBy(css = ".tabcase-container :nth-child(1) > .log-content > :nth-child(3)")
    protected WebElement replayLog;
    /*
    New task elements
     */
    @FindBy(css = ".create-task-form > .task-header > button")
    protected WebElement closeNewTaskButton;
    @FindBy(css = ".chat-and-tasks .btns > :nth-child(1)")
    protected WebElement cancelButton;
    @FindBy(css = ".task-header > .task-icon")
    protected WebElement newTaskIcon;
    @FindBy(css = ".task-header > span")
    protected WebElement newTaskTitle;
    @FindBy(css = ".scrolling-content .title-text > span")
    protected WebElement title;
    @FindBy(css = ".scrolling-content input")
    protected WebElement titleInput;
    @FindBy(css = ".task-selects > :nth-child(1) >  .select-title")
    protected WebElement statusTitle;
    @FindBy(css = ".task-selects > :nth-child(1) > .FiltersSelect_container")
    protected WebElement statusSelect;
    @FindBy(css = ".FiltersSelect_container > .options > .item")
    protected List<WebElement> statusList;
    @FindBy(css = ".task-selects > :nth-child(2) > .select-title")
    protected WebElement assigneeTitle;
    @FindBy(css = ".task-selects > :nth-child(2) > .SelectTeamMember_container")
    protected WebElement assigneeSelect;
    @FindBy(css = ".task-selects > :nth-child(2) > .SelectTeamMember_container > .options > .item")
    protected List<WebElement> assigneeList;
    @FindBy(css = ".scrolling-content > .textarea-container > .area-title > span")
    protected WebElement descriptionTitle;
    @FindBy(css = "[placeholder='Add Description here...']")
    protected WebElement descriptionInput;
    @FindBy(css = ".chat-item > .chat-info")
    protected WebElement chatInfo;
    @FindBy(css = ".chat-and-tasks .btns > :nth-child(2)")
    protected WebElement createButton;
    @FindBy(css = ".title-container .validation-error > span")
    protected WebElement errorMessageEmptyTitle;
    @FindBy(css = ".task-selects .validation-error > span")
    protected WebElement errorMessageEmptyStatus;
    /*
    tasks tab elements
     */
    @FindBy(css = ".add-task > button")
    protected WebElement addNewTaskButton;
    @FindBy(css = ".chat-and-tasks > .tasks-content >.tasks-type.overlay > span")
    protected WebElement openTasksTitle;
    @FindBy(css = ".chat-and-tasks > .tasks-content >.tasks-container > .task-item > .task-icon")
    protected WebElement taskIcon;
    @FindBy(css = ".chat-and-tasks > .tasks-content >.tasks-container > .task-item > .task-content > .title")
    protected WebElement taskTitle;
    @FindBy(css = ".chat-and-tasks > .tasks-content >.tasks-container > .task-item > .task-content")
    protected WebElement taskDescription;
    @FindBy(css = ".tasks-content > :nth-child(3) > span")
    protected WebElement resolvedTasksTitle;
    @FindBy(css = ".audit-logs > :nth-child(1) > .log-content > :nth-child(3)")
    protected WebElement resolvedMessage;
    /*
    Escalate Case popup
     */
    @FindBy(css = ".text-title > span")
    protected WebElement escalateCaseTitle;
    @FindBy(css = ".icon-container > img")
    protected WebElement escalateCaseIcon;
    @FindBy(css = ".assign-descr > span")
    protected WebElement escalateCaseDesc;
    @FindBy(css = ".assignto > .FiltersSelect_container")
    protected WebElement selectUser;
    @FindBy(css = ".options > .item")
    protected List<WebElement> selectUsersList;
    @FindBy(css = ".description > span")
    protected WebElement description;
    @FindBy(css = ".content > textarea")
    protected WebElement textarea;
    @FindBy(css = ".content > .btns > :nth-child(1)")
    protected WebElement escalateCaseCancelButton;
    @FindBy(css = ".content > .btns > :nth-child(2)")
    protected WebElement escalateCaseButton;
    @FindBy(css = ".assignto > .error > span")
    protected WebElement errorMessageSelectUser;
    @FindBy(css = ".content > .error > span")
    protected WebElement errorMessageTextArea;
    @FindBy(css = ".audit-logs > :nth-child(1) > .log-content")
    protected WebElement escalateLog;
    /*
    Close case popup
    */
    @FindBy(css = ".heading > .text-title > span")
    protected WebElement closeCasePopupTitle;
    @FindBy(css = ".icon-container > img")
    protected WebElement closeCasePopupIcon;
    @FindBy(css = ".assign-descr > span:nth-child(2)")
    protected WebElement closeCasePopupDesc;
    @FindBy(css = ".MuiIconButton-colorSecondary > .MuiIconButton-label")
    protected WebElement closeCasePopupCheckbox;
    @FindBy(css = ".content > textarea")
    protected WebElement closeCasePopupField;
    @FindBy(css = ".content > .btns > :nth-child(1)")
    protected WebElement closeCasePopupCancelButton;
    @FindBy(css = ".content > .btns > :nth-child(2)")
    protected WebElement closeCasePopupCloseCaseButton;
    @FindBy(css = ".error > span")
    protected WebElement closeCasePopupErrorMessage;


    @Step("Check that caseIcon appear")
    public boolean caseIcon() {
        waitForElementToBeVisibility(caseIcon);
        return isDisplayed(caseIcon);
    }

    @Step("Check that caseID appear")
    public boolean caseID() {
        waitForElementToBeVisibility(caseID);
        return isDisplayed(caseID);
    }

    @Step("Check that caseName appear")
    public boolean caseName() {
        waitForElementToBeVisibility(caseName);
        return isDisplayed(caseName);
    }

    @Step("Check that caseDescription appear")
    public boolean caseDescription() {
        waitForElementToBeVisibility(caseDescription);
        return isDisplayed(caseDescription);
    }

    @Step("Check that escalateButton appear")
    public boolean escalateButton() {
        waitForElementToBeVisibility(escalateButton);
        return isDisplayed(escalateButton);
    }

    @Step("Click on escalateButton")
    public void escalateBtn(String text) {
        waitForElementToBeClickable(escalateButton);
        click(escalateButton);
        waitForTextToBeInElement(escalateCaseTitle, text);
    }

    @Step("Check that closeCaseButton appear")
    public boolean closeCaseButton() {
        waitForElementToBeVisibility(closeCaseButton);
        return isDisplayed(closeCaseButton);
    }

    @Step("Click on closeCaseButton")
    public void closeCaseBtn() {
        waitForElementToBeVisibility(closeCaseButton);
        click(closeCaseButton);
    }

    @Step("Check that generalInfo appear")
    public boolean generalInfo() {
        waitForElementToBeVisibility(generalInfo);
        return isDisplayed(generalInfo);
    }

    @Step("Check that companyOverview appear")
    public String companyOverview(String text) {
        waitForTextToBeInElement(companyOverview, text);
        return getText(companyOverview);
    }

    @Step("Check that riskProfile appear")
    public String riskProfile(String text) {
        waitForTextToBeInElement(riskProfile, text);
        return getText(riskProfile);
    }

    @Step("Check that ownershipStructure appear")
    public String ownershipStructure(String text) {
        waitForTextToBeInElement(ownershipStructure, text);
        return getText(ownershipStructure);
    }

    @Step("Check that directorsAndManagement appear")
    public String directorsAndManagement(String text) {
        waitForTextToBeInElement(directorsAndManagement, text);
        return getText(directorsAndManagement);
    }

    @Step("Check that chatButton appear")
    public boolean chatButton() {
        waitForElementToBeVisibility(chatButton);
        return isDisplayed(chatButton);
    }

    @Step("Check that tasksButton appear")
    public boolean tasksButton() {
        waitForElementToBeVisibility(tasksButton);
        return isDisplayed(tasksButton);
    }

    @Step("Click on companyOverviewTab")
    public void companyOverviewTab() {
        js.executeScript("arguments[0].scrollIntoView();", companyOverview);
        waitForElementToBeClickable(companyOverview);
        click(companyOverview);
    }

    @Step("Check that legalOverviewTitle appear")
    public String legalOverviewTitle() {
        waitForElementToBeClickable(legalOverviewTitle);
        return getText(legalOverviewTitle);
    }

    @Step("Check that legalName appear")
    public boolean legalName() {
        waitForElementToBeVisibility(legalName);
        return isDisplayed(legalName);
    }

    @Step("Check that legalStatus appear")
    public boolean legalStatus() {
        waitForElementToBeVisibility(legalStatus);
        return isDisplayed(legalStatus);
    }

    @Step("Check that previusName appear")
    public boolean previusName() {
        waitForElementToBeVisibility(previusName);
        return isDisplayed(previusName);
    }

    @Step("Check that dateOfIntercoporation appear")
    public boolean dateOfIntercoporation() {
        waitForElementToBeVisibility(dateOfIntercoporation);
        return isDisplayed(dateOfIntercoporation);
    }

    @Step("Check that alternativeName appear")
    public boolean alternativeName() {
        waitForElementToBeVisibility(alternativeName);
        return isDisplayed(alternativeName);
    }

    @Step("Check that legaTypeEntity appear")
    public boolean legaTypeEntity() {
        waitForElementToBeVisibility(legaTypeEntity);
        return isDisplayed(legaTypeEntity);
    }

    @Step("Check that tradeRegisterNumber appear")
    public boolean tradeRegisterNumber() {
        waitForElementToBeVisibility(tradeRegisterNumber);
        return isDisplayed(tradeRegisterNumber);
    }

    @Step("Check that specialialisation appear")
    public boolean specialialisation() {
        waitForElementToBeVisibility(specialialisation);
        return isDisplayed(specialialisation);
    }

    @Step("Check that contactInfoTitle appear")
    public String contactInfoTitle(String text) {
        waitForTextToBeInElement(contactInfoTitle, text);
        return getText(contactInfoTitle);
    }

    @Step("Check that contactInfoBox1 appear")
    public boolean contactInfoBox1() {
        waitForElementToBeVisibility(contactInfoBox1);
        return isDisplayed(contactInfoBox1);
    }

    @Step("Check that contactInfoBox2 appear")
    public boolean contactInfoBox2() {
        waitForElementToBeVisibility(contactInfoBox2);
        return isDisplayed(contactInfoBox2);
    }

    @Step("Check that contactInfoBox3 appear")
    public boolean contactInfoBox3() {
        waitForElementToBeVisibility(contactInfoBox3);
        return isDisplayed(contactInfoBox3);
    }

    @Step("Click on ownershipStructureTab")
    public void ownershipStructureTab() {
        js.executeScript("arguments[0].scrollIntoView();", ownershipStructureTab);
        waitForElementToBeClickable(ownershipStructureTab);
        click(ownershipStructureTab);
    }

    @Step("Check that uboTitle appear")
    public String uboTitle(String text) {
        waitForTextToBeInElement(uboTitle, text);
        return getText(uboTitle);
    }

    @Step("Click on directorsAndManagementTab")
    public void directorsAndManagementTab() {
        js.executeScript("arguments[0].scrollIntoView();", directorsAndManagement);
        waitForElementToBeClickable(directorsAndManagement);
        click(directorsAndManagement);
    }

    @Step("Check that boardsComitteesTitle appear")
    public String boardsComitteesTitle(String text) {
        waitForTextToBeInElement(boardsComitteesTitle, text);
        return getText(boardsComitteesTitle);
    }

    @Step("Check that seniorManagementTitle appear")
    public String seniorManagementTitle(String text) {
        waitForTextToBeInElement(seniorManagementTitle, text);
        return getText(seniorManagementTitle);
    }

    @Step("Open watches")
    public void watches() {
        waitForElementToBeClickable(watchersButton);
        click(watchersButton);
    }

    @Step("Check that watcherUser appear")
    public boolean watcherUser() {
        waitForElementToBeVisibility(watcherUser);
        return isDisplayed(watcherUser);
    }

    @Step("Send message without tag user")
    public void sendMessageWithoutTagUser(String message) {
        waitForElementToBeClickable(chatButton);
        click(chatButton);
        waitForElementToBeClickable(chatMessageField);
        fillText(chatMessageField, message);
        waitForElementToBeClickable(sendButton);
        click(sendButton);
    }

    @Step("Send message with tag user")
    public void sendMessageWithTagUser(String chooseFromUsersList, String message) {
        waitForElementToBeClickable(chatButton);
        click(chatButton);
        waitForElementToBeClickable(chatMessageField);
        fillText(chatMessageField, "@");
        for (WebElement el : usersList) {
            if (el.getText().equalsIgnoreCase(chooseFromUsersList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        fillText(chatMessageField, " " + message);
        waitForElementToBeClickable(sendButton);
        click(sendButton);
    }

    @Step("Check that userIcon appear")
    public boolean userIcon() {
        waitForElementToBeVisibility(userIcon);
        return isDisplayed(userIcon);
    }

    @Step("Check that userName appear")
    public String userName(String text) {
        waitForTextToBeInElement(userName, text);
        return getText(userName);
    }

    @Step("Check that userMessage appear")
    public String userMessage(String text) {
        waitForTextToBeInElement(userMessage, text);
        js.executeScript("arguments[0].scrollIntoView();", userMessage);
        return getText(userMessage);
    }

    @Step("Choose tab: {chooseTab}")
    public void chooseTab(String chooseTab) {
        waitForElementToBeVisibility(caseName);
        js.executeScript("arguments[0].scrollIntoView();", tab);
        waitForElementToBeClickable(tab);
        for (WebElement el : tabList) {
            if (el.getText().equalsIgnoreCase(chooseTab)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Check that documentsTitle appear")
    public String documentsTitle(String text) {
        waitForTextToBeInElement(documentsTitle, text);
        return getText(documentsTitle);
    }

    @Step("Check that caseConclusionReportTitle appear")
    public String caseConclusionReportTitle(String text) {
        waitForTextToBeInElement(caseConclusionReportTitle, text);
        return getText(caseConclusionReportTitle);
    }

    @Step("Click on caseConclusionReport tab")
    public void caseConclusionReport() {
        waitForElementToBeClickable(caseConclusionReport);
        click(caseConclusionReport);
    }

    @Step("Check that statusBox appear")
    public boolean statusBox() {
        waitForElementToBeVisibility(statusBox);
        return isDisplayed(statusBox);
    }

    @Step("Check that statusDesc appear")
    public boolean statusDesc() {
        waitForElementToBeVisibility(statusDesc);
        return isDisplayed(statusDesc);
    }

    @Step("Check that statusData appear")
    public boolean statusData() {
        waitForElementToBeVisibility(statusData);
        return isDisplayed(statusData);
    }

    @Step("Check that logs appear")
    public boolean logs() {
        waitForElementToBeVisibility(logs);
        return isDisplayed(logs);
    }

    @Step("Check that sendButton is enabled or disable")
    public boolean sendButtonIsEnabledOrDisable(String text) {
        waitForElementToBeVisibility(caseName);
        waitForElementToBeClickable(chatMessageField);
        fillText(chatMessageField, text);
        try {
            waitForElementToBeClickable(sendButton);
            isEnabled(sendButton);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Check that comment appear in log")
    public String comment(String text) {
        waitForTextToBeInElement(comment, text);
        return getText(comment);
    }

    @Step("Check that tag appear in log")
    public String tag(String text) {
        waitForTextToBeInElement(tag, text);
        return getText(tag);
    }

    @Step("Check that replayButton appear")
    public boolean replayButton() {
        waitForElementToBeVisibility(replayButton);
        return isDisplayed(replayButton);
    }

    @Step("Check that openTaskButton appear")
    public boolean openTaskButton() {
        waitForElementToBeVisibility(openTaskButton);
        return isDisplayed(openTaskButton);
    }

    @Step("Check that replaies appear")
    public boolean replies() {
        waitForElementToBeVisibility(replies);
        isDisplayed(replies);
        return true;
    }

    @Step("Check that attachTitle appear")
    public String attachTitle(String text) {
        waitForTextToBeInElement(attachTitle, text);
        return getText(attachTitle);
    }

    @Step("Click on firstMessage")
    public void firstMessage(String text) {
        waitForElementToBeVisibility(caseName);
        waitForTextToBeInElement(firstMessage, text);
        Actions action = new Actions(driver);
        action.moveToElement(replayButton).build().perform();
        waitForElementToBeClickable(firstMessage);
        click(firstMessage);
    }

    @Step("Check that replayInput appear")
    public boolean replayInput() {
        waitForElementToBeVisibility(replayInput);
        return isDisplayed(replayInput);
    }

    @Step("Check that sendReplayButton appear")
    public boolean sendReplayButton() {
        waitForElementToBeVisibility(sendReplayButton);
        return isDisplayed(sendReplayButton);
    }

    @Step("Click on replayButton")
    public void replayCommentButton() {
        Actions action = new Actions(driver);
        action.moveToElement(replayButton).build().perform();
        waitForElementToBeClickable(replayButton);
        click(replayButton);
    }

    @Step("Check that sendReplayButton is disable")
    public boolean sendReplayButtonIsDisable() {
        waitForElementToBeVisibility(sendReplayButton);
        return !sendReplayButton.isEnabled();
    }

    @Step("Replay comment")
    public void replayComment(String replay) {
        waitForElementToBeClickable(replayInput);
        fillText(replayInput, replay);
        waitForElementToBeClickable(sendReplayButton);
        click(sendReplayButton);
    }

    @Step("Check that replayLog appear")
    public String replayLog(String text) {
        waitForTextToBeInElement(replayLog, text);
        js.executeScript("arguments[0].scrollIntoView();", replayLog);
        return getText(replayLog);
    }

    @Step("Open task from comment")
    public void openTaskFromComment() {
        waitForElementToBeVisibility(firstMessage);
        Actions action = new Actions(driver);
        action.moveToElement(firstMessage).build().perform();
        action.moveToElement(openTaskButton).build().perform();
        waitForElementToBeVisibility(openTaskButton);
        click(openTaskButton);
    }

    @Step("Click on closeNewTaskButton")
    public void closeNewTaskButton() {
        waitForElementToBeClickable(closeNewTaskButton);
        click(closeNewTaskButton);
    }

    @Step("Click on cancelButton")
    public void cancelButton() {
        waitForElementToBeClickable(cancelButton);
        click(cancelButton);
    }

    @Step("Check that newTaskIcon appear")
    public boolean newTaskIcon() {
        waitForElementToBeVisibility(newTaskIcon);
        return isDisplayed(newTaskIcon);
    }

    @Step("Check that newTaskTitle appear")
    public String newTaskTitle(String text) {
        waitForTextToBeInElement(newTaskTitle, text);
        return getText(newTaskTitle);
    }

    @Step("Check that statusTitle appear")
    public String statusTitle(String text) {
        waitForTextToBeInElement(statusTitle, text);
        return getText(statusTitle);
    }

    @Step("Check that assigneeTitle appear")
    public String assigneeTitle(String text) {
        waitForTextToBeInElement(assigneeTitle, text);
        return getText(assigneeTitle);
    }

    @Step("Check that descriptionTitle appear")
    public String descriptionTitle(String text) {
        waitForTextToBeInElement(descriptionTitle, text);
        return getText(descriptionTitle);
    }

    @Step("Check that chatInfo appear")
    public boolean chatInfo() {
        waitForElementToBeVisibility(chatInfo);
        return isDisplayed(chatInfo);
    }

    @Step("Check that errorMessageEmptyTitle appear")
    public String errorMessageEmptyTitle(String text) {
        waitForTextToBeInElement(errorMessageEmptyTitle, text);
        return getText(errorMessageEmptyTitle);
    }

    @Step("Click on createButton")
    public void createButton() {
        waitForElementToBeClickable(createButton);
        click(createButton);
    }

    @Step("Check that errorMessageEmptyStatus appear")
    public String errorMessageEmptyStatus(String text) {
        waitForTextToBeInElement(errorMessageEmptyStatus, text);
        return getText(errorMessageEmptyStatus);
    }

    @Step("Fill details for new task")
    public void fillDetailsNewTask(String title, String chooseFromStatusList, String chooseFromAssigneeList, String description) {
        waitForElementToBeClickable(titleInput);
        fillText(titleInput, title);
        waitForElementToBeClickable(statusSelect);
        click(statusSelect);
        for (WebElement el : statusList) {
            if (el.getText().equalsIgnoreCase(chooseFromStatusList)) {
                waitForElementToBeVisibility(el);
                click(el);
                break;
            }
        }
        waitForElementToBeClickable(assigneeSelect);
        click(assigneeSelect);
        for (WebElement el : assigneeList) {
            if (el.getText().equalsIgnoreCase(chooseFromAssigneeList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        waitForElementToBeClickable(descriptionInput);
        fillText(descriptionInput, description);
    }

    @Step("Click on tasks tab")
    public void tasksTab() {
        waitForElementToBeClickable(tasksButton);
        click(tasksButton);
    }

    @Step("Check that openTasksTitle appear")
    public String openTasksTitle(String text) {
        waitForTextToBeInElement(openTasksTitle, text);
        return getText(openTasksTitle);
    }

    @Step("Check that taskIcon appear")
    public boolean taskIcon() {
        waitForElementToBeVisibility(taskIcon);
        return isDisplayed(taskIcon);
    }

    @Step("Check that taskTitle appear")
    public String taskTitle() {
        waitForElementToBeVisibility(taskTitle);
        return getText(taskTitle);
    }

    @Step("Check that taskDescription appear")
    public boolean taskDescription() {
        waitForElementToBeVisibility(taskDescription);
        return isDisplayed(taskDescription);
    }

    @Step("Check that resolvedTasksTitle appear")
    public String resolvedTasksTitle(String text) {
        waitForTextToBeInElement(resolvedTasksTitle, text);
        return getText(resolvedTasksTitle);
    }

    @Step("Click on addNewTaskButton tab")
    public void addNewTaskButton() {
        waitForElementToBeVisibility(caseName);
        js.executeScript("arguments[0].scrollIntoView();", addNewTaskButton);
        waitForElementToBeClickable(addNewTaskButton);
        click(addNewTaskButton);
    }

    @Step("change status task")
    public void changeStatusTask() {
        waitForElementToBeClickable(taskTitle);
        click(taskTitle);
        waitForElementToBeClickable(statusSelect);
        click(statusSelect);
        WebElement resvolves = driver.findElement(By.cssSelector(".FiltersSelect_container > .options > :nth-child(7)"));
        js.executeScript("arguments[0].scrollIntoView();", resvolves);
        waitForElementToBeClickable(resvolves);
        click(resvolves);
        waitForElementToBeClickable(closeNewTaskButton);
        click(closeNewTaskButton);
    }

    @Step("Check that resolvedMessage appear")
    public String resolvedMessage(String text) {
        waitForTextToBeInElement(resolvedMessage, text);
        return getText(resolvedMessage);
    }

    @Step("Check that escalateCaseTitle appear")
    public String escalateCaseTitle(String text) {
        waitForTextToBeInElement(escalateCaseTitle, text);
        return getText(escalateCaseTitle);
    }

    @Step("Check that escalateCaseIcon appear")
    public boolean escalateCaseIcon() {
        waitForElementToBeVisibility(escalateCaseIcon);
        return isDisplayed(escalateCaseIcon);
    }

    @Step("Check that escalateCaseDesc appear")
    public String escalateCaseDesc(String text) {
        waitForTextToBeInElement(escalateCaseDesc, text);
        return getText(escalateCaseDesc);
    }

    @Step("Check that selectUser appear")
    public boolean selectUser() {
        waitForElementToBeVisibility(selectUser);
        return isDisplayed(selectUser);
    }

    @Step("Check that description appear")
    public String description(String text) {
        waitForTextToBeInElement(description, text);
        return getText(description);
    }

    @Step("Check that textarea appear")
    public boolean textarea() {
        waitForElementToBeVisibility(textarea);
        return isDisplayed(textarea);
    }

    @Step("Click on escalateCaseCancelButton")
    public void escalateCaseCancelButton() {
        waitForElementToBeVisibility(escalateCaseCancelButton);
        click(escalateCaseCancelButton);
    }

    @Step("Click on escalateCaseButton")
    public void escalateCaseButton() {
        waitForElementToBeVisibility(escalateCaseButton);
        click(escalateCaseButton);
    }

    @Step("Check that errorMessageSelectUser appear")
    public String errorMessageSelectUser() {
        waitForElementToBeVisibility(errorMessageSelectUser);
        return getText(errorMessageSelectUser);
    }

    @Step("Check that errorMessageTextArea appear")
    public String errorMessageTextArea() {
        waitForElementToBeVisibility(errorMessageTextArea);
        return getText(errorMessageTextArea);
    }

    @Step("Fill escalate case popup")
    public void fillEscalateCasePopup(String chooseFromUsersList, String text) {
        waitForElementToBeVisibility(selectUser);
        sleep(2000);
        click(selectUser);
        for (WebElement el : selectUsersList) {
            if (el.getText().equalsIgnoreCase(chooseFromUsersList)) {
                waitForElementToBeVisibility(el);
                click(el);
                break;
            }
        }
        waitForElementToBeVisibility(textarea);
        fillText(textarea, text);
        waitForElementToBeVisibility(escalateCaseButton);
        click(escalateCaseButton);
    }

    @Step("Check that statusDesc appear")
    public String statusDescription(String text) {
        waitForTextToBeInElement(statusDesc, text);
        return getText(statusDesc);
    }

    @Step("Check that escalateLog appear")
    public String escalateLog(String text) {
        waitForTextToBeInElement(escalateLog, text);
        return getText(escalateLog);
    }

    @Step("Open riskProfileTab")
    public void riskProfileTab() {
        waitForElementToBeClickable(riskProfileTab);
        click(riskProfileTab);
    }

    @Step("Check that riskProfileData appear")
    public boolean riskProfileData() {
        waitForElementToBeVisibility(riskProfileData);
        return isDisplayed(riskProfileData);
    }

    @Step("Check that closeCasePopupIcon appear")
    public boolean closeCasePopupIcon() {
        waitForElementToBeVisibility(closeCasePopupIcon);
        return isDisplayed(closeCasePopupIcon);
    }

    @Step("Check that closeCasePopupTitle appear")
    public boolean closeCasePopupTitle() {
        waitForElementToBeVisibility(closeCasePopupTitle);
        return isDisplayed(closeCasePopupTitle);
    }

    @Step("Check that closeCasePopupDesc appear")
    public boolean closeCasePopupDesc() {
        waitForElementToBeVisibility(closeCasePopupDesc);
        return isDisplayed(closeCasePopupDesc);
    }

    @Step("Check that closeCasePopupField appear")
    public boolean closeCasePopupField() {
        waitForElementToBeVisibility(closeCasePopupField);
        return isDisplayed(closeCasePopupField);
    }

    @Step("Check that closeCasePopupCancelButton appear")
    public boolean closeCasePopupCancelButton() {
        waitForElementToBeVisibility(closeCasePopupCancelButton);
        return isDisplayed(closeCasePopupCancelButton);
    }

    @Step("Check that closeCasePopupCloseCaseButton appear")
    public boolean closeCasePopupCloseCaseButton() {
        waitForElementToBeVisibility(closeCasePopupCloseCaseButton);
        return isDisplayed(closeCasePopupCloseCaseButton);
    }

    @Step("Check that closeCasePopupCloseCaseButtonIsDisable")
    public boolean closeCasePopupCloseCaseButtonIsDisable() {
        try {
            waitForElementToBeVisibility(closeCasePopupCloseCaseButton);
            isEnabled(closeCasePopupCloseCaseButton);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Check that closeCasePopupCloseCaseButtonIsEnable")
    public boolean closeCasePopupCloseCaseButtonIsEnable() {
        try {
            waitForElementToBeVisibility(closeCasePopupCloseCaseButton);
            isEnabled(closeCasePopupCloseCaseButton);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Step("Click on closeCasePopupCancelButton")
    public void closeCasePopupCancelBtn() {
        waitForElementToBeClickable(closeCasePopupCancelButton);
        click(closeCasePopupCancelButton);
    }

    @Step("Fill closeCasePopupCancel fields")
    public void fillCloseCasePopupCancelFields(String text) {
        waitForElementToBeClickable(closeCasePopupCheckbox);
        click(closeCasePopupCheckbox);
        waitForElementToBeClickable(closeCasePopupField);
        fillText(closeCasePopupField, text);
        waitForElementToBeClickable(closeCasePopupCloseCaseButton);
        click(closeCasePopupCloseCaseButton);
    }

    @Step("Check that closeCasePopupErrorMessage appear")
    public String closeCasePopupErrorMessage(String text) {
        waitForTextToBeInElement(closeCasePopupErrorMessage, text);
        return getText(closeCasePopupErrorMessage);
    }
}