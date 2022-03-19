package ir.maktab.twiter.addapterpattern;

public class Main {
    public static void main(String[] args) {
        BankCustomer bankCustomer=new BankCustomer("meli","123");
        bankCustomer.bankDetails();
        System.out.println(bankCustomer.creditCart());
    }
}
