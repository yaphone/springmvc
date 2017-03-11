package cn.zhouyafeng.test;

import javax.annotation.Resource;

import org.junit.Test;

import cn.zhouyafeng.blog.entity.Blog;
import cn.zhouyafeng.blog.service.IBlogService;
import cn.zhouyafeng.test.component.BaseJunitTest;
import cn.zhouyafeng.utils.JackSonUtils;

public class SimpleTest extends BaseJunitTest {

	@Resource
	private IBlogService blogService;

	@Test
	public void JacksonUtilTest() {
		Blog blog = blogService.getBlogEntityById("1");
		System.out.println(blog);
		try {
			System.out.println(JackSonUtils.obj2json(blog));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
