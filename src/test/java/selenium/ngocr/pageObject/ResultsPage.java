package selenium.ngocr.pageObject;

import api.Variables;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BasePage;

import java.util.List;

public class ResultsPage extends BasePage {
    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".Result_container__list__TNoJj > .CCResultDataCell_container__6BHng")
    protected List<WebElement> resultsList;
    @FindBy(css = ".Result_container__list__TNoJj > .CCResultImageCell_container__K\\+SRl")
    protected List<WebElement> imagesList;
    @FindBy(css = ".Result_container__list__TNoJj > .CCResultImageCell_container__val__4RGJT")
    protected List<WebElement> imagesListValues;

    @FindBy(css = ".Result_container__title__6yOH9")
    protected WebElement resultsTitle;

    @FindBy(css = ".Result_container__title__6yOH9")
    protected List<WebElement> resultsTitleList;
    @FindBy(css = ".Result_container__button__YgIaQ")
    protected WebElement completeButton;

    @Step("Return the results and images lists sizes")
    public int verifyListsSizes() {
        waitForPageFinishLoading();
        waitForList(resultsList);
        waitForList(imagesList);
        System.out.println("results size: " + resultsList.size());
        System.out.println("images size: " + imagesList.size());
        return resultsList.size() + imagesList.size();
    }

    @Step("Return the results and images lists sizes")
    public int verifyListsSizes(int timeout) {
//        waitForPageFinishLoading();
        waitForList(resultsList, timeout);
        waitForList(imagesList);
        System.out.println("results size: " + resultsList.size());
        System.out.println("images size: " + imagesList.size());
        return resultsList.size() + imagesList.size();
    }

    @Step("Return the result page title")
    public boolean getResultPageTitle() {
        try {
            waitForPageFinishLoading();
            scrollToElement(resultsTitle);
            return isDisplayed(resultsTitle);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Step("Check if result key exist and return his value")
    public String valueToCheck(Variables variables, String key) {
        waitForElementToBeVisibility(resultsTitle);
        waitForElementToBeVisibility(completeButton);
        isDisplayed(resultsTitle);
        isDisplayed(completeButton);

        String resultsValueRow = null;
        for (WebElement el : resultsList) {
            scrollToElement(el);
            waitForElementToBeVisibility(el);
            if (getText(el).contains(key)) {
                resultsValueRow = getText(el);
                if (getText(el).contains("status")) {
                    variables.setStatus(resultsValueRow.split("\n")[1]);
                } else if (getText(el).contains("caseId")) {
                    variables.setCaseId(resultsValueRow.split("\n")[1]);
                    System.out.println("CaseId: " + variables.getCaseId());
                } else if (getText(el).contains("otp_number")) {
                    System.out.println("Otp: " + getText(el).split("\n")[1]);
                    sleep(2000);
                    if (getText(el).length() == 3) {
                        System.out.println(getText(el));
                        variables.setOtpNum(Integer.parseInt(0 + resultsValueRow.split("\n")[1]));
                        System.out.println("New otp: " + variables.getOtpNum());
                    } else variables.setOtpNum(Integer.parseInt(resultsValueRow.split("\n")[1]));
                }
            }
        }
        for (WebElement el : imagesList) {
            if (getText(el).contains(key)) {
                resultsValueRow = getText(el);
            }
        }
        return resultsValueRow;
    }

    @Step("wait for results list")
    public void resultsWait(int timeout) {
        waitForList(resultsList, timeout);
    }

    @Step("Get all results")
    public void collectResults(Variables variables) {
        String resultsKey;
        String resultsValue;
        int side_counter = 0;
        boolean stt_found=false;
        waitForList(resultsList, 20);
        waitForList(imagesList, 20);
        for (WebElement el : resultsList) {
            String[] splitted = getText(el).split("\n");
            if (splitted.length == 0) {
                continue;
            } else if (splitted.length == 1) {
                resultsKey = splitted[0];
                resultsValue = "";
            } else {
                resultsKey = splitted[0];
                resultsValue = splitted[1];
            }
            switch (resultsKey) {
                case "action_type":
                    variables.setActionType(resultsValue);
                    break;
                case "status":
                    variables.setStatus(resultsValue);
                    break;
                case "caseId":
                    variables.setCaseId(resultsValue);
                    break;
                case "configFilename":
                    variables.setConfigFileName(resultsValue);
                    break;
                case "success":
                    variables.setSuccess(Boolean.parseBoolean(resultsValue));
                    break;

                case "card_type":
                    if (side_counter <= 0) {
                        variables.setCardType1(resultsValue);
                    } else {
                        variables.setCardType2(resultsValue);
                    }
                    side_counter++;
                    break;
                case "last_name_hebrew":
                    variables.setLastNameHebrew(resultsValue);
                    break;
                case "first_name_hebrew":
                    variables.setFirstNameHebrew(resultsValue);
                    break;
                case "last_name":
                case "last_name_english":
                    variables.setLastNameEnglish(resultsValue);
                    break;
                case "first_name":
                case "first_name_english":
                    variables.setFirstNameEnglish(resultsValue);
                    break;
                case "date_of_birth":
                    variables.setDateOfBirth(resultsValue);
                    break;
                case "date_of_issue":
                    variables.setDateOfIssue(resultsValue);
                    break;
                case "date_of_expiry":
                case "expiration_date":
                    variables.setDateOfExpiry(resultsValue);
                    break;
                case "id_number":
                    if (side_counter <= 1) {
                        variables.setIdNumber(Integer.parseInt(resultsValue));
                    } else {
                        variables.setIdNumber2(Integer.parseInt(resultsValue));
                    }
                    break;
                case "number_line":
                    variables.setCreditCardNumber(resultsValue);
                    break;
                case "permit_type":
                    variables.setPermitType(resultsValue);
                    break;
                case "full_name_hebrew":
                    variables.setFullNameHebrew(resultsValue);
                    break;
                case "full_name_english":
                    variables.setFullNameEnglish(resultsValue);
                    break;
                case "member_number":
                    variables.setMemberNumber(Integer.parseInt(resultsValue));
                    break;
                case "internet_code":
                    variables.setInternetCode(Integer.parseInt(resultsValue));
                    break;

                case "image_is_colorful":
                    if (side_counter <= 1) {
                        variables.setImageIsColorful(Boolean.parseBoolean(resultsValue));
                    } else {
                        variables.setImageIsColorful2(Boolean.parseBoolean(resultsValue));
                    }
                    break;
                case "id_checksum_valid":
                    if (side_counter <= 1) {
                        variables.setIdChecksumValid(Boolean.parseBoolean(resultsValue));
                    } else {
                        variables.setIdChecksumValid2(Boolean.parseBoolean(resultsValue));
                    }
                    break;
                case "template_matched":
                    if (side_counter <= 1) {
                        variables.setTemplateMatched(Boolean.parseBoolean(resultsValue));
                    } else {
                        variables.setTemplateMatched2(Boolean.parseBoolean(resultsValue));
                    }
                    break;
                case "document_in_frame":
                    if (side_counter <= 1) {
                        variables.setDocumentInFrame(Boolean.parseBoolean(resultsValue));
                    } else {
                        variables.setDocumentInFrame2(Boolean.parseBoolean(resultsValue));
                    }
                    break;
                case "stamp_detected":
                    variables.setStampDetected(Boolean.parseBoolean(resultsValue));
                case "period_between_issue_and_expiry_valid":
                    variables.setPeriodBetweenIssueAndExpiry(Boolean.parseBoolean(resultsValue));
                    break;
                case "chip_auth":
                    variables.setChipAuth(Boolean.parseBoolean(resultsValue));
                    break;
                case "face_image_replaced_with_passport_image":
                    variables.setFaceImageReplacedWithPassportImage(Boolean.parseBoolean(resultsValue));
                    break;
                case "face_size_valid":
                    variables.setFaceSizeValid(Boolean.parseBoolean(resultsValue));
                    break;
                case "face_position_valid":
                    variables.setFacePositionValid(Boolean.parseBoolean(resultsValue));
                    break;
                case "face_rotation_valid":
                    variables.setFaceRotationValid(Boolean.parseBoolean(resultsValue));
                    break;
                case "expiry_date_valid":
                    variables.setExpiryDateValid(Boolean.parseBoolean(resultsValue));
                    break;
                case "issue_date_valid":
                    variables.setIssueDateValid(Boolean.parseBoolean(resultsValue));
                    break;
                case "place_of_birth":
                    variables.setPlaceOfBirth(resultsValue);
                    break;
                case "gender":
                    variables.setGender(resultsValue);
                    break;
                case "citizenship":
                    variables.setCitizenship(resultsValue);
                    break;
                case "id_number_matches_front":
                    variables.setIdNumberMatchesFront(Boolean.parseBoolean(resultsValue));
                    break;
                case "address":
                    variables.setAddress(resultsValue);
                    break;
                case "license_categories":
                    variables.setLicenseCategories(resultsValue);
                    break;
                case "license_number":
                    variables.setLicenseNumber(Integer.parseInt(resultsValue));
                    break;
                case "b_year":
                    variables.setbYear(Integer.parseInt(resultsValue));
                    break;
                case "otp_number":
                    variables.setOtpNum(Integer.parseInt(resultsValue));
                    break;
                case "personal_number":
                    variables.setPersonalNumber(resultsValue);
                    break;
                case "cheque_number":
                    variables.setChequeNumber(resultsValue);
                    break;
                case "mrz_text":
                    variables.setMrzText(resultsValue);
                    break;
                case "mrz_type":
                    variables.setMrzType(resultsValue);
                    break;
                case "document_type":
                    variables.setDocumentType(resultsValue);
                    break;
                case "document_sub_type":
                    variables.setDocumentSubType(resultsValue);
                    break;
                case "issuing_country_code":
                    variables.setIssuingCountryCode(resultsValue);
                    break;
                case "passport_number":
                    variables.setPassportNumber(Integer.parseInt(resultsValue));
                    break;
                case "nationality_code":
                    variables.setNationalityCode(resultsValue);
                    break;
                case "cropped_field_image":
                    variables.setCroppedImage(resultsValue);
                    break;
                case "name":
                    variables.setErrorName(resultsValue);
                    break;
                case "message":
                    variables.setErrorMessage(resultsValue);
                    break;
                case "code":
                    variables.setErrorCode(Integer.parseInt(resultsValue));
                    break;
                case "sequenceSecondsInterval":
                    variables.setSequenceSecondsInterval(Integer.parseInt(resultsValue));
                    break;
                case "threshold":
                    if (!stt_found){
                        variables.setThreshold(Double.parseDouble(resultsValue));
                    }
                    else{
                        variables.setThreshold2(Double.parseDouble(resultsValue));
                    }
                    break;
                case "score":
                    if (!stt_found){

                        variables.setDblScore(Double.parseDouble(resultsValue));
                    }
                    else{
                        variables.setDblScore2(Double.parseDouble(resultsValue));
                    }
                    break;
                case "stt_text":
                    variables.setSttText(resultsValue);
                    stt_found = true;
                    break;
                case  "original_text":
                    variables.setOriginalText(resultsValue);
                    break;

            }
        }
        for (WebElement imageEl : imagesList) {
            String resultsImageKey = getText(imageEl);
            switch (resultsImageKey) {
                case "face_image":
                    variables.setFaceImage("face_image");
                    break;
                case "input_image":
                    if (variables.getInputImage1() == null) {
                        variables.setInputImage1("input_image");
                    } else {
                        variables.setInputImage2("input_image");
                    }
                    break;
                case "cropped_image":
                    if (variables.getCroppedImage1() == null) {
                        variables.setCroppedImage1("cropped_image");
                    } else {
                        variables.setCroppedImage2("cropped_image");
                    }
                    break;
                case "last_received_image":
                    variables.setLastReceivedImage("last_received_image");
                    break;
            }
        }

    }

    @Step("Verify all the images in the results page are real")
    public boolean verifyImagesAreReal() {
        try {
            for (WebElement el : imagesListValues) {
                imageIsDisplayed(el);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Step("Verify result title exist")
    public boolean titleExists() {
        if (resultsList.size()>0){
            return true;
        }
        else{
            return false;
        }
    }

    }
