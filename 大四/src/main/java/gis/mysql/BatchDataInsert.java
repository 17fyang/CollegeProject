package gis.mysql;

import cn.hutool.core.util.RandomUtil;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.WKTWriter;
import util.JdbcUtil;
import util.StringUtils;

import java.sql.Statement;
import java.util.Random;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/11/9 23:43
 * @Description:
 */
public class BatchDataInsert {
    public static void main(String[] args) throws Exception {
        Statement statement = JdbcUtil.getConnection().createStatement();

        String template = "insert into test_polygon (name,location) values %s";
        String single = "('%s',ST_PolygonFromText('%s'))";

        int i = 87;

        while (i-- > 0) {
            StringBuilder sb = new StringBuilder();

            int n = 1000;
            while (n-- > 0) {
                Random random = new Random();
                String name = StringUtils.randomChinese(Math.abs(random.nextInt()) % 8 + 1);

                String wkt = randomWkt();
                String singleSql = String.format(single, name, wkt);

                if (sb.length() != 0) {
                    sb.append(",");
                }
                sb.append(singleSql);
            }
            String sql = String.format(template, sb.toString());
//            System.out.println(sql);
            statement.execute(sql);
        }

        JdbcUtil.close(null, statement, null);
    }


    private static String randomWkt() {
        GeometryFactory factory = new GeometryFactory();
        WKTWriter writer = new WKTWriter();
        Coordinate start = new Coordinate(RandomUtil.randomDouble(0.0, 10000.0), RandomUtil.randomDouble(0.0, 10000.0));
        double height = RandomUtil.randomDouble(0.0, 100.0);
        double weight = RandomUtil.randomDouble(0.0, 100.0);
        Coordinate[] coordinates = new Coordinate[]{
                start,
                new Coordinate(start.x, start.y + height),
                new Coordinate(start.x + weight, start.y + height),
                new Coordinate(start.x + weight, start.y),
                start
        };

        return writer.write(factory.createPolygon(coordinates));
    }
}
