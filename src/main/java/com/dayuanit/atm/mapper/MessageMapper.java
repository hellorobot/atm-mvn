package com.dayuanit.atm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.atm.domain.MessageForWebsocket;

public interface MessageMapper {
	//也不知道为什么可以传个message
	int addMessage(MessageForWebsocket message);
	
	int countUnReadNum(@Param("userName") String userName);

	List<MessageForWebsocket> listUnReadMessageLimit3(@Param("userName") String userName);
}
