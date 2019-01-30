package com.example.istio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author LG95
 **/
@RestController
public class Controller {
    private static final String PATH_DELIMITER = "/";

    private final RestTemplate restTemplate = new RestTemplate();
    private final String httpbinUrl;
    private final String wiremockUrl;

    public Controller(@Value("${httpbin.url}") String httpbinUrl, @Value("${wiremock.url}") String wiremockUrl) {
        this.httpbinUrl = httpbinUrl;
        this.wiremockUrl = wiremockUrl;
    }

    @GetMapping(value = "/httpbin/{path}")
    public Object getFromHttpbin(@PathVariable String path) {
        return getFromUrl(String.join(PATH_DELIMITER, httpbinUrl, path), Object.class);
    }

    @GetMapping(value = "/httpbin/{path}/{argument}")
    public Object getFromHttpbin(@PathVariable String path, @PathVariable String argument) {
        return getFromUrl(String.join(PATH_DELIMITER, httpbinUrl, path, argument), Object.class);
    }

    @GetMapping(value = "/wiremock/{path}")
    public String getFromWiremock(@PathVariable String path) {
        return getFromUrl(String.join(PATH_DELIMITER, wiremockUrl, path), String.class);
    }

    private <T> T getFromUrl(String url, Class<T> responseClass) {
        return restTemplate.getForObject(URI.create(url), responseClass);
    }
}
