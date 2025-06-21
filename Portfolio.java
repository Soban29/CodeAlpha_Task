import java.util.*;

public class Portfolio {
    private double balance;
    private Map<String, Integer> holdings = new HashMap<>();

    public Portfolio(double startingBalance) {
        this.balance = startingBalance;
    }

    public boolean buyStock(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;
        if (cost <= balance) {
            balance -= cost;
            holdings.put(stock.getSymbol(), holdings.getOrDefault(stock.getSymbol(), 0) + quantity);
            return true;
        }
        return false;
    }

    public boolean sellStock(Stock stock, int quantity) {
        String symbol = stock.getSymbol();
        int owned = holdings.getOrDefault(symbol, 0);
        if (owned >= quantity) {
            holdings.put(symbol, owned - quantity);
            balance += stock.getPrice() * quantity;
            return true;
        }
        return false;
    }

    public void displayPortfolio(Market market) {
        System.out.printf("Cash Balance: $%.2f%n", balance);
        System.out.println("Holdings:");
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            Stock stock = market.getStock(entry.getKey());
            System.out.printf("%s - %d shares @ $%.2f (Value: $%.2f)%n",
                    entry.getKey(), entry.getValue(), stock.getPrice(), stock.getPrice() * entry.getValue());
        }
    }

    public double getTotalValue(Market market) {
        double total = balance;
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            Stock stock = market.getStock(entry.getKey());
            total += stock.getPrice() * entry.getValue();
        }
        return total;
    }
}

