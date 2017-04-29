package cn.zhouyafeng.blog.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.zhouyafeng.blog.dao.BlogDetailEntityMapper;
import cn.zhouyafeng.blog.dao.BlogEntityMapper;
import cn.zhouyafeng.blog.entity.BlogDetailEntity;
import cn.zhouyafeng.blog.entity.BlogEntity;
import cn.zhouyafeng.blog.entity.vo.BlogSearchVo;
import cn.zhouyafeng.blog.face.IBlogServiceFace;
import cn.zhouyafeng.utils.MarkdownProcessor;

@Service("blogService")
public class BlogService implements IBlogServiceFace {

	@Resource
	private BlogEntityMapper blogEntityMapper;
	@Resource
	private BlogDetailEntityMapper blogDetailEntityMapper;
	@Value("${markdownPath}")
	private String markdownPath;

	@Override
	public BlogEntity getBlogEntityById(String id) {
		BlogEntity blog = blogEntityMapper.selectByPrimaryKey(Long.parseLong(id));
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
			searchVo.setTitle(blog.getTitle());
			BlogDetailEntity blogDetailEntity = getBlogDetailEntityBySearchVo(searchVo);
			if (blogDetailEntity == null) { // 数据库中不存在，则新增
				blog.setId(getNextBlogId()); // 博文ID
				blog.setPublishTime(new Date());// 发表时间
				blog.setUpdateTime(new Date());// 修改时间，默认与发表时间相同
				blog.setReadingCount(0L); // 发表时阅读次数为0
				blogEntityMapper.insert(blog);
			} else { // 数据库中已存在，则更新
				blog.setId(blogDetailEntity.getId());
				blog.setUpdateTime(new Date());
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
	public long getNextBlogId() {
		return blogDetailEntityMapper.getNextBlogId();
	}

	@Override
	public boolean saveUploadMdfile(CommonsMultipartFile file) {
		String fileName = file.getOriginalFilename();
		File localFile = new File(markdownPath, fileName);
		if (localFile.exists()) {
			System.out.println(localFile.getAbsolutePath());
			System.out.println(localFile.getName());
			System.out.println(localFile.length());
		} else {
			localFile.getParentFile().mkdirs();
			try {
				file.transferTo(localFile);
				return true;
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public List<String> getLocalMdfileNameList() {
		List<String> mdList = null;
		File file = new File(markdownPath);
		if (!file.exists()) {
			System.out.println("File is not exist");
			mdList = null;
		} else {
			String[] fa = file.list();
			mdList = Arrays.asList(fa);
		}

		return mdList;
	}

}
