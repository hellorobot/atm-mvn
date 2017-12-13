package top.robotman.atm.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import top.robotman.atm.handler.SpringControllerHandler;

public class MyLintener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		SpringControllerHandler.init();
		System.out.println(":D-------context contextInitialized");
		
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println(":D-------context destory");
		
	}

}
