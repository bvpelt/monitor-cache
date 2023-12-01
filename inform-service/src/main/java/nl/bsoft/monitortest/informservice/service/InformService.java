package nl.bsoft.monitortest.informservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class InformService {

    public String informClient(Long customerId, String message) {
        //Adding sleep
        int sleepTime = new Random().nextInt(500);// -- Uncomment the line if you want to add random delay

        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }

        return message;
    }
}