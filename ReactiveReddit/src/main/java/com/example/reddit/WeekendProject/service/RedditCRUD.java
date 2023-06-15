package com.example.reddit.WeekendProject.service;

import com.example.reddit.WeekendProject.model.UserPosts;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RedditCRUD{

    Mono<String> getPostsBySubreddit(String subreddit);

    Flux<UserPosts> getPostsByName(String name);

    Mono<Void> deletePostsByName(String name);

    Flux<UserPosts> getPostsByKeyword(String word);

    Flux<UserPosts> getSortedPosts();

    Mono<String> createRedditPosts();

}
