package com.kh.statement.view;

import java.util.List;
import java.util.Scanner;

import com.kh.statement.controller.DessertController;
import com.kh.statement.model.vo.Dessert;

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
			System.out.println(" 5. 디저트 정보 변경");
			System.out.println(" 6. 디저트 삭제");
			System.out.println("메뉴를 선택해주세요 > ");
			int menuNo = sc.nextInt();
			sc.nextLine();
			
			switch(menuNo) {
			case 1 : save(); break; 
			case 2 : findAll(); break;
			case 3 : findById(); break;
			case 4 : break;
			case 5 : break;
			case 6 : break;
			case 9 : System.out.println("프로그램을 종료합니다."); return;
			default : System.out.println("잘못된 메뉴 선택입니다.");
			}
		}
	}
	
	private void save() {
		
		System.out.println("--- 디저트 추가 ---");
		
		System.out.println("디저트 이름을 입력해주세요 > ");
		String dessertName = sc.nextLine();
		System.out.println("디저트 종류를 입력해주세요(빵, 케이크, 구움과자 > ");
		String category = sc.nextLine();
		System.out.println("디저트 재료를 입력해주세요 > ");
		String ingredient = sc.nextLine();
		System.out.println("칼로리를 입력해주세요 > ");
		String calories = sc.nextLine();
		
		int result = dc.save(dessertName, category, ingredient, calories);
		
		if(result > 0) {
			System.out.println("디저트 추가에 성공했습니다.");
		} else {
			System.out.println("디저트 추가에 실패했습니다.");
		}
	}
	
	private void findAll() {
		
		System.out.println("\n디저트 전체 조회");
		
		List<Dessert> desserts = dc.findAll();
		
		System.out.println("\n조회된 디저트는 " + desserts.size() + "개 입니다.");
		if(desserts.isEmpty()) {
			System.out.println("조회된 결과가 없습니다.");
		} else {
			
			for(Dessert dessert : desserts) {
				System.out.println("====================================");
				System.out.println(dessert.getDessertNo() + "번 디저트 정보");
				System.out.println("디저트 : " + dessert.getDessertName() + ", ");
				System.out.println("종류 : " + dessert.getCategory() + ", ");
				System.out.println("재료 : " + dessert.getIngredient() + ", ");
				System.out.println("칼로리 : " + dessert.getCalories() + ", ");
				System.out.println("등록일 : " + dessert.getEnrollDate() + ", ");
				System.out.println();
			}
		}
		
	}
	
	private void findById() {
		
		System.out.println("\n디저트 검색 서비스 입니다.");
		System.out.println("디저트명을 입력해주세요 > ");
		String dessertName = sc.nextLine();
		
		Dessert dessert = dc.findById(dessertName);
		
		if(dessert != null) {
			System.out.println(dessert + "검색 결과입니다.");
			System.out.println("=====================================");
			System.out.println("디저트 : " + dessert.getDessertName() + ", ");
			System.out.println("종류 : " + dessert.getCategory() + ", ");
			System.out.println("재료 : " + dessert.getIngredient() + ", ");
			System.out.println("칼로리 : " + dessert.getCalories() + ", ");
			System.out.println("등록일 : " + dessert.getEnrollDate() + ", ");
			System.out.println();
		} else {
			System.out.println("해당 디저트는 존재하지 않습니다.");
		}
	}
	
	
	
	
}
