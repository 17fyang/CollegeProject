package otherTest;

/**
 * ClassName: LambdaTest
 * Description:测试在lambda能不能修改外面的内容
 * 答：不能，因为
 * date: 2020/8/14 23:49
 *
 * @author :乌鸦坐飞机亠
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
