package api;

import java.util.*;

public class Variables {

    private ArrayList stages;
    private ArrayList landmarks;
    private String cpalette;
    private String token;
    private String actionType;
    private String typeUnderStage;
    private String messageText;
    private String frameId;
    private String typeUnderPayload;
    private String status;
    private String status1;
    private String stageStatus;
    private String topLeft;
    private String topRight;
    private String bottomLeft;
    private String bottomRight;
    private String errorMessage;
    private String cardType;
    private String caseId;
    private String reason;
    private String inputImage;
    private String faceImage;
    private String croppedImage;
    private String croppedFieldImage;
    private String lastReceivedImage;
    private String qualityScore;
    private String messageTranslations97;
    private String messageTranslations98;
    private String messageTranslations99;
    private String messageTranslations100;
    private String messageTranslations101;
    private String messageTranslations102;
    private String messageTranslations103;
    private String messageTranslations104;
    private String messageTranslations149;
    private String messageTranslations150;
    private String messageTranslations151;
    private String messageTranslations152;
    private String messageTranslations153;
    private String messageTranslations154;
    private String messageTranslations155;
    private String messageTranslations156;
    private String messageTranslations157;
    private String messageTranslations158;
    private String messageTranslations159;
    private String messageTranslations160;
    private String messageTranslations161;
    private String messageTranslations162;
    private String messageTranslations163;
    private String messageTranslations164;
    private String messageTranslations165;
    private String messageTranslations166;
    private String messageTranslations167;
    private String messageTranslations168;

    private String messageTranslations169;
    private String messageTranslations250;
    private String configFileName;
    private String cameraFacingMode;

    private int cardTypeCni;
    private int order;
    private int timestamp;
    private int messageId;
    private int errorCode;

    private float score;
    private float cardRatio;
    private double probability;

    private boolean ok;
    private boolean audio;
    private boolean success;
    private boolean found;
    private boolean inBounds;

    //timeout
    private String actionTypeTimeout;
    private int orderTimeout;
    private String stageTypeTimeout;
    private String stageStatusTimeout;

    /*
    cni
     */
    private String actionType1;
    private int order1;
    private String type1;
    private String cardType1;
    private String qualityScore1;
    private String inputImage1;
    private String faceImage1;
    private String croppedImage1;
    private String actionType2;
    private int order2;
    private String type2;
    private String actionType3;
    private int order3;
    private String type3;
    private String cardType2;
    private String qualityScore2;
    private String inputImage2;
    private String faceImage2;
    private String croppedImage2;
    private String stageStatus2;
    private String stageStatus3;
    private String stageStatus4;
    private String stageStatus5;
    private String stageStatus6;
    private String actionType4;
    private String actionType5;
    private String actionType6;
    private int order4;
    private int order5;
    private int order6;
    private String type4;
    private String type5;
    private String type6;
    private String statusUnderPayload;

    private String aligned_card;
    private String aligned_field;


    //Liveness
    private HashMap messageTranslations;
    private HashMap preStageTranslations;
    private HashMap styles;
    private ArrayList error;
    private String errorName;
    private float detectionScore;
    private float distanceBetweenEyes;
    private float headPosePitch;
    private float headPoseRoll;
    private float headPoseYaw;
    private float illuminationScore;
    private float sharpnessScoreComplete;
    private float maskScore;
    private double threshold;
    private double threshold2;
    private int sharpnessScore;
    private int stageData;
    private Float livenessScore;
    private String serviceType;
    private String statusComplete;
    private String statusReason;
    private String statusUnderStage;
    private int otpNum;
    private String stage2RightOrLeft;
    private String stage4RightOrLeft;
    private List<Integer> boundingBoxLtrb = new ArrayList();
    private List<Object> headPose = new ArrayList();
    private List<Integer> landMarkNose = new ArrayList();
    private List<Integer> rightEye = new ArrayList();
    private List<Integer> leftEye = new ArrayList();
    private List<Integer> mouthRight = new ArrayList();
    private List<Integer> mouthLeft = new ArrayList();
    private String sttText;
    private String originalText;
    private String language;

    //Ocr - IL-ID
    private String lastNameHebrew;
    private String firstNameHebrew;

    private String fullNameHebrew;
    private String fullNameEnglish;
    private String dateOfBirth;
    private String dateOfIssue = null;
    private String dateOfExpiry;
    private String fatherName;
    private String grandfatherName;
    private String motherName;
    private String placeOfBirth;
    private String placeOfIssue;
    private String gender;
    private String citizenship;

    private int noseCenterX;
    private int noseCenterY;
    private int cardNumber;
    private int idNumber;
    private int idNumber2;
    private int licenseNumber;
    private int counter = 0;

    //Ocr - Lawyer Card
    private int memberNumber;
    private int internetCode;

    //Ocr - IL-DL
    private String lastNameEnglish;
    private String firstNameEnglish;
    private int bYear;
    private String address;
    private String licenseCategories;

    //Auth
    private boolean dateOfIssueExpiry;
    private boolean imageIsColorful;
    private boolean imageIsColorful2;
    private boolean periodBetweenIssueAndExpiry;
    private int NumOfColorPixels;
    private boolean stampDetectedAuth;
    private boolean stampDetected;
    private boolean chipAuth;
    private boolean faceImageReplacedWithPassportImage;
    private double chipScore;
    private double bioFaceImageScore;
    private boolean faceSizeValid;
    private boolean facePositionValid;
    private boolean faceRotationValid;
    private boolean idChecksumValid;
    private boolean idChecksumValid2;
    private boolean templateMatched;
    private boolean templateMatched2;
    private boolean idNumberMatchesFront;
    private boolean documentInFrame;
    private boolean documentInFrame2;
    private boolean expiryDateValid;
    private boolean issueDateValid;

    //PHC
    private String chequeNumber;

    //DKDL
    private String personalNumber;

    //MRZ
    private String mrzText;
    private String mrzType;
    private String documentType;
    private String documentSubType;
    private String issuingCountryCode;
    private int passportNumber;

    private String passportNumber1;
    private String nationalityCode;
    private boolean mightBeTruncated;

    //Face
    private int faceScore;
    private int processingTime;
    private String firstErrorImage;
    private String secondErrorImage;
    private List<String> errorImages = new ArrayList();
    private String objId;
    private double encodingVersion;
    private double norm;
    private List<Float> encodingArray1 = new ArrayList<>();
    private List<Float> encodingArray2 = new ArrayList<>();
    private List<Integer> mismatchedEncodingIndexes = new ArrayList<>();
    private int originalIndex;
    private List<Integer> originalIndexArray = new ArrayList<>();
    private List<Object> objDBArray = new ArrayList<>();
    private List<String> objArray = new ArrayList<>();
    private List<Double> similarityScore = new ArrayList<>();
    private int objArraySize;
    private String uuid;
    private String uuid2;
    private String imageEncryptedPart;
    private String imageEncryptedPart2;
    private String groupName;

    //Libraries
    private String cmc7;
    private String franceId;
    private String denmarkDl;
    private String israelDl;
    private String israelId;
    private String passport;
    private String philippineCheque;

    //Liveness head positions
    private String headPositionType;
    private String headPositionType1;
    private String headPositionType2;
    private String headPositionType3;
    private boolean headPositionExpected;
    private boolean headPositionExpected1;
    private boolean headPositionExpected2;
    private boolean headPositionExpected3;

    //gestures
    private String gestureActionType;
    private int gestureOrder;
    private String gestureType;
    private List<String> gesturesArray;
    private int sequenceSecondsInterval;
    private String gestureStageStatus;

    private double dblScore;
    private double dblScore2;

    private String creditCardNumber;

    private String stayPermit;

    private String mandatoryFieldsMessage;

    //#############################################################

    public String getAlignedCard() {
        return aligned_card;
    }

    public void setAlignedCard(String aligned_card) {
        this.aligned_card = aligned_card;
    }

    public String getAlignedField() {
        return aligned_field;
    }

    public void setAlignedField(String aligned_field) {
        this.aligned_field = aligned_field;
    }





    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGestureStageStatus() {
        return gestureStageStatus;
    }

    public void setGestureStageStatus(String gestureStageStatus) {
        this.gestureStageStatus = gestureStageStatus;
    }

    public void setGesturesArray(List<String> gesturesArray) {
        this.gesturesArray = gesturesArray;
    }

    public List<String> getGesturesArray() {
        return gesturesArray;
    }

    public String getGestureActionType() {
        return gestureActionType;
    }

    public void setGestureActionType(String gestureActionType) {
        this.gestureActionType = gestureActionType;
    }

    public int getGestureOrder() {
        return gestureOrder;
    }

    public void setGestureOrder(int gestureOrder) {
        this.gestureOrder = gestureOrder;
    }

    public String getGestureType() {
        return gestureType;
    }

    public void setGestureType(String gestureType) {
        this.gestureType = gestureType;
    }

    public int getSequenceSecondsInterval() {
        return sequenceSecondsInterval;
    }

    public void setSequenceSecondsInterval(int sequenceSecondsInterval) {
        this.sequenceSecondsInterval = sequenceSecondsInterval;
    }

    public String getHeadPositionType1() {
        return headPositionType1;
    }

    public void setHeadPositionType1(String headPositionType1) {
        this.headPositionType1 = headPositionType1;
    }

    public String getHeadPositionType2() {
        return headPositionType2;
    }

    public void setHeadPositionType2(String headPositionType2) {
        this.headPositionType2 = headPositionType2;
    }

    public String getHeadPositionType3() {
        return headPositionType3;
    }

    public void setHeadPositionType3(String headPositionType3) {
        this.headPositionType3 = headPositionType3;
    }

    public boolean isHeadPositionExpected1() {
        return headPositionExpected1;
    }

    public void setHeadPositionExpected1(boolean headPositionExpected1) {
        this.headPositionExpected1 = headPositionExpected1;
    }

    public boolean isHeadPositionExpected2() {
        return headPositionExpected2;
    }

    public void setHeadPositionExpected2(boolean headPositionExpected2) {
        this.headPositionExpected2 = headPositionExpected2;
    }

    public boolean isHeadPositionExpected3() {
        return headPositionExpected3;
    }

    public void setHeadPositionExpected3(boolean headPositionExpected3) {
        this.headPositionExpected3 = headPositionExpected3;
    }

    public String getHeadPositionType() {
        return headPositionType;
    }

    public void setHeadPositionType(String headPositionType) {
        this.headPositionType = headPositionType;
    }

    public boolean isHeadPositionExpected() {
        return headPositionExpected;
    }

    public void setHeadPositionExpected(boolean headPositionExpected) {
        this.headPositionExpected = headPositionExpected;
    }

    public String getCmc7() {
        return cmc7;
    }

    public void setCmc7(String cmc7) {
        this.cmc7 = cmc7;
    }

    public String getFranceId() {
        return franceId;
    }

    public void setFranceId(String franceId) {
        this.franceId = franceId;
    }

    public String getDenmarkDl() {
        return denmarkDl;
    }

    public void setDenmarkDl(String denmarkDl) {
        this.denmarkDl = denmarkDl;
    }

    public String getIsraelDl() {
        return israelDl;
    }

    public void setIsraelDl(String israelDl) {
        this.israelDl = israelDl;
    }

    public String getIsraelId() {
        return israelId;
    }

    public void setIsraelId(String israelId) {
        this.israelId = israelId;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPhilippineCheque() {
        return philippineCheque;
    }

    public void setPhilippineCheque(String philippineCheque) {
        this.philippineCheque = philippineCheque;
    }

    public boolean isIdChecksumValid2() {
        return idChecksumValid2;
    }

    public void setIdChecksumValid2(boolean idChecksumValid2) {
        this.idChecksumValid2 = idChecksumValid2;
    }

    public boolean isTemplateMatched2() {
        return templateMatched2;
    }

    public void setTemplateMatched2(boolean templateMatched2) {
        this.templateMatched2 = templateMatched2;
    }

    public boolean isDocumentInFrame2() {
        return documentInFrame2;
    }

    public void setDocumentInFrame2(boolean documentInFrame2) {
        this.documentInFrame2 = documentInFrame2;
    }

    public boolean isDocumentInFrame() {
        return documentInFrame;
    }

    public void setDocumentInFrame(boolean documentInFrame) {
        this.documentInFrame = documentInFrame;
    }

    public boolean isExpiryDateValid() {
        return expiryDateValid;
    }

    public void setExpiryDateValid(boolean expiryDateValid) {
        this.expiryDateValid = expiryDateValid;
    }

    public boolean isIssueDateValid() {
        return issueDateValid;
    }

    public void setIssueDateValid(boolean issueDateValid) {
        this.issueDateValid = issueDateValid;
    }

    public String getCpalette() {
        return cpalette;
    }

    public void setCpalette(String cpalette) {
        this.cpalette = cpalette;
    }

    public String getActionTypeTimeout() {
        return actionTypeTimeout;
    }

    public void setActionTypeTimeout(String actionTypeTimeout) {
        this.actionTypeTimeout = actionTypeTimeout;
    }

    public int getOrderTimeout() {
        return orderTimeout;
    }

    public void setOrderTimeout(int orderTimeout) {
        this.orderTimeout = orderTimeout;
    }

    public String getStageTypeTimeout() {
        return stageTypeTimeout;
    }

    public void setStageTypeTimeout(String stageTypeTimeout) {
        this.stageTypeTimeout = stageTypeTimeout;
    }

    public String getStageStatusTimeout() {
        return stageStatusTimeout;
    }

    public void setStageStatusTimeout(String stageStatusTimeout) {
        this.stageStatusTimeout = stageStatusTimeout;
    }

    public String getCameraFacingMode() {
        return cameraFacingMode;
    }

    public void setCameraFacingMode(String cameraFacingMode) {
        this.cameraFacingMode = cameraFacingMode;
    }

    public double getThreshold2() {
        return threshold2;
    }

    public void setThreshold2(double threshold2) {
        this.threshold2 = threshold2;
    }

    public String getSttText() {
        return sttText;
    }

    public void setSttText(String sttText) {
        this.sttText = sttText;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getObjArraySize() {
        return objArraySize;
    }

    public void setObjArraySize(int objArraySize) {
        this.objArraySize = objArraySize;
    }

    public List<String> getObjArray() {
        return objArray;
    }

    public List<Object> getObjDBArray() {
        return objDBArray;
    }

    public List<Double> getSimilarityScore() {
        return similarityScore;
    }

    public List<Integer> getOriginalIndexArray() {
        return originalIndexArray;
    }

    public List<Integer> getMismatchedEncodingIndexes() {
        return mismatchedEncodingIndexes;
    }

    public int getOriginalIndex() {
        return originalIndex;
    }

    public void setOriginalIndex(int originalIndex) {
        this.originalIndex = originalIndex;
    }

    public List<Float> getEncodingArray2() {
        return encodingArray2;
    }

    public double getNorm() {
        return norm;
    }

    public void setNorm(double norm) {
        this.norm = norm;
    }

    public List<Float> getEncodingArray1() {
        return encodingArray1;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public double getEncodingVersion() {
        return encodingVersion;
    }

    public void setEncodingVersion(double encodingVersion) {
        this.encodingVersion = encodingVersion;
    }

    public String getSecondErrorImage() {
        return secondErrorImage;
    }

    public void setSecondErrorImage(String secondErrorImage) {
        this.secondErrorImage = secondErrorImage;
    }

    public List<String> getErrorImages() {
        return errorImages;
    }

    public void setErrorImages(List<String> errorImages) {
        this.errorImages = errorImages;
    }

    public String getFirstErrorImage() {
        return firstErrorImage;
    }

    public void setFirstErrorImage(String firstErrorImage) {
        this.firstErrorImage = firstErrorImage;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid2() {
        return uuid2;
    }

    public void setUuid2(String uuid2) {
        this.uuid2 = uuid2;
    }


    public String getImageEncryptedPart() {
        return imageEncryptedPart;
    }

    public void setImageEncryptedPart(String imageEncryptedPart) {
        this.imageEncryptedPart = imageEncryptedPart;
    }

    public String getImageEncryptedPart2() {
        return imageEncryptedPart2;
    }

    public void setImageEncryptedPart2(String imageEncryptedPart2) {
        this.imageEncryptedPart2 = imageEncryptedPart2;
    }


    public int getFaceScore() {
        return faceScore;
    }

    public void setFaceScore(int faceScore) {
        this.faceScore = faceScore;
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

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportNumber1() {
        return passportNumber1;
    }

    public void setPassportNumber1(String passportNumber1) {
        this.passportNumber1 = passportNumber1;
    }


    public String getNationalityCode() {
        return nationalityCode;
    }

    public void setNationalityCode(String nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public boolean isMightBeTruncated() {
        return mightBeTruncated;
    }

    public void setMightBeTruncated(boolean mightBeTruncated) {
        this.mightBeTruncated = mightBeTruncated;
    }

    public String getType5() {
        return type5;
    }

    public void setType5(String type5) {
        this.type5 = type5;
    }

    public String getType6() {
        return type6;
    }

    public void setType6(String type6) {
        this.type6 = type6;
    }

    public String getStageStatus6() {
        return stageStatus6;
    }

    public void setStageStatus6(String stageStatus6) {
        this.stageStatus6 = stageStatus6;
    }

    public String getActionType6() {
        return actionType6;
    }

    public void setActionType6(String actionType6) {
        this.actionType6 = actionType6;
    }

    public int getOrder5() {
        return order5;
    }

    public void setOrder5(int order5) {
        this.order5 = order5;
    }

    public int getOrder6() {
        return order6;
    }

    public void setOrder6(int order6) {
        this.order6 = order6;
    }

    public String getStageStatus5() {
        return stageStatus5;
    }

    public void setStageStatus5(String stageStatus5) {
        this.stageStatus5 = stageStatus5;
    }

    public String getActionType5() {
        return actionType5;
    }

    public void setActionType5(String actionType5) {
        this.actionType5 = actionType5;
    }

    public float getMaskScore() {
        return maskScore;
    }

    public void setMaskScore(float maskScore) {
        this.maskScore = maskScore;
    }

    public List<Integer> getMouthRight() {
        return mouthRight;
    }

    public void setMouthRight(List<Integer> mouthRight) {
        this.mouthRight = mouthRight;
    }

    public List<Integer> getMouthLeft() {
        return mouthLeft;
    }

    public void setMouthLeft(List<Integer> mouthLeft) {
        this.mouthLeft = mouthLeft;
    }

    public List<Object> getHeadPose() {
        return headPose;
    }

    public void setHeadPose(List<Object> headPose) {
        this.headPose = headPose;
    }

    public List<Integer> getLandMarkNose() {
        return landMarkNose;
    }

    public void setLandMarkNose(List<Integer> landMarkNose) {
        this.landMarkNose = landMarkNose;
    }

    public List<Integer> getRightEye() {
        return rightEye;
    }

    public void setRightEye(List<Integer> rightEye) {
        this.rightEye = rightEye;
    }

    public List<Integer> getLeftEye() {
        return leftEye;
    }

    public void setLeftEye(List<Integer> leftEye) {
        this.leftEye = leftEye;
    }

    public List<Integer> getBoundingBoxLtrb() {
        return boundingBoxLtrb;
    }

    public void setBoundingBoxLtrb(List<Integer> boundingBoxLtrb) {
        this.boundingBoxLtrb = boundingBoxLtrb;
    }

    public String getStatusUnderPayload() {
        return statusUnderPayload;
    }

    public void setStatusUnderPayload(String statusUnderPayload) {
        this.statusUnderPayload = statusUnderPayload;
    }

    public String getStageStatus2() {
        return stageStatus2;
    }

    public void setStageStatus2(String stageStatus2) {
        this.stageStatus2 = stageStatus2;
    }

    public String getStageStatus3() {
        return stageStatus3;
    }

    public void setStageStatus3(String stageStatus3) {
        this.stageStatus3 = stageStatus3;
    }

    public String getStageStatus4() {
        return stageStatus4;
    }

    public void setStageStatus4(String stageStatus4) {
        this.stageStatus4 = stageStatus4;
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

    public String getStageStatus() {
        return stageStatus;
    }

    public void setStageStatus(String stageStatus) {
        this.stageStatus = stageStatus;
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

    public boolean isTemplateMatched() {
        return templateMatched;
    }

    public void setTemplateMatched(boolean templateMatched) {
        this.templateMatched = templateMatched;
    }

    public boolean isIdNumberMatchesFront() {
        return idNumberMatchesFront;
    }

    public void setIdNumberMatchesFront(boolean idNumberMatchesFront) {
        this.idNumberMatchesFront = idNumberMatchesFront;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getOtpNum() {
        return otpNum;
    }

    public void setOtpNum(int otpNum) {
        this.otpNum = otpNum;
    }

    public boolean isFaceImageReplacedWithPassportImage() {
        return faceImageReplacedWithPassportImage;
    }

    public void setFaceImageReplacedWithPassportImage(boolean faceImageReplacedWithPassportImage) {
        this.faceImageReplacedWithPassportImage = faceImageReplacedWithPassportImage;
    }

    public double getLivenessScore() {
        return livenessScore;
    }

    public void setLivenessScore(Float livenessScore) {
        this.livenessScore = livenessScore;
    }

    public int getStageData() {
        return stageData;
    }

    public void setStageData(int stageData) {
        this.stageData = stageData;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public String getStatusUnderStage() {
        return statusUnderStage;
    }

    public void setStatusUnderStage(String statusUnderStage) {
        this.statusUnderStage = statusUnderStage;
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

    public boolean isImageIsColorful2() {
        return imageIsColorful2;
    }

    public void setImageIsColorful2(boolean imageIsColorful) {
        this.imageIsColorful2 = imageIsColorful;
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

    public boolean isStampDetectedAuth() {
        return stampDetectedAuth;
    }

    public boolean isStampDetected() {
        return stampDetected;
    }

    public void setStampDetected(boolean stampDetected) {
        this.stampDetected = stampDetected;
    }

    public void setStampDetectedAuth(boolean stampDetectedAuth) {
        this.stampDetectedAuth = stampDetectedAuth;
    }

    public boolean isChipAuth() {
        return chipAuth;
    }

    public void setChipAuth(boolean chipAuth) {
        this.chipAuth = chipAuth;
    }

    public double getBioFaceImageScore() {
        return bioFaceImageScore;
    }

    public void setBioFaceImageScore(double bioFaceImageScore) {
        this.bioFaceImageScore = bioFaceImageScore;
    }

    public double getChipScore() {
        return chipScore;
    }

    public void setChipScore(double chipScore) {
        this.chipScore = chipScore;
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

    public int getbYear() {
        return bYear;
    }

    public void setbYear(int bYear) {
        this.bYear = bYear;
    }

    public int getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(int licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getPlaceOfIssue() {
        return placeOfIssue;
    }

    public void setPlaceOfIssue(String placeOfIssue) {
        this.placeOfIssue = placeOfIssue;
    }

    public String getConfigFileName() {
        return configFileName;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGrandfatherName() {
        return grandfatherName;
    }

    public void setGrandfatherName(String grandfatherName) {
        this.grandfatherName = grandfatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCitizenship() {
        System.out.println(citizenship);
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getNoseCenterX() {
        return noseCenterX;
    }

    public void setNoseCenterX(int NoseCenterX) {
        this.noseCenterX = noseCenterX;
    }

    public String getMessageTranslations97() {
        return messageTranslations97;
    }

    public void setMessageTranslations97(String messageTranslations97) {
        this.messageTranslations97 = messageTranslations97;
    }

    public String getMessageTranslations98() {
        return messageTranslations98;
    }

    public void setMessageTranslations98(String messageTranslations98) {
        this.messageTranslations98 = messageTranslations98;
    }

    public String getMessageTranslations99() {
        return messageTranslations99;
    }

    public void setMessageTranslations99(String messageTranslations99) {
        this.messageTranslations99 = messageTranslations99;
    }

    public String getMessageTranslations100() {
        return messageTranslations100;
    }

    public void setMessageTranslations100(String messageTranslations100) {
        this.messageTranslations100 = messageTranslations100;
    }

    public String getMessageTranslations101() {
        return messageTranslations101;
    }

    public void setMessageTranslations101(String messageTranslations101) {
        this.messageTranslations101 = messageTranslations101;
    }

    public String getMessageTranslations102() {
        return messageTranslations102;
    }

    public void setMessageTranslations102(String messageTranslations102) {
        this.messageTranslations102 = messageTranslations102;
    }

    public String getMessageTranslations103() {
        return messageTranslations103;
    }

    public void setMessageTranslations103(String messageTranslations103) {
        this.messageTranslations103 = messageTranslations103;
    }

    public String getMessageTranslations104() {
        return messageTranslations104;
    }

    public void setMessageTranslations104(String messageTranslations104) {
        this.messageTranslations104 = messageTranslations104;
    }

    public String getMessageTranslations149() {
        return messageTranslations149;
    }

    public void setMessageTranslations149(String messageTranslations149) {
        this.messageTranslations149 = messageTranslations149;
    }

    public String getMessageTranslations150() {
        return messageTranslations150;
    }

    public void setMessageTranslations150(String messageTranslations150) {
        this.messageTranslations150 = messageTranslations150;
    }

    public String getMessageTranslations151() {
        return messageTranslations151;
    }

    public void setMessageTranslations151(String messageTranslations151) {
        this.messageTranslations151 = messageTranslations151;
    }

    public String getMessageTranslations152() {
        return messageTranslations152;
    }

    public void setMessageTranslations152(String messageTranslations152) {
        this.messageTranslations152 = messageTranslations152;
    }

    public String getMessageTranslations153() {
        return messageTranslations153;
    }

    public void setMessageTranslations153(String messageTranslations153) {
        this.messageTranslations153 = messageTranslations153;
    }

    public String getMessageTranslations154() {
        return messageTranslations154;
    }

    public void setMessageTranslations154(String messageTranslations154) {
        this.messageTranslations154 = messageTranslations154;
    }

    public String getMessageTranslations155() {
        return messageTranslations155;
    }

    public void setMessageTranslations155(String messageTranslations155) {
        this.messageTranslations155 = messageTranslations155;
    }

    public String getMessageTranslations156() {
        return messageTranslations156;
    }

    public void setMessageTranslations156(String messageTranslations156) {
        this.messageTranslations156 = messageTranslations156;
    }

    public String getMessageTranslations157() {
        return messageTranslations157;
    }

    public void setMessageTranslations157(String messageTranslations157) {
        this.messageTranslations157 = messageTranslations157;
    }

    public String getMessageTranslations158() {
        return messageTranslations158;
    }

    public void setMessageTranslations158(String messageTranslations158) {
        this.messageTranslations158 = messageTranslations158;
    }

    public String getMessageTranslations159() {
        return messageTranslations159;
    }

    public void setMessageTranslations159(String messageTranslations159) {
        this.messageTranslations159 = messageTranslations159;
    }

    public String getMessageTranslations160() {
        return messageTranslations160;
    }

    public void setMessageTranslations160(String messageTranslations160) {
        this.messageTranslations160 = messageTranslations160;
    }

    public String getMessageTranslations161() {
        return messageTranslations161;
    }

    public void setMessageTranslations161(String messageTranslations161) {
        this.messageTranslations161 = messageTranslations161;
    }

    public String getMessageTranslations162() {
        return messageTranslations162;
    }

    public void setMessageTranslations162(String messageTranslations162) {
        this.messageTranslations162 = messageTranslations162;
    }

    public String getMessageTranslations163() {
        return messageTranslations163;
    }

    public void setMessageTranslations163(String messageTranslations163) {
        this.messageTranslations163 = messageTranslations163;
    }

    public String getMessageTranslations164() {
        return messageTranslations164;
    }

    public void setMessageTranslations164(String messageTranslations164) {
        this.messageTranslations164 = messageTranslations164;
    }

    public String getMessageTranslations165() {
        return messageTranslations165;
    }

    public void setMessageTranslations165(String messageTranslations165) {
        this.messageTranslations165 = messageTranslations165;
    }

    public String getMessageTranslations166() {
        return messageTranslations166;
    }

    public void setMessageTranslations166(String messageTranslations166) {
        this.messageTranslations166 = messageTranslations166;
    }

    public String getMessageTranslations167() {
        return messageTranslations167;
    }

    public void setMessageTranslations167(String messageTranslations167) {
        this.messageTranslations167 = messageTranslations167;
    }

    public String getMessageTranslations168() {
        return messageTranslations168;
    }

    public String getMessageTranslations169() {
        return messageTranslations169;
    }

    public void setMessageTranslations168(String messageTranslations168) {
        this.messageTranslations168 = messageTranslations168;
    }

    public void setMessageTranslations169(String messageTranslations169) {
        this.messageTranslations169 = messageTranslations169;
    }

    public String getMessageTranslations250() {
        return messageTranslations250;
    }

    public void setMessageTranslations250(String messageTranslations168) {
        this.messageTranslations250 = messageTranslations168;
    }

    public int getNoseCenterY() {
        return noseCenterY;
    }

    public void setNoseCenterY(int noseCenterY) {
        this.noseCenterY = noseCenterY;
    }

    public int getCardTypeCni() {
        return cardTypeCni;
    }

    public void setCardTypeCni(int cardTypeCni) {
        cardTypeCni = cardTypeCni;
    }

    public String getLastNameHebrew() {
        return lastNameHebrew;
    }

    public void setLastNameHebrew(String lastNameHebrew) {
        this.lastNameHebrew = lastNameHebrew;
    }

    public String getFirstNameHebrew() {
        return firstNameHebrew;
    }

    public void setFirstNameHebrew(String firstNameHebrew) {
        this.firstNameHebrew = firstNameHebrew;
    }


    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getIdNumber2() {
        return idNumber2;
    }

    public void setIdNumber2(int idNumber2) {
        this.idNumber2 = idNumber2;
    }

    public float getDetectionScore() {
        return detectionScore;
    }

    public void setDetectionScore(float detectionScore) {
        this.detectionScore = detectionScore;
    }

    public float getDistanceBetweenEyes() {
        return distanceBetweenEyes;
    }

    public void setDistanceBetweenEyes(float distanceBetweenEyes) {
        this.distanceBetweenEyes = distanceBetweenEyes;
    }

    public float getHeadPosePitch() {
        return headPosePitch;
    }

    public void setHeadPosePitch(float headPosePitch) {
        this.headPosePitch = headPosePitch;
    }

    public float getHeadPoseRoll() {
        return headPoseRoll;
    }

    public void setHeadPoseRoll(float headPoseRoll) {
        this.headPoseRoll = headPoseRoll;
    }

    public float getHeadPoseYaw() {
        return headPoseYaw;
    }

    public void setHeadPoseYaw(float headPoseYaw) {
        this.headPoseYaw = headPoseYaw;
    }

    public float getIlluminationScore() {
        return illuminationScore;
    }

    public void setIlluminationScore(float illuminationScore) {
        this.illuminationScore = illuminationScore;
    }

    public float getSharpnessScoreComplete() {
        return sharpnessScoreComplete;
    }

    public void setSharpnessScoreComplete(float sharpnessScoreComplete) {
        this.sharpnessScoreComplete = sharpnessScoreComplete;
    }

    public int getSharpnessScore() {
        return sharpnessScore;
    }

    public void setSharpnessScore(int sharpnessScore) {
        this.sharpnessScore = sharpnessScore;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getInputImage() {
        return inputImage;
    }

    public void setInputImage(String inputImage) {
        this.inputImage = inputImage;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public String getCroppedImage() {
        return croppedImage;
    }

    public String getLastReceivedImage() {
        return lastReceivedImage;
    }

    public void setLastReceivedImage(String lastReceivedImage) {
        this.lastReceivedImage = lastReceivedImage;
    }

    public void setCroppedImage(String croppedImage) {
        this.croppedImage = croppedImage;
    }

    public String getStatusComplete() {
        return statusComplete;
    }

    public void setStatusComplete(String statusComplete) {
        this.statusComplete = statusComplete;
    }

    public ArrayList getStages() {
        return stages;
    }

    public void setStages(ArrayList stages) {
        this.stages = stages;
    }

    public ArrayList getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(ArrayList landmarks) {
        this.landmarks = landmarks;
    }

    public boolean isAudio() {
        return audio;
    }

    public void setAudio(boolean audio) {
        this.audio = audio;
    }

    public double getCardRatio() {
        return cardRatio;
    }

    public void setCardRatio(float cardRatio) {
        this.cardRatio = cardRatio;
    }

    public float getQualityScore() {
        float qualityScoreFloat = Float.parseFloat(qualityScore);
        return qualityScoreFloat;
    }

    public void setQualityScore(String qualityScore) {
        this.qualityScore = qualityScore;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public boolean isInBounds() {
        return inBounds;
    }

    public void setInBounds(boolean inBounds) {
        this.inBounds = inBounds;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(String topLeft) {
        this.topLeft = topLeft;
    }

    public String getTopRight() {
        return topRight;
    }

    public void setTopRight(String topRight) {
        this.topRight = topRight;
    }

    public String getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(String bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public String getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(String bottomRight) {
        this.bottomRight = bottomRight;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getMandatoryFieldsMessage() {
        return mandatoryFieldsMessage;
    }

    public void setMandatoryFieldsMessage (String mandatoryFieldsMessage) {
        this.mandatoryFieldsMessage = mandatoryFieldsMessage;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getFrameId() {
        return frameId;
    }

    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    public String getTypeUnderPayload() {
        return typeUnderPayload;
    }

    public void setTypeUnderPayload(String typeUnderPayload) {
        this.typeUnderPayload = typeUnderPayload;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public HashMap getMessageTranslations() {
        return messageTranslations;
    }

    public void setMessageTranslations(HashMap messageTranslations) {
        this.messageTranslations = messageTranslations;
    }

    public ArrayList getError() {
        return error;
    }

    public void setError(ArrayList error) {
        this.error = error;
    }

    public HashMap getPreStageTranslations() {
        return preStageTranslations;
    }

    public void setPreStageTranslations(HashMap preStageTranslations) {
        this.preStageTranslations = preStageTranslations;
    }

    public HashMap getStyles() {
        return styles;
    }

    public void setStyles(HashMap styles) {
        this.styles = styles;
    }

    public String getActionType1() {
        return actionType1;
    }

    public void setActionType1(String actionType1) {
        this.actionType1 = actionType1;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getCardType1() {
        return cardType1;
    }

    public void setCardType1(String cardType1) {
        this.cardType1 = cardType1;
    }

    public float getQualityScore1() {
        float qualityScoreFloat = Float.parseFloat(qualityScore1);
        return qualityScoreFloat;
    }

    public void setQualityScore1(String qualityScore1) {
        this.qualityScore1 = qualityScore1;
    }

    public String getInputImage1() {
        return inputImage1;
    }

    public void setInputImage1(String inputImage1) {
        this.inputImage1 = inputImage1;
    }

    public String getFaceImage1() {
        return faceImage1;
    }

    public void setFaceImage1(String faceImage1) {
        this.faceImage1 = faceImage1;
    }

    public String getCroppedImage1() {
        return croppedImage1;
    }

    public void setCroppedImage1(String croppedImage1) {
        this.croppedImage1 = croppedImage1;
    }

    public String getActionType2() {
        return actionType2;
    }

    public void setActionType2(String actionType2) {
        this.actionType2 = actionType2;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getActionType3() {
        return actionType3;
    }

    public void setActionType3(String actionType3) {
        this.actionType3 = actionType3;
    }

    public String getType3() {
        return type3;
    }

    public void setType3(String type3) {
        this.type3 = type3;
    }

    public String getCardType2() {
        return cardType2;
    }

    public void setCardType2(String cardType2) {
        this.cardType2 = cardType2;
    }

    public float getQualityScore2() {
        float qualityScoreFloat = Float.parseFloat(qualityScore2);
        return qualityScoreFloat;
    }

    public void setQualityScore2(String qualityScore2) {
        this.qualityScore2 = qualityScore2;
    }

    public String getInputImage2() {
        return inputImage2;
    }

    public void setInputImage2(String inputImage2) {
        this.inputImage2 = inputImage2;
    }

    public String getFaceImage2() {
        return faceImage2;
    }

    public void setFaceImage2(String faceImage2) {
        this.faceImage2 = faceImage2;
    }

    public String getCroppedImage2() {
        return croppedImage2;
    }

    public void setCroppedImage2(String croppedImage2) {
        this.croppedImage2 = croppedImage2;
    }

    public String getActionType4() {
        return actionType4;
    }

    public void setActionType4(String actionType4) {
        this.actionType4 = actionType4;
    }

    public String getType4() {
        return type4;
    }

    public void setType4(String type4) {
        this.type4 = type4;
    }

    public int getOrder1() {
        return order1;
    }

    public void setOrder1(int order1) {
        this.order1 = order1;
    }

    public int getOrder2() {
        return order2;
    }

    public void setOrder2(int order2) {
        this.order2 = order2;
    }

    public int getOrder3() {
        return order3;
    }

    public void setOrder3(int order3) {
        this.order3 = order3;
    }

    public int getOrder4() {
        return order4;
    }

    public void setOrder4(int order4) {
        this.order4 = order4;
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

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public double getDblScore() {
        return dblScore;
    }

    public void setDblScore(Double dblScore) {
        this.dblScore = dblScore;
    }

    public double getDblScore2() {
        return dblScore2;
    }

    public void setDblScore2(Double dblScore2) {
        this.dblScore2 = dblScore2;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getPermitType() {
        return stayPermit;
    }

    public void setPermitType(String stayPermit) {
        this.stayPermit = stayPermit;
    }

    public String getFullNameHebrew() {
        return fullNameHebrew;
    }

    public void setFullNameHebrew(String fullNameHebrew) {
        this.fullNameHebrew = fullNameHebrew;
    }

    public String getFullNameEnglish() {
        return fullNameEnglish;
    }

    public void setFullNameEnglish(String fullNameEnglish) {
        this.fullNameEnglish = fullNameEnglish;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }

    public int getInternetCode() {
        return internetCode;
    }

    public void setInternetCode(int internetCode) {
        this.internetCode = internetCode;
    }


}