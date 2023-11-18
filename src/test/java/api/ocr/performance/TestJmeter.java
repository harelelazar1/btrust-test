package api.ocr.performance;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utilities.TestUtils;

import java.io.IOException;
import utilities.TestUtils;
public class TestJmeter {

    final String OCR_IL_ID_JMETER_JOB_NAME = "scanovate_ocr_il_id";

    @Test()
    public void ocrILIDThreads() throws IOException {
        TestUtils tu=new TestUtils();
        int threadCount=tu.verifyStats(OCR_IL_ID_JMETER_JOB_NAME,90);
        AllureLifecycle lifecycle = Allure.getLifecycle();
        lifecycle.updateTestCase(testResult -> testResult.setName("OCR "+threadCount+" Threads"));

    }


}
