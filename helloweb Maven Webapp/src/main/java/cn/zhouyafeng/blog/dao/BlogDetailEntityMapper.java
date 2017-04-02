package cn.zhouyafeng.blog.dao;

import java.util.List;

import cn.zhouyafeng.blog.entity.BlogDetailEntity;
import cn.zhouyafeng.blog.entity.vo.BlogSearchVo;

public interface BlogDetailEntityMapper {
	/**
	 * ��ȡ���еĲ���
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��3��21�� ����11:16:28
	 * @return
	 */
	public List<BlogDetailEntity> getAllBlogDetailEntity();

	/**
	 * ��ȡ���ĵ���һ������ID
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��3��21�� ����11:16:42
	 * @return
	 */
	public long getNextBlogId();

	/**
	 * ��������������ѯ����
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��3��31�� ����12:19:31
	 * @param searchVo
	 * @return
	 */
	public BlogDetailEntity getBlogDetailEntityBySearchVo(BlogSearchVo searchVo);

}
