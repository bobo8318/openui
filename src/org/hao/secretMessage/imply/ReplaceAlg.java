package org.hao.secretMessage.imply;

import org.hao.secretMessage.EncodeAlg;

public class ReplaceAlg implements EncodeAlg {

	private RotoMachine rotoMachine;
	
	public void setRotoMachine(RotoMachine rotoMachine){
		this.rotoMachine = rotoMachine;
	}
	@Override
	public String decode(String source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encode(String source) {
		// TODO Auto-generated method stub
		if(rotoMachine == null ){
			System.out.println("rotoMachine is null");
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<source.length();i++){
			sb.append(rotoMachine.replace(source.charAt(i)));
		}
		return sb.toString();
	}

}
