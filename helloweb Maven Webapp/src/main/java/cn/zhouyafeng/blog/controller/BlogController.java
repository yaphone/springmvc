package cn.zhouyafeng.blog.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhouyafeng.blog.entity.Blog;
import cn.zhouyafeng.blog.service.IBlogService;
import cn.zhouyafeng.utils.JackSonUtils;

@Controller
public class BlogController {
	@Resource
	private IBlogService blogService;

	@RequestMapping(value = "/page/all", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAllBlog() {
		List<Blog> blog = blogService.getAllBlogEntity();
		String result = null;
		try {
			result = JackSonUtils.obj2json(blog);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/detail/one", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getBlogDetailById(String id) {
		Blog blog = blogService.getBlogEntityById("1");
		String result = null;
		try {
			result = JackSonUtils.obj2json(blog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
