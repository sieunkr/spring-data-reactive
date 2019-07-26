package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {

    private final RedisProvider redisProvider;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Blog blog = new Blog();
        blog.setTotal(10);
        blog.setItems(Arrays.asList(new Blog.Item()));

        redisProvider.saveBlog(blog);

        Mono<Blog> blogMono = redisProvider.findBlogById("eddy");

        blogMono.subscribe(b -> {
            //onNext
            System.out.println(b.getTotal());
        },
        e -> {
            //onError
            System.out.println("error");
        },
        () -> {
            //onComplete
            System.out.println("completed");
        });

        System.out.println("test");

    }
}
