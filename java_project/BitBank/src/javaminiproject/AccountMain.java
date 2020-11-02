package javaminiproject;

public class AccountMain implements Util {


	AccountManager manager = AccountManager.getInstance();

	Transaction t = Transaction.getInstance();


    // 여기에서 메서드 없이 시작 할 수 없는 것 같음!==================================
	void startAccountMenu(){
	
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

		System.out.println("============================ ");



		int MenuSelect = SC.nextInt();
		SC.nextLine();
	   	 


		if(!(MenuSelect>=1 && MenuSelect<=7)) {

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

		case 3:

			manager.membership();

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

		} 
		return;
	}
}
}