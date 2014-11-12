package me.itsoho.rbac.config;

import me.itsoho.shiro.cluster.JedisManager;

//import org.crazycake.shiro.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {
	@Value("${jedis.host}")
	private String host;
	
	@Value("${jedis.port}")
	private int port = 6379;
	
	@Value("${jedis.timeout}")
	private int timeout = 2000;
	
	@Value("${jedis.password}")
	private String password;
	
	@Bean
	@ConfigurationProperties(prefix="jedis.pool.config")
	public JedisPoolConfig jedisPoolConfig(){
		return new JedisPoolConfig();
	}
	
	@Bean
	@Autowired
	public JedisManager jedisManager(JedisPoolConfig poolConfig){
		JedisManager ret = new JedisManager();
		ret.setJedisPool(new JedisPool( poolConfig,  host,  port, timeout, password));
		return ret;
	}
	
//	@Bean
//	@ConfigurationProperties(prefix="jedis")
//	public RedisManager redisManager(){
//		return new RedisManager();
//	}
}
