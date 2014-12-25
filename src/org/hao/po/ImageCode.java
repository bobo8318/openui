package org.hao.po;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class ImageCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -44939498968128168L;
	private String strcode;
	private BufferedImage img;
	
	public String getStrcode() {
		return strcode;
	}
	public void setStrcode(String strcode) {
		this.strcode = strcode;
	}
	public BufferedImage getImg() {
		return img;
	}
	public void setImg(BufferedImage img) {
		this.img = img;
	}
	
	
}
