package yo.askText_sh;

import util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ClassName: fillDemo
 * Description:鏌ユ壘鍑虹己澶辩殑椤�
 * date: 2020/10/5 23:46
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class fillDemo {
    public static void main(String[] args) throws SQLException {
        String sql = "select id,page from ask_text_sh";
        int[] arr = new int[1797];
        Statement st = JdbcUtil.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt(1);
            int page = rs.getInt(2);
            if ((id - 1) / 10 + 1 != page) System.out.println(id);
        }
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] != 10) System.out.println((i + 1) + " " + arr[i]);
//        }
    }
}
