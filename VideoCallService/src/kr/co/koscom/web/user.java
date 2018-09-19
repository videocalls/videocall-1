package kr.co.koscom.web;

public class user {
	private String UserId;
	private String Name;
	private String Password;

	public user(String userId, String name, String password) {
		super();
		UserId = userId;
		Name = name;
		Password = password;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		this.UserId = userId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

}
