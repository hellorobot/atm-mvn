<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<title>ATM系统</title>

</head>

<body>
<h1 align="center">存钱界面1</h1>
	<div align="center">
		<img
			src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=827394077,4082791614&fm=27&gp=0.jpg" />

		<br>
		
		</form>
		<br>
		<form action="/bankCard/saveMoney.do" method="post">
		卡号：${ cardNum}
			<input type="hidden" name="cardNum" value="${ cardNum}">
		密码：<input type="text" name="password">
		金额：<input type="text" name="amount">
		<input type="submit" value="存钱">
		
		</form>
	</div>

</body>

<script type="text/javascript">

</script>

<jsp:include page="webFOOT.jsp"></jsp:include>
</html>