package structural.adapter;

public class OldPaymentProcessor {
    public void makePayment(double amount) {
        System.out.println("Processed payment of $" + amount + " via Old System");
    }
}
