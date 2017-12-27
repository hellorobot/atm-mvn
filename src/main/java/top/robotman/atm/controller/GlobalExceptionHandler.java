package top.robotman.atm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.atm.exception.BizException;

import top.robotman.atm.ajaxDTO.AjaxDTO;

/**全局异常处理，捕获所有Controller中抛出的异常。
 * 
 * @author laozhao
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(BizException.class)
	@ResponseBody
	public Object bizExceptionHandler(BizException biz) {
		//biz.printStackTrace();
		log.error("", biz);
		return AjaxDTO.fail(biz.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object ExceptionHandler(Exception e) {
		log.error("", e);
		return AjaxDTO.fail("操作异常，请联系客服电话：10086");
	}


}
