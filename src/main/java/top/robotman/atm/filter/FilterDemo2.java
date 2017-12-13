package top.robotman.atm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterDemo2 implements Filter{

	@Override
	public void destroy() {
		System.out.println("----filter destroy()【2】【2】【2】】--");
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse resp = (HttpServletResponse)arg1;
		req.setCharacterEncoding("UTF-8");
		System.out.println("传入过滤【2】【2】【2】【2】");
		////////////////////////////////////////////////////////
		arg2.doFilter(req, resp);
		///////////////////////////////////////////////////////////
		resp.setCharacterEncoding("UTF-8");
		System.out.println("传出过滤【2】【2】【2】");		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("filter初始化【2】】【2】【2】【2】");
		
	}

}
