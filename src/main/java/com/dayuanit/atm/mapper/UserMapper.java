package com.dayuanit.atm.mapper;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.atm.domain.User;

public interface UserMapper {
	
	User selecetUser(String username);
	
	int registUser(@Param("username")String username,@Param("password")String password);

}
