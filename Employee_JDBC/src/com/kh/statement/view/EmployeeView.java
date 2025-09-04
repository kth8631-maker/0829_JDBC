package com.kh.statement.view;

import java.util.List;
import java.util.Scanner;

import com.kh.statement.controller.EmployeeController;
import com.kh.statement.model.vo.Employee;

/*
1. 회원 전체 조회(사번, 사원명, 급여, 부서명, 직급명)
2. 부서명을 입력받아 부서가 동일한 사원 조회(총무부 입력시 총무부인 사원들만 조회되도록)
3. 직급명을 입력받아 직급이 동일한 사원 조회(과장 입력시 과장인 사원들만 조회되도록)
4. 사원 상세 조회(사번을 입력받아서 모든 컬럼 값 조회)
5. 급여가 높은 상위 다섯명 조회
6. 급여가 낮은 하위 다섯명 조회
7. 사원 추가 기능
8. 사원 정보 수정(사번을 입력받아 급여, 직급, 부서 수정)
9. 사원 퇴사 기능(사번을 입력받아 퇴사 여부, 퇴사일 수정)
모든 SQL문은 xml파일에서 입력받아 사용
DBMS접속정보는 properties파일에서 입력받아 사용
 */

public class EmployeeView {
	
	private Scanner sc = new Scanner(System.in);
	private EmployeeController ec = new EmployeeController();
	
	public void mainMenu() {
		
		while(true) {
			System.out.println(" ---- 회원 조회 프로그램 ---- ");
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 부서명으로 사원 조회");
			System.out.println("3. 직급명으로 사원 조회");
			System.out.println("4. 사원 상세 조회");
			System.out.println("5. 급여 상위 5명 조회");
			System.out.println("6. 급여 하위 5명 조회");
			System.out.println("7. 사원 추가하기");
			System.out.println("8. 사원 정보 수정하기");
			System.out.println("9. 사원 정보 삭제하기(퇴사)");
			System.out.println("메뉴를 선택해주세요 > ");
			int menuNo = sc.nextInt();
			sc.nextLine();
			
			switch(menuNo) {
			case 1 : findAll(); break;
			case 2 : findByDept(); break;
			case 3 : break;
			case 4 : break;
			case 5 : break;
			case 6 : break;
			case 7 : break;
			case 8 : break;
			case 9 : break;
			case 0 : System.out.println("프로그램을 종료합니다."); return;
			default : System.out.println("잘못된 메뉴 선택입니다.");
			}
		}
	}
	
	private void findAll() {
		
		System.out.println("\n회원 전체 조회");
		
		List<Employee> employees = ec.findAll();
		
		System.out.println("\n조회된 총 회원수는 " + employees.size() + "명 입니다.");
		if(employees.isEmpty()) {
			System.out.println("조회 결과가 존재하지 않습니다.");
		} else {
			// 사번, 사원명, 급여, 부서명, 직급명
			for(Employee employee : employees) {
				System.out.println("=======================-------===");
				System.out.print(employee.getEmp_Id() + "번 회원의 정보");
				System.out.print("사번 : " + employee.getEmp_Id() + ", ");
				System.out.print("사원명 : " + employee.getEmp_Name() + ", ");
				System.out.print("급여 : " + employee.getSalary() + ", ");
				System.out.print("부서명 : " + employee.getDept_Title() + ", ");
				System.out.print("직급명 : " + employee.getJob_Name());
				System.out.println();
			}
		}
	}
	
	private void findByDept() {
		
		System.out.println("\n부서명으로 사원 조회");
		System.out.println("부서명을 입력해주세요 > ");
		String dept_Title = sc.nextLine();
		
		Employee employee = ec.findByDept(dept_Title);
		
		if(employee != null) {
			System.out.println(dept_Title + "의 검색 결과입니다.");
			System.out.println("============================");
			System.out.print("사원명 : " + employee.getEmp_Name() + ", ");
			System.out.print("부서명 : " + employee.getDept_Title());
		} else {
			System.out.println("부서명이 존재하지 않습니다.");
		}
	}
	
	

}
