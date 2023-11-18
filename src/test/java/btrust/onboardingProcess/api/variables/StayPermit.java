package btrust.onboardingProcess.api.variables;

import io.restassured.path.json.JsonPath;

public class StayPermit {
    private static String process;

    private static String expiryDate;

    private static String expirationDate;
    private static String frontImage;
    private static String docType;
    private static String ocrType;
    private static String permitType;
    private static boolean processSuccess;
    private static int count;

    private static  String documentNumber;

    public StayPermit() {
        process = null;
        expiryDate = null;
        expirationDate = null;
        frontImage = null;
        permitType = null;
        docType = null;
        ocrType = null;
        documentNumber = null;

        processSuccess = false;
        count = 0;
    }

    public void setStayPermitVariables(JsonPath jsonPath, String i) {
        String path;
        if (i.equalsIgnoreCase("")) {
            path = "data.results.ocr";
        } else path = "data.resultsList[" + i + "]";


        setProcess(jsonPath.getString(path + ".process"));
        setExpiryDate(jsonPath.getString(path + ".expiryDate"));
        setExpirationDate(jsonPath.getString(path + ".expirationDate"));
        setFrontImage(jsonPath.getString(path + ".frontImage"));
        setDocType(jsonPath.getString(path + ".docType"));
        setOcrType(jsonPath.getString(path + ".ocrType"));
        setPermitType(jsonPath.getString(path + ".permitType"));
        setDocumentNumber(jsonPath.getString(path + ".documentNumber"));
        setProcessSuccess(jsonPath.getBoolean(path + ".success"));

        if (jsonPath.getMap(path).containsKey("count"))
            setCount(jsonPath.getInt(path + ".count"));
    }

//#############################################################################



    public static String getDocumentNumber() {
        return documentNumber;
    }

    public static void setDocumentNumber(String documentNumber) {
        StayPermit.documentNumber = documentNumber;
    }

    public static String getProcess() {
        return process;
    }

    public static void setProcess(String process) {
        StayPermit.process = process;
    }

    public static String getExpiryDate() {
        return expiryDate;
    }

    public static void setExpiryDate(String expiryDate) {
        StayPermit.expiryDate = expiryDate;
    }

    public static String getExpirationDate() {
        return expirationDate;
    }

    public static void setExpirationDate(String expirationDate) {
        StayPermit.expirationDate = expirationDate;
    }


    public static String getFrontImage() {
        return frontImage;
    }

    public static void setFrontImage(String frontImage) {
        StayPermit.frontImage = frontImage;
    }


    public static String getOcrType() {
        return ocrType;
    }

    public static void setOcrType(String ocrType) {
        StayPermit.ocrType = ocrType;
    }

    public static String getDocType() {
        return docType;
    }

    public static void setDocType(String docType) {
        StayPermit.docType = docType;
    }


    public static String getPermitType() {
        return permitType;
    }

    public static void setPermitType(String numberLine) {
        StayPermit.permitType = numberLine;
    }

    public static boolean isProcessSuccess() {
        return processSuccess;
    }

    public static void setProcessSuccess(boolean processSuccess) {
        StayPermit.processSuccess = processSuccess;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        StayPermit.count = count;
    }
}
