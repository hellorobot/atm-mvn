package com.dayuanit.atm.service;

import com.dayuanit.atm.mapper.BankCardMapper2;

public interface BtmSerivice {
	
	public void successTrans(BankCardMapper2 bcm,Integer id);
	
	public void faliTrans(BankCardMapper2 bcm,Integer id);
}
