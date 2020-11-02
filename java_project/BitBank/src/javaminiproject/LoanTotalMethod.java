package javaminiproject;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class LoanTotalMethod extends LoanInfor implements Util {

	// 주요 메서드 한 군데에 모아놓고 TEST중 (미완)

	
	
	
	
    // 대출 자격을 확인하는 메서드

	int Balance;// 계좌잔액
	
	// 대출 가능 금액 		
   public void showPossibleAmound() {
	System.out.println("멤버십 등급을 입력해주세요 >> ");

	String membership = SC.nextLine();

	
	if(membership=="Platinum") {							// 멤버십 등급이 플래티넘 이상일 경우 대출 가능

		if(Balance>=60000 && Balance<100000) {			// 6만원 <= 계좌잔액 < 10만원 

			System.out.println("최대 대출 가능 금액 : "/*+Balance*0.5*/);

		} else if(Balance>=100000 && Balance<200000) {	// 10만원 <= 계좌잔액 < 20만원 

			System.out.println("최대 대출 가능 금액 : "/*+Balance*0.7*/);

		} else if(Balance>=200000) {					// 계좌잔액 >= 20만원

			System.out.println("최대 대출 가능 금액 : "/*+Balance*0.9*/);

		}

		return;

	} else if(membership=="Silver" && membership=="Gold"){	// 멤버십 등급이 플래티넘 미만일 경우 대출 불가능

		System.out.println("멤버십 등급이 Platinum 미만인 회원은 대출이 불가능합니다.");
		System.out.println("다시 메뉴로 이동합니다.");
		return;

	} else {
		System.out.println("잘못 입력하셨습니다.");
		System.out.println("다시 입력해주세요.");
		return;

	}
   
	

}
	
   
   
   
   
   private double possibleAmount;   // 대출 가능 금액
   private int loanAmount;          // 대출 금액
   private String loanPeriod;       // 상환기간
   private String membership;
  
   
   List<LoanInfor> loanMember = new ArrayList<LoanInfor>();
     

	// 리스트에 정보 저장
	void inputArray(LoanInfor l) {
		loanMember.add(l);
	}
		
	// 단기, 장기 대출 진행 메서드	
	public void executeLoan() throws NullPointerException {	
			
		while(true) {
		
	//	// 1)멤버쉽이 플래티넘 미만일 경우, 2) 이미 대출을 받은 경우에는 진행할 수 없도록 예외처리
   //    if(membership != "platinum") { //멤버쉽 기능이 완성되면 처리
	//		System.out.println("멤버쉽 등급을 만족하지 못해 대출을 실행할 수 없습니다. 메뉴로 돌아갑니다.");
	//		return;
	//	} 
          if(getLoanAmount()!= 0) {
			System.out.println("이미 대출하신 내역이 존재합니다. 상환 완료 후에 대출이 가능합니다. 메뉴로 돌아갑니다.");
			return;
		}
		
		System.out.println("대출 실행 화면 입니다.");		
		System.out.println("=====================");
		System.out.println("어떤 대출을 실행하시겠습니까?");
		System.out.println("1. 단기 대출");
		System.out.println("2. 장기 대출");
		System.out.println("3. 이전 화면");
		System.out.println("=====================");
				
       		
		int select = 0;
			
		// 메뉴값 예외처리
		try {
			
		select = SC.nextInt();
		SC.nextLine();
		
		if(!(select>=Util.SHORT_LOAN && select<=Util.BEFORE)) { 	
   		MenuMismatchException e = new MenuMismatchException(String.valueOf(select));
   		throw e;
 	    }
   	// 주어진 메뉴를 제외한 숫자 혹은 특수문자를 입력했을 때 예외처리
   	} catch(InputMismatchException | MenuMismatchException e){
   		System.out.println("메뉴의 선택이 올바르지 않습니다.\n"+ "다시 선택해주세요.");
   		SC.nextLine();
   		continue;
   	} catch (Exception e) {
			System.out.println("메뉴의 선택이 올바르지 않습니다.\n"+ "다시 선택해주세요.");		
			SC.nextLine();
			continue;
		}
   	    		
		// 고객의 계좌정보를 불러올 인스턴스 생성
	    AccountManager account = new AccountManager();
		
		try {
		switch(select) {							
		        case 1 :
		        			        
		        System.out.println("단기 대출 화면입니다. 대출 가능한 금액과 금리를 확인해주세요.");		        
		        System.out.println("고객님의 이름을 입력해주세요.");		
				String name = SC.nextLine();		        
				
		        // 고객의 이름과 일치하는 계좌의 배열 인덱스를 찾아서 잔액을 반환 -> executeShortLoan 메서드의 매개변수에 대입
		        for(int i=0; i< AccountManager.accountArray.length; i++) {
		        	if(AccountManager.accountArray[i].getAccountName().equals(name)) {
		        		 executeShortLoan(name, AccountManager.accountArray[i].getBalance());
		         }	else {
		        	 System.out.println("일치하는 정보를 찾을 수 없습니다.");
		        }
		        }
		       		        	        		        
		        case 2 :
		    	System.out.println("장기 대출 화면입니다. 대출 가능한 금액과 금리를 확인해주세요.");
		    	
		    	System.out.println("고객님의 이름을 입력해주세요.");		
				name = SC.nextLine();
		    	
		    	// 고객의 이름과 일치하는 계좌의 배열 인덱스를 찾아서 잔액을 반환 -> executeLongLoan 메서드의 매개변수에 대입
		        for(int i=0; i< AccountManager.accountArray.length; i++) {
		        	if(AccountManager.accountArray[i].getAccountName().equals(name)) {
		        executeLongLoan(name, AccountManager.accountArray[i].getBalance());
	            }
		        }			
		    	return;
		    		    	
		        case 3 :
		    	System.out.println("이전 화면으로 돌아갑니다.");
		    	return;
		    	
		}   
	    }		
		catch(Exception e) {
	        	System.out.println("오류가 발생하였습니다. ");
	    }
	}	//while end
	}

	
	
	
	// 단기대출 진행 메서드
	private void executeShortLoan(String name, long balance) {						

		loanPeriod = "1년"; // 단기 대출 상환 기간
		
		if(60000 <= balance && balance < 100000) {
			possibleAmount = balance*0.5;
		} else if(10000<= balance && balance <150000) {
			possibleAmount = balance*0.7;		
		} else {
			possibleAmount = balance*0.9;
		}	
		
		System.out.println("===================================================");
		System.out.println("( 상환기간 : "+ loanPeriod +", 금리 : "+ getSHORT_INTEREST_RATE() +"% )");				
		System.out.println("---------------------------------------------------");
		System.out.println("대출 가능 금액 : " + possibleAmount +"원.");
		System.out.println("===================================================");
		
		System.out.println("원하시는 금액을 입력해주세요.(가능 금액 : "+ possibleAmount +"원)");		
		loanAmount = SC.nextInt();
		System.out.println("입력 완료>>");
						
		checkMember();
		
		System.out.println("===================================================");
		System.out.println("※ 대출이 정상처리되었습니다. 아래 내용을 확인해주세요.");
		System.out.println("---------------------------------------------------");
		System.out.print("이번 달 이자 "+ ShortLoanInterest(loanAmount)+"원을 제외한 금액,");
		loanAmount = loanAmount-(int)ShortLoanInterest(loanAmount);  //대출금액 - 이번달 이자
		System.out.println(loanAmount+ "원이 고객님의 계좌로 입금됩니다.");		
		System.out.println("===================================================");
		
	
		// 계좌 잔액 + 대출금		
		Account account2 = new Account();
		account2.setBalance(account2.getBalance()+loanAmount);			
	
       //  return loanAmount;	
 }

	
	
	 
	// 장기대출 진행 메서드
 private void executeLongLoan(String name, long balance) {
	
	  loanPeriod = "5년";  // 장기 대출 상환 기간
	  
	  if(60000 <= balance && balance < 100000) {
			possibleAmount = balance*0.5;
		} else if(10000<= balance && balance <150000) {
			possibleAmount = balance*0.7;		
		} else {
			possibleAmount = balance*0.9;
		}		
	    System.out.println("===================================================");
		System.out.println("( 상환기간 : "+loanPeriod+ ", 금리 : "+ getLONG_INTEREST_RATE() +"% )");			
		System.out.println("---------------------------------------------------");
		System.out.println("가능금액 : " + possibleAmount +"원.");
		System.out.println("===================================================");
		
		System.out.println("원하시는 금액을 입력해주세요.(가능 금액 : "+ possibleAmount +"원)");		
		loanAmount = SC.nextInt();
		System.out.println("입력 완료>>");		
		
		System.out.println("===================================================");
		System.out.println("※ 대출이 정상처리되었습니다. 아래 내용을 확인해주세요.");
		System.out.println("---------------------------------------------------");
		System.out.print("이번 달 이자 "+ LongLoanInterest(loanAmount)+"원을 제외한 금액 ");
		loanAmount = loanAmount-(int)LongLoanInterest(loanAmount); //대출금액 - 이번달 이자
		System.out.println(loanAmount+ "원이 고객님의 계좌로 입금됩니다.");
		System.out.println("===================================================");
		
		// 계좌 잔액 + 대출금
		Account account2 = new Account();
		account2.setBalance(account2.getBalance()+loanAmount);	
		
		// 대출이 이루어지면 LoanInfor에 고객의 대출 정보를 저장.
       inputArray(new LoanInfor(name, loanAmount, loanPeriod, ShortLoanInterest(loanAmount)));
		
		
  //  return loanAmount;     		
 }
 
 
 
  private void checkMember() {	
	    System.out.println("본인확인을 위해 비밀번호를 다시 입력해주세요.");
	    SC.nextLine();
	    String password = SC.nextLine();
	    // 회원 정보가 담긴 리스트를 불러오기
		for(int i=0; i<BankMemberDAO.members.size();i++) {
			if(BankMemberDAO.members.get(i).getPassword().equals(password)) {			
			} else {
				System.out.println("비밀번호 입력이 잘못되었습니다. 확인 후 다시 시도해주세요.");
				break;
			}		   
		}	
		System.out.println("본인 확인이 완료되었습니다.");
  }		

 
  
  
  
//대출금 상환 메서드
		void repayLoan() {
			
			int select = 0;
			
			while(true) {
				//상환 메뉴 선택
				System.out.println("상환하실 메뉴를 입력해주세요.");
				System.out.println("1. 이자 납입");
				System.out.println("2. 원리금 상환");
				System.out.println("3. 전액 상환");
				System.out.println(">> ");
				
				try {
					select = SC.nextInt();
					if(!(select>0 && select<4)) {
						Exception e = new Exception(String.valueOf(select));
						throw e;
					}
				} catch (Exception e1) {
					System.out.println("메뉴입력이 잘못되었습니다.");
					SC.nextLine();
					continue;
				}
				break;
			} // while end
				
			switch(select) {
				case 1 :	// 이자 납입
					System.out.println("이자납입test");
					System.out.println("총 대출 금액 : "+getLoanAmount());
					
					if(getLoanPeriod().equals("1년")) {	// 단기 대출 이자
						System.out.println("이번 달 이자 : "+ShortLoanInterest(getLoanAmount()));
					} else {						// 장기 대출 이자
						System.out.println("이번 달 이자 :"+LongLoanInterest(getLoanAmount()));
					}
					
					while(true) {
						System.out.println("1. 납입하기");
						System.out.println("2. 대출 메뉴로 돌아가기");
						select = 0;
						
						try {
							select = SC.nextInt();
							if(!(select>0 && select<3)) {
								Exception e = new Exception(String.valueOf(select));
								throw e;
							}
						} catch(Exception e) {
							System.out.println("메뉴입력이 잘못되었습니다.");
							SC.nextLine();
							continue;
						}
						break;
					}
					
					if(select == 1) {	// 이자 납입하기
						if(getLoanPeriod().equals("1년")) {	// 단기 대출 이자 납입
							System.out.println("대출 기간 : 단기 대출(1년)");
							System.out.println("총 대출 금액 : "+getLoanAmount());
							System.out.println("대출 잔액 : "+ShortLoanBalance(getLoanAmount()));
							System.out.println("이자 납입이 완료되었습니다.");
						} else {						// 장기 대출 이자 납입
							System.out.println("대출 기간 : 장기 대출(5년)");
							System.out.println("총 대출 금액 : "+getLoanAmount());
							System.out.println("대출 잔액 : "+LongLoanBalance(getLoanAmount()));
							System.out.println("이자 납입이 완료되었습니다.");
						}	// 납입 end
					} else {			// 대출 메뉴로 돌아가기
						return;
					}
					break;	// switch break
				case 2 :	// 원리금 상환
					System.out.println("원리금상환test");

					System.out.println("총 대출 금액 : "+getLoanAmount());
					
					if(getLoanPeriod().equals("1년")) {	// 단기 대출 원리금
						System.out.println("이번 달 원리금 : "+(getLoanAmount()/12)+ShortLoanInterest(getLoanAmount()));
					} else {						// 장기 대출 원리금
						System.out.println("이번 달 원리금 :"+(getLoanAmount()/5/12)+LongLoanInterest(getLoanAmount()));
					}
					
					while(true) {
						System.out.println("1. 상환하기");
						System.out.println("2. 대출 메뉴로 돌아가기");
						select = 0;
						
						try {
							select = SC.nextInt();
							if(!(select>0 && select<3)) {
								Exception e = new Exception(String.valueOf(select));
								throw e;
							}
						} catch(Exception e) {
							System.out.println("메뉴입력이 잘못되었습니다.");
							SC.nextLine();
							continue;
						}
						break;
					}
					
					if(select == 1) {	// 원리금 상환하기
						if(getLoanPeriod().equals("1년")) {	// 단기 대출 원리금 납입
							System.out.println("대출 기간 : 단기 대출(1년)");
							System.out.println("총 대출 금액 : "+getLoanAmount());
							System.out.println("대출 잔액 : "+ShortLoanBalance(getLoanAmount()));
							System.out.println("원리금 상환이 완료되었습니다.");
						} else {						// 장기 대출 이자 납입
							System.out.println("대출 기간 : 장기 대출(5년)");
							System.out.println("총 대출 금액 : "+getLoanAmount());
							System.out.println("대출 잔액 : "+LongLoanBalance(getLoanAmount()));
							System.out.println("원리금 상환이 완료되었습니다.");
						}	// 상환 end
					} else {			// 대출 메뉴로 돌아가기
						return;
					}
					break;	// switch break
				case 3 :	// 전액 상환
					System.out.println("전액상환test");
					
					System.out.println("총 대출 금액 : "+getLoanAmount());
					
					if(getLoanPeriod().equals("1년")) {	// 단기 대출 전액 상환
						System.out.println("상환하실 금액 : "+ShortLoanBalance(getLoanAmount()));
					} else {						// 장기 대출 전액 상환
						System.out.println("상환하실 금액 : "+LongLoanBalance(getLoanAmount()));
					}
					
					while(true) {
						System.out.println("1. 전액 상환하기");
						System.out.println("2. 대출 메뉴로 돌아가기");
						select = 0;
						
						try {
							select = SC.nextInt();
							if(!(select>0 && select<3)) {
								Exception e = new Exception(String.valueOf(select));
								throw e;
							}
						} catch(Exception e) {
							System.out.println("메뉴입력이 잘못되었습니다.");
							SC.nextLine();
							continue;
						}
						break;
					}
					
					if(select == 1) {	// 전액 상환하기
						if(getLoanPeriod().equals("1년")) {	// 단기 대출 전액 상환
							System.out.println("대출 기간 : 단기 대출(1년)");
							System.out.println("총 대출 금액 : "+getLoanAmount());
							System.out.println("전액 상환이 완료되었습니다.");
						} else {						// 장기 대출 전액 상환
							System.out.println("대출 기간 : 장기 대출(5년)");
							System.out.println("총 대출 금액 : "+getLoanAmount());
							System.out.println("전액 상환이 완료되었습니다.");
						}	// 상환 end
					} else {			// 대출 메뉴로 돌아가기
						return;
					}
					break;	// switch break
			}
			
		} // 대출금 상환 메서드 end
	
   
   
   
   
   
	
	
}
