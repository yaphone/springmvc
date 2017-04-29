package cn.zhouyafeng.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.github.rjeschke.txtmark.Processor;

import cn.zhouyafeng.blog.entity.BlogDetailEntity;
import cn.zhouyafeng.blog.entity.BlogEntity;
import cn.zhouyafeng.blog.entity.vo.BlogSearchVo;
import cn.zhouyafeng.blog.face.IBlogServiceFace;
import cn.zhouyafeng.test.component.BaseJunitTest;

public class BlogUnitTest extends BaseJunitTest {

	@Resource
	private IBlogServiceFace blogService;

	// @Test
	public void JacksonUtilTest() {
		BlogEntity blog = blogService.getBlogEntityById("1");
		String content = blog.getContent();
		String result = null;
		try {
			result = JSON.toJSONString(blog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String html;
		html = Processor.process(content);
		System.out.println(html);
	}

	/**
	 * 根据标题获取博文测试
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年4月1日 下午10:36:09
	 */
	// @Test
	public void getBlogDetailEntityBySearchVoTest() {
		BlogSearchVo searchVo = new BlogSearchVo();
		searchVo.setTitle("微信网易云音乐播放器");
		BlogDetailEntity blogDetailEntity = blogService.getBlogDetailEntityBySearchVo(searchVo);
		System.out.println(JSON.toJSONString(blogDetailEntity));
	}

	/**
	 * 获取博文自增ID
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年4月1日 下午10:37:13
	 */
	@Test
	public void getNextBlogIdTest() {
		long nextId = blogService.getNextBlogId();
		System.out.println();
		System.out.println(nextId);
		System.out.println();
	}

}
