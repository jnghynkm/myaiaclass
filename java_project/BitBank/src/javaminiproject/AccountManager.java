package javaminiproject;

public class AccountManager implements Util{

	//static int money; // 돈
		private static Account accountArray[] = new Account[100]; //계좌생성 배열
		private int cnt; //배열에 저장된 요소의 개수
		
		private AccountManager(int num) {
			accountArray = new Account[num];   //생성자의 매개변수의 인자를 전달 받아 배열 생성
			cnt = 0;                       
		}
		
		private void addInfor(Account info) {
			accountArray[cnt++] = info;
		}
		
		private static AccountManager manager = new AccountManager(10);
		
		public static AccountManager getInstance() {
			return manager;
		}
		
		//계좌등록
	    public void CreateAccount() {
			for (int i = 0; i < accountArray.length; i++) {
					System.out.println("========================== ");
					System.out.println("계좌번호를 생성하겠습니다");
					System.out.println("새로 만드실 계좌번호를 입력해주세요.");
					System.out.println("계좌 번호 :  ");
					String AccountNumber = SC.next();
					if (FindAccount_Nu(AccountNumber) != null) { //계좌번호가 중복될시
						System.err.println("※ 계좌번호가 중복됩니다. ");
						return;
					} else {
						System.out.println("계좌주 : ");
						String AccountName = SC.next();
						System.out.print("비밀번호 : "); //수정 필요 __숫자 4자리 입력
						String Password = SC.next();
						accountArray[i] = new Account(AccountNumber, AccountName, Password);
					}
						System.out.println("============================================================================");
						System.out.println("*" + FindAccount_Nu(AccountNumber).getAccountName() + "님의 계좌가 정상적으로 개설되었습니다.");
						System.out.println("[계좌 주: " + accountArray[0].getAccountName() + "] , [계좌 번호: " + accountArray[0].getAccountNumber() + "] 입니다.");    
						System.out.println("========================================");
						System.out.println("※ 위 내용을 확인 바랍니다. \r");
						return;
					}
			System.err.println("※ 계좌를 개설 하실 수 없습니다.");
		}
		
	    
		 // 검색된 계좌 객체를 반환하는 메서드
		    public static Account FindAccount_Nu(String AccountNumber) {
		      for (int i = 0; accountArray[i] != null; i++)
				if (accountArray[i].getAccountNumber().equals(AccountNumber)) //전달받은 계좌번호와 저장되어 있는 계좌번호가 일치하면
					return accountArray[i];
			return null;
		    }
		
		    
		    
		
		    // 계좌 조회 
		    public void AccountCheck() {
				String accountName;  //조회할 계좌주
				Account account;     //등록된 계좌
		  
				System.out.println("[   계 좌 조 회    ]");
				if (accountArray[0] == null) {
					System.err.println("※ 등록된 계좌가 없습니다.");
					return;
				}
				System.out.print("조회하실 계좌주 : ");
				accountName = SC.nextLine();
				account = FindAccount_Na(accountName);
				if (!accountName.equals(account.getAccountName())) {
					System.out.println();
					System.err.println("※ 등록된 정보와 일치하지 않습니다.");
					System.err.println("※ 확인 후 다시 이용바랍니다.");
					return;
				} try {
					System.out.print((new StringBuilder(String.valueOf(accountName))).append(" 비밀번호 : ").toString());
					String Password = SC.nextLine();
					if (Password.equals(account.getPassword())) {
						for (int i = 0; accountArray[i] != null; i++)
							if (account.getAccountName().equals(accountArray[i].getAccountName())) {
								Account AccountCheck = accountArray[i];
								if (AccountCheck != null) {
									System.out.println("=========================");
									System.out.println((new StringBuilder("     ")).append(AccountCheck.getAccountName()).append(" 님의 계좌 정보").toString());
									System.out.println("-------------------------");
									System.out.println((new StringBuilder("조회하신 계좌주\t:")).append(AccountCheck.getAccountName()).toString());
									System.out.println((new StringBuilder(String.valueOf(AccountCheck.getAccountName()))).append(" 님의 계좌번호\t:").append(AccountCheck.getAccountNumber()).toString());
									System.out.println((new StringBuilder(String.valueOf(AccountCheck.getAccountName()))).append(" 님의 잔 액 \t:").append(AccountCheck.getBalance()).append(" 원").toString());
									System.out.println("=========================");
									System.out.println("※ 등록된 계좌 정보를 확인 하였습니다.\r");
								}
							}
					} else if (!Password.equals(account.getPassword())) {
						System.err.println("※ 비밀번호가 일치 하지 않습니다.");
						System.err.println("※ 확인 후 다시 이용 바랍니다.");
						return;
					}
				    } catch (Exception e) {
					    System.err.println("등록된 계좌 정보가 존재하지 않습니다.\r");
				    } 
				      return;
			      }

		    
		    public static Account FindAccount_Na(String AccountName) {
				for (int i = 0; accountArray[i] != null; i++)
					if (accountArray[i].getAccountName().equals(AccountName))
						return accountArray[i];
				return null;
			}
	
	
	
	
}
