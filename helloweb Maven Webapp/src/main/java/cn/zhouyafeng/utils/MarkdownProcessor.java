package cn.zhouyafeng.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class MarkdownProcessor {
	private List<File> mdfileList = null;

	public MarkdownProcessor(List<File> mdfileList) {
		this.mdfileList = mdfileList;
	}

	public List<Map<String, String>> getBlogList() { // 返回处理过的博文内容

		List<Map<String, String>> blogList = new ArrayList<Map<String, String>>();
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
				// blog title
				Map<String, String> blogMap = new HashMap<String, String>();
				int titleStart = blogStr.indexOf("TITLE") + 6;
				int titleEnd = blogStr.indexOf("**");
				String title = blogStr.substring(titleStart, titleEnd);
				blogMap.put("title", title);
				blogList.add(blogMap);
			}
		}

		return blogList;
	}

}
