package io.camunda.demo.process_payments.workers.v1;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ChargeCreditCardWorker {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChargeCreditCardWorker.class);


    @JobWorker(type = "charge-credit-card1")
    public Map<String,Object> chargeCreditCard(@Variable("totalWithTax")Double totalWithTax, @Variable("paymentMode")String paymentMode) {
        LOGGER.info("Bill is being paid through credit card : {}",totalWithTax);
        return Map.of("paymentStatus",Boolean.FALSE);
    }
}
