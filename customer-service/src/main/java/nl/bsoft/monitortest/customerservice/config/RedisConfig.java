package nl.bsoft.monitortest.customerservice.config;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

public class RedisConfig {
    //@Value("#{nl.bsoft.monitortest.customerservice.defaultCacheTime")
    private final long defaultCacheTime = 60;
    //@Value("#{nl.bsoft.monitortest.customerservice.itemCacheTime")
    private final long itemCacheTime = 10;

    //@Value("#{nl.bsoft.monitortest.customerservice.customerCacheTime")
    private final long customerCacheTime = 5;

    /* Default cache configuration */
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {

        return (builder) -> builder
                .withCacheConfiguration("itemCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(itemCacheTime)))
                .withCacheConfiguration("customerCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(customerCacheTime)));
    }
}
