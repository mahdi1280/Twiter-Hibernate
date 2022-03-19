package ir.maktab.twiter.addapterpattern;

public class BankCustomer extends Bank implements CreditCard {

    public BankCustomer(String name, String accountNumber) {
        super(name, accountNumber);
    }

    @Override
    public void bankDetails() {
        System.out.println("name: "+super.getName());
        System.out.println("account number: "+super.getAccountNumber());
    }

    @Override
    public String creditCart() {
        return super.getAccountNumber();
    }
}
