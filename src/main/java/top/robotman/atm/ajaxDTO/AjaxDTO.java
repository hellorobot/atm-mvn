package top.robotman.atm.ajaxDTO;

public class AjaxDTO {
	private boolean flag;
	private String message;
	private Object data;
	private int statusCode;
	
	
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public static AjaxDTO success(String message) {
		AjaxDTO dto = new AjaxDTO();
		dto.setFlag(true);
		dto.setMessage(message);
		return dto;
	}
//重载方法	
	public static AjaxDTO success(Object obj) {
		AjaxDTO dto = new AjaxDTO();
		dto.setFlag(true);
		dto.setData(obj);
		return dto;
	}
	
	public static AjaxDTO fail(String message) {
		AjaxDTO dto = new AjaxDTO();
		dto.setFlag(false);
		dto.setMessage(message);
		return dto;
	}
	
	

}
