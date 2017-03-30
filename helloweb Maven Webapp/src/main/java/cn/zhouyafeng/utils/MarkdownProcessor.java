package cn.zhouyafeng.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cn.zhouyafeng.blog.entity.BlogEntity;

public class MarkdownProcessor {
	private List<File> mdfileList = null;

	public MarkdownProcessor(List<File> mdfileList) {
		this.mdfileList = mdfileList;
	}

	public List<BlogEntity> getBlogList() { // 返回处理过的博文内容

		List<BlogEntity> blogList = new ArrayList<BlogEntity>();
		for (File mdfile : mdfileList) {
			List<String> contentStrList = null;
			StringBuffer blogBuffer = new StringBuffer();
			String blogStr = null;
			try {
				contentStrList = FileUtils.readLines(mdfile, "UTF-8");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (contentStrList != null) { // 读取到内容
				for (String line : contentStrList) {
					blogBuffer.append(line);
				}
				blogStr = blogBuffer.toString();
			}
			if (blogStr != null) { // 开始处理blog
				BlogEntity blogEntity = new BlogEntity();
				// 标题
				int titleStart = blogStr.indexOf("TITLE") + 6;
				int titleEnd = blogStr.indexOf("**");
				String blogTitle = blogStr.substring(titleStart, titleEnd);
				blogEntity.setBlogTitle(blogTitle);

				// 分类
				int classifyStart = blogStr.indexOf("CLASSIFY:") + 9;
				int classifyEnd = blogStr.indexOf("**", classifyStart);
				String blogClassify = blogStr.substring(classifyStart, classifyEnd);
				blogEntity.setBlogType(blogClassify);

				// 关键词
				int keywordStart = blogStr.indexOf("KEYWORDS:") + 9;
				int keywordEnd = blogStr.indexOf("**", keywordStart);
				String blogKeywords = blogStr.substring(keywordStart, keywordEnd);
				blogEntity.setBlogTag(blogKeywords);

				// 音乐链接
				int musicUrlStart = blogStr.indexOf("MUSIC:") + 6;
				int musicUrlEnd = blogStr.indexOf("**", musicUrlStart);
				String musicUrl = blogStr.substring(musicUrlStart, musicUrlEnd);
				blogEntity.setMusic(musicUrl);

				// 博文内容
				int contentStart = blogStr.indexOf("------") + 7;
				int contentEnd = blogStr.length();
				String blogContent = blogStr.substring(contentStart, contentEnd);
				blogEntity.setBlogContent(blogContent);

				blogList.add(blogEntity);
			}
		}

		return blogList;
	}

}
