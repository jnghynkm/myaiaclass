package javaminiproject;

import java.util.Scanner;

public class JudgeQualification {
	
	int Balance;				// 계좌 잔액
	String membership;			// 멤버십 등급

	// 생성자
	public JudgeQualification() {
		super();
	}
	
	Scanner sc = new Scanner(System.in);
	
	// 대출 자격 확인 후 대출 가능 금액 출력 메서드
	void showPossibleAmound() {
		BankMemberDAO bmd = new BankMemberDAO();
		LoanProgress lp = LoanProgress.getInstance();
		String nm = "";
		String pw = "";
		int index = -1;
		
		while(true) {
			
				try {
					System.out.println("고객님의 이름을 입력해주세요 >> ");
					nm = sc.nextLine().trim();
					System.out.println("회원 비밀번호를 입력해주세요 >> ");
					pw = sc.nextLine().trim();
					
					for(int i=0; i<bmd.members.size(); i++) {
						if(bmd.members.get(i).getPassword().equals(pw)) {
						index = i;
						}
					}
					
					if(index == -1) {
						NonMemberException e = new NonMemberException();
						throw e;
					}
				} catch(NonMemberException e) {
					System.out.println("존재하지 않는 회원입니다. 메뉴로 돌아갑니다.");
					return;
				}
				break;
			}
		
		
		try {
			if(lp.loan.get(index).getLoanAmount() != 0) {
				System.out.println("이미 대출하신 내역이 존재합니다. 상환 완료 후에 대출이 가능합니다. 메뉴로 돌아갑니다.");
				return;
			}
		} catch (Exception e) {
			// LoanAmount 가 null 인 경우 패스
		}
		
		// Account 객체 생성
		Account ac;
		// AccountManager 인스턴스 생성
		AccountManager am = AccountManager.getInstance();
		// Account 객체에 리스트 대입
		ac = am.FindAccount_Na(nm);
		
		membership = "";
		
		while(true) {
			membership = am.membership(membership);
			if(membership == null) {
				continue;
			} else {
				System.out.println("====================================");
				System.out.println();
				break;
			}
		}
		
		if(membership.equals("Platinum")) {			// 멤버십 등급이 플래티넘 이상일 경우 대출 가능
			if(ac.getBalance()>=60000 && ac.getBalance()<100000) {			// 6만원 <= 계좌잔액 < 10만원 
				System.out.println("최대 대출 가능 금액 : "+(int)(ac.getBalance()*0.5)+"원");
				System.out.println("------------------------------------");
			} else if(ac.getBalance()>=100000 && ac.getBalance()<200000) {	// 10만원 <= 계좌잔액 < 20만원 
				System.out.println("최대 대출 가능 금액 : "+(int)(ac.getBalance()*0.7)+"원");
				System.out.println("------------------------------------");
			} else if(ac.getBalance()>=200000) {							// 계좌잔액 >= 20만원
				System.out.println("최대 대출 가능 금액 : "+(int)(ac.getBalance()*0.9)+"원");
				System.out.println("------------------------------------");
			}
			return;
		} else if(!membership.equals("Platinum")){	// 멤버십 등급이 플래티넘 미만일 경우 대출 불가능
			System.out.println("멤버십 등급이 Platinum 미만인 회원은 대출이 불가능합니다.");
			System.out.println(" [※ 필수 조건 : Platinum 등급(계좌업무 3번 이상, 혹은 예금 금액 60,000원 이상 시 승급)]");
			System.out.println("다시 메뉴로 이동합니다.");
			System.out.println();
			return;
		} 
		return;
	}
	
}

