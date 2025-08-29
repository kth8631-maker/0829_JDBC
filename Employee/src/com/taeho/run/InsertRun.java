package com.taeho.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertRun {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("사원번호를 입력해주세요 >");
		int num = sc.nextInt();
		sc.nextLine();
		System.out.println("이름을 입력해주세요 >");
		String name = sc.nextLine();
		System.out.println("주민등록번호를 입력해주세요 >");
		String number = sc.nextLine();
		System.out.println("이메일 주소를 입력해주세요 >");
		String email = sc.nextLine();
		System.out.println("휴대폰 번호를 입력해주세요 >");
		String phone = sc.nextLine();
		System.out.println("부서코드를 입력해주세요 >");
		String deptCode = sc.nextLine();
		System.out.println("직급코드를 입력해주세요 >");
		String jobCode = sc.nextLine();
		System.out.println("급여등급을 입력해주세요 >");
		String salLevel = sc.nextLine();
		System.out.println("급여를 입력해주세요 >");
		int salary = sc.nextInt();
		sc.nextLine();
		System.out.println("보너스를 입력해주세요 >");
		double bonus = sc.nextDouble();
		System.out.println("관리자 사번을 입력해주세요 >");
		int manager = sc.nextInt();
		sc.nextLine();
		
		String sql = "INSERT "
				     + "INTO "
					       + "EMPLOYEE "
				    +"VALUES "
					       + "("
					              + num
					       + ",'" + name + "'"
					       + ",'" + number + "'"
					       + ",'" + email + "'"
					       + ",'" + phone + "'"
					       + ",'" + deptCode + "'"
					       + ",'" + jobCode + "'"
					       + ",'" + salLevel + "'"
					       + ",'" + salary + "'"
					       + ",'" + bonus + "'"
					       + ",'" + manager + "'"
					       + ",'" + "SYSDATE')";
		System.out.println(sql);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver등록 성공!");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE",
											   "KTH04",
											   "KTH041234");
			System.out.println("DB서버 접속 성공!");
			
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			System.out.println("Statement 객체 생성!");
			
			result = stmt.executeUpdate(sql);
			System.out.println("SQL문 실행 성공!");
			
			if(result > 0) {
				conn.commit();
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
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
