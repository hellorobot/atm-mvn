package top.robotman.atm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service
public class Trycache {
	 @Autowired
	 @Qualifier("redisCacheManager")
	private RedisCacheManager redisCacheManager;
	 @Autowired
	private RedisConnectionFactory jedisConnectionFactory;
	
	@Cacheable(value = "bss",key="#a+'_haha'") 
	public String trycache(String a) {
		System.out.println("RedisCacheManager!!!!!!");
		System.out.println("========redisCacheManager>>>"+redisCacheManager);
		System.out.println("========jedisConnectionFactory>>>"+jedisConnectionFactory);
		return a;
	}

}
