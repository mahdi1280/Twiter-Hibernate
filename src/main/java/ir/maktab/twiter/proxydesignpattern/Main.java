package ir.maktab.twiter.proxydesignpattern;

public class Main {
    public static void main(String[] args) {
        ProxyInternetAccess proxyInternetAccess=new ProxyInternetAccess("ali");
        proxyInternetAccess.getInternetAccess();
    }
}
