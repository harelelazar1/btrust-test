package api.liveness.performance;

import org.testng.annotations.Test;
import utilities.TestUtils;

import java.io.IOException;

public class TestJmeter {

    final String LIVENESS_PASSIVE_JOB_NAME = "scanovate_liveness_passive";

    @Test()
    public void livenessPassiveThreads() throws IOException {
        TestUtils tu=new TestUtils();
        tu.verifyStats(LIVENESS_PASSIVE_JOB_NAME,90);
    }

}
