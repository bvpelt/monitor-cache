package nl.bsoft.monitortest.customerservice.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Slf4j
@Configuration
public class RedisConfig {
    @Value("${nl.bsoft.monitortest.customerservice.defaultCacheTime}")
    private final long defaultCacheTime = 60;

    @Value("${nl.bsoft.monitortest.customerservice.itemCacheTime}")
    private final long custCacheTime = 10;

    @Value("${nl.bsoft.monitortest.customerservice.customerCacheTime}")
    private final long customerCacheTime = 5;

    /* Default cache configuration */
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(defaultCacheTime))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {

        return (builder) -> builder
                .withCacheConfiguration("custCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(custCacheTime)))
                .withCacheConfiguration("customerCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(customerCacheTime)));
    }

    @PostConstruct
    void init() {
        log.info("defaultCacheTime: {}", defaultCacheTime);
        log.info("custCacheTime: {}", custCacheTime);
        log.info("customerCacheTime: {}", customerCacheTime);
    }
}
