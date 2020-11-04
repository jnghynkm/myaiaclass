package javaminiproject;

import java.util.StringTokenizer;

public class AccountManager implements Util{

   private String AccountNumber;   // 계좌번호
   private String password;   // 계좌 비밀번호
   private long balance;   // 잔액
   private static Account accountArray[] = new Account[100]; // 계좌생성 배열
   private int cnt; //배열에 저장된 요소의 개수
   private static Transaction transaction[];   // 거래내역
   private int totalTrans;   // 거래횟수

	   private AccountManager(int num) {
	      accountArray = new Account[num];   //생성자의 매개변수의 인자를 전달 받아 배열 생성
	      transaction = new Transaction[100];
	      cnt = 0;                       
	   }
	
	   private void addInfor(Account info) {
	      accountArray[cnt++] = info;
	   }
	
	   private static AccountManager manager = new AccountManager(100);
	
	   public static AccountManager getInstance() {
	      return manager;
	   }

	   //계좌 생성
	   public void CreateAccount() {
	      for (int i = 0; i<accountArray.length; i++) {
	         System.out.println("========================== ");
	         System.out.println("계좌번호를 생성하겠습니다");
	         System.out.println("계좌번호를 (****-****-****-****)형식으로 입력해주세요.");
	         System.out.println("계좌 번호 :  ");
	         String AccountNumber = SC.next();
	         SC.nextLine();
	         if((token(AccountNumber) == false)){
	            return;
	         }
	         if (FindAccount_Nu(AccountNumber) != null) { // 계좌번호가 중복될시
	            System.out.println("※ 계좌번호가 중복됩니다. ");
	            System.out.println("※ 다시 확인하시기 바랍니다. ");
	            return;
	         } else {
	            System.out.println("계좌주(문자만 입력해주세요.)");
	            System.out.print(" : ");
	            String AccountName = SC.nextLine();
	            name(AccountName);
	            if(name(AccountName)==true) {
	               System.out.println("비밀번호(4자리 숫자로 입력해주세요.)");
	               System.out.print(" : ");
	               String password = SC.nextLine();
	               number(password);
	
	               if(number(password)==true) {
	                  addInfor(new Account(AccountNumber, AccountName, password));
	                  System.out.println(cnt);
	                  System.out.println("============================================================================");
	                  System.out.println("*" + FindAccount_Nu(AccountNumber).getAccountName() + "님의 계좌가 정상적으로 개설되었습니다.");
	                  System.out.println("[계좌 주: " + FindAccount_Nu(AccountNumber).getAccountName() + "] , [계좌 번호: " + FindAccount_Nu(AccountNumber).getAccountNumber() + "] 입니다.");    
	                  System.out.println("========================================");
	                  System.out.println("※ 위 내용을 확인 바랍니다. \r");
	                  return;
	
	               }
	            }
	            else {
	               return;
	            }
	         }
	
	      }
	      System.out.println("※ 계좌를 개설 하실 수 없습니다.");
	   }

	   // 계좌주, 한글과 영문만 입력
	   public boolean name(String nameInput) {
	      char index = 0;
	      for(int i=0; i<nameInput.length(); i++) {
	         char CharInput = nameInput.charAt(i);
	         if((CharInput>=0x41&&CharInput<=0x5A)||(CharInput>=0x61&&CharInput<=0x7A)
	               ||(CharInput>0x3130&&CharInput<0x318F)||(CharInput>=0xAC00&&CharInput<=0xD7A3)) {
	            index = CharInput;
	         }
	         else {
	            System.out.println("잘못된 형식입니다. 계좌주를 다시 입력해주세요.");
	            return false;
	         }
	      }
	      nameInput = String.valueOf(index);
	      return true;
	   }

	   // 비밀번호 4자리, 숫자만 입력
	   public boolean number(String passwordInput) {
	      int index = 0;
	      if(passwordInput.length()>=5) {
	         System.out.println("비밀번호를 4자리만 입력해주세요.");
	         return false;
	      } else if(passwordInput.length()==4){
	         for(int i=0; i<4; i++) {
	            char CharInput = passwordInput.charAt(i);
	            if(CharInput>=0x30&&CharInput<=0x39) {
	               index = (int)CharInput;
	            }
	            else {
	               System.out.println("잘못된 형식입니다. 비밀번호를 다시 입력해주세요.");
	               return false;
	            }
	         }
	         passwordInput = String.valueOf(index);
	         return true;
	      }
	      else {
	         System.out.println("비밀번호를 4자리 입력해주세요.");
	         return false;
	      }
	   }

	   // 계좌번호 입력
	   public boolean token(String numberInput) {
	      if(!(numberInput.length()==19)){
	         System.out.println("잘못된 형식의 계좌번호 입니다.");
	         System.out.println("계좌번호를 (****-****-****-****)형식으로 입력해주세요.");
	         System.out.println();
	         return false;
	      }
	      StringTokenizer a1 = new StringTokenizer(numberInput, "-");
	
	      if(a1.countTokens()==4) {
	         int index = 0;
	         for(int i=0; i<4; i++) {
	
	            char CharInput = numberInput.charAt(i);
	            if(CharInput>=0x30&&CharInput<=0x39) {
	               index = (int)CharInput;
	            } else {
	            System.out.println("계좌번호를 (****-****-****-****)형식으로 입력해주세요.");
	            System.out.println("*는 숫자입니다.");
	            return false;
	            }
	
	         }
	         numberInput = String.valueOf(index);
	         return true;
	      }   
	
	      System.out.println("-를 포함해주세요.");
	      System.out.println("잘못된 형식의 계좌번호 입니다.");
	      System.out.println("계좌번호를 (****-****-****-****)형식으로 입력해주세요.");
	      System.out.println();
	      return false;
	   }

    
   
	   // 비밀번호 검색
	   public Account FindAccount_Nu(String AccountNumber) {
	      for (int i = 0; accountArray[i] != null; i++)
	         if (accountArray[i].getAccountNumber().equals(AccountNumber)) //전달받은 계좌번호와 저장되어 있는 계좌번호가 일치하면
	            return accountArray[i];
	      return null;
	   }
	   
	   
	   // 계좌주 검색
	   	public Account FindAccount_Na(String AccountName) {
	   		for (int i = 0; accountArray[i] != null; i++)
	   			if (accountArray[i].getAccountName().equals(AccountName))
	            return accountArray[i];
	      return null;
	   	}


  
   	
   	

	   // 계좌 조회 
	   public void AccountCheck() {
	
	      System.out.println("[========계 좌 조 회========]");
	      System.out.print("계좌주 : ");
	      String accountName = SC.next();
	      Account account = FindAccount_Na(accountName);
	      if (!accountName.equals(account.getAccountName())) {
	         System.out.println();
	         System.out.println("※ 등록되지 않은 이름입니다.");
	         System.out.println("※ 확인 후 다시 이용바랍니다.");
	         return;
	      }
	      for (int i = 0; accountArray[i] != null; i++)
	         if (account.getAccountName().equals(accountArray[i].getAccountName())) {
	            Account AccountCheck = accountArray[i];
	            if (AccountCheck != null) {
	               System.out.println("=========================");
	               System.out.println((new StringBuilder("     ")).append(AccountCheck.getAccountName()).append(" 님의 계좌 정보").toString());
	               System.out.println("-------------------------");
	               System.out.println((new StringBuilder("조회하신 계좌주\t: ")).append(AccountCheck.getAccountName()).toString());
	               System.out.println((new StringBuilder(String.valueOf(AccountCheck.getAccountName()))).append(" 님의 계좌번호\t: ").append(AccountCheck.getAccountNumber()).toString());
	               System.out.println((new StringBuilder(String.valueOf(AccountCheck.getAccountName()))).append(" 님의 잔 액 \t: ").append(AccountCheck.getBalance()).append(" 원").toString());
	               System.out.println("=========================");
	               System.out.println("※ 등록된 계좌 정보를 확인 하였습니다.\r");
	            }
	
	         }
	
	   } 

  
   
	   //계좌 해지
	   // 사용자에게 계좌번호를 입력 받고 검색한 후 계좌 해지
	   public void deleteAccount() {
	      System.out.println("[========계 좌 삭 제========]");
	      if(cnt==0) {
	         System.out.println("삭제할 정보가 없습니다.");
	         return; 
	      }
	      SC.nextLine();
	      System.out.println("삭제하고자 하는 정보의 계좌번호를 입력해주세요.");
	      String Number = SC.nextLine();
	      SC.nextLine();
	      if((token(Number) == false)){
	         return;
	      }
	      System.out.println("계좌번호의 비밀번호를 입력해주세요.");
	      String Password = SC.nextLine();
	
	      int index = searchIndex(Number, Password); 
	      if(index<0) {
	         System.out.println("찾으시는 계좌번호 "+Number+"의 정보가 존재하지 않습니다.");
	         System.out.println("메뉴로 이동합니다.");
	      } else {
	         // 배열의 요소를 왼쪽으로 shift 
	         for(int i=index; i<cnt-1; i++) {
	            accountArray[i] = accountArray[i+1];
	         }
	         cnt--;  //저장된 개수를 감소
	         System.out.println("요청하신 계좌번호 :"+Number+"가 해지되었습니다.");
	         return;
	      }
	   }
	
	   
	   // 배열의 index를 찾는 메서드
	   private int searchIndex(String Number,String Password) {
	      int index = -1; //정보가 없을 때
	
	      for(int i=0; i<cnt; i++) {
	         if(accountArray[i].getAccountNumber().equals(Number) && accountArray[i].getPassword().equals(Password)) {
	            index = i;
	         }
	      }
	      return index;
	   }

	   
	   // 계좌 입금
	   public void saving() {
	      System.out.println("[========입  금========]");
	      System.out.print("계좌 번호: ");
	      String number = SC.next();
	      if((token(number) == false)){
	         return;
	      }
	      Account account = FindAccount_Nu(number);
	      if(FindAccount_Nu(number) == null) {
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
	            account.setBalance(account.getBalance() + money);   // 잔액+입금 금액
	            transaction[totalTrans++] = new Transaction(number,"입금", money);   // 거래내역 추가
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
	      if((token(number) == false)){
	         return;
	      }
	      Account account = FindAccount_Nu(number);
	      if(FindAccount_Nu(number) == null) {
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
	      } else{
	         System.out.print("출금 금액: \r");
	         long money = SC.nextLong();
	         if (money > account.getBalance()) {
	            System.out.println("잔액이 부족하여 출금할 수 없습니다.");
	            System.out.println();
	         } else {
	            account.setBalance(account.getBalance() - money);   // 잔액-출금 금액
	            transaction[totalTrans++] = new Transaction(number, "출금", money);   // 거래내역 추가
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
	      if((token(number) == false)){
	         return;
	      }
	      Account account = FindAccount_Nu(number);
	      if(FindAccount_Nu(number) == null) {
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
	      } else {
	         System.out.println("보내실 금액: ");
	         long money = SC.nextInt();
	         if (money > account.getBalance()) {
	            System.out.println("잔액이 부족하여 이체할 수 없습니다.");
	            System.out.println();
	         } else {
	            System.out.println("이체할 계좌 : ");
	            String number1 = SC.next();
	            if((token(number1) == false)){
	               return;
	            }
	            Account account1 = FindAccount_Nu(number1);
	
	            if(FindAccount_Nu(number1)==null) {
	               System.out.println("존재하지 않는 계좌입니다.");
	               System.out.println();
	            } else {
	               account.setBalance(account.getBalance() - money);   // account의 잔액-이체금액
	               account1.setBalance(account1.getBalance() + money);   // account1의 잔액+이체금액
	               transaction[totalTrans++] = new Transaction(number, "이체", money);   // 거래내역 추가
	               transaction[totalTrans++] = new Transaction(number1, "입금", money);   // 거래내역 추가
	               System.out.println("현재 잔액은 " + account.getBalance() + "원 입니다.\r");
	               System.out.println();
	            }
	         }
	
	      }
	   }

   
   		// 멤버십 생성 11.04 수정 totaltrans 횟수 바꿈
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
							if (account.getBalance() < 10000 || totalTrans < 1) {
								mbs = "Silver";
								System.out.println(account.getAccountName() + "님의 등급은 " + mbs + "입니다.");
								return mbs;
							} else if (account.getBalance() < 30000 || totalTrans < 2) {
								mbs = "Gold";
								System.out.println(account.getAccountName() + "님의 등급은 " + mbs + "입니다.");
								return mbs;
							} else if (account.getBalance() < 60000 || totalTrans < 3) {
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

		
   //getter & setter 메서드
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

   public Account[] getAccountArray() {
      return accountArray;
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