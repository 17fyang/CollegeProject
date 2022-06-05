package test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author crowOnThePlane
 * @version 1.0
 * @date 2022/4/24 23:13
 */
public class ReflectTest {
    static class Model {
        private static Integer i = 0;
        private Long j = 0L;
        private String k = "";
        private List<String> list = new ArrayList<>();
    }

    public static void main(String[] args) throws IllegalAccessException {
        Model m = new Model();
        Field[] fields = m.getClass().getDeclaredFields();
        System.out.println(Arrays.toString(fields));

        for (Field f : fields) {
            f.setAccessible(true);
            f.set(m, null);
        }
        System.out.println(Arrays.toString(fields));
    }
}


