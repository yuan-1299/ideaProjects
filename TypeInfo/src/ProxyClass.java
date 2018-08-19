public class ProxyClass implements ProxyInterface{
    private ProxyInterface proxyInterface;

    public ProxyClass(ProxyInterface proxyInterface) {
        this.proxyInterface = proxyInterface;
    }

    @Override
    public void doSomthing() {
        System.out.println("Proxy Class did something");
        proxyInterface.doSomthing();
    }
}
