package cn.zhouyafeng.blog.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.zhouyafeng.blog.dao.BlogDetailEntityMapper;
import cn.zhouyafeng.blog.dao.BlogEntityMapper;
import cn.zhouyafeng.blog.entity.BlogDetailEntity;
import cn.zhouyafeng.blog.entity.BlogEntity;
import cn.zhouyafeng.blog.entity.vo.BlogSearchVo;
import cn.zhouyafeng.blog.service.IBlogService;
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
		}
		MarkdownProcessor processor = new MarkdownProcessor(mdfileList);
		List<BlogEntity> blogList = processor.getBlogList();
		for (BlogEntity blog : blogList) {
			BlogSearchVo searchVo = new BlogSearchVo();
			searchVo.setBlogTitle(blog.getBlogTitle());
			BlogDetailEntity blogDetailEntity = getBlogDetailEntityBySearchVo(searchVo);
			if (blogDetailEntity == null) { // ���ݿ��в����ڣ�������
				blog.setId(getNextBlogId()); // ����ID
				blog.setUpdateTime(new Date());// ����ʱ��
				blog.setModifyTime(new Date());// �޸�ʱ�䣬Ĭ���뷢��ʱ����ͬ
				blog.setReadingCount(0); // ����ʱ�Ķ�����Ϊ0
				blogEntityMapper.insert(blog);
			} else { // ���ݿ����Ѵ���
				blog.setModifyTime(new Date());
				blogEntityMapper.updateByPrimaryKeySelective(blog);
			}
		}
		return true;
	}

	@Override
	public BlogDetailEntity getBlogDetailEntityBySearchVo(BlogSearchVo searchVo) {
		BlogDetailEntity blogDetailEntity = blogDetailEntityMapper.getBlogDetailEntityBySearchVo(searchVo);
		return blogDetailEntity;
	}

	@Override
	public int getNextBlogId() {
		return blogDetailEntityMapper.getNextBlogId();
	}

}
