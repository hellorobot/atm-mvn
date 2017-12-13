<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<title>ATM系统</title>

</head>

<body bgcolor="#DC8349  ">
<div align="center">
<h1>登陆</h1>
<img src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=827394077,4082791614&fm=27&gp=0.jpg" />

		
	
			
			<% 
				request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
			%>

</div>

</body>

<script type="text/javascript">



</script>


</html>