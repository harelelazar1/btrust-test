package selenium.ocr.pageObject.oldService;

import java.util.HashMap;

public class OcrFieldsObjects {

    private HashMap objectId;
    private double timestamp;
    private String caseId;
    private String ocrType;
    private String serviceType;
    private String status;
    private String token;
    private String workingMode;
    private String serverType;
    private String actionType;
    private int order;

    private String cardImage;
    private String faceImage;
    private String inputImage;
    private String video;

    private String id_type;
    private String face_quality_status;

    private String lastProcessedImage; //timeout
    private String errorMessage;
    private String errorCode;

    //authentication - ID
    private String COLORFUL_IMAGE_VALID_NAME;
    private String COLORFUL_IMAGE_VALID_VALUE;
    private String EXPIRY_DATE_VALID_NAME;
    private String EXPIRY_DATE_VALID_VALUE;
    private String FACE_POSITION_VALID_NAME;
    private String FACE_POSITION_VALID_VALUE;
    private String FACE_ROTATION_VALID_NAME;
    private String FACE_ROTATION_VALID_VALUE;
    private String FACE_SIZE_VALID_NAME;
    private String FACE_SIZE_VALID_VALUE;
    private String ID_ISSUED_FOR_5_OR_10_YEARS_NAME;
    private String ID_ISSUED_FOR_5_OR_10_YEARS_VALUE;
    private String ID_NUMBER_CHECKSUM_VALID_NAME;
    private String ID_NUMBER_CHECKSUM_VALID_VALUE;
    private String ID_NUMBER_SIZE_VALID_NAME;
    private String ID_NUMBER_SIZE_VALID_VALUE;
    private String ISSUE_DATE_VALID_NAME;
    private String ISSUE_DATE_VALID_VALUE;
    private String TEMPLATE_MATCHING_VALID_NAME;
    private String TEMPLATE_MATCHING_VALID_VALUE;
    private String SYMBOLS_ON_FACE_IMAGE_VALID_NAME;
    private String SYMBOLS_ON_FACE_IMAGE_VALID_VALUE;

    //fields - ID
    private String ISRAEL_ID2_FIELD_NAME_ID_NUMBER_NAME;
    private String ISRAEL_ID2_FIELD_NAME_ID_NUMBER_VALUE;
    private String ISRAEL_ID2_FIELD_NAME_ID_NUMBER_X_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_ID_NUMBER_Y_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_ID_NUMBER_SIZE_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_ID_NUMBER_FONT_AUTH;

    private String ISRAEL_ID2_FIELD_NAME_LAST_NAME_NAME;
    private String ISRAEL_ID2_FIELD_NAME_LAST_NAME_VALUE;
    private String ISRAEL_ID2_FIELD_NAME_LAST_NAME_X_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_LAST_NAME_Y_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_LAST_NAME_SIZE_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_LAST_NAME_FONT_AUTH;

    private String ISRAEL_ID2_FIELD_NAME_FIRST_NAME_NAME;
    private String ISRAEL_ID2_FIELD_NAME_FIRST_NAME_VALUE;
    private String ISRAEL_ID2_FIELD_NAME_FIRST_NAME_X_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_FIRST_NAME_Y_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_FIRST_NAME_SIZE_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_FIRST_NAME_FONT_AUTH;

    private String ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_NAME;
    private String ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_VALUE;
    private String ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_X_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_Y_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_SIZE_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_FONT_AUTH;

    private String ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_NAME;
    private String ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_VALUE;
    private String ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_X_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_Y_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_SIZE_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_FONT_AUTH;

    private String ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_NAME;
    private String ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_VALUE;
    private String ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_X_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_Y_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_SIZE_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_FONT_AUTH;

    private String ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_NAME;
    private String ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_VALUE;
    private String ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_X_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_Y_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_SIZE_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_FONT_AUTH;

    private String ISRAEL_ID2_FIELD_NAME_GENDER_NAME;
    private String ISRAEL_ID2_FIELD_NAME_GENDER_VALUE;
    private String ISRAEL_ID2_FIELD_NAME_GENDER_X_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_GENDER_Y_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_GENDER_SIZE_AUTH;
    private String ISRAEL_ID2_FIELD_NAME_GENDER_FONT_AUTH;


    //DL
    private String croppedImage;
    private String cardType;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_HEBREW_NAME;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_HEBREW_VALUE;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_ENGLISH_NAME;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_ENGLISH_VALUE;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_HEBREW_NAME;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_HEBREW_VALUE;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_ENGLISH_NAME;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_ENGLISH_VALUE;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_BIRTH_NAME;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_BIRTH_VALUE;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_ISSUE_NAME;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_ISSUE_VALUE;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_EXPIRY_NAME;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_EXPIRY_VALUE;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_NUMBER_NAME;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_NUMBER_VALUE;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_NAME;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_VALUE;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ADDRESS_NAME;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ADDRESS_VALUE;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_CATEGORIES_NAME;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_CATEGORIES_VALUE;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_B_YEAR_NAME;
    private String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_B_YEAR_VALUE;

    //MRZ
    private String cardID_type;
    private String first_row_name;
    private String first_row_value;
    private String second_row_name;
    private String second_row_value;
    private String third_row_name;
    private String third_row_value;

    private String date_of_birth_name;
    private String date_of_birth_value;
    private String date_of_expiry_name;
    private String date_of_expiry_value;
    private String first_name_name_in_fields;
    private String first_name_value_in_fields;
    private String gender_name;
    private String gender_value;
    private String id_number_name;
    private String id_number_value;
    private String issuing_country_name;
    private String issuing_country_value;
    private String last_name_name_in_fields;
    private String last_name_value_in_fields;
    private String nationality_name;
    private String nationality_value;
    private String passport_number_name;
    private String passport_number_value;

    private String last_name_name_in_vis;
    private String last_name_value_in_vis;
    private String first_name_name_in_vis;
    private String first_name_value_in_vis;

    private boolean isTruncated;
    private boolean isFaceAvailable;

    //Cheque
    private String numberLineImage;
    private String cheque_number;
    private String firstRowBlocks;
    private String secondRowBlocks;
    private String thirdRowBlocks;
    private int firstBlockCharactersSize;
    private int secondBlockCharactersSize;
    private int thirdBlockCharactersSize;
    private boolean isCorrelationInRange;
    private boolean firstBlockCharactersLetter;
    private boolean secondBlockCharactersLetter;
    private boolean thirdBlockCharactersLetter;
    private String characters;
    private Double minimumCorrelation;
    private Double averageCorrelation;
    private Double requiredMinimumCorrelation;

    public String getServerType() {
        return serverType;
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

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_NAME() {
        return ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_NAME;
    }

    public void setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_NAME(String ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_NAME) {
        this.ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_NAME = ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_NAME;
    }

    public String getISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_VALUE() {
        return ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_VALUE;
    }

    public void setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_VALUE(String ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_VALUE) {
        this.ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_VALUE = ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_VALUE;
    }

    public String getISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_X_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_X_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_X_AUTH(String ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_X_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_X_AUTH = ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_X_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_Y_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_Y_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_Y_AUTH(String ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_Y_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_Y_AUTH = ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_Y_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_SIZE_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_SIZE_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_SIZE_AUTH(String ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_SIZE_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_SIZE_AUTH = ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_SIZE_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_FONT_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_FONT_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_FONT_AUTH(String ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_FONT_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_FONT_AUTH = ISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_FONT_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_GENDER_NAME() {
        return ISRAEL_ID2_FIELD_NAME_GENDER_NAME;
    }

    public void setISRAEL_ID2_FIELD_NAME_GENDER_NAME(String ISRAEL_ID2_FIELD_NAME_GENDER_NAME) {
        this.ISRAEL_ID2_FIELD_NAME_GENDER_NAME = ISRAEL_ID2_FIELD_NAME_GENDER_NAME;
    }

    public String getISRAEL_ID2_FIELD_NAME_GENDER_VALUE() {
        return ISRAEL_ID2_FIELD_NAME_GENDER_VALUE;
    }

    public void setISRAEL_ID2_FIELD_NAME_GENDER_VALUE(String ISRAEL_ID2_FIELD_NAME_GENDER_VALUE) {
        this.ISRAEL_ID2_FIELD_NAME_GENDER_VALUE = ISRAEL_ID2_FIELD_NAME_GENDER_VALUE;
    }

    public String getISRAEL_ID2_FIELD_NAME_GENDER_X_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_GENDER_X_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_GENDER_X_AUTH(String ISRAEL_ID2_FIELD_NAME_GENDER_X_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_GENDER_X_AUTH = ISRAEL_ID2_FIELD_NAME_GENDER_X_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_GENDER_Y_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_GENDER_Y_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_GENDER_Y_AUTH(String ISRAEL_ID2_FIELD_NAME_GENDER_Y_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_GENDER_Y_AUTH = ISRAEL_ID2_FIELD_NAME_GENDER_Y_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_GENDER_SIZE_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_GENDER_SIZE_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_GENDER_SIZE_AUTH(String ISRAEL_ID2_FIELD_NAME_GENDER_SIZE_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_GENDER_SIZE_AUTH = ISRAEL_ID2_FIELD_NAME_GENDER_SIZE_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_GENDER_FONT_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_GENDER_FONT_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_GENDER_FONT_AUTH(String ISRAEL_ID2_FIELD_NAME_GENDER_FONT_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_GENDER_FONT_AUTH = ISRAEL_ID2_FIELD_NAME_GENDER_FONT_AUTH;
    }


    public String getFirst_row_name() {
        return first_row_name;
    }

    public void setFirst_row_name(String first_row_name) {
        this.first_row_name = first_row_name;
    }

    public String getFirst_row_value() {
        return first_row_value;
    }

    public void setFirst_row_value(String first_row_value) {
        this.first_row_value = first_row_value;
    }

    public String getSecond_row_name() {
        return second_row_name;
    }

    public void setSecond_row_name(String second_row_name) {
        this.second_row_name = second_row_name;
    }

    public String getSecond_row_value() {
        return second_row_value;
    }

    public void setSecond_row_value(String second_row_value) {
        this.second_row_value = second_row_value;
    }

    public String getThird_row_name() {
        return third_row_name;
    }

    public void setThird_row_name(String third_row_name) {
        this.third_row_name = third_row_name;
    }

    public String getThird_row_value() {
        return third_row_value;
    }

    public void setThird_row_value(String third_row_value) {
        this.third_row_value = third_row_value;
    }

    public String getDate_of_birth_name() {
        return date_of_birth_name;
    }

    public void setDate_of_birth_name(String date_of_birth_name) {
        this.date_of_birth_name = date_of_birth_name;
    }

    public String getDate_of_birth_value() {
        return date_of_birth_value;
    }

    public void setDate_of_birth_value(String date_of_birth_value) {
        this.date_of_birth_value = date_of_birth_value;
    }

    public String getDate_of_expiry_name() {
        return date_of_expiry_name;
    }

    public void setDate_of_expiry_name(String date_of_expiry_name) {
        this.date_of_expiry_name = date_of_expiry_name;
    }

    public String getDate_of_expiry_value() {
        return date_of_expiry_value;
    }

    public void setDate_of_expiry_value(String date_of_expiry_value) {
        this.date_of_expiry_value = date_of_expiry_value;
    }

    public String getFirst_name_name_in_fields() {
        return first_name_name_in_fields;
    }

    public void setFirst_name_name_in_fields(String first_name_name_in_fields) {
        this.first_name_name_in_fields = first_name_name_in_fields;
    }

    public String getFirst_name_value_in_fields() {
        return first_name_value_in_fields;
    }

    public void setFirst_name_value_in_fields(String first_name_value_in_fields) {
        this.first_name_value_in_fields = first_name_value_in_fields;
    }

    public String getGender_name() {
        return gender_name;
    }

    public void setGender_name(String gender_name) {
        this.gender_name = gender_name;
    }

    public String getGender_value() {
        return gender_value;
    }

    public void setGender_value(String gender_value) {
        this.gender_value = gender_value;
    }

    public String getId_number_name() {
        return id_number_name;
    }

    public void setId_number_name(String id_number_name) {
        this.id_number_name = id_number_name;
    }

    public String getId_number_value() {
        return id_number_value;
    }

    public void setId_number_value(String id_number_value) {
        this.id_number_value = id_number_value;
    }

    public String getIssuing_country_name() {
        return issuing_country_name;
    }

    public void setIssuing_country_name(String issuing_country_name) {
        this.issuing_country_name = issuing_country_name;
    }

    public String getIssuing_country_value() {
        return issuing_country_value;
    }

    public void setIssuing_country_value(String issuing_country_value) {
        this.issuing_country_value = issuing_country_value;
    }

    public String getLast_name_name_in_fields() {
        return last_name_name_in_fields;
    }

    public void setLast_name_name_in_fields(String last_name_name_in_fields) {
        this.last_name_name_in_fields = last_name_name_in_fields;
    }

    public String getLast_name_value_in_fields() {
        return last_name_value_in_fields;
    }

    public void setLast_name_value_in_fields(String last_name_value_in_fields) {
        this.last_name_value_in_fields = last_name_value_in_fields;
    }

    public String getNationality_name() {
        return nationality_name;
    }

    public void setNationality_name(String nationality_name) {
        this.nationality_name = nationality_name;
    }

    public String getNationality_value() {
        return nationality_value;
    }

    public void setNationality_value(String nationality_value) {
        this.nationality_value = nationality_value;
    }

    public String getPassport_number_name() {
        return passport_number_name;
    }

    public void setPassport_number_name(String passport_number_name) {
        this.passport_number_name = passport_number_name;
    }

    public String getPassport_number_value() {
        return passport_number_value;
    }

    public void setPassport_number_value(String passport_number_value) {
        this.passport_number_value = passport_number_value;
    }

    public String getLast_name_name_in_vis() {
        return last_name_name_in_vis;
    }

    public void setLast_name_name_in_vis(String last_name_name_in_vis) {
        this.last_name_name_in_vis = last_name_name_in_vis;
    }

    public String getLast_name_value_in_vis() {
        return last_name_value_in_vis;
    }

    public void setLast_name_value_in_vis(String last_name_value_in_vis) {
        this.last_name_value_in_vis = last_name_value_in_vis;
    }

    public String getFirst_name_name_in_vis() {
        return first_name_name_in_vis;
    }

    public void setFirst_name_name_in_vis(String first_name_name_in_vis) {
        this.first_name_name_in_vis = first_name_name_in_vis;
    }

    public String getFirst_name_value_in_vis() {
        return first_name_value_in_vis;
    }

    public void setFirst_name_value_in_vis(String first_name_value_in_vis) {
        this.first_name_value_in_vis = first_name_value_in_vis;
    }

    public boolean isTruncated() {
        return isTruncated;
    }

    public void setTruncated(boolean truncated) {
        isTruncated = truncated;
    }

    public boolean isFaceAvailable() {
        return isFaceAvailable;
    }

    public void setFaceAvailable(boolean faceAvailable) {
        isFaceAvailable = faceAvailable;
    }


    public String getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(String croppedImage) {
        this.croppedImage = croppedImage;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_HEBREW_NAME() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_HEBREW_NAME;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_HEBREW_NAME(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_HEBREW_NAME) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_HEBREW_NAME = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_HEBREW_NAME;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_HEBREW_VALUE() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_HEBREW_VALUE;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_HEBREW_VALUE(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_HEBREW_VALUE) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_HEBREW_VALUE = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_HEBREW_VALUE;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_ENGLISH_NAME() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_ENGLISH_NAME;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_ENGLISH_NAME(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_ENGLISH_NAME) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_ENGLISH_NAME = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_ENGLISH_NAME;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_ENGLISH_VALUE() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_ENGLISH_VALUE;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_ENGLISH_VALUE(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_ENGLISH_VALUE) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_ENGLISH_VALUE = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_ENGLISH_VALUE;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_HEBREW_NAME() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_HEBREW_NAME;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_HEBREW_NAME(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_HEBREW_NAME) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_HEBREW_NAME = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_HEBREW_NAME;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_HEBREW_VALUE() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_HEBREW_VALUE;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_HEBREW_VALUE(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_HEBREW_VALUE) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_HEBREW_VALUE = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_HEBREW_VALUE;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_ENGLISH_NAME() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_ENGLISH_NAME;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_ENGLISH_NAME(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_ENGLISH_NAME) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_ENGLISH_NAME = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_ENGLISH_NAME;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_ENGLISH_VALUE() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_ENGLISH_VALUE;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_ENGLISH_VALUE(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_ENGLISH_VALUE) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_ENGLISH_VALUE = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_ENGLISH_VALUE;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_BIRTH_NAME() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_BIRTH_NAME;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_BIRTH_NAME(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_BIRTH_NAME) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_BIRTH_NAME = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_BIRTH_NAME;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_BIRTH_VALUE() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_BIRTH_VALUE;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_BIRTH_VALUE(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_BIRTH_VALUE) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_BIRTH_VALUE = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_BIRTH_VALUE;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_ISSUE_NAME() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_ISSUE_NAME;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_ISSUE_NAME(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_ISSUE_NAME) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_ISSUE_NAME = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_ISSUE_NAME;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_ISSUE_VALUE() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_ISSUE_VALUE;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_ISSUE_VALUE(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_ISSUE_VALUE) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_ISSUE_VALUE = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_ISSUE_VALUE;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_EXPIRY_NAME() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_EXPIRY_NAME;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_EXPIRY_NAME(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_EXPIRY_NAME) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_EXPIRY_NAME = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_EXPIRY_NAME;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_EXPIRY_VALUE() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_EXPIRY_VALUE;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_EXPIRY_VALUE(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_EXPIRY_VALUE) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_EXPIRY_VALUE = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_EXPIRY_VALUE;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_NUMBER_NAME() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_NUMBER_NAME;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_NUMBER_NAME(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_NUMBER_NAME) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_NUMBER_NAME = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_NUMBER_NAME;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_NUMBER_VALUE() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_NUMBER_VALUE;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_NUMBER_VALUE(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_NUMBER_VALUE) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_NUMBER_VALUE = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_NUMBER_VALUE;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_NAME() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_NAME;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_NAME(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_NAME) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_NAME = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_NAME;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_VALUE() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_VALUE;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_VALUE(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_VALUE) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_VALUE = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_VALUE;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ADDRESS_NAME() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ADDRESS_NAME;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ADDRESS_NAME(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ADDRESS_NAME) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ADDRESS_NAME = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ADDRESS_NAME;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ADDRESS_VALUE() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ADDRESS_VALUE;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ADDRESS_VALUE(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ADDRESS_VALUE) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ADDRESS_VALUE = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ADDRESS_VALUE;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_CATEGORIES_NAME() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_CATEGORIES_NAME;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_CATEGORIES_NAME(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_CATEGORIES_NAME) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_CATEGORIES_NAME = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_CATEGORIES_NAME;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_CATEGORIES_VALUE() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_CATEGORIES_VALUE;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_CATEGORIES_VALUE(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_CATEGORIES_VALUE) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_CATEGORIES_VALUE = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_CATEGORIES_VALUE;
    }

    public String getISRAEL_ID2_FIELD_NAME_ID_NUMBER_NAME() {
        return ISRAEL_ID2_FIELD_NAME_ID_NUMBER_NAME;
    }

    public void setISRAEL_ID2_FIELD_NAME_ID_NUMBER_NAME(String ISRAEL_ID2_FIELD_NAME_ID_NUMBER_NAME) {
        this.ISRAEL_ID2_FIELD_NAME_ID_NUMBER_NAME = ISRAEL_ID2_FIELD_NAME_ID_NUMBER_NAME;
    }

    public String getISRAEL_ID2_FIELD_NAME_ID_NUMBER_VALUE() {
        return ISRAEL_ID2_FIELD_NAME_ID_NUMBER_VALUE;
    }

    public void setISRAEL_ID2_FIELD_NAME_ID_NUMBER_VALUE(String ISRAEL_ID2_FIELD_NAME_ID_NUMBER_VALUE) {
        this.ISRAEL_ID2_FIELD_NAME_ID_NUMBER_VALUE = ISRAEL_ID2_FIELD_NAME_ID_NUMBER_VALUE;
    }

    public String getISRAEL_ID2_FIELD_NAME_ID_NUMBER_X_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_ID_NUMBER_X_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_ID_NUMBER_X_AUTH(String ISRAEL_ID2_FIELD_NAME_ID_NUMBER_X_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_ID_NUMBER_X_AUTH = ISRAEL_ID2_FIELD_NAME_ID_NUMBER_X_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_ID_NUMBER_Y_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_ID_NUMBER_Y_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_ID_NUMBER_Y_AUTH(String ISRAEL_ID2_FIELD_NAME_ID_NUMBER_Y_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_ID_NUMBER_Y_AUTH = ISRAEL_ID2_FIELD_NAME_ID_NUMBER_Y_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_ID_NUMBER_FONT_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_ID_NUMBER_FONT_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_ID_NUMBER_FONT_AUTH(String ISRAEL_ID2_FIELD_NAME_ID_NUMBER_FONT_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_ID_NUMBER_FONT_AUTH = ISRAEL_ID2_FIELD_NAME_ID_NUMBER_FONT_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_LAST_NAME_NAME() {
        return ISRAEL_ID2_FIELD_NAME_LAST_NAME_NAME;
    }

    public void setISRAEL_ID2_FIELD_NAME_LAST_NAME_NAME(String ISRAEL_ID2_FIELD_NAME_LAST_NAME_NAME) {
        this.ISRAEL_ID2_FIELD_NAME_LAST_NAME_NAME = ISRAEL_ID2_FIELD_NAME_LAST_NAME_NAME;
    }

    public String getISRAEL_ID2_FIELD_NAME_LAST_NAME_VALUE() {
        return ISRAEL_ID2_FIELD_NAME_LAST_NAME_VALUE;
    }

    public void setISRAEL_ID2_FIELD_NAME_LAST_NAME_VALUE(String ISRAEL_ID2_FIELD_NAME_LAST_NAME_VALUE) {
        this.ISRAEL_ID2_FIELD_NAME_LAST_NAME_VALUE = ISRAEL_ID2_FIELD_NAME_LAST_NAME_VALUE;
    }

    public String getISRAEL_ID2_FIELD_NAME_LAST_NAME_X_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_LAST_NAME_X_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_LAST_NAME_X_AUTH(String ISRAEL_ID2_FIELD_NAME_LAST_NAME_X_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_LAST_NAME_X_AUTH = ISRAEL_ID2_FIELD_NAME_LAST_NAME_X_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_LAST_NAME_Y_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_LAST_NAME_Y_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_LAST_NAME_Y_AUTH(String ISRAEL_ID2_FIELD_NAME_LAST_NAME_Y_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_LAST_NAME_Y_AUTH = ISRAEL_ID2_FIELD_NAME_LAST_NAME_Y_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_LAST_NAME_FONT_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_LAST_NAME_FONT_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_LAST_NAME_FONT_AUTH(String ISRAEL_ID2_FIELD_NAME_LAST_NAME_FONT_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_LAST_NAME_FONT_AUTH = ISRAEL_ID2_FIELD_NAME_LAST_NAME_FONT_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_FIRST_NAME_NAME() {
        return ISRAEL_ID2_FIELD_NAME_FIRST_NAME_NAME;
    }

    public void setISRAEL_ID2_FIELD_NAME_FIRST_NAME_NAME(String ISRAEL_ID2_FIELD_NAME_FIRST_NAME_NAME) {
        this.ISRAEL_ID2_FIELD_NAME_FIRST_NAME_NAME = ISRAEL_ID2_FIELD_NAME_FIRST_NAME_NAME;
    }

    public String getISRAEL_ID2_FIELD_NAME_FIRST_NAME_VALUE() {
        return ISRAEL_ID2_FIELD_NAME_FIRST_NAME_VALUE;
    }

    public void setISRAEL_ID2_FIELD_NAME_FIRST_NAME_VALUE(String ISRAEL_ID2_FIELD_NAME_FIRST_NAME_VALUE) {
        this.ISRAEL_ID2_FIELD_NAME_FIRST_NAME_VALUE = ISRAEL_ID2_FIELD_NAME_FIRST_NAME_VALUE;
    }

    public String getISRAEL_ID2_FIELD_NAME_FIRST_NAME_X_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_FIRST_NAME_X_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_FIRST_NAME_X_AUTH(String ISRAEL_ID2_FIELD_NAME_FIRST_NAME_X_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_FIRST_NAME_X_AUTH = ISRAEL_ID2_FIELD_NAME_FIRST_NAME_X_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_FIRST_NAME_Y_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_FIRST_NAME_Y_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_FIRST_NAME_Y_AUTH(String ISRAEL_ID2_FIELD_NAME_FIRST_NAME_Y_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_FIRST_NAME_Y_AUTH = ISRAEL_ID2_FIELD_NAME_FIRST_NAME_Y_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_FIRST_NAME_FONT_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_FIRST_NAME_FONT_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_FIRST_NAME_FONT_AUTH(String ISRAEL_ID2_FIELD_NAME_FIRST_NAME_FONT_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_FIRST_NAME_FONT_AUTH = ISRAEL_ID2_FIELD_NAME_FIRST_NAME_FONT_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_NAME() {
        return ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_NAME;
    }

    public void setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_NAME(String ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_NAME) {
        this.ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_NAME = ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_NAME;
    }

    public String getISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_VALUE() {
        return ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_VALUE;
    }

    public void setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_VALUE(String ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_VALUE) {
        this.ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_VALUE = ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_VALUE;
    }

    public String getISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_X_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_X_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_X_AUTH(String ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_X_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_X_AUTH = ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_X_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_Y_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_Y_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_Y_AUTH(String ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_Y_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_Y_AUTH = ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_Y_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_FONT_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_FONT_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_FONT_AUTH(String ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_FONT_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_FONT_AUTH = ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_FONT_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_NAME() {
        return ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_NAME;
    }

    public void setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_NAME(String ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_NAME) {
        this.ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_NAME = ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_NAME;
    }

    public String getISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_VALUE() {
        return ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_VALUE;
    }

    public void setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_VALUE(String ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_VALUE) {
        this.ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_VALUE = ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_VALUE;
    }

    public String getISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_X_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_X_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_X_AUTH(String ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_X_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_X_AUTH = ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_X_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_Y_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_Y_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_Y_AUTH(String ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_Y_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_Y_AUTH = ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_Y_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_FONT_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_FONT_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_FONT_AUTH(String ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_FONT_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_FONT_AUTH = ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_FONT_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_NAME() {
        return ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_NAME;
    }

    public void setISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_NAME(String ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_NAME) {
        this.ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_NAME = ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_NAME;
    }

    public String getISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_VALUE() {
        return ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_VALUE;
    }

    public void setISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_VALUE(String ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_VALUE) {
        this.ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_VALUE = ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_VALUE;
    }

    public String getISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_X_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_X_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_X_AUTH(String ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_X_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_X_AUTH = ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_X_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_Y_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_Y_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_Y_AUTH(String ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_Y_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_Y_AUTH = ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_Y_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_FONT_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_FONT_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_FONT_AUTH(String ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_FONT_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_FONT_AUTH = ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_FONT_AUTH;
    }

    public OcrFieldsObjects() {
    }

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
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

    public String getOcrType() {
        return ocrType;
    }

    public void setOcrType(String ocrType) {
        this.ocrType = ocrType;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public String getInputImage() {
        return inputImage;
    }

    public void setInputImage(String inputImage) {
        this.inputImage = inputImage;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public String getFace_quality_status() {
        return face_quality_status;
    }

    public void setFace_quality_status(String face_quality_status) {
        this.face_quality_status = face_quality_status;
    }

    public String getCOLORFUL_IMAGE_VALID_NAME() {
        return COLORFUL_IMAGE_VALID_NAME;
    }

    public void setCOLORFUL_IMAGE_VALID_NAME(String COLORFUL_IMAGE_VALID_NAME) {
        this.COLORFUL_IMAGE_VALID_NAME = COLORFUL_IMAGE_VALID_NAME;
    }

    public String getCOLORFUL_IMAGE_VALID_VALUE() {
        return COLORFUL_IMAGE_VALID_VALUE;
    }

    public void setCOLORFUL_IMAGE_VALID_VALUE(String COLORFUL_IMAGE_VALID_VALUE) {
        this.COLORFUL_IMAGE_VALID_VALUE = COLORFUL_IMAGE_VALID_VALUE;
    }

    public String getEXPIRY_DATE_VALID_NAME() {
        return EXPIRY_DATE_VALID_NAME;
    }

    public void setEXPIRY_DATE_VALID_NAME(String EXPIRY_DATE_VALID_NAME) {
        this.EXPIRY_DATE_VALID_NAME = EXPIRY_DATE_VALID_NAME;
    }

    public String getEXPIRY_DATE_VALID_VALUE() {
        return EXPIRY_DATE_VALID_VALUE;
    }

    public void setEXPIRY_DATE_VALID_VALUE(String EXPIRY_DATE_VALID_VALUE) {
        this.EXPIRY_DATE_VALID_VALUE = EXPIRY_DATE_VALID_VALUE;
    }

    public String getFACE_POSITION_VALID_VALUE() {
        return FACE_POSITION_VALID_VALUE;
    }

    public void setFACE_POSITION_VALID_VALUE(String FACE_POSITION_VALID_VALUE) {
        this.FACE_POSITION_VALID_VALUE = FACE_POSITION_VALID_VALUE;
    }

    public String getFACE_POSITION_VALID_NAME() {
        return FACE_POSITION_VALID_NAME;
    }

    public void setFACE_POSITION_VALID_NAME(String FACE_POSITION_VALID_NAME) {
        this.FACE_POSITION_VALID_NAME = FACE_POSITION_VALID_NAME;
    }

    public String getFACE_ROTATION_VALID_NAME() {
        return FACE_ROTATION_VALID_NAME;
    }

    public void setFACE_ROTATION_VALID_NAME(String FACE_ROTATION_VALID_NAME) {
        this.FACE_ROTATION_VALID_NAME = FACE_ROTATION_VALID_NAME;
    }

    public String getFACE_ROTATION_VALID_VALUE() {
        return FACE_ROTATION_VALID_VALUE;
    }

    public void setFACE_ROTATION_VALID_VALUE(String FACE_ROTATION_VALID_VALUE) {
        this.FACE_ROTATION_VALID_VALUE = FACE_ROTATION_VALID_VALUE;
    }

    public String getFACE_SIZE_VALID_VALUE() {
        return FACE_SIZE_VALID_VALUE;
    }

    public void setFACE_SIZE_VALID_VALUE(String FACE_SIZE_VALID_VALUE) {
        this.FACE_SIZE_VALID_VALUE = FACE_SIZE_VALID_VALUE;
    }

    public String getFACE_SIZE_VALID_NAME() {
        return FACE_SIZE_VALID_NAME;
    }

    public void setFACE_SIZE_VALID_NAME(String FACE_SIZE_VALID_NAME) {
        this.FACE_SIZE_VALID_NAME = FACE_SIZE_VALID_NAME;
    }

    public String getID_ISSUED_FOR_5_OR_10_YEARS_NAME() {
        return ID_ISSUED_FOR_5_OR_10_YEARS_NAME;
    }

    public void setID_ISSUED_FOR_5_OR_10_YEARS_NAME(String ID_ISSUED_FOR_5_OR_10_YEARS_NAME) {
        this.ID_ISSUED_FOR_5_OR_10_YEARS_NAME = ID_ISSUED_FOR_5_OR_10_YEARS_NAME;
    }

    public String getID_ISSUED_FOR_5_OR_10_YEARS_VALUE() {
        return ID_ISSUED_FOR_5_OR_10_YEARS_VALUE;
    }

    public void setID_ISSUED_FOR_5_OR_10_YEARS_VALUE(String ID_ISSUED_FOR_5_OR_10_YEARS_VALUE) {
        this.ID_ISSUED_FOR_5_OR_10_YEARS_VALUE = ID_ISSUED_FOR_5_OR_10_YEARS_VALUE;
    }

    public String getID_NUMBER_CHECKSUM_VALID_NAME() {
        return ID_NUMBER_CHECKSUM_VALID_NAME;
    }

    public void setID_NUMBER_CHECKSUM_VALID_NAME(String ID_NUMBER_CHECKSUM_VALID_NAME) {
        this.ID_NUMBER_CHECKSUM_VALID_NAME = ID_NUMBER_CHECKSUM_VALID_NAME;
    }

    public String getID_NUMBER_CHECKSUM_VALID_VALUE() {
        return ID_NUMBER_CHECKSUM_VALID_VALUE;
    }

    public void setID_NUMBER_CHECKSUM_VALID_VALUE(String ID_NUMBER_CHECKSUM_VALID_VALUE) {
        this.ID_NUMBER_CHECKSUM_VALID_VALUE = ID_NUMBER_CHECKSUM_VALID_VALUE;
    }

    public String getID_NUMBER_SIZE_VALID_NAME() {
        return ID_NUMBER_SIZE_VALID_NAME;
    }

    public void setID_NUMBER_SIZE_VALID_NAME(String ID_NUMBER_SIZE_VALID_NAME) {
        this.ID_NUMBER_SIZE_VALID_NAME = ID_NUMBER_SIZE_VALID_NAME;
    }

    public String getID_NUMBER_SIZE_VALID_VALUE() {
        return ID_NUMBER_SIZE_VALID_VALUE;
    }

    public void setID_NUMBER_SIZE_VALID_VALUE(String ID_NUMBER_SIZE_VALID_VALUE) {
        this.ID_NUMBER_SIZE_VALID_VALUE = ID_NUMBER_SIZE_VALID_VALUE;
    }

    public String getISSUE_DATE_VALID_NAME() {
        return ISSUE_DATE_VALID_NAME;
    }

    public void setISSUE_DATE_VALID_NAME(String ISSUE_DATE_VALID_NAME) {
        this.ISSUE_DATE_VALID_NAME = ISSUE_DATE_VALID_NAME;
    }

    public String getISSUE_DATE_VALID_VALUE() {
        return ISSUE_DATE_VALID_VALUE;
    }

    public void setISSUE_DATE_VALID_VALUE(String ISSUE_DATE_VALID_VALUE) {
        this.ISSUE_DATE_VALID_VALUE = ISSUE_DATE_VALID_VALUE;
    }

    public String getTEMPLATE_MATCHING_VALID_NAME() {
        return TEMPLATE_MATCHING_VALID_NAME;
    }

    public void setTEMPLATE_MATCHING_VALID_NAME(String TEMPLATE_MATCHING_VALID_NAME) {
        this.TEMPLATE_MATCHING_VALID_NAME = TEMPLATE_MATCHING_VALID_NAME;
    }

    public String getTEMPLATE_MATCHING_VALID_VALUE() {
        return TEMPLATE_MATCHING_VALID_VALUE;
    }

    public void setTEMPLATE_MATCHING_VALID_VALUE(String TEMPLATE_MATCHING_VALID_VALUE) {
        this.TEMPLATE_MATCHING_VALID_VALUE = TEMPLATE_MATCHING_VALID_VALUE;
    }

    public String getISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_SIZE_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_SIZE_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_SIZE_AUTH(String ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_SIZE_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_SIZE_AUTH = ISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_SIZE_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_SIZE_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_SIZE_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_SIZE_AUTH(String ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_SIZE_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_SIZE_AUTH = ISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_SIZE_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_SIZE_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_SIZE_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_SIZE_AUTH(String ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_SIZE_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_SIZE_AUTH = ISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_SIZE_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_FIRST_NAME_SIZE_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_FIRST_NAME_SIZE_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_FIRST_NAME_SIZE_AUTH(String ISRAEL_ID2_FIELD_NAME_FIRST_NAME_SIZE_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_FIRST_NAME_SIZE_AUTH = ISRAEL_ID2_FIELD_NAME_FIRST_NAME_SIZE_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_LAST_NAME_SIZE_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_LAST_NAME_SIZE_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_LAST_NAME_SIZE_AUTH(String ISRAEL_ID2_FIELD_NAME_LAST_NAME_SIZE_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_LAST_NAME_SIZE_AUTH = ISRAEL_ID2_FIELD_NAME_LAST_NAME_SIZE_AUTH;
    }

    public String getISRAEL_ID2_FIELD_NAME_ID_NUMBER_SIZE_AUTH() {
        return ISRAEL_ID2_FIELD_NAME_ID_NUMBER_SIZE_AUTH;
    }

    public void setISRAEL_ID2_FIELD_NAME_ID_NUMBER_SIZE_AUTH(String ISRAEL_ID2_FIELD_NAME_ID_NUMBER_SIZE_AUTH) {
        this.ISRAEL_ID2_FIELD_NAME_ID_NUMBER_SIZE_AUTH = ISRAEL_ID2_FIELD_NAME_ID_NUMBER_SIZE_AUTH;
    }

    public HashMap getObjectId() {
        return objectId;
    }

    public void setObjectId(HashMap objectId) {
        this.objectId = objectId;
    }

    public String getCardID_type() {
        return cardID_type;
    }

    public void setCardID_type(String cardID_type) {
        this.cardID_type = cardID_type;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_B_YEAR_NAME() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_B_YEAR_NAME;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_B_YEAR_NAME(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_B_YEAR_NAME) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_B_YEAR_NAME = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_B_YEAR_NAME;
    }

    public String getISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_B_YEAR_VALUE() {
        return ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_B_YEAR_VALUE;
    }

    public void setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_B_YEAR_VALUE(String ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_B_YEAR_VALUE) {
        this.ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_B_YEAR_VALUE = ISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_B_YEAR_VALUE;
    }

    public String getLastProcessedImage() {
        return lastProcessedImage;
    }

    public void setLastProcessedImage(String lastProcessedImage) {
        this.lastProcessedImage = lastProcessedImage;
    }

    public String getSYMBOLS_ON_FACE_IMAGE_VALID_NAME() {
        return SYMBOLS_ON_FACE_IMAGE_VALID_NAME;
    }

    public void setSYMBOLS_ON_FACE_IMAGE_VALID_NAME(String SYMBOLS_ON_FACE_IMAGE_VALID_NAME) {
        this.SYMBOLS_ON_FACE_IMAGE_VALID_NAME = SYMBOLS_ON_FACE_IMAGE_VALID_NAME;
    }

    public String getSYMBOLS_ON_FACE_IMAGE_VALID_VALUE() {
        return SYMBOLS_ON_FACE_IMAGE_VALID_VALUE;
    }

    public void setSYMBOLS_ON_FACE_IMAGE_VALID_VALUE(String SYMBOLS_ON_FACE_IMAGE_VALID_VALUE) {
        this.SYMBOLS_ON_FACE_IMAGE_VALID_VALUE = SYMBOLS_ON_FACE_IMAGE_VALID_VALUE;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getCheque_number() {
        return cheque_number;
    }

    public String getFirstRowBlocks() {
        return firstRowBlocks;
    }

    public String getSecondRowBlocks() {
        return secondRowBlocks;
    }

    public String getThirdRowBlocks() {
        return thirdRowBlocks;
    }

    public String getCharacters() {
        return characters;
    }

    public Double getMinimumCorrelation() {
        return minimumCorrelation;
    }

    public Double getAverageCorrelation() {
        return averageCorrelation;
    }

    public Double getRequiredMinimumCorrelation() {
        return requiredMinimumCorrelation;
    }

    public int getFirstBlockCharactersSize() {
        return firstBlockCharactersSize;
    }

    public int getSecondBlockCharactersSize() {
        return secondBlockCharactersSize;
    }

    public int getThirdBlockCharactersSize() {
        return thirdBlockCharactersSize;
    }

    public boolean isCorrelationInRange() {
        return isCorrelationInRange;
    }

    public String getNumberLineImage() {
        return numberLineImage;
    }

    public void setCheque_number(String cheque_number) {
        this.cheque_number = cheque_number;
    }

    public void setFirstRowBlocks(String firstRowBlocks) {
        this.firstRowBlocks = firstRowBlocks;
    }

    public void setSecondRowBlocks(String secondRowBlocks) {
        this.secondRowBlocks = secondRowBlocks;
    }

    public void setThirdRowBlocks(String thirdRowBlocks) {
        this.thirdRowBlocks = thirdRowBlocks;
    }

    public void setFirstBlockCharactersSize(int firstBlockCharactersSize) {
        this.firstBlockCharactersSize = firstBlockCharactersSize;
    }

    public void setFirstBlockCharactersLetter(boolean firstBlockCharactersLetter) {
        this.firstBlockCharactersLetter = firstBlockCharactersLetter;
    }

    public void setSecondBlockCharactersLetter(boolean secondBlockCharactersLetter) {
        this.secondBlockCharactersLetter = secondBlockCharactersLetter;
    }

    public void setThirdBlockCharactersLetter(boolean thirdBlockCharactersLetter) {
        this.thirdBlockCharactersLetter = thirdBlockCharactersLetter;
    }

    public boolean isFirstBlockCharactersLetter() {
        return firstBlockCharactersLetter;
    }

    public boolean isSecondBlockCharactersLetter() {
        return secondBlockCharactersLetter;
    }

    public boolean isThirdBlockCharactersLetter() {
        return thirdBlockCharactersLetter;
    }

    public void setSecondBlockCharactersSize(int secondBlockCharactersSize) {
        this.secondBlockCharactersSize = secondBlockCharactersSize;
    }


    public void setThirdBlockCharactersSize(int thirdBlockCharactersSize) {
        this.thirdBlockCharactersSize = thirdBlockCharactersSize;
    }

    public void setCorrelationInRange(boolean correlationInRange) {
        isCorrelationInRange = correlationInRange;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public void setMinimumCorrelation(Double minimumCorrelation) {
        this.minimumCorrelation = minimumCorrelation;
    }

    public void setAverageCorrelation(Double averageCorrelation) {
        this.averageCorrelation = averageCorrelation;
    }

    public void setRequiredMinimumCorrelation(Double requiredMinimumCorrelation) {
        this.requiredMinimumCorrelation = requiredMinimumCorrelation;
    }

    public void setNumberLineImage(String numberLineImage) {
        this.numberLineImage = numberLineImage;
    }
}

