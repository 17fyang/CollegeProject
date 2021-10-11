package com.stu.yf;

import java.io.File;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/10/12 0:24
 * @Description:
 */
public class PathUtils {
    private static String ROOT = PathUtils.class.getClassLoader().getResource("").getPath();

    static {
        System.out.println(ROOT);
    }

    public static File get(String path) {
        System.out.println(new File(ROOT + path).getAbsolutePath());
        return new File(ROOT + path);
    }
}
