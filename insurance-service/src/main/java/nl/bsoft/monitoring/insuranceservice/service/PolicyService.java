package nl.bsoft.monitoring.insuranceservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class PolicyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PolicyService.class);

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
            LOGGER.error(e.getMessage());
        }

        return policyMap.get(customerId % policyMap.values().size()); // Always map on existing policy
    }
}