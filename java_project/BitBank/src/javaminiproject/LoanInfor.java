package javaminiproject;

public class LoanInfor {
	
	String name;				// 고객 이름
	String account;				// 계좌
	String loanPeriod;			// 대출 기간
	long loanAmount;			// 대출금
	long loanBalance;			// 대출 잔액
	long balance;				// 계좌 잔액
	long interest;				// 이자
	
	public static final double SHORT_INTEREST_RATE=0.07;	// 단기 대출 금리
	public static final double LONG_INTEREST_RATE=0.03;		// 장기 대출 금리
	
	// 생성자
	LoanInfor(){
	}
	
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

	public static double getShortInterestRate() {
		return SHORT_INTEREST_RATE;
	}

	public static double getLongInterestRate() {
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
