package com.kh.miniproject.vo;

public class Member {
	private Integer userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String gender;
	private String userNickName;
	private String email;

	//디폴트 생성자
	public Member() {
		super();
	}
	//로그인을 위한 생성자
	public Member(String userId, String userPwd) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
	}
	
	//수정을 위한 생성자
	public Member(String userId, String userNickName, String email) {
		super();
		this.userId = userId;
		this.userNickName = userNickName;
		this.email = email;
	}
	
	//모든 매개변수 생성자
	public Member(Integer userNo, String userId, String userPwd, String userName, String gender, String userNickName,
			String email) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.userNickName = userNickName;
		this.email = email;
	}

	//no제외한 매개변수 생성자
	public Member(String userId, String userPwd, String userName, String gender, String userNickName, String email) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.userNickName = userNickName;
		this.email = email;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "[userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", gender=" + gender + ", userNickName=" + userNickName + ", email=" + email + "]";
	}
	
}
