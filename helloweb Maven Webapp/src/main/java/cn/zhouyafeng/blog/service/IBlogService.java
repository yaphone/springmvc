package cn.zhouyafeng.blog.service;

import java.util.List;

import cn.zhouyafeng.blog.entity.BlogDetailEntity;
import cn.zhouyafeng.blog.entity.BlogEntity;

public interface IBlogService {
	public BlogEntity getBlogEntityById(String id);

	public List<BlogDetailEntity> getAllBlogDetailEntity();
}
