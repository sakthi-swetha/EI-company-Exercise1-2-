package behavioral.observer;

public class ConcreteInvestor implements Investor {
    private String name;
    public ConcreteInvestor(String name) { this.name = name; }
    @Override
    public void update(Stock stock) {
        System.out.println("Notification for " + name +
            ": Stock " + stock.getSymbol() + " is now $" + stock.getPrice());
    }
}
