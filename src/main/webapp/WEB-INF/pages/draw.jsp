<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


<!-- 头部 -->
<jsp:include page="common/header.jsp"></jsp:include>
<!-- 风格切换 -->

<!-- 侧边导航栏 -->
<jsp:include page="common/left.jsp"></jsp:include>


<!-- 内容区域 -->
<div class="tpl-content-wrapper" id="draw">

	<div class="row-content am-cf">
		<div class="row  am-cf">

			<div class="row">

				<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
					<div class="widget am-cf">
						<div class="widget-head am-cf">
							<div class="widget-title am-fl">取款操作</div>
							<div class="widget-function am-fr">
								<a href="javascript:;" class="am-icon-cog"></a>
							</div>
						</div>
						<div class="widget-body am-fr">

							<form class="am-form tpl-form-line-form">


								<div class="am-form-group">
									<label for="user-phone" class="am-u-sm-3 am-form-label">银行卡
										<span class="tpl-form-line-small-title"></span>
									</label>
									<div class="am-u-sm-9">
										<select data-am-selected="{searchBox: 1}"
											style="display: none;" id="select">
											
											<option value="no">请选择银行卡</option>
											<option id="draw-cardnum" v-for="todo in todos"
											v-bind:value="todo.cardNum"> {{ todo.cardNum }}</option>
										</select>

									</div>
								</div>

								<div class="am-form-group">
									<label for="user-name" class="am-u-sm-3 am-form-label">金額
										<span class="tpl-form-line-small-title"></span>
									</label>
									<div class="am-u-sm-9">
										<input type="text" class="tpl-form-input" id="draw-amount"
											placeholder="请输入取款金額"> <small></small>
									</div>
								</div>

								<div class="am-form-group">
									<label for="user-name" class="am-u-sm-3 am-form-label">密码
										<span class="tpl-form-line-small-title"></span>
									</label>
									<div class="am-u-sm-9">
										<input type="password" class="tpl-form-input" id="draw-password"
											placeholder="请输入6位银行卡密码"> <small></small>
									</div>
								</div>





								<div class="am-form-group">
									<div class="am-u-sm-9 am-u-sm-push-3">
										<button type="button" id="draw-button"
											class="am-btn am-btn-primary tpl-btn-bg-color-success ">取款</button>
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

var app4 = new Vue({
	  el: '#draw',
	  data: {
	    todos: []
	  }
	})

$(document).ready(function() {
	//$('ul#leftchoice li a').removeClass();
	$('ul#leftchoice li a').removeClass('active');
	$('#turn-draw').addClass('active');
	$('#HeadUsername').html('${user.username}');
	//初始化页面参数
	loadBankcard();
});

function loadBankcard(){
	$.post('/bankCard/loadBankcard.do ',{},function(data,status){
		var list = data.data;
		app4.todos = list;
	});	
}
//按钮点击事件
$('#draw-button').click(function(){

	$.post('/bankCard/draw.do ',{
		amount : $('#draw-amount').val(),
		password : $('#draw-password').val(),
		cardnum : $('#select').val()
	},function(data,status){
		
		if(data.flag){
			RB.alert(true,data.message,'/bankCard/toDraw.do');
		}else{
			RB.alert(false,data.message);
		}
		
	});	
});

</script>

</body>

</html>