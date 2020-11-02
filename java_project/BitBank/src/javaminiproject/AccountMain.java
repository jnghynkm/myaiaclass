package javaminiproject;

public class AccountMain implements Util {
	
    public static void main(String[] args) {
    	
    	AccountManager manager = AccountManager.getInstance();
		
		while(true) {
			System.out.println(" 안녕하세요. 무엇을 도와드릴까요? ");
			System.out.println("============================ ");
			System.out.println("1. 계좌 생성");
			System.out.println("2. 계좌 조회");
//			System.out.println("3. 계좌 해지");
//			System.out.println("4. 예금");
//			System.out.println("5. 출금");
//			System.out.println("6. 이체");
//			System.out.println("7. 계좌 거래내역 조회");

			System.out.println("============================ ");
		
		
			int MenuSelect = SC.nextInt();
			
			if(!(MenuSelect>=1 && MenuSelect<7)) {
				System.out.println("메뉴의 선택이 옳바르지 않습니다.\n다시 선택해주세요");
				continue;
			}
			switch (MenuSelect) {
			case 1:
				manager.CreateAccount();
				break;
			case 2:
				manager.AccountCheck();
				break;
		/*	case 3:
				
				break;
			case 4:
				
				break;
			case 5:
			
				break;
			case 6:
			
				break; */
			case 7:
				System.out.println("계좌 서비스를 종료합니다.");
				return;
			}
		
		}
		
    }

    
    
	
}
