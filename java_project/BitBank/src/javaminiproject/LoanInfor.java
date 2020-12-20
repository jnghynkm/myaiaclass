package javaminiproject;

public class LoanInfor implements Util {

	private String name;            // 고객이름
	private String loanPeriod;      // 대출 기간
	private long loanAmount;        // 대출
	private long loanBalance;       // 대출 잔액
	private long interest;       	// 이자
	private long loanPrincipal;  	// 대출 원금
	
	LoanProgress lp = LoanProgress.getInstance();
		
	// 생성자로 초기화 	
	public LoanInfor() {
		
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
	
	// 단기 대출 잔액 차감 메서드
	long shortLoanBalance(long loanAmount, long loanPrincipal) {
		loanBalance =loanAmount-(long)(loanPrincipal/12);
		return loanBalance;
	}
	
	// 장기 대출 잔액 차감 메서드
	long longLoanBalance(long loanAmount, long loanPrincipal) {
		loanBalance =loanAmount-(long)(loanPrincipal/5/12);
		return loanBalance;
	}
	
	// 대출 내역 확인 메서드
	void ShowLoanInfor() {
		AccountManager am = AccountManager.getInstance();
		String nm = "";
		int index = -1;
		
		out: while(true) {
			
				try {
					System.out.println("고객님의 이름을 입력해주세요 >> ");
					nm = SC.nextLine().trim();
					System.out.println("계좌 비밀번호를 입력해주세요 >> ");
					String pw = SC.nextLine().trim();
					
					if(nm == null) {
						BadInputException e = new BadInputException(nm);
						throw e;
					} 
					
					for(int i=0; i<am.getAccountArray().length ;i++) {
						if(am.getAccountArray()[i].getPassword().equals(pw)) {
							for(int j=0; j<lp.loan.size(); j++) {
								if(lp.loan.get(i).getName().equals(nm)) {
								index = j;
								break out;
								}
							}
							
							if(index == -1) {
								Exception e = new Exception();
								throw e;
							}
						} else {
							BadInputException e = new BadInputException(nm);
							throw e;
						}
						
					}
				} catch(BadInputException e) {
					System.out.println("잘못 입력하셨습니다. 다시 입력해주십시오.");
					continue;
				} catch(Exception e) {
					System.out.println("대출 내역이 없습니다. 메뉴로 돌아갑니다.");
					return;
				}
				break;
			}
		
		System.out.println("성      함 : "+lp.loan.get(index).getName());
		System.out.println("대출 기간 : "+lp.loan.get(index).getLoanPeriod());
		System.out.println("대출 금액 : "+lp.loan.get(index).getLoanPrincipal());
		
		if(lp.loan.get(index).getLoanPeriod().equals("1년")) {			// 단기 대출
			System.out.println("금      리 : "+LoanProgress.SHORT_INTEREST_RATE);
			System.out.println("대출 잔액 : "+lp.loan.get(index).getLoanAmount());
			System.out.println("------------------------------------");
		} else if(lp.loan.get(index).getLoanPeriod().equals("5년")) {	// 장기 대출
			System.out.println("금      리 : "+LoanProgress.LONG_INTEREST_RATE);
			System.out.println("대출 잔액 : "+lp.loan.get(index).getLoanAmount());
			System.out.println("------------------------------------");
		}
	}
	
	

}
