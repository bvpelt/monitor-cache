package nl.bsoft.monitortest.customerservice.controller;

import lombok.extern.slf4j.Slf4j;
import nl.bsoft.monitoring.insuranceservice.domain.Claim;
import nl.bsoft.monitoring.insuranceservice.domain.Policy;
import nl.bsoft.monitortest.customerservice.model.Customer;
import nl.bsoft.monitortest.customerservice.model.CustomerDTO;
import nl.bsoft.monitortest.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Value("${spring.application.name}")
    private String applicationName;

    private RestTemplate restTemplate;
    private CustomerService customerService;

    @Autowired
    public CustomerController(RestTemplate rest, CustomerService customerService) {
        this.restTemplate = rest;
        this.customerService = customerService;
    }

    /*
    Get the policy and claim for a customer
     */
    @GetMapping("/customer")
    public ResponseEntity info(@RequestParam("id") Integer customerId) {

        log.info("Incoming request at {} for request customer/info for customer: {} ", applicationName, customerId);
        CustomerDTO customerDTO = this.customerService.getCustomerDTO(customerId);

        Map<String, Integer> policyParams = Collections.singletonMap("id", customerDTO.getPolicyId());
        Policy policy = restTemplate.getForObject(BASE_INSURANCE_URL + "policy?id={id}", Policy.class, policyParams);
        log.info("Recevied for customer: {} policy: {}",  customerId, policy.toString());

        Map<String, Integer> claimParams = Collections.singletonMap("id", customerDTO.getPolicyId());
        Claim claim = restTemplate.getForObject(BASE_INSURANCE_URL + "claim?id={id}", Claim.class, claimParams);
        log.info("Recevied for customer: {} claim: {}", customerId, claim.toString());
        Customer customer = new Customer(customerId, policy.getPolicyText(), claim.getClaimText());

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