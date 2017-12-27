package top.robotman.atm.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;

import top.robotman.atm.ajaxDTO.AjaxDTO;
import top.robotman.atm.handler.SpringControllerHandler;

public class AtmServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
//		String rootPath = config.getServletContext().getRealPath("/");
//		String packagePath = config.getInitParameter("packagePath");
//		try {
//			ControllerHandlerMapper.init(rootPath, packagePath);
//			System.out.println("================[Finish init]===================");
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new ServletException(e.getMessage());
//		} 		
		//SpringControllerHandler.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		StringBuffer url = req.getRequestURL();
		String uri = req.getRequestURI();
		// System.out.println("url : " + url);
		// System.out.println("uri : " + uri);

		String[] fenjie = uri.split("\\/");
		String xxPointDo = fenjie[fenjie.length - 1];
		String controller = fenjie[fenjie.length - 2];

		Object obj = SpringControllerHandler.getController(controller);

		String xxPoint = xxPointDo.substring(0, xxPointDo.length() - 3);
		
		try {
			Method method = obj.getClass().getMethod(xxPoint, HttpServletRequest.class, HttpServletResponse.class);
			Object result = method.invoke(obj, req, resp);

			if (result instanceof String) {
				req.getRequestDispatcher((String) result).forward(req, resp);
			} 
			
			if(result instanceof AjaxDTO) {
				OutputStream os = resp.getOutputStream();

				String json = JSON.toJSONString(result);
				os.write(json.getBytes("utf-8"));
				os.flush();
				os.close();
			}
			
		} catch (Exception e) {
			resp.sendError(404);
			e.printStackTrace();
		}

	}

}
