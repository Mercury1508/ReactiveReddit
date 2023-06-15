package com.example.reddit.WeekendProject.controller;

import com.example.reddit.WeekendProject.model.Children;
import com.example.reddit.WeekendProject.model.Data1;
import com.example.reddit.WeekendProject.model.Overall;
import com.example.reddit.WeekendProject.model.UserPosts;
import com.example.reddit.WeekendProject.repository.UserRepository;
import com.example.reddit.WeekendProject.service.RedditAuthService;
import com.example.reddit.WeekendProject.service.RedditAuthServiceImplementation;
import com.example.reddit.WeekendProject.service.RedditCRUD;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class TestController {

    @Autowired
    private RedditCRUD redditCRUD;

    @RequestMapping("/hello")
    public String hello() {
        return "Working!!!";
    }

    @GetMapping("/test/{subreddit}")
    public Mono<String> testing(@PathVariable String subreddit) throws JsonProcessingException {
        return redditCRUD.getPostsBySubreddit(subreddit);
    }

    @GetMapping("get/{name}")
    public Flux<UserPosts> getPostsByName(@PathVariable String name) {
        return redditCRUD.getPostsByName(name);
    }

    @DeleteMapping("delete/{name}")
    public Mono<Void> deletePostsByName(@PathVariable String name) {
        return redditCRUD.deletePostsByName(name);
    }

    @GetMapping("keyword/{word}")
    public Flux<UserPosts> getPostsByKeyword(@PathVariable String word) {
        return redditCRUD.getPostsByKeyword(word);
    }

    @GetMapping("/sort")
    public Flux<UserPosts> getSortedPosts() {
        return redditCRUD.getSortedPosts();
    }

    @GetMapping("/reddit/post")
    public Mono<String> createRedditPost() {
        return redditCRUD.createRedditPosts();
    }
}
