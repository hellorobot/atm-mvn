package top.robotman.atm.task;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayuanit.atm.domain.TransferTask;
import com.dayuanit.atm.service.AtmService;

@Component
public class TransferBusiness {
	@Autowired
	public AtmService atmservice;

	private int timeout;

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void checkAndTrans() {

		LocalDateTime tmptime = LocalDateTime.now();
		System.out.println("timeout=========================="+tmptime.minusMinutes(timeout));
		List<TransferTask> list = atmservice.listTransferTask(tmptime.minusMinutes(timeout), 0);
		if (list.size() != 0) {
			//System.out.println("list not null ============================="+list.size());
			for (TransferTask task : list) {
				try {
					//System.out.println("task.getInCardnum()========="+task.getInCardnum());
					atmservice.newTransferIN(task.getAmount(), task.getInCardnum(), task.getId());
					//websocket.sendMessage(userName);
				}catch(Exception e) {
					e.printStackTrace();
				}		
			}
		}
	}

	public void checkAndRollback() {
		LocalDateTime tmptime = LocalDateTime.now();
		List<TransferTask> list = atmservice.listTransferTask(tmptime.minusMinutes(timeout), 2);
		if (list != null) {
			for (TransferTask task : list) {
				atmservice.newTransferRollback(task.getAmount(), task.getOutCardnum(), task.getId());
			}
		}
	}

}
