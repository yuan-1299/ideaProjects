public class ProxyDemo {
    public static void consumer(ProxyInterface proxyInterface) {
        proxyInterface.doSomthing();
    }

    public static void main(String[] args) {
        ProxyInterface realObject = new RealClass();
        ProxyInterface proxyObject = new ProxyClass(realObject);

//        consumer(realObject);

        consumer(proxyObject);
    }
}
