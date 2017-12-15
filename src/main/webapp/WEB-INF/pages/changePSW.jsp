<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<jsp:include page="webHEAD.jsp"></jsp:include>

<body>
	<div align="center">
		<h2>修改密码</h2>
		
		<form action="" method="post">
		用户名:${user.username}<br />	
		<input type="hidden"  value="${user.password}" id = "realpassword">
		<input type="hidden"  value="${user.username}" id = "username">
		当前密码 : <input type="text"  id="tmpPSW" name="tmpPSW"><br/>
		修改密码 : <input type="password" id="changePSW01"><br/>
		重复密码 : <input type="password" id="changePSW02"><br />
		</form>
		<button onclick="changePSW();">修改</button>
		<button onclick="forgetPSW();">忘记密码</button>

	</div>

</body>

<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/js/json2.js"></script>

<script type="text/javascript">

function changePSW() {
	
	var realpassword = $('#realpassword').val();
	var tmpPSW = $('#tmpPSW').val();
	
	var changePSW01 = $('#changePSW01').val();	
	var changePSW02 = $('#changePSW02').val();
// 	if(realpassword != tmpPSW){
// 		alert("当前密码错误");
// 		return;
// 	}	
	if(tmpPSW == changePSW01){
		alert("逗我呢");
		return;
	}
	
	if(changePSW01 != changePSW02){
		alert("两次密码不相同，请确认后再提交");
		return;
	}
	
	if(changePSW01 == null){
		alert("密碼不能爲空");
		return;
	}
	
	var changeform = {				
			tmpPSW : $('#tmpPSW').val(),
			changePSW01 : $('#changePSW01').val(),
			changePSW02 : $('#changePSW02').val()
		};
	
	$.post('/user/changePSW.do',changeform,callback);
}

function callback(data,status) {
	var AjaxDto = data;
	if(AjaxDto.flag == false){
		alert(AjaxDto.message);
		return;
	}
	
	alert(AjaxDto.message);
	window.location.href='/user/toLogin.do';
	return;
}

function forgetPSW() {
	alert("你忘记了我能怎么办");
}



</script>
<jsp:include page="webFOOT.jsp"></jsp:include>
</html>