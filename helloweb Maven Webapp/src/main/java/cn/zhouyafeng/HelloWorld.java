package cn.zhouyafeng;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zhouyafeng.blog.entity.Blog;
import cn.zhouyafeng.blog.service.IBlogService;
import cn.zhouyafeng.utils.JackSonUtils;

@Controller
public class HelloWorld {

	@Resource
	private IBlogService blogService;

	@RequestMapping(value = "/hello")
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "yahoo");
		return "hello";
	}

	@RequestMapping(value = "test")
	public void test() {
		Blog blog = blogService.getBlogEntityById("1");
		try {
			System.out.println(JackSonUtils.obj2json(blog));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
