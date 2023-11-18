package selenium.ocr.test.newServer.temp;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class SanityTest{ //extends BaseTest {

    Variables variables = new Variables();

    @Test(description = "tmp")
    @Description("Run Demo End to End test of bio Id")
    public void t02_tmp(){
        String x=null;
        Properties props= System.getProperties();
//        if (System.getProperty("ddd").equals("ff")){
//            Assert.fail("");
//        }
    }
}
