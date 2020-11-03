package javaminiproject;

public class AccountManager implements Util {

	private String AccountNumber; // 계좌번호
	private String password; // 계좌 비밀번호
	private long balance; // 잔액
	private static Account accountArray[] = new Account[100]; // 계좌생성 배열
	private int cnt; // 배열에 저장된 요소의 개수
	private static Transaction transaction[] = new Transaction[100]; // 거래내역
	private int totalTrans; // 거래횟수

	private AccountManager(int num) {
		accountArray = new Account[num]; // 생성자의 매개변수의 인자를 전달 받아 배열 생성
		cnt = 0;
	}
	
	// @테스트 생성자 (추후 삭제)
	public AccountManager() {
		accountArray[0] = new Account("1111", "김태희", "1234");
	}

	private void addInfor(Account info) {
		accountArray[cnt++] = info;
	}

	private static AccountManager manager = new AccountManager(10);

	public static AccountManager getInstance() {
		return manager;
	}

	// 계좌등록
	public void CreateAccount() {
		for (int i = 0; i < accountArray.length; i++) {
			System.out.println("========================== ");
			System.out.println("계좌번호를 생성하겠습니다");
			System.out.println("새로 만드실 계좌번호를 입력해주세요.");
			System.out.println("계좌 번호 :  ");
			String AccountNumber = SC.next();
			Account account = FindAccount_Nu(AccountNumber);
			SC.nextLine();
			if (FindAccount_Nu(AccountNumber) != null) { // 계좌번호가 중복될시
				System.out.println("※ 계좌번호가 중복됩니다. ");
				return;
			} else {
				System.out.println("계좌주 : ");
				String AccountName = SC.nextLine();
				System.out.print("비밀번호 : "); // 수정 필요 __숫자 4자리 입력
				String Password = SC.nextLine();
				addInfor(new Account(AccountNumber, AccountName, Password));
			}
			System.out.println("============================================================================");
			System.out.println("*" + FindAccount_Nu(AccountNumber).getAccountName() + "님의 계좌가 정상적으로 개설되었습니다.");
			System.out.println("[계좌 주: " + accountArray[i].getAccountName() + "] , [계좌 번호: "
					+ accountArray[i].getAccountNumber() + "] 입니다.");
			System.out.println("========================================");
			System.out.println("※ 위 내용을 확인 바랍니다. \r");
			return;
		}
		System.out.println("※ 계좌를 개설 하실 수 없습니다.");
	}

	// 검색된 계좌 객체를 반환하는 메서드
	public Account FindAccount_Nu(String AccountNumber) {
		for (int i = 0; accountArray[i] != null; i++)
			if (accountArray[i].getAccountNumber().equals(AccountNumber)) // 전달받은 계좌번호와 저장되어 있는 계좌번호가 일치하면
				return accountArray[i];
		return null;
	}

	// 계좌 조회
	public void AccountCheck() {

		System.out.println("[========계 좌 조 회========]");
		System.out.print("계좌 번호: ");
		String number = SC.next();
		Account account = FindAccount_Nu(number);
		if (FindAccount_Nu(number) == null) {
			System.out.println("존재하지 않는 계좌입니다.");
			System.out.println("계좌번호를 다시 확인하시기 바랍니다.");
			System.out.println();
			return;
		}
		System.out.print("계좌주 : ");
		String accountName = SC.next();
		account = FindAccount_Na(accountName);
		if (!accountName.equals(account.getAccountName())) {
			System.out.println();
			System.out.println("※ 등록된 정보와 일치하지 않습니다.");
			System.out.println("※ 확인 후 다시 이용바랍니다.");
			return;
		}
		System.out.println("비밀번호 입력 : ");
		String password = SC.next();
		if (account != null) {
			if (!password.equals(account.getPassword())) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				System.out.println("확인 후 이용 바랍니다.");
				System.out.println();
			} else {
				for (int i = 0; accountArray[i] != null; i++)
					if (account.getAccountName().equals(accountArray[i].getAccountName())) {
						Account AccountCheck = accountArray[i];
						if (AccountCheck != null) {
							System.out.println("=========================");
							System.out.println((new StringBuilder("     ")).append(AccountCheck.getAccountName())
									.append(" 님의 계좌 정보").toString());
							System.out.println("-------------------------");
							System.out.println((new StringBuilder("조회하신 계좌주\t:")).append(AccountCheck.getAccountName())
									.toString());
							System.out.println((new StringBuilder(String.valueOf(AccountCheck.getAccountName())))
									.append(" 님의 계좌번호\t:").append(AccountCheck.getAccountNumber()).toString());
							System.out.println((new StringBuilder(String.valueOf(AccountCheck.getAccountName())))
									.append(" 님의 잔 액 \t:").append(AccountCheck.getBalance()).append(" 원").toString());
							System.out.println("=========================");
							System.out.println("※ 등록된 계좌 정보를 확인 하였습니다.\r");
						}

					}
			}
		}
	}

	public static Account FindAccount_Na(String AccountName) {
		for (int i = 0; accountArray[i] != null; i++)
			if (accountArray[i].getAccountName().equals(AccountName))
				return accountArray[i];
		return null;
	}

	// 계좌 입금
	public void saving() {
		System.out.println("[========입  금========]");
		System.out.print("계좌 번호: ");
		String number = SC.next();
		Account account = FindAccount_Nu(number);
		if (FindAccount_Nu(number) == null) {
			System.out.println("존재하지 않는 계좌입니다.");
			System.out.println("계좌번호를 다시 확인하시기 바랍니다.");
			System.out.println();
			return;
		}
		System.out.println("비밀번호 입력 : ");
		String password = SC.next();
		if (account != null) {
			if (!password.equals(account.getPassword())) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				System.out.println("확인 후 이용 바랍니다.");
				System.out.println();
			} else {
				System.out.print("입금 금액 : ");
				long money = SC.nextLong();
				System.out.println();
				account.setBalance(account.getBalance() + money); // 잔액+입금 금액
				transaction[totalTrans++] = new Transaction("입금", money); // 거래내역 추가
				System.out.println("계좌에 " + money + "원이 입금되었습니다.");
				System.out.println("현재 잔액은 " + account.getBalance() + "원 입니다.\r");
				System.out.println();
			}
		}
	}

	// 계좌 출금
	public void withdraw() {
		System.out.println("[========출  금========]");
		System.out.print("계좌 번호: ");
		String number = SC.next();
		Account account = FindAccount_Nu(number);
		if (FindAccount_Nu(number) == null) {
			System.out.println("존재하지 않는 계좌입니다.");
			System.out.println("계좌번호를 다시 확인하시기 바랍니다.");
			System.out.println();
			return;
		}
		System.out.println("비밀번호 입력 : ");
		String password = SC.next();
		if (!password.equals(account.getPassword())) {
			System.out.println("입력하신 비밀번호가 일치하지 않습니다.");
			System.out.println("확인 후 이용 바랍니다.");
			System.out.println();
		}
		if (account != null) {
			System.out.print("출금 금액: \r");
			long money = SC.nextLong();
			if (money > account.getBalance()) {
				System.out.println("잔액이 부족하여 출금할 수 없습니다.");
				System.out.println();
			} else {
				account.setBalance(account.getBalance() - money); // 잔액-출금 금액
				transaction[totalTrans++] = new Transaction("출금", money); // 거래내역 추가
				System.out.println("계좌에서 " + money + "원이 출금되었습니다.");
				System.out.println("현재 잔액은 " + account.getBalance() + "원 입니다.\r");
				System.out.println();
			}
		}

	}

	// 계좌 이체
	public void transfer() {
		System.out.println("[========이  체========]");
		System.out.print("계좌 번호: ");
		String number = SC.next();
		Account account = FindAccount_Nu(number);
		if (FindAccount_Nu(number) == null) {
			System.out.println("존재하지 않는 계좌입니다.");
			System.out.println("계좌번호를 다시 확인하시기 바랍니다.");
			System.out.println();
			return;
		}
		System.out.println("비밀번호 입력 : ");
		String password = SC.next();
		if (!password.equals(account.getPassword())) {
			System.out.println("입력하신 비밀번호가 일치하지 않습니다.");
			System.out.println("확인 후 이용 바랍니다.");
			System.out.println();
		}
		System.out.println("보내실 금액: ");
		long money = SC.nextInt();
		if (money > account.getBalance()) {
			System.out.println("잔액이 부족하여 이체할 수 없습니다.");
			System.out.println();
		} else {
			System.out.println("이체할 계좌 : ");
			String number1 = SC.next();
			Account account1 = FindAccount_Nu(number1);

			if (FindAccount_Nu(number1) == null) {
				System.out.println("존재하지 않는 계좌입니다.");
				System.out.println();
			}
			account.setBalance(account.getBalance() - money); // account의 잔액-이체금액
			account1.setBalance(account1.getBalance() + money); // account1의 잔액+이체금액
			transaction[totalTrans++] = new Transaction("이체", money); // 거래내역 추가
			System.out.println("현재 잔고는 " + account.getBalance() + "원 입니다.\r");
			System.out.println();
		}

	}

	// 멤버십 생성 11.02 1차 수정 11.03 2차 수정
		public String membership(String mbs) {

			System.out.println("계좌주를 입력하세요 : ");
			String accountName = SC.next();
			Account account = FindAccount_Na(accountName);
			if (FindAccount_Na(accountName) == null) {
				System.out.println("존재하지 않는 이름입니다.");
				System.out.println("이름을 다시 확인하시기 바랍니다.");
				System.out.println();
				return mbs;
			} else if (account != null) {
				for (int i = 0; accountArray[i] != null; i++)
					if (account.getAccountName().equals(accountArray[i].getAccountName())) {
						Account AccountCheck = accountArray[i];
						if (AccountCheck != null) {
							if (account.getBalance() < 10000 || totalTrans < 3) {
								mbs = "Silver";
								System.out.println(account.getAccountName() + "님의 등급은 " + mbs + "입니다.");
								return mbs;
							} else if (account.getBalance() < 30000 || totalTrans < 6) {
								mbs = "Gold";
								System.out.println(account.getAccountName() + "님의 등급은 " + mbs + "입니다.");
								return mbs;
							} else if (account.getBalance() < 60000 || totalTrans < 9) {
								mbs = "Dia";
								System.out.println(account.getAccountName() + "님의 등급은 " + mbs + "입니다.");
								return mbs;
							} else {
								mbs = "Platinum";
								System.out.println(account.getAccountName() + "님의 등급은 " + mbs + "입니다.");
								return mbs;

							}
						}
					}
				
			}
			return mbs;
		}

	// getter & setter 메서드
	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public static Account[] getAccountArray() {
		return accountArray;
	}

	public static void setAccountArray(Account[] accountArray) {
		AccountManager.accountArray = accountArray;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public static Transaction[] getTransaction() {
		return transaction;
	}

	public static void setTransaction(Transaction[] transaction) {
		AccountManager.transaction = transaction;
	}

	public int getTotalTrans() {
		return totalTrans;
	}

	public void setTotalTrans(int totalTrans) {
		this.totalTrans = totalTrans;
	}

	public static AccountManager getManager() {
		return manager;
	}

	public static void setManager(AccountManager manager) {
		AccountManager.manager = manager;
	}
}