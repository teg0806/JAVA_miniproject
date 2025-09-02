package com.kh.miniproject.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.miniproject.dao.MemberDao;
import com.kh.miniproject.vo.Member;
import com.kh.miniproject.tamplate.Tamplate;


public class MemberService {
	public MemberService() {
		super();
	}

	public int insertMember(Member m) {
		Connection conn = Tamplate.getConnection();
		
		int result = new MemberDao().insertMember(m, conn);
		
		if(result > 0) {
			Tamplate.commit(conn);
		} else {
			Tamplate.rollback(conn);
		}
		
		Tamplate.close(conn);
		return result;
	}
	
	public List<Member> selectMember(){
		Connection conn = Tamplate.getConnection();
		
		List<Member> list = new MemberDao().selectMember(conn);
		Tamplate.close(conn);
		
		return list;
	}
	
	public int updateMember(Member m) {
		Connection conn = Tamplate.getConnection();
		
		int result = new MemberDao().updateMember(m, conn);
		if(result > 0) {
			Tamplate.commit(conn);
		} else {
			Tamplate.rollback(conn);
		}
		Tamplate.close(conn);
		
		return result;
	}
	
	public int deleteMember(Member m) {
		Connection conn = Tamplate.getConnection();
		
		int result = new MemberDao().deleteMember(m, conn);
		
		if(result > 0) {
			Tamplate.commit(conn);
		} else {
			Tamplate.rollback(conn);
		}
		Tamplate.close(conn);
		
		return result;
	}
	
	public Member memberIdSearch(Member m){
		Connection conn = Tamplate.getConnection();
		Member member = new MemberDao().loginMember(m, conn);
		Tamplate.close(conn);
		return member;
	}
}
