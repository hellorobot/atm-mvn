<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/pages/common/header.jsp" />
<!-- 风格切换 -->

<!-- 侧边导航栏 -->
<jsp:include page="/WEB-INF/pages/common/left.jsp" />


<!-- 内容区域 -->
<div class="tpl-content-wrapper">

	<div class="row-content am-cf">
		<div class="row  am-cf">

			<div class="row">

				<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
					<div class="widget am-cf">
						<div class="widget-head am-cf">
							<div class="widget-title am-fl">银行卡开户</div>
							<div class="widget-function am-fr">
								<a href="javascript:;" class="am-icon-cog"></a>
							</div>
						</div>
						<div class="widget-body am-fr">

							<form class="am-form tpl-form-line-form">
								<div class="am-form-group">
									<label for="user-name" class="am-u-sm-3 am-form-label">密码
										<span class="tpl-form-line-small-title"></span>
									</label>
									<div class="am-u-sm-9">
										<input type="password" class="tpl-form-input" id="password"
											placeholder="请输入6位银行卡密码"> <small></small>
									</div>
								</div>

								<div class="am-form-group">
									<label for="user-name" class="am-u-sm-3 am-form-label">确认密码
										<span class="tpl-form-line-small-title"></span>
									</label>
									<div class="am-u-sm-9">
										<input type="password" class="tpl-form-input" id="password2"
											placeholder="请输入6位银行卡密码"> <small></small>
									</div>
								</div>



								<div class="am-form-group">
									<div class="am-u-sm-9 am-u-sm-push-3">
										<button type="button"
											class="am-btn am-btn-primary tpl-btn-bg-color-success "
											id="openAccount">开户</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



</div>
</div>
</div>
</div>
<jsp:include page="common/footer.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function(){
		$('#HeadUsername').html('${user.username}');
		//$('ul li a').removeClass('active');
		$('ul#leftchoice li a').removeClass('active');
		$('#turn-oppenaccount').addClass('active');
	});

	$('#openAccount').click(function() {
		var password = $('#password').val();
		var password2 = $('#password2').val();
		if (password.length == 0) {
			alert("密码不能为空");
			return;
		}

		if (password != password2) {
			alert("两次密码不相同，请重新输入");
			return;
		}

		$.post("/bankCard/openAccount.do", {
			password : $('#password').val()
		}, function(data, status) {
			if (data.flag = true) {
				alert("开户成功");
				window.location.href = "/user/toUserCenter.do"
			} else {
				alert("开户失败");
			}
		});
	});
	
</script>

</body>

</html>