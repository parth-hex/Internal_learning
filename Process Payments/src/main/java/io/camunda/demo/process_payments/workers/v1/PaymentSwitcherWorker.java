package io.camunda.demo.process_payments.workers.v1;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentSwitcherWorker {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentSwitcherWorker.class);


    @JobWorker(type = "switch-payment")
    public Map<String, Object> chargeCreditCard(@Variable("totalWithTax") Double totalWithTax, @Variable("paymentMode") String paymentMode) {
        LOGGER.info("Payment method is being switched more reliable method ");
        paymentMode = paymentMode.equals("creditCard") ? "debitCard" : "creditCard";
        return Map.of("paymentStatus", Boolean.TRUE , "paymentMode",paymentMode);
    }
}
