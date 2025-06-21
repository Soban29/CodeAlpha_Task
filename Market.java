import java.util.*;

public class Market {
    private Map<String, Stock> stocks = new HashMap<>();

    public Market() {
        // Simulated stocks
        stocks.put("AAPL", new Stock("AAPL", 150.0));
        stocks.put("GOOG", new Stock("GOOG", 2800.0));
        stocks.put("TSLA", new Stock("TSLA", 700.0));
    }

    public Collection<Stock> getAllStocks() {
        return stocks.values();
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol);
    }

    public void displayMarketData() {
        System.out.println("Available Stocks:");
        for (Stock s : stocks.values()) {
            System.out.printf("%s - $%.2f%n", s.getSymbol(), s.getPrice());
        }
    }
}

