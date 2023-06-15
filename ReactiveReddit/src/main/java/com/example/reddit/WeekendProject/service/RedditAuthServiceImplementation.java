package com.example.reddit.WeekendProject.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class RedditAuthServiceImplementation implements RedditAuthService {
    private final WebClient webClient;

    public RedditAuthServiceImplementation(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<String> getAuthToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("xIf4ehAdL-hCXkY0osnS3Q", "n4bYHB4U81AV-8dHXRGVAiPVGLjrsQ");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.put("User-Agent", Collections.singletonList("Hriday"));
        String body = "grant_type=password&username=Mercury1508&password=Hriday12";

        return webClient.post()
                .uri("https://www.reddit.com/api/v1/access_token")
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(String.class)
                .map(responseBody -> {
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, Object> map = new HashMap<>();
                    try {
                        map.putAll(mapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {
                        }));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(responseBody);
                    return String.valueOf(map.get("access_token"));
                });
    }

    public Mono<String> getPosts(String user) {
        return getAuthToken()
                .flatMap(authToken -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setBearerAuth(authToken);
                    headers.put("User-Agent", Collections.singletonList("Hriday"));
                    String url = "https://oauth.reddit.com/r/" + user + "/new";
                    return webClient.get()
                            .uri(url)
                            .headers(httpHeaders -> httpHeaders.addAll(headers))
                            .retrieve()
                            .bodyToMono(String.class);
                });
    }

    public Mono<String> post(String subreddit, String title, String content, String accessToken) {
        return getAuthToken()
                .flatMap(authToken -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setBearerAuth(authToken);
                    headers.put("User-Agent", Collections.singletonList("Hriday"));
                    String url = "https://oauth.reddit.com/api/submit";
                    MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
                    param.add("kind", "self");
                    param.add("sr", subreddit);
                    param.add("text", content);
                    param.add("title", title);
                     return webClient.post()
                            .uri(url)
                            .headers(httpHeaders -> httpHeaders.addAll(headers))
                            .body(BodyInserters.fromValue(param))
                            .retrieve()
                            .bodyToMono(String.class)
                            .doOnSuccess(responseBody -> System.out.println(responseBody));
                });
    }
}
