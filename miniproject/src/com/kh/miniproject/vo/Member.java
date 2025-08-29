package com.kh.miniproject.vo;

public class Member {
	private String id;
	private String pw;
	private String name;
	private String gender;
	private String nickName;
	private String Email;
	
	public Member(String id, String pw, String name, String gender, String nickName, String email) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.nickName = nickName;
		Email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", pw=" + pw + ", name=" + name + ", gender=" + gender + ", nickName=" + nickName
				+ ", Email=" + Email + "]";
	}
	
}
