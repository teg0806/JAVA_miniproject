package com.kh.miniproject.service;

import java.sql.Connection;
import java.util.List;

import com.kh.miniproject.common.SQLTamp;
import com.kh.miniproject.dao.MemberDao;
import com.kh.miniproject.vo.Member;


public class MemberService {
	public MemberService() {
		super();
	}

	public int insertMember(Member m) {
		Connection conn = SQLTamp.getConnection();
		
		int result = new MemberDao().insertMember(m, conn);
		
		if(result > 0) {
			SQLTamp.commit(conn);
		} else {
			SQLTamp.rollback(conn);
		}
		
		SQLTamp.close(conn);
		return result;
	}
	
	public List<Member> selectMember(){
		Connection conn = SQLTamp.getConnection();
		
		List<Member> list = new MemberDao().selectMember(conn);
		SQLTamp.close(conn);
		
		return list;
	}
	
	public int updateMember(Member m) {
		Connection conn = SQLTamp.getConnection();
		
		int result = new MemberDao().updateMember(m, conn);
		if(result > 0) {
			SQLTamp.commit(conn);
		} else {
			SQLTamp.rollback(conn);
		}
		SQLTamp.close(conn);
		
		return result;
	}
	
	public int deleteMember(Member m) {
		Connection conn = SQLTamp.getConnection();
		
		int result = new MemberDao().deleteMember(m, conn);
		
		if(result > 0) {
			SQLTamp.commit(conn);
		} else {
			SQLTamp.rollback(conn);
		}
		SQLTamp.close(conn);
		
		return result;
	}
	
	public Member memberIdSearch(Member m){
		Connection conn = SQLTamp.getConnection();
		Member member = new MemberDao().loginMember(m, conn);
		SQLTamp.close(conn);
		return member;
	}
}
