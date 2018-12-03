package mars.model;

import java.io.Serializable;

public class Mp3Info implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String mp3Name;
	private String mp3Size;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Mp3Info() {
		super();
	}

	@Override
	public String toString() {
		return "Mp3Info [id=" + id + ", lrcName=" + lrcName + ", lrcSize="
				+ lrcSize + ", mp3Name=" + mp3Name + ", mp3Size=" + mp3Size
				+ "]";
	}

	public String getMp3Name() {
		return mp3Name;
	}

	public Mp3Info(String id, String mp3Name, String mp3Size, String lrcName,
			String lrcSize) {
		super();
		this.id = id;
		this.mp3Name = mp3Name;
		this.mp3Size = mp3Size;
		this.lrcName = lrcName;
		this.lrcSize = lrcSize;
	}

	public void setMp3Name(String mp3Name) {
		this.mp3Name = mp3Name;
	}

	public String getMp3Size() {
		return mp3Size;
	}

	public void setMp3Size(String mp3Size) {
		this.mp3Size = mp3Size;
	}

	public String getLrcName() {
		return lrcName;
	}

	public void setLrcName(String lrcName) {
		this.lrcName = lrcName;
	}

	public String getLrcSize() {
		return lrcSize;
	}

	public void setLrcSize(String lrcSize) {
		this.lrcSize = lrcSize;
	}

	private String lrcName;
	private String lrcSize;

}
