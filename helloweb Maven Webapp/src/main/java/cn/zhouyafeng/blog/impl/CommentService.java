package cn.zhouyafeng.blog.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zhouyafeng.blog.dao.CommentEntityMapper;
import cn.zhouyafeng.blog.entity.CommentEntityWithBLOBs;
import cn.zhouyafeng.blog.entity.CommentPublishVo;
import cn.zhouyafeng.blog.service.ICommentService;

@Service("commentService")
public class CommentService implements ICommentService {

	@Resource
	public CommentEntityMapper commentEntityMapper;

	@Override
	public int insertCommentById(CommentPublishVo vo) {
		return commentEntityMapper.insert(convertVoToEntity(vo));
	}

	public CommentEntityWithBLOBs convertVoToEntity(CommentPublishVo vo) {
		CommentEntityWithBLOBs e = new CommentEntityWithBLOBs();
		e.setBlogId(vo.getBlogId());
		e.setCommentTime(new Date());
		e.setContent(vo.getContent());
		e.setEmail(vo.getEmail());
		e.setId(1);
		e.setNickname(vo.getNickname());
		return e;
	}

}
