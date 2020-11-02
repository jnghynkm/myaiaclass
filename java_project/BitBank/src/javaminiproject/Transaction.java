package javaminiproject;

import java.util.Calendar;

public class Transaction implements Util {
	
	private String transType; // 입금 or 출금 or 이체

	private long amount; // 거래금액



	// 내부에서 인스턴스 생성

	private static Transaction t = new Transaction(100);



	// 생성자 호출 제한

	private Transaction(int i) {



	}



	// 외부에서 참조변수를 받을 수 있는 메서드

	public static Transaction getInstance() {

		return t;

	}	



	public Transaction(String type, long money){

		

		this.transType = type; //거래종류

		this.amount = money; //거래금액



	}



	public void trans() {

		AccountManager manager = AccountManager.getInstance();



		System.out.println("[===거래내역 조회===]");

		System.out.print("계좌 번호: ");

		String Accountnumber = SC.next();

		Account account = manager.FindAccount_Nu(Accountnumber);

		if(manager.FindAccount_Nu(Accountnumber) == null) {

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

				System.out.println("고객님의 계좌 : "+ Accountnumber + "의 거래내역입니다.");

				Transaction ts[] = manager.getTransaction();

				for (int i = 0; i < manager.getTotalTrans(); i++) {

					System.out.println(ts[i].getAmount() + " 원" +  ts[i].getTransType());

				}

				System.out.println("계좌 잔액 : " + account.getBalance());

				System.out.println();

			}

		}

	}



	//getter & setter 메서드

	public String toString() {

		return transType;

	}



	public String getTransType() {

		return transType;

	}



	public void setTransType(String transType) {

		this.transType = transType;

	}



	public long getAmount() {

		return amount;

	}



	public void setAmount(long amount) {

		this.amount = amount;

	}
}