package cn.zhouyafeng.blog.face;

import java.util.List;

import cn.zhouyafeng.blog.entity.CommentDetailEntity;
import cn.zhouyafeng.blog.entity.CommentPublishVo;
import cn.zhouyafeng.blog.entity.vo.CommentSearchVo;

public interface ICommentServiceFace {
	/**
	 * �½�����
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��4��4�� ����1:03:02
	 * @param vo
	 * @return
	 */
	public int insertCommentById(CommentPublishVo vo);

	/**
	 * ���ݲ���id��ȡһ������
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��4��4�� ����1:05:16
	 * @return
	 */
	public List<CommentDetailEntity> getCommentDetailsByBlogId(CommentSearchVo searchVo);
}
