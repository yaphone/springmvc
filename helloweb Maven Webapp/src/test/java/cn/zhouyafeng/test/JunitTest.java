package cn.zhouyafeng.test;

import org.junit.Test;

import cn.zhouyafeng.blog.entity.BlogEntity;
import cn.zhouyafeng.utils.JackSonUtils;

public class JunitTest {

	@Test
	public void helloWorld() {
		BlogEntity blogEntity = new BlogEntity();
		blogEntity.setBlogTitle("≤‚ ‘");
		blogEntity.setId("123");
		try {
			System.out.println(JackSonUtils.obj2json(blogEntity));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
