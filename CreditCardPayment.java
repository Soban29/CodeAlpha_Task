import java.util.Random;

public class CreditCardPayment implements PaymentGateway {
    public boolean processPayment(double amount) {
        System.out.println("Processing Credit Card Payment of ₹" + amount);
        return new Random().nextDouble() > 0.2;
    }
}
