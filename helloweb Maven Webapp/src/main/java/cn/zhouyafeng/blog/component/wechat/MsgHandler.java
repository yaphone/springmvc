package cn.zhouyafeng.blog.component.wechat;

import com.alibaba.fastjson.JSONObject;

import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;

public class MsgHandler implements IMsgHandlerFace {

	@Override
	public String textMsgHandle(JSONObject msg) {
		String text = msg.getString("Text");
		System.out.println(text);
		return text;
	}

	@Override
	public String picMsgHandle(JSONObject msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String voiceMsgHandle(JSONObject msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String viedoMsgHandle(JSONObject msg) {
		// TODO Auto-generated method stub
		return null;
	}

}
