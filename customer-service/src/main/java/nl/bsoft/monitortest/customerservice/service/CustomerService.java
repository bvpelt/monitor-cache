package nl.bsoft.monitortest.customerservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.monitor.library.domain.Claim;
import nl.bsoft.monitor.library.domain.Policy;
import nl.bsoft.monitor.library.services.LibCustomerService;
import nl.bsoft.monitor.library.services.MyHttpService;
import nl.bsoft.monitortest.customerservice.controller.MyApiException;
import nl.bsoft.monitortest.customerservice.controller.NotFoundException;
import nl.bsoft.monitortest.customerservice.model.Customer;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.Duration;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final Long timeOutValue = 5L;
    @Value("${nl.bsoft.insuranceurl}")
    private String BASE_INSURANCE_URL;
    @Value("${nl.bsoft.informurl}")
    private String BASE_INFORM_URL;
    /*
    private final CustomerRepository customerRepository;

    public CustomerDTO getCustomerDTO(Integer id) {
        return getCustomerDTOForId(id);
    }

    @Cacheable(value = "custCache")
    public CustomerDTO getCustomerDTOForId(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

     */
    private RestTemplate restTemplate;
    private LibCustomerService libCustomerService;
    private MyHttpService httpClient;

    @Autowired
    public CustomerService(RestTemplate restTemplate, MyHttpService httpClient, LibCustomerService libCustomerService) {
        this.restTemplate = restTemplate;
        this.libCustomerService = libCustomerService;
        this.httpClient = httpClient;

        /*
        // create webclient
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)  // connection timeout 5000ms
                .responseTimeout(Duration.ofMillis(5000))            // response timeout 5000ms
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))  // read timeout 5000 ms
                                .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS))); // write timeout 5000 ms

        this.webclient = webClientBuilder
                .baseUrl(BASE_INSURANCE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

         */
    }


    public Customer getCustomerForId(Long id) {
        Customer customer = new Customer();
        nl.bsoft.monitor.library.domain.Customer libCustomer = libCustomerService.getCustomerById(id);
        log.debug("found customer: {}", libCustomer.toString());

        Duration timeout = Duration.ofSeconds(timeOutValue);

        customer.setCustomerId(libCustomer.getCustomerid());

        Policy p = getPolicy(libCustomer);
        if (p != null) {
            customer.setPolicy(p.getPolicyText());
        } else {
            log.error("No policy found");
            customer.setPolicy("");
        }

        Claim c = getClaim(libCustomer);
        if (c != null) {
            customer.setClaim(c.getClaimText());
        } else {
            log.error("No claim found");
            customer.setClaim("");
        }

        /*
        Mono<Policy> p = this.webclient.get()
                .uri("/policy/{id}", customer.getPolicy())
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        response -> Mono.error(new WebClientResponseException(
                                response.statusCode(),
                                response.statusCode().toString(),
                                response.headers().asHttpHeaders(),
                                null, null, null)))
                .bodyToMono(Policy.class)
                .timeout(Duration.ofSeconds(timeOutValue)) // Set your desired timeout
                .onErrorMap(WebClientResponseException.class, ex -> handleWebClientException(ex, customer.getCustomerId().toString()));


        Policy resultPolicy = p.block(timeout);
        customer.setPolicy(resultPolicy.getPolicyText());

        Mono<Claim> c = this.webclient.get()
                .uri("/claim/{id}", customer.getClaim())
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        response -> Mono.error(new WebClientResponseException(
                                response.statusCode(),
                                response.statusCode().toString(),
                                response.headers().asHttpHeaders(),
                                null, null, null)))
                .bodyToMono(Claim.class)
                .timeout(Duration.ofSeconds(5)) // Set your desired timeout
                .onErrorMap(WebClientResponseException.class, ex -> handleWebClientException(ex, customer.getCustomerId().toString()));

        Claim resultClaim = c.block(timeout);
        customer.setClaim(resultClaim.getClaimText());

        Map<String, Long> policyParams = Collections.singletonMap("id", libCustomer.getPolicyid());
        Policy policy = restTemplate.getForObject(BASE_INSURANCE_URL + "policy/id", Policy.class, policyParams);
        log.info("Recevied for customer: {} policy: {}", customer.getCustomerId(), policy.toString());
        customer.setPolicy(policy.getPolicyText());


        Map<String, Long> claimParams = Collections.singletonMap("id", libCustomer.getClaimid());
        Claim claim = restTemplate.getForObject(BASE_INSURANCE_URL + "claim/id", Claim.class, claimParams);
        log.info("Recevied for customer: {} claim: {}", customer.getCustomerId(), claim.toString());
        customer.setClaim(claim.getClaimText());
*/
        return customer;
    }

    private Policy getPolicy(nl.bsoft.monitor.library.domain.Customer libCustomer) {

        // create webclient instance -> in constructor
        // make request
        /*
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = this.webclient.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/policy/{id}", libCustomer.getPolicyid());

        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.header("Myheader", "Mycontent");

        WebClient.ResponseSpec responseSpec = headersSpec.header(
                        HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        // handle response
        return responseSpec.bodyToMono(Policy.class)
                .onErrorResume(error -> {
                    if (error instanceof WebClientResponseException &&
                            ((WebClientResponseException) error).getStatusCode().is4xxClientError()) {
                        // Handle 4xx errors if needed
                        return Mono.empty();
                    } else {
                        return Mono.error(error);
                    }

                }).block();
*/
        Duration duration = Duration.ofMillis(5000);
        String uriString = "http://localhost:8090/policy/" + libCustomer.getPolicyid().toString();

        CloseableHttpClient closeableHttpClient = null;
        Policy policy = httpClient.getResponse(uriString, duration, Policy.class);

        return policy;
    }


    private Claim getClaim(nl.bsoft.monitor.library.domain.Customer libCustomer) {

        // create webclient instance -> in constructor
        // make request
        /*
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = this.webclient.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/claim/{id}", libCustomer.getClaimid());

        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.header("Myheader", "Mycontent");

        WebClient.ResponseSpec responseSpec = headersSpec.header(
                        HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        // handle response
        return responseSpec.bodyToMono(Claim.class)
                .onErrorResume(error -> {
                    if (error instanceof WebClientResponseException &&
                            ((WebClientResponseException) error).getStatusCode().is4xxClientError()) {
                        // Handle 4xx errors if needed
                        return Mono.empty();
                    } else {
                        return Mono.error(error);
                    }

                }).block();
         */
        Duration duration = Duration.ofMillis(5000);
        String uriString = "http://localhost:8090/claim/" + libCustomer.getClaimid().toString();

        CloseableHttpClient closeableHttpClient = null;
        Claim claim = httpClient.getResponse(uriString, duration, Claim.class);

        return claim;
    }
/*
    private Throwable handleWebClientException(WebClientResponseException ex, String pathVariable) {
        if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
            return new NotFoundException("Resource not found for variable: " + pathVariable);
        } else {
            return new MyApiException("Error calling API", ex);
        }
    }

 */
}
