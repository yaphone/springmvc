package cn.zhouyafeng.blog.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.zhouyafeng.blog.entity.BlogDetailEntity;
import cn.zhouyafeng.blog.entity.BlogEntity;
import cn.zhouyafeng.blog.entity.vo.BlogSearchVo;

public interface IBlogService {
	/**
	 * 根据ID获取博文
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

	/**
	 * 获取博文自增ID
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年4月1日 下午10:35:25
	 * @return
	 */
	public int getNextBlogId();

	/**
	 * 保存上传的md文件
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年4月2日 上午12:30:11
	 */
	public boolean saveUploadMdfile(CommonsMultipartFile file);
}
