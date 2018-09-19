package kr.co.koscom.web;

public class MemberDTO {
	private String email;
	private String name;
	private String password;
	private String phonenumber;
	private String address;
	private String regDate;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [email=" + email + ", name=" + name + ", password=" + password + ", phonenumber="
				+ phonenumber + ", address=" + address + ", regDate=" + regDate + "]";
	}
	
	
}
