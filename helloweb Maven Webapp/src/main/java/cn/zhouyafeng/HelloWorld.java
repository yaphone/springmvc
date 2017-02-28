package cn.zhouyafeng;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {
	@RequestMapping(value = "/hello")
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "yahoo");
		return "hello";
	}
}
