package nl.bsoft.monitor.library.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.InputStream;
import java.time.Duration;

@Slf4j
public class MyHttpService {

    public <T> T getResponse(String uriString, Duration requestDuration, Class<T> clazz) {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpGet httpget = new HttpGet(uriString);
        CloseableHttpResponse response = null;
        T object = null;

        log.info("Starting request at: {}", httpget);
        try {
            response = httpclient.execute(httpget);
            if (response.getStatusLine().getStatusCode() < 300) {
                Header headers[] = response.getAllHeaders();
                for (Header h: response.getAllHeaders()) {
                    log.info("Response Header {} value: {} ", h.getName(), h.getValue());
                }
                String contentType = response.getFirstHeader("Content-Type").getValue();
                log.info("ContentType: {}", contentType);

                HttpEntity entity = response.getEntity();
                Header header = entity.getContentType();
                for (HeaderElement h: header.getElements()) {
                    log.info("Element Header {} value: {} ", h.getName(), h.getValue());
                }

                if (entity != null) {
                    InputStream instream = null;

                    try {
                        instream = entity.getContent();
                        String s = new String(instream.readAllBytes());
                        log.info("Received input: {}", s);
                        if (contentType.equals("application/json")) {
                            ObjectMapper objectMapper = new ObjectMapper();
                            object = objectMapper.readValue(s, clazz);
                        } else {
                            object = (T)s;
                        }
                        log.info("received object: {}", object.toString());
                    } catch (Exception e) {
                        log.error("Error reading content: {}",e);
                    }
                    finally {
                        try {
                            instream.close();
                        } catch (Exception e) {
                            log.error("Error closing inputstream");
                        }
                    }
                }
            } else {
                log.error("Response status: {} - {}", response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
            }
        } catch (Exception e) {
            log.error("Exception: {}", e);
        } finally {
            if (response != null) {
                log.info("Closing response");
                try {
                    response.close();
                } catch (Exception e) {
                    log.error("Error closing response");
                }
            }
        }
        return object;
    }

}
