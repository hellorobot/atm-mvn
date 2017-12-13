package com.dayuanit.atm.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.dayuanit.atm.db.DataBase;
import com.dayuanit.atm.domain.BankCard;
import com.dayuanit.atm.domain.Flow;
import com.dayuanit.atm.exception.BizException;
import com.dayuanit.atm.mapper.BankCardMapper2;
import com.dayuanit.atm.mapper.FLowMapper;
import com.dayuanit.atm.service.AtmService;
import com.dayuanit.atm.utils.CardUtils;
import com.dayuanit.atm.utils.MoneyUtil;

import top.robotman.atm.annotation.Component;
import top.robotman.atm.flipPages.FlipPage;

public class AtmServiceImpl implements AtmService {
	
	@Autowired
	private BankCardMapper2 bcm;
	@Autowired
	private FLowMapper flowMapper;
	
	@Override
	public BankCard openAccount(String password,String ownerName) {
		BankCard bankCard = new BankCard();
		bankCard.setBalance("0.00");

			String cardNum = null;
			for (int i = 0; i < 3; i ++) {
				String tempNum = CardUtils.createCardNum();
				BankCard existBc = bcm.getBankCard(tempNum);
				if (null != existBc) {
					System.out.println("锟斤拷" + i + "锟斤拷锟斤拷");
					continue;
				}			
				cardNum = tempNum;				
				break;
			}
			
			if (null == cardNum) {
				throw new BizException("锟斤拷锟斤拷锟截革拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷");
			}
			
			bankCard.setCardNum(cardNum);
			bankCard.setPassword(password);
			bankCard.setVersion(1);
			bankCard.setOwnerName(ownerName);;
			int rows = bcm.addCard(bankCard);
			if (1 != rows) {
				throw new BizException("x'x'x");
			}		
		return bankCard;
	}

	@Override
	public void deposit(String amount, String cardNum, String password) {
					
			BankCard bankCard = bcm.getBankCard(cardNum);
			if (null == bankCard) {
				throw new BizException("卡号不能为空");
			}
			
			if (!bankCard.getPassword().equals(password)) {
				throw new BizException("密码错误");
			}
			
			amount = CardUtils.checkAmountAndFormat(amount);
			System.out.println("format=" + amount);
			
			bankCard.setBalance(MoneyUtil.plus(bankCard.getBalance(), amount));
			
			int rows = bcm.modifyBalance(bankCard.getCardNum(), bankCard.getBalance(), bankCard.getVersion());
			if (1 != rows) {
				throw new BizException("modifyBalance erro");
			}
			
			Flow flow = new Flow();
			flow.setAmount(amount);
			flow.setCardNum(cardNum);
			flow.setDescript("存款");
			flow.setFlowType(1);
			
			rows = flowMapper.addFlow(flow);
			if (1 != rows) {
				throw new BizException("flow erro");
			}			
		} 

	@Override
	public void draw(String amount, String cardNum, String password) {
		
			BankCard bankCard = bcm.getBankCard(cardNum);
			if (null == bankCard) {
				throw new BizException("drawxxxx");
			}
			
			if (!bankCard.getPassword().equals(password)) {
				throw new BizException("xxxxdraw");
			}
			
			amount = CardUtils.checkAmountAndFormat(amount);
			System.out.println("format=" + amount);
			
			String newBalance = MoneyUtil.sub(bankCard.getBalance(), amount);
			
			if (Double.parseDouble(newBalance) < 0) {
				throw new BizException("flowxxx");
			}
			
			bankCard.setBalance(newBalance);
			int rows = bcm.modifyBalance(bankCard.getCardNum(), bankCard.getBalance(), bankCard.getVersion());
			if (1 != rows) {
				throw new BizException("flowxxx");
			}
			
			Flow flow = new Flow();
			flow.setAmount(amount);
			flow.setCardNum(cardNum);
			flow.setDescript("取钱");
			flow.setFlowType(2);
			
			rows = flowMapper.addFlow(flow);
			if (1 != rows) {
				throw new BizException("flowxxx1");
			}
		}
	
	@Override
	public void transfer(String amount, String inCardNum, String outCardNum, String password) {
			BankCard outCard = bcm.getBankCard(outCardNum);
			
			if (null == outCard) {
				throw new BizException("锟斤拷锟叫匡拷锟脚诧拷锟斤拷锟节伙拷锟斤拷锟诫不锟斤拷确");
			}
			
			if (!outCard.getPassword().equals(password)) {
				throw new BizException("锟斤拷锟叫匡拷锟脚诧拷锟斤拷锟节伙拷锟斤拷锟诫不锟斤拷确");
			}
			
			amount = CardUtils.checkAmountAndFormat(amount);
			System.out.println("format=" + amount);
			
			String newBalance = MoneyUtil.sub(outCard.getBalance(), amount);
			
			if (Double.parseDouble(newBalance) < 0) {
				throw new BizException("锟斤拷畈伙拷悖拷薹锟阶拷锟�");
			}
			
			outCard.setBalance(newBalance);
			int rows = bcm.modifyBalance(outCard.getCardNum(), outCard.getBalance(), outCard.getVersion());
			if (1 != rows) {
				throw new BizException("转锟斤拷失锟斤拷");
			}
			
			Flow flow = new Flow();
			flow.setAmount(amount);
			flow.setCardNum(outCardNum);
			flow.setDescript("杞处");
			flow.setFlowType(3);
			
			rows = flowMapper.addFlow(flow);
			if (1 != rows) {
				throw new BizException("锟斤拷锟斤拷锟剿э拷锟�");
			}
			
			//转锟斤拷锟剿伙拷锟斤拷锟斤拷
			BankCard inCard = bcm.getBankCard(inCardNum);
			if (null == inCard) {
				throw new BizException("锟斤拷锟叫匡拷锟脚诧拷锟斤拷锟斤拷");
			}
			
			String inBalance =  MoneyUtil.plus(inCard.getBalance(), amount);
			
			inCard.setBalance(inBalance);
			rows = bcm.modifyBalance(inCard.getCardNum(), inCard.getBalance(), inCard.getVersion());
			if (1 != rows) {
				throw new BizException("转锟斤拷失锟斤拷");
			}
			
			flow = new Flow();
			flow.setAmount(amount);
			flow.setCardNum(inCardNum);
			flow.setDescript("鏀惰处");
			flow.setFlowType(4);
			
			rows = flowMapper.addFlow(flow);
			if (1 != rows) {
				throw new BizException("鏀惰处澶辫触");
			}
	}
	
	@Override
	public FlipPage queryFlow(String cardNum, String password, int currentPage) {
		FlipPage filpPage = new FlipPage();

		BankCard bankCard = bcm.getBankCard(cardNum);
		if (null == bankCard) {
			throw new BizException("queryFlow Exception1");
		}

		if (!bankCard.getPassword().equals(password)) {
			throw new BizException("queryFlow Exception2");
		}

		filpPage.setCurrentPage(currentPage);
		List<Flow> list1 = flowMapper.listFlow(cardNum,filpPage.getStartNum(currentPage),FlipPage.EVERY_PAGE_FLOW_NUM);
		
		int flowsNum = flowMapper.countFlow(cardNum);
		
		filpPage.setFlowsNum(flowsNum);
		filpPage.setPagesNumxxx(flowsNum);
		
		filpPage.setObj(list1);
		return filpPage;

	}
	
	@Override
	public BankCard getBankCard(String cardNum) {
		return bcm.getBankCard(cardNum);
	}
}
