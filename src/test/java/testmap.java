import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.atm.mapper.UserMapper;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/springXML/springController.xml"})
@Transactional
public class testmap {
	
	@Autowired
	public UserMapper usermap;
	
	@Test
	public void testMapper() {
		int row = usermap.changePSW("zz", "802bca159b910392c97a9203d1db786b");
		assertEquals(1,row);		
	}

}
