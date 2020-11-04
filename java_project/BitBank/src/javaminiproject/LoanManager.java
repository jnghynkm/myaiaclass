package javaminiproject;

public class LoanManager implements Util {
	
	BitBankManager bbm = new BitBankManager();
	JudgeQualification jq = new JudgeQualification();
	LoanRepay lr = new LoanRepay();
	LoanInfor li = new LoanInfor();
	
	
	// 대출 메뉴
	void startLoanMenu() {
		
		LoanProgress lp = LoanProgress.getInstance();
		
	       while(true) {
	    	   
	    	   int select = 0;
	    	   
	    	   System.out.println("안녕하세요 대출관리 메뉴입니다. 무엇을 도와드릴까요>?");
	    	   System.out.println("====================================");
	    	   System.out.println(" 1. 대출 자격 심사");
	    	   System.out.println(" 2. 대출 실행");
	    	   System.out.println(" 3. 대출금 상환 ");
	    	   System.out.println(" 4. 대출 내역 확인");
	    	   System.out.println(" 5. 이전 단계 ");
	    	   System.out.println("====================================");
	    	   	     	              
	     
	       
		       select = SC.nextInt();        
		       SC.nextLine();
		       
		       switch(select) {
		       case 1 :     
		    	   jq.showPossibleAmound(); 
		    	   break;
			   case 2 :    				   
				   lp.executeLoan();	 
				   break;
			   case 3 : 
				   lr.LoanRepay();
				   break;
			   case 4 :
				   li.ShowLoanInfor();
				   break;	
			   case 5 :
				   return;
			   }
		
	       }
	       
		
		
		}

}