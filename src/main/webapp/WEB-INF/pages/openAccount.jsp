<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>

<head>
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<title>ATM系统</title>

</head>

<body bgcolor="#DC8349  ">
<div align="center">
<h1>賬號信息</h1>
<img src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=827394077,4082791614&fm=27&gp=0.jpg" />

		<br>
	<h2>
	卡号：${bc.cardNum }
	 --------
	余额：${bc.balance }
	</h2>
	
	<a href="toUserCenter.do" >》》》回到首頁》》》</a>
</div>

</body>

<jsp:include page="webFOOT.jsp"></jsp:include>
</html>