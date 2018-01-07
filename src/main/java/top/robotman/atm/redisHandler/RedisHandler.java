package top.robotman.atm.redisHandler;

import java.util.List;

import com.dayuanit.atm.domain.BankCard;

public interface RedisHandler {
	
	public List<BankCard> writeBankcard(String username, Integer currentPage,List<BankCard> cardlist);
	
	public List<BankCard> getBankcards(String username, Integer currentPage);

	List<BankCard> writeBankcard2(String username, Integer currentPage, List<BankCard> cardlist);

}
