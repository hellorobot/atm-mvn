package top.robotman.atm.redisHandler;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.dayuanit.atm.domain.BankCard;


@Component
public class RedisHandlerImpl implements RedisHandler{
	
	@Autowired
	private RedisTemplate<String, String> template;

	@Resource(name="redisTemplate")
	private ValueOperations<String, List<BankCard>> cardTemplate;
//	
//	@Resource(name="redisTemplate")
//	private ValueOperations<String, List<FLowTenDTO>> flowTemplate;


	@Override
	public List<BankCard> getBankcards(String username, Integer currentPage) {
		// TODO Auto-generated method stub
		List<BankCard> cardlist = cardTemplate.get("bankcard::" + username);
		System.out.println("===从缓存读===");	
		return cardlist;
	}

	@Override
	@Cacheable(value="bankcard",key="#username")
	public List<BankCard> writeBankcard(String username, Integer currentPage, List<BankCard> cardlist) {
		// TODO Auto-generated method stub
		//cardTemplate.set("bankcard:" + username + ":" + currentPage, cardlist,10, TimeUnit.SECONDS);	
		System.out.println("》》写入缓存》》");
		return cardlist;
		
	}
	
	@Override
	//@Cacheable(value="",key="")
	public List<BankCard> writeBankcard2(String username, Integer currentPage, List<BankCard> cardlist) {
		// TODO Auto-generated method stub
		cardTemplate.set("bankcard:" + username + ":" + currentPage, cardlist,10, TimeUnit.SECONDS);	
		System.out.println("》》写入缓存》》");
		return cardlist;
		
	}
	
	
	
	
}
