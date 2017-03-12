package cn.zhouyafeng.utils;

public class PageEntity {
	public String result = null;

	public PageEntity() {

	}

	public PageEntity(Object o) {
		try {
			this.result = JackSonUtils.obj2json(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
