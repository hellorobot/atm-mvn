package com.dayuanit.atm.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.atm.domain.BankCard;
import com.dayuanit.atm.domain.Flow;
import com.dayuanit.atm.domain.TransferTask;
import com.dayuanit.atm.exception.BizException;
import com.dayuanit.atm.mapper.BankCardMapper2;
import com.dayuanit.atm.mapper.FLowMapper;
import com.dayuanit.atm.service.AtmService;
import com.dayuanit.atm.service.BtmSerivice;
import com.dayuanit.atm.utils.CardUtils;
import com.dayuanit.atm.utils.MoneyUtil;

import top.robotman.atm.ajaxDTO.FlowDto;
import top.robotman.atm.flipPages.FlipPage;
@Service
public class AtmServiceImpl implements AtmService {

	@Autowired
	private BankCardMapper2 bcm;
	@Autowired
	private FLowMapper flowMapper;
	@Autowired
	private BtmSerivice btm;
	

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BankCard openAccount(String password, String ownerName) {
		BankCard bankCard = new BankCard();
		bankCard.setBalance("0.00");

		String cardNum = null;
		for (int i = 0; i < 3; i++) {
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
			throw new BizException("卡号不能为空");
		}

		bankCard.setCardNum(cardNum);
		bankCard.setPassword(password);
		bankCard.setVersion(1);
		bankCard.setOwnerName(ownerName);
		;
		int rows = bcm.addCard(bankCard);
		if (1 != rows) {
			throw new BizException("开户失败");
		}
		return bankCard;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deposit(String amount, String cardNum, String password) {

		BankCard bankCard = bcm.getBankCard(cardNum);
		if (StringUtils.isBlank(cardNum)) {
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
	@Transactional(rollbackFor = Exception.class)
	public void draw(String amount, String cardNum, String password) {
		if (StringUtils.isBlank(cardNum)) {
			throw new BizException("卡号不能为空");
		}

		if (StringUtils.isBlank(password)) {
			throw new BizException("密码不能为空");
		}

		BankCard bankCard = bcm.getBankCard(cardNum);

		if (bankCard == null) {
			throw new BizException("卡号不存在");
		}

		if (!bankCard.getPassword().equals(password)) {
			throw new BizException("密码错误");
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
	@Transactional(rollbackFor = Exception.class)
	public void transfer(String amount, String inCardNum, String outCardNum, String password) {
		BankCard outCard = bcm.getBankCard(outCardNum);

		if (null == outCard) {
			throw new BizException("转出的卡不存在");
		}

		if (outCardNum.equals(inCardNum)) {
			throw new BizException("QAQ你不能转给自己");
		}

		if (!outCard.getPassword().equals(password)) {
			throw new BizException("密码错误");
		}

		amount = CardUtils.checkAmountAndFormat(amount);

		String newBalance = MoneyUtil.sub(outCard.getBalance(), amount);

		// System.out.println("[转出：]==========" + outCardNum);
		// System.out.println("[余额：]==========" + outCard.getBalance());
		// System.out.println("[转出金额：]==========" + amount);
		// System.out.println("[金额差：]==========" + newBalance);
		if (Double.parseDouble(newBalance) < 0) {

			throw new BizException("余额不足");
		}

		outCard.setBalance(newBalance);
		int rows = bcm.modifyBalance(outCard.getCardNum(), outCard.getBalance(), outCard.getVersion());
		if (1 != rows) {
			throw new BizException("转账故障");
		}

		Flow flow = new Flow();
		flow.setAmount(amount);
		flow.setCardNum(outCardNum);
		flow.setDescript("转账");
		flow.setFlowType(3);

		rows = flowMapper.addFlow(flow);
		if (1 != rows) {
			throw new BizException("流水写入失败");
		}

		BankCard inCard = bcm.getBankCard(inCardNum);
		if (null == inCard) {
			throw new BizException("转入卡号不存在");
		}

		String inBalance = MoneyUtil.plus(inCard.getBalance(), amount);

		inCard.setBalance(inBalance);
		rows = bcm.modifyBalance(inCard.getCardNum(), inCard.getBalance(), inCard.getVersion());
		if (1 != rows) {
			throw new BizException("转入失败1");
		}

		flow = new Flow();
		flow.setAmount(amount);
		flow.setCardNum(inCardNum);
		flow.setDescript("转账");
		flow.setFlowType(4);

		rows = flowMapper.addFlow(flow);
		if (1 != rows) {
			throw new BizException("转账失败");
		}
	}

	@Override
	public FlipPage queryFlow(String cardNum, String password, int currentPage) {
		FlipPage filpPage = new FlipPage();

		if (StringUtils.isBlank(cardNum)) {
			throw new BizException("卡号不能为空");
		}

		BankCard bankCard = bcm.getBankCard(cardNum);
		if (bankCard == null) {
			throw new BizException("卡号不存在");
		}

		if (!bankCard.getPassword().equals(password)) {
			throw new BizException("密码错误");
		}

		filpPage.setCurrentPage(currentPage);
		List<Flow> list1 = flowMapper.listFlow(cardNum, filpPage.getStartNum(currentPage),
				FlipPage.EVERY_PAGE_FLOW_NUM);
		List<FlowDto> flowlist = new ArrayList<FlowDto>(list1.size());
		
		for (Flow flow : list1) {
			FlowDto dto = new FlowDto();
			
			SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String data = myFmt.format(flow.getCreateTime());
			
			dto.setAmount(flow.getAmount());
			dto.setCardNum(cardNum);
			dto.setDescript(flow.getDescript());
			dto.setId(flow.getId());
			dto.setCreateTime(data);
			
			flowlist.add(dto);
	}

		int flowsNum = flowMapper.countFlow(cardNum);

		filpPage.setFlowsNum(flowsNum);
		filpPage.setPagesNumxxx(flowsNum);

		filpPage.setObj(flowlist);
		return filpPage;
	}

	@Override
	public BankCard getBankCard(String cardNum) {
		return bcm.getBankCard(cardNum);
	}

	@Override
	public List<Flow> listFlowNearly(String username) {
		return flowMapper.listFlowNearly(username);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int newTransfer(String amount, String inCardNum, String outCardNum, String password) {
		BankCard outCard = bcm.getBankCard(outCardNum);
		BankCard inCard = bcm.getBankCard(inCardNum);
		
		if (null == outCard) {
			throw new BizException("转出的卡不存在");
		}

		if (outCardNum.equals(inCardNum)) {
			throw new BizException("QAQ你不能转给自己");
		}

		if (!outCard.getPassword().equals(password)) {
			throw new BizException("密码错误");
		}
		
		if (null == inCard) {
			throw new BizException("转入卡号不存在");
		}

		amount = CardUtils.checkAmountAndFormat(amount);
		String newBalance = MoneyUtil.sub(outCard.getBalance(), amount);
		if (Double.parseDouble(newBalance) < 0) {
			throw new BizException("余额不足");
		}

		outCard.setBalance(newBalance);
		int rows = bcm.modifyBalance(outCard.getCardNum(), outCard.getBalance(), outCard.getVersion());
		if (1 != rows) {
			throw new BizException("转账故障");
		}
//写入流水
		Flow flow = new Flow();
		flow.setAmount(amount);
		flow.setCardNum(outCardNum);
		flow.setDescript("转出");
		flow.setFlowType(3);

		rows = flowMapper.addFlow(flow);
		if (1 != rows) {
			throw new BizException("流水写入失败");
		}
//写入待转记录表
		int row = bcm.addTransferTask(0, amount, outCardNum, inCardNum);
		if (1 != row) {
			throw new BizException("转账task开启失败");
		}	
		
		return row;
	}

	@Override
	public List<TransferTask> listTransferTask(LocalDateTime time,Integer status) {

		List<TransferTask> list = bcm.qureyTransferTask(time, status);
		
		int i=0;
		for (TransferTask tt : list) {
			i++;
			System.out.println(i+"======================" + tt.getId());
		}
		return list;
	}
	
	
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int newTransferIN(String amount, String inCardNum,Integer id) {
		
		BankCard inCard = bcm.getBankCard(inCardNum);
		if (null == inCard) {
			btm.successTrans(bcm,id);
			throw new BizException("转入银行卡不存在");
		}
		
		String inBalance =  MoneyUtil.plus(inCard.getBalance(), amount);
		
		inCard.setBalance(inBalance);
		int rows = bcm.modifyBalance(inCard.getCardNum(), inCard.getBalance(), inCard.getVersion());
		if (1 != rows) {
			btm.successTrans(bcm,id);
			throw new BizException("转入id:"+id+"转账失败");
		}
		
		Flow flow = new Flow();
		flow.setAmount(amount);
		flow.setCardNum(inCardNum);
		flow.setDescript("转账收入");
		flow.setFlowType(4);
		
		rows = flowMapper.addFlow(flow);
		if (1 != rows) {
			btm.successTrans(bcm,id);
			throw new BizException("转账失败");
		}
		
		int frpws = bcm.modifyTransferTask(1, id);
		System.out.println("============"+frpws);
	return rows;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int newTransferRollback(String amount, String outCardNum, Integer id) {
		BankCard outCard = bcm.getBankCard(outCardNum);
		if (null == outCard) {
			btm.faliTrans(bcm,id);
			throw new BizException("回滚银行卡不存在");
		}
		
		String inBalance =  MoneyUtil.plus(outCard.getBalance(), amount);
		
		outCard.setBalance(inBalance);
		int rows = bcm.modifyBalance(outCard.getCardNum(), outCard.getBalance(), outCard.getVersion());
		if (1 != rows) {
			btm.faliTrans(bcm,id);
			throw new BizException("回滚失败");
		}
		
		Flow flow = new Flow();
		flow.setAmount(amount);
		flow.setCardNum(outCardNum);
		flow.setDescript("转账失败，金额回退");
		flow.setFlowType(5);
		
		rows = flowMapper.addFlow(flow);
		if (1 != rows) {
			btm.faliTrans(bcm,id);
			throw new BizException("回退失败");
		}
		
		bcm.modifyTransferTask(3, id);
	return rows;
	}
}
