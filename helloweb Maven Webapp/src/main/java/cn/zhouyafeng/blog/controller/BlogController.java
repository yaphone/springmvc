package cn.zhouyafeng.blog.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.rjeschke.txtmark.Processor;

import cn.zhouyafeng.blog.entity.Blog;
import cn.zhouyafeng.blog.service.IBlogService;
import cn.zhouyafeng.utils.JackSonUtils;

@Controller
public class BlogController {
	@Resource
	private IBlogService blogService;

	@RequestMapping(value = "/page/all", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAllBlog() {// 获取所有的博文，首页显示，每篇文章只取前200字作为简介
		List<Blog> blogs = blogService.getAllBlogEntity();
		for (Blog blog : blogs) {
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

	@RequestMapping(value = "/detail/one", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getBlogDetailById(String id) {
		Blog blog = blogService.getBlogEntityById(id);
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

}
