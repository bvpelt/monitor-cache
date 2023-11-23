package nl.bsoft.monitoring.insuranceservice.service;

import lombok.extern.slf4j.Slf4j;
import nl.bsoft.monitoring.insuranceservice.domain.Policy;
import nl.bsoft.monitoring.insuranceservice.repositories.PolicyRepository;
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
@CacheConfig(cacheNames = "policyCache")
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Cacheable(cacheNames = "policies")
    @Override
    public List<Policy> getAll() {
        waitSomeTime(3000,"getAll");
        return this.policyRepository.findAll();
    }

    @CacheEvict(cacheNames = "policies", allEntries = true)
    @Override
    public Policy add(Policy policy) {
        waitSomeTime(1000,"Add");
        Policy rp = this.policyRepository.save(policy);
        log.info("Added policy {}", rp.toString());
        return rp;
    }

    @CacheEvict(cacheNames = "policies", allEntries = true)
    @Override
    public Policy update(Policy policy) {
        Optional<Policy> optPolicy = this.policyRepository.findById(policy.getId());
        if (!optPolicy.isPresent())
            return null;

        Policy repPolicy = optPolicy.get();
        repPolicy.setPolicyText(policy.getPolicyText());
        return this.policyRepository.save(repPolicy);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "policy", key = "#id"),
            @CacheEvict(cacheNames = "policies", allEntries = true)})
    @Override
    public void delete(long id) {
        this.policyRepository.deleteById(id);
    }

    @Cacheable(cacheNames = "policy", key = "#id", unless = "#result == null")
    @Override
    public Policy getPolicyById(long id) {
        waitSomeTime(3000,"getPolicyById");
        return this.policyRepository.findById(id).orElse(null);
    }

    private void waitSomeTime(long interval , String stage) {
        log.info("Long Wait Begin for {}", stage);
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Long Wait End");
    }

/*
    private static final Map<Integer, String> policyMap = new HashMap<>();

    static {
        policyMap.put(111, "Policy 111");
        policyMap.put(222, "Policy 222");
        policyMap.put(333, "Policy 333");
        policyMap.put(444, "Policy 444");
        policyMap.put(555, "Policy 555");
        policyMap.put(666, "Policy 666");
        policyMap.put(777, "Policy 777");
    }

    public String findPolicy(Integer customerId) {

        //Adding sleep
        int sleepTime = new Random().nextInt(500);//

        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }

        return policyMap.get(customerId % policyMap.values().size()); // Always map on existing policy
    }

 */
}