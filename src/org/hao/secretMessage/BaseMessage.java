package org.hao.secretMessage;

public class BaseMessage implements secretMessage {

	protected EncodeAlg encodeAlg;
	protected String source;
	
	public void setEncodeAlg(EncodeAlg encodeAlg){
		this.encodeAlg = encodeAlg;
	}
	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String decode() {
		// TODO Auto-generated method stub
		this.source = encodeAlg.decode(source);
		return this.source;
	}

	@Override
	public String encode() {
		// TODO Auto-generated method stub
		this.source = encodeAlg.encode(source);
		return this.source;
	}

}
