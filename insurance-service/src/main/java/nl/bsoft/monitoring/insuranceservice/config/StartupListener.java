package nl.bsoft.monitoring.insuranceservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public final void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("On Application Event is OK");
        cacheManager.getCacheNames().parallelStream().forEach(n -> {
            // cacheManager.getCache(n).clear()
            log.info("Cache: {}", n);
        });
    }

}