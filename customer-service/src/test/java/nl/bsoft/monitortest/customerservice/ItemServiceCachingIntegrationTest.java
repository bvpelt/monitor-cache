package nl.bsoft.monitortest.customerservice;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import nl.bsoft.monitortest.customerservice.config.RedisConfig;
import nl.bsoft.monitortest.customerservice.controller.CustomerController;
import nl.bsoft.monitortest.customerservice.model.Customer;
import nl.bsoft.monitortest.customerservice.repositories.CustomerRepository;
import nl.bsoft.monitortest.customerservice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redis.embedded.RedisServer;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Import({RedisConfig.class, CustomerController.class})
@ExtendWith(SpringExtension.class)
@EnableCaching
@ImportAutoConfiguration(classes = {
        CacheAutoConfiguration.class,
        RedisAutoConfiguration.class
})
class ItemServiceCachingIntegrationTest {

    private static final Integer AN_ID = 1;
    private static final String AN_POLICY = "policy";
    private static final String AN_CLAIM = "claim";
    @MockBean
    private CustomerRepository mockCustomerRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CacheManager cacheManager;

    @Test
    void givenRedisCaching_whenFindItemById_thenItemReturnedFromCache() {
        Customer anItem = new Customer(AN_ID, AN_POLICY, AN_CLAIM);
        given(mockCustomerRepository.findById(AN_ID))
                .willReturn(Optional.of(anItem));

        Customer itemCacheMiss = customerService.getItemForId(AN_ID);
        Customer itemCacheHit = customerService.getItemForId(AN_ID);

        assertThat(itemCacheMiss).isEqualTo(anItem);
        assertThat(itemCacheHit).isEqualTo(anItem);

        verify(mockCustomerRepository, times(1)).findById(AN_ID);
        assertThat(itemFromCache()).isEqualTo(anItem);
    }

    private Object itemFromCache() {
        return cacheManager.getCache("itemCache").get(AN_ID).get();
    }

    @TestConfiguration
    static class EmbeddedRedisConfiguration {
        private final RedisServer redisServer;

        public EmbeddedRedisConfiguration() {
            this.redisServer = new RedisServer();
        }

        @PostConstruct
        public void startRedis() {
            this.redisServer.start();
        }

        @PreDestroy
        public void stopRedis() {
            this.redisServer.stop();
        }
    }
}