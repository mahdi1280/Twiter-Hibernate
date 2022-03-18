package ir.maktab.twiter;

import ir.maktab.twiter.frame.LoginFrame;
import ir.maktab.twiter.session.MySessionFactory;

public class ApplicationMain {
    public static void main(String[] args) {
        MySessionFactory.openSession();
        LoginFrame loginFrame=new LoginFrame();
    }
}
