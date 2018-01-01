package top.robotman.atm.ajaxDTO;

import java.util.List;

import com.dayuanit.atm.domain.MessageForWebsocket;

public class WebSocketDTO {
	private int num;
	private List<MessageForWebsocket> listMessage;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public List<MessageForWebsocket> getListMessage() {
		return listMessage;
	}
	public void setListMessage(List<MessageForWebsocket> listMessage) {
		this.listMessage = listMessage;
	}
	

}
