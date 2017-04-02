package cn.zhouyafeng.blog.dao;

import java.util.List;

import cn.zhouyafeng.blog.entity.BlogDetailEntity;
import cn.zhouyafeng.blog.entity.vo.BlogSearchVo;

public interface BlogDetailEntityMapper {
	/**
	 * 获取所有的博文
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年3月21日 下午11:16:28
	 * @return
	 */
	public List<BlogDetailEntity> getAllBlogDetailEntity();

	/**
	 * 获取博文的下一个自增ID
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年3月21日 下午11:16:42
	 * @return
	 */
	public long getNextBlogId();

	/**
	 * 根据搜索条件查询博文
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年3月31日 上午12:19:31
	 * @param searchVo
	 * @return
	 */
	public BlogDetailEntity getBlogDetailEntityBySearchVo(BlogSearchVo searchVo);

}
