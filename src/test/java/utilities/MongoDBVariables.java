package utilities;

public class MongoDBVariables {

    //General variables
    private String jsonFile;

    private String objectId;
    private String caseId;
    private String serverType;
    private String serviceType;
    private String status;
    private String stageStatus;
    private String workingMode;
    private double timestamp;
    private int counter = 0;

    private String statusReason;
    private boolean success;
    private String video;

    private String actionType;
    private String backSideActionType;
    private int order;
    private int backSideOrder;
    private String typeUnderStage;
    private String backSideTypeUnderStage;
    private String backSideCardType;

    private String cardType;

    //error variables
    private int errorCode;
    private String errorMessage;
    private boolean clientReported;

    //Auth variables
    private boolean dateOfIssueExpiry;
    private boolean imageIsColorful;
    private boolean periodBetweenIssueAndExpiry;
    private int NumOfColorPixels;
    private boolean stampDetected;
    private boolean chipAuth;
    private boolean bioImageScore;
    private boolean faceSizeValid;
    private boolean facePositionValid;
    private boolean faceRotationValid;
    private boolean idChecksumValid;
    private boolean templateMatch;
    private boolean idNumberMatchFront;
    private boolean faceImageReplacedWithPassportImage;

    //Images variables
    private String inputImage;
    private String croppedImage;
    private String croppedFieldImage;
    private String faceImage;
    private String faceImage2;
    private String lastReceivedImage;

    //ID variables
    private String dateOfBirth;
    private String dateOfExpiry;
    private String dateOfIssue;
    private String firstNameHebrew;
    private String lastNameHebrew;
    private String gender;
    private String idNumber;
    private String placeOfBirth;

    //DL variables
    private String lastNameEnglish;
    private String firstNameEnglish;
    private String licenseNumber;
    private String address;
    private String licenseCategories;
    private String bYear;

    //CNI variables
    private String qualityScore;

    //Liveness variables
    private double score;
    private double threshold;
    private double timeStampInLivenessStage;
    private String stageData;
    private String configFilename;
    private String statusInLivenessStage;
    private String stage2RightOrLeft;
    private String stage4RightOrLeft;
    private double sttScore;
    private double sttThreshold;

    //Cheques
    private String chequeNumber;

    //DK-DL
    private String personalNumber;

    //MRZ
    private String mrzText;
    private String mrzType;
    private String documentType;
    private String documentSubType;
    private String issuingCountryCode;
    private String passportNumber;
    private String NationalityCode;
    private boolean mightBeTruncated;

    //********************************************* Getters & Setters *********************************************


    public double getSttScore() {
        return sttScore;
    }

    public void setSttScore(double sttScore) {
        this.sttScore = sttScore;
    }

    public double getSttThreshold() {
        return sttThreshold;
    }

    public void setSttThreshold(double sttThreshold) {
        this.sttThreshold = sttThreshold;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentSubType() {
        return documentSubType;
    }

    public void setDocumentSubType(String documentSubType) {
        this.documentSubType = documentSubType;
    }

    public String getIssuingCountryCode() {
        return issuingCountryCode;
    }

    public void setIssuingCountryCode(String issuingCountryCode) {
        this.issuingCountryCode = issuingCountryCode;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getNationalityCode() {
        return NationalityCode;
    }

    public void setNationalityCode(String nationalityCode) {
        NationalityCode = nationalityCode;
    }

    public boolean isMightBeTruncated() {
        return mightBeTruncated;
    }

    public void setMightBeTruncated(boolean mightBeTruncated) {
        this.mightBeTruncated = mightBeTruncated;
    }

    public String getCroppedFieldImage() {
        return croppedFieldImage;
    }

    public void setCroppedFieldImage(String croppedFieldImage) {
        this.croppedFieldImage = croppedFieldImage;
    }

    public String getMrzText() {
        return mrzText;
    }

    public void setMrzText(String mrzText) {
        this.mrzText = mrzText;
    }

    public String getMrzType() {
        return mrzType;
    }

    public void setMrzType(String mrzType) {
        this.mrzType = mrzType;
    }

    public String getLastReceivedImage() {
        return lastReceivedImage;
    }

    public void setLastReceivedImage(String lastReceivedImage) {
        this.lastReceivedImage = lastReceivedImage;
    }

    public String getStageStatus() {
        return stageStatus;
    }

    public void setStageStatus(String stageStatus) {
        this.stageStatus = stageStatus;
    }

    public boolean isFaceImageReplacedWithPassportImage() {
        return faceImageReplacedWithPassportImage;
    }

    public void setFaceImageReplacedWithPassportImage(boolean faceImageReplacedWithPassportImage) {
        this.faceImageReplacedWithPassportImage = faceImageReplacedWithPassportImage;
    }

    public boolean isIdNumberMatchFront() {
        return idNumberMatchFront;
    }

    public void setIdNumberMatchFront(boolean idNumberMatchFront) {
        this.idNumberMatchFront = idNumberMatchFront;
    }

    public String getJsonFile() {
        return jsonFile;
    }

    public void setJsonFile(String jsonFile) {
        this.jsonFile = jsonFile;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public boolean isFaceSizeValid() {
        return faceSizeValid;
    }

    public void setFaceSizeValid(boolean faceSizeValid) {
        this.faceSizeValid = faceSizeValid;
    }

    public boolean isFacePositionValid() {
        return facePositionValid;
    }

    public void setFacePositionValid(boolean facePositionValid) {
        this.facePositionValid = facePositionValid;
    }

    public boolean isFaceRotationValid() {
        return faceRotationValid;
    }

    public void setFaceRotationValid(boolean faceRotationValid) {
        this.faceRotationValid = faceRotationValid;
    }

    public boolean isIdChecksumValid() {
        return idChecksumValid;
    }

    public void setIdChecksumValid(boolean idChecksumValid) {
        this.idChecksumValid = idChecksumValid;
    }

    public boolean isTemplateMatch() {
        return templateMatch;
    }

    public void setTemplateMatch(boolean templateMatch) {
        this.templateMatch = templateMatch;
    }

    public boolean isBioImageScore() {
        return bioImageScore;
    }

    public void setBioImageScore(boolean bioImageScore) {
        this.bioImageScore = bioImageScore;
    }

    public String getFaceImage2() {
        return faceImage2;
    }

    public void setFaceImage2(String faceImage2) {
        this.faceImage2 = faceImage2;
    }

    public String getBackSideCardType() {
        return backSideCardType;
    }

    public void setBackSideCardType(String backSideCardType) {
        this.backSideCardType = backSideCardType;
    }

    public String getBackSideTypeUnderStage() {
        return backSideTypeUnderStage;
    }

    public void setBackSideTypeUnderStage(String backSideTypeUnderStage) {
        this.backSideTypeUnderStage = backSideTypeUnderStage;
    }

    public int getBackSideOrder() {
        return backSideOrder;
    }

    public void setBackSideOrder(int backSideOrder) {
        this.backSideOrder = backSideOrder;
    }

    public String getBackSideActionType() {
        return backSideActionType;
    }

    public void setBackSideActionType(String backSideActionType) {
        this.backSideActionType = backSideActionType;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStatusInLivenessStage() {
        return statusInLivenessStage;
    }

    public void setStatusInLivenessStage(String statusInLivenessStage) {
        this.statusInLivenessStage = statusInLivenessStage;
    }

    public String getConfigFilename() {
        return configFilename;
    }

    public void setConfigFilename(String configFilename) {
        this.configFilename = configFilename;
    }

    public double getTimeStampInLivenessStage() {
        return timeStampInLivenessStage;
    }

    public void setTimeStampInLivenessStage(double timeStampInLivenessStage) {
        this.timeStampInLivenessStage = timeStampInLivenessStage;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public String getStageData() {
        return stageData;
    }

    public void setStageData(String stageData) {
        this.stageData = stageData;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(String qualityScore) {
        this.qualityScore = qualityScore;
    }

    public String getLastNameEnglish() {
        return lastNameEnglish;
    }

    public void setLastNameEnglish(String lastNameEnglish) {
        this.lastNameEnglish = lastNameEnglish;
    }

    public String getFirstNameEnglish() {
        return firstNameEnglish;
    }

    public void setFirstNameEnglish(String firstNameEnglish) {
        this.firstNameEnglish = firstNameEnglish;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLicenseCategories() {
        return licenseCategories;
    }

    public void setLicenseCategories(String licenseCategories) {
        this.licenseCategories = licenseCategories;
    }

    public String getbYear() {
        return bYear;
    }

    public void setbYear(String bYear) {
        this.bYear = bYear;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public boolean isChipAuth() {
        return chipAuth;
    }

    public void setChipAuth(boolean chipAuth) {
        this.chipAuth = chipAuth;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWorkingMode() {
        return workingMode;
    }

    public void setWorkingMode(String workingMode) {
        this.workingMode = workingMode;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTypeUnderStage() {
        return typeUnderStage;
    }

    public void setTypeUnderStage(String typeUnderStage) {
        this.typeUnderStage = typeUnderStage;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public boolean isDateOfIssueExpiry() {
        return dateOfIssueExpiry;
    }

    public void setDateOfIssueExpiry(boolean dateOfIssueExpiry) {
        this.dateOfIssueExpiry = dateOfIssueExpiry;
    }

    public boolean isImageIsColorful() {
        return imageIsColorful;
    }

    public void setImageIsColorful(boolean imageIsColorful) {
        this.imageIsColorful = imageIsColorful;
    }

    public boolean isPeriodBetweenIssueAndExpiry() {
        return periodBetweenIssueAndExpiry;
    }

    public void setPeriodBetweenIssueAndExpiry(boolean periodBetweenIssueAndExpiry) {
        this.periodBetweenIssueAndExpiry = periodBetweenIssueAndExpiry;
    }

    public int getNumOfColorPixels() {
        return NumOfColorPixels;
    }

    public void setNumOfColorPixels(int numOfColorPixels) {
        NumOfColorPixels = numOfColorPixels;
    }

    public boolean isStampDetected() {
        return stampDetected;
    }

    public void setStampDetected(boolean stampDetected) {
        this.stampDetected = stampDetected;
    }

    public String getInputImage() {
        return inputImage;
    }

    public void setInputImage(String inputImage) {
        this.inputImage = inputImage;
    }

    public String getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(String croppedImage) {
        this.croppedImage = croppedImage;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getFirstNameHebrew() {
        return firstNameHebrew;
    }

    public void setFirstNameHebrew(String firstNameHebrew) {
        this.firstNameHebrew = firstNameHebrew;
    }

    public String getLastNameHebrew() {
        return lastNameHebrew;
    }

    public void setLastNameHebrew(String lastNameHebrew) {
        this.lastNameHebrew = lastNameHebrew;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public boolean getClientReported() {
        return clientReported;
    }

    public void setClientReported(boolean clientReported) {
        this.clientReported = clientReported;
    }

    public String getStage2RightOrLeft() {
        return stage2RightOrLeft;
    }

    public void setStage2RightOrLeft(String stage2RightOrLeft) {
        this.stage2RightOrLeft = stage2RightOrLeft;
    }

    public String getStage4RightOrLeft() {
        return stage4RightOrLeft;
    }

    public void setStage4RightOrLeft(String stage4RightOrLeft) {
        this.stage4RightOrLeft = stage4RightOrLeft;
    }
}
