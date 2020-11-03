package javaminiproject;

import java.util.Scanner;

import javax.print.attribute.Size2DSyntax;

public class JudgeQualification extends LoanInfor {
	
	int Balance;				// 계좌 잔액
	String membership;			// 멤버십 등급

	// 생성자
	public JudgeQualification() {
		super();
	}
	
	Scanner sc = new Scanner(System.in);
	
	// 대출 가능 금액 출력 메서드
	void showPossibleAmound() {
		
		// #회원 정보에 있는지 없는지 확인 후 예외처리 
		BankMemberDAO bmd = new BankMemberDAO();
		LoanProgress lp = new LoanProgress();
		
		String nm = "";
		String pw = "";
		int index = -1;
		
		while(true) {
			
			
			
				try {
					System.out.println("고객님의 이름을 입력해주세요 >> ");
					nm = sc.nextLine();
					System.out.println("비밀번호를 입력해주세요 >> ");
					pw = sc.nextLine();
					
					
					for(int i=0; i<bmd.members.size(); i++) {
						if(bmd.members.get(i).getPassword().equals(pw)) {
						index = i;
						}
					}
					
					if(index == -1) {
						Exception e = new Exception();
						throw e;
					}
				} catch(Exception e) {
					System.out.println("존재하지 않는 회원입니다. 메뉴로 돌아갑니다.");
					return;
				}
				break;
			}
		
		try {
			if(lp.loanMember.get(index).getLoanAmount() != 0) {
				System.out.println("이미 대출하신 내역이 존재합니다. 상환 완료 후에 대출이 가능합니다. 메뉴로 돌아갑니다.");
				return;
			}
		} catch (Exception e) {
			// LoanAmount 가 null 인 경우 패스
		}
		
		// Account 객체 생성
		Account ac;
		// AccountManager의 메서드 통해서 일치하는 리스트 찾기
		AccountManager am = AccountManager.getInstance();
		// Account 객체에 리스트 대입
		ac = am.FindAccount_Na(nm);
		
		//@테스트 객체 생성 (추후 삭제)
		AccountManager amtest = new AccountManager();
		
		
		membership = "";
		
		while(true) {
			
			am.membership(membership);
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
				System.out.println("최대 대출 가능 금액 : "+ac.getBalance()*0.5);
			} else if(ac.getBalance()>=100000 && ac.getBalance()<200000) {	// 10만원 <= 계좌잔액 < 20만원 
				System.out.println("최대 대출 가능 금액 : "+ac.getBalance()*0.7);
			} else if(ac.getBalance()>=200000) {					// 계좌잔액 >= 20만원
				System.out.println("최대 대출 가능 금액 : "+ac.getBalance()*0.9);
			}
			return;
		} else if(!membership.equals("Platinum")){	// 멤버십 등급이 플래티넘 미만일 경우 대출 불가능
			System.out.println("멤버십 등급이 Platinum 미만인 회원은 대출이 불가능합니다.");
			System.out.println("다시 메뉴로 이동합니다.");
			System.out.println();
			return;
		} 
		return;
	}
	
}

