package nl.bsoft.monitoring.insuranceservice.service;

import nl.bsoft.monitoring.insuranceservice.domain.Claim;

import java.util.List;

public interface ClaimService {
    List<Claim> getAll();

    Claim add(Claim claim);

    Claim update(Claim claim);

    void delete(long id);

    Claim getClaimById(long id);
}
