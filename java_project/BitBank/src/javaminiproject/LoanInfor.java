package javaminiproject;

import java.util.ArrayList;
import java.util.List;

public class LoanInfor{

	private String name;           // 고객이름
	private String password;
	private String account;        // 계좌
	private String loanPeriod;     // 대출 기간
//	private double interestRate;   // 대출 금리
	private long loanAmount;        // 대출
	private long loanBalance;       // 대출 잔액
	private long balance;           // 계좌 잔액
	private long interest;       // 이자
		
	List<LoanInfor> loan = new ArrayList<LoanInfor>();
	
	private final double SHORT_INTEREST_RATE = 0.07;	// 단기 대출 금리
	private final double LONG_INTEREST_RATE = 0.03;	// 장기 대출 금리

	
	
	// 생성자로 초기화 	
	// TEST용 초기값 지정
	public LoanInfor() {
		this("김태희", 0, "1년", 2);
	}
	

	public LoanInfor(String name, int loanAmount, String loanPeriod, int interest) {
		this.name = name;
		this.loanPeriod = loanPeriod;
		this.loanAmount = loanAmount;
		this.loanBalance = loanBalance;
		this.balance = balance;
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
	public double getSHORT_INTEREST_RATE() {
		return SHORT_INTEREST_RATE;
	}
	public double getLONG_INTEREST_RATE() {
		return LONG_INTEREST_RATE;
	}


	// 단기 대출 이자 반환 메서드
	long ShortLoanInterest(long loanAmount) {
		interest = (int)(loanAmount*SHORT_INTEREST_RATE/12*100);
		return interest;
	}
	
	// 장기 대출 이자 반환 메서드
	long LongLoanInterest(long loanAmount) {
		interest = (int)(loanAmount*LONG_INTEREST_RATE/5/12*100);
		return interest;
	}
	
	// 단기 대출 잔액 반환 메서드
	long ShortLoanBalance(long loanAmount) {
		loanBalance = loanAmount;
		loanBalance -= (int)(loanAmount*SHORT_INTEREST_RATE/12*100);
		return loanBalance;
	}
	
	// 장기 대출 잔액 반환 메서드
	long LongLoanBalance(long loanAmount) {
		loanBalance = loanAmount;
		loanBalance -= (int)(loanAmount*LONG_INTEREST_RATE/12*100);
		return loanBalance;
	}
	
	// 대출 내역 확인 메서드
	void ShowLoanInfor() {
		System.out.println("성      함 : "+name);
		System.out.println("대출 기간 : "+loanPeriod);
		System.out.println("대출 금액 : "+loanAmount);
		
		if(loanPeriod.equals("1년")) {	// 단기 대출
			System.out.println("금      리 : "+SHORT_INTEREST_RATE);
			System.out.println("대출 잔액 : "+ShortLoanBalance(loanAmount));
			System.out.println("이자 잔액 : "+ShortLoanInterest(loanAmount));
			
		} else {						// 장기 대출
			System.out.println("금      리 : "+LONG_INTEREST_RATE);
			System.out.println("대출 잔액 : "+LongLoanBalance(loanAmount));
			System.out.println("이자 잔액 : "+LongLoanInterest(loanAmount));
		}
		
	}
	
	

}
