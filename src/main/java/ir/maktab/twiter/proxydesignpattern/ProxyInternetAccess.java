package ir.maktab.twiter.proxydesignpattern;

import java.util.*;

public class ProxyInternetAccess implements InternetAccess{

    private static final Set<String> NAMES=new HashSet<>(Arrays.asList("ali","reza","amir"));
    private final String name;
    private final RealInternetAccess realInternetAccess;

    public ProxyInternetAccess(String name){
        realInternetAccess=new RealInternetAccess();
        this.name=name;
    }

    @Override
    public void getInternetAccess() {
        if(NAMES.contains(this.name))
            realInternetAccess.getInternetAccess();
    }

}
