package nl.bsoft.monitoring.insuranceservice.service;

import lombok.extern.slf4j.Slf4j;
import nl.bsoft.monitoring.insuranceservice.domain.Claim;
import nl.bsoft.monitoring.insuranceservice.repositories.ClaimRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
@Slf4j
@Service
@CacheConfig(cacheNames = "claimCache")
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    @Cacheable(cacheNames = "claims")
    @Override
    public List<Claim> getAll() {
        waitSomeTime("getAll");
        return this.claimRepository.findAll();
    }

    @CacheEvict(cacheNames = "claims", allEntries = true)
    @Override
    public Claim add(Claim claim) {
        return this.claimRepository.save(claim);
    }

    @CacheEvict(cacheNames = "claims", allEntries = true)
    @Override
    public Claim update(Claim claim) {
        Optional<Claim> optClaim = this.claimRepository.findById(claim.getId());
        if (!optClaim.isPresent())
            return null;

        Claim repClaim = optClaim.get();
        repClaim.setClaimText(claim.getClaimText());
        return this.claimRepository.save(repClaim);
    }

    @Caching(evict = { @CacheEvict(cacheNames = "claim", key = "#id"),
            @CacheEvict(cacheNames = "claims", allEntries = true) })
    @Override
    public void delete(long id) {
        this.claimRepository.deleteById(id);
    }

    @Cacheable(cacheNames = "claim", key = "#id", unless = "#result == null")
    @Override
    public Claim getClaimById(long id) {
        waitSomeTime("getClaimById");
        return this.claimRepository.findById(id).orElse(null);
    }

    private void waitSomeTime(String stage) {
        log.info("Long Wait Begin for {}", stage);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Long Wait End");
    }

    /*
    private static final Map<Integer, String> claimMap = new HashMap<>();

    static {
        claimMap.put(111, "Claim 111");
        claimMap.put(222, "Claim 222");
        claimMap.put(333, "Claim 333");
        claimMap.put(444, "Claim 444");
        claimMap.put(555, "Claim 555");
        claimMap.put(666, "Claim 666");
        claimMap.put(777, "Claim 777");
    }

    public String findClaim(Integer customerId) {

        //Adding sleep
        int sleepTime = new Random().nextInt(500);// -- Uncomment the line if you want to add random delay

        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }

        return claimMap.get(customerId % claimMap.values().size());
    }
    */

}