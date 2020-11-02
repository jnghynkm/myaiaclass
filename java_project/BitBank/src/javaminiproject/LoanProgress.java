package javaminiproject;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import javax.swing.JOptionPane;

public class LoanProgress extends LoanInfor implements Util {
	
		public static boolean check = false;  // 쓰레드 반복 탈출을 위한 논리값
		
		private double possibleAmount;   // 대출 가능 금액
	    private int loanAmount;          // 대출 금액
	    private String loanPeriod;       // 상환기간
	    private String membership;
	    
	    // 대출 고객 정보를 넣을 리스트
	    List<LoanInfor> members2;
	    
	    
	    // 생성자
		public LoanProgress() {	
			new ArrayList<LoanInfor>();
			this.possibleAmount = 0;
	        this.loanAmount = 0;
		    this.loanPeriod = "";
		  
		}
		
		// 리스트에 정보 저장
		void inputArray(LoanInfor l) {
			members2.add(l);
		}
			
		// 단기, 장기 대출 진행 메서드
		public void excuteLoan() {	
			
			int select = 0;
			
			System.out.println("고객님의 이름을 입력해주세요.");
			String name = SC.nextLine();
			
			while(true) {
			
			// 1)멤버쉽이 플래티넘 미만일 경우, 2) 이미 대출을 받은 경우에는 진행할 수 없도록 예외처리
	        if(membership != "platinum") { //멤버쉽 기능이 완성되면 처리
				System.out.println("멤버쉽 등급을 만족하지 못해 대출을 실행할 수 없습니다. 메뉴로 돌아갑니다.");
				return;
			} 
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
			
			
			try {				
			select = SC.nextInt();		
			if(!(select>=Util.SHORT_LOAN && select<=Util.LONG_LOAN)) { 	
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
	    	break;
	    	
		} //while end
				
			switch(select) {	
			
			       case 1 :
			        System.out.println("단기 대출 화면입니다. 대출 가능한 금액과 금리를 확인해주세요.");
			        executeShortLoan(balance);	
			        inputArray(new LoanInfor(name, loanAmount, loanPeriod, ShortLoanInterest(loanAmount)));
			        break;
			       case 2 :
			    	System.out.println("장기 대출 화면입니다. 대출 가능한 금액과 금리를 확인해주세요.");
			    	executeLongLoan(balance);
			    	inputArray(new LoanInfor(name, loanAmount, loanPeriod, LongLoanInterest(loanAmount)));
			    	break;
			        case 3 :
			    	System.out.println("이전 화면으로 돌아갑니다.");
			    	break;
			    	
			   
			}					
		}			
	
		
		// 단기대출 진행 메서드
		void executeShortLoan(int balance) {	
							
			loanPeriod = "1년"; // 단기 대출 상환 기간
			
			if(60000 <= balance && balance < 100000) {
				possibleAmount = balance*0.5;
			} else if(10000<= balance && balance <150000) {
				possibleAmount = balance*0.7;		
			} else {
				possibleAmount = balance*0.9;
			}		
			System.out.println("( 상환기간 : "+ loanPeriod +", 금리 : "+ getSHORT_INTEREST_RATE() +"% )");				
			System.out.println("대출 가능 금액 : " + possibleAmount +"원.");
			
			//대출 진행을 위한 본인확인 쓰레드
			getPWThread pw = new getPWThread();	
			CountDownThread ct = new CountDownThread();
					
			pw.start();
			ct.start();	
						
			System.out.println("원하시는 금액을 입력해주세요.(가능 금액 : "+ possibleAmount +"원)");		
			loanAmount = SC.nextInt();
			System.out.println("입력 완료>>");
					
			System.out.println("대출이 정상처리되었습니다. 아래 내용을 확인해주세요.");
			System.out.print("이번 달 이자 "+ ShortLoanInterest(loanAmount)+"원을 제외한 금액 ");
			loanAmount = loanAmount-ShortLoanInterest(loanAmount);  //대출금액 - 이번달 이자
			System.out.println(loanAmount+ "원이 고객님의 계좌로 입금될 예정입니다.\n");
				
			
			//계좌부분 완성되면 잔액 + 대출금액 해주기
			
		//	return loanAmount;
	  }
	
		
		
		 
		// 장기대출 진행 메서드
	  void executeLongLoan(int balance) {
		
		  loanPeriod = "5년";  // 장기 대출 상환 기간
		  
		  if(60000 <= balance && balance < 100000) {
				possibleAmount = balance*0.5;
			} else if(10000<= balance && balance <150000) {
				possibleAmount = balance*0.7;		
			} else {
				possibleAmount = balance*0.9;
			}		
		  
			System.out.println("( 상환기간 : "+loanPeriod+ ", 금리 : "+ getLONG_INTEREST_RATE() +"% )");				
			System.out.println("가능금액 : " + possibleAmount +"원.");
			
			//대출 진행을 위한 본인확인 쓰레드
			getPWThread pw = new getPWThread();	
			CountDownThread ct = new CountDownThread();
					
			pw.start();
			ct.start();			
			
			
			System.out.println("원하시는 금액을 입력해주세요.(가능 금액 : "+ possibleAmount +"원)");		
			loanAmount = SC.nextInt();
					
			System.out.println("대출이 정상처리되었습니다. 아래 내용을 확인해주세요.");
			System.out.print("이번 달 이자 "+ LongLoanInterest(loanAmount)+"원을 제외한 금액 ");
			loanAmount = loanAmount-LongLoanInterest(loanAmount); //대출금액 - 이번달 이자
			System.out.println(loanAmount+ "원이 고객님의 계좌로 입금될 예정입니다.\n");
			
			//계좌부분 완성되면 잔액 + 대출금액 해주기
			
			
	   //  return loanAmount;     		
	  }
	  
	}
	
	// 대출 진행 본인 확인 쓰레드
	class getPWThread extends Thread {
	
		@Override
		public void run() {
			System.out.println("본인확인을 위해 비밀번호를 다시 확인합니다.");
		    String password = JOptionPane.showInputDialog("10초 내에 비밀번호를 입력해주세요.");
			BankMemberDAO b1 = new BankMemberDAO();
			for(int i=0; i<b1.members.size();i++) {
				b1.members.get(i).getPassword().equals(password);
				System.out.println("본인 확인이 완료되었습니다. 대출 진행 화면으로 이동합니다.");
				LoanProgress.check = true;
			}		
		}
	}	
	
	
	class CountDownThread extends Thread{
			
			@Override
			public void run() {
				for(int i=10; i>0;i--) {
					if(LoanProgress.check) {	
					return;			
					}
					System.out.println(i);			
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {		
						e.printStackTrace();
					}
				}
				System.exit(0);
			}	

}

