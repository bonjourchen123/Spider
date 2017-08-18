package org.iii.core.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.iii.core.security.entity.SecUser;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath:spring-application.xml",
		"classpath:spring-*.xml"
		})
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@Transactional(isolation = Isolation.READ_COMMITTED)
public class GenericTest {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ApplicationContext context;
	
	public SecUser getMockLoginUser() {
		SecUser loginUser = new SecUser();
		loginUser.setAccount("admin");
		loginUser.setId(1l);
		loginUser.setPassword(DigestUtils.md5Hex("admin"));
		loginUser.setName("admin");
		return loginUser;
	}
	
	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	protected Object getBean(String beanName) {
		return getContext().getBean(beanName);
	}
	
/*	@BeforeClass
	public static void jndiSetup() throws Exception {
		SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
		builder.bind("java:comp/env/jdbc/bcmn", new SimpleDriverDataSource(new SQLServerDriver(),
				"jdbc:sqlserver://msdb.softleader.com.tw;database=testdb", "sa", "sldb01"));
		builder.activate();
	}*/
}
