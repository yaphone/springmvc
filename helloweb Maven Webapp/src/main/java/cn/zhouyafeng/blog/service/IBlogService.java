package cn.zhouyafeng.blog.service;

import java.util.List;

import cn.zhouyafeng.blog.entity.BlogDetailEntity;
import cn.zhouyafeng.blog.entity.BlogEntity;
import cn.zhouyafeng.blog.entity.vo.BlogSearchVo;

public interface IBlogService {
	/**
	 * 获取自增ID
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年3月31日 上午12:20:34
	 * @param id
	 * @return
	 */
	public BlogEntity getBlogEntityById(String id);

	/**
	 * 获取所有的博文
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年3月31日 上午12:20:49
	 * @return
	 */
	public List<BlogDetailEntity> getAllBlogDetailEntity();

	/**
	 * 发表新博文
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年3月31日 上午12:21:00
	 * @param mdList
	 * @return
	 */
	public boolean publishNewBlog(List<String> mdList);

	/**
	 * 根据搜索条件获取博文
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年3月31日 上午12:22:19
	 * @param searchVo
	 * @return
	 */
	public BlogDetailEntity getBlogDetailEntityBySearchVo(BlogSearchVo searchVo);
}
