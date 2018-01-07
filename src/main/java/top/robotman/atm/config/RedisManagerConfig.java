package top.robotman.atm.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
@EnableCaching
public class RedisManagerConfig extends CachingConfigurerSupport{
	@Autowired
	private RedisConnectionFactory jedisConnectionFactory;
//处理如果redis服务器宕机	
	public class AppCacheErrorHandler implements CacheErrorHandler{

		@Override
		public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
			System.out.println("=====sx1===");
			System.out.println(exception);
			return;
		}

		@Override
		public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
			System.out.println("=====sx2===");
			System.out.println(exception);	
			return;
		}

		@Override
		public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
			System.out.println("=====sx3===");
			System.out.println(exception);	
			return;
		}

		@Override
		public void handleCacheClearError(RuntimeException exception, Cache cache) {
			System.out.println("=====sx4===");
			System.out.println(exception);		
			return;
		}		
	}
	@Bean
	@Override
	public CacheErrorHandler errorHandler() {
		return new AppCacheErrorHandler();
	}
	
	// 以后试试名字能不能改
		@SuppressWarnings("serial")
		@Bean(name = "redisCacheManager")
		public RedisCacheManager createCacheManager() {
//			return RedisCacheManager.create(jedisConnectionFactory); //默认管理器
		
			RedisCacheManagerBuilder builder = RedisCacheManagerBuilder.fromConnectionFactory(jedisConnectionFactory);
			Set<String> cacheNames = new HashSet<String>() {{  
		        add("user");
		    }};
			builder.initialCacheNames(cacheNames); //设置多个缓存
			
					
			return builder.build();		
		}

}
