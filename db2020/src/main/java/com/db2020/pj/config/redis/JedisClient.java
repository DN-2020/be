package com.db2020.pj.config.redis;
//package com.db2020.pj.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.util.Pool;
//
//public class JedisClient {
//	 @Bean
//	    public RedisConnectionFactory redisConnectionFactory() {
//	        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
//	        return lettuceConnectionFactory;
//	    }
//	    @Bean
//	    public RedisTemplate<String, Object> redisTemplate() {
//	        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//	        redisTemplate.setConnectionFactory(redisConnectionFactory());
//	        redisTemplate.setKeySerializer(new StringRedisSerializer());
//	        return redisTemplate;
//	    }
//	    @Bean
//	    public Jedis jedis() {
//	        Pool<Jedis> jedisPool;
//	        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//	        jedisPoolConfig.setMaxTotal(1000);
//	        jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 10000, null);
//
//	        return jedisPool.getResource();
//	    }
//}
