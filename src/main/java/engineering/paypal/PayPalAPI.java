package engineering.paypal;

import java.security.SecureRandom;
import java.util.Random;

public class PayPalAPI {

    public PaymentResponse processPayment(PaymentRequest request) {
        PaymentResponse response = new PaymentResponse();
        Random random = new SecureRandom();

        if(request.getAmount().equals(0.0)) {
            response.setSuccess(false);
            response.setTransactionId(null);
            response.setMessage("Amount cannot be 0");
            return response;
        }

        boolean paymentSuccessful = random.nextBoolean();
        if (paymentSuccessful) {
            response.setSuccess(true);
            response.setTransactionId("TRA" + System.currentTimeMillis());
            response.setMessage("Payment successful");
        } else {
            response.setSuccess(false);
            response.setTransactionId(null);
            response.setMessage("Payment failed");
        }

        return response;
    }

}
