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
	public String getAllBlog() {// ��ȡ���еĲ��ģ���ҳ��ʾ��ÿƪ����ֻȡǰ200����Ϊ���
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
		String contentHtml = Processor.process(blog.getContent());
		blog.setContent(contentHtml);
		return JSON.toJSONString(new ResponseEntity<>(blog, HttpStatus.OK));
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
	public ResponseEntity<Object> saveUploadMdfile(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request) {
		boolean result = blogService.saveUploadMdfile(file);
		if (result == true) {
			return new ResponseEntity<>(HttpStatus.OK); // �ļ�����ɹ�
		} else {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE); // �ļ�����ʧ��
		}
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
		List<String> mdList = blogService.getLocalMdfileNameList();
		if (mdList != null) {
			return JSON.toJSONString(new ResponseEntity<>(mdList, HttpStatus.OK));
		} else {
			return JSON.toJSONString(new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE));
		}
	}

	/**
	 * ǰ�˷�������Ҫ����Ĳ����б�
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��3��29�� ����12:16:18
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
