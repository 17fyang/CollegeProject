package otherTest;

/**
 * ClassName: InnerClassTest
 * Description:鍐呴儴绫诲拰澶栭儴绫绘蹇垫祴璇�
 * date: 2020/8/14 23:42
 *
 * @author :涔岄甫鍧愰鏈轰籂
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
