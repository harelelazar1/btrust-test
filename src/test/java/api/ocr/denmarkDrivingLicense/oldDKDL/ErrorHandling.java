package api.ocr.denmarkDrivingLicense.oldDKDL;

import api.Variables;
import api.ApiResponse;
import org.testng.annotations.BeforeMethod;
import utilities.MongoDB.MongoHandler;
import utilities.TestUtils;
public class ErrorHandling extends ApiResponse {

    ApiResponse apiResponse;
    Variables variables;
    MongoHandler mongoHandler;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "DK-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
    }


}
