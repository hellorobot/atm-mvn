package com.dayuanit.atm.service;

import java.util.List;

import com.dayuanit.atm.domain.BankCard;
import com.dayuanit.atm.domain.Flow;

import top.robotman.atm.flipPages.FlipPage;

public interface AtmService {
	
	BankCard openAccount(String password,String owner);
	
	void deposit(String amount, String cardNum, String password);
	
	void draw(String amount, String cardNum, String password);
	
	void transfer(String amount, String inCardNum, String outCardNum, String password);
	
	FlipPage queryFlow(String cardNum, String password,int currentPage);
	
	BankCard getBankCard(String cardNum);

}
