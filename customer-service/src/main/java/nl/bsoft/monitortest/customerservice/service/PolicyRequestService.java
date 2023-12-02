package nl.bsoft.monitortest.customerservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.monitor.library.exceptions.InternalServerErrorException;
import nl.bsoft.monitor.library.services.ExternalRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
@Service
public class PolicyRequestService extends ExternalRequestService {

    @Value("${nl.bsoft.insuranceurl}")
    private String BASE_INSURANCE_URL;
    private final String API_PATH = "policy";
    private static final int MAX_PAGE_SIZE = 200;

    public PolicyRequestService(ObjectMapper objectMapper, HttpClient httpClient, Duration requestDuration) {
        super(objectMapper, httpClient, requestDuration);
    }

    public String getPolicyApiUrl() {
        return BASE_INSURANCE_URL + API_PATH;
    }

    public <T> void processAllItemsFromCompletableFuture(
            BiFunction<Integer, Integer, CompletableFuture<T>> method, ToIntFunction<T> getTotalCount) {
        CompletableFuture<T> future = method.apply(0, MAX_PAGE_SIZE);
        T object = getCompletedFuture(future);
        for (int i = 1; i <= getTotalCount.applyAsInt(object); i++) {
            getCompletedFuture(method.apply(i, MAX_PAGE_SIZE));
        }
    }
    public <T> List<T> getAllItemsFromCompletableFuture(
            BiFunction<Integer, Integer, CompletableFuture<T>> method, ToIntFunction<T> getTotalCount) {
        CompletableFuture<T> future = method.apply(0, MAX_PAGE_SIZE);
        T object = getCompletedFuture(future);
        List<CompletableFuture<T>> futures =
                IntStream.range(1, getTotalCount.applyAsInt(object))
                        .mapToObj(pageIndex -> method.apply(pageIndex, MAX_PAGE_SIZE))
                        .toList();
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        return Stream.concat(futures.stream(), Stream.of(future))
                .map(completedFuture -> completedFuture.getNow(null))
                .filter(Objects::nonNull)
                .toList();
    }
    private <T> T getCompletedFuture(CompletableFuture<T> future) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new InternalServerErrorException(e);
        } catch (ExecutionException e) {
            throw new InternalServerErrorException(e);
        }
    }
}
