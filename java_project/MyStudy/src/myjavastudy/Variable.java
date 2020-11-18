package myjavastudy;

public class Variable {

	public static void main(String[] args) {

		// 두 변수의 값을 교환
		int x = 10;
		int y = 20;
		int tmp;
		
		System.out.println("교환 전 x : "+x+", y : "+y);
		
		tmp = x;
		x = y;
		y = tmp;
		
		System.out.println("교환 후 x : "+x+", y : "+y);
		
		
		
		
	}

}
