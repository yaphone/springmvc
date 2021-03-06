package cn.zhouyafeng.blog.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhouyafeng.blog.dao.CommentDetailEntityMapper;
import cn.zhouyafeng.blog.dao.CommentEntityMapper;
import cn.zhouyafeng.blog.entity.CommentDetailEntity;
import cn.zhouyafeng.blog.entity.CommentEntity;
import cn.zhouyafeng.blog.entity.CommentPublishVo;
import cn.zhouyafeng.blog.entity.vo.CommentSearchVo;
import cn.zhouyafeng.blog.face.ICommentServiceFace;

@Service("commentService")
public class CommentService implements ICommentServiceFace {

	@Resource
	public CommentEntityMapper commentEntityMapper;
	@Autowired
	public CommentDetailEntityMapper commentDetailEntityMapper;

	@Override
	public int insertCommentById(CommentPublishVo vo) {
		return commentEntityMapper.insert(convertVoToEntity(vo));
	}

	public CommentEntity convertVoToEntity(CommentPublishVo vo) {
		CommentEntity e = new CommentEntity();
		e.setBlogId(vo.getBlogId());
		e.setPublishTime(new Date());
		e.setContent(vo.getContent());
		e.setEmail(vo.getEmail());
		e.setId(commentDetailEntityMapper.getNextCommentId());
		e.setNickName(vo.getNickName());
		return e;
	}

	@Override
	public List<CommentDetailEntity> getCommentDetailsByBlogId(CommentSearchVo searchVo) {
		return commentDetailEntityMapper.getCommentDetailsByBlogId(searchVo);
	}

}
