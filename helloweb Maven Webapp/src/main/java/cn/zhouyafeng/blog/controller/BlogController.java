package cn.zhouyafeng.blog.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
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

	@RequestMapping(value = "/page/all", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAllBlog() {// ��ȡ���еĲ��ģ���ҳ��ʾ��ÿƪ����ֻȡǰ200����Ϊ���
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
	 * ���ݲ���id��ȡ��������
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��3��21�� ����12:12:28
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
	 * ����������
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��3��21�� ����11:15:16
	 * @param vo
	 */
	@RequestMapping(value = "/comment/publish/one", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public void publishComment(CommentPublishVo vo) {
		int t = commentService.insertCommentById(vo);
		System.out.println(t);
	}

	/**
	 * �����ϴ��Ĳ���md�ļ�
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @return
	 * @date 2017��3��21�� ����11:51:28
	 */
	@RequestMapping(value = "/mdfile/upload", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> uploadMdfile(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request) {
		String fileName = file.getOriginalFilename();
		String path = "D:\\markdown\\";
		File localFile = new File(path, fileName);
		if (localFile.exists()) {
			System.out.println(localFile.getAbsolutePath());
			System.out.println(localFile.getName());
			System.out.println(localFile.length());
		} else {
			localFile.getParentFile().mkdirs();
			try {
				file.transferTo(localFile);
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * ��ȡ����md���ļ��������е��ļ�
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��3��28�� ����12:05:08
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
			List mdList = Arrays.asList(fa);
			HashMap<String, List> resultMap = new HashMap<String, List>();
			resultMap.put("md", mdList);
			try {
				result = JackSonUtils.obj2json(resultMap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
