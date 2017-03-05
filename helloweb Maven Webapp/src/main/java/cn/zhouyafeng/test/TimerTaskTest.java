package cn.zhouyafeng.test;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimerTaskTest {
	@Scheduled(cron = "* 5 * * * ? ")
	public void test() {
		System.out.println("Hello World!!!");
	}
}
