<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<link rel="stylesheet" href="/assets/css/amazeui.min.css" />
<link rel="stylesheet" href="/assets/css/amazeui.datatables.min.css" />
<link rel="stylesheet" href="/assets/css/app.css">

</head>

<body data-type="index">

	<div class="am-g tpl-g" id="head">

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
						<li class="am-dropdown" data-am-dropdown><a
							href="javascript:;" class="am-dropdown-toggle"
							data-am-dropdown-toggle> <i class="am-icon-bell"></i> <span
								class="am-badge am-badge-warning am-round item-feed-badge">{{count}}</span>
						</a> <!-- 弹出列表 -->
							<ul class="am-dropdown-content tpl-dropdown-content" >
								<li class="tpl-dropdown-menu-notifications" v-for="message in messages"><a
									href="javascript:;"
									class="tpl-dropdown-menu-notifications-item am-cf">
										<div class="tpl-dropdown-menu-notifications-title">
											<i class="am-icon-line-chart"></i> <span> {{message.msg}}</span>
										</div>
										<div class="tpl-dropdown-menu-notifications-time">{{message.createtime}}
										</div>
								</a></li>
							<li class="tpl-dropdown-menu-notifications"><a
									href="javascript:;"
									class="tpl-dropdown-menu-notifications-item am-cf"> <i
										class="am-icon-bell"></i> 进入列表…
								</a></li>
							</ul></li>


						<!-- 退出 -->
						<li class="am-text-sm" ><a href="/user/logout.do"> <span
								class="am-icon-sign-out"></span> 退出
						</a></li>
					</ul>
				</div>
			</div>

		</header>
</div>
		