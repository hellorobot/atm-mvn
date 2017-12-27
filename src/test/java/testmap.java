import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.atm.domain.BankCard;
import com.dayuanit.atm.mapper.BankCardMapper2;
import com.dayuanit.atm.mapper.FLowMapper;
import com.dayuanit.atm.mapper.UserMapper;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/springXML/springController.xml"})
@Transactional
public class testmap {
	
	@Autowired
	public UserMapper usermap;
	@Autowired
	public FLowMapper flowrmap;
	@Autowired
	public BankCardMapper2 bcm;
	
//	@Test
//	public void testMapper() {
//		int row = usermap.changePSW("zz", "802bca159b910392c97a9203d1db786b");
//		assertEquals(1,row);		
//	}
	@Test
	public void testMapper() {
		BankCard bc = bcm.getBankCard("1000000");
		System.out.println(bc.getBalance());
		System.out.println(bc.getPassword());
		assertNotNull(bc);
	
}
	
	
	

}
