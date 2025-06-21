public class User {
    private String name;
    private Portfolio portfolio;

    public User(String name, double startingBalance) {
        this.name = name;
        this.portfolio = new Portfolio(startingBalance);
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public String getName() {
        return name;
    }
}
