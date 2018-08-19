import java.lang.reflect.Proxy;

public class SimpleDynamicProxy {
    public static void consumer(ProxyInterface proxyInterface) {
        proxyInterface.doSomthing();
    }

    public static void main(String[] args) {
        RealClass readObject = new RealClass();
        consumer(readObject);

        ProxyInterface proxy = (ProxyInterface)Proxy.newProxyInstance(ProxyInterface.class.getClassLoader(),
                new Class[]{ProxyInterface.class},
                new DynamicProxyHandler(readObject));
        consumer(proxy);
    }
}
