package top.robotman.atm.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.dayuanit.atm.service.AtmService;
@Component
public class ConcurrentTest {
	
	
	private static final int THREAD_NUM = 10000;
	static AtmService ser;
	static AtomicInteger success=new AtomicInteger();
	static AtomicInteger fail=new AtomicInteger();

	static CountDownLatch cd = new CountDownLatch(THREAD_NUM);
	
	
	public static class TestThread implements Runnable{
			
		CyclicBarrier cyclicbarrier;
		
		public TestThread(CyclicBarrier b) {
			cyclicbarrier = b;
		}

		@Override
		public void run() {
			System.out.println("执行到此等待大家===");
			try {
				cyclicbarrier.await();
				ser.newTransfer("1", "5921", "1000000", "1");
				System.out.println("================+"+success.getAndIncrement());
				
			} catch (Exception e) {
				
				e.printStackTrace();
				System.out.println("================+"+fail.getAndAdd(1));
			}
			cd.countDown();
		}	
		
	}
	
	public static void main(String[] args) {
		
		ApplicationContext appCt = new ClassPathXmlApplicationContext("classpath:springXML/springController.xml");
		ser = appCt.getBean(AtmService.class);
		
		
		CyclicBarrier cb = new CyclicBarrier(THREAD_NUM, new Runnable() {
			
			@Override
			public void run() {
				System.out.println("====这是cyclic内部线程====");		
			}
		});
		
		for(int i=0;i<THREAD_NUM;i++) {
			new Thread(new TestThread(cb)).start();
		}
		
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			cd.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("结果成功："+success);
		System.out.println("结果失败："+fail);
		
		
	}

}
