import java.util.Random;

public class UpiPayment implements PaymentGateway {
    public boolean processPayment(double amount) {
        System.out.println("Processing UPI Payment of ₹" + amount);
        return new Random().nextDouble() > 0.1;
    }
}
