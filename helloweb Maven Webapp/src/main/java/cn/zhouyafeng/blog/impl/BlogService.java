package cn.zhouyafeng.blog.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zhouyafeng.blog.dao.BlogEntityMapper;
import cn.zhouyafeng.blog.dao.BlogMapper;
import cn.zhouyafeng.blog.entity.Blog;
import cn.zhouyafeng.blog.service.IBlogService;

@Service("blogService")
public class BlogService implements IBlogService {

	@Resource
	private BlogMapper blogMapper;
	@Resource
	private BlogEntityMapper blogEntityMapper;

	@Override
	public Blog getBlogEntityById(String id) {
		Blog blog = blogMapper.selectByPrimaryKey(1);
		return blog;
	}

	@Override
	public List<Blog> getAllBlogEntity() {
		return blogEntityMapper.getAllBlogEntity();
	}

}
