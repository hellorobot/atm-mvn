package top.robotman.atm.webSocket;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.dayuanit.atm.domain.MessageForWebsocket;
import com.dayuanit.atm.domain.User;
import com.dayuanit.atm.service.MessageService;

import top.robotman.atm.ajaxDTO.WebSocketDTO;
@Component("WebSocketHandler")
public class WebSocketHandler extends TextWebSocketHandler{
	@Autowired
	private MessageService msgservice;
	
	 private static Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
	 
	 private static final Map<String,WebSocketSession> socketSessionMap = new ConcurrentHashMap<>();

	@Override
protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	// TODO Auto-generated method stub
	super.handleTextMessage(session, message);
	
	System.out.println("===传来的数据"+message);
}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("=============链接关闭");
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("=========服务器端Websocket连接成功");
		
		Object obj = session.getAttributes().get("user");
		if (null == obj) {
			System.out.println("没有session");
			return;
		}

		User user = (User) obj;

		socketSessionMap.put(user.getUsername(), session);
	}
	
//虚假的传一个值给客户端，形成事件。	
	public void sendMessage(String userName) {
		WebSocketSession session = socketSessionMap.get(userName);
		
//		int num = msgservice.countMessage(userName);
//		List<MessageForWebsocket> listMessage = msgservice.listMessage(userName);
//		WebSocketDTO msg = new WebSocketDTO();
//		msg.setListMessage(listMessage);
//		msg.setNum(num);
		
		try {
			session.sendMessage(new TextMessage("服务器响应：sx"));
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return;		
	}
	
	public WebSocketDTO sendMessage2(String userName) {
		int num = msgservice.countMessage(userName);
		List<MessageForWebsocket> listMessage = msgservice.listMessage(userName);
		WebSocketDTO msg = new WebSocketDTO();
		msg.setListMessage(listMessage);
		msg.setNum(num);
		return msg;
	}
	

	

}
