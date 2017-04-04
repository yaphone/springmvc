package cn.zhouyafeng.blog.dao;

import java.util.List;

import cn.zhouyafeng.blog.entity.CommentDetailEntity;
import cn.zhouyafeng.blog.entity.vo.CommentSearchVo;

public interface CommentDetailEntityMapper {
	/**
	 * 获取一级评论的自增ID
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年4月4日 上午1:06:23
	 * @return
	 */
	public long getNextCommentId();

	/**
	 * 根据博文ID获取一级评论列表
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年4月4日 上午1:06:44
	 * @return
	 */
	public List<CommentDetailEntity> getCommentDetailsByBlogId(CommentSearchVo searchVo);

}
