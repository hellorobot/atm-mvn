/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.3.22.v20171030
 * Generated at: 2017-12-13 08:13:55 UTC
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

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("file:/C:/Users/laozhao/.m2/repository/org/apache/taglibs/taglibs-standard-impl/1.2.5/taglibs-standard-impl-1.2.5.jar", Long.valueOf(1512982635119L));
    _jspx_dependants.put("jar:file:/C:/Users/laozhao/.m2/repository/org/apache/taglibs/taglibs-standard-impl/1.2.5/taglibs-standard-impl-1.2.5.jar!/META-INF/fmt.tld", Long.valueOf(1425949870000L));
    _jspx_dependants.put("jar:file:/C:/Users/laozhao/.m2/repository/org/apache/taglibs/taglibs-standard-impl/1.2.5/taglibs-standard-impl-1.2.5.jar!/META-INF/c.tld", Long.valueOf(1425949870000L));
  }

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

      out.write("\r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\">\r\n");
      out.write("\t<title>ATM系统</title>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body bgcolor=\"#DC8349  \">\r\n");
      out.write("<div align=\"center\">\r\n");
      out.write("<h1>用戶中心</h1>\r\n");
      out.write("<img src=\"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=827394077,4082791614&fm=27&gp=0.jpg\" />\r\n");
      out.write("\r\n");
      out.write("\t\t<br>\r\n");
      out.write("\r\n");
      out.write("\t<h1>用户名：");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.username }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</h1>\r\n");
      out.write("\t<a href=\"http://127.0.0.1:8080\" >》》》注銷》》》</a>\r\n");
      out.write("\t<br/>\t\r\n");
      out.write("\t\t<img src=\"/user/openImg.do\" width=\"200\" height=\"200\">\r\n");
      out.write("\t<br/>\t\r\n");
      out.write("\t<a href=\"/bankCard/toOpenaccount.do\">開戶</a>\r\n");
      out.write("\t<a href=\"/user/upLoadIMG.do\">上传照片</a>\r\n");
      out.write("\r\n");
      out.write("\t\t<br/>\r\n");
      out.write("\t---------------------------------\t\r\n");
      out.write("\t\t<table id=\"cardTable\"></table>\r\n");
      out.write("\t\t<span id=\"current\"></span>\r\n");
      out.write("\t\t\t\t<button onclick=\"goFirst();\">首页</button>\r\n");
      out.write("\t\t\t\t<button onclick=\"ahead();\">前一頁</button>\r\n");
      out.write("\t\t\t\t<button onclick=\"next();\">后一頁</button>\r\n");
      out.write("\t\t\t\t<button onclick=\"goLast();\">末頁</button>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/jquery-3.2.1.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/json2.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var currentPage = 1;\r\n");
      out.write("var total = 1;\r\n");
      out.write("\r\n");
      out.write("function next() { \r\n");
      out.write("\tif(currentPage >= total){\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\tcurrentPage = parseInt(currentPage) + 1;\r\n");
      out.write("\t\r\n");
      out.write("\tloadBankcard();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function goFirst() { \r\n");
      out.write("\tcurrentPage = 1;\r\n");
      out.write("\t\r\n");
      out.write("\tloadBankcard();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function goLast() { \r\n");
      out.write("\tcurrentPage = total;\r\n");
      out.write("\t\r\n");
      out.write("\tloadBankcard();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function ahead() { \r\n");
      out.write("\tif(currentPage == 1){\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\tcurrentPage = parseInt(currentPage) - 1;\r\n");
      out.write("\t\r\n");
      out.write("\tloadBankcard();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function savaMoney(cardNum) {\r\n");
      out.write("\t\r\n");
      out.write("\twindow.location.href='/bankCard/toSave.do?cardNum=' + cardNum;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function flow(cardNum) {\r\n");
      out.write("\t\r\n");
      out.write("\twindow.location.href='/bankCard/toFlow.do?cardNum=' + cardNum;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function callback(data,status){\t\r\n");
      out.write("\tvar AjaxDto = JSON.parse(data);\r\n");
      out.write("\tif(AjaxDto.flag){\r\n");
      out.write("\t\ttotal = AjaxDto.data.pagesNum;\r\n");
      out.write("\t\tvar result = AjaxDto.data.obj;\r\n");
      out.write("\t\tvar msg = '<tr><td >银联卡号</td><td >余额</td><td>时间</td><td>操作</td></tr>';\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfor (var i = 0; i < result.length; i++) {\r\n");
      out.write("\t\t\tmsg += '<tr>';\r\n");
      out.write("\t\t\tmsg += '<td>' + result[i].cardNum + '</td>  '\r\n");
      out.write("\t\t\tmsg += '<td>' + result[i].balance + '</td>  '\r\n");
      out.write("\t\t\tmsg += '<td>' + new Date(result[i].modifyTime).toLocaleString() + '</td>   '\r\n");
      out.write("\t\t\tmsg += '<td>' \r\n");
      out.write("\t\t\tmsg += '<button onclick=\"savaMoney('+ result[i].cardNum +');\">存钱</button>  ';\r\n");
      out.write("\t\t\tmsg += '<button >取钱</button>  ';\r\n");
      out.write("\t\t\tmsg += '<button onclick=\"flow(' + result[i].cardNum + ');\">流水</button>  ';\r\n");
      out.write("\t\t\tmsg += '<button>转账</button>  ';\r\n");
      out.write("\t\t\tmsg += '<button> X </button>  ';\r\n");
      out.write("\t\t\tmsg += '</td>'\r\n");
      out.write("\t\t\tmsg += '</tr>';\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar pageNum = currentPage + '/' + total\r\n");
      out.write("\r\n");
      out.write("\t\t$('#cardTable').html(msg);\r\n");
      out.write("\t\t$('#current').html(pageNum);\r\n");
      out.write("\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tif(AjaxDto.statusCode == 302){\r\n");
      out.write("\t\t\talert(AjaxDto.message);\r\n");
      out.write("\t\t\twindow.location.href='/user/toLogin.do';\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function loadBankcard(){\t\r\n");
      out.write("\tvar cardlist = {\r\n");
      out.write("\t\t\tcurrentPage : currentPage\r\n");
      out.write("\t};\r\n");
      out.write("\t\r\n");
      out.write("\t$.post('/user/loadBankcard.do',cardlist,callback);\r\n");
      out.write("\t\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("loadBankcard();\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
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