package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RedisProvider {

    private final ReactiveRedisOperations<String, Blog> blogReactiveRedisOperations;

    public Mono<Blog> findBlogById(String name){
        return blogReactiveRedisOperations.opsForValue().get("test:" + name );
    }

    public Mono<Void> saveBlog(Blog blog){
        blogReactiveRedisOperations.opsForValue().set("test:eddy", blog).subscribe();

        return Mono.empty();
    }

}
