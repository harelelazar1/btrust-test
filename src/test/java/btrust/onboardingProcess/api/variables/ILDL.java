package btrust.onboardingProcess.api.variables;

import io.restassured.path.json.JsonPath;

public class ILDL extends EndpointResults {

    private static String process;
    private static String end;
    private static String dob;
    private static String issuingDate;
    private static String expiryDate;
    private static String firstName;
    private static String lastName;
    private static String dateOfBirth;
    private static String address;
    private static int idNumber;
    private static int documentNumber;
    private static String expirationDate;
    private static String faceImage;
    private static String frontImage;
    private static String cardImage;
    private static String backImage;
    private static String docType;
    private static String issueDate;
    private static String scanVideo;
    private static String licenseCategory;
    private static String licenseIssuingYear;
    private static int licenseIssueYear;
    private static String ocrType;
    private static int backSideIdNumber;
    private static boolean facePosition;
    private static boolean validityExpiryDate;
    private static boolean checksum;
    private static boolean backChecksum;
    private static boolean faceSize;
    private static boolean templateMatching;
    private static boolean backTemplateMatching;
    private static boolean faceRotation;
    private static boolean processSuccess;
    private static boolean documentInFrame;

    private static boolean backDocumentInFrame;
    private static int count;


    public ILDL() {
        process = null;
        end = null;
        dob = null;
        issuingDate = null;
        expiryDate = null;
        firstName = null;
        lastName = null;
        dateOfBirth = null;
        address = null;
        idNumber = 0;
        documentNumber = 0;
        expirationDate = null;
        faceImage = null;
        frontImage = null;
        cardImage = null;
        backImage = null;
        docType = null;
        issueDate = null;
        scanVideo = null;
        licenseCategory = null;
        licenseIssuingYear = null;
        licenseIssueYear = 0;
        ocrType = null;
        backSideIdNumber = 0;
        facePosition = false;
        validityExpiryDate = false;
        checksum = false;
        backChecksum = false;
        faceSize = false;
        templateMatching = false;
        backTemplateMatching = false;
        faceRotation = false;
        processSuccess = false;
        count = 0;
        documentInFrame = false;
        backDocumentInFrame = false;
    }

    public void setILDLVariables(JsonPath jsonPath) {
        setILDLVariables(jsonPath, "");
    }

    public void setILDLVariables(JsonPath jsonPath, String i) {
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
        setAddress(jsonPath.getString(path + ".address"));
        setIdNumber(jsonPath.getInt(path + ".idNumber"));
        setDocumentNumber(jsonPath.getInt(path + ".documentNumber"));
        setExpirationDate(jsonPath.getString(path + ".expirationDate"));
        setFaceImage(jsonPath.getString(path + ".faceImage"));
        setFrontImage(jsonPath.getString(path + ".frontImage"));
        setCardImage(jsonPath.getString(path + ".cardImage"));
        setBackImage(jsonPath.getString(path + ".backImage"));
        setDocType(jsonPath.getString(path + ".docType"));
        setIssueDate(jsonPath.getString(path + ".issueDate"));
        setScanVideo(jsonPath.getString(path + ".scanVideo"));
        setLicenseCategory(jsonPath.getString(path + ".licenseCategory"));
        setLicenseIssuingYear(jsonPath.getString(path + ".licenseIssuingYear"));
        setLicenseIssueYear(jsonPath.getInt(path + ".licenseIssueYear"));
        setOcrType(jsonPath.getString(path + ".ocrType"));
        if (jsonPath.getMap(path).containsKey("backSideIdNumber"))
            setBackSideIdNumber(jsonPath.getInt(path + ".backSideIdNumber"));
        if (jsonPath.getMap(path).containsKey("authentication")) {
            setFacePosition(jsonPath.getBoolean(path + ".authentication[\"verify.facePosition\"]"));
            setValidityExpiryDate(jsonPath.getBoolean(path + ".authentication[\"verify.expiryDate\"]"));
            setChecksum(jsonPath.getBoolean(path + ".authentication[\"verify.checksum\"]"));
            setBackChecksum(jsonPath.getBoolean(path + ".authentication[\"verify.backChecksum\"]"));
            setFaceSize(jsonPath.getBoolean(path + ".authentication[\"verify.faceSize\"]"));
            setTemplateMatching(jsonPath.getBoolean(path + ".authentication[\"verify.templateMatching\"]"));
            setBackTemplateMatching(jsonPath.getBoolean(path + ".authentication[\"verify.backTemplateMatching\"]"));
            setFaceRotation(jsonPath.getBoolean(path + ".authentication[\"verify.faceRotation\"]"));
            setDocumentInFrame(jsonPath.getBoolean(path + ".authentication[\"verify.documentInFrame\"]"));
            setBackDocumentInFrame(jsonPath.getBoolean(path + ".authentication[\"verify.backDocumentInFrame\"]"));
        }
        setProcessSuccess(jsonPath.getBoolean(path + ".success"));
        if (jsonPath.getMap(path).containsKey("count"))
            setCount(jsonPath.getInt(path + ".count"));
    }

//#############################################################################

    public static String getProcess() {
        return process;
    }

    public static void setProcess(String process) {
        ILDL.process = process;
    }

    public static String getEnd() {
        return end;
    }

    public static void setEnd(String end) {
        ILDL.end = end;
    }

    public static String getDob() {
        return dob;
    }

    public static void setDob(String dob) {
        ILDL.dob = dob;
    }

    public static String getIssuingDate() {
        return issuingDate;
    }

    public static void setIssuingDate(String issuingDate) {
        ILDL.issuingDate = issuingDate;
    }

    public static String getExpiryDate() {
        return expiryDate;
    }

    public static void setExpiryDate(String expiryDate) {
        ILDL.expiryDate = expiryDate;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        ILDL.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        ILDL.lastName = lastName;
    }

    public static String getDateOfBirth() {
        return dateOfBirth;
    }

    public static void setDateOfBirth(String dateOfBirth) {
        ILDL.dateOfBirth = dateOfBirth;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        ILDL.address = address;
    }

    public static int getIdNumber() {
        return idNumber;
    }

    public static void setIdNumber(int idNumber) {
        ILDL.idNumber = idNumber;
    }

    public static int getDocumentNumber() {
        return documentNumber;
    }

    public static void setDocumentNumber(int documentNumber) {
        ILDL.documentNumber = documentNumber;
    }

    public static String getExpirationDate() {
        return expirationDate;
    }

    public static void setExpirationDate(String expirationDate) {
        ILDL.expirationDate = expirationDate;
    }

    public static String getFaceImage() {
        return faceImage;
    }

    public static void setFaceImage(String faceImage) {
        ILDL.faceImage = faceImage;
    }

    public static String getFrontImage() {
        return frontImage;
    }

    public static void setFrontImage(String frontImage) {
        ILDL.frontImage = frontImage;
    }

    public static String getCardImage() {
        return cardImage;
    }

    public static void setCardImage(String cardImage) {
        ILDL.cardImage = cardImage;
    }

    public static String getBackImage() {
        return backImage;
    }

    public static void setBackImage(String backImage) {
        ILDL.backImage = backImage;
    }

    public static String getDocType() {
        return docType;
    }

    public static void setDocType(String docType) {
        ILDL.docType = docType;
    }

    public static String getIssueDate() {
        return issueDate;
    }

    public static void setIssueDate(String issueDate) {
        ILDL.issueDate = issueDate;
    }

    public static String getScanVideo() {
        return scanVideo;
    }

    public static void setScanVideo(String scanVideo) {
        ILDL.scanVideo = scanVideo;
    }

    public static String getLicenseCategory() {
        return licenseCategory;
    }

    public static void setLicenseCategory(String licenseCategory) {
        ILDL.licenseCategory = licenseCategory;
    }

    public static String getLicenseIssuingYear() {
        return licenseIssuingYear;
    }

    public static void setLicenseIssuingYear(String licenseIssuingYear) {
        ILDL.licenseIssuingYear = licenseIssuingYear;
    }

    public static int getLicenseIssueYear() {
        return licenseIssueYear;
    }

    public static void setLicenseIssueYear(int licenseIssueYear) {
        ILDL.licenseIssueYear = licenseIssueYear;
    }

    public static String getOcrType() {
        return ocrType;
    }

    public static void setOcrType(String ocrType) {
        ILDL.ocrType = ocrType;
    }

    public static int getBackSideIdNumber() {
        return backSideIdNumber;
    }

    public static void setBackSideIdNumber(int backSideIdNumber) {
        ILDL.backSideIdNumber = backSideIdNumber;
    }

    public static boolean isFacePosition() {
        return facePosition;
    }

    public static void setFacePosition(boolean facePosition) {
        ILDL.facePosition = facePosition;
    }

    public static boolean isValidityExpiryDate() {
        return validityExpiryDate;
    }

    public static void setValidityExpiryDate(boolean validityExpiryDate) {
        ILDL.validityExpiryDate = validityExpiryDate;
    }

    public static boolean isChecksum() {
        return checksum;
    }

    public static void setChecksum(boolean checksum) {
        ILDL.checksum = checksum;
    }

    public static boolean isBackChecksum() {
        return backChecksum;
    }

    public static void setBackChecksum(boolean backChecksum) {
        ILDL.backChecksum = backChecksum;
    }

    public static boolean isFaceSize() {
        return faceSize;
    }

    public static void setFaceSize(boolean faceSize) {
        ILDL.faceSize = faceSize;
    }

    public static boolean isTemplateMatching() {
        return templateMatching;
    }

    public static void setTemplateMatching(boolean templateMatching) {
        ILDL.templateMatching = templateMatching;
    }

    public static boolean isBackTemplateMatching() {
        return backTemplateMatching;
    }

    public static void setBackTemplateMatching(boolean backTemplateMatching) {
        ILDL.backTemplateMatching = backTemplateMatching;
    }

    public static boolean isFaceRotation() {
        return faceRotation;
    }

    public static void setFaceRotation(boolean faceRotation) {
        ILDL.faceRotation = faceRotation;
    }

    public static boolean isProcessSuccess() {
        return processSuccess;
    }

    public static void setProcessSuccess(boolean processSuccess) {
        ILDL.processSuccess = processSuccess;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        ILDL.count = count;
    }

    public static boolean isDocumentInFrame() {
        return documentInFrame;
    }
    public static void setDocumentInFrame(boolean documentInFrame) {
        ILDL.documentInFrame =documentInFrame;
    }

    public static boolean isBackDocumentInFrame() {
        return backDocumentInFrame;
    }
    public static void setBackDocumentInFrame(boolean backDocumentInFrame) {
        ILDL.backDocumentInFrame = backDocumentInFrame;
    }





}
