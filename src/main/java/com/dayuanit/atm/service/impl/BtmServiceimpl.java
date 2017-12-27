package com.dayuanit.atm.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.atm.mapper.BankCardMapper2;
import com.dayuanit.atm.service.BtmSerivice;
@Service
public class BtmServiceimpl implements BtmSerivice{
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void successTrans(BankCardMapper2 bcm,Integer id) {
		//System.out.println("===========sxxxxxx==========");
		bcm.modifyTransferTask(2, id);
		//System.out.println("===========!!!!!!!==========");
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void faliTrans(BankCardMapper2 bcm,Integer id) {
		bcm.modifyTransferTask(4, id);
	}
	
}
