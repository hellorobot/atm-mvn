package com.dayuanit.atm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.dayuanit.atm.db.DataBase;
import com.dayuanit.atm.domain.BankCard;
import com.dayuanit.atm.domain.User;
import com.dayuanit.atm.exception.BizException;
import com.dayuanit.atm.mapper.BankCardMapper;
import com.dayuanit.atm.mapper.BankCardMapper2;
import com.dayuanit.atm.mapper.UserMapper;
import com.dayuanit.atm.service.UserService;


import top.robotman.atm.annotation.Component;
import top.robotman.atm.flipPages.FlipPage;

public class UserServiceimpl implements UserService {
	@Autowired
	private UserMapper usermp;
	@Autowired
	private BankCardMapper2 bankCardMapper2;

	@Override
	public User getUser(String username) {
		User user = usermp.selecetUser(username);
		return user;
	}

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
	public List<BankCard> getCardsPage(String username, int currentPage) {

		FlipPage flipPage = new FlipPage();

//		List<BankCard> bankCardList = bankCardMapper2.getBankCardList(username, flipPage.getStartNum(currentPage),
//				FlipPage.EVERY_PAGE_FLOW_NUM);
		List<BankCard> bankCardList = bankCardMapper2.getBankCardList(username, null, null);
//		flipPage.setCurrentPage(currentPage);
//		flipPage.setObj(bankCardList);

		int cardsNum = bankCardMapper2.getBankCardListNum(username);
//		flipPage.setFlowsNum(cardsNum);
//		flipPage.setPagesNumxxx(cardsNum);
//		return flipPage;
		return bankCardList;
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
