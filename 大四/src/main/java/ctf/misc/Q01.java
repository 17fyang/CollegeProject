package ctf.misc;

import org.bouncycastle.util.encoders.Hex;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/5/19 15:43
 * @Description:
 */
public class Q01 {
    public static void main(String[] args) {
        String s = new String(Hex.decode("400596"));
        System.out.println(s);
    }
}
