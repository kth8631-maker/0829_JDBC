package com.kh.statement.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.statement.model.dto.PasswordDTO;
import com.kh.statement.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	// 메소드 호출할 때마다
	// xml파일로부터 Properties객체로 입력받는 코드를 써야함 중복이다
	// new MemberDao().XXX
	
	public MemberDao() {
		
		try {
			prop.loadFromXML(new FileInputStream("resources/member-mapper.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int save(Connection conn, Member member) { // <- 왜 멤버 ? 데이터 가공해서
		
		// 0) 필요한 변수 세팅~~~
		PreparedStatement pstmt = null;
		int result = 0;
		
		// Properties prop = new Properties();
		try {
			prop.loadFromXML(new FileInputStream("resources/member-mapper.xml"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		String sql = prop.getProperty("save");


		
		// 1) Driver 등록
		// 2) Connection
		
		try {
			// 3_1) PreparedStatement객체 생성(SQL문 미리보내기)
			pstmt = conn.prepareStatement(sql); // sql 반드시 인자로 포함
			
			// 3_2) 미완성된 SQL문일 경우 묶어줄 값 전달하기
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getEmail());
			
			// 4, 5) DB에 완성된 SQL문을 실행한 결과(int) 받기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7_1) 할 일이 다 끝난 PreparedStatement객체만 반납
			JDBCTemplate.close(pstmt);
		}
		// 8) 결과반환
		return result; // 서비스로 반환
	}
	
	public List<Member> findAll(Connection conn){
		
		// 0) 필요한 변수 선언 먼저!
		// PreparedStatement, ResultSet, sql, List
		List<Member> members = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		//Properties prop = new Properties();
		
		// prop.loadFromXML(new FileInputStream());
		
		String sql = prop.getProperty("findAll");

		
		try {
			// 3_1) PreparedStatement 객체 생성(sql문을 인자로 전달하기)
			pstmt = conn.prepareStatement(sql);
			
			// 4, 5) SQL(SELECT)을 실행 후 결과(ResultSet)받기
			rset = pstmt.executeQuery();
			
			// 6) 조회결과 여부 판단 후 => rset.next()
			//    컬럼값을 객체 필드에 매핑
			while(rset.next()) {
				Member member = new Member(rset.getInt("USERNO")
										  ,rset.getString("USERID")
										  ,rset.getString("USERPWD")
										  ,rset.getString("USERNAME")
										  ,rset.getString("EMAIL")
										  ,rset.getDate("ENROLLDATE"));
				members.add(member); // 값이 안날아가기위해 누군가(members) 주소를 갖고 있어야됨
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7) 사용이 다 끝난 JDBC용 객체 반납
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		// 8) 결과반환
		return members; // 서비스로 반환
	}
	
	public Member findById(Connection conn, String userId) {
		
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("findById");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = new Member(rset.getInt("USERNO")
								   ,rset.getString("USERID")
								   ,rset.getString("USERPWD")
								   ,rset.getString("USERNAME")
								   ,rset.getString("EMAIL")
								   ,rset.getDate("ENROLLDATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return member;
	}
	
	public List<Member> findByKeyword(Connection conn, String keyword) {
		
		List<Member> members = new ArrayList(); // 반환할 Member객체들 담을 List
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("findByKeyword");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				members.add(new Member(rset.getInt("USERNO")
									  ,rset.getString("USERID")
									  ,rset.getString("USERPWD")
									  ,rset.getString("USERNAME")
									  ,rset.getString("EMAIL")
									  ,rset.getDate("ENROLLDATE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(conn);
		}
		return members;
	}
	
	public int update(Connection conn, PasswordDTO pd) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("update");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pd.getNewPassword());
			pstmt.setString(2,  pd.getUserId());
			pstmt.setString(3, pd.getUserPwd());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int delete(Connection conn, Member member) {
		// 0)
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("delete");
		// 1~2) 앞에서 다해왔음
		
		try {
			// 3_1)
			pstmt = conn.prepareStatement(sql);
			// 3_2)
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			// 4, 5)
			result = pstmt.executeUpdate();
			// 6) Sercive로 돌아가서 진행
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7)
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
}
