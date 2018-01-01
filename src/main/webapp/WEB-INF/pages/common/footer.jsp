<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/js/myVue.js"></script>
<script src="/assets/js/theme.js"></script>
<script src="/assets/js/amazeui.min.js"></script>
<script src="/assets/js/app.js"></script>
<script type="text/javascript" src="/js/robotman.js"></script>

<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
	<div class="am-modal-dialog">
		<div class="am-modal-hd" id="alertHead"></div>
		<div class="am-modal-bd" id="alertdb"></div>
		<div class="am-modal-footer">
			<span class="am-modal-btn">确定</span>
		</div>
	</div>
</div>


<script>

	
	var ws = new WebSocket("ws://127.0.0.1:8080/WebSocketHandler.socket");

	var timer = setInterval(function() {
		if (ws.readyState == 1) {
			ws.send('waiting!....');
			//ws=new WebSocket("ws://127.0.0.1:8080/WebSocketHandler.socket");
		}
		if (ws.readyState == 3) {
			ws = new WebSocket("ws://127.0.0.1:8080/WebSocketHandler.socket");
		}
		console.log('轮询中。。。');
	}, 1000 * 30);
	//clearInterval(timer);

	var endInterval = setTimeout(function() {
		console.log('结束轮询。。。');
		clearInterval(timer);
	}, 1000 * 60 * 60);

	ws.onopen = function() {
		console.log("Connection open ...");
		ws.send('Hello Server!');
	}

	ws.onmessage = function(evt) {
		console.log("Received Message: " + evt.data);
		loadFlowTable();
		loadMessage();
	};

	ws.onclose = function(event) {
		var code = event.code;
		var reason = event.reason;
		var wasClean = event.wasClean;
		// handle close event
	};

	ws.onerror = function(event) {
		console.log("error " + event.data);
	};
//初始化加载。
	$(document).ready(function() {
		loadMessage();
	});
	
	var websocketapp = new Vue({
		el : '#head',
		data : {
			count : '0',
			messages : []

		}
	});

	function loadMessage() {
		$.post('/user/loadMessage.do', {}, function(data, status) {
		
			var messageNum = data.data.num;
			
			var list = data.data.listMessage;
			//websocketapp.messages = data.data.listMessage;
			var tmpDate=new Date().getTime();
			console.log('=====now time:'+tmpDate);
			
			for(var i=0; i < list.length ; i++){
				//共享传递，拷贝一份引用副本，比较奇怪的方式之前也没遇到过！！
				var message = list[i];
				var timdif = tmpDate - message.createtime;
				
				
			
				if(timdif>0 && timdif<60){
					message.createtime = parseInt(timdif/1000)+'秒前';
				}
				//秒
				if(timdif>60*1000){
					message.createtime=parseInt(timdif/(1000*60))+'分钟前';
				}
				//分
				if(timdif>60*60*1000){
					message.createtime=parseInt(timdif/(1000*60*60))+'小时前';
				}
				//小时
				if(timdif>24*60*60*1000){
					message.createtime=parseInt(timdif/(1000*60*60*24))+'天前';
				}
				//天
				if(timdif>7*24*60*60*1000){
					message.createtime=parseInt(timdif/(1000*60*60*24*7))+'周前';
				}
				//week
 			}
			
			websocketapp.messages = data.data.listMessage;
			
			if(messageNum == 0){
				websocketapp.count = null;
				return;
			}
			if(messageNum > 99){
				websocketapp.count = '99+';
				return;
			}
			websocketapp.count = messageNum;
		});
	}

</script>

