<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<jsp:include page="webHEAD.jsp"></jsp:include>

<body bgcolor="#DC8349  ">
<div align="center">
<h1>流水信息表</h1>
<img src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=827394077,4082791614&fm=27&gp=0.jpg" />

		<br>
		<form id="flowForm" action="/bankCard/qureyFlow.do"  method="post">
		卡号：${cardNum}
			<input type="hidden" name="cardNum" value="${cardNum}">
		密码：<input type="text" name="password" value="${password}">
		<input type="hidden" id="currentPage"  name="currentPage" value="1">
		<input type="submit" value="查询流水">
		
		</form>
		
		<form id="exportForm" action="/bankCard/downFlow.do"  method="post">
			<input type="hidden" name="cardNum" value="${cardNum}">
			<input type="hidden" name="password" value="${password}">		
		</form>
		
		<button onclick="downflow();">下载流水</button>

		

		<table>
			<tr>
				<td>卡号    </td>
				<td>金额    </td>
				<td>备注     </td>	
				<td>时间     </td>
			
			</tr>
			
			<c:forEach items="${flipPage.obj}" var="flow">
				<tr>
				<td>${flow.cardNum}    </td>
				<td>${flow.amount}   </td>
				<td>${flow.descript }   </td>
				<td><fmt:formatDate value="${flow.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>    </td>
			
			</tr>
			</c:forEach>
			
			<c:if test="${not empty flipPage}">
				<button onclick="goFirst();">首页</button>
				<button onclick="ahead();">前一頁</button>
				<button onclick="next();">后一頁</button>
				<button onclick="goLast();">末頁</button>
				${flipPage.currentPage}/${flipPage.pagesNum}
				
				
			</c:if>
				
		</table>
			
	<a href="/user/toUserCenter.do" >》》》回到首頁》》》</a>
</div>

</body>

<script type="text/javascript">
var currentPage = '${flipPage.currentPage}';
var total = ${flipPage.pagesNum};

function next() { 
	if(currentPage == total){
		return false;
	}
	currentPage = parseInt(currentPage) + 1;
	document.getElementById("currentPage").value= currentPage;
	document.getElementById("flowForm").submit();
}

function goFirst() { 

	currentPage = 1;
	document.getElementById("currentPage").value= currentPage;
	document.getElementById("flowForm").submit();
}

function goLast() { 
	currentPage = total;
	document.getElementById("currentPage").value= currentPage;
	document.getElementById("flowForm").submit();
}

function ahead() { 
	if(currentPage == 1){
		return false;
	}
	currentPage = parseInt(currentPage) - 1;
	document.getElementById("currentPage").value= currentPage;
	document.getElementById("flowForm").submit();
}

function downflow() { 
	document.getElementById("exportForm").submit();
	alert("down");
}



</script>

<jsp:include page="webFOOT.jsp"></jsp:include>
</html>