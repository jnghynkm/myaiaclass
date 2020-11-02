package javaminiproject;

import java.util.Scanner;

public class JudgeQualification extends LoanInfor {
	
	int Balance;				// 계좌잔액
	String membership;			// 멤버십 등급

	// 생성자
	public JudgeQualification() {
		super();
	}
	
	Scanner sc = new Scanner(System.in);
	
	// 대출 가능 금액 출력 메서드
	void showPossibleAmound() {
		
		// #회원 정보에 있는지 없는지 확인 후 예외처리 
		System.out.println("고객님의 이름을 입력해주세요 >> ");
		String nm = sc.nextLine().trim();
		
		// LoanProgress 클래스를 통해 이름 비교 후 해당 고객 index 얻기
		LoanProgress lp = new LoanProgress();
		
		int index = 0;
		
		for(int i=0; i<lp.members2.size(); i++) {
			lp.members2.get(i).getName().equals(nm);
			index = i;
		}
		
		if(lp.members2.get(index).getLoanAmount() != 0) {
			System.out.println("이미 대출하신 내역이 존재합니다. 상환 완료 후에 대출이 가능합니다. 메뉴로 돌아갑니다.");
			return;
		}
		
		if(membership.equals("Platinum")) {			// 멤버십 등급이 플래티넘 이상일 경우 대출 가능
			if(Balance>=60000 && Balance<100000) {			// 6만원 <= 계좌잔액 < 10만원 
				System.out.println("최대 대출 가능 금액 : "/*+Balance*0.5*/);
			} else if(Balance>=100000 && Balance<200000) {	// 10만원 <= 계좌잔액 < 20만원 
				System.out.println("최대 대출 가능 금액 : "/*+Balance*0.7*/);
			} else if(Balance>=200000) {					// 계좌잔액 >= 20만원
				System.out.println("최대 대출 가능 금액 : "/*+Balance*0.9*/);
			}
			return;
		} else if(!membership.equals("Platinum")){	// 멤버십 등급이 플래티넘 미만일 경우 대출 불가능
			System.out.println("멤버십 등급이 Platinum 미만인 회원은 대출이 불가능합니다.");
			System.out.println("다시 메뉴로 이동합니다.");
			return;
		} 
		return;
	}
	
}

