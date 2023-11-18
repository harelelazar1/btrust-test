package api.liveness.newVersion.mathilda.mathildaTests;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.Liveness.SttVariables;

import java.io.File;
import java.io.IOException;

public class SttThresholdViaMathilda {

    ApiResponse apiResponse = new ApiResponse();
    Variables variables = new Variables();
    int iterationCounter = 1;
    double sttThresholdFromConfigFile = 0.666;
    MongoHandler mongoHandler = new MongoHandler();

}
