package com.dayuanit.atm.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.atm.domain.BankCard;
import com.dayuanit.atm.domain.TransferTask;


public interface BankCardMapper2 {
	
	BankCard getBankCard(String cardNum);
	
	int addCard(BankCard bankCard);
	
	int modifyBalance(@Param("cardNum") String cardNum, @Param("balance") String balance, @Param("version") int version);
	
	List<BankCard> getBankCardList(@Param("userName")String userName,@Param("startPoint")Integer startPoint,@Param("MoveLength")Integer MoveLength);
	
	int getBankCardListNum(String userName);
	
	int addTransferTask(@Param("status")Integer status,@Param("amount")String amount,@Param("outCardnum")String outCardnum,@Param("inCardnum")String inCardnum);
	
	List<TransferTask> qureyTransferTask(@Param("tmptime")LocalDateTime tmptime,@Param("status")Integer status);
	
	int modifyTransferTask(@Param("status")Integer status,@Param("id")Integer id);

}
