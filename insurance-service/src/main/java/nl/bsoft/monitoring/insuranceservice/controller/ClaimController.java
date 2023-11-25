package nl.bsoft.monitoring.insuranceservice.controller;

import lombok.extern.slf4j.Slf4j;
import nl.bsoft.monitor.library.domain.Claim;
import nl.bsoft.monitor.library.services.LibClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
public class ClaimController {


    private final LibClaimService claimService;
    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    ClaimController(LibClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping("/claim")
    public List<Claim> getAllClaims() throws Exception {
        log.info("Incoming request at {} for request get all claims", applicationName);
        return this.claimService.getAll();
    }

    @PostMapping("/claim")
    public Claim addPolicy(@RequestBody Claim claim) {
        return this.claimService.add(claim);
    }

    @GetMapping("/claim/{id}")
    public Claim getPolicy(@PathVariable("id") Long claimId) throws Exception {
        log.info("Incoming request at {} for request claim from id: {} ", applicationName, claimId);
        return this.claimService.getClaimById(claimId);
    }

    @PutMapping("/claim")
    public Claim updatePolicy(@RequestBody Claim claim) {
        return this.claimService.update(claim);
    }

    @DeleteMapping("/claim/{id}")
    public void deletePolicy(@PathVariable("id") Long claimId) {
        this.claimService.delete(claimId);
    }
}
