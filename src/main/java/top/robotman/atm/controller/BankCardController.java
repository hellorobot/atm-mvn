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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.atm.domain.BankCard;
import com.dayuanit.atm.domain.Flow;
import com.dayuanit.atm.domain.User;
import com.dayuanit.atm.service.AtmService;
import com.dayuanit.atm.service.UserService;
import com.dayuanit.atm.service.impl.AtmServiceImpl;
import com.dayuanit.atm.service.impl.UserServiceimpl;

import top.robotman.atm.ajaxDTO.AjaxDTO;
import top.robotman.atm.annotation.MyAnnotation;
import top.robotman.atm.flipPages.FlipPage;

@Controller
@RequestMapping("/bankCard")
public class BankCardController extends BaseController {

	@Autowired
	private AtmService seivice;
	@Autowired
	private UserService userService;

	@RequestMapping("/toOpenaccount")
	public String toOpenaccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "toOpenAccount";
	}

	@RequestMapping("/openAccount")
	@ResponseBody
	public AjaxDTO openAccount(String password, HttpSession session) throws ServletException, IOException {
		User user = (User) session.getAttribute("user");
		String owner = user.getUsername();
		BankCard bc = seivice.openAccount(password, owner);
		AjaxDTO dto = new AjaxDTO();
		dto.setFlag(true);

		return dto;
	}

	@RequestMapping("/toDraw")
	public String toDraw() {
		return "draw";
	}

	@RequestMapping("/loadBankcard")
	@ResponseBody
	public AjaxDTO loadBankcard(HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<BankCard> list = userService.getCardsPage(user.getUsername(), null);

		return AjaxDTO.success(list);
	}

	@RequestMapping("/draw")
	@ResponseBody
	public AjaxDTO draw(String cardnum, String password, String amount) {
		seivice.draw(amount, cardnum, password);

		return AjaxDTO.success("取款成功");
	}

	@RequestMapping("/toSave")
	public String toSave() {
		return "save";
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxDTO save(String amount, String password, String cardnum) {

		seivice.deposit(amount, cardnum, password);

		return AjaxDTO.success("存款成功");
	}

	@RequestMapping("/toTransfer")
	public String toTransfer() {
		return "transfer";
	}

	// outCardnum : $('#transfer-incardnum').val(),
	// inCardnum : $('#transfer-incardnum').val(),
	// amount : $('#transfer-amount').val(),
	// password : $('#transfer-password').val()
	@RequestMapping("/transfer")
	@ResponseBody
	public AjaxDTO transfer(String outCardnum, String inCardnum, String amount, String password) {

		seivice.transfer(amount, inCardnum, outCardnum, password);

		return AjaxDTO.success("转账成功");
	}

	@RequestMapping("/toFlow")
	public String toFlow() {
		return "flow";
	}

	@RequestMapping("/qureyFlow")
	@ResponseBody
	public AjaxDTO qureyFlow(String cardNum,String password,String currentPage){
		System.out.println("----流水信息=====");
//		cardNum = req.getParameter("cardNum");
//		password = req.getParameter("password");
//		currentPage = req.getParameter("currentPage");

		FlipPage flipPage = seivice.queryFlow(cardNum, password, Integer.parseInt(currentPage));
		System.out.println("查询流水======"+flipPage.getCurrentPage()+"[xxxxx]"+flipPage.getFlowsNum());
		return AjaxDTO.success(flipPage);
	}

	@RequestMapping("/loadNearFlow")
	@ResponseBody
	public AjaxDTO loadNearFlow(HttpSession session) throws ServletException, IOException {
		User user = (User) session.getAttribute("user");
		List<Flow> listFlow = seivice.listFlowNearly(user.getUsername());

		AjaxDTO dto = new AjaxDTO();
		dto.setFlag(true);
		dto.setData(listFlow);

		return dto;
	}

	@RequestMapping("/downFlow")
	public void downFlow(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
		String cardNum = req.getParameter("cardNum");
		String password = req.getParameter("password");

		String valueName = cardNum + "流水" + ".csv";

		String fileName = new String(valueName.getBytes(), "ISO8859-1");

		// 告诉浏览器是以下载的方法获取到资源 filename="+ filename
		// 告诉浏览器以此种编码来解析URLEncoder.encode(realPath, "utf-8"))
		// resp.setContentType("application/octet-stream");
		// 输入流
		try (BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream()))) {
			System.out.println("==============" + valueName + "============");
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
					msg.append(flow.getId()).append(",").append(flow.getCardNum()).append(",").append(flow.getAmount())
							.append(",").append(dateformat1.format(flow.getCreateTime())).append(",")
							.append(flow.getDescript());
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
