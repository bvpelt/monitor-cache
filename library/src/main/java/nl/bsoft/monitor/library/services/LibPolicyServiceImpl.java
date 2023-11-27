package nl.bsoft.monitor.library.services;

import lombok.extern.slf4j.Slf4j;
import nl.bsoft.monitor.library.domain.Policy;
import nl.bsoft.monitor.library.repositories.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@CacheConfig(cacheNames = "policyCache")
public class LibPolicyServiceImpl implements LibPolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Override
    public List<Policy> getAll() {
        waitSomeTime(3000, "getAll");
        return this.policyRepository.findAll();
    }

    @Override
    public Policy add(Policy policy) {
        waitSomeTime(1000, "Add");
        Policy rp = this.policyRepository.save(policy);
        log.info("Added policy {}", rp);
        return rp;
    }

    @Override
    public Policy update(Policy policy) {
        Optional<Policy> optPolicy = this.policyRepository.findById(policy.getId());
        if (!optPolicy.isPresent())
            return null;

        Policy repPolicy = optPolicy.get();
        repPolicy.setPolicyText(policy.getPolicyText());
        return this.policyRepository.save(repPolicy);
    }


    @Override
    public void delete(long id) {
        this.policyRepository.deleteById(id);
    }


    @Override
    public Policy getPolicyById(long id) {
        waitSomeTime(3000, "getPolicyById");
        return this.policyRepository.findById(id).orElse(null);
    }

    private void waitSomeTime(long interval, String stage) {
        log.info("Long Wait Begin for {}", stage);
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Long Wait End");
    }
}