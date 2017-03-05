package cn.zhouyafeng.blog.entity;

import java.io.Serializable;

public class BlogEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String blogTitle;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

}
