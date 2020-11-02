package javaminiproject;

public class LoanManager implements Util{
	
	// 어디에다 넣을지 고민중! 이대로 실행하면 return이 작동을 안함..

	
	AccountMain account = new AccountMain();		
	JudgeQualification jq = new JudgeQualification();
	LoanRepay lr = new LoanRepay();
	LoanInfor li = new LoanInfor();
	
	 // 1차 분기
  void startBankMenu(){   
 
    while(true) {		    	   
   	 System.out.println("안녕하세요! 은행 업무 선택 메뉴입니다."); 
   	 System.out.println("어떤 은행 업무를 선택하시겠습니까?");
   	 System.out.println("=======================");
   	 System.out.println(" 1. 계좌 업무 ");
   	 System.out.println(" 2. 대출 업무 ");
   	 System.out.println(" 3. 이전 단계로");
   	 System.out.println("=======================");
   	  
   	 
   	 int select = 0;
   	 select = SC.nextInt();
   	 
   	 switch(select) {
   	 case 1 :
   		 System.out.println("계좌 업무 화면으로 이동합니다>>");
   		 account.startAccountMenu();
   		 break;
   	 case 2 :
   		 System.out.println("대출 업무 화면으로 이동합니다>>");
   		 startLoanMenu();
   		 break;
   	 case 3 :
   		 System.out.println("이전 단계로 이동합니다>>");
   		 return;
   	 }
   	 return;
     }
  } 
 
    

    
    // 2차 분기
	void startLoanMenu() {
	
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
		   //loan.executeLoan();	 
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