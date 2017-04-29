package cn.zhouyafeng.utils;

import com.alibaba.fastjson.JSON;

public class PageEntity {
	public String result = null;

	public PageEntity() {

	}

	public PageEntity(Object o) {
		try {
			this.result = JSON.toJSONString(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
