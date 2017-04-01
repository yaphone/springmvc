package cn.zhouyafeng.blog.controller;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
	@Value("${markdownPath}")
	private String markdownPath;

	@RequestMapping(value = "/page/all", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAllBlog() {// 获取所有的博文，首页显示，每篇文章只取前200字作为简介
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
		String result = "";
		String path = "D:\\markdown\\";
		File file = new File(path);
		if (!file.exists()) {
			System.out.println("File is not exist");
			result = null;
		} else {
			String[] fa = file.list();
			List<String> mdList = Arrays.asList(fa);
			HashMap<String, List<String>> resultMap = new HashMap<String, List<String>>();
			resultMap.put("md", mdList);
			try {
				ResponseEntity<HashMap> resultEntity = new ResponseEntity<HashMap>(resultMap, HttpStatus.OK);
				result = JackSonUtils.obj2json(resultEntity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
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
		String result = "";
		List<String> mdList = Arrays.asList(mdNameList.split(","));
		boolean res = blogService.publishNewBlog(mdList);
		return result;
	}
}
