package com.kh.miniproject.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import com.kh.miniproject.common.SQLTemplate;
import com.kh.miniproject.dao.MemberDao;
import com.kh.miniproject.vo.Member;


public class MemberService {
	public MemberService() {
		super();
	}

	public int insertMember(Member m) {
		Connection conn = SQLTemplate.getConnection();
		
		int result = new MemberDao().insertMember(m, conn);
		
		if(result > 0) {
			SQLTemplate.commit(conn);
		} else {
			SQLTemplate.rollback(conn);
		}
		
		SQLTemplate.close(conn);
		return result;
	}
	
	public List<Member> selectMember(){
		Connection conn = SQLTemplate.getConnection();
		
		List<Member> list = new MemberDao().selectMember(conn);
		SQLTemplate.close(conn);
		
		return list;
	}
	
	public int updateMember(Member m) {
		Connection conn = SQLTemplate.getConnection();
		
		int result = new MemberDao().updateMember(m, conn);
		if(result > 0) {
			SQLTemplate.commit(conn);
		} else {
			SQLTemplate.rollback(conn);
		}
		SQLTemplate.close(conn);
		
		return result;
	}
	
	public int deleteMember(Member m) {
		Connection conn = SQLTemplate.getConnection();
		
		int result = new MemberDao().deleteMember(m, conn);
		
		if(result > 0) {
			SQLTemplate.commit(conn);
		} else {
			SQLTemplate.rollback(conn);
		}
		SQLTemplate.close(conn);
		
		return result;
	}
	
	public Member memberIdSearch(Member m){
		Connection conn = SQLTemplate.getConnection();
		Member member = new MemberDao().loginMember(m, conn);
		SQLTemplate.close(conn);
		return member;
	}
	
	//파일을 그대로 저장
	public void exportMemberListToFile(List<Member> list) throws IOException{
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
        
        try (PrintWriter writer = new PrintWriter("resources/MemberList.txt")){
        	writer.print(sb.toString()); // 그냥 문자열 그대로 파일에 작성
        }
	}
}
