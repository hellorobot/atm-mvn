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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;
import com.dayuanit.atm.domain.BankCard;
import com.dayuanit.atm.domain.User;
import com.dayuanit.atm.exception.BizException;
import com.dayuanit.atm.service.AtmService;
import com.dayuanit.atm.service.UserService;
import com.dayuanit.atm.service.impl.AtmServiceImpl;
import com.dayuanit.atm.service.impl.UserServiceimpl;

import top.robotman.atm.ajaxDTO.AjaxDTO;
import top.robotman.atm.flipPages.FlipPage;

public abstract class BaseController {
	
	public String toUserCenter(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		return "/WEB-INF/pages/" + "userCenter" + ".jsp";
	}


	

}
