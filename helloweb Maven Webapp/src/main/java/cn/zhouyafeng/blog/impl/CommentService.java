package cn.zhouyafeng.blog.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhouyafeng.blog.dao.CommentDetailEntityMapper;
import cn.zhouyafeng.blog.dao.CommentEntityMapper;
import cn.zhouyafeng.blog.entity.CommentEntityWithBLOBs;
import cn.zhouyafeng.blog.entity.CommentPublishVo;
import cn.zhouyafeng.blog.service.ICommentService;

@Service("commentService")
public class CommentService implements ICommentService {

	@Resource
	public CommentEntityMapper commentEntityMapper;
	@Autowired
	public CommentDetailEntityMapper commentDetailEntityMapper;

	@Override
	public int insertCommentById(CommentPublishVo vo) {
		return commentEntityMapper.insert(convertVoToEntity(vo));
	}

	public CommentEntityWithBLOBs convertVoToEntity(CommentPublishVo vo) {
		CommentEntityWithBLOBs e = new CommentEntityWithBLOBs();
		e.setBlogId(vo.getBlogId());
		e.setPublishTime(new Date());
		e.setContent(vo.getContent());
		e.setEmail(vo.getEmail());
		e.setId(commentDetailEntityMapper.getNextCommentId());
		e.setNickname(vo.getNickname());
		return e;
	}

}
