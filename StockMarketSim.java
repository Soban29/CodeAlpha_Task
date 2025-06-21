import java.util.Scanner;
public class StockMarketSim {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Market market = new Market();
        User user = new User("Trader", 10000.0);

        while (true) {
            System.out.println("\n--- Stock Trading Simulator ---");
            System.out.println("1. View Market");
            System.out.println("2. View Portfolio");
            System.out.println("3. Buy Stock");
            System.out.println("4. Sell Stock");
            System.out.println("5. View Total Value");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    market.displayMarketData();
                    break;
                case 2:
                    user.getPortfolio().displayPortfolio(market);
                    break;
                case 3:
                    System.out.print("Enter stock symbol: ");
                    String buySymbol = scanner.next().toUpperCase();
                    Stock buyStock = market.getStock(buySymbol);
                    if (buyStock != null) {
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        if (user.getPortfolio().buyStock(buyStock, quantity)) {
                            System.out.println("Stock bought successfully!");
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter stock symbol: ");
                    String sellSymbol = scanner.next().toUpperCase();
                    Stock sellStock = market.getStock(sellSymbol);
                    if (sellStock != null) {
                        System.out.print("Enter quantity: ");
                        int sellQty = scanner.nextInt();
                        if (user.getPortfolio().sellStock(sellStock, sellQty)) {
                            System.out.println("Stock sold successfully!");
                        } else {
                            System.out.println("Not enough shares to sell.");
                        }
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 5:
                    double totalValue = user.getPortfolio().getTotalValue(market);
                    System.out.printf("Total Portfolio Value: $%.2f%n", totalValue);
                    break;
                case 6:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

