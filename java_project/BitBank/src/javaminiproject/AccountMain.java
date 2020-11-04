package javaminiproject;

import java.util.InputMismatchException;

public class AccountMain implements Util {

	public void startAccountMenu() {
		
		BitBankManager bbm = new BitBankManager();
		AccountManager manager = AccountManager.getInstance();
		Transaction t = Transaction.getInstance();

		while(true) {
			System.out.println(" 안녕하세요. 무엇을 도와드릴까요? ");
			System.out.println("============================ ");
			System.out.println("1. 계좌 생성");
			System.out.println("2. 계좌 조회");
			System.out.println("3. 계좌 해지");
			System.out.println("4. 예금");
			System.out.println("5. 출금");
			System.out.println("6. 이체");
			System.out.println("7. 계좌 거래내역 조회");
			System.out.println("8. 이전 메뉴로 돌아가기");
			System.out.println("============================ ");
			
			System.out.println("\n>> ");

			int MenuSelect = 0;
			
			try {
				
				MenuSelect = SC.nextInt();
				
			if(!(MenuSelect>=1 && MenuSelect<=8)) {
				BadInputException e = new BadInputException(String.valueOf(MenuSelect));
			    throw e;
		    }
			} catch (InputMismatchException | BadInputException e) {
				System.out.println("메뉴입력이 잘못되었습니다. \n다시 선택해주세요 \r");
				SC.nextLine();
				continue;
			} catch (Exception e1) {
				System.out.println("메뉴입력이 잘못되었습니다. \n다시 선택해주세요 \r");
				SC.nextLine();
				continue;
		  	}
		
			
			
			switch (MenuSelect) {
			case 1:
				manager.CreateAccount();
				break;
			case 2:
				manager.AccountCheck();
				break;
			case 3:
				manager.deleteAccount();
				break;
			case 4:
				manager.saving();
				break;
			case 5:
				manager.withdraw();			
				break;
			case 6:
				manager.transfer();			
				break;
			case 7:
				t.trans();
				break;
			case 8:
				return;
			}

		}

	}

}