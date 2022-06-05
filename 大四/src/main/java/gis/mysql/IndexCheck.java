package gis.mysql;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.WKTWriter;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/11/10 0:14
 * @Description:
 */
public class IndexCheck {
    public static void main(String[] args) {
        GeometryFactory factory = new GeometryFactory();
        WKTWriter writer = new WKTWriter();

        Coordinate[] coordinates = new Coordinate[]{
                new Coordinate(12.0, 13.0),
                new Coordinate(12.0, 114.0),
                new Coordinate(113.0, 114.0),
                new Coordinate(113.0, 13.0),
                new Coordinate(12.0, 13.0)
        };

        System.out.println(writer.write(factory.createPolygon(coordinates)));
    }
}
