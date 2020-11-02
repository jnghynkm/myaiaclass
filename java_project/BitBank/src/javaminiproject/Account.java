package javaminiproject;

import java.util.ArrayList;

public class Account {

    private String AccountNumber; // 계좌번호
	 private String name;         // 이름
	 private String password;     // 계좌 비밀번호
	 private long balance;		  // 잔액
	 private ArrayList<String> list = new ArrayList<>();	// 거래내역
	 private static Account AccountArray[] = new Account[100];
	 
	 //생성자
	 public Account (String accountName, String name, String pw) {      
	 this.AccountNumber = AccountNumber;
	 this.name = name;
	 this.password = password;
	 this.balance = balance;
	 }

	 
	//getter & setter 메서드
	 
	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String AccountNumber) {
		this.AccountNumber = AccountNumber;
	}

	public String getAccountName() {
		return name;
	}

	public void setAccountName(String name) {
		this.name = name;
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

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}
	 
	
	 
	 
}