package com.dayuanit.atm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.atm.domain.Flow;

public interface FLowMapper {
	
	int addFlow(Flow flow);
	
	List<Flow> listFlow(@Param("cardNum")String cardNum,@Param("currentPage")int currentPage,@Param("eachPageFlowsNum")int eachPageFlowsNum);
	
	int countFlow(@Param("cardNum")String cardNum);
	
	List<Flow> listFlowNearly(String ownername);

}
