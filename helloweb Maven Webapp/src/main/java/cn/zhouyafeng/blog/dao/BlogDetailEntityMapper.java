package cn.zhouyafeng.blog.dao;

import java.util.List;

import cn.zhouyafeng.blog.entity.BlogDetailEntity;

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
	public int getNextBlogId();

}
