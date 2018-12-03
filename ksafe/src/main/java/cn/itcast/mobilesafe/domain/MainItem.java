package cn.itcast.mobilesafe.domain;

import android.graphics.drawable.Drawable;

public class MainItem {
	private String item = null;
	private String item2 = null;
	private Drawable icon;

	public MainItem() {

	}

	public MainItem(String item, String item2, Drawable icon) {

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
