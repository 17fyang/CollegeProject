package otherTest;

/**
 * ClassName: InnerClassTest
 * Description:内部类和外部类概念测试
 * date: 2020/8/14 23:42
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class InnerClassTest {
    public static void main(String[] args) {
        OutClass.InnerClass inner = new OutClass.InnerClass();
    }
}

class OutClass {
    private static int i = 0;

    static class InnerClass {
        public int getI() {
            return i;
        }
    }
}
