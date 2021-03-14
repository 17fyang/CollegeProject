package yo.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * ClassName: DBUtil
 * Description:
 * date: 2020/10/3 23:07
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class DBUtil {
    private static DBUtil instance = new DBUtil();

    public static DBUtil getInstance() {
        return instance;
    }

    public void close(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            System.out.println("error!!! 鍏抽棴璧勬簮澶辫触锛�");
            e.printStackTrace();
        }
    }
}
