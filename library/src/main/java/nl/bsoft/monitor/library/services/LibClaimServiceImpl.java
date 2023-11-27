package nl.bsoft.monitor.library.services;

import lombok.extern.slf4j.Slf4j;
import nl.bsoft.monitor.library.domain.Claim;
import nl.bsoft.monitor.library.repositories.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@CacheConfig(cacheNames = "claimCache")
public class LibClaimServiceImpl implements LibClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    @Override
    public List<Claim> getAll() {
        return this.claimRepository.findAll();
    }

    @Override
    public Claim add(Claim claim) {
        return this.claimRepository.save(claim);
    }

    @Override
    public Claim update(Claim claim) {
        Optional<Claim> optClaim = this.claimRepository.findById(claim.getId());
        if (!optClaim.isPresent())
            return null;

        Claim repClaim = optClaim.get();
        repClaim.setClaimText(claim.getClaimText());
        return this.claimRepository.save(repClaim);
    }

    @Override
    public void delete(long id) {
        this.claimRepository.deleteById(id);
    }

    @Override
    public Claim getClaimById(long id) {
        return this.claimRepository.findById(id).orElse(null);
    }

}