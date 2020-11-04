package javaminiproject;

public class BitBankManager implements Util {

	AccountMain account = new AccountMain();
	// LoanManager loan = new LoanManager();

	// BitBank 메인화면
	void startBankMenu() {

		LoanManager loan = new LoanManager();

		while (true) {
			System.out.println("안녕하세요! 은행 업무 선택 메뉴입니다.");
			System.out.println("어떤 은행 업무를 선택하시겠습니까?");
			System.out.println("=======================");
			System.out.println(" 1. 계좌 업무 ");
			System.out.println(" 2. 대출 업무 ");
			System.out.println(" 3. 이전 단계로");
			System.out.println("=======================");

			int select = 0;
			select = SC.nextInt();

			switch (select) {
			case 1:
				System.out.println("계좌 업무 화면으로 이동합니다>>");
				account.startAccountMenu();
				break;
			case 2:
				System.out.println("대출 업무 화면으로 이동합니다>>");
				loan.startLoanMenu();
				break;
			case 3:
				System.out.println("이전 단계로 이동합니다>>");
				return;
			}
		}
	}

}