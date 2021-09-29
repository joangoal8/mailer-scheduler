package com.mailer.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import static java.util.Objects.isNull;

@Component
public class RedisClient {

  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.port}")
  private int port;

  @Value("${spring.redis.jedis.pool.max-active}")
  private int maxActive;

  private JedisPool jedisPool;

  public Jedis getRedisConnection() {
    if (isNull(jedisPool)) {
      jedisPool = new JedisPool(configureRedisPool(), host,
        port);
    }
    return jedisPool.getResource();
  }

  private JedisPoolConfig configureRedisPool() {
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    poolConfig.setMaxTotal(maxActive);
    return poolConfig;
  }
}