package com.dayuanit.atm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.atm.domain.BankCard;

public interface BankCardMapper2 {
	
	BankCard getBankCard(String cardNum);
	
	int addCard(BankCard bankCard);
	
	int modifyBalance(@Param("cardNum") String cardNum, @Param("balance") String balance, @Param("version") int version);
	
	List<BankCard> getBankCardList(@Param("userName")String userName,@Param("startPoint")Integer startPoint,@Param("MoveLength")Integer MoveLength);
	
	int getBankCardListNum(String userName);
}
