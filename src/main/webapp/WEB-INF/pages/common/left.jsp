<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="left-sidebar">
	<!-- 用户信息 -->
	<div class="tpl-sidebar-user-panel">
		<div class="tpl-user-panel-slide-toggleable">


			<div class="am-form-group am-form-file">

				<div class="tpl-user-panel-profile-picture">
					<img id="touxiangIMG" src="/resources/image/${user.id }" alt=""
						onerror="javascript:this.src='/assets/img/user04.png';">
				</div>

				<form enctype="multipart/form-data" method="post"
					action="/user/upLoad2.do" target="avatarFrame" id="avatarForm">
					<input id="doc-form-file" type="file" multiple="" name="file1"
						onchange="upload2();">
				</form>
			</div>

			<a href="javascript:;" class="tpl-user-panel-action-link"> <span
				class="am-icon-pencil"></span> 账号设置
			</a>
		</div>
	</div>

	<!-- 菜单 -->
	<ul class="sidebar-nav" id="leftchoice">

		<li class="sidebar-nav-link"><a href="/user/toUserCenter.do"
			id="turn-usercenter" class="active"> <i
				class="am-icon-home sidebar-nav-link-logo"></i> 首页
		</a></li>

		<li class="sidebar-nav-link"><a href="/bankCard/toOpenaccount.do"
			id="turn-oppenaccount"> <i
				class="am-icon-wpforms sidebar-nav-link-logo"></i> 开户
		</a></li>
		<li class="sidebar-nav-link"><a href="/bankCard/toDraw.do" id="turn-draw"> <i
				class="am-icon-wpforms sidebar-nav-link-logo"></i> 取款
		</a></li>
		<li class="sidebar-nav-link"><a href="/bankCard/toSave.do" id="turn-save"> <i
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