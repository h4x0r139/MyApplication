package cn.itcast.mobilesafe.domain;

import android.graphics.drawable.Drawable;

public class Prevention {
     private Drawable icon1 = null;
     private String PreventionItem = null;
     private Drawable icon2 = null;
     
	public Prevention() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Prevention(Drawable icon1, String preventionItem, Drawable icon2) {
		super();
		this.icon1 = icon1;
		PreventionItem = preventionItem;
		this.icon2 = icon2;
	}
	public Drawable getIcon1() {
		return icon1;
	}
	public void setIcon1(Drawable icon1) {
		this.icon1 = icon1;
	}
	public String getPreventionItem() {
		return PreventionItem;
	}
	public void setPreventionItem(String preventionItem) {
		PreventionItem = preventionItem;
	}
	public Drawable getIcon2() {
		return icon2;
	}
	public void setIcon2(Drawable icon2) {
		this.icon2 = icon2;
	}
     
}
