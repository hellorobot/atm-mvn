package com.dayuanit.atm.service;

import java.util.List;

import com.dayuanit.atm.domain.BankCard;
import com.dayuanit.atm.domain.User;

import top.robotman.atm.flipPages.FlipPage;

public interface UserService {
	
	User login(String username, String password);
	
	List<BankCard> getCardsPage(String username,int startPage);
	
	int regist(String username, String password);
	
	User getUser(String username);
	
	int changePSW(String username, String password);
}
