package utilities.MongoDB.Variables.Ocr;

import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.Step;

public class ILDLVariables extends OcrHandlers {

    private static String lastNameHebrew;
    private static String lastNameEnglish;
    private static String firstNameHebrew;
    private static String firstNameEnglish;
    private static String dateOfBirth;
    private static String dateOfExpiry;
    private static String dateOfIssue;
    private static String licenseNumber;
    private static String idNumber;
    private static String idNumber2;
    private static String address;
    private static String licenseCategories;
    private static String cardType;
    private static String cardType2;
    private static String faceImage;
    private static String inputImage;
    private static String inputImage2;
    private static String croppedImage;
    private static String croppedImage2;
    private static boolean faceSizeValid;
    private static boolean facePositionValid;
    private static boolean faceRotationValid;
    private static String bYear;
    private static boolean documentInFrame;
    private static boolean documentInFrame2;
    private static boolean expiryDateValid;
    private static boolean idChecksumValid;
    private static boolean idChecksumValid2;
    private static boolean templateMatched;
    private static boolean templateMatched2;

    public ILDLVariables() {
        lastNameHebrew = null;
        lastNameEnglish = null;
        firstNameHebrew = null;
        firstNameEnglish = null;
        dateOfBirth = null;
        dateOfExpiry = null;
        dateOfIssue = null;
        licenseNumber = null;
        idNumber = null;
        idNumber2 = null;
        address = null;
        licenseCategories = null;
        cardType = null;
        cardType2 = null;
        faceImage = null;
        inputImage = null;
        inputImage2 = null;
        croppedImage = null;
        croppedImage2 = null;
        faceSizeValid = false;
        facePositionValid = false;
        faceRotationValid = false;
        bYear = null;
        documentInFrame = false;
        documentInFrame2 = false;
        expiryDateValid = false;
        idChecksumValid = false;
        idChecksumValid2 = false;
        templateMatched = false;
    }

//    @Step("Set front side values")
    public static void setFrontSideVariables(String jsonResult, int i) {
        try {
            OcrHandlers.setOcrFrontSideVariables(jsonResult, i);
            if (jsonResult.contains("last_name_hebrew")) {
                setLastNameHebrew(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.last_name_hebrew"));
            } else setLastNameHebrew(null);
            if (jsonResult.contains("last_name_english")) {
                setLastNameEnglish(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.last_name_english"));
            } else setLastNameEnglish(null);
            if (jsonResult.contains("first_name_hebrew")) {
                setFirstNameHebrew(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.first_name_hebrew"));
            } else setFirstNameHebrew(null);
            if (jsonResult.contains("first_name_english")) {
                setFirstNameEnglish(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.first_name_english"));
            } else setFirstNameEnglish(null);
            if (jsonResult.contains("date_of_birth")) {
                setDateOfBirth(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.date_of_birth"));
            } else setDateOfBirth(null);
            if (jsonResult.contains("date_of_expiry")) {
                setDateOfExpiry(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.date_of_expiry"));
            } else setDateOfExpiry(null);
            if (jsonResult.contains("date_of_issue")) {
                setDateOfIssue(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.date_of_issue"));
            } else setDateOfIssue(null);
            if (jsonResult.contains("license_number")) {
                setLicenseNumber(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.license_number"));
            } else setLicenseNumber(null);
            if (jsonResult.contains("id_number")) {
                setIdNumber(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.id_number"));
            } else setIdNumber(null);
            if (jsonResult.contains("address")) {
                setAddress(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.address"));
            } else setAddress(null);
            if (jsonResult.contains("license_categories")) {
                setLicenseCategories(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.license_categories"));
            } else setLicenseCategories(null);
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
            if (jsonResult.contains("id_checksum_valid")) {
                setIdChecksumValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.id_checksum_valid"));
            } else setIdChecksumValid(false);
            if (jsonResult.contains("template_matched")) {
                setTemplateMatched(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.template_matched"));
            } else setTemplateMatched(false);
            if (jsonResult.contains("document_in_frame")) {
                setDocumentInFrame(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.document_in_frame"));
            } else setDocumentInFrame(false);
            if (jsonResult.contains("expiry_date_valid")) {
                setExpiryDateValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.expiry_date_valid"));
            } else setExpiryDateValid(false);
            if (jsonResult.contains("face_size_valid")) {
                setFaceSizeValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.face_size_valid"));
            } else setFaceSizeValid(false);
            if (jsonResult.contains("face_position_valid")) {
                setFacePositionValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.face_position_valid"));
            } else setFacePositionValid(false);
            if (jsonResult.contains("face_rotation_valid")) {
                setFaceRotationValid(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.face_rotation_valid"));
            } else setFaceRotationValid(false);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    @Step("Set back side values")
    public static void setBackSideVariables(String jsonResult, int i) {
        try {
            OcrHandlers.setOcrBackSideVariables(jsonResult, i);
            if (jsonResult.contains("id_number")) {
                setIdNumber2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.id_number"));
            } else setIdNumber2(null);
            if (jsonResult.contains("b_year")) {
                setbYear(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.b_year"));
            } else setbYear(null);
            if (jsonResult.contains("card_type")) {
                setCardType2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.card_type"));
            } else setCardType2(null);
            if (jsonResult.contains("input_image")) {
                setInputImage2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.input_image"));
            } else setInputImage2(null);
            if (jsonResult.contains("cropped_image")) {
                setCroppedImage2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.cropped_image"));
            } else setCroppedImage2(null);
            if (jsonResult.contains("id_checksum_valid")) {
                setIdChecksumValid2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.id_checksum_valid"));
            } else setIdChecksumValid2(false);
            if (jsonResult.contains("template_matched")) {
                setTemplateMatched2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.template_matched"));
            } else setTemplateMatched2(false);
            if (jsonResult.contains("document_in_frame")) {
                setDocumentInFrame2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.auth.document_in_frame"));
            } else setDocumentInFrame2(false);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //###############################################

    public static boolean isDocumentInFrame() {
        return documentInFrame;
    }

    public static void setDocumentInFrame(boolean documentInFrame) {
        ILDLVariables.documentInFrame = documentInFrame;
    }

    public static boolean isDocumentInFrame2() {
        return documentInFrame2;
    }

    public static void setDocumentInFrame2(boolean documentInFrame2) {
        ILDLVariables.documentInFrame2 = documentInFrame2;
    }

    public static boolean isExpiryDateValid() {
        return expiryDateValid;
    }

    public static void setExpiryDateValid(boolean expiryDateValid) {
        ILDLVariables.expiryDateValid = expiryDateValid;
    }

    public static boolean isIdChecksumValid() {
        return idChecksumValid;
    }

    public static void setIdChecksumValid(boolean idChecksumValid) {
        ILDLVariables.idChecksumValid = idChecksumValid;
    }

    public static boolean isIdChecksumValid2() {
        return idChecksumValid2;
    }

    public static void setIdChecksumValid2(boolean idChecksumValid2) {
        ILDLVariables.idChecksumValid2 = idChecksumValid2;
    }

    public static boolean isTemplateMatched() {
        return templateMatched;
    }

    public static void setTemplateMatched(boolean templateMatched) {
        ILDLVariables.templateMatched = templateMatched;
    }

    public static boolean isTemplateMatched2() {
        return templateMatched2;
    }

    public static void setTemplateMatched2(boolean templateMatched2) {
        ILDLVariables.templateMatched2 = templateMatched2;
    }

    public static String getLastNameHebrew() {
        return lastNameHebrew;
    }

    public static void setLastNameHebrew(String lastNameHebrew) {
        ILDLVariables.lastNameHebrew = lastNameHebrew;
    }

    public static String getLastNameEnglish() {
        return lastNameEnglish;
    }

    public static void setLastNameEnglish(String lastNameEnglish) {
        ILDLVariables.lastNameEnglish = lastNameEnglish;
    }

    public static String getFirstNameHebrew() {
        return firstNameHebrew;
    }

    public static void setFirstNameHebrew(String firstNameHebrew) {
        ILDLVariables.firstNameHebrew = firstNameHebrew;
    }

    public static String getFirstNameEnglish() {
        return firstNameEnglish;
    }

    public static void setFirstNameEnglish(String firstNameEnglish) {
        ILDLVariables.firstNameEnglish = firstNameEnglish;
    }

    public static String getLicenseNumber() {
        return licenseNumber;
    }

    public static void setLicenseNumber(String licenseNumber) {
        ILDLVariables.licenseNumber = licenseNumber;
    }

    public static String getIdNumber() {
        return idNumber;
    }

    public static void setIdNumber(String idNumber) {
        ILDLVariables.idNumber = idNumber;
    }

    public static String getIdNumber2() {
        return idNumber2;
    }

    public static void setIdNumber2(String idNumber2) {
        ILDLVariables.idNumber2 = idNumber2;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        ILDLVariables.address = address;
    }

    public static String getLicenseCategories() {
        return licenseCategories;
    }

    public static void setLicenseCategories(String licenseCategories) {
        ILDLVariables.licenseCategories = licenseCategories;
    }

    public static String getCardType() {
        return cardType;
    }

    public static void setCardType(String cardType) {
        ILDLVariables.cardType = cardType;
    }

    public static String getCardType2() {
        return cardType2;
    }

    public static void setCardType2(String cardType2) {
        ILDLVariables.cardType2 = cardType2;
    }

    public static String getFaceImage() {
        return faceImage;
    }

    public static void setFaceImage(String faceImage) {
        ILDLVariables.faceImage = faceImage;
    }

    public static String getInputImage() {
        return inputImage;
    }

    public static void setInputImage(String inputImage) {
        ILDLVariables.inputImage = inputImage;
    }

    public static String getInputImage2() {
        return inputImage2;
    }

    public static void setInputImage2(String inputImage2) {
        ILDLVariables.inputImage2 = inputImage2;
    }

    public static String getCroppedImage() {
        return croppedImage;
    }

    public static void setCroppedImage(String croppedImage) {
        ILDLVariables.croppedImage = croppedImage;
    }

    public static String getCroppedImage2() {
        return croppedImage2;
    }

    public static void setCroppedImage2(String croppedImage2) {
        ILDLVariables.croppedImage2 = croppedImage2;
    }

    public static boolean isFaceSizeValid() {
        return faceSizeValid;
    }

    public static void setFaceSizeValid(boolean faceSizeValid) {
        ILDLVariables.faceSizeValid = faceSizeValid;
    }

    public static boolean isFacePositionValid() {
        return facePositionValid;
    }

    public static void setFacePositionValid(boolean facePositionValid) {
        ILDLVariables.facePositionValid = facePositionValid;
    }

    public static boolean isFaceRotationValid() {
        return faceRotationValid;
    }

    public static void setFaceRotationValid(boolean faceRotationValid) {
        ILDLVariables.faceRotationValid = faceRotationValid;
    }

    public static String getbYear() {
        return bYear;
    }

    public static void setbYear(String bYear) {
        ILDLVariables.bYear = bYear;
    }

    public static String getDateOfBirth() {
        return dateOfBirth;
    }

    public static void setDateOfBirth(String dateOfBirth) {
        ILDLVariables.dateOfBirth = dateOfBirth;
    }

    public static String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public static void setDateOfExpiry(String dateOfExpiry) {
        ILDLVariables.dateOfExpiry = dateOfExpiry;
    }

    public static String getDateOfIssue() {
        return dateOfIssue;
    }

    public static void setDateOfIssue(String dateOfIssue) {
        ILDLVariables.dateOfIssue = dateOfIssue;
    }
}
