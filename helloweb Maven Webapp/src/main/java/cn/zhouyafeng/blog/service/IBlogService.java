package cn.zhouyafeng.blog.service;

import java.util.List;

import cn.zhouyafeng.blog.entity.Blog;

public interface IBlogService {
	public Blog getBlogEntityById(String id);

	public List<Blog> getAllBlogEntity();

	public String test();
}
