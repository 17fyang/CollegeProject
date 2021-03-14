package cloneTest;

import java.math.BigDecimal;

/**
 * ClassName: test
 * Description:
 * date: 2020/7/11 12:23
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class test {
    public static void main(String[] args) {
        String q="0.99178";
        String a="1117.303";
        BigDecimal total=new BigDecimal(a);
        BigDecimal bigA=new BigDecimal(a);
        BigDecimal bigQ=new BigDecimal(q);
        for(int i=0;i<50000;i++){
            bigA=bigA.multiply(bigQ);
            total=total.add(bigA);
        }
        System.out.println(bigA);
        System.out.println(total.toString());
    }
}
