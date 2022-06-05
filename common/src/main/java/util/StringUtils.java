package util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/11/9 23:46
 * @Description:
 */
public class StringUtils {
    

    public static String randomChinese(int len) {
        return RandomStringUtils.random(len, 0x4e00, 0x9fa5, false, false);
    }
}
