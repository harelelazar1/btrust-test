package utilities.MongoDB.Variables.Ocr;

import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.Step;

public class MRZVariables extends OcrHandlers {

    private static String mrzText;
    private static String mrzType;
    private static String documentType;
    private static String documentSubType;
    private static String issuingCountryCode;
    private static String lastName;
    private static String firstName;
    private static String passportNumber;
    private static String nationalityCode;
    private static String dateOfBirth;
    private static String gender;
    private static String dateOfExpiry;
    private static String personalNumber;
    private static boolean mightBeTruncated;
    private static String faceImage;
    private static String InputImage;
    private static String croppedImage;
    private static String croppedFieldImage;
    private static boolean documentInFrame;
    private static boolean expiryDateValid;
    private static boolean faceSizeValid;
    private static boolean facePositionValid;
    private static boolean faceRotationValid;

    public MRZVariables() {
        mrzText = null;
        mrzType = null;
        documentType = null;
        documentSubType = null;
        issuingCountryCode = null;
        lastName = null;
        firstName = null;
        passportNumber = null;
        nationalityCode = null;
        dateOfBirth = null;
        gender = null;
        dateOfExpiry = null;
        personalNumber = null;
        mightBeTruncated = false;
        faceImage = null;
        InputImage = null;
        croppedImage = null;
        croppedFieldImage = null;
        documentInFrame = false;
        expiryDateValid = false;
        faceSizeValid = false;
        facePositionValid = false;
        faceRotationValid = false;
    }

//    @Step("Set front side values")
    public static void setMRZVariables(String jsonResult, int i) {
        try {
            setOcrFrontSideVariables(jsonResult, i);
            if (jsonResult.contains("mrz_text")) {
                setMrzText(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.mrz_text"));
            } else setMrzText(null);
            if (jsonResult.contains("mrz_type")) {
                setMrzType(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.mrz_type"));
            } else setMrzType(null);
            if (jsonResult.contains("document_type")) {
                setDocumentType(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.document_type"));
            } else setDocumentType(null);
            if (jsonResult.contains("document_sub_type")) {
                setDocumentSubType(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.document_sub_type"));
            } else setDocumentSubType(null);
            if (jsonResult.contains("issuing_country_code")) {
                setIssuingCountryCode(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.issuing_country_code"));
            } else setIssuingCountryCode(null);
            if (jsonResult.contains("last_name")) {
                setLastName(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.last_name"));
            } else setLastName(null);
            if (jsonResult.contains("first_name")) {
                setFirstName(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.first_name"));
            } else setFirstName(null);
            if (jsonResult.contains("passport_number")) {
                setPassportNumber(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.passport_number"));
            } else setPassportNumber(null);
            if (jsonResult.contains("nationality_code")) {
                setNationalityCode(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.nationality_code"));
            } else setNationalityCode(null);
            if (jsonResult.contains("date_of_birth")) {
                setDateOfBirth(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.date_of_birth"));
            } else setDateOfBirth(null);
            if (jsonResult.contains("gender")) {
                setGender(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.gender"));
            } else setGender(null);
            if (jsonResult.contains("date_of_expiry")) {
                setDateOfExpiry(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.date_of_expiry"));
            } else setDateOfExpiry(null);
            if (jsonResult.contains("personal_number")) {
                setPersonalNumber(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.personal_number"));
            } else setPersonalNumber(null);
            if (jsonResult.contains("might_be_truncated")) {
                setMightBeTruncated(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.metadata.might_be_truncated"));
            } else setMightBeTruncated(false);
            if (jsonResult.contains("face_image")) {
                setFaceImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.face_image"));
            } else setFaceImage(null);
            if (jsonResult.contains("input_image")) {
                setInputImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.input_image"));
            } else setInputImage(null);
            if (jsonResult.contains("cropped_image")) {
                setCroppedImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.cropped_image"));
            } else setCroppedImage(null);
            if (jsonResult.contains("cropped_field_image")) {
                setCroppedFieldImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.cropped_field_image"));
            } else setCroppedFieldImage(null);
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

//###############################################

    public static boolean isDocumentInFrame() {
        return documentInFrame;
    }

    public static void setDocumentInFrame(boolean documentInFrame) {
        MRZVariables.documentInFrame = documentInFrame;
    }

    public static boolean isExpiryDateValid() {
        return expiryDateValid;
    }

    public static void setExpiryDateValid(boolean expiryDateValid) {
        MRZVariables.expiryDateValid = expiryDateValid;
    }

    public static boolean isFaceSizeValid() {
        return faceSizeValid;
    }

    public static void setFaceSizeValid(boolean faceSizeValid) {
        MRZVariables.faceSizeValid = faceSizeValid;
    }

    public static boolean isFacePositionValid() {
        return facePositionValid;
    }

    public static void setFacePositionValid(boolean facePositionValid) {
        MRZVariables.facePositionValid = facePositionValid;
    }

    public static boolean isFaceRotationValid() {
        return faceRotationValid;
    }

    public static void setFaceRotationValid(boolean faceRotationValid) {
        MRZVariables.faceRotationValid = faceRotationValid;
    }

    public static String getMrzText() {
        return mrzText;
    }

    public static void setMrzText(String mrzText) {
        MRZVariables.mrzText = mrzText;
    }

    public static String getMrzType() {
        return mrzType;
    }

    public static void setMrzType(String mrzType) {
        MRZVariables.mrzType = mrzType;
    }

    public static String getDocumentType() {
        return documentType;
    }

    public static void setDocumentType(String documentType) {
        MRZVariables.documentType = documentType;
    }

    public static String getDocumentSubType() {
        return documentSubType;
    }

    public static void setDocumentSubType(String documentSubType) {
        MRZVariables.documentSubType = documentSubType;
    }

    public static String getIssuingCountryCode() {
        return issuingCountryCode;
    }

    public static void setIssuingCountryCode(String issuingCountryCode) {
        MRZVariables.issuingCountryCode = issuingCountryCode;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        MRZVariables.lastName = lastName;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        MRZVariables.firstName = firstName;
    }

    public static String getPassportNumber() {
        return passportNumber;
    }

    public static void setPassportNumber(String passportNumber) {
        MRZVariables.passportNumber = passportNumber;
    }

    public static String getNationalityCode() {
        return nationalityCode;
    }

    public static void setNationalityCode(String nationalityCode) {
        MRZVariables.nationalityCode = nationalityCode;
    }

    public static String getDateOfBirth() {
        return dateOfBirth;
    }

    public static void setDateOfBirth(String dateOfBirth) {
        MRZVariables.dateOfBirth = dateOfBirth;
    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        MRZVariables.gender = gender;
    }

    public static String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public static void setDateOfExpiry(String dateOfExpiry) {
        MRZVariables.dateOfExpiry = dateOfExpiry;
    }

    public static String getPersonalNumber() {
        return personalNumber;
    }

    public static void setPersonalNumber(String personalNumber) {
        MRZVariables.personalNumber = personalNumber;
    }

    public static boolean isMightBeTruncated() {
        return mightBeTruncated;
    }

    public static void setMightBeTruncated(boolean mightBeTruncated) {
        MRZVariables.mightBeTruncated = mightBeTruncated;
    }

    public static String getFaceImage() {
        return faceImage;
    }

    public static void setFaceImage(String faceImage) {
        MRZVariables.faceImage = faceImage;
    }

    public static String getInputImage() {
        return InputImage;
    }

    public static void setInputImage(String inputImage) {
        InputImage = inputImage;
    }

    public static String getCroppedImage() {
        return croppedImage;
    }

    public static void setCroppedImage(String croppedImage) {
        MRZVariables.croppedImage = croppedImage;
    }

    public static String getCroppedFieldImage() {
        return croppedFieldImage;
    }

    public static void setCroppedFieldImage(String croppedFieldImage) {
        MRZVariables.croppedFieldImage = croppedFieldImage;
    }
}
