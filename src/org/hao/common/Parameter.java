package org.hao.common;

public class Parameter {
	
	public static int PAGE_SIZE = 10;
	
	public static enum DATA_SOURCE{LOCAL,SAE};
	public static enum NEWS_TYPE{
		ARTICLE(1),VIDEO(2),IMAGE(3);
		private int index;
		private NEWS_TYPE(int code){
			this.index = code;
		}
		public int getInt(){
			return index;
		}
	};
	
	public static String IDCode = "fsxtchwh123";
	
	public static String HEADER = "<li class='active'><a href='http://www.openui.cn/1/opennui/hao/blog/index/1'>Home</a></li><li><a href='#about' data-toggle='modal'>About</a></li><li><a href='#contact' data-toggle='modal'>Contact</a></li><li><a href='../showLogin'>Login</a></li>"; //网站通用头部
	public static String FOOTER = " <p>&copy; 2014 郝文浩</p> "; //网站通用底部
	public static String introduce = "非著名IT人士";
	public static String email = "<a  href='mailto:openui@openui.cn'>openui@openui.cn</a>";
	public static String weibo = "<a href='http://weibo.com/u/1721259372'>OpenUI_cn</a>";
	public static String webhost;
	
	public static int article_stat;
	public static int msg_stat;
	public static int visit_stat_today = 0;
	public static String visit_date;
	public static int visit_stat_total = 0;
	
	public static String WORDSBAN = "cao";
	
	public static String VERSION = "2014-10-29 V1.0.3";
	
	public static String OUT_TIME_PAGE = "OSPF";
	
	//0 nosql 1 sql
	public static int DATA_SOURCE_TYPE = 1;

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
		Parameter.introduce = introduce;
	}

	public  String getEmail() {
		return email;
	}

	public  void setEmail(String email) {
		Parameter.email = email;
	}

	public  String getWeibo() {
		return weibo;
	}

	public  void setWeibo(String weibo) {
		Parameter.weibo = weibo;
	}

	public  String getWebhost() {
		return webhost;
	}

	public  void setWebhost(String webhost) {
		Parameter.webhost = webhost;
	}

	public  int getDATA_SOURCE_TYPE() {
		return DATA_SOURCE_TYPE;
	}

	public  void setDATA_SOURCE_TYPE(int dATASOURCETYPE) {
		DATA_SOURCE_TYPE = dATASOURCETYPE;
	}
	
	
}
