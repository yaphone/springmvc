package cn.zhouyafeng.test;

import java.util.List;

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
		List<Blog> blog = blogService.getAllBlogEntity();
		try {
			System.out.println(JackSonUtils.obj2json(blog));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
