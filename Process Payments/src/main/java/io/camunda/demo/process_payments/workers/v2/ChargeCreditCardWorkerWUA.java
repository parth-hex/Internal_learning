package io.camunda.demo.process_payments.workers.v2;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ChargeCreditCardWorkerWUA {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChargeCreditCardWorkerWUA.class);

    @JobWorker(type="charge-credit-card-wua")
    public Map<String, Object> transactUsingCreditCard(@Variable("totalWithTax") Double totalWithTax,@Variable("payment_selection") String paymentSelectionMode) {

        LOGGER.info("Paying amount : {} through credit card.", totalWithTax);

        return Map.of("paymentStatus", Boolean.TRUE);

    }
}
