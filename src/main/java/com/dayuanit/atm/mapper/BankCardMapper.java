package com.dayuanit.atm.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.atm.domain.BankCard;

public interface BankCardMapper {
	
	BankCard getBankCard5(Map<String, Object> param);
	
	BankCard getBankCard6(@Param("cardNum") String cardNum, @Param("id") int id);
	
	int addBankCard(BankCard bankCard);
	
	BankCard getBankCard7(String cardNum);
	
	BankCard getBankCard8(String cardNum);

}
