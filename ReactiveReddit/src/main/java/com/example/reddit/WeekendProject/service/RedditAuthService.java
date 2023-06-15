package com.example.reddit.WeekendProject.service;

import reactor.core.publisher.Mono;

public interface RedditAuthService {

    Mono<String> getAuthToken();

    Mono<String> getPosts(String user);

    Mono<String> post(String subreddit, String title, String content, String accessToken);

}
