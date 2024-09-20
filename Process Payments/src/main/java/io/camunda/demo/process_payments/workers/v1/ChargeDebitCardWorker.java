package io.camunda.demo.process_payments.workers.v1;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ChargeDebitCardWorker {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChargeDebitCardWorker.class);


    @JobWorker(type = "charge-debit-card")
    public Map<String,Object> chargeCreditCard(@Variable("totalWithTax")Double totalWithTax, @Variable("paymentMode")String paymentMode) {
        LOGGER.info("Bill is Being paid through debit card : {}",totalWithTax);
        return Map.of("paymentStatus",Boolean.TRUE);
    }
}
