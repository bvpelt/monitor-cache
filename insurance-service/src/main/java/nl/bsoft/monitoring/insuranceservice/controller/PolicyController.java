package nl.bsoft.monitoring.insuranceservice.controller;

import lombok.extern.slf4j.Slf4j;
import nl.bsoft.monitoring.insuranceservice.domain.Policy;
import nl.bsoft.monitoring.insuranceservice.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
public class PolicyController {

    private final PolicyService policyService;
    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping("/policy")
    public List<Policy> getAllPolicies() throws Exception {
        log.info("Incoming request at {} for request get all policoes", applicationName);
        return this.policyService.getAll();
    }

    @PostMapping("/policy")
    public Policy addPolicy(@RequestBody Policy policy) {
        return this.policyService.add(policy);
    }

    @GetMapping("/policy/{id}")
    public Policy getPolicy(@PathVariable("id") Long policyId) throws Exception {
        log.info("Incoming request at {} for request policy from id: {} ", applicationName, policyId);
        return this.policyService.getPolicyById(policyId);
    }

    @PutMapping("/policy")
    public Policy updatePolicy(@RequestBody Policy policy) {
        return this.policyService.update(policy);
    }

    @DeleteMapping("/plolicy/{id}")
    public void deletePolicy(@PathVariable("id") Long policyId) {
        this.policyService.delete(policyId);
    }

}
