package otherTest;

/**
 * ClassName: LambdaTest
 * Description:娴嬭瘯鍦╨ambda鑳戒笉鑳戒慨鏀瑰闈㈢殑鍐呭
 * 绛旓細涓嶈兘锛屽洜涓�
 * date: 2020/8/14 23:49
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class LambdaTest {
    public static void main(String[] args) {
        int i = 0, j = 2, k = 3;
        realOut((o) -> System.out.println(i + o + j + k));
    }


    public static void realOut(AbstactOut out) {
        out.out(2);
    }
}

interface AbstactOut {
    public void out(int o);
}
