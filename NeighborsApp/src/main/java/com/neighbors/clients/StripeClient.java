package com.neighbors.clients;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

@Component
public class StripeClient {

    @Autowired
    StripeClient() {
        Stripe.apiKey = "sk_test_51HfAipFwD0lhhN8cC9wOhEawNn4wyY7cqkhhe2Zgquzx4fy4cbGGMMi9JRWjLlWjl5sJTwnVfV9dTz5xle2N1C60002JK2Bb2q";
    }

    public Charge chargeCreditCard(String token, double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }
    
    public Customer createCustomer(String token, String email) throws Exception {
        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("email", email);
        customerParams.put("source", token);
        return Customer.create(customerParams);
    }
}
