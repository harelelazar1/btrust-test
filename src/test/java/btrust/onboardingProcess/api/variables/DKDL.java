package btrust.onboardingProcess.api.variables;

import io.restassured.path.json.JsonPath;

public class DKDL extends EndpointResults {

    private static String process;
    private static String end;
    private static String dob;
    private static String issuingDate;
    private static String expiryDate;
    private static String firstName;
    private static String lastName;
    private static String dateOfBirth;
    private static String idNumber;
    private static int documentNumber;
    private static String expirationDate;
    private static String faceImage;
    private static String frontImage;
    private static String cardImage;
    private static String docType;
    private static String issueDate;
    private static String ocrType;
    private static boolean processSuccess;
    private static int count;

    public DKDL() {
        process = null;
        end = null;
        dob = null;
        issuingDate = null;
        expiryDate = null;
        firstName = null;
        lastName = null;
        dateOfBirth = null;
        idNumber = null;
        documentNumber = 0;
        expirationDate = null;
        faceImage = null;
        frontImage = null;
        cardImage = null;
        docType = null;
        issueDate = null;
        ocrType = null;
        processSuccess = false;
        count = 0;
    }

    public void setDKDLVariables(JsonPath jsonPath) {
        setDKDLVariables(jsonPath, "");
    }

    public void setDKDLVariables(JsonPath jsonPath, String i) {
        String path;
        if (i.equalsIgnoreCase("")) {
            path = "data.results.ocr";
        } else path = "data.resultsList[" + i + "]";

        setProcess(jsonPath.getString(path + ".process"));
        setEnd(jsonPath.getString(path + ".end"));
        setDob(jsonPath.getString(path + ".dob"));
        setIssuingDate(jsonPath.getString(path + ".issuingDate"));
        setExpiryDate(jsonPath.getString(path + ".expiryDate"));
        setFirstName(jsonPath.getString(path + ".firstName"));
        setLastName(jsonPath.getString(path + ".lastName"));
        setDateOfBirth(jsonPath.getString(path + ".dateOfBirth"));
        setIdNumber(jsonPath.getString(path + ".idNumber"));
        setDocumentNumber(jsonPath.getInt(path + ".documentNumber"));
        setExpirationDate(jsonPath.getString(path + ".expirationDate"));
        setFaceImage(jsonPath.getString(path + ".faceImage"));
        setFrontImage(jsonPath.getString(path + ".frontImage"));
        setCardImage(jsonPath.getString(path + ".cardImage"));
        setDocType(jsonPath.getString(path + ".docType"));
        setIssueDate(jsonPath.getString(path + ".issueDate"));
        setOcrType(jsonPath.getString(path + ".ocrType"));
        setProcessSuccess(jsonPath.getBoolean(path + ".success"));
        if (jsonPath.getMap(path).containsKey("count"))
            setCount(jsonPath.getInt(path + ".count"));
    }

    //#############################################################################

    public static String getProcess() {
        return process;
    }

    public static void setProcess(String process) {
        DKDL.process = process;
    }

    public static String getEnd() {
        return end;
    }

    public static void setEnd(String end) {
        DKDL.end = end;
    }

    public static String getDob() {
        return dob;
    }

    public static void setDob(String dob) {
        DKDL.dob = dob;
    }

    public static String getIssuingDate() {
        return issuingDate;
    }

    public static void setIssuingDate(String issuingDate) {
        DKDL.issuingDate = issuingDate;
    }

    public static String getExpiryDate() {
        return expiryDate;
    }

    public static void setExpiryDate(String expiryDate) {
        DKDL.expiryDate = expiryDate;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        DKDL.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        DKDL.lastName = lastName;
    }

    public static String getDateOfBirth() {
        return dateOfBirth;
    }

    public static void setDateOfBirth(String dateOfBirth) {
        DKDL.dateOfBirth = dateOfBirth;
    }

    public static String getIdNumber() {
        return idNumber;
    }

    public static void setIdNumber(String idNumber) {
        DKDL.idNumber = idNumber;
    }

    public static int getDocumentNumber() {
        return documentNumber;
    }

    public static void setDocumentNumber(int documentNumber) {
        DKDL.documentNumber = documentNumber;
    }

    public static String getExpirationDate() {
        return expirationDate;
    }

    public static void setExpirationDate(String expirationDate) {
        DKDL.expirationDate = expirationDate;
    }

    public static String getFaceImage() {
        return faceImage;
    }

    public static void setFaceImage(String faceImage) {
        DKDL.faceImage = faceImage;
    }

    public static String getFrontImage() {
        return frontImage;
    }

    public static void setFrontImage(String frontImage) {
        DKDL.frontImage = frontImage;
    }

    public static String getCardImage() {
        return cardImage;
    }

    public static void setCardImage(String cardImage) {
        DKDL.cardImage = cardImage;
    }

    public static String getDocType() {
        return docType;
    }

    public static void setDocType(String docType) {
        DKDL.docType = docType;
    }

    public static String getIssueDate() {
        return issueDate;
    }

    public static void setIssueDate(String issueDate) {
        DKDL.issueDate = issueDate;
    }

    public static String getOcrType() {
        return ocrType;
    }

    public static void setOcrType(String ocrType) {
        DKDL.ocrType = ocrType;
    }

    public static boolean isProcessSuccess() {
        return processSuccess;
    }

    public static void setProcessSuccess(boolean processSuccess) {
        DKDL.processSuccess = processSuccess;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        DKDL.count = count;
    }
}
