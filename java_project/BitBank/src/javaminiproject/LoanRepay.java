package javaminiproject;

import java.util.Scanner;

public class LoanRepay extends LoanInfor {
	
	// 생성자
	public LoanRepay() {
		super();
	}
	
	Scanner sc = new Scanner(System.in);

	// 대출금 상환 메서드
	void LoanRepay() {
		
		int select = 0;
		
		while(true) {
			//상환 메뉴 선택
			System.out.println("====================================");
			System.out.println("상환하실 메뉴를 입력해주세요.");
			System.out.println("1. 이자 납입");
			System.out.println("2. 원리금 상환");
			System.out.println("3. 전액 상환");
			System.out.println("====================================");
			
			try {
				select = sc.nextInt();
				if(!(select>0 && select<4)) {
					Exception e = new Exception(String.valueOf(select));
					throw e;
				}
			} catch (Exception e) {
				System.out.println("메뉴입력이 잘못되었습니다.");
				sc.nextLine();
				continue;
			}
			break;
		} // while end
			
		// #회원 정보에 있는지 없는지 확인 후 예외처리 
				BankMemberDAO bmd = new BankMemberDAO();
				//LoanProgress lp = new LoanProgress();
				LoanProgress lp = new LoanProgress();
				
//				//@테스트 생성자
//				LoanProgress lptest = new LoanProgress("김태희", 2500000, "1년", 1000);
				
				String nm = "";
				
				int index = -1;
				
				while(true) {
					
						try {
							sc.nextLine();
							System.out.println("고객님의 이름을 입력해주세요 >> ");
							nm = sc.nextLine().trim();
							
							if(nm == null) {
								BadInputException e = new BadInputException();
								throw e;
							} 
							
							for(int i=0; i<lp.getLoanMember().size(); i++) {
								if(lp.getLoanMember().get(i).getName().equals(nm)) {
								index = i;
								}
							}
							
							if(index == -1) {
								Exception e = new Exception();
								throw e;
							}
							
						} catch(BadInputException e) {
							System.out.println("잘못 입력하셨습니다. 다시 입력해주십시오.");
							continue;
						} catch(Exception e) {
							System.out.println("존재하지 않는 회원입니다. 메뉴로 돌아갑니다.");
							return;
						}
						break;
					}
				
				Account ac;
				AccountManager am = AccountManager.getInstance();
				ac = am.FindAccount_Na(nm);
		
		switch(select) {
			case 1 :	// 이자 납입
				//System.out.println("이자납입test");
					
				System.out.println("대출 잔액 : "+lp.getLoanMember().get(index).getLoanAmount());
				
				if(lp.getLoanMember().get(index).getLoanPeriod().equals("1년")) {	// 단기 대출 이자
					
					System.out.println("이번 달 이자 : "+ShortLoanInterest(lp.getLoanMember().get(index).getLoanAmount()));
				} else {													// 장기 대출 이자
					System.out.println("이번 달 이자 :"+LongLoanInterest(lp.getLoanMember().get(index).getLoanAmount()));
				}
				
				while(true) {
					System.out.println("1. 납입하기");
					System.out.println("2. 대출 메뉴로 돌아가기");
					select = 0;
					
					try {
						select = sc.nextInt();
						if(!(select>0 && select<3)) {
							Exception e = new Exception(String.valueOf(select));
							throw e;
						}
					} catch(Exception e) {
						System.out.println("메뉴입력이 잘못되었습니다.");
						sc.nextLine();
						continue;
					}
					break;
				}
				
				if(select == 1) {	// 이자 납입하기
					if(lp.getLoanMember().get(index).getLoanPeriod().equals("1년")) {	// 단기 대출 이자 납입
						
						// 계좌 잔액 차감
						ac.setBalance(ac.getBalance()-ShortLoanInterest(lp.getLoanMember().get(index).getLoanAmount()));
						
						
						System.out.println("대출 기간 : 단기 대출(1년)");
						System.out.println("대출 잔액 : "+lp.getLoanMember().get(index).getLoanBalance());
						System.out.println("대출 이자 납입이 완료되었습니다.");
					} else {													// 장기 대출 이자 납입
						
						// 계좌 잔액 차감
						ac.setBalance(ac.getBalance()-LongLoanInterest(lp.getLoanMember().get(index).getLoanAmount()));
						
						System.out.println("대출 기간 : 장기 대출(5년)");
						System.out.println("대출 잔액 : "+lp.getLoanMember().get(index).getLoanBalance());
						System.out.println("대출 이자 납입이 완료되었습니다.");
					}	// 납입 end
				} else {			// 대출 메뉴로 돌아가기
					return;
				}
				break;	// switch break
			case 2 :	// 원리금 상환
				//System.out.println("원리금상환test");

				System.out.println("대출 잔액 : "+lp.getLoanMember().get(index).getLoanAmount());
				
				if(lp.getLoanMember().get(index).getLoanPeriod().equals("1년")) {	// 단기 대출 원리금
					System.out.println("이번 달 원리금 : "+(lp.getLoanMember().get(index).getLoanAmount()/12)+ShortLoanInterest(lp.getLoanMember().get(index).getLoanAmount()));
				} else {													// 장기 대출 원리금
					System.out.println("이번 달 원리금 :"+(lp.getLoanMember().get(index).getLoanAmount()/5/12)+LongLoanInterest(lp.getLoanMember().get(index).getLoanAmount()));
				}
				
				while(true) {
					System.out.println("1. 상환하기");
					System.out.println("2. 대출 메뉴로 돌아가기");
					select = 0;
					
					try {
						select = sc.nextInt();
						if(!(select>0 && select<3)) {
							Exception e = new Exception(String.valueOf(select));
							throw e;
						}
					} catch(Exception e) {
						System.out.println("메뉴입력이 잘못되었습니다.");
						sc.nextLine();
						continue;
					}
					break;
				}
			
				
				if(select == 1) {	// 원리금 상환하기
					if(lp.getLoanMember().get(index).getLoanPeriod().equals("1년")) {	// 단기 대출 원리금 납입
						
						// 계좌 잔액 차감
						ac.setBalance(ac.getBalance()-(lp.getLoanMember().get(index).getLoanAmount()/12)+ShortLoanInterest(lp.getLoanMember().get(index).getLoanAmount()));
						
						System.out.println("대출 기간 : 단기 대출(1년)");
						// 대출 잔액 차감
						System.out.println("대출 잔액 : "+ShortLoanBalance(lp.getLoanMember().get(index).getLoanAmount()));
						System.out.println("대출 원리금 상환이 완료되었습니다.");
					} else {													// 장기 대출 이자 납입
						
						// 계좌 잔액 차감
						ac.setBalance(ac.getBalance()-(lp.getLoanMember().get(index).getLoanAmount()/5/12)+LongLoanInterest(lp.getLoanMember().get(index).getLoanAmount()));
						
						System.out.println("대출 기간 : 장기 대출(5년)");
						// 대줄 잔액 차감
						System.out.println("대출 잔액 : "+LongLoanBalance(lp.getLoanMember().get(index).getLoanAmount()));
						System.out.println("대출 원리금 상환이 완료되었습니다.");
					}	// 상환 end
				} else {			// 대출 메뉴로 돌아가기
					return;
				}
				break;	// switch break
			case 3 :	// 전액 상환
				//System.out.println("전액상환test");
				
				System.out.println("대출 잔액 : "+lp.getLoanMember().get(index).getLoanAmount());
				
				if(lp.getLoanMember().get(index).getLoanPeriod().equals("1년")) {	// 단기 대출 전액 상환
					System.out.println("상환하실 금액 : "+ShortLoanBalance(lp.getLoanMember().get(index).getLoanAmount()));
				} else {													// 장기 대출 전액 상환
					System.out.println("상환하실 금액 : "+LongLoanBalance(lp.getLoanMember().get(index).getLoanAmount()));
				}
				
				while(true) {
					System.out.println("1. 전액 상환하기");
					System.out.println("2. 대출 메뉴로 돌아가기");
					select = 0;
					
					try {
						select = sc.nextInt();
						if(!(select>0 && select<3)) {
							Exception e = new Exception(String.valueOf(select));
							throw e;
						}
					} catch(Exception e) {
						System.out.println("메뉴입력이 잘못되었습니다.");
						sc.nextLine();
						continue;
					}
					break;
				}
				
				if(select == 1) {	// 전액 상환하기
					if(lp.getLoanMember().get(index).getLoanPeriod().equals("1년")) {	// 단기 대출 전액 상환
						
						// 계좌 잔액 차감
						ac.setBalance(ac.getBalance()-lp.getLoanMember().get(index).getLoanBalance());
						
						// 대출 잔액 전액 차감 -> 리스트 삭제
						lp.getLoanMember().remove(index);
						
						System.out.println("대출 기간 : 단기 대출(1년)");
						System.out.println("대출 전액 상환이 완료되었습니다.");
					} else {													// 장기 대출 전액 상환
						
						// 계좌 잔액 차감
						ac.setBalance(ac.getBalance()-lp.getLoanMember().get(index).getLoanBalance());
						
						// 대출 잔액 전액 차감 -> 리스트 삭제
						lp.getLoanMember().remove(index);
						
						System.out.println("대출 기간 : 장기 대출(5년)");
						System.out.println("대출 전액 상환이 완료되었습니다.");
					}	// 상환 end
				} else {			// 대출 메뉴로 돌아가기
					return;
				}
				break;	// switch break
		}
		
	} // 대출금 상환 메서드 end
	

}
