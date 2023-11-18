package utilities.MongoDB;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.MongoDB.Variables.CommonVariables;

public class genericUtil {
    public void checkDb(){
        MongoHandler mongoHandler = new MongoHandler();
        mongoHandler.queryHandler("09-11-2022_08-59-09_1836157888_liveness", "failure");
        System.out.println(CommonVariables.getStatusReason());
//        Assert.assertEquals(CommonVariables.getStatusReason(),"Image injection attempt detected");
    }
}
