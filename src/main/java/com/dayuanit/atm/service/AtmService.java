package com.dayuanit.atm.service;

import java.time.LocalDateTime;
import java.util.List;

import com.dayuanit.atm.domain.BankCard;
import com.dayuanit.atm.domain.Flow;
import com.dayuanit.atm.domain.TransferTask;

import top.robotman.atm.flipPages.FlipPage;

public interface AtmService {
	
	BankCard openAccount(String password,String owner);
	
	void deposit(String amount, String cardNum, String password);
	
	void draw(String amount, String cardNum, String password);
	
	void transfer(String amount, String inCardNum, String outCardNum, String password);
	
	int newTransfer(String amount, String inCardNum, String outCardNum, String password);
	
	int newTransferIN(String amount, String inCardNum,Integer id);
	
	int newTransferRollback(String amount, String outCardNum,Integer id);
	
	FlipPage queryFlow(String cardNum, String password,int currentPage);
	
	BankCard getBankCard(String cardNum);
	
	List<Flow> listFlowNearly(String username);
	
	List<TransferTask> listTransferTask(LocalDateTime time,Integer status);
	
	

}
