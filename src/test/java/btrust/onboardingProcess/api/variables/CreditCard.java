package btrust.onboardingProcess.api.variables;

import io.restassured.path.json.JsonPath;

public class CreditCard extends EndpointResults {


    private static String process;
    private static String end;
    private static String expiryDate;

    private static String expirationDate;
    private static String frontImage;
    private static String cardImage;
    private static String docType;
    private static String ocrType;
    private static String numberLine;
    private static boolean processSuccess;
    private static int count;

    public CreditCard() {
        process = null;
        end = null;
        expiryDate = null;
        expirationDate = null;
        frontImage = null;
        cardImage = null;
        numberLine = null;
        docType = null;
        ocrType = null;

        processSuccess = false;
        count = 0;
    }

    public void setCreditCardVariables(JsonPath jsonPath, String i) {
        String path;
        if (i.equalsIgnoreCase("")) {
            path = "data.results.ocr";
        } else path = "data.resultsList[" + i + "]";


        setEnd(jsonPath.getString(path + ".end"));
        setProcess(jsonPath.getString(path + ".process"));
        setExpiryDate(jsonPath.getString(path + ".expiryDate"));
        setExpirationDate(jsonPath.getString(path + ".expirationDate"));
        setFrontImage(jsonPath.getString(path + ".frontImage"));
        setCardImage(jsonPath.getString(path + ".cardImage"));
        setDocType(jsonPath.getString(path + ".docType"));
        setOcrType(jsonPath.getString(path + ".ocrType"));
        setNumberLine(jsonPath.getString(path + ".numberLine"));
        setProcessSuccess(jsonPath.getBoolean(path + ".success"));
        if (jsonPath.getMap(path).containsKey("count"))
            setCount(jsonPath.getInt(path + ".count"));
    }

//#############################################################################

    public static String getEnd() {
        return end;
    }

    public static void setEnd(String end) {
        CreditCard.end = end;
    }

    public static String getProcess() {
        return process;
    }

    public static void setProcess(String process) {
        CreditCard.process = process;
    }

    public static String getExpiryDate() {
        return expiryDate;
    }

    public static void setExpiryDate(String expiryDate) {
        CreditCard.expiryDate = expiryDate;
    }

    public static String getExpirationDate() {
        return expirationDate;
    }

    public static void setExpirationDate(String expirationDate) {
        CreditCard.expirationDate = expirationDate;
    }


    public static String getFrontImage() {
        return frontImage;
    }

    public static void setFrontImage(String frontImage) {
        CreditCard.frontImage = frontImage;
    }

    public static String getCardImage() {
        return cardImage;
    }

    public static void setCardImage(String cardImage) {
        CreditCard.cardImage = cardImage;
    }


    public static String getOcrType() {
        return ocrType;
    }

    public static void setOcrType(String ocrType) {
        CreditCard.ocrType = ocrType;
    }

    public static String getDocType() {
        return docType;
    }

    public static void setDocType(String docType) {
        CreditCard.docType = docType;
    }


    public static String getNumberLine() {
        return numberLine;
    }

    public static void setNumberLine(String numberLine) {
        CreditCard.numberLine = numberLine;
    }

    public static boolean isProcessSuccess() {
        return processSuccess;
    }

    public static void setProcessSuccess(boolean processSuccess) {
        CreditCard.processSuccess = processSuccess;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        CreditCard.count = count;
    }


}
