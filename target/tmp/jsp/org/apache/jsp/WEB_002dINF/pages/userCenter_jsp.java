/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.4.8.v20171121
 * Generated at: 2018-01-07 15:33:28 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class userCenter_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<!-- 頭部内容-->\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/pages/common/header.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("<!-- 风格切换 -->\n");
      out.write("\n");
      out.write("<!-- 侧边导航栏 -->\n");
      out.write("\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/pages/common/left.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("<!-- 内容区域 -->\n");
      out.write("\n");
      out.write("<div class=\"tpl-content-wrapper\">\n");
      out.write("\n");
      out.write("\t<div class=\"row-content am-cf\">\n");
      out.write("\t\t<div class=\"row  am-cf\" id=\"cardlist\"></div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t<div class=\"am-u-sm-12 am-u-md-12 am-u-lg-6\">\n");
      out.write("\t\t\t<div class=\"widget am-cf\">\n");
      out.write("\t\t\t\t<div class=\"widget-head am-cf\">\n");
      out.write("\t\t\t\t\t<div class=\"widget-title am-fl\">最近十笔流水</div>\n");
      out.write("\t\t\t\t\t<div class=\"widget-function am-fr\"></div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t\t<div class=\"widget-body  widget-body-lg am-fr\">\n");
      out.write("\n");
      out.write("\t\t\t\t\t<table width=\"100%\"\n");
      out.write("\t\t\t\t\t\tclass=\"am-table am-table-compact am-table-bordered am-table-radius am-table-striped tpl-table-black \"\n");
      out.write("\t\t\t\t\t\tid=\"flowTable\">\n");
      out.write("\t\t\t\t\t\t<thead>\n");
      out.write("\t\t\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t\t\t<th>卡號</th>\n");
      out.write("\t\t\t\t\t\t\t\t<th>金額</th>\n");
      out.write("\t\t\t\t\t\t\t\t<th>时间</th>\n");
      out.write("\t\t\t\t\t\t\t\t<th>备注</th>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t\t\t</thead>\n");
      out.write("\t\t\t\t\t\t<tbody>\n");
      out.write("\t\t\t\t\t\t\t<tr class=\"gradeX\">\n");
      out.write("\t\t\t\t\t\t\t\t<td>Amaze UI 模式窗口</td>\n");
      out.write("\t\t\t\t\t\t\t\t<td>张鹏飞</td>\n");
      out.write("\t\t\t\t\t\t\t\t<td>2016-09-26</td>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t</tr>\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t<!-- more data -->\n");
      out.write("\t\t\t\t\t\t</tbody>\n");
      out.write("\t\t\t\t\t</table>\n");
      out.write("\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("<!-- 尾部 -->\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "common/footer.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t$(document).ready(function() {\n");
      out.write("\t\t\t//document.getElementById(\"HeadUsername\").innerHTML='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.username}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("';\n");
      out.write("\t\t\t$('#HeadUsername').html('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.username}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("');\n");
      out.write("\n");
      out.write("\t\t\tloadBankcard();\n");
      out.write("\t\t\tloadFlowTable();\n");
      out.write("\t\t\t//loadMessage();\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\tfunction upload2(){\n");
      out.write("\t\t\t$('#avatarForm').submit();\n");
      out.write("\t\t}\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\tfunction callback(data, status) {\n");
      out.write("\t\t\tif (data.flag) {\n");
      out.write("\t\t\t\tvar cardlist = data.data;\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\tvar msg = \"\";\n");
      out.write("\t\t\t\tvar displayNum = 0;\n");
      out.write("\t\t\t\tif (cardlist.length >= 6) {\n");
      out.write("\t\t\t\t\tdisplayNum = 6;\n");
      out.write("\t\t\t\t} else {\n");
      out.write("\t\t\t\t\tdisplayNum = cardlist.length;\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t\tfor (var i = 0; i < displayNum; i++) {\n");
      out.write("\t\t\t\t\tvar card = cardlist[i];\n");
      out.write("\t\t\t\t\tmsg += '<div class=\"am-u-sm-12 am-u-md-6 am-u-lg-4\"><div class=\"widget widget-primary am-cf\"><div class=\"widget-statistic-header\">';\n");
      out.write("\t\t\t\t\tmsg += card.cardNum + '</div>';\n");
      out.write("\t\t\t\t\tmsg += '<div class=\"widget-statistic-body\"><div class=\"widget-statistic-value\">';\n");
      out.write("\t\t\t\t\tmsg += '￥' + card.balance + '</div>';\n");
      out.write("\t\t\t\t\tmsg += '<div class=\"widget-statistic-description\"></div><span class=\"widget-statistic-icon am-icon-credit-card-alt\"></span></div></div></div>';\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t\t$('#cardlist').html(msg);\n");
      out.write("\t\t\t} else {\n");
      out.write("\t\t\t\tif (data.statusCode == 302) {\n");
      out.write("\t\t\t\t\talert(AjaxDto.message);\n");
      out.write("\t\t\t\t\twindow.location.href = '/user/toLogin.do';\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t}\n");
      out.write("\t\t}\n");
      out.write("\n");
      out.write("\t\tfunction loadBankcard() {\n");
      out.write("\t\t\t$.post('/user/loadBankcard.do', {}, callback);\n");
      out.write("\n");
      out.write("\t\t}\n");
      out.write("\t\t\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\tfunction loadFlowTable() {\n");
      out.write("\t\t\t$.post('/bankCard/loadNearFlow.do', {}, callback2);\n");
      out.write("\n");
      out.write("\t\t}\n");
      out.write("\n");
      out.write("\t\tfunction callback2(data, status) {\n");
      out.write("\t\t\tif (data.flag) {\n");
      out.write("\t\t\t\tvar flowlist = data.data;\n");
      out.write("\t\t\t\tvar msg = \"<thead><tr><th>卡號</th><th>金額</th><th>时间</th><th>备注</th></tr></thead><tbody>\";\n");
      out.write("\t\t\t\tfor (var i = 0; i < flowlist.length; i++) {\n");
      out.write("\t\t\t\t\tvar flow = flowlist[i];\n");
      out.write("\t\t\t\t\tmsg += '<tr class=\"gradeX\"><td>';\n");
      out.write("\t\t\t\t\tmsg += flow.cardNum +'</td><td>';\n");
      out.write("\t\t\t\t\tmsg += flow.amount +'</td><td>';\n");
      out.write("\t\t\t\t\tmsg += (new Date(flow.createTime)).format(\"yyyy-MM-dd hh:mm:ss\") + '</td><td>';\n");
      out.write("\t\t\t\t\tmsg += flow.descript +'</td></tr>';\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t\tmsg += '</tbody>';\n");
      out.write("\t\t\t\t$('#flowTable').html(msg);\n");
      out.write("\t\t\t} else {\n");
      out.write("\t\t\t\tif (data.statusCode == 302) {\n");
      out.write("\t\t\t\t\talert(AjaxDto.message);\n");
      out.write("\t\t\t\t\twindow.location.href = '/user/toLogin.do';\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t}\n");
      out.write("\t\t}\n");
      out.write("\t\t<!-- 时间格式转换，有空再看   -->\n");
      out.write("\t\tDate.prototype.format = function(fmt) { \n");
      out.write("\t\t     var o = { \n");
      out.write("\t\t        \"M+\" : this.getMonth()+1,                 //月份 \n");
      out.write("\t\t        \"d+\" : this.getDate(),                    //日 \n");
      out.write("\t\t        \"h+\" : this.getHours(),                   //小时 \n");
      out.write("\t\t        \"m+\" : this.getMinutes(),                 //分 \n");
      out.write("\t\t        \"s+\" : this.getSeconds(),                 //秒 \n");
      out.write("\t\t        \"q+\" : Math.floor((this.getMonth()+3)/3), //季度 \n");
      out.write("\t\t        \"S\"  : this.getMilliseconds()             //毫秒 \n");
      out.write("\t\t    }; \n");
      out.write("\t\t    if(/(y+)/.test(fmt)) {\n");
      out.write("\t\t            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+\"\").substr(4 - RegExp.$1.length)); \n");
      out.write("\t\t    }\n");
      out.write("\t\t     for(var k in o) {\n");
      out.write("\t\t        if(new RegExp(\"(\"+ k +\")\").test(fmt)){\n");
      out.write("\t\t             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : ((\"00\"+ o[k]).substr((\"\"+ o[k]).length)));\n");
      out.write("\t\t         }\n");
      out.write("\t\t     }\n");
      out.write("\t\t    return fmt; \n");
      out.write("\t\t} \n");
      out.write("\t\t\n");
      out.write("\t\tfunction loadAvatar() {\n");
      out.write("    \t\t$('#touxiangIMG').attr('src','/resources/image/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.id }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("?'+Date());\n");
      out.write("    \t}\n");
      out.write("\t\t\n");
      out.write("\t\t\n");
      out.write("\t\t\n");
      out.write("\t</script>\n");
      out.write("\t\n");
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("<!-- 为什么不显示iframe啊 -->\n");
      out.write("<iframe name=\"avatarFrame\" width=\"500px\" height=\"500px\"> </iframe>\n");
      out.write("\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
