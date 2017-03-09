package cn.zhouyafeng.test;

import javax.annotation.Resource;

import org.junit.Test;

import cn.zhouyafeng.blog.entity.Blog;
import cn.zhouyafeng.blog.impl.BlogService;
import cn.zhouyafeng.utils.JackSonUtils;

public class JunitTest {

	@Resource
	private BlogService blogService = null;

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
