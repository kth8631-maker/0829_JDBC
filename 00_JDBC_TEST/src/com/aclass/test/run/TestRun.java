package com.aclass.test.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.w3c.dom.ls.LSOutput;

public class TestRun {

	public static void main(String[] args) {
		// JDBC 맛보기
		/*
		 * 1. 디비버 실행(클라이언트 프로그램 실행)
		 * 
		 * 2. 접속하기 누름
		 * 
		 * 3. DBMS 선택(ORACLE)
		 * 
		 * 4. ojdbc.jar (API) --> 등록 / jar 파일은 압축 파일
		 * 
		 * 5. IP주소, PORT번호, 사용자계정, 비밀번호
		 * 
		 * 6. 새 SQL편집기
		 * 
		 * 7. INSERT문 작성 ==> INSERT INTO 테이블명 VALUES('값', '값', '값');
		 * 
		 * 8. SQL문을 실행
		 * 
		 * 9. UpdatedRows : 1
		 * 
		 * 10. COMMIT;
		 */
		// 0) 필요한 변수 세팅
		Connection conn = null;  // 1~4번 역할
		Statement stmt = null;   // 6~8번 역할
		int result = 0;
		
		// 사용자에게 값을 입력받아서 DBMS로 전달 => 테이블에 INSERT
		Scanner sc = new Scanner(System.in);
		
		System.out.print("번호를 입력해주세요 > ");
		int num = sc.nextInt();
		sc.nextLine();
		System.out.println("이름을 입력해주세요 >");
		String name = sc.nextLine();
		
		// 실행할 SQL문(완성된 형태로 만들어주기)
		String sql = "INSERT INTO TB_STUDENT CALUES(1, '홍길동', SYSDATE)";
		sql = "INSERT INTO TB_STUDENT VALUES(" + num + ", '" + name + "', SYSDATE)"; 
		
		try {
		// 1) JDBC Driver 등록 -> ORACLE -> ojdbc.jar
		// Driver등록은 프로그램 실행 시 딱 1회만 하면됨
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver등록 성공!");
			
			// 2) DB서버와의 연결(IP주소, PORT번호, 사용자이름, 비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", 
									 		   "KTH04", 
											   "KTH041234");
			System.out.println("DB서버 접속 성공!");
			
			conn.setAutoCommit(false);
			
			// 3) 새 SQL편집기 열기
			stmt = conn.createStatement();
			System.out.println("Statement 객체 생성!");
			
			// 4) SQL문 실행
			// UpdatedRows : N
			// UpdatedRows : N
			// UpdatedRows : N
			// DML(INSERT, UPDATE, DELETE) => 처리된 행의 개수
			// executeUpdate(DML문);
			result = stmt.executeUpdate(sql);
			System.out.println("SQL문 실행 성공!");
			
			// 5) 트랜잭션처리
			if(result > 0) { // INSERT에 성공했을 경우
				conn.commit();
			}
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace(); // 프로젝트에 ojdbc추가했나요?
			System.out.println("55행이 문제 -> 오타");
		} catch(SQLException e) { 
			e.printStackTrace();
			System.out.println("사용자이름/비밀번호가 잘못됐습니다 -> 60,61행");
			System.out.println("부적합한 Oracle URL이 지정되었습니다 -> 59행");
			System.out.println("SQLSyntaxErrorException -> SQL문 확인하기");
			System.out.println("NullPointerException -> JDBC객체들 확인하기");
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		

	}

}
