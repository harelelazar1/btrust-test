package api.ocr.philippinesCheque;

import api.Variables;
import api.ApiResponse;
import org.testng.annotations.BeforeMethod;
import utilities.MongoDB.MongoHandler;
import utilities.TestUtils;
public class Functionality extends ApiResponse {

    Variables variables;
    ApiResponse apiResponse;
    MongoHandler mongoHandler;

    @BeforeMethod
    public void startSessionAndInitToken() {
        variables  = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "PHL-CHEQUES", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
    }


}
