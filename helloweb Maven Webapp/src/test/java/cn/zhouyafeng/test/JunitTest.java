package cn.zhouyafeng.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zhouyafeng.blog.entity.Blog;
import cn.zhouyafeng.blog.service.IBlogService;
import cn.zhouyafeng.utils.JackSonUtils;

public class JunitTest {

	@Autowired
	private IBlogService blogService;

	// @Test
	public void helloWorld() {
		Blog blog = new Blog();
		blog.setBlogTitle("≤‚ ‘");
		blog.setId(123);
		try {
			System.out.println(JackSonUtils.obj2json(blog));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void myBatisTest() {
		Blog blog = blogService.getBlogEntityById("1");
		try {
			System.out.println(JackSonUtils.obj2json(blog));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
