package cn.zhouyafeng;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimerTaskTest {
	@Scheduled(cron = "0/5 * *  * * ? ") // ÿ10��ִ��һ��
	public void test() {
		System.out.println("Hello World!!!");
	}
}
