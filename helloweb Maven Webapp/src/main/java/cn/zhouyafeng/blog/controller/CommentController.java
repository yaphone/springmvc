package cn.zhouyafeng.blog.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.zhouyafeng.blog.entity.CommentDetailEntity;
import cn.zhouyafeng.blog.entity.vo.CommentSearchVo;
import cn.zhouyafeng.blog.service.ICommentService;

@Controller
public class CommentController {

	@Resource
	private ICommentService commentService;

	@RequestMapping(value = "/comment/all/blog_id")
	@ResponseBody
	public String getCommentDetailsByBlogId(@RequestParam("blogId") String blogId) {
		CommentSearchVo searchVo = new CommentSearchVo();
		searchVo.setBlogId(Long.parseLong(blogId));
		List<CommentDetailEntity> result = commentService.getCommentDetailsByBlogId(searchVo);
		if (result != null) {
			return JSON.toJSONString(new ResponseEntity<>(result, HttpStatus.OK));
		} else {
			return JSON.toJSONString(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

}
