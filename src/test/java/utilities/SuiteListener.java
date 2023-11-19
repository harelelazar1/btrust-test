package utilities;

import btrust.onboardingProcess.api.ApiRequests;
import org.junit.runners.Suite;
import org.testng.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;



public class SuiteListener implements ISuiteListener {
    ApiRequests apiRequests;
    String requestId = "H_OunMYopHu3A7w-LieRQ82ROldq3fPRhRvpK_WaVNw=";


    @Override
    public void onStart(ISuite suite) {
        apiRequests = new ApiRequests();
        String accessToken;
        System.out.println("start suite:" + suite.getName());
        loadProperties(suite);
        accessToken = generateToken();
        System.setProperty("ACCESS_TOKEN", "Bearer " + accessToken);
//        TestUtils tu = new TestUtils();
//                if (System.getProperty("NEW_BTRUST_API").equalsIgnoreCase("false")) {
//            try {
//                tu.getSwaggerDef(System.getProperty("OLD_CORE_API_SWAGGER_DOC"));
//            } catch (MalformedURLException e) {
//                throw new RuntimeException(e);
//            }
//        } else {
//            try {
//                tu.getSwaggerDef(System.getProperty("NEW_CORE_API_SWAGGER_DOC"));
//            } catch (MalformedURLException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    private String generateToken() {
        String accessToken = null;
        String userName;
        String password;

        try {
            userName = System.getProperty("LOGIN_USER");
            password = System.getProperty("LOGIN_PASSWORD");
            accessToken = apiRequests.loginToNewSecurityBtrustPage(userName, password, requestId);
        } catch (Exception e) {
            System.out.println("exception during access token");
        }
        return accessToken;
    }

//    private String generateTokenForClientApi() {
//        String accessToken = null;
//        String client_id;
//        String client_secret;
//
//        try {
//            client_id = System.getProperty("CLIENT_ID");
//            client_secret = System.getProperty("CLIENT_SECRET");
//            accessToken = apiRequests.createTokenClientApi(client_id, client_secret);
//        } catch (Exception e) {
//            System.out.println("exception during access token");
//        }
//        return accessToken;
//    }

    private static void loadProperties(ISuite suite) {
        if (suite.getXmlSuite().getTests().size() > 0) {
            TestUtils tu = new TestUtils();
            try {
                tu.loadProperties();
                Properties newProps = new Properties();
                Enumeration<?> names = System.getProperties().propertyNames();
                List<String> values = Arrays.asList(".PATH", "_KEY", "_SECRET", "_PASSWORD");
                while (names.hasMoreElements()) {
                    boolean addProp = true;
                    String sysPropName = (String) names.nextElement();
                    for (String value : values) {
                        if (sysPropName.toUpperCase().contains(value.toUpperCase())) {
                            addProp = false;
                            break;
                        }
                    }
                    if (addProp) {
                        newProps.put(sysPropName, System.getProperty(sysPropName));
                    }
                }
                tu.saveProperties(newProps);
            } catch (IOException e) {
                System.out.println("Suite listener:properties loading failed");
            }
            if (System.getProperty("RESET_MOBILE_CONFIG", "").equals("true")) {
                ApiRequests ar = new ApiRequests();
                try {
                    ar.clientTranslationButtonSet(true);
                } catch (Exception e) {

                }

            }
        }
    }

    @Override
    public void onFinish(ISuite suite) {
    }

}