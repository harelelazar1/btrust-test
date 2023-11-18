package btrust.onboardingProcess.api.variables;

import io.restassured.path.json.JsonPath;

public class MRZ extends EndpointResults {

    private static String process;
    private static String end;
    private static String dob;
    private static String expiryDate;
    private static String firstName;
    private static String lastName;
    private static String dateOfBirth;
    private static String nationalityName;
    private static String nationalityAlpha2;
    private static String nationalityAlpha3;
    private static String gender;
    private static String idNumber;
    private static int documentNumber;
    private static String expirationDate;
    private static String issuingCountryName;
    private static String issuingCountryAlpha2;
    private static String issuingCountryAlpha3;
    private static String countryCode;
    private static String faceImage;
    private static String frontImage;
    private static String cardImage;
    private static String docType;
    private static String scanVideo;
    private static String ocrType;
    private static boolean validityExpiryDate;
    private static boolean checksum;
    private static boolean TemplateMatching;
    private static boolean facePosition;
    private static boolean faceRotation;
    private static boolean faceSize;
    private static boolean processSuccess;
    private static boolean documentInFrame;
    private static int count;

    public MRZ() {
        process = null;
        end = null;
        dob = null;
        expiryDate = null;
        firstName = null;
        lastName = null;
        dateOfBirth = null;
        nationalityName = null;
        nationalityAlpha2 = null;
        nationalityAlpha3 = null;
        gender = null;
        idNumber = null;
        documentNumber = 0;
        expirationDate = null;
        issuingCountryName = null;
        issuingCountryAlpha2 = null;
        issuingCountryAlpha3 = null;
        countryCode = null;
        faceImage = null;
        frontImage = null;
        cardImage = null;
        docType = null;
        scanVideo = null;
        ocrType = null;
        validityExpiryDate = false;
        checksum = false;
        TemplateMatching = false;
        facePosition = false;
        faceRotation = false;
        faceSize = false;
        processSuccess = false;
        documentInFrame = false;
        count = 0;
    }

    public void setMRZVariables(JsonPath jsonPath) {
        setMRZVariables(jsonPath, "");
    }

    public void setMRZVariables(JsonPath jsonPath, String i) {
        String path;
        if (i.equalsIgnoreCase("")) {
            path = "data.results.ocr";
        } else path = "data.resultsList[" + i + "]";

        setProcess(jsonPath.getString(path + ".process"));
        setEnd(jsonPath.getString(path + ".end"));
        setDob(jsonPath.getString(path + ".dob"));
        setExpiryDate(jsonPath.getString(path + ".expiryDate"));
        setFirstName(jsonPath.getString(path + ".firstName"));
        setLastName(jsonPath.getString(path + ".lastName"));
        setDateOfBirth(jsonPath.getString(path + ".dateOfBirth"));
        setNationalityName(jsonPath.getString(path + ".nationality.name"));
        setNationalityAlpha2(jsonPath.getString(path + ".nationality.alpha2"));
        setNationalityAlpha3(jsonPath.getString(path + ".nationality.alpha3"));
        setGender(jsonPath.getString(path + ".gender"));
        setIdNumber(jsonPath.getString(path + ".idNumber"));
        setDocumentNumber(jsonPath.getInt(path + ".documentNumber"));
        setExpirationDate(jsonPath.getString(path + ".expirationDate"));
        setIssuingCountryName(jsonPath.getString(path + ".issuingCountry.name"));
        setIssuingCountryAlpha2(jsonPath.getString(path + ".issuingCountry.alpha2"));
        setIssuingCountryAlpha3(jsonPath.getString(path + ".issuingCountry.alpha3"));
        setCountryCode(jsonPath.getString(path + ".countryCode"));
        setFaceImage(jsonPath.getString(path + ".faceImage"));
        setFrontImage(jsonPath.getString(path + ".frontImage"));
        setCardImage(jsonPath.getString(path + ".cardImage"));
        setDocType(jsonPath.getString(path + ".docType"));
        setScanVideo(jsonPath.getString(path + ".scanVideo"));
        setOcrType(jsonPath.getString(path + ".ocrType"));
        if (jsonPath.getMap(path).containsKey("authentication")) {
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.expiryDate")) {
                setValidityExpiryDate(jsonPath.getBoolean(path + ".authentication[\"verify.expiryDate\"]"));
            }
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.checksum")) {
                setChecksum(jsonPath.getBoolean(path + ".authentication[\"verify.checksum\"]"));
            }
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.templateMatching")) {
                setTemplateMatching(jsonPath.getBoolean(path + ".authentication[\"verify.templateMatching\"]"));
            }
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.facePosition")) {
                setFacePosition(jsonPath.getBoolean(path + ".authentication[\"verify.facePosition\"]"));
            }
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.faceRotation")) {
                setFaceRotation(jsonPath.getBoolean(path + ".authentication[\"verify.faceRotation\"]"));
            }
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.documentInFrame")) {
                setDocumentInFrame(jsonPath.getBoolean(path + ".authentication[\"verify.documentInFrame\"]"));
            }
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.faceSize")) {
                setFaceSize(jsonPath.getBoolean(path + ".authentication[\"verify.faceSize\"]"));
            }

        }
        setProcessSuccess(jsonPath.getBoolean(path + ".success"));
        if (jsonPath.getMap(path).containsKey("count"))
            setCount(jsonPath.getInt(path + ".count"));
    }

    //#############################################################################

    public static boolean isFacePosition() {
        return facePosition;
    }

    public static void setFacePosition(boolean facePosition) {
        MRZ.facePosition = facePosition;
    }

    public static boolean isFaceRotation() {
        return faceRotation;
    }

    public static void setFaceRotation(boolean faceRotation) {
        MRZ.faceRotation = faceRotation;
    }

    public static boolean isFaceSize() {
        return faceSize;
    }

    public static void setFaceSize(boolean faceSize) {
        MRZ.faceSize = faceSize;
    }

    public static String getProcess() {
        return process;
    }

    public static void setProcess(String process) {
        MRZ.process = process;
    }

    public static String getEnd() {
        return end;
    }

    public static void setEnd(String end) {
        MRZ.end = end;
    }

    public static String getDob() {
        return dob;
    }

    public static void setDob(String dob) {
        MRZ.dob = dob;
    }

    public static String getExpiryDate() {
        return expiryDate;
    }

    public static void setExpiryDate(String expiryDate) {
        MRZ.expiryDate = expiryDate;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        MRZ.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        MRZ.lastName = lastName;
    }

    public static String getDateOfBirth() {
        return dateOfBirth;
    }

    public static void setDateOfBirth(String dateOfBirth) {
        MRZ.dateOfBirth = dateOfBirth;
    }

    public static String getNationalityName() {
        return nationalityName;
    }

    public static void setNationalityName(String nationalityName) {
        MRZ.nationalityName = nationalityName;
    }

    public static String getNationalityAlpha2() {
        return nationalityAlpha2;
    }

    public static void setNationalityAlpha2(String nationalityAlpha2) {
        MRZ.nationalityAlpha2 = nationalityAlpha2;
    }

    public static String getNationalityAlpha3() {
        return nationalityAlpha3;
    }

    public static void setNationalityAlpha3(String nationalityAlpha3) {
        MRZ.nationalityAlpha3 = nationalityAlpha3;
    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        MRZ.gender = gender;
    }

    public static String getIdNumber() {
        return idNumber;
    }

    public static void setIdNumber(String idNumber) {
        MRZ.idNumber = idNumber;
    }

    public static int getDocumentNumber() {
        return documentNumber;
    }

    public static void setDocumentNumber(int documentNumber) {
        MRZ.documentNumber = documentNumber;
    }

    public static String getExpirationDate() {
        return expirationDate;
    }

    public static void setExpirationDate(String expirationDate) {
        MRZ.expirationDate = expirationDate;
    }

    public static String getIssuingCountryName() {
        return issuingCountryName;
    }

    public static void setIssuingCountryName(String issuingCountryName) {
        MRZ.issuingCountryName = issuingCountryName;
    }

    public static String getIssuingCountryAlpha2() {
        return issuingCountryAlpha2;
    }

    public static void setIssuingCountryAlpha2(String issuingCountryAlpha2) {
        MRZ.issuingCountryAlpha2 = issuingCountryAlpha2;
    }

    public static String getIssuingCountryAlpha3() {
        return issuingCountryAlpha3;
    }

    public static void setIssuingCountryAlpha3(String issuingCountryAlpha3) {
        MRZ.issuingCountryAlpha3 = issuingCountryAlpha3;
    }

    public static String getCountryCode() {
        return countryCode;
    }

    public static void setCountryCode(String countryCode) {
        MRZ.countryCode = countryCode;
    }

    public static String getFaceImage() {
        return faceImage;
    }

    public static void setFaceImage(String faceImage) {
        MRZ.faceImage = faceImage;
    }

    public static String getFrontImage() {
        return frontImage;
    }

    public static void setFrontImage(String frontImage) {
        MRZ.frontImage = frontImage;
    }

    public static String getCardImage() {
        return cardImage;
    }

    public static void setCardImage(String cardImage) {
        MRZ.cardImage = cardImage;
    }

    public static String getDocType() {
        return docType;
    }

    public static void setDocType(String docType) {
        MRZ.docType = docType;
    }

    public static String getScanVideo() {
        return scanVideo;
    }

    public static void setScanVideo(String scanVideo) {
        MRZ.scanVideo = scanVideo;
    }

    public static String getOcrType() {
        return ocrType;
    }

    public static void setOcrType(String ocrType) {
        MRZ.ocrType = ocrType;
    }

    public static boolean isValidityExpiryDate() {
        return validityExpiryDate;
    }

    public static void setValidityExpiryDate(boolean validityExpiryDate) {
        MRZ.validityExpiryDate = validityExpiryDate;
    }

    public static boolean isChecksum() {
        return checksum;
    }

    public static void setChecksum(boolean checksum) {
        MRZ.checksum = checksum;
    }

    public static boolean isTemplateMatching() {
        return TemplateMatching;
    }

    public static void setTemplateMatching(boolean templateMatching) {
        TemplateMatching = templateMatching;
    }

    public static boolean isDocumentInFrame() {
        return documentInFrame;
    }

    public static void setDocumentInFrame(boolean documentInFrame) {
         MRZ.documentInFrame =documentInFrame;
    }



    public static boolean isProcessSuccess() {
        return processSuccess;
    }

    public static void setProcessSuccess(boolean processSuccess) {
        MRZ.processSuccess = processSuccess;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        MRZ.count = count;
    }
}
