package cn.zhouyafeng.blog.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.zhouyafeng.blog.entity.BlogDetailEntity;
import cn.zhouyafeng.blog.entity.BlogEntity;
import cn.zhouyafeng.blog.entity.vo.BlogSearchVo;

public interface IBlogService {
	/**
	 * ����ID��ȡ����
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��3��31�� ����12:20:34
	 * @param id
	 * @return
	 */
	public BlogEntity getBlogEntityById(String id);

	/**
	 * ��ȡ���еĲ���
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��3��31�� ����12:20:49
	 * @return
	 */
	public List<BlogDetailEntity> getAllBlogDetailEntity();

	/**
	 * �����²���
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��3��31�� ����12:21:00
	 * @param mdList
	 * @return
	 */
	public boolean publishNewBlog(List<String> mdList);

	/**
	 * ��������������ȡ����
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��3��31�� ����12:22:19
	 * @param searchVo
	 * @return
	 */
	public BlogDetailEntity getBlogDetailEntityBySearchVo(BlogSearchVo searchVo);

	/**
	 * ��ȡ��������ID
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��4��1�� ����10:35:25
	 * @return
	 */
	public int getNextBlogId();

	/**
	 * �����ϴ���md�ļ�
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��4��2�� ����12:30:11
	 */
	public boolean saveUploadMdfile(CommonsMultipartFile file);
}
