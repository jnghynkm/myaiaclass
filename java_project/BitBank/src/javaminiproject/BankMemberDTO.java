package javaminiproject;

public class BankMemberDTO implements Util{

	 private String Name;

	    private String Password;

	    private String PhoneNum;

	    

	    public BankMemberDTO(String name, String password, String phoneNum) {

	        Name = name;

	        Password = password;

	        PhoneNum = phoneNum;

	    }


	   public String getName() {

	      return Name;

	   }







	   public void setName(String name) {

	      Name = name;

	   }







	   public String getPassword() {

	      return Password;

	   }







	   public void setPassword(String password) {

	      Password = password;

	   }







	   public String getPhoneNum() {

	      return PhoneNum;

	   }







	   public void setPhoneNum(String phoneNum) {

	      PhoneNum = phoneNum;

	   }

	   @Override

	    public String toString() {

	        return "이름 : " + Name + " 비밀번호 : " + Password + " 전화번호 : " + PhoneNum;

	    }

	    
	
	}
	