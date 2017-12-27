package com.dayuanit.atm.domain;

import java.time.LocalDateTime;

public class TransferTask {
	Integer id;
	Integer status;
	String amount;
	String outCardnum;
	String inCardnum;
	LocalDateTime createtime;
	LocalDateTime modifytime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOutCardnum() {
		return outCardnum;
	}
	public void setOutCardnum(String outCardnum) {
		this.outCardnum = outCardnum;
	}
	public String getInCardnum() {
		return inCardnum;
	}
	public void setInCardnum(String inCardnum) {
		this.inCardnum = inCardnum;
	}
	public LocalDateTime getCreatetime() {
		return createtime;
	}
	public void setCreatetime(LocalDateTime createtime) {
		this.createtime = createtime;
	}
	public LocalDateTime getModifytime() {
		return modifytime;
	}
	public void setModifytime(LocalDateTime modifytime) {
		this.modifytime = modifytime;
	}
	

}
