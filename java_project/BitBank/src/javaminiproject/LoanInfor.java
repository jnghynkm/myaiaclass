package javaminiproject;

import java.util.ArrayList;
import java.util.List;

public class LoanInfor implements Util {

	private String name;           // 고객이름
	private String password;
	private String account;        // 계좌
	private String loanPeriod;     // 대출 기간
//	private double interestRate;   // 대출 금리
	private long loanAmount;       // 대출
	private long loanBalance;      // 대출 잔액
	private long balance;          // 계좌 잔액
	private long interest;		   // 이자
		
	//List<LoanInfor> loan = new ArrayList<LoanInfor>();


	
	
	// 생성자로 초기화 	
	// TEST용 초기값 지정
	public LoanInfor() {
		//this("김태희", 0, "1년", 2);
	}
	

	public LoanInfor(String name, long loanAmount, String loanPeriod, long interest) {
		//loan = new ArrayList<LoanInfor>();
		this.name = name;
		this.loanPeriod = loanPeriod;
		this.loanAmount = loanAmount;
//		this.loanBalance = loanBalance;
//		this.balance = balance;
		this.interest = interest;

	}
	
	// getter/setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public long getInterest() {
		return interest;
	}

	public void setInterest(long interest) {
		this.interest = interest;
	}


	// 단기 대출 이자 반환 메서드
	long ShortLoanInterest(long loanAmount) {
		interest = (long)(loanAmount*LoanProgress.SHORT_INTEREST_RATE/12);
		return interest;
	}
	
	// 장기 대출 이자 반환 메서드
	long LongLoanInterest(long loanAmount) {
		interest = (long)(loanAmount*LoanProgress.LONG_INTEREST_RATE/5/12);
		return interest;
	}
	
	// 단기 대출 잔액 반환 메서드
	long ShortLoanBalance(long loanAmount) {
		loanBalance = loanAmount;
		loanBalance -= (long)(loanAmount*LoanProgress.SHORT_INTEREST_RATE/12);
		return loanBalance;
	}
	
	// 장기 대출 잔액 반환 메서드
	long LongLoanBalance(long loanAmount) {
		loanBalance = loanAmount;
		loanBalance -= (long)(loanAmount*LoanProgress.LONG_INTEREST_RATE/12);
		return loanBalance;
	}
	
	// 대출 내역 확인 메서드
	void ShowLoanInfor() {
		
		LoanProgress lp = LoanProgress.getInstance();
		
		
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
			System.out.println("이자 잔액 : "+lp.loan.get(index).getInterest());
			
		} else if(lp.loan.get(index).getLoanPeriod().equals("5년")) {	// 장기 대출					// 장기 대출
			System.out.println("금      리 : "+LoanProgress.LONG_INTEREST_RATE);
			System.out.println("대출 잔액 : "+lp.loan.get(index).getLoanBalance());
			System.out.println("이자 잔액 : "+lp.loan.get(index).getInterest());
		}
		
	}
	
	

}
