package javaminiproject;

import java.util.ArrayList;
import java.util.List;

public class BankMemberDAO implements Util {
	BitBankManager bbm = new BitBankManager();
	List<BankMemberDTO> members = new ArrayList<>();

	public BankMemberDAO() {
		members.add(new BankMemberDTO("김영", "1234", "01012341234"));
		members.add(new BankMemberDTO("전지현", "8525", "01012345678"));
		members.add(new BankMemberDTO("송혜교", "9855", "01012345678"));
	}

	public void run() {

		int key = 0;

	while ((key = menu()) != 0) {
			switch (key) {
			case 1:
				Login();
				break;
			case 2:
				CreateAccount();
				break;
			case 3:
				FindPw();
				break;
			case 4:
				deleteAccount();
				break;
			case 5:
				System.out.println("프로그램을 종료합니다.");
				return;

			}
		}
	}

	private void Login() {
		SC.nextLine();
		String name = getStrInput("이름을 입력하세요 : ");
		String password = getStrInput("비밀번호를 입력하세요 : ");

		BankMemberDTO member = FindByName(name);
		if (member == null) {
			System.out.println("등록되지 않은 ID입니다.");
		} else if (member.getPassword().equals(password)) {
			System.out.println("[" + member.getName() + "]님께서 로그인 하셨습니다.");
			bbm.startBankMenu();
		} else {
			System.out.println("비밀번호가 틀렸습니다.");
		}
	}

	private void CreateAccount() {
		SC.nextLine();
		String name = getStrInput("이름을 입력하세요 : ");
		String password = getStrInput("비밀번호를 입력하세요 : ");
		String password2 = getStrInput("비밀번호를 재입력하세요 : ");
		String phoneNum = getStrInput("전화번호를 입력하세요 : ");

		if (nameCheck(name)) {
			System.out.println("중복된 이름입니다.");
		} else if (password.equals(password2)) {
			members.add(new BankMemberDTO(name, password, phoneNum));
			System.out.println(name + "님 가입을 축하드립니다.");
		} else {
			System.out.println("비밀번호를 확인해주세요.");
		}

	}

	private boolean nameCheck(String name) {
		boolean check = true;
		BankMemberDTO member = FindByName(name);
		if (member == null)
			check = false;
		return check;
	}

	private BankMemberDTO FindByName(String name) {
		for (BankMemberDTO memberDTO : members) {
			if (memberDTO.getName().equals(name)) {
				return memberDTO;
			}
		}
		return null;
	}

	private boolean phoneNumCheck(String phoneNum) {
		boolean check = true;
		BankMemberDTO member = FindByPhoneNum(phoneNum);
		if (member == null)
			check = false;
		return check;
	}

	private BankMemberDTO FindByPhoneNum(String PhoneNum) {
		for (BankMemberDTO memberDTO : members) {
			if (memberDTO.getPhoneNum().equals(PhoneNum)) {
				return memberDTO;
			}
		}
		return null;
	}

	private boolean passwordCheck(String password) {
		boolean check = true;
		BankMemberDTO member = FindByPassword(password);
		if (member == null)
			check = false;
		return check;
	}

	private BankMemberDTO FindByPassword(String Password) {
		for (BankMemberDTO memberDTO : members) {
			if (memberDTO.getPassword().equals(Password)) {
				return memberDTO;
			}
		}
		return null;
	}

	private void FindPw() {
		SC.nextLine();
		String name = getStrInput("이름을 입력하세요 : ");
		String phoneNum = getStrInput("전화번호를 입력하세요 : ");
		if (!(nameCheck(name)) || !(phoneNumCheck(phoneNum))) {
			System.out.println("회원 정보가 없거나 일치하지 않습니다.");
			System.out.println("이름과 전화번호를 다시 확인해주세요");
		} else {
			for (BankMemberDTO index : members) {
				if (index.getName().equals(name)) {
					System.out.println(name + "님의 비밀번호는" + index.getPassword() + "입니다.");
				}
			}
		}
	}

	private void deleteAccount() {
		SC.nextLine();
		String name = getStrInput("이름을 입력하세요 : ");
		String password = getStrInput("비밀번호를 입력하세요 : ");

		if (!(nameCheck(name)) || !(passwordCheck(password))) {
			System.out.println("정보가 존재하지 않거나 이름이나 비밀번호가 일치하지 않습니다..");
		} else {
			String answer = getStrInput("정말 탈퇴하시겠습니까?(Y/N)");
			if (answer.equals("y") || answer.equals("Y")) {
				members.removeIf(i -> i.getPassword().equals(password) && i.getName().equals(name));
				System.out.println("탈퇴되었습니다.");
			} else if (answer.equals("N") || answer.equals("n")) {
				System.out.println("초기 메뉴로 돌아갑니다.");
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}

	}

	private String getStrInput(String msg) {
		System.out.println(msg);
		return SC.nextLine();
	}

	private int menu() {
		System.out.println("================================================");
		System.out.println("Bit Bank에 오신 것을 환영합니다");
		return getNumInput("[1]로그인 [2]회원가입 [3]비밀번호 찾기 [4]회원 탈퇴 [5]종료");

	}

	private int getNumInput(String msg) {
		System.out.println(msg);
		return SC.nextInt();
	}

}