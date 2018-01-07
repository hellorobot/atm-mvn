package top.robotman.atm.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dayuanit.captcha.Captcha;
import com.dayuanit.captcha.GifCaptcha;

@Controller
@RequestMapping("/checkcode")
public class CheckcodeController {
	
	@RequestMapping("/getCheckcode")
	public void getCaptcha(HttpServletResponse response, HttpSession session) {
		try {
			//这都什么意思。。。。
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/gif");

			// gif格式动画验证码 宽，高，位数。
			Captcha captcha = new GifCaptcha(146, 33, 4);

			captcha.out(response.getOutputStream());

			session.setAttribute("checkcode", captcha.text().toLowerCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
