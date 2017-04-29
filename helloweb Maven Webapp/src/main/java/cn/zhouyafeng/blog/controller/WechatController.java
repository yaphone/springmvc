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
	public String getAllBlog() {// ��ȡ���еĲ��ģ���ҳ��ʾ��ÿƪ����ֻȡǰ200����Ϊ���
		wechatService.login();
		return JSON.toJSONString(new ResponseEntity<>("��ɨ���ά���½", HttpStatus.OK));
	}

}
