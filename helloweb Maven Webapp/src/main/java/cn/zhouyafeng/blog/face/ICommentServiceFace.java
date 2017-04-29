package cn.zhouyafeng.blog.face;

import java.util.List;

import cn.zhouyafeng.blog.entity.CommentDetailEntity;
import cn.zhouyafeng.blog.entity.CommentPublishVo;
import cn.zhouyafeng.blog.entity.vo.CommentSearchVo;

public interface ICommentServiceFace {
	/**
	 * 新建评论
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年4月4日 上午1:03:02
	 * @param vo
	 * @return
	 */
	public int insertCommentById(CommentPublishVo vo);

	/**
	 * 根据博文id获取一级评论
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017年4月4日 上午1:05:16
	 * @return
	 */
	public List<CommentDetailEntity> getCommentDetailsByBlogId(CommentSearchVo searchVo);
}
