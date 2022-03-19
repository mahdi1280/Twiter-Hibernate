package ir.maktab.twiter.proxydesignpattern;

public class RealInternetAccess implements InternetAccess {
    @Override
    public void getInternetAccess() {
        System.out.println("internet access");
    }
}
