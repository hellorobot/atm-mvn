<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<jsp:include page="webHEAD.jsp"></jsp:include>

<body bgcolor="#DC8349  ">
<div align="center">
<h1>登陆</h1>
<img src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=827394077,4082791614&fm=27&gp=0.jpg" />

		<br>
		<form id="flowForm" method="post">
		账号：<input type="text" id="userName">
		密码：<input type="text" id="password">		
		</form>
		<button id="login" onclick="login();">登陆</button>
		<button id="regist" onclick="toRegist();">注册</button>

</div>

</body>

<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
<script type="text/javascript">


	
	function ajaxFunction(data,status){
		var ajaxDto =  data;
		
		if(ajaxDto.flag){
			alert("login success!");
			
			window.location.href='/user/toUserCenter.do';
			
		}else{
			alert(ajaxDto.message);
		}
	}

	function login(){
		var user = {
			userName : $('#userName').val(),
			password : $('#password').val()
		};
		
		$.post("/user/login.do",user,ajaxFunction);
		
	}

	function toRegist(){
		 window.location.href="/user/toRegist.do"; 
	}


</script>

<jsp:include page="webFOOT.jsp"></jsp:include>
</html>