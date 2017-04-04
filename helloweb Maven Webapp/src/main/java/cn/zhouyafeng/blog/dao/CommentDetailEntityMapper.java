package cn.zhouyafeng.blog.dao;

import java.util.List;

import cn.zhouyafeng.blog.entity.CommentDetailEntity;
import cn.zhouyafeng.blog.entity.vo.CommentSearchVo;

public interface CommentDetailEntityMapper {
	/**
	 * ��ȡһ�����۵�����ID
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��4��4�� ����1:06:23
	 * @return
	 */
	public long getNextCommentId();

	/**
	 * ���ݲ���ID��ȡһ�������б�
	 * 
	 * @author Email:zhouyaphone@163.com
	 * @date 2017��4��4�� ����1:06:44
	 * @return
	 */
	public List<CommentDetailEntity> getCommentDetailsByBlogId(CommentSearchVo searchVo);

}
