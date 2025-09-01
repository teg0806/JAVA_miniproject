package com.kh.miniproject.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.kh.miniproject.vo.Member;
import com.kh.miniproject.tamplate.Tamplate;


public class MemberDao {
	private Properties prop = new Properties();
	
	public MemberDao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertMember(Member m, Connection conn) { //성공
		//insertMember -> insert -> 처리된 행 수(int)
		
		//필요한 변수 세팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		//실행할 sql(sql뒤에 ;없어야함!!!)
		String sql = prop.getProperty("insertMember");
//		Member m = new Member(userId, userPwd, userName, gender, userNickName, email);
		try {		
			//아직 미완성 sql문으로 ?의 값을 전부 채워야함
			pstmt = conn.prepareStatement(sql);
			//user_no는 sequance로 생성
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setString(5, m.getUserNickName());
			pstmt.setString(6, m.getEmail());

			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Tamplate.close(pstmt);
		}
		
		return result;
	}
	
	//회원목록을 반환하는 메서드
	public ArrayList<Member> selectMemberList(Connection conn){
		//select문(여러개) -> ResultSet -> ArrayList담기
		
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<>(); //비어있는 상태
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectMemberList");
		
		try {
			//완성된 sql
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
				m.setUserNo(rset.getInt("USER_NO"));
				m.setUserId(rset.getString("USER_ID"));
				m.setUserPwd(rset.getString("USER_PWD"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setGender(rset.getString("GENDER"));
				m.setEmail(rset.getString("EMAIL"));
				
				list.add(m);
			}
			
			//반복문이 끝난시점
			// list -> 비어있거나/ 데이터가 들어있거나
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Tamplate.close(rset);
			Tamplate.close(pstmt);
		}
		
		return list;
	}
	
	//Member객체 m을 통해서 update sql을 전달하는 메서드
	public int updateMember(Member m, Connection conn) {
		//update문 -> 처리된 행 수 : int -> 트랜잭션처리
		
		//필요한 변수 세팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		//실행할 sql(sql뒤에 ;없어야함!!!)
		String sql = prop.getProperty("updateMember");
		
		try {	
			//아직 미완성 sql문으로 ?의 값을 전부 채워야함
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, m.getUserNickName());
			pstmt.setString(2, m.getEmail());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Tamplate.close(pstmt);
		}
		
		return result;
	}
	
	//Member객체 m을 통해서 delete sql을 전달하는 메서드
	public int deleteMember(Member m, Connection conn) {
		//delete문 -> 처리된 행 수 : int -> 트랜잭션처리
		
		//필요한 변수 세팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		//실행할 sql(sql뒤에 ;없어야함!!!)
		String sql = prop.getProperty("deleteMember");
		
		try {	
			//아직 미완성 sql문으로 ?의 값을 전부 채워야함
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Tamplate.close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<Member> memberIdSearch(Member m, Connection conn){
		//select -> ResultSet -> ArrayList

		ArrayList<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("memberIdSearch");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				m = new Member();

				m.setUserId(rset.getString("USER_ID"));
				m.setUserPwd(rset.getString("USER_PW"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Tamplate.close(rset);
			Tamplate.close(pstmt);
		}
		
		return list;
	}
}
