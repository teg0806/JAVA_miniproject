package com.kh.miniproject.controller;

import java.util.ArrayList;
import java.util.List;

import com.kh.miniproject.service.MemberService;
import com.kh.miniproject.vo.Member;

public class MemberController {
	private MemberService ms = new MemberService();
	
	public MemberController() {
		super();

	}

	/*
	 * 사용자의 추가요청을 처리하는 메서드
	 *  userId ~ hobby : 사용자가 입력한 정보를 매개변수로 받음
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, String userNickName, String email) {
		
		//view로부터 전달받은 값을 바로 dao에 전달x
		//vo에 잘 담아서 전달
		Member m = new Member(userId, userPwd, userName, gender, userNickName, email);
		
		int result = ms.insertMember(m);
		
		if(result > 0) {
			//성공화면

		} else {
			//실패화면

		}
	}
	
	//회원을 모두 조회
	public void selectMemberAll() {
		List<Member> list = ms.selectMemberList();
		
		//조회된 결과에 따라서 사용자가 보게될 화면
		if(list.isEmpty()) {

		}else {

		}
	}
	
	//userId, email, phone, address, hobby를 전달받아
	//Member를 수정하는 메서드
	public void updateMember(String userId, String email, String phone, String address, String hobby) {
		Member m = new Member();
		m.setUserId(userId);
		m.setEmail(email);

		
		int result = ms.updateMember(m);
		
		if(result > 0) {

		} else {

		}
	}
	
	public void deleteMember(String userId, String userPwd) {
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		int result = ms.deleteMember(m);
		if(result > 0) {

		} else {

		}
	}
	
	//회원이름으로 키워드 검색
	public void loginMember(Member m) {
		ArrayList<Member> list = ms.memberSearchByName(m);
		
		if(list.isEmpty()) {

		}else {

		}
	}
}
