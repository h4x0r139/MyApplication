package cn.itcast.mobilesafe.domain;

/**
 * type 1 .������ǽ��� 2.������
 * 
 * @author zehua
 * 
 */
public class SmsInfo {
	private String id;
	private String address;
	private String date;
	private int type; // 1 .������ǽ��� 2.������

	private String body;

	public SmsInfo() {
	}

	public SmsInfo(String id, String address, String date, int type, String body) {
		this.id = id;
		this.address = address;
		this.date = date;
		this.type = type;
		this.body = body;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
