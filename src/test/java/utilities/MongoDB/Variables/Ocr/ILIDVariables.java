package utilities.MongoDB.Variables.Ocr;

import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.Step;

public class ILIDVariables extends OcrHandlers {

    private static String dateOfBirth;
    private static String dateOfExpiry;
    private static String dateOfIssue;
    private static String firstNameHebrew;
    private static String gender;
    private static String idNumber;
    private static String idNumber2;
    private static String lastNameHebrew;
    private static String placeOfBirth;
    private static String citizenship;
    private static String cardType;
    private static String cardType2;
    private static String faceImage;
    private static String inputImage;
    private static String inputImage2;
    private static String croppedImage;
    private static String croppedImage2;
    private static boolean imageIsColorful;
    private static boolean imageIsColorful2;
    private static boolean idChecksumValid;
    private static boolean idChecksumValid2;
    private static boolean templateMatch;
    private static boolean templateMatch2;
    private static boolean periodBetweenIssueAndExpiryValid;
    private static boolean StampDetected;
    private static boolean faceSizeValid;
    private static boolean facePositionValid;
    private static boolean faceRotationValid;
    private static boolean chipAuth;
    private static boolean faceImageReplacedWithPassportImage;
    private static boolean idNumberMatchesFront;
    private static boolean documentInFrame;
    private static boolean documentInFrame2;
    private static boolean expiryDateValid;
    private static boolean issueDateValid;

    public ILIDVariables() {
        dateOfBirth = null;
        dateOfExpiry = null;
        dateOfIssue = null;
        firstNameHebrew = null;
        gender = null;
        idNumber = null;
        idNumber2 = null;
        lastNameHebrew = null;
        placeOfBirth = null;
        citizenship = null;
        cardType = null;
        cardType2 = null;
        faceImage = null;
        inputImage = null;
        inputImage2 = null;
        croppedImage = null;
        croppedImage2 = null;
        imageIsColorful = false;
        imageIsColorful2 = false;
        idChecksumValid = false;
        idChecksumValid2 = false;
        templateMatch = false;
        templateMatch2 = false;
        periodBetweenIssueAndExpiryValid = false;
        StampDetected = false;
        faceSizeValid = false;
        facePositionValid = false;
        faceRotationValid = false;
        chipAuth = false;
        faceImageReplacedWithPassportImage = false;
        idNumberMatchesFront = false;
        documentInFrame = false;
        documentInFrame2 = false;
        expiryDateValid = false;
        issueDateValid = false;
    }

//    @Step("Set front side values")
    public static void setBioIdFrontVariables(String jsonResult, int i) {
        try {
            setOcrFrontSideVariables(jsonResult, i);
            if (jsonResult.contains("last_name_hebrew")) {
                setLastNameHebrew(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.last_name_hebrew"));
            } else setLastNameHebrew(null);
            if (jsonResult.contains("first_name_hebrew")) {
                setFirstNameHebrew(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.first_name_hebrew"));
            } else setFirstNameHebrew(null);
            if (jsonResult.contains("date_of_birth")) {
                setDateOfBirth(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.date_of_birth"));
            } else setDateOfBirth(null);
            if (jsonResult.contains("date_of_issue")) {
                setDateOfIssue(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.date_of_issue"));
            } else setDateOfIssue(null);
            if (jsonResult.contains("date_of_expiry")) {
                setDateOfExpiry(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.date_of_expiry"));
            } else setDateOfExpiry(null);
            if (jsonResult.contains("id_number")) {
                setIdNumber(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.id_number"));
            } else setIdNumber(null);
            if (jsonResult.contains("card_type")) {
                setCardType(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.card_type"));
            } else setCardType(null);
            if (jsonResult.contains("face_image")) {
                setFaceImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.face_image"));
            } else setFaceImage(null);
            if (jsonResult.contains("input_image")) {
                setInputImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.input_image"));
            } else setInputImage(null);
            if (jsonResult.contains("cropped_image")) {
                setCroppedImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.cropped_image"));
            } else setCroppedImage(null);
            if (jsonResult.contains("image_is_colorful")) {
                setImageIsColorful(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.image_is_colorful"));
            } else setImageIsColorful(false);
            if (jsonResult.contains("id_checksum_valid")) {
                setIdChecksumValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.id_checksum_valid"));
            } else setIdChecksumValid(false);
            if (jsonResult.contains("template_matched")) {
                setTemplateMatch(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.template_matched"));
            } else setTemplateMatch(false);
            if (jsonResult.contains("period_between_issue_and_expiry_valid")) {
                setPeriodBetweenIssueAndExpiryValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.period_between_issue_and_expiry_valid"));
            } else setPeriodBetweenIssueAndExpiryValid(false);
            if (jsonResult.contains("chip_auth")) {
                setChipAuth(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.chip_auth"));
            } else setChipAuth(false);
            if (jsonResult.contains("face_image_replaced_with_passport_image")) {
                setFaceImageReplacedWithPassportImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.face_image_replaced_with_passport_image"));
            } else setFaceImageReplacedWithPassportImage(false);
            if (jsonResult.contains("face_size_valid")) {
                setFaceSizeValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.face_size_valid"));
            } else setFaceSizeValid(false);
            if (jsonResult.contains("face_position_valid")) {
                setFacePositionValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.face_position_valid"));
            } else setFacePositionValid(false);
            if (jsonResult.contains("face_rotation_valid")) {
                setFaceRotationValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.face_rotation_valid"));
            } else setFaceRotationValid(false);
            if (jsonResult.contains("document_in_frame")) {
                setDocumentInFrame(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.document_in_frame"));
            } else setDocumentInFrame(false);
            if (jsonResult.contains("expiry_date_valid")) {
                setExpiryDateValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.expiry_date_valid"));
            } else setExpiryDateValid(false);
            if (jsonResult.contains("issue_date_valid")) {
                setIssueDateValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.issue_date_valid"));
            } else setIssueDateValid(false);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    @Step("Set back side values")
    public static void setBioIdBackVariables(String jsonResult, int i) {
        try {
            setOcrBackSideVariables(jsonResult, i);
            if (jsonResult.contains("id_number")) {
                setIdNumber2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.id_number"));
            } else setIdNumber2(null);
            if (jsonResult.contains("place_of_birth")) {
                setPlaceOfBirth(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.place_of_birth"));
            } else setPlaceOfBirth(null);
            if (jsonResult.contains("gender")) {
                setGender(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.gender"));
            } else setGender(null);
            if (jsonResult.contains("citizenship")) {
                setCitizenship(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.citizenship"));
            } else setCitizenship(null);
            if (jsonResult.contains("card_type")) {
                setCardType2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.card_type"));
            } else setCardType2(null);
            if (jsonResult.contains("input_image")) {
                setInputImage2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.input_image"));
            } else setInputImage2(null);
            if (jsonResult.contains("cropped_image")) {
                setCroppedImage2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.cropped_image"));
            } else setCroppedImage2(null);
            if (jsonResult.contains("image_is_colorful")) {
                setImageIsColorful2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.image_is_colorful"));
            } else setImageIsColorful2(false);
            if (jsonResult.contains("id_checksum_valid")) {
                setIdChecksumValid2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.id_checksum_valid"));
            } else setIdChecksumValid2(false);
            if (jsonResult.contains("template_matched")) {
                setTemplateMatch2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.template_matched"));
            } else setTemplateMatch2(false);
            if (jsonResult.contains("id_number_matches_front")) {
                setIdNumberMatchesFront(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.id_number_matches_front"));
            } else setIdNumberMatchesFront(false);
            if (jsonResult.contains("document_in_frame")) {
                setDocumentInFrame2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.document_in_frame"));
            } else setDocumentInFrame2(false);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void setBlueIdVariables(String jsonResult, int i) {
        try {
            setOcrFrontSideVariables(jsonResult, i);
            if (jsonResult.contains("date_of_birth")) {
                setDateOfBirth(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.date_of_birth"));
            } else setDateOfBirth(null);
            if (jsonResult.contains("date_of_expiry")) {
                setDateOfExpiry(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.date_of_expiry"));
            } else setDateOfExpiry(null);
            if (jsonResult.contains("date_of_issue")) {
                setDateOfIssue(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.date_of_issue"));
            } else setDateOfIssue(null);
            if (jsonResult.contains("first_name_hebrew")) {
                setFirstNameHebrew(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.first_name_hebrew"));
            } else setFirstNameHebrew(null);
            if (jsonResult.contains("gender")) {
                setGender(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.gender"));
            } else setGender(null);
            if (jsonResult.contains("id_number")) {
                setIdNumber(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.id_number"));
            } else setIdNumber(null);
            if (jsonResult.contains("last_name_hebrew")) {
                setLastNameHebrew(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.last_name_hebrew"));
            } else setLastNameHebrew(null);
            if (jsonResult.contains("place_of_birth")) {
                setPlaceOfBirth(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.place_of_birth"));
            } else setPlaceOfBirth(null);
            if (jsonResult.contains("citizenship")) {
                setCitizenship(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.citizenship"));
            } else setCitizenship(null);
            if (jsonResult.contains("card_type")) {
                setCardType(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.card_type"));
            } else setCardType(null);
            if (jsonResult.contains("face_image")) {
                setFaceImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.face_image"));
            } else setFaceImage(null);
            if (jsonResult.contains("input_image")) {
                setInputImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.input_image"));
            } else setInputImage(null);
            if (jsonResult.contains("cropped_image")) {
                setCroppedImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.cropped_image"));
            } else setCroppedImage(null);
            if (jsonResult.contains("image_is_colorful")) {
                setImageIsColorful(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.image_is_colorful"));
            } else setImageIsColorful(false);
            if (jsonResult.contains("id_checksum_valid")) {
                setIdChecksumValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.id_checksum_valid"));
            } else setIdChecksumValid(false);
            if (jsonResult.contains("template_matched")) {
                setTemplateMatch(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.template_matched"));
            } else setTemplateMatch(false);
            if (jsonResult.contains("period_between_issue_and_expiry_valid")) {
                setPeriodBetweenIssueAndExpiryValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.period_between_issue_and_expiry_valid"));
            } else setPeriodBetweenIssueAndExpiryValid(false);
            if (jsonResult.contains("stamp_detected")) {
                setStampDetected(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.stamp_detected"));
            } else setStampDetected(false);
            if (jsonResult.contains("face_size_valid")) {
                setFaceSizeValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.face_size_valid"));
            } else setFaceSizeValid(false);
            if (jsonResult.contains("face_position_valid")) {
                setFacePositionValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.face_position_valid"));
            } else setFacePositionValid(false);
            if (jsonResult.contains("face_rotation_valid")) {
                setFaceRotationValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.face_rotation_valid"));
            } else setFaceRotationValid(false);
            if (jsonResult.contains("document_in_frame")) {
                setDocumentInFrame(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.document_in_frame"));
            } else setDocumentInFrame(false);
            if (jsonResult.contains("expiry_date_valid")) {
                setExpiryDateValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.expiry_date_valid"));
            } else setExpiryDateValid(false);
            if (jsonResult.contains("issue_date_valid")) {
                setIssueDateValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.issue_date_valid"));
            } else setIssueDateValid(false);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void setGreenIdVariables(String jsonResult, int i) {
        try {
            setOcrFrontSideVariables(jsonResult, i);
            if (jsonResult.contains("date_of_birth")) {
                setDateOfBirth(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.date_of_birth"));
            } else setDateOfBirth(null);
            if (jsonResult.contains("date_of_issue")) {
                setDateOfIssue(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.date_of_issue"));
            } else setDateOfIssue(null);
            if (jsonResult.contains("first_name_hebrew")) {
                setFirstNameHebrew(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.first_name_hebrew"));
            } else setFirstNameHebrew(null);
            if (jsonResult.contains("gender")) {
                setGender(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.gender"));
            } else setGender(null);
            if (jsonResult.contains("id_number")) {
                setIdNumber(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.id_number"));
            } else setIdNumber(null);
            if (jsonResult.contains("last_name_hebrew")) {
                setLastNameHebrew(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.last_name_hebrew"));
            } else setLastNameHebrew(null);
            if (jsonResult.contains("place_of_birth")) {
                setPlaceOfBirth(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.place_of_birth"));
            } else setPlaceOfBirth(null);
            if (jsonResult.contains("card_type")) {
                setCardType(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.card_type"));
            } else setCardType(null);
            if (jsonResult.contains("face_image")) {
                setFaceImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.face_image"));
            } else setFaceImage(null);
            if (jsonResult.contains("input_image")) {
                setInputImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.input_image"));
            } else setInputImage(null);
            if (jsonResult.contains("cropped_image")) {
                setCroppedImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.cropped_image"));
            } else setCroppedImage(null);
            if (jsonResult.contains("image_is_colorful")) {
                setImageIsColorful(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.image_is_colorful"));
            } else setImageIsColorful(false);
            if (jsonResult.contains("id_checksum_valid")) {
                setIdChecksumValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.id_checksum_valid"));
            } else setIdChecksumValid(false);
            if (jsonResult.contains("template_matched")) {
                setTemplateMatch(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.template_matched"));
            } else setTemplateMatch(false);
            if (jsonResult.contains("stamp_detected")) {
                setStampDetected(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.stamp_detected"));
            } else setStampDetected(false);
            if (jsonResult.contains("face_size_valid")) {
                setFaceSizeValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.face_size_valid"));
            } else setFaceSizeValid(false);
            if (jsonResult.contains("face_position_valid")) {
                setFacePositionValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.face_position_valid"));
            } else setFacePositionValid(false);
            if (jsonResult.contains("face_rotation_valid")) {
                setFaceRotationValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.face_rotation_valid"));
            } else setFaceRotationValid(false);
            if (jsonResult.contains("document_in_frame")) {
                setDocumentInFrame(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.document_in_frame"));
            } else setDocumentInFrame(false);
            if (jsonResult.contains("issue_date_valid")) {
                setIssueDateValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.issue_date_valid"));
            } else setIssueDateValid(false);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //###############################################

    public static boolean isDocumentInFrame() {
        return documentInFrame;
    }

    public static void setDocumentInFrame(boolean documentInFrame) {
        ILIDVariables.documentInFrame = documentInFrame;
    }

    public static boolean isDocumentInFrame2() {
        return documentInFrame2;
    }

    public static void setDocumentInFrame2(boolean documentInFrame2) {
        ILIDVariables.documentInFrame2 = documentInFrame2;
    }

    public static boolean isExpiryDateValid() {
        return expiryDateValid;
    }

    public static void setExpiryDateValid(boolean expiryDateValid) {
        ILIDVariables.expiryDateValid = expiryDateValid;
    }

    public static boolean isIssueDateValid() {
        return issueDateValid;
    }

    public static void setIssueDateValid(boolean issueDateValid) {
        ILIDVariables.issueDateValid = issueDateValid;
    }

    public static String getIdNumber2() {
        return idNumber2;
    }

    public static void setIdNumber2(String idNumber2) {
        ILIDVariables.idNumber2 = idNumber2;
    }

    public static boolean isImageIsColorful2() {
        return imageIsColorful2;
    }

    public static void setImageIsColorful2(boolean imageIsColorful2) {
        ILIDVariables.imageIsColorful2 = imageIsColorful2;
    }

    public static boolean isIdChecksumValid2() {
        return idChecksumValid2;
    }

    public static void setIdChecksumValid2(boolean idChecksumValid2) {
        ILIDVariables.idChecksumValid2 = idChecksumValid2;
    }

    public static boolean isTemplateMatch2() {
        return templateMatch2;
    }

    public static void setTemplateMatch2(boolean templateMatch2) {
        ILIDVariables.templateMatch2 = templateMatch2;
    }

    public static boolean isIdNumberMatchesFront() {
        return idNumberMatchesFront;
    }

    public static void setIdNumberMatchesFront(boolean idNumberMatchesFront) {
        ILIDVariables.idNumberMatchesFront = idNumberMatchesFront;
    }

    public static String getInputImage2() {
        return inputImage2;
    }

    public static void setInputImage2(String inputImage2) {
        ILIDVariables.inputImage2 = inputImage2;
    }

    public static String getCroppedImage2() {
        return croppedImage2;
    }

    public static void setCroppedImage2(String croppedImage2) {
        ILIDVariables.croppedImage2 = croppedImage2;
    }

    public static String getCardType2() {
        return cardType2;
    }

    public static void setCardType2(String cardType2) {
        ILIDVariables.cardType2 = cardType2;
    }

    public static boolean isChipAuth() {
        return chipAuth;
    }

    public static void setChipAuth(boolean chipAuth) {
        ILIDVariables.chipAuth = chipAuth;
    }

    public static boolean isFaceImageReplacedWithPassportImage() {
        return faceImageReplacedWithPassportImage;
    }

    public static void setFaceImageReplacedWithPassportImage(boolean faceImageReplacedWithPassportImage) {
        ILIDVariables.faceImageReplacedWithPassportImage = faceImageReplacedWithPassportImage;
    }

    public static String getDateOfBirth() {
        return dateOfBirth;
    }

    public static void setDateOfBirth(String dateOfBirth) {
        ILIDVariables.dateOfBirth = dateOfBirth;
    }

    public static String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public static void setDateOfExpiry(String dateOfExpiry) {
        ILIDVariables.dateOfExpiry = dateOfExpiry;
    }

    public static String getDateOfIssue() {
        return dateOfIssue;
    }

    public static void setDateOfIssue(String dateOfIssue) {
        ILIDVariables.dateOfIssue = dateOfIssue;
    }

    public static String getFirstNameHebrew() {
        return firstNameHebrew;
    }

    public static void setFirstNameHebrew(String firstNameHebrew) {
        ILIDVariables.firstNameHebrew = firstNameHebrew;
    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        ILIDVariables.gender = gender;
    }

    public static String getIdNumber() {
        return idNumber;
    }

    public static void setIdNumber(String idNumber) {
        ILIDVariables.idNumber = idNumber;
    }

    public static String getLastNameHebrew() {
        return lastNameHebrew;
    }

    public static void setLastNameHebrew(String lastNameHebrew) {
        ILIDVariables.lastNameHebrew = lastNameHebrew;
    }

    public static String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public static void setPlaceOfBirth(String placeOfBirth) {
        ILIDVariables.placeOfBirth = placeOfBirth;
    }

    public static String getCitizenship() {
      //  System.out.println(citizenship);
        return citizenship;
    }

    public static void setCitizenship(String citizenship) {
        ILIDVariables.citizenship = citizenship;
    }

    public static String getCardType() {
        return cardType;
    }

    public static void setCardType(String cardType) {
        ILIDVariables.cardType = cardType;
    }

    public static String getFaceImage() {
        return faceImage;
    }

    public static void setFaceImage(String faceImage) {
        ILIDVariables.faceImage = faceImage;
    }

    public static String getInputImage() {
        return inputImage;
    }

    public static void setInputImage(String inputImage) {
        ILIDVariables.inputImage = inputImage;
    }

    public static String getCroppedImage() {
        return croppedImage;
    }

    public static void setCroppedImage(String croppedImage) {
        ILIDVariables.croppedImage = croppedImage;
    }

    public static boolean isImageIsColorful() {
        return imageIsColorful;
    }

    public static void setImageIsColorful(boolean imageIsColorful) {
        ILIDVariables.imageIsColorful = imageIsColorful;
    }

    public static boolean isIdChecksumValid() {
        return idChecksumValid;
    }

    public static void setIdChecksumValid(boolean idChecksumValid) {
        ILIDVariables.idChecksumValid = idChecksumValid;
    }

    public static boolean isTemplateMatch() {
        return templateMatch;
    }

    public static void setTemplateMatch(boolean templateMatch) {
        ILIDVariables.templateMatch = templateMatch;
    }

    public static boolean isPeriodBetweenIssueAndExpiryValid() {
        return periodBetweenIssueAndExpiryValid;
    }

    public static void setPeriodBetweenIssueAndExpiryValid(boolean periodBetweenIssueAndExpiryValid) {
        ILIDVariables.periodBetweenIssueAndExpiryValid = periodBetweenIssueAndExpiryValid;
    }

    public static boolean isStampDetected() {
        return StampDetected;
    }

    public static void setStampDetected(boolean stampDetected) {
        StampDetected = stampDetected;
    }

    public static boolean isFaceSizeValid() {
        return faceSizeValid;
    }

    public static void setFaceSizeValid(boolean faceSizeValid) {
        ILIDVariables.faceSizeValid = faceSizeValid;
    }

    public static boolean isFacePositionValid() {
        return facePositionValid;
    }

    public static void setFacePositionValid(boolean facePositionValid) {
        ILIDVariables.facePositionValid = facePositionValid;
    }

    public static boolean isFaceRotationValid() {
        return faceRotationValid;
    }

    public static void setFaceRotationValid(boolean faceRotationValid) {
        ILIDVariables.faceRotationValid = faceRotationValid;
    }
}
