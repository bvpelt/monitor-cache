package nl.bsoft.monitoring.insuranceservice.controller;

import lombok.extern.slf4j.Slf4j;
import nl.bsoft.monitoring.insuranceservice.service.ClaimService;
import nl.bsoft.monitoring.insuranceservice.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class InsuranceController {
    private final PolicyService policyService;
    private final ClaimService claimService;
    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    InsuranceController(PolicyService policyService, ClaimService claimService) {
        this.policyService = policyService;
        this.claimService = claimService;
    }

    @GetMapping("/policy")
    public String getPolicy(@RequestParam("id") Integer customerId) throws Exception {
        log.info("Incoming request at {} for request policy from customer: {} ", applicationName, customerId);
        return policyService.findPolicy(customerId);
    }

    @GetMapping("/claim")
    public String getFullName(@RequestParam("id") Integer customerId) throws Exception {
        log.info("Incoming request at {} for request claim from customer: {}", applicationName, customerId);
        return claimService.findClaim(customerId);
    }
}