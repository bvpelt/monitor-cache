package nl.bsoft.monitortest.customerservice.controller;

import lombok.extern.slf4j.Slf4j;
import nl.bsoft.monitortest.customerservice.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/")
public class CustomerController {

    @Value("${nl.bsoft.insuranceurl}")
    private String BASE_INSURANCE_URL;

    @Value("${nl.bsoft.informurl}")
    private String BASE_INFORM_URL;
    private RestTemplate restTemplate;
    @Value("${spring.application.name}")
    private String applicationName;

    public CustomerController(RestTemplate rest) {
        this.restTemplate = rest;
    }

    @GetMapping("/customer")
    public ResponseEntity info(@RequestParam("id") Integer customerId) {

        log.info("Incoming request at {} for request customer/info for customer: {} ", applicationName, customerId);
        Map<String, Integer> params = Collections.singletonMap("id", customerId);
        String policyId = restTemplate.getForObject(BASE_INSURANCE_URL + "policy?id={id}", String.class, params);
        log.info("Recevied policyId: {} for customer: {}", policyId, customerId);
        String claimId = restTemplate.getForObject(BASE_INSURANCE_URL + "claim?id={id}", String.class, params);
        log.info("Recevied claimId: {} for customer: {}", claimId, customerId);
        Customer customer = new Customer(customerId, policyId, claimId);

        String url = BASE_INFORM_URL + customerId.toString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        HttpEntity<String> request =
                new HttpEntity<String>(customer.toString(), headers);
        String message = restTemplate.postForObject(url, request, String.class);
        log.info("Received message: {}", message);
        return ResponseEntity.ok(message);
    }

}