package kr.co.koscom.dto;

public class MemberDTO {
	private String id;
	private String name;
	private String password;
	private String email;
	private String redDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRedDate() {
		return redDate;
	}
	public void setRedDate(String redDate) {
		this.redDate = redDate;
	}
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", redDate="
				+ redDate + "]";
	}
	
	
}
