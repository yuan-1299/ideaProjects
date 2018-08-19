import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects)  throws Throwable{
        System.out.println("*** proxy: " + o.getClass() +
        ", method: " + method + ", args: " + objects);
        if (objects != null) {
            for (Object obj : objects)
                System.out.println(" " + obj);
        }
        return method.invoke(proxied, objects);
    }
}
