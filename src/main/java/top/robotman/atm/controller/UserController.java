package top.robotman.atm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.dayuanit.atm.domain.BankCard;
import com.dayuanit.atm.domain.User;
import com.dayuanit.atm.exception.BizException;
import com.dayuanit.atm.service.UserService;
import top.robotman.atm.ajaxDTO.AjaxDTO;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@org.springframework.beans.factory.annotation.Autowired
	private UserService userService;

	@RequestMapping("/toLogin")
	public String toLogin(HttpServletRequest req, HttpServletResponse resp) {
		return "login";
	}

	@RequestMapping("/login")
	@ResponseBody
	public AjaxDTO login(HttpSession session,String userName,String password) throws IOException {
			
		User user = userService.login(userName, DigestUtils.md5Hex(password + userName));

		session.setAttribute("user", user);

		return AjaxDTO.success("success");	
	}

	@RequestMapping("/toRegist")
	public String toRegist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "regist";
	}

	@RequestMapping("/regist")
	@ResponseBody
	public AjaxDTO regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		System.out.println(userName + "---" + password);

		User user = userService.getUser(userName);

		AjaxDTO dataTransfer = new AjaxDTO();
		try {

			if (user != null) {
				throw new BizException("账号重复");
			}

			int row = userService.regist(userName, DigestUtils.md5Hex(password + userName));

			dataTransfer.setFlag(true);
			dataTransfer.setMessage("success");

			return dataTransfer;
		} catch (BizException e) {
			dataTransfer.setFlag(false);
			dataTransfer.setMessage(e.getMessage());

			return dataTransfer;
		}
	}

	@RequestMapping("/upLoadIMG")
	public String upLoadIMG(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "upLoadIMG";
	}
	@RequestMapping("/upLoad2")
	public void upLoad(MultipartFile file1,HttpServletResponse resp,HttpSession session) throws IllegalStateException, IOException{
		//System.out.println(">>>>>>[][][][]"+file1.getName());
		System.out.println("上传图片ing。。。。。。。。。");
		String rp = WebUtils.getRealPath(session.getServletContext(), "");
		
		User user = (User)session.getAttribute("user");
        File uploadedFile = new File(rp + "/upload/" + user.getId());
        
			file1.transferTo(uploadedFile);
			OutputStream out = resp.getOutputStream();
			out.write("<script type=\"text/javascript\">parent.loadAvatar();</script>".getBytes());
			out.flush();
			out.close();			

	}

	@RequestMapping("/upLoad")
	public void upLoad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String savePath = req.getServletContext().getRealPath("/upload");
		File saveDir = new File(savePath);

		if (!saveDir.exists()) {
			saveDir.mkdir();
		} // 如果文件夹不存在就创建一个

		// 创建文件上传核心类
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// 设置编码
		sfu.setHeaderEncoding("UTF-8");
		// 设置上传文件限制
		sfu.setFileSizeMax(1024 * 1024 * 3);
		sfu.setSizeMax(1024 * 1024 * 10);
		// 处理表单请求
		try {
			List<FileItem> itemList = sfu.parseRequest(req);
			for (FileItem fileItem : itemList) {
				String fieldName = fileItem.getFieldName();
				System.out.println("控件名称：" + fieldName);
				// 如果是普通表单，重新编码
				if (fileItem.isFormField()) {
					String value = fileItem.getString();
					value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
					System.out.println("普通内容：" + fieldName + "=" + value);
				} else {
					Long size = fileItem.getSize();
					String fileName = fileItem.getName();
					System.out.println("文件名：" + fileName + "\t大小：" + size + "byte");
					// 设置不可上传的格式
					if (fileName.endsWith(".exe")) {
						req.setAttribute("msg", "不允许上传的类型！");
					} else {
						// 将文件保存到指定的路径
						File file = new File(savePath, fileName);
						fileItem.write(file);
						req.setAttribute("msg", "上传成功！");
						upLoadIMG(req, resp);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/openImg")
	public void openImg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String savePath = req.getServletContext().getRealPath("/");
		File imgFile = new File(savePath + "/WEB-INF/upload/下载.jpg");

		FileInputStream fis = new FileInputStream(imgFile);
		OutputStream os = resp.getOutputStream();

		byte[] tong = new byte[2048];
		int length = 0;
		while (-1 != (length = fis.read(tong))) {
			os.write(tong, 0, length);
			os.flush();
		}
		fis.close();
		os.close();
	}

	@RequestMapping("/loadBankcard")
	@ResponseBody
	public AjaxDTO loadBankcard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession httpSession = req.getSession(true);
		User user = (User) httpSession.getAttribute("user");

		List<BankCard> cardlist = userService.getCardsPage(user.getUsername(), 1);

		AjaxDTO dto = new AjaxDTO();
		try {
			dto.setData(cardlist);
			dto.setFlag(true);

			return dto;
		} catch (BizException e) {
			dto.setFlag(false);
			return dto;
		}
	}

	@RequestMapping("/tochangePSW")
	public String tochangePSW(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "changePSW";
	}

	@RequestMapping("/changePSW")
	@ResponseBody
	public AjaxDTO changePSW(String tmpPSW, String changePSW01, String changePSW02, HttpSession session123)
			throws ServletException, IOException {
		AjaxDTO dto = new AjaxDTO();

		User user = (User) session123.getAttribute("user");

		String username = user.getUsername();
		String password = user.getPassword();

		// String tmpPSW = req.getParameter("tmpPSW");
		// String changePSW01 = req.getParameter("changePSW01");
		// String changePSW02 = req.getParameter("changePSW02");

		String tmpPSWmd5 = DigestUtils.md5Hex(tmpPSW + username);
		String changePSW01md5 = DigestUtils.md5Hex(changePSW01 + username);

		if (!password.equals(tmpPSWmd5)) {
			dto.setFlag(false);
			dto.setMessage("原始密码错误");
			return dto;
		}
		// 执行修改密码的步骤
		System.out.println(tmpPSWmd5);
		userService.changePSW(username, changePSW01md5);
		// 。。。。。。todo

		dto.setFlag(true);
		dto.setMessage("修改成功");
		session123.invalidate();
		return dto;
	}
}
