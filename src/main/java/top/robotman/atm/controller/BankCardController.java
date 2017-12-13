package top.robotman.atm.controller;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dayuanit.atm.domain.BankCard;
import com.dayuanit.atm.domain.Flow;
import com.dayuanit.atm.domain.User;
import com.dayuanit.atm.service.AtmService;
import com.dayuanit.atm.service.UserService;
import com.dayuanit.atm.service.impl.AtmServiceImpl;
import com.dayuanit.atm.service.impl.UserServiceimpl;

import top.robotman.atm.annotation.Autowired;
import top.robotman.atm.annotation.MyAnnotation;
import top.robotman.atm.flipPages.FlipPage;

public class BankCardController extends BaseController {

	private AtmService seivice;
	private UserService userService;

	public void setAtmService(AtmService seivice) {
		this.seivice = seivice;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void toOpenaccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/toOpenaccount.html").forward(req, resp);
	}

	public String openAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String password = req.getParameter("password");
		HttpSession htsession = req.getSession();
		User user = (User) htsession.getAttribute("user");
		String owner = user.getUsername();
		BankCard bc = seivice.openAccount(password, owner);
		req.setAttribute("bc", bc);

		return "/WEB-INF/pages/openAccount.jsp";
	}

	public String toSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cardNum = req.getParameter("cardNum");

		req.setAttribute("cardNum", cardNum);
		return "/WEB-INF/pages/toSave.jsp";
	}

	public String saveMoney(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String amount = req.getParameter("amount");
		String cardNum = req.getParameter("cardNum");
		String password = req.getParameter("password");

		seivice.deposit(amount, cardNum, password);

		BankCard bc = seivice.getBankCard(cardNum);
		req.setAttribute("bc", bc);
		return "/WEB-INF/pages/openAccount.jsp";
	}

	public String transfer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String amount = req.getParameter("amount");
		String cardNumOut = req.getParameter("cardNumOut");
		String password = req.getParameter("password");
		String cardNumIn = req.getParameter("cardNumIn");

		seivice.transfer(amount, cardNumIn, cardNumOut, password);

		BankCard bc = seivice.getBankCard(cardNumOut);
		req.setAttribute("bc", bc);
		return "/WEB-INF/pages/openAccount.jsp";
	}

	public String toFlow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cardNum = req.getParameter("cardNum");

		req.setAttribute("cardNum", cardNum);
		return "/WEB-INF/pages/flow.jsp";
	}

	public String qureyFlow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String cardNum = req.getParameter("cardNum");
		String password = req.getParameter("password");
		String currentPage = req.getParameter("currentPage");

		FlipPage flipPage = seivice.queryFlow(cardNum, password, Integer.parseInt(currentPage));

		req.setAttribute("cardNum", cardNum);
		req.setAttribute("password", password);
		req.setAttribute("flipPage", flipPage);
		return "/WEB-INF/pages/flow.jsp";
	}

	public void downFlow(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
		String cardNum = req.getParameter("cardNum");
		String password = req.getParameter("password");

		String valueName =cardNum + "流水" + ".csv";
		
		String fileName = new String(valueName.getBytes(),"ISO8859-1");
	
		// 告诉浏览器是以下载的方法获取到资源 filename="+ filename
		// 告诉浏览器以此种编码来解析URLEncoder.encode(realPath, "utf-8"))
		//resp.setContentType("application/octet-stream");
		// 输入流
		try (BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream()))) {
			System.out.println("=============="+ valueName +"============");
			resp.setHeader("content-disposition", "attachment; filename=" + fileName);

			String tableHead = "id,卡号,金额,时间,备注";
			bf.write(tableHead);
			bf.newLine();
			bf.flush();

			FlipPage flipPageX = seivice.queryFlow(cardNum, password, 1);

			for (int currentpage = 1; currentpage <= flipPageX.getPagesNum(); currentpage++) {
				
				FlipPage flipPage = seivice.queryFlow(cardNum, password, currentpage);				
				List<Flow> flows = (List<Flow>) flipPage.getObj();

				for (Flow flow : flows) {
					SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					StringBuilder msg = new StringBuilder("");
					msg.append(flow.getId()).append(",").append(flow.getCardNum()).append(",").append(flow.getAmount()).append(",")
							.append(dateformat1.format(flow.getCreateTime())).append(",").append(flow.getDescript());
					System.out.println(msg.toString());
					bf.write(msg.toString());
					bf.newLine();
					bf.flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 输出流
	}
}
