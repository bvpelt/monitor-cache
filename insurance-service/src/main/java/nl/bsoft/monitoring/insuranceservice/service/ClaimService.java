package nl.bsoft.monitoring.insuranceservice.service;

import nl.bsoft.monitoring.insuranceservice.domain.Claim;

import java.util.List;

public interface ClaimService {
    public List<Claim> getAll();

    public Claim add(Claim claim);

    public Claim update(Claim claim);

    public void delete(long id);

    public Claim getClaimById(long id);
}
