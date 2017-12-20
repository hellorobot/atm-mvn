<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<title>ATM系统</title>

</head>

<body bgcolor="#DC8349  ">
	<div align="center">
		<h1>注册</h1>
		<img
			src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=827394077,4082791614&fm=27&gp=0.jpg" />

		<br>
		<form id="flowForm"  method="post">
			账号：<input type="text" id="userName"> 
			密码：<input type="text"id="password"> 
			重复密码：<input type="text"id="password2"> 
		</form>
		
	<button id="regist1" onclick="regist1();">注册你的账号</button>
	</div>

</body>
<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>

<script type="text/javascript">

function ajaxfun(data,status){
	
	var ajaxDto = eval('(' + data + ')');	
	
	if(ajaxDto.flag) {
		alert("注册成功！");
		window.location.href='/';
		
	} else {
		alert(ajaxDto.message);
	}
}

	function regist1() {
		var createUser = {
			userName : $('#userName').val(),
			password : $('#password').val(),
			password2 : $('#password2').val(),
		};
		if(createUser.password!=createUser.password2){
			alert("两次密码不同，请重新输入");
			return;
		}
		
		
		$.post('/user/regist.do',createUser,ajaxfun);
	}
	




</script>

<jsp:include page="webFOOT.jsp"></jsp:include>
</html>