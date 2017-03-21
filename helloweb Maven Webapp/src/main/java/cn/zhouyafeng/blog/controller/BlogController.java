package cn.zhouyafeng.blog.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.rjeschke.txtmark.Processor;

import cn.zhouyafeng.blog.entity.BlogDetailEntity;
import cn.zhouyafeng.blog.entity.BlogEntity;
import cn.zhouyafeng.blog.entity.CommentPublishVo;
import cn.zhouyafeng.blog.service.IBlogService;
import cn.zhouyafeng.blog.service.ICommentService;
import cn.zhouyafeng.utils.JackSonUtils;

@Controller
public class BlogController {
	@Resource
	private IBlogService blogService;
	@Resource
	private ICommentService commentService;

	@RequestMapping(value = "/page/all", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAllBlog() {// ��ȡ���еĲ��ģ���ҳ��ʾ��ÿƪ����ֻȡǰ200����Ϊ���
		List<BlogDetailEntity> blogs = blogService.getAllBlogDetailEntity();
		for (BlogDetailEntity blog : blogs) {
			if (blog.getBlogContent().length() > 200) {
				blog.setBlogContent(blog.getBlogContent().substring(0, 200));
			}
			blog.setBlogContent(Processor.process(blog.getBlogContent()));
		}
		String result = null;
		try {
			result = JackSonUtils.obj2json(blogs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * ���ݲ���id��ȡ��������
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��3��21�� ����12:12:28
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail/one", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getBlogDetailById(String id) {
		BlogEntity blog = blogService.getBlogEntityById(id);
		String contentHtml = Processor.process(blog.getBlogContent());
		System.out.println(contentHtml);
		blog.setBlogContent(contentHtml);
		String result = null;
		try {
			result = JackSonUtils.obj2json(blog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * ����������
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��3��21�� ����11:15:16
	 * @param vo
	 */
	@RequestMapping(value = "/comment/publish/one", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public void publishComment(CommentPublishVo vo) {
		int t = commentService.insertCommentById(vo);
		System.out.println(t);
	}

}
