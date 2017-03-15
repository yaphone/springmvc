package cn.zhouyafeng.test;

import com.github.rjeschke.txtmark.Processor;

public class MyTest {

	public static void main(String[] args) {
		String html = Processor.process("#Hello World");
		System.out.println(html);
	}

}
