package nl.bsoft.monitortest.informservice.controller;

import lombok.extern.slf4j.Slf4j;
import nl.bsoft.monitortest.informservice.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/")
public class InformController {

    private final InformService informService;

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    InformController(InformService informService) {
        this.informService = informService;
    }

    @PostMapping("/inform/{id}")
    public String informClient(@PathVariable("id") Long customerId, @RequestBody String message) {
        log.info("Inform customer: {} with message: {}", customerId, message);
        return informService.informClient(customerId, message);
    }
}