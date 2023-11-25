package nl.bsoft.monitoring.insuranceservice.service;

import lombok.extern.slf4j.Slf4j;
import nl.bsoft.monitor.library.domain.Claim;
import nl.bsoft.monitor.library.services.LibClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@CacheConfig(cacheNames = "claimCache")
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private LibClaimService libClaimService;

    @Cacheable(cacheNames = "claims")
    @Override
    public List<Claim> getAll() {
        waitSomeTime("getAll");
        return this.libClaimService.getAll();
    }

    @CacheEvict(cacheNames = "claims", allEntries = true)
    @Override
    public Claim add(Claim claim) {
        return this.libClaimService.add(claim);
    }

    @CacheEvict(cacheNames = "claims", allEntries = true)
    @Override
    public Claim update(Claim claim) {
        Optional<Claim> optClaim = Optional.ofNullable(this.libClaimService.getClaimById(claim.getId()));
        if (!optClaim.isPresent())
            return null;

        Claim repClaim = optClaim.get();
        repClaim.setClaimText(claim.getClaimText());
        return this.libClaimService.add(repClaim);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "claim", key = "#id"),
            @CacheEvict(cacheNames = "claims", allEntries = true)})
    @Override
    public void delete(long id) {
        this.libClaimService.delete(id); }

    @Cacheable(cacheNames = "claim", key = "#id", unless = "#result == null")
    @Override
    public Claim getClaimById(long id) {
        waitSomeTime("getClaimById");
        return this.libClaimService.getClaimById(id);
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