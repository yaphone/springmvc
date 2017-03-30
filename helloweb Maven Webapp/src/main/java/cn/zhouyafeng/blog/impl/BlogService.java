package cn.zhouyafeng.blog.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.zhouyafeng.blog.dao.BlogDetailEntityMapper;
import cn.zhouyafeng.blog.dao.BlogEntityMapper;
import cn.zhouyafeng.blog.entity.BlogDetailEntity;
import cn.zhouyafeng.blog.entity.BlogEntity;
import cn.zhouyafeng.blog.service.IBlogService;
import cn.zhouyafeng.utils.JackSonUtils;
import cn.zhouyafeng.utils.MarkdownProcessor;

@Service("blogService")
public class BlogService implements IBlogService {

	@Resource
	private BlogEntityMapper blogEntityMapper;
	@Resource
	private BlogDetailEntityMapper blogDetailEntityMapper;
	@Value("${markdownPath}")
	private String markdownPath;

	@Override
	public BlogEntity getBlogEntityById(String id) {
		BlogEntity blog = blogEntityMapper.selectByPrimaryKey(Integer.parseInt(id));
		return blog;
	}

	@Override
	public List<BlogDetailEntity> getAllBlogDetailEntity() {
		return blogDetailEntityMapper.getAllBlogDetailEntity();
	}

	@Override
	public boolean publishNewBlog(List<String> mdList) {
		List<File> mdfileList = new ArrayList<File>();
		for (String mdName : mdList) {
			String path = markdownPath + mdName;
			File mdfile = new File(path);
			mdfileList.add(mdfile);
			MarkdownProcessor processor = new MarkdownProcessor(mdfileList);
			List<BlogEntity> blogList = processor.getBlogList();
			for (BlogEntity blog : blogList) {
				try {
					System.out.println(JackSonUtils.obj2json(blog));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return true;
	}

}
