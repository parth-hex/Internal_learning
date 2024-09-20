package io.camunda.demo.process_payments;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Deployment(resources = {"classpath:process_payments.bpmn","classpath:process_payments_wua.bpmn"})
public class ProcessPaymentsApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessPaymentsApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(ProcessPaymentsApplication.class, args);
    }
}
