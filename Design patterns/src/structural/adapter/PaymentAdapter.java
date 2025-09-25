package structural.adapter;

public class PaymentAdapter implements NewPaymentGateway {
    private final OldPaymentProcessor oldProcessor = new OldPaymentProcessor();
    @Override
    public void payAmount(double amount) {
        oldProcessor.makePayment(amount);
    }
}
