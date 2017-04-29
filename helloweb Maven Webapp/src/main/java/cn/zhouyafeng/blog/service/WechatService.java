package cn.zhouyafeng.blog.service;

import org.springframework.stereotype.Service;

import cn.zhouyafeng.blog.component.wechat.MsgHandler;
import cn.zhouyafeng.blog.face.IWechatServiceFace;
import cn.zhouyafeng.itchat4j.Wechat;

@Service("wechatService")
public class WechatService implements IWechatServiceFace {

	@Override
	public void login() {
		String qrPath = "E://itchat4j";
		MsgHandler msgHandler = new MsgHandler();
		Wechat wechat = new Wechat(msgHandler, qrPath);
		wechat.start();
	}
}
