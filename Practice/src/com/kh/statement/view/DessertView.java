package com.kh.statement.view;

import java.util.Scanner;

import com.kh.statement.controller.DessertController;

public class DessertView {

	private Scanner sc = new Scanner(System.in);
	private DessertController dc = new DessertController();
	
	public void mainMenu() {
		
		while(true) {
			System.out.println(" ---- 디저트 관리 프로그램 ---- ");
			System.out.println(" 1. 디저트 추가");
			System.out.println(" 2. 디저트 전체 조회");
			System.out.println(" 3. 디저트 카테고리 조회");
			System.out.println(" 4. 디저트 이름 키워드로 조회");
			System.out.println("메뉴를 선택해주세요 > ");
			int menuNo = sc.nextInt();
			sc.nextLine();
			
			switch(menuNo) {
			case 1 : save(); break; 
			case 2 : break;
			case 3 : break;
			case 4 : break;
			case 9 : System.out.println("프로그램을 종료합니다."); return;
			default : System.out.println("잘못된 메뉴 선택입니다.");
			}
		}
	}
	
	private void save() {
		
		System.out.println("--- 디저트 추가 ---");
		
		System.out.println("디저트 이름을 입력해주세요 > ");
		
		
	}
	
	
	
	
}
