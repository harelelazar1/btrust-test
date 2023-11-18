package utilities;

import java.sql.*;

public class DBUtils {
    static final String DB_URL = "jdbc:mysql://database-1.cumxvbwrjo6j.eu-west-1.rds.amazonaws.com:3306/qadb";
    static final String USER = "admin";
    static final String PASS = "G38230249";


    public boolean resetPassword() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
            String selectQuery = "SELECT is_temp_password from users WHERE company_id = 4 AND email ='harel.elazar+test1@scanovate.com' and status ='active'";
            ResultSet rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                System.out.print("is temp:" + rs.getString("is_temp_password"));
            }
            rs.close();
            String updateQuery = "UPDATE users " +
                    "SET is_temp_password = 1 WHERE email='harel.elazar+test1@scanovate.com'";
            stmt.executeUpdate(updateQuery);
            selectQuery = "SELECT is_temp_password from users WHERE company_id = 4 AND email ='harel.elazar+test1@scanovate.com' and status ='active'";
            rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                System.out.print("is temp:" + rs.getString("is_temp_password"));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePassword() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
            String selectQuery = "SELECT password from users WHERE company_id = 4 AND email ='harel.elazar+test1@scanovate.com' and status ='active'";
            ResultSet rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                System.out.print("password is :" + rs.getString("password"));
            }
            rs.close();
            String updateQuery = "UPDATE users SET password ='44e321a578f25c40d4ff029f4c1e1ed49373530f6bdfebb1:14c96061e5323842353959b1b57bccbbfe98826af523cf88' WHERE email= 'harel.elazar+test1@scanovate.com' AND active = 1";
            stmt.executeUpdate(updateQuery);
            selectQuery = "SELECT password from users WHERE company_id = 4 AND email ='harel.elazar+test1@scanovate.com' and status ='active'";
            rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                System.out.print("password is :" + rs.getString("password"));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getWebHook() {
        ResultSet rs;
        String result = "";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()
        ) {
            String selectQuery = "select value  from company_config cc where `key` ='server.callback.url' and company_id =4";
            rs = stmt.executeQuery(selectQuery);
            if (rs.next()) {
                result = rs.getString("value");
            } else {
                System.out.println("callback config record not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateWebHookUrl(String url) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()
        ) {
            String updateQuery = "update company_config set value= ? where `key` ='server.callback.url' and company_id =4";
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1, url);
            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("Callback URL:" + rowsUpdated + " row(s) updated");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getTemplateNumber() {
        String templateNumber = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()
        ) {
            String selectQuery = "SELECT template  from document_versions WHERE template IS NOT NULL Order BY  creation_date DESC";
            ResultSet rs = stmt.executeQuery(selectQuery);
            if (rs.next()) {
                templateNumber = rs.getString(1);
                System.out.print("template is :" + rs.getString(1));
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return templateNumber;
    }


    public String getCadeID(String sessionID) {
        String caseIdNumber = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()
        ) {
            String selectQuery = "SELECT id FROM inquiries WHERE session_id ='"+sessionID+"' ORDER BY date DESC";
            ResultSet rs = stmt.executeQuery(selectQuery);
            if (rs.next()) {
                caseIdNumber = rs.getString(1);
                System.out.print("caseID is :" + rs.getString(1));
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return caseIdNumber;
    }


    public static void updateMobileConfig(String blob) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//             Statement stmt = conn.createStatement()
        ) {
            String updateQuery = "update company_config set value= ? where `key` ='mobile.interaction.config' and company_id =4";
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1, blob);
            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("Mobile config:" + rowsUpdated + " row(s) updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean updateCallbackHeaders(String headers) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        ) {
            String updateQuery = "update company_config set value= ? where `key` ='callback.headers' and company_id =4";
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1, headers);
            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("Callback headers:" + rowsUpdated + " row(s) updated");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

    public boolean updateRedirectUrl(String redirectUrl) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        ) {
            String updateQuery = "update company_config set value= ? where `key` ='callback.url' and company_id =4";
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1, redirectUrl);
            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("Redirect URL:" + rowsUpdated + " row(s) updated");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

}
