package yo.askText;

import util.JdbcUtil;
import yo.domain.ask_text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ORM
 * Description:
 * date: 2020/10/3 23:02
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class ORM {
    private static ORM instance = new ORM();
    public static String TABLE = "ask_text_sh";

    public static ORM getInstance() {
        return instance;
    }


    public void insert(ask_text domain) throws SQLException {
        String sql = "insert into " + TABLE + " (id,page,company,title,http_url,net_time) values (%s,%s,'%s','%s','%s','%s')";
        sql = String.format(sql, domain.getId(), domain.getPage(), domain.getCompany(), domain.getTitle(), domain.getHttp_url(), domain.getNet_time());
        Connection conn = null;
        Statement st = null;
        try {
            conn = JdbcUtil.getConnection();
            st = conn.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            throw e;
        } finally {
            JdbcUtil.close(null, st, conn);
        }
    }

    public ask_text selectOne(int id) {
        String sql = "select id, page, company, title, http_url, net_time, download_flag from " + TABLE + " where id = %s";
        sql = String.format(sql, id);
        try {
            Connection conn = JdbcUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            ask_text domain = packOne(rs);
            JdbcUtil.close(rs, st, conn);
            return domain;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ask_text> selectAll() {
        String sql = "select id, page, company, title, http_url, net_time, download_flag from " + TABLE;
        try {
            Connection conn = JdbcUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<ask_text> list = new ArrayList<>();
            while (rs.next()) {
                list.add(packOne(rs));
            }
            JdbcUtil.close(rs, st, conn);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ask_text packOne(ResultSet rs) throws SQLException {
        ask_text domain = new ask_text();
        domain.setId(rs.getInt(1));
        domain.setPage(rs.getInt(2));
        domain.setCompany(rs.getString(3));
        domain.setTitle(rs.getString(4));
        domain.setHttp_url(rs.getString(5));
        domain.setNet_time(rs.getString(6));
        domain.setDownloadFlag(rs.getInt(7));
        return domain;
    }

    public void flagDownload(int id) {
        String sql = "update " + TABLE + " set download_flag = 1 where id = %s";
        sql = String.format(sql, id);
        try {
            Connection conn = JdbcUtil.getConnection();
            Statement st = conn.createStatement();
            st.execute(sql);
            JdbcUtil.close(null, st, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
