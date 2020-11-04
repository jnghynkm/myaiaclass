package javaminiproject;

import java.util.ArrayList;
import java.util.List;

public class LoanInfor implements Util {

	private String name;           // 고객이름
	private String loanPeriod;     // 대출 기간
	private long loanAmount;        // 대출
	private long loanBalance;       // 대출 잔액
	private long interest;       // 이자
	private long loanPrincipal;  // 대출 원금
	
	LoanProgress lp = LoanProgress.getInstance();
		
	// 생성자로 초기화 	
	// TEST용 초기값 지정
	public LoanInfor() {
	//	this("김태희", 0, "1년", 2);
	}
	

	public LoanInfor(String name, long loanAmount, long loanPrincipal,  String loanPeriod, long interest) {
		this.name = name;
		this.loanPeriod = loanPeriod;
		this.loanAmount = loanAmount;
		this.interest = interest;
		this.loanPrincipal = loanPrincipal;

	}
	
	// getter/setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(String loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(long loanAmount) {
		this.loanAmount = loanAmount;
	}

	public long getLoanBalance() {
		return loanBalance;
	}

	public void setLoanBalance(long loanBalance) {
		this.loanBalance = loanBalance;
	}

	public long getInterest() {
		return interest;
	}

	public void setInterest(long interest) {
		this.interest = interest;
	}
	public long getLoanPrincipal() {
		return loanPrincipal;
	}
	public void setLoanPrincipal(long loanPrincipal) {
		this.loanPrincipal = loanPrincipal;
	}

	// 단기 대출 이자 반환 메서드
	long shortLoanInterest(long loanPrincipal) {
		interest =(long)(loanPrincipal*LoanProgress.SHORT_INTEREST_RATE/12);
		return interest;
	}
	
	// 장기 대출 이자 반환 메서드
	long longLoanInterest(long loanPrincipal) {
		interest =(long)(loanPrincipal*LoanProgress.LONG_INTEREST_RATE/5/12);
		return interest;
	}
	
	// 단기 대출 원리금 차감 메서드
	long shortLoanBalance(long loanAmount, long loanPrincipal) {
		loanBalance =loanAmount-(long)(loanPrincipal/12)-shortLoanInterest(loanPrincipal);
		return loanBalance;
	}
	
	// 장기 대출 원리금 차감 메서드
	long longLoanBalance(long loanAmount, long loanPrincipal) {
		loanBalance =loanAmount-(long)(loanPrincipal/5/12)-longLoanInterest(loanPrincipal);
		return loanBalance;
	}
	
	// 대출 내역 확인 메서드
	void ShowLoanInfor() {
		
		String nm = "";
		int index = -1;
		
		while(true) {
			
				try {
					System.out.println("고객님의 이름을 입력해주세요 >> ");
					nm = SC.nextLine();
					
					/*
					for(int i=0; i<lp.loanMember.size(); i++) {
						if(lp.loanMember.get(i).getName().equals(nm)) {
						index = i;
						}
					}
					/**/
					//for(int i=0; i<lp.getLoanMember().size(); i++) {
					for(int i=0; i<lp.loan.size(); i++) {
						if(lp.loan.get(i).getName().equals(nm)) {
						index = i;
						}
					}
					
					if(index == -1) {
						Exception e = new Exception();
						throw e;
					}
				} catch(Exception e) {
					System.out.println("대출 내역이 없습니다. 메뉴로 돌아갑니다.");
					return;
				}
				break;
			}
		
		
		System.out.println("성      함 : "+lp.loan.get(index).getName());
		System.out.println("대출 기간 : "+lp.loan.get(index).getLoanPeriod());
		System.out.println("대출 금액 : "+lp.loan.get(index).getLoanAmount());
		
		
		if(lp.loan.get(index).getLoanPeriod().equals("1년")) {		// 단기 대출
			System.out.println("금      리 : "+LoanProgress.SHORT_INTEREST_RATE);
			System.out.println("대출 잔액 : "+lp.loan.get(index).getLoanBalance());
			
		} else if(lp.loan.get(index).getLoanPeriod().equals("5년")) {	// 장기 대출					// 장기 대출
			System.out.println("금      리 : "+LoanProgress.LONG_INTEREST_RATE);
			System.out.println("대출 잔액 : "+lp.loan.get(index).getLoanBalance());
		}
		
	}
	
	

}
