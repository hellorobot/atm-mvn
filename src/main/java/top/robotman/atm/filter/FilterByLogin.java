package top.robotman.atm.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import top.robotman.atm.ajaxDTO.AjaxDTO;

public class FilterByLogin implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 咱们现在开始写过滤，基本思想是根据登陆与否来决定对方是否强制跳转登陆界面。
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		InputStream is = req.getServletContext().getResourceAsStream("/WEB-INF/classes/white_url.properties");
		Properties pro = new Properties();
		pro.load(is);
		String whiteUrls = (String) pro.get("white_url");
		String[] whiteUrlArray =  whiteUrls.split("\\,");
		//白名单获取了，那么那请求的url来进行对比吧，如果符合就放行，不符合就后续校验
		String tmpurl = req.getRequestURI();
		boolean flag = false;
		for(String whiteUrl : whiteUrlArray) {
			if(whiteUrl.equals(tmpurl)) {
				flag = true;
				break;
			}
		}
	//如果有相同的，flag就是true，那就直接跳转到白名单的页面去就可以	
		if(flag == true) {
			chain.doFilter(req, resp);	
			return;
		}else {
			//否则校验session里是否有user，没有就要求跳转到首页登陆
			checkUserSession(req, resp,chain);
		}
	}
	
	private void checkUserSession(HttpServletRequest req, HttpServletResponse resp,FilterChain chain) throws IOException, ServletException {
		Object obj = req.getSession().getAttribute("user");
		if(obj != null) {
			//如果不为空就说明没问题，需要跳转
			chain.doFilter(req, resp);
			return;
		}else {
			//这里不能简单的写sx，毕竟不文明，我们需要让它返回登陆页面，但是请求也有多种，比如ajax请求和string跳转请求，这里要分开处理
			//如何区分ajax请求还是String请求呢？这里我们根据请求头可以很容易看出来。
			//就是ajax请求的请求头中有X-Requested-With:XMLHttpRequest这个标签，而普通的跳转请求是没有的
			//为了清楚起见，再写个区分方法好了，具体功能在区分方法里实施。
			checkAjaxOrString(req, resp,chain);
		}
	}
	
	private void checkAjaxOrString(HttpServletRequest req, HttpServletResponse resp,FilterChain chain) throws IOException, ServletException {
		String x = req.getHeader("X-Requested-With");
		
		if(null == x || "".equals(x)) {
			System.out.println("重定向sx");
			resp.sendRedirect("/user/toLogin.do");	
			
//			OutputStream os = resp.getOutputStream();
//			AjaxDTO dto = new AjaxDTO();
//			dto.setFlag(false);
//			dto.setMessage("请先登录");
//			os.write(JSON.toJSONString(dto).getBytes("UTF-8"));
//			os.flush();
			//os.close();
			
			resp.sendRedirect("/user/toLogin.do");
			//req.getRequestDispatcher("/user/toLogin.do").forward(req, resp);
			return;
		}else {
			
			AjaxDTO dto = new AjaxDTO();
			dto.setFlag(false);
			dto.setStatusCode(302);
			dto.setMessage("超时：请先登录");

			try(OutputStream os = resp.getOutputStream();){
				os.write(JSON.toJSONString(dto).getBytes("UTF-8"));
				os.flush();
				System.out.println(JSON.toJSONString(dto));
				System.out.println("ajax请求111，sx");
			}			
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("=======login [11112] filter=======");

	}

}
