package nl.bsoft.monitortest.customerservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.monitor.library.domain.Claim;
import nl.bsoft.monitor.library.domain.Policy;
import nl.bsoft.monitor.library.services.LibCustomerService;
import nl.bsoft.monitortest.customerservice.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    @Value("${nl.bsoft.insuranceurl}")
    private String BASE_INSURANCE_URL;

    @Value("${nl.bsoft.informurl}")
    private String BASE_INFORM_URL;

    /*
    private final CustomerRepository customerRepository;

    public CustomerDTO getCustomerDTO(Integer id) {
        return getCustomerDTOForId(id);
    }

    @Cacheable(value = "custCache")
    public CustomerDTO getCustomerDTOForId(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

     */
    private RestTemplate restTemplate;
    private LibCustomerService libCustomerService;

    @Autowired
    public CustomerService(RestTemplate restTemplate, LibCustomerService libCustomerService) {
        this.restTemplate = restTemplate;
        this.libCustomerService = libCustomerService;
    }

    public Customer getCustomerForId(Long id) {
        Customer customer = new Customer();
        nl.bsoft.monitor.library.domain.Customer libCustomer = libCustomerService.getCustomerById(id);

        customer.setCustomerId(libCustomer.getCustomerId());

        Map<String, Long> policyParams = Collections.singletonMap("id", libCustomer.getPolicyId());
        Policy policy = restTemplate.getForObject(BASE_INSURANCE_URL + "policy?id={id}", Policy.class, policyParams);
        log.info("Recevied for customer: {} policy: {}", customer.getCustomerId(), policy.toString());
        customer.setPolicy(policy.getPolicyText());


        Map<String, Long> claimParams = Collections.singletonMap("id", libCustomer.getClaimId());
        Claim claim = restTemplate.getForObject(BASE_INSURANCE_URL + "claim?id={id}", Claim.class, claimParams);
        log.info("Recevied for customer: {} claim: {}", customer.getCustomerId(), claim.toString());
        customer.setClaim(claim.getClaimText());

        return customer;
    }
}
