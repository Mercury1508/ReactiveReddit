package com.example.reddit.WeekendProject.repository;

import com.example.reddit.WeekendProject.model.UserPosts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserRepository extends ReactiveMongoRepository<UserPosts,Integer> {

    Flux<UserPosts> findByAuthor(String name);

    Mono<Void> deleteByAuthor(String name);

    Flux<UserPosts> findByBodyContainingIgnoreCase(String keyword);

}
