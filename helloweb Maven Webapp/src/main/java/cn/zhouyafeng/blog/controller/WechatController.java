package cn.zhouyafeng.blog.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.zhouyafeng.blog.service.IWechatService;

@Controller
public class WechatController {
	@Resource
	private IWechatService wechatService;

	@RequestMapping(value = "/wechat")
	@ResponseBody
	public String getAllBlog() {// 获取所有的博文，首页显示，每篇文章只取前200字作为简介
		wechatService.login();
		return JSON.toJSONString(new ResponseEntity<>("请扫描二维码登陆", HttpStatus.OK));
	}

}
