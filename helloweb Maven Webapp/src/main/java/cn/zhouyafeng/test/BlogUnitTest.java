package cn.zhouyafeng.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.github.rjeschke.txtmark.Processor;

import cn.zhouyafeng.blog.entity.BlogDetailEntity;
import cn.zhouyafeng.blog.entity.BlogEntity;
import cn.zhouyafeng.blog.entity.vo.BlogSearchVo;
import cn.zhouyafeng.blog.service.IBlogService;
import cn.zhouyafeng.test.component.BaseJunitTest;
import cn.zhouyafeng.utils.JackSonUtils;

public class BlogUnitTest extends BaseJunitTest {

	@Resource
	private IBlogService blogService;

	// @Test
	public void JacksonUtilTest() {
		BlogEntity blog = blogService.getBlogEntityById("1");
		String content = blog.getBlogContent();
		String result = null;
		try {
			result = JackSonUtils.obj2json(blog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String html;
		html = Processor.process(content);
		System.out.println(html);
	}

	@Test
	public void getBlogDetailEntityBySearchVoTest() {
		BlogSearchVo searchVo = new BlogSearchVo();
		searchVo.setBlogTitle("Î¢ÐÅÍøÒ×ÔÆÒôÀÖ²¥·ÅÆ÷");
		BlogDetailEntity blogDetailEntity = blogService.getBlogDetailEntityBySearchVo(searchVo);
		try {
			System.out.println(JackSonUtils.obj2json(blogDetailEntity));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
