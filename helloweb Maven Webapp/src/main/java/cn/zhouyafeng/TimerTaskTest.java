package cn.zhouyafeng;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimerTaskTest {
	@Scheduled(cron = "0/5 * *  * * ? ") // 每10秒执行一次
	public void test() {
		System.out.println("Hello World!!!");
	}
}
