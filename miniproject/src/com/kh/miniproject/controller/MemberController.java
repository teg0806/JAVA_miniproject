package com.kh.miniproject.controller;

import java.io.IOException;
import java.util.List;


import com.kh.miniproject.service.MemberService;
import com.kh.miniproject.view.server.DeleteMenu;
import com.kh.miniproject.view.server.JoinMenu;
import com.kh.miniproject.view.server.LoginMenu;
import com.kh.miniproject.view.server.MainFrame;
import com.kh.miniproject.view.server.UpdateMenu;
import com.kh.miniproject.vo.Member;

public class MemberController {
	private MemberService ms = new MemberService();
	
	public MemberController() {
		super();

	}

	public void insertMember(MainFrame frame, Member m) {
		
		//view로부터 전달받은 값을 바로 dao에 전달x
		//vo에 잘 담아서 전달
		Member member = new Member(m.getUserId(), m.getUserPwd(), m.getUserName(), m.getGender(), m.getUserNickName(), m.getEmail());

		int result = ms.insertMember(member);
		
		if(result > 0) {
			//성공화면
            JoinMenu.joinSuccess(frame);
		} else {
			//실패화면
			JoinMenu.joinFail(frame);
		}
	}
	
	//Dao에서 불러온 값들을 저장하는 리스트 메서드
	public List<Member> getMemberList() {
		return ms.selectMember();
	}
	
	//파일 생성은 서비스가 처리하며 파일 처리 성공여부를 반환
	public boolean saveMemberListToFile(List<Member> list) {
	    try {
	        ms.exportMemberListToFile(list); // 파일 저장 로직은 서비스에 위임
	        return true; // 성공
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false; // 실패
	    }
	}
	
	//userId, email, phone, address, hobby를 전달받아
	//Member를 수정하는 메서드
	public void updateMember(MainFrame frame, Member m) {
		int result = ms.updateMember(m);
		
		if(result > 0) {
			UpdateMenu.updateSuccess(frame, m);
		} else {
			UpdateMenu.updateFail(frame);
		}
	}
	
	public void deleteMember(MainFrame frame, String deleteId) {
		Member m = new Member();
		m.setUserId(deleteId);
		int result = ms.deleteMember(m);
		if(result > 0) {
			DeleteMenu.deleteSuccess(frame);
		} else {
			DeleteMenu.deleteFail(frame);
		}
	}
	
	//회원이름으로 키워드 검색
	public void loginMember(MainFrame frame, Member m) {
		Member member = ms.memberIdSearch(m);
    	// [수정] 반환된 Member 객체가 null이 아닌지 확인
    	if(member != null) { // 로그인 성공
    		LoginMenu.loginSuccess(frame, member);
    	} else { // 로그인 실패
    		LoginMenu.loginFail(frame);
    	}
	}

}
