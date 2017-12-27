package top.robotman.atm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
//@Controller
//@RequestMapping("/user")
public abstract class BaseController {
	
	@RequestMapping("/toUserCenter")
	public String toUserCenter(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		return  "userCenter" ;
	}
	


	

}
