package btrust.onboardingProcess.api.variables;

import io.restassured.path.json.JsonPath;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.PropertyAccessor;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

public class CardCapture extends EndpointResults {

    private static String process;
    private static String image;
    private static boolean processSuccess;

    private static String dob;
    private static String issuingDate;
    private static String expiryDate;
    private static String firstName;
    private static String lastName;
    private static String address;
    private static String documentNumber;
    private static String placeOfBirth;

    public CardCapture() {
        process = null;
        image = null;
        processSuccess = false;
    }

    public static String getExpiryDate() {
        return expiryDate;
    }

    public static void setExpiryDate(String expiryDate) {
        CardCapture.expiryDate = expiryDate;
    }

    public static String getDob() {
        return dob;
    }

    public static void setDob(String dob) {
        CardCapture.dob = dob;
    }

    public static String getIssuingDate() {
        return issuingDate;
    }

    public static void setIssuingDate(String issuingDate) {
        CardCapture.issuingDate = issuingDate;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        CardCapture.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        CardCapture.lastName = lastName;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        CardCapture.address = address;
    }

    public static String getDocumentNumber() {
        return documentNumber;
    }

    public static void setDocumentNumber(String documentNumber) {
        CardCapture.documentNumber = documentNumber;
    }

    public static String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public static void setPlaceOfBirth(String placeOfBirth) {
        CardCapture.placeOfBirth = placeOfBirth;
    }

    public void setCardCaptureVariables(JsonPath jsonPath) {
        setCardCaptureVariables(jsonPath, "");
    }

    public void setCardCaptureVariables(JsonPath jsonPath, String i) {
        String path;
        if (i.equalsIgnoreCase("")) {
            path = "data.results.cardCapture";
        } else path = "data.resultsList[" + i + "]";

        setProcess(jsonPath.getString(path + ".process"));
        setImage(jsonPath.getString(path + ".image"));
        setProcessSuccess(jsonPath.getBoolean(path + ".success"));
    }

    public void setCardCaptureVariables(Map<String, String> map) {
        Iterator iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            Object value =  map.get(key);
            setFieldFromJson(key, value);
        }
    }

    private void setFieldFromJson(String fieldName, Object value) {
        try{
            if (doesObjectContainField(this, fieldName)) {
                PropertyAccessor myAccessor = PropertyAccessorFactory.forDirectFieldAccess(this);
                myAccessor.setPropertyValue(fieldName, value);
            } else {
                System.out.println("Card capture set variables:field " + fieldName + " does not exist in " + this.getClass().getName());
            }}
        catch (Exception e){
            System.out.println("Card capture set variables:field " + fieldName + "error:"+ e.getMessage());
        }
    }

    private boolean doesObjectContainField(Object object, String fieldName) {
        Class<?> objectClass = object.getClass();
        for (Field field : objectClass.getDeclaredFields()) {
            if (field.getName().equals(fieldName)) {
                return true;
            }
        }
        return false;
    }
    //#############################################################################

    public static String getProcess() {
        return process;
    }

    public static void setProcess(String process) {
        CardCapture.process = process;
    }

    public static String getImage() {
        return image;
    }

    public static void setImage(String image) {
        CardCapture.image = image;
    }

    public static boolean isProcessSuccess() {
        return processSuccess;
    }

    public static void setProcessSuccess(boolean processSuccess) {
        CardCapture.processSuccess = processSuccess;
    }

}
