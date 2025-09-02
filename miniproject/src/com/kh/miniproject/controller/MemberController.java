package com.kh.miniproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.kh.miniproject.service.MemberService;
import com.kh.miniproject.view.MainFrame;
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
            JOptionPane.showMessageDialog(frame, "가입 완료!");
		} else {
			//실패화면
			JOptionPane.showMessageDialog(frame, "가입 실패!");
		}
	}
	
	//회원을 모두 조회
	public void selectMember(MainFrame frame, Member m) {
		List<Member> list = ms.selectMember();
		
		//조회된 결과에 따라서 사용자가 보게될 화면
		if(list.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "회원 정보가 존재하지 않습니다.");
		}else {
			for(Object o : list) {
				JOptionPane.showMessageDialog(frame, list);
			}
		}
	}
	
	//userId, email, phone, address, hobby를 전달받아
	//Member를 수정하는 메서드
	public void updateMember(MainFrame frame, Member m) {
		int result = ms.updateMember(m);
		
		if(result > 0) {
			JOptionPane.showMessageDialog(frame, "회원 정보가 수정하는데 성공하였습니다.");
		} else {
			JOptionPane.showMessageDialog(frame, "회정 정보를 수정하는데 실패하였습니다.");
		}
	}
	
	public void deleteMember(MainFrame frame, Member m) {
		
		int result = ms.deleteMember(m);
		if(result > 0) {
			JOptionPane.showMessageDialog(frame, "회원 정보가 수정하는데 성공하였습니다.");
		} else {
			JOptionPane.showMessageDialog(frame, "회정 정보를 수정하는데 실패하였습니다.");
		}
	}
	
	//회원이름으로 키워드 검색
	public Member loginMember(MainFrame frame, Member m) {
		Member member = ms.memberIdSearch(m);
		return member;
	}

}
