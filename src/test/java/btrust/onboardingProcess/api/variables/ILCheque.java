package btrust.onboardingProcess.api.variables;

import io.restassured.path.json.JsonPath;

public class ILCheque extends EndpointResults {

    private static String process;
    private static String end;
    private static String documentImage;
    private static String chequeNumberLineImage;
    private static int chequeNumber;
    private static int bankBranchNumber;
    private static int bankNumber;
    private static int bankAccountNumber;
    private static String ocrType;
    private static boolean processSuccess;
    private static int count;

    public ILCheque() {
        process = null;
        end = null;
        documentImage = null;
        chequeNumberLineImage = null;
        chequeNumber = 0;
        bankBranchNumber = 0;
        bankNumber = 0;
        bankAccountNumber = 0;
        ocrType = null;
        processSuccess = false;
        count = 0;
    }

    public void setILChequeVariables(JsonPath jsonPath) {
        setILChequeVariables(jsonPath, "");
    }

    public void setILChequeVariables(JsonPath jsonPath, String i) {
        String path;
        if (i.equalsIgnoreCase("")) {
            path = "data.results.cheque";
        } else path = "data.resultsList[" + i + "]";

        setProcess(jsonPath.getString(path + ".process"));
        setEnd(jsonPath.getString(path + ".end"));
        setDocumentImage(jsonPath.getString(path + ".documentImage"));
        setChequeNumberLineImage(jsonPath.getString(path + ".chequeNumberLineImage"));
        setChequeNumber(jsonPath.getInt(path + ".chequeNumber"));
        setBankBranchNumber(jsonPath.getInt(path + ".bankBranchNumber"));
        setBankNumber(jsonPath.getInt(path + ".bankNumber"));
        setBankAccountNumber(jsonPath.getInt(path + ".bankAccountNumber"));
        setOcrType(jsonPath.getString(path + ".ocrType"));
        setProcessSuccess(jsonPath.getBoolean(path + ".success"));
        setCount(jsonPath.getInt(path + ".count"));
    }

    //#############################################################################

    public static String getProcess() {
        return process;
    }

    public static void setProcess(String process) {
        ILCheque.process = process;
    }

    public static String getEnd() {
        return end;
    }

    public static void setEnd(String end) {
        ILCheque.end = end;
    }

    public static String getDocumentImage() {
        return documentImage;
    }

    public static void setDocumentImage(String documentImage) {
        ILCheque.documentImage = documentImage;
    }

    public static String getChequeNumberLineImage() {
        return chequeNumberLineImage;
    }

    public static void setChequeNumberLineImage(String chequeNumberLineImage) {
        ILCheque.chequeNumberLineImage = chequeNumberLineImage;
    }

    public static int getChequeNumber() {
        return chequeNumber;
    }

    public static void setChequeNumber(int chequeNumber) {
        ILCheque.chequeNumber = chequeNumber;
    }

    public static int getBankBranchNumber() {
        return bankBranchNumber;
    }

    public static void setBankBranchNumber(int bankBranchNumber) {
        ILCheque.bankBranchNumber = bankBranchNumber;
    }

    public static int getBankNumber() {
        return bankNumber;
    }

    public static void setBankNumber(int bankNumber) {
        ILCheque.bankNumber = bankNumber;
    }

    public static int getBankAccountNumber() {
        return bankAccountNumber;
    }

    public static void setBankAccountNumber(int bankAccountNumber) {
        ILCheque.bankAccountNumber = bankAccountNumber;
    }

    public static String getOcrType() {
        return ocrType;
    }

    public static void setOcrType(String ocrType) {
        ILCheque.ocrType = ocrType;
    }

    public static boolean isProcessSuccess() {
        return processSuccess;
    }

    public static void setProcessSuccess(boolean processSuccess) {
        ILCheque.processSuccess = processSuccess;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        ILCheque.count = count;
    }
}
