package com.example.demo.config;

import com.example.demo.Blog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {


    //TODO:리팩토링
    @Bean
    @Primary
    public ReactiveRedisConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory("localhost", 6379);
    }

    @Bean
    ReactiveRedisOperations<String, Blog> redisOperations(ReactiveRedisConnectionFactory connectionFactory) {
        Jackson2JsonRedisSerializer<Blog> serializer = new Jackson2JsonRedisSerializer<>(Blog.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, Blog> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, Blog> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(connectionFactory, context);
    }


}
