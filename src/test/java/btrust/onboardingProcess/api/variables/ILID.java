package btrust.onboardingProcess.api.variables;

import io.restassured.path.json.JsonPath;

public class ILID extends EndpointResults {

    private static String process;
    private static String end;
    private static String dob;
    private static String issuingDate;
    private static String expiryDate;
    private static String firstName;
    private static String lastName;
    private static String dateOfBirth;
    private static String gender;
    private static int idNumber;
    private static String expirationDate;
    private static String faceImage;
    private static String frontImage;
    private static String cardImage;
    private static String backImage;
    private static String docType;
    private static String issueDate;
    private static String placeOfBirth;
    private static String citizenship;
    private static String scanVideo;
    private static String ocrType;
    private static int backSideIdNumber;
    private static boolean isIssueDateValid;
    private static boolean periodBetweenExpiryDateAndIssueDate;
    private static boolean facePosition;
    private static boolean validityExpiryDate;
    private static boolean faceSize;
    private static boolean templateMatching;
    private static boolean faceRotation;
    private static boolean documentChipValid;
    private static boolean colorCheck;
    private static boolean faceImageReplacedWithPassportImage;
    private static boolean faceStamp;
    private static boolean colourfulImageBackSide;
    private static boolean checksum;
    private static boolean validateIfFrontAndBacksideIdMatch;
    private static boolean checksumBackside;
    private static boolean templateMatchingBackside;
    private static boolean processSuccess;
    private static boolean comparatorEquals;
    private static boolean  comparatorSuccess;

    private static boolean documentInFrame;
    private static int count0;
    private static int count1;
    private static int count2;
    private static int statusCode1Attempt;
    private static int statusCode2Attempt;
    private static int statusCode3Attempt;
    private static String statusMessage1Attempt;
    private static String statusMessage2Attempt;
    private static String statusMessage3Attempt;


    public ILID() {
//        isIssueDateValid = false;
        process = null;
        end = null;
        dob = null;
        issuingDate = null;
        expiryDate = null;
        firstName = null;
        lastName = null;
        dateOfBirth = null;
        gender = null;
        idNumber = 0;
        expirationDate = null;
        faceImage = null;
        frontImage = null;
        cardImage = null;
        backImage = null;
        docType = null;
        issueDate = null;
        placeOfBirth = null;
        scanVideo = null;
        ocrType = null;
        backSideIdNumber = 0;
        periodBetweenExpiryDateAndIssueDate = false;
        facePosition = false;
        validityExpiryDate = false;
        faceSize = false;
        faceStamp = false;
        templateMatching = false;
        faceRotation = false;
        documentChipValid = false;
        colorCheck = false;
        faceImageReplacedWithPassportImage = false;
        colourfulImageBackSide = false;
        checksum = false;
        validateIfFrontAndBacksideIdMatch = false;
        checksumBackside = false;
        templateMatchingBackside = false;
        processSuccess = false;
        documentInFrame = false;
    }

    public void setILIDVariables(JsonPath jsonPath) {
        setILIDVariables(jsonPath, "");
    }

    public void setILIDVariables(JsonPath jsonPath, String i) {
        String path;
        if (i.equalsIgnoreCase("")) {
            path = "data.results.ocr";
        } else path = "data.resultsList[" + i + "]";

        if (jsonPath.getMap(path).containsKey("process"))
            setProcess(jsonPath.getString(path + ".process"));
        if (jsonPath.getMap(path).containsKey("end"))
            setEnd(jsonPath.getString(path + ".end"));
        if (jsonPath.getMap(path).containsKey("dob"))
            setDob(jsonPath.getString(path + ".dob"));
        if (jsonPath.getMap(path).containsKey("issuingDate"))
            setIssuingDate(jsonPath.getString(path + ".issuingDate"));
        if (jsonPath.getMap(path).containsKey("expiryDate"))
            setExpiryDate(jsonPath.getString(path + ".expiryDate"));
        if (jsonPath.getMap(path).containsKey("firstName"))
            setFirstName(jsonPath.getString(path + ".firstName"));
        if (jsonPath.getMap(path).containsKey("lastName"))
            setLastName(jsonPath.getString(path + ".lastName"));
        if (jsonPath.getMap(path).containsKey("dateOfBirth"))
            setDateOfBirth(jsonPath.getString(path + ".dateOfBirth"));
        if (jsonPath.getMap(path).containsKey("gender"))
            setGender(jsonPath.getString(path + ".gender"));
        if (jsonPath.getMap(path).containsKey("idNumber"))
            setIdNumber(jsonPath.getInt(path + ".idNumber"));
        if (jsonPath.getMap(path).containsKey("expirationDate"))
            setExpirationDate(jsonPath.getString(path + ".expirationDate"));
        if (jsonPath.getMap(path).containsKey("faceImage"))
            setFaceImage(jsonPath.getString(path + ".faceImage"));
        if (jsonPath.getMap(path).containsKey("frontImage"))
            setFrontImage(jsonPath.getString(path + ".frontImage"));
        if (jsonPath.getMap(path).containsKey("cardImage"))
            setCardImage(jsonPath.getString(path + ".cardImage"));
        if (jsonPath.getMap(path).containsKey("backImage"))
            setBackImage(jsonPath.getString(path + ".backImage"));
        if (jsonPath.getMap(path).containsKey("docType"))
            setDocType(jsonPath.getString(path + ".docType"));
        if (jsonPath.getMap(path).containsKey("issueDate"))
            setIssueDate(jsonPath.getString(path + ".issueDate"));
        if (jsonPath.getMap(path).containsKey("placeOfBirth"))
            setPlaceOfBirth(jsonPath.getString(path + ".placeOfBirth"));
        if (jsonPath.getMap(path).containsKey("citizenship"))
            setCitizenship(jsonPath.getString(path + ".citizenship"));

        if (jsonPath.getMap(path).containsKey("video"))
            setScanVideo(jsonPath.getString(path + ".video"));
        if (jsonPath.getMap(path).containsKey("ocrType"))
            setOcrType(jsonPath.getString(path + ".ocrType"));
        if (jsonPath.getMap(path).containsKey("backSideIdNumber"))
            setBackSideIdNumber(jsonPath.getInt(path + ".backSideIdNumber"));
        if (jsonPath.getMap(path).containsKey("authentication")) {
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.periodBetweenIssueAndExpiry"))
                setPeriodBetweenExpiryDateAndIssueDate(jsonPath.getBoolean(path + ".authentication[\"verify.periodBetweenIssueAndExpiry\"]"));
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.facePosition"))
                setFacePosition(jsonPath.getBoolean(path + ".authentication[\"verify.facePosition\"]"));
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.expiryDate"))
                setValidityExpiryDate(jsonPath.getBoolean(path + ".authentication[\"verify.expiryDate\"]"));
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.faceSize"))
                setFaceSize(jsonPath.getBoolean(path + ".authentication[\"verify.faceSize\"]"));
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.templateMatching"))
                setTemplateMatching(jsonPath.getBoolean(path + ".authentication[\"verify.templateMatching\"]"));
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.faceRotation"))
                setFaceRotation(jsonPath.getBoolean(path + ".authentication[\"verify.faceRotation\"]"));
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.faceStamp"))
                setFaceStamp(jsonPath.getBoolean(path + ".authentication[\"verify.faceStamp\"]"));
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.chipAuth"))
                setDocumentChipValid(jsonPath.getBoolean(path + ".authentication[\"verify.chipAuth\"]"));
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.numOfPixels"))
                setColorCheck(jsonPath.getBoolean(path + ".authentication[\"verify.numOfPixels\"]"));
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.bioImageScore"))
                setFaceImageReplacedWithPassportImage(jsonPath.getBoolean(path + ".authentication[\"verify.bioImageScore\"]"));
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.backNumOfPixels"))
                setColourfulImageBackSide(jsonPath.getBoolean(path + ".authentication[\"verify.backNumOfPixels\"]"));
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.checksum"))
                setChecksum(jsonPath.getBoolean(path +".authentication[\"verify.checksum\"]"));
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.backAndFrontIdMatch"))
                setValidateIfFrontAndBacksideIdMatch(jsonPath.getBoolean(path + ".authentication[\"verify.backAndFrontIdMatch\"]"));
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.backChecksum"))
                setChecksumBackside(jsonPath.getBoolean(path + ".authentication[\"verify.backChecksum\"]"));
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.issueDate"))
                setIsIssueDateValid(jsonPath.getBoolean(path + ".authentication[\"verify.issueDate\"]"));
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.documentInFrame")) {
                setDocumentInFrame(jsonPath.getBoolean(path + ".authentication[\"verify.documentInFrame\"]"));
            }
            if (jsonPath.getMap(path + ".authentication").containsKey("verify.backTemplateMatching"))
                setTemplateMatchingBackside(jsonPath.getBoolean(path + ".authentication[\"verify.backTemplateMatching\"]"));
        }
        if (jsonPath.getMap(path).containsKey("success"))
            setProcessSuccess(jsonPath.getBoolean(path + ".success"));

        if (isProcessSuccess()) {
            if (jsonPath.getMap(path).containsKey("count"))
                setCount0(jsonPath.getInt(path + ".count"));
        } else {
            if (jsonPath.getMap(path).containsKey("statusCode") && jsonPath.getMap(path).containsKey("message")) {
                switch (i) {
                    case "0": {
                        setStatusCode1Attempt(jsonPath.getInt(path + ".statusCode"));
                        setStatusMessage1Attempt(jsonPath.getString(path + ".message"));
                        if (jsonPath.getMap(path).containsKey("count")) {
                            setCount0(jsonPath.getInt(path + ".count"));
                        }
                        break;
                    }
                    case "1": {
                        setStatusCode2Attempt(jsonPath.getInt(path + ".statusCode"));
                        setStatusMessage2Attempt(jsonPath.getString(path + ".message"));
                        if (jsonPath.getMap(path).containsKey("count")) {
                            setCount1(jsonPath.getInt(path + ".count"));
                        }
                        break;
                    }
                    case "2": {
                        setStatusCode3Attempt(jsonPath.getInt(path + ".statusCode"));
                        setStatusMessage3Attempt(jsonPath.getString(path + ".message"));
                        if (jsonPath.getMap(path).containsKey("count")) {
                            setCount2(jsonPath.getInt(path + ".count"));
                        }
                        break;
                    }
                }
            }
        }
    }

    public void setVariablesOfComparator(JsonPath jsonPath, String i) {
        String path;
        path = "data.resultsList[" + (i) + "]";

        if (jsonPath.getMap(path).containsKey("equals")) {
            setComparatorEquals(jsonPath.getBoolean(path + ".equals"));
        }
        if (jsonPath.getMap(path).containsKey("success")) {
            setComparatorSuccess(jsonPath.getBoolean(path + ".success"));
        }

    }

    //#############################################################################

    public static int getCount1() {
        return count1;
    }

    public static void setCount1(int count1) {
        ILID.count1 = count1;
    }

    public static int getCount2() {
        return count2;
    }

    public static void setCount2(int count2) {
        ILID.count2 = count2;
    }

    public static int getStatusCode1Attempt() {
        return statusCode1Attempt;
    }

    public static void setStatusCode1Attempt(int statusCode1Attempt) {
        ILID.statusCode1Attempt = statusCode1Attempt;
    }

    public static int getStatusCode2Attempt() {
        return statusCode2Attempt;
    }

    public static void setStatusCode2Attempt(int statusCode2Attempt) {
        ILID.statusCode2Attempt = statusCode2Attempt;
    }

    public static int getStatusCode3Attempt() {
        return statusCode3Attempt;
    }

    public static void setStatusCode3Attempt(int statusCode3Attempt) {
        ILID.statusCode3Attempt = statusCode3Attempt;
    }

    public static String getStatusMessage1Attempt() {
        return statusMessage1Attempt;
    }

    public static void setStatusMessage1Attempt(String statusMessage1Attempt) {
        ILID.statusMessage1Attempt = statusMessage1Attempt;
    }

    public static String getStatusMessage2Attempt() {
        return statusMessage2Attempt;
    }

    public static void setStatusMessage2Attempt(String statusMessage2Attempt) {
        ILID.statusMessage2Attempt = statusMessage2Attempt;
    }

    public static String getStatusMessage3Attempt() {
        return statusMessage3Attempt;
    }

    public static void setStatusMessage3Attempt(String statusMessage3Attempt) {
        ILID.statusMessage3Attempt = statusMessage3Attempt;
    }

    public static boolean isFaceStamp() {
        return faceStamp;
    }

    public static void setFaceStamp(boolean faceStamp) {
        ILID.faceStamp = faceStamp;
    }

    public static String getProcess() {
        return process;
    }

    public static void setProcess(String process) {
        ILID.process = process;
    }

    public static String getEnd() {
        return end;
    }

    public static void setEnd(String end) {
        ILID.end = end;
    }

    public static String getDob() {
        return dob;
    }

    public static void setDob(String dob) {
        ILID.dob = dob;
    }

    public static String getIssuingDate() {
        return issuingDate;
    }

    public static void setIssuingDate(String issuingDate) {
        ILID.issuingDate = issuingDate;
    }

    public static String getExpiryDate() {
        return expiryDate;
    }

    public static void setExpiryDate(String expiryDate) {
        ILID.expiryDate = expiryDate;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        ILID.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        ILID.lastName = lastName;
    }

    public static String getDateOfBirth() {
        return dateOfBirth;
    }

    public static void setDateOfBirth(String dateOfBirth) {
        ILID.dateOfBirth = dateOfBirth;
    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        ILID.gender = gender;
    }

    public static int getIdNumber() {
        return idNumber;
    }

    public static void setIdNumber(int idNumber) {
        ILID.idNumber = idNumber;
    }

    public static String getExpirationDate() {
        return expirationDate;
    }

    public static void setExpirationDate(String expirationDate) {
        ILID.expirationDate = expirationDate;
    }

    public static String getFaceImage() {
        return faceImage;
    }

    public static void setFaceImage(String faceImage) {
        ILID.faceImage = faceImage;
    }

    public static String getFrontImage() {
        return frontImage;
    }

    public static void setFrontImage(String frontImage) {
        ILID.frontImage = frontImage;
    }

    public static String getCardImage() {
        return cardImage;
    }

    public static void setCardImage(String cardImage) {
        ILID.cardImage = cardImage;
    }

    public static String getBackImage() {
        return backImage;
    }

    public static void setBackImage(String backImage) {
        ILID.backImage = backImage;
    }

    public static String getDocType() {
        return docType;
    }

    public static void setDocType(String docType) {
        ILID.docType = docType;
    }

    public static String getIssueDate() {
        return issueDate;
    }

    public static void setIssueDate(String issueDate) {
        ILID.issueDate = issueDate;
    }

    public static String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public static void setPlaceOfBirth(String placeOfBirth) {
        ILID.placeOfBirth = placeOfBirth;
    }

    public static String getCitizenship() {
        return citizenship;
    }

    public static void setCitizenship(String citizenship) {
        ILID.citizenship = citizenship;
    }

    public static String getScanVideo() {
        return scanVideo;
    }

    public static void setScanVideo(String scanVideo) {
        ILID.scanVideo = scanVideo;
    }

    public static String getOcrType() {
        return ocrType;
    }

    public static void setOcrType(String ocrType) {
        ILID.ocrType = ocrType;
    }

    public static int getBackSideIdNumber() {
        System.out.println(backSideIdNumber);
        return backSideIdNumber;
    }

    public static void setBackSideIdNumber(int backSideIdNumber) {
        ILID.backSideIdNumber = backSideIdNumber;
    }

    public static boolean isPeriodBetweenExpiryDateAndIssueDate() {
        return periodBetweenExpiryDateAndIssueDate;
    }

    public static void setPeriodBetweenExpiryDateAndIssueDate(boolean periodBetweenExpiryDateAndIssueDate) {
        ILID.periodBetweenExpiryDateAndIssueDate = periodBetweenExpiryDateAndIssueDate;

    }

    public static boolean isFacePosition() {
        return facePosition;
    }

    public static void setFacePosition(boolean facePosition) {
        ILID.facePosition = facePosition;
    }

    public static boolean isValidityExpiryDate() {
        return validityExpiryDate;
    }

    public static void setValidityExpiryDate(boolean validityExpiryDate) {
        ILID.validityExpiryDate = validityExpiryDate;
    }

    public static boolean isFaceSize() {
        return faceSize;
    }

    public static void setFaceSize(boolean faceSize) {
        ILID.faceSize = faceSize;
    }

    public static boolean isTemplateMatching() {
        return templateMatching;
    }

    public static void setTemplateMatching(boolean templateMatching) {
        ILID.templateMatching = templateMatching;
    }

    public static boolean isFaceRotation() {
        return faceRotation;
    }

    public static void setFaceRotation(boolean faceRotation) {
        ILID.faceRotation = faceRotation;
    }

    public static boolean isDocumentChipValid() {
        return documentChipValid;
    }

    public static void setDocumentChipValid(boolean documentChipValid) {
        ILID.documentChipValid = documentChipValid;
    }

    public static boolean isColorCheck() {
        return colorCheck;
    }

    public static void setColorCheck(boolean colorCheck) {
        ILID.colorCheck = colorCheck;
    }

    public static boolean isFaceImageReplacedWithPassportImage() {
        return faceImageReplacedWithPassportImage;
    }

    public static void setFaceImageReplacedWithPassportImage(boolean faceImageReplacedWithPassportImage) {
        ILID.faceImageReplacedWithPassportImage = faceImageReplacedWithPassportImage;
    }

    public static boolean isColourfulImageBackSide() {
        return colourfulImageBackSide;
    }

    public static void setColourfulImageBackSide(boolean colourfulImageBackSide) {
        ILID.colourfulImageBackSide = colourfulImageBackSide;
    }

    public static boolean isChecksum() {
        return checksum;
    }

    public static void setChecksum(boolean checksum) {
        ILID.checksum = checksum;
    }

    public static boolean isValidateIfFrontAndBacksideIdMatch() {
        return validateIfFrontAndBacksideIdMatch;
    }

    public static void setValidateIfFrontAndBacksideIdMatch(boolean validateIfFrontAndBacksideIdMatch) {
        ILID.validateIfFrontAndBacksideIdMatch = validateIfFrontAndBacksideIdMatch;
    }

    public static boolean isChecksumBackside() {
        return checksumBackside;
    }

    public static void setChecksumBackside(boolean checksumBackside) {
        ILID.checksumBackside = checksumBackside;
    }

    public static boolean isTemplateMatchingBackside() {
        return templateMatchingBackside;
    }

    public static void setTemplateMatchingBackside(boolean templateMatchingBackside) {
        ILID.templateMatchingBackside = templateMatchingBackside;
    }

    public static boolean isProcessSuccess() {
        return processSuccess;
    }

    public static void setProcessSuccess(boolean processSuccess) {
        ILID.processSuccess = processSuccess;
    }

    public static int getCount0() {
        return count0;
    }

    public static void setCount0(int count0) {
        ILID.count0 = count0;
    }

    public static boolean isComparatorEquals() {
        return comparatorEquals;
    }
    public static void setComparatorEquals(boolean comparatorEquals) {
        ILID.comparatorEquals = comparatorEquals;
    }
    public static boolean isComparatorSuccess() {
        return comparatorSuccess;
    }
    public static void setComparatorSuccess(boolean comparatorSuccess) {
        ILID.comparatorSuccess = comparatorSuccess;
    }

    public static boolean isDocumentInFrame() {
        return documentInFrame;
    }
    public static void setDocumentInFrame(boolean documentInFrame) {
        ILID.documentInFrame =documentInFrame;
    }

    public static boolean isIssueDateValid() {
        return isIssueDateValid;
    }

    public static void setIsIssueDateValid(boolean isIssueDateValid) {
        ILID.isIssueDateValid = isIssueDateValid;
    }




}
