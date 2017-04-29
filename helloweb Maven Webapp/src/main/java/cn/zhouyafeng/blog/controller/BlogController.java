package cn.zhouyafeng.blog.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.rjeschke.txtmark.Processor;

import cn.zhouyafeng.blog.entity.BlogDetailEntity;
import cn.zhouyafeng.blog.entity.BlogEntity;
import cn.zhouyafeng.blog.entity.CommentPublishVo;
import cn.zhouyafeng.blog.face.IBlogServiceFace;
import cn.zhouyafeng.blog.face.ICommentServiceFace;

@Controller
public class BlogController {
	@Resource
	private IBlogServiceFace blogService;
	@Resource
	private ICommentServiceFace commentService;

	@RequestMapping(value = "/page/all", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAllBlog() {// 获取所有的博文，首页显示，每篇文章只取前200字作为简介
		List<BlogDetailEntity> blogs = blogService.getAllBlogDetailEntity();
		for (BlogDetailEntity blog : blogs) {
			if (blog.getContent().length() > 200) {
				blog.setContent(blog.getContent().substring(0, 200));
			}
			blog.setContent(Processor.process(blog.getContent()));
		}
		return JSON.toJSONString(new ResponseEntity<>(blogs, HttpStatus.OK));
	}

	/**
	 * 根据博文id获取博文详情
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年3月21日 上午12:12:28
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail/one", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getBlogDetailById(String id) {
		BlogEntity blog = blogService.getBlogEntityById(id);
		String contentHtml = Processor.process(blog.getContent());
		blog.setContent(contentHtml);
		return JSON.toJSONString(new ResponseEntity<>(blog, HttpStatus.OK));
	}

	/**
	 * 发表新评论
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年3月21日 下午11:15:16
	 * @param vo
	 */
	@RequestMapping(value = "/comment/publish/one", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public void publishComment(CommentPublishVo vo) {
		int t = commentService.insertCommentById(vo);
		System.out.println(t);
	}

	/**
	 * 保存上传的博文md文件
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @return
	 * @date 2017年3月21日 下午11:51:28
	 */
	@RequestMapping(value = "/mdfile/upload", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> saveUploadMdfile(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request) {
		boolean result = blogService.saveUploadMdfile(file);
		if (result == true) {
			return new ResponseEntity<>(HttpStatus.OK); // 文件保存成功
		} else {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE); // 文件保存失败
		}
	}

	/**
	 * 获取保存md的文件夹下所有的文件
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年3月28日 上午12:05:08
	 * @return
	 */
	@RequestMapping(value = "/publish/mdfile/all")
	@ResponseBody
	public String getAllMdfile() {
		List<String> mdList = blogService.getLocalMdfileNameList();
		if (mdList != null) {
			return JSON.toJSONString(new ResponseEntity<>(mdList, HttpStatus.OK));
		} else {
			return JSON.toJSONString(new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE));
		}
	}

	/**
	 * 前端发过来需要发表的博文列表
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年3月29日 上午12:16:18
	 * @param mdNameList
	 * @return
	 */
	@RequestMapping(value = "/publish/new/many")
	@ResponseBody
	public String publishNewMdfiles(@RequestParam("md_name_list") String mdNameList) {
		List<String> mdList = Arrays.asList(mdNameList.split(","));
		boolean result = blogService.publishNewBlog(mdList);
		if (result) {
			return JSON.toJSONString(new ResponseEntity<>(result, HttpStatus.OK));
		} else {
			return JSON.toJSONString(new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE));
		}
	}
}
