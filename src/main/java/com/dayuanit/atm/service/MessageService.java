package com.dayuanit.atm.service;

import java.util.List;

import com.dayuanit.atm.domain.MessageForWebsocket;

public interface MessageService {
	
	public int countMessage(String userName);
	
	public List<MessageForWebsocket> listMessage(String userName);
}
