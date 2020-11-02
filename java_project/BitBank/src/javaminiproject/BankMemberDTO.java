package javaminiproject;

public class BankMemberDTO implements Util{

	 private static String Name;
	    private static String Password;
	    private static String PhoneNum;
	    
	    public BankMemberDTO(String name, String password, String phoneNum) {
	        Name = name;
	        Password = password;
	        PhoneNum = phoneNum;
    
	    }	 
	 

		public static String getName() {
			return Name;
		}



		public void setName(String name) {
			Name = name;
		}



		public static String getPassword() {
			return Password;
		}



		public void setPassword(String password) {
			Password = password;
		}



		public static String getPhoneNum() {
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
	
	
