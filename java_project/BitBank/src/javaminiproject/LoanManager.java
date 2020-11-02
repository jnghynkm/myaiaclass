package javaminiproject;

public class LoanManager implements Util{

	
	JudgeQualification loan1 = new JudgeQualification();
	LoanProgress loan2 = new LoanProgress();
    LoanRepay loan3 = new LoanRepay();
    LoanInfor loan4 = new LoanInfor();
	
	void startLoanMenu() {
	
       while(true) {
    	   
    	   int select = 0;
    	   
    	   SC.nextLine();
    	   
    	   System.out.println("안녕하세요 대출관리 메뉴입니다. 무엇을 도와드릴까요>?");
    	   System.out.println("====================================");
    	   System.out.println(" 1. 대출 자격 심사");
    	   System.out.println(" 2. 대출 실행");
    	   System.out.println(" 3. 대출금 상환 ");
    	   System.out.println(" 4. 대출 내역 확인");
    	   System.out.println("====================================");
    	   	     	               
       select = SC.nextInt();  
       
       SC.nextLine();
            
       switch(select) {
       case 1 :     
    	   loan1.showPossibleAmound(); 
    	   break;
	   case 2 :    				   
		   loan2.executeLoan();	 
		   break;
	   case 3 : 
		   loan3.LoanRepay();
		   break;
	   case 4 :
		   loan4.ShowLoanInfor();
		   break;
	   
	   }
	 break;
	}
}	
	
	
}

