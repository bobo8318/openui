package org.hao.po;

public class ParameterPo {

	private int parameterid;
	public int getParameterid() {
		return parameterid;
	}

	public void setParameterid(int parameterid) {
		this.parameterid = parameterid;
	}

	private int PAGE_SIZE;
	private  String HEADER;
	private  String FOOTER;
	private  String introduce;
	private  String email;
	private  String weibo;
	private  String webhost;
	
	public String getWebhost() {
		return webhost;
	}

	public void setWebhost(String webhost) {
		this.webhost = webhost;
	}

	//0 nosql 1 sql
	public int DATA_SOURCE_TYPE;

	public int getDATA_SOURCE_TYPE() {
		return DATA_SOURCE_TYPE;
	}

	public void setDATA_SOURCE_TYPE(int dATASOURCETYPE) {
		DATA_SOURCE_TYPE = dATASOURCETYPE;
	}

	public  int getPAGE_SIZE() {
		return PAGE_SIZE;
	}

	public  void setPAGE_SIZE(int pAGESIZE) {
		PAGE_SIZE = pAGESIZE;
	}

	public  String getHEADER() {
		return HEADER;
	}

	public  void setHEADER(String hEADER) {
		HEADER = hEADER;
	}

	public  String getFOOTER() {
		return FOOTER;
	}

	public  void setFOOTER(String fOOTER) {
		FOOTER = fOOTER;
	}

	public  String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public  String getEmail() {
		return email;
	}

	public  void setEmail(String email) {
		this.email = email;
	}

	public  String getWeibo() {
		return weibo;
	}

	public  void setWeibo(String weibo) {
		this.weibo = weibo;
	}
	
	
}
