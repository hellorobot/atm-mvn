<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<!-- 頭部内容-->



<jsp:include page="/WEB-INF/pages/common/header.jsp"/>

<!-- 风格切换 -->

<!-- 侧边导航栏 -->


<jsp:include page="/WEB-INF/pages/common/left.jsp"/>

<!-- 内容区域 -->

<div class="tpl-content-wrapper">

	<div class="row-content am-cf">
		<div class="row  am-cf" id="cardlist"></div>



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
<!-- 尾部 -->



<jsp:include page="common/footer.jsp"></jsp:include>


<script type="text/javascript">
		$(document).ready(function() {
			//document.getElementById("HeadUsername").innerHTML='${user.username}';
			$('#HeadUsername').html('${user.username}');

			loadBankcard();
			loadFlowTable();
		});
		
		function upload2(){
			$('#avatarForm').submit();
		}


		function callback(data, status) {
			if (data.flag) {
				var cardlist = data.data;
				
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
		<!-- 时间格式转换，有空再看   -->
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
		
		function loadAvatar() {
    		$('#touxiangIMG').attr('src','/resources/image/${user.id }?'+Date());
    	}
		
	</script>
	


</body>
<!-- 为什么不显示iframe啊 -->
<iframe name="avatarFrame" width="500px" height="500px"> </iframe>

</html>