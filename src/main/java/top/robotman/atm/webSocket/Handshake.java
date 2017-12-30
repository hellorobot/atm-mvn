package top.robotman.atm.webSocket;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class Handshake extends  HttpSessionHandshakeInterceptor{
	
	public Handshake() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
		// TODO Auto-generated method stub
		 System.out.println("Before Handshake");  
		super.afterHandshake(request, response, wsHandler, ex);
	}
	
	@Override
	public boolean beforeHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2,
			Map<String, Object> arg3) throws Exception {
		// TODO Auto-generated method stub
		return super.beforeHandshake(arg0, arg1, arg2, arg3);
	}
}
