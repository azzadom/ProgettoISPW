package controller.app;

import engineering.paypal.*;
import model.Organizer;

public class OnlinePaymentController {

    protected boolean payPayPal(Organizer organizer, Double amount, String reason) {
        PaymentRequest request = new PaymentRequest(organizer.getInfoPayPal(), amount, reason);
        PaymentResponse response = new PayPalAPI().processPayment(request);
        return response.isSuccess();
    }
}


