package org.hao.common;

public enum BtnStyle {

	
	/*private final String primary = "primary";
	private final String info = "info";
	private final String success = "success";
	private final String warning = "warning";
	private final String danger = "danger";
	private final String inverse = "inverse";*/
	primary("primary",1),info("info",2),success("success",3),
	warning("warning",4),danger("danger",5),inverse("inverse",6);
	private String name;
	private int index;
	private BtnStyle(String name, int index){
		this.name = name;
		this.index = index;
	}
	public  static BtnStyle getStyleByIndex(int index){
		for(BtnStyle bs:BtnStyle.values()){
			if(bs.getIndex() == index)
				return bs;
		}
		return null;
	}
	public static String getRandomStyle(){
		int styleIndex = (int)(Math.random()*6+1);
		if( getStyleByIndex(styleIndex) !=null)
			return getStyleByIndex(styleIndex).getName();
		else 
			return "primary";
	}
	public int getIndex(){
		return index;
	}
	public String getName(){
		return name;
	}
}
