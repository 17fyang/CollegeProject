package yo.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * ClassName: DBUtil
 * Description:
 * date: 2020/10/3 23:07
 *
 * @author :乌鸦坐飞机亠
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
            System.out.println("error!!! 关闭资源失败！");
            e.printStackTrace();
        }
    }
}
