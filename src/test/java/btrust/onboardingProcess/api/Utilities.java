package btrust.onboardingProcess.api;

import btrust.onboardingProcess.api.variables.SingleFrame;
import btrust.onboardingProcess.api.variables.*;
import io.restassured.path.json.JsonPath;

import java.util.Map;

public class Utilities {

    ApiResponses apiResponses;

    public Utilities() {
        apiResponses = new ApiResponses();
    }

    static ILID ilid = new ILID();

    public void endpointManager(PreProcess preProcess, EndpointResults endpointResults, String endpointType) {
        switch (endpointType.toLowerCase()) {
            case "v1": {
                apiResponses.getV1Result(preProcess, endpointResults);
                break;
            }
            case "v2": {
                apiResponses.getV2Result(preProcess, endpointResults);
                break;
            }
            case "harel": {
                apiResponses.getHarelResult(preProcess, endpointResults);
                break;
            }
            default: {
                System.out.println("No such endpoint exist.");
            }
        }
    }

    public static void processHandlerV1AndHarel(JsonPath jsonPath) {
        Map<String, String> map = jsonPath.getMap("data.results");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            switch (entry.getKey()) {
                case "ocr": {
                    if (jsonPath.getMap("data.results.ocr").containsKey("ocrType")) {
                        switch (jsonPath.getString("data.results.ocr.ocrType")) {
                            case "IL-ID": {
                                ILID ilid = new ILID();
                                ilid.setILIDVariables(jsonPath);
                                break;
                            }
                            case "IL-DL": {
                                ILDL ildl = new ILDL();
                                ildl.setILDLVariables(jsonPath);
                                break;
                            }
                            case "MRZ": {
                                MRZ mrz = new MRZ();
                                mrz.setMRZVariables(jsonPath);
                            }
                            case "driver_license_DL": {
                                DKDL dkdl = new DKDL();
                                dkdl.setDKDLVariables(jsonPath);
                            }
                        }
                    }
                    break;
                }
                case "liveness": {
                    Liveness liveness = new Liveness();
                    liveness.setLivenessVariables(jsonPath);
                    break;
                }
                case "face": {
                    BiometricMatch biometricMatch = new BiometricMatch();
                    biometricMatch.setBiometricMatchVariables(jsonPath);
                    break;
                }
                case "cheque": {
                    ILCheque ilCheque = new ILCheque();
                    ilCheque.setILChequeVariables(jsonPath);
                    break;
                }
                case "questionnaire": {
                    Questionnaire questionnaire = new Questionnaire();
                    questionnaire.setQuestionnaireVariables(jsonPath);
                    break;
                }
                case "cardCapture": {
                    CardCapture cardCapture = new CardCapture();
                    cardCapture.setCardCaptureVariables(jsonPath);
                    break;
                }
            }
        }
    }

    public static void processHandlerV2(JsonPath jsonPath) {
        String lastProcess = null;
        for (int i = 0; i < jsonPath.getList("data.resultsList").size(); i++) {
            if (jsonPath.getString("data.resultsList[" + i + "].process")!=null) {
                switch (jsonPath.getString("data.resultsList[" + i + "].process")) {
                    case "liveness": {
                        Liveness liveness = new Liveness();
                        liveness.setLivenessVariables(jsonPath, String.valueOf(i));
                        break;
                    }
                    case "ocr": {
                        if (jsonPath.getMap("data.resultsList[" + i + "]").containsKey("ocrType")) {
                            switch (jsonPath.getString("data.resultsList[" + i + "].ocrType")) {
                                case "IL-ID": {
                                    ilid.setILIDVariables(jsonPath, String.valueOf(i));
                                    break;
                                }
                                case "IL-DL": {
                                    ILDL ildl = new ILDL();
                                    ildl.setILDLVariables(jsonPath, String.valueOf(i));
                                    break;
                                }
                                case "cheque_document": {
                                    ILCheque ilCheque = new ILCheque();
                                    ilCheque.setILChequeVariables(jsonPath, String.valueOf(i));
                                    break;
                                }
                                case "MRZ": {
                                    MRZ mrz = new MRZ();
                                    mrz.setMRZVariables(jsonPath, String.valueOf(i));
                                    break;
                                }
                                case "driver_license_DL": {
                                    DKDL dkdl = new DKDL();
                                    dkdl.setDKDLVariables(jsonPath, String.valueOf(i));
                                    break;
                                }
                                case "C-CARD": {
                                    CreditCard creditCard = new CreditCard();
                                    creditCard.setCreditCardVariables(jsonPath, String.valueOf(i));
                                }
                                case "IL-STP": {
                                    StayPermit stayPermit = new StayPermit();
                                    stayPermit.setStayPermitVariables(jsonPath, String.valueOf(i));
                                }

                                default: {
                                    System.out.println("No ocrType field.");
                                    break;
                                }
                            }

                        }
                        // Regula OCR
                        if(lastProcess != null){
                            if (lastProcess.equalsIgnoreCase("card_capture")) {
                                CardCapture cardCapture = new CardCapture();
                                cardCapture.setCardCaptureVariables(jsonPath.getMap("data.resultsList[" + i + "]"));
                            }
                        }
                        break;
                    }
                    case "biometric_match": {
                        BiometricMatch biometricMatch = new BiometricMatch();
                        biometricMatch.setBiometricMatchVariables(jsonPath, String.valueOf(i));
                        break;
                    }
                    case "questionnaire": {
                        Questionnaire questionnaire = new Questionnaire();
                        questionnaire.setQuestionnaireVariables(jsonPath, String.valueOf(i));
                        break;
                    }
                    case "single_frame": {
                        SingleFrame singleFrame = new SingleFrame();
                        singleFrame.setSingleFrameVariables(jsonPath, i);
                        break;
                    }
                    case "card_capture": {
                        CardCapture cardCapture = new CardCapture();
                        cardCapture.setCardCaptureVariables(jsonPath, String.valueOf(i));
                        break;
                    }
                }
                lastProcess=jsonPath.getString("data.resultsList[" + i + "].process");
            }
            if (jsonPath.getString("data.resultsList[" + i + "]").contains("equals")) {
                ilid.setVariablesOfComparator(jsonPath, String.valueOf(i));
                break;
            }

            if (jsonPath.getString("data.resultsList[" + i + "].service")!=null) {
                System.out.println("ocr failed");
                break;
            }

        }
    }
}


