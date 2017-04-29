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
	 * ���ݱ����ȡ���Ĳ���
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��4��1�� ����10:36:09
	 */
	// @Test
	public void getBlogDetailEntityBySearchVoTest() {
		BlogSearchVo searchVo = new BlogSearchVo();
		searchVo.setTitle("΢�����������ֲ�����");
		BlogDetailEntity blogDetailEntity = blogService.getBlogDetailEntityBySearchVo(searchVo);
		System.out.println(JSON.toJSONString(blogDetailEntity));
	}

	/**
	 * ��ȡ��������ID
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��4��1�� ����10:37:13
	 */
	@Test
	public void getNextBlogIdTest() {
		long nextId = blogService.getNextBlogId();
		System.out.println();
		System.out.println(nextId);
		System.out.println();
	}

}
