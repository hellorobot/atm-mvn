<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="common/header.jsp"></jsp:include>
<!-- 风格切换 -->

<!-- 侧边导航栏 -->
<jsp:include page="common/left.jsp"></jsp:include>


<!-- 内容区域 -->
<div class="tpl-content-wrapper" id="flow">

	<div class="row-content am-cf">
		<div class="row  am-cf">

			<div class="row">

				<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
					<div class="widget am-cf">
						<div class="widget-head am-cf">
							<div class="widget-title am-fl">流水操作</div>
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
											style="display: none;" id="cardlist">

											<option value="no">请选择银行卡</option>

											<option v-for="todo in todos" v-bind:value="todo.cardNum">{{todo.cardNum}}</option>
										</select>
										<button type="button" class="am-btn am-btn-default am-radius"
											id="qurey-button">查询</button>
									</div>

								</div>



								<div class="am-form-group">
									<label for="user-name" class="am-u-sm-3 am-form-label">密码
										<span class="tpl-form-line-small-title"></span>
									</label>
									<div class="am-u-sm-9">
										<input type="password" class="tpl-form-input"
											id="flow-password" placeholder="请输入6位银行卡密码"> <small></small>
									</div>
								</div>

							</form>



							<div class="widget am-cf">
								<div class="widget-head am-cf">
									<div class="widget-title am-fl"></div>
									<div class="widget-function am-fr"></div>
								</div>
								<div class="widget-body  widget-body-lg am-fr">

									<table width="100%"
										class="am-table am-table-compact am-table-striped tpl-table-black "
										id="example-r">

										<thead>
											<tr>
												<th>卡号</th>
												<th>金额</th>
												<th>时间</th>
												<th>备注</th>

											</tr>
										</thead>

										<tbody>
											<tr class="gradeX" v-for="flow in flowlist">
												<td>{{flow.cardNum}}</td>
												<td>{{flow.amount }}</td>
												<td>{{flow.createTime }}</td>
												<td>{{flow.descript }}</td>
											</tr>
											<!-- more data -->
										</tbody>

									</table>


								</div>

								<ul data-am-widget="pagination"
									class="am-pagination am-pagination-centered">

									<li><a href="#" class="am-btn am-btn-default am-round"
										onclick="goFirst();">首頁</a></li>

									<li><a href="#" class="am-btn am-btn-default am-round"
										onclick="ahead();">上一页</a></li>
									<li id="fenyeInfo">1/1</li>
									<li><a href="#" class="am-btn am-btn-default am-round"
										onclick="next();">下一页</a></li>

									<li><a href="#" class="am-btn am-btn-default am-round"
										onclick="goLast()">尾頁</a></li>


								</ul>


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
	var currentPage = 1;
	var total = 1;

	var appflow = new Vue({
		el : '#flow',
		data : {
			todos : [],
			selected : 'a',
			flowlist : []
		}
	})

	$(document).ready(function() {
		//$('ul#leftchoice li a').removeClass();
		$('ul#leftchoice li a').removeClass('active');
		$('#turn-flow').addClass('active');
		$('#HeadUsername').html('${user.username}');
		//初始化页面参数
		loadBankcard();
	});

	function loadBankcard() {
		$.post('/bankCard/loadBankcard.do ', {}, function(data, status) {
			var list = data.data;
			appflow.todos = list;
		});
	}
	//查询行为,需求参数
	//		cardNum = req.getParameter("cardNum");
	//		password = req.getParameter("password");
	//		currentPage = req.getParameter("currentPage");

	function qurey() {

		//alert(currentPage);

		$.post('/bankCard/qureyFlow.do', {
			cardNum : $('#cardlist').val(),
			password : $('#flow-password').val(),
			currentPage : currentPage
		}, function(data, status) {
			if (data.flag) {

				appflow.flowlist = data.data.obj;
				total = data.data.pagesNum;
				$('#fenyeInfo').html(currentPage + '/' + total);
				
			} else {
				RB.alert(false, data.message);
				$('#my-alert').on('closed.modal.amui',function(){
					window.location.reload();
				});
				//window.location.reload()
			}
		});
	}

	$('#qurey-button').click(function() {
		qurey();

	});

	//流水显示，分页信息，使用vue来做

	//翻页的一堆方法，其实可以写进js
	function next() {
		if (currentPage == total) {
			return false;
		}
		currentPage = parseInt(currentPage) + 1;
		qurey();
	}

	function goFirst() {

		currentPage = 1;

		qurey();
	}

	function goLast() {
		currentPage = total;

		qurey();
	}

	function ahead() {
		if (currentPage == 1) {
			return false;
		}
		currentPage = parseInt(currentPage) - 1;

		qurey();
	}
</script>
</body>


</html>