package top.robotman.atm.handler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringControllerHandler {
	private static ApplicationContext context;
	
	public static void init() {
		context = new ClassPathXmlApplicationContext("/springXML/springController.xml");		

	}
	
	public static Object getController(String controller) {
		return context.getBean(controller);
	}

}
