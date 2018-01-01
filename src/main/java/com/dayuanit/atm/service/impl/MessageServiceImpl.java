package com.dayuanit.atm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayuanit.atm.domain.MessageForWebsocket;
import com.dayuanit.atm.mapper.MessageMapper;
import com.dayuanit.atm.service.MessageService;
@Component
public class MessageServiceImpl implements  MessageService{
	@Autowired
	MessageMapper messageMapper;

	@Override
	public int countMessage(String userName) {
		int num = messageMapper.countUnReadNum(userName);
		return num;
	}

	@Override
	public List<MessageForWebsocket> listMessage(String userName) {
		List<MessageForWebsocket> list = messageMapper.listUnReadMessageLimit3(userName);		
		return list;
	}
	
	

}
