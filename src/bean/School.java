package bean;

public class School implements java.io.Serializable {
	//属性
	private String cd;
	private String name;
	//セッター
	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}
	//ゲッター
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
