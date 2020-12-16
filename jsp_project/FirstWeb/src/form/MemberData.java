package form;

// Form 에서 전송되는 데이터를 저장하는 beans 정의
public class MemberData {
		
	// 속성 : 변수는  private
	private String id;			// 사용자의 아이디 
	private String pw;			// 사용자의 비밀번호
	private String name;		// 사용자의 이름
	private String photo;		// 사용자의 사진
	
	// 생성자
	public MemberData() {
	}

	public MemberData(String id, String pw, String name, String photo) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.photo = photo;
	}

	// 각 변수들의 getter/setter 메소드를 정의
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "MemberData [id=" + id + ", pw=" + pw + ", name=" + name + ", photo=" + photo + "]";
	}
	
	



	
	
	
	
	
	
	
	
	
	
	

}
