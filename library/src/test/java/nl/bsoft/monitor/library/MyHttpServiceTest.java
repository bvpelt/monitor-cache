package nl.bsoft.monitor.library;

import lombok.extern.slf4j.Slf4j;
import nl.bsoft.monitor.library.domain.Claim;
import nl.bsoft.monitor.library.domain.Policy;
import nl.bsoft.monitor.library.services.MyHttpService;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.time.Duration;

@Slf4j

public class MyHttpServiceTest {



    @Test
    public void getStringBuilderSuccess() {
        MyHttpService myHttpService = new MyHttpService();

        Duration duration = Duration.ofMillis(5000);
        String uriString = "https://dbpedia.org/page/Kadaster";

        String result = myHttpService.getResponse(uriString, duration, String.class);
        Assert.notNull(result, "No builder generated");
        log.info("result: {}", result);
    }

    @Test
    public void getStringBuilderError() {
        MyHttpService myHttpService = new MyHttpService();

        Duration duration = Duration.ofMillis(5000);
        String uriString = "http:www.nu.nl";

        String result = myHttpService.getResponse(uriString, duration, String.class);
        Assert.isNull(result, "Error expected - no builder generated");
        log.info("Builder: {}", (result == null ? "null" : result));
    }

    @Test
    public void getPolicy() {
        MyHttpService myHttpService = new MyHttpService();
        Duration duration = Duration.ofMillis(5000);
        String uriString = "http://localhost:8090/policy/1";

        CloseableHttpClient closeableHttpClient = null;
        Policy policy = myHttpService.getResponse(uriString, duration, Policy.class);

        Assert.notNull(policy, "Policy expected not found");
    }

    @Test
    public void getClaim() {
        MyHttpService myHttpService = new MyHttpService();
        Duration duration = Duration.ofMillis(5000);
        String uriString = "http://localhost:8090/claim/1";

        CloseableHttpClient closeableHttpClient = null;
        Claim claim = myHttpService.getResponse(uriString, duration, Claim.class);

        Assert.notNull(claim, "Claim expected not found");
    }
}
