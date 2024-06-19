package com.privateevents.controller.app;

import com.privateevents.engineering.payment.Observer;
import com.privateevents.engineering.payment.PayPalAPI;
import com.privateevents.engineering.payment.PaymentAPI;
import com.privateevents.engineering.payment.PaymentRequest;
import com.privateevents.model.Organizer;

public class OnlinePaymentController extends Observer {

    private PaymentAPI paymentAPI;

    private Boolean response;

    protected boolean payPayPal(Organizer organizer, Double amount, String reason) {
        PaymentRequest request = new PaymentRequest(organizer.getInfoPayPal(), amount, reason);
        paymentAPI = new PayPalAPI();
        paymentAPI.registerObserver(this);
        long startTime = System.currentTimeMillis();
        long timeout = 60000L;

        paymentAPI.processPayment(request);
        while(response == null) {
            if(System.currentTimeMillis() - startTime > timeout) {
                return false;
            }
        }
        return response;
    }

    @Override
    protected void update(){
        if (paymentAPI != null){
            response = paymentAPI.getResponse();
        }
    }
}


