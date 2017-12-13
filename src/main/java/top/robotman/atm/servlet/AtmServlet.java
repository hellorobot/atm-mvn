package top.robotman.atm.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;
import com.dayuanit.atm.domain.BankCard;
import com.dayuanit.atm.domain.Flow;
import com.dayuanit.atm.domain.User;
import com.dayuanit.atm.exception.BizException;
import com.dayuanit.atm.service.AtmService;
import com.dayuanit.atm.service.UserService;
import com.dayuanit.atm.service.impl.AtmServiceImpl;
import com.dayuanit.atm.service.impl.UserServiceimpl;

import top.robotman.atm.ajaxDTO.AjaxDTO;
import top.robotman.atm.controller.BaseController;
import top.robotman.atm.controller.UserController;
import top.robotman.atm.flipPages.FlipPage;

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
		SpringControllerHandler.init();
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
