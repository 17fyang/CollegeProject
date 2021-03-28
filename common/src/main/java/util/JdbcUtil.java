package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/17 15:38
 * @Description:
 */
public class JdbcUtil {
    private static DataSource ds;

    static {
        Properties pro = new Properties();
        try {
            pro.load(JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("get data source connection fail!");
        }
    }

    public static void close(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null) {
                        try {
                            conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
