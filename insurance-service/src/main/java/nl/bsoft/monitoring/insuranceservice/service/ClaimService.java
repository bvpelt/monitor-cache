package nl.bsoft.monitoring.insuranceservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class ClaimService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClaimService.class);

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
            LOGGER.error(e.getMessage());
        }

        return claimMap.get(customerId % claimMap.values().size());
    }
}