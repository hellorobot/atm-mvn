<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Amaze UI Admin index Examples</title>
<meta name="description" content="这是一个 index 页面">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="/assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<script src="/assets/js/echarts.min.js"></script>
<link rel="stylesheet" href="/assets/css/amazeui.min.css" />
<link rel="stylesheet" href="/assets/css/amazeui.datatables.min.css" />
<link rel="stylesheet" href="/assets/css/app.css">
<script src="/js/jquery-3.2.1.js"></script>

</head>

<body data-type="index">
	<script src="/assets/js/theme.js"></script>
	<div class="am-g tpl-g">
		<!-- 头部 -->
		<header>
			<!-- logo -->
			<div class="am-fl tpl-header-logo">
				<a href="javascript:;"><img src="/assets/img/logo.png" alt=""></a>
			</div>
			<!-- 右侧内容 -->
			<div class="tpl-header-fluid">
				<!-- 侧边切换 -->

				<!-- 搜索 -->

				<!-- 其它功能-->
				<div class="am-fr tpl-header-navbar">
					<ul>
						<!-- 欢迎语 -->
						<li class="am-text-sm tpl-header-navbar-welcome"><a
							href="javascript:;">欢迎你, <span id="HeadUsername" title="sx"></span>
						</a></li>

						<!-- 新邮件 -->

						<!-- 新提示 -->

						<!-- 退出 -->
						<li class="am-text-sm"><a href="javascript:;"> <span
								class="am-icon-sign-out"></span> 退出
						</a></li>
					</ul>
				</div>
			</div>

		</header>
		<!-- 风格切换 -->

		<!-- 侧边导航栏 -->
		<div class="left-sidebar">
			<!-- 用户信息 -->
			<div class="tpl-sidebar-user-panel">
				<div class="tpl-user-panel-slide-toggleable">


					<div class="am-form-group am-form-file">
						<div class="tpl-user-panel-profile-picture">
							<img src="/assets/img/user04.png" alt="">
						</div>

						<input id="doc-form-file" type="file" multiple="">
					</div>





					<a href="javascript:;" class="tpl-user-panel-action-link"> <span
						class="am-icon-pencil"></span> 账号设置
					</a>
				</div>
			</div>

			<!-- 菜单 -->
			<ul class="sidebar-nav">

				<li class="sidebar-nav-link"><a href="index.html"
					class="active"> <i class="am-icon-home sidebar-nav-link-logo"></i>
						首页
				</a></li>
				<li class="sidebar-nav-link"><a href="openaccount.html"> <i
						class="am-icon-wpforms sidebar-nav-link-logo"></i> 开户
				</a></li>
				<li class="sidebar-nav-link"><a href="draw.html"> <i
						class="am-icon-wpforms sidebar-nav-link-logo"></i> 取款
				</a></li>
				<li class="sidebar-nav-link"><a href="deposit.html"> <i
						class="am-icon-wpforms sidebar-nav-link-logo"></i> 存款

				</a></li>
				<li class="sidebar-nav-link"><a href="transfer.html"> <i
						class="am-icon-wpforms sidebar-nav-link-logo"></i> 转账

				</a></li>

				<li class="sidebar-nav-link"><a href="flow.html"> <i
						class="am-icon-bar-chart sidebar-nav-link-logo"></i> 流水

				</a></li>

			</ul>
		</div>


		<!-- 内容区域 -->
		<div class="tpl-content-wrapper">

			<div class="row-content am-cf">
				<div class="row  am-cf" id="cardlist">

					<div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
						<div class="widget widget-primary am-cf">
							<div class="widget-statistic-header">6222*****196</div>
							<div class="widget-statistic-body">
								<div class="widget-statistic-value">￥27,294</div>
								<div class="widget-statistic-description"></div>
								<span class="widget-statistic-icon am-icon-credit-card-alt"></span>
							</div>
						</div>
					</div>

					<div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
						<div class="widget widget-primary am-cf">
							<div class="widget-statistic-header">6222*****196</div>
							<div class="widget-statistic-body">
								<div class="widget-statistic-value">￥27,294</div>
								<div class="widget-statistic-description"></div>
								<span class="widget-statistic-icon am-icon-credit-card-alt"></span>
							</div>
						</div>
					</div>
					<div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
						<div class="widget widget-primary am-cf">
							<div class="widget-statistic-header">6222*****196</div>
							<div class="widget-statistic-body">
								<div class="widget-statistic-value">￥27,294</div>
								<div class="widget-statistic-description"></div>
								<span class="widget-statistic-icon am-icon-credit-card-alt"></span>
							</div>
						</div>
					</div>





				</div>



				<div class="am-u-sm-12 am-u-md-12 am-u-lg-6">
					<div class="widget am-cf">
						<div class="widget-head am-cf">
							<div class="widget-title am-fl">最近十笔流水</div>
							<div class="widget-function am-fr"></div>
						</div>
						<div class="widget-body  widget-body-lg am-fr">

							<table width="100%"
								class="am-table am-table-compact am-table-bordered am-table-radius am-table-striped tpl-table-black "
								id="flowTable">
								<thead>
									<tr>
										<th>卡號</th>
										<th>金額</th>
										<th>时间</th>
										<th>备注</th>

									</tr>
								</thead>
								<tbody>
									<tr class="gradeX">
										<td>Amaze UI 模式窗口</td>
										<td>张鹏飞</td>
										<td>2016-09-26</td>

									</tr>
									<tr class="even gradeC">
										<td>有适配微信小程序的计划吗</td>
										<td>天纵之人</td>
										<td>2016-09-26</td>

									</tr>
									<tr class="gradeX">
										<td>请问有没有amazeui 分享插件</td>
										<td>王宽师</td>
										<td>2016-09-26</td>

									</tr>

									<!-- more data -->
								</tbody>
							</table>

						</div>
					</div>
				</div>
			</div>



		</div>
	</div>
	</div>
	</div>

	<script src="/assets/js/amazeui.min.js"></script>
	<script src="/assets/js/amazeui.datatables.min.js"></script>
	<script src="/assets/js/dataTables.responsive.min.js"></script>
	<script src="/assets/js/app.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {

			//document.getElementById("HeadUsername").innerHTML='${user.username}';
			$('#HeadUsername').html('${user.username}');

			loadBankcard();
			loadFlowTable();
		});

		function callback(data, status) {
			if (data.flag) {
				var cardlist = data.data;
				alert(cardlist.length);
				var msg = "";
				var displayNum = 0;
				if (cardlist.length >= 6) {
					displayNum = 6;
				} else {
					displayNum = cardlist.length;
				}
				for (var i = 0; i < displayNum; i++) {
					var card = cardlist[i];
					msg += '<div class="am-u-sm-12 am-u-md-6 am-u-lg-4"><div class="widget widget-primary am-cf"><div class="widget-statistic-header">';
					msg += card.cardNum + '</div>';
					msg += '<div class="widget-statistic-body"><div class="widget-statistic-value">';
					msg += '￥' + card.balance + '</div>';
					msg += '<div class="widget-statistic-description"></div><span class="widget-statistic-icon am-icon-credit-card-alt"></span></div></div></div>';
				}
				$('#cardlist').html(msg);
			} else {
				if (data.statusCode == 302) {
					alert(AjaxDto.message);
					window.location.href = '/user/toLogin.do';
				}
			}
		}

		function loadBankcard() {
			$.post('/user/loadBankcard.do', {}, callback);

		}

		function loadFlowTable() {
			$.post('/bankCard/loadNearFlow.do', {}, callback2);

		}

		function callback2(data, status) {
			if (data.flag) {
				var flowlist = data.data;
				var msg = "<thead><tr><th>卡號</th><th>金額</th><th>时间</th><th>备注</th></tr></thead><tbody>";
				for (var i = 0; i < flowlist.length; i++) {
					var flow = flowlist[i];
					msg += '<tr class="gradeX"><td>';
					msg += flow.cardNum +'</td><td>';
					msg += flow.amount +'</td><td>';
					msg += (new Date(flow.createTime)).format("yyyy-MM-dd hh:mm:ss") + '</td><td>';
					msg += flow.descript +'</td></tr>';
				}
				msg += '</tbody>';
				$('#flowTable').html(msg);
			} else {
				if (data.statusCode == 302) {
					alert(AjaxDto.message);
					window.location.href = '/user/toLogin.do';
				}
			}
		}
		<!--时间格式转换，有空再看-->
		Date.prototype.format = function(fmt) { 
		     var o = { 
		        "M+" : this.getMonth()+1,                 //月份 
		        "d+" : this.getDate(),                    //日 
		        "h+" : this.getHours(),                   //小时 
		        "m+" : this.getMinutes(),                 //分 
		        "s+" : this.getSeconds(),                 //秒 
		        "q+" : Math.floor((this.getMonth()+3)/3), //季度 
		        "S"  : this.getMilliseconds()             //毫秒 
		    }; 
		    if(/(y+)/.test(fmt)) {
		            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
		    }
		     for(var k in o) {
		        if(new RegExp("("+ k +")").test(fmt)){
		             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
		         }
		     }
		    return fmt; 
		} 
		
	</script>

</body>

</html>