package com.example.reddit.WeekendProject.service;

import com.example.reddit.WeekendProject.model.Children;
import com.example.reddit.WeekendProject.model.Data1;
import com.example.reddit.WeekendProject.model.Overall;
import com.example.reddit.WeekendProject.model.UserPosts;
import com.example.reddit.WeekendProject.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

@Service
public class RedditCRUDImplementation implements RedditCRUD{

    @Autowired
    private RedditAuthService redditAuthService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<String> getPostsBySubreddit(String subreddit) {
        return redditAuthService.getPosts(subreddit)
                .flatMap(response -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Overall responseObj = null;
                    try {
                        responseObj = objectMapper.readValue(response, Overall.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    Data1 d1 = responseObj.getData();
                    List<Children> c1 = d1.getChildren();
                    Flux<UserPosts> saveFlux = Flux.fromIterable(c1)
                            .flatMap(c -> {
                                UserPosts uc = c.getData();
                                Date date = new Date();
                                date.setTime((long) uc.getCreated() * 1000);
                                UserPosts uc2 = new UserPosts(uc.getAuthor(), uc.getLink_title(), uc.getBody(), uc.getCreated());
                                uc2.setDate_of_comment(date);
                                return userRepository.save(uc2);
                            });
                    return saveFlux.then(Mono.just(response));
                });
    }

    @Override
    public Flux<UserPosts> getPostsByName(String name) {
        return userRepository.findByAuthor(name);
    }

    @Override
    public Mono<Void> deletePostsByName(String name) {
        return userRepository.deleteByAuthor(name);
    }

    @Override
    public Flux<UserPosts> getPostsByKeyword(String word) {
        return userRepository.findByBodyContainingIgnoreCase(word);
    }

    @Override
    public Flux<UserPosts> getSortedPosts() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "date_of_comment"));
    }

    @Override
    public Mono<String> createRedditPosts() {
        return redditAuthService.getAuthToken()
                .flatMap(accessToken -> {
                    return redditAuthService.post("test", "Test987", "reactive code test", accessToken)
                            .flatMap(response -> {
                                return getPostsBySubreddit("test")
                                        .flatMap(resp -> {
                                            return Mono.just("Post created successfully.");
                                        });
                            })
                            .onErrorResume(e -> Mono.just("Failed to create post."));
                });
    }


}
