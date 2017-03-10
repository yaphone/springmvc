package cn.zhouyafeng.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.zhouyafeng.blog.entity.Blog;
import cn.zhouyafeng.blog.impl.BlogService;
import cn.zhouyafeng.utils.JackSonUtils;

@Controller
public class HelloWorld {
	@Autowired
	private BlogService blogService;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		Blog blog = blogService.getBlogEntityById("1");
		try {
			System.out.println(JackSonUtils.obj2json(blog));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "hello";
	}
}
