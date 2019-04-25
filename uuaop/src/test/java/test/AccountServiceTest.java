package test;


import com.jsc.service.AccountService;
import com.jsc.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
public class AccountServiceTest {
//    @Autowired
//    private AccountService accountService;

    @Test
    public void accountServiceTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountServiceImpl accountService = (AccountServiceImpl) applicationContext.getBean("accountServicexxx");
        accountService.saveAccount();
    }
}
