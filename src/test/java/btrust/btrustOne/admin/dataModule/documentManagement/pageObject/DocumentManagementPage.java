package btrust.btrustOne.admin.dataModule.documentManagement.pageObject;

import btrust.BasePage;
import com.beust.jcommander.JCommander;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DocumentManagementPage extends BasePage {
    public DocumentManagementPage(WebDriver driver) {
        super(driver);
    }

    public String randomString;
    public String formattedDate;


    @FindBy(css = ".SettingsHeader-module__wrapper__2OOUN.undefined>.SettingsHeader-module__title__acJ6f")
    protected WebElement documentManagementTitle;
    @FindBy(css = "[placeholder='Type document name to search']")
    protected WebElement documentManagementSearchBar;
    @FindBy(css = ".Table-module__wrapper__2FxRF [role='tabpanel']")
    protected List<WebElement> documentManagementRows;
    @FindBy(css = ".AddButton-module__container__26pq2 [type=button]")
    protected WebElement addNewDocumentButton;
    @FindBy(css = ".Table-module__wrapper__2FxRF > div")
    protected List<WebElement> documentsTableList;
    @FindBy(css = ".TopBar-module__wrapper__m30R_")
    protected WebElement insideDocTitle;
    @FindBy(css = ".AddButton-module__modal__tx7fo > li")
    protected List<WebElement> addNewDocumentTypeList;
    @FindBy(css = ".SettingsHeader-module__filters__5fFw8 .filter-item:nth-child(2) .select-button")
    protected WebElement creationTimeFrameSelect;
    @FindBy(css = ".SettingsHeader-module__filters__5fFw8 .filter-item:nth-child(3) .select-button")
    protected WebElement statusSelectButton;
    @FindBy(css = ".items-container [role='tabpanel']")
    protected List<WebElement> statusSelectList;
    @FindBy(css = "[class='clear change-position']")
    protected WebElement clearFilter;
    @FindBy(css = ".radio-btns [role='tabpanel']")
    protected List<WebElement> creationTimeFrameList;
    @FindBy(css = ".calendar-header > .month")
    protected WebElement month;
    @FindBy(css = ".react-datepicker__month [role='option']")
    protected List<WebElement> dayList;
    @FindBy(css = ".calendar-header > .btn.prev")
    protected WebElement previousMonthButton;
    @FindBy(css = ".Table-module__item__3NBox:nth-child(5)")
    protected List<WebElement> creationDateList;
    @FindBy(css = ".cases-pagination [type='button']:nth-child(3)")
    protected WebElement paginationArrowRight;
    @FindBy(css = ".cases-pagination [type='button']:nth-child(1)")
    protected WebElement paginationArrowLeft;
    @FindBy(css = "div[role='tabpanel'] > .selected-date")
    protected WebElement selectedDate;


    // inside doc page
    @FindBy(css = ".MuiTableBody-root .table-row")
    protected List<WebElement> docVersionsRowList;
    @FindBy(css = ".MuiTableBody-root .table-row .Table-module__version__1Zdrp")
    protected List<WebElement> versionNumberList;
    @FindBy(css = ".MuiTableCell-body.MuiTableCell-root.Table-module__edit__2wzww > svg[role='presentation']")
    protected List<WebElement> editDocButtonList;
    @FindBy(css = ".MuiTableCell-body.MuiTableCell-root.Table-module__edit__2wzww")
    protected List<WebElement> editDocButtonListTest;

    @FindBy(css = ".DateSelector_container [type=button]")
    protected WebElement selectActivationDateButton;
    @FindBy(css = ".DocumentFormTitle-module__title__OrQX-")
    protected WebElement editVersionTitle;
    @FindBy(css = ".react-datepicker__week .react-datepicker__day--today")
    protected WebElement todayInActivationDateCalender;
    @FindBy(css = ".btn.disabled-false.next")
    protected WebElement rightClickButtonInDateCalender;
    @FindBy(css = ".react-datepicker__week .react-datepicker__day")
    protected List<WebElement> daysInCalenderList;


    @FindBy(css = ".submit-btn[type=button]")
    protected WebElement activationVersionButton;
    @FindBy(css = ".MuiTableRow-root.table-row > td:nth-child(5)")
    protected WebElement activationDate;
    @FindBy(css = ".TopBar-module__container__14m1z button")
    protected WebElement addNewVersionButton;
    @FindBy(css = ".MuiTableBody-root .table-row div")

    protected List<WebElement> statusList;
    @FindBy(css = "tr > td:nth-child(6)")
    protected List<WebElement> expirationList;

    //new version page
    @FindBy(css = ".TopBar-module__wrapper__m30R_ :nth-child(2)")
    protected WebElement newVersionTitle;
    @FindBy(css = "[placeholder='Type name']")
    protected WebElement docNameInNewVersionPage;

    //new doc request page
    @FindBy(css = "div .DocumentFormTitle-module__title__OrQX-")
    protected WebElement newDocumentRequestTitle1;
    @FindBy(css = "div.DocumentInformation-module__title__3VAMl")
    protected WebElement newDocumentRequestTitle2;
    @FindBy(css = ".BottomStickyActions-module__main__OEizt[type=button]")
    protected WebElement newDocumentRequestContinueButton;
    @FindBy(css = ".BottomStickyActions-module__wrapper__1b1UJ [type=button]")
    protected List<WebElement> newDocumentRequestButtons;
    @FindBy(css = ".FileUploader-module__dropZone__2Xiz3.false > input[type='file']")
    protected WebElement uploadFileInDocRequest;
    @FindBy(css = ".FileUploader-module__dropZone__2Xiz3.false")
    protected WebElement uploadFileInDocRequestButton;
    @FindBy(css = ".PreviewFile-module__name__3mtLL")
    protected WebElement newDocUploadedName;
    @FindBy(css = ".PreviewFile-module__container__18UjG > button[type='button']")
    protected WebElement refreshButtonInExampleImage;
    @FindBy(css = "[class='default__indicators css-1wy0on6']")
    protected WebElement allowedFormatBox;
    @FindBy(css = "[class] [tabindex='-1']")
    protected List<WebElement> allowedFormatSelectList;
    @FindBy(css = ".NewDocumentRequest-module__inputItem__1raaJ:nth-child(1) [placeholder]")
    protected WebElement documentName;
    @FindBy(css = ".NewDocumentRequest-module__inputItem__1raaJ:nth-child(2) [placeholder]")
    protected WebElement documentIdentification;
    @FindBy(css = ".NewDocumentRequest-module__inputItem__1raaJ:nth-child(4) [placeholder]")
    protected WebElement maximumSizeAllowed;

    //e from
    @FindBy(css = "[placeholder='Type name']")
    protected WebElement eFormDocName;
    @FindBy(css = "[placeholder='Type identification']")
    protected WebElement eFormIdentificationName;
    @FindBy(css = "[placeholder='Type template id']")
    protected WebElement eFormTemplateId;
    @FindBy(css = ".ErrorPopup-module__content__1bNBR")
    protected WebElement somethingWentWrongMessage;
    @FindBy(css = ".ErrorPopup-module__actions__3vqaX .ErrorPopup-module__main__1UcHL[type=button]")
    protected WebElement okTakeMeBackButton;
    @FindBy(css = ".TemplateFields-module__title__2HXD-")
    protected WebElement templateFieldsTitle;
    @FindBy(css = ".DocumentInformation-module__list__-Kw4y > div")
    protected List<WebElement> eFormDocInfoTitlesList;
    @FindBy(css = ".DocumentInformation-module__actions__3PU_t > button[type='button']")
    protected WebElement eFormEditDetailsButton;
    @FindBy(css = " .Fields-module__tableRow__QJPig [type='checkbox']")
    protected List<WebElement> templateFieldNameCheckBoxList;
    @FindBy(css = ".Fields-module__item__TQiBz:nth-child(2)")
    protected List<WebElement> entityTypeSelectList;
    @FindBy(css = ".Fields-module__table__13-dF .Fields-module__item__TQiBz:nth-child(3)")
    protected List<WebElement> businessCategorySelectList;
    @FindBy(css = ".Fields-module__item__TQiBz:nth-child(4)")
    protected List<WebElement> dbFieldNameSelectList;
    @FindBy(css = "[class] [tabindex='-1']")
    protected List<WebElement> selectBoxList;
    @FindBy(css = ".TopBar-module__wrapper__m30R_")
    protected WebElement eFormTitle;
    @FindBy(css = "[role] [tabindex='0']:nth-child(2) .MuiTab-wrapper")
    protected WebElement arraysButton;
    @FindBy(css = ".Arrays-module__container__DySwX [type=checkbox]")
    protected List<WebElement> arraysCheckBoxList;
    @FindBy(css = " .Arrays-module__selector__1yrJl:nth-child(3) .css-yk16xz-control")
    protected List<WebElement> EntityTypeList;
    @FindBy(css = " .Arrays-module__selector__1yrJl:nth-child(4) .css-yk16xz-control")
    protected List<WebElement> businessCategoryList;
    @FindBy(css = "div:nth-of-type(4) > .default-custom-selector.isWhite-undefined")
    protected List<WebElement> dbFieldNameList;
    @FindBy(css = ".Fields-module__container__Uaawm [type='checkbox']")
    protected List<WebElement> templateFieldNameCheckboxList;
    @FindBy(css = ".LoadingPopup-module__content__3UAnt")
    protected WebElement creatingNewEFormPopup;
    @FindBy(css = ".NewDocumentForm-module__form__2zTpX .NewDocumentForm-module__inputItem__1llhm div")
    protected List<WebElement> eformFieldNameList;
    @FindBy(css = "div .NewDocumentForm-module__form__2zTpX .NewDocumentForm-module__inputItem__1llhm input")
    protected List<WebElement> eformInputNameList;
    @FindBy(css = ".NewDocumentRequest-module__form__2D7IV .NewDocumentRequest-module__label__7Ee92")
    protected List<WebElement> informativeFieldNameList;
    @FindBy(css = ".NewDocumentRequest-module__form__2D7IV input[placeholder")
    protected List<WebElement> informativeInputNameList;

    @FindBy(css = ".NewDocumentRequest-module__form__2D7IV .NewDocumentRequest-module__label__7Ee92")
    protected List<WebElement> uploadFieldNameList;
    @FindBy(css = ".default__value-container.default__value-container--is-multi.css-1hwfws3,.NewDocumentRequest-module__form__2D7IV input[placeholder")
    protected List<WebElement> uploadInputNameList;
    //    @FindBy(css = ".default__value-container.default__value-container--is-multi.css-1hwfws3")
//    protected WebElement uploadSelectField;
    @FindBy(css = ".DocumentInformation-module__fieldItem__2wwxi :nth-child(1)")
    protected List<WebElement> detailsFieldNameList;
    @FindBy(css = ".DocumentInformation-module__fieldItem__2wwxi :nth-child(2)")
    protected List<WebElement> detailsFieldValueList;
    @FindBy(css = "div .Breadcrumbs-module__bread-page-pointer__lLgMH")
    protected WebElement returnBack;
    @FindBy(css = "div.Table-module__row__1zwFQ :nth-child(2)")
    protected List<WebElement> documentNameList;
    @FindBy(css = "div.Table-module__row__1zwFQ :nth-child(3)")
    protected List<WebElement> documentTypeList;
    @FindBy(xpath = "//input[@type='file']")
    protected WebElement fileInput;


    @Step("Create new file in folder")
    public void createNewFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
    }

    @Step("Delete file from folder")
    public void deleteFileFromFolder(String filePath) throws IOException {
        Path path = FileSystems.getDefault().getPath(filePath);
        try {
            Files.delete(path);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (IOException x) {
            System.err.println(x);
        }
    }


    @Step("Upload file")
    public void uploadFile(String filePath) {
        fileInput.sendKeys(filePath);
//        fileInput.submit();
    }


    @Step("click on return back link ")
    public void returnBackButton() {
        waitForElementToBeClickable(returnBack);
        click(returnBack);
    }


    @Step("fill Eform Document Data")
    public void fillEformDocumentData(String fieldName, String inputData, String template_Id) {
        try {
            waitForPageFinishLoading();
            for (int i = 0; i < eformFieldNameList.size(); i++) {
                scrollToElement(eformFieldNameList.get(i));
                if (getText(eformFieldNameList.get(i)).equalsIgnoreCase(fieldName)) {
                    scrollToElement(eformInputNameList.get(i));
                    waitForElementToBeClickable(eformInputNameList.get(i));
                    if (fieldName == "Template ID") {
                        fillText(eformInputNameList.get(i), template_Id);
                        break;
                    }
                    fillText(eformInputNameList.get(i), inputData);
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong.");
        }
    }


    @Step("fill Informative Document Data")
    public void fillInformativeDocumentData(String fieldName, String inputData) {
        try {
            waitForPageFinishLoading();
            for (int i = 0; i < informativeFieldNameList.size(); i++) {
                scrollToElement(informativeFieldNameList.get(i));
                if (getText(informativeFieldNameList.get(i)).equalsIgnoreCase(fieldName)) {
                    scrollToElement(informativeInputNameList.get(i));
                    waitForElementToBeClickable(informativeInputNameList.get(i));
                    fillText(informativeInputNameList.get(i), inputData);
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong.");
        }
    }


    @Step("fill Upload Document Data")
    public void fillUploadDocumentData(String fieldName, String inputData, String Formats) {
        try {
            waitForPageFinishLoading();
            for (int i = 0; i < uploadFieldNameList.size(); i++) {
                scrollToElement(uploadFieldNameList.get(i));
                if (getText(uploadFieldNameList.get(i)).equalsIgnoreCase(fieldName)) {

                    switch (fieldName) {
                        case "Document Name":
                        case "Document Identification":
                        case "Maximum Size Allowed": {
                            scrollToElement(uploadInputNameList.get(i));
                            waitForElementToBeClickable(uploadInputNameList.get(i));
                            fillText(uploadInputNameList.get(i), inputData);
                            break;
                        }
                        case "Allowed Formats": {
                            scrollToElement(uploadInputNameList.get(i));
                            click(uploadInputNameList.get(i));
                            chooseValueFromList(Formats);
                            break;
                        }
                    }
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong.");
        }
    }

    public void chooseValueFromList(String value) {
        waitForPageFinishLoading();
        WebElement el = driver.findElement(By.xpath(String.format("//*[text()='%s']", value)));
        click(el);
    }

    @Step("check Eform Document details")
    public String checkEformDocumentDetails(String fieldName) {
        try {
            String fieldValue = null;
            waitForPageFinishLoading();
            for (int i = 0; i < detailsFieldNameList.size(); i++) {
                scrollToElement(detailsFieldNameList.get(i));
                if (getText(detailsFieldNameList.get(i)).equalsIgnoreCase(fieldName)) {
                    scrollToElement(detailsFieldValueList.get(i));
                    fieldValue = getText(detailsFieldValueList.get(i));
                    return fieldValue;
                }
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong.");
        }
        return null;
    }


    @Step("document Management Title")
    public String documentManagementTitle(String text) {
        waitForTextToBeInElement(documentManagementTitle, text);
        return getText(documentManagementTitle);
    }

    @Step("document request Title page 1")
    public String newDocumentRequestTitle1(String text) {
        waitForTextToBeInElement(newDocumentRequestTitle1, text);
        return getText(newDocumentRequestTitle1);
    }

    @Step("document request Title page 2")
    public String newDocumentRequestTitle2(String text) {
        waitForTextToBeInElement(newDocumentRequestTitle2, text);
        return getText(newDocumentRequestTitle2);
    }

    @Step("insert text in search box")
    public void documentManagementSearchBar(String text) {
        waitForElementToBeVisibility(documentManagementSearchBar);
        fillText(documentManagementSearchBar, text);
    }


    @Step("search name in table")
    public boolean searchFormNameInTable(String documentName) {
        waitForElementToBeVisibility(documentManagementSearchBar);
        fillText(documentManagementSearchBar, documentName);
        for (WebElement el : documentNameList) {
            waitForElementToBeVisibility(el);
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(documentName)) {
                return true;
            }
        }
        return false;
    }


    @Step("search document type in table")
    public Boolean searchFormDocumentTypeInTable(String documentType) {
        waitForPageFinishLoading();
        for (WebElement el : documentTypeList) {
            waitForElementToBeVisibility(el);
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(documentType)) {
                return true;
            }
        }
        return false;
    }


    @Step("search name in table and click")
    public void searchFormNameInTableAndClick(String documentName) {
        waitForElementToBeVisibility(documentManagementSearchBar);
        fillText(documentManagementSearchBar, documentName);
        for (WebElement el : documentNameList) {
            waitForElementToBeVisibility(el);
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(documentName)) {
                scrollToElement(el);
                click(el);
                break;
            }
        }
    }

    @Step("inside Doc Title")
    public String insideDocTitle() {
        waitForElementToBeVisibility(insideDocTitle);
        return getText(insideDocTitle);
    }

    @Step("click add New Document Button")
    public void addNewDocumentButton() {
        waitForElementToBeClickable(addNewDocumentButton);
        click(addNewDocumentButton);
    }


    @Step("check if add New Document Button display")
    public boolean checkIfNewDocumentButtonDisplay() {
        try {
            waitForPageFinishLoading();
            if (isDisplayed(addNewDocumentButton)) {
                scrollToElement(addNewDocumentButton);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("click add new Document Request Continue Button")
    public void newDocumentContinueButton() {
        waitForPageFinishLoading();
        sleep(2000);// this sleep exist, because in e-form create form there is a loading popup that interrupts the continue button
        waitForElementToBeClickable(newDocumentRequestContinueButton);
        click(newDocumentRequestContinueButton);
    }

    @Step("click add new Document Request Continue Button")
    public void newDocumentContinueButtonForEForm() {
        waitForPageFinishLoading();
        sleep(5000); // this sleep exist, because in e-form create form there is a loading popup that interrupts the continue button
        waitForElementToBeClickable(newDocumentRequestContinueButton);
        click(newDocumentRequestContinueButton);
    }


    @Step("click add new Document Request Buttons Cancel or Continue")
    public void newDocumentClickOnButton(String buttonName) {
        waitForPageFinishLoading();
        for (WebElement el : newDocumentRequestButtons) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(buttonName)) {
                scrollToElement(el);
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }

    }


    @Step("choose document row From Data Mapper Table")
    public void addNewDocumentTypeList(String documentName) {
        waitForPageFinishLoading();
        for (WebElement el : documentManagementRows) {
            if (getText(el).equalsIgnoreCase(documentName)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }


    @Step("choose new document Type From list")
    public void chooseNewDocumentTypeFromList(String documentName) {
        waitForPageFinishLoading();
        for (WebElement el : addNewDocumentTypeList) {
            if (getText(el).equalsIgnoreCase(documentName)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("create random string")
    public String randomString() {

        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkelmonpqrstuvwxyz";
        // create random string builder
        StringBuilder sb = new StringBuilder();
        // create an object of Random class
        Random random = new Random();
        // specify length of random string
        int length = 10;
        for (int i = 0; i < length; i++) {
            // generate random index number
            int index = random.nextInt(alphabet.length());
            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);
            // append the character to string builder
            sb.append(randomChar);
        }

        randomString = sb.toString();
        return randomString;
    }

    @Step("new document request continue Button Is enable")
    public boolean newDocumentRequestContinueButtonIsEnable() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(newDocumentRequestContinueButton);
        return isEnabled(newDocumentRequestContinueButton);
    }

    @Step("choose document type from list")
    public void chooseRowFromDocumentsTableList(String documentName) {
        waitForElementToBeVisibility(documentManagementSearchBar);
        documentManagementSearchBar(documentName);
        sleep(3000); //this sleep is to wait to load the search
        for (WebElement el : documentsTableList) {
            if (getText(el).contains(documentName)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        waitForPageFinishLoading();
    }

    @Step("Open creation time frame filter and choose date")
    public void creationTimeFrameChooseDate(String chooseFromTimeFrameList, String chooseOldDay, String
            chooseFutureDay, String chooseMonth) {
        waitForElementToBeClickable(creationTimeFrameSelect);
        click(creationTimeFrameSelect);
        for (WebElement el : creationTimeFrameList) {
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
        click(creationTimeFrameSelect);
    }

    @Step("Check the value of month")
    public String month() {
        waitForElementToBeVisibility(month);
        return getText(month);
    }


    @Step("check if current date is between date range")
    public boolean validateDateRange() {
        waitForPageFinishLoading();
        try {
            waitForElementToBeVisibility(selectedDate);
            String date = getText(selectedDate);
            String[] dateArray = date.split("-");

            String startDay = dateArray[0];
            Date startDate = new SimpleDateFormat("dd/MM/yy").parse(startDay);

            String endDay = dateArray[1];
            Date endDate = new SimpleDateFormat("dd/MM/yy").parse(endDay);


            for (WebElement el : creationDateList) {
                waitForElementToBeVisibility(el);
                Date currentDate = new SimpleDateFormat("dd/MM/yy").parse(String.format(getText(el), "dd/MM/yy"));
                if (currentDate.equals(startDate) || currentDate.after(startDate)) {
                    if (currentDate.equals(endDate) || currentDate.before(endDate)) {
                    } else {
                        return false;
                    }
                }
            }
            //move page
            while (isEnabled(paginationArrowRight)) {
                waitForElementToBeClickable(paginationArrowRight);
                click(paginationArrowRight);
                waitForPageFinishLoading();
                waitForList(creationDateList);
                for (WebElement elem : creationDateList) {
                    waitForElementToBeVisibility(elem);
                    Date currentDate = new SimpleDateFormat("dd/MM/yy").parse(String.format(getText(elem), "dd/MM/yy"));
                    if (currentDate.equals(startDate) || currentDate.after(startDate)) {
                        if (currentDate.equals(endDate) || currentDate.before(endDate)) {
                        } else {
                            return false;
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Step("validate the date in creation time List")
    public void chooseDateRangeFromRadioList(String dateRange) {
        waitForPageFinishLoading();
        waitForElementToBeClickable(creationTimeFrameSelect);
        click(creationTimeFrameSelect);
        waitForList(creationTimeFrameList);
        for (WebElement el : creationTimeFrameList) {
            if (getText(el).equalsIgnoreCase(dateRange)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
    }

    @Step("upload example image to Doc Request")
    public void uploadExampleImageToDocRequest(String filePath) {
        waitForPageFinishLoading();
        waitForElementToBeClickable(uploadFileInDocRequestButton);

        File file = new File(filePath);
        uploadFile(uploadFileInDocRequest, file.getAbsolutePath());
    }

    @Step("get text of upload File In Doc Request Button")
    public String uploadFileInDocRequestButtonText(String buttonName) {
        waitForTextToBeInElement(uploadFileInDocRequestButton, buttonName);
        return getText(uploadFileInDocRequestButton);
    }

    @Step("get text of new Doc Uploaded Name")
    public String newDocUploadedName(String fileName) {
        waitForTextToBeInElement(newDocUploadedName, fileName);
        return getText(newDocUploadedName);
    }

    @Step("click refresh Button In Example Image")
    public void refreshButtonInExampleImage() {
        waitForElementToBeClickable(refreshButtonInExampleImage);
        click(refreshButtonInExampleImage);
    }

    @Step("choose allowed format from select list")
    public boolean chooseFormatFileFromSelectList(String formatName) {
        try {
            waitForElementToBeClickable(allowedFormatBox);
            click(allowedFormatBox);
            waitForElementToBeVisibility(allowedFormatSelectList.get(0));
            for (WebElement el : allowedFormatSelectList) {
                if (getText(el).equalsIgnoreCase(formatName)) {
                    scrollToElement(el);
                    waitForElementToBeClickable(allowedFormatBox);
                    click(allowedFormatBox);
                    break;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("fill new doc request data ")
    public void fillNewDocRequest(String docName, String docIdentification, String formatName, String fileSize) {

        if (isEnabled(documentName)) {
            waitForElementToBeClickable(documentName);
            fillText(documentName, docName + randomString());
        }

        waitForElementToBeClickable(documentIdentification);
        fillText(documentIdentification, docIdentification);

        waitForElementToBeClickable(allowedFormatBox);
        click(allowedFormatBox);
        waitForElementToBeVisibility(allowedFormatSelectList.get(0));
        for (WebElement el : allowedFormatSelectList) {
            if (getText(el).equalsIgnoreCase(formatName)) {
                scrollToElement(el);
                click(el);
                break;
            }
        }
        waitForElementToBeClickable(maximumSizeAllowed);
        fillText(maximumSizeAllowed, fileSize);

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yy");
        formattedDate = myDateObj.format(myFormatObj);
    }

    @Step("get text for all the version doc row in doc versions page")
    public String docVersionsRowList() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(docVersionsRowList.get(0));
        return getText(docVersionsRowList.get(0));
    }

    @Step("get text version number in doc versions page")
    public String versionNumberList() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(versionNumberList.get(0));
        return getText(versionNumberList.get(0));
    }

    @Step("is edit doc button version is enable")
    public boolean editDocButtonIsEnabled() {
        waitForPageFinishLoading();
        try {
            mouseHoverOnElement(editDocButtonList.get(0));
            waitForElementToBeVisibility(editDocButtonList.get(0));
            return isEnabled(editDocButtonList.get(0));
        } catch (Exception e) {
            return false;
        }
    }

    @Step("click on edit Doc Button")
    public void editDocButton() {
        mouseHoverOnElement(editDocButtonList.get(0));
        waitForElementToBeClickable(editDocButtonList.get(0));
        click(editDocButtonList.get(0));
    }


    @Step("get text version number in doc versions page")
    public String editVersionTitle() {
        waitForElementToBeVisibility(editVersionTitle);
        return getText(editVersionTitle);
    }

    @Step("select today activation date from date calender")
    public void selectTodayAsActivationDate() {
        waitForElementToBeClickable(selectActivationDateButton);
        click(selectActivationDateButton);
        waitForElementToBeClickable(todayInActivationDateCalender);
        click(todayInActivationDateCalender);
        waitForElementToBeClickable(activationVersionButton);
        click(activationVersionButton);
    }

    @Step("select 15' in next month  date activation date from date calender")
    public void selectNextMonthAsActivationDate(String dayInTheMonth) {
        waitForElementToBeClickable(selectActivationDateButton);
        click(selectActivationDateButton);
        waitForElementToBeClickable(rightClickButtonInDateCalender);
        click(rightClickButtonInDateCalender);
        waitForPageFinishLoading();

        for (WebElement el : daysInCalenderList) {
            if (getText(el).equalsIgnoreCase(dayInTheMonth)) {
                waitForElementToBeClickable(el);
                click(el);
            }
        }
        waitForPageFinishLoading();
        waitForElementToBeClickable(activationVersionButton);
        click(activationVersionButton);
    }


    @Step("get text activation Date in doc row")
    public String activationDate() {
        waitForElementToBeVisibility(activationDate);
        return getText(activationDate);
    }

    @Step("click on add New Version Button")
    public void addNewVersionButton() {
        waitForElementToBeClickable(addNewVersionButton);
        click(addNewVersionButton);
    }

    @Step("check if add New Version Button display")
    public boolean checkAddNewVersionButtonDisplay() {
        try {
            waitForPageFinishLoading();
            scrollToElement(addNewVersionButton);
            if (isDisplayed(addNewVersionButton)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }

    @Step("get text of new Version Title")
    public String newVersionTitle() {
        waitForElementToBeVisibility(newVersionTitle);
        return getText(newVersionTitle);
    }


    @Step("is doc name in new version page is enable")
    public boolean docNameInNewVersionPageIsEnable() {
        try {
            waitForElementToBeVisibility(docNameInNewVersionPage);
            if (isEnabled(docNameInNewVersionPage)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Step("is add new version button is enable inside doc page")
    public boolean addNewVersionButtonIsEnable() {
        try {
            waitForElementToBeVisibility(addNewVersionButton);
            isEnabled(addNewVersionButton);
            return isEnabled(addNewVersionButton);
        } catch (Exception e) {
            return false;
        }
    }


    @Step("get text doc Status from list - inside doc page")
    public String getStatusFromList(int num) {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(statusList.get(num));
        return getText(statusList.get(num));
    }

    @Step("get expiration date from list - inside doc page")
    public String getExpirationDateFromList(int num) {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(expirationList.get(num));
        return getText(expirationList.get(num));
    }


    @Step("fill new Informative Document data ")
    public void fillNewInformativeDocument(String docName, String docIdentification) {

        if (isEnabled(documentName)) {
            waitForElementToBeClickable(documentName);
            fillText(documentName, docName + randomString());
        }
        waitForElementToBeClickable(documentIdentification);
        fillText(documentIdentification, docIdentification);

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yy");
        formattedDate = myDateObj.format(myFormatObj);
    }

    @Step("fill new Informative Document data ")
    public void fillNewEForm(String docName, String docIdentification, String templateIdInput) {

        if (isEnabled(eFormDocName)) {
            waitForElementToBeClickable(eFormDocName);
            fillText(eFormDocName, docName + randomString());
        }
        waitForElementToBeClickable(eFormIdentificationName);
        fillText(eFormIdentificationName, docIdentification);

        waitForElementToBeClickable(eFormTemplateId);
        fillText(eFormTemplateId, templateIdInput);

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yy");
        formattedDate = myDateObj.format(myFormatObj);
    }

    @Step("get text of something went wrong message")
    public String somethingWentWrongMessage() {
        waitForElementToBeVisibility(somethingWentWrongMessage);
        return getText(somethingWentWrongMessage);
    }

    @Step("click on ok Take Me Back Button")
    public void okTakeMeBackButton() {
        waitForElementToBeClickable(okTakeMeBackButton);
        click(okTakeMeBackButton);
    }

    @Step("get text of template Fields Title")
    public String templateFieldsTitle() {
        waitForElementToBeVisibility(templateFieldsTitle);
        return getText(templateFieldsTitle);
    }

    @Step("get text of template Fields Title")
    public String eFormDocInfoTitlesList(String text) {
        waitForList(eFormDocInfoTitlesList);
        String titleInput = null;
        for (WebElement el : eFormDocInfoTitlesList) {
            if (getText(el).contains(text)) {
                waitForElementToBeVisibility(el);
                titleInput = getText(el);
            }
        }
        return titleInput;
    }

    @Step("click eForm Edit Details Button")
    public void eFormEditDetailsButton() {
        waitForElementToBeClickable(eFormEditDetailsButton);
        click(eFormEditDetailsButton);
    }

    @Step("get text of eForm Title")
    public String eFormTitle() {
        waitForElementToBeVisibility(eFormTitle);
        return getText(eFormTitle);
    }

    @Step("click arrays Button")
    public void arraysButton() {
        waitForElementToBeClickable(arraysButton);
        click(arraysButton);
    }

    @Step("choose all templates fields and  select type/category/dbName")
    public boolean addingTemplateFieldsNamesToEForm(String entityType, String businessCategory, String dbFieldName) {
        try {
            for (int i = 0; i < templateFieldNameCheckBoxList.size(); i++) {
                mouseHoverOnElement(templateFieldNameCheckBoxList.get(i));
//                scrollToElement(templateFieldNameCheckBoxList.get(i));
                click(templateFieldNameCheckBoxList.get(i));

                for (int j = i; j <= entityTypeSelectList.size(); j++) {
                    waitForElementToBeClickable(entityTypeSelectList.get(j));
                    click(entityTypeSelectList.get(j));
                    waitForList(selectBoxList);
                    for (WebElement el : selectBoxList) {
                        if (getText(el).contains(entityType)) {
                            waitForElementToBeClickable(el);
                            click(el);
                            break;
                        }
                    }

                    waitForElementToBeClickable(businessCategorySelectList.get(j));
                    click(businessCategorySelectList.get(j));
                    waitForPageFinishLoading();
                    for (WebElement el : selectBoxList) {
                        if (getText(el).contains(businessCategory)) {
                            waitForElementToBeClickable(el);
                            click(el);
                            break;
                        }
                    }

                    waitForElementToBeClickable(dbFieldNameSelectList.get(j));
                    click(dbFieldNameSelectList.get(j));
                    waitForPageFinishLoading();
                    for (WebElement el : selectBoxList) {
                        if (getText(el).contains(dbFieldName)) {
                            waitForElementToBeClickable(el);
                            click(el);
                            break;
                        }
                    }
                    break;
                }
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Step("choose all templates fields from Array tables")
    public boolean addingTemplateFieldsFromArrayInEForm(String entityType, String businessCategory, String dbFieldName) {

        try {
            int count = arraysCheckBoxList.size();
            for (int a = count - 1; a >= 0; a--) {
                click(arraysCheckBoxList.get(a));
            }

            for (int b = count - 1; b >= 0; b--) {
                waitForElementToBeClickable(EntityTypeList.get(b));
                mouseHoverOnElement(businessCategoryList.get(b));
                click(EntityTypeList.get(b));
                waitForList(selectBoxList);
                for (WebElement el : selectBoxList) {
                    if (getText(el).contains(entityType)) {
                        waitForElementToBeClickable(el);
                        click(el);
                        break;
                    }
                }


                waitForElementToBeClickable(businessCategoryList.get(b));
                mouseHoverOnElement(businessCategoryList.get(b));
                click(businessCategoryList.get(b));
                for (WebElement el : selectBoxList) {
                    if (getText(el).contains(businessCategory)) {
                        waitForElementToBeClickable(el);
                        click(el);
                        break;
                    }
                }
            }

            for (int i = 0; i < templateFieldNameCheckboxList.size(); i++) {
                mouseHoverOnElement(templateFieldNameCheckboxList.get(i));
                click(templateFieldNameCheckboxList.get(i));

                for (int j = i; j < dbFieldNameList.size(); j++) {
                    mouseHoverOnElement(dbFieldNameList.get(j));
                    waitForElementToBeClickable(dbFieldNameList.get(j));
                    click(dbFieldNameList.get(j));
                    for (WebElement el : selectBoxList) {
                        if (getText(el).contains(dbFieldName)) {
                            waitForElementToBeClickable(el);
                            click(el);
                            break;
                        }
                    }
                    break;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Step("is pagination Arrow Right Is Enabled")
    public boolean paginationArrowRightIsEnabled() {
        waitForPageFinishLoading();
        try {
            scrollToElement(paginationArrowRight);
            waitForElementToBeVisibility(paginationArrowRight);
            if (isEnabled(paginationArrowRight)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Step("pagination Arrow Right Button")
    public void paginationArrowRight() {
        waitForElementToBeClickable(paginationArrowRight);
        click(paginationArrowRight);
    }

    @Step("status filter Select Button")
    public void statusSelectButton() {
        waitForElementToBeClickable(statusSelectButton);
        click(statusSelectButton);
    }

    @Step("clear status Filter Button")
    public void clearFilterButton() {
        waitForElementToBeClickable(clearFilter);
        click(clearFilter);
    }

    @Step("choose Docs Status Filter From Select List")
    public void chooseDocsStatusFilterFromSelectList(String StatusFilterButton) {
        statusSelectButton();
        waitForPageFinishLoading();

        for (WebElement el : statusSelectList) {
            if (getText(el).contains(StatusFilterButton)) {
                scrollToElement(el);
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        statusSelectButton();
    }

    @Step("choose Docs Status Filter From Select List")
    public boolean isDocNameExistInDocsTableList(String docsName) {
        waitForPageFinishLoading();
        try {
            for (WebElement el : documentsTableList) {
                scrollToElement(el);
                if (getText(el).contains(docsName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
