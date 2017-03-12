package cn.zhouyafeng.test.component;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/springmvc.xml", "classpath:config/spring-mybatis.xml" })
public class BaseJunitTest {
	@BeforeClass
	public static void testBeforeClass() {
		System.out.println("--------------------------------");
	}

	@AfterClass
	public static void afterBeforeClass() {
		System.out.println("*********************************");
	}

}