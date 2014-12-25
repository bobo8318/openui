package org.hao.test;

import org.hao.secretMessage.BaseMessage;
import org.hao.secretMessage.imply.ReplaceAlg;
import org.hao.secretMessage.imply.ReplaceRulPool;
import org.hao.secretMessage.imply.RotoMachine;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(BtnStyle.getRandomStyle());
		BaseMessage bm = new BaseMessage();//基础编码信息
		ReplaceAlg ra = new ReplaceAlg();//编码算法
		RotoMachine rm = new RotoMachine();//转子机策略
		rm.setRule(ReplaceRulPool.getRul("keyboard"));
		ra.setRotoMachine(rm);//设置替换策略
		bm.setEncodeAlg(ra);
		bm.setSource("gaoyunpengshigou");
		System.out.println(bm.encode());
	}

}
