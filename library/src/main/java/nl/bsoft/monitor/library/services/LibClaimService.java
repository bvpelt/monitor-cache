package nl.bsoft.monitor.library.services;

import nl.bsoft.monitor.library.domain.Claim;

import java.util.List;

public interface LibClaimService {
    List<Claim> getAll();

    Claim add(Claim claim);

    Claim update(Claim claim);

    void delete(long id);

    Claim getClaimById(long id);
}
