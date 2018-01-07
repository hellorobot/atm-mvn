package com.dayuanit.atm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayuanit.atm.domain.BankCard;
import com.dayuanit.atm.domain.User;
import com.dayuanit.atm.exception.BizException;
import com.dayuanit.atm.mapper.BankCardMapper2;
import com.dayuanit.atm.mapper.UserMapper;
import com.dayuanit.atm.service.UserService;


import top.robotman.atm.flipPages.FlipPage;
import top.robotman.atm.redisHandler.RedisHandler;
@Service
public class UserServiceimpl implements UserService {
	@Autowired
	private UserMapper usermp;
	@Autowired
	private BankCardMapper2 bankCardMapper2;
	@Autowired
	private RedisHandler redisHandler;

	@Override
	public User getUser(String username) {
		User user = usermp.selecetUser(username);
		return user;
	}

	@Override
	public int regist(String username, String password) throws BizException {
		
		if (StringUtils.isBlank(username)) {
			throw new BizException("用户名不能为空");
		}

		int row = usermp.registUser(username, password);
		return row;
	}

	@Override
	public User login(String username, String password) throws BizException {
		
		if (StringUtils.isBlank(username)) {
			throw new BizException("用户名不能为空");
		}
		
		User user = usermp.selecetUser(username);
		System.out.println(user);
		if (user == null) {
			throw new BizException("用户名不存在");
		}

		if (!user.getPassword().equals(password)) {
			throw new BizException("用户名或密码错误");
		}

		return user;
	}

	@Override
	public List<BankCard> getCardsPage(String username, Integer currentPage) {
		List<BankCard> bankCardListByRedis = null;
		//先查redis吧
		try {
			bankCardListByRedis =  redisHandler.getBankcards(username, currentPage);
		}catch(Exception e) {
			e.printStackTrace();
		}
				
		//如果为空，就查出来，并且写入缓存（如果可以简易开一个线程来写缓存，不然太麻烦）
		if(null == bankCardListByRedis) {
			
			FlipPage flipPage = new FlipPage();
			List<BankCard> bankCardList = bankCardMapper2.getBankCardList(username, null, null);
			int cardsNum = bankCardMapper2.getBankCardListNum(username);
			//将获得的东西存入redis
			try {
				redisHandler.writeBankcard(username, currentPage, bankCardList);
			}
			catch(Exception e) {
				e.printStackTrace();
			}		
			return bankCardList;		
		}
		return bankCardListByRedis;	
	}

	@Override
	public int changePSW(String username, String password) {
		int rows = usermp.changePSW(username,password);	
		return rows;
	}

	//
	// public static void main(String[] args) {
	// int row = new UserServiceimpl().regist("qwerw", "qwer");
	// System.out.println(row);
	// }

}
