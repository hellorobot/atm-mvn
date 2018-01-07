<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>atm</title>
<meta name="description" content="这是一个 index 页面">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="/assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="/assets/css/amazeui.min.css" />
<link rel="stylesheet" href="/assets/css/amazeui.datatables.min.css" />
<link rel="stylesheet" href="/assets/css/app.css">


</head>

<body data-type="login">

	<div class="am-g tpl-g">
		<!-- 风格切换 -->
		<div class="tpl-skiner">
			<div class="tpl-skiner-toggle am-icon-cog"></div>
			<div class="tpl-skiner-content">
				<div class="tpl-skiner-content-title">选择主题</div>
				<div class="tpl-skiner-content-bar">
					<span class="skiner-color skiner-white" data-color="theme-white"></span>
					<span class="skiner-color skiner-black" data-color="theme-black"></span>
				</div>
			</div>
		</div>
		<div class="tpl-login">
			<div class="tpl-login-content">
				<div class="tpl-login-logo"></div>



				<form class="am-form tpl-form-line-form">
					<div class="am-form-group">
						<input type="text" class="tpl-form-input" id="userName"
							placeholder="请输入账号">

					</div>

					<div class="am-form-group">
						<input type="password" class="tpl-form-input" id="password"
							placeholder="请输入密码">

		



					</div>
					<div   class="am-u-sm-6 am-padding-left-0 am-form-group" >
						 <img id="codeImg" alt=""
							src="/checkcode/getCheckcode.do"  height="30" width="135">
							<!-- onclick="loadCaptcha();" -->
					</div>
					
					<div  class="am-u-sm-6 am-padding-left-0 am-form-group" >
						<input type="text" class="tpl-form-input" id="checkcode"
							placeholder="请输入验证码"> 
							<!-- onclick="loadCaptcha();" -->
					</div>
					



					<div class="am-form-group">

						<button type="button"
							class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn"
							onclick="login();">登录</button>

					</div>

					<div class="am-form-group">

						<button type="button"
							class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn"
							onclick="toRegist();">注册</button>

					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 模拟 Alert
	<button type="button" class="am-btn am-btn-primary"
		data-am-modal="{target: '#my-alert'}">Alertxxxx</button>
	-->

	<div class="am-modal am-modal-alert" tabindex="-1" id="login-alert">
		<div class="am-modal-dialog">
			<div class="am-modal-hd" id="login-1">{{ message }}</div>
			<div class="am-modal-bd" id="login-2">{{ message }}</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn">确定</span>
			</div>
		</div>
	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>


	<script type="text/javascript">
		var Loginflag = '${user}';

		var app1 = new Vue({
			el : '#login-1',
			data : {
				message : 'Hello Vue!'
			}
		})

		var app2 = new Vue({
			el : '#login-2',
			data : {
				message : 'Hello Vue!'
			}
		})

		$(document).ready(function() {
			if (Loginflag != '') {
				//alert(' ${user.username} ');
				//alert('sx');
				app1.message = '登陆成功';
				app2.message = '欢迎您，' + ' ${user.username} ';

				//RB.alert();

				$('#login-alert').modal('open');
				$('#login-alert').on('close.modal.amui', function() {
					window.location.href = '/user/toUserCenter.do';
				});
			}
		});

		function ajaxFunction(data, status) {
			var ajaxDto = data;

			if (ajaxDto.flag) {
				//location.replace(location.href);
				//刷新页面以读取session，
				window.location.href = '/user/toLogin.do';
				//alert(' ${user.username} ');
				// 				app1.message = '登陆成功';
				// 				app2.message = '欢迎您，' + ' ${user.username} ';

				// 				$('#login-alert').modal('open');
				// 				$('#login-alert').on('close.modal.amui', function() {
				// 					window.location.href = '/user/toUserCenter.do';
				// 				});
			} else {
				//alert(ajaxDto.message);
				RB.alert(false, '登录失败,' + ajaxDto.message);

				// 				app1.message = '登陆失败';
				// 				app2.message = 'sx ~' + ajaxDto.message;
				// 				$('#login-alert').modal('open');
			}
		}

		function login() {
			var user = {
				userName : $('#userName').val(),
				password : $('#password').val()
			};

			$.post("/user/login.do", user, ajaxFunction);
		}

		function toRegist() {
			window.location.href = "/user/toRegist.do";
		}
	</script>

</body>

</html>