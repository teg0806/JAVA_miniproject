package com.kh.miniproject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.kh.miniproject.service.MemberService;
import com.kh.miniproject.view.server.MainFrame;
import com.kh.miniproject.view.server.ServerMenu;
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
	public void selectMember(MainFrame frame, Member m, JTextArea resultArea) {
		List<Member> list = ms.selectMember();
		
		//조회된 결과에 따라서 사용자가 보게될 화면
		// 받아온 데이터를 JTextArea에 예쁘게 뿌려준다!
        if (list.isEmpty()) {
            resultArea.setText("조회된 회원이 없습니다.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("===== 전체 회원 목록 =====\n\n");
            for (Member member : list) {
                sb.append("회원번호: ").append(member.getUserNo()).append("\n");
                sb.append("아이디: ").append(member.getUserId()).append("\n");
                sb.append("비밀번호: ").append(member.getUserPwd()).append("\n");
                sb.append("이름: ").append(member.getUserName()).append("\n");
                sb.append("성별: ").append(member.getGender()).append("\n");
                sb.append("닉네임: ").append(member.getUserNickName()).append("\n");
                sb.append("이메일: ").append(member.getEmail()).append("\n");
                sb.append("---------------------------------\n");
                
            }
            
            resultArea.setText(sb.toString());
            resultArea.setCaretPosition(0); // 스크롤 맨 위로
            
            //PrintWriter를 사용
            try (PrintWriter writer = new PrintWriter("resources/MemberList.txt")) {
                writer.print(sb.toString()); // 그냥 문자열 그대로 파일에 쓴다.
                JOptionPane.showMessageDialog(frame, "MemberList.txt 파일에 저장이 완료되었습니다.");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "파일 저장 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
	
	//userId, email, phone, address, hobby를 전달받아
	//Member를 수정하는 메서드
	public void updateMember(MainFrame frame, Member m) {
		int result = ms.updateMember(m);
		
		if(result > 0) {
			JOptionPane.showMessageDialog(frame, "회원 정보가 수정하는데 성공하였습니다.");
			//화면 전환
            frame.changePanel(new ServerMenu(frame, m));
		} else {
			JOptionPane.showMessageDialog(frame, "회정 정보를 수정하는데 실패하였습니다.");
		}
	}
	
	public void deleteMember(MainFrame frame, String deleteId) {
		Member m = new Member();
		m.setUserId(deleteId);
		int result = ms.deleteMember(m);
		if(result > 0) {
			JOptionPane.showMessageDialog(frame, "회원 정보가 삭제하는데 성공하였습니다.");
		} else {
			JOptionPane.showMessageDialog(frame, "회정 정보를 삭제하는데 실패하였습니다.");
		}
	}
	
	//회원이름으로 키워드 검색
	public void loginMember(MainFrame frame, Member m) {
		Member member = ms.memberIdSearch(m);
    	// [수정] 반환된 Member 객체가 null이 아닌지 확인
    	if(member != null) { // 로그인 성공
    		JOptionPane.showMessageDialog(frame, member.getUserNickName() + "님, 환영합니다.");
    		// 모든 정보가 담긴 loginUser 객체를 다음 화면으로 전달
    		frame.changePanel(new ServerMenu(frame, member));
    	} else { // 로그인 실패
    		JOptionPane.showMessageDialog(frame, "아이디 또는 비밀번호가 일치하지 않습니다.");
    	}
	}

}
