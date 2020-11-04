package javaminiproject;

import java.util.Calendar;

public class Transaction implements Util {
   private String transactionDate; // 거래일자
   private String transactionTime; // 거래시간
   private String transType; // 입금 or 출금 or 이체
   private long amount; // 거래금액
   private String AccountNumber; // 거래번호

   // 내부에서 인스턴스 생성
   private static Transaction t = new Transaction(100);

   // 생성자 호출 제한
   private Transaction(int i) {

   }

   // 외부에서 참조변수를 받을 수 있는 메서드
   public static Transaction getInstance() {
      return t;
   }

   public Transaction(String AccountNumber, String type, long money) {
      Calendar c = Calendar.getInstance();
      c.add(Calendar.MONTH, 1);
      transactionDate = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DATE);
      transactionTime = c.get(Calendar.HOUR_OF_DAY) + "시 " + c.get(Calendar.MINUTE) + "분 " + c.get(Calendar.SECOND)
            + "초";
      this.AccountNumber = AccountNumber; // 계좌번호
      this.transType = type; // 거래종류
      this.amount = money; // 거래금액
   }

   public void trans() {
      AccountManager manager = AccountManager.getInstance();

      System.out.println("[=====거래내역 조회=====]");
      System.out.print("계좌 번호: ");
      String Accountnumber = SC.next();
      if((manager.token(Accountnumber) == false)){
         return;
      }
      Account account = manager.FindAccount_Nu(Accountnumber);
      if (manager.FindAccount_Nu(Accountnumber) == null) {
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
            System.out.println("======================================================");
            System.out.println("고객님의 계좌 " + Accountnumber + "의 거래내역입니다.");
            Transaction ts[] = manager.getTransaction();
            for (int i = 0; i < manager.getTotalTrans(); i++) {
               if (ts[i].AccountNumber.equals(Accountnumber)) {
                  System.out.println(
                        "거래일: " + ts[i].getTransactionDate() + "\t거래시간: " + ts[i].getTransactionTime()
                              + "\t" + ts[i].getAmount() + "원\t" + ts[i].getTransType());
               }
            }
            System.out.println("------------------------------------------------------");
            System.out.println("계좌 잔액 : " + account.getBalance());
            System.out.println("======================================================");
            System.out.println();
         }
      }
   }

   // getter & setter 메서드
   public String toString() {
      return transType;
   }

   public String getTransactionDate() {
      return transactionDate;
   }

   public void setTransactionDate(String transactionDate) {
      this.transactionDate = transactionDate;
   }

   public String getTransactionTime() {
      return transactionTime;
   }

   public void setTransactionTime(String transactionTime) {
      this.transactionTime = transactionTime;
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

   public String getAccountNumber() {
      return AccountNumber;
   }

   public void setAccountNumber(String accountNumber) {
      AccountNumber = accountNumber;
   }

}