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
		<h1>用戶中心</h1>
		<img
		src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=827394077,4082791614&fm=27&gp=0.jpg" width="300"  />
		<br>
		<h1>用户名：${user.username }</h1>
		<a href="http://127.0.0.1:8080">》》》注銷》》》</a> <br/> 
		
		<imgsrc="/user/openImg.do" width="200" height="200"> <br/> 
		
		<a href="/bankCard/toOpenaccount.do">開戶</a> 
		<a href="/user/upLoadIMG.do">上传照片</a>
		<a href="/user/tochangePSW.do">修改密码</a>
		<br /> ---------------------------------
		
		<table id="cardTable"></table>
		<span id="current"></span>
		<button onclick="goFirst();">首页</button>
		<button onclick="ahead();">前一頁</button>
		<button onclick="next();">后一頁</button>
		<button onclick="goLast();">末頁</button>

	</div>

</body>
<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/js/json2.js"></script>

<script type="text/javascript">
var currentPage = 1;
var total = 1;

function next() { 
	if(currentPage >= total){
		return false;
	}
	currentPage = parseInt(currentPage) + 1;
	
	loadBankcard();
}

function goFirst() { 
	currentPage = 1;
	
	loadBankcard();
}

function goLast() { 
	currentPage = total;
	
	loadBankcard();
}

function ahead() { 
	if(currentPage == 1){
		return false;
	}
	currentPage = parseInt(currentPage) - 1;
	
	loadBankcard();
}

function savaMoney(cardNum) {
	
	window.location.href='/bankCard/toSave.do?cardNum=' + cardNum;
}

function flow(cardNum) {
	
	window.location.href='/bankCard/toFlow.do?cardNum=' + cardNum;
}


function callback(data,status){	
	
	var AjaxDto = data;
	
	if(AjaxDto.flag){
		total = AjaxDto.data.pagesNum;
		var result = AjaxDto.data.obj;
		var msg = '<tr><td >银联卡号</td><td >余额</td><td>时间</td><td>操作</td></tr>';
		
		for (var i = 0; i < result.length; i++) {
			msg += '<tr>';
			msg += '<td>' + result[i].cardNum + '</td>  '
			msg += '<td>' + result[i].balance + '</td>  '
			msg += '<td>' + new Date(result[i].modifyTime).toLocaleString() + '</td>   '
			msg += '<td>' 
			msg += '<button onclick="savaMoney('+ result[i].cardNum +');">存钱</button>  ';
			msg += '<button >取钱</button>  ';
			msg += '<button onclick="flow(' + result[i].cardNum + ');">流水</button>  ';
			msg += '<button>转账</button>  ';
			msg += '<button> X </button>  ';
			msg += '</td>'
			msg += '</tr>';
		}
		
		var pageNum = currentPage + '/' + total

		$('#cardTable').html(msg);
		$('#current').html(pageNum);

	}else{
		if(AjaxDto.statusCode == 302){
			alert(AjaxDto.message);
			window.location.href='/user/toLogin.do';
		}
	}
}

function loadBankcard(){	
	var cardlist = {
			currentPage : currentPage
	};
	
	$.post('/user/loadBankcard.do',cardlist,callback);
	
}

loadBankcard();

</script>

</html>