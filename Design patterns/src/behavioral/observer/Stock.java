package behavioral.observer;
import java.util.ArrayList;
import java.util.List;
public class Stock {
	private String symbol;
    private double price;
    private final List<Investor> investors = new ArrayList<>();

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public void attach(Investor investor) {
        investors.add(investor);
    }

    public void detach(Investor investor) {
        investors.remove(investor);
    }

    public void setPrice(double price) {
        this.price = price;
        notifyInvestors();
    }

    private void notifyInvestors() {
        for (Investor investor : investors) {
            investor.update(this);
        }
    }

    public String getSymbol() { return symbol; }
    public double getPrice() { return price; }

}
