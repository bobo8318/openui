package org.hao.secretMessage;

public interface EncodeAlg {

	public String encode(String source);
	public String decode(String source);
}
