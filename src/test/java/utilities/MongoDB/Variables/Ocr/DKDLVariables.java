package utilities.MongoDB.Variables.Ocr;

import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.Step;

public class DKDLVariables extends OcrHandlers {

    private static String lastName;
    private static String firstName;
    private static String dateOfBirth;
    private static String dateOfIssue;
    private static String dateOfExpiry;
    private static String personalNumber;
    private static String licenseNumber;
    private static String cardType;
    private static String faceImage;
    private static String inputImage;

    public DKDLVariables() {
        lastName = null;
        firstName = null;
        dateOfBirth = null;
        dateOfIssue = null;
        dateOfExpiry = null;
        personalNumber = null;
        licenseNumber = null;
        cardType = null;
        faceImage = null;
        inputImage = null;
    }

//    @Step("Set front side values")
    public static void setVariables(String jsonResult, int i) {
        try {
            OcrHandlers.setOcrFrontSideVariables(jsonResult, i);
            if (jsonResult.contains("last_name")) {
                setLastName(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.last_name"));
            } else setLastName(null);
            if (jsonResult.contains("first_name")) {
                setFirstName(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.first_name"));
            } else setFirstName(null);
            if (jsonResult.contains("date_of_birth")) {
                setDateOfBirth(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.date_of_birth"));
            } else setDateOfBirth(null);
            if (jsonResult.contains("date_of_issue")) {
                setDateOfIssue(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.date_of_issue"));
            } else setDateOfIssue(null);
            if (jsonResult.contains("date_of_expiry")) {
                setDateOfExpiry(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.date_of_expiry"));
            } else setDateOfExpiry(null);
            if (jsonResult.contains("personal_number")) {
                setPersonalNumber(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.personal_number"));
            } else setPersonalNumber(null);
            if (jsonResult.contains("license_number")) {
                setLicenseNumber(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.license_number"));
            } else setLicenseNumber(null);
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
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //###############################################

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        DKDLVariables.lastName = lastName;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        DKDLVariables.firstName = firstName;
    }

    public static String getDateOfBirth() {
        return dateOfBirth;
    }

    public static void setDateOfBirth(String dateOfBirth) {
        DKDLVariables.dateOfBirth = dateOfBirth;
    }

    public static String getDateOfIssue() {
        return dateOfIssue;
    }

    public static void setDateOfIssue(String dateOfIssue) {
        DKDLVariables.dateOfIssue = dateOfIssue;
    }

    public static String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public static void setDateOfExpiry(String dateOfExpiry) {
        DKDLVariables.dateOfExpiry = dateOfExpiry;
    }

    public static String getPersonalNumber() {
        return personalNumber;
    }

    public static void setPersonalNumber(String personalNumber) {
        DKDLVariables.personalNumber = personalNumber;
    }

    public static String getLicenseNumber() {
        return licenseNumber;
    }

    public static void setLicenseNumber(String licenseNumber) {
        DKDLVariables.licenseNumber = licenseNumber;
    }

    public static String getCardType() {
        return cardType;
    }

    public static void setCardType(String cardType) {
        DKDLVariables.cardType = cardType;
    }

    public static String getFaceImage() {
        return faceImage;
    }

    public static void setFaceImage(String faceImage) {
        DKDLVariables.faceImage = faceImage;
    }

    public static String getInputImage() {
        return inputImage;
    }

    public static void setInputImage(String inputImage) {
        DKDLVariables.inputImage = inputImage;
    }

    public static String getCroppedImage() {
        return croppedImage;
    }

    public static void setCroppedImage(String croppedImage) {
        DKDLVariables.croppedImage = croppedImage;
    }

    private static String croppedImage;
}
