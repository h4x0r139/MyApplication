package cn.itcast.mobilesafe.domain;

import android.graphics.drawable.Drawable;

public class Communication {
	private String item = null;
	private String item2 = null;
	private Drawable icon;
	
	public Communication() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Communication(String item, String item2, Drawable icon) {
		super();
		this.item = item;
		this.item2 = item2;
		this.icon = icon;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getItem2() {
		return item2;
	}
	public void setItem2(String item2) {
		this.item2 = item2;
	}
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
}
