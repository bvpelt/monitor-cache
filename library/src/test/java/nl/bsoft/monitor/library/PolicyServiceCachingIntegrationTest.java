package nl.bsoft.monitor.library;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import nl.bsoft.monitor.library.domain.Claim;
import nl.bsoft.monitor.library.domain.Policy;
import nl.bsoft.monitor.library.repositories.ClaimRepository;
import nl.bsoft.monitor.library.repositories.PolicyRepository;
import nl.bsoft.monitor.library.services.LibClaimService;
import nl.bsoft.monitor.library.services.LibPolicyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class PolicyServiceCachingIntegrationTest {

    private static final Long AN_ID = 1L;
    private static final String AN_POLICY = "Policy 001";
    private static final String AN_CLAIM = "Claim 001";

    @MockBean
    PolicyRepository mockPolicyRepository;
    @MockBean
    ClaimRepository mockClaimRepository;
    @Autowired
    private LibPolicyService policyService;
    @Autowired
    private LibClaimService claimService;


    //@Test
    void givenRedisCaching_whenFindPolicyById_thenPolicyReturnedFromCache() {

        Policy anItem = new Policy(AN_ID, AN_POLICY);
        given(mockPolicyRepository.findById(AN_ID))
                .willReturn(Optional.of(anItem));

        Policy itemCacheMiss = policyService.getPolicyById(AN_ID);
        Policy itemCacheHit = policyService.getPolicyById(AN_ID);

        assertThat(itemCacheMiss).isEqualTo(anItem);
        assertThat(itemCacheHit).isEqualTo(anItem);

        verify(mockPolicyRepository, times(1)).findById(AN_ID);

    }

    // @Test
    void givenRedisCaching_whenFindClaimById_thenClaimReturnedFromCache() {

        Claim anItem = new Claim(AN_ID, AN_CLAIM);
        given(mockClaimRepository.findById(AN_ID))
                .willReturn(Optional.of(anItem));

        Claim itemCacheMiss = claimService.getClaimById(AN_ID);
        Claim itemCacheHit = claimService.getClaimById(AN_ID);

        assertThat(itemCacheMiss).isEqualTo(anItem);
        assertThat(itemCacheHit).isEqualTo(anItem);

        verify(mockClaimRepository, times(1)).findById(AN_ID);

    }

}