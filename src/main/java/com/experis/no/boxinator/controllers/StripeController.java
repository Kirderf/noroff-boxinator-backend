package com.experis.no.boxinator.controllers;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class StripeController {

    // You can move this to application.properties and inject using @Value
    static {
        // This is your test secret API key.
        Stripe.apiKey = "sk_test_51O4htnK2lMp6ijYz32AF6JWsnSsLRl4TLzg3JC9DzWvH9juyeiSPA4xwkBS2iW7KLypEtXZAiHziVOvKqis2JVXs00G3OXZ0Vy";
    }


    @PostMapping("/create-checkout-session")
    @ResponseBody
    public Map<String, String> createCheckoutSession() throws Exception {
        String YOUR_DOMAIN = "http://localhost:5173";
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setUiMode(SessionCreateParams.UiMode.EMBEDDED)
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setReturnUrl(YOUR_DOMAIN + "/return?session_id={CHECKOUT_SESSION_ID}")
                        .build();

        Session session = Session.create(params);

        Map<String, String> map = new HashMap<>();
        map.put("clientSecret", session.getClientSecret());

        return map;
    }

    @GetMapping("/session-status")
    @ResponseBody
    public Map<String, String> getSessionStatus(String session_id) throws Exception {
        Session session = Session.retrieve(session_id);
        Map<String, String> map = new HashMap<>();
        map.put("status", session.getStatus());
        map.put("customer_email", session.getCustomerEmail());

        return map;
    }
}
