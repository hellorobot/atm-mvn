package top.robotman.atm.task;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayuanit.atm.domain.TransferTask;
import com.dayuanit.atm.service.AtmService;
import com.dayuanit.atm.service.impl.AtmServiceImpl;

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
		List<TransferTask> list = atmservice.listTransferTask(tmptime.minusMinutes(timeout), 0);
		if (list != null) {
			for (TransferTask task : list) {
				atmservice.newTransferIN(task.getAmount(), task.getInCardnum(), task.getId());
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
